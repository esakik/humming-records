package com.application.humming.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

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
