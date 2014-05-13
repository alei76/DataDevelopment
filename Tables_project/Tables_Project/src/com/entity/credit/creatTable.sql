
CREATE TABLE `credit_future` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `date` int(10) unsigned DEFAULT NULL COMMENT '时间',
  `qid` varchar(10) DEFAULT NULL COMMENT '渠道',
  `activetype` int(10) unsigned DEFAULT NULL COMMENT '10000表示次日，10001表示7日，10002表示30日',
  `active_count` int(10) unsigned DEFAULT NULL COMMENT '活跃数',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='赚金币新用户future数据表'



CREATE TABLE `credit_third` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `date` int(10) unsigned DEFAULT NULL COMMENT '时间',
  `software_name` varchar(10) DEFAULT NULL COMMENT '软件名称',
  `download_count` int(10) unsigned DEFAULT NULL COMMENT '下载次数', 
  `auto_launch_num` int(10) unsigned DEFAULT NULL COMMENT '安装后自动打开次数',
  `self_launch_count` int(10) unsigned DEFAULT NULL COMMENT '用户主动打开次数',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='赚金币三方应用数据表'


CREATE TABLE `credit_new_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `date` int(10) unsigned DEFAULT NULL COMMENT '时间',
  `qid` varchar(10) DEFAULT NULL COMMENT '渠道',
  `install_count` int(10) unsigned DEFAULT NULL COMMENT '安装数',
  `active_count` int(10) unsigned DEFAULT NULL COMMENT '活跃数',
  `download_count` int(10) unsigned DEFAULT NULL COMMENT '下载数',
  `nextDay_active_count` int(10) unsigned DEFAULT NULL COMMENT '次日活跃数',
  `nextDay_active_rate` int(10) unsigned DEFAULT NULL COMMENT '次日活跃率',
  `week_active_count` int(10) unsigned DEFAULT NULL COMMENT '七日活跃数',
  `week_active_rate` int(10) unsigned DEFAULT NULL COMMENT '七日活跃率',
  `month_active_count` int(10) unsigned DEFAULT NULL COMMENT '三十日活跃数',
  `month_active_rate` int(10) unsigned DEFAULT NULL COMMENT '三十日活跃率',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='赚金币新用户数据表'

create table credit_basic(`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `date` int(10) unsigned DEFAULT NULL COMMENT '时间',
  `qid` varchar(10) DEFAULT NULL COMMENT '渠道',
  `launch_count` int(10) unsigned DEFAULT NULL COMMENT '服务启动量',
  `active_count` int(10) unsigned DEFAULT NULL COMMENT '活跃数',
  `download_count` int(10) unsigned DEFAULT NULL COMMENT '下载数',
  `third_count` int(10) unsigned DEFAULT NULL COMMENT '打开第三方应用',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='赚金币基础数据表'


drop table credit_experience1
CREATE  TABLE `credit_experience1` (
id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
date int(10) unsigned not NULL,
1001_total_click   int(10) unsigned Default NULL,
1001_total_usr_num  int(10) unsigned Default NULL,
1001_ratio  int(10) unsigned Default NULL,
1002_total_click  int(10) unsigned Default NULL,
1002_total_usr_num  int(10) unsigned Default NULL,
1002_ratio  int(10) unsigned Default NULL,
1003_total_click  int(10) unsigned Default NULL,
1003_total_usr_num  int(10) unsigned Default NULL,
1003_ratio  int(10) unsigned Default NULL,
1004_total_click  int(10) unsigned Default NULL,
1004_total_usr_num  int(10) unsigned Default NULL,
1004_ratio  int(10) unsigned Default NULL,
1005_total_click  int(10) unsigned Default NULL,
1005_total_usr_num  int(10) unsigned Default NULL,
1005_ratio  int(10) unsigned Default NULL,
1006_total_click  int(10) unsigned Default NULL,
1006_total_usr_num  int(10) unsigned Default NULL,
1006_ratio  int(10) unsigned Default NULL,
1007_total_click  int(10) unsigned Default NULL,
1007_total_usr_num  int(10) unsigned Default NULL,
1007_ratio  int(10) unsigned Default NULL,
 PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='赚金币用户体验数据表'


drop table credit_experience11
CREATE  TABLE `credit_experience11` (
id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
date int(10) unsigned NOT NULL,
11001_total_click  int(10) unsigned default NULL,
11001_total_usr_num  int(10) unsigned default NULL,
11001_ratio  int(10) unsigned default NULL,
11002_total_click  int(10) unsigned default NULL,
11002_total_usr_num  int(10) unsigned default NULL,
11002_ratio  int(10) unsigned default NULL,
11003_total_click  int(10) unsigned default NULL,
11003_total_usr_num  int(10) unsigned default NULL,
11003_ratio  int(10) unsigned default NULL,
11004_total_click  int(10) unsigned default NULL,
11004_total_usr_num  int(10) unsigned default NULL,
11004_ratio  int(10) unsigned default NULL,
11005_total_click  int(10) unsigned default NULL,
11005_total_usr_num  int(10) unsigned default NULL,
11005_ratio  int(10) unsigned default NULL,
11006_total_click  int(10) unsigned default NULL,
11006_total_usr_num  int(10) unsigned default NULL,
11006_ratio  int(10) unsigned default NULL,
 PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='赚金币用户体验数据表'




drop table credit_experience1213
CREATE  TABLE `credit_experience1213` (
id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
date int(10) unsigned Not NULL,
12001_total_click  int(10) unsigned  default NULL,
12001_total_usr_num  int(10) unsigned default  NULL,
12001_ratio  int(10) unsigned default NULL,
12002_total_click  int(10) unsigned default NULL,
12002_total_usr_num  int(10) unsigned default NULL,
12002_ratio  int(10) unsigned default NULL,
12003_total_click  int(10) unsigned default NULL,
12003_total_usr_num  int(10) unsigned default NULL,
12003_ratio  int(10) unsigned default NULL,
12004_total_click  int(10) unsigned default NULL,
12004_total_usr_num  int(10) unsigned default NULL,
12004_ratio  int(10) unsigned default NULL,
13001_total_click  int(10) unsigned default NULL,
13001_total_usr_num  int(10) unsigned default NULL,
13001_ratio  int(10) unsigned default NULL,
13002_total_click  int(10) unsigned default NULL,
13002_total_usr_num  int(10) unsigned default NULL,
13002_ratio  int(10) unsigned default NULL,
 PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='赚金币用户体验数据表'



CREATE  TABLE `credit_experience14` (
id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
date int(10) unsigned Not null,
14001_total_click  int(10) unsigned ,
14001_total_usr_num  int(10) unsigned ,
14001_ratio  int(10) unsigned ,
14002_total_click  int(10) unsigned ,
14002_total_usr_num  int(10) unsigned ,
14002_ratio  int(10) unsigned ,
14003_total_click  int(10) unsigned ,
14003_total_usr_num  int(10) unsigned ,
14003_ratio  int(10) unsigned ,
14004_total_click  int(10) unsigned ,
14004_total_usr_num  int(10) unsigned ,
14004_ratio  int(10) unsigned ,
140041_total_click  int(10) unsigned ,
140041_total_usr_num  int(10) unsigned ,
140041_ratio  int(10) unsigned ,
140042_total_click  int(10) unsigned ,
140042_total_usr_num  int(10) unsigned ,
140042_ratio  int(10) unsigned ,
140043_total_click  int(10) unsigned ,
140043_total_usr_num  int(10) unsigned ,
140043_ratio  int(10) unsigned ,
14005_total_click  int(10) unsigned ,
14005_total_usr_num  int(10) unsigned ,
14005_ratio  int(10) unsigned ,
14006_total_click  int(10) unsigned ,
14006_total_usr_num  int(10) unsigned ,
14006_ratio  int(10) unsigned ,
14007_total_click  int(10) unsigned ,
14007_total_usr_num  int(10) unsigned ,
14007_ratio  int(10) unsigned ,
14008_total_click  int(10) unsigned ,
14008_total_usr_num  int(10) unsigned ,
14008_ratio  int(10) unsigned ,
14009_total_click  int(10) unsigned ,
14009_total_usr_num  int(10) unsigned ,
14009_ratio  int(10) unsigned ,
PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='赚金币用户体验数据表'











CREATE TABLE `credit_transaction` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `date` int(10) unsigned DEFAULT NULL COMMENT '时间',
  `raffle_user_num` int(10) unsigned DEFAULT NULL COMMENT '抽奖总用户数',
  `raffle_count` int(10) unsigned DEFAULT NULL COMMENT '抽奖总次数', 
  `credit_user_num` int(10) unsigned DEFAULT NULL COMMENT '积分发放用户数',
  `credit_issue` int(10) unsigned DEFAULT NULL COMMENT '积分发放量',
  `ali_usernum` int(10) unsigned DEFAULT NULL COMMENT '积分兑换支付宝的用户数',
  `ali_point` int(10) unsigned DEFAULT NULL COMMENT '积分兑换支付宝的数量',
  `exchange_cell_usernum` int(10) unsigned DEFAULT NULL COMMENT '积分兑换话费用户数',
  `exchange_cell_point_count` int(10) unsigned DEFAULT NULL COMMENT '积分兑换话费量',
  `scheme1` int(10) unsigned DEFAULT NULL COMMENT '方案1',
  `scheme2` int(10) unsigned DEFAULT NULL COMMENT '方案2',
  `scheme3` int(10) unsigned DEFAULT NULL COMMENT '方案3',
  `scheme4` int(10) unsigned DEFAULT NULL COMMENT '方案4',
  `scheme5` int(10) unsigned DEFAULT NULL COMMENT '方案5',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='赚金币兑换数据表'
