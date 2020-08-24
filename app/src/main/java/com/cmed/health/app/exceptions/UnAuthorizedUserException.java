package com.cmed.health.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED, value = HttpStatus.UNAUTHORIZED)
public class UnAuthorizedUserException extends RuntimeException {

    public UnAuthorizedUserException(String msg) {
        super(msg);
    }
}
