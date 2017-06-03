/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-06-02 20:11:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '授权id',
  `resource_ids` varchar(768) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源id(对应系统的security.oauth2.resource.id)',
  `client_secret` varchar(768) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '秘钥',
  `scope` varchar(768) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '(read,write,trust)',
  `authorized_grant_types` varchar(768) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '授权类型',
  `web_server_redirect_uri` varchar(768) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '转发url',
  `authorities` varchar(768) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限。（本系统未用）',
  `access_token_validity` double DEFAULT NULL COMMENT '过期时间',
  `refresh_token_validity` double DEFAULT NULL COMMENT '刷新token时间',
  `additional_information` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `autoapprove` varchar(768) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `daily_access_num` int(11) DEFAULT NULL,
  `minute_access_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='auth2.0授权信息表';

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('1', '123', 'gateway,car_resource,insurance_resource,risk_resource,data_base_resource', '123', 'read', 'client_credentials,password', '', '', '1230000000', '0', '', '', '2017-03-16 21:56:24', '2017-03-24 14:42:05', '1', null);
INSERT INTO `oauth_client_details` VALUES ('2', '2', 'openid', 'acmesecret', 'read', 'client_credentials', 'http://localhost:8765/login', '', '1230000000', '0', '', '', '2017-03-10 11:56:49', '2017-03-30 15:00:56', '2', null);
INSERT INTO `oauth_client_details` VALUES ('3', '234', 'gateway,car_resource,insurance_resource,risk_resource,data_base_resource', '234', 'read', 'client_credentials,password', '', '', '1230000000', '0', '', '', '2017-03-16 21:56:24', '2017-03-24 14:42:05', '3', null);
INSERT INTO `oauth_client_details` VALUES ('4', '456', 'gateway,car_resource,insurance_resource,risk_resource,data_base_resource', '123', 'read', 'client_credentials,password', '', '', '1230000000', '0', '', '', '2017-03-16 16:10:48', '2017-03-23 15:53:18', '4', null);
INSERT INTO `oauth_client_details` VALUES ('5', '4567', 'gateway,car_resource,insurance_resource,risk_resource', '123', 'read', 'password', '', '', '1230000000', '0', '', '', '2017-03-16 09:54:26', '2017-03-24 14:40:18', '5', null);
INSERT INTO `oauth_client_details` VALUES ('6', '67UV12c', 'gateway,car_resource,insurance_resource,risk_resource', 'fe0c0a90b475d255a61176af106d9c20', 'read', 'client_credentials,password', '', 'ROLE_client', '1230000000', '0', '', '', '2017-03-10 11:56:49', '2017-03-30 14:30:54', '6', null);

-- ----------------------------
-- Table structure for oauth_resource
-- ----------------------------
DROP TABLE IF EXISTS `oauth_resource`;
CREATE TABLE `oauth_resource` (
  `resource_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent` int(11) DEFAULT NULL COMMENT '上级菜单ID',
  `name` varchar(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '菜单名字',
  `level` tinyint(2) NOT NULL DEFAULT '0' COMMENT '菜单级别：0第一级，1第二级，2第三级，3页面资源',
  `url` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限控制URL',
  `sort` tinyint(3) DEFAULT NULL COMMENT '排序',
  `code` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '资源code',
  `type` tinyint(3) DEFAULT '0' COMMENT '类型 0 系统 1 菜单 2 资源URL',
  `page` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '页面URL',
  `status` tinyint(3) DEFAULT '0' COMMENT '0 否 1是',
  `system` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '所属系统',
  `source_type` tinyint(1) DEFAULT '0' COMMENT '资源类型.0-admin管理资源，1-外部访问接口资源',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统菜单表';

-- ----------------------------
-- Records of oauth_resource
-- ----------------------------
INSERT INTO `oauth_resource` VALUES ('1', '0', '易修通', '0', '/', '0', '', '0', '', '1', '0', '0', '2017-03-10 11:14:44', '2017-03-27 11:49:36');
INSERT INTO `oauth_resource` VALUES ('2', '1', '系统管理', '1', '', '127', '', '1', '', '1', '0', '0', '2017-03-10 11:14:44', '2017-06-22 11:47:16');
INSERT INTO `oauth_resource` VALUES ('3', '2', '部门管理', '2', '/resourse/**', '3', '', '1', '/list', '1', '0', '0', '2017-06-21 11:47:20', '2017-03-28 14:46:34');
INSERT INTO `oauth_resource` VALUES ('4', '2', '角色管理', '2', '/role/**', '2', '', '1', '/list', '1', '0', '0', '2017-03-10 11:14:44', '2017-03-27 11:47:29');
INSERT INTO `oauth_resource` VALUES ('5', '2', '资源管理', '2', '/page/**', '2', '', '1', '/list', '1', '0', '0', '2017-03-10 11:14:44', '2017-03-27 11:47:31');
INSERT INTO `oauth_resource` VALUES ('8', '0', '现房', '2', '', '9', '', '1', '', '0', '0', '0', '2017-03-10 11:14:44', '2017-03-27 11:47:22');
INSERT INTO `oauth_resource` VALUES ('10', '0', '风控', '0', '/risk/data/datagather', '0', '', '0', '', '0', '0', '0', '2017-03-15 13:47:58', '2017-06-06 11:47:23');
INSERT INTO `oauth_resource` VALUES ('11', '0', '基础数据', '0', '/data-base/**', '0', '', '0', '', '1', '0', '0', '2017-03-24 14:15:10', '2017-03-24 17:59:15');
INSERT INTO `oauth_resource` VALUES ('12', '2', '员工管理', '2', '/user/**', '4', '', '1', '', '1', '0', null, '2017-06-22 11:47:28', '2017-06-13 11:47:32');
INSERT INTO `oauth_resource` VALUES ('13', '2', '外部系统账号', '2', '', '5', '', '1', '', '1', '0', null, '2017-06-07 11:47:36', '2017-07-05 11:47:39');

-- ----------------------------
-- Table structure for oauth_system
-- ----------------------------
DROP TABLE IF EXISTS `oauth_system`;
CREATE TABLE `oauth_system` (
  `system_id` int(11) NOT NULL AUTO_INCREMENT,
  `system_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '系统名称',
  `system_remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `client_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '客户端Id',
  `system_type` int(11) DEFAULT NULL COMMENT '系统类型 1 内部系统 2 合作平台 3 车蚂蚁用户',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `daily_access_num` int(11) DEFAULT NULL COMMENT '每日接口访问频率',
  `minute_access_num` int(11) DEFAULT NULL COMMENT '每分钟接口访问频率',
  PRIMARY KEY (`system_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='auth外部系统信息';

-- ----------------------------
-- Records of oauth_system
-- ----------------------------
INSERT INTO `oauth_system` VALUES ('9', '123456', '', '456', '1', '2017-03-17 09:45:52', '2017-03-23 14:17:31', null, null);

-- ----------------------------
-- Table structure for oauth_system_resource
-- ----------------------------
DROP TABLE IF EXISTS `oauth_system_resource`;
CREATE TABLE `oauth_system_resource` (
  `oauth_system_resource_id` int(11) NOT NULL AUTO_INCREMENT,
  `system_id` int(11) DEFAULT NULL COMMENT '系统ID',
  `resource_id` int(11) DEFAULT NULL COMMENT '资源ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`oauth_system_resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='系统资源表';

-- ----------------------------
-- Records of oauth_system_resource
-- ----------------------------
INSERT INTO `oauth_system_resource` VALUES ('1', '2', '5', '2017-03-14 12:16:23', '2017-03-20 14:49:41');
INSERT INTO `oauth_system_resource` VALUES ('2', '2', '6', '2017-03-14 17:55:42', '2017-03-20 14:49:38');
INSERT INTO `oauth_system_resource` VALUES ('3', '2', '10', '2017-03-15 13:48:28', '2017-03-20 14:49:39');
INSERT INTO `oauth_system_resource` VALUES ('4', '7', '10', '2017-03-22 16:42:37', '2017-06-07 11:48:59');
INSERT INTO `oauth_system_resource` VALUES ('5', '9', '10', '2017-03-22 16:49:17', '2017-06-15 11:49:03');
INSERT INTO `oauth_system_resource` VALUES ('7', '10', '5', '2017-03-17 09:44:48', '2017-03-17 21:55:51');
INSERT INTO `oauth_system_resource` VALUES ('8', '6', '11', '2017-03-24 14:19:16', '2017-06-14 11:49:05');

-- ----------------------------
-- Table structure for user_base
-- ----------------------------
DROP TABLE IF EXISTS `user_base`;
CREATE TABLE `user_base` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '工号',
  `pwd` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_roster` varchar(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '花名',
  `user_name` varchar(36) COLLATE utf8_unicode_ci NOT NULL COMMENT '姓名',
  `depart_id` int(11) DEFAULT NULL COMMENT '所属部门',
  `user_post` varchar(36) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '职务',
  `user_phone` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '电话',
  `user_email` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'email',
  `ins_tm` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `leader` tinyint(3) DEFAULT '0' COMMENT '0 否 1是',
  `status` tinyint(3) DEFAULT '0' COMMENT '0 已离职 1 实习期 2 正式员工',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `system_id` int(11) DEFAULT NULL COMMENT '对应系统Id ',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `daily_access_num` int(11) DEFAULT '200' COMMENT '每日接口访问频率',
  `minute_access_num` int(11) DEFAULT '30' COMMENT '每分钟接口访问频率',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统员工表';

-- ----------------------------
-- Records of user_base
-- ----------------------------
INSERT INTO `user_base` VALUES ('1', null, 'e10adc3949ba59abbe56e057f20f883e', 'maven', '123456', null, null, null, null, '2017-06-02 11:17:28', '0', '0', '2017-06-02 11:17:28', null, '2017-06-02 11:21:00', '200', '30');
INSERT INTO `user_base` VALUES ('2', '123456', 'e10adc3949ba59abbe56e057f20f883e', '123', '易修通管理员', '5', '1', '11335454656', '123@qq.com', '2016-10-19 13:50:07', '1', '1', '2017-06-02 11:36:01', '9', '2017-03-27 11:43:39', '200', '30');
INSERT INTO `user_base` VALUES ('3', '345', '345', '345g324', '34g3', '1', '345', '345', '25', '2016-11-03 20:06:06', '1', '0', '2017-06-02 11:36:04', '1', '2017-03-23 14:34:02', '200', '30');
INSERT INTO `user_base` VALUES ('5', '123', 'e10adc3949ba59abbe56e057f20f883e', '123', '13', '1', '1', '11335454656', '123@qq.com', '2016-10-19 13:50:07', '1', '2', '2017-06-02 11:36:07', '1', '2017-03-23 14:34:03', '200', '30');
INSERT INTO `user_base` VALUES ('12', '1234567', 'e10adc3949ba59abbe56e057f20f883e', 'admin', '123456', '1', '1212', '15990027607', '123456@123456.com', '2016-11-11 09:22:22', '0', '1', '2017-06-02 11:36:09', '1', '2017-03-27 11:43:36', '200', '30');
INSERT INTO `user_base` VALUES ('33', '12qwet', 'c20ad4d76fe97759aa27a0c99bff6710', '12', '12', '2', '1', '1', '1', '2016-11-04 19:56:41', '0', '0', '2017-06-29 11:36:12', '1', '2017-03-23 14:34:03', '200', '30');
INSERT INTO `user_base` VALUES ('43', '0710', 'E10ADC3949BA59ABBE56E057F20F883E', '轴承', '轴承', '1', 'java', '121223434', '', '2016-11-05 16:19:58', '0', '2', '2017-06-29 11:36:15', '1', '2017-03-23 14:34:04', '200', '30');
INSERT INTO `user_base` VALUES ('44', 'nuanfeng', 'e10adc3949ba59abbe56e057f20f883e', 'nuanfeng', 'nuanfeng', '1', 'nuanfeng', 'nuanfeng666', 'nuanfeng', '2016-11-08 17:12:41', '1', '2', '2017-06-29 11:36:19', '1', '2017-03-23 14:34:05', '200', '30');
INSERT INTO `user_base` VALUES ('47', 'aaa', 'e10adc3949ba59abbe56e057f20f883e', 'aaa', 'aaa', '1', 'aaa', 'aaa', 'aaa', '2016-11-11 13:45:23', '0', '2', '2017-06-28 11:36:22', '1', '2017-03-23 14:34:06', '200', '30');
INSERT INTO `user_base` VALUES ('48', 'AAAAAA', '36d04a9d74392c727b1a9bf97a7bcbac', 'AAAAAA', 'AAAAAA', '4', 'AAAAAA', 'AAAAAA', 'AAAAAA', '2016-12-02 17:31:09', '1', '2', '2017-06-29 11:36:25', '1', '2017-03-23 14:34:07', '200', '30');
INSERT INTO `user_base` VALUES ('49', 'BBBBBB', 'fa0903293ec8fc1f19087d0eb2ffded8', 'BBBBBB', 'BBBBBB', '4', 'BBBBBB', 'BBBBBB', 'BBBBBB', '2016-12-02 17:31:42', '1', '2', '2017-06-14 11:36:28', '1', '2017-03-23 14:34:10', '200', '30');
INSERT INTO `user_base` VALUES ('50', 'CCCCCCc35', '', 'CCCCCC', 'CCCCCC', '1', 'CCCCCC', 'CCCCCC', 'CCCCCC', '2016-12-02 17:33:33', '0', '2', '2017-06-02 11:36:32', '9', '2017-03-29 11:10:18', '200', '30');
INSERT INTO `user_base` VALUES ('51', '110', 'e10adc3949ba59abbe56e057f20f883e', '测试', '测试', '1', '110', '12345678901', '123456@123.com', '2016-12-12 14:42:18', '0', '0', '2017-06-29 11:36:34', '1', '2017-03-23 14:34:09', '200', '30');
INSERT INTO `user_base` VALUES ('52', '111111', 'b0baee9d279d34fa1dfd71aadb908c3f', '11111', '1111', '5', '1', '11111111111', '', '2016-12-13 10:23:06', '0', '1', '2017-06-29 11:36:37', '1', '2017-03-23 14:34:15', '200', '30');
INSERT INTO `user_base` VALUES ('53', '22222', 'bcbe3365e6ac95ea2c0343a2395834dd', '222', '222', '5', '2', '222222', '', '2016-12-13 10:24:49', '0', '1', '2017-06-21 11:36:40', '1', '2017-03-23 14:34:15', '200', '30');
INSERT INTO `user_base` VALUES ('54', '007', '89bc98ba73f6374571c58450f7f60929', 'maven', 'maven', '1', 'java', '110', '', '2017-03-08 11:43:49', '0', '2', '2017-06-14 11:36:44', '1', '2017-03-23 14:34:16', '200', '30');
INSERT INTO `user_base` VALUES ('55', '2', '', '1111', '111111', '2', '1111', '111111', '123', '2017-06-14 11:37:17', '0', '1', '2017-06-15 11:36:48', '1', '2017-03-23 14:34:18', '200', '30');
INSERT INTO `user_base` VALUES ('56', 'qqq', '', 'qqq', 'qqq', '12', 'qqq', 'qq', 'qq', '2017-06-22 11:37:20', '1', '1', '2017-07-06 11:36:51', '9', '2017-03-28 10:07:01', '200', '30');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(36) COLLATE utf8_unicode_ci NOT NULL,
  `role_remark` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色描述',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '系统管理员', '系统管理员', '2017-06-07 11:37:41', '2017-06-20 11:37:44');
INSERT INTO `user_role` VALUES ('2', '市场', '市场', '2017-06-14 11:37:47', '2017-06-20 11:37:50');
INSERT INTO `user_role` VALUES ('3', '运营', '运营', '2017-06-28 11:37:53', '2017-06-20 11:37:59');
INSERT INTO `user_role` VALUES ('4', '运营策划', '运营策划', '2017-06-06 11:38:02', '2017-06-13 11:38:05');
INSERT INTO `user_role` VALUES ('5', '财务', '', '2017-06-21 11:38:08', '2017-06-13 11:38:12');
INSERT INTO `user_role` VALUES ('6', '技术', '技术维护', '2017-06-14 11:38:21', '2017-06-14 11:38:25');
INSERT INTO `user_role` VALUES ('7', '测试', '测试', '2017-06-21 11:38:28', '2017-06-22 11:38:31');

-- ----------------------------
-- Table structure for user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_role_relation`;
CREATE TABLE `user_role_relation` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int(11) NOT NULL COMMENT '角色Id',
  `user_id` int(11) NOT NULL COMMENT '员工Id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='员工角色关联表';

-- ----------------------------
-- Records of user_role_relation
-- ----------------------------
INSERT INTO `user_role_relation` VALUES ('1', '1', '1', '2016-12-13 10:14:06', '2017-03-27 10:13:45');
INSERT INTO `user_role_relation` VALUES ('10', '2', '1', '2016-11-10 15:20:54', '2017-03-27 12:05:10');
INSERT INTO `user_role_relation` VALUES ('37', '6', '43', '2016-12-12 17:21:04', '2017-06-22 11:39:46');
INSERT INTO `user_role_relation` VALUES ('54', '2', '5', '2017-01-11 11:55:15', '2017-03-16 21:58:03');
INSERT INTO `user_role_relation` VALUES ('59', '6', '46', '2017-01-17 15:22:38', '2017-03-14 18:06:45');
INSERT INTO `user_role_relation` VALUES ('60', '7', '9', '2017-01-17 15:22:38', '2017-03-23 16:19:38');
INSERT INTO `user_role_relation` VALUES ('62', '2', '9', '2017-02-20 10:44:31', '2017-03-23 16:19:37');
INSERT INTO `user_role_relation` VALUES ('63', '3', '9', '2017-02-20 10:44:31', '2017-03-23 16:19:39');
INSERT INTO `user_role_relation` VALUES ('64', '4', '9', '2017-02-20 10:44:31', '2017-03-23 16:19:42');
INSERT INTO `user_role_relation` VALUES ('66', '5', '6', '2017-02-20 10:44:31', '2017-03-16 21:48:51');

-- ----------------------------
-- Table structure for user_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `user_role_resource`;
CREATE TABLE `user_role_resource` (
  `role_resource_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `resource_id` int(11) NOT NULL COMMENT '资源Id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色菜单关联表';

-- ----------------------------
-- Records of user_role_resource
-- ----------------------------
INSERT INTO `user_role_resource` VALUES ('1', '1', '2', '2017-03-10 11:15:35', '2017-03-27 11:48:16');
INSERT INTO `user_role_resource` VALUES ('2', '1', '3', '2017-03-10 11:15:35', '2017-03-27 11:48:17');
INSERT INTO `user_role_resource` VALUES ('3', '1', '4', '2017-03-10 11:15:35', '2017-03-27 11:48:20');
INSERT INTO `user_role_resource` VALUES ('4', '1', '5', '2017-03-10 11:15:35', '2017-03-27 11:48:33');
INSERT INTO `user_role_resource` VALUES ('5', '1', '6', '2017-03-10 11:15:35', '2017-03-27 11:48:37');
