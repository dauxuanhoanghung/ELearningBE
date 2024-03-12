CREATE DATABASE  IF NOT EXISTS `elearning` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `elearning`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: elearning
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` text COLLATE utf8mb4_unicode_ci,
  `author_id` int NOT NULL,
  `publish_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `author_id` (`author_id`),
  CONSTRAINT `blog_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog_comment`
--

DROP TABLE IF EXISTS `blog_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog_comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blog_id` int NOT NULL,
  `user_id` int NOT NULL,
  `content` text COLLATE utf8mb4_unicode_ci,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `blog_comment_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`),
  CONSTRAINT `blog_comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_comment`
--

LOCK TABLES `blog_comment` WRITE;
/*!40000 ALTER TABLE `blog_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `subtitle` varchar(225) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `background` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `slug` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `price` decimal(10,2) DEFAULT '0.00',
  `creator_id` int NOT NULL,
  `publish_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `creator_id` (`creator_id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'Get Started with Git/Github','This course is for starter to begin learning Git/Github','Get Started with Git/Github','https://techvccloud.mediacdn.vn/280518386289090560/2021/3/2/023-1614681588418717257234-0-0-767-1366-crop-16146815915111444794187.png','get-started-with-git-github',10000.00,1,'2023-09-24 17:00:00','2023-09-25 14:28:54'),(2,'Python Master','Python beginner to Master','Python beginner to Master','https://res.cloudinary.com/dexvnphga/image/upload/v1696154801/DoAn/jhxbq4hmbwlzxe7v4ue6.png','python-master',0.00,1,'2023-09-30 17:00:00','2023-10-01 10:06:39'),(3,'ReactJS Net Ninja Course','ReactJS  beginner to Master','ReactJS beginner to Master','https://media.geeksforgeeks.org/wp-content/cdn-uploads/20200309202057/How-To-Learn-ReactJS-A-Complete-Guide-For-Beginners.jpg','reactjs-net-ninja-course',0.00,1,'2023-09-24 17:00:00','2023-09-25 14:28:54'),(4,'Vue JS 3 Tutorial for Beginners - Net Ninja','Vue JS 3 Tutorial for Beginners','Vue JS 3 Tutorial for Beginners, FROM ZERO TO HERO','https://d2ms8rpfqc4h24.cloudfront.net/Introduction_to_Vue_JS_959ca27287.jpg','vue-js-3-tutorial-net-ninja',0.00,1,'2023-11-24 17:00:00','2023-11-24 17:00:00'),(5,'JS Course for Beginners','JS Course','JS Course','https://res.cloudinary.com/dexvnphga/image/upload/v1697727137/DoAn/yjzacqm7zgc1o6qqiphi.jpg','js-course-for-beginners',19998.00,2,'2023-10-18 17:00:00','2023-10-19 14:52:15'),(6,'Angular 2 Tutorial with Net Ninja','Yo gang, welcome to your very first Angular 2 tutorial for beginners... Angular 2 is one of the most popular JavaScript frameworks for creating dynamic web applications, and comes hot on the tail of it\'s incredibly popular predecessor, Angular 1. A lot has changed since then, so through this ng2 series, I\'ll be guiding you through how to create a we app from scratch, and teaching you the core essentials along the way...','Angular 2 Tutorial','https://miro.medium.com/v2/resize:fit:1400/1*4hhei-6UcZ1OFUG7-Z_vjw.jpeg','angular-2-tutorial-with-net-ninja',0.00,2,'2023-11-25 17:00:00','2023-11-25 17:00:00'),(7,'Python Tkinter GUI','Python Tkinter GUI','Python Tkinter GUI','https://i.ytimg.com/vi/YBvFcvisxxM/maxresdefault.jpg','python-tkinter-gui',0.00,5,'2023-11-25 17:00:00','2023-11-25 17:00:00'),(8,'ElectronJS Tutorial','In this ElectronJS Tutorial, we learn what is electron js and why we use it. Make Desktop software with HTML, CSS, JS','Electron js tutorial for beginners','https://i.ytimg.com/vi/sJFuMKPfpfs/sddefault.jpg','electronjs-tutorial',0.00,5,'2023-12-05 17:00:00','2023-12-04 17:00:00'),(9,'Python Selenium','This selenium tutorial is designed for beginners to learn how to use the python selenium module to perform web scraping, web testing and create website bots. Selenium is an automation framework that allows you to interact with websites using something called a web driver.','Selenium with Python','https://blog.testproject.io/wp-content/uploads/2020/06/16-06-A.jpg','python-selenium',0.00,2,'2023-12-05 17:00:00','2023-12-05 17:00:00'),(10,'NodeJS Express MongoDB Tutorial 2023','This is a complete NodeJS masterclass 12-hour, with ExpressJS, MongoDB, Mongoose, and other MERN stack elements. #nodejs #expressjs #mongodb #reactjs ','MERN stack. MongoDB ExpressJS. NodeJS','https://i.ytimg.com/vi/VT20NTbn6U4/maxresdefault.jpg','node-expressjs-mongodb-tutorial-2023',0.00,1,'2023-12-05 17:00:00','2023-12-05 17:00:00'),(11,'Rails 6 for Beginners','This is a complete Rails 6 for beginners','Rails 6 for Beginners','https://i.ytimg.com/vi/F-eI4MXnyPA/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLBQ98wPnb0rsj1tG3t6yC0eE6MvOg','rails-6-for-beginners',0.00,1,'2023-12-05 17:00:00','2023-12-05 17:00:00');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_comment`
--

DROP TABLE IF EXISTS `course_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NOT NULL,
  `user_id` int NOT NULL,
  `content` text COLLATE utf8mb4_unicode_ci,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `course_comment_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `course_comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_comment`
--

LOCK TABLES `course_comment` WRITE;
/*!40000 ALTER TABLE `course_comment` DISABLE KEYS */;
INSERT INTO `course_comment` VALUES (1,1,1,'This course is OK','2023-09-29 13:41:54'),(2,1,1,'This course is perfect to practice with Git and Github','2023-09-29 13:50:39'),(3,2,2,'This course is OK','2023-10-15 12:57:09'),(4,2,1,'This course is good','2023-10-15 12:57:51');
/*!40000 ALTER TABLE `course_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_criteria`
--

DROP TABLE IF EXISTS `course_criteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_criteria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NOT NULL,
  `text` text COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `course_criteria_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_criteria`
--

LOCK TABLES `course_criteria` WRITE;
/*!40000 ALTER TABLE `course_criteria` DISABLE KEYS */;
INSERT INTO `course_criteria` VALUES (1,1,'No require'),(2,1,'Newbie can learn'),(3,1,'Master learning Git'),(4,2,'No requirements for newbie'),(5,2,'Python OOP'),(6,2,'Python basic'),(7,2,'Python Enhance'),(8,5,'Js new'),(9,5,'New comer to Javascript ES6'),(10,5,'Rebase Javascript ES6'),(11,3,'Front end from SSR to CSR'),(12,3,'FE JS starter'),(13,3,'New comer with ReactJS'),(14,11,'Learn how to rapidly prototype ideas and turn them into presentable apps'),(15,11,'Become a professional Ruby on Rails developer'),(16,11,'Apply for jobs at software companies as Ruby on Rails developer'),(17,11,'Become a professional web application developer'),(18,11,'Design and build virtually any web application you can imagine'),(19,4,'Build amazing Vue.js Applications - all the Way from Small and Simple Ones up to Large Enterprise-level Ones'),(20,4,'Leverage Vue.js in both Multi- and Single-Page-Applications (MPAs and SPAs)'),(21,4,'Understand the Theory behind Vue.js and use it in Real Projects'),(22,4,'Learn the latest version of Vue (Vue 3), including the brand-new Composition API'),(23,5,'No coding experience is necessary to take this course! I take you from beginner to expert!'),(24,5,'Any computer and OS will work — Windows, macOS or Linux. We will set up your text editor the course.'),(25,5,'A basic understanding of HTML and CSS is a plus, but not a must! The course includes an HTML and CSS crash course.'),(26,6,'NO Angular 1 or Angular 2+ knowledge is required!'),(27,6,'Basic HTML and CSS knowledge helps, but isn\'t a must-have'),(28,6,'Prior TypeScript knowledge also helps but isn\'t necessary to benefit from this course'),(29,6,'Basic JavaScript knowledge is required'),(30,7,'You should have some programming knowledge, with Python or another language. Although we provide a complete Python Refresher course that covers everything you need to know, this is not an introductory Python course.'),(31,7,'You need a computer with either Linux, Mac, or Windows 10. Older Windows versions will work too but there can be some small issues that we can work around.'),(32,7,'Developing on a mobile device is possible by using a web editor. How to do this is detailed inside the course.'),(33,8,'A good understanding of JavaScript. Basic understanding of ES6 would be beneficial.'),(34,8,'Working knowledge of HTML & CSS.'),(35,8,'Be comfortable with very basic usage of the command line (CLI)'),(36,8,'Understand JavaScript in the Browser. e.g. Knowing what the global \'window\' variable is etc.'),(37,8,'Basic understanding of Node.js'),(38,8,'Basic knowledge of Git (Recommended, but not required)'),(39,9,'All Installation setup including Python basics is taken care as part of course'),(40,9,'Theoretical Material,Code dump are available for download'),(41,9,'Join in our Selenium Training community where 3 Million Students Learning Together which you will not see in any other Selenium courses on Udemy'),(42,9,'Though these are online Lectures.You will have Life Time instructor support.'),(43,10,'Absolutely NO understanding of Node or back-end development is required! I take you from beginner to advanced developer!'),(44,10,'Basic understanding of JavaScript is required (the course contains a section about asynchronous JavaScript with promises and async/await in case you need to get up to speed)'),(45,10,'Basic understanding of HTML is a plus (only for final part of the course), but NOT a must'),(46,10,'Any computer and OS will work — Windows, macOS or Linux');
/*!40000 ALTER TABLE `course_criteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite_course`
--

DROP TABLE IF EXISTS `favorite_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorite_course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `course_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `favorite_course_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `favorite_course_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite_course`
--

LOCK TABLES `favorite_course` WRITE;
/*!40000 ALTER TABLE `favorite_course` DISABLE KEYS */;
INSERT INTO `favorite_course` VALUES (1,1,1);
/*!40000 ALTER TABLE `favorite_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecture`
--

DROP TABLE IF EXISTS `lecture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecture` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `video_url` text COLLATE utf8mb4_unicode_ci,
  `order_index` int NOT NULL,
  `section_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `section_id` (`section_id`),
  CONSTRAINT `lecture_ibfk_1` FOREIGN KEY (`section_id`) REFERENCES `section` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=202 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecture`
--

LOCK TABLES `lecture` WRITE;
/*!40000 ALTER TABLE `lecture` DISABLE KEYS */;
INSERT INTO `lecture` VALUES (1,'Introduce to Python','Introduce to Python','VIDEO','https://spring-boot-elearning.s3.ap-southeast-1.amazonaws.com/Introduction%20to%20Python-%20Introduction.mp4',1,1),(2,'Set up Environment','Setup Environment','VIDEO','https://www.youtube.com/watch?v=GZbeL5AcTgw',2,1),(3,'Git','Git','VIDEO','https://www.youtube.com/watch?v=gtAMSRwPHvo',1,2),(4,'Git status','Git status','VIDEO','https://www.youtube.com/watch?v=d7p-KkrSIyo',1,3),(6,'Draw Heart with Turtle','Draw Heart with Turtle','VIDEO','https://www.youtube.com/watch?v=A8UHaRKw_kQ',3,1),(7,'Introduction 2','Introduction 2','VIDEO','https://www.youtube.com/watch?v=iHADSeaByp8',2,4),(8,'Introduction','Introduction','VIDEO','https://www.youtube.com/watch?v=J2tVkEpcaOY',1,4),(9,'Introduction','Introduction','VIDEO','https://www.youtube.com/watch?v=j942wKiXFu8',1,5),(10,'Creating a React Application','Creating a React Application','VIDEO','https://www.youtube.com/watch?v=kVeOpcw4GWY',1,6),(11,'Components & Templates','Components & Templates','VIDEO','https://www.youtube.com/watch?v=9D1x7-2FmTA',2,6),(12,'Dynamic Values in Templates','Dynamic Values in Templates','VIDEO','https://www.youtube.com/watch?v=pnhO8UaCgxg',3,6),(13,'Multiple Components','Multiple Components','VIDEO','https://www.youtube.com/watch?v=0sSYmRImgRY',4,6),(14,'Adding Styles','Adding Styles to React components','VIDEO','https://www.youtube.com/watch?v=NbTrGcz4DW8',5,6),(15,'Click Events','ReactJS Click Events - onClick','VIDEO','https://www.youtube.com/watch?v=0XSDAup85SA',6,6),(16,'Using State (useState hook)','ReactJS Using State (useState hook)','VIDEO','https://www.youtube.com/watch?v=4pO-HcG2igk',7,6),(17,'Intro to React Dev Tools','Intro to React Dev Tools','VIDEO','https://www.youtube.com/watch?v=rb1GWqCJid4',2,5),(18,'Render Outputting Lists','ReactJS Render Outputting Lists','VIDEO','https://www.youtube.com/watch?v=tHjxSVaj_wY',8,6),(19,'Props','ReactJS Props','VIDEO','https://www.youtube.com/watch?v=PHaECbrKgs0',9,6),(20,'Reusing Components','Reusing Components','VIDEO','https://www.youtube.com/watch?v=-YpnB-zlkPU',10,6),(21,'Functions as Props','Functions as Props','VIDEO','https://www.youtube.com/watch?v=CWEOYFzgOJs',11,6),(22,'useEffect Hook (the basics)','useEffect Hook (the basics)','VIDEO','https://www.youtube.com/watch?v=gv9ugDJ1ynU',12,6),(23,'useEffect Hook - useEffect Dependencies','useEffect Hook - useEffect Dependencies','VIDEO','https://www.youtube.com/watch?v=jQc_bTFZ5_I',13,6),(24,'Using JSON Server','Using JSON Server','VIDEO','https://www.youtube.com/watch?v=eao7ABGFUXs',14,6),(25,'Fetching Data with useEffect','Fetching Data with useEffect','VIDEO','https://www.youtube.com/watch?v=qdCHEUaFhBk',15,6),(26,'Conditional Loading Message','Conditional Loading Message','VIDEO','https://www.youtube.com/watch?v=qtheqr0jgIQ',16,6),(27,'Handling Fetch Errors','Handling Fetch Errors','VIDEO','https://www.youtube.com/watch?v=DTBta08fXGU',17,6),(28,'Development Environment Setting','Development Environment Setting','VIDEO','https://www.youtube.com/watch?v=sEGC-adSKXo',3,4),(29,'Basic JavaScript Syntax','Basic JavaScript Syntax','VIDEO','https://www.youtube.com/watch?v=KXxXr0RxGDE',1,7),(30,'JavaScript Variables','JavaScript Variables','VIDEO','https://www.youtube.com/watch?v=plOo5hNVQJU',2,7),(31,'JavaScript Data Types','JavaScript Data Types','VIDEO','https://www.youtube.com/watch?v=yjE_xXL26qA',3,7),(32,'JavaScript Type Conversion','JavaScript Type Conversion','VIDEO','https://www.youtube.com/watch?v=jfQyMPzPTjY',4,7),(33,'Expressions and Operators','Expressions and Operators','VIDEO','https://www.youtube.com/watch?v=nMQlXMHMz_Y',5,7),(34,'JavaScript Arrays','JavaScript Arrays','VIDEO','https://www.youtube.com/watch?v=S2JVtEwa-kU',6,7),(35,'Function Declaration','Function Declaration','VIDEO','https://www.youtube.com/watch?v=yPJCFWLd23o',7,7),(36,'JavaScript Function Expressions','JavaScript Function Expressions','VIDEO','https://www.youtube.com/watch?v=Wggcy2oKV3E',8,7),(37,'JavaScript Decision Statements','JavaScript Decision Statements','VIDEO','https://www.youtube.com/watch?v=Fk3tdDAWkCI',9,7),(38,'Vue JS 3 Tutorial for Beginners - Introduction','Vue JS 3 Tutorial for Beginners - Introduction','VIDEO','https://www.youtube.com/watch?v=YrxBCBibVo0',1,8),(39,'Vue.js Basics (part 1)','Vue.js Basics (part 1)','VIDEO','https://www.youtube.com/watch?v=F7PLPJqVotk',2,8),(40,'Vue.js Basics (part 2)','Vue.js Basics (part 2)','VIDEO','https://www.youtube.com/watch?v=CYPZBK8zUik',3,8),(41,'The Vue CLI & Bigger Projects (part 1)','The Vue CLI & Bigger Projects (part 1)','VIDEO','https://www.youtube.com/watch?v=GWRvrSqnFbM',1,9),(42,'The Vue CLI & Bigger Projects (part 2)','The Vue CLI & Bigger Projects (part 2)','VIDEO','https://www.youtube.com/watch?v=KM1U6DqZf8M',2,9),(43,'Build a Reaction Timer Game','Build a Reaction Timer Game','VIDEO','https://www.youtube.com/watch?v=bc6czIBLKTg',3,9),(47,'Vue JS 3 - Forms & Inputs','Vue JS 3- Forms & Inputs','VIDEO','https://www.youtube.com/watch?v=ixOcve5PX-Q',4,9),(48,'Vue JS 3 - The Vue Router','Vue JS 3 - The Vue Router','VIDEO','https://www.youtube.com/watch?v=juocv4AtrHo',5,9),(49,'Vue JS 3 - Fetching Data','Vue JS 3 - Fetching Data','VIDEO','https://www.youtube.com/watch?v=7iDGJolHFmU',6,9),(50,'The Composition API (part 1)','The Composition API (part 1)','VIDEO','https://www.youtube.com/watch?v=V-kxBWcPJfo',7,9),(51,'The Composition API (part 2)','The Composition API (part 2)','VIDEO','https://www.youtube.com/watch?v=0FwBjPeLqQ8',8,9),(52,'Vue JS 3 - Next Steps','Vue JS 3 - Next Steps','VIDEO','https://www.youtube.com/watch?v=Dj_2j-IKXuo',9,9),(53,'Angular 2 Tutorial  - Introduction','Angular 2 Tutorial  - Introduction','VIDEO','https://www.youtube.com/watch?v=DwTNR3EBSJQ',1,10),(54,'Angular 2 Tutorial  - Installing the Angular CLI','Angular 2 Tutorial  - Installing the Angular CLI','VIDEO','https://www.youtube.com/watch?v=5OLLPQq1GqQ',2,10),(55,'Angular 2 Tutorial  - Intro to TypeScript','Angular 2 Tutorial  - Intro to TypeScript','VIDEO','https://www.youtube.com/watch?v=Gn8FEdVtM18',3,10),(56,'Angular 2 Tutorial  - Core Files Overview','Angular 2 Tutorial  - Core Files Overview','VIDEO','https://www.youtube.com/watch?v=PXkXR26U04g',4,10),(57,'Angular 2 Tutorial  - Components','Angular 2 Tutorial  - Components','VIDEO','https://www.youtube.com/watch?v=YKi2A2PFTnc',1,11),(58,'Angular 2 Tutorial  - Templates & CSS','Angular 2 Tutorial  - Templates & CSS','VIDEO','https://www.youtube.com/watch?v=A90UT3o5bMk',2,11),(59,'Angular 2 Tutorial  - Creating a Component','Angular 2 Tutorial  - Creating a Component','VIDEO','https://www.youtube.com/watch?v=GMkF1iUY3n8',3,11),(60,'Angular 2 Tutorial  - Nesting Components','Angular 2 Tutorial  - Nesting Components','VIDEO','https://www.youtube.com/watch?v=uQZDNTeR44E',4,11),(61,'Angular 2 Tutorial  - Ng-Content Directive','Angular 2 Tutorial  - Ng-Content Directive','VIDEO','https://www.youtube.com/watch?v=7RQDIChgaZk',5,11),(62,'Angular 2 Tutorial - Data Binding','Angular 2 Tutorial 0 - Data Binding','VIDEO','https://www.youtube.com/watch?v=VF47uWWql04',6,11),(63,'Angular 2 Tutorial - Property Binding','Angular 2 Tutorial 1 - Property Binding','VIDEO','https://www.youtube.com/watch?v=VkxyuKuJZrM',7,11),(64,'Angular 2 Tutorial - Event Binding','Angular 2 Tutorial 2 - Event Binding','VIDEO','https://www.youtube.com/watch?v=uh5ZKGEEAkY',8,11),(65,'Angular 2 Tutorial - 2 Way Data Binding','Angular 2 Tutorial 3 - 2 Way Data Binding','VIDEO','https://www.youtube.com/watch?v=HXjVelFtpuQ',9,11),(66,'Angular 2 Tutorial - Custom Property Binding (& @Input)','Angular 2 Tutorial 4 - Custom Property Binding (& @Input)','VIDEO','https://www.youtube.com/watch?v=qenFRHDxU6E',10,11),(67,'Angular 2 Tutorial - Custom Event Binding (& @Output)','Angular 2 Tutorial 5 - Custom Event Binding (& @Output)','VIDEO','https://www.youtube.com/watch?v=xFYzfHGCJBw',11,11),(68,'Angular 2 Tutorial - Routing','Angular 2 Tutorial 6 - Routing','VIDEO','https://www.youtube.com/watch?v=patjvNM9Qbc',12,11),(69,'Angular 2 Tutorial - Adding Links','Angular 2 Tutorial 7 - Adding Links','VIDEO','https://www.youtube.com/watch?v=NPCdCknF6TY',13,11),(70,'Angular 2 Tutorial - Route Params','Angular 2 Tutorial 8 - Route Params','VIDEO','https://www.youtube.com/watch?v=8KPPRzRLvjc',14,11),(71,'Angular 2 Tutorial - Directives Introduction','Angular 2 Tutorial 9 - Directives Introduction','VIDEO','https://www.youtube.com/watch?v=Wwix0JSQKF8',15,11),(72,'Angular 2 Tutorial - ngFor','Angular 2 Tutorial 0 - ngFor','VIDEO','https://www.youtube.com/watch?v=GqTJy9VALhI',16,11),(73,'Angular 2 Tutorial - Pipes','Angular 2 Tutorial 1 - Pipes','VIDEO','https://www.youtube.com/watch?v=iCmV2jBdDS8',17,11),(74,'Angular 2 Tutorial - Custom Filter Pipe','Angular 2 Tutorial 2 - Custom Filter Pipe','VIDEO','https://www.youtube.com/watch?v=sVTNaYBVP88',18,11),(75,'Angular 2 Tutorial - Services','Angular 2 Tutorial 3 - Services','VIDEO','https://www.youtube.com/watch?v=74NARvL2BI0',19,11),(76,'Angular 2 Tutorial - HTTP Service','Angular 2 Tutorial 4 - HTTP Service','VIDEO','https://www.youtube.com/watch?v=IOp9OmNdHy4',20,11),(77,'Angular 2 Tutorial - Connecting to Firebase','Angular 2 Tutorial 5 - Connecting to Firebase','VIDEO','https://www.youtube.com/watch?v=H9HZ41vM7mk',21,11),(78,'Angular 2 Tutorial - Firebase API','Angular 2 Tutorial 6 - Firebase API','VIDEO','https://www.youtube.com/watch?v=Fb9o2uwRAk0',22,11),(79,'Angular 2 Tutorial - Posting Data to Firebase','Angular 2 Tutorial 7 - Posting Data to Firebase','VIDEO','https://www.youtube.com/watch?v=YEizFZ6eaas',23,11),(80,'Learn Tkinter With Projects  Python Tkinter GUI Tutorial #0','Learn Tkinter With Projects  Python Tkinter GUI Tutorial #0','VIDEO','https://www.youtube.com/watch?v=-Q4lm8eYulw',1,12),(81,'Notepad & Calculator In Tkinter + Why Tkinter?  Python Tkinter GUI Tutorial In Hindi','Notepad & Calculator In Tkinter + Why Tkinter?  Python Tkinter GUI Tutorial In Hindi','VIDEO','https://www.youtube.com/watch?v=unYqUyeGWQY',2,12),(82,'Our First Tkinter GUI  Python Tkinter GUI Tutorial In Hindi','Our First Tkinter GUI  Python Tkinter GUI Tutorial In Hindi','VIDEO','https://www.youtube.com/watch?v=K7XV9-GjzbY',3,12),(83,'Tkinter Widgets & Attributes  Python Tkinter GUI Tutorial In Hindi','Tkinter Widgets & Attributes  Python Tkinter GUI Tutorial In Hindi','VIDEO','https://www.youtube.com/watch?v=tHI_LtnZNo4',1,13),(84,'Label, Geometry, Maxsize & Minsize  Python Tkinter GUI Tutorial In Hindi','Label, Geometry, Maxsize & Minsize  Python Tkinter GUI Tutorial In Hindi','VIDEO','https://www.youtube.com/watch?v=dizKSszF74A',2,13),(85,'Displaying Images Using Label  Python Tkinter GUI Tutorial In Hindi','Displaying Images Using Label  Python Tkinter GUI Tutorial In Hindi','VIDEO','https://www.youtube.com/watch?v=end4IWH0ihY',3,13),(86,'Attributes Of Label & Pack  Python Tkinter GUI Tutorial In Hindi','Attributes Of Label & Pack  Python Tkinter GUI Tutorial In Hindi','VIDEO','https://www.youtube.com/watch?v=0d-aZ5moXzA',4,13),(87,'Exercise 1: Creating Newspaper GUI  Python Tkinter GUI Tutorial In Hindi','Exercise 1: Creating Newspaper GUI  Python Tkinter GUI Tutorial In Hindi','VIDEO','https://www.youtube.com/watch?v=WYLokw4mZQw',5,13),(88,'Frame In Tkinter  Python Tkinter GUI Tutorial In Hindi','Frame In Tkinter  Python Tkinter GUI Tutorial In Hindi','VIDEO','https://www.youtube.com/watch?v=dgFjh5WbQDU',6,13),(89,'Packing Buttons In Tkinter  Python Tkinter GUI Tutorial In Hindi','Packing Buttons In Tkinter  Python Tkinter GUI Tutorial In Hindi','VIDEO','https://www.youtube.com/watch?v=hM9f376cxpw',7,13),(90,'Entry Widget & Grid Layout In Tkinter  Python Tkinter GUI Tutorial 0','Entry Widget & Grid Layout In Tkinter  Python Tkinter GUI Tutorial 0','VIDEO','https://www.youtube.com/watch?v=Rg96iAgQlfg',8,13),(91,'Travel Form Using Checkbuttons & Entry Widget  Python Tkinter GUI Tutorial 1','Travel Form Using Checkbuttons & Entry Widget  Python Tkinter GUI Tutorial 1','VIDEO','https://www.youtube.com/watch?v=H0hOBmc3-tw',9,13),(92,'Accepting User Input In Tkinter Form  Python Tkinter GUI Tutorial 2','Accepting User Input In Tkinter Form  Python Tkinter GUI Tutorial 2','VIDEO','https://www.youtube.com/watch?v=dvLMe-L5e-g',10,13),(93,'Canvas Widget In Python Tkinter  Python Tkinter GUI Tutorial 3','Canvas Widget In Python Tkinter  Python Tkinter GUI Tutorial 3','VIDEO','https://www.youtube.com/watch?v=NPwT6jI5fvI',11,13),(94,'Handling Events In Tkinter GUI  Python Tkinter GUI Tutorial 4','Handling Events In Tkinter GUI  Python Tkinter GUI Tutorial 4','VIDEO','https://www.youtube.com/watch?v=ZWYhXncauUA',12,13),(95,'Python GUI Exercise 1: Solution  Python Tkinter GUI Tutorial 5','Python GUI Exercise 1: Solution  Python Tkinter GUI Tutorial 5','VIDEO','https://www.youtube.com/watch?v=Le1Sp71JI70',13,13),(96,'Python GUI Exercise 2: Window Resizer GUI  Python Tkinter GUI Tutorial 6','Python GUI Exercise 2: Window Resizer GUI  Python Tkinter GUI Tutorial 6','VIDEO','https://www.youtube.com/watch?v=8Qy_809RDM0',14,13),(97,'Menus & Submenus In Tkinter Python  Python Tkinter GUI Tutorial 7','Menus & Submenus In Tkinter Python  Python Tkinter GUI Tutorial 7','VIDEO','https://www.youtube.com/watch?v=mea-xEhMf3Y',15,13),(98,'Message Box In Tkinter Python  Python Tkinter GUI Tutorial 8','Message Box In Tkinter Python  Python Tkinter GUI Tutorial 8','VIDEO','https://www.youtube.com/watch?v=yiAlV5Huo84',16,13),(99,'Sliders In Tkinter Using Scale()  Python Tkinter GUI Tutorial 9','Sliders In Tkinter Using Scale()  Python Tkinter GUI Tutorial 9','VIDEO','https://www.youtube.com/watch?v=luAhG37X8pE',17,13),(100,'Creating RadioButtons In Tkinter  Python Tkinter GUI Tutorial 0','Creating RadioButtons In Tkinter  Python Tkinter GUI Tutorial 0','VIDEO','https://www.youtube.com/watch?v=BIeO2JTxOuw',18,13),(101,'ListBox In Tkinter Python Tkinter GUI Tutorial 1','ListBox In Tkinter Python Tkinter GUI Tutorial 1','VIDEO','https://www.youtube.com/watch?v=eLSJKrlS95I',19,13),(102,'ScrollBar In Tkinter GUI  Python Tkinter GUI Tutorial 2','ScrollBar In Tkinter GUI  Python Tkinter GUI Tutorial 2','VIDEO','https://www.youtube.com/watch?v=BULr4y47V7c',20,13),(103,'Tkinter GUI Exercise 2 Solution + Shoutouts  Python Tkinter GUI Tutorial 3','Tkinter GUI Exercise 2 Solution + Shoutouts  Python Tkinter GUI Tutorial 3','VIDEO','https://www.youtube.com/watch?v=nF61b4wAmxE',21,13),(104,'Status Bar In Tkinter  Python Tkinter GUI Tutorial 4','Status Bar In Tkinter  Python Tkinter GUI Tutorial 4','VIDEO','https://www.youtube.com/watch?v=ZhmKAoKdiQQ',22,13),(105,'Using Classes And Objects To Create GUIs  Python Tkinter GUI Tutorial 5','Using Classes And Objects To Create GUIs  Python Tkinter GUI Tutorial 5','VIDEO','https://www.youtube.com/watch?v=kY22BriodaU',23,13),(106,'More Tkinter Tips, Tricks & Functions  Python Tkinter GUI Tutorial 6','More Tkinter Tips, Tricks & Functions  Python Tkinter GUI Tutorial 6','VIDEO','https://www.youtube.com/watch?v=EA3g9AZCIdU',24,13),(107,'Creating A Calculator Using Tkinter   Python Tkinter GUI Tutorial 7','Creating A Calculator Using Tkinter   Python Tkinter GUI Tutorial 7','VIDEO','https://www.youtube.com/watch?v=fbxsYcSccJE',1,14),(108,'Tkinter GUI Text Editor Announcement  Python Tkinter GUI Tutorial 8','Tkinter GUI Text Editor Announcement  Python Tkinter GUI Tutorial 8','VIDEO','https://www.youtube.com/watch?v=QaLFoPq2sjA',2,14),(109,'Creating a GUI Notepad In Tkinter  Python Tkinter GUI Tutorial 9','Creating a GUI Notepad In Tkinter  Python Tkinter GUI Tutorial 9','VIDEO','https://www.youtube.com/watch?v=UMTaOFNk73Y',3,14),(110,'Tkinter Tutorials Conclusion + Resources  Python Tkinter GUI Tutorial 0','Tkinter Tutorials Conclusion + Resources  Python Tkinter GUI Tutorial 0','VIDEO','https://www.youtube.com/watch?v=K_IbYqZQHko',4,14),(111,'Electron js tutorial for Beginners - Introduction','Electron js tutorial for Beginners - Introduction','VIDEO','https://www.youtube.com/watch?v=OLiY4AkY3Yk',1,15),(112,'Electron js tutorial for Beginners - Installation and setup with  window','Electron js tutorial for Beginners  installation and setup with  window','VIDEO','https://www.youtube.com/watch?v=zjfdrxexNPs',2,15),(113,'Electron js tutorial for Beginners - Setup and Hello world Program','Electron js tutorial for Beginners  Setup and Hello world Program','VIDEO','https://www.youtube.com/watch?v=fR8ud349sn8',3,15),(114,'Electron js tutorial for Beginners - Hello world Program','Electron js tutorial for Beginners  Hello world Program','VIDEO','https://www.youtube.com/watch?v=2EKmHi0pKl4',4,15),(115,'Electron js tutorial for Beginners - Dev Tool','Electron js tutorial for Beginners  Dev Tool','VIDEO','https://www.youtube.com/watch?v=KpOnx1dbuvA',5,15),(116,'Electron js tutorial for Beginners - Main and Render Process','Electron js tutorial for Beginners   Main and Render Process','VIDEO','https://www.youtube.com/watch?v=Z2IzeYiN310',1,16),(117,'Electron js tutorial for Beginners - Browser Window Properties','Electron js tutorial for Beginners  Browser Window Properties','VIDEO','https://www.youtube.com/watch?v=rFJ44zdbpvo',2,16),(118,'Electron js tutorial for Beginners - Child Window','Electron js tutorial for Beginners  Child Window','VIDEO','https://www.youtube.com/watch?v=97dNTfmulsc',3,16),(119,'Electron js tutorial for Beginners - IPC Render and Main render  share data','Electron js tutorial for Beginners  IPC Render and Main render  share data','VIDEO','https://www.youtube.com/watch?v=Ytu5yXHhiVc',4,16),(120,'Electron js tutorial for Beginners - Make build','Electron js tutorial for Beginners  make build','VIDEO','https://www.youtube.com/watch?v=MzuQ3_D3MCw',5,16),(121,'Electron js tutorial for Beginners - Reload Main Process','Electron js tutorial for Beginners - Reload Main Process','VIDEO','https://www.youtube.com/watch?v=qP5ueAfoyCA',6,16),(122,'Electron js tutorial for Beginners - Important  App life cycle event','Electron js tutorial for Beginners - Important  App life cycle event','VIDEO','https://www.youtube.com/watch?v=ECq-mMdKepc',7,16),(123,'Electron js tutorial for Beginners - frame less application','Electron js tutorial for Beginners - frame less application','VIDEO','https://www.youtube.com/watch?v=eK4h7qZ57Uw',8,16),(124,'Electron js tutorial for Beginners - Window State','Electron js tutorial for Beginners - Window State','VIDEO','https://www.youtube.com/watch?v=hnm23Ns2kqk',9,16),(125,'Electron js tutorial for Beginners - Web content','Electron js tutorial for Beginners - Web content','VIDEO','https://www.youtube.com/watch?v=idP5JLdoKVI',10,16),(126,'Electron js tutorial for Beginners - Global Shortcut','Electron js tutorial for Beginners - Global Shortcut','VIDEO','https://www.youtube.com/watch?v=t59F_cCOfI4',11,16),(127,'Electron js tutorial for Beginners - Dialog box','Electron js tutorial for Beginners - Dialog box','VIDEO','https://www.youtube.com/watch?v=tLvvE--Fsp4',12,16),(128,'Electron js tutorial for Beginners - Global Shortcut','Electron js tutorial for Beginners - Global Shortcut','VIDEO','https://www.youtube.com/watch?v=XUf5p50qfAM',13,16),(129,'Electron js tutorial for Beginners - Context Menu','Electron js tutorial for Beginners - Context Menu','VIDEO','https://www.youtube.com/watch?v=Ny44dZByPPA',14,16),(130,'Electron js tutorial for Beginners - Tray','Electron js tutorial for Beginners - Tray','VIDEO','https://www.youtube.com/watch?v=f8tcPdBaF2Y',15,16),(131,'Electron js tutorial for Beginners - Webframe','Electron js tutorial for Beginners - Webframe','VIDEO','https://www.youtube.com/watch?v=Odn9xCgNPKk',16,16),(132,'Electron js tutorial for Beginners - Process  Shared API','Electron js tutorial for Beginners - Process  Shared API','VIDEO','https://www.youtube.com/watch?v=BO-2zBbcqqI',17,16),(133,'Python Selenium Tutorial - Web Scraping, Bots & Testing','Python Selenium Tutorial - Web Scraping, Bots & Testing','VIDEO','https://www.youtube.com/watch?v=Xjv1sY630Uc',1,17),(134,'Python Selenium Tutorial - Locating Elements From HTML','Python Selenium Tutorial - Locating Elements From HTML','VIDEO','https://www.youtube.com/watch?v=b5jt2bhSeXs',2,17),(135,'Python Selenium Tutorial - Page Navigating and Clicking Elements','Python Selenium Tutorial - Page Navigating and Clicking Elements','VIDEO','https://www.youtube.com/watch?v=U6gbGk5WPws',3,17),(136,'Python Selenium Tutorial - ActionChains & Automating Cookie Clicker!','Python Selenium Tutorial - ActionChains & Automating Cookie Clicker!','VIDEO','https://www.youtube.com/watch?v=OISEEL5eBqg',4,17),(137,'Python Selenium Tutorial - UnitTest Framework (Part 1)','Python Selenium Tutorial - UnitTest Framework (Part 1)','VIDEO','https://www.youtube.com/watch?v=9_5Wqgni_Xw',5,17),(138,'Python Selenium Tutorial - UnitTest Framework (Part 2)','Python Selenium Tutorial - UnitTest Framework (Part 2)','VIDEO','https://www.youtube.com/watch?v=O_sIPPA4euw',6,17),(139,'Node JS Installation NodeJS #nodejs #expressjs','Node JS Installation NodeJS #nodejs #expressjs','VIDEO','https://www.youtube.com/watch?v=1CgJPjGzFII',1,18),(140,'Web Server Concepts API, Ports, Request, Response Node JS #nodejs #expressjs','Web Server Concepts API, Ports, Request, Response Node JS #nodejs #expressjs','VIDEO','https://www.youtube.com/watch?v=sfMNI0yLZII',1,19),(141,'First Node.js / ExpressJS Web Server What is Express JS ? Node JS #nodejs','First Node.js / ExpressJS Web Server What is Express JS ? Node JS #nodejs','VIDEO','https://www.youtube.com/watch?v=6WG6w3ipc9U',2,19),(142,'Serving Static from Files Express JS express.static Node JS #nodejs #expressjs','Serving Static from Files Express JS express.static Node JS #nodejs #expressjs','VIDEO','https://www.youtube.com/watch?v=Xl3msGMwPp0',3,19),(143,'API endpoint in nodejs/express request, response Node JS #nodejs #expressjs','API endpoint in nodejs/express request, response Node JS #nodejs #expressjs','VIDEO','https://www.youtube.com/watch?v=TVeH7YkH9LU',4,19),(144,'Environment Variables in Node JS process.env nodejs Node JS #nodejs #expressjs','Environment Variables in Node JS process.env nodejs Node JS #nodejs #expressjs','VIDEO','https://www.youtube.com/watch?v=Exbe46JuTe8',5,19),(145,'Req.query query param in rest api NodeJS #nodejs #expressjs','Req.query query param in rest api NodeJS #nodejs #expressjs','VIDEO','https://www.youtube.com/watch?v=vnRsFtwWu8M',1,20),(146,'request params in node Params in express JS Node JS #nodejs #expressjs','request params in node Params in express JS Node JS #nodejs #expressjs','VIDEO','https://www.youtube.com/watch?v=4lSykV_rhBo',2,20),(147,'Req.body Node JS Express req.body undefined express Node JS #nodejs #expressjs','Req.body Node JS Express req.body undefined express Node JS #nodejs #expressjs','VIDEO','https://www.youtube.com/watch?v=bQApV_RddO4',3,20),(148,'What is Middleware in Node JS Custom middleware Node JS 0 #nodejs #expressjs','What is Middleware in Node JS Custom middleware Node JS 0 #nodejs #expressjs','VIDEO','https://www.youtube.com/watch?v=qkMJL0FwiyE',4,20),(149,'Restart Node Server automatically Nodemon in Node Node JS 1 #nodejs #expressjs','Restart Node Server automatically Nodemon in Node Node JS 1 #nodejs #expressjs','VIDEO','https://www.youtube.com/watch?v=aXuLsfq4cpE',5,20),(150,'What is Cookie ? Session management in Node JS express Node JS 2 #nodejs #expressjs','What is Cookie ? Session management in Node JS express Node JS 2 #nodejs #expressjs','VIDEO','https://www.youtube.com/watch?v=v_Ewns3n_Ow',6,20),(151,'Passport JS in Node JS Passport JS Authentication Node JS 3 #nodejs #expressjs','Passport JS in Node JS Passport JS Authentication Node JS 3 #nodejs #expressjs','VIDEO','https://www.youtube.com/watch?v=U-S3rgG8hBE',7,20),(152,'Hashing Password in Node.js using Bcrypt library Node JS 4 #nodejs #expressjs','Hashing Password in Node.js using Bcrypt library Node JS 4 #nodejs #expressjs','VIDEO','https://www.youtube.com/watch?v=5e4LCRoCVA4',8,20),(153,'Heroku Node js App deploy Heroku CLI Node JS 5 #reactjs #expressjs #heroku','Heroku Node js App deploy Heroku CLI Node JS 5 #reactjs #expressjs #heroku','VIDEO','https://www.youtube.com/watch?v=GeXA_MzMR6I',9,20),(154,'DotEnv package Creating environment variables Node Js 16 #nodejs','DotEnv package Creating environment variables Node Js 16 #nodejs','VIDEO','https://www.youtube.com/watch?v=h6Gks3DjFKg',10,20),(155,'Node js Event Loop Understanding Asynchronous Javascript Callbacks in Javascript','Node js Event Loop Understanding Asynchronous Javascript Callbacks in Javascript','VIDEO','https://www.youtube.com/watch?v=W-HQC_YUGBY',11,20),(156,'File Upload in NodeJS using Multer Fetch API for file upload Sending form data with fetch','File Upload in NodeJS using Multer Fetch API for file upload Sending form data with fetch','VIDEO','https://www.youtube.com/watch?v=qfN6c5FEAQQ',12,20),(157,'Part 1: Installing Ruby on Rails','Part 1: Installing Ruby on Rails','VIDEO','https://www.youtube.com/watch?v=wkNR1hG4yOk',1,21),(158,'Part 2: How to create a new Rails app','Part 2: How to create a new Rails app','VIDEO','https://www.youtube.com/watch?v=4PLg-Oe3MfA',1,22),(159,'Part 3: How HTTP Requests work in the Browser','Part 3: How HTTP Requests work in the Browser','VIDEO','https://www.youtube.com/watch?v=Bx2mFSsrucM',2,22),(160,'Part 4: The MVC (Model, View, Controller) Pattern?','Part 4: The MVC (Model, View, Controller) Pattern?','VIDEO','https://www.youtube.com/watch?v=lKUR4mu1M-U',3,22),(161,'Part 5: Routes and Route Types','Part 5: Routes and Route Types','VIDEO','https://www.youtube.com/watch?v=lwL_KWtflN0',4,22),(162,'Part 6: The Root Route','Part 6: The Root Route','VIDEO','https://www.youtube.com/watch?v=nM2fTxKJ4O8',5,22),(163,'Part 7: Adding Bootstrap CSS & Javascript','Part 7: Adding Bootstrap CSS & Javascript','VIDEO','https://www.youtube.com/watch?v=EzCl-6etSGI',6,22),(164,'Part 8: Using Partials for the Navbar','Part 8: Using Partials for the Navbar','VIDEO','https://www.youtube.com/watch?v=6wS9-6BFBbs',7,22),(165,'Part 9: URL Helpers and link_to','Part 9: URL Helpers and link_to','VIDEO','https://www.youtube.com/watch?v=Hj_h2v36e1M',8,22),(166,'Part 10: Setting up a Git repository and Flash messages','Part 10: Setting up a Git repository and Flash messages','VIDEO','https://www.youtube.com/watch?v=5xTkSIuizRs',9,22),(167,'Part 11: Creating the User model','Part 11: Creating the User model','VIDEO','https://www.youtube.com/watch?v=cuWoZw1D3oY',1,23),(168,'Part 12: Validations','Part 12: Validations','VIDEO','https://www.youtube.com/watch?v=l-Jv5vMjB70',2,23),(169,'Part 13: Creating a Sign Up Form','Part 13: Creating a Sign Up Form','VIDEO','https://www.youtube.com/watch?v=A5-BLCuAyBY',3,23),(170,'Part 14: Handling Sign Up Errors','Part 14: Handling Sign Up Errors','VIDEO','https://www.youtube.com/watch?v=5sT_3Trmtw0',4,23),(171,'Part 15: Login with Session Cookies','Part 15: Login with Session Cookies','VIDEO','https://www.youtube.com/watch?v=IzbQAj_tcfI',5,23),(172,'Part 16: Logging Out Users','Part 16: Logging Out Users','VIDEO','https://www.youtube.com/watch?v=fCunJskZkYI',6,23),(173,'Part 17: Login Form','Part 17: Login Form','VIDEO','https://www.youtube.com/watch?v=UC5LjuFDQM4',7,23),(174,'Part 18: Accessing the Current User','Part 18: Accessing the Current User','VIDEO','https://www.youtube.com/watch?v=hlpKyOb5yLc',8,23),(175,'Part 19: Edit Password','Part 19: Edit Password','VIDEO','https://www.youtube.com/watch?v=YWdwts1cgjI',9,23),(176,'Part 20: Forgot Your Password','Part 20: Forgot Your Password','VIDEO','https://www.youtube.com/watch?v=5azbhq4z3kw',10,23),(177,'Part 21: Reset Password Token Mailer','Part 21: Reset Password Token Mailer','VIDEO','https://www.youtube.com/watch?v=JMXGExhr0C4',11,23),(178,'Part 22: Password Reset Update','Part 22: Password Reset Update','VIDEO','https://www.youtube.com/watch?v=kTB5z4NcrhM',12,23),(179,'Part 23: Rails Credentials','Part 23: Rails Credentials','VIDEO','https://www.youtube.com/watch?v=_SnwlA0LvYQ',13,23),(180,'[OLD] Rails for Beginners Part 24: OmniAuth URLs','[OLD] Rails for Beginners Part 24: OmniAuth URLs','VIDEO','https://www.youtube.com/watch?v=-x9wCR3Fv3k',14,23),(181,'Part 24: OmniAuth URLs [Revised for Omniauth 2.0]','Part 24: OmniAuth URLs [Revised for Omniauth 2.0]','VIDEO','https://www.youtube.com/watch?v=FbUWc8Vnu8Y',15,23),(182,'Part 25: Twitter Account Model','Part 25: Twitter Account Model','VIDEO','https://www.youtube.com/watch?v=o48-i4m6W78',16,23),(183,'Part 26: Table Plus','Part 26: Table Plus','VIDEO','https://www.youtube.com/watch?v=0jhSV6BQJXg',17,23),(184,'Part 27: Twitter Accounts Page','Part 27: Twitter Accounts Page','VIDEO','https://www.youtube.com/watch?v=IUFRC9RXs_Y',18,23),(185,'[OLD] Rails for Beginners Part 27: Twitter Accounts Page','[OLD] Rails for Beginners Part 27: Twitter Accounts Page','VIDEO','https://www.youtube.com/watch?v=06jOyisd_bA',19,23),(186,'Part 28: Setting Records with Before Actions','Part 28: Setting Records with Before Actions','VIDEO','https://www.youtube.com/watch?v=1cGrocFSGIE',20,23),(187,'Part 29: Tweets Index & New Actions','Part 29: Tweets Index & New Actions','VIDEO','https://www.youtube.com/watch?v=dYx0mUDK1Xc',21,23),(188,'Part 30: Tweet Validations','Part 30: Tweet Validations','VIDEO','https://www.youtube.com/watch?v=B7Rq4538W1o',22,23),(189,'Part 31: Tweet Partial','Part 31: Tweet Partial','VIDEO','https://www.youtube.com/watch?v=jxsf6-bWJcY',23,23),(190,'Part 32: Edit and Destroy Tweets','Part 32: Edit and Destroy Tweets','VIDEO','https://www.youtube.com/watch?v=IG-lEKwv_fE',24,23),(191,'Part 33: Twitter API','Part 33: Twitter API','VIDEO','https://www.youtube.com/watch?v=tvDq_JJenjs',25,23),(192,'Part 34: Background Job for Posting Tweets','Part 34: Background Job for Posting Tweets','VIDEO','https://www.youtube.com/watch?v=vvNJlgiQtGQ',26,23),(193,'Part 35: Editing Tweets','Part 35: Editing Tweets','VIDEO','https://www.youtube.com/watch?v=4D3Ub3_e3EA',27,23),(194,'Part 36: Background Jobs with Sidekiq','Part 36: Background Jobs with Sidekiq','VIDEO','https://www.youtube.com/watch?v=aaGSh38nzq8',28,23),(195,'Part 37: Creating a GitHub Repo to store our code','Part 37: Creating a GitHub Repo to store our code','VIDEO','https://www.youtube.com/watch?v=YJM5pl_Xlxs',29,23),(196,'Part 38: Deploying to Heroku','Part 38: Deploying to Heroku','VIDEO','https://www.youtube.com/watch?v=bQ2wXZf9irA',30,23),(197,'Part 39: Dependent Destroy Model Associations','Part 39: Dependent Destroy Model Associations','VIDEO','https://www.youtube.com/watch?v=TZkRZsLsjFM',31,23),(198,'[OLD] Rails for Beginners Part 40: OmniAuth CSRF Protection','[OLD] Rails for Beginners Part 40: OmniAuth CSRF Protection','VIDEO','https://www.youtube.com/watch?v=NDxvUeseqXk',32,23),(199,'Part 41: Next Steps','Part 41: Next Steps','VIDEO','https://www.youtube.com/watch?v=ZxuUAUQGdN8',33,23),(200,'How to build a Multithreaded HTTP Server in Ruby from Scratch  Preview','How to build a Multithreaded HTTP Server in Ruby from Scratch  Preview','VIDEO','https://www.youtube.com/watch?v=6Zpq7R51Rfs',34,23),(201,'HTTP Server from Scratch: Rack & Rails Support  Preview','HTTP Server from Scratch: Rack & Rails Support  Preview','VIDEO','https://www.youtube.com/watch?v=XmpP5IQy-gI',35,23);
/*!40000 ALTER TABLE `lecture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecture_comment`
--

DROP TABLE IF EXISTS `lecture_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecture_comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `lecture_id` int NOT NULL,
  `user_id` int NOT NULL,
  `content` text COLLATE utf8mb4_unicode_ci,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `lecture_id` (`lecture_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `lecture_comment_ibfk_1` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`id`),
  CONSTRAINT `lecture_comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecture_comment`
--

LOCK TABLES `lecture_comment` WRITE;
/*!40000 ALTER TABLE `lecture_comment` DISABLE KEYS */;
INSERT INTO `lecture_comment` VALUES (1,1,1,'It\'s great','2023-10-05 16:49:21'),(2,2,1,'It\'s great','2023-10-06 16:49:21'),(3,3,1,'It\'s perfect to understand what you said','2023-10-05 16:49:21'),(4,4,1,'It\'s great','2023-10-05 16:49:21'),(5,13,3,'It\'s great','2023-10-06 16:49:21'),(6,6,1,'It\'s perfect to understand what you said','2023-10-05 16:49:21'),(7,7,1,'It\'s perfect to understand what you said','2023-10-05 16:49:21'),(8,8,1,'It\'s great','2023-10-06 16:49:21'),(9,9,1,'It\'s great','2023-10-05 16:49:21'),(10,10,1,'It\'s great','2023-10-06 16:49:21'),(11,11,1,'It\'s perfect to understand what you said','2023-10-05 16:49:21'),(12,12,1,'It\'s great','2023-10-05 16:49:21'),(13,13,1,'It\'s great','2023-10-05 16:49:21'),(14,10,2,'It\'s perfect to understand what you said','2023-10-06 16:49:21'),(15,11,2,'It\'s perfect to understand what you said','2023-10-05 16:49:21'),(16,12,2,'It\'s great','2023-10-06 16:49:21'),(17,13,2,'It\'s great','2023-10-05 16:49:21');
/*!40000 ALTER TABLE `lecture_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecturer_registration`
--

DROP TABLE IF EXISTS `lecturer_registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecturer_registration` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image_url` text COLLATE utf8mb4_unicode_ci,
  `user_id` int NOT NULL,
  `registration_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `lecturer_registration_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecturer_registration`
--

LOCK TABLES `lecturer_registration` WRITE;
/*!40000 ALTER TABLE `lecturer_registration` DISABLE KEYS */;
INSERT INTO `lecturer_registration` VALUES (1,'https://res.cloudinary.com/dexvnphga/image/upload/v1697375659/DoAn/w3rcpsaaiwh4drmc0oel.png',5,'2023-10-15 13:14:18');
/*!40000 ALTER TABLE `lecturer_registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER'),(3,'ROLE_LECTURER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `section` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NOT NULL,
  `section_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_index` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `section_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
INSERT INTO `section` VALUES (1,2,'Get started',1),(2,1,'What is Git/Github ?',1),(3,1,'Get Started',2),(4,5,'Introduction',1),(5,3,'Introduction',1),(6,3,'Getting Started',2),(7,5,'Getting Started',2),(8,4,'Introduction',1),(9,4,'Getting Started',2),(10,6,'Introduction',1),(11,6,'Getting Started',2),(12,7,'Preview Result',1),(13,7,'Getting started',2),(14,7,'Practice by building apps',3),(15,8,'Introduction & Installation',1),(16,8,'Get started',2),(17,9,'Getting started',1),(18,10,'Introduction',1),(19,10,'Setting up',2),(20,10,'Practice',3),(21,11,'Introduction & Setup',1),(22,11,'The Ruby Programming Language',2),(23,11,'Practicing',3);
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `course_id` int NOT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,1,2,0.00,'2023-10-16 07:31:01'),(2,1,1,10000.00,'2023-10-16 07:31:01');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `first_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `last_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','$2a$10$8ut36oMXUlCHBy4Mpx763eOkg.S7KepZ4oDklcwfGNKWvU5K67BLW','Hùn','ĐX Hoàng ','https://res.cloudinary.com/dexvnphga/image/upload/v1697019049/DoAn/nz9a7vdxefn7a6uf7v27.png','2051052059hung@ou.edu.vn','2023-09-25 14:24:02'),(2,'hung110785279923642180129','$2a$10$5gQJC.skvQelgMDuKAkysO663qKO0c.Cm7/.RS.nA8oMKt3KnStLS','Hoang','Hung','https://lh3.googleusercontent.com/a/ACg8ocJ_OSHGKfMV7cM9FcLAZdBFD8yk98bt3RBTt7Vh1Q3K=s96-c','tdph1168@gmail.com','2023-09-26 13:21:30'),(3,'username','$2a$10$YnbMwn5GzsZ0Q0zKFvoKRuwgArCbDW3nlcc.XTpYTa.jJOGYp3VXa','User','Name','https://res.cloudinary.com/dexvnphga/image/upload/v1697019049/DoAn/nz9a7vdxefn7a6uf7v27.png','username@gmail.com','2022-08-26 15:26:10'),(4,'username1','$2a$10$YnbMwn5GzsZ0Q0zKFvoKRuwgArCbDW3nlcc.XTpYTa.jJOGYp3VXa','User','Name','https://res.cloudinary.com/dexvnphga/image/upload/v1697019049/DoAn/nz9a7vdxefn7a6uf7v27.png','username1@gmail.com','2023-08-26 15:26:10'),(5,'hoang105244826892173847958','$2a$10$aSrk7.iNZVVNeco6.GM0R.f3nwZ94BUjWY6Yv74lGMmRCgiW/l4aq','Hùng','Hoàng','https://res.cloudinary.com/dexvnphga/image/upload/v1697019049/DoAn/nz9a7vdxefn7a6uf7v27.png','qthcsdlhung2059@gmail.com','2023-10-15 13:08:52'),(6,'username2','$2a$10$YnbMwn5GzsZ0Q0zKFvoKRuwgArCbDW3nlcc.XTpYTa.jJOGYp3VXa','User','Name','https://res.cloudinary.com/dexvnphga/image/upload/v1697019049/DoAn/nz9a7vdxefn7a6uf7v27.png','username@gmail.com','2023-08-26 15:26:10'),(7,'username3','$2a$10$YnbMwn5GzsZ0Q0zKFvoKRuwgArCbDW3nlcc.XTpYTa.jJOGYp3VXa','User','Name','https://res.cloudinary.com/dexvnphga/image/upload/v1697019049/DoAn/nz9a7vdxefn7a6uf7v27.png','username3@gmail.com','2023-08-26 15:26:10'),(8,'username4','$2a$10$YnbMwn5GzsZ0Q0zKFvoKRuwgArCbDW3nlcc.XTpYTa.jJOGYp3VXa','User','Name','https://res.cloudinary.com/dexvnphga/image/upload/v1697019049/DoAn/nz9a7vdxefn7a6uf7v27.png','username4@gmail.com','2023-08-26 15:26:10'),(9,'username5','$2a$10$YnbMwn5GzsZ0Q0zKFvoKRuwgArCbDW3nlcc.XTpYTa.jJOGYp3VXa','User','Name','https://res.cloudinary.com/dexvnphga/image/upload/v1697019049/DoAn/nz9a7vdxefn7a6uf7v27.png','username5@gmail.com','2023-08-26 15:26:10'),(10,'username6','$2a$10$YnbMwn5GzsZ0Q0zKFvoKRuwgArCbDW3nlcc.XTpYTa.jJOGYp3VXa','User','Name','https://res.cloudinary.com/dexvnphga/image/upload/v1697019049/DoAn/nz9a7vdxefn7a6uf7v27.png','username6@gmail.com','2023-08-26 15:26:10'),(11,'username7','$2a$10$YnbMwn5GzsZ0Q0zKFvoKRuwgArCbDW3nlcc.XTpYTa.jJOGYp3VXa','User','Name','https://res.cloudinary.com/dexvnphga/image/upload/v1697019049/DoAn/nz9a7vdxefn7a6uf7v27.png','username7@gmail.com','2023-08-26 15:26:10'),(12,'username8','$2a$10$YnbMwn5GzsZ0Q0zKFvoKRuwgArCbDW3nlcc.XTpYTa.jJOGYp3VXa','User','Name','https://res.cloudinary.com/dexvnphga/image/upload/v1697019049/DoAn/nz9a7vdxefn7a6uf7v27.png','username8@gmail.com','2023-08-26 15:26:10'),(13,'username9','$2a$10$YnbMwn5GzsZ0Q0zKFvoKRuwgArCbDW3nlcc.XTpYTa.jJOGYp3VXa','User','Name','https://res.cloudinary.com/dexvnphga/image/upload/v1697019049/DoAn/nz9a7vdxefn7a6uf7v27.png','username9@gmail.com','2023-08-26 15:26:10');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_note`
--

DROP TABLE IF EXISTS `user_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_note` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `lecture_id` int NOT NULL,
  `note_text` text COLLATE utf8mb4_unicode_ci,
  `note_time` int DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `lecture_id` (`lecture_id`),
  CONSTRAINT `user_note_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_note_ibfk_2` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_note`
--

LOCK TABLES `user_note` WRITE;
/*!40000 ALTER TABLE `user_note` DISABLE KEYS */;
INSERT INTO `user_note` VALUES (1,1,2,'New note',0,'2023-10-01 16:51:07'),(2,1,3,'Note here',3,'2023-10-15 13:04:21'),(3,1,3,'The new note',67,'2023-10-15 13:04:46'),(4,1,1,'Add note',2,'2023-10-20 07:29:45');
/*!40000 ALTER TABLE `user_note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,2),(2,1,1),(3,1,3),(4,2,2),(5,3,2),(6,4,2),(7,2,3),(8,5,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voucher`
--

DROP TABLE IF EXISTS `voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voucher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `discount` decimal(5,2) NOT NULL,
  `expiration_date` date DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `course_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `voucher_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher`
--

LOCK TABLES `voucher` WRITE;
/*!40000 ALTER TABLE `voucher` DISABLE KEYS */;
/*!40000 ALTER TABLE `voucher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-19 16:42:07
