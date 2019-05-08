SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_user_usrgrp`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_usrgrp`;
CREATE TABLE `t_user_usrgrp` (
  `c_user_id` int(11) NOT NULL,
  `c_usrgrp_id` tinyint(4) NOT NULL,
  PRIMARY KEY (`c_user_id`,`c_usrgrp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_owner_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_owner_permission`;
CREATE TABLE `t_owner_permission` (
  `c_owner_id` int(11) NOT NULL,
  `c_resource_id` tinyint(4) NOT NULL,
  `c_operations` varchar(100) DEFAULT NULL,
  `c_effect_time` datetime DEFAULT NULL,
  `c_expire_time` datetime DEFAULT NULL,
  `c_disabled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`c_owner_id`,`c_resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_owner_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_owner_role`;
CREATE TABLE `t_owner_role` (
  `c_owner_id` tinyint(4) NOT NULL,
  `c_role_id` tinyint(4) NOT NULL,
  `c_effect_time` datetime DEFAULT NULL,
  `c_expire_time` datetime DEFAULT NULL,
  `c_disabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`c_owner_id`,`c_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_resource`
-- ----------------------------
DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource` (
  `c_id` tinyint(4) NOT NULL,
  `c_type_id` tinyint(4) NOT NULL,
  `c_name` varchar(300) NOT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_resource_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_resource_type`;
CREATE TABLE `t_resource_type` (
  `c_id` tinyint(4) NOT NULL,
  `c_name` varchar(100) NOT NULL,
  `c_operations` varchar(300) NOT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `c_id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `c_name` varchar(200) NOT NULL,
  `c_description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `c_role_id` tinyint(4) NOT NULL,
  `c_resource_id` tinyint(4) NOT NULL,
  `c_operations` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`c_role_id`,`c_resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_name` varchar(200) NOT NULL,
  `c_password` varchar(500) NOT NULL,
  `c_email` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_user_group`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_group`;
CREATE TABLE `t_user_group` (
  `c_id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `c_name` varchar(200) NOT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- init t_resource
-- ----------------------------
INSERT INTO `t_resource` VALUES ('1', '1', '测试菜单项1');
INSERT INTO `t_resource` VALUES ('2', '1', '测试菜单项2');
INSERT INTO `t_resource` VALUES ('3', '2', '测试资源项1');

-- ----------------------------
-- init t_resource_type
-- ----------------------------
INSERT INTO `t_resource_type` VALUES ('1', '测试菜单', 'VIEW,DELETE');
INSERT INTO `t_resource_type` VALUES ('2', '测试资源', 'VIEW');

-- ----------------------------
-- init t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin@test.com');

-- ----------------------------
-- init t_user_group
-- ----------------------------
INSERT INTO `t_user_group` VALUES ('1', '测试用户组');

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '测试角色', '');