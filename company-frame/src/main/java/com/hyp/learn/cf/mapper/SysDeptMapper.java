package com.hyp.learn.cf.mapper;

import com.hyp.learn.cf.entity.SysDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pingxin
 * @since 2020-03-03
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     * 多个数据 要用 @Param
     * @param oldStr
     * @param newStr
     * @param relationCode
     * @return
     */
    int updateRelationCode(@Param("oldStr") String oldStr, @Param("newStr") String newStr, @Param("relationCode") String relationCode);

    List<String> selectChildIds(String relationCode);

    List<SysDept> selectAllByNotContainChild(List<String> list);

}
