/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : zhihu

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2014-12-25 17:05:39
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `action`
-- ----------------------------
DROP TABLE IF EXISTS `action`;
CREATE TABLE `action` (
  `id` int(11) NOT NULL,
  `creator_id` int(11) NOT NULL COMMENT '动作发起者id',
  `type` tinyint(4) NOT NULL COMMENT '动作种类',
  `answer_id` int(11) DEFAULT NULL,
  `init_time` time DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of action
-- ----------------------------

-- ----------------------------
-- Table structure for `answer`
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL COMMENT '回答内容',
  `question_id` int(11) NOT NULL COMMENT '回答问题id',
  `responder_id` int(11) NOT NULL COMMENT '回答作者id',
  `agree_count` int(11) DEFAULT NULL COMMENT '点赞数',
  `anonymous_tag` tinyint(4) DEFAULT NULL COMMENT '标示是否匿名',
  `init_time` time DEFAULT NULL COMMENT '回答创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of answer
-- ----------------------------
INSERT INTO `answer` VALUES ('1', '好好学习天天向上', '113', '321231', '0', '1', '14:12:41');
INSERT INTO `answer` VALUES ('2', 'his', '1003', '10000', '0', '1', '15:12:51');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `content` varchar(255) NOT NULL COMMENT '评论内容',
  `replier_id` int(11) NOT NULL COMMENT '评论者id',
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
-- Table structure for `people_follow_people`
-- ----------------------------
DROP TABLE IF EXISTS `people_follow_people`;
CREATE TABLE `people_follow_people` (
  `followee_id` int(11) NOT NULL,
  `follower_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of people_follow_people
-- ----------------------------

-- ----------------------------
-- Table structure for `people_follow_ques`
-- ----------------------------
DROP TABLE IF EXISTS `people_follow_ques`;
CREATE TABLE `people_follow_ques` (
  `question_id` int(11) NOT NULL COMMENT '受关注问题id',
  `follower_id` int(11) NOT NULL COMMENT '关注问题用户id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of people_follow_ques
-- ----------------------------

-- ----------------------------
-- Table structure for `people_follow_topic`
-- ----------------------------
DROP TABLE IF EXISTS `people_follow_topic`;
CREATE TABLE `people_follow_topic` (
  `topic_id` int(11) NOT NULL COMMENT '受关注话题',
  `follower_id` int(11) NOT NULL COMMENT '关注话题用户'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of people_follow_topic
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES ('2', '11111', '2222', null, null, null);
INSERT INTO `question` VALUES ('3', '11111111', '2222222', null, null, null);
INSERT INTO `question` VALUES ('4', '11111', '2222', '11', '15:12:25', '1');
INSERT INTO `question` VALUES ('5', 'ÎªÊ²Ã´Ìì¿ÕÊÇÀ¶É«µÄ', 'ÒòÎª¹â´óÕÛÉä°¡º¢×Ó', '11', '13:12:50', '1');
INSERT INTO `question` VALUES ('6', 'ÎªÊ²Ã´Ìì¿ÕÊÇÀ¶É«µÄ', 'ÒòÎª¹â´óÕÛÉä°¡º¢×Ó', '11', '13:12:00', '1');
INSERT INTO `question` VALUES ('7', 'ÎªÊ²Ã´Ìì¿ÕÊÇÀ¶É«µÄ', 'ÒòÎª¹â´óÕÛÉä°¡º¢×Ó', '11', '13:12:20', '1');
INSERT INTO `question` VALUES ('8', '333333', '4444444555555', '11', '14:12:34', '1');
INSERT INTO `question` VALUES ('9', '1111', '2222', '321231', '14:12:14', '1');
INSERT INTO `question` VALUES ('10', '11111', '2222222', '10000', '15:12:27', '1');

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

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL COMMENT '用户姓名息信',
  `introduce` varchar(50) DEFAULT NULL COMMENT '用户自我介绍信息',
  `init_time` time DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
