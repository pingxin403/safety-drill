package com.hyp.learn.shiro.persistence.beans;

import com.hyp.learn.shiro.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author pingxin
 * @since 2020-03-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysLog extends AbstractDO {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户操作
     */
    private String operation;

    /**
     * 响应时间
     */
    private Integer time;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求参数
     */
    private String params;

    /**
     * IP地址
     */
    private String ip;


}
