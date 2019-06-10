-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.25-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema vgb
--

CREATE DATABASE IF NOT EXISTS vgb;
USE vgb;

--
-- Definition of table `customers`
--

DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
  `id` char(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(60) NOT NULL,
  `gender` char(1) NOT NULL,
  `birthday` date NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `married` tinyint(1) DEFAULT NULL,
  `blood_type` varchar(2) DEFAULT NULL,
  `discount` int(3) NOT NULL DEFAULT '0',
  `class_name` varchar(20) NOT NULL DEFAULT 'Customer',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unx_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customers`
--

/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` (`id`,`name`,`password`,`email`,`gender`,`birthday`,`phone`,`address`,`married`,`blood_type`,`discount`,`class_name`) VALUES 
 ('A123123123','林微微','123456','willy@gmail.com','M','1985-09-04','0933125873','新竹市風很大的地方',1,'AB',25,'VIP'),
 ('A123123132','陳姍姍','123456','sandy@gmail.com','M','1993-10-04','0933125873','新竹市風很大的地方',0,'A',15,'VIP'),
 ('A123456789','菜狄倫','123456','test01@gmail.com','M','1987-07-05',NULL,NULL,NULL,NULL,0,'Customer'),
 ('A123456798','狄樺傑','123456','lunar@gmail.com','M','1990-07-02',NULL,NULL,0,NULL,0,'Customer'),
 ('A223456781','蔡六月','123456','junejune@gmail.com','F','1989-09-06','0965124588','台中市火車站附近有貓的地方',0,'O',15,'VIP');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;


--
-- Definition of table `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `unit_price` double NOT NULL,
  `sotck` int(11) NOT NULL DEFAULT '0',
  `description` varchar(200) DEFAULT NULL,
  `photo_url` varchar(200) DEFAULT NULL,
  `discount` int(3) NOT NULL DEFAULT '0',
  `class_name` varchar(20) NOT NULL DEFAULT 'Product',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `products`
--

/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` (`id`,`name`,`unit_price`,`sotck`,`description`,`photo_url`,`discount`,`class_name`) VALUES 
 (1,'珠友 Leader 大小通吃可調式多功能 削鉛筆機/色鉛筆機',205,0,NULL,NULL,0,'Product'),
 (2,'Java SE11與Android 9.x程式設計範例教本',560,10,'這裡是description','這裡是url',21,'Outlet');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
