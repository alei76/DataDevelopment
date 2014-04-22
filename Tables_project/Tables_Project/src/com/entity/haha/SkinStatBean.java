package com.entity.haha;

import com.dao.hahaCal.EnumBeanFieldType;


/**
*此类由MySQLToBean工具自动生成
*备注(数据表的comment字段)：haha日历皮肤应用表
*@author childlikeman@gmail.com,http://t.qq.com/lostpig
*@since 2014-04-03 17:40:14
*/

/****
 * 原先的表
 * CREATE TABLE `user_skin_select20140104` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `softid` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `softversion` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `qid` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `skinid` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `selectnum` int(10) DEFAULT NULL,
  `ipaddress` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8
 * ***/


/**
 * 目标表
 * CREATE TABLE `haha_skin_stat` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Time` int(10) unsigned NOT NULL COMMENT '时间',
  `SkinName` varchar(32) NOT NULL COMMENT '皮肤名',
  `SkinID` int(32) NOT NULL COMMENT '皮肤id',
  `clickCount` int(32) NOT NULL COMMENT '点击数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='haha日历皮肤应用表'
 * @author admin
 *
 */




public class SkinStatBean implements IMysqlBean
{
	
	
	/*
	 * select 20140104 as Time,"" as SkinName,a.skinid as SkinID,a.selectnum as clickCount  from user_skin_select20140104 as a
	 * */
public String sql="select [?] as Time,\"not defined\" as SkinName,a.skinid as SkinID,a.selectnum as clickCount  from user_skin_select[?] as a";
private int Time=0;// 日期
private String SkinName="";//皮肤名
private String SkinID="";//皮肤id
private int clickCount=0;//点击数

public String getSkinName(){
	return this.SkinName;
}
@AnnotationGenSQL4Bean(sql1="",et=EnumBeanFieldType.STRING, TbColumnName = "SkinName")
public void setSkinName(String SkinName){
	this.SkinName=SkinName;
}
public String getSkinID(){
	return this.SkinID;
}
@AnnotationGenSQL4Bean(sql1="",et=EnumBeanFieldType.STRING, TbColumnName = "SkinID")
public void setSkinID(String SkinID){
	this.SkinID=SkinID;
}
public int getClickCount(){
	return this.clickCount;
}
@AnnotationGenSQL4Bean(sql1="",et=EnumBeanFieldType.INT, TbColumnName = "clickCount")
public void setClickCount(int clickCount){
	this.clickCount=clickCount;
}
public int getTime() {
	return Time;
}

@AnnotationGenSQL4Bean(sql1="select [?]",et=EnumBeanFieldType.INT, TbColumnName = "Time")
public void setTime(int time) {
	Time = time;
}
@Override
public String getSQL_insert() {
	// TODO Auto-generated method stub
	//select 20140104 as Time,"" as SkinName,a.skinid as SkinID,a.selectnum as clickCount  from user_skin_select20140104 as a
	return "insert into haha_skin_stat(Time,SkinName,SkinID,clickCount) values(?,?,?,?)";
}

}
