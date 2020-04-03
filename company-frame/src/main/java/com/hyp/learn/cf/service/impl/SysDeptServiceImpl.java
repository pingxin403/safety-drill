package com.hyp.learn.cf.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyp.learn.cf.commons.constants.Constant;
import com.hyp.learn.cf.commons.exception.BusinessException;
import com.hyp.learn.cf.commons.exception.code.BaseResponseCode;
import com.hyp.learn.cf.entity.SysDept;
import com.hyp.learn.cf.entity.SysUser;
import com.hyp.learn.cf.mapper.SysDeptMapper;
import com.hyp.learn.cf.service.ISysDeptService;
import com.hyp.learn.cf.service.ISysUserService;
import com.hyp.learn.cf.utils.CodeUtil;
import com.hyp.learn.cf.utils.PageUtils;
import com.hyp.learn.cf.vo.req.DeptAddReqVO;
import com.hyp.learn.cf.vo.req.DeptPageReqVO;
import com.hyp.learn.cf.vo.req.DeptUpdateReqVO;
import com.hyp.learn.cf.vo.req.UserPageUserByDeptReqVO;
import com.hyp.learn.cf.vo.resp.DeptRespNodeVO;
import com.hyp.learn.cf.commons.object.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
@Slf4j
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private ISysUserService userService;

    @Override
    public SysDept addDept(DeptAddReqVO vo) {
        System.out.println("fds");
        String relationCode;
        long result = redisService.incrby(Constant.DEPT_CODE_KEY, 1);
        String deptCode = CodeUtil.deptCode(String.valueOf(result), 6, "0");
        SysDept parent = sysDeptMapper.selectById(vo.getPid());
        if (vo.getPid().equals("0")) {
            relationCode = deptCode;
        } else if (null == parent) {
            log.error("传入的 pid:{}不合法", vo.getPid());
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        } else {
            relationCode = parent.getRelationCode() + deptCode;
        }
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(vo, sysDept);
        sysDept.setCreateTime(LocalDateTime.now());
        sysDept.setId(UUID.randomUUID().toString());
        sysDept.setDeptNo(deptCode);
        sysDept.setRelationCode(relationCode);
        int count = sysDeptMapper.insert(sysDept);
        if (count != 1) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }
        return sysDept;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDept(DeptUpdateReqVO vo) {

        SysDept sysDept = sysDeptMapper.selectById(vo.getId());
        if (null == sysDept) {
            log.error("传入 的 id:{}不合法", vo.getId());
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        SysDept update = new SysDept();
        BeanUtils.copyProperties(vo, update);
        update.setUpdateTime(LocalDateTime.now());
        int count = sysDeptMapper.updateById(update);
        if (count != 1) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }

        /**
         * 说明层级发生了变化
         */
        if (!StringUtils.isEmpty(vo.getPid()) && !vo.getPid().equals(sysDept.getPid())) {
            SysDept parent = sysDeptMapper.selectById(vo.getPid());
            if (!vo.getPid().equals("0") && null == parent) {
                log.error("传入 的 pid:{}不合法", vo.getId());
                throw new BusinessException(BaseResponseCode.DATA_ERROR);
            }
            SysDept oldParent = sysDeptMapper.selectById(sysDept.getPid());
            String oldRelationCode;
            String newRelationCode;
            /**
             * 根目录降到其他目录
             */
            if (sysDept.getPid().equals("0")) {
                oldRelationCode = sysDept.getDeptNo();
                newRelationCode = parent.getRelationCode() + sysDept.getDeptNo();
            } else if (vo.getPid().equals("0")) {//其他目录升级到跟目录
                oldRelationCode = sysDept.getRelationCode();
                newRelationCode = sysDept.getDeptNo();
            } else {
                oldRelationCode = oldParent.getRelationCode();
                newRelationCode = parent.getRelationCode();
            }
            sysDeptMapper.updateRelationCode(oldRelationCode, newRelationCode, sysDept.getRelationCode());
        }
    }

    @Override
    public SysDept detailInfo(String id) {
        return sysDeptMapper.selectById(id);
    }

    @Override
    public void deleted(String id) {
        SysDept sysDept = sysDeptMapper.selectById(id);
        if (null == sysDept) {
            log.error("传入 的 id:{}不合法", id);
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        List<String> deptIds = sysDeptMapper.selectChildIds(sysDept.getRelationCode());
        List<SysUser> list = userService.getUserListByDeptIds(deptIds);
        if (!list.isEmpty()) {
            throw new BusinessException(BaseResponseCode.NOT_PERMISSION_DELETED_DEPT);
        }
        sysDept.setDeleted(0);
        sysDept.setUpdateTime(LocalDateTime.now());
        int count = sysDeptMapper.updateById(sysDept);
        if (count != 1) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERRO);
        }
    }

    @Override
    public PageVO<SysDept> pageInfo(DeptPageReqVO vo) {

        Page<SysDept> page = new Page<>(vo.getPageNum(), vo.getPageSize());
        Page<SysDept> deptPage = sysDeptMapper.selectPage(page, Wrappers.emptyWrapper());

        return PageUtils.getPageVO(deptPage);
    }

    @Override
    public List<DeptRespNodeVO> deptTreeList(String deptId) {
        List<SysDept> list;
        if (StringUtils.isEmpty(deptId)) {
            list = sysDeptMapper.selectList(Wrappers.emptyWrapper());
        } else {
            SysDept sysDept = sysDeptMapper.selectById(deptId);
            if (sysDept == null) {
                throw new BusinessException(BaseResponseCode.DATA_ERROR);
            }
            List<String> childIds = sysDeptMapper.selectChildIds(sysDept.getRelationCode());
            list = sysDeptMapper.selectAllByNotContainChild(childIds);
        }
        //默认加一个顶级方便新增顶级部门
        DeptRespNodeVO respNodeVO = new DeptRespNodeVO();
        respNodeVO.setTitle("默认顶级部门");
        respNodeVO.setId("0");
        respNodeVO.setSpread(true);
        respNodeVO.setChildren(getTree(list));
        List<DeptRespNodeVO> result = new ArrayList<>();
        result.add(respNodeVO);
        return result;
    }

    private List<DeptRespNodeVO> getTree(List<SysDept> all) {
        List<DeptRespNodeVO> list = new ArrayList<>();
        for (SysDept sysDept : all) {
            if (sysDept.getPid().equals("0")) {
                DeptRespNodeVO deptTree = new DeptRespNodeVO();
                BeanUtils.copyProperties(sysDept, deptTree);
                deptTree.setTitle(sysDept.getName());
                deptTree.setSpread(true);
                deptTree.setChildren(getChild(sysDept.getId(), all));
                list.add(deptTree);
            }
        }
        return list;
    }

    private List<DeptRespNodeVO> getChild(String id, List<SysDept> all) {
        List<DeptRespNodeVO> list = new ArrayList<>();
        for (SysDept sysDept : all) {
            if (sysDept.getPid().equals(id)) {
                DeptRespNodeVO deptTree = new DeptRespNodeVO();
                BeanUtils.copyProperties(sysDept, deptTree);
                deptTree.setTitle(sysDept.getName());
                deptTree.setChildren(getChild(sysDept.getId(), all));
                list.add(deptTree);
            }
        }
        return list;
    }

    @Override
    public List<SysUser> pageDeptUserInfo(UserPageUserByDeptReqVO vo) {

        SysDept sysDept = sysDeptMapper.selectById(vo.getDeptId());
        if (null == sysDept) {
            log.error("传入 的 id:{}不合法", vo.getDeptId());
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        List<String> strings = sysDeptMapper.selectChildIds(sysDept.getRelationCode());

        return userService.selectUserInfoByDeptIds(strings);
    }

    @Override
    public List<SysDept> selectAll() {
        List<SysDept> list = sysDeptMapper.selectList(Wrappers.emptyWrapper());
        for (SysDept sysDept : list) {
            SysDept parent = sysDeptMapper.selectById(sysDept.getPid());
            if (parent != null) {
                sysDept.setPid(parent.getName());
            }
        }
        return list;
    }
}
