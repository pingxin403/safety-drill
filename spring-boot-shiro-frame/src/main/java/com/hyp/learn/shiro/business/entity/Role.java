package com.hyp.learn.shiro.business.entity;

import com.hyp.learn.shiro.persistence.beans.SysRole;

import java.time.LocalDateTime;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.business.entity
 * hyp create at 20-3-29
 **/
public class Role {
    private SysRole sysRole;

    public Role() {
        this.sysRole = new SysRole();
    }

    public Role(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    public SysRole getSysRole() {
        return this.sysRole;
    }

    public Long getId() {
        return this.sysRole.getId();
    }

    public void setId(Long id) {
        this.sysRole.setId(id);
    }

    public String getName() {
        return this.sysRole.getName();
    }

    public void setName(String name) {
        this.sysRole.setName(name);
    }


    public String getDescription() {
        return this.sysRole.getDescription();
    }

    public void setDescription(String description) {
        this.sysRole.setDescription(description);
    }

    public boolean isAvailable() {
        Boolean value = this.sysRole.getStatus();
        return value != null ? value : false;
    }

    public void setAvailable(boolean available) {
        this.sysRole.setStatus(available);
    }

    public LocalDateTime getCreateTime() {
        return this.sysRole.getCreateTime();
    }

    public void setCreateTime(LocalDateTime regTime) {
        this.sysRole.setCreateTime(regTime);
    }

    public LocalDateTime getUpdateTime() {
        return this.sysRole.getUpdateTime();
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.sysRole.setUpdateTime(updateTime);
    }
}
