package com.application.humming.dao;

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
import org.springframework.transaction.annotation.Transactional;

import com.application.humming.entity.ItemEntity;

import lombok.NonNull;

@Repository
@Transactional
public class ItemDao {

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

    /**
     * アイテム情報を主キー検索する.
     *
     * @param Integer id
     * @return ItemEntity アイテム情報
     */
    public ItemEntity findByPrimaryKey(@NonNull final Integer id) {
        String sql = "SELECT id, singer, song, price, stock, amount, description, image FROM items WHERE id=:id ORDER BY id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        try {
            return jdbcTemplate.queryForObject(sql, param, RowMapper);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * アイテム情報を全件取得する.
     *
     * @return List<ItemEntity> アイテム情報全件
     */
    public List<ItemEntity> findAll() {
        String sql = "SELECT id, singer, song, price, stock, amount, description, image FROM items ORDER BY id";
        try {
            return jdbcTemplate.query(sql, RowMapper);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * 歌手名または曲名からアイテム情報を取得する.
     *
     * @param String singerOrSong
s     * @return List<ItemEntity> アイテム情報
     */
    public List<ItemEntity> findBySingerOrSong(@NonNull final String singerOrSong) {
        String sql = "SELECT id, singer, song, price, stock, amount, description, image FROM items WHERE singer LIKE :singer OR song LIKE :song ORDER BY id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("singer", '%' + singerOrSong + '%').addValue("song", '%' + singerOrSong + '%');
        try {
            return jdbcTemplate.query(sql, param, RowMapper);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * OffsetからLimitまでのアイテム情報を取得する.
     *
     * @param Integer offset
     * @param Integer limit
     * @return List<ItemEntity> アイテム情報
     */
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
