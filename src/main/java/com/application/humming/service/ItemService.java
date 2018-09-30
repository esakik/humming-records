package com.application.humming.service;

import java.util.List;

import com.application.humming.dto.ItemDto;
import com.application.humming.exception.HummingException;

import lombok.NonNull;

public interface ItemService {

    /**
     * 初期表示のアイテム一覧を取得する.
     *
     * @param offset
     * @param limit
     * @return List<ItemEntity>
     * @throws HummingException
     */
    List<ItemDto> getInitialItemList(@NonNull final Integer offset, @NonNull final Integer limit) throws HummingException;

    /**
     * アイテム詳細を取得する.
     *
     * @param id
     * @return ItemEntity
     */
    ItemDto getItemDetail(@NonNull final Integer id);

    /**
     * 歌手名または曲名でアイテムを取得する.
     *
     * @param singerOrSong
     * @return List<ItemEntity>
     */
    List<ItemDto> getItemWithSingerOrSong(@NonNull final String singerOrSong);

    /**
     * ページング数を計算する.
     *
     * @return Integer
     */
    Integer calculatePagingCount();
}
