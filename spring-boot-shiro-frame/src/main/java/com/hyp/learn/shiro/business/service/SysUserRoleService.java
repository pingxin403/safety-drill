package com.hyp.learn.shiro.business.service;

import com.hyp.learn.shiro.business.entity.UserRole;
import com.hyp.learn.shiro.framework.object.AbstractService;
import com.hyp.learn.shiro.persistence.beans.SysUserRole;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.business.service
 * hyp create at 20-3-29
 **/
public interface SysUserRoleService extends AbstractService<SysUserRole, Long> {

    /**
     * 添加用户角色
     *
     * @param userId
     * @param roleIds
     */
    void addUserRole(Long userId, String roleIds);

    /**
     * 根据用户ID删除用户角色
     *
     * @param userId
     */
    void removeByUserId(Long userId);
}
