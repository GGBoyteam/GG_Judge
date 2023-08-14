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
  `cid` bigint(255) NOT NULL AUTO_INCREMENT COMMENT '编译器id',
  `language` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '语言',
  `version` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '版本',
  `need_compile` tinyint(1) DEFAULT NULL COMMENT '是否需要编译',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `compiler` */

/*Table structure for table `example` */

DROP TABLE IF EXISTS `example`;

CREATE TABLE `example` (
  `eid` bigint(255) NOT NULL AUTO_INCREMENT COMMENT '样例id',
  `pid` bigint(255) NOT NULL COMMENT '题目id',
  `input` text COLLATE utf8mb4_unicode_ci COMMENT '输入',
  `output` text COLLATE utf8mb4_unicode_ci COMMENT '输出',
  `status` tinyint(1) DEFAULT '0' COMMENT '是否作为题目展示的样例(1展示,0作为判题样例)',
  PRIMARY KEY (`eid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `example` */

/*Table structure for table `problem` */

DROP TABLE IF EXISTS `problem`;

CREATE TABLE `problem` (
  `pid` bigint(255) NOT NULL AUTO_INCREMENT COMMENT '题目id',
  `title` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '题目标题',
  `author` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '题目作者',
  `description` text COLLATE utf8mb4_unicode_ci COMMENT '题目描述',
  `input_description` text COLLATE utf8mb4_unicode_ci COMMENT '输入描述',
  `output_description` text COLLATE utf8mb4_unicode_ci COMMENT '输出描述',
  `example_description` text COLLATE utf8mb4_unicode_ci COMMENT '样例描述',
  `status` tinyint(1) DEFAULT NULL COMMENT '是否展示(0展示，1不展示)',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `problem` */

insert  into `problem`(`pid`,`title`,`author`,`description`,`input_description`,`output_description`,`example_description`,`status`) values (1,'Hello,World','1090199643','编写一个能够输出 `Hello,World!` 的程序。\n\n提示：\n\n* 使用英文标点符号；\n* `Hello,World!` 逗号后面**没有**空格。\n* `H` 和 `W` 为**大写**字母。\n','无\n','无\n','\n',0);

/*Table structure for table `problem_tag` */

DROP TABLE IF EXISTS `problem_tag`;

CREATE TABLE `problem_tag` (
  `tid` bigint(255) NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `title` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标签标题',
  `color` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标签颜色',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `problem_tag` */

insert  into `problem_tag`(`tid`,`title`,`color`) values (1,'矩阵乘法','#AD49EF'),(2,'DP','#49B8EF'),(3,'入门','#EF6565'),(4,'普及+','#EF6565'),(5,'模拟','#EFBA49'),(6,'字符串','#F1BC4D'),(7,'枚举','#F89148'),(8,'递推','#F7CA43'),(9,'哈希','#D5F439'),(10,'队列','#F4D239'),(11,'前缀和','#F4D239'),(12,'栈','#F4B339'),(13,'数据结构','#3BA2E3'),(14,'DFS','#62E456'),(15,'并查集',NULL),(16,'图论','#3B87E3'),(17,'链表','#3BB6E3'),(18,'快速幂','#64DC96'),(19,'剪枝','#5375EE'),(20,'位运算','#7D8ED9'),(21,'循环','#ED604A'),(22,'贪心','#4AD7ED'),(23,'NOIP','#A3EB6C'),(24,'树','#B06CEB'),(25,'CSP',NULL),(26,'斜率优化','#AF3EE7'),(27,'二分','#52EFE5'),(28,'拓扑排序','#5D69F2'),(29,'区间DP','#9A7EF9'),(30,'01背包','#6DDDE1'),(31,'BFS','#62D391'),(32,'分治','#62D39C'),(33,'递归','#E6D265'),(34,'离散化','#B052EE'),(35,'最短路','#8E51EA'),(36,'邻接表','#6F64E6'),(37,'最小生成树','#6F64E6'),(38,'图的遍历','#64A3E6'),(39,'KMP','#8165F1'),(40,'Trie树','#8165F1'),(41,'AC自动机','#8165F1'),(42,'树状数组','#555CE4'),(43,'结构体','#E4AD55'),(44,'高精度','#D9B479'),(45,'博弈问题',NULL),(46,'素数',NULL),(47,'Pollard-rho 算法','#4BB2E6'),(48,'回文自动机',NULL),(49,'生成树','#5E5CE4'),(50,'差分','#55CD67'),(51,'倍增','#805BE7'),(52,'二叉堆','#AA5BE7'),(53,'同余','#7A35B0'),(54,'线段树','#A652F9'),(55,'分块','#A652F9'),(56,'计数','#7592EA'),(57,'数位DP','#9F5CEB'),(58,'SPJ',NULL),(59,'背包','#5EBA6C'),(60,'树形DP','#3182E6'),(61,'状压DP',NULL),(62,'STL','#3DC8A3'),(63,'树的直径',NULL),(64,'字典树',NULL),(65,'双指针',NULL),(66,'数组问题',NULL);

/*Table structure for table `problem_tag_relation` */

DROP TABLE IF EXISTS `problem_tag_relation`;

CREATE TABLE `problem_tag_relation` (
  `rid` bigint(255) DEFAULT NULL COMMENT '题目标签关联id',
  `pid` bigint(255) DEFAULT NULL COMMENT '题目id',
  `tid` bigint(255) DEFAULT NULL COMMENT '标签id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `problem_tag_relation` */

insert  into `problem_tag_relation`(`rid`,`pid`,`tid`) values (NULL,1,3);

/*Table structure for table `problem_true_code` */

DROP TABLE IF EXISTS `problem_true_code`;

CREATE TABLE `problem_true_code` (
  `code_id` bigint(255) NOT NULL AUTO_INCREMENT COMMENT '正确代码id',
  `pid` bigint(255) NOT NULL COMMENT '题目id',
  `language` varbinary(50) NOT NULL COMMENT '编程语言',
  `version` int(20) NOT NULL COMMENT '编译版本',
  `code` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '正确代码',
  PRIMARY KEY (`code_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `problem_true_code` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
