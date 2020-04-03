package com.hyp.learn.shiro.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyp.learn.shiro.business.entity.User;
import com.hyp.learn.shiro.business.vo.UserVO;
import com.hyp.learn.shiro.framework.object.AbstractService;
import com.hyp.learn.shiro.persistence.beans.SysUser;

import java.util.List;

/**
 * 用户
 *
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.business.service
 * hyp create at 20-3-29
 **/
public interface SysUserService extends AbstractService<SysUser, Long> {
    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    Page<User> findPageByCondition(UserVO vo);

    /**
     * 更新用户最后一次登录的状态信息
     *
     * @param user
     * @return
     */
    User updateUserLastLoginInfo(User user);

    /**
     * 根据用户名查找
     *
     * @param userName
     * @return
     */
    User getByUserName(String userName);

    /**
     * 通过角色Id获取用户列表
     *
     * @param roleId
     * @return
     */
    List<User> listByRoleId(Long roleId);

}
