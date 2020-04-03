package com.hyp.learn.shiro.persistence.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyp.learn.shiro.business.vo.ResourceVO;
import com.hyp.learn.shiro.persistence.beans.SysResources;
import com.hyp.learn.shiro.plugin.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.persistence.mapper
 * hyp create at 20-3-30
 **/
@Repository
public interface SysResourceMapper extends BaseMapper<SysResources> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    IPage<SysResources> findPageBreakByCondition(Page<?> page, @Param("u") ResourceVO vo);

    List<SysResources> listUserResources(Map<String, Object> map);

    /**
     * @param rid
     * @return
     */
    List<SysResources> queryResourcesListWithSelected(Long rid);

    List<SysResources> listUrlAndPermission();

    List<SysResources> listAllAvailableMenu();

    List<SysResources> listMenuResourceByPid(Long pid);

    List<SysResources> listByUserId(Long userId);
}
