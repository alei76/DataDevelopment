package com.entity.statistics;

/**
 * 统计库
 * 安装统计
 * @author ZhuYa
 *
 */
public class Install_collect_gswb {
	private int recodID	;
	private String qid;
	private String advertIdentifier;
	private int allInstallNum;			//总得安装数量
	private int onlyInstallNum;			//去重之后得安装量
	private int addOnlyPlayerNum;		//新增加得用户量
	private int cybercafeInstallNum;	//网吧安装数量
	private int monthOnlyInstallNum;	//月度去重安装量
	private int monthAddInstallNum;		//月度唯一增加量
	private int advertDate;
	private int dateLine;
	private String softVer;
	private String uid;					//用户标示符
	private int tab_date;				//表时间
	
	
	public int getTab_date() {
		return tab_date;
	}
	public void setTab_date(int tabDate) {
		tab_date = tabDate;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public int getRecodID() {
		return recodID;
	}
	public void setRecodID(int recodID) {
		this.recodID = recodID;
	}
	public String getAdvertIdentifier() {
		return advertIdentifier;
	}
	public void setAdvertIdentifier(String advertIdentifier) {
		this.advertIdentifier = advertIdentifier;
	}
	public int getAllInstallNum() {
		return allInstallNum;
	}
	public void setAllInstallNum(int allInstallNum) {
		this.allInstallNum = allInstallNum;
	}
	public int getOnlyInstallNum() {
		return onlyInstallNum;
	}
	public void setOnlyInstallNum(int onlyInstallNum) {
		this.onlyInstallNum = onlyInstallNum;
	}
	public int getAddOnlyPlayerNum() {
		return addOnlyPlayerNum;
	}
	public void setAddOnlyPlayerNum(int addOnlyPlayerNum) {
		this.addOnlyPlayerNum = addOnlyPlayerNum;
	}
	public int getCybercafeInstallNum() {
		return cybercafeInstallNum;
	}
	public void setCybercafeInstallNum(int cybercafeInstallNum) {
		this.cybercafeInstallNum = cybercafeInstallNum;
	}
	public int getMonthOnlyInstallNum() {
		return monthOnlyInstallNum;
	}
	public void setMonthOnlyInstallNum(int monthOnlyInstallNum) {
		this.monthOnlyInstallNum = monthOnlyInstallNum;
	}
	public int getMonthAddInstallNum() {
		return monthAddInstallNum;
	}
	public void setMonthAddInstallNum(int monthAddInstallNum) {
		this.monthAddInstallNum = monthAddInstallNum;
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
}
