/*
 Navicat Premium Dump SQL

 Source Server         : local_mysql
 Source Server Type    : MySQL
 Source Server Version : 50643 (5.6.43)
 Source Host           : localhost:3306
 Source Schema         : vue3-manager

 Target Server Type    : MySQL
 Target Server Version : 50643 (5.6.43)
 File Encoding         : 65001

 Date: 28/03/2025 16:46:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for security_user
-- ----------------------------
DROP TABLE IF EXISTS `security_user`;
CREATE TABLE `security_user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `createby` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `updateby` datetime NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '安全用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of security_user
-- ----------------------------
INSERT INTO `security_user` VALUES ('6c2e9839-f13b-4e17-a6b2-ee7980333a10', 'admin', 'admin', NULL, NULL, '2025-03-28 15:49:10', 'system', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
