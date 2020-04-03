package com.hyp.learn.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class SpringBootShiroFrameApplication {

    public static void main(String[] args) {

        // 启动Sprign Boot
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootShiroFrameApplication.class);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

}
