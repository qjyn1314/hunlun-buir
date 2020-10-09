/*
 Navicat Premium Data Transfer

 Source Server         : 47.104.78.115_3308-1234567
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 47.104.78.115:3308
 Source Schema         : evening

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 09/10/2020 13:52:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` int(0) NULL DEFAULT NULL COMMENT '父级ID',
  `per_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '权限名称',
  `per_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '权限编码',
  `per_url` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '权限路径',
  `per_icon` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图表展示',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '权限说明',
  `per_sort` int(0) NULL DEFAULT 1 COMMENT '排序',
  `per_status` int(0) NULL DEFAULT 2 COMMENT '是否启用：1-启用；2-不启用',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 0, '代码工具', 'code', '', '&#xe64e;', '', 1, 1, '2020-09-27 06:54:50', '2020-09-27 07:03:24');
INSERT INTO `sys_permission` VALUES (2, 1, '生成配置', 'code:set', 'page/generator/configure.html', '&#xe716;', '', 1, 1, '2020-09-27 06:55:23', '2020-09-27 07:03:24');
INSERT INTO `sys_permission` VALUES (3, 1, '代码生成', 'code:make', 'page/generator/generator.html', '&#xe609;', '', 1, 1, '2020-09-27 06:56:02', '2020-09-27 07:03:24');
INSERT INTO `sys_permission` VALUES (4, 0, '权限管理', 'permission', 'page/permission/permission.html', '&#xe672;', '', 1, 1, '2020-09-27 06:56:49', '2020-09-27 07:03:24');
INSERT INTO `sys_permission` VALUES (5, 0, '角色管理', 'role', 'page/role/role.html', '&#xe613;', '', 1, 1, '2020-09-27 06:57:20', '2020-09-27 07:03:24');
INSERT INTO `sys_permission` VALUES (6, 0, '用户中心', 'user', 'page/user/users.html', '&#xe612;', '', 1, 1, '2020-09-27 06:57:45', '2020-09-27 07:03:24');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '角色编码',
  `role_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '角色说明',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'tourists', '游客', '只用于普通用户注册后使用生成代码工具', '2020-09-27 09:13:53', '2020-09-27 09:13:53');
INSERT INTO `sys_role` VALUES (2, 'admin', '管理员', '超级管理员', '2020-09-27 09:30:03', '2020-09-27 09:30:03');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` int(0) NOT NULL COMMENT '角色ID',
  `permission_id` int(0) NOT NULL COMMENT '权限ID',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (4, 2, 1, '2020-09-27 09:56:39', '2020-09-27 09:56:39');
INSERT INTO `sys_role_permission` VALUES (5, 2, 2, '2020-09-27 09:56:45', '2020-09-27 09:56:45');
INSERT INTO `sys_role_permission` VALUES (6, 2, 3, '2020-09-27 09:56:51', '2020-09-27 09:56:51');
INSERT INTO `sys_role_permission` VALUES (7, 2, 4, '2020-09-27 09:57:09', '2020-09-27 09:57:09');
INSERT INTO `sys_role_permission` VALUES (8, 2, 5, '2020-09-27 09:57:15', '2020-09-27 09:57:15');
INSERT INTO `sys_role_permission` VALUES (9, 2, 6, '2020-09-27 09:57:23', '2020-09-27 09:57:23');
INSERT INTO `sys_role_permission` VALUES (10, 2, 7, '2020-09-27 09:57:32', '2020-09-27 09:57:32');
INSERT INTO `sys_role_permission` VALUES (23, 1, 1, '2020-10-09 05:42:10', '2020-10-09 05:42:10');
INSERT INTO `sys_role_permission` VALUES (24, 1, 2, '2020-10-09 05:42:10', '2020-10-09 05:42:10');
INSERT INTO `sys_role_permission` VALUES (25, 1, 3, '2020-10-09 05:42:10', '2020-10-09 05:42:10');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态 0锁定 1有效',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最近访问时间',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别 0男 1女 2保密',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标志 0:未删除 1:删除',
  PRIMARY KEY (`id`, `user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$S9hUc/9isc5icu/NVeQuxOGwQfK2wbnG.Hlg1rsfYW6pzWy14p6Ly', 'qjyn1314@163.com', '15515014022', '1', NULL, '0', NULL, NULL, NULL, '2020-09-18 08:07:07', NULL, '2020-09-18 08:07:07', NULL, '0');
INSERT INTO `sys_user` VALUES (19, 'zhangsan', '$2a$10$5bzgEJ3OeFW4GHK3n9VbG.OOVCiR8ePCpMCuv4xhTrV48nH.bvJGW', 'qjyn1314@163.com', '15321355715', '1', NULL, '1', NULL, NULL, NULL, '2020-10-09 02:41:46', NULL, '2020-10-09 02:41:46', NULL, '0');
INSERT INTO `sys_user` VALUES (20, 'lisi', '$2a$10$XFcPaIRXII6s3QVtWInvNu1Nuq5ancNfbym1C7neLDAW7cZl1m9RS', 'lisi@163.com', '15515014022', '0', NULL, '1', NULL, NULL, NULL, '2020-10-09 03:14:22', NULL, '2020-10-09 03:14:22', NULL, '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(0) NOT NULL COMMENT '用户ID',
  `role_id` int(0) NOT NULL COMMENT '角色ID',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (13, 1, 2, '2020-10-09 03:13:25', '2020-10-09 03:13:25');
INSERT INTO `sys_user_role` VALUES (14, 20, 1, '2020-10-09 03:14:22', '2020-10-09 03:14:22');
INSERT INTO `sys_user_role` VALUES (16, 19, 1, '2020-10-09 05:42:35', '2020-10-09 05:42:35');

SET FOREIGN_KEY_CHECKS = 1;
