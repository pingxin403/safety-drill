package com.hyp.learn.shiro.business.vo;


import com.hyp.learn.shiro.business.entity.User;
import com.hyp.learn.shiro.framework.object.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserVO extends BaseVO {
    private User user;
}
