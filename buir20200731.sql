/*
 Navicat Premium Data Transfer

 Source Server         : 47.104.78.115_3308
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 47.104.78.115:3308
 Source Schema         : buir

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 31/07/2020 16:31:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for buir_permission
-- ----------------------------
DROP TABLE IF EXISTS `buir_permission`;
CREATE TABLE `buir_permission`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` int(0) NULL DEFAULT NULL COMMENT '父级ID',
  `per_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '权限名称',
  `per_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '权限编码',
  `per_url` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '权限路径',
  `per_icon` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图表展示',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '权限说明',
  `per_sort` int(0) NULL DEFAULT 1 COMMENT '排序',
  `per_status` int(0) NULL DEFAULT 2 COMMENT '是否启用：1-启用；2-不启用',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of buir_permission
-- ----------------------------
INSERT INTO `buir_permission` VALUES (1, 0, '主页', 'homes', '', '<i class=\"layui-icon\">&#xe68e;</i>', '主页', 0, 1, '2017-12-12 09:46:12', '2020-07-31 06:38:05');
INSERT INTO `buir_permission` VALUES (2, 0, '用户', 'users', '', '<i class=\"layui-icon\">&#xe612;</i>', '用户增删改查', 1, 1, '2017-12-12 09:46:12', '2020-07-31 06:38:09');
INSERT INTO `buir_permission` VALUES (3, 0, '代码生成', 'generators', '', '<i class=\"layui-icon\">&#xe64e;</i>', '代码生成配置及生成', 2, 1, '2017-12-12 09:46:12', '2020-07-31 06:38:11');
INSERT INTO `buir_permission` VALUES (4, 1, '控制台', 'console', '/view/index.html.do', NULL, '首页控制台', 0, 1, '2020-07-22 06:11:53', '2020-07-31 06:38:15');
INSERT INTO `buir_permission` VALUES (5, 2, '用户账号', 'count', '/view/user/users.html.do', NULL, '用户账号的增删改查', 5, 1, '2020-07-22 06:12:28', '2020-07-31 07:03:55');
INSERT INTO `buir_permission` VALUES (6, 2, '权限配置', 'permission', '/view/user/permission.html.do', NULL, '权限的增删改查', 3, 1, '2020-07-22 06:13:26', '2020-07-31 07:04:01');
INSERT INTO `buir_permission` VALUES (7, 2, '角色配置', 'role', '/view/user/role.html.do', NULL, '角色的增删改查', 4, 1, '2020-07-22 06:14:13', '2020-07-31 07:04:07');
INSERT INTO `buir_permission` VALUES (8, 3, '生成配置', 'configure', '/view/generator/configure.html.do', NULL, '生成配置', 6, 1, '2020-07-22 06:14:40', '2020-07-31 06:38:43');
INSERT INTO `buir_permission` VALUES (9, 3, '生成代码', 'generator', '/view/generator/generator.html.do', NULL, '生成代码', 7, 1, '2020-07-22 06:15:51', '2020-07-31 06:38:46');
INSERT INTO `buir_permission` VALUES (10, 0, '牛场asdfasdfadsf', 'niuchang', '/inde.html.doasdfadsfadsd', 'asdasd', 'asdasd', 1, 2, '2020-07-29 03:25:46', '2020-07-31 06:39:23');
INSERT INTO `buir_permission` VALUES (11, 10, '牛场管理', 'asdfasdf', 'asdfasdf', 'asdfasdf', 'asdasdf', 1, 2, '2020-07-29 04:19:53', '2020-07-31 06:39:24');
INSERT INTO `buir_permission` VALUES (12, 0, '鸡场', 'jichang', 'jichang', 'jichang', 'jichang', 1, 2, '2020-07-29 06:15:34', '2020-07-31 06:39:25');
INSERT INTO `buir_permission` VALUES (13, 0, '哈哈哈哈', '', '', '', '', 1, 2, '2020-07-30 02:47:15', '2020-07-31 06:39:26');

-- ----------------------------
-- Table structure for buir_role
-- ----------------------------
DROP TABLE IF EXISTS `buir_role`;
CREATE TABLE `buir_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色编码',
  `role_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '角色说明',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of buir_role
-- ----------------------------
INSERT INTO `buir_role` VALUES (1, 'admin', '超级管理员', '超级管理员', '2017-12-12 09:46:12', '2020-07-23 01:46:37');
INSERT INTO `buir_role` VALUES (2, 'aix', '普通用户', '普通用户', '2017-12-12 09:46:12', '2020-07-23 01:46:39');
INSERT INTO `buir_role` VALUES (3, 'ceshi', '测试', '测试权限', '2020-07-23 02:51:56', '2020-07-23 09:50:18');
INSERT INTO `buir_role` VALUES (6, 'ceshi', '测试角色', '测试角色说明', '2020-07-30 09:12:01', '2020-07-30 09:12:01');
INSERT INTO `buir_role` VALUES (7, '', '', '', '2020-07-31 01:23:38', '2020-07-31 01:23:38');

-- ----------------------------
-- Table structure for buir_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `buir_role_permission`;
CREATE TABLE `buir_role_permission`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` int(0) NOT NULL COMMENT '角色ID',
  `permission_id` int(0) NOT NULL COMMENT '权限ID',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of buir_role_permission
-- ----------------------------
INSERT INTO `buir_role_permission` VALUES (29, 3, 1, '2020-07-31 05:18:28', '2020-07-31 05:18:28');
INSERT INTO `buir_role_permission` VALUES (30, 3, 4, '2020-07-31 05:18:28', '2020-07-31 05:18:28');
INSERT INTO `buir_role_permission` VALUES (31, 3, 10, '2020-07-31 05:18:28', '2020-07-31 05:18:28');
INSERT INTO `buir_role_permission` VALUES (32, 3, 11, '2020-07-31 05:18:28', '2020-07-31 05:18:28');
INSERT INTO `buir_role_permission` VALUES (33, 3, 12, '2020-07-31 05:18:28', '2020-07-31 05:18:28');
INSERT INTO `buir_role_permission` VALUES (34, 3, 13, '2020-07-31 05:18:28', '2020-07-31 05:18:28');
INSERT INTO `buir_role_permission` VALUES (35, 6, 1, '2020-07-31 05:18:55', '2020-07-31 05:18:55');
INSERT INTO `buir_role_permission` VALUES (36, 6, 4, '2020-07-31 05:18:55', '2020-07-31 05:18:55');
INSERT INTO `buir_role_permission` VALUES (37, 6, 2, '2020-07-31 05:18:55', '2020-07-31 05:18:55');
INSERT INTO `buir_role_permission` VALUES (38, 6, 5, '2020-07-31 05:18:55', '2020-07-31 05:18:55');
INSERT INTO `buir_role_permission` VALUES (39, 6, 6, '2020-07-31 05:18:55', '2020-07-31 05:18:55');
INSERT INTO `buir_role_permission` VALUES (40, 6, 7, '2020-07-31 05:18:55', '2020-07-31 05:18:55');
INSERT INTO `buir_role_permission` VALUES (41, 6, 3, '2020-07-31 05:18:55', '2020-07-31 05:18:55');
INSERT INTO `buir_role_permission` VALUES (42, 6, 8, '2020-07-31 05:18:55', '2020-07-31 05:18:55');
INSERT INTO `buir_role_permission` VALUES (43, 6, 9, '2020-07-31 05:18:55', '2020-07-31 05:18:55');
INSERT INTO `buir_role_permission` VALUES (44, 6, 10, '2020-07-31 05:18:55', '2020-07-31 05:18:55');
INSERT INTO `buir_role_permission` VALUES (45, 6, 11, '2020-07-31 05:18:55', '2020-07-31 05:18:55');
INSERT INTO `buir_role_permission` VALUES (46, 6, 12, '2020-07-31 05:18:55', '2020-07-31 05:18:55');
INSERT INTO `buir_role_permission` VALUES (47, 6, 13, '2020-07-31 05:18:55', '2020-07-31 05:18:55');
INSERT INTO `buir_role_permission` VALUES (48, 1, 2, '2020-07-31 06:07:47', '2020-07-31 06:07:47');
INSERT INTO `buir_role_permission` VALUES (49, 1, 5, '2020-07-31 06:07:48', '2020-07-31 06:07:48');
INSERT INTO `buir_role_permission` VALUES (50, 1, 6, '2020-07-31 06:07:48', '2020-07-31 06:07:48');
INSERT INTO `buir_role_permission` VALUES (51, 1, 7, '2020-07-31 06:07:48', '2020-07-31 06:07:48');
INSERT INTO `buir_role_permission` VALUES (52, 1, 1, '2020-07-31 06:07:48', '2020-07-31 06:07:48');
INSERT INTO `buir_role_permission` VALUES (53, 1, 4, '2020-07-31 06:07:48', '2020-07-31 06:07:48');
INSERT INTO `buir_role_permission` VALUES (54, 1, 3, '2020-07-31 06:07:48', '2020-07-31 06:07:48');
INSERT INTO `buir_role_permission` VALUES (55, 1, 8, '2020-07-31 06:07:48', '2020-07-31 06:07:48');
INSERT INTO `buir_role_permission` VALUES (56, 1, 9, '2020-07-31 06:07:48', '2020-07-31 06:07:48');
INSERT INTO `buir_role_permission` VALUES (57, 2, 3, '2020-07-31 06:07:58', '2020-07-31 06:07:58');
INSERT INTO `buir_role_permission` VALUES (58, 2, 8, '2020-07-31 06:07:58', '2020-07-31 06:07:58');
INSERT INTO `buir_role_permission` VALUES (59, 2, 9, '2020-07-31 06:07:58', '2020-07-31 06:07:58');
INSERT INTO `buir_role_permission` VALUES (60, 2, 1, '2020-07-31 06:07:58', '2020-07-31 06:07:58');
INSERT INTO `buir_role_permission` VALUES (61, 2, 4, '2020-07-31 06:07:58', '2020-07-31 06:07:58');

-- ----------------------------
-- Table structure for buir_user
-- ----------------------------
DROP TABLE IF EXISTS `buir_user`;
CREATE TABLE `buir_user`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `nick_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户昵称',
  `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户登录邮箱',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户登录密码',
  `salt` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码盐值',
  `status` int(0) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否冻结；0-待审核；1-审核通过；2-冻结；',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 143 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of buir_user
-- ----------------------------
INSERT INTO `buir_user` VALUES (8, 'admin', 'qjyn1314@163.com', '000719df13a09486872b3a792d5c45ac', 'dba9957d83d0d3f2b6ba5562ba53cb', 1, '2020-05-27 10:19:41', '2020-05-27 10:19:41');
INSERT INTO `buir_user` VALUES (9, '超级管理员', 'qjyn1314@gmail.com', '7aa37868d398b0d6d551951554187ca3', 'c7477193fc05cde6fb006f37b7a0ce', 1, '2020-05-28 06:22:12', '2020-05-28 06:22:12');

-- ----------------------------
-- Table structure for buir_user_role
-- ----------------------------
DROP TABLE IF EXISTS `buir_user_role`;
CREATE TABLE `buir_user_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(0) NOT NULL COMMENT '用户ID',
  `role_id` int(0) NOT NULL COMMENT '角色ID',
  `created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of buir_user_role
-- ----------------------------
INSERT INTO `buir_user_role` VALUES (5, 9, 1, '2020-07-23 07:16:47', '2020-07-23 07:16:47');
INSERT INTO `buir_user_role` VALUES (7, 8, 1, '2020-07-23 08:36:50', '2020-07-23 08:54:37');

SET FOREIGN_KEY_CHECKS = 1;
