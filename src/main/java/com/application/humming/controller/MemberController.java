package com.application.humming.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.application.humming.constant.PageConstants;
import com.application.humming.dto.MemberDto;
import com.application.humming.entity.MemberEntity;
import com.application.humming.entity.OrderEntity;
import com.application.humming.form.LoginForm;
import com.application.humming.form.RegistForm;
import com.application.humming.service.MemberService;
import com.application.humming.service.OrderService;
import com.application.humming.util.PropertiesUtil;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private OrderService orderService;

    @Autowired
    HttpSession session;

    @ModelAttribute
    public RegistForm setUpForm() {
        return new RegistForm();
    }

    @ModelAttribute
    public LoginForm setUpLoginForm() {
        return new LoginForm();
    }

    /**
     * ログイン画面を表示する.
     *
     * @return ログイン画面
     */
    @RequestMapping(value = "/loginForm")
    public String displayLoginForm() {
        return PageConstants.LOGIN_PAGE;
    }

    /**
     * ログイン処理を行う.
     *
     * @return 初期表示画面
     */
    @RequestMapping(value = "/login")
    public String login(@Validated LoginForm loginForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return displayLoginForm();
        }

        final String email = loginForm.getMailAddress();
        final String password = loginForm.getPassword();

        final MemberDto memberDto = memberService.createMemberDto(email, password);
        // ユーザー情報無し → エラーメッセージを表示する
        if (memberDto == null) {
            model.addAttribute("error", PropertiesUtil.getProperties("login.error.invalid"));
            return displayLoginForm();
        }
        // ユーザー情報有り → ユーザー情報をセッションで保持する
        session.setAttribute("member", memberDto);

        // 前回ログイン時に未確定の注文が有ればカートに反映させる TODO リファクタリング
        final OrderEntity orderEntity = (OrderEntity) session.getAttribute("order");
        if (orderEntity != null) {
            session.setAttribute("order", orderService.updateOrderInfo(memberDto.getId(), orderEntity));
        }

        return "redirect:/";
    }

    /**
     * ログアウト処理を行う.
     *
     * @return 初期表示画面
     */
    @RequestMapping(value = "/logout")
    public String logout(SessionStatus sessionStatus) {
        memberService.logout(sessionStatus);
        return "redirect:/";
    }

    /**
     * 新規会員登録画面を表示する.
     *
     * @return 新規会員登録画面
     */
    @RequestMapping(value = "/registForm")
    public String displayRegistForm() {
        return PageConstants.REGIST_PAGE;
    }

    /**
     * 新規会員登録する.
     *
     * @return ログイン画面
     */
    @RequestMapping(value = "regist")
    public String regist(@Validated RegistForm registForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return displayRegistForm();
        }

        final String email = registForm.getEmail();
        final boolean isUniqueEmailFlag = memberService.checkIfUniqueEmail(email);
        // 既に登録されたメールアドレス → エラーメッセージを表示する
        if (!isUniqueEmailFlag) {
            model.addAttribute("error", PropertiesUtil.getProperties("regist.error.invalid.email"));
            return displayRegistForm();
        }

        final String password = registForm.getPassword();
        final String confirmationPassword = registForm.getConfirmationPassword();
        // パスワードと確認パスワードが一致 → 新規会員登録する
        if (password.equals(confirmationPassword)) {
            final MemberEntity memberEntity = new MemberEntity();
            BeanUtils.copyProperties(registForm, memberEntity);
            memberService.regist(memberEntity);
            return "redirect:/member/loginForm";
        // パスワードと確認パスワードが一致しない → エラーメッセージを表示する
        } else {
            model.addAttribute("error", PropertiesUtil.getProperties("regist.error.invalid.password"));
            return displayRegistForm();
        }
    }

    /**
     * マイページを表示する.
     *
     * @return マイページ
     */
    @RequestMapping(value = "/mypage")
    public String displayMyPage() {
        if (session.getAttribute("member") == null) {
            return PageConstants.LOGIN_PAGE;
        }
        return PageConstants.MY_PAGE;
    }

    /**
     * 退会確認画面を表示する.
     *
     * @return 退会確認画面
     */
    @RequestMapping(value = "/withdraw/confirm")
    public String displayWithdrawPage() {
        if (session.getAttribute("member") == null) {
            return PageConstants.LOGIN_PAGE;
        }
        return PageConstants.WITHDRAW_CONFIRM_PAGE;
    }

    /**
     * 退会処理をする.
     *
     * @return 退会完了画面
     */
    @RequestMapping(value = "/withdraw/complete")
    public String withdraw(RegistForm registForm, SessionStatus sessionStatus) {
        if (session.getAttribute("member") == null) {
            return PageConstants.LOGIN_PAGE;
        }
        memberService.withdraw(registForm.getDeleted(), sessionStatus);
        return "redirect:/member/withdraw/redirect";
    }

    @RequestMapping(value = "/withdraw/redirect")
    public String redirectCompleteWithdrawPage() {
        return PageConstants.WITHDRAW_COMPLETE_PAGE;
    }

    /**
     * 問い合わせ画面を表示する.
     *
     * @return 問い合わせ画面
     */
    @RequestMapping(value = "/contact")
    public String displayContactPage() {
        return PageConstants.CONTACT_PAGE;
    }
}