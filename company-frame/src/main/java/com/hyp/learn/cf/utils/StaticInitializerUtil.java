package com.hyp.learn.cf.utils;


import com.hyp.learn.cf.config.property.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class StaticInitializerUtil {

    @Autowired
    private JwtProperties jwtProperties;

    public StaticInitializerUtil(JwtProperties jwtProperties) {
        JwtTokenUtil.setTokenSettings(jwtProperties);
    }
}
