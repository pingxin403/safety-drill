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
VALUES ('21', '21', 'Druid监控', 'menu', '/druid/index.html', 'druid', '20', '1', '0', '1', '', '2018-05-17 12:46:37',
        '2018-05-17 12:52:33', 1);
INSERT INTO `sys_resources`
VALUES ('22', '22', 'API监控', 'menu', '/swagger-ui.html', 'swagger', '20', '2', '0', '1', '', '2018-05-17 12:46:37',
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

