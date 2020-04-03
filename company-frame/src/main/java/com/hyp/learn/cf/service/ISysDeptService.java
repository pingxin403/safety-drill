package com.hyp.learn.cf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hyp.learn.cf.entity.SysDept;
import com.hyp.learn.cf.entity.SysUser;
import com.hyp.learn.cf.vo.req.DeptAddReqVO;
import com.hyp.learn.cf.vo.req.DeptPageReqVO;
import com.hyp.learn.cf.vo.req.DeptUpdateReqVO;
import com.hyp.learn.cf.vo.req.UserPageUserByDeptReqVO;
import com.hyp.learn.cf.vo.resp.DeptRespNodeVO;
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
public interface ISysDeptService extends IService<SysDept> {
    SysDept addDept(DeptAddReqVO vo);

    void updateDept(DeptUpdateReqVO vo);

    SysDept detailInfo(String id);

    void deleted(String id);

    PageVO<SysDept> pageInfo(DeptPageReqVO vo);


    List<DeptRespNodeVO> deptTreeList(String deptId);

    List<SysUser> pageDeptUserInfo(UserPageUserByDeptReqVO vo);

    List<SysDept> selectAll();
}
