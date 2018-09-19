package com.application.humming.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

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
    @Pattern(regexp = "^(070|080|090)-\\d{4}-\\d{4}$", message = "電話番号が正しい形式ではありません。")
    private String telephone;

    /** パスワード. */
    @NotBlank(message = "パスワードは入力必須です。")
    @Length(min=8, message = "パスワードは8桁以上で入力してください。")
    @Pattern(regexp = "^[0-9a-zA-Z]+$", message = "パスワードは半角英数字で入力してください。")
    private String password;

    /** 確認用パスワード. */
    @NotBlank(message = "パスワード (確認)は入力必須です。")
    private String confirmationPassword;

}