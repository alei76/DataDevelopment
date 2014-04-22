package com.entity.statistics;

/**
 * 统计库
 * 新用户信息统计
 * @author ZhuYa
 *
 */
public class New_user_data_gswb {
	private int dateLine ;				//数据时间 --- 跟清单表时间一致
	private int todayNewInstallNum ;	//当日新用户安装量
	private int todayNewUninstallNum ;  //当日新用户卸载量
	private int todayNewActiveNum ;		//当日新用户活跃量
	private int nextDayActiveNum ;		//次日活跃用户数
	private int activeNum7Day ;			//7日活跃量
	private int activeNum30Day ;		//30日活跃量
	private int uninstallNum30Day ;		//30日卸载量
	private String type		;			//活跃类型
	private int num ;					//用户数量
	
	
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
