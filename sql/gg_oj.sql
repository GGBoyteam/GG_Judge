/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.36 : Database - ggoj
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ggoj` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `ggoj`;

/*Table structure for table `compile_param` */

DROP TABLE IF EXISTS `compile_param`;

CREATE TABLE `compile_param` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `problem_id` bigint(255) DEFAULT NULL COMMENT '问题id',
  `complier_id` bigint(255) DEFAULT NULL COMMENT '编译器id',
  `time_limit` bigint(255) DEFAULT NULL COMMENT '时间限制,单位：纳秒',
  `memory_limit` bigint(255) DEFAULT NULL COMMENT '内存限制,单位：byte',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `compile_param` */

/*Table structure for table `complier` */

DROP TABLE IF EXISTS `complier`;

CREATE TABLE `complier` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '编译器名称',
  `arg` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '编译器参数',
  `env` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '编译器环境',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `complier` */

/*Table structure for table `example` */

DROP TABLE IF EXISTS `example`;

CREATE TABLE `example` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `problem_id` bigint(255) NOT NULL COMMENT '题目id',
  `input` text COLLATE utf8mb4_unicode_ci COMMENT '样例输入内容',
  `output` text COLLATE utf8mb4_unicode_ci COMMENT '样例输出内容',
  `score` int(10) DEFAULT '0' COMMENT '样例得分',
  `display` int(1) NOT NULL DEFAULT '0' COMMENT '是否在题目中显示，作为题目的参考样例，0不显示，1显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `example` */

/*Table structure for table `problem` */

DROP TABLE IF EXISTS `problem`;

CREATE TABLE `problem` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `titile` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '题目标题',
  `input_description` text COLLATE utf8mb4_unicode_ci COMMENT '题目输入描述',
  `output_description` text COLLATE utf8mb4_unicode_ci COMMENT '题目输出描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `problem` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
