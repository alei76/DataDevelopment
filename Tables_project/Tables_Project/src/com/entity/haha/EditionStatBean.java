package com.entity.haha;

import com.dao.hahaCal.EnumBeanFieldType;


/**
*������MySQLToBean�����Զ�����
*��ע(���ݱ��comment�ֶ�)��haha�����汾ͳ�Ʊ�
*@author childlikeman@gmail.com,http://t.qq.com/lostpig
*@since 2014-04-03 17:33:47
*/


/*
 * CREATE TABLE `haha_edition_stat` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Time` int(10) unsigned NOT NULL COMMENT 'ʱ��',
  `InstallCount` int(10) NOT NULL COMMENT '���հ�װ��',
  `UninstallCount` int(10) NOT NULL COMMENT '����ж����',
  `LaunchCount` int(10) NOT NULL COMMENT '�����û�����������',
  `ActiveCount` int(10) NOT NULL COMMENT '���ջ�Ծ��',
  `ActiveRate` decimal(6,2) unsigned DEFAULT NULL COMMENT '���ջ�Ծ��',
  `UninstallRate` decimal(6,2) unsigned DEFAULT NULL COMMENT '����ж����',
  `VCU_COUNT` int(10) NOT NULL COMMENT '����Ч�û�',
  `Edition` varchar(60) NOT NULL COMMENT '�汾��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='haha�����汾ͳ�Ʊ�'*/

/*
 * 
 *select  20140104 as Time,count(distinct a.uid) as InstallCount, b.UninstallCount as UninstallCount,c.LaunchCount as LaunchCount, d.ActiveCount as ActiveCount,
0 as ActiveRate,0 as UninstallRate,
e.vcuNum as VCU_COUNT,a.SoftVer as Edition

from install20140104 as a 

left join
(
    select a.SoftVer,count(distinct a.uid) as UninstallCount from uninstall20140104 as a 
)b on a.softVer=b.softVer

left join
(
    select a.SoftVer,count(distinct a.uid) as LaunchCount from online20140104 as a 
)c on a.SoftVer=c.softVer

left join
(
   select a.SoftVersion,count(distinct a.uid) as ActiveCount from user_action20140104 as a
)d on a.SoftVer=d.SoftVersion

left join
(
   select a.SoftVersion,count(distinct a.uid)as vcuNum from user_action20140104 as a where a.actionnum>1
)e on a.SoftVer=e.SoftVersion

 * */



public class EditionStatBean implements IMysqlBean
{
	
public String sql=""
		+ "select [?] as Time,count(distinct a.uid) as InstallCount, "
		+ "b.UninstallCount as UninstallCount,c.LaunchCount as LaunchCount,"
		+ " d.ActiveCount as ActiveCount,0 as ActiveRate,0 as UninstallRate,"
		+ "e.vcuNum as VCU_COUNT,a.SoftVer as Edition from install[?] as a "
		+ "left join(select a.SoftVer,count(distinct a.uid) as UninstallCount from "
		+ "uninstall[?] as a)b on a.softVer=b.softVer "
		+ "left join"
		+ "(select a.SoftVer,count(distinct a.uid) as LaunchCount from online[?] as a )c "
		+ "on a.SoftVer=c.softVer "
		+ "left join"
		+ "(select a.SoftVersion,count(distinct a.uid) as ActiveCount from user_action[?] as a)d on a.SoftVer=d.SoftVersion"
		+ " left join("
		+ "select a.SoftVersion,count(distinct a.uid)as vcuNum from user_action[?] as a where a.actionnum>1)e"
		+ " on a.SoftVer=e.SoftVersion";
		
          //StringBuffer sql2= new StringBuffer("dddd");
         
          
	
private int Time;// ����
public String getSql() {
	return sql;
}

public void setSql(String sql) {
	this.sql = sql;
}

public int getTime() {
	return Time;
}

private int InstallCount;//���հ�װ��
private int UninstallCount;//����ж����
private int LaunchCount;//�����û�����������
private int ActiveCount;//���ջ�Ծ��
private Double ActiveRate;  //�Զ�����bean�Ĺ���������
private Double UninstallRate;
private int VCU_COUNT;//����Ч�û�
private String Edition;//�汾��





///?????????����������⣬ע��time������
@AnnotationGenSQL4Bean(sql1="select [?]",et=EnumBeanFieldType.INT, TbColumnName = "Time")
public void setTime(int time) {
	Time = time;
}

public String getEdition(){
	return this.Edition;
}


@AnnotationGenSQL4Bean(sql1="select from install[?] as a,uninstall[?] as b",et=EnumBeanFieldType.STRING, TbColumnName = "Edition")
public void setEdition(String Edition){
	this.Edition=Edition;
}


public int getInstallCount(){
	return this.InstallCount;
}

//@AnnotationGenSQL4Bean(sql1="select count(distinct a.uid) from install[?] as a group by a.Edition ",et=EnumBeanFieldType.INT, TbColumnName = "InstallCount")
@AnnotationGenSQL4Bean(sql1="select count(distinct a.uid) from install[?] as a order by a.Edition",et=EnumBeanFieldType.INT, TbColumnName = "InstallCount")
// # �ð汾�����û���
public void setInstallCount(int InstallCount){
	this.InstallCount=InstallCount;
}


public int getUninstallCount(){
	return this.UninstallCount;
}

@AnnotationGenSQL4Bean(sql1="select count(distinct a.uid) from uninstall[?] as a order by a.Edition ",et=EnumBeanFieldType.INT, TbColumnName = "UninstallCount")
public void setUninstallCount(int UninstallCount){
	this.UninstallCount=UninstallCount;
}
public int getLaunchCount(){
	return this.LaunchCount;
}
@AnnotationGenSQL4Bean(sql1="select count(distinct a.uid) from online[?] as a order by a.Edition ",et=EnumBeanFieldType.INT, TbColumnName = "LaunchCount")
public void setLaunchCount(int LaunchCount){
	this.LaunchCount=LaunchCount;
}
public int getActiveCount(){
	return this.ActiveCount;
}
@AnnotationGenSQL4Bean(sql1="select count(distinct a.uid) from install[?] as a group by a.Edition ",et=EnumBeanFieldType.INT, TbColumnName = "ActiveCount")
public void setActiveCount(int ActiveCount){
	this.ActiveCount=ActiveCount;
}
public Double getActiveRate(){
	return this.ActiveRate;
}

//////////////////////////////////////////////////////////////////////

@AnnotationGenSQL4Bean(sql1="select count(distinct a.uid) from install[?] as a group by a.Edition ",et=EnumBeanFieldType.Double, TbColumnName = "ActiveRate")
public void setActiveRate(Double ActiveRate){
	this.ActiveRate=ActiveRate;
}
public Double getUninstallRate(){
	return this.UninstallRate;
}
@AnnotationGenSQL4Bean(sql1="select count(distinct a.uid) from install20140309 as a group by a.Edition ",et=EnumBeanFieldType.Double, TbColumnName = "UninstallRate")
public void setUninstallRate(Double UninstallRate){
	this.UninstallRate=UninstallRate;
}
public int getVCU_COUNT(){
	return this.VCU_COUNT;
}
@AnnotationGenSQL4Bean(sql1="select count(distinct a.uid) from user_action[?] as a group by a.Edition ",et=EnumBeanFieldType.INT, TbColumnName = "VCU_COUNT")
public void setVCU_COUNT(int VCU_COUNT){
	this.VCU_COUNT=VCU_COUNT;
}

@Override
public String getSQL_insert() {
	// TODO Auto-generated method stub
	return "insert into haha_edition_stat(Time,InstallCount,UninstallCount,LaunchCount,ActiveCount,ActiveRate,UninstallRate,VCU_COUNT,Edition)" +
				" values(?,?,?,?,?,?,?,?,?)";
}





}
