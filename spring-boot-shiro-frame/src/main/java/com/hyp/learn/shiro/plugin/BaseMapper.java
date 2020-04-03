package com.hyp.learn.shiro.plugin;


/**
 * 公有Mapper
 *
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.plugin
 * hyp create at 20-3-30
 **/
public interface BaseMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {
    // 特别注意，该接口不能被扫描到，否则会出错
}

