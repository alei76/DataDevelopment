package com.entity.union;

/**
 * 装机联盟 安装明细
 * @author ZhuYa
 *
 */
public class Promotion_Install {
	private int id ;				//id
	private int uid ;				//QID(渠道)
	private String software_id;		//软件标识
	private String software_version;//软件版本
	private int num;				//数量
	private int dateline;			//时间
	private String pc_id;			//pc终端
	private String userId;			//用户ID
	
	
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
