package com.hyp.learn.cf.controller;


import com.hyp.learn.cf.commons.aop.annotation.LogAnnotation;
import com.hyp.learn.cf.entity.SysLog;
import com.hyp.learn.cf.service.ISysLogService;
import com.hyp.learn.cf.utils.DataResult;
import com.hyp.learn.cf.vo.req.SysLogPageReqVO;
import com.hyp.learn.cf.commons.object.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "系统模块-系统操作日志管理")
@RestController
public class SysLogController {


    @Autowired
    private ISysLogService logService;

    @PostMapping("/logs")
    @ApiOperation(value = "分页查询系统操作日志接口")
    @LogAnnotation(title = "系统操作日志管理", action = "分页查询系统操作日志")
    @RequiresPermissions("sys:log:list")
    public DataResult<PageVO<SysLog>> pageInfo(@RequestBody SysLogPageReqVO vo) {
        PageVO<SysLog> sysLogPageVO = logService.pageInfo(vo);
        DataResult<PageVO<SysLog>> result = DataResult.success();
        result.setData(sysLogPageVO);
        return result;
    }

    @DeleteMapping("/logs")
    @ApiOperation(value = "删除日志接口")
    @LogAnnotation(title = "系统操作日志管理", action = "删除系统操作日志")
    @RequiresPermissions("sys:log:deleted")
    public DataResult deleted(@RequestBody List<String> logIds) {
        logService.deleted(logIds);
        return DataResult.success();
    }
}
