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

 Date: 24/05/2020 12:33:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for buir_user
-- ----------------------------
DROP TABLE IF EXISTS `buir_user`;
CREATE TABLE `buir_user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名称',
  `user_mail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户登录邮箱',
  `user_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户登录密码',
  `password_salt` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码盐值',
  `status` int(2) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否冻结；0-待审核；1-审核通过；2-冻结；',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of buir_user
-- ----------------------------
INSERT INTO `buir_user` VALUES (53, 'admin', 'qjyn1314@gmail.com', 'dfa3ea96a882992728f7d3c099a297d7', '9bc606', 0, '2020-03-27 05:28:43', '2020-03-27 05:28:43');
INSERT INTO `buir_user` VALUES (54, 'admin', 'qjyn1314@162.com', '1080d72aa03b71afc2d14cb3a93bf225', 'c95353', 0, '2020-03-27 11:43:26', '2020-03-27 11:43:26');
INSERT INTO `buir_user` VALUES (55, 'admin', '1602423365@qq.com', '43b2cafe63659523fa8f70712a70cebe', 'dbb61b', 0, '2020-03-27 11:48:32', '2020-03-27 11:48:32');
INSERT INTO `buir_user` VALUES (56, 'Mr.Wang', 'qjyn1314@163.com', '8183a013e34504cf76027d3f6602402b', '9242e8', 0, '2020-04-03 02:15:22', '2020-04-03 02:15:22');

SET FOREIGN_KEY_CHECKS = 1;
