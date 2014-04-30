package com.entity.credit;


import com.sunxd.common.EnumBeanFieldType;
import com.sunxd.common.entity.AnnotationGenSQL4Bean;
import com.sunxd.common.entity.IMysqlBean;

public class CreditNewUserStatBean implements IMysqlBean
{

private String vendor;//����
private int InstallCount;//�������û���װ��
private int downloadCount;//���������û���
private int LaunchCount;//�������û�����������
private int ActiveCount;//�������û���Ծ��
private int NextDayActiveCount;//�����û���Ծ��
private String NextDayActiveRate;
private int WeekActiveCount;//7�ջ�Ծ��
private String WeekActiveRate;
private int MonthActiveCount;//30�ջ�Ծ��
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
/**ÿ�������ĵ������û���װ��*/
@AnnotationGenSQL4Bean(sql1="select count(distinct id) from money.member as a where a.reg_date>unix_timestamp('20140423') and a.reg_date<unix_timestamp('20140429')  group by a.qid",et=EnumBeanFieldType.STRING, TbColumnName = "Time")
public void setInstallCount(int InstallCount){
	this.InstallCount=InstallCount;
}
public int getDownloadCount(){
	return this.downloadCount;
}
/**ÿ�����������û�������::�Ѿ���֤�﷨*/
@AnnotationGenSQL4Bean(sql1="select count(distinct a.member_id) from (money.member_app_data as a left join money.member as b on a.member_id=b.id) where a.usetype=4 and b.reg_date>unix_timestamp('20140423') and b.reg_date<unix_timestamp('20140424') group by b.qid",et=EnumBeanFieldType.STRING, TbColumnName = "Time")
public void setDownloadCount(int downloadCount){
	this.downloadCount=downloadCount;
}
public int getLaunchCount(){
	return this.LaunchCount;
}
/**ÿ�����������û������������ص�usetypeΪ2*/
@AnnotationGenSQL4Bean(sql1="select count(distinct a.member_id) from (money.member_app_data as a left join money.member as b on a.member_id=b.id) where a.usetype=2 and b.reg_date>unix_timestamp('20140423') and b.reg_date<unix_timestamp('20140424') group by b.qid",et=EnumBeanFieldType.STRING, TbColumnName = "Time")
public void setLaunchCount(int LaunchCount){
	this.LaunchCount=LaunchCount;
}
public int getActiveCount(){
	return this.ActiveCount;
}
/**ÿ�������ĵ��ջ�Ծ�����û���*/
@AnnotationGenSQL4Bean(sql1="select count(distinct a.member_id) from (money.member_app_data as a left join money.member as b on a.member_id=b.id) where b.reg_date>unix_timestamp('20140423') and b.reg_date<unix_timestamp('20140424') group by b.qid",et=EnumBeanFieldType.STRING, TbColumnName = "Time")
public void setActiveCount(int ActiveCount){
	this.ActiveCount=ActiveCount;
}
public int getNextDayActiveCount(){
	return this.NextDayActiveCount;
}
////���»���һ��Annotation����ΪҪִ�и��²�����

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