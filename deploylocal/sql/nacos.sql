/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.36 : Database - nacos
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`nacos` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `nacos`;

/*Table structure for table `config_info` */

DROP TABLE IF EXISTS `config_info`;

CREATE TABLE `config_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ä¿®æ”¹æ—¶é—´',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'ç§Ÿæˆ·å­—æ®µ',
  `c_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `c_use` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `effect` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `c_schema` text COLLATE utf8_bin,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';

/*Data for the table `config_info` */

insert  into `config_info`(`id`,`data_id`,`group_id`,`content`,`md5`,`gmt_create`,`gmt_modified`,`src_user`,`src_ip`,`app_name`,`tenant_id`,`c_desc`,`c_use`,`effect`,`type`,`c_schema`) values (1,'route.yaml','gateway','spring:\n  cloud:\n    gateway:\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/auth-service/**\n          filters:\n            - RewritePath=/auth-service/(?<segment>.*), /$\\{segment}\n        - id: admin-service\n          uri: lb://admin-service\n          predicates:\n            - Path=/admin-service/**\n          filters:\n            - RewritePath=/admin-service/(?<segment>.*), /$\\{segment}\n        - id: judge-service\n          uri: lb://judge-service\n          predicates:\n            - Path=/judge-service/**\n          filters:\n            - RewritePath=/judge-service/(?<segment>.*), /$\\{segment}\n        - id: system-service\n          uri: lb://system-service\n          predicates:\n            - Path=/system-service/**\n          filters:\n            - RewritePath=/system-service/(?<segment>.*), /$\\{segment}','e6073fd04846b1d9a01af67e67a5e4da','2023-07-24 07:39:21','2023-08-10 03:29:11','nacos','59.61.28.208','','','','','','yaml',''),(2,'mysql.yaml','env','spring:\n  datasource:\n    url: jdbc:mysql://172.16.0.11:3306/${datasource.name}?useSSL=false&useUnicode=true&characterEncoding=utf8\n    username: root\n    password: zhang147852\n    driver-class-name: com.mysql.cj.jdbc.Driver','a6c6737f7c714c8bb7843ff90d61cdd9','2023-07-24 07:39:59','2023-08-05 07:41:06','nacos','112.51.40.147','','','','','','yaml',''),(3,'nacos.yaml','env','spring:\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 192.168.56.10:8848\n        username: nacos\n        password: zhang147852','4e7ab7cb6ea64669f98368d0b2724c86','2023-07-24 07:40:19','2023-09-06 02:39:47','nacos','192.168.56.1','','','','','','yaml',''),(4,'redis.yaml','env','spring:\n  redis:\n    host: 172.16.0.15\n    port: 6379\n    password: zhang147852','106af5c39576c7903081ec7f24ab7f43','2023-07-24 07:45:48','2023-08-04 09:06:14','nacos','59.61.29.183','','','','','','yaml',''),(6,'judge.yaml','env','a','0cc175b9c0f1b6a831c399e269772661','2023-07-24 07:48:59','2023-07-24 07:48:59','nacos','59.61.28.193','','',NULL,NULL,NULL,'yaml',NULL),(24,'CQ.properties','DEFAULT_GROUP','botQQ=2745416965\nbotURL=http://192.168.0.1:9001/\n','d3357c5f98c6de5957ccf8f7ae1e0e31','2023-07-26 12:39:39','2023-08-04 06:35:39','nacos','59.61.26.25','','a11739b4-e67b-4f9a-bd0d-52482636053b','','','','properties',''),(30,'prefix.properties','DEFAULT_GROUP','prefix=懒惰者,懒惰师,懒惰大师,懒惰王,懒惰皇,懒惰半圣,懒惰圣人,懒惰帝,普通成员','048661d92e43e2ab615490be79fe9884','2023-07-26 13:00:16','2023-07-29 15:47:01','nacos','218.207.190.132','','a11739b4-e67b-4f9a-bd0d-52482636053b','','','','properties',''),(56,'route.yaml','gateway','spring:\n  cloud:\n    gateway:\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/auth-service/**\n          filters:\n            - RewritePath=/auth-service/(?<segment>.*), /$\\{segment}\n        - id: system-service\n          uri: lb://system-service\n          predicates:\n            - Path=/system-service/**\n          filters:\n            - RewritePath=/system-service/(?<segment>.*), /$\\{segment}\n        - id: judge-service\n          uri: lb://judge-service\n          predicates:\n            - Path=/judge-service/**\n          filters:\n            - RewritePath=/judge-service/(?<segment>.*), /$\\{segment}','fc8a4c31fdda0f7e9c61bf537d294e3e','2023-07-29 12:26:39','2023-07-30 08:57:23','nacos','218.207.190.132','','46b2694e-349a-47de-9ed7-bf59d17a0793','','','','yaml',''),(57,'mysql.yaml','env','spring:\n  datasource:\n    url: jdbc:mysql://192.168.56.10:3306/${datasource.name}?useSSL=false\n    username: root\n    password: root\n    driver-class-name: com.mysql.cj.jdbc.Driver','6a8695150674e7a188c5e4466f225f15','2023-07-29 12:26:39','2023-07-29 12:32:38','nacos','218.207.190.132','','46b2694e-349a-47de-9ed7-bf59d17a0793','','','','yaml',''),(58,'nacos.yaml','env','spring:\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 222.187.223.125:18848\n        username: nacos\n        password: zhang147852\n        namespace: 46b2694e-349a-47de-9ed7-bf59d17a0793','bac5b2dfe57abe3ddb3d934940d6eb32','2023-07-29 12:26:39','2023-07-29 12:37:23','nacos','218.207.190.132','','46b2694e-349a-47de-9ed7-bf59d17a0793','','','','yaml',''),(59,'redis.yaml','env','spring:\n  redis:\n    host: 192.168.56.10\n    port: 6379','740dfeffc0be54e849b4e2e23952505c','2023-07-29 12:26:39','2023-07-29 12:33:09','nacos','218.207.190.132','','46b2694e-349a-47de-9ed7-bf59d17a0793','','','','yaml',''),(60,'judge.yaml','env','a','0cc175b9c0f1b6a831c399e269772661','2023-07-29 12:26:39','2023-07-29 12:26:39',NULL,'218.207.190.132','','46b2694e-349a-47de-9ed7-bf59d17a0793',NULL,NULL,NULL,'yaml',NULL),(61,'auth-service.properties','DEFAULT_GROUP','md5.salt=zhangsiyaodashuaibi','01c150f92468e78778f56229dfee5b62','2023-07-29 12:26:39','2023-07-29 12:26:39',NULL,'218.207.190.132','auth-service','46b2694e-349a-47de-9ed7-bf59d17a0793','',NULL,NULL,'properties',NULL),(93,'route.yaml','gateway','spring:\n  cloud:\n    gateway:\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/auth-service/**\n          filters:\n            - RewritePath=/auth-service/(?<segment>.*), /$\\{segment}\n        - id: admin-service\n          uri: lb://admin-service\n          predicates:\n            - Path=/admin-service/**\n          filters:\n            - RewritePath=/admin-service/(?<segment>.*), /$\\{segment}\n        - id: judge-service\n          uri: lb://judge-service\n          predicates:\n            - Path=/judge-service/**\n          filters:\n            - RewritePath=/judge-service/(?<segment>.*), /$\\{segment}\n        - id: system-service\n          uri: lb://system-service\n          predicates:\n            - Path=/system-service/**\n          filters:\n            - RewritePath=/system-service/(?<segment>.*), /$\\{segment}','e6073fd04846b1d9a01af67e67a5e4da','2023-09-05 13:54:20','2023-09-05 13:54:20',NULL,'192.168.56.1','','64251e42-0d1e-4aeb-9408-efe3fd300e28','',NULL,NULL,'yaml',NULL),(94,'mysql.yaml','env','spring:\n  datasource:\n    url: jdbc:mysql://192.168.56.10:3306/${datasource.name}?useSSL=false&useUnicode=true&characterEncoding=utf8\n    username: root\n    password: root\n    driver-class-name: com.mysql.cj.jdbc.Driver','e0f3e1f5d220b9400d66b2b017cddb63','2023-09-05 13:54:20','2023-09-05 13:54:52','nacos','192.168.56.1','','64251e42-0d1e-4aeb-9408-efe3fd300e28','','','','yaml',''),(95,'nacos.yaml','env','spring:\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 192.168.56.10:8848\n        username: nacos\n        password: zhang147852','4e7ab7cb6ea64669f98368d0b2724c86','2023-09-05 13:54:20','2023-09-06 00:33:27','nacos','192.168.56.1','','64251e42-0d1e-4aeb-9408-efe3fd300e28','','','','yaml',''),(96,'redis.yaml','env','spring:\n  redis:\n    host: 192.168.56.10\n    port: 6379','740dfeffc0be54e849b4e2e23952505c','2023-09-05 13:54:20','2023-09-06 00:33:53','nacos','192.168.56.1','','64251e42-0d1e-4aeb-9408-efe3fd300e28','','','','yaml',''),(97,'judge.yaml','env','a','0cc175b9c0f1b6a831c399e269772661','2023-09-05 13:54:20','2023-09-05 13:54:20',NULL,'192.168.56.1','','64251e42-0d1e-4aeb-9408-efe3fd300e28',NULL,NULL,NULL,'yaml',NULL),(100,'route.yaml','gateway','spring:\n  cloud:\n    gateway:\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/auth-service/**\n          filters:\n            - RewritePath=/auth-service/(?<segment>.*), /$\\{segment}\n        - id: admin-service\n          uri: lb://admin-service\n          predicates:\n            - Path=/admin-service/**\n          filters:\n            - RewritePath=/admin-service/(?<segment>.*), /$\\{segment}\n        - id: judge-service\n          uri: lb://judge-service\n          predicates:\n            - Path=/judge-service/**\n          filters:\n            - RewritePath=/judge-service/(?<segment>.*), /$\\{segment}\n        - id: system-service\n          uri: lb://system-service\n          predicates:\n            - Path=/system-service/**\n          filters:\n            - RewritePath=/system-service/(?<segment>.*), /$\\{segment}','e6073fd04846b1d9a01af67e67a5e4da','2023-09-06 01:55:15','2023-09-06 01:55:15',NULL,'192.168.56.1','','dev','',NULL,NULL,'yaml',NULL),(102,'nacos.yaml','env','spring:\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 192.168.56.10:8848\n        username: nacos\n        password: zhang147852','4e7ab7cb6ea64669f98368d0b2724c86','2023-09-06 01:55:15','2023-09-06 01:55:15',NULL,'192.168.56.1','','dev','',NULL,NULL,'yaml',NULL),(103,'redis.yaml','env','spring:\n  redis:\n    host: 192.168.56.10\n    port: 6379','740dfeffc0be54e849b4e2e23952505c','2023-09-06 01:55:15','2023-09-06 01:55:15',NULL,'192.168.56.1','','dev','',NULL,NULL,'yaml',NULL),(104,'judge.yaml','env','a','0cc175b9c0f1b6a831c399e269772661','2023-09-06 01:55:15','2023-09-06 01:55:15',NULL,'192.168.56.1','','dev',NULL,NULL,NULL,'yaml',NULL),(107,'mysql.yaml','env','spring:\n  datasource:\n    url: jdbc:mysql://192.168.56.10:3306/${datasource.name}?useSSL=false&useUnicode=true&characterEncoding=utf8\n    username: root\n    password: root\n    driver-class-name: com.mysql.cj.jdbc.Driver','e0f3e1f5d220b9400d66b2b017cddb63','2023-09-06 02:48:00','2023-09-06 02:48:00',NULL,'192.168.56.1','','dev','',NULL,NULL,'yaml',NULL);

/*Table structure for table `config_info_aggr` */

DROP TABLE IF EXISTS `config_info_aggr`;

CREATE TABLE `config_info_aggr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'å†…å®¹',
  `gmt_modified` datetime NOT NULL COMMENT 'ä¿®æ”¹æ—¶é—´',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'ç§Ÿæˆ·å­—æ®µ',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='å¢žåŠ ç§Ÿæˆ·å­—æ®µ';

/*Data for the table `config_info_aggr` */

/*Table structure for table `config_info_beta` */

DROP TABLE IF EXISTS `config_info_beta`;

CREATE TABLE `config_info_beta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ä¿®æ”¹æ—¶é—´',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'ç§Ÿæˆ·å­—æ®µ',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_beta';

/*Data for the table `config_info_beta` */

/*Table structure for table `config_info_tag` */

DROP TABLE IF EXISTS `config_info_tag`;

CREATE TABLE `config_info_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ä¿®æ”¹æ—¶é—´',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_tag';

/*Data for the table `config_info_tag` */

/*Table structure for table `config_tags_relation` */

DROP TABLE IF EXISTS `config_tags_relation`;

CREATE TABLE `config_tags_relation` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';

/*Data for the table `config_tags_relation` */

/*Table structure for table `group_capacity` */

DROP TABLE IF EXISTS `group_capacity`;

CREATE TABLE `group_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ä¸»é”®ID',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group IDï¼Œç©ºå­—ç¬¦è¡¨ç¤ºæ•´ä¸ªé›†ç¾¤',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'é…é¢ï¼Œ0è¡¨ç¤ºä½¿ç”¨é»˜è®¤å€¼',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'ä½¿ç”¨é‡',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'å•ä¸ªé…ç½®å¤§å°ä¸Šé™ï¼Œå•ä½ä¸ºå­—èŠ‚ï¼Œ0è¡¨ç¤ºä½¿ç”¨é»˜è®¤å€¼',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'èšåˆå­é…ç½®æœ€å¤§ä¸ªæ•°ï¼Œï¼Œ0è¡¨ç¤ºä½¿ç”¨é»˜è®¤å€¼',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'å•ä¸ªèšåˆæ•°æ®çš„å­é…ç½®å¤§å°ä¸Šé™ï¼Œå•ä½ä¸ºå­—èŠ‚ï¼Œ0è¡¨ç¤ºä½¿ç”¨é»˜è®¤å€¼',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'æœ€å¤§å˜æ›´åŽ†å²æ•°é‡',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ä¿®æ”¹æ—¶é—´',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='é›†ç¾¤ã€å„Groupå®¹é‡ä¿¡æ¯è¡¨';

/*Data for the table `group_capacity` */

/*Table structure for table `his_config_info` */

DROP TABLE IF EXISTS `his_config_info`;

CREATE TABLE `his_config_info` (
  `id` bigint(64) unsigned NOT NULL,
  `nid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text COLLATE utf8_bin,
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `op_type` char(10) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'ç§Ÿæˆ·å­—æ®µ',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='å¤šç§Ÿæˆ·æ”¹é€ ';

/*Data for the table `his_config_info` */

insert  into `his_config_info`(`id`,`nid`,`data_id`,`group_id`,`app_name`,`content`,`md5`,`gmt_create`,`gmt_modified`,`src_user`,`src_ip`,`op_type`,`tenant_id`) values (0,99,'judge-service.properties','DEFAULT_GROUP','','spring.','f3919be35f63f6381eef7f9ff95c0da6','2023-08-10 01:42:50','2023-08-10 01:42:51','nacos','59.61.28.208','I',''),(92,100,'judge-service.properties','DEFAULT_GROUP','','spring.','f3919be35f63f6381eef7f9ff95c0da6','2023-08-10 01:43:35','2023-08-10 01:43:35','nacos','59.61.28.208','U',''),(1,101,'route.yaml','gateway','','spring:\n  cloud:\n    gateway:\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/auth-service/**\n          filters:\n            - RewritePath=/auth-service/(?<segment>.*), /$\\{segment}\n        - id: admin-service\n          uri: lb://admin-service\n          predicates:\n            - Path=/admin-service/**\n          filters:\n            - RewritePath=/admin-service/(?<segment>.*), /$\\{segment}\n        - id: judge-service\n          uri: lb://judge-service\n          predicates:\n            - Path=/judge-service/**\n          filters:\n            - RewritePath=/judge-service/(?<segment>.*), /$\\{segment}\n        - id: system-service\n          uri: lb://system-service\n          predicates:\n            - Path=/system-service/**\n          filters:\n            - RewritePath=/system-service/(?<segment>.*), /$\\{segment}','e6073fd04846b1d9a01af67e67a5e4da','2023-08-10 03:29:11','2023-08-10 03:29:11','nacos','59.61.28.208','U',''),(0,102,'route.yaml','gateway','','spring:\n  cloud:\n    gateway:\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/auth-service/**\n          filters:\n            - RewritePath=/auth-service/(?<segment>.*), /$\\{segment}\n        - id: admin-service\n          uri: lb://admin-service\n          predicates:\n            - Path=/admin-service/**\n          filters:\n            - RewritePath=/admin-service/(?<segment>.*), /$\\{segment}\n        - id: judge-service\n          uri: lb://judge-service\n          predicates:\n            - Path=/judge-service/**\n          filters:\n            - RewritePath=/judge-service/(?<segment>.*), /$\\{segment}\n        - id: system-service\n          uri: lb://system-service\n          predicates:\n            - Path=/system-service/**\n          filters:\n            - RewritePath=/system-service/(?<segment>.*), /$\\{segment}','e6073fd04846b1d9a01af67e67a5e4da','2023-09-05 13:54:20','2023-09-05 13:54:20',NULL,'192.168.56.1','I','64251e42-0d1e-4aeb-9408-efe3fd300e28'),(0,103,'mysql.yaml','env','','spring:\n  datasource:\n    url: jdbc:mysql://172.16.0.11:3306/${datasource.name}?useSSL=false&useUnicode=true&characterEncoding=utf8\n    username: root\n    password: zhang147852\n    driver-class-name: com.mysql.cj.jdbc.Driver','a6c6737f7c714c8bb7843ff90d61cdd9','2023-09-05 13:54:20','2023-09-05 13:54:20',NULL,'192.168.56.1','I','64251e42-0d1e-4aeb-9408-efe3fd300e28'),(0,104,'nacos.yaml','env','','spring:\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 172.16.0.14:8848\n        username: nacos\n        password: zhang147852','528dc963c3ba8da99efaaa75b8edc068','2023-09-05 13:54:20','2023-09-05 13:54:20',NULL,'192.168.56.1','I','64251e42-0d1e-4aeb-9408-efe3fd300e28'),(0,105,'redis.yaml','env','','spring:\n  redis:\n    host: 172.16.0.15\n    port: 6379\n    password: zhang147852','106af5c39576c7903081ec7f24ab7f43','2023-09-05 13:54:20','2023-09-05 13:54:20',NULL,'192.168.56.1','I','64251e42-0d1e-4aeb-9408-efe3fd300e28'),(0,106,'judge.yaml','env','','a','0cc175b9c0f1b6a831c399e269772661','2023-09-05 13:54:20','2023-09-05 13:54:20',NULL,'192.168.56.1','I','64251e42-0d1e-4aeb-9408-efe3fd300e28'),(0,107,'auth-service.properties','DEFAULT_GROUP','auth-service','md5.salt=zhangsiyaodashuaibi\ndatasource.name=auth\nspring.cloud.nacos.discovery.ip=172.16.0.13','af491a8f029e78c47ce9c44dabbe803a','2023-09-05 13:54:20','2023-09-05 13:54:20',NULL,'192.168.56.1','I','64251e42-0d1e-4aeb-9408-efe3fd300e28'),(0,108,'gateway.properties','DEFAULT_GROUP','','spring.cloud.nacos.discovery.ip=172.16.0.8','5d07cb9393ded1c48256a871f8f8d444','2023-09-05 13:54:20','2023-09-05 13:54:20',NULL,'192.168.56.1','I','64251e42-0d1e-4aeb-9408-efe3fd300e28'),(0,109,'system-service.properties','DEFAULT_GROUP','','datasource.name=system\nspring.cloud.nacos.discovery.ip=172.16.0.12','08f06afbfab30736659770bc8994a256','2023-09-05 13:54:20','2023-09-05 13:54:20',NULL,'192.168.56.1','I','64251e42-0d1e-4aeb-9408-efe3fd300e28'),(0,110,'config.properties','env','','server.servlet.encoding.charset=UTF-8\r\nserver.servlet.encoding.enabled=true\r\nserver.servlet.encoding.force=true\r\nserver.servlet.encoding.force-request=true\r\nserver.servlet.encoding.force-response=true\r\nserver.tomcat.uri-encoding=UTF-8\r\nfeign.client.config.default.connectTimeout=5000\r\nfeign.client.config.default.readTimeout=30000','b93a266f17120edfe317adc5925b4a45','2023-09-05 13:54:20','2023-09-05 13:54:20',NULL,'192.168.56.1','I','64251e42-0d1e-4aeb-9408-efe3fd300e28'),(0,111,'judge-service.properties','DEFAULT_GROUP','','datasource.name=judgeServer\nspring.cloud.nacos.discovery.ip=172.16.0.48','a8b712b0f6696efd97d7b675bfb91f7a','2023-09-05 13:54:20','2023-09-05 13:54:20',NULL,'192.168.56.1','I','64251e42-0d1e-4aeb-9408-efe3fd300e28'),(94,112,'mysql.yaml','env','','spring:\n  datasource:\n    url: jdbc:mysql://172.16.0.11:3306/${datasource.name}?useSSL=false&useUnicode=true&characterEncoding=utf8\n    username: root\n    password: zhang147852\n    driver-class-name: com.mysql.cj.jdbc.Driver','a6c6737f7c714c8bb7843ff90d61cdd9','2023-09-05 13:54:51','2023-09-05 13:54:52','nacos','192.168.56.1','U','64251e42-0d1e-4aeb-9408-efe3fd300e28'),(98,113,'auth-service.properties','DEFAULT_GROUP','auth-service','md5.salt=zhangsiyaodashuaibi\ndatasource.name=auth\nspring.cloud.nacos.discovery.ip=172.16.0.13','af491a8f029e78c47ce9c44dabbe803a','2023-09-05 15:27:44','2023-09-05 15:27:45','nacos','192.168.56.1','D','64251e42-0d1e-4aeb-9408-efe3fd300e28'),(101,114,'config.properties','env','','server.servlet.encoding.charset=UTF-8\r\nserver.servlet.encoding.enabled=true\r\nserver.servlet.encoding.force=true\r\nserver.servlet.encoding.force-request=true\r\nserver.servlet.encoding.force-response=true\r\nserver.tomcat.uri-encoding=UTF-8\r\nfeign.client.config.default.connectTimeout=5000\r\nfeign.client.config.default.readTimeout=30000','b93a266f17120edfe317adc5925b4a45','2023-09-05 15:37:50','2023-09-05 15:37:51','nacos','192.168.56.1','D','64251e42-0d1e-4aeb-9408-efe3fd300e28'),(99,115,'gateway.properties','DEFAULT_GROUP','','spring.cloud.nacos.discovery.ip=172.16.0.8','5d07cb9393ded1c48256a871f8f8d444','2023-09-05 15:37:57','2023-09-05 15:37:57','nacos','192.168.56.1','D','64251e42-0d1e-4aeb-9408-efe3fd300e28'),(100,116,'system-service.properties','DEFAULT_GROUP','','datasource.name=system\nspring.cloud.nacos.discovery.ip=172.16.0.12','08f06afbfab30736659770bc8994a256','2023-09-05 15:38:00','2023-09-05 15:38:00','nacos','192.168.56.1','D','64251e42-0d1e-4aeb-9408-efe3fd300e28'),(102,117,'judge-service.properties','DEFAULT_GROUP','','datasource.name=judgeServer\nspring.cloud.nacos.discovery.ip=172.16.0.48','a8b712b0f6696efd97d7b675bfb91f7a','2023-09-05 15:38:02','2023-09-05 15:38:03','nacos','192.168.56.1','D','64251e42-0d1e-4aeb-9408-efe3fd300e28'),(95,118,'nacos.yaml','env','','spring:\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 172.16.0.14:8848\n        username: nacos\n        password: zhang147852','528dc963c3ba8da99efaaa75b8edc068','2023-09-06 00:33:27','2023-09-06 00:33:27','nacos','192.168.56.1','U','64251e42-0d1e-4aeb-9408-efe3fd300e28'),(96,119,'redis.yaml','env','','spring:\n  redis:\n    host: 172.16.0.15\n    port: 6379\n    password: zhang147852','106af5c39576c7903081ec7f24ab7f43','2023-09-06 00:33:52','2023-09-06 00:33:53','nacos','192.168.56.1','U','64251e42-0d1e-4aeb-9408-efe3fd300e28'),(0,120,'route.yaml','gateway','','spring:\n  cloud:\n    gateway:\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/auth-service/**\n          filters:\n            - RewritePath=/auth-service/(?<segment>.*), /$\\{segment}\n        - id: admin-service\n          uri: lb://admin-service\n          predicates:\n            - Path=/admin-service/**\n          filters:\n            - RewritePath=/admin-service/(?<segment>.*), /$\\{segment}\n        - id: judge-service\n          uri: lb://judge-service\n          predicates:\n            - Path=/judge-service/**\n          filters:\n            - RewritePath=/judge-service/(?<segment>.*), /$\\{segment}\n        - id: system-service\n          uri: lb://system-service\n          predicates:\n            - Path=/system-service/**\n          filters:\n            - RewritePath=/system-service/(?<segment>.*), /$\\{segment}','e6073fd04846b1d9a01af67e67a5e4da','2023-09-06 01:55:15','2023-09-06 01:55:15',NULL,'192.168.56.1','I','dev'),(0,121,'mysql.yaml','env','','spring:\n  datasource:\n    url: jdbc:mysql://192.168.56.10:3306/${datasource.name}?useSSL=false&useUnicode=true&characterEncoding=utf8\n    username: root\n    password: root\n    driver-class-name: com.mysql.cj.jdbc.Driver','e0f3e1f5d220b9400d66b2b017cddb63','2023-09-06 01:55:15','2023-09-06 01:55:15',NULL,'192.168.56.1','I','dev'),(0,122,'nacos.yaml','env','','spring:\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 192.168.56.10:8848\n        username: nacos\n        password: zhang147852','4e7ab7cb6ea64669f98368d0b2724c86','2023-09-06 01:55:15','2023-09-06 01:55:15',NULL,'192.168.56.1','I','dev'),(0,123,'redis.yaml','env','','spring:\n  redis:\n    host: 192.168.56.10\n    port: 6379','740dfeffc0be54e849b4e2e23952505c','2023-09-06 01:55:15','2023-09-06 01:55:15',NULL,'192.168.56.1','I','dev'),(0,124,'judge.yaml','env','','a','0cc175b9c0f1b6a831c399e269772661','2023-09-06 01:55:15','2023-09-06 01:55:15',NULL,'192.168.56.1','I','dev'),(101,125,'mysql.yaml','env','','spring:\n  datasource:\n    url: jdbc:mysql://192.168.56.10:3306/${datasource.name}?useSSL=false&useUnicode=true&characterEncoding=utf8\n    username: root\n    password: root\n    driver-class-name: com.mysql.cj.jdbc.Driver','e0f3e1f5d220b9400d66b2b017cddb63','2023-09-06 02:03:09','2023-09-06 02:03:10','nacos','192.168.56.1','D','dev'),(0,126,'mysql.yaml','env','','a','0cc175b9c0f1b6a831c399e269772661','2023-09-06 02:03:32','2023-09-06 02:03:33','nacos','192.168.56.1','I','dev'),(3,127,'nacos.yaml','env','','spring:\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 172.16.0.14:8848\n        username: nacos\n        password: zhang147852','528dc963c3ba8da99efaaa75b8edc068','2023-09-06 02:39:47','2023-09-06 02:39:47','nacos','192.168.56.1','U',''),(105,128,'mysql.yaml','env','','a','0cc175b9c0f1b6a831c399e269772661','2023-09-06 02:47:49','2023-09-06 02:47:50','nacos','192.168.56.1','D','dev'),(0,129,'mysql.yaml','env','','spring:\n  datasource:\n    url: jdbc:mysql://192.168.56.10:3306/${datasource.name}?useSSL=false&useUnicode=true&characterEncoding=utf8\n    username: root\n    password: root\n    driver-class-name: com.mysql.cj.jdbc.Driver','e0f3e1f5d220b9400d66b2b017cddb63','2023-09-06 02:47:59','2023-09-06 02:48:00',NULL,'192.168.56.1','I','dev'),(34,130,'auth-service.properties','DEFAULT_GROUP','auth-service','md5.salt=zhangsiyaodashuaibi\ndatasource.name=auth\nspring.cloud.nacos.discovery.ip=172.16.0.13','af491a8f029e78c47ce9c44dabbe803a','2023-09-06 03:48:57','2023-09-06 03:48:57','nacos','192.168.56.1','D',''),(71,131,'gateway.properties','DEFAULT_GROUP','','spring.cloud.nacos.discovery.ip=172.16.0.8','5d07cb9393ded1c48256a871f8f8d444','2023-09-06 03:49:00','2023-09-06 03:49:00','nacos','192.168.56.1','D',''),(76,132,'system-service.properties','DEFAULT_GROUP','','datasource.name=system\nspring.cloud.nacos.discovery.ip=172.16.0.12','08f06afbfab30736659770bc8994a256','2023-09-06 03:49:03','2023-09-06 03:49:03','nacos','192.168.56.1','D',''),(82,133,'config.properties','env','','server.servlet.encoding.charset=UTF-8\r\nserver.servlet.encoding.enabled=true\r\nserver.servlet.encoding.force=true\r\nserver.servlet.encoding.force-request=true\r\nserver.servlet.encoding.force-response=true\r\nserver.tomcat.uri-encoding=UTF-8\r\nfeign.client.config.default.connectTimeout=5000\r\nfeign.client.config.default.readTimeout=30000','b93a266f17120edfe317adc5925b4a45','2023-09-06 03:49:06','2023-09-06 03:49:06','nacos','192.168.56.1','D',''),(92,134,'judge-service.properties','DEFAULT_GROUP','','datasource.name=judgeServer\nspring.cloud.nacos.discovery.ip=172.16.0.48','a8b712b0f6696efd97d7b675bfb91f7a','2023-09-06 03:49:08','2023-09-06 03:49:09','nacos','192.168.56.1','D','');

/*Table structure for table `permissions` */

DROP TABLE IF EXISTS `permissions`;

CREATE TABLE `permissions` (
  `role` varchar(50) NOT NULL,
  `resource` varchar(255) NOT NULL,
  `action` varchar(8) NOT NULL,
  UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `permissions` */

insert  into `permissions`(`role`,`resource`,`action`) values ('GGBot','a11739b4-e67b-4f9a-bd0d-52482636053b:*:*','rw'),('LISTEN','64251e42-0d1e-4aeb-9408-efe3fd300e28:*:*','r'),('LISTEN',':*:*','r'),('LISTEN','dev:*:*','r');

/*Table structure for table `roles` */

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `username` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `idx_user_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `roles` */

insert  into `roles`(`username`,`role`) values ('ggbot','GGBot'),('listen','LISTEN'),('nacos','ROLE_ADMIN');

/*Table structure for table `tenant_capacity` */

DROP TABLE IF EXISTS `tenant_capacity`;

CREATE TABLE `tenant_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ä¸»é”®ID',
  `tenant_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'é…é¢ï¼Œ0è¡¨ç¤ºä½¿ç”¨é»˜è®¤å€¼',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'ä½¿ç”¨é‡',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'å•ä¸ªé…ç½®å¤§å°ä¸Šé™ï¼Œå•ä½ä¸ºå­—èŠ‚ï¼Œ0è¡¨ç¤ºä½¿ç”¨é»˜è®¤å€¼',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'èšåˆå­é…ç½®æœ€å¤§ä¸ªæ•°',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'å•ä¸ªèšåˆæ•°æ®çš„å­é…ç½®å¤§å°ä¸Šé™ï¼Œå•ä½ä¸ºå­—èŠ‚ï¼Œ0è¡¨ç¤ºä½¿ç”¨é»˜è®¤å€¼',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT 'æœ€å¤§å˜æ›´åŽ†å²æ•°é‡',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'åˆ›å»ºæ—¶é—´',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'ä¿®æ”¹æ—¶é—´',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='ç§Ÿæˆ·å®¹é‡ä¿¡æ¯è¡¨';

/*Data for the table `tenant_capacity` */

/*Table structure for table `tenant_info` */

DROP TABLE IF EXISTS `tenant_info`;

CREATE TABLE `tenant_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT 'åˆ›å»ºæ—¶é—´',
  `gmt_modified` bigint(20) NOT NULL COMMENT 'ä¿®æ”¹æ—¶é—´',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';

/*Data for the table `tenant_info` */

insert  into `tenant_info`(`id`,`kp`,`tenant_id`,`tenant_name`,`tenant_desc`,`create_source`,`gmt_create`,`gmt_modified`) values (3,'1','64251e42-0d1e-4aeb-9408-efe3fd300e28','prod','生产环境','nacos',1693922048635,1693922048635),(4,'1','dev','dev','开发空间','nacos',1693965282608,1693965282608);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`username`,`password`,`enabled`) values ('ggbot','$2a$10$18aHsB3aHL.Xs826Eh8oOe8qfU6ivqU.DTFdrmTcJIr8P/9qKjlTC',1),('listen','$2a$10$TjeWPSU9iXtbrpnV1hdGu.U6lAEhnljgb81cQ0tDrSGIHo30B6fxu',1),('nacos','$2a$10$S1IJBIMnn3JzekmAusndr.d/uSPLI1TxhslKsb.UWkjr05CIE2KWK',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
