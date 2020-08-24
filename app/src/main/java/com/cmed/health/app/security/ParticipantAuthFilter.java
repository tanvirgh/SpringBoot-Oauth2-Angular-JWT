package com.cmed.health.app.security;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import java.io.IOException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Log4j2
public class ParticipantAuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String ptoken = request.getParameter("ptoken");
        log.debug("ptoken: " +ptoken);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
