package com.application.humming.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class RegistForm {

    /** 名前. */
    @NotBlank(message = "お名前を入力してください")
    private String name;

    /** メールアドレス. */
    @NotBlank(message = "メールアドレスを入力してください")
    @Email(message = "アドレスが不正です")
    private String email;

    /** 住所. */
    @NotBlank(message = "住所を入力してください")
    private String address;

    /** 電話番号. */
    @NotBlank(message = "電話番号を入力してください")
    //@Range(min=0, message = "適切な値を入力してください")
    private String telephone;

    /** パスワード. */
    @NotBlank(message = "パスワードを入力してください")
    //@Length(min=8, max=20, message = "8桁以上で入力してください")
    private String password;

    /** 確認用パスワード. */
    @NotBlank(message = "確認用パスワードを入力してください")
    private String confirmationPassword;

    /** 削除フラグ. */
    private Integer deleted;
}