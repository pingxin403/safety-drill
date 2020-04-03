package com.hyp.learn.shiro.business.shiro.dao;

import com.hyp.learn.shiro.business.consts.CacheConst;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author hyp
 * Project name is spring-boot-shiro-frame
 * Include in com.hyp.learn.shiro.business.shiro
 * hyp create at 20-4-2
 **/
public class ShiroRedisSessionDAO extends AbstractSessionDAO {
    private static Logger LOGGER = LoggerFactory.getLogger(ShiroRedisSessionDAO.class);
    /**
     * key前缀
     */
    private static final String SHIRO_REDIS_SESSION_KEY_PREFIX = CacheConst.SHIRO_REDIS_SESSION_KEY_PREFIX;

    private RedisTemplate<String, Object> redisTemplate;

    private ValueOperations valueOperations;

    public ShiroRedisSessionDAO(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("shiro redis session create. sessionId={}", sessionId);
        }
        this.assignSessionId(session, sessionId);
        valueOperations.set(generateKey(sessionId), session, session.getTimeout(), TimeUnit.MILLISECONDS);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("shiro redis session read. sessionId={}", sessionId);
        }
        return (Session) valueOperations.get(generateKey(sessionId));
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("shiro redis session update. sessionId={}", session.getId());
        }
        valueOperations.set(generateKey(session.getId()), session, session.getTimeout(), TimeUnit.MILLISECONDS);
    }

    @Override
    public void delete(Session session) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("shiro redis session delete. sessionId={}", session.getId());
        }
        redisTemplate.delete(generateKey(session.getId()));
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<String> keySet = redisTemplate.keys(generateKey("*"));
        Set<Session> sessionSet = new HashSet<>();
        if (CollectionUtils.isEmpty(keySet)) {
            return Collections.emptySet();
        }
        for (Object key : keySet) {
            sessionSet.add((Session) valueOperations.get(key));
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("shiro redis session all. size={}", sessionSet.size());
        }
        return sessionSet;
    }

    /**
     * 重组key
     * 区别其他使用环境的key
     *
     * @param key
     * @return
     */
    private String generateKey(Object key) {
        return SHIRO_REDIS_SESSION_KEY_PREFIX + this.getClass().getName() + "_" + key;
    }
}
