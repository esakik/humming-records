package com.application.humming.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.application.humming.dao.MemberDao;
import com.application.humming.entity.MemberEntity;

import lombok.NonNull;

@Repository
public class MemberDaoImpl implements MemberDao {

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

    @Override
    public MemberEntity findByEmail(@NonNull final String email) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
        try {
            return jdbcTemplate.queryForObject("SELECT id, name, email, password, address, telephone FROM members WHERE email = :email", param, ROW_MAPPER);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void save(@NonNull final MemberEntity memberEntity) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(memberEntity);
        if (memberEntity.getId() == null) {
            jdbcTemplate.update("INSERT INTO members (name, email, password, address, telephone) VALUES (:name, :email, :password, :address , :telephone)", param);
        } else {
            jdbcTemplate.update("UPDATE members SET name = :name, email = :email, password = :password WHERE id = :id", param);
        }
    }

    @Override
    public void delete(@NonNull final Integer id) {
        String sql = "DELETE FROM members WHERE id=:id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        jdbcTemplate.update(sql, param);
    }
}
