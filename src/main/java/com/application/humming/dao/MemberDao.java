package com.application.humming.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.application.humming.entity.MemberEntity;

import lombok.NonNull;

@Repository
public class MemberDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final RowMapper<MemberEntity> ROW_MAPPER = (rs, i) -> {
        return MemberEntity.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .address(rs.getString("address"))
                .telephone(rs.getString("telephone"))
                .build();
    };

    /**
     * 会員情報を主キー検索する.
     *
     * @param Integer userId
     * @return MemberEntity 会員
     */
    public MemberEntity findByPrimaryKey(@NonNull final Integer id) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        try {
            return jdbcTemplate.queryForObject("SELECT id, name, email, password, address, telephone FROM members WHERE id = :id", param, ROW_MAPPER);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * 会員情報を全件取得する.
     *
     * @return List<MemberEntity> 会員情報全件
     */
    public List<MemberEntity> findAll() {
        String sql = "SELECT * FROM members ORDER BY id";
        try {
            return jdbcTemplate.query(sql, ROW_MAPPER);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * メールアドレスとパスワードから会員情報を取得する.
     *
     * @param String email
     * @param String password
     * @return MemberEntity 会員情報
     */
    public MemberEntity findByMailAddressAndMember(@NonNull final String email, @NonNull final String password) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("email", email).addValue("password", password);
        try {
            return jdbcTemplate.queryForObject("SELECT id, name, email, password, address, telephone FROM members WHERE email = :email AND password = :password", param, ROW_MAPPER);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * メールアドレスから会員情報を取得する.
     *
     * @param String email
     * @return MemberEntity 会員情報
     */
    public MemberEntity findByEmail(@NonNull final String email) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
        try {
            return jdbcTemplate.queryForObject("SELECT id, name, email, password, address, telephone FROM members WHERE email = :email", param, ROW_MAPPER);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * 会員情報を登録または更新する.
     *
     * @param MemberEntity member
     * @return MemberEntity 会員情報
     */
    public void save(@NonNull final MemberEntity memberEntity) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(memberEntity);
        if (memberEntity.getId() == null) {
            jdbcTemplate.update("INSERT INTO members (name, email, password, address, telephone) VALUES (:name, :email, :password, :address , :telephone)", param);
        } else {
            jdbcTemplate.update("UPDATE members SET name = :name, email = :email, password = :password WHERE id = :id", param);
        }
    }

    /**
     * 会員情報を削除する.
     *
     * @param Integer id
     */
    public void delete(@NonNull final Integer id) {
        String sql = "DELETE FROM members WHERE id=:id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        jdbcTemplate.update(sql, param);
    }
}
