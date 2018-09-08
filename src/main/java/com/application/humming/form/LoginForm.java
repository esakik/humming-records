package com.application.humming.form;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginForm {

    /** メールアドレス. */
    @NotNull(message = "値を入力してください")
    private String mailAddress;

    /** パスワード. */
    @NotNull(message = "値を入力してください")
    private String password;
}
