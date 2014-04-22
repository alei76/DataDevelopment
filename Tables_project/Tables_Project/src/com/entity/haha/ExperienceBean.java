package com.entity.haha;

import java.util.HashMap;
/**
*此类由MySQLToBean工具自动生成
*备注(数据表的comment字段)：无备注信息
*@author childlikeman@gmail.com,http://t.qq.com/lostpig
*@since 2014-04-15 13:20:25
*/
import java.util.Map;

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

/**
 * 
 * 
 * 1 刷新按钮,ref_click,ref_num
2 天气图标,weather_click,weather_num
3 年份选择下拉框,year_choose_click,year_choose_num
4 月份选择下拉框,month_choose_click,month_choose_num
5 日历界面点击,rili_click, rili_num
6 记事本使用, notepad_click,notepad_num   
7 透明度使用, trans_click,trans_num
8 回到“今”日图标,today_click,today_num
9 关闭开机自动运行,close_open_click,close_open_num
10 时间同步,synch_click,synch_num
11. 7654
 * 
 * */
public class ExperienceBean implements IMysqlBean
{
private Integer id;//自增
private Integer time=0;//时间20140326
private Integer ref_click=0;//刷新按钮 点击次数
private Integer ref_num=0;//刷新按钮 日点击用户量
private Integer weather_click=0;//天气图标 日点击次数
private Integer weather_num=0;
private Integer year_choose_click=0;//年份选择下拉框 日点击次数
private Integer year_choose_num=0;//年份选择下拉框 日点击用户量
private Integer month_choose_click=0;//月份选择下拉框 日点击次数
private Integer month_choose_num=0;//月份选择下拉框 日点击用户数
private Integer rili_click=0;//日历界面点击 日点击次数
private Integer rili_num=0;//日历界面点击 日点击用户量
private Integer notepad_click=0;//记事本使用 日点击次数
private Integer notepad_num=0;//记事本使用 日点击用户量
private Integer skin_click=0;//换肤图标 日点击次数
private Integer skin_num=0;//换肤图标 日点击用户量
private Integer trans_click=0;//透明度使用 日点击次数
private Integer trans_num=0;//透明度使用 日点击用户数
private Integer today_click=0;//回到今日图标 日点击次数
private Integer today_num=0;//回到今日图标 日点击用户量
private Integer button_click=0;//记事本按钮 日点击次数
private Integer button_num=0;//记事本按钮 日点击用户量
private Integer close_open_click=0;//关闭开机自动运行 日点击次数
private Integer close_open_num=0;//关闭开机自动运行 日点击用户量
private Integer synch_click=0;//时间同步 日点击次数
private Integer synch_num=0;//时间同步 日点击用户量

public String getSql() {
	return sql;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getTime() {
	return time;
}
public void setTime(Integer time) {
	this.time = time;
}
public Integer getRef_click() {
	return ref_click;
}
public void setRef_click(Integer ref_click) {
	this.ref_click = ref_click;
}
public Integer getRef_num() {
	return ref_num;
}
public void setRef_num(Integer ref_num) {
	this.ref_num = ref_num;
}
public Integer getWeather_click() {
	return weather_click;
}
public void setWeather_click(Integer weather_click) {
	this.weather_click = weather_click;
}
public Integer getWeather_num() {
	return weather_num;
}
public void setWeather_num(Integer weather_num) {
	this.weather_num = weather_num;
}
public Integer getYear_choose_click() {
	return year_choose_click;
}
public void setYear_choose_click(Integer year_choose_click) {
	this.year_choose_click = year_choose_click;
}
public Integer getYear_choose_num() {
	return year_choose_num;
}
public void setYear_choose_num(Integer year_choose_num) {
	this.year_choose_num = year_choose_num;
}
public Integer getMonth_choose_click() {
	return month_choose_click;
}
public void setMonth_choose_click(Integer month_choose_click) {
	this.month_choose_click = month_choose_click;
}
public Integer getMonth_choose_num() {
	return month_choose_num;
}
public void setMonth_choose_num(Integer month_choose_num) {
	this.month_choose_num = month_choose_num;
}
public Integer getRili_click() {
	return rili_click;
}
public void setRili_click(Integer rili_click) {
	this.rili_click = rili_click;
}
public Integer getRili_num() {
	return rili_num;
}
public void setRili_num(Integer rili_num) {
	this.rili_num = rili_num;
}
public Integer getNotepad_click() {
	return notepad_click;
}
public void setNotepad_click(Integer notepad_click) {
	this.notepad_click = notepad_click;
}
public Integer getNotepad_num() {
	return notepad_num;
}
public void setNotepad_num(Integer notepad_num) {
	this.notepad_num = notepad_num;
}
public Integer getSkin_click() {
	return skin_click;
}
public void setSkin_click(Integer skin_click) {
	this.skin_click = skin_click;
}
public Integer getSkin_num() {
	return skin_num;
}
public void setSkin_num(Integer skin_num) {
	this.skin_num = skin_num;
}
public Integer getTrans_click() {
	return trans_click;
}
public void setTrans_click(Integer trans_click) {
	this.trans_click = trans_click;
}
public Integer getTrans_num() {
	return trans_num;
}
public void setTrans_num(Integer trans_num) {
	this.trans_num = trans_num;
}
public Integer getToday_click() {
	return today_click;
}
public void setToday_click(Integer today_click) {
	this.today_click = today_click;
}
public Integer getToday_num() {
	return today_num;
}
public void setToday_num(Integer today_num) {
	this.today_num = today_num;
}
public Integer getButton_click() {
	return button_click;
}
public void setButton_click(Integer button_click) {
	this.button_click = button_click;
}
public Integer getButton_num() {
	return button_num;
}
public void setButton_num(Integer button_num) {
	this.button_num = button_num;
}
public Integer getClose_open_click() {
	return close_open_click;
}
public void setClose_open_click(Integer close_open_click) {
	this.close_open_click = close_open_click;
}
public Integer getClose_open_num() {
	return close_open_num;
}
public void setClose_open_num(Integer close_open_num) {
	this.close_open_num = close_open_num;
}
public Integer getSynch_click() {
	return synch_click;
}
public void setSynch_click(Integer synch_click) {
	this.synch_click = synch_click;
}
public Integer getSynch_num() {
	return synch_num;
}
public void setSynch_num(Integer synch_num) {
	this.synch_num = synch_num;
}
/*
public String getSql_usercount() {
	return sql_usercount;
}
public void setSql_usercount(String sql_usercount) {
	this.sql_usercount = sql_usercount;
}
public String getSql_clickcount() {
	return sql_clickcount;
}
public void setSql_clickcount(String sql_clickcount) {
	this.sql_clickcount = sql_clickcount;
}
public void setSql(String sql) {
	this.sql = sql;
}
*/

//public String sql_usercount="select count(distinct a.uid) from user_action[?] as a where a.action=[?]";
//public String sql_clickcount="select count(a.actionnum) from user_action[?] as a where a.action=[?]";
public String sql="select a.action,count(distinct a.uid) as userCount,count(a.actionnum)as actionCount from user_action[?] as a group by a.action";


@Override
public String getSQL_insert() {
	// TODO Auto-generated method stub
	return "insert into haha_experience(Time,ref_click,ref_num,weather_click,weather_num,year_choose_click,year_choose_num,month_choose_click,month_choose_num,rili_click,rili_num,notepad_click,notepad_num,skin_click,skin_num,trans_click,trans_num,today_click,today_num,button_click,button_num,close_open_click,close_open_num,synch_click,synch_num) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
}





}