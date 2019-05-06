创建数据表
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for kbc_properties
-- ----------------------------
DROP TABLE IF EXISTS `t_properties`;
CREATE TABLE `t_properties` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `c_group_key` varchar(255) NOT NULL,
  `c_key_name` varchar(255) NOT NULL,
  `c_key_value` varchar(255) NOT NULL,
  `c_create_date` datetime NOT NULL,
  `c_creator` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
