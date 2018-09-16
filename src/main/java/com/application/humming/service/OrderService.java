package com.application.humming.service;

import java.util.List;

import com.application.humming.dto.OrderDto;
import com.application.humming.dto.OrderItemDto;
import com.application.humming.entity.OrderEntity;
import com.application.humming.exception.HummingException;

import lombok.NonNull;

public interface OrderService {

    /**
     * 注文アイテム情報を取得する.
     *
     * @param id
     * @return List<OrderItemDto>
     */
    public List<OrderItemDto> getOrderItemInfos(@NonNull final Integer id);

    /**
     * 買い物かごに注文アイテムを追加する.
     *
     * @param orderItemDto
     * @return OrderDto
     */
    public OrderDto addOrderItem(@NonNull final OrderItemDto orderItemDto);

    /**
     * 買い物かごから注文アイテムを削除する.
     *
     * @param orderItemDto
     * @return OrderDto
     */
    public OrderDto deleteOrderItem(@NonNull final OrderItemDto orderItemDto);

    /**
     * 注文を確定する.
     *
     * @param orderEntity
     * @param deliveryTime
     * @param deliverySpecifiedTime
     * @throws HummingException
     */
    public void orderComplete(@NonNull final OrderEntity orderEntity, @NonNull final String deliveryTime, @NonNull final String deliverySpecifiedTime) throws HummingException;

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