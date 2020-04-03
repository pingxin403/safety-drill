package com.hyp.learn.shiro.business.entity;

import com.hyp.learn.shiro.business.enums.ResourceTypeEnum;
import com.hyp.learn.shiro.persistence.beans.SysResources;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.business.entity
 * hyp create at 20-3-29
 **/
public class Resources {
    private SysResources sysResources;

    public Resources() {
        this.sysResources = new SysResources();
    }

    public Resources(SysResources sysResources) {
        this.sysResources = sysResources;
    }

    public SysResources getSysResources() {
        return this.sysResources;
    }

    public Long getId() {
        return this.sysResources.getId();
    }

    public void setId(Long id) {
        this.sysResources.setId(id);
    }

    public String getName() {
        return this.sysResources.getName();
    }

    public void setName(String name) {
        this.sysResources.setName(name);
    }

    public ResourceTypeEnum getType() {
        return this.sysResources.getType() != null ? ResourceTypeEnum.valueOf(this.sysResources.getType()) : null;
    }

    public void setType(ResourceTypeEnum type) {
        this.sysResources.setType(type.toString());
    }

    public String getUrl() {
        return this.sysResources.getUrl();
    }

    public void setUrl(String url) {
        this.sysResources.setUrl(url);
    }

    public String getPermission() {
        return this.sysResources.getPerms();
    }

    public void setPermission(String permission) {
        this.sysResources.setPerms(permission);
    }

    public Long getParentId() {
        return this.sysResources.getPId();
    }

    public void setParentId(Long parentId) {
        this.sysResources.setPId(parentId);
    }

    public Integer getSort() {
        return this.sysResources.getOrderNum();
    }

    public void setSort(Integer sort) {
        this.sysResources.setOrderNum(sort);
    }

    public boolean isAvailable() {
        Boolean value = this.sysResources.getStatus();
        return value != null ? value : false;
    }

    public void setAvailable(boolean available) {
        this.sysResources.setStatus(available);
    }

    public Boolean getExternal() {
        Boolean value = this.sysResources.getExternal();
        return null == value ? false : value;
    }

    public void setExternal(Boolean external) {
        this.sysResources.setExternal(external);
    }

    public String getIcon() {
        return this.sysResources.getIcon();
    }

    public void setIcon(String icon) {
        this.sysResources.setIcon(icon);
    }

    public LocalDateTime getCreateTime() {
        return this.sysResources.getCreateTime();
    }

    public void setCreateTime(LocalDateTime regTime) {
        this.sysResources.setCreateTime(regTime);
    }

    public LocalDateTime getUpdateTime() {
        return this.sysResources.getUpdateTime();
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.sysResources.setUpdateTime(updateTime);
    }

    public SysResources getParent() {
        return this.sysResources.getParent();
    }

    public void setParent(SysResources parent) {
        this.sysResources.setParent(parent);
    }

    public List<SysResources> getNodes() {
        return this.sysResources.getNodes();
    }

    public void setNodes(List<SysResources> nodes) {
        this.sysResources.setNodes(nodes);
    }
}
