/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Version : 50712
 Source Host           : localhost
 Source Database       : bank_sys

 Target Server Version : 50712
 File Encoding         : utf-8

 Date: 10/22/2016 12:52:47 PM
*/
DROP DATABASE IF EXISTS `bank_sys`;
CREATE DATABASE IF NOT EXISTS `bank_sys` DEFAULT CHARSET UTF8 COLLATE UTF8_GENERAL_CI;
USE `bank_sys`;

SET NAMES UTF8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `log`
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  `ip` varchar(100) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `map_role_privilege`
-- ----------------------------
DROP TABLE IF EXISTS `map_role_privilege`;
CREATE TABLE `map_role_privilege` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `privilege_id` int(11) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `map_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `map_user_role`;
CREATE TABLE `map_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `privilege`
-- ----------------------------
DROP TABLE IF EXISTS `privilege`;
CREATE TABLE `privilege` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `display_name` varchar(50) NOT NULL,
  `privilege_name` varchar(50) NOT NULL,
  `privilege_code` varchar(50) NOT NULL,
  `url` varchar(500) NOT NULL,
  `parent_id` int(11) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `display_name` varchar(50) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  `role_code` varchar(50) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `gender` int(1) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `password` varchar(100) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `organization`
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `organization_name` text NOT NULL,
  `organization_code` varchar(300) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_num` varchar(100) NOT NULL,
  `open_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `opener` int(11) NOT NULL,
  `card_type` int(11) NOT NULL,
  `bank_book` varchar(100) NOT NULL,
  `account_type` int(11) NOT NULL,
  `card_mark` int(11) NOT NULL,
  `pay_passsword` varchar(100) NOT NULL,
  `account_status` int(11) NOT NULL,
  `depositor_id` int(11) NOT NULL,
  `remain_money` decimal(14,4) NOT NULL DEFAULT '0.0000',
  `interest_start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `interest_stop_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `rate_id` int(11) NOT NULL,
  `constant_code` varchar(100) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `depositor`;
CREATE TABLE `depositor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `depositor_name` varchar(100) NOT NULL,
  `card_num` varchar(100) NOT NULL,
  `depositor_code` varchar(100) NOT NULL,
  `gender` int(1) NOT NULL,
  `birthday` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `join_time` varchar(100) ,
  `political_status` int(5) NOT NULL,
  `duty` int(5),
  `work_time` int(11),
  `credit_level` int(10) NOT NULL,
  `telephone` varchar(50) NOT NULL,
  `mobile_phone` varchar(50) NOT NULL,
  `introducer` int(11) NOT NULL,
  `address` varchar(500) NOT NULL,
  `image` varchar(100),
  `org_id` int(11) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Function structure for `getChild`
-- ----------------------------
DROP FUNCTION IF EXISTS `getChild`;
delimiter ;;
CREATE FUNCTION `getChild`(parentId INT) RETURNS varchar(1000) CHARSET UTF8
BEGIN 
    DECLARE sTemp VARCHAR(1000); 
    DECLARE sTempChd VARCHAR(1000); 
    
    SET sTemp = '$'; 
    SET sTempChd =CAST(parentId AS CHAR); 
     
    WHILE sTempChd IS NOT NULL DO 
      SET sTemp = CONCAT(sTemp,',',sTempChd); 
      SELECT GROUP_CONCAT(id) INTO sTempChd FROM privilege WHERE FIND_IN_SET(parent_id,sTempChd)>0 AND is_deleted=0; 
    END WHILE; 
    RETURN sTemp; 
 END
 ;;
delimiter ;

-- ----------------------------
--  Function structure for `getParent`
-- ----------------------------
DROP FUNCTION IF EXISTS `getParent`;
delimiter ;;
CREATE FUNCTION `getParent`(childId INT) RETURNS varchar(1000) CHARSET UTF8
BEGIN 
    DECLARE sTemp VARCHAR(1000); 
    DECLARE sTempChd VARCHAR(1000); 
    
    SET sTemp = '$'; 
    SET sTempChd =CAST(childId AS CHAR); 
     
    WHILE sTempChd IS NOT NULL DO 
      SET sTemp = CONCAT(sTemp,',',sTempChd); 
      SELECT GROUP_CONCAT(parent_id) INTO sTempChd FROM privilege WHERE FIND_IN_SET(id,sTempChd)>0 AND is_deleted=0; 
    END WHILE; 
    RETURN sTemp; 
 END
 ;;
delimiter ;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'admin', '0', 'admin@dev.com', '18236916020', 'c3284d0f94606de1fd2af172aba15bf3', '0', '2016-10-16 20:44:51', '2016-10-16 20:44:51');
COMMIT;

-- ----------------------------
--  Records of `role`
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES ('1', '系统管理', 'SystemManage', 'JS1001', '0', '2016-10-31 21:14:17', '2016-10-31 21:14:17');
COMMIT;

-- ----------------------------
--  Records of `privilege`
-- ----------------------------
BEGIN;
INSERT INTO `privilege` VALUES ('1', '平台权限', 'SystemPrivilege', '0000', '#', '0', '1', '2016-10-23 20:04:33', '2016-10-25 22:06:00');
INSERT INTO `privilege` VALUES ('2', '系统管理', 'systemManage', '1000', '#', '1', '0', '2016-10-22 20:01:54', '2016-10-30 00:15:11');
INSERT INTO `privilege` VALUES ('3', '用户管理', 'UserManage', '1006', '/page/user/search', '2', '0', '2016-11-01 00:33:31', '2016-11-01 00:33:31');
INSERT INTO `privilege` VALUES ('4', '角色管理', 'RoleManage', '1001', '/page/role/search', '2', '0', '2016-10-23 19:31:20', '2016-10-23 19:31:24');
INSERT INTO `privilege` VALUES ('5', '权限管理', 'PrivilegeManage', '1002', '/page/privilege/search', '2', '0', '2016-10-23 19:32:42', '2016-10-23 19:32:42');
INSERT INTO `privilege` VALUES ('6', '数据库备份', 'DataBackup', '1003', '/page/backup/database', '2', '0', '2016-10-23 19:33:26', '2016-10-23 19:33:26');
INSERT INTO `privilege` VALUES ('7', '登录日志', 'LogManage', '1004', '/page/log/search', '2', '0', '2016-10-23 19:34:36', '2016-10-23 19:34:36');
INSERT INTO `privilege` VALUES ('8', '退出系统', 'ExitSystem', '1005', '#', '2', '0', '2016-10-23 19:35:24', '2016-10-23 19:35:24');
INSERT INTO `privilege` VALUES ('9', '平台用户管理', 'PlatformUserManage', '2000', '#', '1', '0', '2016-11-13 19:35:24', '2016-11-13 19:35:24');
INSERT INTO `privilege` VALUES ('10', '社员管理', 'DepositorManage', '2001', '#', '9', '0', '2016-11-13 19:35:24', '2016-11-13 19:35:24');
INSERT INTO `privilege` VALUES ('11', '帐户管理', 'AccountManage', '2002', '#', '9', '0', '2016-11-13 19:35:24', '2016-11-13 19:35:24');
INSERT INTO `privilege` VALUES ('12', '组织管理', 'OrganizationManage', '2003', '/page/org/search', '9', '0', '2016-11-13 19:35:24', '2016-11-13 19:35:24');
INSERT INTO `privilege` VALUES ('13', '账户管理', 'AccountManage', '2004', '/page/account/search', '9', '0', '2016-11-13 19:35:24', '2016-11-13 19:35:24');
COMMIT;

-- ----------------------------
--  Records of `map_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `map_user_role` VALUES ('1', '1', '1', '0', '2016-11-02 21:59:35', '2016-11-02 21:59:38');
COMMIT;

-- ----------------------------
--  Records of `map_role_privilege`
-- ----------------------------
BEGIN;
INSERT INTO `map_role_privilege` VALUES ('1', '1', '2', '0', '2016-10-31 21:14:24', '2016-10-31 21:14:24');
INSERT INTO `map_role_privilege` VALUES ('2', '1', '3', '0', '2016-10-31 21:14:24', '2016-10-31 21:14:24');
INSERT INTO `map_role_privilege` VALUES ('3', '1', '4', '0', '2016-10-31 21:14:24', '2016-10-31 21:14:24');
INSERT INTO `map_role_privilege` VALUES ('4', '1', '5', '0', '2016-10-31 21:14:24', '2016-10-31 21:14:24');
INSERT INTO `map_role_privilege` VALUES ('5', '1', '6', '0', '2016-10-31 21:14:24', '2016-10-31 21:14:24');
INSERT INTO `map_role_privilege` VALUES ('6', '1', '7', '0', '2016-10-31 21:14:24', '2016-10-31 21:14:24');
INSERT INTO `map_role_privilege` VALUES ('7', '1', '8', '0', '2016-11-01 00:34:04', '2016-11-01 00:34:04');

INSERT INTO `map_role_privilege` VALUES ('8', '1', '9', '0', '2016-11-13 00:34:04', '2016-11-13 00:34:04');
INSERT INTO `map_role_privilege` VALUES ('9', '1', '10', '0', '2016-11-13 00:34:04', '2016-11-13 00:34:04');
INSERT INTO `map_role_privilege` VALUES ('10', '1', '11', '0', '2016-11-13 00:34:04', '2016-11-13 00:34:04');
INSERT INTO `map_role_privilege` VALUES ('11', '1', '12', '0', '2016-11-13 00:34:04', '2016-11-13 00:34:04');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
