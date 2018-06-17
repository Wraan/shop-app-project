package com.shop.config;

import com.shop.interceptors.ConnectionManagement;
import com.shop.interceptors.SessionManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by Mike on 01.07.2017.
 */
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
    SessionManagement sessionManagement;
    ConnectionManagement connectionManagement;

    @Autowired
    public MvcConfig(SessionManagement sessionManagement, ConnectionManagement connectionManagement) {
        this.sessionManagement = sessionManagement;
        this.connectionManagement = connectionManagement;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionManagement)
                .addPathPatterns("/**");

        registry.addInterceptor(connectionManagement)
                .addPathPatterns("/**");
    }
}
