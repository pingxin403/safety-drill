package com.hyp.learn.shiro.business.shiro.realm;

import com.hyp.learn.shiro.business.entity.Resources;
import com.hyp.learn.shiro.business.entity.Role;
import com.hyp.learn.shiro.business.entity.User;
import com.hyp.learn.shiro.business.enums.StatusEnum;
import com.hyp.learn.shiro.business.enums.UserTypeEnum;
import com.hyp.learn.shiro.business.service.SysResourcesService;
import com.hyp.learn.shiro.business.service.SysRoleService;
import com.hyp.learn.shiro.business.service.SysUserService;
import com.hyp.learn.shiro.persistence.beans.SysResources;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Shiro-密码输入错误的状态下重试次数的匹配管理
 *
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.business.shiro.realm
 * hyp create at 20-3-29
 **/
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private SysUserService userService;
    @Autowired
    @Lazy
    private SysResourcesService resourcesService;
    @Autowired
    @Lazy
    private SysRoleService roleService;


    /**
     * 权限认证，为当前登录的Subject授予角色和权限（角色的权限信息集合）
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.warn("调用ShiroRealm.doGetAuthorizationInfo");
// 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Long userId = (Long) SecurityUtils.getSubject().getPrincipal();

        // 赋予角色
        List<Role> roles = roleService.listRolesByUserId(userId);
        for (Role r : roles) {
            info.addRole(r.getName());
        }

        // 赋予权限
        final List<Resources> resourcesList = new ArrayList<>();
        User user = new User(userService.getById(userId));
        if (null == user) {
            return info;
        }
        // ROOT用户默认拥有所有权限
        if (UserTypeEnum.ROOT.toString().equalsIgnoreCase(user.getUserType())) {
            List<SysResources> list =
                    resourcesService.list();
            list.forEach((it) -> {
                resourcesList.add(new Resources(it));
            });
        } else {
            resourcesList.addAll(resourcesService.listByUserId(userId));
        }

        if (!CollectionUtils.isEmpty(resourcesList)) {
            HashSet<String> permissionSet = new HashSet<>();
            for (Resources r : resourcesList) {
                String p = null;
                if (!StringUtils.isEmpty(p = r.getPermission())) {
                    permissionSet.addAll(Arrays.asList(p.split(",")));
                }
            }
            info.setStringPermissions(permissionSet);
        }
        return info;
    }

    /**
     * 提供账户信息返回认证信息（用户的角色信息集合）
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.warn("调用ShiroRealm.doGetAuthenticationInfo");
        String username = (String) token.getPrincipal();
        User user = userService.getByUserName(username);
        if (user == null) {
            throw new UnknownAccountException("账号不存在！");
        }
        if (user.getStatus() != null && StatusEnum.DISABLE.getCode().equals(user.getStatus())) {
            throw new LockedAccountException("账号已被锁定，禁止登陆!");
        }
        return new SimpleAuthenticationInfo(
                user.getId(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getSysUser().getSalt()),
                getName()
        );
    }
}
