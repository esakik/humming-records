package com.application.humming.logic;

import com.application.humming.entity.MemberEntity;
import com.application.humming.exception.HummingException;

import lombok.NonNull;

public interface MemberLogic {

    /**
     * 会員情報を取得する.
     *
     * @param email
     * @return MemberEntity
     */
    MemberEntity findByEmail(@NonNull final String email);

    /**
     * 会員情報を登録する.
     *
     * @param memberEntity
     * @throws HummingException
     */
    void save(@NonNull final MemberEntity memberEntity) throws HummingException;

    /**
     * 会員情報を削除する.
     *
     * @param id
     * @throws HummingException
     */
    void delete(@NonNull final Integer id) throws HummingException;
}