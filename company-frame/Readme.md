# company-frame

#### 介绍

基于spring boot 2.2.4、shiro、jwt、redis、swagger2、mybatis 、thymeleaf、layui 后台管理系统， 权限控制的方式为 RBAC。代码通熟易懂 、JWT（无状态token）过期自动刷新，数据全程 ajax 获取，封装 ajax 工具类、菜单无线层级展示，解决 layui.tree 树形组件，回显问题。数据交互都是以 JSON 格式交互。后台接口RESTful 风格，支持前后端分离，app公用一套接口。

#### 软件架构

软件架构说明

- 核心框架：spring boot 2.2.4
- 持久层框架：mybatis
- 数据库连接池：alibaba druid
- 安全框架：apache shiro
- 无状态 JWT
- 缓存框架：redis(自定义 RedisTemplate 序列化)
- 日志框架：logback
- 接口文档：swagger 2.9.2
- 前端模板：thymeleaf+layui2x

#### **部署**

- 下载redis 启动redis

  ```
  docker pull redis
  docker run -p 6379:6379 -d redis
  ```

- 创建company_frame数据库

- 导入company_frame.sql

- 启动项目

- 接口文档访问 http://localhost:8080/swagger-ui.html#/

- 登录地址 http://localhost:8080/index/login

- 登录密码 都是 666666