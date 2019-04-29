package com.k2data.kbc.login.config;

import com.k2data.kbc.login.config.intercepors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;


@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Value("${kbc.login.interceptor.allow.origin}")
    private String loginAllowOrigin;
    @Value("${kbc.login.interceptor.intercept.origin}")
    private String loginInterceptOrigin;


    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns(loginInterceptOrigin).excludePathPatterns(stringOriginToList(loginAllowOrigin));
    }

    private static List<String> stringOriginToList(String origins){
        return Arrays.asList(origins.split(","));
    }

}
