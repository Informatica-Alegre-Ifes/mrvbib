-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: localhost    Database: MRVBIB
-- ------------------------------------------------------
-- Server version	5.7.24-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `DADO_GPS`
--

DROP TABLE IF EXISTS `DADO_GPS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DADO_GPS` (
  `DADO_ID_DADO_GPS` int(11) NOT NULL AUTO_INCREMENT,
  `DADO_CD_MAC_DISPOSITIVO` char(17) NOT NULL,
  `DADO_VL_LATITUDE` decimal(10,6) NOT NULL,
  `DADO_SG_ORIENTACAO_LATITUDE` char(1) NOT NULL,
  `DADO_VL_LONGITUDE` decimal(10,6) NOT NULL,
  `DADO_SG_ORIENTACAO_LONGITUDE` char(1) NOT NULL,
  `DADO_VL_VELOCIDADE` decimal(10,6) NOT NULL,
  `DADO_DT_CAPTURA` datetime NOT NULL,
  `DADO_DT_REGISTRO` datetime NOT NULL,
  PRIMARY KEY (`DADO_ID_DADO_GPS`)
) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DADO_GPS`
--

LOCK TABLES `DADO_GPS` WRITE;
/*!40000 ALTER TABLE `DADO_GPS` DISABLE KEYS */;
INSERT INTO `DADO_GPS` VALUES (4,'76-E0-D5-53-91-B0',2045.458300,'S',4127.651870,'W',0.080000,'2018-11-24 22:45:25','2018-11-24 20:45:55'),(5,'76-E0-D5-53-91-B0',2045.465480,'S',4127.666220,'W',13.210000,'2018-11-24 22:47:35','2018-11-24 20:47:57'),(6,'76-E0-D5-53-91-B0',2045.650970,'S',4127.522200,'W',34.450000,'2018-11-24 22:48:36','2018-11-24 20:48:58'),(7,'76-E0-D5-53-91-B0',2045.740750,'S',4127.167980,'W',40.280000,'2018-11-24 22:49:37','2018-11-24 20:49:59'),(8,'76-E0-D5-53-91-B0',2045.941480,'S',4126.803920,'W',45.960000,'2018-11-24 22:50:38','2018-11-24 20:51:01'),(9,'CA-1D-AD-31-B2-D3',2047.770190,'S',4124.066510,'W',53.500000,'2018-11-24 22:56:44','2018-11-24 20:57:06'),(10,'CA-1D-AD-31-B2-D3',2047.491750,'S',4123.668620,'W',53.560000,'2018-11-24 22:57:45','2018-11-24 20:58:07'),(11,'CA-1D-AD-31-B2-D3',2047.452910,'S',4123.300200,'W',33.850000,'2018-11-24 22:58:46','2018-11-24 20:59:09'),(12,'CA-1D-AD-31-B2-D3',2047.601040,'S',4122.764830,'W',50.440000,'2018-11-24 22:59:47','2018-11-24 21:00:09'),(13,'CA-1D-AD-31-B2-D3',2047.774190,'S',4122.447190,'W',44.230000,'2018-11-24 23:00:48','2018-11-24 21:01:10'),(14,'16-9D-E6-24-A5-48',2047.995780,'S',4122.284910,'W',19.180000,'2018-11-24 23:01:46','2018-11-24 21:02:11'),(15,'B8-27-EB-53-8D-AC',2047.670620,'S',4122.629260,'W',36.830000,'2018-11-24 23:03:50','2018-11-24 21:04:15'),(16,'B6-35-FE-76-4D-B2',2047.493000,'S',4123.023730,'W',51.650000,'2018-11-24 23:04:51','2018-11-24 21:05:16'),(17,'B6-35-FE-76-4D-B2',2047.415450,'S',4123.454030,'W',39.240000,'2018-11-24 23:05:52','2018-11-24 21:06:17'),(18,'B6-35-FE-76-4D-B2',2047.595420,'S',4123.826110,'W',49.990000,'2018-11-24 23:06:53','2018-11-24 21:07:18'),(19,'B6-35-FE-76-4D-B2',2047.773680,'S',4124.156350,'W',19.760000,'2018-11-24 23:07:54','2018-11-24 21:08:19'),(20,'62-1C-6D-AD-20-E1',2045.467050,'S',4127.660370,'W',0.787000,'2018-11-24 20:29:58','2018-11-26 19:39:27'),(21,'62-1C-6D-AD-20-E1',2045.457030,'S',4127.655030,'W',0.013000,'2018-11-24 20:31:07','2018-11-26 19:39:27'),(22,'62-1C-6D-AD-20-E1',2045.457310,'S',4127.654460,'W',0.015000,'2018-11-24 20:32:08','2018-11-26 19:39:27'),(23,'62-1C-6D-AD-20-E1',2045.456670,'S',4127.655930,'W',0.022000,'2018-11-24 20:33:09','2018-11-26 19:39:27'),(24,'62-1C-6D-AD-20-E1',2045.458690,'S',4127.654540,'W',0.021000,'2018-11-24 20:34:10','2018-11-26 19:39:27'),(25,'76-E0-D5-53-91-B0',2045.458300,'S',4127.651870,'W',0.043000,'2018-11-24 20:45:25','2018-11-26 19:39:28'),(26,'76-E0-D5-53-91-B0',2045.459390,'S',4127.655400,'W',0.023000,'2018-11-24 20:46:34','2018-11-26 19:39:28'),(27,'76-E0-D5-53-91-B0',2045.465480,'S',4127.666220,'W',7.132000,'2018-11-24 20:47:35','2018-11-26 19:39:28'),(28,'76-E0-D5-53-91-B0',2045.650970,'S',4127.522200,'W',18.601000,'2018-11-24 20:48:36','2018-11-26 19:39:28'),(29,'76-E0-D5-53-91-B0',2045.740750,'S',4127.167980,'W',21.749000,'2018-11-24 20:49:37','2018-11-26 19:39:28'),(30,'76-E0-D5-53-91-B0',2045.941480,'S',4126.803920,'W',24.817000,'2018-11-24 20:50:38','2018-11-26 19:39:28'),(31,'76-6B-B1-37-35-18',2046.129020,'S',4126.305200,'W',33.648000,'2018-11-24 20:51:39','2018-11-26 19:39:28'),(32,'76-6B-B1-37-35-18',2046.224990,'S',4125.615510,'W',38.857000,'2018-11-24 20:52:40','2018-11-26 19:39:28'),(33,'76-6B-B1-37-35-18',2046.662880,'S',4125.143130,'W',39.829000,'2018-11-24 20:53:41','2018-11-26 19:39:28'),(34,'76-6B-B1-37-35-18',2047.130840,'S',4124.710710,'W',35.611000,'2018-11-24 20:54:42','2018-11-26 19:39:28'),(35,'76-6B-B1-37-35-18',2047.641810,'S',4124.408690,'W',33.392000,'2018-11-24 20:55:43','2018-11-26 19:39:28'),(36,'CA-1D-AD-31-B2-D3',2047.770190,'S',4124.066510,'W',28.888000,'2018-11-24 20:56:44','2018-11-26 19:39:28'),(37,'CA-1D-AD-31-B2-D3',2047.491750,'S',4123.668620,'W',28.921000,'2018-11-24 20:57:45','2018-11-26 19:39:28'),(38,'CA-1D-AD-31-B2-D3',2047.452910,'S',4123.300200,'W',18.277000,'2018-11-24 20:58:46','2018-11-26 19:39:28'),(39,'CA-1D-AD-31-B2-D3',2047.601040,'S',4122.764830,'W',27.237000,'2018-11-24 20:59:47','2018-11-26 19:39:28'),(40,'CA-1D-AD-31-B2-D3',2047.774190,'S',4122.447190,'W',23.882000,'2018-11-24 21:00:48','2018-11-26 19:39:28'),(41,'16-9D-E6-24-A5-48',2047.995780,'S',4122.284910,'W',10.355000,'2018-11-24 21:01:46','2018-11-26 19:39:28'),(42,'16-9D-E6-24-A5-48',2047.885410,'S',4122.364810,'W',16.270000,'2018-11-24 21:02:47','2018-11-26 19:39:28'),(43,'B8-27-EB-53-8D-AC',2047.670620,'S',4122.629260,'W',19.885000,'2018-11-24 21:03:50','2018-11-26 19:39:29'),(44,'B6-35-FE-76-4D-B2',2047.493000,'S',4123.023730,'W',27.888000,'2018-11-24 21:04:51','2018-11-26 19:39:29'),(45,'B6-35-FE-76-4D-B2',2047.415450,'S',4123.454030,'W',21.189000,'2018-11-24 21:05:52','2018-11-26 19:39:29'),(46,'B6-35-FE-76-4D-B2',2047.595420,'S',4123.826110,'W',26.992000,'2018-11-24 21:06:53','2018-11-26 19:39:29'),(47,'B6-35-FE-76-4D-B2',2047.773680,'S',4124.156350,'W',10.672000,'2018-11-24 21:07:54','2018-11-26 19:39:29'),(48,'B6-35-FE-76-4D-B2',2047.582010,'S',4124.475430,'W',33.006000,'2018-11-24 21:08:55','2018-11-26 19:39:29'),(49,'B6-35-FE-76-4D-B2',2047.041080,'S',4124.728860,'W',36.165000,'2018-11-24 21:09:56','2018-11-26 19:39:29'),(50,'FE-27-EF-F9-AB-FC',2046.623230,'S',4125.163290,'W',30.821000,'2018-11-24 21:10:57','2018-11-26 19:39:29'),(51,'FE-27-EF-F9-AB-FC',2046.170530,'S',4125.665970,'W',45.487000,'2018-11-24 21:11:58','2018-11-26 19:39:29'),(52,'FE-27-EF-F9-AB-FC',2046.117880,'S',4126.425660,'W',41.719000,'2018-11-24 21:12:59','2018-11-26 19:39:29'),(53,'FE-27-EF-F9-AB-FC',2045.789120,'S',4126.936660,'W',36.848000,'2018-11-24 21:14:00','2018-11-26 19:39:29'),(54,'FE-27-EF-F9-AB-FC',2045.651490,'S',4127.483950,'W',26.230000,'2018-11-24 21:15:01','2018-11-26 19:39:29'),(55,'3E-1D-28-46-E5-55',2045.448150,'S',4127.629730,'W',5.585000,'2018-11-24 21:16:02','2018-11-26 19:39:29'),(56,'3E-1D-28-46-E5-55',2045.504820,'S',4127.583820,'W',0.029000,'2018-11-24 21:17:03','2018-11-26 19:39:29'),(57,'3E-1D-28-46-E5-55',2045.557010,'S',4127.623180,'W',5.418000,'2018-11-24 21:18:04','2018-11-26 19:39:29'),(58,'3E-1D-28-46-E5-55',2045.525570,'S',4127.673010,'W',24.732000,'2018-11-24 21:19:05','2018-11-26 19:39:29'),(59,'3E-1D-28-46-E5-55',2045.691480,'S',4127.362500,'W',23.075000,'2018-11-24 21:20:06','2018-11-26 19:39:30'),(60,'B6-39-9C-D3-13-CB',2045.856890,'S',4126.846600,'W',32.314000,'2018-11-24 21:21:07','2018-11-26 19:39:30'),(61,'B6-39-9C-D3-13-CB',2046.130750,'S',4126.284050,'W',42.127000,'2018-11-24 21:22:08','2018-11-26 19:39:30'),(62,'B6-39-9C-D3-13-CB',2046.276970,'S',4125.583110,'W',41.643000,'2018-11-24 21:23:09','2018-11-26 19:39:30'),(63,'B6-39-9C-D3-13-CB',2046.790670,'S',4125.034880,'W',45.720000,'2018-11-24 21:24:10','2018-11-26 19:39:30'),(64,'B6-39-9C-D3-13-CB',2047.322450,'S',4124.658210,'W',30.186000,'2018-11-24 21:25:11','2018-11-26 19:39:30'),(65,'E6-FE-97-B4-A1-17',2047.694900,'S',4124.351910,'W',26.855000,'2018-11-24 21:26:12','2018-11-26 19:39:30'),(66,'E6-FE-97-B4-A1-17',2047.773800,'S',4124.072400,'W',20.123000,'2018-11-24 21:27:13','2018-11-26 19:39:30'),(67,'E6-FE-97-B4-A1-17',2047.534980,'S',4123.724670,'W',30.914000,'2018-11-24 21:28:14','2018-11-26 19:39:30'),(68,'E6-FE-97-B4-A1-17',2047.442040,'S',4123.353580,'W',26.473000,'2018-11-24 21:29:15','2018-11-26 19:39:30'),(69,'E6-FE-97-B4-A1-17',2047.527060,'S',4122.891960,'W',32.012000,'2018-11-24 21:30:16','2018-11-26 19:39:30'),(70,'E6-FE-97-B4-A1-17',2047.687580,'S',4122.583220,'W',0.057000,'2018-11-24 21:31:17','2018-11-26 19:39:30'),(71,'5E-E8-6F-E1-71-0D',2047.688840,'S',4123.942430,'W',9.418000,'2018-11-26 14:32:06','2018-11-26 19:39:30'),(72,'5E-E8-6F-E1-71-0D',2047.728860,'S',4124.289560,'W',17.290000,'2018-11-26 14:33:13','2018-11-26 19:39:30'),(73,'5E-E8-6F-E1-71-0D',2047.112670,'S',4124.707470,'W',51.090000,'2018-11-26 14:34:11','2018-11-26 19:39:30'),(74,'5E-E8-6F-E1-71-0D',2046.552100,'S',4125.236790,'W',40.439000,'2018-11-26 14:35:12','2018-11-26 19:39:30'),(75,'16-16-AA-99-F8-91',2046.109140,'S',4125.958980,'W',54.626000,'2018-11-26 14:36:13','2018-11-26 19:39:30'),(76,'16-16-AA-99-F8-91',2045.885750,'S',4126.826530,'W',45.488000,'2018-11-26 14:37:14','2018-11-26 19:39:30'),(77,'16-16-AA-99-F8-91',2045.649660,'S',4127.468290,'W',18.626000,'2018-11-26 14:38:15','2018-11-26 19:39:30'),(78,'16-16-AA-99-F8-91',2045.457100,'S',4127.291040,'W',9.631000,'2018-11-26 14:39:16','2018-11-26 19:39:30'),(79,'16-16-AA-99-F8-91',2045.380360,'S',4127.119880,'W',13.900000,'2018-11-26 14:40:17','2018-11-26 19:39:30'),(80,'B8-27-EB-53-8D-AC',2047.598520,'S',4122.794000,'W',0.463000,'2018-11-26 19:39:00','2018-11-26 19:39:31'),(81,'5A-18-66-DC-5E-B6',2047.471130,'S',4123.146110,'W',50.290000,'2018-11-26 21:42:07','2018-11-26 19:42:30'),(82,'5A-18-66-DC-5E-B6',2047.364530,'S',4123.440350,'W',0.060000,'2018-11-26 21:44:09','2018-11-26 19:44:36'),(83,'5A-18-66-DC-5E-B6',2047.361940,'S',4123.441430,'W',1.220000,'2018-11-26 21:45:10','2018-11-26 19:45:32'),(84,'5A-18-66-DC-5E-B6',2047.368830,'S',4123.438240,'W',0.010000,'2018-11-26 21:46:10','2018-11-26 19:46:33'),(85,'5A-18-66-DC-5E-B6',2047.371120,'S',4123.437250,'W',0.040000,'2018-11-26 21:47:08','2018-11-26 19:47:33'),(86,'4E-7B-AB-C7-72-08',2048.050920,'S',4122.243370,'W',24.470000,'2018-11-27 00:07:29','2018-11-26 22:07:57'),(87,'4E-7B-AB-C7-72-08',2048.121030,'S',4121.866150,'W',54.780000,'2018-11-27 00:08:36','2018-11-26 22:09:01'),(88,'2A-AE-60-8E-FA-0A',2050.147190,'S',4109.473030,'W',1.860000,'2018-11-27 02:08:30','2018-11-27 00:09:00'),(89,'2A-AE-60-8E-FA-0A',2050.150510,'S',4109.469390,'W',0.130000,'2018-11-27 02:09:34','2018-11-27 00:09:59'),(90,'2A-AE-60-8E-FA-0A',2049.874000,'S',4109.436710,'W',48.470000,'2018-11-27 02:10:35','2018-11-27 00:11:00'),(91,'92-01-3C-E8-A5-CC',2049.412630,'S',4109.362220,'W',58.600000,'2018-11-27 02:11:36','2018-11-27 00:12:01'),(92,'92-01-3C-E8-A5-CC',2048.819250,'S',4109.479670,'W',65.600000,'2018-11-27 02:12:37','2018-11-27 00:13:02'),(93,'02-16-DD-4F-1F-5C',2045.445170,'S',4113.545570,'W',95.990000,'2018-11-27 02:20:45','2018-11-27 00:21:10'),(94,'06-2C-B8-4C-52-1D',2045.484130,'S',4114.521110,'W',95.010000,'2018-11-27 02:21:46','2018-11-27 00:22:11'),(95,'06-2C-B8-4C-52-1D',2045.485690,'S',4115.517890,'W',109.430000,'2018-11-27 02:22:47','2018-11-27 00:23:12'),(96,'06-2C-B8-4C-52-1D',2045.893540,'S',4116.237930,'W',84.640000,'2018-11-27 02:23:48','2018-11-27 00:24:13'),(97,'82-5E-AF-C1-B6-AE',2047.891460,'S',4122.358410,'W',38.320000,'2018-11-27 02:32:58','2018-11-27 00:33:24'),(98,'82-5E-AF-C1-B6-AE',2047.684080,'S',4122.599000,'W',21.850000,'2018-11-27 02:33:59','2018-11-27 00:34:24'),(99,'82-5E-AF-C1-B6-AE',2047.465820,'S',4123.176600,'W',81.670000,'2018-11-27 02:35:00','2018-11-27 00:35:25'),(100,'82-5E-AF-C1-B6-AE',2047.765540,'S',4124.084890,'W',50.010000,'2018-11-27 02:37:03','2018-11-27 00:37:28'),(101,'EE-A9-9C-50-2F-F8',2046.122470,'S',4126.217130,'W',97.870000,'2018-11-27 02:41:07','2018-11-27 00:41:32'),(102,'02-B5-9D-13-C2-BA',2045.739330,'S',4127.051620,'W',9.630000,'2018-11-27 02:43:09','2018-11-27 00:43:34'),(103,'02-B5-9D-13-C2-BA',2045.634000,'S',4127.054330,'W',0.120000,'2018-11-27 02:45:11','2018-11-27 00:45:36'),(104,'02-B5-9D-13-C2-BA',2045.622240,'S',4127.136400,'W',14.070000,'2018-11-27 02:47:13','2018-11-27 00:47:38'),(105,'FA-69-5A-C1-59-0A',2045.567240,'S',4127.245680,'W',10.920000,'2018-11-27 02:48:14','2018-11-27 00:48:39'),(106,'FA-69-5A-C1-59-0A',2045.546770,'S',4127.300020,'W',11.790000,'2018-11-27 02:50:16','2018-11-27 00:50:41'),(107,'FA-69-5A-C1-59-0A',2045.610500,'S',4127.615760,'W',51.750000,'2018-11-27 02:52:18','2018-11-27 00:52:43'),(108,'66-D7-07-6B-C6-95',2044.912090,'S',4128.421630,'W',0.880000,'2018-11-27 02:54:20','2018-11-27 00:54:45'),(109,'66-D7-07-6B-C6-95',2044.910120,'S',4128.425720,'W',0.240000,'2018-11-27 02:56:22','2018-11-27 00:56:47'),(110,'56-E4-BD-29-F7-7B',2044.907150,'S',4128.416050,'W',0.140000,'2018-11-27 02:58:24','2018-11-27 00:58:49'),(111,'56-E4-BD-29-F7-7B',2044.911420,'S',4128.416290,'W',0.100000,'2018-11-27 03:00:26','2018-11-27 01:00:51'),(112,'56-E4-BD-29-F7-7B',2044.911590,'S',4128.419700,'W',0.160000,'2018-11-27 03:01:27','2018-11-27 01:01:52'),(113,'56-E4-BD-29-F7-7B',2044.911780,'S',4128.423340,'W',0.030000,'2018-11-27 03:02:28','2018-11-27 01:02:53'),(114,'56-E4-BD-29-F7-7B',2044.914720,'S',4128.421800,'W',0.070000,'2018-11-27 03:03:29','2018-11-27 01:03:54'),(115,'CA-83-00-3D-46-80',2044.926870,'S',4128.456740,'W',6.810000,'2018-11-27 03:05:31','2018-11-27 01:05:56'),(116,'CA-83-00-3D-46-80',2045.098380,'S',4127.837850,'W',50.580000,'2018-11-27 03:07:33','2018-11-27 01:07:58'),(117,'CA-83-00-3D-46-80',2045.515750,'S',4127.675110,'W',51.730000,'2018-11-27 03:08:34','2018-11-27 01:08:59'),(118,'06-10-7B-62-85-EA',2045.814110,'S',4126.890950,'W',48.320000,'2018-11-27 03:10:36','2018-11-27 01:11:01'),(119,'06-10-7B-62-85-EA',2046.119120,'S',4125.973010,'W',59.170000,'2018-11-27 03:12:38','2018-11-27 01:13:05'),(120,'D2-01-38-33-6D-8A',2047.783920,'S',4124.165970,'W',30.020000,'2018-11-27 03:17:43','2018-11-27 01:18:08'),(121,'C6-2E-C0-EE-D3-DF',2047.437710,'S',4123.580990,'W',38.010000,'2018-11-27 03:19:45','2018-11-27 01:20:10'),(122,'C6-2E-C0-EE-D3-DF',2047.536100,'S',4122.867980,'W',41.550000,'2018-11-27 03:21:47','2018-11-27 01:22:12'),(123,'B8-27-EB-53-8D-AC',2047.908990,'S',4122.348640,'W',37.360000,'2018-11-27 03:23:49','2018-11-27 01:24:14'),(124,'A2-62-C1-AB-57-CD',2047.820660,'S',4122.430750,'W',21.590000,'2018-11-27 03:25:51','2018-11-27 01:26:16'),(125,'B8-27-EB-53-8D-AC',2047.681840,'S',4122.580880,'W',0.000000,'2018-11-27 03:27:53','2018-11-27 01:28:18'),(126,'46-17-3B-A0-BA-9F',2047.451330,'S',4123.263860,'W',28.740000,'2018-11-27 15:37:59','2018-11-27 13:38:31'),(127,'46-17-3B-A0-BA-9F',2047.420950,'S',4123.517890,'W',24.470000,'2018-11-27 15:39:07','2018-11-27 13:39:29'),(128,'46-17-3B-A0-BA-9F',2047.619060,'S',4123.855550,'W',34.840000,'2018-11-27 15:40:08','2018-11-27 13:40:30'),(129,'46-17-3B-A0-BA-9F',2047.773730,'S',4124.120660,'W',31.120000,'2018-11-27 15:41:09','2018-11-27 13:41:31'),(130,'2A-7E-EB-57-DF-1D',2045.816680,'S',4126.875700,'W',78.970000,'2018-11-27 15:46:11','2018-11-27 13:46:35'),(131,'2A-7E-EB-57-DF-1D',2045.627150,'S',4127.458730,'W',31.100000,'2018-11-27 15:47:12','2018-11-27 13:47:37'),(132,'3A-FE-B4-FE-70-89',2045.459120,'S',4127.297950,'W',25.810000,'2018-11-27 15:48:13','2018-11-27 13:48:38'),(133,'3A-FE-B4-FE-70-89',2045.384490,'S',4127.115550,'W',25.930000,'2018-11-27 15:49:14','2018-11-27 13:49:39'),(134,'3A-FE-B4-FE-70-89',2045.357490,'S',4127.078790,'W',0.070000,'2018-11-27 15:50:15','2018-11-27 13:50:40'),(135,'62-72-C1-DE-AD-6C',2047.312130,'S',4123.486560,'W',11.680000,'2018-11-27 20:02:10','2018-11-27 18:02:32'),(136,'62-72-C1-DE-AD-6C',2047.313620,'S',4123.453890,'W',0.570000,'2018-11-27 20:03:11','2018-11-27 18:03:33'),(137,'96-DD-5A-CB-5A-70',2047.309040,'S',4123.448170,'W',0.130000,'2018-11-27 20:04:09','2018-11-27 18:04:34'),(138,'96-DD-5A-CB-5A-70',2047.307580,'S',4123.445690,'W',0.470000,'2018-11-27 20:05:10','2018-11-27 18:05:35'),(139,'96-DD-5A-CB-5A-70',2047.310620,'S',4123.444730,'W',0.060000,'2018-11-27 20:06:11','2018-11-27 18:06:36'),(140,'96-DD-5A-CB-5A-70',2047.312600,'S',4123.444400,'W',0.130000,'2018-11-27 20:07:12','2018-11-27 18:07:37'),(141,'96-DD-5A-CB-5A-70',2047.309730,'S',4123.447410,'W',0.020000,'2018-11-27 20:09:14','2018-11-27 18:09:40'),(142,'AE-50-3E-51-D7-C2',2047.312090,'S',4123.448080,'W',0.140000,'2018-11-27 20:10:15','2018-11-27 18:10:43'),(143,'AE-50-3E-51-D7-C2',2047.312790,'S',4123.446210,'W',4.190000,'2018-11-27 20:11:16','2018-11-27 18:11:41'),(144,'AE-50-3E-51-D7-C2',2047.483630,'S',4122.919570,'W',58.940000,'2018-11-27 20:12:17','2018-11-27 18:12:42'),(145,'B8-27-EB-53-8D-AC',2047.689900,'S',4122.584800,'W',0.050000,'2018-11-27 20:23:28','2018-11-27 18:23:53'),(146,'B8-27-EB-53-8D-AC',2047.688650,'S',4122.584770,'W',0.090000,'2018-11-27 20:24:29','2018-11-27 18:25:15'),(147,'B8-27-EB-53-8D-AC',2047.684590,'S',4122.583330,'W',0.090000,'2018-11-27 20:25:30','2018-11-27 18:25:55'),(148,'B8-27-EB-53-8D-AC',2047.683260,'S',4122.584310,'W',0.030000,'2018-11-27 20:26:31','2018-11-27 18:26:56'),(149,'B8-27-EB-53-8D-AC',2047.682160,'S',4122.584330,'W',0.000000,'2018-11-27 20:27:32','2018-11-27 18:27:57'),(150,'B8-27-EB-53-8D-AC',2047.681010,'S',4122.585410,'W',0.030000,'2018-11-27 20:28:33','2018-11-27 18:28:58'),(151,'8A-CD-58-8B-CA-EE',2047.500690,'S',4123.015720,'W',81.120000,'2018-11-27 20:30:35','2018-11-27 18:31:00'),(152,'8A-CD-58-8B-CA-EE',2047.417490,'S',4123.516100,'W',23.840000,'2018-11-27 20:31:36','2018-11-27 18:32:01'),(153,'8A-CD-58-8B-CA-EE',2047.698830,'S',4123.951170,'W',47.300000,'2018-11-27 20:32:37','2018-11-27 18:33:02'),(154,'5A-45-E8-99-C5-E3',2045.480970,'S',4127.257010,'W',0.100000,'2018-11-27 21:43:11','2018-11-27 19:43:33'),(155,'5A-45-E8-99-C5-E3',2045.481910,'S',4127.261630,'W',14.710000,'2018-11-27 21:44:09','2018-11-27 19:44:34'),(156,'AE-59-05-D3-0B-C0',2045.805760,'S',4126.903010,'W',62.010000,'2018-11-28 00:08:17','2018-11-27 22:08:39'),(157,'DA-4C-54-72-EA-0A',2047.685860,'S',4124.357320,'W',59.980000,'2018-11-28 00:13:22','2018-11-27 22:13:44'),(158,'DA-4C-54-72-EA-0A',2047.724410,'S',4123.976630,'W',57.240000,'2018-11-28 00:14:20','2018-11-27 22:14:45'),(159,'DA-4C-54-72-EA-0A',2047.433670,'S',4123.575660,'W',54.200000,'2018-11-28 00:15:21','2018-11-27 22:15:46'),(160,'56-05-E9-4F-8F-6F',2047.470540,'S',4123.177640,'W',61.880000,'2018-11-28 00:16:22','2018-11-27 22:16:47'),(161,'56-05-E9-4F-8F-6F',2047.682440,'S',4122.619790,'W',50.400000,'2018-11-28 00:17:23','2018-11-27 22:17:48');
/*!40000 ALTER TABLE `DADO_GPS` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER TRG_DADO_GPS_INS
BEFORE INSERT ON DADO_GPS
FOR EACH ROW
BEGIN
	DECLARE MENSAGEM_ERRO VARCHAR(128);
	DECLARE DADO_GPS_ENCONTRADO INT;
	SELECT COUNT(1) INTO DADO_GPS_ENCONTRADO FROM DADO_GPS WHERE NEW.DADO_CD_MAC_DISPOSITIVO = DADO_CD_MAC_DISPOSITIVO AND NEW.DADO_DT_CAPTURA = DADO_DT_CAPTURA;
	IF DADO_GPS_ENCONTRADO > 0 THEN
		SET MENSAGEM_ERRO = CONCAT('TRG_DADO_GPS_INS - [DADO_CD_MAC_DISPOSITIVO] E [DADO_DT_CAPTURA] JÁ EXISTEM PARA: ', NEW.DADO_CD_MAC_DISPOSITIVO, ' E ', CAST(NEW.DADO_DT_CAPTURA AS CHAR), '.');
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = MENSAGEM_ERRO;
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-12 19:46:19
