package com.hyp.learn.cf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hyp.learn.cf.entity.SysPermission;
import com.hyp.learn.cf.vo.req.PermissionAddReqVO;
import com.hyp.learn.cf.vo.req.PermissionPageReqVO;
import com.hyp.learn.cf.vo.req.PermissionUpdateReqVO;
import com.hyp.learn.cf.commons.object.PageVO;
import com.hyp.learn.cf.vo.resp.PermissionRespNode;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author pingxin
 * @since 2020-03-03
 */
public interface ISysPermissionService extends IService<SysPermission> {
    List<SysPermission> getPermission(String userId);

    SysPermission addPermission(PermissionAddReqVO vo);

    SysPermission detailInfo(String permissionId);

    void updatePermission(PermissionUpdateReqVO vo);

    void deleted(String permissionId);

    PageVO<SysPermission> pageInfo(PermissionPageReqVO vo);

    List<SysPermission> selectAll();

    Set<String> getPermissionsByUserId(String userId);

    List<PermissionRespNode> permissionTreeList(String userId);

    List<PermissionRespNode> selectAllByTree();

    List<PermissionRespNode> selectAllMenuByTree(String permissionId);
}
