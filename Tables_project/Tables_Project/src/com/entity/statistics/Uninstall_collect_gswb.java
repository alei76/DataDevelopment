package com.entity.statistics;

/**
 * �������뷨
 * ж��ͳ�Ʊ�
 * @author ZhuYa
 *
 */
public class Uninstall_collect_gswb {
	private String advertIdentifier ;   //QID��ȡ��6λ֮����ַ�
	private int uninstNum ;				//ж����
	private int advertDate ;			//QID��ȡ��6λ���ַ�
	private int dateLine ;				//��ѯʱ��
	private String softVer ;			//�汾
	
	
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
