package com.entity.credit;

public class CreditTransactionStatBean {
	
	private int raffleCount;//当日抽奖次数
	private int rafflenum;//当日抽奖用户数
	private int issuecount;//当日积分发放量
	private int issuenum;//当日积分发放用户数
	private int tradecount;//当日使用积分用户数
	private int tradenum;//当日使用积分用户数
	public int getRaffleCount(){
		return this.raffleCount;
	}
	public void setRaffleCount(int raffleCount){
		this.raffleCount=raffleCount;
	}
	public int getRafflenum(){
		return this.rafflenum;
	}
	public void setRafflenum(int rafflenum){
		this.rafflenum=rafflenum;
	}
	public int getIssuecount(){
		return this.issuecount;
	}
	public void setIssuecount(int issuecount){
		this.issuecount=issuecount;
	}
	public int getIssuenum(){
		return this.issuenum;
	}
	public void setIssuenum(int issuenum){
		this.issuenum=issuenum;
	}
	public int getTradecount(){
		return this.tradecount;
	}
	public void setTradecount(int tradecount){
		this.tradecount=tradecount;
	}
	public int getTradenum(){
		return this.tradenum;
	}
	public void setTradenum(int tradenum){
		this.tradenum=tradenum;
	}



}
