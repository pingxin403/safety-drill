package com.hyp.learn.shiro.framework.object;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.framework.object
 * hyp create at 20-3-29
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractDO implements Serializable {
    /**
     * @fieldName: serialVersionUID
     * @fieldType: long
     */
    private static final long serialVersionUID = 508869767856350L;


    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    /**
     * 是否删除(1未删除；0已删除)
     */
    @TableLogic //标记逻辑删除属性
    private Integer deleted;

}
