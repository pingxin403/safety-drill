package com.hyp.learn.shiro.persistence.beans;

import com.hyp.learn.shiro.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.persistence.beans
 * hyp create at 20-3-29
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser extends AbstractDO {

    private String username;
    /**
     * 加密盐值
     */
    private String salt;
    /**
     * 用户密码密文
     */
    private String password;
    private String nickName;
    private String phone;
    private String email;
    private String qq;
    private Date birthday;
    private Integer gender;

    private String avatar;
    private String userType;
    /**
     * 创建来源(1.web 2.android 3.ios )
     */
    private Integer createWhere;
    private String regIp;
    private String lastLoginIp;
    private Date lastLoginTime;
    private Integer loginCount;
    private String remark;
    private Integer status;
}
