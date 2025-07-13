-- MySQL dump 10.13  Distrib 8.0.40, for Linux (x86_64)
--
-- Host: localhost    Database: viajecitos_db
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `amortizacion`
--

USE viajecitos_db;

DROP TABLE IF EXISTS `amortizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `amortizacion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `factura_id` int NOT NULL,
  `numero_cuota` int NOT NULL,
  `fecha_pago` date NOT NULL,
  `monto_cuota` decimal(10,2) NOT NULL,
  `saldo_restante` decimal(10,2) DEFAULT NULL,
  `estado_pago` enum('Pendiente','Pagado') DEFAULT 'Pendiente',
  PRIMARY KEY (`id`),
  KEY `factura_id` (`factura_id`),
  CONSTRAINT `amortizacion_ibfk_1` FOREIGN KEY (`factura_id`) REFERENCES `facturas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amortizacion`
--

LOCK TABLES `amortizacion` WRITE;
/*!40000 ALTER TABLE `amortizacion` DISABLE KEYS */;
INSERT INTO `amortizacion` VALUES (1,3,1,'2025-07-16',36.67,73.33,'Pendiente'),(2,3,2,'2025-08-16',36.67,36.67,'Pendiente'),(3,3,3,'2025-09-16',36.67,0.00,'Pendiente'),(4,4,1,'2025-07-16',36.68,73.34,'Pendiente'),(5,4,2,'2025-08-16',36.68,36.67,'Pendiente'),(6,4,3,'2025-09-16',36.68,0.00,'Pendiente'),(7,5,1,'2025-07-16',36.68,73.34,'Pendiente'),(8,5,2,'2025-08-16',36.68,36.67,'Pendiente'),(9,5,3,'2025-09-16',36.68,0.00,'Pendiente'),(10,6,1,'2025-07-16',42.18,84.34,'Pendiente'),(11,6,2,'2025-08-16',42.18,42.17,'Pendiente'),(12,6,3,'2025-09-16',42.18,0.00,'Pendiente'),(13,7,1,'2025-07-16',42.19,84.33,'Pendiente'),(14,7,2,'2025-08-16',42.18,42.17,'Pendiente'),(15,7,3,'2025-09-16',42.17,0.00,'Pendiente'),(16,8,1,'2025-07-16',42.19,84.33,'Pendiente'),(17,8,2,'2025-08-16',42.18,42.17,'Pendiente'),(18,8,3,'2025-09-16',42.17,0.00,'Pendiente'),(19,10,1,'2025-07-17',84.36,168.68,'Pendiente'),(20,10,2,'2025-08-17',84.36,84.35,'Pendiente'),(21,10,3,'2025-09-17',84.36,0.00,'Pendiente'),(22,11,1,'2025-07-17',126.54,253.02,'Pendiente'),(23,11,2,'2025-08-17',126.54,126.52,'Pendiente'),(24,11,3,'2025-09-17',126.54,0.00,'Pendiente'),(25,12,1,'2025-07-18',126.54,253.02,'Pendiente'),(26,12,2,'2025-08-18',126.54,126.52,'Pendiente'),(27,12,3,'2025-09-18',126.54,0.00,'Pendiente'),(28,13,1,'2025-07-23',84.36,168.68,'Pendiente'),(29,13,2,'2025-08-23',84.36,84.35,'Pendiente'),(30,13,3,'2025-09-23',84.36,0.00,'Pendiente'),(31,14,1,'2025-07-24',43.23,84.86,'Pendiente'),(32,14,2,'2025-08-24',43.23,42.69,'Pendiente'),(33,14,3,'2025-09-24',43.23,0.00,'Pendiente');
/*!40000 ALTER TABLE `amortizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compras`
--

DROP TABLE IF EXISTS `compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compras` (
  `id` int NOT NULL AUTO_INCREMENT,
  `usuario_id` int NOT NULL,
  `metodo_pago_id` int NOT NULL,
  `fecha_compra` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `codigo_empleado` varchar(10) NOT NULL DEFAULT 'EMP001',
  `subtotal` decimal(10,2) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `metodo_pago_id` (`metodo_pago_id`),
  CONSTRAINT `compras_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `compras_ibfk_2` FOREIGN KEY (`metodo_pago_id`) REFERENCES `metodos_pago` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compras`
--

LOCK TABLES `compras` WRITE;
/*!40000 ALTER TABLE `compras` DISABLE KEYS */;
INSERT INTO `compras` VALUES (1,1,3,'2025-06-16 23:10:57','EMP001',110.00,110.00),(2,1,3,'2025-06-16 23:24:38','EMP001',110.00,110.00),(3,1,3,'2025-06-16 23:33:11','EMP001',110.00,110.00),(4,1,3,'2025-06-17 00:00:38','EMP001',110.00,110.00),(5,1,3,'2025-06-17 00:09:02','EMP001',110.00,110.00),(6,1,3,'2025-06-17 00:21:16','EMP001',110.00,126.50),(7,1,3,'2025-06-17 00:22:26','EMP001',110.00,126.50),(8,1,3,'2025-06-17 00:31:34','EMP001',110.00,126.50),(10,1,3,'2025-06-18 00:57:29','SISTEMA',220.00,253.00),(11,1,3,'2025-06-18 01:01:42','SISTEMA',330.00,379.50),(12,1,3,'2025-06-18 08:24:16','EMP001',330.00,379.50),(13,1,3,'2025-06-23 10:14:14','EMP001',220.00,253.00),(14,1,3,'2025-06-24 19:01:45','SISTEMA',110.00,126.50);
/*!40000 ALTER TABLE `compras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_compras`
--

DROP TABLE IF EXISTS `detalle_compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_compras` (
  `id` int NOT NULL AUTO_INCREMENT,
  `compra_id` int NOT NULL,
  `vuelo_id` int NOT NULL,
  `cantidad_asientos` int NOT NULL,
  `subtotal_vuelo` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `compra_id` (`compra_id`),
  KEY `vuelo_id` (`vuelo_id`),
  CONSTRAINT `detalle_compras_ibfk_1` FOREIGN KEY (`compra_id`) REFERENCES `compras` (`id`),
  CONSTRAINT `detalle_compras_ibfk_2` FOREIGN KEY (`vuelo_id`) REFERENCES `vuelos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_compras`
--

LOCK TABLES `detalle_compras` WRITE;
/*!40000 ALTER TABLE `detalle_compras` DISABLE KEYS */;
INSERT INTO `detalle_compras` VALUES (1,1,1,1,110.00),(2,2,1,1,110.00),(3,3,1,1,110.00),(4,4,1,1,110.00),(5,5,1,1,110.00),(6,6,1,1,110.00),(7,7,1,1,110.00),(8,8,1,1,110.00),(10,10,1,2,220.00),(11,11,1,3,330.00),(12,12,3,3,330.00),(13,13,4,2,220.00),(14,14,5,1,110.00);
/*!40000 ALTER TABLE `detalle_compras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facturas`
--

DROP TABLE IF EXISTS `facturas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facturas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `compra_id` int NOT NULL,
  `fecha_emision` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `compra_id` (`compra_id`),
  CONSTRAINT `facturas_ibfk_1` FOREIGN KEY (`compra_id`) REFERENCES `compras` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facturas`
--

LOCK TABLES `facturas` WRITE;
/*!40000 ALTER TABLE `facturas` DISABLE KEYS */;
INSERT INTO `facturas` VALUES (1,1,'2025-06-16 23:10:57'),(2,2,'2025-06-16 23:24:38'),(3,3,'2025-06-16 23:33:11'),(4,4,'2025-06-17 00:00:38'),(5,5,'2025-06-17 00:09:02'),(6,6,'2025-06-17 00:21:16'),(7,7,'2025-06-17 00:22:26'),(8,8,'2025-06-17 00:31:34'),(10,10,'2025-06-18 00:57:29'),(11,11,'2025-06-18 01:01:43'),(12,12,'2025-06-18 08:24:16'),(13,13,'2025-06-23 10:14:14'),(14,14,'2025-06-24 19:01:45');
/*!40000 ALTER TABLE `facturas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historial_busquedas`
--

DROP TABLE IF EXISTS `historial_busquedas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historial_busquedas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `usuario_id` int NOT NULL,
  `ciudad_origen` varchar(3) NOT NULL,
  `ciudad_destino` varchar(3) NOT NULL,
  `fecha_busqueda` date NOT NULL,
  `fecha_realizada` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  CONSTRAINT `historial_busquedas_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historial_busquedas`
--

LOCK TABLES `historial_busquedas` WRITE;
/*!40000 ALTER TABLE `historial_busquedas` DISABLE KEYS */;
INSERT INTO `historial_busquedas` VALUES (1,1,'UIO','GYE','2025-06-16','2025-06-16 23:33:11'),(2,1,'UIO','GYE','2025-06-16','2025-06-17 00:09:02'),(3,1,'UIO','GYE','2025-06-16','2025-06-17 00:21:16'),(4,1,'UIO','GYE','2025-06-16','2025-06-17 00:22:26'),(5,1,'UIO','GYE','2025-06-16','2025-06-17 00:31:34'),(6,1,'uio','Gye','2025-06-16','2025-06-17 12:47:49'),(7,1,'uio','gye','2025-06-16','2025-06-17 12:58:57'),(8,1,'uio','gye','2025-06-16','2025-06-17 13:00:28'),(9,1,'uio','gye','2025-06-16','2025-06-17 13:04:43'),(10,1,'uio','gye','2025-06-16','2025-06-17 13:26:53'),(11,1,'uio','gye','2025-06-16','2025-06-17 13:30:00'),(12,1,'uio','gye','2025-06-16','2025-06-18 00:01:25'),(13,1,'uio','gye','2025-06-16','2025-06-18 00:01:39'),(14,1,'uio','gye','2025-06-16','2025-06-18 00:14:39'),(15,1,'uio','gye','2025-06-16','2025-06-18 00:53:54'),(16,1,'uio','gye','2025-06-16','2025-06-18 00:56:03'),(17,1,'uio','gye','2025-06-16','2025-06-18 00:56:29'),(18,1,'uio','gye','2025-06-16','2025-06-18 00:56:55'),(19,1,'uio','gye','2025-06-16','2025-06-18 01:01:26'),(20,1,'uio','Gye','2025-06-16','2025-06-18 01:17:14'),(21,1,'uio','Gye','2025-06-16','2025-06-17 20:23:23'),(22,1,'uio','gye','2025-06-16','2025-06-18 07:30:39'),(23,1,'uio','gye','2025-06-16','2025-06-18 08:21:20'),(24,1,'uio','gye','2025-06-18','2025-06-18 08:22:44'),(25,1,'uio','gye','2025-06-18','2025-06-18 08:23:44'),(26,1,'uio','gye','2025-06-19','2025-06-18 08:24:01'),(27,1,'uio','gye','2025-06-19','2025-06-23 09:53:21'),(28,1,'uio','gye','2025-06-19','2025-06-23 10:03:04'),(29,1,'uio','gye','2025-06-23','2025-06-23 10:13:45'),(30,1,'uio','gye','2025-06-26','2025-06-24 17:11:27'),(31,1,'uio','gye','2025-06-26','2025-06-24 17:11:58'),(32,1,'uio','gye','2025-06-26','2025-06-24 17:15:01'),(33,1,'uio','gye','2025-06-26','2025-06-24 17:16:35'),(34,1,'uio','gye','2025-06-26','2025-06-24 17:24:51'),(35,1,'uio','gye','2025-06-26','2025-06-24 17:32:41'),(36,1,'uio','gye','2025-06-26','2025-06-24 19:01:19'),(37,1,'uio','gye','2025-06-26','2025-06-30 07:55:13'),(38,1,'uio','gye','2025-06-26','2025-07-01 17:57:43'),(39,1,'uio','gye','2025-07-26','2025-07-01 18:55:15'),(40,1,'uio','gye','2025-07-26','2025-07-01 18:55:47'),(41,1,'uio','gye','2025-07-26','2025-07-02 08:19:23'),(42,1,'uio','gye','2025-07-26','2025-07-02 08:26:14');
/*!40000 ALTER TABLE `historial_busquedas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metodos_pago`
--

DROP TABLE IF EXISTS `metodos_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `metodos_pago` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre_metodo` varchar(50) NOT NULL,
  `tipo_pago` enum('Efectivo','Crédito') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_metodo` (`nombre_metodo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metodos_pago`
--

LOCK TABLES `metodos_pago` WRITE;
/*!40000 ALTER TABLE `metodos_pago` DISABLE KEYS */;
INSERT INTO `metodos_pago` VALUES (1,'Efectivo','Efectivo'),(2,'Transferencia','Efectivo'),(3,'Tarjeta ','Crédito');
/*!40000 ALTER TABLE `metodos_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `cedula` varchar(20) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `nombre_usuario` varchar(50) NOT NULL,
  `contrasena` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cedula` (`cedula`),
  UNIQUE KEY `correo` (`correo`),
  UNIQUE KEY `nombre_usuario` (`nombre_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Monster','Campaña','0101010101','monster@example.com','0991112233','Monster','Monster9'),(2,'María','Gómez','0202020202','maria.gomez@example.com','0992223344','mariag','clave123'),(3,'Carlos','Ruiz','0303030303','carlos.ruiz@example.com','0993334455','carlosr','clave123'),(4,'Ana','Loja','0404040404','ana.loja@example.com','0994445566','anal','clave123'),(5,'Luis','Torres','0505050505','luis.torres@example.com','0995556677','luist','clave123'),(6,'Sofía','Naranjo','0606060606','sofia.naranjo@example.com','0996667788','sofian','clave123');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vuelos`
--

DROP TABLE IF EXISTS `vuelos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vuelos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ciudad_origen` varchar(3) NOT NULL,
  `ciudad_destino` varchar(3) NOT NULL,
  `fecha_salida` date NOT NULL,
  `hora_salida` time NOT NULL,
  `valor` decimal(8,2) NOT NULL,
  `asientos_disponibles` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vuelos`
--

LOCK TABLES `vuelos` WRITE;
/*!40000 ALTER TABLE `vuelos` DISABLE KEYS */;
INSERT INTO `vuelos` VALUES (1,'UIO','GYE','2025-06-16','08:30:00',110.00,50),(2,'UIO','GYE','2025-06-18','08:30:00',110.00,50),(3,'UIO','GYE','2025-06-19','08:30:00',110.00,50),(4,'UIO','GYE','2025-06-23','18:30:00',110.00,48),(5,'UIO','GYE','2025-06-26','18:30:00',110.00,49),(6,'UIO','GYE','2025-06-26','10:30:00',110.00,50),(7,'UIO','GYE','2025-07-26','18:30:00',110.00,50),(8,'UIO','GYE','2025-07-26','10:30:00',110.00,50);
/*!40000 ALTER TABLE `vuelos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-10  7:20:53
