package com.application.humming.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class RegistForm {

    /** 名前. */
    @NotBlank(message = "お名前は入力必須です。")
    private String name;

    /** メールアドレス. */
    @NotBlank(message = "メールアドレスは入力必須です。")
    @Email(message = "メールアドレスが正しい形式ではありません。")
    private String email;

    /** 住所. */
    @NotBlank(message = "住所は入力必須です。")
    private String address;

    /** 電話番号. */
    @NotBlank(message = "電話番号は入力必須です。")
    @Range(min = 8, max = 12, message = "電話番号が正しい形式ではありません。")
    private String telephone;

    /** パスワード. */
    @NotBlank(message = "パスワードは入力必須です。")
    @Length(min=8, message = "パスワードは8桁以上で入力してください。")
    private String password;

    /** 確認用パスワード. */
    @NotBlank(message = "確認用パスワードは入力必須です。")
    private String confirmationPassword;

    /** 削除フラグ. */
    private Integer deleted;
}