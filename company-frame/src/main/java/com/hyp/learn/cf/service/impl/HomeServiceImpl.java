package com.hyp.learn.cf.service.impl;

import com.hyp.learn.cf.entity.SysDept;
import com.hyp.learn.cf.entity.SysUser;
import com.hyp.learn.cf.service.IHomeService;

import com.hyp.learn.cf.service.ISysDeptService;
import com.hyp.learn.cf.service.ISysPermissionService;
import com.hyp.learn.cf.service.ISysUserService;
import com.hyp.learn.cf.vo.resp.HomeRespVO;
import com.hyp.learn.cf.vo.resp.PermissionRespNode;
import com.hyp.learn.cf.vo.resp.UserInfoRespVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HomeServiceImpl implements IHomeService {
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private ISysPermissionService permissionService;

    @Override
    public HomeRespVO getHomeInfo(String userId) {


        SysUser sysUser=userService.detailInfo(userId);
        UserInfoRespVO vo=new UserInfoRespVO();

        if(sysUser!=null){
            BeanUtils.copyProperties(sysUser, vo);
            SysDept sysDept = deptService.detailInfo(sysUser.getDeptId());
            if(sysDept!=null){
                vo.setDeptId(sysDept.getId());
                vo.setDeptName(sysDept.getName());
            }

        }

        List<PermissionRespNode> menus = permissionService.permissionTreeList(userId);

        HomeRespVO respVO=new HomeRespVO();
        respVO.setMenus(menus);
        respVO.setUserInfo(vo);

        return respVO;
    }
}
