
package com.hyp.learn.shiro.persistence.mapper;


import com.hyp.learn.shiro.persistence.beans.SysUserRole;
import com.hyp.learn.shiro.plugin.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    List<Integer> findUserIdByRoleId(Integer roleId);
}
