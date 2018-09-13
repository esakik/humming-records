package com.application.humming.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.application.humming.constant.PageConstants;
import com.application.humming.dto.ItemDto;
import com.application.humming.dto.MemberDto;
import com.application.humming.dto.OrderDto;
import com.application.humming.dto.OrderItemDto;
import com.application.humming.entity.MemberEntity;
import com.application.humming.entity.OrderEntity;
import com.application.humming.form.LoginForm;
import com.application.humming.form.OrderForm;
import com.application.humming.form.ShoppingCartForm;
import com.application.humming.service.OrderService;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpSession session;

    @ModelAttribute
    public LoginForm setUpLoginForm() {
        return new LoginForm();
    }

    /**
     * カートの中身を表示する.
     *
     * @return カート画面
     */
    @RequestMapping(value = "/cart")
    public String displayCart(Model model) {
        final OrderDto orderDto = (OrderDto) session.getAttribute("order");

        if (orderDto != null) {
            final List<OrderItemDto> orderItemDtoList = orderService.getOrderItemInCart(orderDto.getId());
            model.addAttribute("orderItemList", orderItemDtoList);

            final List<ItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.addAll(orderItemDtoList.stream().map(OrderItemDto::getItemDto).collect(Collectors.toList()));
            model.addAttribute("itemList", itemDtoList);
        }
        return PageConstants.CART_PAGE;
    }

    /**
     * 商品をカートに追加する.
     *
     * @return カート画面
     */
    @RequestMapping(value="/cart/add")
    public String addToCart(ShoppingCartForm shoppingCartForm, Model model) {
        final OrderItemDto orderItemDto = new OrderItemDto();
        BeanUtils.copyProperties(shoppingCartForm, orderItemDto);

        final OrderDto orderDto = orderService.addToCart(orderItemDto);
        session.setAttribute("order", orderDto);
        return displayCart(model);
    }

    /**
     * 商品をカートから削除する.
     *
     * @return カート画面
     */
    @RequestMapping(value="/cart/delete")
    public String deleteOrderItem(ShoppingCartForm shoppingCartForm, Model model) {
        OrderDto orderDto = (OrderDto) session.getAttribute("order");

        final OrderItemDto orderItemDto = new OrderItemDto();
        BeanUtils.copyProperties(shoppingCartForm, orderItemDto);

        orderDto = orderService.deleteOrderItemFromCart(orderDto, orderItemDto);
        session.setAttribute("order", orderDto);
        return displayCart(model);
    }

    /**
     * 注文内容を表示する.
     *
     * @return 注文確認画面
     */
    @RequestMapping(value="/confirm")
    public String displayConfirmOrder(Model model) {
        final MemberDto memberDto = (MemberDto) session.getAttribute("member");
        if(memberDto == null) {
            return PageConstants.LOGIN_PAGE;
        }

        final OrderDto orderDto = (OrderDto) session.getAttribute("order");
        final List<OrderItemDto> orderItemDtoList = orderService.getOrderedItems(orderDto.getId());
        model.addAttribute("orderItemList", orderItemDtoList);

        final List<ItemDto> itemDtoList = new ArrayList<>();
        itemDtoList.addAll(orderItemDtoList.stream().map(OrderItemDto::getItemDto).collect(Collectors.toList()));
        model.addAttribute("itemList", itemDtoList);

        return PageConstants.ORDER_CONFIRM_PAGE;
    }

    /**
     * 注文内容を確定する.
     *
     * @return 注文確定画面
     */
    @RequestMapping(value = "/redirect")
    public String completeOrder(@Validated OrderForm orderForm, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        MemberDto memberDto = (MemberDto) session.getAttribute("member");
        if(memberDto == null) {
            return PageConstants.LOGIN_PAGE;
        }

        if (result.hasErrors()) {
            return displayConfirmOrder(model);
        }

        final OrderEntity orderEntity= new OrderEntity();
        BeanUtils.copyProperties(orderForm, orderEntity);

        orderEntity.setTotalPrice(orderForm.getTotalPrice());
        orderEntity.setMemberId(memberDto.getId());

        orderService.completeOrder(orderEntity, orderForm.getDeliveryTime(), orderForm.getDeliverySpecifiedTime());

        session.removeAttribute("order");

        return "redirect:/order/complete";
    }

    /**
     * 注文確定画面を表示する.
     *
     * @return 注文確定画面
     */
    @RequestMapping(value = "/complete")
    public String orderRedirect() {
        return PageConstants.ORDER_COMPLETE_PAGE;
    }

    /**
     * 注文履歴を表示する.
     *
     * @return 注文履歴画面
     */
    @RequestMapping(value="/history")
    public String displayOrderHistory(LoginForm forn, Model model) {
        final MemberDto memberDto = (MemberDto) session.getAttribute("member");
        if (memberDto == null) {
            return PageConstants.LOGIN_PAGE;
        }

        final MemberEntity memberEntity= new MemberEntity();
        BeanUtils.copyProperties(memberDto, memberEntity);

        final List<OrderDto> orderDtoList = orderService.getOrderHistory(memberEntity.getId());
        model.addAttribute("orderList", orderDtoList);

        if (orderDtoList == null) {
            return PageConstants.ORDER_HISTORY_PAGE;
        }

        final List<OrderItemDto> orderItemDtoList = orderService.getOrderItemHistory(orderDtoList);
        model.addAttribute("orderItemList", orderItemDtoList);

        final List<ItemDto> itemDtoList = new ArrayList<>();
        itemDtoList.addAll(orderItemDtoList.stream().map(OrderItemDto::getItemDto).collect(Collectors.toList()));
        model.addAttribute("itemList", itemDtoList);

        return PageConstants.ORDER_HISTORY_PAGE;
    }
}