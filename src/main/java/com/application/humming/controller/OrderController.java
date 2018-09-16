package com.application.humming.controller;

import java.util.List;

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
import com.application.humming.dto.MemberDto;
import com.application.humming.dto.OrderDto;
import com.application.humming.dto.OrderItemDto;
import com.application.humming.entity.OrderEntity;
import com.application.humming.exception.HummingException;
import com.application.humming.form.AddItemForm;
import com.application.humming.form.DeleteItemForm;
import com.application.humming.form.LoginForm;
import com.application.humming.form.OrderForm;
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
     * 買い物かご画面を表示する.
     *
     * @return 買い物かご画面
     */
    @RequestMapping(value = "/cart")
    public String displayCartPage(Model model) {
        final OrderDto orderDto = (OrderDto) session.getAttribute("order");
        if (orderDto != null) {
            final List<OrderItemDto> orderItemDtoList = orderService.getOrderItemInfos(orderDto.getId());
            model.addAttribute("orderItemList", orderItemDtoList);
        }
        return PageConstants.CART_PAGE;
    }

    /**
     * 注文アイテムを買い物かごに追加する.
     *
     * @return 買い物かご画面
     */
    @RequestMapping(value="/cart/add")
    public String addOrderItem(AddItemForm addItemForm, Model model) {
        final OrderItemDto orderItemDto = new OrderItemDto();
        BeanUtils.copyProperties(addItemForm, orderItemDto);
        session.setAttribute("order", orderService.addOrderItem(orderItemDto));
        return displayCartPage(model);
    }

    /**
     * 注文アイテムを買い物かごから削除する.
     *
     * @return 買い物かご画面
     */
    @RequestMapping(value="/cart/delete")
    public String deleteOrderItem(DeleteItemForm deleteItemForm, Model model) {
        final OrderItemDto orderItemDto = new OrderItemDto();
        BeanUtils.copyProperties(deleteItemForm, orderItemDto);
        session.setAttribute("order", orderService.deleteOrderItem(orderItemDto));
        return displayCartPage(model);
    }

    /**
     * 注文内容を表示する.
     *
     * @return 注文確認画面
     */
    @RequestMapping(value="/confirm")
    public String displayOrderConfirmPage(Model model) {
        final MemberDto memberDto = (MemberDto) session.getAttribute("member");
        if(memberDto == null) {
            return PageConstants.LOGIN_PAGE;
        }
        final OrderDto orderDto = (OrderDto) session.getAttribute("order");
        final List<OrderItemDto> orderItemDtoList = orderService.getOrderItemInfos(orderDto.getId());
        model.addAttribute("orderItemList", orderItemDtoList);
        return PageConstants.ORDER_CONFIRM_PAGE;
    }

    /**
     * 注文内容を確定する.
     *
     * @return 注文確定画面
     * @throws HummingException
     */
    @RequestMapping(value = "/redirect")
    public String order(@Validated OrderForm orderForm, BindingResult result, RedirectAttributes redirectAttributes, Model model) throws HummingException {
        MemberDto memberDto = (MemberDto) session.getAttribute("member");
        final OrderDto orderDto = (OrderDto) session.getAttribute("order");
        if(memberDto.getId() == null || orderDto == null) {
            return PageConstants.LOGIN_PAGE;
        }

        if (result.hasErrors()) {
            return displayOrderConfirmPage(model);
        }

        final OrderEntity orderEntity= new OrderEntity();
        BeanUtils.copyProperties(orderForm, orderEntity);
        orderEntity.setId(orderDto.getId());
        orderEntity.setMemberId(memberDto.getId());

        orderService.orderComplete(orderEntity, orderForm.getDeliveryTime(), orderForm.getDeliverySpecifiedTime());
        session.removeAttribute("order");
        return "redirect:/order/complete";
    }

    /**
     * 注文確定画面を表示する.
     *
     * @return 注文確定画面
     */
    @RequestMapping(value = "/complete")
    public String displayOrderCompletePage() {
        return PageConstants.ORDER_COMPLETE_PAGE;
    }
}