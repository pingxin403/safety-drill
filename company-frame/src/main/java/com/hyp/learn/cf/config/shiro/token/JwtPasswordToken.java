package com.hyp.learn.cf.config.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;


public class JwtPasswordToken extends UsernamePasswordToken {
    private String token;

    public JwtPasswordToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }
}
