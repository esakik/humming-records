package com.application.humming.form;

import lombok.Data;

@Data
public class DeleteItemForm {

    /** ID. */
    private Integer id;

    /** 商品ID. */
    private Integer itemId;

    /** 注文ID. */
    private Integer orderId;

    /** 注文数量. */
    private Integer quantity;
}