package com.entity.credit;


import com.sunxd.common.EnumBeanFieldType;
import com.sunxd.common.entity.AnnotationGenSQL4Bean;
import com.sunxd.common.entity.IMysqlBean;

public class CreditNewUserStatBean implements IMysqlBean
{

private String vendor;//渠道
private int InstallCount;//当日新用户安装量
private int downloadCount;//当日下载用户数
private int LaunchCount;//当日新用户服务启动量
private int ActiveCount;//当日新用户活跃量
private int NextDayActiveCount;//次日用户活跃量
private String NextDayActiveRate;
private int WeekActiveCount;//7日活跃量
private String WeekActiveRate;
private int MonthActiveCount;//30日活跃量
private String MonthActiveRate;


public String getVendor(){
	return this.vendor;
}

@AnnotationGenSQL4Bean(sql1="select a.qid,count(distinct id) from money.member as a where a.reg_date>unix_timestamp('20140423') and a.reg_date<unix_timestamp('20140429')  group by a.qid",et=EnumBeanFieldType.STRING, TbColumnName = "Time")
public void setVendor(String vendor){
	this.vendor=vendor;
}
public int getInstallCount(){
	return this.InstallCount;
}
/**每种渠道的当日新用户安装数*/
@AnnotationGenSQL4Bean(sql1="select count(distinct id) from money.member as a where a.reg_date>unix_timestamp('20140423') and a.reg_date<unix_timestamp('20140429')  group by a.qid",et=EnumBeanFieldType.STRING, TbColumnName = "Time")
public void setInstallCount(int InstallCount){
	this.InstallCount=InstallCount;
}
public int getDownloadCount(){
	return this.downloadCount;
}
/**每种渠道的新用户下载数::已经验证语法*/
@AnnotationGenSQL4Bean(sql1="select count(distinct a.member_id) from (money.member_app_data as a left join money.member as b on a.member_id=b.id) where a.usetype=4 and b.reg_date>unix_timestamp('20140423') and b.reg_date<unix_timestamp('20140424') group by b.qid",et=EnumBeanFieldType.STRING, TbColumnName = "Time")
public void setDownloadCount(int downloadCount){
	this.downloadCount=downloadCount;
}
public int getLaunchCount(){
	return this.LaunchCount;
}
/**每种渠道的新用户启动数：下载的usetype为2*/
@AnnotationGenSQL4Bean(sql1="select count(distinct a.member_id) from (money.member_app_data as a left join money.member as b on a.member_id=b.id) where a.usetype=2 and b.reg_date>unix_timestamp('20140423') and b.reg_date<unix_timestamp('20140424') group by b.qid",et=EnumBeanFieldType.STRING, TbColumnName = "Time")
public void setLaunchCount(int LaunchCount){
	this.LaunchCount=LaunchCount;
}
public int getActiveCount(){
	return this.ActiveCount;
}
/**每种渠道的当日活跃的新用户数*/
@AnnotationGenSQL4Bean(sql1="select count(distinct a.member_id) from (money.member_app_data as a left join money.member as b on a.member_id=b.id) where b.reg_date>unix_timestamp('20140423') and b.reg_date<unix_timestamp('20140424') group by b.qid",et=EnumBeanFieldType.STRING, TbColumnName = "Time")
public void setActiveCount(int ActiveCount){
	this.ActiveCount=ActiveCount;
}
public int getNextDayActiveCount(){
	return this.NextDayActiveCount;
}
////以下换用一种Annotation，因为要执行更新操作。

public void setNextDayActiveCount(int NextDayActiveCount){
	this.NextDayActiveCount=NextDayActiveCount;
}
public String getNextDayActiveRate(){
	return this.NextDayActiveRate;
}

public void setNextDayActiveRate(String NextDayActiveRate){
	this.NextDayActiveRate=NextDayActiveRate;
}
public int getWeekActiveCount(){
	return this.WeekActiveCount;
}

public void setWeekActiveCount(int WeekActiveCount){
	this.WeekActiveCount=WeekActiveCount;
}
public String getWeekActiveRate(){
	return this.WeekActiveRate;
}

public void setWeekActiveRate(String WeekActiveRate){
	this.WeekActiveRate=WeekActiveRate;
}
public int getMonthActiveCount(){
	return this.MonthActiveCount;
}

public void setMonthActiveCount(int MonthActiveCount){
	this.MonthActiveCount=MonthActiveCount;
}
public String getMonthActiveRate(){
	return this.MonthActiveRate;
}

public void setMonthActiveRate(String MonthActiveRate){
	this.MonthActiveRate=MonthActiveRate;
}

@Override
public String getSQL_insert() {
	// TODO Auto-generated method stub
	return null;
}

}