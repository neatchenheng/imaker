/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.0.87-50-log : Database - liveshowcms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`liveshowcms` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `liveshowcms`;

/*Table structure for table `tb_channel` */

DROP TABLE IF EXISTS `tb_channel`;

CREATE TABLE `tb_channel` (
  `id` int(11) NOT NULL auto_increment,
  `channel_id` int(11) NOT NULL COMMENT '自定义频道ID',
  `name` varchar(100) NOT NULL,
  `url` varchar(100) default NULL,
  `editor` varchar(50) NOT NULL COMMENT '编辑email',
  `status` tinyint(4) NOT NULL COMMENT '状态，{0:关闭,1:开启}',
  `create_time` date NOT NULL,
  `update_time` date NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_channel` */

insert  into `tb_channel`(`id`,`channel_id`,`name`,`url`,`editor`,`status`,`create_time`,`update_time`) values (1,151001,'gdpartner','http://www.gdpartner.cn/','123123416@qq.com',1,'2015-10-29','2015-10-30');

/*Table structure for table `tb_page` */

DROP TABLE IF EXISTS `tb_page`;

CREATE TABLE `tb_page` (
  `id` int(11) NOT NULL auto_increment,
  `channel_id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL COMMENT 'CMS里的名称标识',
  `title` varchar(100) NOT NULL COMMENT 'html里的标题',
  `meta_keywords` varchar(200) default NULL COMMENT 'html里的关键字',
  `meta_desc` varchar(200) default NULL COMMENT 'html里的描述',
  `url` varchar(50) NOT NULL COMMENT '页面url',
  `editor` varchar(50) default NULL COMMENT '页面编辑email',
  `status` tinyint(4) NOT NULL COMMENT '状态,{0:关闭,1:正常}',
  `create_time` date NOT NULL,
  `update_time` date NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_page` */

/*Table structure for table `tb_plugin` */

DROP TABLE IF EXISTS `tb_plugin`;

CREATE TABLE `tb_plugin` (
  `id` int(11) NOT NULL auto_increment,
  `plugin_id` int(11) NOT NULL COMMENT '自定义插件ID',
  `name` varchar(100) NOT NULL COMMENT '插件名称',
  `theme` int(11) NOT NULL COMMENT '页面主题,{100:2015v1,101:2015v2}',
  `device` int(11) NOT NULL COMMENT '支持设备类型,{100:PC端,200:PHONE端}',
  `status` tinyint(4) NOT NULL COMMENT '状态，{0:关闭,1:正常}',
  `update_time` datetime NOT NULL COMMENT '最后更新时间',
  `create_time` datetime default NULL COMMENT '创建时间',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `plugin_id` (`plugin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `tb_plugin` */

insert  into `tb_plugin`(`id`,`plugin_id`,`name`,`theme`,`device`,`status`,`update_time`,`create_time`) values (13,100,'轮播大图',100,100,1,'2015-10-28 16:05:36','2015-10-20 20:32:57'),(14,101,'Banner(长方条)',100,100,1,'2015-10-28 16:13:39','2015-10-27 21:31:37'),(15,102,'图文列表(6列)',100,100,1,'2015-10-28 16:13:29','2015-10-28 16:13:29');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
