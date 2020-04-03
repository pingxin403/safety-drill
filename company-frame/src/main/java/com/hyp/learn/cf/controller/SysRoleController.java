package com.hyp.learn.cf.controller;


import com.hyp.learn.cf.commons.aop.annotation.LogAnnotation;
import com.hyp.learn.cf.commons.constants.Constant;
import com.hyp.learn.cf.entity.SysRole;
import com.hyp.learn.cf.service.ISysRoleService;
import com.hyp.learn.cf.utils.DataResult;
import com.hyp.learn.cf.vo.req.RoleAddReqVO;
import com.hyp.learn.cf.vo.req.RolePageReqVO;
import com.hyp.learn.cf.vo.req.RoleUpdateReqVO;
import com.hyp.learn.cf.commons.object.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@Api(tags = "组织模块-角色管理")
public class SysRoleController {
    @Autowired
    private ISysRoleService roleService;

    @PostMapping("/role")
    @ApiOperation(value = "新增角色接口")
    @LogAnnotation(title = "角色管理",action = "新增角色")
    @RequiresPermissions("sys:role:add")
    public DataResult<SysRole> addRole(@RequestBody @Valid RoleAddReqVO vo){
        DataResult<SysRole> result= DataResult.success();
        result.setData(roleService.addRole(vo));
        return result;
    }
    @DeleteMapping("/role/{id}")
    @ApiOperation(value = "删除角色接口")
    @LogAnnotation(title = "角色管理",action = "删除角色")
    @RequiresPermissions("sys:role:deleted")
    public DataResult deleted(@PathVariable("id") String id){
        roleService.deletedRole(id);
        return DataResult.success();
    }
    @PutMapping("/role")
    @ApiOperation(value = "更新角色信息接口")
    @LogAnnotation(title = "角色管理",action = "更新角色信息")
    @RequiresPermissions("sys:role:update")
    public DataResult updateDept(@RequestBody @Valid RoleUpdateReqVO vo, HttpServletRequest request){
        roleService.updateRole(vo,request.getHeader(Constant.ACCESS_TOKEN));
        return DataResult.success();
    }
    @GetMapping("/role/{id}")
    @ApiOperation(value = "查询角色详情接口")
    @LogAnnotation(title = "角色管理",action = "查询角色详情")
    @RequiresPermissions("sys:role:detail")
    public DataResult<SysRole> detailInfo(@PathVariable("id") String id){
        DataResult<SysRole> result=DataResult.success();
        result.setData(roleService.detailInfo(id));
        return result;
    }
    @PostMapping("/roles")
    @ApiOperation(value = "分页获取角色信息接口")
    @LogAnnotation(title = "角色管理",action = "分页获取角色信息")
    @RequiresPermissions("sys:role:list")
    public DataResult<PageVO<SysRole>> pageInfo(@RequestBody RolePageReqVO vo){
        DataResult<PageVO<SysRole>> result=DataResult.success();
        result.setData(roleService.pageInfo(vo));
        return result;
    }

}
