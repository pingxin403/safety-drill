package com.hyp.learn.shiro.framework.config;

import com.hyp.learn.shiro.framework.property.AppProperties;
import com.hyp.learn.shiro.framework.tag.CustomTag;
import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModelException;
import freemarker.template.utility.TemplateModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * freemarker配置类
 *
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.framework.config
 * hyp create at 20-3-29
 **/
@Configuration
public class FreeMarkerConfig {
    @Autowired
    protected freemarker.template.Configuration configuration;
    @Autowired
    protected CustomTag customTag;

    @Autowired
    private AppProperties appProperties;


    /**
     * 添加自定义标签
     */
    @PostConstruct
    public void setSharedVariable() throws TemplateModelException {
        configuration.setSharedVariable("pxTag", customTag);
        //shiro标签
        configuration.setSharedVariable("shiro", new ShiroTags());

        //设置全局变量
        configuration.setSharedVariable("app", appProperties);

    }
}
