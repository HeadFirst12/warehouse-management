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

 Date: 01/04/2021 01:05:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for entry_warehouse_management
-- ----------------------------
DROP TABLE IF EXISTS `entry_warehouse_management`;
CREATE TABLE `entry_warehouse_management` (
  `entry_goods_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '入库主键',
  `entry_order_id` varchar(20) NOT NULL COMMENT '入库订单号',
  `entry_goods_name` varchar(32) NOT NULL COMMENT '入库商品名',
  `entry_goods_number` bigint(20) NOT NULL COMMENT '入库商品数量',
  `entry_goods_time` date NOT NULL COMMENT '入库商品时间',
  `before_entry_goods_number` bigint(20) NOT NULL COMMENT '入库之前商品数量',
  `after_entry_goods_number` bigint(20) NOT NULL COMMENT '入库之后商品数量',
  `operator_id` bigint(20) NOT NULL COMMENT '操作员Id',
  `create_time` date NOT NULL COMMENT '创建时间',
  `update_time` date NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`entry_goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for goods_management
-- ----------------------------
DROP TABLE IF EXISTS `goods_management`;
CREATE TABLE `goods_management` (
  `goods_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '货物主键',
  `goods_name` varchar(32) NOT NULL COMMENT '货物名字',
  `goods_number` bigint(20) NOT NULL COMMENT '货物数量',
  `yesterday_goods_number` bigint(20) DEFAULT NULL COMMENT '昨天货物数量',
  `last_week_goods_number` bigint(20) DEFAULT NULL COMMENT '上周货物数量',
  `last_month_goods_number` bigint(20) DEFAULT NULL COMMENT '上月货物数量',
  `last_quarter_goods_number` bigint(20) DEFAULT NULL COMMENT '上季度货物数量',
  `last_year_goods_number` bigint(20) DEFAULT NULL COMMENT '去年货物数量',
  `last_operator_id` bigint(20) NOT NULL COMMENT '最后一位操作该货物的操作员id',
  `quantity_ceiling` bigint(20) NOT NULL COMMENT '货物上限',
  `quantity_floor` bigint(20) NOT NULL COMMENT '货物下限',
  `goods_status_id` int(11) NOT NULL COMMENT '库存状态id',
  `create_time` date NOT NULL COMMENT '创建时间',
  `update_time` date NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for goods_status_management
-- ----------------------------
DROP TABLE IF EXISTS `goods_status_management`;
CREATE TABLE `goods_status_management` (
  `goods_status_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '货物状态主键',
  `goods_status` varchar(10) NOT NULL COMMENT '货物状态',
  `goods_status_desc` varchar(100) NOT NULL COMMENT '货物状态描述',
  PRIMARY KEY (`goods_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for out_warehouse_management
-- ----------------------------
DROP TABLE IF EXISTS `out_warehouse_management`;
CREATE TABLE `out_warehouse_management` (
  `out_goods_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '出货主键',
  `out_order_id` varchar(20) NOT NULL COMMENT '出库订单号',
  `out_goods_name` varchar(32) NOT NULL COMMENT '出货商品名',
  `out_goods_number` bigint(20) NOT NULL COMMENT '出货商品数量',
  `out_goods_time` date NOT NULL COMMENT '出货商品时间',
  `before_out_goods_number` bigint(20) NOT NULL COMMENT '出货前商品数量',
  `after_out_goods_number` bigint(20) NOT NULL COMMENT '出货后商品数量',
  `operator_id` bigint(20) NOT NULL COMMENT '操作员Id',
  `create_time` date NOT NULL COMMENT '创建时间',
  `update_time` date NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`out_goods_id`)
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
