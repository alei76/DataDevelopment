package com.entity.union;

/**
 * װ������ ����ʹ����ϸ
 * @author ZhuYa
 *
 */
public class Promotion_Online {
	private int id ;							//id
	private int uid ;							//QID(����)
	private String software_id;		//�����ʶ
	private String software_version;//����汾
	private int num;							//����
	private int dateline;					//ʱ��
	private int state;							//״̬:0�ȴ����� 1�Ѿ�����
	private String pc_id;					//ʹ�õĵ���Ψһ��ʶ
	private int day;							//����
	
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getSoftware_id() {
		return software_id;
	}
	public void setSoftware_id(String software_id) {
		this.software_id = software_id;
	}
	public String getSoftware_version() {
		return software_version;
	}
	public void setSoftware_version(String software_version) {
		this.software_version = software_version;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getDateline() {
		return dateline;
	}
	public void setDateline(int dateline) {
		this.dateline = dateline;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getPc_id() {
		return pc_id;
	}
	public void setPc_id(String pc_id) {
		this.pc_id = pc_id;
	}
}
