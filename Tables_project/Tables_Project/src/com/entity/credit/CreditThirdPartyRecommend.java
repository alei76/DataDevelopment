package com.entity.credit;

public class CreditThirdPartyRecommend {

	private int softwareName;//软件名称
	private int downloadCount;//下载量
	private int launchcount;//安装后主动打开次数（把主动去掉）：第三方软件安装后客户端主动帮用户打开的情况
	private int totalLaunchCount;//不去重，一共打开了多少次
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
