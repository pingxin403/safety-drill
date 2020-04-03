package com.hyp.learn.shiro.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyp.learn.shiro.business.entity.Role;
import com.hyp.learn.shiro.business.enums.BaseResponseCode;
import com.hyp.learn.shiro.business.service.ShiroService;
import com.hyp.learn.shiro.business.service.SysRoleResourcesService;
import com.hyp.learn.shiro.business.service.SysRoleService;
import com.hyp.learn.shiro.business.vo.RoleVO;
import com.hyp.learn.shiro.framework.object.PageResult;
import com.hyp.learn.shiro.framework.object.ResponseVO;
import com.hyp.learn.shiro.util.ResultUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统角色管理
 */
@RestController
@RequestMapping("/roles")
public class RestRoleController {
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysRoleResourcesService roleResourcesService;
    @Autowired
    private ShiroService shiroService;

    @RequiresPermissions("roles")
    @PostMapping("/list")
    public PageResult getAll(RoleVO vo) {
        Page<Role> pageInfo = roleService.findPageBreakByCondition(vo);
        return ResultUtil.tablePage(pageInfo);
    }

    @RequiresPermissions("user:allotRole")
    @PostMapping("/rolesWithSelected")
    public ResponseVO<List<Role>> rolesWithSelected(Integer uid) {
        return ResultUtil.success(null, roleService.queryRoleListWithSelected(uid));
    }

    @RequiresPermissions("role:allotResource")
    @PostMapping("/saveRoleResources")
    public ResponseVO saveRoleResources(Long roleId, String resourcesId) {
        if (StringUtils.isEmpty(roleId)) {
            return ResultUtil.error("error");
        }
        roleResourcesService.addRoleResources(roleId, resourcesId);
        // 重新加载所有拥有roleId的用户的权限信息
        shiroService.reloadAuthorizingByRoleId(roleId);
        return ResultUtil.success("成功");
    }

    @RequiresPermissions("role:add")
    @PostMapping(value = "/add")
    public ResponseVO add(Role role) {
        roleService.save(role.getSysRole());
        return ResultUtil.success("成功");
    }

    @RequiresPermissions(value = {"role:batchDelete", "role:delete"}, logical = Logical.OR)
    @PostMapping(value = "/remove")
    public ResponseVO remove(Long[] ids) {
        if (null == ids) {
            return ResultUtil.error(500, "请至少选择一条记录");
        }
        for (Long id : ids) {
            roleService.removeById(id);
            roleResourcesService.removeByRoleId(id);
        }
        return ResultUtil.success("成功删除 [" + ids.length + "] 个角色");
    }

    @RequiresPermissions("role:edit")
    @PostMapping("/get/{id}")
    public ResponseVO get(@PathVariable Long id) {
        return ResultUtil.success(null, this.roleService.getById(id));
    }

    @RequiresPermissions("role:edit")
    @PostMapping("/edit")
    public ResponseVO edit(Role role) {
        try {
            roleService.updateById(role.getSysRole());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("角色修改失败！");
        }
        return ResultUtil.success(BaseResponseCode.SUCCESS);
    }

}
