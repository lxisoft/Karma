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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ApprovalStatus entity. @author Sanil kumar';
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Category entity. @author Dheeraj das.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Comment entity @author Deepthi E';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
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
INSERT INTO `databasechangelog` VALUES ('00000000000001','jhipster','config/liquibase/changelog/00000000000000_initial_schema.xml','2018-12-03 19:45:34',1,'EXECUTED','7:3628d21006c83bc696d51a75927e556f','createTable tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableName=jhi_user_authority; addForeignKeyConstraint baseTableName=jhi_user_authority, constraintName=fk_authority_name, ...','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103744-1','jhipster','config/liquibase/changelog/20181118103744_added_entity_RegisteredUser.xml','2018-12-03 19:45:35',2,'EXECUTED','7:4c5fc492a66de1dc7581f96eee751906','createTable tableName=registered_user','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103745-1','jhipster','config/liquibase/changelog/20181118103745_added_entity_ApprovalStatus.xml','2018-12-03 19:45:35',3,'EXECUTED','7:77e4e36a20a301883afa1b3daeef43ac','createTable tableName=approval_status','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103746-1','jhipster','config/liquibase/changelog/20181118103746_added_entity_Address.xml','2018-12-03 19:45:35',4,'EXECUTED','7:823c6d9999a93aa82802059fbcb0fdc2','createTable tableName=address','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103747-1','jhipster','config/liquibase/changelog/20181118103747_added_entity_Media.xml','2018-12-03 19:45:36',5,'EXECUTED','7:3830e2326272a81ea6bad4c2c731a07d','createTable tableName=media','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103748-1','jhipster','config/liquibase/changelog/20181118103748_added_entity_Category.xml','2018-12-03 19:45:36',6,'EXECUTED','7:8d861f5caade3b58c565a1217a483064','createTable tableName=category','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103749-1','jhipster','config/liquibase/changelog/20181118103749_added_entity_UserCheck.xml','2018-12-03 19:45:36',7,'EXECUTED','7:68009601e60ef16b1c7bb2d470f18920','createTable tableName=user_check','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103750-1','jhipster','config/liquibase/changelog/20181118103750_added_entity_Need.xml','2018-12-03 19:45:38',8,'EXECUTED','7:6ed8252ab4b687ea61dac61193347429','createTable tableName=need; dropDefaultValue columnName=jhi_date, tableName=need; createTable tableName=need_categories; addPrimaryKey tableName=need_categories','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103751-1','jhipster','config/liquibase/changelog/20181118103751_added_entity_Help.xml','2018-12-03 19:45:38',9,'EXECUTED','7:a15e32b50dfc7929c58c28a679427944','createTable tableName=help; dropDefaultValue columnName=jhi_time, tableName=help','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103752-1','jhipster','config/liquibase/changelog/20181118103752_added_entity_Severity.xml','2018-12-03 19:45:38',10,'EXECUTED','7:70dd6b820d10d352231c5135c22ffbc9','createTable tableName=severity','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103753-1','jhipster','config/liquibase/changelog/20181118103753_added_entity_VerificationTeam.xml','2018-12-03 19:45:40',11,'EXECUTED','7:29efce71a09132ec3db8f9d7a6f01877','createTable tableName=verification_team; createTable tableName=verification_team_approving_users; addPrimaryKey tableName=verification_team_approving_users','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103754-1','jhipster','config/liquibase/changelog/20181118103754_added_entity_Post.xml','2018-12-03 19:45:41',12,'EXECUTED','7:3c47892ae96b63411abc6e05e90b2ed0','createTable tableName=post; dropDefaultValue columnName=jhi_date, tableName=post','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103755-1','jhipster','config/liquibase/changelog/20181118103755_added_entity_Comment.xml','2018-12-03 19:45:41',13,'EXECUTED','7:8519452e4dc94e2f6ca99ecb624ff4d9','createTable tableName=comment; dropDefaultValue columnName=jhi_date, tableName=comment','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103756-1','jhipster','config/liquibase/changelog/20181118103756_added_entity_Reply.xml','2018-12-03 19:45:42',14,'EXECUTED','7:a684e887b20d0f9d2bd6897b9154d38d','createTable tableName=reply; dropDefaultValue columnName=jhi_date, tableName=reply','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103757-1','jhipster','config/liquibase/changelog/20181118103757_added_entity_Feed.xml','2018-12-03 19:45:43',15,'EXECUTED','7:c7b0af01190c023056b379be48656a1b','createTable tableName=feed; dropDefaultValue columnName=jhi_date, tableName=feed','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181203055324-1','jhipster','config/liquibase/changelog/20181203055324_added_entity_IdentityProof.xml','2018-12-03 19:45:44',16,'EXECUTED','7:2899637d2d03cda80e4fb94f4d84d727','createTable tableName=identity_proof','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181203055325-1','jhipster','config/liquibase/changelog/20181203055325_added_entity_IdentityProofType.xml','2018-12-03 19:45:44',17,'EXECUTED','7:b9c97d8fbb2fbdf66f7d4be2cce17745','createTable tableName=identity_proof_type','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103744-2','jhipster','config/liquibase/changelog/20181118103744_added_entity_constraints_RegisteredUser.xml','2018-12-03 19:45:45',18,'EXECUTED','7:0a389e711ef48ea6bd29b7758cb7d11b','addForeignKeyConstraint baseTableName=registered_user, constraintName=fk_registered_user_profile_pic_id, referencedTableName=media; addForeignKeyConstraint baseTableName=registered_user, constraintName=fk_registered_user_id_proof_id, referencedTab...','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103746-2','jhipster','config/liquibase/changelog/20181118103746_added_entity_constraints_Address.xml','2018-12-03 19:45:45',19,'EXECUTED','7:23f6332ca500696e2407a8e70e77495d','addForeignKeyConstraint baseTableName=address, constraintName=fk_address_registered_user_id, referencedTableName=registered_user','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103747-2','jhipster','config/liquibase/changelog/20181118103747_added_entity_constraints_Media.xml','2018-12-03 19:45:48',20,'EXECUTED','7:243af96bab9ae57804972af87a7de261','addForeignKeyConstraint baseTableName=media, constraintName=fk_media_need_id, referencedTableName=need; addForeignKeyConstraint baseTableName=media, constraintName=fk_media_help_id, referencedTableName=help; addForeignKeyConstraint baseTableName=m...','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103749-2','jhipster','config/liquibase/changelog/20181118103749_added_entity_constraints_UserCheck.xml','2018-12-03 19:45:50',21,'EXECUTED','7:faf32909a48ddef23d52a4cdac4f47c0','addForeignKeyConstraint baseTableName=user_check, constraintName=fk_user_check_checked_need_id, referencedTableName=need; addForeignKeyConstraint baseTableName=user_check, constraintName=fk_user_check_checked_user_id, referencedTableName=registere...','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103750-2','jhipster','config/liquibase/changelog/20181118103750_added_entity_constraints_Need.xml','2018-12-03 19:45:51',22,'EXECUTED','7:95f3f2fa88aa531414e57bd95c7f36cf','addForeignKeyConstraint baseTableName=need, constraintName=fk_need_severity_id, referencedTableName=severity; addForeignKeyConstraint baseTableName=need, constraintName=fk_need_verification_team_id, referencedTableName=verification_team; addForeig...','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103751-2','jhipster','config/liquibase/changelog/20181118103751_added_entity_constraints_Help.xml','2018-12-03 19:45:55',23,'EXECUTED','7:bb437944ae4f9b6375469120ee543e8d','addForeignKeyConstraint baseTableName=help, constraintName=fk_help_approval_status_id, referencedTableName=approval_status; addForeignKeyConstraint baseTableName=help, constraintName=fk_help_provided_user_id, referencedTableName=registered_user; a...','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103753-2','jhipster','config/liquibase/changelog/20181118103753_added_entity_constraints_VerificationTeam.xml','2018-12-03 19:45:55',24,'EXECUTED','7:5a1e1b3b1971b4e88305c514e2c3bd41','addForeignKeyConstraint baseTableName=verification_team_approving_users, constraintName=fk_verification_team_approving_users_verification_teams_id, referencedTableName=verification_team; addForeignKeyConstraint baseTableName=verification_team_appr...','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103754-2','jhipster','config/liquibase/changelog/20181118103754_added_entity_constraints_Post.xml','2018-12-03 19:45:56',25,'EXECUTED','7:12aebc8e9c04828f94f1d7023f819496','addForeignKeyConstraint baseTableName=post, constraintName=fk_post_registered_user_id, referencedTableName=registered_user','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103755-2','jhipster','config/liquibase/changelog/20181118103755_added_entity_constraints_Comment.xml','2018-12-03 19:45:57',26,'EXECUTED','7:fa978784fa8a8ab685b3ad3b7726867f','addForeignKeyConstraint baseTableName=comment, constraintName=fk_comment_need_id, referencedTableName=need; addForeignKeyConstraint baseTableName=comment, constraintName=fk_comment_help_id, referencedTableName=help; addForeignKeyConstraint baseTab...','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103756-2','jhipster','config/liquibase/changelog/20181118103756_added_entity_constraints_Reply.xml','2018-12-03 19:45:58',27,'EXECUTED','7:3ad14f0df9d6398d7fd12bd1028a09e1','addForeignKeyConstraint baseTableName=reply, constraintName=fk_reply_comment_id, referencedTableName=comment; addForeignKeyConstraint baseTableName=reply, constraintName=fk_reply_replied_user_id, referencedTableName=registered_user','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181118103757-2','jhipster','config/liquibase/changelog/20181118103757_added_entity_constraints_Feed.xml','2018-12-03 19:45:58',28,'EXECUTED','7:5e91fdc247fd4c9514c2c0fc65243ae3','addForeignKeyConstraint baseTableName=feed, constraintName=fk_feed_registered_user_id, referencedTableName=registered_user','',NULL,'3.5.4',NULL,NULL,'3846532183'),('20181203055324-2','jhipster','config/liquibase/changelog/20181203055324_added_entity_constraints_IdentityProof.xml','2018-12-03 19:45:59',29,'EXECUTED','7:2979f60493e5c35c11446e0806afb5fd','addForeignKeyConstraint baseTableName=identity_proof, constraintName=fk_identity_proof_identity_proof_type_id, referencedTableName=identity_proof_type','',NULL,'3.5.4',NULL,NULL,'3846532183');
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
  `jhi_date` datetime,
  `reference_id` bigint(20) DEFAULT NULL,
  `registered_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_feed_registered_user_id` (`registered_user_id`),
  CONSTRAINT `fk_feed_registered_user_id` FOREIGN KEY (`registered_user_id`) REFERENCES `registered_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Feed entity @author Deepthi E';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed`
--

LOCK TABLES `feed` WRITE;
/*!40000 ALTER TABLE `feed` DISABLE KEYS */;
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
  `jhi_time` datetime,
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
  CONSTRAINT `fk_help_provided_user_id` FOREIGN KEY (`provided_user_id`) REFERENCES `registered_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Service entity @Author Sooraj Pn';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `help`
--

LOCK TABLES `help` WRITE;
/*!40000 ALTER TABLE `help` DISABLE KEYS */;
/*!40000 ALTER TABLE `help` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `identity_proof`
--

DROP TABLE IF EXISTS `identity_proof`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `identity_proof` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_no` varchar(255) DEFAULT NULL,
  `identity_proof_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_identity_proof_identity_proof_type_id` (`identity_proof_type_id`),
  CONSTRAINT `fk_identity_proof_identity_proof_type_id` FOREIGN KEY (`identity_proof_type_id`) REFERENCES `identity_proof_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='IdentityProof entity. @author Sarangi Balu';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `identity_proof`
--

LOCK TABLES `identity_proof` WRITE;
/*!40000 ALTER TABLE `identity_proof` DISABLE KEYS */;
/*!40000 ALTER TABLE `identity_proof` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `identity_proof_type`
--

DROP TABLE IF EXISTS `identity_proof_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `identity_proof_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `jhi_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='IdentityProofType entity. @author Sarangi Balu';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `identity_proof_type`
--

LOCK TABLES `identity_proof_type` WRITE;
/*!40000 ALTER TABLE `identity_proof_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `identity_proof_type` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jhi_persistent_audit_event`
--

LOCK TABLES `jhi_persistent_audit_event` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_event` DISABLE KEYS */;
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
  CONSTRAINT `fk_media_post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `fk_media_help_id` FOREIGN KEY (`help_id`) REFERENCES `help` (`id`),
  CONSTRAINT `fk_media_need_id` FOREIGN KEY (`need_id`) REFERENCES `need` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Media entity. @author Dheeraj das.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media`
--

LOCK TABLES `media` WRITE;
/*!40000 ALTER TABLE `media` DISABLE KEYS */;
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
  `person_in_charge_id` bigint(20) DEFAULT NULL,
  `posted_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_need_severity_id` (`severity_id`),
  KEY `fk_need_verification_team_id` (`verification_team_id`),
  KEY `fk_need_approval_status_id` (`approval_status_id`),
  KEY `fk_need_person_in_charge_id` (`person_in_charge_id`),
  KEY `fk_need_posted_user_id` (`posted_user_id`),
  CONSTRAINT `fk_need_posted_user_id` FOREIGN KEY (`posted_user_id`) REFERENCES `registered_user` (`id`),
  CONSTRAINT `fk_need_approval_status_id` FOREIGN KEY (`approval_status_id`) REFERENCES `approval_status` (`id`),
  CONSTRAINT `fk_need_person_in_charge_id` FOREIGN KEY (`person_in_charge_id`) REFERENCES `registered_user` (`id`),
  CONSTRAINT `fk_need_severity_id` FOREIGN KEY (`severity_id`) REFERENCES `severity` (`id`),
  CONSTRAINT `fk_need_verification_team_id` FOREIGN KEY (`verification_team_id`) REFERENCES `verification_team` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Need entity @author Balagopal v';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `need`
--

LOCK TABLES `need` WRITE;
/*!40000 ALTER TABLE `need` DISABLE KEYS */;
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
  `jhi_date` datetime,
  `registered_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_post_registered_user_id` (`registered_user_id`),
  CONSTRAINT `fk_post_registered_user_id` FOREIGN KEY (`registered_user_id`) REFERENCES `registered_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Post entity @author Deepthi E';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
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
  `id_proof_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_registered_user_id_proof_id` (`id_proof_id`),
  UNIQUE KEY `ux_registered_user_profile_pic_id` (`profile_pic_id`),
  CONSTRAINT `fk_registered_user_id_proof_id` FOREIGN KEY (`id_proof_id`) REFERENCES `identity_proof` (`id`),
  CONSTRAINT `fk_registered_user_profile_pic_id` FOREIGN KEY (`profile_pic_id`) REFERENCES `media` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='RegisteredUser entity. @author Muhammed Ruhail';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registered_user`
--

LOCK TABLES `registered_user` WRITE;
/*!40000 ALTER TABLE `registered_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `registered_user` ENABLE KEYS */;
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
  `replied_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reply_comment_id` (`comment_id`),
  KEY `fk_reply_replied_user_id` (`replied_user_id`),
  CONSTRAINT `fk_reply_replied_user_id` FOREIGN KEY (`replied_user_id`) REFERENCES `registered_user` (`id`),
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='UserCheck entity @author Deepthi E';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_check`
--

LOCK TABLES `user_check` WRITE;
/*!40000 ALTER TABLE `user_check` DISABLE KEYS */;
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

-- Dump completed on 2018-12-05 12:14:56
