package com.hyp.learn.shiro.business.vo;

import com.hyp.learn.shiro.framework.object.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResourceVO extends BaseVO {
    private String type;
}

