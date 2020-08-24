package com.cmed.health.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author razib
 * @date 11/4/18
 * @email fakrul@impelitsolutions.com
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED, value = HttpStatus.UNAUTHORIZED)
public class UnAuthorizedUserException extends RuntimeException {

    public UnAuthorizedUserException(String msg) {
        super(msg);
    }
}
