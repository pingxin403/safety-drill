package com.hyp.learn.cf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyp.learn.cf.commons.exception.BusinessException;
import com.hyp.learn.cf.commons.exception.code.BaseResponseCode;
import com.hyp.learn.cf.entity.SysRolePermission;
import com.hyp.learn.cf.mapper.SysRolePermissionMapper;
import com.hyp.learn.cf.service.ISysRolePermissionService;
import com.hyp.learn.cf.vo.req.RolePermissionOperationReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author pingxin
 * @since 2020-03-03
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements ISysRolePermissionService {
    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public int removeByRoleId(String roleId) {
        return sysRolePermissionMapper.removeByRoleId(roleId);
    }

    @Override
    public List<String> getPermissionIdsByRoles(List<String> roleIds) {
        return sysRolePermissionMapper.getPermissionIdsByRoles(roleIds);
    }

    @Override
    public void addRolePermission(RolePermissionOperationReqVO vo) {

        List<SysRolePermission> list = new ArrayList<>();
        for (String permissionId : vo.getPermissionIds()) {
            SysRolePermission sysRolePermission = new SysRolePermission();
            sysRolePermission.setId(UUID.randomUUID().toString());
            sysRolePermission.setCreateTime(LocalDateTime.now());
            sysRolePermission.setPermissionId(permissionId);
            sysRolePermission.setRoleId(vo.getRoleId());
            list.add(sysRolePermission);
        }
        sysRolePermissionMapper.removeByRoleId(vo.getRoleId());
        int count = sysRolePermissionMapper.batchRolePermission(list);
        if (count == 0) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }
    }

    @Override
    public int removeByPermissionId(String permissionId) {
        return sysRolePermissionMapper.removeByPermissionId(permissionId);
    }

    @Override
    public List<String> getRoleIds(String permissionId) {

        return sysRolePermissionMapper.getRoleIds(permissionId);
    }

    @Override
    public List<String> getPermissionIdsByRoleId(String roleId) {

        return sysRolePermissionMapper.getPermissionIdsByRoleId(roleId);
    }
}
