package com.hyp.learn.shiro.framework.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyp.learn.shiro.framework.redis.ShiroRedisManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.framework.config
 * hyp create at 20-3-29
 **/
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * 缓存数据时Key的生成器，可以依据业务和技术场景自行定制
     *
     * @return
     */
    @Override
    @Bean
    public KeyGenerator keyGenerator() {

        return (target, method, parame) -> {
            StringBuilder sb = new StringBuilder();
            //类名+方法名+参数
            sb.append(target.getClass().getName())
                    .append(".")
                    .append(method.getName());
            for (Object p : parame) {
                sb.append(p);
            }
            return sb.toString();
        };
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {

        return RedisCacheManager.builder(
                RedisCacheWriter
                        .nonLockingRedisCacheWriter(factory)
        ).cacheDefaults(
                RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(
                                Duration.ofDays(30)
                        )
        )
                .build();
    }

    /**
     * 自定义templates
     * see: https://blog.csdn.net/weixin_43549578/article/details/84821986
     *
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        MyRedisSerializer myRedisSerializer = new MyRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
//        template.setValueSerializer(myRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
//        template.setHashValueSerializer(myRedisSerializer);

        template.afterPropertiesSet();
        return template;
    }
}
