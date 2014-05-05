package com.entity.credit;

import com.sunxd.common.EnumBeanFieldType;

import com.sunxd.common.entity.AnnotationGenSQL4Bean;

public class CreditThirdPartyRecommend {

	private int date;
	private int softwareName;//软件名称
	private int downloadCount;//下载量
	private int autolaunchcount;//安装后赚金币帮用户打开的次数：第三方软件安装后客户端主动帮用户打开的情况
	private int intentLaunchCount;//用户主动打开的次数
	//1安装 2第三方第应用打开（用户主动打开） 3第三方应用安装（自动打开） 4第三方应用下载',
	
	
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	
	
	public int getSoftwareName() {
		return softwareName;
	}
	public void setSoftwareName(int softwareName) {
		this.softwareName = softwareName;
	}
	public int getDownloadCount() {
		return downloadCount;
	}
	//产生多行数据 
	@AnnotationGenSQL4Bean(sql1="select * from member_app_data where usetype=4 group by app_name",et=EnumBeanFieldType.STRING, TbColumnName = "Time")
	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}
	public int getAutolaunchcount() {
		return autolaunchcount;
	}
	@AnnotationGenSQL4Bean(sql1="select * from member_app_data where usetype=3 group by app_name",et=EnumBeanFieldType.STRING, TbColumnName = "Time")
	public void setAutolaunchcount(int autolaunchcount) {
		this.autolaunchcount = autolaunchcount;
	}
	public int getIntentLaunchCount() {
		return intentLaunchCount;
	}
	@AnnotationGenSQL4Bean(sql1="select * from member_app_data where usetype=2 group by app_name",et=EnumBeanFieldType.STRING, TbColumnName = "Time")
	public void setIntentLaunchCount(int intentLaunchCount) {
		this.intentLaunchCount = intentLaunchCount;
	}

	
	
	

}
