package com.entity.union;

/**
 * 装机联盟总表
 * @author ZhuYa
 *
 */
public class Promotion_All {
	
	private int dateline;					//时间
	private int activeNum;					//活跃用户总数
	private int spreadNum;					//有业绩用户数
	private int spreadJiFenNum;				//用户推广积分总量
	private int consumeNum;					//用户消耗积分总量
	private int loginNum;					//当日注册用户数
	private int landedNum;					//当日登录用户数
	private int downloadNum;				//当日下载用户数
	private int lostNum;					//当日流失用户数
	private int regInsActiveNum;			//当日活跃用户数
	private double activepercentage;		//当日活跃率
	private int nextRegInsActive;			//次日活跃用户数
	private int nextActivePercentage;		//次日活跃率
	private int next7DayRegInsActive;		//7日活跃用户
	private int next7DayPercentage;			//7日活跃率
	private int next30DayRegInsActive;		//30日活跃用户数
	private int next30DayPercentage;		//30日活跃率
	private int faFangNum	;				//当日积分发放用户数
	private int faFangIntegrationNum;		//当日发放积分总量
	private int installationTodayNum;		//当日推广用户数
	private int integrationNum;				//当日推广积分量
	private int regNum;						//当日签到用户总数
	private int regIntegrationNum;			//当日签到积分量
	private int useNum;						//当日积分使用用户数
	private int useIntegrationNum;			//当日积分消耗总量
	private int duihuanNum;					//当日兑换话费用户数
	private int dhIntegrationNum;			//当日兑换话费积分量
	private int tixianNum;					//当日提现用户数
	private int txIntegrationNum;			//当日提现积分量
	private int intAllIntegrationNum;		//积分发放总量
	private int regAllIntegrationNum;		//签到积分量
	private int insAllIntegrationNum;		//推广积分量
	private int ateAllIntegrationNum;		//活动积分总量
	
	
	public int getAteAllIntegrationNum() {
		return ateAllIntegrationNum;
	}
	public void setAteAllIntegrationNum(int ateAllIntegrationNum) {
		this.ateAllIntegrationNum = ateAllIntegrationNum;
	}
	public int getDateline() {
		return dateline;
	}
	public void setDateline(int dateline) {
		this.dateline = dateline;
	}
	public int getActiveNum() {
		return activeNum;
	}
	public void setActiveNum(int activeNum) {
		this.activeNum = activeNum;
	}
	public int getSpreadNum() {
		return spreadNum;
	}
	public void setSpreadNum(int spreadNum) {
		this.spreadNum = spreadNum;
	}
	public int getSpreadJiFenNum() {
		return spreadJiFenNum;
	}
	public void setSpreadJiFenNum(int spreadJiFenNum) {
		this.spreadJiFenNum = spreadJiFenNum;
	}
	public int getConsumeNum() {
		return consumeNum;
	}
	public void setConsumeNum(int consumeNum) {
		this.consumeNum = consumeNum;
	}
	public int getLoginNum() {
		return loginNum;
	}
	public void setLoginNum(int loginNum) {
		this.loginNum = loginNum;
	}
	public int getLandedNum() {
		return landedNum;
	}
	public void setLandedNum(int landedNum) {
		this.landedNum = landedNum;
	}
	public int getDownloadNum() {
		return downloadNum;
	}
	public void setDownloadNum(int downloadNum) {
		this.downloadNum = downloadNum;
	}
	public int getLostNum() {
		return lostNum;
	}
	public void setLostNum(int lostNum) {
		this.lostNum = lostNum;
	}
	
	public int getRegInsActiveNum() {
		return regInsActiveNum;
	}
	public double getActivepercentage() {
		return activepercentage;
	}
	public void setActivepercentage(double activepercentage) {
		this.activepercentage = activepercentage;
	}
	public void setRegInsActiveNum(int regInsActiveNum) {
		this.regInsActiveNum = regInsActiveNum;
	}
	public int getNextRegInsActive() {
		return nextRegInsActive;
	}
	public void setNextRegInsActive(int nextRegInsActive) {
		this.nextRegInsActive = nextRegInsActive;
	}
	public int getNextActivePercentage() {
		return nextActivePercentage;
	}
	public void setNextActivePercentage(int nextActivePercentage) {
		this.nextActivePercentage = nextActivePercentage;
	}
	public int getNext7DayRegInsActive() {
		return next7DayRegInsActive;
	}
	public void setNext7DayRegInsActive(int next7DayRegInsActive) {
		this.next7DayRegInsActive = next7DayRegInsActive;
	}
	public int getNext7DayPercentage() {
		return next7DayPercentage;
	}
	public void setNext7DayPercentage(int next7DayPercentage) {
		this.next7DayPercentage = next7DayPercentage;
	}
	public int getNext30DayRegInsActive() {
		return next30DayRegInsActive;
	}
	public void setNext30DayRegInsActive(int next30DayRegInsActive) {
		this.next30DayRegInsActive = next30DayRegInsActive;
	}
	public int getNext30DayPercentage() {
		return next30DayPercentage;
	}
	public void setNext30DayPercentage(int next30DayPercentage) {
		this.next30DayPercentage = next30DayPercentage;
	}
	public int getFaFangNum() {
		return faFangNum;
	}
	public void setFaFangNum(int faFangNum) {
		this.faFangNum = faFangNum;
	}
	public int getFaFangIntegrationNum() {
		return faFangIntegrationNum;
	}
	public void setFaFangIntegrationNum(int faFangIntegrationNum) {
		this.faFangIntegrationNum = faFangIntegrationNum;
	}
	public int getInstallationTodayNum() {
		return installationTodayNum;
	}
	public void setInstallationTodayNum(int installationTodayNum) {
		this.installationTodayNum = installationTodayNum;
	}
	public int getIntegrationNum() {
		return integrationNum;
	}
	public void setIntegrationNum(int integrationNum) {
		this.integrationNum = integrationNum;
	}
	public int getRegNum() {
		return regNum;
	}
	public void setRegNum(int regNum) {
		this.regNum = regNum;
	}
	public int getRegIntegrationNum() {
		return regIntegrationNum;
	}
	public void setRegIntegrationNum(int regIntegrationNum) {
		this.regIntegrationNum = regIntegrationNum;
	}
	public int getUseNum() {
		return useNum;
	}
	public void setUseNum(int useNum) {
		this.useNum = useNum;
	}
	public int getUseIntegrationNum() {
		return useIntegrationNum;
	}
	public void setUseIntegrationNum(int useIntegrationNum) {
		this.useIntegrationNum = useIntegrationNum;
	}
	public int getDuihuanNum() {
		return duihuanNum;
	}
	public void setDuihuanNum(int duihuanNum) {
		this.duihuanNum = duihuanNum;
	}
	public int getDhIntegrationNum() {
		return dhIntegrationNum;
	}
	public void setDhIntegrationNum(int dhIntegrationNum) {
		this.dhIntegrationNum = dhIntegrationNum;
	}
	public int getTixianNum() {
		return tixianNum;
	}
	public void setTixianNum(int tixianNum) {
		this.tixianNum = tixianNum;
	}
	public int getTxIntegrationNum() {
		return txIntegrationNum;
	}
	public void setTxIntegrationNum(int txIntegrationNum) {
		this.txIntegrationNum = txIntegrationNum;
	}
	public int getIntAllIntegrationNum() {
		return intAllIntegrationNum;
	}
	public void setIntAllIntegrationNum(int intAllIntegrationNum) {
		this.intAllIntegrationNum = intAllIntegrationNum;
	}
	public int getRegAllIntegrationNum() {
		return regAllIntegrationNum;
	}
	public void setRegAllIntegrationNum(int regAllIntegrationNum) {
		this.regAllIntegrationNum = regAllIntegrationNum;
	}
	public int getInsAllIntegrationNum() {
		return insAllIntegrationNum;
	}
	public void setInsAllIntegrationNum(int insAllIntegrationNum) {
		this.insAllIntegrationNum = insAllIntegrationNum;
	}
}
