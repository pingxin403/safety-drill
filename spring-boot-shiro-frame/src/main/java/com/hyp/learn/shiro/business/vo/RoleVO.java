package com.hyp.learn.shiro.business.vo;

import com.hyp.learn.shiro.business.entity.Role;
import com.hyp.learn.shiro.framework.object.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class RoleVO extends BaseVO {
    private Role role;
}

