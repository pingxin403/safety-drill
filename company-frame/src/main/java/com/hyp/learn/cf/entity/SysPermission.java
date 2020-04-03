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
public class SysPermission extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单权限编码
     */
    private String code;

    /**
     * 菜单权限名称
     */
    private String name;

    /**
     * 授权(多个用逗号分隔，如：sys:user:add,sys:user:edit)
     */
    private String perms;

    /**
     * 访问地址URL
     */
    private String url;

    /**
     * 资源请求类型
     */
    private String method;

    /**
     * 父级菜单权限名称
     */
    private String pid;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 菜单权限类型(1:目录;2:菜单;3:按钮)
     */
    private Integer type;

    /**
     * 状态1:正常 0：禁用
     */
    private Integer status;


}
