package com.application.humming.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    /** ID. */
    private Integer id;

    /** 商品ID. */
    private Integer itemId;

    /** 注文ID. */
    private Integer orderId;

    /** 注文数量. */
    private Integer quantity;

    /** 注文商品. */
    private ItemDto itemInfo;
}
