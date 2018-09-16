package com.application.humming.logic;

import java.util.List;

import com.application.humming.dto.OrderDto;
import com.application.humming.dto.OrderItemDto;
import com.application.humming.entity.OrderEntity;
import com.application.humming.exception.HummingException;

import lombok.NonNull;

public interface OrderLogic {

    /**
     * 注文アイテム情報を取得する.
     *
     * @param orderId
     * @return List<OrderItemDto>
     */
    List<OrderItemDto> findOrderItemInfosByOrderId(@NonNull final Integer orderId);

    /**
     * 注文情報を生成する.
     *
     * @param orderItemDto
     * @return OrderEntity
     */
    OrderEntity createOrderInfoByOrderItem(@NonNull final OrderItemDto orderItemDto);

    /**
     * 注文情報を追加・更新する.
     *
     * @param orderEntity
     * @return OrderDto
     */
    OrderDto insertOrUpdateOrderInfo(@NonNull final OrderEntity orderEntity);

    /**
     * 注文アイテム情報を追加・更新する.
     *
     * @param orderItemDto
     * @return OrderDto
     */
    void insertOrUpdateOrderItemInfo(@NonNull final OrderItemDto orderItemDto);

    /**
     * カートからアイテムを削除する.
     *
     * @param orderItemDto
     * @return OrderDto
     */
    OrderDto deleteOrderItemInfo(@NonNull final OrderItemDto orderItemDto);

    /**
     * 注文ステータスを更新する.
     *
     * @param orderEntity
     */
    void updateStatus(@NonNull final OrderEntity orderEntity);

    /**
     * 配送時間をセットする.
     *
     * @param orderEntity
     * @param deliveryTime
     * @param deliverySpecifiedTime
     * @throws HummingException
     */
    void setDeliveryTime(@NonNull final OrderEntity orderEntity, @NonNull final String deliveryTime, @NonNull final String deliverySpecifiedTime) throws HummingException;

    /**
     * 確定済みの注文情報を取得する.
     *
     * @param memberId
     * @return List<OrderDto>
     */
    List<OrderDto> findOrderedInfoByMemberId(final Integer memberId);

    /**
     * 確定済みの注文アイテム情報を取得する.
     *
     * @param orderDtoList
     * @return List<OrderItemDto>
     */
    List<OrderItemDto> findOrderedItemInfo(@NonNull final List<OrderDto> orderDtoList);
}