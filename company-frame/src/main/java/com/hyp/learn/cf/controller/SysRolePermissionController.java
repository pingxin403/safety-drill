package com.hyp.learn.cf.controller;


import com.hyp.learn.cf.commons.aop.annotation.LogAnnotation;
import com.hyp.learn.cf.service.ISysRolePermissionService;
import com.hyp.learn.cf.utils.DataResult;
import com.hyp.learn.cf.vo.req.RolePermissionOperationReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pingxin
 * @since 2020-03-03
 */
@RequestMapping("/sys")
@RestController
@Api(tags = "组织管理-角色和菜单关联接口")
public class SysRolePermissionController {
    @Autowired
    private ISysRolePermissionService rolePermissionService;

    @PostMapping("/role/permission")
    @ApiOperation(value = "修改或者新增角色菜单权限接口")
    @LogAnnotation(title = "角色和菜单关联接口",action = "修改或者新增角色菜单权限")
    @RequiresPermissions(value = {"sys:role:update","sys:role:add"},logical = Logical.OR)
    public DataResult operationRolePermission(@RequestBody @Valid RolePermissionOperationReqVO vo){
        rolePermissionService.addRolePermission(vo);
        return DataResult.success();
    }
}
