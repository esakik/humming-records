package com.application.humming.logic.impl;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.application.humming.dao.ItemDao;
import com.application.humming.dao.OrderDao;
import com.application.humming.dao.OrderItemDao;
import com.application.humming.dto.ItemDto;
import com.application.humming.dto.OrderItemDto;
import com.application.humming.entity.ItemEntity;
import com.application.humming.entity.OrderEntity;
import com.application.humming.entity.OrderItemEntity;
import com.application.humming.type.OrderStatus;

@RunWith(Enclosed.class)
public class OrderLogicImplTest {

    /**
     * CreateOrderItemInfoByOrderIdTest.
     */
    @RunWith(SpringJUnit4ClassRunner.class)
    public static class createOrderItemInfoByOrderIdTest {

        @InjectMocks
        private OrderLogicImpl sut;
        @Mock
        private ItemDao itemDaoMock;
        @Mock
        private OrderItemDao orderItemDaoMock;

        private ItemEntity itemEntity;
        private ItemDto itemDto;
        private OrderItemEntity orderItemEntity;
        private OrderItemDto orderItemDto;

        private static final String SINGER = "Singer";
        private static final String SONG = "Song";
        private static final int PRICE = 1000;
        private static final int STOCK = 10;
        private static final int AMOUNT = 5;
        private static final String DESCRIPTION = "Item説明";
        private static final String IMAGE = "";

        private static final int ITEM_ID = 1;
        private static final int ORDER_ID = 1;
        private static final int ORDER_ITEM_ID = 1;
        private static final int QUANTITY = 1;

        @Before
        public void setUp() {
            itemDto = ItemDto.builder()
                    .id(ITEM_ID)
                    .singer(SINGER)
                    .song(SONG)
                    .price(PRICE)
                    .stock(STOCK)
                    .amount(AMOUNT)
                    .description(DESCRIPTION)
                    .image(IMAGE)
                    .build();

            itemEntity = new ItemEntity();
            BeanUtils.copyProperties(itemDto, itemEntity);

            orderItemEntity = OrderItemEntity.builder()
                    .id(ORDER_ITEM_ID)
                    .itemId(ITEM_ID)
                    .orderId(ORDER_ID)
                    .quantity(QUANTITY)
                    .build();

            orderItemDto = new OrderItemDto();
            BeanUtils.copyProperties(orderItemEntity, orderItemDto);
            orderItemDto.setItemInfo(itemDto);
        }

        @Test
        public void success() {
            final List<OrderItemDto> expected = Arrays.asList(orderItemDto);

            when(orderItemDaoMock.findByOrderId(ORDER_ID)).thenReturn(Arrays.asList(orderItemEntity));
            when(itemDaoMock.findAll()).thenReturn(Arrays.asList(itemEntity));

            final List<OrderItemDto> actual = sut.findOrderItemInfosByOrderId(ORDER_ID);
            assertThat(actual, equalTo(expected));
            verify(orderItemDaoMock, times(1)).findByOrderId(ORDER_ID);
            verify(itemDaoMock, times(1)).findAll();
        }

        @Test
        public void empty_case_orderItem_is_empty() {
            final List<OrderItemDto> expected = new ArrayList<>();

            when(orderItemDaoMock.findByOrderId(ORDER_ID)).thenReturn(null);

            final List<OrderItemDto> actual = sut.findOrderItemInfosByOrderId(ORDER_ID);
            assertThat(actual, equalTo(expected));
            verify(orderItemDaoMock, times(1)).findByOrderId(ORDER_ID);
            verify(itemDaoMock, times(0)).findAll();
        }

        @Test
        public void empty_case_item_is_empty() {
            final List<OrderItemDto> expected = new ArrayList<>();

            when(orderItemDaoMock.findByOrderId(ORDER_ID)).thenReturn(Arrays.asList(orderItemEntity));
            when(itemDaoMock.findAll()).thenReturn(null);

            final List<OrderItemDto> actual = sut.findOrderItemInfosByOrderId(ORDER_ID);
            assertThat(actual, equalTo(expected));
            verify(orderItemDaoMock, times(1)).findByOrderId(ORDER_ID);
            verify(itemDaoMock, times(1)).findAll();
        }
    }

    /**
     * CreateOrderInfoByOrderItemTest.
     */
    @RunWith(SpringJUnit4ClassRunner.class)
    public static class createOrderInfoByOrderItemTest {

        @InjectMocks
        private OrderLogicImpl sut;
        @Mock
        private ItemDao itemDaoMock;
        @Mock
        private OrderDao orderDaoMock;
        @Mock
        private OrderItemDao orderItemDaoMock;
        @Mock
        private HttpSession session;

        private ItemEntity itemEntity;
        private ItemDto itemDto;
        private OrderEntity orderEntity;
        private OrderItemDto orderItemDto;

        private static final String SINGER = "Singer";
        private static final String SONG = "Song";
        private static final int PRICE = 1000;
        private static final int STOCK = 10;
        private static final int AMOUNT = 5;
        private static final String DESCRIPTION = "Item説明";
        private static final String IMAGE = "";

        private static final int ITEM_ID = 1;
        private static final int MEMBER_ID = 0;
        private static final int ORDER_ID = 1;
        private static final int ORDER_ITEM_ID = 1;
        private static final int QUANTITY = 1;

        @Before
        public void setUp() {
            itemEntity = ItemEntity.builder()
                    .id(ITEM_ID)
                    .singer(SINGER)
                    .song(SONG)
                    .price(PRICE)
                    .stock(STOCK)
                    .amount(AMOUNT)
                    .description(DESCRIPTION)
                    .image(IMAGE)
                    .build();

            orderItemDto = OrderItemDto.builder()
                    .id(ORDER_ITEM_ID)
                    .itemId(ITEM_ID)
                    .orderId(ORDER_ID)
                    .quantity(QUANTITY)
                    .itemInfo(itemDto)
                    .build();

            orderEntity = OrderEntity.builder()
                    .memberId(MEMBER_ID)
                    .totalPrice(PRICE * QUANTITY)
                    .status(OrderStatus.UNDETERMINED.getCode())
                    .build();
        }

        @Test
        public void success() {
            final OrderEntity expected = orderEntity;
            when(itemDaoMock.findByPrimaryKey(ITEM_ID)).thenReturn(itemEntity);
            final OrderEntity actual = sut.createOrderInfoByOrderItem(orderItemDto);
            assertThat(actual, equalTo(expected));
        }
    }

}
