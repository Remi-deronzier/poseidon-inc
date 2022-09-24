package com.nnk.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nnk.springboot.app.ApplicationRequestInterceptor;

/**
 * This class allows to configure the MVC application so as to intercept every
 * http request
 * 
 * @author Rémi Deronzier
 */
@Configuration
public class ApplicationMVCConfig implements WebMvcConfigurer {

    @Autowired
    private ApplicationRequestInterceptor applicationRequestInterceptor;

    /**
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(applicationRequestInterceptor)
                .addPathPatterns("*");
    }
}