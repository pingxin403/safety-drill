package com.hyp.learn.shiro.business.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author hyp
 * Project name is spring-boot-shiro-frame
 * Include in com.hyp.learn.shiro.framework.redis
 * hyp create at 20-4-2
 **/
public class ShiroRedisJdkCache<K, V> implements Cache<K, V> {
    private static Logger LOGGER = LoggerFactory.getLogger(ShiroRedisJdkCache.class);

    /**
     * key前缀
     */
    private static final String REDIS_SHIRO_CACHE_KEY_PREFIX = "redis.shiro.cache_";

    /**
     * cache name
     */
    private String name;

    /**
     * jedis 连接工厂
     */
    private JedisConnectionFactory jedisConnectionFactory;

    /**
     * 序列化工具
     */
    private RedisSerializer serializer = new JdkSerializationRedisSerializer();

    /**
     * 存储key的redis.list的key值
     */
    private String keyListKey;

    public ShiroRedisJdkCache(String name, JedisConnectionFactory jedisConnectionFactory) {
        this.name = name;
        this.jedisConnectionFactory = jedisConnectionFactory;
        this.keyListKey = "redis.shiro.cache.key_" + name;
    }

    @Override
    public V get(K key) throws CacheException {
        LOGGER.debug("shiro redis cache get.{} K={}", name, key);
        RedisConnection redisConnection = null;
        V result = null;
        try {
            redisConnection = jedisConnectionFactory.getConnection();
            result = (V) serializer.deserialize(redisConnection.get(serializer.serialize(generateKey(key))));
        } catch (Exception e) {
            LOGGER.error("shiro redis cache get exception. ", e);
        } finally {
            if (null != redisConnection) {
                redisConnection.close();
            }
        }
        return result;
    }

    @Override
    public V put(K key, V value) throws CacheException {
        LOGGER.debug("shiro redis cache put.{} K={} V={}", name, key, value);
        RedisConnection redisConnection = null;
        V result = null;
        try {
            redisConnection = jedisConnectionFactory.getConnection();
            result = (V) serializer.deserialize(redisConnection.get(serializer.serialize(generateKey(key))));

            redisConnection.set(serializer.serialize(generateKey(key)), serializer.serialize(value));

            redisConnection.lPush(serializer.serialize(keyListKey), serializer.serialize(generateKey(key)));
        } catch (Exception e) {
            LOGGER.error("shiro redis cache put exception. ", e);
        } finally {
            if (null != redisConnection) {
                redisConnection.close();
            }
        }
        return result;
    }

    @Override
    public V remove(K key) throws CacheException {
        LOGGER.debug("shiro redis cache remove.{} K={}", name, key);
        RedisConnection redisConnection = null;
        V result = null;
        try {
            redisConnection = jedisConnectionFactory.getConnection();
            result = (V) serializer.deserialize(redisConnection.get(serializer.serialize(generateKey(key))));

            redisConnection.expireAt(serializer.serialize(generateKey(key)), 0);

            redisConnection.lRem(serializer.serialize(keyListKey), 0, serializer.serialize(key));
        } catch (Exception e) {
            LOGGER.error("shiro redis cache remove exception. ", e);
        } finally {
            if (null != redisConnection) {
                redisConnection.close();
            }
        }
        return result;
    }

    @Override
    public void clear() throws CacheException {
        LOGGER.debug("shiro redis cache clear.{}", name);
        RedisConnection redisConnection = null;
        try {
            redisConnection = jedisConnectionFactory.getConnection();

            Long length = redisConnection.lLen(serializer.serialize(keyListKey));
            if (0 == length) {
                return;
            }

            List<byte[]> keyList = redisConnection.lRange(serializer.serialize(keyListKey), 0, length - 1);
            for (byte[] key : keyList) {
                redisConnection.expireAt(key, 0);
            }

            redisConnection.expireAt(serializer.serialize(keyListKey), 0);
            keyList.clear();
        } catch (Exception e) {
            LOGGER.error("shiro redis cache clear exception.", e);
        } finally {
            if (null != redisConnection) {
                redisConnection.close();
            }
        }
    }

    @Override
    public int size() {
        LOGGER.debug("shiro redis cache size.{}", name);
        RedisConnection redisConnection = null;
        int length = 0;
        try {
            redisConnection = jedisConnectionFactory.getConnection();
            length = Math.toIntExact(redisConnection.lLen(serializer.serialize(keyListKey)));
        } catch (Exception e) {
            LOGGER.error("shiro redis cache size exception.", e);
        } finally {
            if (null != redisConnection) {
                redisConnection.close();
            }
        }
        return length;
    }

    @Override
    public Set keys() {
        LOGGER.debug("shiro redis cache keys.{}", name);
        RedisConnection redisConnection = null;
        Set resultSet = null;
        try {
            redisConnection = jedisConnectionFactory.getConnection();

            Long length = redisConnection.lLen(serializer.serialize(keyListKey));
            if (0 == length) {
                return resultSet;
            }

            List<byte[]> keyList = redisConnection.lRange(serializer.serialize(keyListKey), 0, length - 1);
            resultSet = keyList.stream().map(bytes -> serializer.deserialize(bytes)).collect(Collectors.toSet());
        } catch (Exception e) {
            LOGGER.error("shiro redis cache keys exception.", e);
        } finally {
            if (null != redisConnection) {
                redisConnection.close();
            }
        }
        return resultSet;
    }

    @Override
    public Collection values() {
        return null;
    }

    /**
     * 重组key
     * 区别其他使用环境的key
     *
     * @param key
     * @return
     */
    private String generateKey(K key) {
        return REDIS_SHIRO_CACHE_KEY_PREFIX + name + "_" + key;
    }
}
