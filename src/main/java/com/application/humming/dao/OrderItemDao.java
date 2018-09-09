package com.application.humming.dao;

import java.util.List;

import com.application.humming.dto.OrderItemDto;
import com.application.humming.entity.OrderItemEntity;

import lombok.NonNull;

public interface OrderItemDao {

    /**
     * 注文アイテムをを注文IDで検索する.
     *
     * @param Integer orderId
     * @return List<OrderItemEntity>
     */
    List<OrderItemEntity> findByOrderId(@NonNull final Integer orderId);

    /**
     * 注文アイテムを注文IDと商品IDで検索する.
     *
     * @param Integer orderId
     * @param Integer itemId
     * @return OrderItemEntity
     */
    OrderItemEntity findbyOrderIdAndItemId(final Integer orderId, @NonNull final Integer itemId);

    /**
     * 注文アイテムの数量を更新する.
     *
     * @param Integer orderId
     * @param Integer itemId
     * @param Integer quantity
     */
    void updateQuantity(@NonNull final Integer orderId, @NonNull final Integer itemId, @NonNull final Integer quantity);

    /**
     * 注文アイテムを追加する.
     *
     * @param OrderItemDto orderItemDto
     */
    void insert(@NonNull final OrderItemDto orderItemDto);

    /**
     * 注文アイテムを削除する.
     *
     * @param Integer orderId
     * @param Integer itemId
     */
    void deleteByOrderIdAndItemId(@NonNull final Integer orderId, @NonNull final Integer itemId);
}