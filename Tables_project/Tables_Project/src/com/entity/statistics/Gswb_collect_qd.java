package com.entity.statistics;

/**
 * 渠道汇总统计 基础类
 * @author ZhuYa
 *
 */
public class Gswb_collect_qd {
	private String qdType;
	private Integer serviceStartNum;
	private Integer activeNum;
	private Integer onlyInstallNum;
	private Integer monthAddInstallNum;
	private Integer inputLV1Num;
	private Integer inputLV2Num;
	private Integer inputLV3Num;
	private Integer activeNum7Days;
	private Integer uninstNum;
	private Integer dateLine;
	private Integer installNum;
	private String qid;
	private String uid;					//用户标示符
	private Integer activeInputNum;		//安装用户在1,2,3度下的用户数
	private double activeInputPer;		//活跃率 
	
	/**
	 * 活跃率
	 * @return
	 */
	public double getActiveInputPer() {
		return activeInputPer;
	}
	public void setActiveInputPer(double activeInputPer) {
		this.activeInputPer = activeInputPer;
	}
	/**
	 * 安装用户在1,2,3度下的用户数
	 * @return
	 */
	public Integer getActiveInputNum() {
		return activeInputNum;
	}
	/**
	 * 安装用户在1,2,3度下的用户数
	 * @param activeInputNum
	 */
	public void setActiveInputNum(Integer activeInputNum) {
		this.activeInputNum = activeInputNum;
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
	public Integer getInstallNum() {
		return installNum;
	}
	public void setInstallNum(Integer installNum) {
		this.installNum = installNum;
	}
	public String getQdType() {
		return qdType;
	}
	public void setQdType(String qdType) {
		this.qdType = qdType;
	}
	public Integer getServiceStartNum() {
		return serviceStartNum;
	}
	public void setServiceStartNum(Integer serviceStartNum) {
		this.serviceStartNum = serviceStartNum;
	}
	public Integer getActiveNum() {
		return activeNum;
	}
	public void setActiveNum(Integer activeNum) {
		this.activeNum = activeNum;
	}
	public Integer getOnlyInstallNum() {
		return onlyInstallNum;
	}
	public void setOnlyInstallNum(Integer onlyInstallNum) {
		this.onlyInstallNum = onlyInstallNum;
	}
	public Integer getMonthAddInstallNum() {
		return monthAddInstallNum;
	}
	public void setMonthAddInstallNum(Integer monthAddInstallNum) {
		this.monthAddInstallNum = monthAddInstallNum;
	}
	public Integer getInputLV1Num() {
		return inputLV1Num;
	}
	public void setInputLV1Num(Integer inputLV1Num) {
		this.inputLV1Num = inputLV1Num;
	}
	public Integer getInputLV2Num() {
		return inputLV2Num;
	}
	public void setInputLV2Num(Integer inputLV2Num) {
		this.inputLV2Num = inputLV2Num;
	}
	public Integer getInputLV3Num() {
		return inputLV3Num;
	}
	public void setInputLV3Num(Integer inputLV3Num) {
		this.inputLV3Num = inputLV3Num;
	}
	public Integer getActiveNum7Days() {
		return activeNum7Days;
	}
	public void setActiveNum7Days(Integer activeNum7Days) {
		this.activeNum7Days = activeNum7Days;
	}
	public Integer getUninstNum() {
		return uninstNum;
	}
	public void setUninstNum(Integer uninstNum) {
		this.uninstNum = uninstNum;
	}
	public Integer getDateLine() {
		return dateLine;
	}
	public void setDateLine(Integer dateLine) {
		this.dateLine = dateLine;
	}
}
