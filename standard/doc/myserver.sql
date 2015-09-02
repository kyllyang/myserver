-- MySQL dump 10.11
--
-- Host: localhost    Database: myserver
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_app_menu`
--

LOCK TABLES `ms_app_menu` WRITE;
/*!40000 ALTER TABLE `ms_app_menu` DISABLE KEYS */;
INSERT INTO `ms_app_menu` VALUES (1,NULL,'系统维护','',3,NULL),(2,1,'组织机构','',1,33),(3,1,'角色管理','',2,29),(4,1,'数据字典','',3,36),(5,NULL,'应用模块','',2,NULL),(6,NULL,'地图管理','',1,NULL),(7,5,'模块管理','',1,40),(8,5,'菜单管理','',2,43),(11,6,'专题管理','',3,59),(12,1,'附件管理','',4,60),(13,1,'系统配置','',5,61),(14,6,'图层配置','',1,62),(15,6,'地图配置','',2,63);
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
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_app_menu_application_thematic`
--

LOCK TABLES `ms_app_menu_application_thematic` WRITE;
/*!40000 ALTER TABLE `ms_app_menu_application_thematic` DISABLE KEYS */;
INSERT INTO `ms_app_menu_application_thematic` VALUES (1,41,1,22),(5,41,1,21),(6,41,1,26),(2,37,3,25);
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
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_app_module`
--

LOCK TABLES `ms_app_module` WRITE;
/*!40000 ALTER TABLE `ms_app_module` DISABLE KEYS */;
INSERT INTO `ms_app_module` VALUES (27,41,'系统管理','',3,'1',NULL,NULL),(29,27,'角色管理','',2,'2','1','{className: \'Base.sys.role.RoleContainer\', config: {}}'),(33,27,'组织机构','',1,'2','1','{className: \'Base.sys.org.OrgContainer\', config: {}}'),(36,27,'数据字典','',3,'2','1','{className: \'Base.sys.dict.DictContainer\', config: {}}'),(37,NULL,'餐饮订单','',1,'1',NULL,NULL),(38,37,'餐厅菜品维护','',1,'2','1','{className: \'Meaord.restaurant.RestaurantGridPanel\', config: {}}'),(39,41,'应用管理','',2,'1',NULL,NULL),(40,39,'模块管理','',1,'2','1','{className: \'Base.app.module.ModuleContainer\', config: {}}'),(41,NULL,'运行维护','',1,'1',NULL,NULL),(42,41,'地图管理','',1,'1',NULL,NULL),(43,39,'菜单管理','',2,'2','1','{className: \'Base.app.menu.MenuContainer\', config: {}}'),(58,53,'aa','',1,'1',NULL,NULL),(59,42,'专题管理','',3,'2','1','{className: \'Base.gis.thematic.ThematicContainer\', config: {}}'),(60,27,'附件管理','',4,'2','1','{className: \'Base.sys.attachment.AttachmentContainer\', config: {}}'),(61,27,'系统配置','',5,'2','1','{className: \'Base.sys.config.ConfigContainer\', config: {}}'),(62,42,'图层配置','',1,'2','1','{className: \'Base.gis.layer.LayerContainer\', config: {}}'),(63,42,'地图配置','',2,'2','1','{className: \'Base.gis.map.MapContainer\', config: {}}');
/*!40000 ALTER TABLE `ms_app_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_gis_ol_control`
--

DROP TABLE IF EXISTS `ms_gis_ol_control`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_gis_ol_control` (
  `id_` int(11) NOT NULL auto_increment,
  `map_id_` int(11) default NULL,
  `control_class_name_` varchar(255) default NULL,
  `auto_hide_` varchar(255) default NULL,
  `collapsed_` varchar(255) default NULL,
  `collapse_label_` varchar(255) default NULL,
  `collapsible_` varchar(255) default NULL,
  `coordinate_format_` varchar(255) default NULL,
  `delta_` varchar(255) default NULL,
  `duration_` varchar(255) default NULL,
  `extent_` varchar(255) default NULL,
  `keys_` varchar(255) default NULL,
  `label_` varchar(255) default NULL,
  `label_active_` varchar(255) default NULL,
  `layers_` varchar(255) default NULL,
  `max_resolution_` varchar(255) default NULL,
  `min_resolution_` varchar(255) default NULL,
  `min_width_` varchar(255) default NULL,
  `projection_` varchar(255) default NULL,
  `tip_label_` varchar(255) default NULL,
  `undefined_html_` varchar(255) default NULL,
  `units_` varchar(255) default NULL,
  `zoom_in_label_` varchar(255) default NULL,
  `zoom_in_tip_label_` varchar(255) default NULL,
  `zoom_out_label_` varchar(255) default NULL,
  `zoom_out_tip_label_` varchar(255) default NULL,
  PRIMARY KEY  (`id_`),
  UNIQUE KEY `id__UNIQUE` (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_gis_ol_control`
--

LOCK TABLES `ms_gis_ol_control` WRITE;
/*!40000 ALTER TABLE `ms_gis_ol_control` DISABLE KEYS */;
/*!40000 ALTER TABLE `ms_gis_ol_control` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_gis_ol_interaction`
--

DROP TABLE IF EXISTS `ms_gis_ol_interaction`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_gis_ol_interaction` (
  `id_` int(11) NOT NULL auto_increment,
  `map_id_` int(11) default NULL,
  `interaction_class_name_` varchar(255) default NULL,
  `add_condition_` varchar(255) default NULL,
  `condition_` varchar(255) default NULL,
  `delta_` varchar(255) default NULL,
  `duration_` varchar(255) default NULL,
  `filter_` varchar(255) default NULL,
  `format_constructors_` varchar(255) default NULL,
  `handle_down_event_` varchar(255) default NULL,
  `handle_drag_event_` varchar(255) default NULL,
  `handle_event_` varchar(255) default NULL,
  `handle_move_event_` varchar(255) default NULL,
  `handle_up_event_` varchar(255) default NULL,
  `layers_` varchar(255) default NULL,
  `multi_` varchar(255) default NULL,
  `pixel_delta_` varchar(255) default NULL,
  `projection_` varchar(255) default NULL,
  `remove_condition_` varchar(255) default NULL,
  `style_` varchar(255) default NULL,
  `toggle_condition_` varchar(255) default NULL,
  `wrap_x_` varchar(255) default NULL,
  PRIMARY KEY  (`id_`),
  UNIQUE KEY `id__UNIQUE` (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_gis_ol_interaction`
--

LOCK TABLES `ms_gis_ol_interaction` WRITE;
/*!40000 ALTER TABLE `ms_gis_ol_interaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `ms_gis_ol_interaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_gis_ol_layer`
--

DROP TABLE IF EXISTS `ms_gis_ol_layer`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_gis_ol_layer` (
  `id_` int(11) NOT NULL auto_increment,
  `brightness_` varchar(255) default NULL,
  `contrast_` varchar(255) default NULL,
  `hue_` varchar(255) default NULL,
  `opacity_` varchar(255) default NULL,
  `saturation_` varchar(255) default NULL,
  `visible_` varchar(255) default NULL,
  `extent_` varchar(255) default NULL,
  `min_resolution_` varchar(255) default NULL,
  `max_resolution_` varchar(255) default NULL,
  `source_class_name_` varchar(255) default NULL,
  `layer_class_name_` varchar(255) default NULL,
  PRIMARY KEY  (`id_`),
  UNIQUE KEY `id__UNIQUE` (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_gis_ol_layer`
--

LOCK TABLES `ms_gis_ol_layer` WRITE;
/*!40000 ALTER TABLE `ms_gis_ol_layer` DISABLE KEYS */;
/*!40000 ALTER TABLE `ms_gis_ol_layer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_gis_ol_map`
--

DROP TABLE IF EXISTS `ms_gis_ol_map`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_gis_ol_map` (
  `id_` int(11) NOT NULL auto_increment,
  `load_tiles_while_animating_` varchar(255) default NULL,
  `load_tiles_while_interacting_` varchar(255) default NULL,
  `logo_` varchar(255) default NULL,
  `renderer_` varchar(255) default NULL,
  `name_` varchar(255) default NULL,
  PRIMARY KEY  (`id_`),
  UNIQUE KEY `unique_id_` (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_gis_ol_map`
--

LOCK TABLES `ms_gis_ol_map` WRITE;
/*!40000 ALTER TABLE `ms_gis_ol_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `ms_gis_ol_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_gis_ol_overlay`
--

DROP TABLE IF EXISTS `ms_gis_ol_overlay`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_gis_ol_overlay` (
  `id_` int(11) NOT NULL auto_increment,
  `map_id_` int(11) default NULL,
  `element_` varchar(255) default NULL,
  `offset_` varchar(255) default NULL,
  `position_` varchar(255) default NULL,
  `positioning_` varchar(255) default NULL,
  `stop_event_` varchar(255) default NULL,
  `insert_first_` varchar(255) default NULL,
  `auto_pan_` varchar(255) default NULL,
  `auto_pan_animation_` varchar(255) default NULL,
  `auto_pan_margin_` varchar(255) default NULL,
  PRIMARY KEY  (`id_`),
  UNIQUE KEY `id__UNIQUE` (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_gis_ol_overlay`
--

LOCK TABLES `ms_gis_ol_overlay` WRITE;
/*!40000 ALTER TABLE `ms_gis_ol_overlay` DISABLE KEYS */;
/*!40000 ALTER TABLE `ms_gis_ol_overlay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_gis_ol_view`
--

DROP TABLE IF EXISTS `ms_gis_ol_view`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_gis_ol_view` (
  `id_` int(11) NOT NULL auto_increment,
  `map_id_` int(11) default NULL,
  `center_` varchar(255) default NULL,
  `constrain_rotation_` varchar(255) default NULL,
  `enable_rotation_` varchar(255) default NULL,
  `extent_` varchar(255) default NULL,
  `max_resolution_` varchar(255) default NULL,
  `min_resolution_` varchar(255) default NULL,
  `max_zoom_` varchar(255) default NULL,
  `min_zoom_` varchar(255) default NULL,
  `projection_` varchar(255) default NULL,
  `resolution_` varchar(255) default NULL,
  `resolutions_` varchar(255) default NULL,
  `rotation_` varchar(255) default NULL,
  `zoom_` varchar(255) default NULL,
  `zoomFactor_` varchar(255) default NULL,
  PRIMARY KEY  (`id_`),
  UNIQUE KEY `id__UNIQUE` (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_gis_ol_view`
--

LOCK TABLES `ms_gis_ol_view` WRITE;
/*!40000 ALTER TABLE `ms_gis_ol_view` DISABLE KEYS */;
/*!40000 ALTER TABLE `ms_gis_ol_view` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_gis_thematic`
--

DROP TABLE IF EXISTS `ms_gis_thematic`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_gis_thematic` (
  `id_` int(11) NOT NULL auto_increment,
  `name_` varchar(255) default NULL,
  `sort_` int(11) default NULL,
  `map_id_` int(11) default NULL,
  PRIMARY KEY  (`id_`),
  UNIQUE KEY `unique_id_` (`id_`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_gis_thematic`
--

LOCK TABLES `ms_gis_thematic` WRITE;
/*!40000 ALTER TABLE `ms_gis_thematic` DISABLE KEYS */;
INSERT INTO `ms_gis_thematic` VALUES (1,'专题一',1,NULL),(3,'专题二',2,NULL),(4,'专题三',3,NULL),(5,'专题四',4,NULL);
/*!40000 ALTER TABLE `ms_gis_thematic` ENABLE KEYS */;
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
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
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
-- Table structure for table `ms_sys_employee`
--

DROP TABLE IF EXISTS `ms_sys_employee`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_sys_employee` (
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_sys_employee`
--

LOCK TABLES `ms_sys_employee` WRITE;
/*!40000 ALTER TABLE `ms_sys_employee` DISABLE KEYS */;
INSERT INTO `ms_sys_employee` VALUES (1,1,'admin','40bd001563085fc35165329ea1ff5c5ecbdbbeef',0,'杨磊',1,'kyllyang@gmail.com'),(2,1,'cjc','40bd001563085fc35165329ea1ff5c5ecbdbbeef',1,'赵嘉琛',6,'');
/*!40000 ALTER TABLE `ms_sys_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_sys_employee_role`
--

DROP TABLE IF EXISTS `ms_sys_employee_role`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_sys_employee_role` (
  `employee_id_` int(11) NOT NULL default '0',
  `role_id_` int(11) NOT NULL default '0',
  PRIMARY KEY  (`employee_id_`,`role_id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_sys_employee_role`
--

LOCK TABLES `ms_sys_employee_role` WRITE;
/*!40000 ALTER TABLE `ms_sys_employee_role` DISABLE KEYS */;
INSERT INTO `ms_sys_employee_role` VALUES (1,3);
/*!40000 ALTER TABLE `ms_sys_employee_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ms_sys_role`
--

DROP TABLE IF EXISTS `ms_sys_role`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ms_sys_role` (
  `id_` int(11) NOT NULL auto_increment,
  `name_` varchar(255) default NULL,
  `description_` varchar(255) default NULL,
  `sort_` int(11) default NULL,
  PRIMARY KEY  (`id_`),
  UNIQUE KEY `unique_id_` (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ms_sys_role`
--

LOCK TABLES `ms_sys_role` WRITE;
/*!40000 ALTER TABLE `ms_sys_role` DISABLE KEYS */;
INSERT INTO `ms_sys_role` VALUES (3,'系统管理员','x',1),(5,'餐饮订单管理员','',2);
/*!40000 ALTER TABLE `ms_sys_role` ENABLE KEYS */;
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
INSERT INTO `ms_sys_role_function` VALUES (3,27),(3,29),(3,33),(3,36),(3,39),(3,40),(3,41),(3,42),(3,43),(3,59),(3,60),(3,61),(3,62),(3,63),(5,29),(5,30),(5,31),(5,37),(5,38);
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

-- Dump completed on 2015-09-02  8:12:12
