package com.entity.haha;

/**
*������MySQLToBean�����Զ�����
*��ע(���ݱ��comment�ֶ�)��haha�����û��������ݱ�
*@author childlikeman@gmail.com,http://t.qq.com/lostpig
*@since 2014-04-03 17:35:37
*/

/*********
 * Դ��
 * CREATE TABLE `user_action20140104` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `softid` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `softversion` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `qid` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `action` tinyint(4) DEFAULT NULL,
  `actionnum` int(10) DEFAULT NULL,
  `ipaddress` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8
 ***********/


/***
 * CREATE TABLE `haha_experience` (
  `id` int(10) NOT NULL COMMENT '����',
  `Time` int(10) NOT NULL COMMENT 'ʱ��20140326',
  `ref_click` int(10) NOT NULL COMMENT 'ˢ�°�ť �������',
  `ref_num` int(10) NOT NULL COMMENT 'ˢ�°�ť �յ���û���',
  `weather_click` int(10) NOT NULL COMMENT '����ͼ�� �յ������',
  `weather_num` int(10) NOT NULL,
  `year_choose_click` int(10) NOT NULL COMMENT '���ѡ�������� �յ������',
  `year_choose_num` int(10) NOT NULL COMMENT '���ѡ�������� �յ���û���',
  `month_choose_click` int(10) NOT NULL COMMENT '�·�ѡ�������� �յ������',
  `month_choose_num` int(10) NOT NULL COMMENT '�·�ѡ�������� �յ���û���',
  `rili_click` int(10) NOT NULL COMMENT '���������� �յ������',
  `rili_num` int(10) NOT NULL COMMENT '���������� �յ���û���',
  `notepad_click` int(10) NOT NULL COMMENT '���±�ʹ�� �յ������',
  `notepad_num` int(10) NOT NULL COMMENT '���±�ʹ�� �յ���û���',
  `skin_click` int(10) NOT NULL COMMENT '����ͼ�� �յ������',
  `skin_num` int(10) NOT NULL COMMENT '����ͼ�� �յ���û���',
  `trans_click` int(10) NOT NULL COMMENT '͸����ʹ�� �յ������',
  `trans_num` int(10) NOT NULL COMMENT '͸����ʹ�� �յ���û���',
  `today_click` int(10) NOT NULL COMMENT '�ص�����ͼ�� �յ������',
  `today_num` int(10) NOT NULL COMMENT '�ص�����ͼ�� �յ���û���',
  `button_click` int(10) NOT NULL COMMENT '���±���ť �յ������',
  `button_num` int(10) NOT NULL COMMENT '���±���ť �յ���û���',
  `close_open_click` int(10) NOT NULL COMMENT '�رտ����Զ����� �յ������',
  `close_open_num` int(10) NOT NULL COMMENT '�رտ����Զ����� �յ���û���',
  `synch_click` int(10) NOT NULL COMMENT 'ʱ��ͬ�� �յ������',
  `synch_num` int(10) NOT NULL COMMENT 'ʱ��ͬ�� �յ���û���',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
 
 **************/

/**Ŀ���
 * CREATE TABLE `haha_experience` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Time` int(10) unsigned NOT NULL COMMENT 'ʱ��',
  `UXTypeName` varchar(32) NOT NULL COMMENT '�û���������',
  `UXID` int(10) NOT NULL COMMENT '�û������ʶ��',
  `clickCount` int(10) NOT NULL COMMENT '�����',
  `userCount` int(10) NOT NULL COMMENT '����û���',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='haha�����û��������ݱ�'
 * */






public class UsrExperienceStatOLDBean{
private String UXTypeName;//�û���������
private int UXID;//�û������ʶ��
private int clickCount;//�����
private int userCount;//����û���
public String getUXTypeName(){
	return this.UXTypeName;
}
public void setUXTypeName(String UXTypeName){
	this.UXTypeName=UXTypeName;
}
public int getUXID(){
	return this.UXID;
}
public void setUXID(int UXID){
	this.UXID=UXID;
}
public int getClickCount(){
	return this.clickCount;
}
public void setClickCount(int clickCount){
	this.clickCount=clickCount;
}
public int getUserCount(){
	return this.userCount;
}
public void setUserCount(int userCount){
	this.userCount=userCount;
}

}