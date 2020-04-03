package com.hyp.learn.shiro.persistence.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyp.learn.shiro.business.vo.UserVO;
import com.hyp.learn.shiro.persistence.beans.SysUser;
import com.hyp.learn.shiro.plugin.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.persistence.mapper
 * hyp create at 20-3-30
 **/
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
    IPage<SysUser> findPageBreakByCondition(Page<?> page, @Param("u") UserVO vo);

    List<SysUser> listByRoleId(Long roleId);
}
