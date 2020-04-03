package com.hyp.learn.shiro.framework.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.hyp.learn.shiro.business.consts.CacheConst;
import com.hyp.learn.shiro.business.service.ShiroService;
import com.hyp.learn.shiro.business.shiro.credentials.RetryLimitCredentialsMatcher;
import com.hyp.learn.shiro.business.shiro.dao.ShiroRedisSessionDAO;
import com.hyp.learn.shiro.business.shiro.filter.CaptchaValidateFilter;
import com.hyp.learn.shiro.business.shiro.filter.KickoutSessionControlFilter;
import com.hyp.learn.shiro.business.shiro.realm.ShiroRealm;
import com.hyp.learn.shiro.framework.property.RedisProperties;
import com.hyp.learn.shiro.framework.redis.ShiroRedisManager;
import com.hyp.learn.shiro.persistence.mapper.SysUserMapper;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Shiro 配置
 *
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.framework.config
 * hyp create at 20-3-29
 **/
@Configuration
public class ShiroConfig {

    /**
     * shiro操作
     */
    @Autowired
    private ShiroService shiroService;

    @Autowired
    private SysUserMapper userMapper;
    /**
     * redis 缓存
     */
    @Autowired
    private RedisProperties redisProperties;

    @Autowired
    private DefaultWebSessionManager sessionManager;

    /**
     * spring的MethodInvokingFactoryBean可以用来实现观察者模式
     *
     * @param securityManager
     * @return
     */
    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(SecurityManager securityManager) {
        MethodInvokingFactoryBean b = new MethodInvokingFactoryBean();
        b.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        b.setArguments(securityManager);
        return b;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * Filter Chain定义说明
     * 1、一个URL可以配置多个Filter，使用逗号分隔
     * 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        bean.setSecurityManager(manager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        bean.setLoginUrl("/passport/login/");
        // 登录成功后要跳转的链接
        bean.setSuccessUrl("/index");
        // 未授权界面;
        bean.setUnauthorizedUrl("/error/403");

        //自定义拦截器
        LinkedHashMap<String, Filter> filtersMap = new LinkedHashMap<>();
        //用来校验验证码
        filtersMap.put("captchaVaildate", new CaptchaValidateFilter());
        filtersMap.put("kickout", kickoutSessionControlFilter());
//        filtersMap.put("authc", new MyFormAuthenticationFilter());
        bean.setFilters(filtersMap);

        // 配置数据库中的resource
        Map<String, String> map = shiroService.loadFilterChainDefinitions();
        bean.setFilterChainDefinitionMap(map);


        return bean;
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean
    public SecurityManager securityManager(@Qualifier("shiroRealm") ShiroRealm realm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //设置realm
        manager.setRealm(realm);
        manager.setCacheManager(redisCacheManager());

//        自定义session管理 使用redis
        manager.setSessionManager(sessionManager);
        // 注入记住我管理器
        manager.setRememberMeManager(rememberMeManager());

        return manager;
    }

    @Bean(name = "shiroRealm")
    public ShiroRealm shiroRealm() {
        ShiroRealm realm = new ShiroRealm();
        realm.setCredentialsMatcher(credentialsMatcher());
        realm.setCachingEnabled(true);
        realm.setCacheManager(redisCacheManager());
        return realm;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     *
     * @return
     */
    @Bean(name = "credentialsMatcher")
    public RetryLimitCredentialsMatcher credentialsMatcher() {
        return new RetryLimitCredentialsMatcher();
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param manager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public ShiroRedisManager redisManager() {
        ShiroRedisManager manager = new ShiroRedisManager();
        manager.setHost(redisProperties.getHost());
        manager.setPort(redisProperties.getPort());
        manager.setTimeout(redisProperties.getTimeout());
        manager.setPassword(redisProperties.getPassword());
        manager.setDatabase(redisProperties.getDatabase());
        return manager;
    }


    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager manager = new RedisCacheManager();
        manager.setRedisManager(redisManager());
        manager.setKeyPrefix(CacheConst.SHIRO_REDIS_SESSION_KEY_PREFIX);
        return manager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public ShiroRedisSessionDAO redisSessionDAO(RedisTemplate redisTemplate) {
        ShiroRedisSessionDAO d = new ShiroRedisSessionDAO(redisTemplate);
        return d;
    }

    /**
     * shiro session的管理
     */
    @Bean
    public DefaultWebSessionManager sessionManager(ShiroRedisSessionDAO sessionDAO) {
        DefaultWebSessionManager manager = new DefaultWebSessionManager();
        //过期时间
        manager.setGlobalSessionTimeout(redisProperties.getExpire());

        // 删除过期的session
        manager.setDeleteInvalidSessions(true);


        //操作dao
        manager.setSessionDAO(sessionDAO);

        manager.setCacheManager(redisCacheManager());

        // 是否定时检查session
        manager.setSessionValidationSchedulerEnabled(true);
        manager.setSessionValidationScheduler(configSessionValidationScheduler());

        manager.setSessionIdCookieEnabled(true);
        //自定义Cookie
        // 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie cookie = new SimpleCookie(CacheConst.PROJECT);
        // 记住我cookie生效时间30天 ,单位秒。 注释掉，默认永久不过期
        cookie.setMaxAge(redisProperties.getExpire());
        cookie.setPath("/");

        manager.setSessionIdCookie(cookie);

        return manager;
    }

    /**
     * session会话验证调度器
     *
     * @return session会话验证调度器
     */
    @Bean
    public ExecutorServiceSessionValidationScheduler configSessionValidationScheduler() {
        ExecutorServiceSessionValidationScheduler sessionValidationScheduler = new ExecutorServiceSessionValidationScheduler();
        //设置session的失效扫描间隔，单位为毫秒
        sessionValidationScheduler.setInterval(300 * 1000);
        return sessionValidationScheduler;
    }

    /**
     * cookie对象;
     *
     * @return
     */
    public SimpleCookie rememberMeCookie() {
        // 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie rememberMe = new SimpleCookie("rememberMe");
        // 记住我cookie生效时间30天 ,单位秒。 注释掉，默认永久不过期
        rememberMe.setMaxAge(redisProperties.getExpire());

        //js不可修改
        rememberMe.setHttpOnly(true);

        return rememberMe;
    }

    /**
     * cookie管理对象;记住我功能
     *
     * @return
     */
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        manager.setCipherKey(Base64.decode("1QWLxg+NYmxraMoxAXu/Iw=="));

        return manager;
    }

    /**
     * setUnauthorizedUrl("/403")不起作用
     *
     * @return
     */
    @Bean
    public SimpleMappingExceptionResolver resolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        /*未授权处理页*/
        properties.setProperty("org.apache.shiro.authz.UnauthorizedException", "/error/403");
        resolver.setExceptionMappings(properties);
        return resolver;
    }


    /**
     * 限制同一账号登录同时登录人数控制
     *
     * @return 过滤器
     */
//    @Bean
    public KickoutSessionControlFilter kickoutSessionControlFilter() {
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        //使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
        //这里我们还是用之前shiro使用的redisManager()实现的cacheManager()缓存管理
        //也可以重新另写一个，重新配置缓存时间之类的自定义缓存属性
        kickoutSessionControlFilter.setCacheManager(redisCacheManager());
        //用于根据会话ID，获取会话进行踢出操作的；
        kickoutSessionControlFilter.setSessionManager(sessionManager);
        //是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；踢出顺序。
        kickoutSessionControlFilter.setKickoutAfter(false);
        //同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
        kickoutSessionControlFilter.setMaxSession(1);

        kickoutSessionControlFilter.setUserMapper(userMapper);

        //被踢出后重定向到的地址；
        kickoutSessionControlFilter.setKickoutUrl("/login");
        return kickoutSessionControlFilter;
    }


}
