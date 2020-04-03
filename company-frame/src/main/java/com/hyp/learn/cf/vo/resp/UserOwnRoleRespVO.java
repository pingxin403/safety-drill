package com.hyp.learn.cf.vo.resp;

import com.hyp.learn.cf.entity.SysRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserOwnRoleRespVO {
    @ApiModelProperty("所有角色集合")
    private List<SysRole> allRole;
    @ApiModelProperty(value = "用户所拥有角色集合")
    private List<String> ownRoles;
}
