/*
Navicat MySQL Data Transfer

Source Server         : 10.1.10.76
Source Server Version : 50724
Source Host           : 10.1.10.76:3306
Source Database       : kbc

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-05-05 16:09:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_tag_name` varchar(255) DEFAULT NULL,
  `c_num` int(11) DEFAULT '0',
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
