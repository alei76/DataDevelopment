package com.entity.credit;

import com.dao.hahaCal.EnumBeanFieldType;
import com.entity.haha.AnnotationGenSQL4Bean;

public class CreditTransactionStatBean {
	
	private int raffleCount;//当日抽奖次数
	private int raffleUsernum;//当日抽奖用户数
	private int issuecount;//当日积分发放量
	private int issueUsernum;//当日积分发放用户数
	///
	private int rechargePhoneUserCount;//当日积分兑换话费用户数
	private int rechargePhoneNum;//当日积分用来充话费的积分数
	private int rechargeAliUserCount;//当日积分兑换支付宝用户数
	private int rechargeAliNum;// 当日用户用来充支付宝的积分数
	///
	private int scheme1PointCount;//方案一积分总量
	
	//where a.inputtime>unix_timestamp('20140424') and a.inputtime<unix_timestamp('20140425')
	
	
	public int getRaffleCount(){
		return this.raffleCount;
	}
	@AnnotationGenSQL4Bean(sql1="select count(a.member_id) from lottery_data as a where a.type=1",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setRaffleCount(int raffleCount){
		this.raffleCount=raffleCount;
	}
	
	
	public int getRaffleUsernum() {
		return raffleUsernum;
	}
    @AnnotationGenSQL4Bean(sql1="select count(distinct a.member_id) from lottery_data as a where a.type=1 and",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setRaffleUsernum(int raffleUsernum) {
		this.raffleUsernum = raffleUsernum;
	}
	
	
	public int getIssuecount() {
		return issuecount;
	}

    
	@AnnotationGenSQL4Bean(sql1="select sum(a.point) from member_point_data as a where a.type=1 and a.way=1",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setIssuecount(int issuecount) {
		this.issuecount = issuecount;
	}
	
	public int getIssueUsernum() {
		return issueUsernum;
	}
	@AnnotationGenSQL4Bean(sql1="select count(distinct a.member_id) from member_point_data as a where a.type=1 and a.way=1",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setIssueUsernum(int issueUsernum) {
		this.issueUsernum = issueUsernum;
	}

	
	
	

	public int getRechargePhoneNum() {
		return rechargePhoneNum;
	}
	@AnnotationGenSQL4Bean(sql1="select sum(b.cost) from exchange_order as a inner join exchange_item as b on a.item_id=b.id where b.type=2",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setRechargePhoneNum(int rechargePhoneNum) {
		this.rechargePhoneNum = rechargePhoneNum;
	}
	
  	public int getRechargeAliNum() {
		return rechargeAliNum;
	}
	@AnnotationGenSQL4Bean(sql1="select sum(b.cost) from exchange_order as a inner join exchange_item as b on a.item_id=b.id where b.type=1",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setRechargeAliNum(int rechargeAliNum) {
		this.rechargeAliNum = rechargeAliNum;
	}

	
	
	
	public int getRechargePhoneUserCount() {
		return rechargePhoneUserCount;
	}

	
	@AnnotationGenSQL4Bean(sql1="select count(distinct a.mid) from exchange_order as a inner join exchange_item as b on a.item_id=b.id where b.type=2",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setRechargePhoneUserCount(int rechargePhoneUserCount) {
		this.rechargePhoneUserCount = rechargePhoneUserCount;
	}

	public int getRechargeAliUserCount() {
		return rechargeAliUserCount;
	}
	
	@AnnotationGenSQL4Bean(sql1="select count(distinct a.mid) from exchange_order as a inner join exchange_item as b on a.item_id=b.id where b.type=1",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setRechargeAliUserCount(int rechargeAliUserCount) {
		this.rechargeAliUserCount = rechargeAliUserCount;
	}

	
	

	

	public int getScheme1PointCount() {
		return scheme1PointCount;
	}

	
	@AnnotationGenSQL4Bean(sql1="select count(b.point) from  lottery_data as a inner join member_point_data as b on a.trade_id=b.trade_id where a.difftype=1",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setScheme1PointCount(int scheme1PointCount) {
		this.scheme1PointCount = scheme1PointCount;
	}
	

}
