package com.application.humming.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.humming.constant.PageConstants;
import com.application.humming.exception.HummingException;

@Controller
@RequestMapping(value = "/contact")
public class ContactController {

    /**
     * 問い合わせ画面を表示する.
     *
     * @return 問い合わせ画面
     * @throws HummingException
     */
    @RequestMapping(value = "/input")
    public String displayContactPage() {
        return PageConstants.CONTACT_PAGE;
    }
}
