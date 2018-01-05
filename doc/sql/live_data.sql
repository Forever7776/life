/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : live

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2018-01-03 17:32:03
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
-- Records of shiro_authority_setting
-- ----------------------------
INSERT INTO `shiro_authority_setting` VALUES ('1', '/', 'anon', '1', '0');
INSERT INTO `shiro_authority_setting` VALUES ('2', '/login', 'anon', '2', '0');
INSERT INTO `shiro_authority_setting` VALUES ('3', '/logout ', 'anon', '3', '0');
INSERT INTO `shiro_authority_setting` VALUES ('4', '/js/**', 'anon', '4', '0');
INSERT INTO `shiro_authority_setting` VALUES ('5', '/css/**', 'anon', '5', '0');
INSERT INTO `shiro_authority_setting` VALUES ('6', '/images/**', 'anon', '6', '0');
INSERT INTO `shiro_authority_setting` VALUES ('7', '/shiro-cas', 'anon', '7', '0');
INSERT INTO `shiro_authority_setting` VALUES ('8', '/record', 'anon', '8', '0');
INSERT INTO `shiro_authority_setting` VALUES ('9', '/community', 'anon', '9', '0');
INSERT INTO `shiro_authority_setting` VALUES ('10', '/blog', 'anon', '10', '0');
INSERT INTO `shiro_authority_setting` VALUES ('11', '/tool', 'anon', '10', '0');
INSERT INTO `shiro_authority_setting` VALUES ('12', '/about', 'anon', '11', '0');
INSERT INTO `shiro_authority_setting` VALUES ('13', '/**', 'roles[ROLE_USER]', '99', '0');

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
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES ('3', null, 'backimage.png', '1546374', '20171127140551_backimage.png', 'png', '0', null, null, '0', '2017-11-27 14:05:53');
INSERT INTO `sys_file` VALUES ('4', null, 'timg.jpg', '17736', '20171128090454_timg.jpg', 'jpg', '0', null, null, '0', '2017-11-28 09:04:55');
INSERT INTO `sys_file` VALUES ('5', null, 'idea快捷键.jpg', '296837', '20171128151203_idea快捷键.jpg', 'jpg', '0', null, null, '0', '2017-11-28 15:12:04');
INSERT INTO `sys_file` VALUES ('6', null, 'backimage.png', '1546374', '2017-12-11 16:29:16_backimage.png', 'png', '0', null, null, '0', '2017-12-11 16:29:18');
INSERT INTO `sys_file` VALUES ('7', '0', 'x4.png', '62030', '20171228105620_x4.png', 'png', '0', null, null, '0', '2017-12-28 10:56:21');

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
-- Records of sys_function
-- ----------------------------
INSERT INTO `sys_function` VALUES ('ea8e4925d39a4576929b0c2a5d59d43b', '0', '上传文件', '1', '/up', null, null, '2017-12-21 10:55:04', '0');

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
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('37f5f3764caa489aab2564e4f3499d89', 'member', '正式会员', '0', '');

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
-- Records of sys_role_function
-- ----------------------------
INSERT INTO `sys_role_function` VALUES ('b37ad9d5e8c442599765726a66e9dba4', 'ea8e4925d39a4576929b0c2a5d59d43b', '37f5f3764caa489aab2564e4f3499d89');

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
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('df72c8a62d9b4cf680f888a30d4b5c2e', '37f5f3764caa489aab2564e4f3499d89', '9b22227ffea6486aabf56d5614d06380');

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

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('9b22227ffea6486aabf56d5614d06380', 'kezhen', 'e10adc3949ba59abbe56e057f20f883e', 'http://ozygzlhwd.bkt.clouddn.com/my/love/IMG_2720.jpg?imageslim', '冰峰雪座', '柯真', '305389431@qq.com', '18671310745', null, null, '2017-12-20 12:32:08', '2018-01-03 11:23:53', '0');
