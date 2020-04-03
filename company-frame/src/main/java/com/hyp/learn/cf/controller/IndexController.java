package com.hyp.learn.cf.controller;


import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * @ClassName: 返回菜单对应的视图html   有少部分需要返回页面生成的数据在这里
 */
@Api(tags = "视图", description = "负责返回视图")
@Controller
@RequestMapping("/index")
public class IndexController {

    @GetMapping("/login")
    public String logout() {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model, HttpServletRequest request) {
        return "home";
    }

    @GetMapping("/users/password")
    public String updatePassword() {
        return "users/update_password";
    }

    @GetMapping("/users/info")
    public String userDetail(Model model) {
        model.addAttribute("flagType", "edit");
        return "users/user_edit";
    }

    @GetMapping("/menus")
    public String menusList() {

        return "menus/menu_list";
    }

    @GetMapping("/roles")
    public String roleList() {
        return "roles/role_list";
    }

    @GetMapping("/users")
    public String userList() {
        return "users/user_list";
    }

    @GetMapping("/logs")
    public String logList() {
        return "logs/log_list";
    }

    @GetMapping("/depts")
    public String deptList() {
        return "depts/dept_list";
    }

    @GetMapping("/403")
    public String error403() {
        return "error/403";
    }

    @GetMapping("/404")
    public String error404() {
        return "error/404";
    }

    @GetMapping("/500")
    public String error405() {
        return "error/500";
    }

    @GetMapping("/main")
    public String indexHome() {
        return "main";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
