-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: democesi
-- ------------------------------------------------------
-- Server version	8.0.30

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

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `id_categoria` int NOT NULL AUTO_INCREMENT,
  `nombre_categoria` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_categoria`),
  UNIQUE KEY `nombre_categoria_UNIQUE` (`nombre_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jornadas`
--

DROP TABLE IF EXISTS `jornadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jornadas` (
  `id_jornada` int NOT NULL AUTO_INCREMENT,
  `objetivo_jornada` varchar(255) DEFAULT NULL,
  `titulo_jornada` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_jornada`),
  UNIQUE KEY `titulo_UNIQUE` (`titulo_jornada`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `materiales`
--

DROP TABLE IF EXISTS `materiales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materiales` (
  `id_material` int NOT NULL AUTO_INCREMENT,
  `titulo_jornada` varchar(45) DEFAULT NULL,
  `nombre_categoria` varchar(45) DEFAULT NULL,
  `descripcion_material` varchar(255) DEFAULT NULL,
  `fuente_material` varchar(255) DEFAULT NULL,
  `enlaceDoc_material` varchar(255) DEFAULT NULL,
  `tipo_material` varchar(255) DEFAULT NULL,
  `titulo_material` varchar(45) DEFAULT NULL,
  `prioritario_material` tinyint DEFAULT NULL,
  `procedencia_material` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_material`),
  UNIQUE KEY `titulo_UNIQUE` (`titulo_material`),
  KEY `id_jornada_idx` (`titulo_jornada`),
  KEY `matcat_idx` (`nombre_categoria`),
  CONSTRAINT `matcat` FOREIGN KEY (`nombre_categoria`) REFERENCES `categorias` (`nombre_categoria`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `matjor` FOREIGN KEY (`titulo_jornada`) REFERENCES `jornadas` (`titulo_jornada`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `materiales_propuestas`
--

DROP TABLE IF EXISTS `materiales_propuestas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materiales_propuestas` (
  `id_materiales_propuestas` varchar(45) NOT NULL,
  `titulo_propuesta` varchar(45) DEFAULT NULL,
  `titulo_material` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_materiales_propuestas`),
  KEY `id_propuesta_idx` (`titulo_propuesta`),
  KEY `id_material_idx` (`titulo_material`),
  CONSTRAINT `mpmat` FOREIGN KEY (`titulo_material`) REFERENCES `materiales` (`titulo_material`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `mpprop` FOREIGN KEY (`titulo_propuesta`) REFERENCES `propuestas` (`titulo_propuesta`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `propuestas`
--

DROP TABLE IF EXISTS `propuestas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `propuestas` (
  `id_propuesta` int NOT NULL AUTO_INCREMENT,
  `nombre_categoria` varchar(45) DEFAULT NULL,
  `autor_propuesta` varchar(255) DEFAULT NULL,
  `fecha_propuesta` date DEFAULT NULL,
  `titulo_propuesta` varchar(255) DEFAULT NULL,
  `descripcion_propuesta` varchar(255) DEFAULT NULL,
  `estado_propuesta` varchar(255) DEFAULT NULL,
  `motivoRechazo_propuesta` varchar(255) DEFAULT NULL,
  `motivacion_propuesta` varchar(255) DEFAULT NULL,
  `origen_propuesta` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_propuesta`),
  UNIQUE KEY `titulo_UNIQUE` (`titulo_propuesta`),
  KEY `propcat_idx` (`nombre_categoria`),
  CONSTRAINT `propcat` FOREIGN KEY (`nombre_categoria`) REFERENCES `categorias` (`nombre_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-22  2:06:28
