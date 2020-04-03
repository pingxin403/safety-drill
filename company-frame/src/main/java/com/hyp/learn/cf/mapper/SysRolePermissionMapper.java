package com.hyp.learn.cf.mapper;

import com.hyp.learn.cf.entity.SysRolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pingxin
 * @since 2020-03-03
 */
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {


    int removeByRoleId(String roleId);

    List<String> getPermissionIdsByRoles(List<String> roleIds);

    int batchRolePermission(List<SysRolePermission> list);

    int removeByPermissionId(String permissionId);

    List<String> getRoleIds(String permissionId);

    List<String> getPermissionIdsByRoleId(String roleId);
}
