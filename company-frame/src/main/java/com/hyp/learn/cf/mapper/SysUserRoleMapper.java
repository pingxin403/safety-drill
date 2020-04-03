package com.hyp.learn.cf.mapper;

import com.hyp.learn.cf.entity.SysUserRole;
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
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    int removeByRoleId(String roleId);

    List<String> getRoleIdsByUserId(String userId);

    int removeByUserId(String userId);

    List<String> getInfoByUserIdByRoleId( String roleId);

    List<String> getUserIdsByRoleIds(List<String> roleIds);

    int batchUserRole(List<SysUserRole> list);

}
