package com.hyp.learn.cf.entity;

import com.hyp.learn.cf.commons.object.BaseDO;
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
public class SysDept extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 部门编号(规则：父级关系编码+自己的编码)
     */
    private String deptNo;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 父级id
     */
    private String pid;

    /**
     * 状态(1:正常；0:弃用)
     */
    private Integer status;

    /**
     * 为了维护更深层级关系
     */
    private String relationCode;

    /**
     * 部门经理user_id
     */
    private String deptManagerId;

    /**
     * 部门经理名称
     */
    private String managerName;

    /**
     * 部门经理联系电话
     */
    private String phone;



}
