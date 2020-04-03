package com.hyp.learn.shiro.business.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyp.learn.shiro.business.entity.UserRole;
import com.hyp.learn.shiro.business.service.SysUserRoleService;
import com.hyp.learn.shiro.persistence.beans.SysUserRole;
import com.hyp.learn.shiro.persistence.mapper.SysUserRoleMapper;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper resourceMapper;


    private List<UserRole> getUserRole(List<SysUserRole> sysUserRole) {
        if (CollectionUtils.isEmpty(sysUserRole)) {
            return null;
        }
        List<UserRole> urList = new ArrayList<>();
        for (SysUserRole r : sysUserRole) {
            urList.add(new UserRole(r));
        }
        return urList;
    }

    /**
     * 添加用户角色
     *
     * @param userId
     * @param roleIds
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public void addUserRole(Long userId, String roleIds) {
        //删除
        removeByUserId(userId);
        //添加
        String[] roleIdArr = roleIds.split(",");
        if (roleIdArr.length == 0) {
            return;
        }
        SysUserRole u = null;
        List<SysUserRole> roles = new ArrayList<>();
        for (String roleId : roleIdArr) {
            if (StringUtils.isEmpty(roleId)) {
                continue;
            }
            u = new SysUserRole();
            u.setUserId(userId);
            u.setRoleId(Long.parseLong(roleId));
            roles.add(u);
        }
        this.saveBatch(roles);
    }

    /**
     * 根据用户ID删除用户角色
     *
     * @param userId
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public void removeByUserId(Long userId) {
        SysUserRole role = new SysUserRole();
        role.setUserId(userId);
        resourceMapper.delete(Wrappers.update(role));
    }
}
