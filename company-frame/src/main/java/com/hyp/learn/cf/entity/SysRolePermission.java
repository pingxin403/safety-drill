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
public class SysRolePermission extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 菜单权限id
     */
    private String permissionId;


}
