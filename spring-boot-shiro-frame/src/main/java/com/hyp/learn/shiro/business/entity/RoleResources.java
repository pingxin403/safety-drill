package com.hyp.learn.shiro.business.entity;

import com.hyp.learn.shiro.persistence.beans.SysRoleResources;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.business.entity
 * hyp create at 20-3-29
 **/
public class RoleResources {
    private SysRoleResources sysRoleResources;

    public RoleResources() {
        this.sysRoleResources = new SysRoleResources();
    }

    public RoleResources(SysRoleResources sysRoleResources) {
        this.sysRoleResources = sysRoleResources;
    }

    public SysRoleResources getSysRoleResources() {
        return this.sysRoleResources;
    }

    public Long getRoleId() {
        return this.sysRoleResources.getRoleId();
    }

    public void setRoleId(Long roleId) {
        this.sysRoleResources.setRoleId(roleId);
    }

    public Long getResourcesId() {
        return this.sysRoleResources.getResourcesId();
    }

    public void setResourcesId(Long resourcesId) {
        this.sysRoleResources.setResourcesId(resourcesId);
    }

    public LocalDateTime getCreateTime() {
        return this.sysRoleResources.getCreateTime();
    }

    public void setCreateTime(LocalDateTime regTime) {
        this.sysRoleResources.setCreateTime(regTime);
    }

    public LocalDateTime getUpdateTime() {
        return this.sysRoleResources.getUpdateTime();
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.sysRoleResources.setUpdateTime(updateTime);
    }
}
