package com.hyp.learn.cf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hyp.learn.cf.entity.SysUserRole;
import com.hyp.learn.cf.vo.req.UserRoleOperationReqVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author pingxin
 * @since 2020-03-03
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    int removeByRoleId(String roleId);

    List<String> getRoleIdsByUserId(String userId);


    void addUserRoleInfo(UserRoleOperationReqVO vo);

    int removeByUserId(String userId);


    List<String> getUserIdsByRoleIds(List<String> roleIds);

}
