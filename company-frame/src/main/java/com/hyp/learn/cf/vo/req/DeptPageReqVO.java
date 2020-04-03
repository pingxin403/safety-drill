package com.hyp.learn.cf.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DeptPageReqVO {
    @ApiModelProperty(value = "第几页")
    private int pageNum=1;

    @ApiModelProperty(value = "分页数量")
    private int pageSize=10;
}
