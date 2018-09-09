package com.application.humming.dto;

import java.sql.Timestamp;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    /** ID. */
    private Integer id;

    /** 会員ID. */
    private Integer memberId;

    /** 注文状況. */
    private Integer status;

    /** 合計金額. */
    private Integer totalPrice;

    /** 注文日時. */
    private Date orderDate;

    /** 配達先氏名. */
    private String deliveryName;

    /** 配達先メールアドレス. */
    private String deliveryEmail;

    /** 配達先住所. */
    private String deliveryAddress;

    /** 配達先電話番号. */
    private String deliveryTelephone;

    /** 配達日時. */
    private Timestamp deliveryTime;
}
