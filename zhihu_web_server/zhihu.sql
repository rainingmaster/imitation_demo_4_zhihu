/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : zhihu

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2014-12-26 17:17:24
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
  `responder_id` int(11) NOT NULL COMMENT '回答作者id',
  `agree_count` int(11) DEFAULT NULL COMMENT '点赞数',
  `anonymous_tag` tinyint(4) DEFAULT NULL COMMENT '标示是否匿名',
  `init_time` time DEFAULT NULL COMMENT '回答创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of answer
-- ----------------------------
INSERT INTO `answer` VALUES ('1', '好好学习天天向上', '4', '11', '0', '1', '14:12:41');
INSERT INTO `answer` VALUES ('2', 'his', '3', '11', '133', '1', '15:12:51');
INSERT INTO `answer` VALUES ('3', '好好学习天天向上2', '4', '11', '1241', '1', '14:12:17');
INSERT INTO `answer` VALUES ('4', '天才是百分之百的灵感加百分之一的汗水', '3', '11', '213', '1', '14:12:07');
INSERT INTO `answer` VALUES ('5', '`21`12`', '1001', '11', '0', '1', '15:12:27');
INSERT INTO `answer` VALUES ('6', 'cjg.', '1003', '11', '0', '1', '17:12:30');
INSERT INTO `answer` VALUES ('7', '都是方法v的', '1001', '11', '0', '1', '17:12:32');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of people
-- ----------------------------
INSERT INTO `people` VALUES ('2', null, null, null, null);
INSERT INTO `people` VALUES ('11', 'tempooo', 'a superwoman', '10:58:25', 'tt');

-- ----------------------------
-- Table structure for `people_agree_answer`
-- ----------------------------
DROP TABLE IF EXISTS `people_agree_answer`;
CREATE TABLE `people_agree_answer` (
  `answer_id` int(11) DEFAULT NULL,
  `follower_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of people_agree_answer
-- ----------------------------

-- ----------------------------
-- Table structure for `people_agree_comment`
-- ----------------------------
DROP TABLE IF EXISTS `people_agree_comment`;
CREATE TABLE `people_agree_comment` (
  `comment_id` int(11) DEFAULT NULL,
  `people_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of people_agree_comment
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
INSERT INTO `question` VALUES ('2', '11111', '2222', '11', '12:21:38', null);
INSERT INTO `question` VALUES ('3', '22222', '2222222', '11', '11:21:35', null);
INSERT INTO `question` VALUES ('4', '11111', '2222', '11', '15:12:25', '1');
INSERT INTO `question` VALUES ('5', '好好学习天天向上', '好好学习天天向上1', '11', '13:12:50', '1');
INSERT INTO `question` VALUES ('6', '好好学习天天向上2', '好好学习天天向上2', '11', '13:12:00', '1');
INSERT INTO `question` VALUES ('7', '好好学习天天向上3', '好好学习天天向上3', '11', '13:12:20', '1');
INSERT INTO `question` VALUES ('8', '333333', '4444444555555', '11', '14:12:34', '1');
INSERT INTO `question` VALUES ('9', '1111', '2222', '11', '14:12:14', '1');
INSERT INTO `question` VALUES ('10', '11111', '2222222', '11', '15:12:27', '1');

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

-- ----------------------------
-- View structure for `action`
-- ----------------------------
DROP VIEW IF EXISTS `action`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `action` AS select `question`.`id` AS `target_id`,`question`.`asker_id` AS `initiator_id`,'2' AS `type`,`question`.`init_time` AS `init_time` from `question` where (`question`.`anonymous_tag` = 1) union all select `answer`.`id` AS `target_id`,`answer`.`responder_id` AS `initiator_id`,'3' AS `type`,`answer`.`init_time` AS `init_time` from `answer` where (`answer`.`anonymous_tag` = 1);
