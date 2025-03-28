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

 Date: 28/03/2025 16:46:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for security_key
-- ----------------------------
DROP TABLE IF EXISTS `security_key`;
CREATE TABLE `security_key`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `public_key` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '公钥',
  `private_key` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '私钥',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `createby` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `updateby` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '安全密钥表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of security_key
-- ----------------------------
INSERT INTO `security_key` VALUES ('eedbe387-7b04-4295-9b99-71f48a8b6d04', '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', '2025-03-28 16:26:56', 'System', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
