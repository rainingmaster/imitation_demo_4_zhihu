/*
Navicat MySQL Data Transfer

Source Server         : Mysql
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : zhihu

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2014-12-24 23:18:49
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `answer`
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL COMMENT '回答内容',
  `question_id` int(11) NOT NULL COMMENT '回答问题id',
  `user_id` int(11) NOT NULL COMMENT '回答作者id',
  `agree_count` int(11) DEFAULT NULL COMMENT '点赞数',
  `anonymous_tag` tinyint(4) DEFAULT NULL COMMENT '标示是否匿名',
  `init_time` time DEFAULT NULL COMMENT '回答创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of answer
-- ----------------------------

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `content` varchar(255) NOT NULL COMMENT '评论内容',
  `user_id` int(11) NOT NULL COMMENT '评论者id',
  `init_time` time NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for `people`
-- ----------------------------
DROP TABLE IF EXISTS `people`;
CREATE TABLE `people` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL COMMENT '用户姓名息信',
  `introduce` varchar(50) DEFAULT NULL COMMENT '用户自我介绍信息',
  `init_time` time DEFAULT NULL COMMENT '注册时间',
  `photo` varchar(255) DEFAULT NULL COMMENT '用户照片路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of people
-- ----------------------------

-- ----------------------------
-- Table structure for `people_interested_ques`
-- ----------------------------
DROP TABLE IF EXISTS `people_interested_ques`;
CREATE TABLE `people_interested_ques` (
  `question_id` int(11) DEFAULT NULL COMMENT '受关注问题id',
  `user_id` int(11) DEFAULT NULL COMMENT '关注问题用户id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of people_interested_ques
-- ----------------------------

-- ----------------------------
-- Table structure for `people_interested_topic`
-- ----------------------------
DROP TABLE IF EXISTS `people_interested_topic`;
CREATE TABLE `people_interested_topic` (
  `topic_id` int(11) DEFAULT NULL COMMENT '受关注话题',
  `user_id` int(11) DEFAULT NULL COMMENT '关注话题用户'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of people_interested_topic
-- ----------------------------

-- ----------------------------
-- Table structure for `question`
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL COMMENT '问题标题信息',
  `content` text COMMENT '问题内容信息',
  `asker_id` int(11) DEFAULT NULL COMMENT '问者提id',
  `init_time` time DEFAULT NULL COMMENT '建立时间',
  `anonymous_tag` tinyint(4) DEFAULT NULL COMMENT '标示是否匿名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('2', '11111', '2222', null, null, null);
INSERT INTO `question` VALUES ('3', '11111111', '2222222', null, null, null);
INSERT INTO `question` VALUES ('4', '11111', '2222', '11', '15:12:25', '1');
INSERT INTO `question` VALUES ('5', 'ÎªÊ²Ã´Ìì¿ÕÊÇÀ¶É«µÄ', 'ÒòÎª¹â´óÕÛÉä°¡º¢×Ó', '11', '13:12:50', '1');
INSERT INTO `question` VALUES ('6', 'ÎªÊ²Ã´Ìì¿ÕÊÇÀ¶É«µÄ', 'ÒòÎª¹â´óÕÛÉä°¡º¢×Ó', '11', '13:12:00', '1');
INSERT INTO `question` VALUES ('7', 'ÎªÊ²Ã´Ìì¿ÕÊÇÀ¶É«µÄ', 'ÒòÎª¹â´óÕÛÉä°¡º¢×Ó', '11', '13:12:20', '1');

-- ----------------------------
-- Table structure for `topic`
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topic` varchar(255) NOT NULL COMMENT '话题名称',
  `introduce` varchar(255) DEFAULT NULL COMMENT '话题介绍',
  `parent_id` int(11) DEFAULT NULL COMMENT '父话题的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic
-- ----------------------------
