package com.hyp.learn.shiro.business.consts;

/**
 * @author hyp
 * Project name is spring-boot-shiro-frame
 * Include in com.hyp.learn.shiro.business.consts
 * hyp create at 20-4-1
 **/
public class CacheConst {
    public static final String PROJECT = "shiro";
    public static final String SHIRO_KICKOUT_SESSION = "shiro-kickout-session";
    public static final String PASSWORD_RETRY_CACHE = "passwordRetryCache";


    /**
     * 用户登录次数计数   redisKey 前缀
     */
    public static final String SHIRO_LOGIN_COUNT = "shiro_login_count_";
    /**
     * 用户登录是否被锁定    30分钟 redisKey 前缀
     */
    public static final String SHIRO_IS_LOCK = "shiro_is_lock_";

    /**
     * key前缀
     */
    public static final String SHIRO_REDIS_SESSION_KEY_PREFIX = "shiro.redis.session_";

}
