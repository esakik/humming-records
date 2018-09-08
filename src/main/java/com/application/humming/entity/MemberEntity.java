package com.application.humming.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MemberEntity {

    /** ID. */
    private Integer id;

    /** 名前. */
    private String name;

    /** メールアドレス. */
    private String email;

    /** パスワード */
    private String password;

    /** 住所. */
    private String address;

    /** 電話番号. */
    private String telephone;
}