package com.entity.statistics;

/**
 * �������뷨
 * ����ͳ�Ʊ�
 * @author ZhuYa
 *
 */
public class Online_collect_gswb {
	private String qid ;
	private String advertIdentifier ;  //��ȡQID��6λ֮�������
	private int onlineNum ;			   //����ͳ����
	private int advertDate ;		   //��ȡQID6λ������
	private int dateLine ;			   //�����ݴ���ʱ��
	private String softVer ;		   //�汾
	private int serviceStartnum ;      //����������
	
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
