# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.20)
# Database: cmedhealth
# Generation Time: 2020-08-24 05:58:19 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table prescriptions
# ------------------------------------------------------------

DROP TABLE IF EXISTS `prescriptions`;

CREATE TABLE `prescriptions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `modified_date` datetime(6) NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  `age` int(11) NOT NULL,
  `diagnosis_details` varchar(255) NOT NULL,
  `patient_gender` varchar(255) DEFAULT NULL,
  `medicine` varchar(255) NOT NULL,
  `date_of_next_visit` date NOT NULL,
  `patient_name` varchar(255) NOT NULL,
  `date_of_prescription` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `prescriptions` WRITE;
/*!40000 ALTER TABLE `prescriptions` DISABLE KEYS */;

INSERT INTO `prescriptions` (`id`, `created_date`, `modified_date`, `version`, `age`, `diagnosis_details`, `patient_gender`, `medicine`, `date_of_next_visit`, `patient_name`, `date_of_prescription`)
VALUES
	(1,'2020-08-23 19:25:47.000000','2020-08-23 19:25:47.000000',1,12,'test','1','test','2020-08-30','fakrul','2020-08-20');

/*!40000 ALTER TABLE `prescriptions` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table roles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `modified_date` datetime(6) NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;

INSERT INTO `roles` (`id`, `created_date`, `modified_date`, `version`, `description`, `name`)
VALUES
	(1,'2019-06-20 12:25:36.000000','2019-06-20 12:25:36.000000',NULL,'Kantell admin','ADMIN'),
	(2,'2019-06-20 12:43:28.000000','2019-06-20 12:43:28.000000',NULL,'Client manager','MANAGER'),
	(3,'2019-06-20 12:43:29.000000','2019-06-20 12:43:29.000000',NULL,'Client team leader','LEAD'),
	(4,'2019-06-20 12:43:30.000000','2019-06-20 12:43:30.000000',NULL,'End user','PARTICIPANTS');

/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_roles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;

INSERT INTO `user_roles` (`user_id`, `role_id`)
VALUES
	(1,4);

/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) NOT NULL,
  `modified_date` datetime(6) NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `enabled` bit(1) DEFAULT b'1',
  `expired` bit(1) DEFAULT b'0',
  `first_name` varchar(255) NOT NULL,
  `got_invitation` bit(1) DEFAULT b'0',
  `has_logged_in` bit(1) DEFAULT b'0',
  `last_name` varchar(255) NOT NULL,
  `last_password_reset_date` datetime(6) NOT NULL,
  `locked` bit(1) DEFAULT b'0',
  `password` varchar(255) DEFAULT NULL,
  `team_lead_id` bigint(20) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `org_id` bigint(20) DEFAULT NULL,
  `surveyor_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  KEY `FK902wn47cndp7hjhfvvb48brg` (`org_id`),
  KEY `FKcvsbujcmcfakb7n4jpmvlf9by` (`surveyor_id`),
  CONSTRAINT `FK902wn47cndp7hjhfvvb48brg` FOREIGN KEY (`org_id`) REFERENCES `organizations` (`id`),
  CONSTRAINT `FKcvsbujcmcfakb7n4jpmvlf9by` FOREIGN KEY (`surveyor_id`) REFERENCES `surveyors` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;

INSERT INTO `users` (`id`, `created_date`, `modified_date`, `version`, `email`, `enabled`, `expired`, `first_name`, `got_invitation`, `has_logged_in`, `last_name`, `last_password_reset_date`, `locked`, `password`, `team_lead_id`, `username`, `org_id`, `surveyor_id`)
VALUES
	(1,'2020-08-23 19:25:47.000000','2020-08-23 19:25:47.000000',1,'comillatanvir@gmail.com',b'1',b'0','tanvir',b'0',b'0','ahmed','2020-08-23 19:25:47.000000',b'0','$2a$10$9bhHfAj/9111OQcVt4C0Menkb6lARC9k7JLVgg8NXHN6upR1shn9u',NULL,'comillatanvir@gmail.com',NULL,NULL);

/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
