package com.hyp.learn.shiro.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyp.learn.shiro.business.entity.Resources;
import com.hyp.learn.shiro.business.vo.ResourceVO;
import com.hyp.learn.shiro.framework.object.AbstractService;
import com.hyp.learn.shiro.persistence.beans.SysResources;

import java.util.List;
import java.util.Map;

/**
 * 系统资源
 *
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.business.service
 * hyp create at 20-3-29
 **/
public interface SysResourcesService extends AbstractService<SysResources, Long> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    Page<Resources> findPageBreakByCondition(ResourceVO vo);

    /**
     * 获取用户的资源列表
     *
     * @param map
     * @return
     */
    List<Resources> listUserResources(Map<String, Object> map);

    /**
     * 获取ztree使用的资源列表
     *
     * @param rid
     * @return
     */
    List<Map<String, Object>> queryResourcesListWithSelected(Long rid);

    /**
     * 获取资源的url和permission
     *
     * @return
     */
    List<Resources> listUrlAndPermission();

    /**
     * 获取所有可用的菜单资源
     *
     * @return
     */
    List<Resources> listAllAvailableMenu();

    /**
     * 获取父级资源下所有menu资源
     *
     * @return
     */
    List<Map<String, Object>> listChildMenuByPid(Long pid);

    /**
     * 获取用户关联的所有资源
     *
     * @param userId
     * @return
     */
    List<Resources> listByUserId(Long userId);
}
