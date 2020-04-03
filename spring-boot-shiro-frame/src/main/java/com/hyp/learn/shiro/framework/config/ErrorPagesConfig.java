package com.hyp.learn.shiro.framework.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 自定义的异常页面配置
 *
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.framework.config
 * hyp create at 20-3-29
 **/
@Configuration
public class ErrorPagesConfig {
    /**
     * 自定义异常处理路径
     *
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> containerCustomizer() {
        return factory -> {
            factory.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400"));
            factory.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401"));
            factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/error/403"));
            factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
            factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
            factory.addErrorPages(new ErrorPage(Throwable.class, "/error/500"));
        };
    }
}
