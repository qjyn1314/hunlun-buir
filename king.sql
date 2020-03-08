/*
 Navicat Premium Data Transfer

 Source Server         : 47.104.78.115_3308
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 47.104.78.115:3308
 Source Schema         : king

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 08/03/2020 17:28:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ko_user
-- ----------------------------
DROP TABLE IF EXISTS `ko_user`;
CREATE TABLE `ko_user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(25) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '用户名(登录名)',
  `real_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `identity_card` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '身份证号',
  `user_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `sex` smallint(1) NULL DEFAULT 2 COMMENT '1--男；2--女；',
  `age` int(10) NULL DEFAULT NULL COMMENT '年龄',
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '登录密码',
  `salt` varchar(10) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '权限加盐',
  `postal_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '通讯地址',
  `contact_number` varchar(12) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '联系电话',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '用户最后登录时间',
  `creater` bigint(10) NULL DEFAULT NULL COMMENT '创建者',
  `creater_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updater` bigint(10) NULL DEFAULT NULL COMMENT '更新者',
  `updater_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ko_user
-- ----------------------------
INSERT INTO `ko_user` VALUES (7, 'kingperson', '测试使用', '猜猜看', 'qjyn1314@foxmail.com', 2, 0, 'f57e44c108de7fcabf4322bbaf7df74c', 'yH4Gri', '北京昌平霍营', '110', '2018-08-27 20:27:44', 0, '2018-07-31 23:55:11', 7, '2018-09-30 21:49:14');
INSERT INTO `ko_user` VALUES (8, 'qjynkow', NULL, NULL, 'didu1314@foxmail.com', 2, 0, 'e1a4c3decd13ef3d3e3e21da5f59ec37', 'Z0YIjv', NULL, NULL, NULL, 0, '2018-08-01 00:03:58', 0, '2018-08-01 00:03:58');
INSERT INTO `ko_user` VALUES (9, 'admin', '王俊明', '410521199611261514', 'qjyn1314@163.com', 1, 22, '847147f9a561650ac6fedf2ba68bf12e', 'SDx0Zd', '北京昌平', '15321355715', '2018-10-14 16:26:39', 0, '2018-08-07 22:28:53', 9, '2018-08-27 22:45:08');
INSERT INTO `ko_user` VALUES (10, 'asdasdsad', NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `ko_user` VALUES (22, '排骨-2020-01-29 23:07:29', NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `ko_user` VALUES (23, '排骨-2020-01-29 23:09:17', NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `ko_user` VALUES (27, '排骨-2020-02-29 12:20:05', NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `ko_user` VALUES (28, '2020-02-29 12:27:22', NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名称',
  `user_mail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户邮箱',
  `user_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户密码',
  `password_salt` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码盐值',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (51, 'admin', 'qjyn1314@163.com', 'admin', '', '2020-02-14 18:20:18', '2020-02-14 18:20:18');
INSERT INTO `user` VALUES (52, 'admin', 'qjyn1314@163.com', 'admin', '', '2020-02-14 18:21:14', '2020-02-14 18:21:14');

SET FOREIGN_KEY_CHECKS = 1;
