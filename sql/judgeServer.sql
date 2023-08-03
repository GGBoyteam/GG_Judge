/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.36 : Database - judgeServer
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`judgeServer` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `judgeServer`;

/*Table structure for table `commit_log` */

DROP TABLE IF EXISTS `commit_log`;

CREATE TABLE `commit_log` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(255) DEFAULT NULL,
  `origin` text COLLATE utf8mb4_unicode_ci,
  `time` bigint(255) DEFAULT NULL,
  `memory` bigint(255) DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `commit_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `commit_log` */

/*Table structure for table `compiler` */

DROP TABLE IF EXISTS `compiler`;

CREATE TABLE `compiler` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `language` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `version` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `compiler` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role_key` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sort` bigint(255) DEFAULT NULL,
  `status` int(10) DEFAULT '1',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`description`,`role_key`,`sort`,`status`,`create_time`) values (1,'admin','管理员，拥有全部权限','admin',0,0,'2023-07-29 08:36:02'),(2,'user','用户','sort',0,0,'2023-07-29 08:36:02');

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(255) DEFAULT NULL,
  `permission` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `route_id` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3126 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `role_permission` */

insert  into `role_permission`(`id`,`role_id`,`permission`,`route_id`) values (1,1,'*:*:*',NULL);

/*Table structure for table `route` */

DROP TABLE IF EXISTS `route`;

CREATE TABLE `route` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT COMMENT '路由主键',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '路由名称',
  `path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '#' COMMENT '请求路径',
  `component` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '#' COMMENT '前端组件',
  `parent` bigint(255) DEFAULT '0' COMMENT '父路由id',
  `permission` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '#' COMMENT '权限(#代表不限制权限)',
  `hidden` tinyint(1) DEFAULT '0',
  `always_show` tinyint(1) DEFAULT NULL,
  `no_cache` tinyint(1) DEFAULT '0',
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `icon` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `redirect` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `breadcrumb` tinyint(1) DEFAULT NULL,
  `active_menu` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `status` int(10) DEFAULT NULL,
  `sort` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `route` */

insert  into `route`(`id`,`name`,`path`,`component`,`parent`,`permission`,`hidden`,`always_show`,`no_cache`,`title`,`icon`,`redirect`,`breadcrumb`,`active_menu`,`create_date`,`status`,`sort`) values (1,'System','/system','Layout',0,'#',0,1,NULL,'系统管理','system','noRedirect',1,'/system/user',NULL,NULL,NULL),(6,'Role','role','system/role/index',1,'#',0,0,NULL,'角色管理',NULL,NULL,NULL,'system/user',NULL,NULL,NULL),(7,'Route','route','system/route/index',1,'#',0,0,NULL,'路由管理',NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT COMMENT '玩家主键id',
  `role_id` bigint(255) DEFAULT '1' COMMENT '角色id',
  `username` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `user_info` */

insert  into `user_info`(`id`,`role_id`,`username`) values (1,1,'1090199643');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
