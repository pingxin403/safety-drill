package com.hyp.learn.shiro.framework.property;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 应用属性
 *
 * @author hyp
 * Project name is spring-boot-shiro-frame
 * Include in com.hyp.learn.shiro.framework.property
 * hyp create at 20-3-31
 **/
@Configuration
@ConfigurationProperties(prefix = "app")
@Data
@EqualsAndHashCode(callSuper = false)
@Order(-1)
public class AppProperties {

    private String title = "Shiro权限管理后台";

    public String scope = "2018-2020";

    public String site = "https://hanyunpeng0521.github.io";

    public String author = "pingxin";


    public String name = "PX";

    public String url = "https://hanyunpeng0521.github.io";

    public String desc = "study hard and make progress every day";

    private String version = "1.0.0";

}
