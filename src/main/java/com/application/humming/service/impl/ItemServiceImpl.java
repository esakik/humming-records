package com.application.humming.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.application.humming.dto.ItemDto;
import com.application.humming.entity.ItemEntity;
import com.application.humming.exception.HummingException;
import com.application.humming.logic.ItemLogic;
import com.application.humming.service.ItemService;
import com.application.humming.util.PropertiesUtil;

import lombok.NonNull;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemLogic itemLogic;

    /**
     * 初期表示のアイテム一覧を取得する.
     *
     * @param Integer offset
     * @param Integer limit
     * @return List<ItemEntity>
     * @throws HummingException
     */
    @Override
    public List<ItemDto> getInitialItemList(@NonNull final Integer offset, @NonNull final Integer limit) throws HummingException {
        final List<ItemEntity> itemEntityList = itemLogic.findFromOffsetToLimit(offset, limit);
        if (CollectionUtils.isEmpty(itemEntityList)) {
            // TODO Exception Handler
            throw new HummingException(PropertiesUtil.getProperties("item.error.empty.result"));
        }
        final List<ItemDto> itemDtoList = new ArrayList<>();
        itemEntityList.stream().forEach(itemEntity -> {
            final ItemDto itemDto = new ItemDto();
            BeanUtils.copyProperties(itemEntity, itemDto);
            itemDtoList.add(itemDto);
        });
        return itemDtoList;
    }

    /**
     * アイテム詳細を取得する.
     *
     * @param Integer id
     * @return ItemEntity
     */
    @Override
    public ItemDto getItemDetail(@NonNull final Integer id) {
        final ItemEntity itemEntity = itemLogic.findByPrimaryKey(id);
        final ItemDto itemDto = new ItemDto();
        BeanUtils.copyProperties(itemEntity, itemDto);
        return itemDto;
    }

    /**
     * 歌手名または曲名でアイテムを取得する.
     *
     * @param String singerOrSong
     * @return List<ItemEntity>
     */
    @Override
    public List<ItemDto> getItemWithSingerOrSong(@NonNull final String singerOrSong) {
        final List<ItemEntity> itemEntityList = itemLogic.findBySingerOrSong(singerOrSong);
        final List<ItemDto> itemDtoList = new ArrayList<>();
        itemEntityList.stream().forEach(itemEntity -> {
            final ItemDto itemDto = new ItemDto();
            BeanUtils.copyProperties(itemEntity, itemDto);
            itemDtoList.add(itemDto);
        });
        return itemDtoList;
    }
}
