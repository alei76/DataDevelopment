package com.entity.statistics;
/**
 * 统计库
 * 活跃表统计
 * @author ZhuYa
 *
 */
public class Active_collect_gswb {
	private String qid;					//用户标识符
	private int recodID;
	private String advertIdentifier;	//QID后6位截取剩余的
	private int activeNum;				//所有用户总活跃量
	private int newUserActiveNum;		//新用户活跃量
	private int inputLV1Num;			//输入次数小于等于10
	private int inputLV2Num;			//输入次数小于等于140
	private int inputLV3Num;			//输入次数大于140
	private int advertDate;				//截取的6位
	private int dateLine;				//查询的日期
	private String softVer;				//版本
	
	
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
	public int getActiveNum() {
		return activeNum;
	}
	public void setActiveNum(int activeNum) {
		this.activeNum = activeNum;
	}
	public int getNewUserActiveNum() {
		return newUserActiveNum;
	}
	public void setNewUserActiveNum(int newUserActiveNum) {
		this.newUserActiveNum = newUserActiveNum;
	}
	public int getInputLV1Num() {
		return inputLV1Num;
	}
	public void setInputLV1Num(int inputLV1Num) {
		this.inputLV1Num = inputLV1Num;
	}
	public int getInputLV2Num() {
		return inputLV2Num;
	}
	public void setInputLV2Num(int inputLV2Num) {
		this.inputLV2Num = inputLV2Num;
	}
	public int getInputLV3Num() {
		return inputLV3Num;
	}
	public void setInputLV3Num(int inputLV3Num) {
		this.inputLV3Num = inputLV3Num;
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
