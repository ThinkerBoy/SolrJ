/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : solrdb

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-11-09 22:44:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `id` varchar(10) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES ('1', '《周易》', '老子', '中国最古老的典籍，被称为群经之首。假如《周易》不能排名第一，那第一只有空着了。很多人提到这本书都用3个字来形容“看不懂”，的确这是一本很难懂的书，它涉及到很多的内容，天文地理，算卦，为人处世，从政，经商，军事等等');
INSERT INTO `books` VALUES ('2', '《六韬》', '姜子牙', '现在一般认为此书成于战国时代，全书以姜太公与周文王，周武王的对话方式编纂而成。假如现在认为的成书年代为真，那此书应该是后人根据口口相传的故事结合周朝流传下来的行军打仗案例整合而成。');
INSERT INTO `books` VALUES ('3', '《论语》', '孔子', '这个不需要讲太多了，从它面世以来就成为了经典，几千年的封建历史离不开儒家思想更离不开《论语》。');
INSERT INTO `books` VALUES ('4', '《孙子兵法》', '孙武', '它被称为中国最杰出的兵法著作，历来备受推崇，是各国军事家，经济家的必备之书没有之一，它的作者孙武被称为兵圣，世界兵学鼻祖。每个中国人必读之书，再多的语言也无法形容它的地位。');
INSERT INTO `books` VALUES ('5', '《吕氏春秋》', '吕不韦', '主要讲了先秦之前的一些资料，记载了先秦之前的一些古史旧闻，古人遗语等，其中有一部分是其他书籍中没有的。');
INSERT INTO `books` VALUES ('6', '《厚黑学》', '李宗吾', '《厚黑学》是民国时期李宗吾的著作，主要宣扬脸皮要厚如城墙，心要黑如煤炭，这样才能成为“英雄豪杰”，列举了曹操、刘备、孙权、司马懿、项羽、刘邦等人物的脸皮厚和心肠黑。书中内容富有讽刺意味从某些角度反映了人性黑暗的一面，但也反映了人的为人处世之道。');
