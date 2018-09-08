package com.application.humming.form;

import com.application.humming.entity.ItemEntity;

import lombok.Data;

@Data
public class ShoppingCartForm {

    /** ID. */
    private Integer id;

    /** 商品ID. */
    private Integer itemId;

    /** 注文ID. */
    private Integer orderId;

    /** 注文数量. */
    private Integer quantity;

    /** 注文商品. */
    private ItemEntity item;
}