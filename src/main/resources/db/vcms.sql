/*
Navicat MySQL Data Transfer

Source Server         : helloworld
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : vcms

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2020-04-02 20:52:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_exception_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_exception_log`;
CREATE TABLE `sys_exception_log` (
  `exception_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `request_param` varchar(5000) CHARACTER SET utf8 DEFAULT NULL COMMENT '请求参数',
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '异常名称',
  `message` blob COMMENT '异常信息',
  `user_id` bigint(11) DEFAULT NULL COMMENT '操作用户ID ',
  `user_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '操作用户名称',
  `method` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '操作方法',
  `uri` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '请求URI',
  `ip` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT 'IP',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`exception_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_exception_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `login_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '账户名称',
  `ip` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT 'IP',
  `browser` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '浏览器',
  `system` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '操作系统',
  `location` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '地理位置',
  `create_time` datetime DEFAULT NULL COMMENT '登录时间',
  `info` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '操作信息',
  `host_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '主机名称',
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
INSERT INTO `sys_login_log` VALUES ('56', 'admin', '0:0:0:0:0:0:0:1', 'Chrome', 'Windows 10', '0', '2020-03-28 22:33:33', '登录成功！', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES ('57', 'admin', '0:0:0:0:0:0:0:1', 'Chrome', 'Windows 10', '0', '2020-03-28 22:42:25', '登录成功！', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES ('58', 'admin', '0:0:0:0:0:0:0:1', 'Chrome', 'Windows 10', '0', '2020-03-28 22:58:43', '登录成功！', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES ('59', 'admin', '0:0:0:0:0:0:0:1', 'Chrome', 'Windows 10', '0', '2020-03-28 23:01:25', '登录成功！', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_login_log` VALUES ('60', 'admin', '127.0.0.1', 'Chrome', 'Windows 10', '0', '2020-03-28 23:03:03', '登录成功！', '127.0.0.1');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT '0' COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', '/', 'layui-icon-face-smile', '0', '2019-01-17 13:35:13');
INSERT INTO `sys_menu` VALUES ('2', '1', '用户管理', 'user/list', 'layui-icon-face-smile', '1', '2019-01-17 13:35:13');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'role/list', 'layui-icon-face-smile', '2', '2019-01-17 13:35:13');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', 'menu/list', 'layui-icon-face-smile', '3', '2019-01-17 13:35:13');
INSERT INTO `sys_menu` VALUES ('5', '1', '职位管理', 'post/list', 'layui-icon-face-smile', '4', '2019-01-17 13:35:13');
INSERT INTO `sys_menu` VALUES ('6', '1', '组织管理', 'orgn/list', 'layui-icon-face-smile', '5', '2019-01-17 13:35:13');
INSERT INTO `sys_menu` VALUES ('7', '1', '登录日志', 'login/list', 'layui-icon-face-smile', '6', '2019-01-17 13:35:13');
INSERT INTO `sys_menu` VALUES ('8', '1', '操作日志', 'operate/list', 'layui-icon-face-smile', '7', '2019-01-17 13:35:13');
INSERT INTO `sys_menu` VALUES ('9', '1', '异常日志', 'exception/list', 'layui-icon-face-smile', '8', '2019-01-17 13:35:13');

-- ----------------------------
-- Table structure for sys_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operate_log`;
CREATE TABLE `sys_operate_log` (
  `operate_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '系统模块',
  `type` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '操作类型',
  `details` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
  `request_param` varchar(5000) CHARACTER SET utf8 DEFAULT NULL COMMENT '请求参数',
  `response_param` varchar(5000) CHARACTER SET utf8 DEFAULT NULL COMMENT '返回参数',
  `user_id` bigint(11) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户名称',
  `method` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '操作方法',
  `uri` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '请求uri',
  `ip` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT 'ip',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`operate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_operate_log
-- ----------------------------
INSERT INTO `sys_operate_log` VALUES ('12', '异常日志模块', '删除', '日志删除', '{}', '{\"code\":0}', '1', 'admin', 'com.wsd.controller.ExceptionLogController.deleteExceptionLog', '/vcms/exception_log/deleteExceptionLog', '0:0:0:0:0:0:0:1', '2020-03-28 22:59:34');

-- ----------------------------
-- Table structure for sys_orgn
-- ----------------------------
DROP TABLE IF EXISTS `sys_orgn`;
CREATE TABLE `sys_orgn` (
  `orgn_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级部门ID，一级部门ID为0',
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT '0' COMMENT '序号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `manager` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '部门负责人',
  `mobile` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`orgn_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_orgn
-- ----------------------------
INSERT INTO `sys_orgn` VALUES ('1', '0', '公司', '0', '2020-03-15 15:35:21', '', '', '');
INSERT INTO `sys_orgn` VALUES ('5', '1', '研发中心', '1', '2020-03-15 16:28:12', '1', '13423232322', '1@qq.com');
INSERT INTO `sys_orgn` VALUES ('6', '1', '销售部', '2', '2020-03-15 16:28:36', '2', '13454545444', '1@qq.com');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '职位名称',
  `code` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '职位编码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('5', '董事长', 'ceo', '2020-03-15 14:55:05', '董事长');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色标识',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ROLE_admin', '管理员', '2019-01-17 13:35:13', '管理员');
INSERT INTO `sys_role` VALUES ('24', 'ROLE_user', '普通用户', '2020-03-03 15:16:59', '普通用户');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=331 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('320', '24', '1');
INSERT INTO `sys_role_menu` VALUES ('321', '24', '5');
INSERT INTO `sys_role_menu` VALUES ('322', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('323', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('324', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('325', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('326', '1', '5');
INSERT INTO `sys_role_menu` VALUES ('327', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('328', '1', '7');
INSERT INTO `sys_role_menu` VALUES ('329', '1', '8');
INSERT INTO `sys_role_menu` VALUES ('330', '1', '9');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `enabled` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `userface` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `orgn_id` bigint(20) DEFAULT NULL COMMENT '部门id',
  `post_id` bigint(20) DEFAULT NULL COMMENT '职位id',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$MU4rPhyDag7DVc5Ewxh6puyhwfmOtM22wa/BlmBNRkUzjY5lfXC52', 'admin@qq.com', '13488888888', '1', '2017-05-01 11:11:11', '系统管理员', null, '系统管理员', '5', '5');
INSERT INTO `sys_user` VALUES ('3', '11', '$2a$10$1Ks7sGZ/HB9JTVBOdGMeHuuIL6RRQOxXywE5/uTlzSrmZl60mizL2', '1@qq.com', '13434343333', '1', '2020-03-28 12:25:49', '11111ii', null, '1', '5', '5');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('11', '1', '1');
INSERT INTO `sys_user_role` VALUES ('19', '3', '24');
