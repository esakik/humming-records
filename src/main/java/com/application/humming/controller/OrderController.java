package com.application.humming.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.application.humming.constant.PageConstants;
import com.application.humming.entity.ItemEntity;
import com.application.humming.entity.MemberEntity;
import com.application.humming.entity.OrderEntity;
import com.application.humming.entity.OrderItemEntity;
import com.application.humming.form.LoginForm;
import com.application.humming.form.OrderForm;
import com.application.humming.form.ShoppingCartForm;
import com.application.humming.logic.OrderLogic;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderLogic orderLogic;

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
        final OrderEntity orderEntity = (OrderEntity) session.getAttribute("order");
        if (orderEntity != null) {
            final List<OrderItemEntity> orderItemEntityList = orderLogic.getOrderItemInfo(orderEntity);
            final List<ItemEntity> itemEntityList = orderLogic.getItemInfo(orderEntity);
            model.addAttribute("orderItemList", orderItemEntityList);
            model.addAttribute("itemList", itemEntityList);
        }
        return PageConstants.CART_PAGE;
    }

    /**
     * 商品をカートに追加する.
     *
     * @return カート画面
     */
    @RequestMapping(value="/cart/add")
    public String addCart(ShoppingCartForm shoppingCartForm, Model model) {
        final MemberEntity memberEntity = (MemberEntity) session.getAttribute("member");
        final OrderEntity orderEntity = (OrderEntity) session.getAttribute("order");
        final OrderEntity latestOrder = orderLogic.putItemIntoShoppingCart(shoppingCartForm, orderEntity, memberEntity);
        final List<ItemEntity> itemEntityList = orderLogic.getItemInfo(latestOrder);
        final List<OrderItemEntity> orderItemEntityList = orderLogic.getOrderItemInfo(latestOrder);
        session.setAttribute("itemList", itemEntityList);
        session.setAttribute("orderItemList", orderItemEntityList);
        session.setAttribute("order", latestOrder);
        return displayCart(model);
    }

    /**
     * 商品をカートから削除する.
     *
     * @return カート画面
     */
    @RequestMapping(value="/cart/delete")
    public String deleteOrderItem(ShoppingCartForm shoppingCartForm, Model model) {
        OrderEntity orderEntity = (OrderEntity) session.getAttribute("order");
        orderEntity = orderLogic.deleteOrderItem(orderEntity, shoppingCartForm);
        session.setAttribute("order", orderEntity);
        return displayCart(model);
    }

    /**
     * 注文内容を表示する.
     *
     * @return 注文内容確認画面
     */
    @RequestMapping(value="/confirm")
    public String displayConfirmOrder(Model model) {
        final MemberEntity memberEntity = (MemberEntity)session.getAttribute("member");
        if(memberEntity == null) {
            return PageConstants.LOGIN_PAGE;
        }

        final OrderEntity orderEntity = (OrderEntity) session.getAttribute("order");
        final List<OrderItemEntity> orderItemEntityList = orderLogic.getOrderItemInfo(orderEntity);
        final List<ItemEntity> itemEntityList = orderLogic.getItemInfo(orderEntity);
        model.addAttribute("itemList", itemEntityList);
        model.addAttribute("orderItemList", orderItemEntityList);
        return PageConstants.ORDER_CONFIRM_PAGE;
    }

    /**
     * 注文内容を確定する.
     *
     * @return 注文内容確定画面
     */
    @RequestMapping(value = "/complete")
    public String completeOrder(LoginForm loginForm, @Validated OrderForm orderForm, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        MemberEntity memberEntity = (MemberEntity)session.getAttribute("member");
        if(memberEntity == null) {
            return PageConstants.LOGIN_PAGE;
        }

        if (result.hasErrors()) {
            return displayConfirmOrder(model);
        }

        final OrderEntity orderEntity = (OrderEntity) session.getAttribute("order");
        orderEntity.setDeliveryName(orderForm.getDeliveryName());
        orderEntity.setDeliveryAddress(orderForm.getDeliveryAddress());
        orderEntity.setDeliveryEmail(orderForm.getDeliveryEmail());
        orderEntity.setDeliveryTelephone(orderForm.getDeliveryTelephone());
        Date now = new Date();
        orderEntity.setOrderDate(now);
        orderEntity.setStatus(1);

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date formatDate = new Date();
        final Calendar calendar = Calendar.getInstance();

        try {
            formatDate = sdf.parse(orderForm.getDeliveryTime());
            calendar.setTime(formatDate);
            calendar.add(Calendar.HOUR_OF_DAY, Integer.parseInt(orderForm.getDeliverySpecifiedTime()));

        } catch (ParseException e) {

        }
        final Date date = calendar.getTime();
        final Timestamp time = new Timestamp(date.getTime());
        orderEntity.setDeliveryTime(time);
        orderLogic.updateStatusByOrderId(orderEntity);

        session.setAttribute("order", null);
        session.removeAttribute("itemList");
        session.removeAttribute("orderItemList");

        return "redirect:/order/redirect";
    }

    @RequestMapping(value = "/redirect")
    public String orderRedirect() {
        return PageConstants.ORDER_COMPLETE_PAGE;
    }

    /**
     * 注文履歴を表示する.
     *
     * @return 注文履歴
     */
    @RequestMapping(value="/history")
    public String displayOrderHistory(LoginForm forn, Model model) {
        final MemberEntity memberEntity = (MemberEntity) session.getAttribute("member");
        if (memberEntity == null) {
            return PageConstants.LOGIN_PAGE;
        }
        final List<OrderEntity> orderEntityList = orderLogic.getOrderHistory(memberEntity);
        model.addAttribute("orderList", orderEntityList);
        if (orderEntityList == null) {
            return PageConstants.ORDER_HISTORY_PAGE;
        }
        final List<OrderItemEntity> orderItemEntityList = orderLogic.searchOrderItemHistory(orderEntityList);
        model.addAttribute("orderItemList", orderItemEntityList);
        final List<ItemEntity> itemEntityList = orderLogic.searchItemHistory(orderItemEntityList);
        model.addAttribute("itemList", itemEntityList);
        return PageConstants.ORDER_HISTORY_PAGE;
    }
}