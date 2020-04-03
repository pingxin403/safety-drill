package com.hyp.learn.shiro.business.entity;

import com.hyp.learn.shiro.persistence.beans.SysUserRole;

import java.time.LocalDateTime;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.business.entity
 * hyp create at 20-3-29
 **/
public class UserRole {

    private SysUserRole sysUserRole;

    public UserRole() {
        this.sysUserRole = new SysUserRole();
    }

    public UserRole(SysUserRole sysUserRole) {
        this.sysUserRole = sysUserRole;
    }

    public SysUserRole getSysUserRole() {
        return this.sysUserRole;
    }

    public Long getUserId() {
        return this.sysUserRole.getUserId();
    }

    public void setUserId(Long userId) {
        this.sysUserRole.setUserId(userId);
    }

    public Long getRoleId() {
        return this.sysUserRole.getRoleId();
    }

    public void setRoleId(Long roleId) {
        this.sysUserRole.setRoleId(roleId);
    }

    public LocalDateTime getCreateTime() {
        return this.sysUserRole.getCreateTime();
    }

    public void setCreateTime(LocalDateTime regTime) {
        this.sysUserRole.setCreateTime(regTime);
    }

    public LocalDateTime getUpdateTime() {
        return this.sysUserRole.getUpdateTime();
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.sysUserRole.setUpdateTime(updateTime);
    }
}
