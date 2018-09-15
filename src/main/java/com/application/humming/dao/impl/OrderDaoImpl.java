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
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) jdbcTemplate.getJdbcOperations());
        SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("orders");
        insert = withTableName.usingGeneratedKeyColumns("id");
    }

    @Override
    public List<OrderEntity> findByMemberIdAndStatus(@NonNull final Integer memberId, @NonNull final Integer status) {
        String sql = "SELECT id, member_id, status, total_price, order_date, delivery_name, delivery_email, delivery_address,"
                + " delivery_telephone, delivery_time FROM orders WHERE member_id = :memberId and status = :status";
        SqlParameterSource param = new MapSqlParameterSource().addValue("memberId", memberId).addValue("status", status);
        try {
            return jdbcTemplate.query(sql, param, ROW_MAPPER);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void updateTotalPrice(final Integer orderId, @NonNull final Integer totalPrice) {
        String sql = "UPDATE orders SET total_price = :totalPrice WHERE id = :orderId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("totalPrice", totalPrice).addValue("orderId", orderId);
        jdbcTemplate.update(sql, param);
    }

    @Override
    public OrderEntity save(@NonNull final OrderEntity orderEntity) {
        if (orderEntity.getId() == null) {
            SqlParameterSource param = new BeanPropertySqlParameterSource(orderEntity);
            orderEntity.setId(insert.executeAndReturnKey(param).intValue());
        } else {
            String sql = "UPDATE orders SET status = :status, total_price = :totalPrice, order_date = :orderDate, delivery_name = :deliveryName,"
                    + " delivery_email = :deliveryEmail, delivery_address = :deliveryAddress, delivery_telephone = :deliveryTelephone,"
                    + " delivery_time = :deliveryTime WHERE id = :id";
            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue("id", orderEntity.getId())
                    .addValue("status", orderEntity.getStatus())
                    .addValue("totalPrice", orderEntity.getTotalPrice())
                    .addValue("orderDate", orderEntity.getOrderDate())
                    .addValue("deliveryName", orderEntity.getDeliveryName())
                    .addValue("deliveryEmail", orderEntity.getDeliveryEmail())
                    .addValue("deliveryAddress", orderEntity.getDeliveryAddress())
                    .addValue("deliveryTelephone", orderEntity.getDeliveryTelephone())
                    .addValue("deliveryTime", orderEntity.getDeliveryTime());
            jdbcTemplate.update(sql, param);
        }
        return orderEntity;
    }

    @Override
    public void deleteByPrimaryKey(@NonNull final Integer id) {
        String sql = "DELETE FROM orders WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        jdbcTemplate.update(sql, param);
    }
}