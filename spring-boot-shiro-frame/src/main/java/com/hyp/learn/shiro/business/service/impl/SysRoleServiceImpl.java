package com.hyp.learn.shiro.business.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyp.learn.shiro.business.entity.Role;
import com.hyp.learn.shiro.business.service.SysRoleService;
import com.hyp.learn.shiro.business.vo.RoleVO;
import com.hyp.learn.shiro.persistence.beans.SysRole;
import com.hyp.learn.shiro.persistence.mapper.SysRoleMapper;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    /**
     * 获取ztree使用的角色列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<Map<String, Object>> queryRoleListWithSelected(Integer userId) {
        List<SysRole> sysRole = roleMapper.queryRoleListWithSelected(userId);
        if (CollectionUtils.isEmpty(sysRole)) {
            return null;
        }
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        for (SysRole role : sysRole) {
            map = new HashMap<String, Object>(3);
            map.put("id", role.getId());
            map.put("pId", 0);
            map.put("checked", role.getSelected() != null && role.getSelected() == 1);
            map.put("name", role.getDescription());
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public Page<Role> findPageBreakByCondition(RoleVO vo) {
        IPage<SysRole> sysRolesPage = roleMapper.findPageBreakByCondition(new Page<>(vo.getPageNumber(), vo.getPageSize()), vo);
        List<SysRole> sysRoles = sysRolesPage.getRecords();
        if (CollectionUtils.isEmpty(sysRoles)) {
            return null;
        }
        List<Role> roles = new ArrayList<>();
        for (SysRole r : sysRoles) {
            roles.add(new Role(r));
        }
        Page<Role> result = new Page<>();
        result.setRecords(roles);
        result.setPages(sysRolesPage.getPages());
        result.setTotal(sysRolesPage.getTotal());
        result.setSize(sysRolesPage.getSize());
        result.setCurrent(sysRolesPage.getCurrent());
        return result;
    }

    /**
     * 获取用户的角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<Role> listRolesByUserId(Long userId) {
        List<SysRole> sysRoles = roleMapper.listRolesByUserId(userId);
        if (CollectionUtils.isEmpty(sysRoles)) {
            return null;
        }
        List<Role> roles = new ArrayList<>();
        for (SysRole r : sysRoles) {
            roles.add(new Role(r));
        }
        return roles;
    }
}
