/*
Navicat MySQL Data Transfer

Source Server         : Mysql
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : zhihu

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2014-12-14 21:29:49
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `answer`
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `id` int(11) NOT NULL,
  `content` text NOT NULL COMMENT '回答内容',
  `topic_id` int(11) NOT NULL COMMENT '回答问题id',
  `user_id` int(11) NOT NULL COMMENT '回答作者id',
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
  `content` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `user_id` int(11) DEFAULT NULL COMMENT '评论者id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for `question`
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL COMMENT '问题标题信息',
  `content` text COMMENT '问题内容信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------

-- ----------------------------
-- Table structure for `topic`
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `id` int(11) NOT NULL,
  `topic` varchar(255) NOT NULL COMMENT '话题名称',
  `introduce` varchar(255) DEFAULT NULL COMMENT '话题介绍',
  `parent_id` int(11) DEFAULT NULL COMMENT '父话题的id'
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for `usr_interested_ques`
-- ----------------------------
DROP TABLE IF EXISTS `usr_interested_ques`;
CREATE TABLE `usr_interested_ques` (
  `question_id` int(11) DEFAULT NULL COMMENT '受关注问题id',
  `user_id` int(11) DEFAULT NULL COMMENT '关注问题用户id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usr_interested_ques
-- ----------------------------

-- ----------------------------
-- Table structure for `usr_interested_topic`
-- ----------------------------
DROP TABLE IF EXISTS `usr_interested_topic`;
CREATE TABLE `usr_interested_topic` (
  `topic_id` int(11) DEFAULT NULL COMMENT '受关注话题',
  `user_id` int(11) DEFAULT NULL COMMENT '关注话题用户'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usr_interested_topic
-- ----------------------------
