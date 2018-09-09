package com.application.humming.service;

import java.util.List;

import com.application.humming.dto.OrderDto;
import com.application.humming.dto.OrderItemDto;
import com.application.humming.entity.OrderEntity;

import lombok.NonNull;

public interface OrderService {

    /**
     * カート内のアイテムを取得する.
     *
     * @param Integer id
     * @return List<OrderItemDto>
     */
    public List<OrderItemDto> getOrderItemInCart(@NonNull final Integer id);

    /**
     * カートにアイテムを追加する.
     *
     * @param OrderItemDto orderItemDto
     * @return OrderDto
     */
    public OrderDto addToCart(@NonNull final OrderItemDto orderItemDto);

    /**
     * カートからアイテムを削除する.
     *
     * @param OrderDto orderDto
     * @param OrderItemDto orderItemDto
     * @return OrderDto
     */
    public OrderDto deleteOrderItemFromCart(@NonNull final OrderDto orderDto, @NonNull final OrderItemDto orderItemDto);

    /**
     * 注文内容を取得する.
     *
     * @param Integer id
     * @return List<OrderItemDto>
     */
    public List<OrderItemDto> getOrderedItems(@NonNull final Integer id);

    /**
     * 注文を確定する.
     *
     * @param OrderEntity orderEntity
     * @param String deliveryTime
     * @param String deliverySpecifiedTime
     */
    public void completeOrder(@NonNull final OrderEntity orderEntity, @NonNull final String deliveryTime, @NonNull final String deliverySpecifiedTime);

    /**
     * 注文履歴を取得する.
     *
     * @param Integer id
     * @return List<OrderDto>
     */
    public List<OrderDto> getOrderHistory(@NonNull final Integer id);

    /**
     * 注文アイテム履歴を取得する.
     *
     * @param List<OrderDto> orderDtoList
     * @return List<OrderItemDto>
     */
    public List<OrderItemDto> getOrderItemHistory(@NonNull final List<OrderDto> orderDtoList);
}