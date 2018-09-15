package com.application.humming.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.application.humming.dao.OrderDao;
import com.application.humming.entity.OrderEntity;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert insert;

    private static final RowMapper<OrderEntity> ROW_MAPPER = (rs, i) -> {
        return OrderEntity.builder()
                .id(rs.getInt("id"))
                .memberId(rs.getInt("member_id"))
                .status(rs.getInt("status"))
                .totalPrice(rs.getInt("total_price"))
                .orderDate(rs.getDate("order_date"))
                .deliveryName(rs.getString("delivery_name"))
                .deliveryEmail(rs.getString("delivery_email"))
                .deliveryAddress(rs.getString("delivery_address"))
                .deliveryTelephone(rs.getString("delivery_telephone"))
                .deliveryTime(rs.getTimestamp("delivery_time"))
                .build();
    };

    @PostConstruct
    public void init() {
        final SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) jdbcTemplate.getJdbcOperations());
        final SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("orders");
        insert = withTableName.usingGeneratedKeyColumns("id");
    }

    @Override
    public List<OrderEntity> findByMemberIdAndStatus(@NonNull final Integer memberId, @NonNull final Integer status) {
        final String sql = "SELECT id, member_id, status, total_price, order_date, delivery_name, delivery_email, delivery_address,"
                + " delivery_telephone, delivery_time FROM orders WHERE member_id = :memberId AND status = :status";
        final SqlParameterSource param = new MapSqlParameterSource().addValue("memberId", memberId).addValue("status", status);
        try {
            return jdbcTemplate.query(sql, param, ROW_MAPPER);
        } catch (final DataAccessException e) {
            log.warn("Fail to find orders by memberId and status, memberId: {}, status, message: {}", memberId, status, e.getMessage());
            return null;
        }
    }

    @Override
    public void updateTotalPrice(@NonNull final Integer orderId, @NonNull final Integer totalPrice) {
        final String sql = "UPDATE orders SET total_price = :totalPrice WHERE id = :orderId";
        final SqlParameterSource param = new MapSqlParameterSource().addValue("totalPrice", totalPrice).addValue("orderId", orderId);
        try {
            jdbcTemplate.update(sql, param);
        } catch (final DataAccessException e) {
            log.warn("Fail to update total price, orderId: {}, totalPrice: {}, message: {}", orderId, totalPrice, e.getMessage());
        }
    }

    @Override
    public OrderEntity save(@NonNull final OrderEntity orderEntity) {
        if (orderEntity.getId() == null) {
            final SqlParameterSource param = new BeanPropertySqlParameterSource(orderEntity);
            orderEntity.setId(insert.executeAndReturnKey(param).intValue());
            return orderEntity;
        }
        final String sql = "UPDATE orders SET status = :status, member_id = :memberId, total_price = :totalPrice, order_date = :orderDate, delivery_name = :deliveryName,"
                + " delivery_email = :deliveryEmail, delivery_address = :deliveryAddress, delivery_telephone = :deliveryTelephone,"
                + " delivery_time = :deliveryTime WHERE id = :id";
        final SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", orderEntity.getId())
                .addValue("memberId", orderEntity.getMemberId())
                .addValue("status", orderEntity.getStatus())
                .addValue("totalPrice", orderEntity.getTotalPrice())
                .addValue("orderDate", orderEntity.getOrderDate())
                .addValue("deliveryName", orderEntity.getDeliveryName())
                .addValue("deliveryEmail", orderEntity.getDeliveryEmail())
                .addValue("deliveryAddress", orderEntity.getDeliveryAddress())
                .addValue("deliveryTelephone", orderEntity.getDeliveryTelephone())
                .addValue("deliveryTime", orderEntity.getDeliveryTime());
        try {
            jdbcTemplate.update(sql, param);
        } catch (final DataAccessException e) {
            log.warn("Fail to save order, id: {}, memberId: {}, message: {}", orderEntity.getId(), orderEntity.getMemberId(), e.getMessage());
        }
        return orderEntity;
    }
}