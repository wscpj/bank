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
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0',
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
  `is_deleted` tinyint(1) NOT NULL,
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
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'admin', '0', 'admin@dev.com', '18236916020', 'c3284d0f94606de1fd2af172aba15bf3', '0', '2016-10-16 20:44:51', '2016-10-16 20:44:51');
COMMIT;

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

SET FOREIGN_KEY_CHECKS = 1;
