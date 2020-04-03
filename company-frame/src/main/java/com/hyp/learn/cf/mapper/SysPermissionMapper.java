package com.hyp.learn.cf.mapper;

import com.hyp.learn.cf.entity.SysPermission;
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
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    List<SysPermission> selectChild(String pid);
}
