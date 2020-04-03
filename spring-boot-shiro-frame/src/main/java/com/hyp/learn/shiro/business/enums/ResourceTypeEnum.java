package com.hyp.learn.shiro.business.enums;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.business.enums
 * hyp create at 20-3-29
 **/
public enum ResourceTypeEnum {
    /**
     *
     */
    menu("菜单"), button("按钮"), directory("目录");

    private final String info;

    private ResourceTypeEnum(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
