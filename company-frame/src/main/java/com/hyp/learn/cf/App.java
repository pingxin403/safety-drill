package com.hyp.learn.cf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hyp
 * Project name is company-frame
 * Include in com.hyp.learn.cf
 * hyp create at 20-3-3
 **/
@SpringBootApplication(scanBasePackages = {"com.hyp.learn.cf"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}
