package com.hyp.learn.cf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hyp.learn.cf.entity.SysRole;
import com.hyp.learn.cf.vo.req.RoleAddReqVO;
import com.hyp.learn.cf.vo.req.RolePageReqVO;
import com.hyp.learn.cf.vo.req.RoleUpdateReqVO;
import com.hyp.learn.cf.commons.object.PageVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author pingxin
 * @since 2020-03-03
 */
public interface ISysRoleService extends IService<SysRole> {

    SysRole addRole(RoleAddReqVO vo);

    void updateRole(RoleUpdateReqVO vo, String accessToken);

    SysRole detailInfo(String id);

    void deletedRole(String id);

    PageVO<SysRole> pageInfo(RolePageReqVO vo);

    List<SysRole> getRoleInfoByUserId(String userId);

    List<String> getRoleNames(String userId);

    List<SysRole> selectAllRoles();
}
