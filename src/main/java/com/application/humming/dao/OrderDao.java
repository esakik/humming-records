package com.application.humming.dao;

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
import org.springframework.transaction.annotation.Transactional;

import com.application.humming.entity.OrderEntity;

import lombok.NonNull;

@Repository
@Transactional
public class OrderDao {

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

    /**
     * 注文情報を会員IDと注文ステータスで検索する.
     *
     * @param Integer memberId
     * @param Integer status
     * @return OrderEntity 注文情報
     */
    public OrderEntity findByMemberIdAndStatus(@NonNull final Integer memberId, @NonNull final Integer status) {
        String sql = "SELECT id, member_id, status, total_price, order_date, delivery_name, delivery_email, delivery_address,"
                + " delivery_telephone, delivery_time FROM orders WHERE member_id = :memberId and status = :status";
        SqlParameterSource param = new MapSqlParameterSource().addValue("memberId", memberId).addValue("status", status);
        try {
            return jdbcTemplate.queryForObject(sql, param, ROW_MAPPER);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * 注文履歴を会員IDで検索する.
     *
     * @param Integer userId
     * @return List<OrderEntity> 注文履歴
     */
    public List<OrderEntity> searchOrderHistory(@NonNull final Integer memberId) {
        String sql = "SELECT id, member_id, status, total_price, order_date, delivery_name, delivery_email,"
                + " delivery_address, delivery_telephone, delivery_time FROM orders WHERE member_id = :memberId and status = 1 ORDER BY id DESC";
        SqlParameterSource param = new MapSqlParameterSource().addValue("memberId", memberId);
        try {
            return jdbcTemplate.query(sql, param, ROW_MAPPER);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * 合計金額を主キー検索する.
     *
     * @param Integer id
     * @return Integer 合計金額
     */
    public Integer findTotalPriceByPrimaryKey(@NonNull final Integer id) {
        String sql = "SELECT total_price FROM orders WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        try {
            return jdbcTemplate.queryForObject(sql, param, Integer.class);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * 合計金額を更新する.
     *
     * @param Integer orderId
     * @param Integer totalPrice
     */
    public void updateTotalPrice(@NonNull final Integer orderId, @NonNull final Integer totalPrice) {
        String sql = "UPDATE orders SET total_price = :totalPrice WHERE id = :orderId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("totalPrice", totalPrice).addValue("orderId", orderId);
        jdbcTemplate.update(sql, param);
    }

    /**
     * 注文IDで会員IDを更新する.
     *
     * @param Integer orderId
     * @param Integer memberId
     */
    public void updateMemberIdByOrderId(@NonNull final Integer orderId, @NonNull final Integer memberId) {
        String sql = "update orders set member_id = :memberId where id = :orderId";
        SqlParameterSource param = new MapSqlParameterSource().addValue("memberId", memberId).addValue("orderId", orderId);
        jdbcTemplate.update(sql, param);
    }

    /**
     * 注文ステータスを更新する.
     * @param OrderEntity order
     */
    public void updateStatusByOrderId(@NonNull final OrderEntity orderEntity) {
        String sql = "UPDATE orders SET status = :status, order_date = :orderDate, delivery_name = :deliveryName,"
                + " delivery_email = :deliveryEmail, delivery_address = :deliveryAddress, delivery_telephone = :deliveryTelephone,"
                + " delivery_time = :deliveryTime WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("status", orderEntity.getStatus())
                .addValue("orderDate", orderEntity.getOrderDate()).addValue("id", orderEntity.getId())
                .addValue("deliveryName", orderEntity.getDeliveryName()).addValue("deliveryEmail", orderEntity.getDeliveryEmail())
                .addValue("deliveryAddress", orderEntity.getDeliveryAddress()).addValue("deliveryTelephone", orderEntity.getDeliveryTelephone())
                .addValue("deliveryTime", orderEntity.getDeliveryTime());
        jdbcTemplate.update(sql, param);
    }

    /**
     * TODO 削除できそう
     * 注文情報を追加する.
     *
     * @param OrderEntity order
     * @return OrderEntity 注文情報
     */
    public OrderEntity addOrderByCaseGetOrderId(@NonNull final OrderEntity orderEntity) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(orderEntity);
        if (orderEntity.getId() == null) {
            orderEntity.setId(insert.executeAndReturnKey(param).intValue());
        } else {
            String sql = "UPDATE orders SET total_price = :totalPrice WHERE id = :id";
            SqlParameterSource param1 = new MapSqlParameterSource().addValue("totalPrice", orderEntity.getTotalPrice()).addValue("id", orderEntity.getId());
            jdbcTemplate.update(sql, param1);
        }
        return orderEntity;
    }

    /**
     * 注文情報を削除する.
     *
     * @param Integer id
     */
    public void deleteByPrimaryKey(@NonNull final Integer id) {
        String sql = "DELETE FROM orders WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        jdbcTemplate.update(sql, param);
    }
}