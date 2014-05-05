package com.entity.credit;

import com.dao.hahaCal.EnumBeanFieldType;
import com.entity.haha.AnnotationGenSQL4Bean;

public class CreditEditionStatBean{
	
	
	
	
	public String sql=""
			+ "select [?] as Time,count(distinct a.uid) as InstallCount, "
			+ "b.UninstallCount as UninstallCount,c.LaunchCount as LaunchCount,"
			+ " d.ActiveCount as ActiveCount,0 as ActiveRate,0 as UninstallRate,"
			+ "e.vcuNum a"
			+ "s VCU_COUNT,a.SoftVer as Edition from install[?] as a "
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	private String Edition;//�汾��
	private int InstallCount;//��ͬ�汾��ÿ���û���װ����ÿ�찲װ�˸ð汾��׬��ҵ��û�������
	private int ActiveCount;//��ͬ�汾��ÿ���û�������׬��Ҵ���1�ε��û�����
	private int useCount;//��ͬ�汾��ÿ���û���APP����������һ�������Ӧ�õ��û���
	
	
	public String getEdition(){
		return this.Edition;
	}
	@AnnotationGenSQL4Bean(sql1="select [?]",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setEdition(String Edition){
		this.Edition=Edition;
	}
	public int getInstallCount(){
		return this.InstallCount;
	}
	@AnnotationGenSQL4Bean(sql1="select [?]",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setInstallCount(int InstallCount){
		this.InstallCount=InstallCount;
	}
	public int getActiveCount(){
		return this.ActiveCount;
	}
	@AnnotationGenSQL4Bean(sql1="select [?]",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setActiveCount(int ActiveCount){
		this.ActiveCount=ActiveCount;
	}
	public int getUseCount(){
		return this.useCount;
	}
	@AnnotationGenSQL4Bean(sql1="select [?]",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setUseCount(int useCount){
		this.useCount=useCount;
	}
}