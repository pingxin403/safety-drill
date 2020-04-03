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
  PRIMARY KEY (`id`),
  KEY `idx_sys_resource_parent_id` (`p_id`)
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
  `nickName`        varchar(30)                  DEFAULT '' COMMENT '昵称',
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




-- ----------------------------
-- Records of sys_resources
-- ----------------------------
INSERT INTO `sys_resources`
VALUES ('1', '1', '用户管理', 'menu', null, null, '0', '1', '0', '1', 'fa fa-users', '2018-05-16 17:02:54',
        '2018-05-16 17:02:54', 1);
INSERT INTO `sys_resources`
VALUES ('2', '2', '用户列表', 'menu', '/users', 'users', '1', '1', '0', '1', null, '2017-12-22 13:56:15',
        '2018-05-16 14:44:20',
        1);
INSERT INTO `sys_resources`
VALUES ('3', '3', '新增用户', 'button', null, 'user:add', '2', '2', '0', '1', null, '2018-05-16 14:07:43',
        '2018-05-16 14:16:23', 1);
INSERT INTO `sys_resources`
VALUES ('4', '4', '批量删除用户', 'button', null, 'user:batchDelete', '2', '3', '0', '1', null, '2018-05-16 14:12:23',
        '2018-05-16 14:16:35', 1);
INSERT INTO `sys_resources`
VALUES ('5', '5', '编辑用户', 'button', null, 'user:edit', '2', '4', '0', '1', null, '2018-05-16 14:12:50',
        '2018-05-16 14:16:43', 1);
INSERT INTO `sys_resources`
VALUES ('6', '6', '删除用户', 'button', null, 'user:delete', '2', '5', '0', '1', null, '2018-05-16 14:13:09',
        '2018-05-16 14:51:50', 1);
INSERT INTO `sys_resources`
VALUES ('7', '7', '分配用户角色', 'button', null, 'user:allotRole', '2', '6', '0', '1', null, '2018-05-16 14:15:28',
        '2018-05-16 14:16:54', 1);
INSERT INTO `sys_resources`
VALUES ('8', '8', '系统配置', 'menu', null, null, '0', '2', '0', '1', 'fa fa-cogs', '2017-12-20 16:40:06',
        '2017-12-20 16:40:08', 1);
INSERT INTO `sys_resources`
VALUES ('9', '9', '资源管理', 'menu', '/resources', 'resources', '8', '1', '0', '1', null, '2017-12-22 15:31:05',
        '2017-12-22 15:31:05', 1);
INSERT INTO `sys_resources`
VALUES ('10', '10', '新增资源', 'button', null, 'resource:add', '9', '2', '0', '1', null, '2018-05-16 14:07:43',
        '2018-05-16 14:16:23', 1);
INSERT INTO `sys_resources`
VALUES ('11', '11', '批量删除资源', 'button', null, 'resource:batchDelete', '9', '3', '0', '1', null, '2018-05-16 14:12:23',
        '2018-05-16 14:16:35', 1);
INSERT INTO `sys_resources`
VALUES ('12', '12', '编辑资源', 'button', null, 'resource:edit', '9', '4', '0', '1', null, '2018-05-16 14:12:50',
        '2018-05-16 14:16:43', 1);
INSERT INTO `sys_resources`
VALUES ('13', '13', '删除资源', 'button', null, 'resource:delete', '9', '5', '0', '1', null, '2018-05-16 14:13:09',
        '2018-05-16 14:51:50', 1);
INSERT INTO `sys_resources`
VALUES ('14', '14', '角色管理', 'menu', '/roles', 'roles', '8', '2', '0', '1', '', '2017-12-22 15:31:27',
        '2018-05-17 12:51:06',
        1);
INSERT INTO `sys_resources`
VALUES ('15', '15', '新增角色', 'button', null, 'role:add', '14', '2', '0', '1', null, '2018-05-16 14:07:43',
        '2018-05-16 14:16:23', 1);
INSERT INTO `sys_resources`
VALUES ('16', '16', '批量删除角色', 'button', null, 'role:batchDelete', '14', '3', '0', '1', null, '2018-05-16 14:12:23',
        '2018-05-16 14:16:35', 1);
INSERT INTO `sys_resources`
VALUES ('17', '17', '编辑角色', 'button', null, 'role:edit', '14', '4', '0', '1', null, '2018-05-16 14:12:50',
        '2018-05-16 14:16:43', 1);
INSERT INTO `sys_resources`
VALUES ('18', '18', '删除角色', 'button', null, 'role:delete', '14', '5', '0', '1', null, '2018-05-16 14:13:09',
        '2018-05-16 14:51:50', 1);
INSERT INTO `sys_resources`
VALUES ('19', '19', '分配角色资源', 'button', null, 'role:allotResource', '14', '6', '0', '1', null, '2018-05-17 10:04:21',
        '2018-05-17 10:04:21', 1);
INSERT INTO `sys_resources`
VALUES ('20', '20', '数据监控', 'menu', '', '', null, '3', '0', '1', 'fa fa-heartbeat', '2018-05-17 12:38:20',
        '2018-05-17 12:53:06', 1);
INSERT INTO `sys_resources`
VALUES ('21', '21', 'Druid监控', 'menu', '/druid/index.html', 'druid', '20', '1', '1', '1', '', '2018-05-17 12:46:37',
        '2018-05-17 12:52:33', 1);



-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role`
VALUES ('1', 'role:root', '超级管理员', '1', '2017-12-20 16:40:24', '2017-12-20 16:40:26', 1);
INSERT INTO `sys_role`
VALUES ('2', 'role:admin', '管理员', '1', '2017-12-22 13:56:39', '2017-12-22 13:56:39', 1);


-- ----------------------------
-- Records of sys_role_resources
-- ----------------------------
INSERT INTO `sys_role_resources`
VALUES ('27', '1', '20', '2018-05-17 12:52:41', '2018-05-17 12:52:41', 1);
INSERT INTO `sys_role_resources`
VALUES ('28', '1', '21', '2018-05-17 12:52:41', '2018-05-17 12:52:41', 1);
INSERT INTO `sys_role_resources`
VALUES ('29', '1', '1', '2018-05-17 12:52:41', '2018-05-17 12:52:41', 1);
INSERT INTO `sys_role_resources`
VALUES ('30', '1', '2', '2018-05-17 12:52:41', '2018-05-17 12:52:41', 1);
INSERT INTO `sys_role_resources`
VALUES ('31', '1', '3', '2018-05-17 12:52:41', '2018-05-17 12:52:41', 1);
INSERT INTO `sys_role_resources`
VALUES ('32', '1', '4', '2018-05-17 12:52:41', '2018-05-17 12:52:41', 1);
INSERT INTO `sys_role_resources`
VALUES ('33', '1', '5', '2018-05-17 12:52:41', '2018-05-17 12:52:41', 1);
INSERT INTO `sys_role_resources`
VALUES ('34', '1', '6', '2018-05-17 12:52:41', '2018-05-17 12:52:41', 1);
INSERT INTO `sys_role_resources`
VALUES ('35', '1', '7', '2018-05-17 12:52:41', '2018-05-17 12:52:41', 1);
INSERT INTO `sys_role_resources`
VALUES ('36', '1', '8', '2018-05-17 12:52:41', '2018-05-17 12:52:41', 1);
INSERT INTO `sys_role_resources`
VALUES ('37', '1', '9', '2018-05-17 12:52:41', '2018-05-17 12:52:41', 1);
INSERT INTO `sys_role_resources`
VALUES ('38', '1', '10', '2018-05-17 12:52:41', '2018-05-17 12:52:41', 1);
INSERT INTO `sys_role_resources`
VALUES ('39', '1', '11', '2018-05-17 12:52:41', '2018-05-17 12:52:41', 1);
INSERT INTO `sys_role_resources`
VALUES ('40', '1', '12', '2018-05-17 12:52:41', '2018-05-17 12:52:41', 1);
INSERT INTO `sys_role_resources`
VALUES ('41', '1', '13', '2018-05-17 12:52:41', '2018-05-17 12:52:41', 1);
INSERT INTO `sys_role_resources`
VALUES ('42', '1', '14', '2018-05-17 12:52:41', '2018-05-17 12:52:41', 1);
INSERT INTO `sys_role_resources`
VALUES ('43', '1', '15', '2018-05-17 12:52:41', '2018-05-17 12:52:41', 1);
INSERT INTO `sys_role_resources`
VALUES ('44', '1', '16', '2018-05-17 12:52:41', '2018-05-17 12:52:41', 1);
INSERT INTO `sys_role_resources`
VALUES ('45', '1', '17', '2018-05-17 12:52:41', '2018-05-17 12:52:41', 1);
INSERT INTO `sys_role_resources`
VALUES ('46', '1', '18', '2018-05-17 12:52:41', '2018-05-17 12:52:41', 1);
INSERT INTO `sys_role_resources`
VALUES ('47', '1', '19', '2018-05-17 12:52:41', '2018-05-17 12:52:41', 1);
INSERT INTO `sys_role_resources`
VALUES ('48', '2', '20', '2018-05-17 12:52:51', '2018-05-17 12:52:51', 1);
INSERT INTO `sys_role_resources`
VALUES ('49', '2', '21', '2018-05-17 12:52:51', '2018-05-17 12:52:51', 1);
INSERT INTO `sys_role_resources`
VALUES ('50', '2', '2', '2018-05-17 12:52:51', '2018-05-17 12:52:51', 1);
INSERT INTO `sys_role_resources`
VALUES ('51', '2', '3', '2018-05-17 12:52:51', '2018-05-17 12:52:51', 1);
INSERT INTO `sys_role_resources`
VALUES ('52', '2', '8', '2018-05-17 12:52:51', '2018-05-17 12:52:51', 1);
INSERT INTO `sys_role_resources`
VALUES ('53', '2', '9', '2018-05-17 12:52:51', '2018-05-17 12:52:51', 1);
INSERT INTO `sys_role_resources`
VALUES ('54', '2', '10', '2018-05-17 12:52:51', '2018-05-17 12:52:51', 1);
INSERT INTO `sys_role_resources`
VALUES ('55', '2', '14', '2018-05-17 12:52:51', '2018-05-17 12:52:51', 1);
INSERT INTO `sys_role_resources`
VALUES ('56', '2', '15', '2018-05-17 12:52:51', '2018-05-17 12:52:51', 1);
INSERT INTO `sys_role_resources`
VALUES ('57', '2', '1', '2018-05-17 12:52:51', '2018-05-17 12:52:51', 1);

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES ('1', 'root', '929123f8f17944e8b0a531045453e1f1', 'CGUx1FN++xS+4wNDFeN6DA==', '超级管理员', '15151551516',
        '1670787053@qq.com', '1670787053', null,
        null,
        'https://s2.ax1x.com/2020/02/11/1ovw6J.jpg', 'ROOT', 1, null, '127.0.0.1', '2018-05-17 13:09:35', '228', null,
        '1', '2018-01-02 09:32:15', '2018-05-17 13:09:35', 1);
INSERT INTO `sys_user`
VALUES ('2', 'admin', '929123f8f17944e8b0a531045453e1f1', 'gXp2EbyZ+sB/A6QUMhiUJQ==', '管理员', '15151551516',
        '1670787053@qq.com', '1670787053', null, null,
        null, 'ADMIN', 2, '0:0:0:0:0:0:0:1', '0:0:0:0:0:0:0:1', '2018-05-17 13:08:30', '13', null, '1',
        '2018-01-02 15:56:34', '2018-05-17 13:08:30', 1);

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role`
VALUES ('1', '1', '1', '2018-01-02 10:47:27', '2018-01-02 10:47:27', 1);
INSERT INTO `sys_user_role`
VALUES ('2', '2', '2', '2018-01-05 18:21:02', '2018-01-05 18:21:02', 1);

