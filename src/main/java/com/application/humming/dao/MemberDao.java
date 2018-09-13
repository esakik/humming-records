package com.application.humming.dao;

import com.application.humming.entity.MemberEntity;

import lombok.NonNull;

public interface MemberDao {

    /**
     * メールアドレスから会員情報を取得する.
     *
     * @param email
     * @return MemberEntity
     */
    MemberEntity findByEmail(@NonNull final String email);

    /**
     * 会員情報を登録または更新する.
     *
     * @param memberEntity
     * @return MemberEntity
     */
    void save(@NonNull final MemberEntity memberEntity);

    /**
     * 会員情報を削除する.
     *
     * @param id
     */
    void delete(@NonNull final Integer id);
}
