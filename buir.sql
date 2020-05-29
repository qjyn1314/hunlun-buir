/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : 127.0.0.1:3306
 Source Schema         : buir

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 29/05/2020 09:51:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for buir_permission
-- ----------------------------
DROP TABLE IF EXISTS `buir_permission`;
CREATE TABLE `buir_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `permission` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '权限名称',
  `permission_url` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '权限路径',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '权限说明',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of buir_permission
-- ----------------------------
INSERT INTO `buir_permission` VALUES (1, 'permission:admin', NULL, '超级管理权限', '2017-12-12 09:46:12', '2017-12-12 09:46:12');
INSERT INTO `buir_permission` VALUES (2, 'permission:aix', NULL, '监控权限', '2017-12-12 09:46:12', '2017-12-12 09:46:12');
INSERT INTO `buir_permission` VALUES (3, 'permission:adduser', NULL, '添加用户权限', '2017-12-12 09:46:12', '2017-12-12 09:46:12');

-- ----------------------------
-- Table structure for buir_project_user
-- ----------------------------
DROP TABLE IF EXISTS `buir_project_user`;
CREATE TABLE `buir_project_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `project_id` int(11) NOT NULL COMMENT '项目ID',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目用户关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for buir_role
-- ----------------------------
DROP TABLE IF EXISTS `buir_role`;
CREATE TABLE `buir_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '角色说明',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of buir_role
-- ----------------------------
INSERT INTO `buir_role` VALUES (1, 'admin', '超级管理员', '2017-12-12 09:46:12', '2017-12-12 09:46:12');
INSERT INTO `buir_role` VALUES (2, 'aix', '系统监控员', '2017-12-12 09:46:12', '2017-12-12 09:46:12');

-- ----------------------------
-- Table structure for buir_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `buir_role_permission`;
CREATE TABLE `buir_role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `permission_id` int(11) NOT NULL COMMENT '权限ID',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of buir_role_permission
-- ----------------------------
INSERT INTO `buir_role_permission` VALUES (1, 1, 1, '2017-12-12 09:46:12', '2017-12-12 09:46:12');
INSERT INTO `buir_role_permission` VALUES (2, 1, 3, '2017-12-12 09:46:12', '2017-12-12 09:46:12');
INSERT INTO `buir_role_permission` VALUES (3, 2, 2, '2017-12-12 09:46:12', '2017-12-12 09:46:12');

-- ----------------------------
-- Table structure for buir_user
-- ----------------------------
DROP TABLE IF EXISTS `buir_user`;
CREATE TABLE `buir_user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `nick_name` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户昵称',
  `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户登录邮箱',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户登录密码',
  `salt` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码盐值',
  `status` int(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否冻结；0-待审核；1-审核通过；2-冻结；',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of buir_user
-- ----------------------------
INSERT INTO `buir_user` VALUES (8, 'admin', 'qjyn1314@163.com', '000719df13a09486872b3a792d5c45ac', 'dba9957d83d0d3f2b6ba5562ba53cb', 0, '2020-05-27 10:19:41', '2020-05-27 10:19:41');
INSERT INTO `buir_user` VALUES (9, '超级管理员', 'qjyn1314@gmail.com', '7aa37868d398b0d6d551951554187ca3', 'c7477193fc05cde6fb006f37b7a0ce', 0, '2020-05-28 06:22:12', '2020-05-28 06:22:12');
INSERT INTO `buir_user` VALUES (10, '呼伦贝尔大草原', 'qjyn1314@foxmail.com', '39a3e65c673264a767c07d75919815c8', 'd5400f430f40ea332ab25812eee47e', 0, '2020-05-28 06:26:03', '2020-05-28 06:26:03');
INSERT INTO `buir_user` VALUES (11, '呼伦贝尔大草原的中午', 'qjyn1314@126.com', '4db2d71e65a1cf4b529d2aedc9ed43cc', 'ac782fb4e4a753b60354a33adea8e3', 0, '2020-05-28 06:37:24', '2020-05-28 06:37:24');

-- ----------------------------
-- Table structure for buir_user_role
-- ----------------------------
DROP TABLE IF EXISTS `buir_user_role`;
CREATE TABLE `buir_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of buir_user_role
-- ----------------------------
INSERT INTO `buir_user_role` VALUES (1, 1, 1, '2017-05-05 00:00:00', '2017-05-05 00:00:00');
INSERT INTO `buir_user_role` VALUES (2, 2, 2, '2017-05-05 00:00:00', '2017-05-05 00:00:00');

SET FOREIGN_KEY_CHECKS = 1;
