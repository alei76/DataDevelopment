package com.entity.statistics;

/**
 * ͳ�ƿ�
 * ���û���Ϣͳ��
 * @author ZhuYa
 *
 */
public class New_user_data_gswb {
	private int dateLine ;				//����ʱ�� --- ���嵥��ʱ��һ��
	private int todayNewInstallNum ;	//�������û���װ��
	private int todayNewUninstallNum ;  //�������û�ж����
	private int todayNewActiveNum ;		//�������û���Ծ��
	private int nextDayActiveNum ;		//���ջ�Ծ�û���
	private int activeNum7Day ;			//7�ջ�Ծ��
	private int activeNum30Day ;		//30�ջ�Ծ��
	private int uninstallNum30Day ;		//30��ж����
	private String type		;			//��Ծ����
	private int num ;					//�û�����
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getDateLine() {
		return dateLine;
	}
	public void setDateLine(int dateLine) {
		this.dateLine = dateLine;
	}
	public int getTodayNewInstallNum() {
		return todayNewInstallNum;
	}
	public void setTodayNewInstallNum(int todayNewInstallNum) {
		this.todayNewInstallNum = todayNewInstallNum;
	}
	public int getTodayNewUninstallNum() {
		return todayNewUninstallNum;
	}
	public void setTodayNewUninstallNum(int todayNewUninstallNum) {
		this.todayNewUninstallNum = todayNewUninstallNum;
	}
	public int getTodayNewActiveNum() {
		return todayNewActiveNum;
	}
	public void setTodayNewActiveNum(int todayNewActiveNum) {
		this.todayNewActiveNum = todayNewActiveNum;
	}
	public int getNextDayActiveNum() {
		return nextDayActiveNum;
	}
	public void setNextDayActiveNum(int nextDayActiveNum) {
		this.nextDayActiveNum = nextDayActiveNum;
	}
	public int getActiveNum7Day() {
		return activeNum7Day;
	}
	public void setActiveNum7Day(int activeNum7Day) {
		this.activeNum7Day = activeNum7Day;
	}
	public int getActiveNum30Day() {
		return activeNum30Day;
	}
	public void setActiveNum30Day(int activeNum30Day) {
		this.activeNum30Day = activeNum30Day;
	}
	public int getUninstallNum30Day() {
		return uninstallNum30Day;
	}
	public void setUninstallNum30Day(int uninstallNum30Day) {
		this.uninstallNum30Day = uninstallNum30Day;
	}
}
