package com.hyp.learn.cf.mapper;

import com.hyp.learn.cf.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pingxin
 * @since 2020-03-03
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser getUserInfoByName(String username);

    List<SysUser> selectUserInfoByDeptIds (@Param("deptIds") List<String> deptIds);

    List<SysUser> getUserListByDeptId(String deptId);

    int deletedUsers(@Param("sysUser") SysUser sysUser, @Param("list") List<String> list);


}
