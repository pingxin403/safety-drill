package com.hyp.learn.shiro.persistence.beans;

import com.hyp.learn.shiro.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.persistence.beans
 * hyp create at 20-3-29
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SysResources extends AbstractDO {
    /**
     * 菜单权限编码
     */
    private String code;
    private String name;
    private String type;
    private String url;
    private String perms;
    private Long pId;
    private Integer orderNum;
    private Boolean external;
    private Boolean status;
    private String icon;

    private transient String checked;
    private transient SysResources parent;
    private transient List<SysResources> nodes;
}
