package com.hyp.learn.shiro.business.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hyp.learn.shiro.business.vo.LogVO;
import com.hyp.learn.shiro.persistence.beans.SysLog;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author pingxin
 * @since 2020-03-03
 */
public interface SysLogService extends IService<SysLog> {
    Page<SysLog> pageInfo(LogVO vo);

    void deleted(List<String> logIds);
}
