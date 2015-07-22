-- MySQL dump 10.11
--
-- Host: localhost    Database: lighter
-- ------------------------------------------------------
-- Server version	5.0.51b-community

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
-- Table structure for table `ms_app_menu`
--

DROP TABLE IF EXISTS `ms_app_menu`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_app_menu` (
  `id_` int(11) NOT NULL auto_increment,
  `parent_id_` int(11) default NULL,
  `name_` varchar(255) default NULL,
  `description_` varchar(255) default NULL,
  `sort_` int(11) default NULL,
  `function_id_` int(11) default NULL,
  PRIMARY KEY  (`id_`),
  UNIQUE KEY `unique_id_` (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_app_menu`
--

LOCK TABLES `ms_app_menu` WRITE;
/*!40000 ALTER TABLE `ms_app_menu` DISABLE KEYS */;
INSERT INTO `ms_app_menu` VALUES (1,NULL,'系统维护','',3,NULL),(2,1,'组织机构','',1,33),(3,1,'角色管理','',2,29),(4,1,'数据字典','',3,36),(5,NULL,'应用模块','',2,NULL),(6,NULL,'地理信息','',1,NULL),(7,5,'模块管理','',1,40),(8,5,'菜单管理','',2,43),(11,6,'专题管理','',1,59),(12,1,'附件管理','',4,60),(13,1,'系统配置','',5,61);
/*!40000 ALTER TABLE `ms_app_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_app_menu_application_thematic`
--

DROP TABLE IF EXISTS `ms_app_menu_application_thematic`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_app_menu_application_thematic` (
  `menu_id_` int(11) NOT NULL,
  `application_id_` int(11) default NULL,
  `thematic_id` int(11) default NULL,
  `id_` int(11) NOT NULL auto_increment,
  UNIQUE KEY `unique_id_` (`id_`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_app_menu_application_thematic`
--

LOCK TABLES `ms_app_menu_application_thematic` WRITE;
/*!40000 ALTER TABLE `ms_app_menu_application_thematic` DISABLE KEYS */;
INSERT INTO `ms_app_menu_application_thematic` VALUES (1,41,1,22),(5,41,1,21),(6,41,1,20);
/*!40000 ALTER TABLE `ms_app_menu_application_thematic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_app_module`
--

DROP TABLE IF EXISTS `ms_app_module`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_app_module` (
  `id_` int(11) NOT NULL auto_increment,
  `parent_id_` int(11) default NULL,
  `name_` varchar(255) NOT NULL,
  `description_` varchar(255) NOT NULL,
  `sort_` int(11) default NULL,
  `type_` varchar(1) NOT NULL,
  `func_type_` varchar(1) default NULL,
  `func_code_` varchar(20000) default NULL,
  PRIMARY KEY  (`id_`),
  UNIQUE KEY `unique_id_` (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_app_module`
--

LOCK TABLES `ms_app_module` WRITE;
/*!40000 ALTER TABLE `ms_app_module` DISABLE KEYS */;
INSERT INTO `ms_app_module` VALUES (27,41,'系统管理','',3,'1',NULL,NULL),(29,27,'角色管理','',2,'2','1','{className: \'Base.sys.role.RoleContainer\', config: {}}'),(33,27,'组织机构','',1,'2','1','{className: \'Base.sys.org.OrgContainer\', config: {}}'),(36,27,'数据字典','',3,'2','1','{className: \'Base.sys.dict.DictContainer\', config: {}}'),(37,NULL,'餐饮订单','',1,'1',NULL,NULL),(38,37,'餐厅菜品维护','',1,'2','1','{className: \'Meaord.restaurant.RestaurantGridPanel\', config: {}}'),(39,41,'应用管理','',2,'1',NULL,NULL),(40,39,'模块管理','',1,'2','1','{className: \'Base.app.module.ModuleContainer\', config: {}}'),(41,NULL,'运行维护','',1,'1',NULL,NULL),(42,41,'地图管理','',1,'1',NULL,NULL),(43,39,'菜单管理','',2,'2','1','{className: \'Base.app.menu.MenuContainer\', config: {}}'),(58,53,'aa','',1,'1',NULL,NULL),(59,42,'专题管理','',1,'2','1','{className: \'Base.gis.thematic.ThematicContainer\', config: {}}'),(60,27,'附件管理','',4,'2','1','{className: \'Base.sys.attachment.AttachmentContainer\', config: {}}'),(61,27,'系统配置','',5,'2','1','{className: \'Base.sys.config.ConfigContainer\', config: {}}');
/*!40000 ALTER TABLE `ms_app_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_gis_thematic`
--

DROP TABLE IF EXISTS `ms_gis_thematic`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_gis_thematic` (
  `id_` int(11) NOT NULL auto_increment,
  `name_` varchar(255) NOT NULL,
  `sort_` int(11) NOT NULL,
  PRIMARY KEY  (`id_`),
  UNIQUE KEY `unique_id_` (`id_`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_gis_thematic`
--

LOCK TABLES `ms_gis_thematic` WRITE;
/*!40000 ALTER TABLE `ms_gis_thematic` DISABLE KEYS */;
INSERT INTO `ms_gis_thematic` VALUES (1,'专题一',1),(3,'专题二',2),(4,'专题三',3),(5,'专题四',4);
/*!40000 ALTER TABLE `ms_gis_thematic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_li_area`
--

DROP TABLE IF EXISTS `ms_li_area`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_li_area` (
  `ID_` int(11) NOT NULL auto_increment,
  `NAME_` varchar(255) default NULL,
  PRIMARY KEY  (`ID_`),
  UNIQUE KEY `unique_ID_` (`ID_`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_li_area`
--

LOCK TABLES `ms_li_area` WRITE;
/*!40000 ALTER TABLE `ms_li_area` DISABLE KEYS */;
INSERT INTO `ms_li_area` VALUES (4,'延边朝鲜族自治州'),(3,'长春市'),(5,'通化市'),(6,'松原市'),(7,'四平市'),(8,'辽源市'),(9,'吉林市'),(10,'白山市'),(11,'白城市');
/*!40000 ALTER TABLE `ms_li_area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_li_customer`
--

DROP TABLE IF EXISTS `ms_li_customer`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_li_customer` (
  `ID_` int(11) NOT NULL auto_increment,
  `VISIT_DATE_` date default NULL,
  `COMPANY_NAME_` varchar(255) default NULL,
  `LINK_MAN_` varchar(255) default NULL,
  `JOB_` varchar(255) default NULL,
  `PHONE_` varchar(255) default NULL,
  `OFFICE_PHONE_` varchar(255) default NULL,
  `EMAIL_` varchar(255) default NULL,
  `LEVEL_` varchar(255) default NULL,
  `VISIT_RESULT_` varchar(255) default NULL,
  `EMPLOYEE_ID_` int(11) default NULL,
  PRIMARY KEY  (`ID_`),
  UNIQUE KEY `unique_ID_` (`ID_`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_li_customer`
--

LOCK TABLES `ms_li_customer` WRITE;
/*!40000 ALTER TABLE `ms_li_customer` DISABLE KEYS */;
INSERT INTO `ms_li_customer` VALUES (20,'2015-07-15','单位名称2','联系人2','职务2','123456','234567','a@b.com','等级2','拜访结果2',22),(16,'2015-07-14','单位名称1','联系人1','职务1','123456','234567','a@b.com','等级1','拜访结果1',22),(22,'2015-07-22','单位名称3','联系人3','职务3','123456','','','','',21);
/*!40000 ALTER TABLE `ms_li_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_li_customer_trace`
--

DROP TABLE IF EXISTS `ms_li_customer_trace`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_li_customer_trace` (
  `ID_` int(11) NOT NULL auto_increment,
  `VISIT_DATE_` date default NULL,
  `LINK_MAN_` varchar(255) default NULL,
  `JOB_` varchar(255) default NULL,
  `PHONE_` varchar(255) default NULL,
  `OFFICE_PHONE_` varchar(255) default NULL,
  `EMAIL_` varchar(255) default NULL,
  `LEVEL_` varchar(255) default NULL,
  `VISIT_RESULT_` varchar(255) default NULL,
  `CUSTOMER_ID_` int(11) default NULL,
  PRIMARY KEY  (`ID_`),
  UNIQUE KEY `unique_ID_` (`ID_`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_li_customer_trace`
--

LOCK TABLES `ms_li_customer_trace` WRITE;
/*!40000 ALTER TABLE `ms_li_customer_trace` DISABLE KEYS */;
INSERT INTO `ms_li_customer_trace` VALUES (2,'2015-07-14','联系人1','职务1','123456','234567','a@b.com','等级1','拜访结果1',15),(3,'2015-07-14','联系人11','职务11','1234561','2345671','a1@b.com','等级11','拜访结果11',16),(4,'2015-07-15','联系人2','职务2','123456','234567','a@b.com','等级2','拜访结果2',20),(7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,21),(8,'2015-07-22','联系人3','职务3','','','','','',22),(9,'2015-07-22','联系人','职务','','','','','',23);
/*!40000 ALTER TABLE `ms_li_customer_trace` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_li_employee`
--

DROP TABLE IF EXISTS `ms_li_employee`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_li_employee` (
  `id_` int(11) NOT NULL auto_increment,
  `sort_` int(11) default NULL,
  `username_` varchar(255) NOT NULL,
  `password_` varchar(255) NOT NULL,
  `freeze_` int(11) NOT NULL,
  `name_` varchar(255) NOT NULL,
  `department_id_` int(11) default NULL,
  `email_` varchar(255) default NULL,
  PRIMARY KEY  (`id_`),
  UNIQUE KEY `unique_id_` (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_li_employee`
--

LOCK TABLES `ms_li_employee` WRITE;
/*!40000 ALTER TABLE `ms_li_employee` DISABLE KEYS */;
INSERT INTO `ms_li_employee` VALUES (1,1,'admin','40bd001563085fc35165329ea1ff5c5ecbdbbeef',0,'杨磊',1,'kyllyang@gmail.com'),(21,2,'zzw','40bd001563085fc35165329ea1ff5c5ecbdbbeef',0,'郑忠伟',NULL,''),(22,3,'sqf','40bd001563085fc35165329ea1ff5c5ecbdbbeef',0,'孙庆福',NULL,'');
/*!40000 ALTER TABLE `ms_li_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_li_employee_area`
--

DROP TABLE IF EXISTS `ms_li_employee_area`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_li_employee_area` (
  `employee_id_` int(11) NOT NULL default '0',
  `area_id_` int(11) NOT NULL default '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_li_employee_area`
--

LOCK TABLES `ms_li_employee_area` WRITE;
/*!40000 ALTER TABLE `ms_li_employee_area` DISABLE KEYS */;
INSERT INTO `ms_li_employee_area` VALUES (22,3),(22,5),(21,6),(21,3);
/*!40000 ALTER TABLE `ms_li_employee_area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_li_employee_role`
--

DROP TABLE IF EXISTS `ms_li_employee_role`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_li_employee_role` (
  `employee_id_` int(11) NOT NULL default '0',
  `role_id_` int(11) NOT NULL default '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_li_employee_role`
--

LOCK TABLES `ms_li_employee_role` WRITE;
/*!40000 ALTER TABLE `ms_li_employee_role` DISABLE KEYS */;
INSERT INTO `ms_li_employee_role` VALUES (1,3),(1,6),(1,7),(1,5),(21,5),(22,5);
/*!40000 ALTER TABLE `ms_li_employee_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_li_expense`
--

DROP TABLE IF EXISTS `ms_li_expense`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_li_expense` (
  `ID_` int(11) NOT NULL auto_increment,
  `START_DATE_` date default NULL,
  `END_DATE_` date default NULL,
  `CAR_EXPENSE_` decimal(10,2) default NULL,
  `CITY_TRAFFIC_EXPENSE_` decimal(10,2) default NULL,
  `SUBSIDY_EXPENSE_` decimal(10,2) default NULL,
  `OTHER_EXPENSE_` decimal(10,2) default NULL,
  `AREA_ID_` int(11) default NULL,
  `CUSTOMER_ID_` int(11) default NULL,
  `PROJECT_ID_` int(11) default NULL,
  PRIMARY KEY  (`ID_`),
  UNIQUE KEY `unique_ID_` (`ID_`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_li_expense`
--

LOCK TABLES `ms_li_expense` WRITE;
/*!40000 ALTER TABLE `ms_li_expense` DISABLE KEYS */;
INSERT INTO `ms_li_expense` VALUES (1,'2015-07-01','2015-07-15','10.13','20.00','30.00','40.00',3,16,4),(3,'2015-07-22','2015-07-22','12.00','23.00','34.00','45.00',3,22,5),(4,'2015-07-22','2015-07-22','11.00','22.00','44.00','33.00',6,22,5);
/*!40000 ALTER TABLE `ms_li_expense` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_li_product`
--

DROP TABLE IF EXISTS `ms_li_product`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_li_product` (
  `ID_` int(11) NOT NULL auto_increment,
  `NAME_` varchar(255) default NULL,
  `MODEL_` varchar(255) default NULL,
  `UNIT_` varchar(255) default NULL,
  `AMOUNT_` decimal(10,2) default NULL,
  `IN_TAX_PRICE_` decimal(10,2) default NULL,
  `IN_TAX_TOTAL_` decimal(10,2) default NULL,
  `OUT_TAX_PRICE_` decimal(10,2) default NULL,
  `OUT_TAX_TOTAL_` decimal(10,2) default NULL,
  `CUSTOMER_ID_` int(11) default NULL,
  `PROJECT_ID_` int(11) default NULL,
  `GROSS_MARGIN_` decimal(10,2) default NULL,
  `VERNOR_ID_` int(11) default NULL,
  PRIMARY KEY  (`ID_`),
  UNIQUE KEY `unique_ID_` (`ID_`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_li_product`
--

LOCK TABLES `ms_li_product` WRITE;
/*!40000 ALTER TABLE `ms_li_product` DISABLE KEYS */;
INSERT INTO `ms_li_product` VALUES (2,'产品名称1','型号1','单位1','1.00','2.20','3.00','4.00','5.00',16,3,'6.00',5);
/*!40000 ALTER TABLE `ms_li_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_li_project`
--

DROP TABLE IF EXISTS `ms_li_project`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_li_project` (
  `ID_` int(11) NOT NULL auto_increment,
  `CUSTOMER_ID_` int(11) default NULL,
  `NAME_` varchar(255) default NULL,
  `LINK_MAN_` varchar(255) default NULL,
  `PHONE_` varchar(255) default NULL,
  `FUND_` varchar(255) default NULL,
  PRIMARY KEY  (`ID_`),
  UNIQUE KEY `unique_ID_` (`ID_`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_li_project`
--

LOCK TABLES `ms_li_project` WRITE;
/*!40000 ALTER TABLE `ms_li_project` DISABLE KEYS */;
INSERT INTO `ms_li_project` VALUES (3,16,'项目1','联系人1','123456','资金情况1'),(4,16,'项目2','联系人2','123456','资金情况2'),(5,22,'项目zzw','联系人zzw','123456','');
/*!40000 ALTER TABLE `ms_li_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_li_project_trace`
--

DROP TABLE IF EXISTS `ms_li_project_trace`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_li_project_trace` (
  `ID_` int(11) NOT NULL auto_increment,
  `LINK_MAN_` varchar(255) default NULL,
  `PHONE_` varchar(255) default NULL,
  `FUND_` varchar(255) default NULL,
  `RESULT_` varchar(255) default NULL,
  `SUCCESS_RATE_` varchar(255) default NULL,
  `PROJECT_ID_` int(11) default NULL,
  PRIMARY KEY  (`ID_`),
  UNIQUE KEY `unique_ID_` (`ID_`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_li_project_trace`
--

LOCK TABLES `ms_li_project_trace` WRITE;
/*!40000 ALTER TABLE `ms_li_project_trace` DISABLE KEYS */;
INSERT INTO `ms_li_project_trace` VALUES (2,'项目联系人1','123456','100万','跟踪成果1','项目成功率1',3),(3,'联系人zzw','123456','','','',5);
/*!40000 ALTER TABLE `ms_li_project_trace` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_li_role`
--

DROP TABLE IF EXISTS `ms_li_role`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_li_role` (
  `id_` int(11) NOT NULL auto_increment,
  `name_` varchar(255) default NULL,
  `description_` varchar(255) default NULL,
  `sort_` int(11) default NULL,
  `code_` varchar(255) default NULL,
  PRIMARY KEY  (`id_`),
  UNIQUE KEY `unique_id_` (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_li_role`
--

LOCK TABLES `ms_li_role` WRITE;
/*!40000 ALTER TABLE `ms_li_role` DISABLE KEYS */;
INSERT INTO `ms_li_role` VALUES (3,'系统管理员','x',1,'ADMIN'),(5,'客户信息维护','',2,'CUSTOMER'),(6,'用户信息维护',NULL,4,'USER'),(7,'产品信息维护',NULL,3,'PRODUCT');
/*!40000 ALTER TABLE `ms_li_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_li_verdor`
--

DROP TABLE IF EXISTS `ms_li_verdor`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_li_verdor` (
  `ID_` int(11) NOT NULL auto_increment,
  `NAME_` varchar(255) default NULL,
  PRIMARY KEY  (`ID_`),
  UNIQUE KEY `unique_ID_` (`ID_`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_li_verdor`
--

LOCK TABLES `ms_li_verdor` WRITE;
/*!40000 ALTER TABLE `ms_li_verdor` DISABLE KEYS */;
INSERT INTO `ms_li_verdor` VALUES (8,'厂商3'),(7,'厂商4'),(6,'厂商2'),(5,'厂商1');
/*!40000 ALTER TABLE `ms_li_verdor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_sys_attachment`
--

DROP TABLE IF EXISTS `ms_sys_attachment`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_sys_attachment` (
  `id_` int(11) NOT NULL auto_increment,
  `entity_name_` varchar(255) default NULL,
  `entity_id_` varchar(255) default NULL,
  `content_type_` varchar(255) default NULL,
  `original_filename_` varchar(255) default NULL,
  `random_filename_` varchar(255) default NULL,
  `file_size_` int(11) default NULL,
  `extension_name_` varchar(255) default NULL,
  PRIMARY KEY  (`id_`),
  UNIQUE KEY `unique_id_` (`id_`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_sys_attachment`
--

LOCK TABLES `ms_sys_attachment` WRITE;
/*!40000 ALTER TABLE `ms_sys_attachment` DISABLE KEYS */;
INSERT INTO `ms_sys_attachment` VALUES (2,'org.kyll.myserver.business.meaord.entity.MeaordDishes','11','image/jpeg','286323.jpg','286323.jpg_abffd48dcb8b4337bbe1aa5094bb9e27',182972,'jpg'),(4,'org.kyll.myserver.business.meaord.entity.MeaordDishes','13','image/jpeg','280089.jpg','280089_dae6ba1250f7464787514ed28a35026f',772018,'jpg'),(5,'org.kyll.myserver.business.meaord.entity.MeaordDishes','14','image/jpeg','286323.jpg','286323_491ed1973bd54231a4d4a67f168aa722',182972,'jpg'),(6,'org.kyll.myserver.business.meaord.entity.MeaordDishes','15','image/jpeg','286323.jpg','286323_3673393848d04e6eb7f7b0dedef8f801',182972,'jpg'),(20,'org.kyll.myserver.business.meaord.entity.MeaordDishes','20','image/jpeg','53f98e7b69401b0c697f6c97.jpg','53f98e7b69401b0c697f6c97_bebdcb21f3544cdaa66eab047c77aefd',284388,'jpg'),(19,'org.kyll.myserver.business.meaord.entity.MeaordDishes','18','image/jpeg','54a3bd9569401b20920c802a.jpg','54a3bd9569401b20920c802a_f50a6f15f1b948ec903a54f213cb12cf',288988,'jpg'),(17,'org.kyll.myserver.business.meaord.entity.MeaordDishes','17','image/jpeg','53f6f30369401b0c697f6729.jpg','53f6f30369401b0c697f6729_882a33bdfe3849109d312a67a539a88a',499605,'jpg'),(18,'org.kyll.myserver.business.meaord.entity.MeaordDishes','19','image/jpeg','53ec62c1cb7b254103fbf95f.jpg','53ec62c1cb7b254103fbf95f_fc6a949d6cb54445b615673b25cedddd',208011,'jpg'),(22,'org.kyll.myserver.business.meaord.entity.MeaordDishes','23','image/jpeg','54a746af69401b50292971bb.jpg','54a746af69401b50292971bb_a2a660a6f48e466ea2370d11e42ee525',506188,'jpg'),(23,'org.kyll.myserver.business.meaord.entity.MeaordDishes','25','image/jpeg','5305b74c48d5b9432257235e.jpg','5305b74c48d5b9432257235e_b3f80c7427014c5ab3fa8a032db8f089',475362,'jpg'),(24,'org.kyll.myserver.business.meaord.entity.MeaordDishes','26','image/jpeg','53217f8f48d5b91c93bef343.jpg','53217f8f48d5b91c93bef343_d0f59883fe164f3f83c9d362677537e4',556815,'jpg'),(27,'org.kyll.myserver.business.meaord.entity.MeaordDishes','28','image/jpeg','5417f83569401b74e6739276.jpg','5417f83569401b74e6739276_0a907787e67845a38d7d8831dd027585',760333,'jpg'),(26,'org.kyll.myserver.business.meaord.entity.MeaordDishes','27','image/jpeg','5343a5d248d5b93cb1fd9999.jpg','5343a5d248d5b93cb1fd9999_33888ea498c94691b7005dd8356a69aa',716912,'jpg');
/*!40000 ALTER TABLE `ms_sys_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_sys_config`
--

DROP TABLE IF EXISTS `ms_sys_config`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_sys_config` (
  `id_` int(11) NOT NULL auto_increment,
  `key_` varchar(255) default NULL,
  `value_` varchar(255) default NULL,
  `sort_` int(11) default NULL,
  PRIMARY KEY  (`id_`),
  UNIQUE KEY `unique_id_` (`id_`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_sys_config`
--

LOCK TABLES `ms_sys_config` WRITE;
/*!40000 ALTER TABLE `ms_sys_config` DISABLE KEYS */;
INSERT INTO `ms_sys_config` VALUES (1,'ATTACHMENT_PATH','e:\\temp\\myserver',1),(2,'EMPLOYEE_DEFAULT_PASSWORD','123',2);
/*!40000 ALTER TABLE `ms_sys_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_sys_department`
--

DROP TABLE IF EXISTS `ms_sys_department`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_sys_department` (
  `id_` int(11) NOT NULL auto_increment,
  `parent_id_` int(11) default NULL,
  `name_` varchar(255) default NULL,
  `description_` varchar(255) default NULL,
  `sort_` int(11) default NULL,
  PRIMARY KEY  (`id_`),
  UNIQUE KEY `unique_id_` (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_sys_department`
--

LOCK TABLES `ms_sys_department` WRITE;
/*!40000 ALTER TABLE `ms_sys_department` DISABLE KEYS */;
INSERT INTO `ms_sys_department` VALUES (1,NULL,'部门1','描述1',1),(3,1,'部门2','描述2',1),(4,3,'部门3','',1),(5,1,'部门4','',1),(6,NULL,'部门5','',1);
/*!40000 ALTER TABLE `ms_sys_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_sys_dict`
--

DROP TABLE IF EXISTS `ms_sys_dict`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_sys_dict` (
  `id_` int(11) NOT NULL auto_increment,
  `invoke_code_` varchar(255) default NULL,
  `name_` varchar(255) default NULL,
  `parent_id_` int(11) default NULL,
  `sort_` int(11) default NULL,
  PRIMARY KEY  (`id_`),
  UNIQUE KEY `unique_id_` (`id_`),
  UNIQUE KEY `unique_invoke_code_` (`invoke_code_`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_sys_dict`
--

LOCK TABLES `ms_sys_dict` WRITE;
/*!40000 ALTER TABLE `ms_sys_dict` DISABLE KEYS */;
INSERT INTO `ms_sys_dict` VALUES (1,'sys','系统管理',NULL,1),(11,'app','应用管理',NULL,2),(12,'app_module','模块',11,1),(13,'app_module_type','类型',12,1),(14,'app_module_funcType','功能类型',12,2);
/*!40000 ALTER TABLE `ms_sys_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_sys_dict_item`
--

DROP TABLE IF EXISTS `ms_sys_dict_item`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_sys_dict_item` (
  `id_` int(11) NOT NULL auto_increment,
  `key_` varchar(255) default NULL,
  `value_` varchar(255) default NULL,
  `dict_id_` int(11) default NULL,
  `sort_` int(11) default NULL,
  PRIMARY KEY  (`id_`),
  UNIQUE KEY `unique_id_` (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_sys_dict_item`
--

LOCK TABLES `ms_sys_dict_item` WRITE;
/*!40000 ALTER TABLE `ms_sys_dict_item` DISABLE KEYS */;
INSERT INTO `ms_sys_dict_item` VALUES (12,'2','功能',13,2),(13,'1','模块',13,1),(14,'2','URL',14,2),(15,'1','JavaScript',14,1);
/*!40000 ALTER TABLE `ms_sys_dict_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_sys_role_function`
--

DROP TABLE IF EXISTS `ms_sys_role_function`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_sys_role_function` (
  `role_id_` int(11) NOT NULL,
  `function_id_` int(11) NOT NULL default '0',
  PRIMARY KEY  (`role_id_`,`function_id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_sys_role_function`
--

LOCK TABLES `ms_sys_role_function` WRITE;
/*!40000 ALTER TABLE `ms_sys_role_function` DISABLE KEYS */;
INSERT INTO `ms_sys_role_function` VALUES (3,27),(3,29),(3,33),(3,36),(3,39),(3,40),(3,41),(3,42),(3,43),(3,59),(3,60),(3,61),(5,29),(5,30),(5,31),(5,37),(5,38);
/*!40000 ALTER TABLE `ms_sys_role_function` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-22  5:46:20
