package com.cmed.health.app.util;

import com.cmed.health.app.security.jwt.JwtTokenUtil;
import com.cmed.health.core.dto.ActorUser;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class RequestContext {

    @Autowired
    HttpServletRequest request;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public ActorUser getCurrentUser(){
        return (ActorUser) request.getAttribute("actorUser");
    }
}
