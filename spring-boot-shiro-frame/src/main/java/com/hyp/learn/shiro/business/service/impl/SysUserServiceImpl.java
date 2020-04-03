package com.hyp.learn.shiro.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyp.learn.shiro.business.entity.User;
import com.hyp.learn.shiro.business.service.SysRoleService;
import com.hyp.learn.shiro.business.service.SysUserService;
import com.hyp.learn.shiro.business.vo.UserVO;
import com.hyp.learn.shiro.framework.handler.RequestHolder;
import com.hyp.learn.shiro.persistence.beans.SysUser;
import com.hyp.learn.shiro.persistence.mapper.SysUserMapper;
import com.hyp.learn.shiro.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleService roleService;


    @Override
    public Page<User> findPageByCondition(UserVO vo) {

        IPage<SysUser> sysUsers = sysUserMapper.findPageBreakByCondition(new Page<User>(vo.getPageNumber(), vo.getPageSize()), vo);

        List<SysUser> records = sysUsers.getRecords();
        if (org.springframework.util.CollectionUtils.isEmpty(records)) {
            return null;
        }
        List<User> users = new ArrayList<>();
        for (SysUser su : records) {
            users.add(new User(su));
        }

        Page<User> usersPage = new Page<>();
        usersPage.setRecords(users);
        usersPage.setCurrent(sysUsers.getCurrent());
        usersPage.setSize(sysUsers.getSize());
        usersPage.setTotal(sysUsers.getTotal());
        usersPage.setPages(sysUsers.getPages());

        return usersPage;
    }

    /**
     * 更新用户最后一次登录的状态信息
     *
     * @param user
     * @return
     */
    @Override
    public User updateUserLastLoginInfo(User user) {
        if (user != null) {
            user.setLoginCount(user.getLoginCount() + 1);
            user.setLastLoginTime(new Date());
            user.setLastLoginIp(IpUtil.getRealIp(RequestHolder.getRequest()));
            user.setPassword(null);
            this.updateById(user.getSysUser());
        }
        return user;
    }

    /**
     * 根据用户名查找
     *
     * @param userName
     * @return
     */
    @Override
    public User getByUserName(String userName) {
        QueryWrapper<SysUser> wrapper = Wrappers.query();
        wrapper.eq("username", userName);
        SysUser sysUser = sysUserMapper.selectOne(wrapper);
        return new User(sysUser);
    }

    /**
     * 通过角色Id获取用户列表
     *
     * @param roleId
     * @return
     */
    @Override
    public List<User> listByRoleId(Long roleId) {
        List<SysUser> sysUsers = sysUserMapper.listByRoleId(roleId);
        if (CollectionUtils.isEmpty(sysUsers)) {
            return null;
        }
        List<User> users = new ArrayList<>();
        for (SysUser su : sysUsers) {
            users.add(new User(su));
        }
        return users;
    }

}
