package com.hyp.learn.shiro.business.service;

import com.hyp.learn.shiro.business.entity.User;

import java.util.Map;

/**
 * Shiro-权限相关的业务处理
 *
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.business.service
 * hyp create at 20-3-29
 **/
public interface ShiroService {
    /**
     * 初始化权限
     */
    Map<String, String> loadFilterChainDefinitions();

    /**
     * 重新加载权限
     */
    void updatePermission();

    /**
     * 重新加载用户权限
     *
     * @param user
     */
    void reloadAuthorizingByUserId(User user);

    /**
     * 重新加载所有拥有roleId角色的用户的权限
     *
     * @param roleId
     */
    void reloadAuthorizingByRoleId(Long roleId);


}
