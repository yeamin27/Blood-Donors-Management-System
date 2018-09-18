-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: db_blood_donors
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.16.04.1

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
-- Table structure for table `TBL_DONORS`
--

DROP TABLE IF EXISTS `TBL_DONORS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TBL_DONORS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) NOT NULL,
  `FATHERS_NAME` varchar(30) NOT NULL,
  `MOTHERS_NAME` varchar(30) DEFAULT NULL,
  `GENDER` varchar(10) DEFAULT NULL,
  `AGE` int(11) DEFAULT NULL,
  `WEIGHT` varchar(10) DEFAULT NULL,
  `BLOOD_GROUP` varchar(10) NOT NULL,
  `MARITAL_STATUS` varchar(15) DEFAULT NULL,
  `NID` varchar(20) DEFAULT NULL,
  `LAST_DONATION` date DEFAULT NULL,
  `DISEASE` text,
  `MOBILE` varchar(15) DEFAULT NULL,
  `DIVISION` varchar(30) DEFAULT NULL,
  `DISTRICT` varchar(30) DEFAULT NULL,
  `ADDRESS` text,
  `IMAGE` longblob,
  PRIMARY KEY (`ID`)
) AUTO_INCREMENT = 1001, ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TBL_DONORS`
--

LOCK TABLES `TBL_DONORS` WRITE;
/*!40000 ALTER TABLE `TBL_DONORS` DISABLE KEYS */;
/*!40000 ALTER TABLE `TBL_DONORS` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-29 14:54:07