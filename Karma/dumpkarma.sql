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
  `registered_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_address_registered_user_id` (`registered_user_id`),
  CONSTRAINT `fk_address_registered_user_id` FOREIGN KEY (`registered_user_id`) REFERENCES `registered_user` (`id`)
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
INSERT INTO `approval_status` VALUES (1,'pending'),(2,'approved'),(3,'declined'),(4,'completed'),(5,'incompleted'),(6,'closed');
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
  `jhi_date` datetime DEFAULT NULL,
  `need_id` bigint(20) DEFAULT NULL,
  `help_id` bigint(20) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `commented_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_need_id` (`need_id`),
  KEY `fk_comment_help_id` (`help_id`),
  KEY `fk_comment_post_id` (`post_id`),
  KEY `fk_comment_commented_user_id` (`commented_user_id`),
  CONSTRAINT `fk_comment_commented_user_id` FOREIGN KEY (`commented_user_id`) REFERENCES `registered_user` (`id`),
  CONSTRAINT `fk_comment_help_id` FOREIGN KEY (`help_id`) REFERENCES `help` (`id`),
  CONSTRAINT `fk_comment_need_id` FOREIGN KEY (`need_id`) REFERENCES `need` (`id`),
  CONSTRAINT `fk_comment_post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='Comment entity @author Deepthi E';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'super need polich',NULL,3,NULL,NULL,1),(2,'which place',NULL,3,NULL,NULL,2),(3,'sanilee',NULL,3,NULL,NULL,2),(4,'nice one',NULL,6,NULL,NULL,NULL),(5,'niceeee',NULL,11,NULL,NULL,NULL),(6,'niceeee',NULL,NULL,1,NULL,NULL),(7,'kooy',NULL,NULL,1,NULL,NULL),(8,'heyyy',NULL,NULL,NULL,3,NULL),(9,'hiiiiii',NULL,NULL,NULL,4,NULL);
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
INSERT INTO `databasechangelog` VALUES ('00000000000001','jhipster','config/liquibase/changelog/00000000000000_initial_schema.xml','2018-11-21 09:34:39',1,'EXECUTED','7:3628d21006c83bc696d51a75927e556f','createTable tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableName=jhi_user_authority; addForeignKeyConstraint baseTableName=jhi_user_authority, constraintName=fk_authority_name, ...','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103744-1','jhipster','config/liquibase/changelog/20181118103744_added_entity_RegisteredUser.xml','2018-11-21 09:34:40',2,'EXECUTED','7:abcd5e33dbac58ad1247330673de8a87','createTable tableName=registered_user','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103745-1','jhipster','config/liquibase/changelog/20181118103745_added_entity_ApprovalStatus.xml','2018-11-21 09:34:41',3,'EXECUTED','7:77e4e36a20a301883afa1b3daeef43ac','createTable tableName=approval_status','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103746-1','jhipster','config/liquibase/changelog/20181118103746_added_entity_Address.xml','2018-11-21 09:34:41',4,'EXECUTED','7:823c6d9999a93aa82802059fbcb0fdc2','createTable tableName=address','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103747-1','jhipster','config/liquibase/changelog/20181118103747_added_entity_Media.xml','2018-11-21 09:34:41',5,'EXECUTED','7:3830e2326272a81ea6bad4c2c731a07d','createTable tableName=media','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103748-1','jhipster','config/liquibase/changelog/20181118103748_added_entity_Category.xml','2018-11-21 09:34:41',6,'EXECUTED','7:8d861f5caade3b58c565a1217a483064','createTable tableName=category','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103749-1','jhipster','config/liquibase/changelog/20181118103749_added_entity_UserCheck.xml','2018-11-21 09:34:42',7,'EXECUTED','7:68009601e60ef16b1c7bb2d470f18920','createTable tableName=user_check','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103750-1','jhipster','config/liquibase/changelog/20181118103750_added_entity_Need.xml','2018-11-21 09:34:43',8,'EXECUTED','7:f358ea034523b8e13c6fb77f9b069664','createTable tableName=need; dropDefaultValue columnName=jhi_date, tableName=need; createTable tableName=need_categories; addPrimaryKey tableName=need_categories','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103751-1','jhipster','config/liquibase/changelog/20181118103751_added_entity_Help.xml','2018-11-21 09:34:43',9,'EXECUTED','7:a15e32b50dfc7929c58c28a679427944','createTable tableName=help; dropDefaultValue columnName=jhi_time, tableName=help','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103752-1','jhipster','config/liquibase/changelog/20181118103752_added_entity_Severity.xml','2018-11-21 09:34:43',10,'EXECUTED','7:70dd6b820d10d352231c5135c22ffbc9','createTable tableName=severity','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103753-1','jhipster','config/liquibase/changelog/20181118103753_added_entity_VerificationTeam.xml','2018-11-21 09:34:44',11,'EXECUTED','7:29efce71a09132ec3db8f9d7a6f01877','createTable tableName=verification_team; createTable tableName=verification_team_approving_users; addPrimaryKey tableName=verification_team_approving_users','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103754-1','jhipster','config/liquibase/changelog/20181118103754_added_entity_Post.xml','2018-11-21 09:34:44',12,'EXECUTED','7:3c47892ae96b63411abc6e05e90b2ed0','createTable tableName=post; dropDefaultValue columnName=jhi_date, tableName=post','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103755-1','jhipster','config/liquibase/changelog/20181118103755_added_entity_Comment.xml','2018-11-21 09:34:45',13,'EXECUTED','7:8519452e4dc94e2f6ca99ecb624ff4d9','createTable tableName=comment; dropDefaultValue columnName=jhi_date, tableName=comment','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103756-1','jhipster','config/liquibase/changelog/20181118103756_added_entity_Reply.xml','2018-11-21 09:34:45',14,'EXECUTED','7:a684e887b20d0f9d2bd6897b9154d38d','createTable tableName=reply; dropDefaultValue columnName=jhi_date, tableName=reply','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103757-1','jhipster','config/liquibase/changelog/20181118103757_added_entity_Feed.xml','2018-11-21 09:34:46',15,'EXECUTED','7:c7b0af01190c023056b379be48656a1b','createTable tableName=feed; dropDefaultValue columnName=jhi_date, tableName=feed','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103744-2','jhipster','config/liquibase/changelog/20181118103744_added_entity_constraints_RegisteredUser.xml','2018-11-21 09:34:46',16,'EXECUTED','7:de0d477e14f741b8990da1a0258f3078','addForeignKeyConstraint baseTableName=registered_user, constraintName=fk_registered_user_profile_pic_id, referencedTableName=media','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103746-2','jhipster','config/liquibase/changelog/20181118103746_added_entity_constraints_Address.xml','2018-11-21 09:34:47',17,'EXECUTED','7:23f6332ca500696e2407a8e70e77495d','addForeignKeyConstraint baseTableName=address, constraintName=fk_address_registered_user_id, referencedTableName=registered_user','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103747-2','jhipster','config/liquibase/changelog/20181118103747_added_entity_constraints_Media.xml','2018-11-21 09:34:48',18,'EXECUTED','7:243af96bab9ae57804972af87a7de261','addForeignKeyConstraint baseTableName=media, constraintName=fk_media_need_id, referencedTableName=need; addForeignKeyConstraint baseTableName=media, constraintName=fk_media_help_id, referencedTableName=help; addForeignKeyConstraint baseTableName=m...','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103749-2','jhipster','config/liquibase/changelog/20181118103749_added_entity_constraints_UserCheck.xml','2018-11-21 09:34:56',19,'EXECUTED','7:faf32909a48ddef23d52a4cdac4f47c0','addForeignKeyConstraint baseTableName=user_check, constraintName=fk_user_check_checked_need_id, referencedTableName=need; addForeignKeyConstraint baseTableName=user_check, constraintName=fk_user_check_checked_user_id, referencedTableName=registere...','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103750-2','jhipster','config/liquibase/changelog/20181118103750_added_entity_constraints_Need.xml','2018-11-21 09:34:59',20,'EXECUTED','7:c02121a6f2cdc341ed1257f53e501749','addForeignKeyConstraint baseTableName=need, constraintName=fk_need_severity_id, referencedTableName=severity; addForeignKeyConstraint baseTableName=need, constraintName=fk_need_verification_team_id, referencedTableName=verification_team; addForeig...','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103751-2','jhipster','config/liquibase/changelog/20181118103751_added_entity_constraints_Help.xml','2018-11-21 09:35:00',21,'EXECUTED','7:bb437944ae4f9b6375469120ee543e8d','addForeignKeyConstraint baseTableName=help, constraintName=fk_help_approval_status_id, referencedTableName=approval_status; addForeignKeyConstraint baseTableName=help, constraintName=fk_help_provided_user_id, referencedTableName=registered_user; a...','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103753-2','jhipster','config/liquibase/changelog/20181118103753_added_entity_constraints_VerificationTeam.xml','2018-11-21 09:35:01',22,'EXECUTED','7:5a1e1b3b1971b4e88305c514e2c3bd41','addForeignKeyConstraint baseTableName=verification_team_approving_users, constraintName=fk_verification_team_approving_users_verification_teams_id, referencedTableName=verification_team; addForeignKeyConstraint baseTableName=verification_team_appr...','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103754-2','jhipster','config/liquibase/changelog/20181118103754_added_entity_constraints_Post.xml','2018-11-21 09:35:01',23,'EXECUTED','7:12aebc8e9c04828f94f1d7023f819496','addForeignKeyConstraint baseTableName=post, constraintName=fk_post_registered_user_id, referencedTableName=registered_user','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103755-2','jhipster','config/liquibase/changelog/20181118103755_added_entity_constraints_Comment.xml','2018-11-21 09:35:03',24,'EXECUTED','7:fa978784fa8a8ab685b3ad3b7726867f','addForeignKeyConstraint baseTableName=comment, constraintName=fk_comment_need_id, referencedTableName=need; addForeignKeyConstraint baseTableName=comment, constraintName=fk_comment_help_id, referencedTableName=help; addForeignKeyConstraint baseTab...','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103756-2','jhipster','config/liquibase/changelog/20181118103756_added_entity_constraints_Reply.xml','2018-11-21 09:35:04',25,'EXECUTED','7:3ad14f0df9d6398d7fd12bd1028a09e1','addForeignKeyConstraint baseTableName=reply, constraintName=fk_reply_comment_id, referencedTableName=comment; addForeignKeyConstraint baseTableName=reply, constraintName=fk_reply_replied_user_id, referencedTableName=registered_user','',NULL,'3.5.4',NULL,NULL,'2773076263'),('20181118103757-2','jhipster','config/liquibase/changelog/20181118103757_added_entity_constraints_Feed.xml','2018-11-21 09:35:04',26,'EXECUTED','7:5e91fdc247fd4c9514c2c0fc65243ae3','addForeignKeyConstraint baseTableName=feed, constraintName=fk_feed_registered_user_id, referencedTableName=registered_user','',NULL,'3.5.4',NULL,NULL,'2773076263');
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
-- Table structure for table `feed`
--

DROP TABLE IF EXISTS `feed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feed` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `jhi_type` varchar(255) DEFAULT NULL,
  `jhi_date` datetime DEFAULT NULL,
  `reference_id` bigint(20) DEFAULT NULL,
  `registered_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_feed_registered_user_id` (`registered_user_id`),
  CONSTRAINT `fk_feed_registered_user_id` FOREIGN KEY (`registered_user_id`) REFERENCES `registered_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='Feed entity @author Deepthi E';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed`
--

LOCK TABLES `feed` WRITE;
/*!40000 ALTER TABLE `feed` DISABLE KEYS */;
INSERT INTO `feed` VALUES (1,'User Posted a new Need','NeedPost',NULL,35,NULL),(2,'User Helped a need','HelpPostAfterCompletion',NULL,8,1),(3,'User commented on the Help','HelpPostComment',NULL,6,NULL),(4,'User commented on the Help','HelpPostComment',NULL,5,1),(5,'User commented on the Help','HelpPostComment',NULL,4,2),(6,'User commented on the Help','HelpPostComment',NULL,3,3),(7,NULL,'NeedPostComment',NULL,26,3),(8,NULL,'NeedIsFake',NULL,37,2),(9,'User marked the need as Genuine','NeedIsGenuine',NULL,37,3);
/*!40000 ALTER TABLE `feed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `help`
--

DROP TABLE IF EXISTS `help`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `help` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `jhi_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `approval_status_id` bigint(20) DEFAULT NULL,
  `provided_user_id` bigint(20) DEFAULT NULL,
  `fulfilled_need_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_help_approval_status_id` (`approval_status_id`),
  KEY `fk_help_provided_user_id` (`provided_user_id`),
  KEY `fk_help_fulfilled_need_id` (`fulfilled_need_id`),
  CONSTRAINT `fk_help_approval_status_id` FOREIGN KEY (`approval_status_id`) REFERENCES `approval_status` (`id`),
  CONSTRAINT `fk_help_fulfilled_need_id` FOREIGN KEY (`fulfilled_need_id`) REFERENCES `need` (`id`),
  CONSTRAINT `fk_help_provided_user_id` FOREIGN KEY (`provided_user_id`) REFERENCES `registered_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='Service entity @Author Sooraj Pn';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `help`
--

LOCK TABLES `help` WRITE;
/*!40000 ALTER TABLE `help` DISABLE KEYS */;
INSERT INTO `help` VALUES (1,'2018-11-05 14:48:12','iam ready to help',4,NULL,2),(2,'2018-11-05 14:48:12','iam interested to help',4,2,3),(3,'2018-11-22 09:04:10','jlkjlkjlkj',4,NULL,2),(4,'2018-11-22 10:52:23','iam ready to help',4,NULL,15),(5,'2018-11-22 11:25:21','jhgjkghjghhj',4,NULL,2),(6,'2018-11-22 11:50:21','hhjgjhgj',4,NULL,10),(7,'2018-11-22 15:14:19','vjgjh',5,NULL,2),(8,'2018-11-22 15:55:50','help u',5,NULL,3);
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
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_event`
--

LOCK TABLES `jhi_persistent_audit_event` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_event` DISABLE KEYS */;
INSERT INTO `jhi_persistent_audit_event` VALUES (1,'access-token','2018-11-21 01:00:41','AUTHENTICATION_FAILURE'),(2,'admin','2018-11-21 01:01:24','AUTHENTICATION_SUCCESS'),(3,'admin','2018-11-21 01:02:52','AUTHENTICATION_SUCCESS'),(4,'admin','2018-11-21 01:05:08','AUTHENTICATION_SUCCESS'),(5,'admin','2018-11-21 01:15:06','AUTHENTICATION_SUCCESS'),(6,'admin','2018-11-21 01:18:55','AUTHENTICATION_SUCCESS'),(7,'admin','2018-11-21 01:34:19','AUTHENTICATION_SUCCESS'),(8,'admin','2018-11-21 01:39:02','AUTHENTICATION_SUCCESS'),(9,'admin','2018-11-21 01:41:09','AUTHENTICATION_SUCCESS'),(10,'admin','2018-11-21 01:41:18','AUTHENTICATION_SUCCESS'),(11,'admin','2018-11-21 01:44:48','AUTHENTICATION_SUCCESS'),(12,'admin','2018-11-21 01:45:08','AUTHENTICATION_SUCCESS'),(13,'admin','2018-11-21 01:46:23','AUTHENTICATION_SUCCESS'),(14,'admin','2018-11-21 01:48:13','AUTHENTICATION_SUCCESS'),(15,'admin','2018-11-21 01:51:45','AUTHENTICATION_SUCCESS'),(16,'admin','2018-11-21 01:55:09','AUTHENTICATION_SUCCESS'),(17,'admin','2018-11-21 01:56:24','AUTHENTICATION_SUCCESS'),(18,'admin','2018-11-21 01:57:03','AUTHENTICATION_SUCCESS'),(19,'admin','2018-11-21 02:43:39','AUTHENTICATION_SUCCESS'),(20,'admin','2018-11-21 02:43:48','AUTHENTICATION_SUCCESS'),(21,'admin','2018-11-21 02:45:08','AUTHENTICATION_SUCCESS'),(22,'admin','2018-11-21 02:47:06','AUTHENTICATION_SUCCESS'),(23,'admin','2018-11-21 02:47:52','AUTHENTICATION_SUCCESS'),(24,'admin','2018-11-21 02:48:28','AUTHENTICATION_SUCCESS'),(25,'admin','2018-11-21 02:48:49','AUTHENTICATION_SUCCESS'),(26,'admin','2018-11-21 02:50:29','AUTHENTICATION_SUCCESS'),(27,'admin','2018-11-21 02:51:08','AUTHENTICATION_SUCCESS'),(28,'admin','2018-11-21 02:53:04','AUTHENTICATION_SUCCESS'),(29,'admin','2018-11-21 02:54:19','AUTHENTICATION_SUCCESS'),(30,'admin','2018-11-21 02:55:35','AUTHENTICATION_SUCCESS'),(31,'access-token','2018-11-21 03:21:58','AUTHENTICATION_FAILURE'),(32,'admin','2018-11-21 03:22:29','AUTHENTICATION_SUCCESS'),(33,'admin','2018-11-21 03:40:02','AUTHENTICATION_SUCCESS'),(34,'admin','2018-11-21 03:48:17','AUTHENTICATION_SUCCESS'),(35,'admin','2018-11-21 04:00:51','AUTHENTICATION_SUCCESS'),(36,'admin','2018-11-21 04:03:49','AUTHENTICATION_SUCCESS'),(37,'admin','2018-11-21 04:05:44','AUTHENTICATION_SUCCESS'),(38,'admin','2018-11-21 04:08:18','AUTHENTICATION_SUCCESS'),(39,'admin','2018-11-21 04:08:53','AUTHENTICATION_SUCCESS'),(40,'admin','2018-11-21 04:10:16','AUTHENTICATION_SUCCESS'),(41,'admin','2018-11-21 04:10:33','AUTHENTICATION_SUCCESS'),(42,'admin','2018-11-21 04:12:39','AUTHENTICATION_SUCCESS'),(43,'admin','2018-11-21 04:17:30','AUTHENTICATION_SUCCESS'),(44,'admin','2018-11-21 04:17:52','AUTHENTICATION_SUCCESS'),(45,'admin','2018-11-21 04:20:34','AUTHENTICATION_SUCCESS'),(46,'admin','2018-11-21 04:25:19','AUTHENTICATION_SUCCESS'),(47,'admin','2018-11-21 04:25:25','AUTHENTICATION_SUCCESS'),(48,'admin','2018-11-21 04:36:30','AUTHENTICATION_SUCCESS'),(49,'admin','2018-11-21 04:37:31','AUTHENTICATION_SUCCESS'),(50,'admin','2018-11-21 04:39:39','AUTHENTICATION_SUCCESS'),(51,'admin','2018-11-21 04:40:30','AUTHENTICATION_SUCCESS'),(52,'admin','2018-11-21 04:41:59','AUTHENTICATION_SUCCESS'),(53,'admin','2018-11-21 04:42:23','AUTHENTICATION_SUCCESS'),(54,'admin','2018-11-21 04:49:01','AUTHENTICATION_SUCCESS'),(55,'admin','2018-11-21 04:55:02','AUTHENTICATION_SUCCESS'),(56,'admin','2018-11-21 04:55:52','AUTHENTICATION_SUCCESS'),(57,'access-token','2018-11-21 05:13:59','AUTHENTICATION_FAILURE'),(58,'admin','2018-11-21 05:15:23','AUTHENTICATION_SUCCESS'),(59,'admin','2018-11-21 05:16:13','AUTHENTICATION_SUCCESS'),(60,'admin','2018-11-21 05:24:46','AUTHENTICATION_SUCCESS'),(61,'admin','2018-11-21 05:24:53','AUTHENTICATION_SUCCESS'),(62,'admin','2018-11-21 05:26:01','AUTHENTICATION_SUCCESS'),(63,'admin','2018-11-21 05:27:16','AUTHENTICATION_SUCCESS'),(64,'access-token','2018-11-21 11:19:30','AUTHENTICATION_FAILURE'),(65,'admin','2018-11-21 11:23:35','AUTHENTICATION_SUCCESS'),(66,'admin','2018-11-21 11:32:55','AUTHENTICATION_SUCCESS'),(67,'admin','2018-11-21 11:33:47','AUTHENTICATION_SUCCESS'),(68,'admin','2018-11-21 11:34:04','AUTHENTICATION_SUCCESS'),(69,'admin','2018-11-21 11:36:08','AUTHENTICATION_SUCCESS'),(70,'admin','2018-11-21 11:39:47','AUTHENTICATION_SUCCESS'),(71,'admin','2018-11-21 11:40:26','AUTHENTICATION_SUCCESS'),(72,'admin','2018-11-21 11:40:35','AUTHENTICATION_SUCCESS'),(73,'admin','2018-11-21 11:40:42','AUTHENTICATION_SUCCESS'),(74,'admin','2018-11-21 11:42:37','AUTHENTICATION_SUCCESS'),(75,'admin','2018-11-21 11:42:48','AUTHENTICATION_SUCCESS'),(76,'admin','2018-11-21 11:55:49','AUTHENTICATION_SUCCESS'),(77,'admin','2018-11-21 11:56:40','AUTHENTICATION_SUCCESS'),(78,'admin','2018-11-21 11:56:48','AUTHENTICATION_SUCCESS'),(79,'admin','2018-11-21 11:58:24','AUTHENTICATION_SUCCESS'),(80,'admin','2018-11-21 12:06:56','AUTHENTICATION_SUCCESS'),(81,'admin','2018-11-21 12:07:13','AUTHENTICATION_SUCCESS'),(82,'access-token','2018-11-21 12:30:25','AUTHENTICATION_FAILURE'),(83,'admin','2018-11-21 12:31:09','AUTHENTICATION_SUCCESS'),(84,'access-token','2018-11-21 12:37:13','AUTHENTICATION_FAILURE'),(85,'admin','2018-11-21 12:37:52','AUTHENTICATION_SUCCESS'),(86,'admin','2018-11-21 12:38:15','AUTHENTICATION_SUCCESS'),(87,'access-token','2018-11-21 12:41:03','AUTHENTICATION_FAILURE'),(88,'admin','2018-11-21 12:41:21','AUTHENTICATION_SUCCESS'),(89,'access-token','2018-11-21 12:42:00','AUTHENTICATION_FAILURE'),(90,'admin','2018-11-21 12:42:22','AUTHENTICATION_SUCCESS'),(91,'access-token','2018-11-21 12:42:37','AUTHENTICATION_FAILURE'),(92,'admin','2018-11-21 12:43:23','AUTHENTICATION_SUCCESS'),(93,'admin','2018-11-21 12:56:03','AUTHENTICATION_SUCCESS'),(94,'admin','2018-11-21 12:56:31','AUTHENTICATION_SUCCESS'),(95,'admin','2018-11-21 12:57:43','AUTHENTICATION_SUCCESS'),(96,'access-token','2018-11-21 12:58:27','AUTHENTICATION_FAILURE'),(97,'admin','2018-11-21 12:58:43','AUTHENTICATION_SUCCESS'),(98,'admin','2018-11-21 13:06:21','AUTHENTICATION_SUCCESS'),(99,'admin','2018-11-21 13:12:42','AUTHENTICATION_SUCCESS'),(100,'access-token','2018-11-21 13:13:03','AUTHENTICATION_FAILURE'),(101,'access-token','2018-11-21 13:13:15','AUTHENTICATION_FAILURE'),(102,'admin','2018-11-21 13:13:37','AUTHENTICATION_SUCCESS');
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
INSERT INTO `jhi_persistent_audit_evt_data` VALUES (1,'message','eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJOS2UxSG91YVR2NnotRW1leXBYZnRSeFVGOXhlY0pTR1FGam1oRDY1N1hJIn0.eyJqdGkiOiJhMDMxYzg0OS1iOTNmLTQxOTItYjYxMi02ZmUxZDE1ZGYwOTgiLCJleHAiOjE1NDIwMTAwNDMsIm5iZiI6MCwiaWF0IjoxNTQyMDA2NDQzLCJpc3MiOiJodHRwczovL2tjbmV'),(1,'type','org.springframework.security.authentication.BadCredentialsException'),(2,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(3,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(4,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(5,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(6,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(7,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(8,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(9,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(10,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(11,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(12,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(13,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(14,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(15,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(16,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(17,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(18,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(19,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(20,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(21,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(22,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(23,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(24,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(25,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(26,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(27,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(28,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(29,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(30,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(31,'message','eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJOS2UxSG91YVR2NnotRW1leXBYZnRSeFVGOXhlY0pTR1FGam1oRDY1N1hJIn0.eyJqdGkiOiJiNzQ3NjcwZi00OGU5LTRmM2UtYmMzYy1iNmJmNzI3MzE4NmQiLCJleHAiOjE1NDI3ODgwNjMsIm5iZiI6MCwiaWF0IjoxNTQyNzg4MDAzLCJpc3MiOiJodHRwczovL2tjbmV'),(31,'type','org.springframework.security.authentication.BadCredentialsException'),(32,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(33,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(34,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(35,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(36,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(37,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(38,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(39,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(40,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(41,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(42,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(43,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(44,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(45,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(46,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(47,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(48,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(49,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(50,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(51,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(52,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(53,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(54,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(55,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(56,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(57,'message','eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJOS2UxSG91YVR2NnotRW1leXBYZnRSeFVGOXhlY0pTR1FGam1oRDY1N1hJIn0.eyJqdGkiOiIzOTYxMTE0Ny1mODFjLTRjNGQtOTc5My1kOTE3N2M1MTY1YjEiLCJleHAiOjE1NDI3OTUwMTEsIm5iZiI6MCwiaWF0IjoxNTQyNzk0OTUxLCJpc3MiOiJodHRwczovL2tjbmV'),(57,'type','org.springframework.security.authentication.BadCredentialsException'),(58,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(59,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(60,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(61,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(62,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(63,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(64,'message','eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJOS2UxSG91YVR2NnotRW1leXBYZnRSeFVGOXhlY0pTR1FGam1oRDY1N1hJIn0.eyJqdGkiOiJiYmY4YjI5NS1kYmMyLTQ3MGUtOGU4Ny04OTA2Y2Y0ODMzMzAiLCJleHAiOjE1NDI4MTg4MzIsIm5iZiI6MCwiaWF0IjoxNTQyODE4NzcyLCJpc3MiOiJodHRwczovL2tjbmV'),(64,'type','org.springframework.security.authentication.BadCredentialsException'),(65,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(66,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(67,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(68,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(69,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(70,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(71,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(72,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(73,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(74,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(75,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(76,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(77,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(78,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(79,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(80,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(81,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(82,'message','eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJOS2UxSG91YVR2NnotRW1leXBYZnRSeFVGOXhlY0pTR1FGam1oRDY1N1hJIn0.eyJqdGkiOiI5ZmRjOWY1OS01MzNhLTRiYjQtYmUzOC1iNDk4ZjExZDAzODAiLCJleHAiOjE1NDI4MTkyNjQsIm5iZiI6MCwiaWF0IjoxNTQyODE5MjA0LCJpc3MiOiJodHRwczovL2tjbmV'),(82,'type','org.springframework.security.authentication.BadCredentialsException'),(83,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(84,'message','eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJOS2UxSG91YVR2NnotRW1leXBYZnRSeFVGOXhlY0pTR1FGam1oRDY1N1hJIn0.eyJqdGkiOiI5ZmRjOWY1OS01MzNhLTRiYjQtYmUzOC1iNDk4ZjExZDAzODAiLCJleHAiOjE1NDI4MTkyNjQsIm5iZiI6MCwiaWF0IjoxNTQyODE5MjA0LCJpc3MiOiJodHRwczovL2tjbmV'),(84,'type','org.springframework.security.authentication.BadCredentialsException'),(85,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(86,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(87,'message','eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJOS2UxSG91YVR2NnotRW1leXBYZnRSeFVGOXhlY0pTR1FGam1oRDY1N1hJIn0.eyJqdGkiOiJiZWFlYTNkZi0yOTIyLTQxMzAtOTY0OS1jYWIyNmU4NDI5ZDciLCJleHAiOjE1NDI4MjMzMTIsIm5iZiI6MCwiaWF0IjoxNTQyODIzMjUyLCJpc3MiOiJodHRwczovL2tjbmV'),(87,'type','org.springframework.security.authentication.BadCredentialsException'),(88,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(89,'message','eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJOS2UxSG91YVR2NnotRW1leXBYZnRSeFVGOXhlY0pTR1FGam1oRDY1N1hJIn0.eyJqdGkiOiI4MWI5MTYwMS1jMjg4LTRkNWMtOTA0OC1lYzI1M2MwZmRlODMiLCJleHAiOjE1NDI4MjM3MjAsIm5iZiI6MCwiaWF0IjoxNTQyODIzNjYwLCJpc3MiOiJodHRwczovL2tjbmV'),(89,'type','org.springframework.security.authentication.BadCredentialsException'),(90,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(91,'message','eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJOS2UxSG91YVR2NnotRW1leXBYZnRSeFVGOXhlY0pTR1FGam1oRDY1N1hJIn0.eyJqdGkiOiJiZWFlYTNkZi0yOTIyLTQxMzAtOTY0OS1jYWIyNmU4NDI5ZDciLCJleHAiOjE1NDI4MjMzMTIsIm5iZiI6MCwiaWF0IjoxNTQyODIzMjUyLCJpc3MiOiJodHRwczovL2tjbmV'),(91,'type','org.springframework.security.authentication.BadCredentialsException'),(92,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(93,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(94,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(95,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(96,'message','eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJOS2UxSG91YVR2NnotRW1leXBYZnRSeFVGOXhlY0pTR1FGam1oRDY1N1hJIn0.eyJqdGkiOiJiZWFlYTNkZi0yOTIyLTQxMzAtOTY0OS1jYWIyNmU4NDI5ZDciLCJleHAiOjE1NDI4MjMzMTIsIm5iZiI6MCwiaWF0IjoxNTQyODIzMjUyLCJpc3MiOiJodHRwczovL2tjbmV'),(96,'type','org.springframework.security.authentication.BadCredentialsException'),(97,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(98,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(99,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>'),(100,'message','eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJOS2UxSG91YVR2NnotRW1leXBYZnRSeFVGOXhlY0pTR1FGam1oRDY1N1hJIn0.eyJqdGkiOiJiMDEzYTBjYS1iMmFjLTRjYjQtYWM3MC02YjlkMDQwNDQzMDQiLCJleHAiOjE1NDI4MjQ5NzcsIm5iZiI6MCwiaWF0IjoxNTQyODI0OTE3LCJpc3MiOiJodHRwczovL2tjbmV'),(100,'type','org.springframework.security.authentication.BadCredentialsException'),(101,'message','eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJOS2UxSG91YVR2NnotRW1leXBYZnRSeFVGOXhlY0pTR1FGam1oRDY1N1hJIn0.eyJqdGkiOiJiMDEzYTBjYS1iMmFjLTRjYjQtYWM3MC02YjlkMDQwNDQzMDQiLCJleHAiOjE1NDI4MjQ5NzcsIm5iZiI6MCwiaWF0IjoxNTQyODI0OTE3LCJpc3MiOiJodHRwczovL2tjbmV'),(101,'type','org.springframework.security.authentication.BadCredentialsException'),(102,'details','remoteAddress=0:0:0:0:0:0:0:1, tokenType=BearertokenValue=<TOKEN>');
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
  `created_date` timestamp NULL DEFAULT NULL,
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
  CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`)
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
  `post_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_media_need_id` (`need_id`),
  KEY `fk_media_help_id` (`help_id`),
  KEY `fk_media_post_id` (`post_id`),
  CONSTRAINT `fk_media_help_id` FOREIGN KEY (`help_id`) REFERENCES `help` (`id`),
  CONSTRAINT `fk_media_need_id` FOREIGN KEY (`need_id`) REFERENCES `need` (`id`),
  CONSTRAINT `fk_media_post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='Media entity. @author Dheeraj das.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media`
--

LOCK TABLES `media` WRITE;
/*!40000 ALTER TABLE `media` DISABLE KEYS */;
INSERT INTO `media` VALUES (1,'rose','/src/main/resources/static/uploadedfiles/rose.jpeg','.jpeg',NULL,NULL,NULL),(3,'sunflower','/src/main/resources/static/uploadedfiles/sunflower.jpeg','.jpeg',NULL,NULL,NULL),(4,'baby','/src/main/resources/static/uploadedfiles/baby.jpeg','.jpeg',NULL,NULL,NULL),(5,'apple','/src/main/resources/static/uploadedfiles/apple.jpeg','.jpeg',NULL,NULL,NULL),(6,'lilly','/src/main/resources/static/uploadedfiles/lilly.jpeg','.jpeg',NULL,NULL,NULL),(7,'cherry','/src/main/resources/static/uploadedfiles/cherry.jpeg','.jpeg',NULL,NULL,NULL),(8,'lotus','/src/main/resources/static/uploadedfiles/lotus.jpeg','.jpeg',NULL,NULL,NULL),(9,'orange','/src/main/resources/static/uploadedfiles/orange.jpeg','.jpeg',NULL,NULL,NULL);
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
  `jhi_date` datetime DEFAULT NULL,
  `severity_id` bigint(20) DEFAULT NULL,
  `verification_team_id` bigint(20) DEFAULT NULL,
  `approval_status_id` bigint(20) DEFAULT NULL,
  `posted_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_need_severity_id` (`severity_id`),
  KEY `fk_need_verification_team_id` (`verification_team_id`),
  KEY `fk_need_approval_status_id` (`approval_status_id`),
  KEY `fk_need_posted_user_id` (`posted_user_id`),
  CONSTRAINT `fk_need_approval_status_id` FOREIGN KEY (`approval_status_id`) REFERENCES `approval_status` (`id`),
  CONSTRAINT `fk_need_posted_user_id` FOREIGN KEY (`posted_user_id`) REFERENCES `registered_user` (`id`),
  CONSTRAINT `fk_need_severity_id` FOREIGN KEY (`severity_id`) REFERENCES `severity` (`id`),
  CONSTRAINT `fk_need_verification_team_id` FOREIGN KEY (`verification_team_id`) REFERENCES `verification_team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='Need entity @author Balagopal v';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `need`
--

LOCK TABLES `need` WRITE;
/*!40000 ALTER TABLE `need` DISABLE KEYS */;
INSERT INTO `need` VALUES (1,'i need help','you','2018-11-05 14:48:12',NULL,NULL,2,1),(2,'i want help','someone else','2018-11-05 14:48:12',2,NULL,2,2),(3,'help me','you','2018-11-05 14:48:12',1,NULL,1,3),(4,'help!','you','2018-11-05 14:48:12',NULL,NULL,1,4),(5,'ruhail da.... mail vanituduo','you','2018-11-22 10:02:12',NULL,NULL,1,6),(6,'sanileeeee','you','2018-11-22 10:09:23',NULL,NULL,2,5),(7,'anjus...............','you','2018-11-22 10:20:37',NULL,NULL,1,1),(8,'i need help urgent','you','2018-11-22 10:43:09',NULL,NULL,2,8),(9,'oh god','you','2018-11-22 14:25:51',NULL,NULL,1,7),(10,NULL,NULL,NULL,NULL,NULL,2,6),(11,'i wantt help','you','2018-11-05 14:48:12',NULL,NULL,2,1),(12,'i needddd help','you','2018-11-05 14:48:12',NULL,NULL,2,2);
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
INSERT INTO `need_categories` VALUES (1,21),(2,3),(3,5);
/*!40000 ALTER TABLE `need_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `jhi_date` datetime DEFAULT NULL,
  `registered_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_post_registered_user_id` (`registered_user_id`),
  CONSTRAINT `fk_post_registered_user_id` FOREIGN KEY (`registered_user_id`) REFERENCES `registered_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='Post entity @author Deepthi E';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'iam feeling happy','2018-11-29 00:00:00',1),(2,'iam coool','2018-11-29 00:00:00',2),(3,'fineeeee','2018-11-29 00:00:00',3),(4,'iam feeling happy','2018-11-29 00:00:00',4);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registered_user`
--

DROP TABLE IF EXISTS `registered_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registered_user` (
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
  `emotional_quotient` bigint(20) DEFAULT NULL,
  `social_quotient` bigint(20) DEFAULT NULL,
  `happiness_index` bigint(20) DEFAULT NULL,
  `profile_pic_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_registered_user_profile_pic_id` (`profile_pic_id`),
  CONSTRAINT `fk_registered_user_profile_pic_id` FOREIGN KEY (`profile_pic_id`) REFERENCES `media` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='RegisteredUser entity. @author Muhammed Ruhail';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registered_user`
--

LOCK TABLES `registered_user` WRITE;
/*!40000 ALTER TABLE `registered_user` DISABLE KEYS */;
INSERT INTO `registered_user` VALUES (1,'anjali.chandran@lxisoft.com','Anjali','c',9,'am happy','nulla nisl','Female','2018-03-30','AB+ve',2,10,6,1),(2,'sarangibalu.a@lxisoft.com','Sarangi','Kuma',8,'am cool','nulla nisl','Male','1992-03-30','B+ve',2,10,6,3),(3,'sanilkumar.p@lxisoft.com','Sanil','Kuma',8,'am cool','nulla nisl','Male','1992-03-30','B+ve',2,10,6,4),(4,'muhammed.ruhail@lxisoft.com','Ruhail','m',10,'am cool','nulla nisl','male','2018-03-30','AB+ve',2,10,6,5),(5,'neeraja.m@lxisoft.com','Neeraja','m',15,'am fine','abc','Female','2018-03-30','AB+ve',2,10,6,6),(6,'sooraj.p.n@lxisoft.com','Sooraj','m',18,'am fine','abc','male','2018-03-30','AB+ve',2,10,6,7),(7,'dheeraj.das@lxisoft.com','Dheeraj','m',18,'am coool','abc','male','2018-03-30','AB+ve',2,10,6,8),(8,'deepthi.e@lxisoft.com','Deepthi','m',18,'am coool','abc','female','2018-03-30','A-ve',2,10,6,9);
/*!40000 ALTER TABLE `registered_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registered_user_followers`
--

DROP TABLE IF EXISTS `registered_user_followers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registered_user_followers` (
  `followers_id` bigint(20) NOT NULL,
  `registered_users_id` bigint(20) NOT NULL,
  PRIMARY KEY (`registered_users_id`,`followers_id`),
  KEY `fk_registered_user_followers_followers_id` (`followers_id`),
  CONSTRAINT `fk_registered_user_followers_followers_id` FOREIGN KEY (`followers_id`) REFERENCES `registered_user` (`id`),
  CONSTRAINT `fk_registered_user_followers_registered_users_id` FOREIGN KEY (`registered_users_id`) REFERENCES `registered_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registered_user_followers`
--

LOCK TABLES `registered_user_followers` WRITE;
/*!40000 ALTER TABLE `registered_user_followers` DISABLE KEYS */;
INSERT INTO `registered_user_followers` VALUES (1,2),(2,1),(3,1),(3,2),(4,2);
/*!40000 ALTER TABLE `registered_user_followers` ENABLE KEYS */;
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
  `jhi_date` datetime DEFAULT NULL,
  `comment_id` bigint(20) DEFAULT NULL,
  `replied_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reply_comment_id` (`comment_id`),
  KEY `fk_reply_replied_user_id` (`replied_user_id`),
  CONSTRAINT `fk_reply_comment_id` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `fk_reply_replied_user_id` FOREIGN KEY (`replied_user_id`) REFERENCES `registered_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='Reply  entity @author  Deepthi E';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
INSERT INTO `reply` VALUES (1,'super','2018-11-29 00:00:00',1,1),(2,'nice','2018-11-29 00:00:00',2,2),(3,'super one','2018-11-29 00:00:00',7,3),(4,'super','2018-11-29 00:00:00',8,5),(5,'super','2018-11-29 00:00:00',9,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='@Author Anjali';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `severity`
--

LOCK TABLES `severity` WRITE;
/*!40000 ALTER TABLE `severity` DISABLE KEYS */;
INSERT INTO `severity` VALUES (1,'very urgent'),(2,'urgent');
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
  `post_id` bigint(20) DEFAULT NULL,
  `checked_help_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_check_checked_need_id` (`checked_need_id`),
  KEY `fk_user_check_checked_user_id` (`checked_user_id`),
  KEY `fk_user_check_comment_id` (`comment_id`),
  KEY `fk_user_check_reply_id` (`reply_id`),
  KEY `fk_user_check_post_id` (`post_id`),
  KEY `fk_user_check_checked_help_id` (`checked_help_id`),
  CONSTRAINT `fk_user_check_checked_help_id` FOREIGN KEY (`checked_help_id`) REFERENCES `help` (`id`),
  CONSTRAINT `fk_user_check_checked_need_id` FOREIGN KEY (`checked_need_id`) REFERENCES `need` (`id`),
  CONSTRAINT `fk_user_check_checked_user_id` FOREIGN KEY (`checked_user_id`) REFERENCES `registered_user` (`id`),
  CONSTRAINT `fk_user_check_comment_id` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `fk_user_check_post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `fk_user_check_reply_id` FOREIGN KEY (`reply_id`) REFERENCES `reply` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='UserCheck entity @author Deepthi E';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_check`
--

LOCK TABLES `user_check` WRITE;
/*!40000 ALTER TABLE `user_check` DISABLE KEYS */;
INSERT INTO `user_check` VALUES (2,'postive','genuineness',2,2,NULL,NULL,NULL,NULL),(3,'negative','genuineness',2,1,NULL,NULL,NULL,NULL),(4,'negative','genuineness',5,1,NULL,NULL,NULL,NULL),(5,'negative','genuineness',5,2,NULL,NULL,NULL,NULL),(6,'postive','genuineness',2,3,NULL,NULL,NULL,NULL),(7,'positive','like',NULL,1,NULL,NULL,NULL,1),(8,'positive','like',NULL,2,NULL,NULL,NULL,1),(9,'negative','like',NULL,3,NULL,NULL,NULL,1),(10,'postive','genuiness',2,2,NULL,NULL,NULL,NULL),(11,'postive','genuiness',2,3,NULL,NULL,NULL,NULL),(12,'postive','genuiness',2,1,NULL,NULL,NULL,NULL),(13,'postive','genuiness',3,1,NULL,NULL,NULL,NULL),(14,'postive','genuiness',3,2,NULL,NULL,NULL,NULL),(15,'postive','genuiness',3,3,NULL,NULL,NULL,NULL),(16,'positive','like',NULL,1,NULL,NULL,NULL,2),(17,'positive','like',NULL,2,NULL,NULL,NULL,2),(18,'positive','like',NULL,3,NULL,NULL,NULL,2),(19,'negative','like',NULL,3,NULL,NULL,NULL,2),(20,'postive','like',NULL,3,1,NULL,NULL,NULL),(21,'negative','dislike',NULL,3,2,NULL,NULL,NULL),(22,'postive','like',NULL,3,NULL,1,NULL,NULL),(23,'negative','dislike',NULL,3,NULL,2,NULL,NULL),(24,'negative','dislike',NULL,3,NULL,NULL,1,NULL),(25,'positive','like',NULL,3,NULL,NULL,2,NULL);
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
  CONSTRAINT `fk_verification_team_approving_users_approving_users_id` FOREIGN KEY (`approving_users_id`) REFERENCES `registered_user` (`id`),
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-29 14:24:49
