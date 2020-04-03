package com.hyp.learn.cf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyp.learn.cf.commons.exception.BusinessException;
import com.hyp.learn.cf.commons.exception.code.BaseResponseCode;
import com.hyp.learn.cf.entity.SysUserRole;
import com.hyp.learn.cf.mapper.SysUserRoleMapper;
import com.hyp.learn.cf.service.ISysUserRoleService;
import com.hyp.learn.cf.vo.req.UserRoleOperationReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public int removeByRoleId(String roleId) {
        return sysUserRoleMapper.removeByRoleId(roleId);
    }

    @Override
    public List<String> getRoleIdsByUserId(String userId) {
        return sysUserRoleMapper.getRoleIdsByUserId(userId);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addUserRoleInfo(UserRoleOperationReqVO vo) {
        if (vo.getRoleIds() == null || vo.getRoleIds().isEmpty()) {
            return;
        }
        List<SysUserRole> list = new ArrayList<>();
        for (String roleId : vo.getRoleIds()) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setId(UUID.randomUUID().toString());
            sysUserRole.setCreateTime(LocalDateTime.now());
            sysUserRole.setUserId(vo.getUserId());
            sysUserRole.setRoleId(roleId);
            list.add(sysUserRole);
        }
        sysUserRoleMapper.removeByUserId(vo.getUserId());
        int count = sysUserRoleMapper.batchUserRole(list);
        if (count == 0) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }
    }

    @Override
    public int removeByUserId(String userId) {

        return sysUserRoleMapper.removeByUserId(userId);
    }

    @Override
    public List<String> getUserIdsByRoleIds(List<String> roleIds) {

        return sysUserRoleMapper.getUserIdsByRoleIds(roleIds);
    }
}
