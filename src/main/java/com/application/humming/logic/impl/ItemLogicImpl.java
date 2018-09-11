package com.application.humming.logic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.application.humming.dao.ItemDao;
import com.application.humming.entity.ItemEntity;
import com.application.humming.logic.ItemLogic;

import lombok.NonNull;

@Component
public class ItemLogicImpl implements ItemLogic {

    @Autowired
    private ItemDao itemDao;

    @Override
    public ItemEntity findByPrimaryKey(@NonNull final Integer id) {
        return itemDao.findByPrimaryKey(id);
    }

    @Override
    public List<ItemEntity> findAll() {
        return itemDao.findAll();
    }

    @Override
    public List<ItemEntity> findBySingerOrSong(@NonNull final String singerOrSong) {
        return itemDao.findBySingerOrSong(singerOrSong);
    }

    @Override
    public List<ItemEntity> findFromOffsetToLimit(@NonNull final Integer offset, @NonNull final Integer limit) {
        return itemDao.findFromOffsetToLimit(offset, limit);
    }

    @Override
    public Integer getItemCount() {
        return itemDao.findAll().size();
    }
}
