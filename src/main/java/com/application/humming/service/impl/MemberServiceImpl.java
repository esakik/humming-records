package com.application.humming.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.support.SessionStatus;

import com.application.humming.dto.MemberDto;
import com.application.humming.entity.MemberEntity;
import com.application.humming.logic.MemberLogic;
import com.application.humming.service.MemberService;

import lombok.NonNull;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberLogic memberLogic;

    @Autowired
    HttpSession session;

    @Autowired
    StandardPasswordEncoder spe;

    @Bean
    public StandardPasswordEncoder standardPasswordEncoder() {
        return new StandardPasswordEncoder();
    }

    private final static String[] SESSION_OBJECT = { "member", "order" };

    @Override
    public MemberDto createMemberDto(@NonNull final String email, @NonNull final String password) {
        final MemberDto memberDto = new MemberDto();
        final MemberEntity memberEntity = memberLogic.getMemberInfoByEmail(email);
        if (memberEntity != null) {
            final String encodedPassword = memberEntity.getPassword();
            if (spe.matches(password, encodedPassword)) {
                BeanUtils.copyProperties(memberEntity, memberDto);
            }
        }
        return memberDto;
    }

    @Override
    public void logout(@NonNull final SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        removeAttributes(sessionStatus, SESSION_OBJECT);
    }

    @Override
    public boolean checkIfUniqueEmail(@NonNull final String email) {
        return memberLogic.getMemberInfoByEmail(email) == null;
    }

    @Override
    public void regist(@NonNull final MemberEntity memberEntity) {
        memberEntity.setPassword(spe.encode(memberEntity.getPassword()));
        memberLogic.save(memberEntity);
    }

    @Override
    public void withdraw(@NonNull final Integer id, @NonNull final SessionStatus sessionStatus) {
        memberLogic.delete(id);
        removeAttributes(sessionStatus, SESSION_OBJECT);
    }

    /**
     * セッションに格納されている情報を削除する.
     *
     * @param SessionStatus sessionStatus
     * @param String attribute
     */
    private void removeAttributes(@NonNull final SessionStatus sessionStatus, final String[] attributes) {
        sessionStatus.setComplete();
        for (final String attribute : attributes) {
            session.removeAttribute(attribute);
        }
    }
}