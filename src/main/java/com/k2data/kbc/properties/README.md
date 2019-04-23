# kbc-Properties
#只有增和查
groupkey需要中英文对应关系

# 使用说明
创建数据表
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for kbc_properties
-- ----------------------------
DROP TABLE IF EXISTS `kbc_properties`;
CREATE TABLE `kbc_properties` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `group_key` varchar(255) NOT NULL,
  `key_name` varchar(255) NOT NULL,
  `key_value` varchar(255) NOT NULL,
  `create_date` datetime NOT NULL,
  `creator` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
