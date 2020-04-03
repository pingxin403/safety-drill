package com.hyp.learn.shiro.business.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyp.learn.shiro.business.service.SysRoleResourcesService;
import com.hyp.learn.shiro.persistence.beans.SysRoleResources;
import com.hyp.learn.shiro.persistence.mapper.SysRoleResourcesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hyp
 * Project name is spring-boot-shiro-frame
 * Include in com.hyp.learn.shiro.business.service.impl
 * hyp create at 20-3-31
 **/
@Service
public class SysRoleResourcesServiceImpl extends ServiceImpl<SysRoleResourcesMapper, SysRoleResources> implements SysRoleResourcesService {

    @Autowired
    private SysRoleResourcesMapper mapper;

    /**
     * 添加角色资源
     *
     * @param roleId
     * @param resourcesIds
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public void addRoleResources(Long roleId, String resourcesIds) {
//删除
        removeByRoleId(roleId);
        //添加
        if (!StringUtils.isEmpty(resourcesIds)) {
            String[] resourcesArr = resourcesIds.split(",");
            if (resourcesArr.length == 0) {
                return;
            }
            SysRoleResources r = null;
            List<SysRoleResources> roleResources = new ArrayList<>();
            for (String ri : resourcesArr) {
                if (StringUtils.isEmpty(ri)) {
                    continue;
                }
                r = new SysRoleResources();
                r.setRoleId(roleId);
                r.setResourcesId(Long.parseLong(ri));
                r.setCreateTime(LocalDateTime.now());
                r.setUpdateTime(LocalDateTime.now());
                roleResources.add(r);

            }
            if (roleResources.size() == 0) {
                return;
            }
            this.saveBatch(roleResources);
        }
    }

    /**
     * 通过角色id批量删除
     *
     * @param roleId
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public void removeByRoleId(Long roleId) {
//删除
        SysRoleResources resources = new SysRoleResources();
        resources.setRoleId(roleId);
        mapper.delete(Wrappers.update(resources));
    }
}
