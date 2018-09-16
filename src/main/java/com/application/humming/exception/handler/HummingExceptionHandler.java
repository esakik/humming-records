package com.application.humming.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.application.humming.constant.PageConstants;
import com.application.humming.exception.HummingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class HummingExceptionHandler {

    @ExceptionHandler(value = { HummingException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String HummingException(final HummingException e) {
        log.error("Occured HummingException, message: {}", e.getMessage());
        return PageConstants.SYSTEM_ERROR_PAGE;
    }
}