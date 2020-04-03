package com.hyp.learn.cf.config.shiro.matcher;


import com.hyp.learn.cf.commons.constants.Constant;
import com.hyp.learn.cf.commons.exception.BusinessException;
import com.hyp.learn.cf.commons.exception.code.BaseResponseCode;
import com.hyp.learn.cf.config.shiro.token.JwtPasswordToken;
import com.hyp.learn.cf.service.impl.RedisService;
import com.hyp.learn.cf.utils.JwtTokenUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;


public class ShiroHashedCredentialsMatcher extends SimpleCredentialsMatcher {

    @Autowired
    private RedisService redisService;
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        JwtPasswordToken jwtPasswordToken = (JwtPasswordToken) token;
        String accessToken = (String) jwtPasswordToken.getPrincipal();
        String userId= JwtTokenUtil.getUserId(accessToken);
        if(redisService.hasKey(Constant.ACCOUNT_LOCK_KEY+userId)){
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK_ERROR);
        }
        if(redisService.hasKey(Constant.DELETED_USER_KEY+userId)){
            throw new BusinessException(BaseResponseCode.ACCOUNT_ERROR);
        }
        if(redisService.hasKey(Constant.JWT_REFRESH_TOKEN_BLACKLIST+accessToken)){
            throw new BusinessException(BaseResponseCode.TOKEN_ERROR);
        }
        if(redisService.hasKey(Constant.JWT_REFRESH_STATUS+accessToken)){
            return true;
        }
        if(JwtTokenUtil.isTokenExpired(accessToken)){
            throw new BusinessException(BaseResponseCode.TOKEN_PAST_DUE);
        }
        if(redisService.hasKey(Constant.JWT_REFRESH_KEY+userId)&&redisService.getExpire(Constant.JWT_REFRESH_KEY+userId, TimeUnit.MILLISECONDS)>JwtTokenUtil.getRemainingTime(accessToken)){
            if(!redisService.hasKey(Constant.JWT_REFRESH_IDENTIFICATION+accessToken)){
                throw new BusinessException(BaseResponseCode.TOKEN_PAST_DUE);
            }
        }
        return true;
    }
}
