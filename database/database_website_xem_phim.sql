-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: website_xem_phim
-- ------------------------------------------------------
-- Server version	8.0.16

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
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `actor` (
  `id_actor` int(11) NOT NULL AUTO_INCREMENT,
  `name_actor` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `code_actor` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_country` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_actor`),
  KEY `fk_quoctich_dv_idx` (`id_country`),
  CONSTRAINT `fk_quoctich_dv` FOREIGN KEY (`id_country`) REFERENCES `country` (`id_country`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
INSERT INTO `actor` VALUES (1,'Lưu Đào','luu-dao',4),(2,'Dương Thước','duong-thuoc',4),(3,'Kim Thần','kim-than',4),(4,'Lưu Đoan Đoan','luu-doan-doan',4),(5,'Trình Tranh','trinh-tranh',4),(6,'Thái Tử Luân','thai-tu-luan',4),(7,'Vương Nhược Tuyết','vuong-nhuoc-tuyet',4),(8,'Nhậm Lệ','nham-le',4);
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `category` (
  `id_category` int(11) NOT NULL AUTO_INCREMENT,
  `name_category` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `code_category` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_category`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Cổ Trang - Thần Thoại','co-trang-than-thoai'),(2,'Võ Thuật - Kiếm Hiệp','vo-thuat-kiem-hiep'),(3,'Phiêu Lưu - Hành Động','phieu-luu-hanh-dong'),(4,'Tâm Lý - Tình Cảm','tam-ly-tinh-cam'),(5,'Hoạt Hình','hoat-hinh'),(6,'Khoa Học - Viễn Tưởng','khoa-hoc-vien-tuong'),(7,'Gia Đình - Học Đường','gia-dienh-hoc-duong'),(8,'Thể Thao - Âm Nhạc','the-thao-am-nhac');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `country` (
  `id_country` int(11) NOT NULL AUTO_INCREMENT,
  `name_country` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `code_country` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_country`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Việt Nam','viet-nam'),(2,'Nhật Bản','nhat-ban'),(3,'Hàn Quốc','han-quoc'),(4,'Trung Quốc','trung-quoc'),(5,'Âu Mỹ','au-my'),(6,'Ấn Độ','an-do'),(7,'Thái Lan','thai-lan');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `director`
--

DROP TABLE IF EXISTS `director`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `director` (
  `id_director` int(11) NOT NULL AUTO_INCREMENT,
  `name_director` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_country` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_director`),
  KEY `fk_quoctich_daodien_idx` (`id_country`),
  CONSTRAINT `fk_quoctich_daodien` FOREIGN KEY (`id_country`) REFERENCES `country` (`id_country`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `director`
--

LOCK TABLES `director` WRITE;
/*!40000 ALTER TABLE `director` DISABLE KEYS */;
/*!40000 ALTER TABLE `director` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `episode`
--

DROP TABLE IF EXISTS `episode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `episode` (
  `id_episode` int(11) NOT NULL AUTO_INCREMENT,
  `link_episode` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ordinal_numbers` int(11) DEFAULT '0',
  `id_movie` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_episode`),
  KEY `fk_movie_idx` (`id_movie`),
  CONSTRAINT `dsafsadf` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id_movie`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `episode`
--

LOCK TABLES `episode` WRITE;
/*!40000 ALTER TABLE `episode` DISABLE KEYS */;
INSERT INTO `episode` VALUES (23,'phim-moi-26-tap-1-2',1,37),(24,'phim-moi-26-tap-2-2',2,37),(25,'phim-moi-12222-25-tap-1-4',1,36),(26,'phim-moi-12222-25-tap-2-4',2,36),(29,'phim-moi-38-tap-1-6',1,52),(30,'phim-moi-38-tap-2-6',2,52),(31,'phim-moi-38-tap-3-7',3,52);
/*!40000 ALTER TABLE `episode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `movie` (
  `id_movie` int(11) NOT NULL AUTO_INCREMENT,
  `name_movie` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `name_english` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `content` mediumtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `status` int(2) DEFAULT NULL,
  `link_movie` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `number_episode_movie` int(11) DEFAULT '0',
  `datetime_post` datetime DEFAULT NULL,
  `language` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `view` int(11) DEFAULT '0',
  `year_produce` int(11) DEFAULT '0',
  `id_country_produce` int(11) DEFAULT NULL,
  `id_user_post` int(11) DEFAULT NULL,
  `id_director` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_movie`),
  KEY `fk_quocgia_sx_idx` (`id_country_produce`),
  KEY `fk_user_post_idx` (`id_user_post`),
  KEY `fk_daodiensx_idx` (`id_director`),
  KEY `id_director` (`id_director`),
  CONSTRAINT `FKbjus48sqw06rsxe4ndehy99y4` FOREIGN KEY (`id_country_produce`) REFERENCES `country` (`id_country`),
  CONSTRAINT `FKgjmpxku41anfij3pv75mtidw1` FOREIGN KEY (`id_director`) REFERENCES `director` (`id_director`),
  CONSTRAINT `fk_daodiensx` FOREIGN KEY (`id_director`) REFERENCES `director` (`id_director`),
  CONSTRAINT `fk_quocgia_sx` FOREIGN KEY (`id_country_produce`) REFERENCES `country` (`id_country`),
  CONSTRAINT `fk_user_post` FOREIGN KEY (`id_user_post`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (9,'Chúng Ta Đều Phải Sống Tốt',NULL,'when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',0,'chung-ta-đeu-phai-song-tot-1',11,'2019-05-13 01:18:01',NULL,70,2019,4,NULL,NULL),(10,'Bạch Phát',NULL,'when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',0,'bach-phat-2',1,'2019-05-13 01:19:23',NULL,7,2019,4,NULL,NULL),(11,'Chúng Ta Đều Phải Sống Tốt',NULL,'when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',1,'chung-ta-đeu-phai-song-tot-3',0,'2019-05-13 01:19:41',NULL,1,2019,1,NULL,NULL),(12,'Chiêu Diêu',NULL,'when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',0,'chieu-dieu-4',55,'2019-05-13 01:19:56',NULL,3,2019,4,NULL,NULL),(13,'Lưỡng Thế Hoan','printing and typesettin','when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',1,'luong-the-hoan-5',0,'2019-05-13 01:21:18',NULL,3,2019,4,NULL,NULL),(16,'Chạy Đi Chờ Chi',NULL,'Chạy Đi Chờ Chi là phiên bản Việt hóa của chương trình từ Hàn Quốc, nhưng phía điều hành và sản xuất cho biết, điểm nhấn của chương trình Chạy Đi Chờ Chi, cũng giống như tên gọi, sẽ là một chương trình đậm chất Việt Nam. Toàn bộ ê-kíp của SBS Running Man Hàn Quốc sang tận Việt Nam, lựa chọn những điều “rất Việt” vào chương trình, với hy vọng làm nên một mô hình giải trí gần gũi với khán giả và xa hơn, là giới thiệu được những đặc trưng văn hóa Việt Nam đến người xem toàn Thế giới.\r\n',1,'chay-đi-cho-chi-8',11,'2019-05-13 02:05:37',NULL,0,2019,1,NULL,NULL),(17,'Mê Cung',NULL,'Mê Cung đưa người xem lạc vào một mê cung khó tìm được lối ra. Phim bắt đầu bằng vụ án một cô gái trẻ bị sát hại trong đêm tại một xóm trọ. Hung thủ bị bắt ngay sau đó với những chứng cớ buộc tội đanh thép. Vụ án tưởng chừng khép lại nếu như Khánh (Hồng Đăng) - Đội trưởng đội hình sự - không kiên quyết lật ngược vấn đề và tìm ra vụ án tương tụ diễn ra trước đó 2 tháng. Hai nạn nhân khá thân nhau. Hai gã hung thủ đều bị bắt rất nhanh với chất kích thích vẫn còn trong máu. Nhưng liệu những kẻ đó có phải hung thủ thực sự hay chỉ là con tốt thí mạng cho kế hoạch hoàn hảo của tên sát nhân thông minh đến mức biến thái?',0,'me-cung-9',4,'2019-05-13 02:06:21',NULL,2,2019,1,NULL,NULL),(18,'Thần Thám',NULL,'Thần Thám kể về Bạch Vũ một lần nữa hóa thân vào vai một trinh sát nhân dân mẫn cán, làm việc bảo vệ lẽ phải tên La Phi. Sở hữu chỉ số IQ cao cùng thân thủ rất tốt, La Phi thường một mình làm việc, phá bỏ nhiều vụ án quan trọng, đầy cam go.',1,'than-tham-10',3,'2019-05-13 10:03:48',NULL,0,2019,4,NULL,NULL),(19,'Chiêu Diêu',NULL,'sdfsd',0,'chieu-dieu-11',0,'2019-05-14 09:59:23',NULL,0,2019,2,NULL,NULL),(20,'Lưỡng Thế Hoan','Lưỡng Thế Hoan','Lưỡng Thế Hoan',1,'abc-12',0,'2019-05-14 10:42:40',NULL,1,2019,1,NULL,NULL),(23,'phim moi',NULL,'noi dung phim',1,'phim-moi-13',0,'2019-06-19 19:53:48',NULL,7,2017,2,NULL,NULL),(25,'phim moi 1',NULL,'adđ',1,'phim-moi-1-14',0,'2019-06-19 21:28:13',NULL,0,2019,1,NULL,NULL),(26,'phim moi 12',NULL,'2',1,'phim-moi-12-15',0,'2019-06-19 21:29:09',NULL,0,2019,1,NULL,NULL),(27,'phim moi 3',NULL,'4',1,'phim-moi-3-16',0,'2019-06-19 21:29:22',NULL,0,2019,1,NULL,NULL),(28,'phim moi 4',NULL,'ddd',1,'phim-moi-4-17',0,'2019-06-19 21:30:48',NULL,0,2019,1,NULL,NULL),(29,'phim môi 5',NULL,'33',1,'phim-moi-5-18',0,'2019-06-19 21:31:25',NULL,0,2019,1,NULL,NULL),(30,'dddd',NULL,'eeee',1,'dddd-19',0,'2019-06-19 21:31:42',NULL,0,2019,1,NULL,NULL),(31,'phim moi 14',NULL,'4',1,'phim-moi-14-20',0,'2019-06-19 21:32:02',NULL,0,2019,1,NULL,NULL),(32,'phim moi 124',NULL,'3333',1,'phim-moi-124-21',0,'2019-06-19 21:32:49',NULL,5,2019,1,NULL,NULL),(33,'sdfadf',NULL,'d',1,'sdfadf-22',0,'2019-06-19 21:33:04',NULL,0,2019,1,NULL,NULL),(34,'32324',NULL,'22',1,'32324-23',0,'2019-06-19 21:33:19',NULL,30,2019,1,NULL,NULL),(35,'phim moi11',NULL,'3',1,'phim-moi11-24',0,'2019-06-20 01:16:20',NULL,15,2019,1,NULL,NULL),(36,'phim moi 12222',NULL,'sdfsdf',1,'phim-moi-12222-25',0,'2019-06-20 01:16:35',NULL,15,2019,1,NULL,NULL),(37,'phim moi',NULL,'sdfadf',0,'phim-moi-26',0,'2019-06-20 02:07:02',NULL,5,2019,1,NULL,NULL),(52,'phim moi',NULL,'sdfsdf',1,'phim-moi-38',0,'2019-06-23 12:34:52',NULL,7,2019,1,5,NULL);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_actor`
--

DROP TABLE IF EXISTS `movie_actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `movie_actor` (
  `id_movie` int(11) NOT NULL,
  `id_actor` int(11) NOT NULL,
  PRIMARY KEY (`id_actor`,`id_movie`),
  KEY `fk_movie_idx` (`id_movie`),
  CONSTRAINT `FKd0gf6todt6rgvn1bqaxvhx5m6` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id_movie`),
  CONSTRAINT `FKsms00nlffofe5lm78j51kuatf` FOREIGN KEY (`id_actor`) REFERENCES `actor` (`id_actor`),
  CONSTRAINT `fk_actor` FOREIGN KEY (`id_actor`) REFERENCES `actor` (`id_actor`),
  CONSTRAINT `fk_movie` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id_movie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_actor`
--

LOCK TABLES `movie_actor` WRITE;
/*!40000 ALTER TABLE `movie_actor` DISABLE KEYS */;
INSERT INTO `movie_actor` VALUES (9,1),(9,2),(9,3),(9,4),(9,5),(9,6),(9,7),(9,8),(10,1),(10,2),(10,3),(10,8);
/*!40000 ALTER TABLE `movie_actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_category`
--

DROP TABLE IF EXISTS `movie_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `movie_category` (
  `id_movie` int(11) NOT NULL,
  `id_category` int(11) NOT NULL,
  PRIMARY KEY (`id_movie`,`id_category`),
  KEY `fk_category_idx` (`id_category`),
  CONSTRAINT `FKkiiga47t25i6mh7msj4svk9dr` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id_movie`),
  CONSTRAINT `FKnv6u8cbgiotobylajcr5hkcok` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`),
  CONSTRAINT `fk_category` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`),
  CONSTRAINT `fk_movie_movi` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id_movie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_category`
--

LOCK TABLES `movie_category` WRITE;
/*!40000 ALTER TABLE `movie_category` DISABLE KEYS */;
INSERT INTO `movie_category` VALUES (9,1),(10,1),(13,1),(52,1),(9,2),(10,2),(11,2),(12,2),(9,3),(10,3),(11,3),(12,3),(9,4),(10,4),(11,4),(12,4),(52,4),(10,5),(11,5),(12,5),(10,6),(11,6);
/*!40000 ALTER TABLE `movie_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (3,'ROLE_USER'),(4,'ROLE_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slide`
--

DROP TABLE IF EXISTS `slide`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `slide` (
  `id_slide` int(11) NOT NULL AUTO_INCREMENT,
  `link_image` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `id_movie` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_slide`),
  KEY `fk_abcdddd_idx` (`id_movie`),
  CONSTRAINT `fk_abcdddd` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id_movie`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slide`
--

LOCK TABLES `slide` WRITE;
/*!40000 ALTER TABLE `slide` DISABLE KEYS */;
INSERT INTO `slide` VALUES (1,'slide1',1,9),(2,'slide2',1,10),(3,'slide3',1,11),(4,'slide4',0,12);
/*!40000 ALTER TABLE `slide` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trailer`
--

DROP TABLE IF EXISTS `trailer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `trailer` (
  `id_trailer` int(11) NOT NULL AUTO_INCREMENT,
  `link_trailer` varchar(45) DEFAULT NULL,
  `ordinal_numbers` varchar(45) DEFAULT NULL,
  `id_movie` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_trailer`),
  KEY `fk_asdfsdf_idx` (`id_movie`),
  CONSTRAINT `fk_asdfsdf` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id_movie`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trailer`
--

LOCK TABLES `trailer` WRITE;
/*!40000 ALTER TABLE `trailer` DISABLE KEYS */;
INSERT INTO `trailer` VALUES (1,'phim-moi-38-trailer-1-2','1',52);
/*!40000 ALTER TABLE `trailer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `datetime_created` datetime DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (5,'admin@gmail.com','$2a$10$zmsKgf/vaU04X2AqLdG6mOEeCNXurX4B0dwedoTmzOHQGKgtvTiE6','2019-06-18 00:00:00'),(6,'user@gmail.com','$2a$10$OHeltD6NB6C2ChwsuqxOQ.90Q8PGU9chUQK5FE22NGF9/i9jVi1V6',NULL),(7,'user1@gmail.com','$2a$10$nVvYv3WGnk2NhIrL.mp9LOg1lORQoP5uxcv9s8ofhwAOPFKRw.93K',NULL),(8,'user2@gmail.com','$2a$10$0g7xuHZ6MeMzVw8x0F44PeF/7ngpDZaxp4NBDAZWpfDMJ8sBxZ9i.',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_role` (
  `id_user` int(11) NOT NULL,
  `id_role` int(11) NOT NULL,
  PRIMARY KEY (`id_user`,`id_role`),
  KEY `fk_role_idx` (`id_role`),
  CONSTRAINT `FK2aam9nt2tv8vcfymi3jo9c314` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`),
  CONSTRAINT `FKfhxaael2m459kbk8lv8smr5iv` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  CONSTRAINT `fk_role` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`),
  CONSTRAINT `fk_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (5,3),(6,3),(7,3),(8,3),(5,4);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-23 17:19:31
