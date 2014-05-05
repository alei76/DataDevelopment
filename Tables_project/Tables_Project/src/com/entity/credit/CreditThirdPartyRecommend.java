package com.entity.credit;

import com.sunxd.common.EnumBeanFieldType;

import com.sunxd.common.entity.AnnotationGenSQL4Bean;

public class CreditThirdPartyRecommend {

	private int date;
	private int softwareName;//�������
	private int downloadCount;//������
	private int autolaunchcount;//��װ��׬��Ұ��û��򿪵Ĵ����������������װ��ͻ����������û��򿪵����
	private int intentLaunchCount;//�û������򿪵Ĵ���
	//1��װ 2��������Ӧ�ô򿪣��û������򿪣� 3������Ӧ�ð�װ���Զ��򿪣� 4������Ӧ������',
	
	
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
	//������������ 
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
