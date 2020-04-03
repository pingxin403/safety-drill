package com.hyp.learn.shiro.persistence.beans;

import com.hyp.learn.shiro.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.persistence.beans
 * hyp create at 20-3-29
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserRole extends AbstractDO {
    private Long userId;
    private Long roleId;
}
