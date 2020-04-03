package com.hyp.learn.shiro.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyp.learn.shiro.business.entity.Resources;
import com.hyp.learn.shiro.business.service.SysResourcesService;
import com.hyp.learn.shiro.business.vo.ResourceVO;
import com.hyp.learn.shiro.persistence.beans.SysResources;
import com.hyp.learn.shiro.persistence.mapper.SysResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author hyp
 * Project name is spring-boot-shiro-frame
 * Include in com.hyp.learn.shiro.business.service.impl
 * hyp create at 20-3-31
 **/
@Service
public class SysResourcesServiceImpl extends ServiceImpl<SysResourceMapper, SysResources> implements SysResourcesService {

    @Autowired
    private SysResourceMapper resourceMapper;

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public Page<Resources> findPageBreakByCondition(ResourceVO vo) {
        IPage<SysResources> sysResourcesPage = resourceMapper.findPageBreakByCondition(new Page<>(vo.getPageNumber(), vo.getPageSize()), vo);
        List<SysResources> sysResources = sysResourcesPage.getRecords();
        if (CollectionUtils.isEmpty(sysResources)) {
            return null;
        }
        List<Resources> resources = new ArrayList<>();
        for (SysResources r : sysResources) {
            resources.add(new Resources(r));
        }
        Page<Resources> result = new Page<>();
        result.setRecords(resources);
        result.setPages(sysResourcesPage.getPages());
        result.setTotal(sysResourcesPage.getTotal());
        result.setSize(sysResourcesPage.getSize());
        result.setCurrent(sysResourcesPage.getCurrent());

        return result;
    }

    /**
     * 获取用户的资源列表
     *
     * @param map
     * @return
     */
    @Override
    public List<Resources> listUserResources(Map<String, Object> map) {
        List<SysResources> sysResources = resourceMapper.listUserResources(map);
        if (CollectionUtils.isEmpty(sysResources)) {
            return null;
        }
        List<Resources> resources = new ArrayList<>();
        for (SysResources r : sysResources) {
            resources.add(new Resources(r));
        }
        return resources;
    }

    /**
     * 获取ztree使用的资源列表
     *
     * @param rid
     * @return
     */
    @Override
    public List<Map<String, Object>> queryResourcesListWithSelected(Long rid) {
        List<SysResources> sysResources = resourceMapper.queryResourcesListWithSelected(rid);
        if (CollectionUtils.isEmpty(sysResources)) {
            return null;
        }
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        for (SysResources resources : sysResources) {
            map = new HashMap<String, Object>(3);
            map.put("id", resources.getId());
            map.put("pId", resources.getPId());
            map.put("checked", resources.getChecked());
            map.put("name", resources.getName());
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 获取资源的url和permission
     *
     * @return
     */
    @Override
    public List<Resources> listUrlAndPermission() {
        List<SysResources> sysResources = resourceMapper.listUrlAndPermission();
        return getResources(sysResources);
    }

    /**
     * 获取所有可用的菜单资源
     *
     * @return
     */
    @Override
    public List<Resources> listAllAvailableMenu() {
        List<SysResources> sysResources = resourceMapper.listAllAvailableMenu();
        return getResources(sysResources);
    }

    /**
     * 获取父级资源下所有menu资源
     *
     * @param pid
     * @return
     */
    @Override
    public List<Map<String, Object>> listChildMenuByPid(Long pid) {
        List<SysResources> sysResources = resourceMapper.listMenuResourceByPid(pid);
        if (CollectionUtils.isEmpty(sysResources)) {
            return null;
        }
        List<Map<String, Object>> result = new LinkedList<>();
        Map<String, Object> item = null;
        for (SysResources sysResource : sysResources) {
            item = new HashMap<>(2);
            item.put("value", sysResource.getId());
            item.put("text", sysResource.getName());
            result.add(item);
        }
        return result;
    }

    /**
     * 获取用户关联的所有资源
     *
     * @param userId
     * @return
     */
    @Override
    public List<Resources> listByUserId(Long userId) {
        List<SysResources> sysResources = resourceMapper.listByUserId(userId);
        return getResources(sysResources);
    }

    private List<Resources> getResources(List<SysResources> sysResources) {
        if (CollectionUtils.isEmpty(sysResources)) {
            return null;
        }
        List<Resources> resources = new ArrayList<>();
        for (SysResources r : sysResources) {
            resources.add(new Resources(r));
        }
        return resources;
    }
}
