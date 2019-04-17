# kbc-audit

#pom 校验依赖
aop依赖
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>

校验依赖
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>3.6</version>
        </dependency>

#注意切面拦截包配置:使用标配置的方法


# 使用说明
创建数据表

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for kbc_audit_log
-- ----------------------------
DROP TABLE IF EXISTS `kbc_audit_log`;
CREATE TABLE `kbc_audit_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '物理主键',
  `creator` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `class_method` text COLLATE utf8_bin,
  `ip` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '操作IP地址',
  `request_URL` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '请求URI',
  `request_method` varchar(5) COLLATE utf8_bin NOT NULL COMMENT '操作方式',
  `title` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_agent` varchar(300) COLLATE utf8_bin NOT NULL,
  `params` text COLLATE utf8_bin,
  `type_log` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '日志类型',
  PRIMARY KEY (`id`),
  KEY `sys_log_create_by` (`creator`),
  KEY `sys_log_request_uri` (`request_URL`),
  KEY `sys_log_create_date` (`create_date`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='日志表';``
