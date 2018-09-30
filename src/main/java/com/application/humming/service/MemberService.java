package com.application.humming.service;

import org.springframework.web.bind.support.SessionStatus;

import com.application.humming.dto.MemberDto;
import com.application.humming.entity.MemberEntity;
import com.application.humming.exception.HummingException;

import lombok.NonNull;

public interface MemberService {

    /**
     * ログイン処理を行う.
     *
     * @param email
     * @param password
     * @return MemberDto
     */
    MemberDto createMemberDto(@NonNull final String email, @NonNull final String password);

    /**
     * ログアウト処理を行う.
     *
     * @param sessionStatus
     */
    void logout(@NonNull final SessionStatus sessionStatus);

    /**
     * メールアドレスの重複チェックを行う.
     *
     * @param email
     * @return boolean
     */
    boolean checkIfUniqueEmail(@NonNull final String email);

    /**
     * 新規会員登録を行う.
     *
     * @param memberEntity
     * @throws HummingException
     */
    void regist(@NonNull final MemberEntity memberEntity) throws HummingException;

    /**
     * 退会処理を行う.
     *
     * @param id
     * @param sessionStatus
     * @throws HummingException
     */
    void withdraw(@NonNull final Integer id, @NonNull final SessionStatus sessionStatus) throws HummingException;
}