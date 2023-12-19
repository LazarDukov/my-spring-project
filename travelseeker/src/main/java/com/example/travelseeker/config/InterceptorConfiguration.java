package com.example.travelseeker.config;

import com.example.travelseeker.interceptor.AddingOffersInCartInterceptor;
import com.example.travelseeker.interceptor.HideSoldOffersInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    private final HideSoldOffersInterceptor hideSoldOffersInterceptor;
    private final AddingOffersInCartInterceptor addingOffersInCartInterceptor;


    public InterceptorConfiguration(HideSoldOffersInterceptor hideSoldOffersInterceptor, AddingOffersInCartInterceptor addingOffersInCartInterceptor) {
        this.hideSoldOffersInterceptor = hideSoldOffersInterceptor;
        this.addingOffersInCartInterceptor = addingOffersInCartInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(hideSoldOffersInterceptor);
        registry.addInterceptor(addingOffersInCartInterceptor);
    }
}
