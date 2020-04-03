package com.hyp.learn.shiro.framework.config;

import com.hyp.learn.shiro.framework.interceptor.RememberAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.framework.config
 * hyp create at 20-3-29
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private RememberAuthenticationInterceptor rememberAuthenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rememberAuthenticationInterceptor)
                .excludePathPatterns("/passport/**", "/error/**", "/assets/**", "favicon.ico", "*.html", "*.js", "*.css")
                .addPathPatterns("/**");
    }
}
