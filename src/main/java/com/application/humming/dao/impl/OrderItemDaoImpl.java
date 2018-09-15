package com.application.humming.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.application.humming.dao.OrderItemDao;
import com.application.humming.dto.OrderItemDto;
import com.application.humming.entity.OrderItemEntity;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class OrderItemDaoImpl implements OrderItemDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final RowMapper<OrderItemEntity> ROW_MAPPER = (rs, i) -> {
        return OrderItemEntity.builder()
                .id(rs.getInt("id"))
                .itemId(rs.getInt("item_id"))
                .orderId(rs.getInt("order_id"))
                .quantity(rs.getInt("quantity"))
                .build();
    };

    @Override
    public List<OrderItemEntity> findByOrderId(@NonNull final Integer orderId) {
        String sql = "SELECT id, item_id, order_id, quantity FROM order_items WHERE order_id = :orderId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId);
        try {
            return jdbcTemplate.query(sql, param, ROW_MAPPER);
        } catch (DataAccessException e) {
            log.warn("Error message, orderId: {}, message: {}", orderId, e.getMessage());
            return null;
        }
    }

    @Override
    public OrderItemEntity findbyOrderIdAndItemId(final Integer orderId, @NonNull final Integer itemId) {
        String sql = "SELECT id, item_id, order_id, quantity FROM order_items WHERE order_id = :orderId AND item_id = :itemId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId).addValue("itemId", itemId);
        try {
            return jdbcTemplate.queryForObject(sql, param, ROW_MAPPER);
        } catch (DataAccessException e) {
            log.warn("Error message, orderId: {}, itemId: {}, message: {}", orderId, itemId, e.getMessage());
            return null;
        }
    }

    @Override
    public void updateQuantity(@NonNull final Integer orderId, @NonNull final Integer itemId, @NonNull final Integer quantity) {
        String sql = "UPDATE order_items SET quantity = quantity + :quantity WHERE order_id = :orderId and item_id = :itemId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("quantity", quantity).addValue("orderId", orderId).addValue("itemId", itemId);
        jdbcTemplate.update(sql, param);
    }

    @Override
    public void insert(@NonNull final OrderItemDto orderItemDto){
        String sql = "INSERT INTO order_items (item_id, order_id, quantity) VALUES (:itemId, :orderId, :quantity)";
        SqlParameterSource param = new MapSqlParameterSource().addValue("itemId", orderItemDto.getItemId()).addValue("orderId", orderItemDto.getOrderId()).addValue("quantity", orderItemDto.getQuantity());
        jdbcTemplate.update(sql, param);
    }

    @Override
    public void deleteByOrderIdAndItemId(@NonNull final Integer orderId, @NonNull final Integer itemId) {
        String sql = "DELETE FROM order_items WHERE order_id = :orderId AND item_id = :itemId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderId).addValue("itemId", itemId);
        jdbcTemplate.update(sql, param);
    }
}
