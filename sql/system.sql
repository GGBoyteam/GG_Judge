/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.36 : Database - system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `system`;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`description`,`role_key`,`sort`,`status`,`create_time`) values (1,'admin','管理员，拥有全部权限','admin',0,0,'2023-07-31 08:17:26'),(2,'user','用户','sort',0,0,'2023-07-31 08:17:29');

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(255) DEFAULT NULL,
  `permission` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `route_id` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3166 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `role_permission` */

insert  into `role_permission`(`id`,`role_id`,`permission`,`route_id`) values (1,1,'*:*:*',NULL),(3151,2,'system',1),(3152,2,'#',6),(3153,2,'#',7);

/*Table structure for table `route` */

DROP TABLE IF EXISTS `route`;

CREATE TABLE `route` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT COMMENT '路由主键',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '路由名称',
  `path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '#' COMMENT '请求路径',
  `component` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '#' COMMENT '前端组件',
  `parent` bigint(255) DEFAULT '0' COMMENT '父路由id',
  `permission` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '#' COMMENT '权限(#代表不限制权限)',
  `route_type` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `hidden` tinyint(1) DEFAULT '0',
  `always_show` tinyint(1) DEFAULT '0',
  `no_cache` tinyint(1) DEFAULT '0',
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `icon` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `redirect` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `breadcrumb` tinyint(1) DEFAULT NULL,
  `active_menu` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` int(10) DEFAULT '0',
  `sort` bigint(255) DEFAULT '0',
  `link` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `route` */

insert  into `route`(`id`,`name`,`path`,`component`,`parent`,`permission`,`route_type`,`hidden`,`always_show`,`no_cache`,`title`,`icon`,`redirect`,`breadcrumb`,`active_menu`,`create_time`,`status`,`sort`,`link`) values (1,'system','/system','Layout',0,'system','M',0,1,0,'系统管理','system','noRedirect',1,NULL,'2023-07-31 08:26:20',0,0,NULL),(6,'role','role','system/role/index',1,'#','C',0,0,0,'角色管理','peoples',NULL,NULL,NULL,'2023-07-31 08:26:15',0,0,NULL),(7,'route','route','system/route/index',1,'#','C',0,0,0,'路由管理','tree-table',NULL,NULL,NULL,'2023-07-31 08:26:17',0,0,NULL),(11,'user','user','system/user/index',1,'#','C',0,0,0,'用户管理','user',NULL,NULL,NULL,'2023-07-31 08:26:13',0,0,NULL),(13,'judge','/judge','Layout',0,'judge','M',0,1,0,'题库管理','code','noRedirect',NULL,NULL,'2023-07-31 08:26:10',0,0,NULL),(14,'content','/content','Layout',0,'content','M',0,0,0,'竞赛管理','redis-list','noRedirect',NULL,NULL,'2023-07-31 08:26:08',0,0,NULL);

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT COMMENT '玩家主键id',
  `username` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nickname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` bigint(255) DEFAULT NULL,
  `status` int(10) DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `user_info` */

insert  into `user_info`(`id`,`username`,`nickname`,`phone`,`status`,`create_time`) values (18,'1090199643','小张',13559068804,0,'2023-08-01 09:10:32');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(255) DEFAULT NULL,
  `user_id` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`role_id`,`user_id`) values (5,1,18),(6,2,18);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
