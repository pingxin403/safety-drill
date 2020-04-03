package com.hyp.learn.shiro.business.service;

import com.hyp.learn.shiro.business.entity.RoleResources;
import com.hyp.learn.shiro.framework.object.AbstractService;
import com.hyp.learn.shiro.persistence.beans.SysRoleResources;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.business.service
 * hyp create at 20-3-29
 **/
public interface SysRoleResourcesService extends AbstractService<SysRoleResources, Long> {

    /**
     * 添加角色资源
     *
     * @param roleId
     * @param resourcesIds
     */
    void addRoleResources(Long roleId, String resourcesIds);

    /**
     * 通过角色id批量删除
     *
     * @param roleId
     */
    void removeByRoleId(Long roleId);
}
