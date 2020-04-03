-- MySQL dump 10.13  Distrib 5.7.29, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: company_frame

--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`
(
  `id`              varchar(64) NOT NULL COMMENT '主键',
  `dept_no`         varchar(18)   DEFAULT NULL COMMENT '部门编号(规则：父级关系编码+自己的编码)',
  `name`            varchar(300)  DEFAULT NULL COMMENT '部门名称',
  `pid`             varchar(64) NOT NULL COMMENT '父级id',
  `status`          tinyint(4)    DEFAULT '1' COMMENT '状态(1:正常；0:弃用)',
  `relation_code`   varchar(3000) DEFAULT NULL COMMENT '为了维护更深层级关系',
  `dept_manager_id` varchar(64)   DEFAULT NULL COMMENT '部门经理user_id',
  `manager_name`    varchar(255)  DEFAULT NULL COMMENT '部门经理名称',
  `phone`           varchar(20)   DEFAULT NULL COMMENT '部门经理联系电话',
  `create_time`     datetime      DEFAULT NOW() COMMENT '创建时间',
  `update_time`     datetime      DEFAULT NOW() ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`         tinyint(4)    DEFAULT '1' COMMENT '是否删除(1未删除；0已删除)',
  PRIMARY KEY (`id`)
);
--
-- Table structure for table `sys_log`
--

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log`
(
  `id`          varchar(64) NOT NULL,
  `user_id`     varchar(64)   DEFAULT NULL COMMENT '用户id',
  `username`    varchar(50)   DEFAULT NULL COMMENT '用户名',
  `operation`   varchar(50)   DEFAULT NULL COMMENT '用户操作',
  `time`        int(11)       DEFAULT NULL COMMENT '响应时间',
  `method`      varchar(200)  DEFAULT NULL COMMENT '请求方法',
  `params`      varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `ip`          varchar(64)   DEFAULT NULL COMMENT 'IP地址',
  `create_time` datetime      DEFAULT NOW() COMMENT '创建时间',
  `update_time` datetime      DEFAULT NOW() ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`     tinyint(4)    DEFAULT '1' COMMENT '是否删除(1未删除；0已删除)',
  PRIMARY KEY (`id`)
);
--
-- Table structure for table `sys_permission`
--

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission`
(
  `id`          varchar(64) NOT NULL COMMENT '主键',
  `code`        varchar(64)  DEFAULT NULL COMMENT '菜单权限编码',
  `name`        varchar(300) DEFAULT NULL COMMENT '菜单权限名称',
  `perms`       varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：sys:user:add,sys:user:edit)',
  `url`         varchar(100) DEFAULT NULL COMMENT '访问地址URL',
  `method`      varchar(10)  DEFAULT NULL COMMENT '资源请求类型',
  `pid`         varchar(64)  DEFAULT NULL COMMENT '父级菜单权限名称',
  `order_num`   int(11)      DEFAULT '0' COMMENT '排序',
  `type`        tinyint(4)   DEFAULT NULL COMMENT '菜单权限类型(1:目录;2:菜单;3:按钮)',
  `status`      tinyint(4)   DEFAULT '1' COMMENT '状态1:正常 0：禁用',
  `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
  `update_time` datetime     DEFAULT NOW() ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`     tinyint(4)   DEFAULT '1' COMMENT '是否删除(1未删除；0已删除)',
  PRIMARY KEY (`id`)
);
--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role`
(
  `id`          varchar(64) NOT NULL COMMENT '主键',
  `name`        varchar(255) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(300) DEFAULT NULL,
  `status`      tinyint(4)   DEFAULT '1' COMMENT '状态(1:正常0:弃用)',
  `create_time` datetime     DEFAULT NOW() COMMENT '创建时间',
  `update_time` datetime     DEFAULT NOW() ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`     tinyint(4)   DEFAULT '1' COMMENT '是否删除(1未删除；0已删除)',
  PRIMARY KEY (`id`)
);

--
-- Table structure for table `sys_role_permission`
--

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission`
(
  `id`            varchar(64) NOT NULL COMMENT '主键',
  `role_id`       varchar(64) DEFAULT NULL COMMENT '角色id',
  `permission_id` varchar(64) DEFAULT NULL COMMENT '菜单权限id',
  `create_time`   datetime    DEFAULT NOW() COMMENT '创建时间',
  `update_time`   datetime    DEFAULT NOW() ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`       tinyint(4)  DEFAULT '1' COMMENT '是否删除(1未删除；0已删除)',
  PRIMARY KEY (`id`)
);

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user`
(
  `id`           varchar(64)  NOT NULL COMMENT '用户id',
  `username`     varchar(50)  NOT NULL COMMENT '账户名称',
  `salt`         varchar(40) DEFAULT NULL COMMENT '加密盐值',
  `password`     varchar(200) NOT NULL COMMENT '用户密码密文',
  `phone`        varchar(20) DEFAULT NULL COMMENT '手机号码',
  `dept_id`      varchar(64) DEFAULT NULL COMMENT '部门id',
  `real_name`    varchar(60) DEFAULT NULL COMMENT '真实名称',
  `nick_name`    varchar(60) DEFAULT NULL COMMENT '昵称',
  `email`        varchar(50) DEFAULT NULL COMMENT '邮箱(唯一)',
  `status`       tinyint(4)  DEFAULT '1' COMMENT '账户状态(1.正常 2.锁定 )',
  `sex`          tinyint(4)  DEFAULT '1' COMMENT '性别(1.男 2.女)',
  `deleted`      tinyint(4)  DEFAULT '1' COMMENT '是否删除(1未删除；0已删除)',
  `create_id`    varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_id`    varchar(64) DEFAULT NULL COMMENT '更新人',
  `create_where` tinyint(4)  DEFAULT '1' COMMENT '创建来源(1.web 2.android 3.ios )',
  `create_time`  datetime    DEFAULT NOW() COMMENT '创建时间',
  `update_time`  datetime    DEFAULT NOW() ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
  `id`          varchar(64) NOT NULL COMMENT '用户id',
  `user_id`     varchar(64) DEFAULT NULL,
  `role_id`     varchar(64) DEFAULT NULL COMMENT '角色id',
  `create_time` datetime    DEFAULT NOW() COMMENT '创建时间',
  `update_time` datetime    DEFAULT NOW() ON UPDATE CURRENT_TIMESTAMP,
  `deleted`     tinyint(4)  DEFAULT '1' COMMENT '是否删除(1未删除；0已删除)',
  PRIMARY KEY (`id`)
);

