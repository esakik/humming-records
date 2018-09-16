package com.application.humming.service;

import java.util.List;

import com.application.humming.dto.OrderDto;
import com.application.humming.dto.OrderItemDto;
import com.application.humming.entity.OrderEntity;

import lombok.NonNull;

public interface OrderService {

    /**
     * 買い物かごにアイテムを追加する.
     *
     * @param orderItemDto
     * @return OrderDto
     */
    public OrderDto addToCart(@NonNull final OrderItemDto orderItemDto);

    /**
     * 買い物かごからアイテムを削除する.
     *
     * @param orderDto
     * @param orderItemDto
     * @return OrderDto
     */
    public OrderDto deleteOrderItemFromCart(@NonNull final OrderDto orderDto, @NonNull final OrderItemDto orderItemDto);

    /**
     * 注文アイテム情報を取得する.
     *
     * @param id
     * @return List<OrderItemDto>
     */
    public List<OrderItemDto> getOrderItemInfos(@NonNull final Integer id);

    /**
     * 注文を確定する.
     *
     * @param orderEntity
     * @param deliveryTime
     * @param deliverySpecifiedTime
     */
    public void completeOrder(@NonNull final OrderEntity orderEntity, @NonNull final String deliveryTime, @NonNull final String deliverySpecifiedTime);

    /**
     * 注文履歴を取得する.
     *
     * @param id
     * @return List<OrderDto>
     */
    public List<OrderDto> getOrderHistory(@NonNull final Integer id);

    /**
     * 注文アイテム履歴を取得する.
     *
     * @param orderDtoList
     * @return List<OrderItemDto>
     */
    public List<OrderItemDto> getOrderItemHistory(@NonNull final List<OrderDto> orderDtoList);
}