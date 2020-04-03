package com.hyp.learn.cf.commons.object;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author hyp
 * Project name is company-frame
 * Include in com.hyp.learn.cf.entity
 * hyp create at 20-3-3
 **/
@Data
public class BaseDO implements Serializable {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    /**
     * 是否删除(1未删除；0已删除)
     */
    @TableLogic //标记逻辑删除属性
    private Integer deleted;
}