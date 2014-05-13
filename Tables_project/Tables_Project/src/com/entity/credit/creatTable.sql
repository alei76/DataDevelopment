
CREATE TABLE `credit_future` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '����id',
  `date` int(10) unsigned DEFAULT NULL COMMENT 'ʱ��',
  `qid` varchar(10) DEFAULT NULL COMMENT '����',
  `activetype` int(10) unsigned DEFAULT NULL COMMENT '10000��ʾ���գ�10001��ʾ7�գ�10002��ʾ30��',
  `active_count` int(10) unsigned DEFAULT NULL COMMENT '��Ծ��',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='׬������û�future���ݱ�'



CREATE TABLE `credit_third` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '����id',
  `date` int(10) unsigned DEFAULT NULL COMMENT 'ʱ��',
  `software_name` varchar(10) DEFAULT NULL COMMENT '�������',
  `download_count` int(10) unsigned DEFAULT NULL COMMENT '���ش���', 
  `auto_launch_num` int(10) unsigned DEFAULT NULL COMMENT '��װ���Զ��򿪴���',
  `self_launch_count` int(10) unsigned DEFAULT NULL COMMENT '�û������򿪴���',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='׬�������Ӧ�����ݱ�'


CREATE TABLE `credit_new_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '����id',
  `date` int(10) unsigned DEFAULT NULL COMMENT 'ʱ��',
  `qid` varchar(10) DEFAULT NULL COMMENT '����',
  `install_count` int(10) unsigned DEFAULT NULL COMMENT '��װ��',
  `active_count` int(10) unsigned DEFAULT NULL COMMENT '��Ծ��',
  `download_count` int(10) unsigned DEFAULT NULL COMMENT '������',
  `nextDay_active_count` int(10) unsigned DEFAULT NULL COMMENT '���ջ�Ծ��',
  `nextDay_active_rate` int(10) unsigned DEFAULT NULL COMMENT '���ջ�Ծ��',
  `week_active_count` int(10) unsigned DEFAULT NULL COMMENT '���ջ�Ծ��',
  `week_active_rate` int(10) unsigned DEFAULT NULL COMMENT '���ջ�Ծ��',
  `month_active_count` int(10) unsigned DEFAULT NULL COMMENT '��ʮ�ջ�Ծ��',
  `month_active_rate` int(10) unsigned DEFAULT NULL COMMENT '��ʮ�ջ�Ծ��',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='׬������û����ݱ�'

create table credit_basic(`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '����id',
  `date` int(10) unsigned DEFAULT NULL COMMENT 'ʱ��',
  `qid` varchar(10) DEFAULT NULL COMMENT '����',
  `launch_count` int(10) unsigned DEFAULT NULL COMMENT '����������',
  `active_count` int(10) unsigned DEFAULT NULL COMMENT '��Ծ��',
  `download_count` int(10) unsigned DEFAULT NULL COMMENT '������',
  `third_count` int(10) unsigned DEFAULT NULL COMMENT '�򿪵�����Ӧ��',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='׬��һ������ݱ�'


drop table credit_experience1
CREATE  TABLE `credit_experience1` (
id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '����id',
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
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='׬����û��������ݱ�'


drop table credit_experience11
CREATE  TABLE `credit_experience11` (
id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '����id',
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
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='׬����û��������ݱ�'




drop table credit_experience1213
CREATE  TABLE `credit_experience1213` (
id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '����id',
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
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='׬����û��������ݱ�'



CREATE  TABLE `credit_experience14` (
id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '����id',
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
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='׬����û��������ݱ�'











CREATE TABLE `credit_transaction` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '����id',
  `date` int(10) unsigned DEFAULT NULL COMMENT 'ʱ��',
  `raffle_user_num` int(10) unsigned DEFAULT NULL COMMENT '�齱���û���',
  `raffle_count` int(10) unsigned DEFAULT NULL COMMENT '�齱�ܴ���', 
  `credit_user_num` int(10) unsigned DEFAULT NULL COMMENT '���ַ����û���',
  `credit_issue` int(10) unsigned DEFAULT NULL COMMENT '���ַ�����',
  `ali_usernum` int(10) unsigned DEFAULT NULL COMMENT '���ֶһ�֧�������û���',
  `ali_point` int(10) unsigned DEFAULT NULL COMMENT '���ֶһ�֧����������',
  `exchange_cell_usernum` int(10) unsigned DEFAULT NULL COMMENT '���ֶһ������û���',
  `exchange_cell_point_count` int(10) unsigned DEFAULT NULL COMMENT '���ֶһ�������',
  `scheme1` int(10) unsigned DEFAULT NULL COMMENT '����1',
  `scheme2` int(10) unsigned DEFAULT NULL COMMENT '����2',
  `scheme3` int(10) unsigned DEFAULT NULL COMMENT '����3',
  `scheme4` int(10) unsigned DEFAULT NULL COMMENT '����4',
  `scheme5` int(10) unsigned DEFAULT NULL COMMENT '����5',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='׬��Ҷһ����ݱ�'
