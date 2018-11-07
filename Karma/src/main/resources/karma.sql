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
INSERT INTO `address` VALUES (1,'magna at','turpis integer','Decatur','Georgia','United States',5750,NULL),(2,'non pretium','sed nisl','Shawnee Mission','Kansas','United States',7,NULL),(3,'velit donec','nulla suscipit','Spring','Texas','United States',594,NULL),(4,'faucibus orci','tellus in','Detroit','Michigan','United States',4,NULL),(5,'viverra dapibus','hendrerit at','Lake Worth','Florida','United States',93,NULL),(6,'in leo','purus phasellus','Torrance','California','United States',700,NULL),(7,'viverra diam','eu orci','Washington','District of Columbia','United States',97,NULL),(8,'in blandit','erat vestibulum','Jefferson City','Missouri','United States',57,NULL),(9,'tortor duis','luctus et','Oakland','California','United States',0,NULL),(10,'sed sagittis','viverra diam','El Paso','Texas','United States',5,NULL);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Category entity. @author Dheeraj das.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'blandit non','erat quisque'),(2,'id sapien','vel ipsum'),(3,'sem fusce','eu nibh'),(4,'nec euismod','habitasse platea'),(5,'mauris laoreet','eu interdum'),(6,'magna vulputate','fusce posuere'),(7,'tortor sollicitudin','nisi nam'),(8,'vestibulum quam','curabitur gravida'),(9,'pede malesuada','accumsan felis'),(10,'posuere cubilia','nibh fusce');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
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
INSERT INTO `databasechangelog` VALUES ('00000000000001','jhipster','config/liquibase/changelog/00000000000000_initial_schema.xml','2018-09-24 22:33:05',1,'EXECUTED','7:5ef25e3f65bca8f9e6b65198dcbbbb3b','createTable tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableName=jhi_user_authority; addForeignKeyConstraint baseTableName=jhi_user_authority, constraintName=fk_authority_name, ...','',NULL,'3.5.4',NULL,NULL,'7808583077'),('20180924060348-1','jhipster','config/liquibase/changelog/20180924060348_added_entity_LoggedUser.xml','2018-09-24 22:33:05',2,'EXECUTED','7:c9891ff81e88567630c278073fbdc9ba','createTable tableName=logged_user','',NULL,'3.5.4',NULL,NULL,'7808583077'),('20180924060349-1','jhipster','config/liquibase/changelog/20180924060349_added_entity_ApprovalStatus.xml','2018-09-24 22:33:06',3,'EXECUTED','7:77e4e36a20a301883afa1b3daeef43ac','createTable tableName=approval_status','',NULL,'3.5.4',NULL,NULL,'7808583077'),('20180924060350-1','jhipster','config/liquibase/changelog/20180924060350_added_entity_Address.xml','2018-09-24 22:33:06',4,'EXECUTED','7:c72f5c5b73864f09ffab4ae81c6338de','createTable tableName=address','',NULL,'3.5.4',NULL,NULL,'7808583077'),('20180924060351-1','jhipster','config/liquibase/changelog/20180924060351_added_entity_Media.xml','2018-09-24 22:33:06',5,'EXECUTED','7:25fe4c6fa7d1cb90e9ba46d37d4d0284','createTable tableName=media','',NULL,'3.5.4',NULL,NULL,'7808583077'),('20180924060352-1','jhipster','config/liquibase/changelog/20180924060352_added_entity_Category.xml','2018-09-24 22:33:07',6,'EXECUTED','7:8d861f5caade3b58c565a1217a483064','createTable tableName=category','',NULL,'3.5.4',NULL,NULL,'7808583077'),('20180924060353-1','jhipster','config/liquibase/changelog/20180924060353_added_entity_UserCheck.xml','2018-09-24 22:33:07',7,'EXECUTED','7:2b4a0924506fbb21bf2166e65d6d6934','createTable tableName=user_check','',NULL,'3.5.4',NULL,NULL,'7808583077'),('20180924060354-1','jhipster','config/liquibase/changelog/20180924060354_added_entity_Need.xml','2018-09-24 22:33:08',8,'EXECUTED','7:f358ea034523b8e13c6fb77f9b069664','createTable tableName=need; dropDefaultValue columnName=jhi_date, tableName=need; createTable tableName=need_categories; addPrimaryKey tableName=need_categories','',NULL,'3.5.4',NULL,NULL,'7808583077'),('20180924060355-1','jhipster','config/liquibase/changelog/20180924060355_added_entity_Help.xml','2018-09-24 22:33:08',9,'EXECUTED','7:a15e32b50dfc7929c58c28a679427944','createTable tableName=help; dropDefaultValue columnName=jhi_time, tableName=help','',NULL,'3.5.4',NULL,NULL,'7808583077'),('20180924060356-1','jhipster','config/liquibase/changelog/20180924060356_added_entity_Severity.xml','2018-09-24 22:33:09',10,'EXECUTED','7:70dd6b820d10d352231c5135c22ffbc9','createTable tableName=severity','',NULL,'3.5.4',NULL,NULL,'7808583077'),('20180924060357-1','jhipster','config/liquibase/changelog/20180924060357_added_entity_VerificationTeam.xml','2018-09-24 22:33:10',11,'EXECUTED','7:29efce71a09132ec3db8f9d7a6f01877','createTable tableName=verification_team; createTable tableName=verification_team_approving_users; addPrimaryKey tableName=verification_team_approving_users','',NULL,'3.5.4',NULL,NULL,'7808583077'),('20180924060348-2','jhipster','config/liquibase/changelog/20180924060348_added_entity_constraints_LoggedUser.xml','2018-09-24 22:33:10',12,'EXECUTED','7:3bfb9f9ebd966e3592c2a855feca38ed','addForeignKeyConstraint baseTableName=logged_user, constraintName=fk_logged_user_profile_pic_id, referencedTableName=media','',NULL,'3.5.4',NULL,NULL,'7808583077'),('20180924060350-2','jhipster','config/liquibase/changelog/20180924060350_added_entity_constraints_Address.xml','2018-09-24 22:33:10',13,'EXECUTED','7:9756a9a5dd08392cab78c1f337111583','addForeignKeyConstraint baseTableName=address, constraintName=fk_address_logged_user_id, referencedTableName=logged_user','',NULL,'3.5.4',NULL,NULL,'7808583077'),('20180924060351-2','jhipster','config/liquibase/changelog/20180924060351_added_entity_constraints_Media.xml','2018-09-24 22:33:11',14,'EXECUTED','7:f3ffbbd53c662bfd6e3941a522a1a4f5','addForeignKeyConstraint baseTableName=media, constraintName=fk_media_need_id, referencedTableName=need','',NULL,'3.5.4',NULL,NULL,'7808583077'),('20180924060353-2','jhipster','config/liquibase/changelog/20180924060353_added_entity_constraints_UserCheck.xml','2018-09-24 22:33:12',15,'EXECUTED','7:11ad4494826a8759e8147cab1884177c','addForeignKeyConstraint baseTableName=user_check, constraintName=fk_user_check_marked_user_id, referencedTableName=need','',NULL,'3.5.4',NULL,NULL,'7808583077'),('20180924060354-2','jhipster','config/liquibase/changelog/20180924060354_added_entity_constraints_Need.xml','2018-09-24 22:33:23',16,'EXECUTED','7:20d6b298019d4ea7fb4ea4f9171510e1','addForeignKeyConstraint baseTableName=need, constraintName=fk_need_severity_id, referencedTableName=severity; addForeignKeyConstraint baseTableName=need, constraintName=fk_need_verification_team_id, referencedTableName=verification_team; addForeig...','',NULL,'3.5.4',NULL,NULL,'7808583077'),('20180924060355-2','jhipster','config/liquibase/changelog/20180924060355_added_entity_constraints_Help.xml','2018-09-24 22:33:24',17,'EXECUTED','7:6a16cdfffbd403c618a5744504e1d960','addForeignKeyConstraint baseTableName=help, constraintName=fk_help_approval_status_id, referencedTableName=approval_status; addForeignKeyConstraint baseTableName=help, constraintName=fk_help_provided_user_id, referencedTableName=logged_user; addFo...','',NULL,'3.5.4',NULL,NULL,'7808583077'),('20180924060357-2','jhipster','config/liquibase/changelog/20180924060357_added_entity_constraints_VerificationTeam.xml','2018-09-24 22:33:24',18,'EXECUTED','7:9bc9c61276fd3910d2370298744265cf','addForeignKeyConstraint baseTableName=verification_team_approving_users, constraintName=fk_verification_team_approving_users_verification_teams_id, referencedTableName=verification_team; addForeignKeyConstraint baseTableName=verification_team_appr...','',NULL,'3.5.4',NULL,NULL,'7808583077');
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
INSERT INTO `databasechangeloglock` VALUES (1,'','2018-09-27 13:36:37','Sanil (169.254.138.157)');
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
  CONSTRAINT `fk_help_provided_user_id` FOREIGN KEY (`provided_user_id`) REFERENCES `logged_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Service entity @Author Sooraj Pn';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `help`
--

LOCK TABLES `help` WRITE;
/*!40000 ALTER TABLE `help` DISABLE KEYS */;
INSERT INTO `help` VALUES (1,'2018-01-06 20:11:30','adipiscing molestie hendrerit at vulputate vitae nisl aenean lectus pellentesque',NULL,NULL,NULL),(2,'2018-08-22 11:13:07','risus praesent lectus vestibulum quam sapien varius ut blandit non',NULL,NULL,NULL),(3,'2018-03-14 14:49:33','ultrices erat tortor sollicitudin mi sit amet lobortis sapien sapien',NULL,NULL,NULL),(4,'2018-01-01 13:23:26','vestibulum eget vulputate ut ultrices vel augue vestibulum ante ipsum',NULL,NULL,NULL),(5,'2017-12-08 05:18:02','eu magna vulputate luctus cum sociis natoque penatibus et magnis',NULL,NULL,NULL),(6,'2017-10-24 06:54:01','at dolor quis odio consequat varius integer ac leo pellentesque',NULL,NULL,NULL),(7,'2017-09-29 04:51:32','nibh in quis justo maecenas rhoncus aliquam lacus morbi quis',NULL,NULL,NULL),(8,'2018-06-25 00:40:27','sit amet eleifend pede libero quis orci nullam molestie nibh',NULL,NULL,NULL),(9,'2018-04-18 02:58:35','nibh fusce lacus purus aliquet at feugiat non pretium quis',NULL,NULL,NULL),(10,'2018-07-29 18:24:29','adipiscing lorem vitae mattis nibh ligula nec sem duis aliquam',NULL,NULL,NULL),(11,'2018-09-28 21:45:44','Helped',4,NULL,NULL),(12,'2018-09-28 22:01:39','hg',3,NULL,1),(13,'2018-09-29 15:39:39','Helped new',4,NULL,1),(14,'2018-10-01 16:20:58','Help for need 1',4,NULL,1),(15,'2018-10-03 16:44:10','new help-helped',4,NULL,41),(16,'2018-10-03 16:45:47','help',5,NULL,5);
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
INSERT INTO `logged_user` VALUES (1,'bwillgress0@bravesites.com','Brennen','Willgress',78,'ut tellus nulla ut erat id mauris vulputate elementum nullam varius nulla facilisi cras non velit nec nisi vulputate nonummy','in leo','Male','2017-09-27','purus eu magna vulputate luctus',NULL),(2,'mlivingstone1@etsy.com','Merla','Livingstone',26,'urna pretium nisl ut volutpat sapien arcu sed augue aliquam erat volutpat in congue etiam','aliquet pulvinar','Female','2018-08-28','vulputate elementum nullam varius nulla',NULL),(3,'njurgensen2@bigcartel.com','Norah','Jurgensen',72,'tempus vivamus in felis eu sapien cursus vestibulum proin eu mi nulla ac enim in tempor turpis nec euismod','quam sapien','Female','2018-06-11','platea dictumst maecenas',NULL),(4,'mtumasian3@google.nl','Marje','Tumasian',55,'feugiat non pretium quis lectus suspendisse potenti in eleifend quam a odio in hac habitasse platea','sagittis sapien','Female','2018-09-01','in porttitor pede',NULL),(5,'paldam4@google.ca','Pauletta','Aldam',36,'luctus ultricies eu nibh quisque id justo sit amet sapien dignissim vestibulum vestibulum ante ipsum primis in faucibus orci luctus','vulputate justo','Female','2017-10-30','orci vehicula condimentum curabitur in',NULL),(6,'rdunhill5@cbsnews.com','Richy','Dunhill',55,'lacus morbi quis tortor id nulla ultrices aliquet maecenas leo odio condimentum id luctus nec molestie sed justo pellentesque viverra','vestibulum proin','Male','2018-05-09','mauris ullamcorper purus',NULL),(7,'pgrut6@storify.com','Pacorro','Grut',52,'lectus suspendisse potenti in eleifend quam a odio in hac habitasse platea dictumst maecenas ut massa quis','eget vulputate','Male','2017-11-02','aliquam sit amet diam in',NULL),(8,'hbaldry7@upenn.edu','Hyacintha','Baldry',11,'sit amet diam in magna bibendum imperdiet nullam orci pede venenatis non sodales sed tincidunt eu felis fusce','eget nunc','Female','2018-08-18','duis faucibus accumsan',NULL),(9,'rwinwright8@yellowbook.com','Raddie','Winwright',29,'dis parturient montes nascetur ridiculus mus etiam vel augue vestibulum rutrum rutrum neque aenean auctor gravida sem praesent id','in tempus','Male','2018-08-13','lectus vestibulum quam sapien varius',NULL),(10,'bskilling9@arizona.edu','Barbee','Skilling',73,'cum sociis natoque penatibus et magnis dis parturient montes nascetur ridiculus mus etiam vel augue vestibulum rutrum rutrum neque','amet sapien','Female','2018-06-21','magna bibendum',NULL);
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
  PRIMARY KEY (`id`),
  KEY `fk_media_need_id` (`need_id`),
  KEY `fk_media_help_id` (`help_id`),
  CONSTRAINT `fk_media_help_id` FOREIGN KEY (`help_id`) REFERENCES `help` (`id`),
  CONSTRAINT `fk_media_need_id` FOREIGN KEY (`need_id`) REFERENCES `need` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Media entity. @author Dheeraj das.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media`
--

LOCK TABLES `media` WRITE;
/*!40000 ALTER TABLE `media` DISABLE KEYS */;
INSERT INTO `media` VALUES (1,'ipsum primis','curae duis','non velit',NULL),(2,'suspendisse potenti','eget tempus','blandit non',NULL),(3,'montes nascetur','metus arcu','ante ipsum',NULL),(4,'id nulla','sit amet','blandit nam',NULL),(5,'rhoncus dui','curae nulla','at ipsum',NULL),(6,'ornare imperdiet','erat quisque','tortor quis',NULL),(7,'nisi venenatis','ligula nec','lectus aliquam',NULL),(8,'quis augue','vel est','magnis dis',NULL),(9,'augue luctus','sociis natoque','aliquet pulvinar',NULL),(10,'suspendisse ornare','commodo vulputate','ultrices enim',NULL);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Need entity @author Balagopal v';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `need`
--

LOCK TABLES `need` WRITE;
/*!40000 ALTER TABLE `need` DISABLE KEYS */;
INSERT INTO `need` VALUES (1,'rutrum ac lobortis vel dapibus at diam nam tristique tortor','vestibulum quam sapien','2018-03-19 22:53:20',NULL,NULL,2,NULL),(2,'penatibus et magnis dis parturient montes nascetur ridiculus mus vivamus','donec ut mauris eget','2018-03-21 21:17:33',NULL,NULL,2,NULL),(3,'morbi vel lectus in quam fringilla rhoncus mauris enim leo','nec dui luctus rutrum','2017-12-23 20:14:03',NULL,NULL,2,NULL),(4,'nullam sit amet turpis elementum ligula vehicula consequat morbi a','quisque erat eros','2018-08-25 08:15:59',NULL,NULL,2,NULL),(5,'eros suspendisse accumsan tortor quis turpis sed ante vivamus tortor','at vulputate vitae','2018-05-24 02:52:41',NULL,NULL,2,NULL),(36,'I need financial help','someone else',NULL,NULL,NULL,2,NULL),(37,'Date test','you',NULL,NULL,NULL,2,NULL),(38,'Date second test','you',NULL,NULL,NULL,1,NULL),(39,'3rd test','you',NULL,NULL,NULL,3,NULL),(40,'4th attempt','you','2018-09-28 02:35:31',NULL,NULL,3,NULL),(41,'New help','someone else','2018-09-29 15:35:10',NULL,NULL,2,NULL);
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
INSERT INTO `need_categories` VALUES (1,38);
/*!40000 ALTER TABLE `need_categories` ENABLE KEYS */;
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
INSERT INTO `severity` VALUES (1,'id mauris vulputate'),(2,'velit donec diam'),(3,'volutpat dui maecenas'),(4,'feugiat non pretium');
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
  PRIMARY KEY (`id`),
  KEY `fk_user_check_checked_need_id` (`checked_need_id`),
  KEY `fk_user_check_checked_user_id` (`checked_user_id`),
  CONSTRAINT `fk_user_check_checked_user_id` FOREIGN KEY (`checked_user_id`) REFERENCES `logged_user` (`id`),
  CONSTRAINT `fk_user_check_checked_need_id` FOREIGN KEY (`checked_need_id`) REFERENCES `need` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='UserCheck entity @author Deepthi E';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_check`
--

LOCK TABLES `user_check` WRITE;
/*!40000 ALTER TABLE `user_check` DISABLE KEYS */;
INSERT INTO `user_check` VALUES (1,'suspendisse potenti','ridiculus mus',NULL),(2,'eget massa','justo lacinia',NULL),(3,'mus vivamus','aenean lectus',NULL),(4,'libero nullam','vestibulum vestibulum',NULL),(5,'sapien a','nam dui',NULL),(6,'odio justo','sit amet',NULL),(7,'quisque ut','lacus at',NULL),(8,'nullam orci','sem duis',NULL),(9,'sem praesent','quis turpis',NULL),(10,'cubilia curae','libero rutrum',NULL);
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
INSERT INTO `verification_team` VALUES (1,'id sapien in'),(2,'pretium quis lectus'),(3,'tincidunt nulla mollis'),(4,'purus aliquet at'),(5,'semper porta volutpat');
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-03 14:20:39
