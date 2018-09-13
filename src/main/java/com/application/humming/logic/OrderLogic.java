package com.application.humming.logic;

import java.util.List;

import com.application.humming.dto.OrderDto;
import com.application.humming.dto.OrderItemDto;
import com.application.humming.entity.OrderEntity;

import lombok.NonNull;

public interface OrderLogic {

    /**
     * 注文アイテム情報を取得する.
     *
     * @param id
     * @return List<OrderItemDto>
     */
    List<OrderItemDto> getOrderItemInfo(@NonNull final Integer id);

    /**
     * 注文アイテム情報を更新する.
     *
     * @param orderItemDto
     * @return OrderDto
     */
    OrderDto updateOrderItemInfo(@NonNull final OrderItemDto orderItemDto);

    /**
     * カートからアイテムを削除する.
     *
     * @param orderDto
     * @param orderItemDto
     * @return OrderDto
     */
    OrderDto deleteOrderItemInfo(@NonNull final OrderDto orderDto, @NonNull final OrderItemDto orderItemDto);

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
     */
    void setDeliveryTime(@NonNull final OrderEntity orderEntity, @NonNull final String deliveryTime, @NonNull final String deliverySpecifiedTime);

    /**
     * 確定済みの注文情報を取得する.
     *
     * @param id
     * @return List<OrderDto>
     */
    List<OrderDto> getOrderedInfo(final Integer id);

    /**
     * 確定済みの注文アイテム情報を取得する.
     *
     * @param orderDtoList
     * @return List<OrderItemDto>
     */
    List<OrderItemDto> getOrderedItemInfo(@NonNull final List<OrderDto> orderDtoList);
}