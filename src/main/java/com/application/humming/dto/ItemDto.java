package com.application.humming.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    /** ID. */
    private Integer id;

    /** 歌手名. */
    private String singer;

    /** 曲名. */
    private String song;

    /** 価格. */
    private Integer price;

    /** 在庫. */
    private Integer stock;

    /** 売上量. */
    private Integer amount;

    /** 商品説明. */
    private String description;

    /** 商品画像. */
    private String image;
}
