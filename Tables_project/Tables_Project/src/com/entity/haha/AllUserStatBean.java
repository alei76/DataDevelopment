package com.entity.haha;



import com.dao.hahaCal.EnumBeanFieldType;
import com.sunxd.common.entity.BeanFactory;

/**
*此类由MySQLToBean工具自动生成
*备注(数据表的comment字段)：haha日历总用户统计表
*@author childlikeman@gmail.com,http://t.qq.com/lostpig
*@since 2014-04-03 17:22:57
*/

/*
CREATE TABLE `haha_all_user_stat` (
		  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
		  `Time` int(10) unsigned NOT NULL COMMENT '时间',
		  `LaunchCount` int(15) NOT NULL COMMENT '当日服务启动总用户量',
		  `UninstallCount` int(10) NOT NULL COMMENT '当日用户卸载总量',
		  `ActiveCount` int(32) NOT NULL COMMENT '当日总活跃量',
		  `UninstallRate` decimal(6,2) unsigned DEFAULT NULL COMMENT '当日总卸载率',
		  `TotalVCU` int(32) NOT NULL COMMENT '总vcu总有效用户',
		  PRIMARY KEY (`id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='haha日历总用户统计表'*/




public class AllUserStatBean implements IMysqlBean
{	
	private int Time;        //时间
	private int LaunchCount;//当日服务启动总用户量
	private int UninstallCount;//当日用户卸载总量
	private int ActiveCount;//当日总活跃量
	private Double UninstallRate;////////?????????????????????????
	private int TotalVCU;//总vcu总有效用户
	
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
		String insert_sql= "insert into haha_all_user_stat(Time,LaunchCount,UninstallCount,ActiveCount,UninstallRate,TotalVCU)" +
				" values(?,?,?,?,?,?)";
		return insert_sql;
	}
	
	
	public int getLaunchCount()
	{
		return this.LaunchCount;
	}
	
	@AnnotationGenSQL4Bean(sql1="select count(distinct a.uid) from online[?] as a",et=EnumBeanFieldType.INT, TbColumnName = "LaunchCount") 
	// 已经验证，注意，这里的时间Time是日期，因此不需要考虑online表里的时间，online的表名字自然包含了时间
	public void setLaunchCount(int LaunchCount){
		this.LaunchCount=LaunchCount;
	}
	public int getUninstallCount(){
		return this.UninstallCount;
	}

	@AnnotationGenSQL4Bean(sql1="select count(distinct a.uid) from uninstall[?] as a",et=EnumBeanFieldType.INT, TbColumnName = "UninstallCount")
	// 已经验证
	public void setUninstallCount(int UninstallCount){
		this.UninstallCount=UninstallCount;
	}
	public int getActiveCount(){
		return this.ActiveCount;
	}
	
	@AnnotationGenSQL4Bean(sql1="select count(distinct a.uid) from user_action[?] as a ",et=EnumBeanFieldType.INT, TbColumnName = "ActiveCount")
	// 已经验证
	public void setActiveCount(int ActiveCount){
		this.ActiveCount=ActiveCount;
	}
	public Double getUninstallRate(){
		return this.UninstallRate;
	}
	
	@AnnotationGenSQL4Bean(sql1="select (select count(distinct a.uid) from uninstall[?] as a)/100",et=EnumBeanFieldType.Double, TbColumnName = "UninstallRate")
	// 需求有问题
	public void setUninstallRate(Double UninstallRate){
		this.UninstallRate=UninstallRate;
	}
	public int getTotalVCU(){
		return this.TotalVCU;
	}
	
	@AnnotationGenSQL4Bean(sql1="select count(b.uid) from (select  a.uid, sum(a.actionnum) as mycount from user_action[?] as a group by a.uid) b where b.mycount>2",et=EnumBeanFieldType.INT, TbColumnName = "TotalVCU")
	//？？？？？？这个和第一个表里的vcu有什么区别呢？
	//                           select count(b.uid) from (select  a.uid, sum(a.actionnum) as mycount from user_action[?] as a group by a.uid) b where b.mycount>2
	public void setTotalVCU(int TotalVCU){
		this.TotalVCU=TotalVCU;
	}

	

}