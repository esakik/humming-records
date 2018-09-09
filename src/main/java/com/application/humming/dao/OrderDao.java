package com.application.humming.dao;

import java.util.List;

import com.application.humming.entity.OrderEntity;

import lombok.NonNull;

public interface OrderDao {

    /**
     * 注文情報を会員IDと注文ステータスで検索する.
     *
     * @param Integer memberId
     * @param Integer status
     * @return List<OrderEntity>
     */
    List<OrderEntity> findByMemberIdAndStatus(@NonNull final Integer memberId, @NonNull final Integer status);

    /**
     * 合計金額を更新する.
     *
     * @param Integer orderId
     * @param Integer totalPrice
     */
    void updateTotalPrice(final Integer orderId, @NonNull final Integer totalPrice);

    /**
     * 注文情報を追加・更新する.
     *
     * @param OrderEntity orderEntity
     * @return OrderEntity
     */
    OrderEntity save(@NonNull final OrderEntity orderEntity);

    /**
     * 注文情報を削除する.
     *
     * @param Integer id
     */
    void deleteByPrimaryKey(@NonNull final Integer id);
}