CREATE DATABASE  IF NOT EXISTS `mathumi` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `mathumi`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: mathumi
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `invitation_entity`
--

DROP TABLE IF EXISTS `invitation_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `invitation_entity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `entity_name` varchar(120) NOT NULL,
  `class_id` int(11) NOT NULL,
  `invitation_code` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `entity_name_UNIQUE` (`entity_name`),
  UNIQUE KEY `invitation_code_UNIQUE` (`invitation_code`),
  KEY `invitation_class_FK_idx` (`class_id`),
  CONSTRAINT `invitation_class_FK` FOREIGN KEY (`class_id`) REFERENCES `invitation_entity_class` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invitation_entity`
--

LOCK TABLES `invitation_entity` WRITE;
/*!40000 ALTER TABLE `invitation_entity` DISABLE KEYS */;
INSERT INTO `invitation_entity` VALUES (1,'test1',1,'testcode');
/*!40000 ALTER TABLE `invitation_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invitation_entity_class`
--

DROP TABLE IF EXISTS `invitation_entity_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `invitation_entity_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `class_name_UNIQUE` (`class_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invitation_entity_class`
--

LOCK TABLES `invitation_entity_class` WRITE;
/*!40000 ALTER TABLE `invitation_entity_class` DISABLE KEYS */;
INSERT INTO `invitation_entity_class` VALUES (1,'individual'),(2,'institution');
/*!40000 ALTER TABLE `invitation_entity_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mobile_vertification`
--

DROP TABLE IF EXISTS `mobile_vertification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `mobile_vertification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(45) NOT NULL,
  `vert_code` varchar(10) NOT NULL,
  `expiry_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `mobile_UNIQUE` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mobile_vertification`
--

LOCK TABLES `mobile_vertification` WRITE;
/*!40000 ALTER TABLE `mobile_vertification` DISABLE KEYS */;
INSERT INTO `mobile_vertification` VALUES (1,'+61451616187','9184','2018-11-12 01:46:55'),(3,'+61451616188','7431','2018-11-12 02:37:48'),(4,'','7184','2018-11-12 04:17:39'),(5,'451616187','5108','2018-11-12 22:25:49'),(6,'0451616187','6917','2018-11-12 21:37:24');
/*!40000 ALTER TABLE `mobile_vertification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password_salthash` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `logincookie` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `userid_UNIQUE` (`userid`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'yuantianyi','yty199432','TIANYI','YUAN','0451616187','963 Ferntree Gully Road,Wheelers Hill,3150,VIC,Melbourne','0c132fac858509bea684e5e1fb35e309'),(2,'test1','awdkauighuei.wfaafwa./2wda','Fizz','Peter','0451616465','123 test road','1'),(3,'test2','tyjwdkauwdaa./2wda','Cord','Shele','0451616122','31 apple road','1'),(4,'test3','25wdkauighuei.wfaafwa./2wda','Breery','Bran','0456616177','99 happy road','1'),(6,'test10.1368493202547254','awdkauighuei.wfaafwa./2wda','Fizz','Peter','0451616465','123 test road','1');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-13  9:12:36
