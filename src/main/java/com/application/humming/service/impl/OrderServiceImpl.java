package com.application.humming.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.application.humming.dto.OrderDto;
import com.application.humming.dto.OrderItemDto;
import com.application.humming.entity.OrderEntity;
import com.application.humming.logic.OrderLogic;
import com.application.humming.service.OrderService;

import lombok.NonNull;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderLogic orderLogic;

    @Override
    public List<OrderItemDto> getOrderItemInfos(@NonNull final Integer orderId) {
        return orderLogic.findOrderItemInfosByOrderId(orderId);
    }

    @Override
    public OrderDto addOrderItem(@NonNull final OrderItemDto orderItemDto) {
        return orderLogic.updateOrderItemInfo(orderItemDto);
    }

    @Override
    public OrderDto deleteOrderItem(@NonNull final OrderDto orderDto, @NonNull final OrderItemDto orderItemDto) {
        return orderLogic.deleteOrderItemInfo(orderDto, orderItemDto);
    }

    @Override
    public void completeOrder(@NonNull final OrderEntity orderEntity, @NonNull final String deliveryTime, @NonNull final String deliverySpecifiedTime) {
        orderLogic.setDeliveryTime(orderEntity, deliveryTime, deliverySpecifiedTime);
        orderLogic.updateStatus(orderEntity);
    }

    @Override
    public List<OrderDto> getOrderHistory(@NonNull final Integer memberId){
        return orderLogic.createOrderedInfoByMemberId(memberId);
    }

    @Override
    public List<OrderItemDto> getOrderItemHistory(@NonNull final List<OrderDto> orderDtoList){
        return orderLogic.createOrderedItemInfo(orderDtoList);
    }
}
