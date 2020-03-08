/*
 Navicat Premium Data Transfer

 Source Server         : 47.104.78.115_3308
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 47.104.78.115:3308
 Source Schema         : collection

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 08/03/2020 17:27:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for org
-- ----------------------------
DROP TABLE IF EXISTS `org`;
CREATE TABLE `org`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父级ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_parentid_id`(`id`, `parent_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of org
-- ----------------------------
INSERT INTO `org` VALUES (1, 'one', 0);
INSERT INTO `org` VALUES (2, 'two', 0);
INSERT INTO `org` VALUES (3, 'three', 0);
INSERT INTO `org` VALUES (4, 'four', 0);
INSERT INTO `org` VALUES (5, 'five', 0);
INSERT INTO `org` VALUES (6, 'six', 0);
INSERT INTO `org` VALUES (7, 'seven', 0);
INSERT INTO `org` VALUES (8, 'eight', 0);
INSERT INTO `org` VALUES (9, 'night', 0);
INSERT INTO `org` VALUES (10, 'ten', 0);
INSERT INTO `org` VALUES (11, '一', 1);
INSERT INTO `org` VALUES (12, '二', 2);
INSERT INTO `org` VALUES (13, '三', 3);
INSERT INTO `org` VALUES (14, '四', 4);
INSERT INTO `org` VALUES (15, '五', 5);
INSERT INTO `org` VALUES (16, '六', 6);
INSERT INTO `org` VALUES (17, '七', 7);
INSERT INTO `org` VALUES (18, '八', 8);
INSERT INTO `org` VALUES (19, '九', 9);
INSERT INTO `org` VALUES (20, '十', 10);
INSERT INTO `org` VALUES (21, '壹', 1);
INSERT INTO `org` VALUES (22, '贰', 2);
INSERT INTO `org` VALUES (23, '叁', 3);
INSERT INTO `org` VALUES (24, '肆', 4);
INSERT INTO `org` VALUES (25, '伍', 5);
INSERT INTO `org` VALUES (26, '陆', 6);
INSERT INTO `org` VALUES (27, '柒', 7);
INSERT INTO `org` VALUES (28, '捌', 8);
INSERT INTO `org` VALUES (29, '玖', 9);
INSERT INTO `org` VALUES (30, '拾', 10);
INSERT INTO `org` VALUES (31, '壹', 1);
INSERT INTO `org` VALUES (36, '顶级组织', 0);
INSERT INTO `org` VALUES (38, '顶级组织-2020-02-29 12:20:05', 2);
INSERT INTO `org` VALUES (40, '2020-02-29 12:27:22', 0);

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime(0) NOT NULL,
  `log_modified` datetime(0) NOT NULL,
  `ext` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `context` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
