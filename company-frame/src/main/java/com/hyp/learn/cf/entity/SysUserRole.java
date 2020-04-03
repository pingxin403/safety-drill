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
public class SysUserRole extends BaseDO {

    private static final long serialVersionUID = 1L;

    private String userId;

    /**
     * 角色id
     */
    private String roleId;

}
