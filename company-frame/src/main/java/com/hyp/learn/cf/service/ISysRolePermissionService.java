package com.hyp.learn.cf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hyp.learn.cf.entity.SysRolePermission;
import com.hyp.learn.cf.vo.req.RolePermissionOperationReqVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author pingxin
 * @since 2020-03-03
 */
public interface ISysRolePermissionService extends IService<SysRolePermission> {
    int removeByRoleId(String roleId);

    List<String> getPermissionIdsByRoles(List<String> roleIds);

    void addRolePermission(RolePermissionOperationReqVO vo);

    int removeByPermissionId(String permissionId);

    List<String> getRoleIds(String permissionId);

    List<String> getPermissionIdsByRoleId(String roleId);

}
