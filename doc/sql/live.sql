/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : live

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2017-12-29 18:32:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for shiro_authority_setting
-- ----------------------------
DROP TABLE IF EXISTS `shiro_authority_setting`;
CREATE TABLE `shiro_authority_setting` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `url` varchar(64) DEFAULT NULL COMMENT '链接地址',
  `permission_init` varchar(16) DEFAULT NULL COMMENT '需要具备的权限',
  `sort` tinyint(2) DEFAULT NULL COMMENT '排序',
  `status` tinyint(2) DEFAULT '0' COMMENT '状态(0:正常  1:删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) DEFAULT '0' COMMENT '用户ID',
  `file_name` varchar(64) NOT NULL COMMENT '文件名称',
  `file_size` int(11) NOT NULL COMMENT '大小',
  `file_key` varchar(64) DEFAULT NULL COMMENT '七牛KEY名',
  `file_suffix` varchar(14) NOT NULL COMMENT '后缀',
  `file_type` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1:图片 2:视频',
  `file_path` varchar(50) DEFAULT NULL COMMENT '路径',
  `media_id` varchar(50) DEFAULT NULL COMMENT '微信素材ID',
  `file_status` tinyint(2) DEFAULT NULL COMMENT '文件上传成功状态（成功：0 失败 ：-1）',
  `insert_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '文件插入时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='文件系统表';

-- ----------------------------
-- Table structure for sys_function
-- ----------------------------
DROP TABLE IF EXISTS `sys_function`;
CREATE TABLE `sys_function` (
  `id` varchar(32) NOT NULL,
  `level` tinyint(2) DEFAULT '0' COMMENT '菜单级别（一级，二级）',
  `name` varchar(32) NOT NULL COMMENT '菜单名称',
  `sort` tinyint(2) DEFAULT NULL COMMENT '菜单排序',
  `url` varchar(64) NOT NULL COMMENT '菜单路径',
  `icon` varchar(32) DEFAULT NULL COMMENT '大功能图标',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父级url的id',
  `insert_date` datetime DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(2) DEFAULT '0' COMMENT '菜单状态(0:展示 1:隐藏)',
  PRIMARY KEY (`id`),
  KEY `FK_brd7b3keorj8pmxcv8bpahnxp` (`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(32) NOT NULL COMMENT '表ID',
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色code',
  `role_name` varchar(32) NOT NULL COMMENT '角色名称',
  `status` int(2) DEFAULT '0' COMMENT '状态(0:正常 1:删除)',
  `rolete_mb` varchar(10) DEFAULT '' COMMENT '角色模板',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for sys_role_function
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_function`;
CREATE TABLE `sys_role_function` (
  `id` varchar(32) NOT NULL,
  `function_id` varchar(32) NOT NULL COMMENT '功能ID',
  `role_id` varchar(32) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`),
  KEY `FK_fvsillj2cxyk5thnuu625urab` (`function_id`) USING BTREE,
  KEY `FK_9dww3p4w8jwvlrgwhpitsbfif` (`role_id`) USING BTREE,
  CONSTRAINT `sys_role_function_ibfk_1` FOREIGN KEY (`function_id`) REFERENCES `sys_function` (`id`),
  CONSTRAINT `sys_role_function_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单角色表';

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`),
  KEY `FK_n2ucxeorvpjy7qhnmuem01kbx` (`role_id`) USING BTREE,
  KEY `FK_d4qb5xld2pfb0bkjx9iwtolda` (`user_id`) USING BTREE,
  CONSTRAINT `sys_role_user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色用户表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL,
  `username` varchar(50) NOT NULL COMMENT '用户名(登录名,如jack)',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `headimg` varchar(100) DEFAULT NULL COMMENT '头像',
  `nickname` varchar(32) DEFAULT NULL COMMENT '昵称',
  `name` varchar(32) DEFAULT NULL COMMENT '用户真实姓名',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(16) DEFAULT NULL COMMENT '手机',
  `login_time` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
  `logout_time` timestamp NULL DEFAULT NULL COMMENT '登出时间(手动登出,系统登出即session超时时间)',
  `insert_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `last_update` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '正常：0  手动注销：1  系统销毁：99',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台用户表';
