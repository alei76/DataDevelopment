package com.entity.credit;

public class CreditThirdPartyRecommend {

	private int softwareName;//�������
	private int downloadCount;//������
	private int launchcount;//��װ�������򿪴�����������ȥ�����������������װ��ͻ����������û��򿪵����
	private int totalLaunchCount;//��ȥ�أ�һ�����˶��ٴ�
	public int getSoftwareName(){
		return this.softwareName;
	}
	public void setSoftwareName(int softwareName){
		this.softwareName=softwareName;
	}
	public int getDownloadCount(){
		return this.downloadCount;
	}
	public void setDownloadCount(int downloadCount){
		this.downloadCount=downloadCount;
	}
	public int getLaunchcount(){
		return this.launchcount;
	}
	public void setLaunchcount(int launchcount){
		this.launchcount=launchcount;
	}
	public int getTotalLaunchCount(){
		return this.totalLaunchCount;
	}
	public void setTotalLaunchCount(int totalLaunchCount){
		this.totalLaunchCount=totalLaunchCount;
	}


}
