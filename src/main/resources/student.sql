/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-07-10 09:57:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('5', '小奶狗', '1', '2018-03-09 15:03:18');
INSERT INTO `student` VALUES ('34', '小', '1', '2018-03-09 15:02:56');
INSERT INTO `student` VALUES ('43', '东邪', '0', '2017-12-14 11:12:40');
INSERT INTO `student` VALUES ('53', '刘思婉小可爱', '0', '2018-03-05 14:28:18');
INSERT INTO `student` VALUES ('54', '裴青湖', '0', '2018-03-05 14:24:36');
INSERT INTO `student` VALUES ('55', '李佳加', '0', '2018-03-08 14:30:12');
INSERT INTO `student` VALUES ('56', '石磊', '0', '2018-06-05 20:15:22');
INSERT INTO `student` VALUES ('57', '胡日', '0', '2018-07-10 09:57:23');

-- ----------------------------
-- Table structure for student_hobbies
-- ----------------------------
DROP TABLE IF EXISTS `student_hobbies`;
CREATE TABLE `student_hobbies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentid` int(11) NOT NULL,
  `hobbies` varchar(255) COLLATE utf8_bin NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of student_hobbies
-- ----------------------------
INSERT INTO `student_hobbies` VALUES ('1', '33', '打豆豆', '2017-12-13 19:58:10');
INSERT INTO `student_hobbies` VALUES ('2', '34', '打农药', '2017-12-13 19:58:31');
INSERT INTO `student_hobbies` VALUES ('3', '43', '打麻将', '2017-12-14 11:13:35');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '',
  `password` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123456');
