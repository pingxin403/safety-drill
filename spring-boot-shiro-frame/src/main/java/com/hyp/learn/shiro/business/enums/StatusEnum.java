package com.hyp.learn.shiro.business.enums;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.business.enums
 * hyp create at 20-3-29
 **/
public enum StatusEnum {
    /**
     *
     */
    NORMAL(1, "正常"),
    DISABLE(0, "禁用"),
    ;
    private Integer code;
    private String desc;

    StatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static StatusEnum get(Integer code) {
        if (null == code) {
            return NORMAL;
        }
        StatusEnum[] enums = StatusEnum.values();
        for (StatusEnum anEnum : enums) {
            if (anEnum.getCode().equals(code)) {
                return anEnum;
            }
        }
        return NORMAL;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
