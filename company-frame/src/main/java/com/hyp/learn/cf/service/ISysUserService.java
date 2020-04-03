package com.hyp.learn.cf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hyp.learn.cf.entity.SysUser;
import com.hyp.learn.cf.vo.req.*;
import com.hyp.learn.cf.vo.resp.LoginRespVO;
import com.hyp.learn.cf.commons.object.PageVO;
import com.hyp.learn.cf.vo.resp.UserOwnRoleRespVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author pingxin
 * @since 2020-03-03
 */
public interface ISysUserService extends IService<SysUser> {

    String register(RegisterReqVO vo);

    LoginRespVO login(LoginReqVO vo);


    String refreshToken(String refreshToken, String accessToken);

    void updateUserInfo(UserUpdateReqVO vo, String operationId);


    PageVO<SysUser> pageInfo(UserPageReqVO vo);

    SysUser detailInfo(String userId);

    List<SysUser> selectUserInfoByDeptIds(List<String> deptIds);

    void addUser(UserAddReqVO vo);

    void logout(String accessToken, String refreshToken);

    void updatePwd(UpdatePasswordReqVO vo, String userId, String accessToken, String refreshToken);

    List<SysUser> getUserListByDeptId(String deptId);

    List<SysUser> getUserListByDeptIds(List<String> deptIds);

    void deletedUsers(List<String> userIds, String operationId);

    UserOwnRoleRespVO getUserOwnRole(String userId);

    void setUserOwnRole(String userId, List<String> roleIds);
}
