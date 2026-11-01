package com.rapidcycling.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final static String CONTROLLER_API_V1_PACKAGE = "com.rapidcycling.controller.api.v1";
    private final static String CONTROLLER_API_V1_PATH = "/api/v1";

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix(CONTROLLER_API_V1_PATH,
                HandlerTypePredicate.forBasePackage(CONTROLLER_API_V1_PACKAGE));
    }

}
