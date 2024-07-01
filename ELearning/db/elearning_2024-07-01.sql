# ************************************************************
# Sequel Ace SQL dump
# Version 20067
#
# https://sequel-ace.com/
# https://github.com/Sequel-Ace/Sequel-Ace
#
# Host: localhost (MySQL 8.3.0)
# Database: elearning
# Generation Time: 2024-07-01 15:54:38 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE='NO_AUTO_VALUE_ON_ZERO', SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table blog
# ------------------------------------------------------------

DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `author_id` int NOT NULL,
  `publish_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `author_id` (`author_id`),
  CONSTRAINT `blog_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# Dump of table blog_comment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `blog_comment`;

CREATE TABLE `blog_comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blog_id` int NOT NULL,
  `user_id` int NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `blog_comment_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`),
  CONSTRAINT `blog_comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# Dump of table course
# ------------------------------------------------------------

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `subtitle` varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `background` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `preview_video_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `slug` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `price` decimal(10,2) DEFAULT '0.00',
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  `count_registration` int DEFAULT '0',
  `creator_id` int NOT NULL,
  `publish_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `creator_id` (`creator_id`),
  KEY `idx_course_slug` (`slug`),
  KEY `idx_course_name` (`name`),
  KEY `idx_course_price` (`price`),
  KEY `idx_course_name_price` (`name`,`price`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;

INSERT INTO `course` (`id`, `name`, `description`, `subtitle`, `background`, `preview_video_url`, `slug`, `price`, `deleted`, `count_registration`, `creator_id`, `publish_date`, `created_date`, `updated_date`)
VALUES
	(1,'Get Started with Git/Github','This course is for starter to begin learning Git/Github','Get Started with Git/Github','https://techvccloud.mediacdn.vn/280518386289090560/2021/3/2/023-1614681588418717257234-0-0-767-1366-crop-16146815915111444794187.png',NULL,'get-started-with-git-github',10.00,b'0',4,1,'2023-09-24 17:00:00','2023-09-25 14:28:54','2024-07-01 15:53:50'),
	(2,'Python Master','Python beginner to Master','Python beginner to Master','https://res.cloudinary.com/dexvnphga/image/upload/v1696154801/DoAn/jhxbq4hmbwlzxe7v4ue6.png',NULL,'python-master',0.00,b'0',2,1,'2023-09-30 17:00:00','2023-10-01 10:06:39','2024-05-11 06:41:41'),
	(3,'ReactJS Net Ninja Course','ReactJS  beginner to Master','ReactJS beginner to Master','https://media.geeksforgeeks.org/wp-content/cdn-uploads/20200309202057/How-To-Learn-ReactJS-A-Complete-Guide-For-Beginners.jpg',NULL,'reactjs-net-ninja-course',0.00,b'0',2,1,'2023-09-24 17:00:00','2023-09-25 14:28:54','2024-05-14 15:25:50'),
	(4,'Vue JS 3 Tutorial for Beginners - Net Ninja','Vue JS 3 Tutorial for Beginners','Vue JS 3 Tutorial for Beginners, FROM ZERO TO HERO','https://d2ms8rpfqc4h24.cloudfront.net/Introduction_to_Vue_JS_959ca27287.jpg',NULL,'vue-js-3-tutorial-net-ninja',0.00,b'0',1,1,'2023-11-24 17:00:00','2023-11-24 17:00:00','2024-04-30 07:25:27'),
	(5,'JS Course for Beginners','JS Course','JS Course','https://res.cloudinary.com/dexvnphga/image/upload/v1697727137/DoAn/yjzacqm7zgc1o6qqiphi.jpg',NULL,'js-course-for-beginners',10.00,b'0',18,2,'2023-10-18 17:00:00','2023-10-19 14:52:15','2024-07-01 15:52:13'),
	(6,'Angular 2 Tutorial with Net Ninja','Yo gang, welcome to your very first Angular 2 tutorial for beginners... Angular 2 is one of the most popular JavaScript frameworks for creating dynamic web applications, and comes hot on the tail of it\'s incredibly popular predecessor, Angular 1. A lot has changed since then, so through this ng2 series, I\'ll be guiding you through how to create a we app from scratch, and teaching you the core essentials along the way...','Angular 2 Tutorial','https://miro.medium.com/v2/resize:fit:1400/1*4hhei-6UcZ1OFUG7-Z_vjw.jpeg',NULL,'angular-2-tutorial-with-net-ninja',0.00,b'0',0,2,'2023-11-25 17:00:00','2023-11-25 17:00:00','2023-11-25 17:00:00'),
	(7,'Python Tkinter GUI','Python Tkinter GUI','Python Tkinter GUI','https://i.ytimg.com/vi/YBvFcvisxxM/maxresdefault.jpg',NULL,'python-tkinter-gui',0.00,b'0',1,5,'2023-11-25 17:00:00','2023-11-25 17:00:00','2024-05-14 15:28:05'),
	(8,'ElectronJS Tutorial','In this ElectronJS Tutorial, we learn what is electron js and why we use it. Make Desktop software with HTML, CSS, JS','Electron js tutorial for beginners','https://i.ytimg.com/vi/sJFuMKPfpfs/sddefault.jpg',NULL,'electronjs-tutorial',0.00,b'0',1,5,'2023-12-05 17:00:00','2023-12-04 17:00:00','2024-04-12 08:39:12'),
	(9,'Python Selenium','This selenium tutorial is designed for beginners to learn how to use the python selenium module to perform web scraping, web testing and create website bots. Selenium is an automation framework that allows you to interact with websites using something called a web driver.','Selenium with Python','https://blog.testproject.io/wp-content/uploads/2020/06/16-06-A.jpg',NULL,'python-selenium',0.00,b'0',0,2,'2023-12-05 17:00:00','2023-12-05 17:00:00','2023-11-25 17:00:00'),
	(10,'NodeJS Express MongoDB Tutorial 2023','This is a complete NodeJS masterclass 12-hour, with ExpressJS, MongoDB, Mongoose, and other MERN stack elements. #nodejs #expressjs #mongodb #reactjs ','MERN stack. MongoDB ExpressJS. NodeJS','https://i.ytimg.com/vi/VT20NTbn6U4/maxresdefault.jpg',NULL,'node-expressjs-mongodb-tutorial-2023',0.00,b'0',0,1,'2023-12-05 17:00:00','2023-12-05 17:00:00','2023-11-25 17:00:00'),
	(11,'Rails 6 for Beginners','This is a complete Rails 6 for beginners','Rails 6 for Beginners','https://i.ytimg.com/vi/F-eI4MXnyPA/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLBQ98wPnb0rsj1tG3t6yC0eE6MvOg',NULL,'rails-6-for-beginners',0.00,b'0',0,1,'2023-12-05 17:00:00','2023-12-05 17:00:00','2023-11-25 17:00:00'),
	(19,'Basketball','Basketball','Basketball','https://res.cloudinary.com/dexvnphga/image/upload/v1714575317/DoAn/qpp7k1un3iwha2bkolci.png',NULL,NULL,0.00,b'0',0,1,'2024-05-01 21:54:47','2024-05-01 21:55:16','2024-05-01 21:55:16'),
	(20,'Sprite','aAAA','Football is a good sport game play.','https://res.cloudinary.com/dexvnphga/image/upload/v1714575670/DoAn/hdbdbt1xx8wwrcdgre3k.jpg',NULL,NULL,0.00,b'0',0,1,'2024-05-01 22:00:37','2024-05-01 22:01:08','2024-05-10 22:19:33');

/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table course_comment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `course_comment`;

CREATE TABLE `course_comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NOT NULL,
  `user_id` int NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `idx_course_comment_course_id` (`course_id`),
  CONSTRAINT `course_comment_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `course_comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `course_comment` WRITE;
/*!40000 ALTER TABLE `course_comment` DISABLE KEYS */;

INSERT INTO `course_comment` (`id`, `course_id`, `user_id`, `content`, `created_date`, `updated_date`)
VALUES
	(1,1,1,'This course is OK','2023-09-29 13:41:54','2024-03-12 15:33:11'),
	(2,1,1,'This course is perfect to practice with Git and Github','2023-09-29 13:50:39','2024-03-12 15:33:11'),
	(3,2,2,'This course is OK','2023-10-15 12:57:09','2024-03-12 15:33:11'),
	(4,2,1,'This course is good','2023-10-15 12:57:51','2024-03-12 15:33:11'),
	(5,7,7,'The best choice for pythn beginner, let choose it','2024-05-11 13:33:25',NULL),
	(6,8,7,'If you want to learn how to create a desktop app, let choose it','2024-05-11 13:34:11',NULL),
	(7,2,1,'Oh, this course has noproblem','2024-05-12 20:00:15',NULL);

/*!40000 ALTER TABLE `course_comment` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table course_criteria
# ------------------------------------------------------------

DROP TABLE IF EXISTS `course_criteria`;

CREATE TABLE `course_criteria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NOT NULL,
  `text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_course_criteria_course_id` (`course_id`),
  CONSTRAINT `course_criteria_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `course_criteria` WRITE;
/*!40000 ALTER TABLE `course_criteria` DISABLE KEYS */;

INSERT INTO `course_criteria` (`id`, `course_id`, `text`)
VALUES
	(1,1,'No require'),
	(2,1,'Newbie can learn'),
	(3,1,'Master learning Git'),
	(4,2,'No requirements for newbie'),
	(5,2,'Python OOP'),
	(6,2,'Python basic'),
	(7,2,'Python Enhance'),
	(8,5,'Js new'),
	(9,5,'New comer to Javascript ES6'),
	(10,5,'Rebase Javascript ES6'),
	(11,3,'Front end from SSR to CSR'),
	(12,3,'FE JS starter'),
	(13,3,'New comer with ReactJS'),
	(14,11,'Learn how to rapidly prototype ideas and turn them into presentable apps'),
	(15,11,'Become a professional Ruby on Rails developer'),
	(16,11,'Apply for jobs at software companies as Ruby on Rails developer'),
	(17,11,'Become a professional web application developer'),
	(18,11,'Design and build virtually any web application you can imagine'),
	(19,4,'Build amazing Vue.js Applications - all the Way from Small and Simple Ones up to Large Enterprise-level Ones'),
	(20,4,'Leverage Vue.js in both Multi- and Single-Page-Applications (MPAs and SPAs)'),
	(21,4,'Understand the Theory behind Vue.js and use it in Real Projects'),
	(22,4,'Learn the latest version of Vue (Vue 3), including the brand-new Composition API'),
	(23,5,'No coding experience is necessary to take this course! I take you from beginner to expert!'),
	(24,5,'Any computer and OS will work — Windows, macOS or Linux. We will set up your text editor the course.'),
	(25,5,'A basic understanding of HTML and CSS is a plus, but not a must! The course includes an HTML and CSS crash course.'),
	(26,6,'NO Angular 1 or Angular 2+ knowledge is required!'),
	(27,6,'Basic HTML and CSS knowledge helps, but isn\'t a must-have'),
	(28,6,'Prior TypeScript knowledge also helps but isn\'t necessary to benefit from this course'),
	(29,6,'Basic JavaScript knowledge is required'),
	(30,7,'You should have some programming knowledge, with Python or another language. Although we provide a complete Python Refresher course that covers everything you need to know, this is not an introductory Python course.'),
	(31,7,'You need a computer with either Linux, Mac, or Windows 10. Older Windows versions will work too but there can be some small issues that we can work around.'),
	(32,7,'Developing on a mobile device is possible by using a web editor. How to do this is detailed inside the course.'),
	(33,8,'A good understanding of JavaScript. Basic understanding of ES6 would be beneficial.'),
	(34,8,'Working knowledge of HTML & CSS.'),
	(35,8,'Be comfortable with very basic usage of the command line (CLI)'),
	(36,8,'Understand JavaScript in the Browser. e.g. Knowing what the global \'window\' variable is etc.'),
	(37,8,'Basic understanding of Node.js'),
	(38,8,'Basic knowledge of Git (Recommended, but not required)'),
	(39,9,'All Installation setup including Python basics is taken care as part of course'),
	(40,9,'Theoretical Material,Code dump are available for download'),
	(41,9,'Join in our Selenium Training community where 3 Million Students Learning Together which you will not see in any other Selenium courses on Udemy'),
	(42,9,'Though these are online Lectures.You will have Life Time instructor support.'),
	(43,10,'Absolutely NO understanding of Node or back-end development is required! I take you from beginner to advanced developer!'),
	(44,10,'Basic understanding of JavaScript is required (the course contains a section about asynchronous JavaScript with promises and async/await in case you need to get up to speed)'),
	(45,10,'Basic understanding of HTML is a plus (only for final part of the course), but NOT a must'),
	(46,10,'Any computer and OS will work — Windows, macOS or Linux'),
	(48,19,'Basketball'),
	(49,19,'Basketball');

/*!40000 ALTER TABLE `course_criteria` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table favorite_course
# ------------------------------------------------------------

DROP TABLE IF EXISTS `favorite_course`;

CREATE TABLE `favorite_course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `course_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `favorite_course_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `favorite_course_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `favorite_course` WRITE;
/*!40000 ALTER TABLE `favorite_course` DISABLE KEYS */;

INSERT INTO `favorite_course` (`id`, `user_id`, `course_id`)
VALUES
	(6,1,7),
	(7,1,20),
	(10,1,1),
	(11,7,5);

/*!40000 ALTER TABLE `favorite_course` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table last_lecture
# ------------------------------------------------------------

DROP TABLE IF EXISTS `last_lecture`;

CREATE TABLE `last_lecture` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `course_id` int DEFAULT NULL,
  `lecture_id` int DEFAULT NULL,
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `last_lecture_ibfk_1_idx` (`user_id`),
  KEY `last_lecture_ibfk_2_idx` (`course_id`),
  KEY `last_lecture_ibfk_3_idx` (`lecture_id`),
  CONSTRAINT `last_lecture_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL,
  CONSTRAINT `last_lecture_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE SET NULL,
  CONSTRAINT `last_lecture_ibfk_3` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `last_lecture` WRITE;
/*!40000 ALTER TABLE `last_lecture` DISABLE KEYS */;

INSERT INTO `last_lecture` (`id`, `user_id`, `course_id`, `lecture_id`, `updated_date`)
VALUES
	(1,1,1,3,'2024-04-30 21:54:00'),
	(3,1,2,1,'2024-04-22 22:55:21'),
	(4,1,3,17,'2024-05-17 23:14:30'),
	(5,1,4,49,'2024-05-19 09:54:49'),
	(6,1,8,113,'2024-05-12 18:37:41'),
	(7,7,2,1,'2024-05-11 13:41:41'),
	(8,7,3,9,'2024-05-14 22:25:50'),
	(9,7,7,82,'2024-05-15 00:18:21'),
	(10,12,5,8,'2024-05-18 09:38:25'),
	(11,1,5,8,'2024-06-08 16:45:56'),
	(12,7,5,8,'2024-05-18 21:23:21'),
	(13,8,5,8,'2024-05-18 21:30:26'),
	(14,8,1,3,'2024-05-18 21:31:28'),
	(15,9,1,3,'2024-05-19 23:40:31'),
	(16,9,5,8,'2024-06-08 16:29:44'),
	(17,13,5,8,'2024-07-01 22:53:08'),
	(18,7,1,3,'2024-07-01 22:54:10');

/*!40000 ALTER TABLE `last_lecture` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table lecture
# ------------------------------------------------------------

DROP TABLE IF EXISTS `lecture`;

CREATE TABLE `lecture` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `duration` int NOT NULL DEFAULT '0',
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `video_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `order_index` int NOT NULL,
  `section_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `section_id` (`section_id`),
  CONSTRAINT `lecture_ibfk_1` FOREIGN KEY (`section_id`) REFERENCES `section` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `lecture` WRITE;
/*!40000 ALTER TABLE `lecture` DISABLE KEYS */;

INSERT INTO `lecture` (`id`, `title`, `content`, `description`, `duration`, `type`, `video_url`, `order_index`, `section_id`)
VALUES
	(1,'Introduce to Python','Introduce to Python','Introduce to Python',89,'VIDEO','https://spring-boot-elearning.s3.ap-southeast-1.amazonaws.com/Introduction%20to%20Python-%20Introduction.mp4',1,1),
	(2,'Set up Environment','Setup Environment','Setup Environment',240,'VIDEO','https://www.youtube.com/watch?v=GZbeL5AcTgw',2,1),
	(3,'Git','Git','Git',159,'VIDEO','https://www.youtube.com/watch?v=gtAMSRwPHvo',1,2),
	(4,'Git status','Git status','Git status',38,'VIDEO','https://www.youtube.com/watch?v=d7p-KkrSIyo',1,3),
	(6,'Draw Heart with Turtle','Draw Heart with Turtle','Draw Heart with Turtle',27,'VIDEO','https://www.youtube.com/watch?v=A8UHaRKw_kQ',3,1),
	(7,'Introduction 2','Introduction 2','Introduction 2',146,'VIDEO','https://www.youtube.com/watch?v=iHADSeaByp8',2,4),
	(8,'Introduction','Introduction','Introduction',243,'VIDEO','https://www.youtube.com/watch?v=J2tVkEpcaOY',1,4),
	(9,'Introduction','Introduction','Introduction',366,'VIDEO','https://www.youtube.com/watch?v=j942wKiXFu8',1,5),
	(10,'Creating a React Application','Creating a React Application','Creating a React Application',781,'VIDEO','https://www.youtube.com/watch?v=kVeOpcw4GWY',1,6),
	(11,'Components & Templates','Components & Templates','Components & Templates',396,'VIDEO','https://www.youtube.com/watch?v=9D1x7-2FmTA',2,6),
	(12,'Dynamic Values in Templates','Dynamic Values in Templates','Dynamic Values in Templates',332,'VIDEO','https://www.youtube.com/watch?v=pnhO8UaCgxg',3,6),
	(13,'Multiple Components','Multiple Components','Multiple Components',369,'VIDEO','https://www.youtube.com/watch?v=0sSYmRImgRY',4,6),
	(14,'Adding Styles','Adding Styles to React components','Adding Styles to React components',400,'VIDEO','https://www.youtube.com/watch?v=NbTrGcz4DW8',5,6),
	(15,'Click Events','ReactJS Click Events - onClick','ReactJS Click Events - onClick',440,'VIDEO','https://www.youtube.com/watch?v=0XSDAup85SA',6,6),
	(16,'Using State (useState hook)','ReactJS Using State (useState hook)','ReactJS Using State (useState hook)',401,'VIDEO','https://www.youtube.com/watch?v=4pO-HcG2igk',7,6),
	(17,'Intro to React Dev Tools','Intro to React Dev Tools','Intro to React Dev Tools',259,'VIDEO','https://www.youtube.com/watch?v=rb1GWqCJid4',2,5),
	(18,'Render Outputting Lists','ReactJS Render Outputting Lists','ReactJS Render Outputting Lists',403,'VIDEO','https://www.youtube.com/watch?v=tHjxSVaj_wY',8,6),
	(19,'Props','ReactJS Props','ReactJS Props',535,'VIDEO','https://www.youtube.com/watch?v=PHaECbrKgs0',9,6),
	(20,'Reusing Components','Reusing Components','Reusing Components',176,'VIDEO','https://www.youtube.com/watch?v=-YpnB-zlkPU',10,6),
	(21,'Functions as Props','Functions as Props','Functions as Props',270,'VIDEO','https://www.youtube.com/watch?v=CWEOYFzgOJs',11,6),
	(22,'useEffect Hook (the basics)','useEffect Hook (the basics)','useEffect Hook (the basics)',235,'VIDEO','https://www.youtube.com/watch?v=gv9ugDJ1ynU',12,6),
	(23,'useEffect Hook - useEffect Dependencies','useEffect Hook - useEffect Dependencies','useEffect Hook - useEffect Dependencies',260,'VIDEO','https://www.youtube.com/watch?v=jQc_bTFZ5_I',13,6),
	(24,'Using JSON Server','Using JSON Server','Using JSON Server',310,'VIDEO','https://www.youtube.com/watch?v=eao7ABGFUXs',14,6),
	(25,'Fetching Data with useEffect','Fetching Data with useEffect','Fetching Data with useEffect',474,'VIDEO','https://www.youtube.com/watch?v=qdCHEUaFhBk',15,6),
	(26,'Conditional Loading Message','Conditional Loading Message',NULL,226,'VIDEO','https://www.youtube.com/watch?v=qtheqr0jgIQ',16,6),
	(27,'Handling Fetch Errors','Handling Fetch Errors',NULL,458,'VIDEO','https://www.youtube.com/watch?v=DTBta08fXGU',17,6),
	(28,'Development Environment Setting','Development Environment Setting',NULL,739,'VIDEO','https://www.youtube.com/watch?v=sEGC-adSKXo',3,4),
	(29,'Basic JavaScript Syntax','Basic JavaScript Syntax',NULL,800,'VIDEO','https://www.youtube.com/watch?v=KXxXr0RxGDE',1,7),
	(30,'JavaScript Variables','JavaScript Variables',NULL,878,'VIDEO','https://www.youtube.com/watch?v=plOo5hNVQJU',2,7),
	(31,'JavaScript Data Types','JavaScript Data Types',NULL,344,'VIDEO','https://www.youtube.com/watch?v=yjE_xXL26qA',3,7),
	(32,'JavaScript Type Conversion','JavaScript Type Conversion',NULL,536,'VIDEO','https://www.youtube.com/watch?v=jfQyMPzPTjY',4,7),
	(33,'Expressions and Operators','Expressions and Operators',NULL,1046,'VIDEO','https://www.youtube.com/watch?v=nMQlXMHMz_Y',5,7),
	(34,'JavaScript Arrays','JavaScript Arrays',NULL,677,'VIDEO','https://www.youtube.com/watch?v=S2JVtEwa-kU',6,7),
	(35,'Function Declaration','Function Declaration',NULL,591,'VIDEO','https://www.youtube.com/watch?v=yPJCFWLd23o',7,7),
	(36,'JavaScript Function Expressions','JavaScript Function Expressions',NULL,732,'VIDEO','https://www.youtube.com/watch?v=Wggcy2oKV3E',8,7),
	(37,'JavaScript Decision Statements','JavaScript Decision Statements',NULL,891,'VIDEO','https://www.youtube.com/watch?v=Fk3tdDAWkCI',9,7),
	(38,'Vue JS 3 Tutorial for Beginners - Introduction','Vue JS 3 Tutorial for Beginners - Introduction',NULL,820,'VIDEO','https://www.youtube.com/watch?v=YrxBCBibVo0',1,8),
	(39,'Vue.js Basics (part 1)','Vue.js Basics (part 1)',NULL,1821,'VIDEO','https://www.youtube.com/watch?v=F7PLPJqVotk',2,8),
	(40,'Vue.js Basics (part 2)','Vue.js Basics (part 2)',NULL,2041,'VIDEO','https://www.youtube.com/watch?v=CYPZBK8zUik',3,8),
	(41,'The Vue CLI & Bigger Projects (part 1)','The Vue CLI & Bigger Projects (part 1)',NULL,2343,'VIDEO','https://www.youtube.com/watch?v=GWRvrSqnFbM',1,9),
	(42,'The Vue CLI & Bigger Projects (part 2)','The Vue CLI & Bigger Projects (part 2)',NULL,2647,'VIDEO','https://www.youtube.com/watch?v=KM1U6DqZf8M',2,9),
	(43,'Build a Reaction Timer Game','Build a Reaction Timer Game',NULL,2518,'VIDEO','https://www.youtube.com/watch?v=bc6czIBLKTg',3,9),
	(47,'Vue JS 3 - Forms & Inputs','Vue JS 3- Forms & Inputs',NULL,2741,'VIDEO','https://www.youtube.com/watch?v=ixOcve5PX-Q',4,9),
	(48,'Vue JS 3 - The Vue Router','Vue JS 3 - The Vue Router',NULL,2868,'VIDEO','https://www.youtube.com/watch?v=juocv4AtrHo',5,9),
	(49,'Vue JS 3 - Fetching Data','Vue JS 3 - Fetching Data',NULL,1210,'VIDEO','https://www.youtube.com/watch?v=7iDGJolHFmU',6,9),
	(50,'The Composition API (part 1)','The Composition API (part 1)',NULL,2681,'VIDEO','https://www.youtube.com/watch?v=V-kxBWcPJfo',7,9),
	(51,'The Composition API (part 2)','The Composition API (part 2)',NULL,2581,'VIDEO','https://www.youtube.com/watch?v=0FwBjPeLqQ8',8,9),
	(52,'Vue JS 3 - Next Steps','Vue JS 3 - Next Steps',NULL,102,'VIDEO','https://www.youtube.com/watch?v=Dj_2j-IKXuo',9,9),
	(53,'Angular 2 Tutorial  - Introduction','Angular 2 Tutorial  - Introduction',NULL,252,'VIDEO','https://www.youtube.com/watch?v=DwTNR3EBSJQ',1,10),
	(54,'Angular 2 Tutorial  - Installing the Angular CLI','Angular 2 Tutorial  - Installing the Angular CLI',NULL,366,'VIDEO','https://www.youtube.com/watch?v=5OLLPQq1GqQ',2,10),
	(55,'Angular 2 Tutorial  - Intro to TypeScript','Angular 2 Tutorial  - Intro to TypeScript',NULL,229,'VIDEO','https://www.youtube.com/watch?v=Gn8FEdVtM18',3,10),
	(56,'Angular 2 Tutorial  - Core Files Overview','Angular 2 Tutorial  - Core Files Overview',NULL,400,'VIDEO','https://www.youtube.com/watch?v=PXkXR26U04g',4,10),
	(57,'Angular 2 Tutorial  - Components','Angular 2 Tutorial  - Components',NULL,601,'VIDEO','https://www.youtube.com/watch?v=YKi2A2PFTnc',1,11),
	(58,'Angular 2 Tutorial  - Templates & CSS','Angular 2 Tutorial  - Templates & CSS',NULL,350,'VIDEO','https://www.youtube.com/watch?v=A90UT3o5bMk',2,11),
	(59,'Angular 2 Tutorial  - Creating a Component','Angular 2 Tutorial  - Creating a Component',NULL,387,'VIDEO','https://www.youtube.com/watch?v=GMkF1iUY3n8',3,11),
	(60,'Angular 2 Tutorial  - Nesting Components','Angular 2 Tutorial  - Nesting Components',NULL,321,'VIDEO','https://www.youtube.com/watch?v=uQZDNTeR44E',4,11),
	(61,'Angular 2 Tutorial  - Ng-Content Directive','Angular 2 Tutorial  - Ng-Content Directive',NULL,165,'VIDEO','https://www.youtube.com/watch?v=7RQDIChgaZk',5,11),
	(62,'Angular 2 Tutorial - Data Binding','Angular 2 Tutorial 0 - Data Binding',NULL,382,'VIDEO','https://www.youtube.com/watch?v=VF47uWWql04',6,11),
	(63,'Angular 2 Tutorial - Property Binding','Angular 2 Tutorial 1 - Property Binding',NULL,277,'VIDEO','https://www.youtube.com/watch?v=VkxyuKuJZrM',7,11),
	(64,'Angular 2 Tutorial - Event Binding','Angular 2 Tutorial 2 - Event Binding',NULL,244,'VIDEO','https://www.youtube.com/watch?v=uh5ZKGEEAkY',8,11),
	(65,'Angular 2 Tutorial - 2 Way Data Binding','Angular 2 Tutorial 3 - 2 Way Data Binding',NULL,269,'VIDEO','https://www.youtube.com/watch?v=HXjVelFtpuQ',9,11),
	(66,'Angular 2 Tutorial - Custom Property Binding (& @Input)','Angular 2 Tutorial 4 - Custom Property Binding (& @Input)',NULL,364,'VIDEO','https://www.youtube.com/watch?v=qenFRHDxU6E',10,11),
	(67,'Angular 2 Tutorial - Custom Event Binding (& @Output)','Angular 2 Tutorial 5 - Custom Event Binding (& @Output)',NULL,441,'VIDEO','https://www.youtube.com/watch?v=xFYzfHGCJBw',11,11),
	(68,'Angular 2 Tutorial - Routing','Angular 2 Tutorial 6 - Routing',NULL,684,'VIDEO','https://www.youtube.com/watch?v=patjvNM9Qbc',12,11),
	(69,'Angular 2 Tutorial - Adding Links','Angular 2 Tutorial 7 - Adding Links',NULL,279,'VIDEO','https://www.youtube.com/watch?v=NPCdCknF6TY',13,11),
	(70,'Angular 2 Tutorial - Route Params','Angular 2 Tutorial 8 - Route Params',NULL,306,'VIDEO','https://www.youtube.com/watch?v=8KPPRzRLvjc',14,11),
	(71,'Angular 2 Tutorial - Directives Introduction','Angular 2 Tutorial 9 - Directives Introduction',NULL,418,'VIDEO','https://www.youtube.com/watch?v=Wwix0JSQKF8',15,11),
	(72,'Angular 2 Tutorial - ngFor','Angular 2 Tutorial 0 - ngFor',NULL,461,'VIDEO','https://www.youtube.com/watch?v=GqTJy9VALhI',16,11),
	(73,'Angular 2 Tutorial - Pipes','Angular 2 Tutorial 1 - Pipes',NULL,267,'VIDEO','https://www.youtube.com/watch?v=iCmV2jBdDS8',17,11),
	(74,'Angular 2 Tutorial - Custom Filter Pipe','Angular 2 Tutorial 2 - Custom Filter Pipe',NULL,733,'VIDEO','https://www.youtube.com/watch?v=sVTNaYBVP88',18,11),
	(75,'Angular 2 Tutorial - Services','Angular 2 Tutorial 3 - Services',NULL,657,'VIDEO','https://www.youtube.com/watch?v=74NARvL2BI0',19,11),
	(76,'Angular 2 Tutorial - HTTP Service','Angular 2 Tutorial 4 - HTTP Service',NULL,701,'VIDEO','https://www.youtube.com/watch?v=IOp9OmNdHy4',20,11),
	(77,'Angular 2 Tutorial - Connecting to Firebase','Angular 2 Tutorial 5 - Connecting to Firebase',NULL,256,'VIDEO','https://www.youtube.com/watch?v=H9HZ41vM7mk',21,11),
	(78,'Angular 2 Tutorial - Firebase API','Angular 2 Tutorial 6 - Firebase API',NULL,511,'VIDEO','https://www.youtube.com/watch?v=Fb9o2uwRAk0',22,11),
	(79,'Angular 2 Tutorial - Posting Data to Firebase','Angular 2 Tutorial 7 - Posting Data to Firebase',NULL,469,'VIDEO','https://www.youtube.com/watch?v=YEizFZ6eaas',23,11),
	(80,'Learn Tkinter With Projects  Python Tkinter GUI Tutorial #0','Learn Tkinter With Projects  Python Tkinter GUI Tutorial #0',NULL,294,'VIDEO','https://www.youtube.com/watch?v=-Q4lm8eYulw',1,12),
	(81,'Notepad & Calculator In Tkinter + Why Tkinter?  Python Tkinter GUI Tutorial In Hindi','Notepad & Calculator In Tkinter + Why Tkinter?  Python Tkinter GUI Tutorial In Hindi',NULL,520,'VIDEO','https://www.youtube.com/watch?v=unYqUyeGWQY',2,12),
	(82,'Our First Tkinter GUI  Python Tkinter GUI Tutorial In Hindi','Our First Tkinter GUI  Python Tkinter GUI Tutorial In Hindi',NULL,518,'VIDEO','https://www.youtube.com/watch?v=K7XV9-GjzbY',3,12),
	(83,'Tkinter Widgets & Attributes  Python Tkinter GUI Tutorial In Hindi','Tkinter Widgets & Attributes  Python Tkinter GUI Tutorial In Hindi',NULL,308,'VIDEO','https://www.youtube.com/watch?v=tHI_LtnZNo4',1,13),
	(84,'Label, Geometry, Maxsize & Minsize  Python Tkinter GUI Tutorial In Hindi','Label, Geometry, Maxsize & Minsize  Python Tkinter GUI Tutorial In Hindi',NULL,741,'VIDEO','https://www.youtube.com/watch?v=dizKSszF74A',2,13),
	(85,'Displaying Images Using Label  Python Tkinter GUI Tutorial In Hindi','Displaying Images Using Label  Python Tkinter GUI Tutorial In Hindi',NULL,554,'VIDEO','https://www.youtube.com/watch?v=end4IWH0ihY',3,13),
	(86,'Attributes Of Label & Pack  Python Tkinter GUI Tutorial In Hindi','Attributes Of Label & Pack  Python Tkinter GUI Tutorial In Hindi',NULL,1202,'VIDEO','https://www.youtube.com/watch?v=0d-aZ5moXzA',4,13),
	(87,'Exercise 1: Creating Newspaper GUI  Python Tkinter GUI Tutorial In Hindi','Exercise 1: Creating Newspaper GUI  Python Tkinter GUI Tutorial In Hindi',NULL,151,'VIDEO','https://www.youtube.com/watch?v=WYLokw4mZQw',5,13),
	(88,'Frame In Tkinter  Python Tkinter GUI Tutorial In Hindi','Frame In Tkinter  Python Tkinter GUI Tutorial In Hindi',NULL,760,'VIDEO','https://www.youtube.com/watch?v=dgFjh5WbQDU',6,13),
	(89,'Packing Buttons In Tkinter  Python Tkinter GUI Tutorial In Hindi','Packing Buttons In Tkinter  Python Tkinter GUI Tutorial In Hindi',NULL,536,'VIDEO','https://www.youtube.com/watch?v=hM9f376cxpw',7,13),
	(90,'Entry Widget & Grid Layout In Tkinter  Python Tkinter GUI Tutorial 0','Entry Widget & Grid Layout In Tkinter  Python Tkinter GUI Tutorial 0',NULL,695,'VIDEO','https://www.youtube.com/watch?v=Rg96iAgQlfg',8,13),
	(91,'Travel Form Using Checkbuttons & Entry Widget  Python Tkinter GUI Tutorial 1','Travel Form Using Checkbuttons & Entry Widget  Python Tkinter GUI Tutorial 1',NULL,1042,'VIDEO','https://www.youtube.com/watch?v=H0hOBmc3-tw',9,13),
	(92,'Accepting User Input In Tkinter Form  Python Tkinter GUI Tutorial 2','Accepting User Input In Tkinter Form  Python Tkinter GUI Tutorial 2',NULL,714,'VIDEO','https://www.youtube.com/watch?v=dvLMe-L5e-g',10,13),
	(93,'Canvas Widget In Python Tkinter  Python Tkinter GUI Tutorial 3','Canvas Widget In Python Tkinter  Python Tkinter GUI Tutorial 3',NULL,936,'VIDEO','https://www.youtube.com/watch?v=NPwT6jI5fvI',11,13),
	(94,'Handling Events In Tkinter GUI  Python Tkinter GUI Tutorial 4','Handling Events In Tkinter GUI  Python Tkinter GUI Tutorial 4',NULL,506,'VIDEO','https://www.youtube.com/watch?v=ZWYhXncauUA',12,13),
	(95,'Python GUI Exercise 1: Solution  Python Tkinter GUI Tutorial 5','Python GUI Exercise 1: Solution  Python Tkinter GUI Tutorial 5',NULL,1540,'VIDEO','https://www.youtube.com/watch?v=Le1Sp71JI70',13,13),
	(96,'Python GUI Exercise 2: Window Resizer GUI  Python Tkinter GUI Tutorial 6','Python GUI Exercise 2: Window Resizer GUI  Python Tkinter GUI Tutorial 6',NULL,216,'VIDEO','https://www.youtube.com/watch?v=8Qy_809RDM0',14,13),
	(97,'Menus & Submenus In Tkinter Python  Python Tkinter GUI Tutorial 7','Menus & Submenus In Tkinter Python  Python Tkinter GUI Tutorial 7',NULL,937,'VIDEO','https://www.youtube.com/watch?v=mea-xEhMf3Y',15,13),
	(98,'Message Box In Tkinter Python  Python Tkinter GUI Tutorial 8','Message Box In Tkinter Python  Python Tkinter GUI Tutorial 8',NULL,704,'VIDEO','https://www.youtube.com/watch?v=yiAlV5Huo84',16,13),
	(99,'Sliders In Tkinter Using Scale()  Python Tkinter GUI Tutorial 9','Sliders In Tkinter Using Scale()  Python Tkinter GUI Tutorial 9',NULL,686,'VIDEO','https://www.youtube.com/watch?v=luAhG37X8pE',17,13),
	(100,'Creating RadioButtons In Tkinter  Python Tkinter GUI Tutorial 0','Creating RadioButtons In Tkinter  Python Tkinter GUI Tutorial 0',NULL,645,'VIDEO','https://www.youtube.com/watch?v=BIeO2JTxOuw',18,13),
	(101,'ListBox In Tkinter Python Tkinter GUI Tutorial 1','ListBox In Tkinter Python Tkinter GUI Tutorial 1',NULL,378,'VIDEO','https://www.youtube.com/watch?v=eLSJKrlS95I',19,13),
	(102,'ScrollBar In Tkinter GUI  Python Tkinter GUI Tutorial 2','ScrollBar In Tkinter GUI  Python Tkinter GUI Tutorial 2',NULL,541,'VIDEO','https://www.youtube.com/watch?v=BULr4y47V7c',20,13),
	(103,'Tkinter GUI Exercise 2 Solution + Shoutouts  Python Tkinter GUI Tutorial 3','Tkinter GUI Exercise 2 Solution + Shoutouts  Python Tkinter GUI Tutorial 3',NULL,576,'VIDEO','https://www.youtube.com/watch?v=nF61b4wAmxE',21,13),
	(104,'Status Bar In Tkinter  Python Tkinter GUI Tutorial 4','Status Bar In Tkinter  Python Tkinter GUI Tutorial 4',NULL,430,'VIDEO','https://www.youtube.com/watch?v=ZhmKAoKdiQQ',22,13),
	(105,'Using Classes And Objects To Create GUIs  Python Tkinter GUI Tutorial 5','Using Classes And Objects To Create GUIs  Python Tkinter GUI Tutorial 5',NULL,626,'VIDEO','https://www.youtube.com/watch?v=kY22BriodaU',23,13),
	(106,'More Tkinter Tips, Tricks & Functions  Python Tkinter GUI Tutorial 6','More Tkinter Tips, Tricks & Functions  Python Tkinter GUI Tutorial 6',NULL,477,'VIDEO','https://www.youtube.com/watch?v=EA3g9AZCIdU',24,13),
	(107,'Creating A Calculator Using Tkinter   Python Tkinter GUI Tutorial 7','Creating A Calculator Using Tkinter   Python Tkinter GUI Tutorial 7',NULL,1584,'VIDEO','https://www.youtube.com/watch?v=fbxsYcSccJE',1,14),
	(108,'Tkinter GUI Text Editor Announcement  Python Tkinter GUI Tutorial 8','Tkinter GUI Text Editor Announcement  Python Tkinter GUI Tutorial 8',NULL,131,'VIDEO','https://www.youtube.com/watch?v=QaLFoPq2sjA',2,14),
	(109,'Creating a GUI Notepad In Tkinter  Python Tkinter GUI Tutorial 9','Creating a GUI Notepad In Tkinter  Python Tkinter GUI Tutorial 9',NULL,1895,'VIDEO','https://www.youtube.com/watch?v=UMTaOFNk73Y',3,14),
	(110,'Tkinter Tutorials Conclusion + Resources  Python Tkinter GUI Tutorial 0','Tkinter Tutorials Conclusion + Resources  Python Tkinter GUI Tutorial 0',NULL,548,'VIDEO','https://www.youtube.com/watch?v=K_IbYqZQHko',4,14),
	(111,'Electron js tutorial for Beginners - Introduction','Electron js tutorial for Beginners - Introduction',NULL,182,'VIDEO','https://www.youtube.com/watch?v=OLiY4AkY3Yk',1,15),
	(112,'Electron js tutorial for Beginners - Installation and setup with  window','Electron js tutorial for Beginners  installation and setup with  window',NULL,360,'VIDEO','https://www.youtube.com/watch?v=zjfdrxexNPs',2,15),
	(113,'Electron js tutorial for Beginners - Setup and Hello world Program','Electron js tutorial for Beginners  Setup and Hello world Program',NULL,440,'VIDEO','https://www.youtube.com/watch?v=fR8ud349sn8',3,15),
	(114,'Electron js tutorial for Beginners - Hello world Program','Electron js tutorial for Beginners  Hello world Program',NULL,296,'VIDEO','https://www.youtube.com/watch?v=2EKmHi0pKl4',4,15),
	(115,'Electron js tutorial for Beginners - Dev Tool','Electron js tutorial for Beginners  Dev Tool',NULL,179,'VIDEO','https://www.youtube.com/watch?v=KpOnx1dbuvA',5,15),
	(116,'Electron js tutorial for Beginners - Main and Render Process','Electron js tutorial for Beginners   Main and Render Process',NULL,186,'VIDEO','https://www.youtube.com/watch?v=Z2IzeYiN310',1,16),
	(117,'Electron js tutorial for Beginners - Browser Window Properties','Electron js tutorial for Beginners  Browser Window Properties',NULL,413,'VIDEO','https://www.youtube.com/watch?v=rFJ44zdbpvo',2,16),
	(118,'Electron js tutorial for Beginners - Child Window','Electron js tutorial for Beginners  Child Window',NULL,307,'VIDEO','https://www.youtube.com/watch?v=97dNTfmulsc',3,16),
	(119,'Electron js tutorial for Beginners - IPC Render and Main render  share data','Electron js tutorial for Beginners  IPC Render and Main render  share data',NULL,381,'VIDEO','https://www.youtube.com/watch?v=Ytu5yXHhiVc',4,16),
	(120,'Electron js tutorial for Beginners - Make build','Electron js tutorial for Beginners  make build',NULL,425,'VIDEO','https://www.youtube.com/watch?v=MzuQ3_D3MCw',5,16),
	(121,'Electron js tutorial for Beginners - Reload Main Process','Electron js tutorial for Beginners - Reload Main Process',NULL,307,'VIDEO','https://www.youtube.com/watch?v=qP5ueAfoyCA',6,16),
	(122,'Electron js tutorial for Beginners - Important  App life cycle event','Electron js tutorial for Beginners - Important  App life cycle event',NULL,573,'VIDEO','https://www.youtube.com/watch?v=ECq-mMdKepc',7,16),
	(123,'Electron js tutorial for Beginners - frame less application','Electron js tutorial for Beginners - frame less application','Electron js tutorial for Beginners - frame less application',244,'VIDEO','https://www.youtube.com/watch?v=eK4h7qZ57Uw',8,16),
	(124,'Electron js tutorial for Beginners - Window State','Electron js tutorial for Beginners - Window State','Electron js tutorial for Beginners - Window State',435,'VIDEO','https://www.youtube.com/watch?v=hnm23Ns2kqk',9,16),
	(125,'Electron js tutorial for Beginners - Web content','Electron js tutorial for Beginners - Web content','Electron js tutorial for Beginners - Web content',499,'VIDEO','https://www.youtube.com/watch?v=idP5JLdoKVI',10,16),
	(126,'Electron js tutorial for Beginners - Global Shortcut','Electron js tutorial for Beginners - Global Shortcut','Electron js tutorial for Beginners - Global Shortcut',382,'VIDEO','https://www.youtube.com/watch?v=t59F_cCOfI4',11,16),
	(127,'Electron js tutorial for Beginners - Dialog box','Electron js tutorial for Beginners - Dialog box','Electron js tutorial for Beginners - Dialog box',424,'VIDEO','https://www.youtube.com/watch?v=tLvvE--Fsp4',12,16),
	(128,'Electron js tutorial for Beginners - Global Shortcut','Electron js tutorial for Beginners - Global Shortcut','Electron js tutorial for Beginners - Global Shortcut',559,'VIDEO','https://www.youtube.com/watch?v=XUf5p50qfAM',13,16),
	(129,'Electron js tutorial for Beginners - Context Menu','Electron js tutorial for Beginners - Context Menu','Electron js tutorial for Beginners - Context Menu',272,'VIDEO','https://www.youtube.com/watch?v=Ny44dZByPPA',14,16),
	(130,'Electron js tutorial for Beginners - Tray','Electron js tutorial for Beginners - Tray','Electron js tutorial for Beginners - Tray',381,'VIDEO','https://www.youtube.com/watch?v=f8tcPdBaF2Y',15,16),
	(131,'Electron js tutorial for Beginners - Webframe','Electron js tutorial for Beginners - Webframe','Electron js tutorial for Beginners - Webframe',306,'VIDEO','https://www.youtube.com/watch?v=Odn9xCgNPKk',16,16),
	(132,'Electron js tutorial for Beginners - Process  Shared API','Electron js tutorial for Beginners - Process  Shared API','Electron js tutorial for Beginners - Process  Shared API',367,'VIDEO','https://www.youtube.com/watch?v=BO-2zBbcqqI',17,16),
	(133,'Python Selenium Tutorial - Web Scraping, Bots & Testing','Python Selenium Tutorial - Web Scraping, Bots & Testing','Python Selenium Tutorial - Web Scraping, Bots & Testing',700,'VIDEO','https://www.youtube.com/watch?v=Xjv1sY630Uc',1,17),
	(134,'Python Selenium Tutorial - Locating Elements From HTML','Python Selenium Tutorial - Locating Elements From HTML','Python Selenium Tutorial - Locating Elements From HTML',971,'VIDEO','https://www.youtube.com/watch?v=b5jt2bhSeXs',2,17),
	(135,'Python Selenium Tutorial - Page Navigating and Clicking Elements','Python Selenium Tutorial - Page Navigating and Clicking Elements','Python Selenium Tutorial - Page Navigating and Clicking Elements',498,'VIDEO','https://www.youtube.com/watch?v=U6gbGk5WPws',3,17),
	(136,'Python Selenium Tutorial - ActionChains & Automating Cookie Clicker!','Python Selenium Tutorial - ActionChains & Automating Cookie Clicker!','Python Selenium Tutorial - ActionChains & Automating Cookie Clicker!',824,'VIDEO','https://www.youtube.com/watch?v=OISEEL5eBqg',4,17),
	(137,'Python Selenium Tutorial - UnitTest Framework (Part 1)','Python Selenium Tutorial - UnitTest Framework (Part 1)','Python Selenium Tutorial - UnitTest Framework (Part 1)',1130,'VIDEO','https://www.youtube.com/watch?v=9_5Wqgni_Xw',5,17),
	(138,'Python Selenium Tutorial - UnitTest Framework (Part 2)','Python Selenium Tutorial - UnitTest Framework (Part 2)','Python Selenium Tutorial - UnitTest Framework (Part 2)',1155,'VIDEO','https://www.youtube.com/watch?v=O_sIPPA4euw',6,17),
	(139,'Node JS Installation NodeJS #nodejs #expressjs','Node JS Installation NodeJS #nodejs #expressjs','Node JS Installation NodeJS #nodejs #expressjs',741,'VIDEO','https://www.youtube.com/watch?v=1CgJPjGzFII',1,18),
	(140,'Web Server Concepts API, Ports, Request, Response Node JS #nodejs #expressjs','Web Server Concepts API, Ports, Request, Response Node JS #nodejs #expressjs','Web Server Concepts API, Ports, Request, Response Node JS #nodejs #expressjs',817,'VIDEO','https://www.youtube.com/watch?v=sfMNI0yLZII',1,19),
	(141,'First Node.js / ExpressJS Web Server What is Express JS ? Node JS #nodejs','First Node.js / ExpressJS Web Server What is Express JS ? Node JS #nodejs','First Node.js / ExpressJS Web Server What is Express JS ? Node JS #nodejs',388,'VIDEO','https://www.youtube.com/watch?v=6WG6w3ipc9U',2,19),
	(142,'Serving Static from Files Express JS express.static Node JS #nodejs #expressjs','Serving Static from Files Express JS express.static Node JS #nodejs #expressjs','Serving Static from Files Express JS express.static Node JS #nodejs #expressjs',444,'VIDEO','https://www.youtube.com/watch?v=Xl3msGMwPp0',3,19),
	(143,'API endpoint in nodejs/express request, response Node JS #nodejs #expressjs','API endpoint in nodejs/express request, response Node JS #nodejs #expressjs','API endpoint in nodejs/express request, response Node JS #nodejs #expressjs',435,'VIDEO','https://www.youtube.com/watch?v=TVeH7YkH9LU',4,19),
	(144,'Environment Variables in Node JS process.env nodejs Node JS #nodejs #expressjs','Environment Variables in Node JS process.env nodejs Node JS #nodejs #expressjs','Environment Variables in Node JS process.env nodejs Node JS #nodejs #expressjs',335,'VIDEO','https://www.youtube.com/watch?v=Exbe46JuTe8',5,19),
	(145,'Req.query query param in rest api NodeJS #nodejs #expressjs','Req.query query param in rest api NodeJS #nodejs #expressjs','Req.query query param in rest api NodeJS #nodejs #expressjs',419,'VIDEO','https://www.youtube.com/watch?v=vnRsFtwWu8M',1,20),
	(146,'request params in node Params in express JS Node JS #nodejs #expressjs','request params in node Params in express JS Node JS #nodejs #expressjs','request params in node Params in express JS Node JS #nodejs #expressjs',383,'VIDEO','https://www.youtube.com/watch?v=4lSykV_rhBo',2,20),
	(147,'Req.body Node JS Express req.body undefined express Node JS #nodejs #expressjs','Req.body Node JS Express req.body undefined express Node JS #nodejs #expressjs','Req.body Node JS Express req.body undefined express Node JS #nodejs #expressjs',439,'VIDEO','https://www.youtube.com/watch?v=bQApV_RddO4',3,20),
	(148,'What is Middleware in Node JS Custom middleware Node JS 0 #nodejs #expressjs','What is Middleware in Node JS Custom middleware Node JS 0 #nodejs #expressjs','What is Middleware in Node JS Custom middleware Node JS 0 #nodejs #expressjs\n',481,'VIDEO','https://www.youtube.com/watch?v=qkMJL0FwiyE',4,20),
	(149,'Restart Node Server automatically Nodemon in Node Node JS 1 #nodejs #expressjs','Restart Node Server automatically Nodemon in Node Node JS 1 #nodejs #expressjs','Restart Node Server automatically Nodemon in Node Node JS 1 #nodejs #expressjs',132,'VIDEO','https://www.youtube.com/watch?v=aXuLsfq4cpE',5,20),
	(150,'What is Cookie ? Session management in Node JS express Node JS 2 #nodejs #expressjs','What is Cookie ? Session management in Node JS express Node JS 2 #nodejs #expressjs','What is Cookie ? Session management in Node JS express Node JS 2 #nodejs #expressjs',329,'VIDEO','https://www.youtube.com/watch?v=v_Ewns3n_Ow',6,20),
	(151,'Passport JS in Node JS Passport JS Authentication Node JS 3 #nodejs #expressjs','Passport JS in Node JS Passport JS Authentication Node JS 3 #nodejs #expressjs',NULL,1325,'VIDEO','https://www.youtube.com/watch?v=U-S3rgG8hBE',7,20),
	(152,'Hashing Password in Node.js using Bcrypt library Node JS 4 #nodejs #expressjs','Hashing Password in Node.js using Bcrypt library Node JS 4 #nodejs #expressjs',NULL,454,'VIDEO','https://www.youtube.com/watch?v=5e4LCRoCVA4',8,20),
	(153,'Heroku Node js App deploy Heroku CLI Node JS 5 #reactjs #expressjs #heroku','Heroku Node js App deploy Heroku CLI Node JS 5 #reactjs #expressjs #heroku',NULL,809,'VIDEO','https://www.youtube.com/watch?v=GeXA_MzMR6I',9,20),
	(154,'DotEnv package Creating environment variables Node Js 16 #nodejs','DotEnv package Creating environment variables Node Js 16 #nodejs',NULL,390,'VIDEO','https://www.youtube.com/watch?v=h6Gks3DjFKg',10,20),
	(155,'Node js Event Loop Understanding Asynchronous Javascript Callbacks in Javascript','Node js Event Loop Understanding Asynchronous Javascript Callbacks in Javascript',NULL,1350,'VIDEO','https://www.youtube.com/watch?v=W-HQC_YUGBY',11,20),
	(156,'File Upload in NodeJS using Multer Fetch API for file upload Sending form data with fetch','File Upload in NodeJS using Multer Fetch API for file upload Sending form data with fetch',NULL,1552,'VIDEO','https://www.youtube.com/watch?v=qfN6c5FEAQQ',12,20),
	(157,'Part 1: Installing Ruby on Rails','Part 1: Installing Ruby on Rails',NULL,155,'VIDEO','https://www.youtube.com/watch?v=wkNR1hG4yOk',1,21),
	(158,'Part 2: How to create a new Rails app','Part 2: How to create a new Rails app',NULL,213,'VIDEO','https://www.youtube.com/watch?v=4PLg-Oe3MfA',1,22),
	(159,'Part 3: How HTTP Requests work in the Browser','Part 3: How HTTP Requests work in the Browser',NULL,486,'VIDEO','https://www.youtube.com/watch?v=Bx2mFSsrucM',2,22),
	(160,'Part 4: The MVC (Model, View, Controller) Pattern?','Part 4: The MVC (Model, View, Controller) Pattern?',NULL,285,'VIDEO','https://www.youtube.com/watch?v=lKUR4mu1M-U',3,22),
	(161,'Part 5: Routes and Route Types','Part 5: Routes and Route Types',NULL,286,'VIDEO','https://www.youtube.com/watch?v=lwL_KWtflN0',4,22),
	(162,'Part 6: The Root Route','Part 6: The Root Route',NULL,155,'VIDEO','https://www.youtube.com/watch?v=nM2fTxKJ4O8',5,22),
	(163,'Part 7: Adding Bootstrap CSS & Javascript','Part 7: Adding Bootstrap CSS & Javascript',NULL,163,'VIDEO','https://www.youtube.com/watch?v=EzCl-6etSGI',6,22),
	(164,'Part 8: Using Partials for the Navbar','Part 8: Using Partials for the Navbar',NULL,241,'VIDEO','https://www.youtube.com/watch?v=6wS9-6BFBbs',7,22),
	(165,'Part 9: URL Helpers and link_to','Part 9: URL Helpers and link_to',NULL,249,'VIDEO','https://www.youtube.com/watch?v=Hj_h2v36e1M',8,22),
	(166,'Part 10: Setting up a Git repository and Flash messages','Part 10: Setting up a Git repository and Flash messages',NULL,867,'VIDEO','https://www.youtube.com/watch?v=5xTkSIuizRs',9,22),
	(167,'Part 11: Creating the User model','Part 11: Creating the User model',NULL,406,'VIDEO','https://www.youtube.com/watch?v=cuWoZw1D3oY',1,23),
	(168,'Part 12: Validations','Part 12: Validations',NULL,390,'VIDEO','https://www.youtube.com/watch?v=l-Jv5vMjB70',2,23),
	(169,'Part 13: Creating a Sign Up Form','Part 13: Creating a Sign Up Form',NULL,633,'VIDEO','https://www.youtube.com/watch?v=A5-BLCuAyBY',3,23),
	(170,'Part 14: Handling Sign Up Errors','Part 14: Handling Sign Up Errors',NULL,498,'VIDEO','https://www.youtube.com/watch?v=5sT_3Trmtw0',4,23),
	(171,'Part 15: Login with Session Cookies','Part 15: Login with Session Cookies',NULL,434,'VIDEO','https://www.youtube.com/watch?v=IzbQAj_tcfI',5,23),
	(172,'Part 16: Logging Out Users','Part 16: Logging Out Users',NULL,262,'VIDEO','https://www.youtube.com/watch?v=fCunJskZkYI',6,23),
	(173,'Part 17: Login Form','Part 17: Login Form',NULL,354,'VIDEO','https://www.youtube.com/watch?v=UC5LjuFDQM4',7,23),
	(174,'Part 18: Accessing the Current User','Part 18: Accessing the Current User',NULL,376,'VIDEO','https://www.youtube.com/watch?v=hlpKyOb5yLc',8,23),
	(175,'Part 19: Edit Password','Part 19: Edit Password',NULL,502,'VIDEO','https://www.youtube.com/watch?v=YWdwts1cgjI',9,23),
	(176,'Part 20: Forgot Your Password','Part 20: Forgot Your Password',NULL,524,'VIDEO','https://www.youtube.com/watch?v=5azbhq4z3kw',10,23),
	(177,'Part 21: Reset Password Token Mailer','Part 21: Reset Password Token Mailer',NULL,696,'VIDEO','https://www.youtube.com/watch?v=JMXGExhr0C4',11,23),
	(178,'Part 22: Password Reset Update','Part 22: Password Reset Update',NULL,469,'VIDEO','https://www.youtube.com/watch?v=kTB5z4NcrhM',12,23),
	(179,'Part 23: Rails Credentials','Part 23: Rails Credentials',NULL,237,'VIDEO','https://www.youtube.com/watch?v=_SnwlA0LvYQ',13,23),
	(180,'[OLD] Rails for Beginners Part 24: OmniAuth URLs','[OLD] Rails for Beginners Part 24: OmniAuth URLs',NULL,245,'VIDEO','https://www.youtube.com/watch?v=-x9wCR3Fv3k',14,23),
	(181,'Part 24: OmniAuth URLs [Revised for Omniauth 2.0]','Part 24: OmniAuth URLs [Revised for Omniauth 2.0]',NULL,319,'VIDEO','https://www.youtube.com/watch?v=FbUWc8Vnu8Y',15,23),
	(182,'Part 25: Twitter Account Model','Part 25: Twitter Account Model',NULL,679,'VIDEO','https://www.youtube.com/watch?v=o48-i4m6W78',16,23),
	(183,'Part 26: Table Plus','Part 26: Table Plus',NULL,132,'VIDEO','https://www.youtube.com/watch?v=0jhSV6BQJXg',17,23),
	(184,'Part 27: Twitter Accounts Page','Part 27: Twitter Accounts Page',NULL,794,'VIDEO','https://www.youtube.com/watch?v=IUFRC9RXs_Y',18,23),
	(185,'[OLD] Rails for Beginners Part 27: Twitter Accounts Page','[OLD] Rails for Beginners Part 27: Twitter Accounts Page',NULL,752,'VIDEO','https://www.youtube.com/watch?v=06jOyisd_bA',19,23),
	(186,'Part 28: Setting Records with Before Actions','Part 28: Setting Records with Before Actions',NULL,91,'VIDEO','https://www.youtube.com/watch?v=1cGrocFSGIE',20,23),
	(187,'Part 29: Tweets Index & New Actions','Part 29: Tweets Index & New Actions',NULL,744,'VIDEO','https://www.youtube.com/watch?v=dYx0mUDK1Xc',21,23),
	(188,'Part 30: Tweet Validations','Part 30: Tweet Validations',NULL,340,'VIDEO','https://www.youtube.com/watch?v=B7Rq4538W1o',22,23),
	(189,'Part 31: Tweet Partial','Part 31: Tweet Partial',NULL,378,'VIDEO','https://www.youtube.com/watch?v=jxsf6-bWJcY',23,23),
	(190,'Part 32: Edit and Destroy Tweets','Part 32: Edit and Destroy Tweets',NULL,444,'VIDEO','https://www.youtube.com/watch?v=IG-lEKwv_fE',24,23),
	(191,'Part 33: Twitter API','Part 33: Twitter API',NULL,299,'VIDEO','https://www.youtube.com/watch?v=tvDq_JJenjs',25,23),
	(192,'Part 34: Background Job for Posting Tweets','Part 34: Background Job for Posting Tweets',NULL,513,'VIDEO','https://www.youtube.com/watch?v=vvNJlgiQtGQ',26,23),
	(193,'Part 35: Editing Tweets','Part 35: Editing Tweets',NULL,136,'VIDEO','https://www.youtube.com/watch?v=4D3Ub3_e3EA',27,23),
	(194,'Part 36: Background Jobs with Sidekiq','Part 36: Background Jobs with Sidekiq',NULL,379,'VIDEO','https://www.youtube.com/watch?v=aaGSh38nzq8',28,23),
	(195,'Part 37: Creating a GitHub Repo to store our code','Part 37: Creating a GitHub Repo to store our code',NULL,244,'VIDEO','https://www.youtube.com/watch?v=YJM5pl_Xlxs',29,23),
	(196,'Part 38: Deploying to Heroku','Part 38: Deploying to Heroku',NULL,1024,'VIDEO','https://www.youtube.com/watch?v=bQ2wXZf9irA',30,23),
	(197,'Part 39: Dependent Destroy Model Associations','Part 39: Dependent Destroy Model Associations',NULL,208,'VIDEO','https://www.youtube.com/watch?v=TZkRZsLsjFM',31,23),
	(198,'[OLD] Rails for Beginners Part 40: OmniAuth CSRF Protection','[OLD] Rails for Beginners Part 40: OmniAuth CSRF Protection',NULL,268,'VIDEO','https://www.youtube.com/watch?v=NDxvUeseqXk',32,23),
	(199,'Part 41: Next Steps','Part 41: Next Steps',NULL,128,'VIDEO','https://www.youtube.com/watch?v=ZxuUAUQGdN8',33,23),
	(200,'How to build a Multithreaded HTTP Server in Ruby from Scratch  Preview','How to build a Multithreaded HTTP Server in Ruby from Scratch  Preview',NULL,286,'VIDEO','https://www.youtube.com/watch?v=6Zpq7R51Rfs',34,23),
	(201,'HTTP Server from Scratch: Rack & Rails Support  Preview','HTTP Server from Scratch: Rack & Rails Support  Preview',NULL,144,'VIDEO','https://www.youtube.com/watch?v=XmpP5IQy-gI',35,23),
	(202,'ABC','ABCSK',NULL,0,'TEXT',NULL,1,27),
	(203,'GOGO','GOGO',NULL,0,'VIDEO',NULL,1,26),
	(204,'GOGO','GOGO',NULL,0,'VIDEO','https://www.youtube.com/watch?v=TUCUUaOUz-s',2,26),
	(205,'ABC','ABCHS',NULL,0,'VIDEO','https://www.youtube.com/watch?v=A-EN7_aXpEo',3,26),
	(206,'ABSk','AJCKSJ',NULL,19,'VIDEO','https://www.youtube.com/watch?v=AKp0xU_2OA8',1,28),
	(207,'Lecture 2','Lecture 2',NULL,8,'VIDEO','https://www.youtube.com/watch?v=Q0TPFyfmIhk',2,28);

/*!40000 ALTER TABLE `lecture` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table lecture_comment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `lecture_comment`;

CREATE TABLE `lecture_comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `lecture_id` int NOT NULL,
  `user_id` int NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `lecture_id` (`lecture_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `lecture_comment_ibfk_1` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`id`),
  CONSTRAINT `lecture_comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `lecture_comment` WRITE;
/*!40000 ALTER TABLE `lecture_comment` DISABLE KEYS */;

INSERT INTO `lecture_comment` (`id`, `lecture_id`, `user_id`, `content`, `created_date`)
VALUES
	(1,1,1,'It\'s great','2023-10-05 16:49:21'),
	(2,2,1,'It\'s great','2023-10-06 16:49:21'),
	(3,3,1,'It\'s perfect to understand what you said','2023-10-05 16:49:21'),
	(4,4,1,'It\'s great','2023-10-05 16:49:21'),
	(5,13,3,'It\'s great','2023-10-06 16:49:21'),
	(6,6,1,'It\'s perfect to understand what you said','2023-10-05 16:49:21'),
	(7,7,1,'It\'s perfect to understand what you said','2023-10-05 16:49:21'),
	(8,8,1,'It\'s great','2023-10-06 16:49:21'),
	(9,9,1,'It\'s great','2023-10-05 16:49:21'),
	(10,10,1,'It\'s great','2023-10-06 16:49:21'),
	(11,11,1,'It\'s perfect to understand what you said','2023-10-05 16:49:21'),
	(12,12,1,'It\'s great','2023-10-05 16:49:21'),
	(13,13,1,'It\'s great','2023-10-05 16:49:21'),
	(14,10,2,'It\'s perfect to understand what you said','2023-10-06 16:49:21'),
	(15,11,2,'It\'s perfect to understand what you said','2023-10-05 16:49:21'),
	(16,12,2,'It\'s great','2023-10-06 16:49:21'),
	(17,13,2,'It\'s great','2023-10-05 16:49:21'),
	(18,2,1,'ABC','2024-04-16 22:26:53'),
	(19,2,1,'ABC','2024-04-16 22:27:47'),
	(20,2,1,'This lecture seems ok','2024-04-16 22:31:35'),
	(21,2,1,'Same as previous comment (((:','2024-04-16 22:33:17'),
	(22,17,1,'Hello\n','2024-04-18 15:58:31');

/*!40000 ALTER TABLE `lecture_comment` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table lecturer_registration
# ------------------------------------------------------------

DROP TABLE IF EXISTS `lecturer_registration`;

CREATE TABLE `lecturer_registration` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `user_id` int NOT NULL,
  `registration_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `lecturer_registration_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `lecturer_registration` WRITE;
/*!40000 ALTER TABLE `lecturer_registration` DISABLE KEYS */;

INSERT INTO `lecturer_registration` (`id`, `image_url`, `user_id`, `registration_date`)
VALUES
	(7,'https://res.cloudinary.com/dexvnphga/image/upload/v1715519723/DoAn/ufbve2ys5vjtrlxpcws5.png',7,'2024-05-12 20:15:21');

/*!40000 ALTER TABLE `lecturer_registration` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table progress
# ------------------------------------------------------------

DROP TABLE IF EXISTS `progress`;

CREATE TABLE `progress` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `lecture_id` int DEFAULT NULL,
  `done` bit(1) NOT NULL DEFAULT b'1',
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `progress_ibfk_1_idx` (`user_id`),
  KEY `progress_ibfk_2_idx` (`lecture_id`),
  CONSTRAINT `progress_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL,
  CONSTRAINT `progress_ibfk_2` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `progress` WRITE;
/*!40000 ALTER TABLE `progress` DISABLE KEYS */;

INSERT INTO `progress` (`id`, `user_id`, `lecture_id`, `done`, `created_date`)
VALUES
	(1,1,1,b'1','2024-04-29 04:10:47'),
	(2,1,2,b'1','2024-04-29 04:11:01');

/*!40000 ALTER TABLE `progress` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;

INSERT INTO `role` (`id`, `name`)
VALUES
	(1,'ROLE_ADMIN'),
	(2,'ROLE_USER'),
	(3,'ROLE_LECTURER');

/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table section
# ------------------------------------------------------------

DROP TABLE IF EXISTS `section`;

CREATE TABLE `section` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `order_index` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `section_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;

INSERT INTO `section` (`id`, `course_id`, `name`, `order_index`)
VALUES
	(1,2,'Get started',1),
	(2,1,'What is Git/Github ?',1),
	(3,1,'Get Started',2),
	(4,5,'Introduction',1),
	(5,3,'Introduction',1),
	(6,3,'Getting Started',2),
	(7,5,'Getting Started',2),
	(8,4,'Introduction',1),
	(9,4,'Getting Started',2),
	(10,6,'Introduction',1),
	(11,6,'Getting Started',2),
	(12,7,'Preview Result',1),
	(13,7,'Getting started',2),
	(14,7,'Practice by building apps',3),
	(15,8,'Introduction & Installation',1),
	(16,8,'Get started',2),
	(17,9,'Getting started',1),
	(18,10,'Introduction',1),
	(19,10,'Setting up',2),
	(20,10,'Practice',3),
	(21,11,'Introduction & Setup',1),
	(22,11,'The Ruby Programming Language',2),
	(23,11,'Practicing',3),
	(26,19,'Start',1),
	(27,19,'Basketball',2),
	(28,20,'Get started',1),
	(29,20,'Practice with me',2);

/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table transaction
# ------------------------------------------------------------

DROP TABLE IF EXISTS `transaction`;

CREATE TABLE `transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `original_amount` decimal(10,2) NOT NULL DEFAULT '0.00',
  `amount` decimal(10,2) DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `course_id` int NOT NULL,
  `payer_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  KEY `transaction_ibfk_3_idx` (`payer_id`),
  KEY `transaction_ibfk_1` (`user_id`),
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL,
  CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `transaction_ibfk_3` FOREIGN KEY (`payer_id`) REFERENCES `user` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;

INSERT INTO `transaction` (`id`, `original_amount`, `amount`, `code`, `created_date`, `course_id`, `payer_id`, `user_id`, `method`)
VALUES
	(1,0.00,0.00,NULL,'2023-10-16 07:31:01',2,1,1,NULL),
	(2,0.00,10000.00,NULL,'2023-10-16 07:31:01',1,1,1,NULL),
	(3,0.00,0.00,NULL,'2024-04-10 00:05:41',3,1,1,NULL),
	(4,0.00,0.00,NULL,'2024-04-12 15:39:13',8,1,1,NULL),
	(5,10.00,10.00,NULL,'2024-04-14 23:47:27',5,1,1,NULL),
	(6,0.00,0.00,NULL,'2024-04-30 14:25:27',4,1,1,NULL),
	(7,0.00,0.00,NULL,'2024-05-11 13:41:41',2,7,7,NULL),
	(8,0.00,0.00,NULL,'2024-05-14 22:25:50',3,7,7,NULL),
	(9,0.00,0.00,NULL,'2024-05-14 22:28:06',7,7,7,NULL),
	(16,10.00,10.00,NULL,'2024-05-18 09:38:25',5,12,12,NULL),
	(24,10.00,10.00,NULL,'2024-05-18 21:23:00',5,1,7,NULL),
	(26,10.00,10.00,NULL,'2024-05-18 21:30:13',5,8,8,NULL),
	(27,10.00,10.00,NULL,'2024-05-18 21:31:16',1,8,8,NULL),
	(28,10.00,10.00,NULL,'2024-05-19 09:57:22',1,1,9,NULL),
	(29,10.00,10.00,NULL,'2024-06-08 16:29:30',5,1,9,NULL),
	(30,10.00,10.00,NULL,'2024-07-01 22:52:13',5,13,13,NULL),
	(31,10.00,10.00,NULL,'2024-07-01 22:53:50',1,7,7,NULL);

/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `first_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `last_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `slug` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` bit(1) DEFAULT b'0',
  `credit` decimal(10,2) DEFAULT '0.00',
  `credit_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_username` (`username`),
  KEY `idx_user_email` (`email`),
  KEY `idx_user_slug` (`slug`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `avatar`, `email`, `slug`, `created_date`, `updated_date`, `deleted`, `credit`, `credit_number`)
VALUES
	(1,'admin','$2a$10$8ut36oMXUlCHBy4Mpx763eOkg.S7KepZ4oDklcwfGNKWvU5K67BLW','Hùng','Đậu Xuân Hoàng ','https://res.cloudinary.com/dexvnphga/image/upload/v1697019049/DoAn/nz9a7vdxefn7a6uf7v27.png','2051052059hung@ou.edu.vn',NULL,'2023-09-25 14:24:02','2024-07-01 15:53:50',b'0',107.00,NULL),
	(2,'hung110785279923642180129','$2a$10$5gQJC.skvQelgMDuKAkysO663qKO0c.Cm7/.RS.nA8oMKt3KnStLS','Hoang','Hung','https://lh3.googleusercontent.com/a/ACg8ocJ_OSHGKfMV7cM9FcLAZdBFD8yk98bt3RBTt7Vh1Q3K=s96-c','tdph1168@gmail.com',NULL,'2023-09-26 13:21:30','2024-07-01 13:21:26',b'0',0.00,NULL),
	(3,'username','$2a$10$YnbMwn5GzsZ0Q0zKFvoKRuwgArCbDW3nlcc.XTpYTa.jJOGYp3VXa','User','Name','https://res.cloudinary.com/dexvnphga/image/upload/v1697019049/DoAn/nz9a7vdxefn7a6uf7v27.png','username@gmail.com',NULL,'2022-08-26 15:26:10','2024-07-01 13:21:26',b'0',0.00,NULL),
	(4,'username1','$2a$10$YnbMwn5GzsZ0Q0zKFvoKRuwgArCbDW3nlcc.XTpYTa.jJOGYp3VXa','User','Name','https://res.cloudinary.com/dexvnphga/image/upload/v1697019049/DoAn/nz9a7vdxefn7a6uf7v27.png','username1@gmail.com',NULL,'2023-08-26 15:26:10','2024-07-01 13:21:26',b'0',0.00,NULL),
	(5,'hoang105244826892173847958','$2a$10$aSrk7.iNZVVNeco6.GM0R.f3nwZ94BUjWY6Yv74lGMmRCgiW/l4aq','Hùng','Hoàng','https://res.cloudinary.com/dexvnphga/image/upload/v1697019049/DoAn/nz9a7vdxefn7a6uf7v27.png','qthcsdlhung2059@gmail.com',NULL,'2023-10-15 13:08:52','2024-07-01 13:21:26',b'0',0.00,NULL),
	(6,'username2','$2a$10$YnbMwn5GzsZ0Q0zKFvoKRuwgArCbDW3nlcc.XTpYTa.jJOGYp3VXa','User','Name','https://res.cloudinary.com/dexvnphga/image/upload/v1697019049/DoAn/nz9a7vdxefn7a6uf7v27.png','username@gmail.com',NULL,'2023-08-26 15:26:10','2024-07-01 13:21:26',b'0',0.00,NULL),
	(7,'username3','$2a$10$EvvQWCc8kPpXX92ED6qgpu3D5qsnxqNnyEy9CU2oNWxzu0e4/P17q','User','Name','https://res.cloudinary.com/dexvnphga/image/upload/v1697019049/DoAn/nz9a7vdxefn7a6uf7v27.png','username3@gmail.com',NULL,'2023-08-26 15:26:10','2024-07-01 13:21:26',b'0',0.00,NULL),
	(8,'username4','$2a$10$YnbMwn5GzsZ0Q0zKFvoKRuwgArCbDW3nlcc.XTpYTa.jJOGYp3VXa','User','Name','https://res.cloudinary.com/dexvnphga/image/upload/v1697019049/DoAn/nz9a7vdxefn7a6uf7v27.png','username4@gmail.com',NULL,'2023-08-26 15:26:10','2024-07-01 13:21:26',b'0',0.00,NULL),
	(9,'username5','$2a$10$YnbMwn5GzsZ0Q0zKFvoKRuwgArCbDW3nlcc.XTpYTa.jJOGYp3VXa','User','Name','https://res.cloudinary.com/dexvnphga/image/upload/v1697019049/DoAn/nz9a7vdxefn7a6uf7v27.png','username5@gmail.com',NULL,'2023-08-26 15:26:10','2024-07-01 13:21:26',b'0',0.00,NULL),
	(10,'username6','$2a$10$YnbMwn5GzsZ0Q0zKFvoKRuwgArCbDW3nlcc.XTpYTa.jJOGYp3VXa','User','Name','https://res.cloudinary.com/dexvnphga/image/upload/v1697019049/DoAn/nz9a7vdxefn7a6uf7v27.png','username6@gmail.com',NULL,'2023-08-26 15:26:10','2024-07-01 13:21:26',b'0',0.00,NULL),
	(11,'username7','$2a$10$YnbMwn5GzsZ0Q0zKFvoKRuwgArCbDW3nlcc.XTpYTa.jJOGYp3VXa','User','Name','https://res.cloudinary.com/dexvnphga/image/upload/v1697019049/DoAn/nz9a7vdxefn7a6uf7v27.png','username7@gmail.com',NULL,'2023-08-26 15:26:10','2024-07-01 13:21:26',b'0',0.00,NULL),
	(12,'username8','$2a$10$YnbMwn5GzsZ0Q0zKFvoKRuwgArCbDW3nlcc.XTpYTa.jJOGYp3VXa','User','Name','https://res.cloudinary.com/dexvnphga/image/upload/v1697019049/DoAn/nz9a7vdxefn7a6uf7v27.png','username8@gmail.com',NULL,'2023-08-26 15:26:10','2024-07-01 13:21:26',b'0',0.00,NULL),
	(13,'username9','$2a$10$YnbMwn5GzsZ0Q0zKFvoKRuwgArCbDW3nlcc.XTpYTa.jJOGYp3VXa','User','Name','https://res.cloudinary.com/dexvnphga/image/upload/v1697019049/DoAn/nz9a7vdxefn7a6uf7v27.png','username9@gmail.com',NULL,'2023-08-26 15:26:10','2024-07-01 15:52:13',b'0',10.00,NULL),
	(14,'alanwalker','$2a$10$f2qg9im2DIqCS3wenE.MceD12FLfe/togDMRh0ABezNQ9a181V80.','Alan','Walker',NULL,'abc@example.com',NULL,'2024-04-08 00:59:53','2024-07-01 13:21:26',NULL,0.00,NULL);

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_note
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_note`;

CREATE TABLE `user_note` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `lecture_id` int NOT NULL,
  `note_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `note_time` int DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `lecture_id` (`lecture_id`),
  CONSTRAINT `user_note_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_note_ibfk_2` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `user_note` WRITE;
/*!40000 ALTER TABLE `user_note` DISABLE KEYS */;

INSERT INTO `user_note` (`id`, `user_id`, `lecture_id`, `note_text`, `note_time`, `created_date`, `updated_date`)
VALUES
	(1,1,2,'New note',0,'2023-10-01 16:51:07','2024-03-12 15:18:42'),
	(2,1,3,'Note here',3,'2023-10-15 13:04:21','2024-03-12 15:18:42'),
	(3,1,3,'The map of git',67,'2024-04-20 15:01:41','2024-04-20 15:01:41'),
	(4,1,1,'Add note',2,'2023-10-20 07:29:45','2024-03-12 15:18:42'),
	(9,1,9,'Note here',132,'2024-05-12 20:08:35','2024-05-12 20:08:35'),
	(10,1,17,'ABC Update',13,'2024-04-18 15:51:41',NULL),
	(11,1,17,'Test Update',91,'2024-04-18 15:57:32','2024-04-18 15:57:32'),
	(12,1,49,'fetching in vue',5,'2024-05-19 08:24:46','2024-05-19 08:24:46');

/*!40000 ALTER TABLE `user_note` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;

INSERT INTO `user_role` (`id`, `user_id`, `role_id`)
VALUES
	(1,1,2),
	(2,1,1),
	(3,1,3),
	(4,2,2),
	(5,3,2),
	(6,4,2),
	(7,2,3),
	(8,5,2),
	(9,5,3),
	(10,14,2),
	(11,10,2),
	(12,6,2),
	(13,7,2),
	(14,8,2),
	(15,9,2),
	(16,11,2),
	(17,12,2),
	(18,13,2);

/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table voucher
# ------------------------------------------------------------

DROP TABLE IF EXISTS `voucher`;

CREATE TABLE `voucher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `discount` decimal(5,2) NOT NULL,
  `expiration_date` timestamp NULL DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `course_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `voucher_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
