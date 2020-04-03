package com.hyp.learn.shiro.business.consts;

/**
 * 程序中公用的常量类
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.business.consts
 * hyp create at 20-3-29
 **/
public class CommonConst {
    /**
     * 安全密码(UUID生成)，作为盐值用于用户密码的加密
     */
    public static final String PX_SECURITY_KEY = "929123f8f17944e8b0a531045453e1f1";

    /**
     * 程序默认的错误状态码
     */
    public static final int DEFAULT_ERROR_CODE = 500;

    /**
     * 程序默认的成功状态码
     */
    public static final int DEFAULT_SUCCESS_CODE = 200;

    public static final String CAPTCHA_PARAM = "verifyCode"; //前台提交的验证码参数名

    public static final String FAILURE_KEY_ATTRIBUTE = "shiroLoginFailure";  //验证失败后存储到的属性名
}
