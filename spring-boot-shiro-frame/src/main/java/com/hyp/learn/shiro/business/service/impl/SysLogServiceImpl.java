package com.hyp.learn.shiro.business.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyp.learn.shiro.business.service.SysLogService;
import com.hyp.learn.shiro.business.vo.LogVO;
import com.hyp.learn.shiro.persistence.beans.SysLog;
import com.hyp.learn.shiro.persistence.mapper.SysLogMapper;
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
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {
    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public Page<SysLog> pageInfo(LogVO vo) {

        Page<SysLog> page = new Page<>(vo.getPageNumber(), vo.getPageSize());

        SysLog sysLog = new SysLog();
        BeanUtils.copyProperties(vo, sysLog);

        return sysLogMapper.selectPage(page, Wrappers.query(sysLog));
    }

    @Override
    public void deleted(List<String> logIds) {
        sysLogMapper.deleteBatchIds(logIds);
    }
}
