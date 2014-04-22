package com.entity.statistics;

/**
 * 光速输入法
 * 在线统计表
 * @author ZhuYa
 *
 */
public class Online_collect_gswb {
	private String qid ;
	private String advertIdentifier ;  //截取QID后6位之外的数据
	private int onlineNum ;			   //在线统计量
	private int advertDate ;		   //截取QID6位的数据
	private int dateLine ;			   //表数据创建时间
	private String softVer ;		   //版本
	private int serviceStartnum ;      //服务启动量
	
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getAdvertIdentifier() {
		return advertIdentifier;
	}
	public void setAdvertIdentifier(String advertIdentifier) {
		this.advertIdentifier = advertIdentifier;
	}
	public int getOnlineNum() {
		return onlineNum;
	}
	public void setOnlineNum(int onlineNum) {
		this.onlineNum = onlineNum;
	}
	public int getAdvertDate() {
		return advertDate;
	}
	public void setAdvertDate(int advertDate) {
		this.advertDate = advertDate;
	}
	public int getDateLine() {
		return dateLine;
	}
	public void setDateLine(int dateLine) {
		this.dateLine = dateLine;
	}
	public String getSoftVer() {
		return softVer;
	}
	public void setSoftVer(String softVer) {
		this.softVer = softVer;
	}
	public int getServiceStartnum() {
		return serviceStartnum;
	}
	public void setServiceStartNum(int serviceStartNum) {
		this.serviceStartnum = serviceStartNum;
	}
}
