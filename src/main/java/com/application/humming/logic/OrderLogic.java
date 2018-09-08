package com.application.humming.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.humming.dao.ItemDao;
import com.application.humming.dao.OrderDao;
import com.application.humming.dao.OrderItemDao;
import com.application.humming.entity.ItemEntity;
import com.application.humming.entity.MemberEntity;
import com.application.humming.entity.OrderEntity;
import com.application.humming.entity.OrderItemEntity;
import com.application.humming.form.ShoppingCartForm;
import com.application.humming.type.OrderStatus;

import lombok.NonNull;

/**
 *
 * @author rakus
 *
 */
@Service
public class OrderLogic {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderItemDao orderItemDao;

    /**
     * 未確定の注文情報を取得する.
     *
     * @param Integer memberId
     * @param Integer status
     * @return OrderEntity
     */
    public OrderEntity getUndeterminedOrderInfo(@NonNull final Integer memberId) {
        return orderDao.findByMemberIdAndStatus(memberId, OrderStatus.UNDETERMINED.getCode());
    }

    /**
     * 注文情報を更新する.
     *
     * @param OrderEntity orderInfo
     * @param OrderEntity undeterminedOrderInfo
     * @return OrderEntity
     */
    public OrderEntity updateOrderInfo(@NonNull final OrderEntity orderInfo, final OrderEntity undeterminedOrderInfo) {
        if (undeterminedOrderInfo != null) {
            final Integer orderId = orderInfo.getId();
            final Integer undeterminedOrderId = undeterminedOrderInfo.getId();
            final List<OrderItemEntity> orderItemList = orderItemDao.findByOrderId(orderId);
            final List<OrderItemEntity> undeterminedOrderItemList = orderItemDao.findByOrderId(undeterminedOrderId);

            for (OrderItemEntity orderItem : orderItemList) {
                for (OrderItemEntity undeterminedOrderItem : undeterminedOrderItemList) {
                    if (orderItem.getItemId() == undeterminedOrderItem.getItemId()) {
                        orderItemDao.updateQuantityOfOrderItem(orderId, orderItem.getItemId(), undeterminedOrderItem.getQuantity());
                        orderItemDao.deleteByIdAndItemId(undeterminedOrderId, undeterminedOrderItem.getItemId());
                    }
                }
            }
            orderDao.deleteByPrimaryKey(undeterminedOrderId);

            final Integer totalPrice = undeterminedOrderInfo.getTotalPrice() + orderInfo.getTotalPrice();
            orderDao.updateTotalPrice(orderId, totalPrice);
            orderInfo.setTotalPrice(totalPrice);

            return orderInfo;
        }
        return orderInfo;
    }

    /**
     * カートに商品を追加する.
     *
     * @param ShoppingCartForm shoppingCartForm
     * @param OrderEntity orderEntity
     * @param MemberEntity memberEntity
     * @return
     */
    public OrderEntity putItemIntoShoppingCart(ShoppingCartForm shoppingCartForm, OrderEntity orderEntity, MemberEntity memberEntity) {
        final ItemEntity itemEntity = itemDao.findByPrimaryKey(shoppingCartForm.getItemId());
        final Integer totalPrice = shoppingCartForm.getQuantity() * itemEntity.getPrice();

        final OrderEntity orderInfo = new OrderEntity();
        if (memberEntity == null) {
            orderInfo.setMemberId(0);
            orderInfo.setStatus(OrderStatus.UNDETERMINED.getCode());
            if (orderEntity == null) {
                orderInfo.setTotalPrice(totalPrice);
            } else {
                orderInfo.setId(orderEntity.getId());
                orderInfo.setTotalPrice(totalPrice + orderEntity.getTotalPrice());
            }
        } else {
            orderInfo.setMemberId(memberEntity.getId());
            orderInfo.setStatus(OrderStatus.UNDETERMINED.getCode());
            if (orderEntity == null) {
                orderInfo.setTotalPrice(totalPrice);
            } else {
                orderInfo.setId(orderEntity.getId());
                orderInfo.setTotalPrice(totalPrice + orderEntity.getTotalPrice());
            }
        }

        final OrderEntity newOrder = orderDao.addOrderByCaseGetOrderId(orderInfo);
        shoppingCartForm.setOrderId(newOrder.getId());

        final OrderItemEntity orderItem = orderItemDao.findbyOrderIdAndItemId(newOrder.getId(),shoppingCartForm.getItemId());
        if (orderItem != null) {
            orderItemDao.updateQuantityOfOrderItem(shoppingCartForm.getOrderId(), shoppingCartForm.getItemId(),
                    shoppingCartForm.getQuantity());
        } else {
            orderItemDao.addOrderItem(shoppingCartForm);
        }
        return newOrder;
    }

    /**
     * 商品情報を取得する.
     *
     * @param OrderEntity orderEntity
     * @return List<OrderItemInfoDto>
     */
    public List<ItemEntity> getItemInfo(OrderEntity orderEntity) {
        final List<ItemEntity> itemList = new ArrayList<>();
        final List<OrderItemEntity> orderItemList = orderItemDao.findByOrderId(orderEntity.getId());
        for (final OrderItemEntity orderItem : orderItemList) {
            itemList.add(itemDao.findByPrimaryKey(orderItem.getItemId()));
        }
        return itemList;
    }

    /**
     * 注文商品情報を取得する.
     *
     * @param OrderEntity orderEntity
     * @return List<OrderItemEntity>
     */
    public List<OrderItemEntity> getOrderItemInfo(OrderEntity orderEntity){
        return orderItemDao.findByOrderId(orderEntity.getId());
    }

    /**
     * カートから商品を削除する.
     *
     * @param orderEntity
     * @param shoppingCartForm
     * @return OrderEntity
     */
    public OrderEntity deleteOrderItem(OrderEntity orderEntity, ShoppingCartForm shoppingCartForm) {
        final ItemEntity itemEntity = itemDao.findByPrimaryKey(shoppingCartForm.getItemId());
        final Integer totalPrice = orderEntity.getTotalPrice() - itemEntity.getPrice() * shoppingCartForm.getQuantity();
        orderEntity.setTotalPrice(totalPrice);
        orderItemDao.deleteByOrderIdAndItemId(orderEntity.getId(), shoppingCartForm.getItemId());
        orderDao.updateTotalPrice(orderEntity.getId(), totalPrice);
        return orderEntity;
    }

    /**
     * 注文履歴を取得する.
     *
     * @param MemberEntity memberEntity
     * @return List<OrderEntity>
     */
    public List<OrderEntity> getOrderHistory(MemberEntity memberEntity){
        return orderDao.searchOrderHistory(memberEntity.getId());
    }

    /**
     * 注文商品の履歴を取得する.
     *
     * @param List<OrderEntity> orderEntityList
     * @return List<OrderItemEntity>
     */
    public List<OrderItemEntity> searchOrderItemHistory(List<OrderEntity> orderEntityList){
        final List<OrderItemEntity> orderItemList = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntityList) {
            for (OrderItemEntity orderItem : orderItemDao.findByOrderId(orderEntity.getId())) {
                orderItemList.add(orderItemDao.findbyOrderIdAndItemId(orderItem.getOrderId(),
                        orderItem.getItemId()));
            }
        }
        return orderItemList;
    }

    /**
     * 注文履歴の商品情報を取得する.
     *
     * @param List<OrderItemEntity> orderItemList
     * @return List<ItemEntity>
     */
    public List<ItemEntity> searchItemHistory(List<OrderItemEntity> orderItemList){
        final List<ItemEntity> itemEntityList = new ArrayList<>();
        for(OrderItemEntity orderItem : orderItemList) {
            itemEntityList.add(itemDao.findByPrimaryKey(orderItem.getItemId()));
        }
        for (int i = 0; i < itemEntityList.size(); i++) {
            for (int j = i + 1; j < itemEntityList.size(); j++) {
                if (itemEntityList.get(i).getId() == itemEntityList.get(j).getId()) {
                    itemEntityList.remove(j);
                }
            }
        }
        return itemEntityList;
    }

    /**
     * 注文確定時のstatus（0：購入前 → 1：購入済）の変更
     * @param orderEntity
     */
    public void updateStatusByOrderId(OrderEntity orderEntity) {
        orderDao.updateStatusByOrderId(orderEntity);
    }

}