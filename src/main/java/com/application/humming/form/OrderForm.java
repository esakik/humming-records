package com.application.humming.form;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class OrderForm {

    /** 注文ステータス. */
    private Integer status;

    /** 注文日時. */
    private Date orderDate;

    /** 配達先氏名. */
    @NotBlank(message="お名前を入力してください")
    private String deliveryName;

    /** 配達先メールアドレス. */
    @NotBlank(message="メールアドレスを入力してください")
    @Email(message="Eメールの形式が不正です")
    private String deliveryEmail;

    /** 配達先住所. */
    @NotBlank(message="住所を入力してください")
    private String deliveryAddress;

    /** 配達先電話番号. */
    @NotBlank(message="電話番号を入力してください")
    private String deliveryTelephone;

    /** 配達時間. */
    @NotNull
    private String deliveryTime;

    /** 配達指定時間. */
    private String deliverySpecifiedTime;

    /** 合計金額. */
    private Integer totalPrice;
}