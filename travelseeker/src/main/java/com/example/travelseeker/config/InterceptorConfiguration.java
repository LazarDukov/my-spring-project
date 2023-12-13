package com.example.travelseeker.config;

import com.example.travelseeker.interceptor.HideSoldOffersInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    private final HideSoldOffersInterceptor hideSoldOffersInterceptor;

    public InterceptorConfiguration(HideSoldOffersInterceptor hideSoldOffersInterceptor) {
        this.hideSoldOffersInterceptor = hideSoldOffersInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(hideSoldOffersInterceptor); // Add path patterns for the controllers you want to intercept
    }
}
