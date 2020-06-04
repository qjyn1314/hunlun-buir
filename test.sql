/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : 127.0.0.1:3306
 Source Schema         : spring

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 04/06/2020 11:49:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bankmoney
-- ----------------------------
DROP TABLE IF EXISTS `bankmoney`;
CREATE TABLE `bankmoney`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表主键',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `money` int(10) NULL DEFAULT NULL COMMENT '金额',
  `data_json` json NULL COMMENT '测试json字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bankmoney
-- ----------------------------
INSERT INTO `bankmoney` VALUES (1, 'jack', 5000, '{\"blog\": \"https://www.cnblogs.com/captainad\", \"author\": \"Captain&D\"}');
INSERT INTO `bankmoney` VALUES (2, 'luse', 15000, '{\"blog\": \"https://www.cnblogs.com/captainad\", \"author\": \"Captain&D\"}');
INSERT INTO `bankmoney` VALUES (3, 'jack', 5000, NULL);
INSERT INTO `bankmoney` VALUES (4, 'luse', 15000, NULL);
INSERT INTO `bankmoney` VALUES (5, 'jack', 5000, NULL);

-- ----------------------------
-- Table structure for com_data_parameter
-- ----------------------------
DROP TABLE IF EXISTS `com_data_parameter`;
CREATE TABLE `com_data_parameter`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `is_enable` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for com_data_parameter_value
-- ----------------------------
DROP TABLE IF EXISTS `com_data_parameter_value`;
CREATE TABLE `com_data_parameter_value`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `para_id` int(11) NOT NULL,
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `pid` int(11) NULL DEFAULT NULL,
  `is_enable` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `order` int(11) NULL DEFAULT NULL,
  `para_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `para_id`(`para_id`) USING BTREE,
  INDEX `PARAVALUE_CODE`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
