package com.application.humming.dao;

import java.util.List;

import com.application.humming.entity.ItemEntity;

import lombok.NonNull;

public interface ItemDao {

    /**
     * アイテムを主キー検索する.
     *
     * @param Integer id
     * @return ItemEntity
     */
    ItemEntity findByPrimaryKey(@NonNull final Integer id);

    /**
     * アイテムを全件取得する.
     *
     * @return List<ItemEntity>
     */
    List<ItemEntity> findAll();

    /**
     * 歌手名または曲名からアイテムを取得する.
     *
     * @param String singerOrSong
s     * @return List<ItemEntity>
     */
    List<ItemEntity> findBySingerOrSong(@NonNull final String singerOrSong);

    /**
     * OffsetからLimitまでのアイテムを取得する.
     *
     * @param Integer offset
     * @param Integer limit
     * @return List<ItemEntity>
     */
    List<ItemEntity> findFromOffsetToLimit(@NonNull final Integer offset, @NonNull final Integer limit);
}
