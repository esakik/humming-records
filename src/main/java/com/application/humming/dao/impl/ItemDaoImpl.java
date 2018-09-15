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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ItemDaoImpl implements ItemDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

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

    @PostConstruct
    public void init() {
        final SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) jdbcTemplate.getJdbcOperations());
        final SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("items");
        withTableName.usingGeneratedKeyColumns("id");
    }

    @Override
    public ItemEntity findByPrimaryKey(@NonNull final Integer id) {
        final String sql = "SELECT id, singer, song, price, stock, amount, description, image FROM items WHERE id=:id ORDER BY id";
        final SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        try {
            return jdbcTemplate.queryForObject(sql, param, RowMapper);
        } catch (final DataAccessException e) {
            log.warn("Fail to find item by id, id: {}, message: {}", id, e.getMessage());
            return null;
        }
    }

    @Override
    public List<ItemEntity> findAll() {
        final String sql = "SELECT id, singer, song, price, stock, amount, description, image FROM items ORDER BY id";
        try {
            return jdbcTemplate.query(sql, RowMapper);
        } catch (final DataAccessException e) {
            log.warn("Fail to find all items, message: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public List<ItemEntity> findBySingerOrSong(@NonNull final String singerOrSong) {
        final String sql = "SELECT id, singer, song, price, stock, amount, description, image FROM items WHERE singer LIKE :singer OR song LIKE :song ORDER BY id";
        final SqlParameterSource param = new MapSqlParameterSource().addValue("singer", '%' + singerOrSong + '%').addValue("song", '%' + singerOrSong + '%');
        try {
            return jdbcTemplate.query(sql, param, RowMapper);
        } catch (final DataAccessException e) {
            log.warn("Fail to find by singer or song, singerOrSong: {}, message: {}", singerOrSong, e.getMessage());
            return null;
        }
    }

    @Override
    public List<ItemEntity> findFromOffsetToLimit(@NonNull final Integer offset, @NonNull final Integer limit) {
        final String sql = "SELECT id, singer, song, price, stock, amount, description, image FROM items order by id OFFSET :offset LIMIT :limit";
        final SqlParameterSource param = new MapSqlParameterSource().addValue("offset", offset).addValue("limit", limit);
        try {
            return jdbcTemplate.query(sql, param, RowMapper);
        } catch (final DataAccessException e) {
            log.warn("Fail to find items from offset to limit, offset: {}, limit: {}, message: {}", offset, limit, e.getMessage());
            return null;
        }
    }
}
