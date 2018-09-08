package com.application.humming.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.application.humming.entity.OrderItemEntity;
import com.application.humming.form.ShoppingCartForm;

import lombok.NonNull;

@Repository
public class OrderItemDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final RowMapper<OrderItemEntity> ROW_MAPPER = (rs, i) -> {
        return OrderItemEntity.builder()
                .id(rs.getInt("id"))
                .itemId(rs.getInt("itemId"))
                .orderId(rs.getInt("orderId"))
                .quantity(rs.getInt("quantity"))
                .build();
    };

    /**
     * 注文商品情報を商品IDで検索する.
     *
     * @param Integer itemId
     * @return OrderItemEntity 注文商品情報
     */
    public OrderItemEntity findByItemId(@NonNull final Integer itemId) {
        String sql = "SELECT id, itemid, orderid, quantity FROM order_items WHERE itemid = :itemId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("itemId", itemId);
        try {
            return jdbcTemplate.queryForObject(sql, param, ROW_MAPPER);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * OrderItemをorderIdで検索.
     *
     * @param Integer orderId
     * @return List<OrderItemEntity> 注文商品情報
     */
    public List<OrderItemEntity> findByOrderId(@NonNull final Integer orderId) {
        String sql = "SELECT id, itemid, orderid, quantity FROM order_items WHERE orderid = :orderId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId);
        try {
            return jdbcTemplate.query(sql, param, ROW_MAPPER);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * 注文商品情報を注文IDと商品IDで検索する.
     *
     * @param Integer orderId
     * @param Integer itemId
     * @return OrderItemEntity 注文商品情報
     */
    public OrderItemEntity findbyOrderIdAndItemId(@NonNull final Integer orderId, @NonNull final Integer itemId) {
        String sql = "SELECT id, itemid, orderid, quantity FROM order_items WHERE orderid = :orderId AND itemid = :itemId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId).addValue("itemId", itemId);
        try {
            return jdbcTemplate.queryForObject(sql, param, ROW_MAPPER);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * 注文IDを更新する.
     *
     * @param Integer orderId
     * @param Integer lastOrderId
     */
    public void updateByOrderId(@NonNull final Integer orderId, @NonNull final Integer lastOrderId){
        String sql = "UPDATE order_items SET orderid = :lastOrderId WHERE orderid = :orderId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("lastOrderId", lastOrderId).addValue("orderId", orderId);
        jdbcTemplate.update(sql, param);
    }

    /**
     * 商品数量を追加する.
     *
     * @param Integer orderId
     * @param Integer itemId
     * @param Integer quantity
     */
    public void updateQuantityOfOrderItem(@NonNull final Integer orderId, @NonNull final Integer itemId, @NonNull final Integer quantity) {
        String sql = "UPDATE order_items SET quantity = quantity + :quantity WHERE orderId = :orderId and itemId = :itemId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("quantity", quantity).addValue("orderId", orderId).addValue("itemId", itemId);
        jdbcTemplate.update(sql, param);
    }

    /**
     * カートに商品を追加する.
     *
     * @param ShoppingCartForm shoppingCartForm
     */
    public void addOrderItem(@NonNull final ShoppingCartForm shoppingCartForm){
        String sql = "INSERT INTO order_items (itemid, orderid, quantity) VALUES (:itemId, :orderId, :quantity)";
        SqlParameterSource param = new MapSqlParameterSource().addValue("itemId", shoppingCartForm.getItemId()).addValue("orderId", shoppingCartForm.getOrderId()).addValue("quantity", shoppingCartForm.getQuantity());
        jdbcTemplate.update(sql, param);
    }

    /**
     * カート内の商品を削除する.
     *
     * @param Integer orderId
     * @param Integer itemId
     */
    public void deleteByOrderIdAndItemId(@NonNull final Integer orderId, @NonNull final Integer itemId) {
        String sql = "DELETE FROM order_items WHERE orderid = :orderId AND itemid = :itemId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId).addValue("itemId", itemId);
        jdbcTemplate.update(sql, param);
    }

    /**
     * カート内の商品を削除する.
     *
     * @param Integer id
     * @param Integer itemId
     */
    public void deleteByIdAndItemId(@NonNull final Integer id, @NonNull final Integer itemId) {
        String sql = "delete from order_items where id = :id and itemid = :itemId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id).addValue("itemId", itemId);
        jdbcTemplate.update(sql, param);
    }
}