package com.hyp.learn.shiro.business.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @author hyp
 * Project name is spring-boot-shiro-frame
 * Include in com.hyp.learn.shiro.framework.redis
 * hyp create at 20-4-2
 **/
public class ShiroRedisCacheManager implements CacheManager, Destroyable {
    private static Logger LOGGER = LoggerFactory.getLogger(ShiroRedisCacheManager.class);

    private JedisConnectionFactory jedisConnectionFactory;

    public ShiroRedisCacheManager(JedisConnectionFactory jedisConnectionFactory) {
        this.jedisConnectionFactory = jedisConnectionFactory;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        LOGGER.debug("shiro redis cache manager get cache. name={} ", name);
        return new ShiroRedisJdkCache<>(name, jedisConnectionFactory);
    }

    @Override
    public void destroy() throws Exception {

    }
}