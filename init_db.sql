DROP TABLE IF EXISTS `kbc_announce`;
CREATE TABLE `kbc_announce` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '物理主键',
  `content` varchar(255) NOT NULL COMMENT '通知正文内容',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `start_time` datetime NOT NULL COMMENT '通知展示启动时间',
  `end_time` datetime NOT NULL COMMENT '通知展示结束时间',
  `creator` varchar(255) NOT NULL COMMENT '创建者id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

LOCK TABLES `kbc_announce` WRITE;
INSERT INTO `kbc_announce` VALUES (1,'通知1','remark1','2019-01-01 00:00:00','2019-01-02 00:00:00','zhangsan','2019-01-01 00:00:00');
INSERT INTO `kbc_announce` VALUES (2,'通知2','remark2','2019-01-01 00:00:00','2019-01-02 00:00:00','zhangsan','2019-01-01 00:00:00');
INSERT INTO `kbc_announce` VALUES (3,'通知3','remark3','2019-01-01 00:00:00','2019-01-02 00:00:00','zhangsan','2019-01-01 00:00:00');
INSERT INTO `kbc_announce` VALUES (4,'通知4','remark4','2019-01-01 00:00:00','2019-01-02 00:00:00','zhangsan','2019-01-01 00:00:00');
UNLOCK TABLES;