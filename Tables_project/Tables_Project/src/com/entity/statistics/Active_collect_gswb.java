package com.entity.statistics;
/**
 * ͳ�ƿ�
 * ��Ծ��ͳ��
 * @author ZhuYa
 *
 */
public class Active_collect_gswb {
	private String qid;					//�û���ʶ��
	private int recodID;
	private String advertIdentifier;	//QID��6λ��ȡʣ���
	private int activeNum;				//�����û��ܻ�Ծ��
	private int newUserActiveNum;		//���û���Ծ��
	private int inputLV1Num;			//�������С�ڵ���10
	private int inputLV2Num;			//�������С�ڵ���140
	private int inputLV3Num;			//�����������140
	private int advertDate;				//��ȡ��6λ
	private int dateLine;				//��ѯ������
	private String softVer;				//�汾
	
	
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
