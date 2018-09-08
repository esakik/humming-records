package com.application.humming.service;

import org.springframework.web.bind.support.SessionStatus;

import com.application.humming.dto.MemberDto;
import com.application.humming.entity.MemberEntity;

import lombok.NonNull;

public interface MemberService {

    /**
     * ログイン処理を行う.
     *
     * @param String email
     * @param String password
     * @return MemberDto
     */
    MemberDto createMemberDto(@NonNull final String email, @NonNull final String password);

    /**
     * ログアウト処理を行う.
     *
     * @param SessionStatus sessionStatus
     */
    void logout(@NonNull final SessionStatus sessionStatus);

    /**
     * emailの重複チェックを行う.
     *
     * @param email
     * @return boolean
     */
    boolean checkIfUniqueEmail(@NonNull final String email);

    /**
     * 新規会員登録を行う.
     *
     * @param MemberEntity memberEntity
     */
    void regist(@NonNull final MemberEntity memberEntity);

    /**
     * 退会処理を行う.
     *
     * @param Integer id
     * @param SessionStatus sessionStatus
     */
    void withdraw(@NonNull final Integer id, @NonNull final SessionStatus sessionStatus);
}