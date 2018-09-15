package com.application.humming.dao;

import java.util.List;

import com.application.humming.entity.OrderEntity;

import lombok.NonNull;

public interface OrderDao {

    /**
     * 注文情報を会員IDと注文ステータスで検索する.
     *
     * @param memberId
     * @param status
     * @return List<OrderEntity>
     */
    List<OrderEntity> findByMemberIdAndStatus(@NonNull final Integer memberId, @NonNull final Integer status);

    /**
     * 合計金額を更新する.
     *
     * @param orderId
     * @param totalPrice
     */
    void updateTotalPrice(final Integer orderId, @NonNull final Integer totalPrice);

    /**
     * 注文情報を追加・更新する.
     *
     * @param orderEntity
     * @return OrderEntity
     */
    OrderEntity save(@NonNull final OrderEntity orderEntity);
}