package com.hyp.learn.cf.controller;


import com.hyp.learn.cf.commons.aop.annotation.LogAnnotation;
import com.hyp.learn.cf.entity.SysDept;
import com.hyp.learn.cf.entity.SysUser;
import com.hyp.learn.cf.service.impl.SysDeptServiceImpl;
import com.hyp.learn.cf.utils.DataResult;
import com.hyp.learn.cf.vo.req.DeptAddReqVO;
import com.hyp.learn.cf.vo.req.DeptPageReqVO;
import com.hyp.learn.cf.vo.req.DeptUpdateReqVO;
import com.hyp.learn.cf.vo.req.UserPageUserByDeptReqVO;
import com.hyp.learn.cf.vo.resp.DeptRespNodeVO;
import com.hyp.learn.cf.commons.object.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pingxin
 * @since 2020-03-03
 */
@RequestMapping("/sys")
@RestController
@Api(tags = "组织模块-机构管理")
public class SysDeptController {
    @Autowired
    private SysDeptServiceImpl deptService;

    @PostMapping("/dept")
    @ApiOperation(value = "新增组织接口")
    @LogAnnotation(title = "机构管理", action = "新增组织")
    @RequiresPermissions("sys:dept:add")
    public DataResult<SysDept> addDept(@RequestBody @Valid DeptAddReqVO vo) {
        DataResult<SysDept> result = DataResult.success();
        result.setData(deptService.addDept(vo));
        return result;
    }

    @DeleteMapping("/dept/{id}")
    @ApiOperation(value = "删除组织接口")
    @LogAnnotation(title = "机构管理", action = "删除组织")
    @RequiresPermissions("sys:dept:deleted")
    public DataResult deleted(@PathVariable("id") String id) {
        deptService.deleted(id);
        return DataResult.success();
    }

    @PutMapping("/dept")
    @ApiOperation(value = "更新组织信息接口")
    @LogAnnotation(title = "机构管理", action = "更新组织信息")
    @RequiresPermissions("sys:dept:update")
    public DataResult updateDept(@RequestBody @Valid DeptUpdateReqVO vo) {
        deptService.updateDept(vo);
        return DataResult.success();
    }

    @GetMapping("/dept/{id}")
    @ApiOperation(value = "查询组织详情接口")
    @LogAnnotation(title = "机构管理", action = "查询组织详情")
    @RequiresPermissions("sys:dept:detail")
    public DataResult<SysDept> detailInfo(@PathVariable("id") String id) {
        DataResult<SysDept> result = DataResult.success();
        result.setData(deptService.detailInfo(id));
        return result;
    }

    @PostMapping("/depts")
    @ApiOperation(value = "分页获取组织信息接口")
    @LogAnnotation(title = "机构管理", action = "分页获取组织信息")
    @RequiresPermissions("sys:dept:list")
    public DataResult<PageVO<SysDept>> pageInfo(@RequestBody DeptPageReqVO vo) {
        DataResult<PageVO<SysDept>> result = DataResult.success();
        result.setData(deptService.pageInfo(vo));
        return result;
    }

    @GetMapping("/dept/tree")
    @ApiOperation(value = "树型组织列表接口")
    @LogAnnotation(title = "机构管理", action = "树型组织列表")
    @RequiresPermissions(value = {"sys:user:update", "sys:user:add", "sys:dept:add", "sys:dept:update"}, logical = Logical.OR)
    public DataResult<List<DeptRespNodeVO>> getTree(@RequestParam(required = false) String deptId) {
        DataResult<List<DeptRespNodeVO>> result = DataResult.success();
        result.setData(deptService.deptTreeList(deptId));
        return result;
    }

    @PostMapping("/dept/users")
    @ApiOperation(value = "分页获取组织下所有用户接口")
    @LogAnnotation(title = "机构管理", action = "分页获取组织下所有用户")
    @RequiresPermissions("sys:dept:user:list")
    public DataResult<List<SysUser>> pageDeptUserInfos(@RequestBody @Valid UserPageUserByDeptReqVO vo) {
        DataResult<List<SysUser>> result = DataResult.success();
        result.setData(deptService.pageDeptUserInfo(vo));
        System.out.println("fsdfds");
        return result;
    }

    @GetMapping("/depts")
    @ApiOperation(value = "获取机构列表接口")
    @LogAnnotation(title = "机构管理", action = "获取所有组织机构")
    @RequiresPermissions("sys:dept:list")
    public DataResult<List<SysDept>> getDeptAll() {
        DataResult<List<SysDept>> result = DataResult.success();
        result.setData(deptService.selectAll());
        return result;
    }
}
