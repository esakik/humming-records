package com.application.humming.logic;

import java.util.List;

import com.application.humming.entity.ItemEntity;

import lombok.NonNull;

public interface ItemLogic {

    /**
     * アイテムを1つ取得する.
     *
     * @param Integer id
     * @return ItemEntity
     */
    ItemEntity findByPrimaryKey(@NonNull final Integer id);

    /**
     * アイテムを全て取得する.
     *
     * @return List<ItemEntity>
     */
    List<ItemEntity> findAll();

    /**
     * 歌手名または曲名で検索する.
     *
     * @param String singerOrSong
     * @return List<ItemEntity>
     */
    List<ItemEntity> findBySingerOrSong(@NonNull final String singerOrSong);

    /**
     * offsetからlimitまでを検索する.
     *
     * @param Integer offset
     * @param Integer limit
     * @return List<ItemEntity>
     */
    List<ItemEntity> findFromOffsetToLimit(@NonNull final Integer offset, @NonNull final Integer limit);
}
