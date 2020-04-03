package com.hyp.learn.cf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hyp.learn.cf.entity.SysLog;
import com.hyp.learn.cf.vo.req.SysLogPageReqVO;
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
public interface ISysLogService extends IService<SysLog> {
    PageVO<SysLog> pageInfo(SysLogPageReqVO vo);

    void deleted(List<String> logIds);
}
