/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : springcloud_learning

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 01/06/2019 14:39:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for soa_content
-- ----------------------------
DROP TABLE IF EXISTS `soa_content`;
CREATE TABLE `soa_content`  (
  `content_id` int(11) NOT NULL AUTO_INCREMENT,
  `content_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of soa_content
-- ----------------------------
INSERT INTO `soa_content` VALUES (1, '第一个content');
INSERT INTO `soa_content` VALUES (2, '第二个content');

-- ----------------------------
-- Table structure for soa_group
-- ----------------------------
DROP TABLE IF EXISTS `soa_group`;
CREATE TABLE `soa_group`  (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of soa_group
-- ----------------------------
INSERT INTO `soa_group` VALUES (1, '第一个群');
INSERT INTO `soa_group` VALUES (2, '第二个群');

SET FOREIGN_KEY_CHECKS = 1;
