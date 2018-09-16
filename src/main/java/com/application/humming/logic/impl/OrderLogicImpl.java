package com.application.humming.logic.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.application.humming.constant.HummingConstants;
import com.application.humming.dao.ItemDao;
import com.application.humming.dao.OrderDao;
import com.application.humming.dao.OrderItemDao;
import com.application.humming.dto.ItemDto;
import com.application.humming.dto.MemberDto;
import com.application.humming.dto.OrderDto;
import com.application.humming.dto.OrderItemDto;
import com.application.humming.entity.ItemEntity;
import com.application.humming.entity.OrderEntity;
import com.application.humming.entity.OrderItemEntity;
import com.application.humming.logic.OrderLogic;
import com.application.humming.type.OrderStatus;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderLogicImpl implements OrderLogic {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private HttpSession session;

    @Override
    public List<OrderItemDto> createOrderItemInfoByOrderId(@NonNull final Integer orderId){
        final List<OrderItemDto> orderItemDtoList = new ArrayList<>();

        final List<OrderItemEntity> orderItemEntityList = orderItemDao.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderItemEntityList)) {
            log.info("Order Items are empty, Class: {}, OrderId: {}", OrderLogic.class, orderId);
            return orderItemDtoList;
        }

        final List<ItemEntity> itemEntityList = itemDao.findAll();
        if (CollectionUtils.isEmpty(itemEntityList)) {
            log.info("Items are empty, Class: {}, OrderId: {}", OrderLogic.class, orderId);
            return orderItemDtoList;
        }

        setItemInfo(orderItemDtoList, orderItemEntityList, itemEntityList);

        return orderItemDtoList;
    }

    @Override
    public void setItemInfo(@NonNull final List<OrderItemDto> orderItemDtoList, @NonNull final List<OrderItemEntity> orderItemEntityList, @NonNull final List<ItemEntity> itemEntityList) {
        orderItemEntityList.forEach(orderItemEntity -> {
            final OrderItemDto orderItemDto = new OrderItemDto();
            BeanUtils.copyProperties(orderItemEntity, orderItemDto);

            itemEntityList.forEach(itemEntity -> {
                if (itemEntity.getId() == orderItemEntity.getItemId()) {
                    final ItemDto itemDto = new ItemDto();
                    BeanUtils.copyProperties(itemEntity, itemDto);
                    orderItemDto.setItemDto(itemDto);
                }
            });
            orderItemDtoList.add(orderItemDto);
        });
    }

    @Override
    public OrderDto updateOrderItemInfo(@NonNull final OrderItemDto orderItemDto) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setMemberId(HummingConstants.NON_MEMBER_ID);
        // ログイン状態のとき注文情報に会員IDをセットする
        final MemberDto memberDto = (MemberDto) session.getAttribute("member");
        if (memberDto != null) {
            orderEntity.setMemberId(memberDto.getId());
        }
        // 合計金額を計算する
        final ItemEntity itemEntity = itemDao.findByPrimaryKey(orderItemDto.getItemId());
        final Integer totalPrice = orderItemDto.getQuantity() * itemEntity.getPrice();
        // セッションに注文情報がある場合は注文IDを取得する
        final OrderDto orderDto = (OrderDto) session.getAttribute("order");
        if (orderDto != null) {
            orderEntity.setId(orderDto.getId());
            orderEntity.setTotalPrice(totalPrice + orderDto.getTotalPrice());
        } else {
            orderEntity.setTotalPrice(totalPrice);
        }
        // 注文ステータスはすべて「0: 注文未確定」
        orderEntity.setStatus(OrderStatus.UNDETERMINED.getCode());

        // カートの中身を更新
        orderEntity = orderDao.save(orderEntity);
        orderItemDto.setOrderId(orderEntity.getId());

        // アイテムをカートに追加する
        final OrderItemEntity orderItemEntity = orderItemDao.findbyOrderIdAndItemId(orderEntity.getId(), orderItemDto.getItemId());
        if (orderItemEntity == null) {
            orderItemDao.insert(orderItemDto);
        } else {
            orderItemDao.updateQuantity(orderItemDto.getOrderId(), orderItemDto.getItemId(), orderItemDto.getQuantity());
        }
        return OrderDto.builder()
                .id(orderEntity.getId())
                .memberId(orderEntity.getMemberId())
                .status(orderEntity.getStatus())
                .totalPrice(orderEntity.getTotalPrice())
                .build();
    }

    @Override
    public OrderDto deleteOrderItemInfo(@NonNull final OrderDto orderDto, @NonNull final OrderItemDto orderItemDto) {
        final ItemEntity itemEntity = itemDao.findByPrimaryKey(orderItemDto.getItemId());
        // 合計金額を再計算する
        final Integer totalPrice = orderDto.getTotalPrice() - itemEntity.getPrice() * orderItemDto.getQuantity();
        orderDto.setTotalPrice(totalPrice);
        orderDao.updateTotalPrice(orderDto.getId(), totalPrice);
        // 削除処理を行う
        orderItemDao.deleteByOrderIdAndItemId(orderDto.getId(), orderItemDto.getItemId());
        return orderDto;
    }

    @Override
    public void updateStatus(@NonNull final OrderEntity orderEntity) {
        orderEntity.setOrderDate(new Date());
        orderEntity.setStatus(OrderStatus.DETERMINED.getCode());
        orderDao.save(orderEntity);
    }

    @Override
    public void setDeliveryTime(@NonNull final OrderEntity orderEntity, @NonNull final String deliveryTime, @NonNull final String deliverySpecifiedTime) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date formatDate = new Date();
        final Calendar calendar = Calendar.getInstance();

        try {
            formatDate = sdf.parse(deliveryTime);
            calendar.setTime(formatDate);
            calendar.add(Calendar.HOUR_OF_DAY, Integer.parseInt(deliverySpecifiedTime));

        } catch (ParseException e) {

        }
        orderEntity.setDeliveryTime(new Timestamp(calendar.getTime().getTime()));
    }

    @Override
    public List<OrderDto> createOrderedInfoByMemberId(final Integer memberId){
        final  List<OrderDto> orderDtoList = new ArrayList<>();
        final List<OrderEntity> orderEntityList = orderDao.findByMemberIdAndStatus(memberId, OrderStatus.DETERMINED.getCode());
        orderEntityList.forEach(orderEntity -> {
            final OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(orderEntity, orderDto);
            orderDtoList.add(orderDto);
        });

        return orderDtoList;
    }

    @Override
    public List<OrderItemDto> createOrderedItemInfo(@NonNull final List<OrderDto> orderDtoList){
        final List<OrderItemEntity> orderItemEntityList = new ArrayList<>();

        final List<Integer> orderIdList = orderDtoList.stream().map(OrderDto::getId).collect(Collectors.toList());
        orderIdList.stream().forEach(orderId -> {
            orderItemDao.findByOrderId(orderId).stream().forEach(orderItem -> {
                orderItemEntityList.add(orderItemDao.findbyOrderIdAndItemId(orderItem.getOrderId(), orderItem.getItemId()));
            });
        });

        final List<OrderItemDto> orderItemDtoList = new ArrayList<>();
        orderItemEntityList.stream().forEach(orderItemEntity -> {
            final ItemDto itemDto = new ItemDto();
            final ItemEntity itemEntity = itemDao.findByPrimaryKey(orderItemEntity.getItemId());
            BeanUtils.copyProperties(itemEntity, itemDto);

            orderItemEntity.setItemDto(itemDto);
            final OrderItemDto orderItemDto = new OrderItemDto();
            BeanUtils.copyProperties(orderItemEntity, orderItemDto);

            orderItemDtoList.add(orderItemDto);
        });

        return orderItemDtoList;
    }
}
