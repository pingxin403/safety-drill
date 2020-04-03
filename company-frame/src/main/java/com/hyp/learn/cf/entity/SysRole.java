package com.hyp.learn.cf.entity;

import com.hyp.learn.cf.commons.object.BaseDO;

import java.util.List;

import com.hyp.learn.cf.vo.resp.PermissionRespNode;
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
public class SysRole extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    private String name;

    private String description;

    /**
     * 状态(1:正常0:弃用)
     */
    private Integer status;



    private transient List<PermissionRespNode> permissionRespNodes;



}
