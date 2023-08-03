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
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';

/*Data for the table `config_info` */

insert  into `config_info`(`id`,`data_id`,`group_id`,`content`,`md5`,`gmt_create`,`gmt_modified`,`src_user`,`src_ip`,`app_name`,`tenant_id`,`c_desc`,`c_use`,`effect`,`type`,`c_schema`) values (1,'route.yaml','gateway','spring:\n  cloud:\n    gateway:\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/auth-service/**\n          filters:\n            - RewritePath=/auth-service/(?<segment>.*), /$\\{segment}\n        - id: admin-service\n          uri: lb://admin-service\n          predicates:\n            - Path=/admin-service/**\n          filters:\n            - RewritePath=/admin-service/(?<segment>.*), /$\\{segment}\n        - id: judge-service\n          uri: lb://judge-service\n          predicates:\n            - Path=/judge-service/**\n          filters:\n            - RewritePath=/judge-service/(?<segment>.*), /$\\{segment}\n        - id: system-service\n          uri: lb://system-service\n          predicates:\n            - Path=/system-service/**\n          filters:\n            - RewritePath=/system-service/(?<segment>.*), /$\\{segment}','e6073fd04846b1d9a01af67e67a5e4da','2023-07-24 07:39:21','2023-07-30 08:47:03','nacos','218.207.190.132','','','','','','yaml',''),(2,'mysql.yaml','env','spring:\n  datasource:\n    url: jdbc:mysql://192.168.1.13:3306/${datasource.name}?useSSL=false&useUnicode=true&characterEncoding=utf8\n    username: root\n    password: zhang147852\n    driver-class-name: com.mysql.cj.jdbc.Driver','ff828967a35c12143d440a78101d3f95','2023-07-24 07:39:59','2023-07-31 07:17:27','nacos','59.61.25.13','','','','','','yaml',''),(3,'nacos.yaml','env','spring:\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 192.168.1.13:8848\n        username: nacos\n        password: zhang147852','6879358680b3e744cee79d88dc43affc','2023-07-24 07:40:19','2023-07-31 03:07:07','nacos','59.61.25.13','','','','','','yaml',''),(4,'redis.yaml','env','spring:\n  redis:\n    host: 192.168.1.13\n    port: 6379','6e43d521bbbfbcc4d5b5e28deb5ead01','2023-07-24 07:45:48','2023-07-31 03:07:19','nacos','59.61.25.13','','','','','','yaml',''),(6,'judge.yaml','env','a','0cc175b9c0f1b6a831c399e269772661','2023-07-24 07:48:59','2023-07-24 07:48:59','nacos','59.61.28.193','','',NULL,NULL,NULL,'yaml',NULL),(24,'CQ.properties','DEFAULT_GROUP','botQQ=2745416965\nbotURL=http://172.17.0.1:9001/\n','312265379fae29a97e80bff5817b40e6','2023-07-26 12:39:39','2023-07-26 12:59:37','nacos','218.207.190.132','','a11739b4-e67b-4f9a-bd0d-52482636053b','','','','properties',''),(30,'prefix.properties','DEFAULT_GROUP','prefix=懒惰者,懒惰师,懒惰大师,懒惰王,懒惰皇,懒惰半圣,懒惰圣人,懒惰帝,普通成员','048661d92e43e2ab615490be79fe9884','2023-07-26 13:00:16','2023-07-29 15:47:01','nacos','218.207.190.132','','a11739b4-e67b-4f9a-bd0d-52482636053b','','','','properties',''),(34,'auth-service.properties','DEFAULT_GROUP','md5.salt=zhangsiyaodashuaibi\ndatasource.name=auth\nspring.cloud.nacos.discovery.ip=192.168.1.3','054e58943302ca423f03596404d76a92','2023-07-27 03:57:48','2023-07-31 04:04:15','nacos','59.61.25.13','auth-service','','','','','properties',''),(56,'route.yaml','gateway','spring:\n  cloud:\n    gateway:\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/auth-service/**\n          filters:\n            - RewritePath=/auth-service/(?<segment>.*), /$\\{segment}\n        - id: system-service\n          uri: lb://system-service\n          predicates:\n            - Path=/system-service/**\n          filters:\n            - RewritePath=/system-service/(?<segment>.*), /$\\{segment}\n        - id: judge-service\n          uri: lb://judge-service\n          predicates:\n            - Path=/judge-service/**\n          filters:\n            - RewritePath=/judge-service/(?<segment>.*), /$\\{segment}','fc8a4c31fdda0f7e9c61bf537d294e3e','2023-07-29 12:26:39','2023-07-30 08:57:23','nacos','218.207.190.132','','46b2694e-349a-47de-9ed7-bf59d17a0793','','','','yaml',''),(57,'mysql.yaml','env','spring:\n  datasource:\n    url: jdbc:mysql://192.168.56.10:3306/${datasource.name}?useSSL=false\n    username: root\n    password: root\n    driver-class-name: com.mysql.cj.jdbc.Driver','6a8695150674e7a188c5e4466f225f15','2023-07-29 12:26:39','2023-07-29 12:32:38','nacos','218.207.190.132','','46b2694e-349a-47de-9ed7-bf59d17a0793','','','','yaml',''),(58,'nacos.yaml','env','spring:\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 222.187.223.125:18848\n        username: nacos\n        password: zhang147852\n        namespace: 46b2694e-349a-47de-9ed7-bf59d17a0793','bac5b2dfe57abe3ddb3d934940d6eb32','2023-07-29 12:26:39','2023-07-29 12:37:23','nacos','218.207.190.132','','46b2694e-349a-47de-9ed7-bf59d17a0793','','','','yaml',''),(59,'redis.yaml','env','spring:\n  redis:\n    host: 192.168.56.10\n    port: 6379','740dfeffc0be54e849b4e2e23952505c','2023-07-29 12:26:39','2023-07-29 12:33:09','nacos','218.207.190.132','','46b2694e-349a-47de-9ed7-bf59d17a0793','','','','yaml',''),(60,'judge.yaml','env','a','0cc175b9c0f1b6a831c399e269772661','2023-07-29 12:26:39','2023-07-29 12:26:39',NULL,'218.207.190.132','','46b2694e-349a-47de-9ed7-bf59d17a0793',NULL,NULL,NULL,'yaml',NULL),(61,'auth-service.properties','DEFAULT_GROUP','md5.salt=zhangsiyaodashuaibi','01c150f92468e78778f56229dfee5b62','2023-07-29 12:26:39','2023-07-29 12:26:39',NULL,'218.207.190.132','auth-service','46b2694e-349a-47de-9ed7-bf59d17a0793','',NULL,NULL,'properties',NULL),(71,'gateway.properties','DEFAULT_GROUP','spring.cloud.nacos.discovery.ip=192.168.1.13','827328dde9ab7280ded8e1c7a05c6b87','2023-07-31 03:24:18','2023-07-31 03:24:57','nacos','59.61.25.13','','','','','','properties',''),(76,'system-service.properties','DEFAULT_GROUP','datasource.name=system\nspring.cloud.nacos.discovery.ip=192.168.1.73','d8bfdfaa606901580954af4e9fa2020b','2023-07-31 05:41:43','2023-07-31 05:43:29','nacos','59.61.25.13','','','','','','properties',''),(82,'config.properties','env','server.servlet.encoding.charset=UTF-8\r\nserver.servlet.encoding.enabled=true\r\nserver.servlet.encoding.force=true\r\nserver.servlet.encoding.force-request=true\r\nserver.servlet.encoding.force-response=true\r\nserver.tomcat.uri-encoding=UTF-8\r\nfeign.client.config.default.connectTimeout=5000\r\nfeign.client.config.default.readTimeout=30000','b93a266f17120edfe317adc5925b4a45','2023-08-02 02:14:25','2023-08-02 02:14:25','nacos','59.61.25.13','','',NULL,NULL,NULL,'properties',NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='å¤šç§Ÿæˆ·æ”¹é€ ';

/*Data for the table `his_config_info` */

insert  into `his_config_info`(`id`,`nid`,`data_id`,`group_id`,`app_name`,`content`,`md5`,`gmt_create`,`gmt_modified`,`src_user`,`src_ip`,`op_type`,`tenant_id`) values (0,1,'route.yaml','gateway','','a','0cc175b9c0f1b6a831c399e269772661','2023-07-24 07:39:20','2023-07-24 07:39:21','nacos','59.61.28.193','I',''),(0,2,'mysql.yaml','env','','a','0cc175b9c0f1b6a831c399e269772661','2023-07-24 07:39:58','2023-07-24 07:39:59','nacos','59.61.28.193','I',''),(0,3,'nacos.yaml','env','','a','0cc175b9c0f1b6a831c399e269772661','2023-07-24 07:40:18','2023-07-24 07:40:19','nacos','59.61.28.193','I',''),(0,4,'redis.yaml','env','','a','0cc175b9c0f1b6a831c399e269772661','2023-07-24 07:45:48','2023-07-24 07:45:48','nacos','59.61.28.193','I',''),(0,5,'judge','env','','a','0cc175b9c0f1b6a831c399e269772661','2023-07-24 07:48:35','2023-07-24 07:48:35','nacos','59.61.28.193','I',''),(5,6,'judge','env','','a','0cc175b9c0f1b6a831c399e269772661','2023-07-24 07:48:42','2023-07-24 07:48:42','nacos','59.61.28.193','D',''),(0,7,'judge.yaml','env','','a','0cc175b9c0f1b6a831c399e269772661','2023-07-24 07:48:59','2023-07-24 07:48:59','nacos','59.61.28.193','I',''),(3,8,'nacos.yaml','env','','a','0cc175b9c0f1b6a831c399e269772661','2023-07-24 08:06:44','2023-07-24 08:06:44','nacos','59.61.28.193','U',''),(0,9,'aaa.yaml','DEFAULT_GROUP','','ddddddd','338d811d532553557ca33be45b6bde55','2023-07-24 08:19:35','2023-07-24 08:19:35','nacos','59.61.28.193','I',''),(0,10,'aaa.yml','DEFAULT_GROUP','','ddddddd','338d811d532553557ca33be45b6bde55','2023-07-24 08:20:16','2023-07-24 08:20:17','nacos','59.61.28.193','I',''),(8,11,'aaa.yaml','DEFAULT_GROUP','','ddddddd','338d811d532553557ca33be45b6bde55','2023-07-24 11:08:54','2023-07-24 11:08:55','nacos','27.149.118.128','D',''),(9,12,'aaa.yml','DEFAULT_GROUP','','ddddddd','338d811d532553557ca33be45b6bde55','2023-07-24 11:08:57','2023-07-24 11:08:58','nacos','27.149.118.128','D',''),(3,13,'nacos.yaml','env','','spring:\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 222.187.223.125:18848\n        username: listen\n        password: listen','785a6cef798167fe3237ecd35a83d4c5','2023-07-24 11:09:16','2023-07-24 11:09:15','nacos','27.149.118.128','U',''),(4,14,'redis.yaml','env','','a','0cc175b9c0f1b6a831c399e269772661','2023-07-25 01:29:15','2023-07-25 01:29:16','nacos','59.61.25.41','U',''),(4,15,'redis.yaml','env','','spring:\n  redis:\n    host: 222.187.223.125\n    port: 6379\n    password: 1234','45a121b410243f64c62ef68308203500','2023-07-25 03:09:30','2023-07-25 03:09:30','nacos','59.61.25.41','U',''),(4,16,'redis.yaml','env','','spring:\n  redis:\n    host: 222.187.223.125\n    port: 16379\n    password: 1234','a43d431ae47a96ba6a09536b3231f444','2023-07-25 03:13:34','2023-07-25 03:13:35','nacos','59.61.25.41','U',''),(3,17,'nacos.yaml','env','','spring:\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 222.187.223.125:18848\n        username: nacos\n        password: zhang147852','2e3dadda4eb65896919ba985b9329bc4','2023-07-25 03:14:58','2023-07-25 03:14:58','nacos','59.61.25.41','U',''),(3,18,'nacos.yaml','env','','spring:\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 222.187.223.125:18848\n        username: listen\n        password: listen','785a6cef798167fe3237ecd35a83d4c5','2023-07-25 03:16:25','2023-07-25 03:16:26','nacos','59.61.25.41','U',''),(4,19,'redis.yaml','env','','spring:\n  redis:\n    host: 222.187.223.125\n    port: 36379\n    password: 1234','18337a17720d59eea89e38a94011e69c','2023-07-25 03:17:45','2023-07-25 03:17:46','nacos','59.61.25.41','U',''),(0,20,'CQ_config','DEFAULT_GROUP','','botQQ: 2745416965\r\nbotUrl: http://172.17.0.1:9001/','50cc78e90eb9a5a3064122581af936dc','2023-07-26 11:37:44','2023-07-26 11:37:45','nacos','218.207.190.132','I','a11739b4-e67b-4f9a-bd0d-52482636053b'),(17,21,'CQ_config','DEFAULT_GROUP','','botQQ: 2745416965\r\nbotUrl: http://172.17.0.1:9001/','50cc78e90eb9a5a3064122581af936dc','2023-07-26 11:40:56','2023-07-26 11:40:56','nacos','218.207.190.132','D','a11739b4-e67b-4f9a-bd0d-52482636053b'),(0,22,'CQ_config.yaml','DEFAULT_GROUP','','botQQ: 2745416965\r\nbotUrl: http://172.17.0.1:9001/','50cc78e90eb9a5a3064122581af936dc','2023-07-26 11:41:08','2023-07-26 11:41:09','nacos','218.207.190.132','I','a11739b4-e67b-4f9a-bd0d-52482636053b'),(0,23,'prefix.yaml','DEFAULT_GROUP','','prefix:\r\n    - 群猪\r\n    - 勤奋者','8d5c8a525698b0e2ac73e9f94e5f685f','2023-07-26 11:42:10','2023-07-26 11:42:11','nacos','218.207.190.132','I','a11739b4-e67b-4f9a-bd0d-52482636053b'),(18,24,'CQ_config.yaml','DEFAULT_GROUP','','botQQ: 2745416965\r\nbotUrl: http://172.17.0.1:9001/','50cc78e90eb9a5a3064122581af936dc','2023-07-26 12:26:47','2023-07-26 12:26:48','ggbot','218.207.190.132','U','a11739b4-e67b-4f9a-bd0d-52482636053b'),(18,25,'CQ_config.yaml','DEFAULT_GROUP','','bot:\n    botQQ: 2745416965\n    botUrl: http://172.17.0.1:9001/','a841aeb1afd2584a7ec75594f5748679','2023-07-26 12:27:11','2023-07-26 12:27:12','ggbot','218.207.190.132','U','a11739b4-e67b-4f9a-bd0d-52482636053b'),(18,26,'CQ_config.yaml','DEFAULT_GROUP','','bot:\n    botQQ: 2745416965\n    botUrl: http://172.17.0.1:9001/','a841aeb1afd2584a7ec75594f5748679','2023-07-26 12:28:38','2023-07-26 12:28:38','ggbot','218.207.190.132','U','a11739b4-e67b-4f9a-bd0d-52482636053b'),(0,27,'CQ','DEFAULT_GROUP','','botQQ=2963456487\r\nbotURL=DAWDAWD','44ecc3b77dd1d24997675e85607c2756','2023-07-26 12:39:06','2023-07-26 12:39:06','nacos','218.207.190.132','I','a11739b4-e67b-4f9a-bd0d-52482636053b'),(23,28,'CQ','DEFAULT_GROUP','','botQQ=2963456487\r\nbotURL=DAWDAWD','44ecc3b77dd1d24997675e85607c2756','2023-07-26 12:39:14','2023-07-26 12:39:14','nacos','218.207.190.132','D','a11739b4-e67b-4f9a-bd0d-52482636053b'),(0,29,'CQ.properties','DEFAULT_GROUP','','botQQ=123456','ed6154b95cde733bc0f0ce490d698b6e','2023-07-26 12:39:39','2023-07-26 12:39:39','nacos','218.207.190.132','I','a11739b4-e67b-4f9a-bd0d-52482636053b'),(24,30,'CQ.properties','DEFAULT_GROUP','','botQQ=123456','ed6154b95cde733bc0f0ce490d698b6e','2023-07-26 12:50:05','2023-07-26 12:50:05','nacos','218.207.190.132','U','a11739b4-e67b-4f9a-bd0d-52482636053b'),(24,31,'CQ.properties','DEFAULT_GROUP','','botQQ=2745416965','092e407ff5edfcbffa16d972767a346c','2023-07-26 12:50:42','2023-07-26 12:50:42','nacos','218.207.190.132','U','a11739b4-e67b-4f9a-bd0d-52482636053b'),(18,32,'CQ_config.yaml','DEFAULT_GROUP','','bot:\n    botQQ: \"2745416965\"\n    botUrl: \"http://172.17.0.1:9001/\"','b947f3125550098255cad8d17af6b485','2023-07-26 12:53:04','2023-07-26 12:53:05','nacos','218.207.190.132','D','a11739b4-e67b-4f9a-bd0d-52482636053b'),(19,33,'prefix.yaml','DEFAULT_GROUP','','prefix:\r\n    - 群猪\r\n    - 勤奋者','8d5c8a525698b0e2ac73e9f94e5f685f','2023-07-26 12:53:18','2023-07-26 12:53:18','nacos','218.207.190.132','U','a11739b4-e67b-4f9a-bd0d-52482636053b'),(24,34,'CQ.properties','DEFAULT_GROUP','','botQQ=2745416965\nbotURL=http://172.17.0.1:9001/','5db65139c8303517371f4082b8382ac8','2023-07-26 12:58:17','2023-07-26 12:58:17','nacos','218.207.190.132','U','a11739b4-e67b-4f9a-bd0d-52482636053b'),(24,35,'CQ.properties','DEFAULT_GROUP','','botQQ=2745416965\nbotURL=http://172.17.0.1:9001/\nprefix=群主,清分者','3e43c36915ac36392957320da706fd4c','2023-07-26 12:59:37','2023-07-26 12:59:37','nacos','218.207.190.132','U','a11739b4-e67b-4f9a-bd0d-52482636053b'),(19,36,'prefix.yaml','DEFAULT_GROUP','','prefix:\n    - \"群猪\"\n    - \"勤奋者\"','688f1851d3c2a33d86849a1ab6a0cf66','2023-07-26 12:59:49','2023-07-26 12:59:50','nacos','218.207.190.132','D','a11739b4-e67b-4f9a-bd0d-52482636053b'),(0,37,'prefix.properties','DEFAULT_GROUP','','prefix=群主,我','6603daaf37706ef79a2375efe8ca98f1','2023-07-26 13:00:15','2023-07-26 13:00:16','nacos','218.207.190.132','I','a11739b4-e67b-4f9a-bd0d-52482636053b'),(1,38,'route.yaml','gateway','','a','0cc175b9c0f1b6a831c399e269772661','2023-07-27 01:11:49','2023-07-27 01:11:49','nacos','59.61.25.41','U',''),(2,39,'mysql.yaml','env','','a','0cc175b9c0f1b6a831c399e269772661','2023-07-27 03:20:28','2023-07-27 03:20:29','nacos','59.61.25.41','U',''),(2,40,'mysql.yaml','env','','spring:\n  datasource:\n    url: jdbc:mysql://222.187.223.125:13306/${datasource.name}?useSSL=false\n    username: root\n    password: zhang147852','a59f26ad5c4da29e25415f99cb1a0a30','2023-07-27 03:21:18','2023-07-27 03:21:18','nacos','59.61.25.41','U',''),(0,41,'auth-service.properties','DEFAULT_GROUP','','a','0cc175b9c0f1b6a831c399e269772661','2023-07-27 03:57:48','2023-07-27 03:57:48','nacos','59.61.25.41','I',''),(34,42,'auth-service.properties','DEFAULT_GROUP','','a','0cc175b9c0f1b6a831c399e269772661','2023-07-27 03:58:16','2023-07-27 03:58:17','nacos','59.61.25.41','U',''),(34,43,'auth-service.properties','DEFAULT_GROUP','auth-service','a','0cc175b9c0f1b6a831c399e269772661','2023-07-27 04:28:48','2023-07-27 04:28:48','nacos','59.61.25.41','U',''),(1,44,'route.yaml','gateway','','spring:\n  cloud:\n    gateway:\n      routes:\n        - id: auth-service # 路由标示，必须唯一\n          uri: lb://authService # 路由的目标地址,lb表示负载均衡\n          predicates: # 路由断言，判断请求是否符合规则\n            - Path=/auth/**','5dfa8b4255ea5db9ef1b0e8b842aa2c5','2023-07-27 06:00:18','2023-07-27 06:00:19','nacos','59.61.25.41','U',''),(1,45,'route.yaml','gateway','','spring:\n  cloud:\n    gateway:\n      routes:\n        - id: auth-service # 路由标示，必须唯一\n          uri: lb://auth-service # 路由的目标地址,lb表示负载均衡\n          predicates: # 路由断言，判断请求是否符合规则\n            - Path=/auth/**','419d1fec40b1f5fdc55b89de24361a0a','2023-07-27 11:50:51','2023-07-27 11:50:51','nacos','218.207.190.132','U',''),(1,46,'route.yaml','gateway','','spring:\n  cloud:\n    gateway:\n      globalcors:\n        cors-configurations:\n          \'[/**]\':\n            allowedOriginPatterns: \"*\"\n            allowed-methods: \"*\"\n            allowed-headers: \"*\"\n      default-filters:\n        - DedupeResponseHeader=Access-Control-Allow-Origin Access-Control-Allow-Credentials Vary, RETAIN_UNIQUE\n      routes:\n        - id: auth-service # 路由标示，必须唯一\n          uri: lb://auth-service # 路由的目标地址,lb表示负载均衡\n          predicates: # 路由断言，判断请求是否符合规则\n            - Path=/auth/**','ff76a83314c9a6728383e504e0eb7d13','2023-07-28 01:38:18','2023-07-28 01:38:18','nacos','218.207.190.132','U',''),(1,47,'route.yaml','gateway','','spring:\n  cloud:\n    gateway:\n      default-filters:\n        - DedupeResponseHeader=Access-Control-Allow-Origin Access-Control-Allow-Credentials Vary, RETAIN_UNIQUE\n      routes:\n        - id: auth-service # 路由标示，必须唯一\n          uri: lb://auth-service # 路由的目标地址,lb表示负载均衡\n          predicates: # 路由断言，判断请求是否符合规则\n            - Path=/auth/**\n          filter:\n            - /auth/(?<segment>.*), /$\\{segment}','1ee7364a9de0d1d4f6425f95637d08d6','2023-07-28 01:41:45','2023-07-28 01:41:45','nacos','27.149.117.223','U',''),(1,48,'route.yaml','gateway','','spring:\n  cloud:\n    gateway:\n      default-filters:\n        - DedupeResponseHeader=Access-Control-Allow-Origin Access-Control-Allow-Credentials Vary, RETAIN_UNIQUE\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/auth/**\n          filter:\n            - /auth/(?<segment>.*), /$\\{segment}\n        - id: admin-service\n          uri: lb://admin-service\n          predicates:\n            - Path=/admin/**\n          filter:\n            - /admin/(?<segment>.*), /$\\{segment}','dfae75888c724b812bb3e67dcface875','2023-07-28 01:48:18','2023-07-28 01:48:18','nacos','27.149.117.223','U',''),(1,49,'route.yaml','gateway','','spring:\n  cloud:\n    gateway:\n      default-filters:\n        - DedupeResponseHeader=Access-Control-Allow-Origin Access-Control-Allow-Credentials Vary, RETAIN_UNIQUE\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/auth/**\n          filter:\n            - RewritePath=/auth/(?<segment>.*), /$\\{segment}\n        - id: admin-service\n          uri: lb://admin-service\n          predicates:\n            - Path=/admin/**\n          filter:\n            - RewritePath=/admin/(?<segment>.*), /$\\{segment}','1c586544ca9ec6a904ca5474c9f55521','2023-07-28 01:48:27','2023-07-28 01:48:28','nacos','27.149.117.223','U',''),(1,50,'route.yaml','gateway','','spring:\n  cloud:\n    gateway:\n      default-filters:\n        - DedupeResponseHeader=Access-Control-Allow-Origin Access-Control-Allow-Credentials Vary, RETAIN_UNIQUE\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/auth/**\n          filter:\n            - RewritePath=/auth/(?<segment>.*), /$\\{segment}\n        - id: admin-service\n          uri: lb://admin-service\n          predicates:\n            - Path=/admin/**\n          filter:\n            - RewritePath=/admin/(?<segment>.*), /$\\{segment}','1c586544ca9ec6a904ca5474c9f55521','2023-07-28 01:53:36','2023-07-28 01:53:36','nacos','27.149.117.223','U',''),(1,51,'route.yaml','gateway','','spring:\n  cloud:\n    gateway:\n      default-filters:\n        - DedupeResponseHeader=Access-Control-Allow-Origin Access-Control-Allow-Credentials Vary, RETAIN_UNIQUE\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/auth/**\n          filter:\n            - RewritePath=/auth/(?<segment>.*), /$\\{segment}\n        - id: admin-service\n          uri: lb://admin-service\n          predicates:\n            - Path=/admin/**\n          filter:\n            - RewritePath=/admin/(?<segment>.*), /$\\{segment}','1c586544ca9ec6a904ca5474c9f55521','2023-07-28 01:55:17','2023-07-28 01:55:18','nacos','27.149.117.223','U',''),(1,52,'route.yaml','gateway','','spring:\n  cloud:\n    gateway:\n      default-filters:\n        - DedupeResponseHeader=Access-Control-Allow-Origin Access-Control-Allow-Credentials Vary, RETAIN_UNIQUE\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/auth/**\n          filter:\n            - RewritePath=/auth/(?<segment>.*), /$\\\\{segment}\n        - id: admin-service\n          uri: lb://admin-service\n          predicates:\n            - Path=/admin/**\n          filter:\n            - RewritePath=/admin/(?<segment>.*), /$\\{segment}','ac8362fc25ef4f6f15abd4d741a29dd9','2023-07-28 01:55:56','2023-07-28 01:55:57','nacos','27.149.117.223','U',''),(1,53,'route.yaml','gateway','','spring:\n  cloud:\n    gateway:\n      default-filters:\n        - DedupeResponseHeader=Access-Control-Allow-Origin Access-Control-Allow-Credentials Vary, RETAIN_UNIQUE\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/auth/**\n          filter:\n            - RewritePath=/auth/(?<segment>.*), /$\\{segment}\n        - id: admin-service\n          uri: lb://admin-service\n          predicates:\n            - Path=/admin/**\n          filter:\n            - RewritePath=/admin/(?<segment>.*), /$\\{segment}','1c586544ca9ec6a904ca5474c9f55521','2023-07-28 02:00:20','2023-07-28 02:00:20','nacos','27.149.117.223','U',''),(1,54,'route.yaml','gateway','','spring:\n  cloud:\n    gateway:\n      default-filters:\n        - DedupeResponseHeader=Access-Control-Allow-Origin Access-Control-Allow-Credentials Vary, RETAIN_UNIQUE\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/api/auth/**\n          filter:\n            - RewritePath=/api/auth/(?<segment>.*), /auth/$\\{segment}\n        - id: admin-service\n          uri: lb://admin-service\n          predicates:\n            - Path=/admin/**\n          filter:\n            - RewritePath=/admin/(?<segment>.*), /$\\{segment}','c1ec9fe6bad27af3d89d91a6b8970895','2023-07-28 02:00:39','2023-07-28 02:00:39','nacos','27.149.117.223','U',''),(1,55,'route.yaml','gateway','','spring:\n  cloud:\n    gateway:\n      default-filters:\n        - DedupeResponseHeader=Access-Control-Allow-Origin Access-Control-Allow-Credentials Vary, RETAIN_UNIQUE\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/api/auth/**\n          filter:\n            - RewritePath=/api/auth/(?<segment>.*), /auth/$\\{segment}\n        - id: admin-service\n          uri: lb://admin-service\n          predicates:\n            - Path=/admin/**\n          filter:\n            - RewritePath=/admin/(?<segment>.*), /$\\{segment}','c1ec9fe6bad27af3d89d91a6b8970895','2023-07-28 02:05:52','2023-07-28 02:05:53','nacos','27.149.117.223','U',''),(1,56,'route.yaml','gateway','','spring:\n  cloud:\n    gateway:\n      default-filters:\n        - DedupeResponseHeader=Access-Control-Allow-Origin Access-Control-Allow-Credentials Vary, RETAIN_UNIQUE\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/api/auth/**\n          filter:\n            - RewritePath=/api/(?<segment>.*), /$\\{segment}\n        - id: admin-service\n          uri: lb://admin-service\n          predicates:\n            - Path=/admin/**\n          filter:\n            - RewritePath=/admin/(?<segment>.*), /$\\{segment}','1a61fc17f8205f7832004519da9a8ea4','2023-07-28 02:06:41','2023-07-28 02:06:41','nacos','27.149.117.223','U',''),(1,57,'route.yaml','gateway','','spring:\n  cloud:\n    gateway:\n      default-filters:\n        - DedupeResponseHeader=Access-Control-Allow-Origin Access-Control-Allow-Credentials Vary, RETAIN_UNIQUE\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/api/auth/**\n          filters:\n            - RewritePath=/api/(?<segment>.*), /$\\{segment}\n        - id: admin-service\n          uri: lb://admin-service\n          predicates:\n            - Path=/admin/**\n          filters:\n            - RewritePath=/admin/(?<segment>.*), /$\\{segment}','2e0521bf4029d42912c2a62a23fee4af','2023-07-28 02:08:02','2023-07-28 02:08:02','nacos','27.149.117.223','U',''),(1,58,'route.yaml','gateway','','spring:\n  cloud:\n    gateway:\n      default-filters:\n        - DedupeResponseHeader=Access-Control-Allow-Origin Access-Control-Allow-Credentials Vary, RETAIN_UNIQUE\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/api/auth/**\n          filters:\n            - RewritePath=/api/auth/(?<segment>.*), /$\\{segment}\n        - id: admin-service\n          uri: lb://admin-service\n          predicates:\n            - Path=/api/admin/**\n          filters:\n            - RewritePath=/api/admin/(?<segment>.*), /$\\{segment}','e592e5383b5dedd2df322dc50a6ad7c3','2023-07-28 02:15:23','2023-07-28 02:15:24','nacos','27.149.117.223','U',''),(1,59,'route.yaml','gateway','','spring:\n  cloud:\n    gateway:\n      default-filters:\n        - DedupeResponseHeader=Access-Control-Allow-Origin Access-Control-Allow-Credentials Vary, RETAIN_UNIQUE\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/auth-service/**\n          filters:\n            - RewritePath=/auth-service/(?<segment>.*), /$\\{segment}\n        - id: admin-service\n          uri: lb://admin-service\n          predicates:\n            - Path=/admin-service/**\n          filters:\n            - RewritePath=/admin-service/(?<segment>.*), /$\\{segment}','406e732ff93f5d411e141ba446049ce2','2023-07-28 09:43:02','2023-07-28 09:43:02','nacos','27.149.117.223','U',''),(1,60,'route.yaml','gateway','','spring:\n  cloud:\n    gateway:\n      default-filters:\n        - DedupeResponseHeader=Access-Control-Allow-Origin Access-Control-Allow-Credentials Vary, RETAIN_UNIQUE\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/auth-service/**\n          filters:\n            - RewritePath=/auth-service/(?<segment>.*), /$\\{segment}\n        - id: admin-service\n          uri: lb://admin-service\n          predicates:\n            - Path=/admin-service/**\n          filters:\n            - RewritePath=/admin-service/(?<segment>.*), /$\\{segment}\n        - id: judge-service\n          uri: lb://judge-service\n          predicates:\n            - Path=/judge-service/**\n          filters:\n            - RewritePath=/judge-service/(?<segment>.*), /$\\{segment}','1055228b9e1b6f358330ac0c1ea9f517','2023-07-28 09:43:15','2023-07-28 09:43:15','nacos','27.149.117.223','U',''),(30,61,'prefix.properties','DEFAULT_GROUP','','prefix=群主,我','6603daaf37706ef79a2375efe8ca98f1','2023-07-28 09:46:55','2023-07-28 09:46:55','nacos','27.149.117.223','U','a11739b4-e67b-4f9a-bd0d-52482636053b'),(1,62,'route.yaml','gateway','','spring:\n  cloud:\n    gateway:\n      default-filters:\n        - DedupeResponseHeader=Access-Control-Allow-Origin Access-Control-Allow-Credentials Vary, RETAIN_UNIQUE\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/auth-service/**\n          filters:\n            - RewritePath=/auth-service/(?<segment>.*), /$\\{segment}\n        - id: admin-service\n          uri: lb://admin-service\n          predicates:\n            - Path=/admin-service/**\n          filters:\n            - RewritePath=/admin-service/(?<segment>.*), /$\\{segment}\n        - id: judge-service\n          uri: lb://judge-service\n          predicates:\n            - Path=/judge-service/**\n          filters:\n            - RewritePath=/judge-service/(?<segment>.*), /$\\{segment}','1055228b9e1b6f358330ac0c1ea9f517','2023-07-29 04:06:21','2023-07-29 04:06:21','nacos','218.207.190.132','U',''),(0,63,'route.yaml','gateway','','spring:\n  cloud:\n    gateway:\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/auth-service/**\n          filters:\n            - RewritePath=/auth-service/(?<segment>.*), /$\\{segment}\n        - id: admin-service\n          uri: lb://admin-service\n          predicates:\n            - Path=/admin-service/**\n          filters:\n            - RewritePath=/admin-service/(?<segment>.*), /$\\{segment}\n        - id: judge-service\n          uri: lb://judge-service\n          predicates:\n            - Path=/judge-service/**\n          filters:\n            - RewritePath=/judge-service/(?<segment>.*), /$\\{segment}','94b45bf56c35cfc43509e39c9c5ad8fd','2023-07-29 12:26:39','2023-07-29 12:26:39',NULL,'218.207.190.132','I','46b2694e-349a-47de-9ed7-bf59d17a0793'),(0,64,'mysql.yaml','env','','spring:\n  datasource:\n    url: jdbc:mysql://222.187.223.125:13306/${datasource.name}?useSSL=false\n    username: root\n    password: zhang147852\n    driver-class-name: com.mysql.cj.jdbc.Driver','6be99263e3906ecf61e9450eac84e84f','2023-07-29 12:26:39','2023-07-29 12:26:39',NULL,'218.207.190.132','I','46b2694e-349a-47de-9ed7-bf59d17a0793'),(0,65,'nacos.yaml','env','','spring:\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 222.187.223.125:18848\n        username: nacos\n        password: zhang147852','2e3dadda4eb65896919ba985b9329bc4','2023-07-29 12:26:39','2023-07-29 12:26:39',NULL,'218.207.190.132','I','46b2694e-349a-47de-9ed7-bf59d17a0793'),(0,66,'redis.yaml','env','','spring:\n  redis:\n    host: 222.187.223.125\n    port: 36379','71d708d8bef767e86e54bb848a186d2d','2023-07-29 12:26:39','2023-07-29 12:26:39',NULL,'218.207.190.132','I','46b2694e-349a-47de-9ed7-bf59d17a0793'),(0,67,'judge.yaml','env','','a','0cc175b9c0f1b6a831c399e269772661','2023-07-29 12:26:39','2023-07-29 12:26:39',NULL,'218.207.190.132','I','46b2694e-349a-47de-9ed7-bf59d17a0793'),(0,68,'auth-service.properties','DEFAULT_GROUP','auth-service','md5.salt=zhangsiyaodashuaibi','01c150f92468e78778f56229dfee5b62','2023-07-29 12:26:39','2023-07-29 12:26:39',NULL,'218.207.190.132','I','46b2694e-349a-47de-9ed7-bf59d17a0793'),(57,69,'mysql.yaml','env','','spring:\n  datasource:\n    url: jdbc:mysql://222.187.223.125:13306/${datasource.name}?useSSL=false\n    username: root\n    password: zhang147852\n    driver-class-name: com.mysql.cj.jdbc.Driver','6be99263e3906ecf61e9450eac84e84f','2023-07-29 12:32:38','2023-07-29 12:32:38','nacos','218.207.190.132','U','46b2694e-349a-47de-9ed7-bf59d17a0793'),(59,70,'redis.yaml','env','','spring:\n  redis:\n    host: 222.187.223.125\n    port: 36379','71d708d8bef767e86e54bb848a186d2d','2023-07-29 12:33:09','2023-07-29 12:33:09','nacos','218.207.190.132','U','46b2694e-349a-47de-9ed7-bf59d17a0793'),(58,71,'nacos.yaml','env','','spring:\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 222.187.223.125:18848\n        username: nacos\n        password: zhang147852','2e3dadda4eb65896919ba985b9329bc4','2023-07-29 12:37:23','2023-07-29 12:37:23','nacos','218.207.190.132','U','46b2694e-349a-47de-9ed7-bf59d17a0793'),(30,72,'prefix.properties','DEFAULT_GROUP','','prefix=懒惰者,懒惰师,懒惰大师,懒惰王,懒惰皇,懒惰半圣,懒惰圣人,懒惰帝','cf6bc63f285e18fd6f69e69b756f0251','2023-07-29 15:47:01','2023-07-29 15:47:01','nacos','218.207.190.132','U','a11739b4-e67b-4f9a-bd0d-52482636053b'),(1,73,'route.yaml','gateway','','spring:\n  cloud:\n    gateway:\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/auth-service/**\n          filters:\n            - RewritePath=/auth-service/(?<segment>.*), /$\\{segment}\n        - id: admin-service\n          uri: lb://admin-service\n          predicates:\n            - Path=/admin-service/**\n          filters:\n            - RewritePath=/admin-service/(?<segment>.*), /$\\{segment}\n        - id: judge-service\n          uri: lb://judge-service\n          predicates:\n            - Path=/judge-service/**\n          filters:\n            - RewritePath=/judge-service/(?<segment>.*), /$\\{segment}','94b45bf56c35cfc43509e39c9c5ad8fd','2023-07-30 08:47:03','2023-07-30 08:47:03','nacos','218.207.190.132','U',''),(56,74,'route.yaml','gateway','','spring:\n  cloud:\n    gateway:\n      routes:\n        - id: auth-service\n          uri: lb://auth-service\n          predicates:\n            - Path=/auth-service/**\n          filters:\n            - RewritePath=/auth-service/(?<segment>.*), /$\\{segment}\n        - id: admin-service\n          uri: lb://admin-service\n          predicates:\n            - Path=/admin-service/**\n          filters:\n            - RewritePath=/admin-service/(?<segment>.*), /$\\{segment}\n        - id: judge-service\n          uri: lb://judge-service\n          predicates:\n            - Path=/judge-service/**\n          filters:\n            - RewritePath=/judge-service/(?<segment>.*), /$\\{segment}','94b45bf56c35cfc43509e39c9c5ad8fd','2023-07-30 08:57:23','2023-07-30 08:57:23','nacos','218.207.190.132','U','46b2694e-349a-47de-9ed7-bf59d17a0793'),(2,75,'mysql.yaml','env','','spring:\n  datasource:\n    url: jdbc:mysql://222.187.223.125:13306/${datasource.name}?useSSL=false\n    username: root\n    password: zhang147852\n    driver-class-name: com.mysql.cj.jdbc.Driver','6be99263e3906ecf61e9450eac84e84f','2023-07-31 03:06:02','2023-07-31 03:06:03','nacos','59.61.25.13','U',''),(3,76,'nacos.yaml','env','','spring:\n  cloud:\n    nacos:\n      discovery:\n        server-addr: 222.187.223.125:18848\n        username: nacos\n        password: zhang147852','2e3dadda4eb65896919ba985b9329bc4','2023-07-31 03:07:07','2023-07-31 03:07:07','nacos','59.61.25.13','U',''),(4,77,'redis.yaml','env','','spring:\n  redis:\n    host: 222.187.223.125\n    port: 36379','71d708d8bef767e86e54bb848a186d2d','2023-07-31 03:07:19','2023-07-31 03:07:19','nacos','59.61.25.13','U',''),(0,78,'gateway.properties','DEFAULT_GROUP','','spring.cloud.nacos.discovery.ip=192.168.1.31','9b1587a71ddeb6c60a1633c092c78133','2023-07-31 03:24:17','2023-07-31 03:24:18','nacos','59.61.25.13','I',''),(71,79,'gateway.properties','DEFAULT_GROUP','','spring.cloud.nacos.discovery.ip=192.168.1.31','9b1587a71ddeb6c60a1633c092c78133','2023-07-31 03:24:57','2023-07-31 03:24:57','nacos','59.61.25.13','U',''),(34,80,'auth-service.properties','DEFAULT_GROUP','auth-service','md5.salt=zhangsiyaodashuaibi','01c150f92468e78778f56229dfee5b62','2023-07-31 04:02:30','2023-07-31 04:02:31','nacos','59.61.25.13','U',''),(34,81,'auth-service.properties','DEFAULT_GROUP','auth-service','md5.salt=zhangsiyaodashuaibi\n\ndatasource.name=auth','3f2f2a20a7b572ae6badeff2363a49c2','2023-07-31 04:02:37','2023-07-31 04:02:37','nacos','59.61.25.13','U',''),(34,82,'auth-service.properties','DEFAULT_GROUP','auth-service','md5.salt=zhangsiyaodashuaibi\ndatasource.name=auth','f53cc5c0d63e9928ddb348c16cbd58aa','2023-07-31 04:04:15','2023-07-31 04:04:15','nacos','59.61.25.13','U',''),(0,83,'system-service.properties','DEFAULT_GROUP','','192.168.1.73','775d3fe12982fd2be3327289ff18ec9e','2023-07-31 05:41:42','2023-07-31 05:41:43','nacos','59.61.25.13','I',''),(76,84,'system-service.properties','DEFAULT_GROUP','','192.168.1.73','775d3fe12982fd2be3327289ff18ec9e','2023-07-31 05:42:12','2023-07-31 05:42:12','nacos','59.61.25.13','U',''),(76,85,'system-service.properties','DEFAULT_GROUP','','datasource.name=system\nspring.cloud.nacos.discovery.ip=192.168.1.73','d8bfdfaa606901580954af4e9fa2020b','2023-07-31 05:43:29','2023-07-31 05:43:29','nacos','59.61.25.13','U',''),(2,86,'mysql.yaml','env','','spring:\n  datasource:\n    url: jdbc:mysql://192.168.1.13:3306/${datasource.name}?useSSL=false\n    username: root\n    password: zhang147852\n    driver-class-name: com.mysql.cj.jdbc.Driver','c9321e1af1f8f9d9560f38013a8460ca','2023-07-31 07:12:07','2023-07-31 07:12:08','nacos','59.61.25.13','U',''),(2,87,'mysql.yaml','env','','spring:\n  datasource:\n    url: jdbc:mysql://192.168.1.13:3306/${datasource.name}?useSSL=false&characterEncoding=utf8mb4\n    username: root\n    password: zhang147852\n    driver-class-name: com.mysql.cj.jdbc.Driver','8223f91c9af4444bfad17c9ad14afa34','2023-07-31 07:17:05','2023-07-31 07:17:05','nacos','59.61.25.13','U',''),(2,88,'mysql.yaml','env','','spring:\n  datasource:\n    url: jdbc:mysql://192.168.1.13:3306/${datasource.name}?useSSL=false&useUnicode=true&characterEncoding=utf8mb4\n    username: root\n    password: zhang147852\n    driver-class-name: com.mysql.cj.jdbc.Driver','2898d98fd12a4de2d027d63175ab3e78','2023-07-31 07:17:27','2023-07-31 07:17:27','nacos','59.61.25.13','U',''),(0,89,'config.properties','env','','server.servlet.encoding.charset=UTF-8\r\nserver.servlet.encoding.enabled=true\r\nserver.servlet.encoding.force=true\r\nserver.servlet.encoding.force-request=true\r\nserver.servlet.encoding.force-response=true\r\nserver.tomcat.uri-encoding=UTF-8\r\nfeign.client.config.default.connectTimeout=5000\r\nfeign.client.config.default.readTimeout=30000','b93a266f17120edfe317adc5925b4a45','2023-08-02 02:14:24','2023-08-02 02:14:25','nacos','59.61.25.13','I','');

/*Table structure for table `permissions` */

DROP TABLE IF EXISTS `permissions`;

CREATE TABLE `permissions` (
  `role` varchar(50) NOT NULL,
  `resource` varchar(255) NOT NULL,
  `action` varchar(8) NOT NULL,
  UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `permissions` */

insert  into `permissions`(`role`,`resource`,`action`) values ('GGBot','a11739b4-e67b-4f9a-bd0d-52482636053b:*:*','rw'),('LISTEN','46b2694e-349a-47de-9ed7-bf59d17a0793:*:*','r'),('LISTEN',':*:*','r');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';

/*Data for the table `tenant_info` */

insert  into `tenant_info`(`id`,`kp`,`tenant_id`,`tenant_name`,`tenant_desc`,`create_source`,`gmt_create`,`gmt_modified`) values (1,'1','a11739b4-e67b-4f9a-bd0d-52482636053b','GG_bot','群机器人配置文件','nacos',1690371249339,1690371249339),(2,'1','46b2694e-349a-47de-9ed7-bf59d17a0793','product','生产环境','nacos',1690633583988,1690633583988);

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
