package com.entity.statistics;

/**
 * ͳ�ƿ�
 * ��װͳ��
 * @author ZhuYa
 *
 */
public class Install_collect_gswb {
	private int recodID	;
	private String qid;
	private String advertIdentifier;
	private int allInstallNum;			//�ܵð�װ����
	private int onlyInstallNum;			//ȥ��֮��ð�װ��
	private int addOnlyPlayerNum;		//�����ӵ��û���
	private int cybercafeInstallNum;	//���ɰ�װ����
	private int monthOnlyInstallNum;	//�¶�ȥ�ذ�װ��
	private int monthAddInstallNum;		//�¶�Ψһ������
	private int advertDate;
	private int dateLine;
	private String softVer;
	private String uid;					//�û���ʾ��
	private int tab_date;				//��ʱ��
	
	
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
