package com.application.humming.logic.impl;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.application.humming.dao.ItemDao;
import com.application.humming.entity.ItemEntity;

@RunWith(SpringJUnit4ClassRunner.class)
public class ItemLogicImplTest {

    @InjectMocks
    private ItemLogicImpl sut;
    @Mock
    private ItemDao itemDaoMock;

    private ItemEntity itemEntity;

    private static final int ID = 1;
    private static final String SINGER = "Singer";
    private static final String SONG = "Song";
    private static final int PRICE = 1000;
    private static final int STOCK = 10;
    private static final int AMOUNT = 5;
    private static final String DESCRIPTION = "Item説明";
    private static final String IMAGE = "";
    private static final int OFFSET = 0;
    private static final int LIMIT = 12;

    @Before
    public void setUp() {
        itemEntity = ItemEntity.builder()
        .id(ID)
        .singer(SINGER)
        .song(SONG)
        .price(PRICE)
        .stock(STOCK)
        .amount(AMOUNT)
        .description(DESCRIPTION)
        .image(IMAGE)
        .build();
    }

    @Test
    public void findByPrimaryKeyTest_Success() {
        final ItemEntity expected = itemEntity;
        when(itemDaoMock.findByPrimaryKey(ID)).thenReturn(itemEntity);
        final ItemEntity actual = sut.findByPrimaryKey(ID);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void findByPrimaryKeyTest_DataNotFound() {
        when(itemDaoMock.findByPrimaryKey(ID)).thenReturn(null);
        final ItemEntity actual = sut.findByPrimaryKey(ID);
        assertThat(actual, nullValue());
    }

    @Test
    public void findAllTest_Success() {
        final List<ItemEntity> expected = Arrays.asList(itemEntity);
        when(itemDaoMock.findAll()).thenReturn(Arrays.asList(itemEntity));
        final List<ItemEntity> actual = sut.findAll();
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void findAllTest_DataNotFound() {
        when(itemDaoMock.findAll()).thenReturn(null);
        final List<ItemEntity> actual = sut.findAll();
        assertThat(actual, nullValue());
    }

    @Test
    public void findBySingerOrSongTest_Success() {
        final List<ItemEntity> expected = Arrays.asList(itemEntity);
        when(itemDaoMock.findBySingerOrSong(SINGER)).thenReturn(Arrays.asList(itemEntity));
        final List<ItemEntity> actual = sut.findBySingerOrSong(SINGER);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void findBySingerOrSongTest_DataNotFound() {
        when(itemDaoMock.findBySingerOrSong(SINGER)).thenReturn(null);
        final List<ItemEntity> actual = sut.findBySingerOrSong(SINGER);
        assertThat(actual, nullValue());
    }

    @Test
    public void findFromOffsetToLimitTest_Success() {
        final List<ItemEntity> expected = Arrays.asList(itemEntity);
        when(itemDaoMock.findFromOffsetToLimit(OFFSET, LIMIT)).thenReturn(Arrays.asList(itemEntity));
        final List<ItemEntity> actual = sut.findFromOffsetToLimit(OFFSET, LIMIT);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void findFromOffsetToLimitTest_DataNotFound() {
        when(itemDaoMock.findFromOffsetToLimit(OFFSET, LIMIT)).thenReturn(null);
        final List<ItemEntity> actual = sut.findFromOffsetToLimit(OFFSET, LIMIT);
        assertThat(actual, nullValue());
    }

    @Test
    public void getItemCountTest_Success() {
        final int expected = Arrays.asList(itemEntity).size();
        when(itemDaoMock.findAll()).thenReturn(Arrays.asList(itemEntity));
        final int actual = sut.getItemCount();
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void getItemCountTest_DataNotFound() {
        when(itemDaoMock.findAll()).thenReturn(null);
        final int actual = sut.getItemCount();
        assertThat(actual, equalTo(NumberUtils.INTEGER_ZERO));
    }

}
