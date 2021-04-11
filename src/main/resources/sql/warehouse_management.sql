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

 Date: 12/04/2021 01:03:48
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
  `entry_goods_id` varchar(22) NOT NULL COMMENT '入库商品编号',
  `entry_goods_name` varchar(32) NOT NULL COMMENT '入库商品名',
  `entry_goods_number` bigint(20) NOT NULL COMMENT '入库商品数量',
  `entry_goods_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '入库商品时间',
  `before_entry_goods_number` bigint(20) NOT NULL COMMENT '入库之前商品数量',
  `after_entry_goods_number` bigint(20) NOT NULL COMMENT '入库之后商品数量',
  `operator_id` bigint(20) NOT NULL COMMENT '操作员Id',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of entry_warehouse_management
-- ----------------------------
BEGIN;
INSERT INTO `entry_warehouse_management` VALUES (31, 'E2021041122203411725', 'E2A6D8A19C494FAEBB7F81', 'Ipad Air 4', 200, '2021-04-11 22:20:34', 100, 300, 1, '2021-04-11 22:20:34');
INSERT INTO `entry_warehouse_management` VALUES (32, 'E2021041122265109833', 'E2A6D8A19C494FAEBB7F81', 'Ipad Air 4', 200, '2021-04-11 22:26:51', 300, 500, 1, '2021-04-11 22:26:51');
INSERT INTO `entry_warehouse_management` VALUES (33, 'E2021041122292907994', 'E2A6D8A19C494FAEBB7F81', 'Ipad Air 4', 2000, '2021-04-11 22:29:29', 400, 2400, 1, '2021-04-11 22:29:29');
INSERT INTO `entry_warehouse_management` VALUES (34, 'E2021041122300250911', 'E2A6D8A19C494FAEBB7F81', 'Ipad Air 4', 500, '2021-04-11 22:30:02', 2400, 2900, 1, '2021-04-11 22:30:02');
COMMIT;

-- ----------------------------
-- Table structure for goods_management
-- ----------------------------
DROP TABLE IF EXISTS `goods_management`;
CREATE TABLE `goods_management` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '货物主键',
  `goods_id` varchar(22) NOT NULL COMMENT '货物编号',
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_management
-- ----------------------------
BEGIN;
INSERT INTO `goods_management` VALUES (9, '41E62070EFAE43F6A74050', 'MacBook Pro1', 220, 11, 0, 1, 0, 0, 1, 200, 0, 5, '2021-04-10 12:51:44', '2021-04-11 14:10:59');
INSERT INTO `goods_management` VALUES (10, 'FF1261F06576442192F639', 'Iphone12', 101, 0, 0, 0, 0, 0, 1, 1000, 100, 1, '2021-04-10 15:43:45', '2021-04-10 08:03:02');
INSERT INTO `goods_management` VALUES (11, 'E2A6D8A19C494FAEBB7F81', 'Ipad Air 4', 900, 0, 0, 0, 0, 0, 1, 2000, 100, 3, '2021-04-10 15:48:05', '2021-04-11 14:33:01');
INSERT INTO `goods_management` VALUES (12, 'B7953246017C43AFAF6EF9', 'Apple Watch', 100, 0, 0, 0, 0, 0, 1, 100, 0, 2, '2021-04-10 15:54:15', '2021-04-10 15:54:15');
INSERT INTO `goods_management` VALUES (51, '1D7B08393DBB48DE9AD5CE', 'airPort Pro', 100, 0, 0, 0, 0, 0, 1, 1000, 0, 1, '2021-04-11 00:13:42', '2021-04-11 00:13:42');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_status_management
-- ----------------------------
BEGIN;
INSERT INTO `goods_status_management` VALUES (1, '00', '库存超过下限');
INSERT INTO `goods_status_management` VALUES (2, '01', '库存状态接近下限');
INSERT INTO `goods_status_management` VALUES (3, '09', '库存状态良好');
INSERT INTO `goods_status_management` VALUES (4, '10', '库存状态接近上限');
INSERT INTO `goods_status_management` VALUES (5, '11', '库存超过上限');
COMMIT;

-- ----------------------------
-- Table structure for out_warehouse_management
-- ----------------------------
DROP TABLE IF EXISTS `out_warehouse_management`;
CREATE TABLE `out_warehouse_management` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '出货主键',
  `out_order_id` varchar(20) NOT NULL COMMENT '出库订单号',
  `out_goods_id` varchar(22) NOT NULL COMMENT '出货商品编号',
  `out_goods_name` varchar(32) NOT NULL COMMENT '出货商品名',
  `out_goods_number` bigint(20) NOT NULL COMMENT '出货商品数量',
  `out_goods_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '出货商品时间',
  `before_out_goods_number` bigint(20) NOT NULL COMMENT '出货前商品数量',
  `after_out_goods_number` bigint(20) NOT NULL COMMENT '出货后商品数量',
  `operator_id` bigint(20) NOT NULL COMMENT '操作员Id',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of out_warehouse_management
-- ----------------------------
BEGIN;
INSERT INTO `out_warehouse_management` VALUES (4, 'L2021041122271603426', 'E2A6D8A19C494FAEBB7F81', 'Ipad Air 4', 100, '2021-04-11 22:27:16', 500, 400, 1, '2021-04-11 22:27:16');
INSERT INTO `out_warehouse_management` VALUES (5, 'L2021041122330079665', 'E2A6D8A19C494FAEBB7F81', 'Ipad Air 4', 2000, '2021-04-11 22:33:00', 2900, 900, 1, '2021-04-11 22:33:00');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of personnel_management
-- ----------------------------
BEGIN;
INSERT INTO `personnel_management` VALUES (1, 'admin', '123456', 1, 'heyin', 23, '13304312524', 0, '2021-04-01', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
