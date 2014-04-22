package com.entity.haha;

/**
*此类由MySQLToBean工具自动生成
*备注(数据表的comment字段)：haha日历用户体验数据表
*@author childlikeman@gmail.com,http://t.qq.com/lostpig
*@since 2014-04-03 17:35:37
*/

/*********
 * 源表
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
  `id` int(10) NOT NULL COMMENT '自增',
  `Time` int(10) NOT NULL COMMENT '时间20140326',
  `ref_click` int(10) NOT NULL COMMENT '刷新按钮 点击次数',
  `ref_num` int(10) NOT NULL COMMENT '刷新按钮 日点击用户量',
  `weather_click` int(10) NOT NULL COMMENT '天气图标 日点击次数',
  `weather_num` int(10) NOT NULL,
  `year_choose_click` int(10) NOT NULL COMMENT '年份选择下拉框 日点击次数',
  `year_choose_num` int(10) NOT NULL COMMENT '年份选择下拉框 日点击用户量',
  `month_choose_click` int(10) NOT NULL COMMENT '月份选择下拉框 日点击次数',
  `month_choose_num` int(10) NOT NULL COMMENT '月份选择下拉框 日点击用户数',
  `rili_click` int(10) NOT NULL COMMENT '日历界面点击 日点击次数',
  `rili_num` int(10) NOT NULL COMMENT '日历界面点击 日点击用户量',
  `notepad_click` int(10) NOT NULL COMMENT '记事本使用 日点击次数',
  `notepad_num` int(10) NOT NULL COMMENT '记事本使用 日点击用户量',
  `skin_click` int(10) NOT NULL COMMENT '换肤图标 日点击次数',
  `skin_num` int(10) NOT NULL COMMENT '换肤图标 日点击用户量',
  `trans_click` int(10) NOT NULL COMMENT '透明度使用 日点击次数',
  `trans_num` int(10) NOT NULL COMMENT '透明度使用 日点击用户数',
  `today_click` int(10) NOT NULL COMMENT '回到今日图标 日点击次数',
  `today_num` int(10) NOT NULL COMMENT '回到今日图标 日点击用户量',
  `button_click` int(10) NOT NULL COMMENT '记事本按钮 日点击次数',
  `button_num` int(10) NOT NULL COMMENT '记事本按钮 日点击用户量',
  `close_open_click` int(10) NOT NULL COMMENT '关闭开机自动运行 日点击次数',
  `close_open_num` int(10) NOT NULL COMMENT '关闭开机自动运行 日点击用户量',
  `synch_click` int(10) NOT NULL COMMENT '时间同步 日点击次数',
  `synch_num` int(10) NOT NULL COMMENT '时间同步 日点击用户量',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
 
 **************/

/**目标表
 * CREATE TABLE `haha_experience` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Time` int(10) unsigned NOT NULL COMMENT '时间',
  `UXTypeName` varchar(32) NOT NULL COMMENT '用户体验类型',
  `UXID` int(10) NOT NULL COMMENT '用户体验标识符',
  `clickCount` int(10) NOT NULL COMMENT '点击数',
  `userCount` int(10) NOT NULL COMMENT '点击用户数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='haha日历用户体验数据表'
 * */






public class UsrExperienceStatOLDBean{
private String UXTypeName;//用户体验类型
private int UXID;//用户体验标识符
private int clickCount;//点击数
private int userCount;//点击用户数
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