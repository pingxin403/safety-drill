-- ----------------------------
-- Table structure for sys_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_resources`;
CREATE TABLE `sys_resources`
(
  `id`          bigint(20) NOT NULL AUTO_INCREMENT,
  `code`        varchar(64)  DEFAULT NULL COMMENT '菜单权限编码',
  `name`        varchar(100) DEFAULT NULL COMMENT '菜单权限名称',
  `type`        varchar(50)  DEFAULT NULL COMMENT '菜单权限类型(1:菜单;2:按钮;3:目录)',
  `url`         varchar(200) DEFAULT NULL COMMENT '访问地址URL',
  `perms`       varchar(100) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：sys:user:add,sys:user:edit)',
  `p_id`        bigint(20)   DEFAULT '0' COMMENT '父级菜单权限名称',
  `order_num`   int(10)      DEFAULT NULL COMMENT '排序',
  `external`    tinyint(1)   DEFAULT NULL COMMENT '是否外部链接',
  `status`      tinyint(4)   DEFAULT '1' COMMENT '状态1:正常 0：禁用',
  `icon`        varchar(100) DEFAULT NULL COMMENT '菜单图标',
  `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`     tinyint(4)   DEFAULT '1' COMMENT '是否删除(1未删除；0已删除)',
  PRIMARY KEY (`id`)
);



-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
  `id`          bigint(20) NOT NULL AUTO_INCREMENT,
  `name`        varchar(100) DEFAULT NULL COMMENT '角色名',
  `description` varchar(300) DEFAULT NULL,
  `status`      tinyint(1)   DEFAULT '1' COMMENT '状态(1:正常0:弃用)',
  `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`     tinyint(4)   DEFAULT '1' COMMENT '是否删除(1未删除；0已删除)',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for sys_role_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resources`;
CREATE TABLE `sys_role_resources`
(
  `id`           bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id`      bigint(20) NOT NULL,
  `resources_id` bigint(20) NOT NULL,
  `create_time`  datetime   DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time`  datetime   DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted`      tinyint(4) DEFAULT '1' COMMENT '是否删除(1未删除；0已删除)',
  PRIMARY KEY (`id`)
);


-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
  `id`              int(11) NOT NULL AUTO_INCREMENT,
  `username`        varchar(100)                 DEFAULT NULL,
  `salt`            varchar(40)                  DEFAULT NULL COMMENT '加密盐值',
  `password`        varchar(100)                 DEFAULT NULL COMMENT '登录密码',
  `nick_name`       varchar(30)                  DEFAULT '' COMMENT '昵称',
  `phone`           varchar(30)                  DEFAULT NULL COMMENT '手机号',
  `email`           varchar(100)                 DEFAULT NULL COMMENT '邮箱地址',
  `qq`              varchar(20)                  DEFAULT NULL COMMENT 'QQ',
  `birthday`        date                         DEFAULT NULL COMMENT '生日',
  `gender`          tinyint(2)                   DEFAULT NULL COMMENT '性别',
  `avatar`          varchar(255)                 DEFAULT NULL COMMENT '头像地址',
  `user_type`       enum ('ROOT','ADMIN','USER') DEFAULT 'ADMIN' COMMENT '超级管理员、管理员、普通用户',
  `create_where`    tinyint(4)                   DEFAULT '1' COMMENT '创建来源(1.web 2.android 3.ios )',
  `reg_ip`          varchar(30)                  DEFAULT NULL COMMENT '注册IP',
  `last_login_ip`   varchar(30)                  DEFAULT NULL COMMENT '最近登录IP',
  `last_login_time` datetime                     DEFAULT NULL COMMENT '最近登录时间',
  `login_count`     int(10)                      DEFAULT '0' COMMENT '登录次数',
  `remark`          varchar(100)                 DEFAULT NULL COMMENT '用户备注',
  `status`          tinyint(4)                   DEFAULT '1' COMMENT '账户状态(1.正常 2.锁定 )',
  `create_time`     datetime                     DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_time`     datetime                     DEFAULT NOW() ON UPDATE CURRENT_TIMESTAMP,
  `deleted`         tinyint(4)                   DEFAULT '1' COMMENT '是否删除(1未删除；0已删除)',
  PRIMARY KEY (`id`)
);


-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
  `id`          bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id`     bigint(20) NOT NULL,
  `role_id`     bigint(20) NOT NULL,
  `create_time` datetime   DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime   DEFAULT NOW() ON UPDATE CURRENT_TIMESTAMP,
  `deleted`     tinyint(4) DEFAULT '1' COMMENT '是否删除(1未删除；0已删除)',
  PRIMARY KEY (`id`)
);

--
-- Table structure for table `sys_log`
--

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log`
(
  `id`          bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id`     bigint(20)    DEFAULT NULL COMMENT '用户id',
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



