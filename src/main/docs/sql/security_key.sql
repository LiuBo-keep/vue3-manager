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

 Date: 09/04/2025 11:25:04
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
  `aes_key` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'ase密钥',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `createby` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `updateby` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '安全密钥表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of security_key
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
