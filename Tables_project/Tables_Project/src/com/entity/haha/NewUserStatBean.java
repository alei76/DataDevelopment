package com.entity.haha;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.dao.hahaCal.EnumBeanFieldType;

/**各个field分开用sql实现，而没有将几个sql合在一起实现，是因为有些不能合在一起*/
/*
 * 
 * CREATE TABLE `HahaCalenderNewUserStat` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Time` int(10) unsigned NOT NULL COMMENT '时间',
  `InstallCount` int(10) NOT NULL COMMENT '当日新用户安装量',
  `UninstallCount` int(10) NOT NULL COMMENT '当日新用户卸载量',
  `LaunchCount` int(32) NOT NULL COMMENT '当日新用户服务启动量',
  `ActiveCount` int(32) NOT NULL COMMENT '当日新用户活跃量',
  `NextDayActiveCount` int(32) NOT NULL COMMENT '次日用户活跃量',
 `NextDayActiveRate` decimal(6,2) unsigned DEFAULT NULL COMMENT '次日活跃率',
 `WeekActiveCount` int(32) NOT NULL COMMENT '7日活跃量',
 `WeekActiveRate` decimal(6,2) unsigned DEFAULT NULL COMMENT '7日活跃率',
 `MonthActiveCount` int(32) NOT NULL COMMENT '30日活跃量',
 `MonthActiveRate` decimal(6,2) unsigned DEFAULT NULL COMMENT '30日活跃率',
`newVCU` int(10) unsigned DEFAULT 0 COMMENT '新VCU用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='haha日历新用户统计表'
 * 
 * */
@AnnotationSQLCommon4Bean(sql1="select count(distinct b.uid)from install[?] as a inner join user_action[?] as b on a.UID=b.uid",et=EnumBeanFieldType.STRING)

public class NewUserStatBean implements IMysqlBean

{
	
	
	private String insert_sql= "insert into haha_new_user_stat(Time,newVCU,InstallCount,UninstallCount,LaunchCount,ActiveCount,NextDayActiveCount,NextDayActiveRate,WeekActiveCount,WeekActiveRate,MonthActiveCount,MonthActiveRate)" +
			" values(?,?,?,?,?,?,?,?,?,?,?,?)";

	private Integer id;  //bean的编号
	private String time="";  //时间
	private Integer InstallCount; //当日新用户安装量：去重
	private Integer UninstallCount; //当日新用户卸载量：去重
	private Integer LaunchCount; // 当日新用户服务启动量
	private Integer ActiveCount; //当日新用户活跃量
	private Integer NextDayActiveCount=0; //次日活跃量
	private Double NextDayActiveRate=0.0; //次日活跃率
	private Integer WeekActiveCount=0;//7日活跃量
	private Double WeekActiveRate=0.0; //次日活跃率
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

	

//select  * from user_action20140101 as a group by a.uid   则系统会随机选择同一个uid的多条记录中的一个
//select  a.uid, sum(a.actionnum) from user_action20140101 as a group by a.uid 则会统计每条用户的操作总数
//select * from (select  a.uid, sum(a.actionnum) as count from user_action20140101 as a group by a.uid) b where b.count>3 则会选择出count数高于三的
//select count(b.uid) from (select  a.uid, sum(a.actionnum) as count from user_action20140101 as a group by a.uid) b where b.count>2 则会统计出满足这个条件的用户数

	
