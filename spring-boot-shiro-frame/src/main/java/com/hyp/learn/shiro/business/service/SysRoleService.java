package com.hyp.learn.shiro.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyp.learn.shiro.business.entity.Role;
import com.hyp.learn.shiro.business.vo.RoleVO;
import com.hyp.learn.shiro.framework.object.AbstractService;
import com.hyp.learn.shiro.persistence.beans.SysRole;

import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.business.service
 * hyp create at 20-3-29
 **/
public interface SysRoleService extends AbstractService<SysRole, Long> {
    /**
     * 获取ztree使用的角色列表
     *
     * @param uid
     * @return
     */
    List<Map<String, Object>> queryRoleListWithSelected(Integer uid);

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    Page<Role> findPageBreakByCondition(RoleVO vo);

    /**
     * 获取用户的角色
     *
     * @param userId
     * @return
     */
    List<Role> listRolesByUserId(Long userId);
}
