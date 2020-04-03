package com.hyp.learn.cf.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyp.learn.cf.entity.SysLog;
import com.hyp.learn.cf.mapper.SysLogMapper;
import com.hyp.learn.cf.service.ISysLogService;
import com.hyp.learn.cf.utils.PageUtils;
import com.hyp.learn.cf.vo.req.SysLogPageReqVO;
import com.hyp.learn.cf.commons.object.PageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author pingxin
 * @since 2020-03-03
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {
    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public PageVO<SysLog> pageInfo(SysLogPageReqVO vo) {

        Page<SysLog> page = new Page<>(vo.getPageNum(), vo.getPageSize());

        SysLog sysLog = new SysLog();
        BeanUtils.copyProperties(vo, sysLog);

        Page<SysLog> logPage = sysLogMapper.selectPage(page, Wrappers.query(sysLog));

        return PageUtils.getPageVO(logPage);
    }

    @Override
    public void deleted(List<String> logIds) {
        sysLogMapper.deleteBatchIds(logIds);
    }
}
