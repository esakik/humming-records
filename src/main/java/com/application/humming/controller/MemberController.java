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
import com.application.humming.form.LoginForm;
import com.application.humming.form.RegistForm;
import com.application.humming.service.MemberService;
import com.application.humming.util.PropertiesUtil;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

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
    @RequestMapping(value = "/login/input")
    public String displayLoginPage() {
        return PageConstants.LOGIN_PAGE;
    }

    /**
     * ログイン処理を行う.
     *
     * @return 初期表示画面
     */
    @RequestMapping(value = "/login/complete")
    public String login(@Validated LoginForm loginForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return displayLoginPage();
        }

        final String email = loginForm.getEmail();
        final String password = loginForm.getPassword();

        final MemberDto memberDto = memberService.createMemberDto(email, password);
        // ユーザー情報無し → エラーメッセージを表示する
        if (memberDto.getId() == null) {
            model.addAttribute("error", PropertiesUtil.getProperties("login.error.invalid"));
            return displayLoginPage();
        }
        // ユーザー情報有り → ユーザー情報をセッションで保持する
        session.setAttribute("member", memberDto);

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
     * 会員登録入力画面を表示する.
     *
     * @return 会員登録入力画面
     */
    @RequestMapping(value = "/regist/input")
    public String displayRegistInputPage() {
        return PageConstants.REGIST_INPUT_PAGE;
    }

    /**
     * 会員登録の確認を行う.
     *
     * @return 会員登録確認画面
     */
    @RequestMapping(value = "/regist/confirm")
    public String displayRegistConfirmPage(@Validated RegistForm registForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return displayRegistInputPage();
        }

        final String email = registForm.getEmail();
        final boolean isUniqueEmailFlag = memberService.checkIfUniqueEmail(email);
        // 既に登録されたメールアドレス → エラーメッセージを表示する
        if (!isUniqueEmailFlag) {
            model.addAttribute("emailError", PropertiesUtil.getProperties("regist.error.invalid.email"));
            return displayRegistInputPage();
        }

        final String password = registForm.getPassword();
        final String confirmationPassword = registForm.getConfirmationPassword();
        // パスワードと確認パスワードが一致 → 新規会員登録する
        if (password.equals(confirmationPassword)) {
            final MemberDto memberDto = new MemberDto();
            BeanUtils.copyProperties(registForm, memberDto);
            session.setAttribute("member", memberDto);
            return PageConstants.REGIST_CONFIRM_PAGE;
        // パスワードと確認パスワードが一致しない → エラーメッセージを表示する
        } else {
            model.addAttribute("passwordError", PropertiesUtil.getProperties("regist.error.invalid.password"));
            return displayRegistInputPage();
        }
    }

    /**
     * 会員登録処理を行う.
     *
     * @return 会員登録完了画面
     */
    @RequestMapping(value = "/regist/redirect")
    public String regist() {
        final MemberDto memberDto = (MemberDto) session.getAttribute("member");
        if (memberDto == null) {
            return PageConstants.REGIST_INPUT_PAGE;
        }
        final MemberEntity memberEntity = new MemberEntity();
        BeanUtils.copyProperties(memberDto, memberEntity);
        memberService.regist(memberEntity);
        return "redirect:/member/regist/complete";
    }

    /**
     * 会員登録完了画面を表示する.
     *
     * @return 会員登録完了画面
     */
    @RequestMapping(value = "/regist/complete")
    public String displayRegistCompletePage() {
        return PageConstants.REGIST_COMPLETE_PAGE;
    }

    /**
     * 退会確認画面を表示する.
     *
     * @return 退会確認画面
     */
    @RequestMapping(value = "/withdraw/confirm")
    public String displayWithdrawConfirmPage() {
        if (session.getAttribute("member") == null) {
            return PageConstants.LOGIN_PAGE;
        }
        return PageConstants.WITHDRAW_CONFIRM_PAGE;
    }

    /**
     * 退会処理を行う.
     *
     * @return 退会完了画面
     */
    @RequestMapping(value = "/withdraw/redirect")
    public String withdraw(RegistForm registForm, SessionStatus sessionStatus) {
        if (session.getAttribute("member") == null) {
            return PageConstants.LOGIN_PAGE;
        }
        memberService.withdraw(registForm.getDeleted(), sessionStatus);
        return "redirect:/member/withdraw/complete";
    }

    /**
     * 退会完了画面を表示する.
     *
     * @return 退会完了画面
     */
    @RequestMapping(value = "/withdraw/complete")
    public String redirectCompleteWithdrawPage() {
        return PageConstants.WITHDRAW_COMPLETE_PAGE;
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
     * 問い合わせ画面を表示する.
     *
     * @return 問い合わせ画面
     */
    @RequestMapping(value = "/contact")
    public String displayContactPage() {
        return PageConstants.CONTACT_PAGE;
    }
}