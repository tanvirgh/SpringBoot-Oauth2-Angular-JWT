package com.cmed.health.app.configs;

import com.cmed.health.app.security.ParticipantAuthFilter;
import com.cmed.health.app.support.WebLinkFactory;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

/**
 * @author razib
 * @date 5/6/18
 * @email fakrul@impelitsolutions.com
 */

@Configuration
@EnableAsync
public class BeansConfig {

    @Bean
    public ModelMapper mapper() {
        ModelMapper mapper = new ModelMapper();
        return mapper;
    }

    @Bean
    public DeviceResolverHandlerInterceptor
    deviceResolverHandlerInterceptor() {
        return new DeviceResolverHandlerInterceptor();
    }

    @Bean
    public DeviceHandlerMethodArgumentResolver
    deviceHandlerMethodArgumentResolver() {
        return new DeviceHandlerMethodArgumentResolver();
    }

    @Bean(name = "webLinkFactory")
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public WebLinkFactory webLinkFactory() {
        return new WebLinkFactory();
    }

    @Bean
    public FilterRegistrationBean registerParticipantAuthFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(getParticipantFilter());
        registration.addUrlPatterns("/participants/*");
        registration.setName("participantAuthFilter");
        registration.setOrder(1);
        return registration;
    }

    public Filter getParticipantFilter() {
        return new ParticipantAuthFilter();
    }
/*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(deviceResolverHandlerInterceptor());
    }

    @Override
    public void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(deviceHandlerMethodArgumentResolver());
    }*/

}
