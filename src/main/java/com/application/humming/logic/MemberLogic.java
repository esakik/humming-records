package com.application.humming.logic;

import com.application.humming.entity.MemberEntity;

import lombok.NonNull;

public interface MemberLogic {

    /**
     * 会員情報を取得する.
     *
     * @param email
     * @return MemberEntity
     */
    MemberEntity getMemberInfoByEmail(@NonNull final String email);

    /**
     * 会員情報を登録する.
     *
     * @param memberEntity
     */
    void save(@NonNull final MemberEntity memberEntity);

    /**
     * 会員情報を削除する.
     *
     * @param id
     */
    void delete(@NonNull final Integer id);
}