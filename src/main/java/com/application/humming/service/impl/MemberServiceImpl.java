package com.application.humming.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.SessionStatus;

import com.application.humming.dto.MemberDto;
import com.application.humming.entity.MemberEntity;
import com.application.humming.logic.MemberLogic;
import com.application.humming.service.MemberService;

import lombok.NonNull;

@Service
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
        session.setAttribute("order", null);
        session.setAttribute("orderItemList", null);
        session.setAttribute("itemList", null);
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
        sessionStatus.setComplete();
        session.removeAttribute("order");
        session.removeAttribute("orderItemList");
        session.removeAttribute("itemList");
    }
}