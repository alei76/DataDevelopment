package com.entity.union;

/**
 * װ������ ��װ��ϸ
 * @author ZhuYa
 *
 */
public class Promotion_Install {
	private int id ;				//id
	private int uid ;				//QID(����)
	private String software_id;		//�����ʶ
	private String software_version;//����汾
	private int num;				//����
	private int dateline;			//ʱ��
	private String pc_id;			//pc�ն�
	private String userId;			//�û�ID
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPc_id() {
		return pc_id;
	}
	public void setPc_id(String pc_id) {
		this.pc_id = pc_id;
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
	public void setSoftware_id(String softwareId) {
		software_id = softwareId;
	}
	public String getSoftware_version() {
		return software_version;
	}
	public void setSoftware_version(String softwareVersion) {
		software_version = softwareVersion;
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
}
