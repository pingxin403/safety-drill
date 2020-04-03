package com.hyp.learn.shiro.business.shiro.dao;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import java.io.Serializable;

/**
 * @author hyp
 * Project name is spring-boot-shiro-frame
 * Include in com.hyp.learn.shiro.business.shiro.dao
 * hyp create at 20-4-2
 **/
public class ShiroRedisCacheSessionDAO extends CachingSessionDAO {
    @Override
    protected void doUpdate(Session session) {
        this.getCacheManager().getCache(this.getClass().getName()).put(session.getId(), session);
    }

    @Override
    protected void doDelete(Session session) {
        this.getCacheManager().getCache(this.getClass().getName()).remove(session.getId());
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.getCacheManager().getCache(this.getClass().getName()).put(session.getId(), session);
        return sessionId;

    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return (Session) this.getCacheManager().getCache(this.getClass().getName()).get(sessionId);
    }
}
