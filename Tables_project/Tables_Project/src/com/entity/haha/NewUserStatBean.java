package com.entity.haha;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.dao.hahaCal.EnumBeanFieldType;

/**����field�ֿ���sqlʵ�֣���û�н�����sql����һ��ʵ�֣�����Ϊ��Щ���ܺ���һ��*/
/*
 * 
 * CREATE TABLE `HahaCalenderNewUserStat` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Time` int(10) unsigned NOT NULL COMMENT 'ʱ��',
  `InstallCount` int(10) NOT NULL COMMENT '�������û���װ��',
  `UninstallCount` int(10) NOT NULL COMMENT '�������û�ж����',
  `LaunchCount` int(32) NOT NULL COMMENT '�������û�����������',
  `ActiveCount` int(32) NOT NULL COMMENT '�������û���Ծ��',
  `NextDayActiveCount` int(32) NOT NULL COMMENT '�����û���Ծ��',
 `NextDayActiveRate` decimal(6,2) unsigned DEFAULT NULL COMMENT '���ջ�Ծ��',
 `WeekActiveCount` int(32) NOT NULL COMMENT '7�ջ�Ծ��',
 `WeekActiveRate` decimal(6,2) unsigned DEFAULT NULL COMMENT '7�ջ�Ծ��',
 `MonthActiveCount` int(32) NOT NULL COMMENT '30�ջ�Ծ��',
 `MonthActiveRate` decimal(6,2) unsigned DEFAULT NULL COMMENT '30�ջ�Ծ��',
`newVCU` int(10) unsigned DEFAULT 0 COMMENT '��VCU�û�',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='haha�������û�ͳ�Ʊ�'
 * 
 * */
@AnnotationSQLCommon4Bean(sql1="select count(distinct b.uid)from install[?] as a inner join user_action[?] as b on a.UID=b.uid",et=EnumBeanFieldType.STRING)

public class NewUserStatBean implements IMysqlBean

{
	
	
	private String insert_sql= "insert into haha_new_user_stat(Time,newVCU,InstallCount,UninstallCount,LaunchCount,ActiveCount,NextDayActiveCount,NextDayActiveRate,WeekActiveCount,WeekActiveRate,MonthActiveCount,MonthActiveRate)" +
			" values(?,?,?,?,?,?,?,?,?,?,?,?)";

	private Integer id;  //bean�ı��
	private String time="";  //ʱ��
	private Integer InstallCount; //�������û���װ����ȥ��
	private Integer UninstallCount; //�������û�ж������ȥ��
	private Integer LaunchCount; // �������û�����������
	private Integer ActiveCount; //�������û���Ծ��
	private Integer NextDayActiveCount=0; //���ջ�Ծ��
	private Double NextDayActiveRate=0.0; //���ջ�Ծ��
	private Integer WeekActiveCount=0;//7�ջ�Ծ��
	private Double WeekActiveRate=0.0; //���ջ�Ծ��
	private Integer MonthActiveCount=0;
	private Double MonthActiveRate=0.0;
	private Integer newVCU;	
	
	@Override
	public String getSQL_insert() {
		// TODO Auto-generated method stub
		return insert_sql;
	}
	
	public Integer getId() 
	{
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTime() {
		return time;
	}
	@AnnotationGenSQL4Bean(sql1="select [?]",et=EnumBeanFieldType.STRING, TbColumnName = "Time")
	public void setTime(String time) {
		this.time = time;
	}
	
	public Integer getInstallCount() {
		return InstallCount;
	}
	@AnnotationGenSQL4Bean(sql1="select count(distinct a.UID)from install[?] as a",et=EnumBeanFieldType.INT, TbColumnName = "InstallCount")
	public void setInstallCount(Integer installCount) {
		InstallCount = installCount;
	}
	
	public Integer getUninstallCount() {
		return UninstallCount;
	}
	@AnnotationGenSQL4Bean(sql1="select count(distinct a.UID)from uninstall[?] as a ,install[?] as b where a.UID=b.UID",et=EnumBeanFieldType.INT, TbColumnName = "UninstallCount")
	public void setUninstallCount(Integer uninstallCount) {
		UninstallCount = uninstallCount;
	}
	
	public Integer getLaunchCount() {
		return LaunchCount;
	}
	@AnnotationGenSQL4Bean(sql1="select count(distinct a.UID) from online[?] as a , install[?] as b where a.UID=b.UID",et=EnumBeanFieldType.INT, TbColumnName = "LaunchCount")
	public void setLaunchCount(Integer launchCount) {
		LaunchCount = launchCount;
	}
	
	public Integer getActiveCount() {
		return ActiveCount;
	}
	@AnnotationGenSQL4Bean(sql1="select count(distinct a.uid)from user_action[?] as a",et=EnumBeanFieldType.INT, TbColumnName = "ActiveCount")
	public void setActiveCount(Integer activeCount) {
		ActiveCount = activeCount;
	}
	
	
	public Integer getnewVCU() {
		return newVCU;
	}
	//select count(c.uid) from (select b.uid,b.mycount from (select  a.uid as uid, sum(a.actionnum) as mycount from user_action20140107 as a group by a.uid) b where b.mycount>2)as c inner join install20140107 as d on c.uid=d.uid
	@AnnotationGenSQL4Bean(sql1="select count(c.uid) from (select b.uid,b.mycount from (select  a.uid as uid, sum(a.actionnum) as mycount from user_action20140107 as a group by a.uid) b where b.mycount>2)as c inner join install20140107 as d on c.uid=d.uid",et=EnumBeanFieldType.INT,TbColumnName = "newVCU")
	//@AnnotationGenSQL4Bean(sql1="select count(b.uid) from (select  a.uid, sum(a.actionnum) as mycount from user_action[?] as a group by a.uid) b where b.mycount>2",et=EnumBeanFieldType.INT,TbColumnName = "newVCU")
	//select count(b.uid) from (((select  a.uid, sum(a.actionnum) as mycount from user_action20140107 as a group by a.uid) b where b.mycount>2)as a inner join install20140107 as b on a.UID=b.uid)
	public void setnewVCU(Integer newVCU) {
		this.newVCU = newVCU;
	}
	
	/***********************************************************************************************************************************/
	
	@AnnotationGetSQL4FutureBean(sql="select count(distinct b.uid)from install[?] as a inner join user_action[?] as b on a.UID=b.uid ",et=EnumBeanFieldType.INT)
	public Integer getNextDayActiveCount() {
		return NextDayActiveCount;
	}
	
	@AnnotationGetSQL4FutureBean(sql="select count(distinct b.uid)from install[?] as a inner join user_action[?] as b on a.UID=b.uid ",et=EnumBeanFieldType.INT)
	public Integer getWeekActiveCount() {
		return WeekActiveCount;
	}
	
	@AnnotationGetSQL4FutureBean(sql="select count(distinct b.uid)from install[?] as a inner join user_action[?] as b on a.UID=b.uid ",et=EnumBeanFieldType.INT)
	public Integer getMonthActiveCount() {
		return MonthActiveCount;
	}
	
	
	@AnnotationGetSQL4FutureBean(sql="select [?]/[?]",et=EnumBeanFieldType.Double)
	public Double getNextDayActiveRate() {
		return NextDayActiveRate;
	}
	
	@AnnotationGetSQL4FutureBean(sql="select [?]/[?] ",et=EnumBeanFieldType.Double)
	public Double getWeekActiveRate() {
		return WeekActiveRate;
	}
	
	@AnnotationGetSQL4FutureBean(sql="select [?]/[?]",et=EnumBeanFieldType.Double)
	public Double getMonthActiveRate() {
		return MonthActiveRate;
	}
	
	/***********************************************************************************************************************************/

    
	@AnnotationUpdateSQL4FutureBean(sql="update haha_new_user_stat set nextDayActiveCount = [?] where Time = [?]",et=EnumBeanFieldType.INT, name = "NextDayActiveCount")
	public void setNextDayActiveCount(Integer nextDayActiveCount) {
		NextDayActiveCount = nextDayActiveCount;
	}
	
	
	@AnnotationUpdateSQL4FutureBean(sql="update haha_new_user_stat set nextDayActiveRate = [?] where Time = [?]",et=EnumBeanFieldType.Double, name = "NextDayActiveRate")
	public void setNextDayActiveRate(Double nextDayActiveRate) {
		NextDayActiveRate = nextDayActiveRate;
	}
	
	@AnnotationUpdateSQL4FutureBean(sql="update haha_new_user_stat set WeekActiveCount = [?] where Time = [?]",et=EnumBeanFieldType.INT, name = "WeekActiveCount")
	public void setWeekActiveCount(Integer weekActiveCount) {
		WeekActiveCount = weekActiveCount;
	}
	
	@AnnotationUpdateSQL4FutureBean(sql="update haha_new_user_stat set WeekActiveRate = [?] where Time = [?]",et=EnumBeanFieldType.Double, name = "WeekActiveRate")
	public void setWeekActiveRate(Double weekActiveRate) {
		WeekActiveRate = weekActiveRate;
	}
	@AnnotationUpdateSQL4FutureBean(sql="update haha_new_user_stat set MonthActiveCount = [?] where Time = [?]",et=EnumBeanFieldType.INT, name = "MonthActiveCount")
	public void setMonthActiveCount(Integer monthActiveCount) {
		MonthActiveCount = monthActiveCount;
	}
	
	
	@AnnotationUpdateSQL4FutureBean(sql="update haha_new_user_stat set MonthActiveRate = [?] where Time = [?]",et=EnumBeanFieldType.Double, name = "MonthActiveRate")
	public void setMonthActiveRate(Double monthActiveRate) {
		MonthActiveRate = monthActiveRate;
	}
	
	
	
	
}

	

//select  * from user_action20140101 as a group by a.uid   ��ϵͳ�����ѡ��ͬһ��uid�Ķ�����¼�е�һ��
//select  a.uid, sum(a.actionnum) from user_action20140101 as a group by a.uid ���ͳ��ÿ���û��Ĳ�������
//select * from (select  a.uid, sum(a.actionnum) as count from user_action20140101 as a group by a.uid) b where b.count>3 ���ѡ���count����������
//select count(b.uid) from (select  a.uid, sum(a.actionnum) as count from user_action20140101 as a group by a.uid) b where b.count>2 ���ͳ�Ƴ���������������û���

	
