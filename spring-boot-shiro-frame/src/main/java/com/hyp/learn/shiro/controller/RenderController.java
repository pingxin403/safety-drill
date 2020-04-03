package com.hyp.learn.shiro.controller;

/**
 * 页面渲染相关 -- 页面跳转
 */

import com.hyp.learn.shiro.util.ResultUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RenderController {

    @RequiresAuthentication
    @GetMapping(value = {"", "/index"})
    public ModelAndView home() {
        return ResultUtil.view("index");
    }

    @RequiresPermissions("users")
    @GetMapping("/users")
    public ModelAndView user() {
        return ResultUtil.view("user/list");
    }

    @RequiresPermissions("resources")
    @GetMapping("/resources")
    public ModelAndView resources() {
        return ResultUtil.view("resources/list");
    }

    @RequiresPermissions("roles")
    @GetMapping("/roles")
    public ModelAndView roles() {
        return ResultUtil.view("role/list");
    }

}
