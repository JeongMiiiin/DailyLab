-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: dailylab-db.cuvkk1xkflwb.ap-northeast-2.rds.amazonaws.com    Database: dailylab
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` bigint NOT NULL AUTO_INCREMENT,
  `birthday` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `exit_date` datetime(6) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `goal` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `join_date` datetime(6) DEFAULT NULL,
  `mbti_id` bigint DEFAULT '1',
  `provider` varchar(255) DEFAULT NULL,
  `religion` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (2,'1997-09-13','xkagja2006@naver.com',NULL,'F','\"취업하기\"','\"무직\"','2023-09-29 22:58:20.210824',71,'kakao','\"무교\"','이지영'),(3,'1997-08-25','rkdehdvy315@nate.com',NULL,'M',NULL,'\"무직\"','2023-09-18 15:15:11.739353',49,'kakao','\"무교\"','강동표'),(4,'1998-10-11','yun12343@naver.com',NULL,'M','\"외식 하기\"','\"학생\"','2023-09-18 17:21:48.094379',54,'kakao','\"무교\"','박세윤'),(5,'1998-10-11','yun12343@naver.com',NULL,'M','\"휴식\"','\"학생\"','2023-09-28 22:48:24.070042',69,'naver','\"힌두교\"','박세윤'),(10,'1998-12-02','tkfkd122@naver.com',NULL,'F',NULL,'\"무직\"','2023-09-22 12:51:05.083354',53,'kakao','\"기독교\"','김주하'),(14,'2023-10-01','k3371548@naver.com',NULL,'M','\"ㄱㄴㄷ\"','\"학생\"','2023-09-22 22:55:58.243466',69,'kakao','\"힌두교\"','정민'),(15,'2023-09-12','xkagja2006@naver.com',NULL,'M',NULL,'\"학생\"','2023-09-22 14:56:56.057544',81,'naver','\"무교\"','이지영'),(16,'2023-09-21','xkagja2006@gmail.com',NULL,'F',NULL,NULL,'2023-09-22 14:57:11.501293',1,'google',NULL,'이지영'),(17,'2023-09-11','wnsdud12365@naver.com',NULL,'M','\"\"','\"학생\"','2023-09-24 18:02:24.316521',68,'naver','\"무교\"','전준영'),(18,'1980-01-17','ssafy.seyoung@gmail.com',NULL,'M',NULL,NULL,'2023-09-26 10:01:12.680119',1,'google',NULL,'박세영 ([컨설턴트] 박세영)'),(19,'1960-07-16','sdafsdq@naver.com',NULL,'M','오픽 IH','무직','2023-09-01 16:11:22.000000',24,'google','무교','이정재'),(20,'1971-02-05','sdafsdw@naver.com',NULL,'F','변비 완치','직장인','2023-09-01 16:11:22.000000',13,'google','원불교','남궁민수'),(21,'1982-03-04','sdafsde@naver.com',NULL,'M','취직','무직','2023-09-01 16:11:22.000000',2,'google','기독교','이순신'),(22,'1993-07-03','sdafsdr@naver.com',NULL,'F','','학생','2023-09-01 16:11:22.000000',31,'google','불교','마크주커버그'),(23,'2004-09-02','sdafsdt@naver.com',NULL,'M','','직장인','2023-09-01 16:11:22.000000',30,'google','천주교','일론머스크'),(24,'2015-11-01','sdafsdy@naver.com',NULL,'F','','무직','2023-09-01 16:11:22.000000',19,'google','원불교','PT바넘'),(25,'2006-01-12','sdafsdu@naver.com',NULL,'M','','무직','2023-09-01 16:11:22.000000',18,'google','원불교','이재용'),(26,'1997-12-14','sdafsdi@naver.com',NULL,'F','','직장인','2023-09-01 16:11:22.000000',27,'google','원불교','싸버지'),(27,'1988-03-13','sdafsdo@naver.com',NULL,'M','','무직','2023-09-01 16:11:22.000000',6,'google','원불교','포스트말론'),(28,'1997-10-01','sdafsdp@naver.com',NULL,'F','','학생','2023-09-01 16:11:22.000000',5,'google','원불교','이안'),(29,'1960-04-13','sdafsds@naver.com',NULL,'M','','직장인','2023-09-01 16:11:22.000000',14,'google','기독교','엠마'),(30,'1951-07-01','sdafsdd@naver.com',NULL,'F','','무직','2023-09-01 16:11:22.000000',23,'google','무교','클로에'),(31,'1942-04-09','sdafsdf@naver.com',NULL,'M','','직장인','2023-09-01 16:11:22.000000',22,'google','무교','마르코'),(32,'2003-01-21','sdafsdg@naver.com',NULL,'F','','무직','2023-09-01 16:11:22.000000',11,'google','기독교','디에고'),(33,'1994-04-17','sdafsdh@naver.com',NULL,'F','','직장인','2023-09-01 16:11:22.000000',2,'google','무교','코코'),(34,'1995-12-15','sdafsdj@naver.com',NULL,'M','','직장인','2023-09-01 16:11:22.000000',9,'google','기독교','황정민'),(35,'1998-12-02','zegalmari@naver.com',NULL,'F','','학생','2023-09-01 16:11:22.000000',53,'google','기독교','제갈마리'),(37,'1980-01-17','ddalkuk@kakao.com',NULL,'M',NULL,NULL,'2023-09-26 14:02:48.564441',1,'kakao',NULL,'세영'),(39,'1995-02-06','wnsdud124894@gmail.com',NULL,'M',NULL,NULL,'2023-09-26 16:00:08.073119',1,'google',NULL,'전준영'),(40,'1997-12-29','wnsdud12365@naver.com',NULL,'M','\"취업!!\"','\"무직\"','2023-09-26 16:02:55.444117',53,'kakao','\"무교\"','전준영'),(41,'2023-09-12','song960705@naver.com',NULL,'M',NULL,NULL,'2023-09-27 08:40:47.687644',53,'kakao',NULL,'찬환'),(42,'1997-08-25','rkdehdvy315@gmail.com',NULL,'M','\"취업하기\"','\"무직\"','2023-09-29 19:43:20.821061',51,'google','\"무교\"','강동표'),(43,'1997-10-13','xkagja2020@gmail.com',NULL,'F',NULL,NULL,'2023-10-01 16:09:11.107264',1,'google',NULL,'이지영'),(47,'2023-10-01','k3371548@naver.com',NULL,'M',NULL,NULL,'2023-10-01 21:46:16.645209',55,'naver',NULL,'정민'),(49,'1997-12-29','meoldae188@gmail.com',NULL,'M',NULL,'\"학생\"','2023-10-01 21:54:06.406926',54,'google','\"무교\"','전준영'),(50,'2000-02-02',NULL,NULL,'M',NULL,NULL,'2023-10-04 13:10:00.496139',28,'kakao',NULL,'정민'),(51,'2023-10-02','k3371548@gmail.com',NULL,'M',NULL,NULL,'2023-10-03 17:44:59.653662',53,'google','\"무교\"','정민'),(52,'1999-03-03','dayoung100@naver.com',NULL,'F','\"잘살기\"','\"무직\"','2023-10-04 12:13:15.865136',53,'kakao',NULL,'이다영'),(53,'1998-09-09','wjdgusaho@naver.com',NULL,'M',NULL,'\"학생\"','2023-10-04 12:16:15.623007',71,'kakao','\"기독교\"','정현모'),(55,'1998-10-11','youn12343@gmail.com',NULL,'M',NULL,NULL,'2023-10-04 12:52:24.467541',1,'google',NULL,'박세윤'),(56,'1996-01-20','wjd5126@naver.com',NULL,'M','\"취업\"','\"직장인\"','2023-10-04 13:12:21.989894',78,'kakao','\"무교\"','정형준'),(57,'1998-09-05','dragontig98@naver.com',NULL,'M','\"삼성입사\"','\"학생\"','2023-10-04 13:22:02.576428',68,'kakao','\"천주교\"','김용범');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-04 17:49:08
