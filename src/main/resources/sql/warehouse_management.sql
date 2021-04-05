/*
 Navicat Premium Data Transfer

 Source Server         : car-data
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : warehouse_management

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 06/04/2021 07:29:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for entry_warehouse_management
-- ----------------------------
DROP TABLE IF EXISTS `entry_warehouse_management`;
CREATE TABLE `entry_warehouse_management` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '入库主键',
  `entry_order_id` varchar(20) NOT NULL COMMENT '入库订单号',
  `entry_goods_id` bigint(20) NOT NULL COMMENT '入库商品编号',
  `entry_goods_name` varchar(32) NOT NULL COMMENT '入库商品名',
  `entry_goods_number` bigint(20) NOT NULL COMMENT '入库商品数量',
  `entry_goods_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '入库商品时间',
  `before_entry_goods_number` bigint(20) NOT NULL COMMENT '入库之前商品数量',
  `after_entry_goods_number` bigint(20) NOT NULL COMMENT '入库之后商品数量',
  `operator_id` bigint(20) NOT NULL COMMENT '操作员Id',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of entry_warehouse_management
-- ----------------------------
BEGIN;
INSERT INTO `entry_warehouse_management` VALUES (1, 'E2021040413323787506', 1, 'Apple笔记本电脑', 100, '2021-04-04 13:45:12', 0, 100, 1, '2021-04-04 13:45:28');
INSERT INTO `entry_warehouse_management` VALUES (2, 'E2021040413323787506', 2, 'iphone12', 100, '2021-04-04 18:27:29', 20, 120, 1, '2021-04-04 18:27:35');
INSERT INTO `entry_warehouse_management` VALUES (3, 'E2021040419112847818', 3, '暂时随便填了', 11, '2021-04-04 19:11:28', 0, 11, 1, '2021-04-04 19:11:28');
INSERT INTO `entry_warehouse_management` VALUES (4, 'E2021040421221844157', 2, '暂时随便填了', 0, '2021-04-04 21:22:18', 0, 0, 1, '2021-04-04 21:22:18');
INSERT INTO `entry_warehouse_management` VALUES (5, 'E2021040421222090246', 2, '暂时随便填了', 0, '2021-04-04 21:22:20', 0, 0, 1, '2021-04-04 21:22:20');
INSERT INTO `entry_warehouse_management` VALUES (6, 'E2021040421222161324', 2, '暂时随便填了', 0, '2021-04-04 21:22:21', 0, 0, 1, '2021-04-04 21:22:21');
INSERT INTO `entry_warehouse_management` VALUES (7, 'E2021040421222181491', 2, '暂时随便填了', 0, '2021-04-04 21:22:21', 0, 0, 1, '2021-04-04 21:22:21');
INSERT INTO `entry_warehouse_management` VALUES (8, 'E2021040421222202282', 2, '暂时随便填了', 0, '2021-04-04 21:22:22', 0, 0, 1, '2021-04-04 21:22:22');
INSERT INTO `entry_warehouse_management` VALUES (9, 'E2021040421222220752', 2, '暂时随便填了', 0, '2021-04-04 21:22:22', 0, 0, 1, '2021-04-04 21:22:22');
INSERT INTO `entry_warehouse_management` VALUES (10, 'E2021040421223933824', 2, '暂时随便填了', 0, '2021-04-04 21:22:39', 0, 0, 1, '2021-04-04 21:22:39');
INSERT INTO `entry_warehouse_management` VALUES (11, 'E2021040421251070377', 2, '暂时随便填了', 0, '2021-04-04 21:25:10', 0, 0, 1, '2021-04-04 21:25:10');
INSERT INTO `entry_warehouse_management` VALUES (12, 'E2021040421251234172', 2, '暂时随便填了', 0, '2021-04-04 21:25:12', 0, 0, 1, '2021-04-04 21:25:12');
INSERT INTO `entry_warehouse_management` VALUES (13, 'E2021040421254116219', 2, '暂时随便填了', 0, '2021-04-04 21:25:41', 0, 0, 1, '2021-04-04 21:25:41');
INSERT INTO `entry_warehouse_management` VALUES (14, 'E2021040421254239911', 2, '暂时随便填了', 0, '2021-04-04 21:25:42', 0, 0, 1, '2021-04-04 21:25:42');
INSERT INTO `entry_warehouse_management` VALUES (15, 'E2021040421254256719', 2, '暂时随便填了', 0, '2021-04-04 21:25:42', 0, 0, 1, '2021-04-04 21:25:42');
INSERT INTO `entry_warehouse_management` VALUES (16, 'E2021040421254290612', 2, '暂时随便填了', 0, '2021-04-04 21:25:42', 0, 0, 1, '2021-04-04 21:25:42');
INSERT INTO `entry_warehouse_management` VALUES (17, 'E2021040421254338625', 2, '暂时随便填了', 0, '2021-04-04 21:25:43', 0, 0, 1, '2021-04-04 21:25:43');
INSERT INTO `entry_warehouse_management` VALUES (18, 'E2021040421254417639', 2, '暂时随便填了', 0, '2021-04-04 21:25:44', 0, 0, 1, '2021-04-04 21:25:44');
INSERT INTO `entry_warehouse_management` VALUES (19, 'E2021040421254441860', 2, '暂时随便填了', 0, '2021-04-04 21:25:44', 0, 0, 1, '2021-04-04 21:25:44');
INSERT INTO `entry_warehouse_management` VALUES (20, 'E2021040421254460677', 2, '暂时随便填了', 0, '2021-04-04 21:25:44', 0, 0, 1, '2021-04-04 21:25:44');
INSERT INTO `entry_warehouse_management` VALUES (21, 'E2021040421254496868', 2, '暂时随便填了', 0, '2021-04-04 21:25:44', 0, 0, 1, '2021-04-04 21:25:44');
INSERT INTO `entry_warehouse_management` VALUES (22, 'E2021040421260389159', 2, '暂时随便填了', 0, '2021-04-04 21:26:03', 0, 0, 1, '2021-04-04 21:26:03');
INSERT INTO `entry_warehouse_management` VALUES (23, 'E2021040421260448836', 2, '暂时随便填了', 0, '2021-04-04 21:26:04', 0, 0, 1, '2021-04-04 21:26:04');
INSERT INTO `entry_warehouse_management` VALUES (24, 'E2021040421281312993', 2, '暂时随便填了', 0, '2021-04-04 21:28:13', 0, 0, 1, '2021-04-04 21:28:13');
INSERT INTO `entry_warehouse_management` VALUES (25, 'E2021040421300743631', 2, '暂时随便填了', 0, '2021-04-04 21:30:07', 0, 0, 1, '2021-04-04 21:30:07');
INSERT INTO `entry_warehouse_management` VALUES (26, 'E2021040421315605328', 2, '暂时随便填了', 0, '2021-04-04 21:31:56', 0, 0, 1, '2021-04-04 21:31:56');
INSERT INTO `entry_warehouse_management` VALUES (27, 'E2021040421355396799', 2, '暂时随便填了', 0, '2021-04-04 21:35:53', 0, 0, 1, '2021-04-04 21:35:53');
COMMIT;

-- ----------------------------
-- Table structure for goods_management
-- ----------------------------
DROP TABLE IF EXISTS `goods_management`;
CREATE TABLE `goods_management` (
  `goods_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '货物主键',
  `goods_name` varchar(32) NOT NULL COMMENT '货物名字',
  `goods_number` bigint(20) NOT NULL COMMENT '货物数量',
  `yesterday_goods_number` bigint(20) DEFAULT '0' COMMENT '昨天货物数量',
  `last_week_goods_number` bigint(20) DEFAULT '0' COMMENT '上周货物数量',
  `last_month_goods_number` bigint(20) DEFAULT '0' COMMENT '上月货物数量',
  `last_quarter_goods_number` bigint(20) DEFAULT '0' COMMENT '上季度货物数量',
  `last_year_goods_number` bigint(20) DEFAULT '0' COMMENT '去年货物数量',
  `last_operator_id` bigint(20) NOT NULL COMMENT '最后一位操作该货物的操作员id',
  `quantity_ceiling` bigint(20) NOT NULL COMMENT '货物上限',
  `quantity_floor` bigint(20) NOT NULL COMMENT '货物下限',
  `goods_status_id` int(11) NOT NULL COMMENT '库存状态id',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_management
-- ----------------------------
BEGIN;
INSERT INTO `goods_management` VALUES (1, 'Apple笔记本电脑', 100, 0, 0, 0, 0, 0, 1, 1000, 20, 0, '2021-04-04 00:00:00', '2021-04-04 00:00:00');
INSERT INTO `goods_management` VALUES (2, 'Iphone12', 100, 0, 0, 0, 0, 0, 1, 500, 0, 1, '2021-04-04 21:57:57', '2021-04-04 21:57:57');
COMMIT;

-- ----------------------------
-- Table structure for goods_status_management
-- ----------------------------
DROP TABLE IF EXISTS `goods_status_management`;
CREATE TABLE `goods_status_management` (
  `goods_status_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '货物状态主键',
  `goods_status` varchar(10) NOT NULL COMMENT '货物状态',
  `goods_status_desc` varchar(100) NOT NULL COMMENT '货物状态描述',
  PRIMARY KEY (`goods_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_status_management
-- ----------------------------
BEGIN;
INSERT INTO `goods_status_management` VALUES (1, '09', '库存状态良好');
INSERT INTO `goods_status_management` VALUES (2, '10', '库存状态接近上限');
INSERT INTO `goods_status_management` VALUES (3, '01', '库存状态接近下限');
COMMIT;

-- ----------------------------
-- Table structure for out_warehouse_management
-- ----------------------------
DROP TABLE IF EXISTS `out_warehouse_management`;
CREATE TABLE `out_warehouse_management` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '出货主键',
  `out_order_id` varchar(20) NOT NULL COMMENT '出库订单号',
  `out_goods_id` bigint(20) NOT NULL COMMENT '出货商品编号',
  `out_goods_name` varchar(32) NOT NULL COMMENT '出货商品名',
  `out_goods_number` bigint(20) NOT NULL COMMENT '出货商品数量',
  `out_goods_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '出货商品时间',
  `before_out_goods_number` bigint(20) NOT NULL COMMENT '出货前商品数量',
  `after_out_goods_number` bigint(20) NOT NULL COMMENT '出货后商品数量',
  `operator_id` bigint(20) NOT NULL COMMENT '操作员Id',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for permission_management
-- ----------------------------
DROP TABLE IF EXISTS `permission_management`;
CREATE TABLE `permission_management` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '级别主键',
  `permission_name` varchar(20) NOT NULL COMMENT '级别名称',
  `permission_description` varchar(100) NOT NULL COMMENT '具体权利描述',
  `responsibility` varchar(50) NOT NULL COMMENT '职责',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission_management
-- ----------------------------
BEGIN;
INSERT INTO `permission_management` VALUES (1, '超级管理员', '管理所有的管理员', '最高权限');
INSERT INTO `permission_management` VALUES (2, '管理员', '管理所有员工', '员工管理');
INSERT INTO `permission_management` VALUES (3, '普通员工', '对自己资料的修改', '去添加入库，出库信息，维护库存');
COMMIT;

-- ----------------------------
-- Table structure for personnel_management
-- ----------------------------
DROP TABLE IF EXISTS `personnel_management`;
CREATE TABLE `personnel_management` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '人员主键',
  `user_name` varchar(32) NOT NULL COMMENT '用户名',
  `user_password` varchar(32) NOT NULL DEFAULT '123456' COMMENT '密码',
  `permission_level` int(6) NOT NULL COMMENT '人员级别,0超级管理员，1管理员，2普通员工',
  `name` varchar(32) NOT NULL COMMENT '人员姓名',
  `age` int(6) DEFAULT NULL COMMENT '人员年龄',
  `tel` varchar(11) NOT NULL COMMENT '人员电话',
  `sex` tinyint(4) NOT NULL COMMENT '性别',
  `join_time` date NOT NULL COMMENT '加入时间',
  `authorize` tinyint(4) NOT NULL COMMENT '是否授权',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of personnel_management
-- ----------------------------
BEGIN;
INSERT INTO `personnel_management` VALUES (2, 'admin', '123456', 0, 'heyin', 23, '13304312524', 0, '2021-04-01', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
