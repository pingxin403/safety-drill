package com.hyp.learn.shiro.persistence.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyp.learn.shiro.business.vo.RoleVO;
import com.hyp.learn.shiro.persistence.beans.SysRole;
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
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    IPage<SysRole> findPageBreakByCondition(Page<?> page,@Param("r") RoleVO vo);

    /**
     * 该节代码参考自http://blog.csdn.net/poorcoder_/article/details/71374002
     * 感谢网友
     *
     * @param userId
     * @return
     */
    List<SysRole> queryRoleListWithSelected(Integer userId);

    List<SysRole> listRolesByUserId(Long userId);
}
