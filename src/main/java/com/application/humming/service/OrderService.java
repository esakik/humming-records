package com.application.humming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.humming.dao.OrderDao;
import com.application.humming.dao.OrderItemDao;
import com.application.humming.entity.OrderEntity;
import com.application.humming.logic.OrderLogic;

import lombok.NonNull;

@Service
public class OrderService {

    @Autowired
    OrderLogic orderLogic;

    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderItemDao orderItemDao;

    /**
     * 前回ログイン時に未確定の注文情報を反映させる.
     *
     * @param Integer memberId
     * @param OrderEntity orderEntity
     * @return OrderEntity
     */
    public OrderEntity updateOrderInfo(@NonNull final Integer memberId, @NonNull final OrderEntity orderInfo) {
        final OrderEntity undeterminedOrderInfo = orderLogic.getUndeterminedOrderInfo(memberId);
        return orderLogic.updateOrderInfo(orderInfo, undeterminedOrderInfo);
    }
}