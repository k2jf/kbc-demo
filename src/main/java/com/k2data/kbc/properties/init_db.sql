创建数据表
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_properties
-- ----------------------------
DROP TABLE IF EXISTS `t_properties`;
CREATE TABLE `t_properties` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `c_group_key` varchar(255) NOT NULL,
  `c_key` varchar(255) NOT NULL,
  `c_value` varchar(255) NOT NULL,
  `c_create_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
