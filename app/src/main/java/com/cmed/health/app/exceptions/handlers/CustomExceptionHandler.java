package com.cmed.health.app.exceptions.handlers;

import com.cmed.health.app.exceptions.UnAuthorizedUserException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = UnAuthorizedUserException.class)
    public String handleUnauthorizedUser(){
        return "error";
    }
}
