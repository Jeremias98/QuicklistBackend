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
-- Table structure for table `alumno`
--

DROP TABLE IF EXISTS `ALUMNO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ALUMNO` (
  `ALUMNO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `DNI` varchar(60) NOT NULL,
  `APELLIDO` varchar(45) NOT NULL,
  `NOMBRE` varchar(45) NOT NULL,
  `TELEFONO` varchar(20) NOT NULL,
  `CELULAR` varchar(20) NOT NULL,
  `EMAIL` varchar(256) NOT NULL,
  `SEXO` varchar(60) NOT NULL,
  `NACIONALIDAD` char(60) NOT NULL,
  `DIRECCION` varchar(80) NOT NULL,
  PRIMARY KEY (`ALUMNO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `asistencia`
--

DROP TABLE IF EXISTS `TIPO_ASISTENCIA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TIPO_ASISTENCIA` (
  `TIPO_ASISTENCIA_ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPCION` varchar(60) NOT NULL,
  PRIMARY KEY (`TIPO_ASISTENCIA_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `TIPO_ASISTENCIA` WRITE;
/*!40000 ALTER TABLE `TIPO_ASISTENCIA` DISABLE KEYS */;
INSERT INTO `TIPO_ASISTENCIA` VALUES(1, 'PRESENTE');
INSERT INTO `TIPO_ASISTENCIA` VALUES(2, 'AUSENTE');
INSERT INTO `TIPO_ASISTENCIA` VALUES(3, 'TARDE');
/*!40000 ALTER TABLE `TIPO_ASISTENCIA` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `ASISTENCIA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ASISTENCIA` (
  `ASISTENCIA_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ALUMNO_ID` int(11) NOT NULL,
  `GRUPO_ID` int(11) NOT NULL,
  `FECHA` datetime NOT NULL,
  `ASISTENCIA` int(11) NOT NULL,
  `CUENTA_ID` int(11) NOT NULL,
  PRIMARY KEY (`ASISTENCIA_ID`,`ALUMNO_ID`,`GRUPO_ID`,`ASISTENCIA`,`CUENTA_ID`),
  KEY `CUENTA_ID` (`CUENTA_ID`),
  CONSTRAINT `ASISTENCIA_ibfk_1` FOREIGN KEY (`ALUMNO_ID`) REFERENCES `ALUMNO` (`ALUMNO_ID`),
  CONSTRAINT `ASISTENCIA_ibfk_2` FOREIGN KEY (`GRUPO_ID`) REFERENCES `GRUPO` (`GRUPO_ID`),
  CONSTRAINT `ASISTENCIA_ibfk_3` FOREIGN KEY (`ASISTENCIA`) REFERENCES `TIPO_ASISTENCIA` (`TIPO_ASISTENCIA_ID`),
  CONSTRAINT `ASISTENCIA_ibfk_4` FOREIGN KEY (`CUENTA_ID`) REFERENCES `CUENTA` (`CUENTA_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asistencia`
--

LOCK TABLES `ASISTENCIA` WRITE;
/*!40000 ALTER TABLE `ASISTENCIA` DISABLE KEYS */;
/*!40000 ALTER TABLE `ASISTENCIA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta`
--

DROP TABLE IF EXISTS `CUENTA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CUENTA` (
  `CUENTA_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USUARIO` varchar(30) COLLATE latin1_bin NOT NULL,
  `CONTRASEÑA` char(32) COLLATE latin1_bin NOT NULL,
  PRIMARY KEY (`CUENTA_ID`),
  UNIQUE KEY `USUARIO_UNIQUE` (`USUARIO`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cuenta_grupo`
--

DROP TABLE IF EXISTS `CUENTA_GRUPO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CUENTA_GRUPO` (
  `CUENTA_GRUPO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUENTA_ID` int(11) NOT NULL,
  `GRUPO_ID` int(11) NOT NULL,
  PRIMARY KEY (`CUENTA_GRUPO_ID`,`CUENTA_ID`,`GRUPO_ID`),
  KEY `GRUPO_ID` (`GRUPO_ID`),
  CONSTRAINT `CUENTA_GRUPO_ibfk_1` FOREIGN KEY (`CUENTA_ID`) REFERENCES `CUENTA` (`CUENTA_ID`),
  CONSTRAINT `CUENTA_GRUPO_ibfk_2` FOREIGN KEY (`GRUPO_ID`) REFERENCES `GRUPO` (`GRUPO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grupo`
--

DROP TABLE IF EXISTS `GRUPO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GRUPO` (
  `GRUPO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(45) COLLATE latin1_bin DEFAULT NULL,
  `FLAG` int(11) DEFAULT NULL,
  PRIMARY KEY (`GRUPO_ID`),
  UNIQUE KEY `NOMBRE_UNIQUE` (`NOMBRE`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `GRUPO` WRITE;
/*!40000 ALTER TABLE `grupo` DISABLE KEYS */;
INSERT INTO `GRUPO` VALUES (1,'1ro 1ra',0),(2,'1ro 2da',0),(3,'1ro 3ra',0),(4,'1ro 4ta',0),(5,'1ro 5ta',0),(6,'1ro 6ta',0),(7,'1ro 7ma',0),(8,'1ro 8va',0),(9,'1ro 9na',0),(10,'1ro 10ma',0),(11,'2do 1ra',0),(12,'2do 2da',0),(13,'2do 3ra',0),(14,'2do 4ta',0),(15,'2do 5ta',0),(16,'2do 6ta',0),(17,'2do 7ma',0),(18,'2do 8va',0),(19,'2do 9na',0),(20,'2do 10ma',0),(21,'3ro 1ra',0),(22,'3ro 2da',0),(23,'3ro 3ra',0),(24,'3ro 4ta',0),(25,'3ro 5ta',0),(26,'3ro 6ta',0),(27,'3ro 7ma',0),(28,'3ro 8va',0),(29,'3ro 9na',0),(30,'3ro 10ma',0),(31,'4to 1ra',0),(32,'4to 2da',0),(33,'4to 3ra',0),(34,'4to 4ta',0),(35,'4to 5ta',0),(36,'4to 6ta',0),(37,'4to 7ma',0),(38,'4to 8va',0),(39,'4to 9na',0),(40,'4to 10ma',0),(41,'5to 1ra',0),(42,'5to 2da',0),(43,'5to 3ra',0),(44,'5to 4ta',0),(45,'5to 5ta',0),(46,'5to 6ta',0),(47,'5to 7ma',0),(48,'5to 8va',0),(49,'5to 9na',0),(50,'5to 10ma',0),(51,'6to 1ra',0),(52,'6to 2da',0),(53,'6to 3ra',0),(54,'6to 4ta',0),(55,'6to 5ta',0),(56,'6to 6ta',0),(57,'6to 7ma',0),(58,'6to 8va',0),(59,'6to 9na',0),(60,'6to 10ma',0),(61,'7mo 1ra',0),(62,'7mo 2da',0),(63,'7mo 3ra',0),(64,'7mo 4ta',0),(65,'7mo 5ta',0),(66,'7mo 6ta',0),(67,'7mo 7ma',0),(68,'7mo 8va',0),(69,'7mo 9na',0),(70,'7mo 10ma',0);
/*!40000 ALTER TABLE `GRUPO` ENABLE KEYS */;
UNLOCK TABLES;
--
-- Table structure for table `grupo_alumno`
--

DROP TABLE IF EXISTS `GRUPO_ALUMNO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GRUPO_ALUMNO` (
  `GRUPO_ALUMNO_ID` int(11) NOT NULL AUTO_INCREMENT,
  `GRUPO_ID` int(11) NOT NULL,
  `ALUMNO_ID` int(11) NOT NULL,
  PRIMARY KEY (`GRUPO_ALUMNO_ID`,`GRUPO_ID`,`ALUMNO_ID`),
  KEY `ALUMNO_ID` (`ALUMNO_ID`),
  CONSTRAINT `GRUPO_ALUMNO_ibfk_1` FOREIGN KEY (`GRUPO_ID`) REFERENCES `GRUPO` (`GRUPO_ID`),
  CONSTRAINT `GRUPO_ALUMNO_ibfk_2` FOREIGN KEY (`ALUMNO_ID`) REFERENCES `ALUMNO` (`ALUMNO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `PAIS`;
CREATE TABLE `PAIS` (
`PAIS_ID` int(11) NOT NULL AUTO_INCREMENT,
`ISO` char(2) NOT NULL,
`NOMBRE` varchar(80) NOT NULL,
PRIMARY KEY (`PAIS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;
 
INSERT INTO `PAIS` VALUES(1, 'AF', 'Afganistán');
INSERT INTO `PAIS` VALUES(2, 'AX', 'Islas Gland');
INSERT INTO `PAIS` VALUES(3, 'AL', 'Albania');
INSERT INTO `PAIS` VALUES(4, 'DE', 'Alemania');
INSERT INTO `PAIS` VALUES(5, 'AD', 'Andorra');
INSERT INTO `PAIS` VALUES(6, 'AO', 'Angola');
INSERT INTO `PAIS` VALUES(7, 'AI', 'Anguilla');
INSERT INTO `PAIS` VALUES(8, 'AQ', 'Antártida');
INSERT INTO `PAIS` VALUES(9, 'AG', 'Antigua y Barbuda');
INSERT INTO `PAIS` VALUES(10, 'AN', 'Antillas Holandesas');
INSERT INTO `PAIS` VALUES(11, 'SA', 'Arabia Saudí');
INSERT INTO `PAIS` VALUES(12, 'DZ', 'Argelia');
INSERT INTO `PAIS` VALUES(13, 'AR', 'Argentina');
INSERT INTO `PAIS` VALUES(14, 'AM', 'Armenia');
INSERT INTO `PAIS` VALUES(15, 'AW', 'Aruba');
INSERT INTO `PAIS` VALUES(16, 'AU', 'Australia');
INSERT INTO `PAIS` VALUES(17, 'AT', 'Austria');
INSERT INTO `PAIS` VALUES(18, 'AZ', 'Azerbaiyán');
INSERT INTO `PAIS` VALUES(19, 'BS', 'Bahamas');
INSERT INTO `PAIS` VALUES(20, 'BH', 'Bahréin');
INSERT INTO `PAIS` VALUES(21, 'BD', 'Bangladesh');
INSERT INTO `PAIS` VALUES(22, 'BB', 'Barbados');
INSERT INTO `PAIS` VALUES(23, 'BY', 'Bielorrusia');
INSERT INTO `PAIS` VALUES(24, 'BE', 'Bélgica');
INSERT INTO `PAIS` VALUES(25, 'BZ', 'Belice');
INSERT INTO `PAIS` VALUES(26, 'BJ', 'Benin');
INSERT INTO `PAIS` VALUES(27, 'BM', 'Bermudas');
INSERT INTO `PAIS` VALUES(28, 'BT', 'Bhután');
INSERT INTO `PAIS` VALUES(29, 'BO', 'Bolivia');
INSERT INTO `PAIS` VALUES(30, 'BA', 'Bosnia y Herzegovina');
INSERT INTO `PAIS` VALUES(31, 'BW', 'Botsuana');
INSERT INTO `PAIS` VALUES(32, 'BV', 'Isla Bouvet');
INSERT INTO `PAIS` VALUES(33, 'BR', 'Brasil');
INSERT INTO `PAIS` VALUES(34, 'BN', 'Brunéi');
INSERT INTO `PAIS` VALUES(35, 'BG', 'Bulgaria');
INSERT INTO `PAIS` VALUES(36, 'BF', 'Burkina Faso');
INSERT INTO `PAIS` VALUES(37, 'BI', 'Burundi');
INSERT INTO `PAIS` VALUES(38, 'CV', 'Cabo Verde');
INSERT INTO `PAIS` VALUES(39, 'KY', 'Islas Caimán');
INSERT INTO `PAIS` VALUES(40, 'KH', 'Camboya');
INSERT INTO `PAIS` VALUES(41, 'CM', 'Camerún');
INSERT INTO `PAIS` VALUES(42, 'CA', 'Canadá');
INSERT INTO `PAIS` VALUES(43, 'CF', 'República Centroafricana');
INSERT INTO `PAIS` VALUES(44, 'TD', 'Chad');
INSERT INTO `PAIS` VALUES(45, 'CZ', 'República Checa');
INSERT INTO `PAIS` VALUES(46, 'CL', 'Chile');
INSERT INTO `PAIS` VALUES(47, 'CN', 'China');
INSERT INTO `PAIS` VALUES(48, 'CY', 'Chipre');
INSERT INTO `PAIS` VALUES(49, 'CX', 'Isla de Navidad');
INSERT INTO `PAIS` VALUES(50, 'VA', 'Ciudad del Vaticano');
INSERT INTO `PAIS` VALUES(51, 'CC', 'Islas Cocos');
INSERT INTO `PAIS` VALUES(52, 'CO', 'Colombia');
INSERT INTO `PAIS` VALUES(53, 'KM', 'Comoras');
INSERT INTO `PAIS` VALUES(54, 'CD', 'República Democrática del Congo');
INSERT INTO `PAIS` VALUES(55, 'CG', 'Congo');
INSERT INTO `PAIS` VALUES(56, 'CK', 'Islas Cook');
INSERT INTO `PAIS` VALUES(57, 'KP', 'Corea del Norte');
INSERT INTO `PAIS` VALUES(58, 'KR', 'Corea del Sur');
INSERT INTO `PAIS` VALUES(59, 'CI', 'Costa de Marfil');
INSERT INTO `PAIS` VALUES(60, 'CR', 'Costa Rica');
INSERT INTO `PAIS` VALUES(61, 'HR', 'Croacia');
INSERT INTO `PAIS` VALUES(62, 'CU', 'Cuba');
INSERT INTO `PAIS` VALUES(63, 'DK', 'Dinamarca');
INSERT INTO `PAIS` VALUES(64, 'DM', 'Dominica');
INSERT INTO `PAIS` VALUES(65, 'DO', 'República Dominicana');
INSERT INTO `PAIS` VALUES(66, 'EC', 'Ecuador');
INSERT INTO `PAIS` VALUES(67, 'EG', 'Egipto');
INSERT INTO `PAIS` VALUES(68, 'SV', 'El Salvador');
INSERT INTO `PAIS` VALUES(69, 'AE', 'Emiratos Árabes Unidos');
INSERT INTO `PAIS` VALUES(70, 'ER', 'Eritrea');
INSERT INTO `PAIS` VALUES(71, 'SK', 'Eslovaquia');
INSERT INTO `PAIS` VALUES(72, 'SI', 'Eslovenia');
INSERT INTO `PAIS` VALUES(73, 'ES', 'España');
INSERT INTO `PAIS` VALUES(74, 'UM', 'Islas ultramarinas de Estados Unidos');
INSERT INTO `PAIS` VALUES(75, 'US', 'Estados Unidos');
INSERT INTO `PAIS` VALUES(76, 'EE', 'Estonia');
INSERT INTO `PAIS` VALUES(77, 'ET', 'Etiopía');
INSERT INTO `PAIS` VALUES(78, 'FO', 'Islas Feroe');
INSERT INTO `PAIS` VALUES(79, 'PH', 'Filipinas');
INSERT INTO `PAIS` VALUES(80, 'FI', 'Finlandia');
INSERT INTO `PAIS` VALUES(81, 'FJ', 'Fiyi');
INSERT INTO `PAIS` VALUES(82, 'FR', 'Francia');
INSERT INTO `PAIS` VALUES(83, 'GA', 'Gabón');
INSERT INTO `PAIS` VALUES(84, 'GM', 'Gambia');
INSERT INTO `PAIS` VALUES(85, 'GE', 'Georgia');
INSERT INTO `PAIS` VALUES(86, 'GS', 'Islas Georgias del Sur y Sandwich del Sur');
INSERT INTO `PAIS` VALUES(87, 'GH', 'Ghana');
INSERT INTO `PAIS` VALUES(88, 'GI', 'Gibraltar');
INSERT INTO `PAIS` VALUES(89, 'GD', 'Granada');
INSERT INTO `PAIS` VALUES(90, 'GR', 'Grecia');
INSERT INTO `PAIS` VALUES(91, 'GL', 'Groenlandia');
INSERT INTO `PAIS` VALUES(92, 'GP', 'Guadalupe');
INSERT INTO `PAIS` VALUES(93, 'GU', 'Guam');
INSERT INTO `PAIS` VALUES(94, 'GT', 'Guatemala');
INSERT INTO `PAIS` VALUES(95, 'GF', 'Guayana Francesa');
INSERT INTO `PAIS` VALUES(96, 'GN', 'Guinea');
INSERT INTO `PAIS` VALUES(97, 'GQ', 'Guinea Ecuatorial');
INSERT INTO `PAIS` VALUES(98, 'GW', 'Guinea-Bissau');
INSERT INTO `PAIS` VALUES(99, 'GY', 'Guyana');
INSERT INTO `PAIS` VALUES(100, 'HT', 'Haití');
INSERT INTO `PAIS` VALUES(101, 'HM', 'Islas Heard y McDonald');
INSERT INTO `PAIS` VALUES(102, 'HN', 'Honduras');
INSERT INTO `PAIS` VALUES(103, 'HK', 'Hong Kong');
INSERT INTO `PAIS` VALUES(104, 'HU', 'Hungría');
INSERT INTO `PAIS` VALUES(105, 'IN', 'India');
INSERT INTO `PAIS` VALUES(106, 'ID', 'Indonesia');
INSERT INTO `PAIS` VALUES(107, 'IR', 'Irán');
INSERT INTO `PAIS` VALUES(108, 'IQ', 'Iraq');
INSERT INTO `PAIS` VALUES(109, 'IE', 'Irlanda');
INSERT INTO `PAIS` VALUES(110, 'IS', 'Islandia');
INSERT INTO `PAIS` VALUES(111, 'IL', 'Israel');
INSERT INTO `PAIS` VALUES(112, 'IT', 'Italia');
INSERT INTO `PAIS` VALUES(113, 'JM', 'Jamaica');
INSERT INTO `PAIS` VALUES(114, 'JP', 'Japón');
INSERT INTO `PAIS` VALUES(115, 'JO', 'Jordania');
INSERT INTO `PAIS` VALUES(116, 'KZ', 'Kazajstán');
INSERT INTO `PAIS` VALUES(117, 'KE', 'Kenia');
INSERT INTO `PAIS` VALUES(118, 'KG', 'Kirguistán');
INSERT INTO `PAIS` VALUES(119, 'KI', 'Kiribati');
INSERT INTO `PAIS` VALUES(120, 'KW', 'Kuwait');
INSERT INTO `PAIS` VALUES(121, 'LA', 'Laos');
INSERT INTO `PAIS` VALUES(122, 'LS', 'Lesotho');
INSERT INTO `PAIS` VALUES(123, 'LV', 'Letonia');
INSERT INTO `PAIS` VALUES(124, 'LB', 'Líbano');
INSERT INTO `PAIS` VALUES(125, 'LR', 'Liberia');
INSERT INTO `PAIS` VALUES(126, 'LY', 'Libia');
INSERT INTO `PAIS` VALUES(127, 'LI', 'Liechtenstein');
INSERT INTO `PAIS` VALUES(128, 'LT', 'Lituania');
INSERT INTO `PAIS` VALUES(129, 'LU', 'Luxemburgo');
INSERT INTO `PAIS` VALUES(130, 'MO', 'Macao');
INSERT INTO `PAIS` VALUES(131, 'MK', 'ARY Macedonia');
INSERT INTO `PAIS` VALUES(132, 'MG', 'Madagascar');
INSERT INTO `PAIS` VALUES(133, 'MY', 'Malasia');
INSERT INTO `PAIS` VALUES(134, 'MW', 'Malawi');
INSERT INTO `PAIS` VALUES(135, 'MV', 'Maldivas');
INSERT INTO `PAIS` VALUES(136, 'ML', 'Malí');
INSERT INTO `PAIS` VALUES(137, 'MT', 'Malta');
INSERT INTO `PAIS` VALUES(138, 'FK', 'Islas Malvinas');
INSERT INTO `PAIS` VALUES(139, 'MP', 'Islas Marianas del Norte');
INSERT INTO `PAIS` VALUES(140, 'MA', 'Marruecos');
INSERT INTO `PAIS` VALUES(141, 'MH', 'Islas Marshall');
INSERT INTO `PAIS` VALUES(142, 'MQ', 'Martinica');
INSERT INTO `PAIS` VALUES(143, 'MU', 'Mauricio');
INSERT INTO `PAIS` VALUES(144, 'MR', 'Mauritania');
INSERT INTO `PAIS` VALUES(145, 'YT', 'Mayotte');
INSERT INTO `PAIS` VALUES(146, 'MX', 'México');
INSERT INTO `PAIS` VALUES(147, 'FM', 'Micronesia');
INSERT INTO `PAIS` VALUES(148, 'MD', 'Moldavia');
INSERT INTO `PAIS` VALUES(149, 'MC', 'Mónaco');
INSERT INTO `PAIS` VALUES(150, 'MN', 'Mongolia');
INSERT INTO `PAIS` VALUES(151, 'MS', 'Montserrat');
INSERT INTO `PAIS` VALUES(152, 'MZ', 'Mozambique');
INSERT INTO `PAIS` VALUES(153, 'MM', 'Myanmar');
INSERT INTO `PAIS` VALUES(154, 'NA', 'Namibia');
INSERT INTO `PAIS` VALUES(155, 'NR', 'Nauru');
INSERT INTO `PAIS` VALUES(156, 'NP', 'Nepal');
INSERT INTO `PAIS` VALUES(157, 'NI', 'Nicaragua');
INSERT INTO `PAIS` VALUES(158, 'NE', 'Níger');
INSERT INTO `PAIS` VALUES(159, 'NG', 'Nigeria');
INSERT INTO `PAIS` VALUES(160, 'NU', 'Niue');
INSERT INTO `PAIS` VALUES(161, 'NF', 'Isla Norfolk');
INSERT INTO `PAIS` VALUES(162, 'NO', 'Noruega');
INSERT INTO `PAIS` VALUES(163, 'NC', 'Nueva Caledonia');
INSERT INTO `PAIS` VALUES(164, 'NZ', 'Nueva Zelanda');
INSERT INTO `PAIS` VALUES(165, 'OM', 'Omán');
INSERT INTO `PAIS` VALUES(166, 'NL', 'Países Bajos');
INSERT INTO `PAIS` VALUES(167, 'PK', 'Pakistán');
INSERT INTO `PAIS` VALUES(168, 'PW', 'Palau');
INSERT INTO `PAIS` VALUES(169, 'PS', 'Palestina');
INSERT INTO `PAIS` VALUES(170, 'PA', 'Panamá');
INSERT INTO `PAIS` VALUES(171, 'PG', 'Papúa Nueva Guinea');
INSERT INTO `PAIS` VALUES(172, 'PY', 'Paraguay');
INSERT INTO `PAIS` VALUES(173, 'PE', 'Perú');
INSERT INTO `PAIS` VALUES(174, 'PN', 'Islas Pitcairn');
INSERT INTO `PAIS` VALUES(175, 'PF', 'Polinesia Francesa');
INSERT INTO `PAIS` VALUES(176, 'PL', 'Polonia');
INSERT INTO `PAIS` VALUES(177, 'PT', 'Portugal');
INSERT INTO `PAIS` VALUES(178, 'PR', 'Puerto Rico');
INSERT INTO `PAIS` VALUES(179, 'QA', 'Qatar');
INSERT INTO `PAIS` VALUES(180, 'GB', 'Reino Unido');
INSERT INTO `PAIS` VALUES(181, 'RE', 'Reunión');
INSERT INTO `PAIS` VALUES(182, 'RW', 'Ruanda');
INSERT INTO `PAIS` VALUES(183, 'RO', 'Rumania');
INSERT INTO `PAIS` VALUES(184, 'RU', 'Rusia');
INSERT INTO `PAIS` VALUES(185, 'EH', 'Sahara Occidental');
INSERT INTO `PAIS` VALUES(186, 'SB', 'Islas Salomón');
INSERT INTO `PAIS` VALUES(187, 'WS', 'Samoa');
INSERT INTO `PAIS` VALUES(188, 'AS', 'Samoa Americana');
INSERT INTO `PAIS` VALUES(189, 'KN', 'San Cristóbal y Nevis');
INSERT INTO `PAIS` VALUES(190, 'SM', 'San Marino');
INSERT INTO `PAIS` VALUES(191, 'PM', 'San Pedro y Miquelón');
INSERT INTO `PAIS` VALUES(192, 'VC', 'San Vicente y las Granadinas');
INSERT INTO `PAIS` VALUES(193, 'SH', 'Santa Helena');
INSERT INTO `PAIS` VALUES(194, 'LC', 'Santa Lucía');
INSERT INTO `PAIS` VALUES(195, 'ST', 'Santo Tomé y Príncipe');
INSERT INTO `PAIS` VALUES(196, 'SN', 'Senegal');
INSERT INTO `PAIS` VALUES(197, 'CS', 'Serbia y Montenegro');
INSERT INTO `PAIS` VALUES(198, 'SC', 'Seychelles');
INSERT INTO `PAIS` VALUES(199, 'SL', 'Sierra Leona');
INSERT INTO `PAIS` VALUES(200, 'SG', 'Singapur');
INSERT INTO `PAIS` VALUES(201, 'SY', 'Siria');
INSERT INTO `PAIS` VALUES(202, 'SO', 'Somalia');
INSERT INTO `PAIS` VALUES(203, 'LK', 'Sri Lanka');
INSERT INTO `PAIS` VALUES(204, 'SZ', 'Suazilandia');
INSERT INTO `PAIS` VALUES(205, 'ZA', 'Sudáfrica');
INSERT INTO `PAIS` VALUES(206, 'SD', 'Sudán');
INSERT INTO `PAIS` VALUES(207, 'SE', 'Suecia');
INSERT INTO `PAIS` VALUES(208, 'CH', 'Suiza');
INSERT INTO `PAIS` VALUES(209, 'SR', 'Surinam');
INSERT INTO `PAIS` VALUES(210, 'SJ', 'Svalbard y Jan Mayen');
INSERT INTO `PAIS` VALUES(211, 'TH', 'Tailandia');
INSERT INTO `PAIS` VALUES(212, 'TW', 'Taiwán');
INSERT INTO `PAIS` VALUES(213, 'TZ', 'Tanzania');
INSERT INTO `PAIS` VALUES(214, 'TJ', 'Tayikistán');
INSERT INTO `PAIS` VALUES(215, 'IO', 'Territorio Británico del Océano Índico');
INSERT INTO `PAIS` VALUES(216, 'TF', 'Territorios Australes Franceses');
INSERT INTO `PAIS` VALUES(217, 'TL', 'Timor Oriental');
INSERT INTO `PAIS` VALUES(218, 'TG', 'Togo');
INSERT INTO `PAIS` VALUES(219, 'TK', 'Tokelau');
INSERT INTO `PAIS` VALUES(220, 'TO', 'Tonga');
INSERT INTO `PAIS` VALUES(221, 'TT', 'Trinidad y Tobago');
INSERT INTO `PAIS` VALUES(222, 'TN', 'Túnez');
INSERT INTO `PAIS` VALUES(223, 'TC', 'Islas Turcas y Caicos');
INSERT INTO `PAIS` VALUES(224, 'TM', 'Turkmenistán');
INSERT INTO `PAIS` VALUES(225, 'TR', 'Turquía');
INSERT INTO `PAIS` VALUES(226, 'TV', 'Tuvalu');
INSERT INTO `PAIS` VALUES(227, 'UA', 'Ucrania');
INSERT INTO `PAIS` VALUES(228, 'UG', 'Uganda');
INSERT INTO `PAIS` VALUES(229, 'UY', 'Uruguay');
INSERT INTO `PAIS` VALUES(230, 'UZ', 'Uzbekistán');
INSERT INTO `PAIS` VALUES(231, 'VU', 'Vanuatu');
INSERT INTO `PAIS` VALUES(232, 'VE', 'Venezuela');
INSERT INTO `PAIS` VALUES(233, 'VN', 'Vietnam');
INSERT INTO `PAIS` VALUES(234, 'VG', 'Islas Vírgenes Británicas');
INSERT INTO `PAIS` VALUES(235, 'VI', 'Islas Vírgenes de los Estados Unidos');
INSERT INTO `PAIS` VALUES(236, 'WF', 'Wallis y Futuna');
INSERT INTO `PAIS` VALUES(237, 'YE', 'Yemen');
INSERT INTO `PAIS` VALUES(238, 'DJ', 'Yibuti');
INSERT INTO `PAIS` VALUES(239, 'ZM', 'Zambia');
INSERT INTO `PAIS` VALUES(240, 'ZW', 'Zimbabue');

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

