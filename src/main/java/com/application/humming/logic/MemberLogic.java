package com.application.humming.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.application.humming.dao.MemberDao;
import com.application.humming.entity.MemberEntity;

import lombok.NonNull;

@Component
public class MemberLogic {

    @Autowired
    MemberDao memberDao;

    /**
     * 主キー検索する.
     *
     * @param Integer id
     * @return MemberEntity
     *
     * */
    public MemberEntity findByPrimaryKey(@NonNull final Integer id) {
        return memberDao.findByPrimaryKey(id);
    }

    /**
     * 全件検索する.
     *
     * @return List<MemberEntity>
     */
    public List<MemberEntity> findAll() {
        return memberDao.findAll();
    }

    /**
     * メールアドレスとパスワードから会員情報を取得する.
     *
     * @param String mailAddress
     * @param String password
     * @return MemberEntity
     */
    public MemberEntity getMemberInfoByMailAddressAndPassword(@NonNull final String mailAddress, @NonNull final String password){
        return memberDao.findByMailAddressAndMember(mailAddress, password);
    }

    /**
     * メールアドレスで検索する.
     *
     * @param String email
     * @return MemberEntity
     */
    public MemberEntity getMemberInfoByEmail(@NonNull final String email) {
        return memberDao.findByEmail(email);
    }

    /**
     * 会員情報を更新または新規登録する.
     *
     * @param MemberEntity memberEntity
     */
    public void save(@NonNull final MemberEntity memberEntity){
        memberDao.save(memberEntity);
    }

    /**
     * 会員IDで会員情報を削除する.
     *
     * @param Integer id
     */
    public void delete(@NonNull final Integer id){
        memberDao.delete(id);
    }
}