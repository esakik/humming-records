package com.application.humming.dao.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.application.humming.dao.ItemDao;
import com.application.humming.entity.ItemEntity;

import lombok.NonNull;

@Repository
public class ItemDaoImpl implements ItemDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) jdbcTemplate.getJdbcOperations());
        SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("items");
        withTableName.usingGeneratedKeyColumns("id");
    }

    private static final RowMapper<ItemEntity> RowMapper = (rs, i) -> {
        return ItemEntity.builder()
                .id(rs.getInt("id"))
                .singer(rs.getString("singer"))
                .song(rs.getString("song"))
                .price(rs.getInt("price"))
                .stock(rs.getInt("stock"))
                .amount(rs.getInt("amount"))
                .description(rs.getString("description"))
                .image(rs.getString("image"))
                .build();
    };

    @Override
    public ItemEntity findByPrimaryKey(@NonNull final Integer id) {
        String sql = "SELECT id, singer, song, price, stock, amount, description, image FROM items WHERE id=:id ORDER BY id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        try {
            return jdbcTemplate.queryForObject(sql, param, RowMapper);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<ItemEntity> findAll() {
        String sql = "SELECT id, singer, song, price, stock, amount, description, image FROM items ORDER BY id";
        try {
            return jdbcTemplate.query(sql, RowMapper);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<ItemEntity> findBySingerOrSong(@NonNull final String singerOrSong) {
        String sql = "SELECT id, singer, song, price, stock, amount, description, image FROM items WHERE singer LIKE :singer OR song LIKE :song ORDER BY id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("singer", '%' + singerOrSong + '%').addValue("song", '%' + singerOrSong + '%');
        try {
            return jdbcTemplate.query(sql, param, RowMapper);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<ItemEntity> findFromOffsetToLimit(@NonNull final Integer offset, @NonNull final Integer limit) {
        String sql = "select id, singer, song, price, stock, amount, description, image from items order by id offset :offset limit :limit";
        SqlParameterSource param = new MapSqlParameterSource().addValue("offset", offset).addValue("limit", limit);
        try {
            return jdbcTemplate.query(sql, param, RowMapper);
        } catch (DataAccessException e) {
            return null;
        }
    }
}
