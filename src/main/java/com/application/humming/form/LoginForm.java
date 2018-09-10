package com.application.humming.form;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginForm {

    /** メールアドレス. */
    @NotBlank(message = "メールアドレスは入力必須です。")
    private String email;

    /** パスワード. */
    @NotBlank(message = "パスワードは入力必須です。")
    private String password;
}
