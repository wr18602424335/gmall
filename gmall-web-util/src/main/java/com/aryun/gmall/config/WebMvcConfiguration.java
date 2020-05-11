package com.aryun.gmall.config;

import com.aryun.gmall.interceptors.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Autowired
    AuthInterceptor addInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加token拦截器
        InterceptorRegistration tokenInterceptorRegistration = registry.addInterceptor(addInterceptor);
        tokenInterceptorRegistration.addPathPatterns("/**");
        tokenInterceptorRegistration.excludePathPatterns("/error");
        tokenInterceptorRegistration.excludePathPatterns("/static/**");
        //tokenInterceptorRegistration.excludePathPatterns("/login");
    }
}
