package com.hyp.learn.shiro.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyp.learn.shiro.business.entity.User;
import com.hyp.learn.shiro.business.enums.BaseResponseCode;
import com.hyp.learn.shiro.business.service.SysUserRoleService;
import com.hyp.learn.shiro.business.service.SysUserService;
import com.hyp.learn.shiro.business.vo.UserVO;
import com.hyp.learn.shiro.framework.object.PageResult;
import com.hyp.learn.shiro.framework.object.ResponseVO;
import com.hyp.learn.shiro.util.PasswordUtil;
import com.hyp.learn.shiro.util.ResultUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理
 */
@RestController
@RequestMapping("/user")
public class RestUserController {
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysUserRoleService userRoleService;

    @RequiresPermissions("users")
    @PostMapping("/list")
    public PageResult list(UserVO vo) {
        Page<User> pageInfo = userService.findPageByCondition(vo);
        return ResultUtil.tablePage(pageInfo);
    }

    /**
     * 保存用户角色
     *
     * @param userId
     * @param roleIds 用户角色
     *                此处获取的参数的角色id是以 “,” 分隔的字符串
     * @return
     */
    @RequiresPermissions("user:allotRole")
    @PostMapping("/saveUserRoles")
    public ResponseVO saveUserRoles(Long userId, String roleIds) {
        if (StringUtils.isEmpty(userId)) {
            return ResultUtil.error("error");
        }
        userRoleService.addUserRole(userId, roleIds);
        return ResultUtil.success("成功");
    }

    @RequiresPermissions("user:add")
    @PostMapping(value = "/add")
    public ResponseVO add(User user) {
        User u = userService.getByUserName(user.getUsername());
        if (u != null) {
            return ResultUtil.error("该用户名[" + user.getUsername() + "]已存在！请更改用户名");
        }
        try {
            user.setPassword(PasswordUtil.encrypt(user.getPassword(), user.getUsername()));
            userService.save(user.getSysUser());
            return ResultUtil.success("成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("error");
        }
    }

    @RequiresPermissions(value = {"user:batchDelete", "user:delete"}, logical = Logical.OR)
    @PostMapping(value = "/remove")
    public ResponseVO remove(Long[] ids) {
        if (null == ids) {
            return ResultUtil.error(500, "请至少选择一条记录");
        }
        for (Long id : ids) {
            userService.removeById(id);
            userRoleService.removeByUserId(id);
        }
        return ResultUtil.success("成功删除 [" + ids.length + "] 个用户");
    }

    @RequiresPermissions("user:edit")
    @PostMapping("/get/{id}")
    public ResponseVO get(@PathVariable Long id) {
        return ResultUtil.success(null, this.userService.getById(id));
    }

    @RequiresPermissions("user:edit")
    @PostMapping("/edit")
    public ResponseVO edit(User user) {
        try {
            userService.updateById(user.getSysUser());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("用户修改失败！");
        }
        return ResultUtil.success(BaseResponseCode.SUCCESS);
    }

}
