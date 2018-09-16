package com.application.humming.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.application.humming.dao.MemberDao;
import com.application.humming.entity.MemberEntity;
import com.application.humming.exception.HummingException;
import com.application.humming.logic.MemberLogic;

import lombok.NonNull;

@Component
public class MemberLogicImpl implements MemberLogic {

    @Autowired
    MemberDao memberDao;

    @Override
    public MemberEntity findByEmail(@NonNull final String email) {
        return memberDao.findByEmail(email);
    }

    @Override
    public void save(@NonNull final MemberEntity memberEntity) throws HummingException {
        memberDao.save(memberEntity);
    }

    @Override
    public void delete(@NonNull final Integer id) throws HummingException {
        memberDao.delete(id);
    }
}
