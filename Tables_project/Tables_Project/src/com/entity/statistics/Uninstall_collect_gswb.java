package com.entity.statistics;

/**
 * 光速输入法
 * 卸载统计表
 * @author ZhuYa
 *
 */
public class Uninstall_collect_gswb {
	private String advertIdentifier ;   //QID截取后6位之外的字符
	private int uninstNum ;				//卸载量
	private int advertDate ;			//QID截取后6位的字符
	private int dateLine ;				//查询时间
	private String softVer ;			//版本
	
	
	public String getAdvertIdentifier() {
		return advertIdentifier;
	}
	public void setAdvertIdentifier(String advertIdentifier) {
		this.advertIdentifier = advertIdentifier;
	}
	public int getUninstNum() {
		return uninstNum;
	}
	public void setUninstNum(int uninstNum) {
		this.uninstNum = uninstNum;
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
