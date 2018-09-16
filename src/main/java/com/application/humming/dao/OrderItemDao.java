package com.application.humming.dao;

import java.util.List;

import com.application.humming.dto.OrderItemDto;
import com.application.humming.entity.OrderItemEntity;

import lombok.NonNull;

public interface OrderItemDao {

    /**
     * 注文アイテムをを注文IDで検索する.
     *
     * @param orderId
     * @return List<OrderItemEntity>
     */
    List<OrderItemEntity> findByOrderId(@NonNull final Integer orderId);

    /**
     * 注文アイテムを注文IDと商品IDで検索する.
     *
     * @param orderId
     * @param itemId
     * @return OrderItemEntity
     */
    OrderItemEntity findbyOrderIdAndItemId(@NonNull final Integer orderId, @NonNull final Integer itemId);

    /**
     * 注文アイテムの数量を更新する.
     *
     * @param orderItemDto
     */
    void updateQuantity(@NonNull final OrderItemDto orderItemDto);

    /**
     * 注文アイテムを追加する.
     *
     * @param orderItemDto
     */
    void insert(@NonNull final OrderItemDto orderItemDto);

    /**
     * 注文アイテムを削除する.
     *
     * @param orderId
     * @param itemId
     */
    void deleteByOrderIdAndItemId(@NonNull final Integer orderId, @NonNull final Integer itemId);
}