/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : 127.0.0.1:3306
 Source Schema         : pos

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 25/05/2020 12:12:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_manager_role
-- ----------------------------
DROP TABLE IF EXISTS `t_manager_role`;
CREATE TABLE `t_manager_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `manager_id` int(11) NOT NULL COMMENT '管理用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_manager_role
-- ----------------------------
INSERT INTO `t_manager_role` VALUES (1, 1, 1, '2017-05-05 00:00:00', '2017-05-05 00:00:00');
INSERT INTO `t_manager_role` VALUES (2, 2, 2, '2017-05-05 00:00:00', '2017-05-05 00:00:00');

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `permission` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '权限名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '权限说明',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES (1, 'permission:admin', '超级管理权限', '2017-12-12 09:46:12', '2017-12-12 09:46:12');
INSERT INTO `t_permission` VALUES (2, 'permission:aix', '监控权限', '2017-12-12 09:46:12', '2017-12-12 09:46:12');
INSERT INTO `t_permission` VALUES (3, 'permission:adduser', '添加用户权限', '2017-12-12 09:46:12', '2017-12-12 09:46:12');

-- ----------------------------
-- Table structure for t_project_user
-- ----------------------------
DROP TABLE IF EXISTS `t_project_user`;
CREATE TABLE `t_project_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `project_id` int(11) NOT NULL COMMENT '项目ID',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目用户关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '角色说明',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'admin', '超级管理员', '2017-12-12 09:46:12', '2017-12-12 09:46:12');
INSERT INTO `t_role` VALUES (2, 'aix', '系统监控员', '2017-12-12 09:46:12', '2017-12-12 09:46:12');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `permission_id` int(11) NOT NULL COMMENT '权限ID',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES (1, 1, 1, '2017-12-12 09:46:12', '2017-12-12 09:46:12');
INSERT INTO `t_role_permission` VALUES (2, 1, 3, '2017-12-12 09:46:12', '2017-12-12 09:46:12');
INSERT INTO `t_role_permission` VALUES (3, 2, 2, '2017-12-12 09:46:12', '2017-12-12 09:46:12');

SET FOREIGN_KEY_CHECKS = 1;
