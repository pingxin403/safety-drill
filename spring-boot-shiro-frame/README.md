----

### 项目阐述——Springboot shiro

Springboot + shiro权限管理。这或许是流程最详细、代码最干净、配置最简单的shiro上手项目了。



#### 开发环境

| 工具    | 版本或描述                |    
| ----- | -------------------- |    
| OS    | Ubuntu 18.04            |    
| JDK   | 1.8                 |    
| IDE   | IntelliJ IDEA 2018.3 |    
| Maven | 3.6.1              |    
| MySQL | 8.0.11                |  


#### SQL Model

![GEpoQg.png](https://s1.ax1x.com/2020/03/28/GEpoQg.png)

#### 使用说明

1. 使用IDE导入本项目
2. 新建数据库`CREATE DATABASE shiro-frame;`
3. 导入数据库`docs/db/shiro.sql`
4. 修改(`resources/application.yml`)配置文件
   1. 数据库链接属性(可搜索`datasource`) 
   2. redis配置(可搜索`redis`)
5. 运行项目(三种方式)
   1. 项目根目录下执行`mvn -X clean package -Dmaven.test.skip=true`编译打包，然后执行`java -jar shiro-admin/target/shiro-admin.jar`
   2. 项目根目录下执行`mvn springboot:run`
   3. 直接运行`ShiroAdminApplication.java`
6. 浏览器访问`http://127.0.0.1:8080`

**用户密码**

_超级管理员_： 账号：root  密码：123456 

_普通管理员_： 账号：admin  密码：123456

**Druid监控**

_链接_： `http://127.0.0.1:8080/druid/index.html`

用户名：px-druid  密码：px-druid
