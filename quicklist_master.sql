-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: quicklist
-- ------------------------------------------------------
-- Server version	5.7.12-log

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
-- Table structure for table `active_user`
--

DROP TABLE IF EXISTS `active_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `active_user` (
  `ACTIVE_USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `NAME` varchar(60) NOT NULL,
  PRIMARY KEY (`ACTIVE_USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `active_user`
--

LOCK TABLES `active_user` WRITE;
/*!40000 ALTER TABLE `active_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `active_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alumno`
--

DROP TABLE IF EXISTS `alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alumno` (
  `ALUMNO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `DNI` varchar(60) COLLATE latin1_bin NOT NULL,
  `APELLIDO` varchar(45) COLLATE latin1_bin NOT NULL,
  `NOMBRE` varchar(45) COLLATE latin1_bin NOT NULL,
  `TELEFONO` varchar(20) COLLATE latin1_bin NOT NULL,
  `CELULAR` varchar(20) COLLATE latin1_bin NOT NULL,
  `EMAIL` varchar(256) COLLATE latin1_bin NOT NULL,
  `SEXO` varchar(60) COLLATE latin1_bin NOT NULL,
  `NACIONALIDAD` char(60) COLLATE latin1_bin NOT NULL,
  `DIRECCION` varchar(80) COLLATE latin1_bin NOT NULL,
  PRIMARY KEY (`ALUMNO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumno`
--

LOCK TABLES `alumno` WRITE;
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
INSERT INTO `alumno` VALUES (1,'41104933','Ramírez','Jeremías Nicolás','44514445','','','Varón','13','Callao 883'),(2,'41123123','Castellano','Gonzalo','46606098','','','Varón','13','Calle Falsa 123'),(3,'41123123','Corazza','Martín Ariel','44514445','','','Varón','13','Calle Falsa 123'),(4,'40123123','Torres','Agustín','44514445','','','Varón','13','Calle Falsa 123');
/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asistencia`
--

DROP TABLE IF EXISTS `asistencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asistencia` (
  `ASISTENCIA_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ALUMNO_ID` int(11) NOT NULL,
  `GRUPO_ID` int(11) NOT NULL,
  `ASISTENCIA` int(11) NOT NULL,
  `CUENTA_ID` int(11) NOT NULL,
  `FECHA` varchar(60) COLLATE latin1_bin NOT NULL,
  PRIMARY KEY (`ASISTENCIA_ID`,`ALUMNO_ID`,`GRUPO_ID`,`ASISTENCIA`,`CUENTA_ID`),
  KEY `CUENTA_ID` (`CUENTA_ID`),
  KEY `ASISTENCIA_ibfk_1` (`ALUMNO_ID`),
  KEY `ASISTENCIA_ibfk_2` (`GRUPO_ID`),
  KEY `ASISTENCIA_ibfk_3` (`ASISTENCIA`),
  CONSTRAINT `ASISTENCIA_ibfk_1` FOREIGN KEY (`ALUMNO_ID`) REFERENCES `alumno` (`ALUMNO_ID`),
  CONSTRAINT `ASISTENCIA_ibfk_2` FOREIGN KEY (`GRUPO_ID`) REFERENCES `grupo` (`GRUPO_ID`),
  CONSTRAINT `ASISTENCIA_ibfk_3` FOREIGN KEY (`ASISTENCIA`) REFERENCES `tipo_asistencia` (`TIPO_ASISTENCIA_ID`),
  CONSTRAINT `ASISTENCIA_ibfk_4` FOREIGN KEY (`CUENTA_ID`) REFERENCES `cuenta` (`CUENTA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=248 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asistencia`
--

LOCK TABLES `asistencia` WRITE;
/*!40000 ALTER TABLE `asistencia` DISABLE KEYS */;
INSERT INTO `asistencia` VALUES (204,1,2,3,3,'28-11-2016'),(205,3,2,2,3,'28-11-2016'),(206,2,2,2,3,'28-11-2016'),(236,2,2,3,3,'29-11-2016'),(237,1,2,3,3,'29-11-2016'),(238,3,2,3,3,'29-11-2016'),(243,4,3,2,3,'29-11-2016'),(244,2,2,3,3,'30-11-2016'),(245,3,2,1,3,'30-11-2016'),(246,1,2,1,3,'30-11-2016'),(247,4,3,1,3,'30-11-2016');
/*!40000 ALTER TABLE `asistencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta`
--

DROP TABLE IF EXISTS `cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuenta` (
  `CUENTA_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USUARIO` varchar(30) COLLATE latin1_bin NOT NULL,
  `CONTRASEÑA` char(32) COLLATE latin1_bin NOT NULL,
  PRIMARY KEY (`CUENTA_ID`),
  UNIQUE KEY `USUARIO_UNIQUE` (`USUARIO`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta`
--

LOCK TABLES `cuenta` WRITE;
/*!40000 ALTER TABLE `cuenta` DISABLE KEYS */;
INSERT INTO `cuenta` VALUES (1,'Luis','¤]0ô¥/™\r^S5Š»RÆE'),(2,'Vanesa','¤]0ô¥/™\r^S5Š»RÆE'),(3,'Daniel','¤]0ô¥/™\r^S5Š»RÆE');
/*!40000 ALTER TABLE `cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta_grupo`
--

DROP TABLE IF EXISTS `cuenta_grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuenta_grupo` (
  `CUENTA_GRUPO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUENTA_ID` int(11) NOT NULL,
  `GRUPO_ID` int(11) NOT NULL,
  PRIMARY KEY (`CUENTA_GRUPO_ID`,`CUENTA_ID`,`GRUPO_ID`),
  KEY `GRUPO_ID` (`GRUPO_ID`),
  KEY `CUENTA_GRUPO_ibfk_1` (`CUENTA_ID`),
  CONSTRAINT `CUENTA_GRUPO_ibfk_1` FOREIGN KEY (`CUENTA_ID`) REFERENCES `cuenta` (`CUENTA_ID`),
  CONSTRAINT `CUENTA_GRUPO_ibfk_2` FOREIGN KEY (`GRUPO_ID`) REFERENCES `grupo` (`GRUPO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta_grupo`
--

LOCK TABLES `cuenta_grupo` WRITE;
/*!40000 ALTER TABLE `cuenta_grupo` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuenta_grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `grupo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupo` (
  `GRUPO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(45) COLLATE latin1_bin DEFAULT NULL,
  `FLAG` int(11) DEFAULT NULL,
  PRIMARY KEY (`GRUPO_ID`),
  UNIQUE KEY `NOMBRE_UNIQUE` (`NOMBRE`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo`
--

LOCK TABLES `grupo` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
INSERT INTO `grupo` VALUES (1,'1ro 1ra',0),(2,'1ro 2da',0),(3,'1ro 3ra',0),(4,'1ro 4ta',0),(5,'1ro 5ta',0),(6,'1ro 6ta',0),(7,'1ro 7ma',0),(8,'1ro 8va',0),(9,'1ro 9na',0),(10,'1ro 10ma',0),(11,'2do 1ra',0),(12,'2do 2da',0),(13,'2do 3ra',0),(14,'2do 4ta',0),(15,'2do 5ta',0),(16,'2do 6ta',0),(17,'2do 7ma',0),(18,'2do 8va',0),(19,'2do 9na',0),(20,'2do 10ma',0),(21,'3ro 1ra',0),(22,'3ro 2da',0),(23,'3ro 3ra',0),(24,'3ro 4ta',0),(25,'3ro 5ta',0),(26,'3ro 6ta',0),(27,'3ro 7ma',0),(28,'3ro 8va',0),(29,'3ro 9na',0),(30,'3ro 10ma',0),(31,'4to 1ra',0),(32,'4to 2da',0),(33,'4to 3ra',0),(34,'4to 4ta',0),(35,'4to 5ta',0),(36,'4to 6ta',0),(37,'4to 7ma',0),(38,'4to 8va',0),(39,'4to 9na',0),(40,'4to 10ma',0),(41,'5to 1ra',0),(42,'5to 2da',0),(43,'5to 3ra',0),(44,'5to 4ta',0),(45,'5to 5ta',0),(46,'5to 6ta',0),(47,'5to 7ma',0),(48,'5to 8va',0),(49,'5to 9na',0),(50,'5to 10ma',0),(51,'6to 1ra',0),(52,'6to 2da',0),(53,'6to 3ra',0),(54,'6to 4ta',0),(55,'6to 5ta',0),(56,'6to 6ta',0),(57,'6to 7ma',0),(58,'6to 8va',0),(59,'6to 9na',0),(60,'6to 10ma',0),(61,'7mo 1ra',0),(62,'7mo 2da',0),(63,'7mo 3ra',0),(64,'7mo 4ta',0),(65,'7mo 5ta',0),(66,'7mo 6ta',0),(67,'7mo 7ma',0),(68,'7mo 8va',0),(69,'7mo 9na',0),(70,'7mo 10ma',0);
/*!40000 ALTER TABLE `grupo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo_alumno`
--

DROP TABLE IF EXISTS `grupo_alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupo_alumno` (
  `GRUPO_ALUMNO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `GRUPO_ID` int(11) NOT NULL,
  `ALUMNO_ID` int(11) NOT NULL,
  PRIMARY KEY (`GRUPO_ALUMNO_ID`,`GRUPO_ID`,`ALUMNO_ID`),
  KEY `ALUMNO_ID` (`ALUMNO_ID`),
  KEY `GRUPO_ALUMNO_ibfk_1` (`GRUPO_ID`),
  CONSTRAINT `GRUPO_ALUMNO_ibfk_1` FOREIGN KEY (`GRUPO_ID`) REFERENCES `grupo` (`GRUPO_ID`),
  CONSTRAINT `GRUPO_ALUMNO_ibfk_2` FOREIGN KEY (`ALUMNO_ID`) REFERENCES `alumno` (`ALUMNO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo_alumno`
--

LOCK TABLES `grupo_alumno` WRITE;
/*!40000 ALTER TABLE `grupo_alumno` DISABLE KEYS */;
INSERT INTO `grupo_alumno` VALUES (1,2,1),(2,2,2),(3,2,3),(4,3,4);
/*!40000 ALTER TABLE `grupo_alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pais` (
  `PAIS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ISO` char(2) NOT NULL,
  `NOMBRE` varchar(80) NOT NULL,
  PRIMARY KEY (`PAIS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES (1,'AF','Afganistán'),(2,'AX','Islas Gland'),(3,'AL','Albania'),(4,'DE','Alemania'),(5,'AD','Andorra'),(6,'AO','Angola'),(7,'AI','Anguilla'),(8,'AQ','Antártida'),(9,'AG','Antigua y Barbuda'),(10,'AN','Antillas Holandesas'),(11,'SA','Arabia Saudí'),(12,'DZ','Argelia'),(13,'AR','Argentina'),(14,'AM','Armenia'),(15,'AW','Aruba'),(16,'AU','Australia'),(17,'AT','Austria'),(18,'AZ','Azerbaiyán'),(19,'BS','Bahamas'),(20,'BH','Bahréin'),(21,'BD','Bangladesh'),(22,'BB','Barbados'),(23,'BY','Bielorrusia'),(24,'BE','Bélgica'),(25,'BZ','Belice'),(26,'BJ','Benin'),(27,'BM','Bermudas'),(28,'BT','Bhután'),(29,'BO','Bolivia'),(30,'BA','Bosnia y Herzegovina'),(31,'BW','Botsuana'),(32,'BV','Isla Bouvet'),(33,'BR','Brasil'),(34,'BN','Brunéi'),(35,'BG','Bulgaria'),(36,'BF','Burkina Faso'),(37,'BI','Burundi'),(38,'CV','Cabo Verde'),(39,'KY','Islas Caimán'),(40,'KH','Camboya'),(41,'CM','Camerún'),(42,'CA','Canadá'),(43,'CF','República Centroafricana'),(44,'TD','Chad'),(45,'CZ','República Checa'),(46,'CL','Chile'),(47,'CN','China'),(48,'CY','Chipre'),(49,'CX','Isla de Navidad'),(50,'VA','Ciudad del Vaticano'),(51,'CC','Islas Cocos'),(52,'CO','Colombia'),(53,'KM','Comoras'),(54,'CD','República Democrática del Congo'),(55,'CG','Congo'),(56,'CK','Islas Cook'),(57,'KP','Corea del Norte'),(58,'KR','Corea del Sur'),(59,'CI','Costa de Marfil'),(60,'CR','Costa Rica'),(61,'HR','Croacia'),(62,'CU','Cuba'),(63,'DK','Dinamarca'),(64,'DM','Dominica'),(65,'DO','República Dominicana'),(66,'EC','Ecuador'),(67,'EG','Egipto'),(68,'SV','El Salvador'),(69,'AE','Emiratos Árabes Unidos'),(70,'ER','Eritrea'),(71,'SK','Eslovaquia'),(72,'SI','Eslovenia'),(73,'ES','España'),(74,'UM','Islas ultramarinas de Estados Unidos'),(75,'US','Estados Unidos'),(76,'EE','Estonia'),(77,'ET','Etiopía'),(78,'FO','Islas Feroe'),(79,'PH','Filipinas'),(80,'FI','Finlandia'),(81,'FJ','Fiyi'),(82,'FR','Francia'),(83,'GA','Gabón'),(84,'GM','Gambia'),(85,'GE','Georgia'),(86,'GS','Islas Georgias del Sur y Sandwich del Sur'),(87,'GH','Ghana'),(88,'GI','Gibraltar'),(89,'GD','Granada'),(90,'GR','Grecia'),(91,'GL','Groenlandia'),(92,'GP','Guadalupe'),(93,'GU','Guam'),(94,'GT','Guatemala'),(95,'GF','Guayana Francesa'),(96,'GN','Guinea'),(97,'GQ','Guinea Ecuatorial'),(98,'GW','Guinea-Bissau'),(99,'GY','Guyana'),(100,'HT','Haití'),(101,'HM','Islas Heard y McDonald'),(102,'HN','Honduras'),(103,'HK','Hong Kong'),(104,'HU','Hungría'),(105,'IN','India'),(106,'ID','Indonesia'),(107,'IR','Irán'),(108,'IQ','Iraq'),(109,'IE','Irlanda'),(110,'IS','Islandia'),(111,'IL','Israel'),(112,'IT','Italia'),(113,'JM','Jamaica'),(114,'JP','Japón'),(115,'JO','Jordania'),(116,'KZ','Kazajstán'),(117,'KE','Kenia'),(118,'KG','Kirguistán'),(119,'KI','Kiribati'),(120,'KW','Kuwait'),(121,'LA','Laos'),(122,'LS','Lesotho'),(123,'LV','Letonia'),(124,'LB','Líbano'),(125,'LR','Liberia'),(126,'LY','Libia'),(127,'LI','Liechtenstein'),(128,'LT','Lituania'),(129,'LU','Luxemburgo'),(130,'MO','Macao'),(131,'MK','ARY Macedonia'),(132,'MG','Madagascar'),(133,'MY','Malasia'),(134,'MW','Malawi'),(135,'MV','Maldivas'),(136,'ML','Malí'),(137,'MT','Malta'),(138,'FK','Islas Malvinas'),(139,'MP','Islas Marianas del Norte'),(140,'MA','Marruecos'),(141,'MH','Islas Marshall'),(142,'MQ','Martinica'),(143,'MU','Mauricio'),(144,'MR','Mauritania'),(145,'YT','Mayotte'),(146,'MX','México'),(147,'FM','Micronesia'),(148,'MD','Moldavia'),(149,'MC','Mónaco'),(150,'MN','Mongolia'),(151,'MS','Montserrat'),(152,'MZ','Mozambique'),(153,'MM','Myanmar'),(154,'NA','Namibia'),(155,'NR','Nauru'),(156,'NP','Nepal'),(157,'NI','Nicaragua'),(158,'NE','Níger'),(159,'NG','Nigeria'),(160,'NU','Niue'),(161,'NF','Isla Norfolk'),(162,'NO','Noruega'),(163,'NC','Nueva Caledonia'),(164,'NZ','Nueva Zelanda'),(165,'OM','Omán'),(166,'NL','Países Bajos'),(167,'PK','Pakistán'),(168,'PW','Palau'),(169,'PS','Palestina'),(170,'PA','Panamá'),(171,'PG','Papúa Nueva Guinea'),(172,'PY','Paraguay'),(173,'PE','Perú'),(174,'PN','Islas Pitcairn'),(175,'PF','Polinesia Francesa'),(176,'PL','Polonia'),(177,'PT','Portugal'),(178,'PR','Puerto Rico'),(179,'QA','Qatar'),(180,'GB','Reino Unido'),(181,'RE','Reunión'),(182,'RW','Ruanda'),(183,'RO','Rumania'),(184,'RU','Rusia'),(185,'EH','Sahara Occidental'),(186,'SB','Islas Salomón'),(187,'WS','Samoa'),(188,'AS','Samoa Americana'),(189,'KN','San Cristóbal y Nevis'),(190,'SM','San Marino'),(191,'PM','San Pedro y Miquelón'),(192,'VC','San Vicente y las Granadinas'),(193,'SH','Santa Helena'),(194,'LC','Santa Lucía'),(195,'ST','Santo Tomé y Príncipe'),(196,'SN','Senegal'),(197,'CS','Serbia y Montenegro'),(198,'SC','Seychelles'),(199,'SL','Sierra Leona'),(200,'SG','Singapur'),(201,'SY','Siria'),(202,'SO','Somalia'),(203,'LK','Sri Lanka'),(204,'SZ','Suazilandia'),(205,'ZA','Sudáfrica'),(206,'SD','Sudán'),(207,'SE','Suecia'),(208,'CH','Suiza'),(209,'SR','Surinam'),(210,'SJ','Svalbard y Jan Mayen'),(211,'TH','Tailandia'),(212,'TW','Taiwán'),(213,'TZ','Tanzania'),(214,'TJ','Tayikistán'),(215,'IO','Territorio Británico del Océano Índico'),(216,'TF','Territorios Australes Franceses'),(217,'TL','Timor Oriental'),(218,'TG','Togo'),(219,'TK','Tokelau'),(220,'TO','Tonga'),(221,'TT','Trinidad y Tobago'),(222,'TN','Túnez'),(223,'TC','Islas Turcas y Caicos'),(224,'TM','Turkmenistán'),(225,'TR','Turquía'),(226,'TV','Tuvalu'),(227,'UA','Ucrania'),(228,'UG','Uganda'),(229,'UY','Uruguay'),(230,'UZ','Uzbekistán'),(231,'VU','Vanuatu'),(232,'VE','Venezuela'),(233,'VN','Vietnam'),(234,'VG','Islas Vírgenes Británicas'),(235,'VI','Islas Vírgenes de los Estados Unidos'),(236,'WF','Wallis y Futuna'),(237,'YE','Yemen'),(238,'DJ','Yibuti'),(239,'ZM','Zambia'),(240,'ZW','Zimbabue');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_asistencia`
--

DROP TABLE IF EXISTS `tipo_asistencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_asistencia` (
  `TIPO_ASISTENCIA_ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPCION` varchar(60) COLLATE latin1_bin NOT NULL,
  PRIMARY KEY (`TIPO_ASISTENCIA_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_asistencia`
--

LOCK TABLES `tipo_asistencia` WRITE;
/*!40000 ALTER TABLE `tipo_asistencia` DISABLE KEYS */;
INSERT INTO `tipo_asistencia` VALUES (1,'PRESENTE'),(2,'AUSENTE'),(3,'TARDE');
/*!40000 ALTER TABLE `tipo_asistencia` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-30 11:35:15
