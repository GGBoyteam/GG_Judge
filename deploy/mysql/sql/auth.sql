/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.36 : Database - auth
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`auth` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `auth`;

/*Table structure for table `userlogin` */

DROP TABLE IF EXISTS `userlogin`;

CREATE TABLE `userlogin` (
  `username` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` text COLLATE utf8mb4_unicode_ci,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `qq` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `wx` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `enable` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `userlogin` */

insert  into `userlogin`(`username`,`password`,`email`,`qq`,`wx`,`enable`) values ('1090199643','f68c672becb4c4f1c3fe82f7e878da70',NULL,NULL,NULL,0);

/* Procedure structure for procedure `deleteroute` */

/*!50003 DROP PROCEDURE IF EXISTS  `deleteroute` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `deleteroute`(IN sid bigint(255))
BEGIN
   SELECT id from role WHERE id=1 into @id;
      DELETE FROM role WHERE id=@id;
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
