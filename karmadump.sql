-- MySQL dump 10.13  Distrib 5.5.23, for Win64 (x86)
--
-- Host: localhost    Database: karma
-- ------------------------------------------------------
-- Server version	5.5.23

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `house_name` varchar(255) DEFAULT NULL,
  `place` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `zip` bigint(20) DEFAULT NULL,
  `logged_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_address_logged_user_id` (`logged_user_id`),
  CONSTRAINT `fk_address_logged_user_id` FOREIGN KEY (`logged_user_id`) REFERENCES `logged_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Address entity. @author Neeraja';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `approval_status`
--

DROP TABLE IF EXISTS `approval_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `approval_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='ApprovalStatus entity. @author Sanil kumar';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `approval_status`
--

LOCK TABLES `approval_status` WRITE;
/*!40000 ALTER TABLE `approval_status` DISABLE KEYS */;
INSERT INTO `approval_status` VALUES (1,'pending'),(2,'approved'),(3,'declined'),(4,'completed'),(5,'incompleted'),(6,'cancelled');
/*!40000 ALTER TABLE `approval_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sub_category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='Category entity. @author Dheeraj das.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'finance','financial'),(2,'education','education'),(3,'health','health'),(4,'nec euismod','habitasse platea'),(5,'mauris laoreet','eu interdum'),(6,'magna vulputate','fusce posuere'),(7,'tortor sollicitudin','nisi nam'),(8,'vestibulum quam','curabitur gravida'),(9,'pede malesuada','accumsan felis'),(10,'posuere cubilia','nibh fusce');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `jhi_date` datetime,
  `need_id` bigint(20) DEFAULT NULL,
  `help_id` bigint(20) DEFAULT NULL,
  `news_feed_id` bigint(20) DEFAULT NULL,
  `violation_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_need_id` (`need_id`),
  KEY `fk_comment_help_id` (`help_id`),
  KEY `fk_comment_news_feed_id` (`news_feed_id`),
  KEY `fk_comment_violation_id` (`violation_id`),
  CONSTRAINT `fk_comment_violation_id` FOREIGN KEY (`violation_id`) REFERENCES `violation` (`id`),
  CONSTRAINT `fk_comment_help_id` FOREIGN KEY (`help_id`) REFERENCES `help` (`id`),
  CONSTRAINT `fk_comment_need_id` FOREIGN KEY (`need_id`) REFERENCES `need` (`id`),
  CONSTRAINT `fk_comment_news_feed_id` FOREIGN KEY (`news_feed_id`) REFERENCES `news_feed` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='Comment entity @author Deepthi E';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (4,'violation comment','2018-11-06 03:04:33',NULL,NULL,NULL,4),(5,'violation comment2','2018-11-06 03:04:33',NULL,NULL,NULL,4),(6,'violation comment3','2018-11-05 07:04:33',NULL,NULL,NULL,4),(7,'violation comment4','2018-11-05 07:04:33',NULL,NULL,NULL,4),(8,'violation comment5','2018-11-05 07:04:33',NULL,NULL,NULL,4),(9,'violation comment5','2018-11-05 07:04:33',NULL,NULL,NULL,2);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangelog`
--

DROP TABLE IF EXISTS `databasechangelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangelog`
--

LOCK TABLES `databasechangelog` WRITE;
/*!40000 ALTER TABLE `databasechangelog` DISABLE KEYS */;
INSERT INTO `databasechangelog` VALUES ('00000000000001','jhipster','config/liquibase/changelog/00000000000000_initial_schema.xml','2018-11-02 13:33:01',1,'EXECUTED','7:5ef25e3f65bca8f9e6b65198dcbbbb3b','createTable tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableName=jhi_user_authority; addForeignKeyConstraint baseTableName=jhi_user_authority, constraintName=fk_authority_name, ...','',NULL,'3.5.4',NULL,NULL,'1145778909'),('20180924060348-1','jhipster','config/liquibase/changelog/20180924060348_added_entity_LoggedUser.xml','2018-11-02 13:33:01',2,'EXECUTED','7:c9891ff81e88567630c278073fbdc9ba','createTable tableName=logged_user','',NULL,'3.5.4',NULL,NULL,'1145778909'),('20180924060349-1','jhipster','config/liquibase/changelog/20180924060349_added_entity_ApprovalStatus.xml','2018-11-02 13:33:01',3,'EXECUTED','7:77e4e36a20a301883afa1b3daeef43ac','createTable tableName=approval_status','',NULL,'3.5.4',NULL,NULL,'1145778909'),('20180924060350-1','jhipster','config/liquibase/changelog/20180924060350_added_entity_Address.xml','2018-11-02 13:33:01',4,'EXECUTED','7:c72f5c5b73864f09ffab4ae81c6338de','createTable tableName=address','',NULL,'3.5.4',NULL,NULL,'1145778909'),('20180924060351-1','jhipster','config/liquibase/changelog/20180924060351_added_entity_Media.xml','2018-11-02 13:33:01',5,'EXECUTED','7:18e226086d22aa9614638ee6bc6b7453','createTable tableName=media','',NULL,'3.5.4',NULL,NULL,'1145778909'),('20180924060352-1','jhipster','config/liquibase/changelog/20180924060352_added_entity_Category.xml','2018-11-02 13:33:02',6,'EXECUTED','7:8d861f5caade3b58c565a1217a483064','createTable tableName=category','',NULL,'3.5.4',NULL,NULL,'1145778909'),('20180924060353-1','jhipster','config/liquibase/changelog/20180924060353_added_entity_UserCheck.xml','2018-11-02 13:33:02',7,'EXECUTED','7:f9b9efba4e14864e1a19ad1292e77ebe','createTable tableName=user_check','',NULL,'3.5.4',NULL,NULL,'1145778909'),('20180924060354-1','jhipster','config/liquibase/changelog/20180924060354_added_entity_Need.xml','2018-11-02 13:33:02',8,'EXECUTED','7:f358ea034523b8e13c6fb77f9b069664','createTable tableName=need; dropDefaultValue columnName=jhi_date, tableName=need; createTable tableName=need_categories; addPrimaryKey tableName=need_categories','',NULL,'3.5.4',NULL,NULL,'1145778909'),('20180924060355-1','jhipster','config/liquibase/changelog/20180924060355_added_entity_Help.xml','2018-11-02 13:33:03',9,'EXECUTED','7:80905e972ad4fa1a963b636a568473b3','createTable tableName=help; dropDefaultValue columnName=jhi_time, tableName=help','',NULL,'3.5.4',NULL,NULL,'1145778909'),('20180924060356-1','jhipster','config/liquibase/changelog/20180924060356_added_entity_Severity.xml','2018-11-02 13:33:03',10,'EXECUTED','7:70dd6b820d10d352231c5135c22ffbc9','createTable tableName=severity','',NULL,'3.5.4',NULL,NULL,'1145778909'),('20180924060357-1','jhipster','config/liquibase/changelog/20180924060357_added_entity_VerificationTeam.xml','2018-11-02 13:33:03',11,'EXECUTED','7:29efce71a09132ec3db8f9d7a6f01877','createTable tableName=verification_team; createTable tableName=verification_team_approving_users; addPrimaryKey tableName=verification_team_approving_users','',NULL,'3.5.4',NULL,NULL,'1145778909'),('20181029105812-1','jhipster','config/liquibase/changelog/20181029105812_added_entity_NewsFeed.xml','2018-11-02 13:33:03',12,'EXECUTED','7:00ebc8775ad2725a05176849021fc203','createTable tableName=news_feed; dropDefaultValue columnName=jhi_date, tableName=news_feed','',NULL,'3.5.4',NULL,NULL,'1145778909'),('20181029105813-1','jhipster','config/liquibase/changelog/20181029105813_added_entity_Comment.xml','2018-11-02 13:33:04',13,'EXECUTED','7:ece272f6466ac40e1cf391ffb631941a','createTable tableName=comment; dropDefaultValue columnName=jhi_date, tableName=comment','',NULL,'3.5.4',NULL,NULL,'1145778909'),('20181029105814-1','jhipster','config/liquibase/changelog/20181029105814_added_entity_Reply.xml','2018-11-02 13:33:04',14,'EXECUTED','7:28cfc663f4357ea9136c9363d76346cf','createTable tableName=reply; dropDefaultValue columnName=jhi_date, tableName=reply','',NULL,'3.5.4',NULL,NULL,'1145778909'),('20181029105815-1','jhipster','config/liquibase/changelog/20181029105815_added_entity_Violation.xml','2018-11-02 13:33:04',15,'EXECUTED','7:6f15c6ac66640d537433ab82e5db2b15','createTable tableName=violation; dropDefaultValue columnName=jhi_date, tableName=violation','',NULL,'3.5.4',NULL,NULL,'1145778909'),('20180924060348-2','jhipster','config/liquibase/changelog/20180924060348_added_entity_constraints_LoggedUser.xml','2018-11-02 13:33:05',16,'EXECUTED','7:3bfb9f9ebd966e3592c2a855feca38ed','addForeignKeyConstraint baseTableName=logged_user, constraintName=fk_logged_user_profile_pic_id, referencedTableName=media','',NULL,'3.5.4',NULL,NULL,'1145778909'),('20180924060350-2','jhipster','config/liquibase/changelog/20180924060350_added_entity_constraints_Address.xml','2018-11-02 13:33:06',17,'EXECUTED','7:9756a9a5dd08392cab78c1f337111583','addForeignKeyConstraint baseTableName=address, constraintName=fk_address_logged_user_id, referencedTableName=logged_user','',NULL,'3.5.4',NULL,NULL,'1145778909'),('20180924060351-2','jhipster','config/liquibase/changelog/20180924060351_added_entity_constraints_Media.xml','2018-11-02 13:33:07',18,'EXECUTED','7:a8035ec4e8c1b0c56caf38ac9b0aacd4','addForeignKeyConstraint baseTableName=media, constraintName=fk_media_need_id, referencedTableName=need; addForeignKeyConstraint baseTableName=media, constraintName=fk_media_help_id, referencedTableName=help; addForeignKeyConstraint baseTableName=m...','',NULL,'3.5.4',NULL,NULL,'1145778909'),('20180924060353-2','jhipster','config/liquibase/changelog/20180924060353_added_entity_constraints_UserCheck.xml','2018-11-02 13:33:09',19,'EXECUTED','7:6975632a3c1294db210e3cd1bf9758e7','addForeignKeyConstraint baseTableName=user_check, constraintName=fk_user_check_checked_need_id, referencedTableName=need; addForeignKeyConstraint baseTableName=user_check, constraintName=fk_user_check_checked_user_id, referencedTableName=logged_us...','',NULL,'3.5.4',NULL,NULL,'1145778909'),('20180924060354-2','jhipster','config/liquibase/changelog/20180924060354_added_entity_constraints_Need.xml','2018-11-02 13:33:11',20,'EXECUTED','7:20d6b298019d4ea7fb4ea4f9171510e1','addForeignKeyConstraint baseTableName=need, constraintName=fk_need_severity_id, referencedTableName=severity; addForeignKeyConstraint baseTableName=need, constraintName=fk_need_verification_team_id, referencedTableName=verification_team; addForeig...','',NULL,'3.5.4',NULL,NULL,'1145778909'),('20180924060355-2','jhipster','config/liquibase/changelog/20180924060355_added_entity_constraints_Help.xml','2018-11-02 13:33:12',21,'EXECUTED','7:6a16cdfffbd403c618a5744504e1d960','addForeignKeyConstraint baseTableName=help, constraintName=fk_help_approval_status_id, referencedTableName=approval_status; addForeignKeyConstraint baseTableName=help, constraintName=fk_help_provided_user_id, referencedTableName=logged_user; addFo...','',NULL,'3.5.4',NULL,NULL,'1145778909'),('20180924060357-2','jhipster','config/liquibase/changelog/20180924060357_added_entity_constraints_VerificationTeam.xml','2018-11-02 13:33:12',22,'EXECUTED','7:9bc9c61276fd3910d2370298744265cf','addForeignKeyConstraint baseTableName=verification_team_approving_users, constraintName=fk_verification_team_approving_users_verification_teams_id, referencedTableName=verification_team; addForeignKeyConstraint baseTableName=verification_team_appr...','',NULL,'3.5.4',NULL,NULL,'1145778909'),('20181029105812-2','jhipster','config/liquibase/changelog/20181029105812_added_entity_constraints_NewsFeed.xml','2018-11-02 13:33:12',23,'EXECUTED','7:ddc082a3867e577228c8694bcaf14a61','addForeignKeyConstraint baseTableName=news_feed, constraintName=fk_news_feed_logged_user_id, referencedTableName=logged_user','',NULL,'3.5.4',NULL,NULL,'1145778909'),('20181029105813-2','jhipster','config/liquibase/changelog/20181029105813_added_entity_constraints_Comment.xml','2018-11-02 13:33:13',24,'EXECUTED','7:4c4cf0f6ae5e365d7e6c62729df0a1a4','addForeignKeyConstraint baseTableName=comment, constraintName=fk_comment_need_id, referencedTableName=need; addForeignKeyConstraint baseTableName=comment, constraintName=fk_comment_help_id, referencedTableName=help; addForeignKeyConstraint baseTab...','',NULL,'3.5.4',NULL,NULL,'1145778909'),('20181029105814-2','jhipster','config/liquibase/changelog/20181029105814_added_entity_constraints_Reply.xml','2018-11-02 13:33:14',25,'EXECUTED','7:20c54caa16572308342376bdbe1c083b','addForeignKeyConstraint baseTableName=reply, constraintName=fk_reply_comment_id, referencedTableName=comment','',NULL,'3.5.4',NULL,NULL,'1145778909');
/*!40000 ALTER TABLE `databasechangelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangeloglock`
--

DROP TABLE IF EXISTS `databasechangeloglock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangeloglock`
--

LOCK TABLES `databasechangeloglock` WRITE;
/*!40000 ALTER TABLE `databasechangeloglock` DISABLE KEYS */;
INSERT INTO `databasechangeloglock` VALUES (1,'\0',NULL,NULL);
/*!40000 ALTER TABLE `databasechangeloglock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `help`
--

DROP TABLE IF EXISTS `help`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `help` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `jhi_time` timestamp NULL,
  `description` varchar(255) DEFAULT NULL,
  `approval_status_id` bigint(20) DEFAULT NULL,
  `provided_user_id` bigint(20) DEFAULT NULL,
  `fulfilled_need_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_help_approval_status_id` (`approval_status_id`),
  KEY `fk_help_provided_user_id` (`provided_user_id`),
  KEY `fk_help_fulfilled_need_id` (`fulfilled_need_id`),
  CONSTRAINT `fk_help_fulfilled_need_id` FOREIGN KEY (`fulfilled_need_id`) REFERENCES `need` (`id`),
  CONSTRAINT `fk_help_approval_status_id` FOREIGN KEY (`approval_status_id`) REFERENCES `approval_status` (`id`),
  CONSTRAINT `fk_help_provided_user_id` FOREIGN KEY (`provided_user_id`) REFERENCES `logged_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `help`
--

LOCK TABLES `help` WRITE;
/*!40000 ALTER TABLE `help` DISABLE KEYS */;
INSERT INTO `help` VALUES (1,'2018-11-02 13:44:09','helped',4,NULL,1);
/*!40000 ALTER TABLE `help` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_authority`
--

DROP TABLE IF EXISTS `jhi_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_authority` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_authority`
--

LOCK TABLES `jhi_authority` WRITE;
/*!40000 ALTER TABLE `jhi_authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `jhi_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_audit_event`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_audit_event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` varchar(50) NOT NULL,
  `event_date` timestamp NULL DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `idx_persistent_audit_event` (`principal`,`event_date`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_event`
--

LOCK TABLES `jhi_persistent_audit_event` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_event` DISABLE KEYS */;
INSERT INTO `jhi_persistent_audit_event` VALUES (1,'access-token','2018-11-02 10:38:23','AUTHENTICATION_FAILURE'),(2,'admin','2018-11-02 10:39:53','AUTHENTICATION_SUCCESS'),(3,'admin','2018-11-02 10:42:33','AUTHENTICATION_SUCCESS'),(4,'admin','2018-11-02 10:43:26','AUTHENTICATION_SUCCESS'),(5,'admin','2018-11-02 10:46:02','AUTHENTICATION_SUCCESS'),(6,'access-token','2018-11-02 11:01:26','AUTHENTICATION_FAILURE'),(7,'admin','2018-11-02 11:02:56','AUTHENTICATION_SUCCESS'),(8,'admin','2018-11-02 11:07:17','AUTHENTICATION_SUCCESS'),(9,'admin','2018-11-02 11:08:11','AUTHENTICATION_SUCCESS'),(10,'admin','2018-11-02 11:08:15','AUTHENTICATION_SUCCESS'),(11,'admin','2018-11-02 11:08:53','AUTHENTICATION_SUCCESS'),(12,'admin','2018-11-04 15:23:53','AUTHENTICATION_SUCCESS'),(13,'admin','2018-11-04 15:25:02','AUTHENTICATION_SUCCESS'),(14,'admin','2018-11-04 15:25:48','AUTHENTICATION_SUCCESS'),(15,'admin','2018-11-04 15:26:55','AUTHENTICATION_SUCCESS'),(16,'admin','2018-11-04 16:55:31','AUTHENTICATION_SUCCESS'),(17,'admin','2018-11-04 16:56:37','AUTHENTICATION_SUCCESS'),(18,'admin','2018-11-04 16:56:55','AUTHENTICATION_SUCCESS'),(19,'admin','2018-11-04 16:59:41','AUTHENTICATION_SUCCESS'),(20,'admin','2018-11-04 17:00:21','AUTHENTICATION_SUCCESS'),(21,'admin','2018-11-04 17:00:26','AUTHENTICATION_SUCCESS'),(22,'admin','2018-11-04 17:01:25','AUTHENTICATION_SUCCESS'),(23,'admin','2018-11-04 17:02:20','AUTHENTICATION_SUCCESS'),(24,'admin','2018-11-04 17:03:03','AUTHENTICATION_SUCCESS'),(25,'admin','2018-11-04 17:33:47','AUTHENTICATION_SUCCESS'),(26,'admin','2018-11-05 03:49:14','AUTHENTICATION_SUCCESS'),(27,'admin','2018-11-05 05:39:16','AUTHENTICATION_SUCCESS'),(28,'admin','2018-11-05 05:39:18','AUTHENTICATION_SUCCESS'),(29,'admin','2018-11-05 05:39:58','AUTHENTICATION_SUCCESS'),(30,'admin','2018-11-05 05:47:37','AUTHENTICATION_SUCCESS'),(31,'admin','2018-11-05 05:51:34','AUTHENTICATION_SUCCESS'),(32,'admin','2018-11-05 06:08:05','AUTHENTICATION_SUCCESS'),(33,'access-token','2018-11-05 06:14:53','AUTHENTICATION_FAILURE'),(34,'admin','2018-11-05 06:15:32','AUTHENTICATION_SUCCESS'),(35,'admin','2018-11-05 06:18:21','AUTHENTICATION_SUCCESS'),(36,'admin','2018-11-05 06:27:31','AUTHENTICATION_SUCCESS'),(37,'admin','2018-11-05 06:29:08','AUTHENTICATION_SUCCESS'),(38,'admin','2018-11-05 06:30:42','AUTHENTICATION_SUCCESS'),(39,'admin','2018-11-05 06:31:06','AUTHENTICATION_SUCCESS'),(40,'admin','2018-11-05 06:32:11','AUTHENTICATION_SUCCESS'),(41,'admin','2018-11-05 06:33:04','AUTHENTICATION_SUCCESS'),(42,'admin','2018-11-05 06:33:58','AUTHENTICATION_SUCCESS'),(43,'admin','2018-11-05 06:35:19','AUTHENTICATION_SUCCESS'),(44,'admin','2018-11-05 06:35:32','AUTHENTICATION_SUCCESS'),(45,'admin','2018-11-05 06:36:57','AUTHENTICATION_SUCCESS'),(46,'admin','2018-11-05 06:37:18','AUTHENTICATION_SUCCESS'),(47,'admin','2018-11-05 06:46:24','AUTHENTICATION_SUCCESS'),(48,'access-token','2018-11-05 06:57:51','AUTHENTICATION_FAILURE'),(49,'admin','2018-11-05 06:58:18','AUTHENTICATION_SUCCESS');
/*!40000 ALTER TABLE `jhi_persistent_audit_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_persistent_audit_evt_data`
--

DROP TABLE IF EXISTS `jhi_persistent_audit_evt_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_audit_evt_data` (
  `event_id` bigint(20) NOT NULL,
  `name` varchar(150) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`,`name`),
  KEY `idx_persistent_audit_evt_data` (`event_id`),
  CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_evt_data`
--

LOCK TABLES `jhi_persistent_audit_evt_data` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` DISABLE KEYS */;
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (1,'message','eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJOS2UxSG91YVR2NnotRW1leXBYZnRSeFVGOXhlY0pTR1FGam1oRDY1N1hJIn0.eyJqdGkiOiI0OTVkNTgxMi04OTRhLTQzMWUtYTAxZS1hZDVjODViMzdhOTIiLCJleHAiOjE1NDEwNzA3NDcsIm5iZiI6MCwiaWF0IjoxNTQxMDY3MTQ3LCJpc3MiOiJodHRwczovL2tjbmV'),(1,'type','org.springframework.security.authentication.BadCredentialsException'),(2,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(3,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(4,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(5,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(6,'message','eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJOS2UxSG91YVR2NnotRW1leXBYZnRSeFVGOXhlY0pTR1FGam1oRDY1N1hJIn0.eyJqdGkiOiI4YWI3NjgzZS1lYzEzLTQ2ZmYtYjlhYi02Yzg3NmZkYjBhOTYiLCJleHAiOjE1NDExNTkxMzAsIm5iZiI6MCwiaWF0IjoxNTQxMTU1NTMwLCJpc3MiOiJodHRwczovL2tjbmV'),(6,'type','org.springframework.security.authentication.BadCredentialsException'),(7,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(8,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(9,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(10,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(11,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(12,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(13,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(14,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(15,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(16,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(17,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(18,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(19,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(20,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(21,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(22,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(23,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(24,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(25,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(26,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(27,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(28,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(29,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(30,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(31,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(32,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(33,'message','eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJOS2UxSG91YVR2NnotRW1leXBYZnRSeFVGOXhlY0pTR1FGam1oRDY1N1hJIn0.eyJqdGkiOiI1YTEwYWU4ZS01YzNhLTQwNzMtYjI2NC0wOTRhNDk5MDAxODAiLCJleHAiOjE1NDEzOTk4OTksIm5iZiI6MCwiaWF0IjoxNTQxMzk2Mjk5LCJpc3MiOiJodHRwczovL2tjbmV'),(33,'type','org.springframework.security.authentication.BadCredentialsException'),(34,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(35,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(36,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(37,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(38,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(39,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(40,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(41,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(42,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(43,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(44,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(45,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(46,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(47,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(48,'message','eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJOS2UxSG91YVR2NnotRW1leXBYZnRSeFVGOXhlY0pTR1FGam1oRDY1N1hJIn0.eyJqdGkiOiI5NDllZWFjNS0wYzAwLTRiZDgtYjlmYS0zOTQ3NjIzNWM1M2EiLCJleHAiOjE1NDE0MDIyNTIsIm5iZiI6MCwiaWF0IjoxNTQxMzk4NjUyLCJpc3MiOiJodHRwczovL2tjbmV'),(48,'type','org.springframework.security.authentication.BadCredentialsException'),(49,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>');
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user`
--

DROP TABLE IF EXISTS `jhi_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_user` (
  `id` varchar(100) NOT NULL,
  `login` varchar(50) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(254) DEFAULT NULL,
  `image_url` varchar(256) DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(6) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_user_login` (`login`),
  UNIQUE KEY `ux_user_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user`
--

LOCK TABLES `jhi_user` WRITE;
/*!40000 ALTER TABLE `jhi_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `jhi_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jhi_user_authority`
--

DROP TABLE IF EXISTS `jhi_user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_user_authority` (
  `user_id` varchar(100) NOT NULL,
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`authority_name`),
  KEY `fk_authority_name` (`authority_name`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`),
  CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_user_authority`
--

LOCK TABLES `jhi_user_authority` WRITE;
/*!40000 ALTER TABLE `jhi_user_authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `jhi_user_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logged_user`
--

DROP TABLE IF EXISTS `logged_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logged_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `rating` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `profession` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `blood_group` varchar(255) DEFAULT NULL,
  `profile_pic_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_logged_user_profile_pic_id` (`profile_pic_id`),
  CONSTRAINT `fk_logged_user_profile_pic_id` FOREIGN KEY (`profile_pic_id`) REFERENCES `media` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='LoggedUser entity. @author Muhammed Ruhail';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logged_user`
--

LOCK TABLES `logged_user` WRITE;
/*!40000 ALTER TABLE `logged_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `logged_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `media`
--

DROP TABLE IF EXISTS `media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `media` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `extension` varchar(255) DEFAULT NULL,
  `need_id` bigint(20) DEFAULT NULL,
  `help_id` bigint(20) DEFAULT NULL,
  `news_feed_id` bigint(20) DEFAULT NULL,
  `violation_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_media_need_id` (`need_id`),
  KEY `fk_media_help_id` (`help_id`),
  KEY `fk_media_news_feed_id` (`news_feed_id`),
  KEY `fk_media_violation_id` (`violation_id`),
  CONSTRAINT `fk_media_violation_id` FOREIGN KEY (`violation_id`) REFERENCES `violation` (`id`),
  CONSTRAINT `fk_media_help_id` FOREIGN KEY (`help_id`) REFERENCES `help` (`id`),
  CONSTRAINT `fk_media_need_id` FOREIGN KEY (`need_id`) REFERENCES `need` (`id`),
  CONSTRAINT `fk_media_news_feed_id` FOREIGN KEY (`news_feed_id`) REFERENCES `news_feed` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='Media entity. @author Dheeraj das.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media`
--

LOCK TABLES `media` WRITE;
/*!40000 ALTER TABLE `media` DISABLE KEYS */;
INSERT INTO `media` VALUES (1,'cat.jpg','src/main/resources/static/uploadedfiles/cat.jpg','image/jpeg',1,NULL,NULL,NULL),(2,'dog.jpg','src/main/resources/static/uploadedfiles/dog.jpg','image/jpeg',2,NULL,NULL,NULL),(3,'rose.jpeg','src/main/resources/static/uploadedfiles/rose.jpeg','image/jpeg',NULL,1,NULL,NULL),(4,'dog1.jpg','src/main/resources/static/uploadedfiles/dog1.jpg','image/jpeg',NULL,NULL,NULL,NULL),(5,'ev.png','src/main/resources/static/uploadedfiles/ev.png','image/png',3,NULL,NULL,NULL);
/*!40000 ALTER TABLE `media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `need`
--

DROP TABLE IF EXISTS `need`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `need` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `beneficiary_type` varchar(255) DEFAULT NULL,
  `jhi_date` datetime,
  `severity_id` bigint(20) DEFAULT NULL,
  `verification_team_id` bigint(20) DEFAULT NULL,
  `approval_status_id` bigint(20) DEFAULT NULL,
  `posted_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_need_severity_id` (`severity_id`),
  KEY `fk_need_verification_team_id` (`verification_team_id`),
  KEY `fk_need_approval_status_id` (`approval_status_id`),
  KEY `fk_need_posted_user_id` (`posted_user_id`),
  CONSTRAINT `fk_need_posted_user_id` FOREIGN KEY (`posted_user_id`) REFERENCES `logged_user` (`id`),
  CONSTRAINT `fk_need_approval_status_id` FOREIGN KEY (`approval_status_id`) REFERENCES `approval_status` (`id`),
  CONSTRAINT `fk_need_severity_id` FOREIGN KEY (`severity_id`) REFERENCES `severity` (`id`),
  CONSTRAINT `fk_need_verification_team_id` FOREIGN KEY (`verification_team_id`) REFERENCES `verification_team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='Need entity @author Balagopal v';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `need`
--

LOCK TABLES `need` WRITE;
/*!40000 ALTER TABLE `need` DISABLE KEYS */;
INSERT INTO `need` VALUES (1,'financial help','you','2018-11-02 19:04:42',NULL,NULL,2,NULL),(2,'education help','someone else','2018-11-02 19:10:51',NULL,NULL,1,NULL),(3,'hffffff','you','2018-11-03 04:25:47',NULL,NULL,1,NULL);
/*!40000 ALTER TABLE `need` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `need_categories`
--

DROP TABLE IF EXISTS `need_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `need_categories` (
  `categories_id` bigint(20) NOT NULL,
  `needs_id` bigint(20) NOT NULL,
  PRIMARY KEY (`needs_id`,`categories_id`),
  KEY `fk_need_categories_categories_id` (`categories_id`),
  CONSTRAINT `fk_need_categories_categories_id` FOREIGN KEY (`categories_id`) REFERENCES `category` (`id`),
  CONSTRAINT `fk_need_categories_needs_id` FOREIGN KEY (`needs_id`) REFERENCES `need` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `need_categories`
--

LOCK TABLES `need_categories` WRITE;
/*!40000 ALTER TABLE `need_categories` DISABLE KEYS */;
INSERT INTO `need_categories` VALUES (1,3),(2,2);
/*!40000 ALTER TABLE `need_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news_feed`
--

DROP TABLE IF EXISTS `news_feed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news_feed` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `jhi_date` datetime,
  `logged_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_news_feed_logged_user_id` (`logged_user_id`),
  CONSTRAINT `fk_news_feed_logged_user_id` FOREIGN KEY (`logged_user_id`) REFERENCES `logged_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='NewsFeed entity @author Deepthi E';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news_feed`
--

LOCK TABLES `news_feed` WRITE;
/*!40000 ALTER TABLE `news_feed` DISABLE KEYS */;
/*!40000 ALTER TABLE `news_feed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply`
--

DROP TABLE IF EXISTS `reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `jhi_date` datetime,
  `comment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reply_comment_id` (`comment_id`),
  CONSTRAINT `fk_reply_comment_id` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Reply  entity @author  Deepthi E';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
/*!40000 ALTER TABLE `reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `severity`
--

DROP TABLE IF EXISTS `severity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `severity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `severity_level` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='@Author Anjali';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `severity`
--

LOCK TABLES `severity` WRITE;
/*!40000 ALTER TABLE `severity` DISABLE KEYS */;
/*!40000 ALTER TABLE `severity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_check`
--

DROP TABLE IF EXISTS `user_check`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_check` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `vote_type` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `checked_need_id` bigint(20) DEFAULT NULL,
  `checked_user_id` bigint(20) DEFAULT NULL,
  `comment_id` bigint(20) DEFAULT NULL,
  `reply_id` bigint(20) DEFAULT NULL,
  `news_feed_id` bigint(20) DEFAULT NULL,
  `violation_id` bigint(20) DEFAULT NULL,
  `checked_help_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_check_checked_need_id` (`checked_need_id`),
  KEY `fk_user_check_checked_user_id` (`checked_user_id`),
  KEY `fk_user_check_comment_id` (`comment_id`),
  KEY `fk_user_check_reply_id` (`reply_id`),
  KEY `fk_user_check_news_feed_id` (`news_feed_id`),
  KEY `fk_user_check_violation_id` (`violation_id`),
  KEY `fk_user_check_checked_help_id` (`checked_help_id`),
  CONSTRAINT `fk_user_check_checked_help_id` FOREIGN KEY (`checked_help_id`) REFERENCES `help` (`id`),
  CONSTRAINT `fk_user_check_checked_need_id` FOREIGN KEY (`checked_need_id`) REFERENCES `need` (`id`),
  CONSTRAINT `fk_user_check_checked_user_id` FOREIGN KEY (`checked_user_id`) REFERENCES `logged_user` (`id`),
  CONSTRAINT `fk_user_check_comment_id` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `fk_user_check_news_feed_id` FOREIGN KEY (`news_feed_id`) REFERENCES `news_feed` (`id`),
  CONSTRAINT `fk_user_check_reply_id` FOREIGN KEY (`reply_id`) REFERENCES `reply` (`id`),
  CONSTRAINT `fk_user_check_violation_id` FOREIGN KEY (`violation_id`) REFERENCES `violation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_check`
--

LOCK TABLES `user_check` WRITE;
/*!40000 ALTER TABLE `user_check` DISABLE KEYS */;
INSERT INTO `user_check` VALUES (7,'postive','genuiness',1,NULL,NULL,NULL,NULL,NULL,NULL),(8,'postive','genuiness',2,NULL,NULL,NULL,NULL,NULL,NULL),(9,'negative','genuiness',3,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user_check` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verification_team`
--

DROP TABLE IF EXISTS `verification_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `verification_team` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `approval_level` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='@Author Sarangi Balu';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verification_team`
--

LOCK TABLES `verification_team` WRITE;
/*!40000 ALTER TABLE `verification_team` DISABLE KEYS */;
/*!40000 ALTER TABLE `verification_team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verification_team_approving_users`
--

DROP TABLE IF EXISTS `verification_team_approving_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `verification_team_approving_users` (
  `approving_users_id` bigint(20) NOT NULL,
  `verification_teams_id` bigint(20) NOT NULL,
  PRIMARY KEY (`verification_teams_id`,`approving_users_id`),
  KEY `fk_verification_team_approving_users_approving_users_id` (`approving_users_id`),
  CONSTRAINT `fk_verification_team_approving_users_approving_users_id` FOREIGN KEY (`approving_users_id`) REFERENCES `logged_user` (`id`),
  CONSTRAINT `fk_verification_team_approving_users_verification_teams_id` FOREIGN KEY (`verification_teams_id`) REFERENCES `verification_team` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verification_team_approving_users`
--

LOCK TABLES `verification_team_approving_users` WRITE;
/*!40000 ALTER TABLE `verification_team_approving_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `verification_team_approving_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `violation`
--

DROP TABLE IF EXISTS `violation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `violation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `is_anonymous` bit(1) DEFAULT NULL,
  `jhi_date` datetime,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='Violation entity @author Deepthi E';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `violation`
--

LOCK TABLES `violation` WRITE;
/*!40000 ALTER TABLE `violation` DISABLE KEYS */;
INSERT INTO `violation` VALUES (1,'traffic break','','2018-11-05 03:04:33'),(2,'violation','\0','2018-10-11 03:04:33'),(4,'traffic break','','2018-11-05 03:04:33'),(5,'causing pollution','','2018-11-11 07:04:33'),(6,'pollution','','2018-11-11 08:04:33');
/*!40000 ALTER TABLE `violation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-05 14:12:23
