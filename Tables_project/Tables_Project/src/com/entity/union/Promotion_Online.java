package com.entity.union;

/**
 * 装机联盟 在线使用明细
 * @author ZhuYa
 *
 */
public class Promotion_Online {
	private int id ;							//id
	private int uid ;							//QID(渠道)
	private String software_id;		//软件标识
	private String software_version;//软件版本
	private int num;							//数量
	private int dateline;					//时间
	private int state;							//状态:0等待结算 1已经结算
	private String pc_id;					//使用的电脑唯一标识
	private int day;							//天数
	
	
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
