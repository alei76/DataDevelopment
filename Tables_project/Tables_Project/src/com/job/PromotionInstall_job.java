package com.job;

import java.util.Date;

import com.action.union.PromotionAll_Action;
import com.action.union.PromotionInstall_Action;
import com.action.union.PromotionSoftware_Action;
import com.db.info.Dictionary_DB;

/**
 * 装机联盟  统计
 * 任务调度
 * @author ZhuYa
 *
 */
public class PromotionInstall_job {
	//param1(0:是当前日期,1:是手动输入)
	//param2(手动输入的时间)
	String tab_date = Dictionary_DB.getBeforeDate(0, null).toString();
	PromotionInstall_Action action = new PromotionInstall_Action();
	PromotionAll_Action actionAll = new PromotionAll_Action();
	PromotionSoftware_Action soft = new PromotionSoftware_Action();
	
	/**
	 * 明细统计 任务调度
	 * @param dateTime 输入时间
	 * @param ip01	: localhost = 43库
	 * @param user01 
	 * @param pwd01  
	 * @param ip02 : 127库
	 * @param user02 
	 * @param pwd02 
	 */
	public void PromotionInstall_work(String dateTime,String ip01,String user01,String pwd01,String ip02,String user02,String pwd02){
		int inputType = 0;	//0:是当前日期	1:手动输入
		//判断手动输入时间
		if(dateTime != null && dateTime.trim().length()!=0){
			tab_date = dateTime;
			inputType = 1;
		}
		System.out.println("明细统计开始 : "+ new Date());
		//插入装机联盟明细统计表
		action.insertPromotionInstall(ip01, user01, pwd01, Dictionary_DB.db_zj7654_log, ip02, user02, pwd02, Dictionary_DB.db_union, tab_date,inputType,Dictionary_DB.db_gswb_log,"install");
		System.out.println("明细统计结束 : "+ new Date());
	}
	
	/**
	 * 有效安装用户数
	 * @param dateTime
	 * @param ip01	:	43库
	 * @param user01
	 * @param pwd01
	 * @param ip02	:	127库
	 * @param user02
	 * @param pwd02
	 */
	public void PromotionOnline_work(String dateTime,String ip01,String user01,String pwd01,String ip02,String user02,String pwd02){
		int inputType = 0;
		//判断手动输入时间
		if(dateTime != null && dateTime.trim().length()!=0){
			tab_date = dateTime;
			inputType = 1;
		}
		System.out.println("有效安装用户统计开始 : "+new Date());
		//插入装机联盟在线使用统计表
		action.insertPromotionOnline(ip01, user01, pwd01, Dictionary_DB.db_statistics, ip02, user02, pwd02, Dictionary_DB.db_union, Dictionary_DB.db_gswb_log, String.valueOf(tab_date));
		System.out.println("有效安装用户统计结束 : "+new Date());
	}
	
	/**
	 * 旧需求 : 连续使用3天
	 * 在线使用统计  任务调度
	 * @param dateTime
	 */
	public void PromotionOnline_work_old(String dateTime,String ip01,String user01,String pwd01,String ip02,String user02,String pwd02){
		int inputType = 0;
		//判断手动输入时间
		if(dateTime != null && dateTime.trim().length()!=0){
			tab_date = dateTime;
			inputType = 1;
		}
		System.out.println("在线使用统计开始 : "+new Date());
		//插入装机联盟在线使用统计表
		action.insertPromotionOnlineOld(ip01, user01, pwd01, Dictionary_DB.db_zj7654_log, ip02, user02, pwd02, Dictionary_DB.db_union,inputType,Integer.parseInt(tab_date),"online",Dictionary_DB.db_gswb_log);
		System.out.println("在线使用统计结束 : "+new Date());
	}
	
	/**
	 * 装机联盟总表  任务调度
	 * @param dateTime
	 * @param ip	: 127库
	 * @param user
	 * @param pwd
	 * @param ip01	: 43库
	 * @param user01
	 * @param pwd01
	 */
	public void PromotionAll_work(String dateTime,String ip,String user,String pwd,String ip01,String user01,String pwd01){
		String tab_date_01 = Dictionary_DB.getTodayDate(1).toString();  //次日   -2
		String tab_date_07 = Dictionary_DB.getTodayDate(7).toString();  //第7日 -7
		String tab_date_30 = Dictionary_DB.getTodayDate(30).toString(); //第30日 -30
		
		//判断手动输入时间
		if(dateTime != null && dateTime.trim().length()!=0){
			tab_date = dateTime;
			tab_date_01 = Dictionary_DB.getTodayDate(tab_date.toString(),1).toString();  //次日   -2
			tab_date_07 = Dictionary_DB.getTodayDate(tab_date.toString(),7).toString();  //次日   -7
			tab_date_30 = Dictionary_DB.getTodayDate(tab_date.toString(),30).toString();  //次日   -30
		}
		
		System.out.println("装机联盟总表使用统计开始 : "+new Date());
		//插入装机联盟在线使用统计表
		actionAll.insertPromotionAll(ip, user, pwd, Dictionary_DB.db_union, tab_date, tab_date_01, tab_date_07, tab_date_30,ip01,user01,pwd01,Dictionary_DB.db_statistics);
		System.out.println("装机联盟总表使用统计结束 : "+new Date());
	}
	
	/**
	 * 返回装机联盟软件数据统计
	 * @param dateTime
	 * @param ip	: 127库
	 * @param user
	 * @param pwd
	 * @param ip01	:	43库
	 * @param user01
	 * @param pwd01
	 */
	public void PromotionSoft_work(String dateTime,String ip,String user,String pwd,String ip01,String user01,String pwd01){
		//判断手动输入时间
		if(dateTime != null && dateTime.trim().length()!=0){
			tab_date = dateTime;
		}
		
		System.out.println("装机联盟软件数据使用统计开始 : "+new Date());
		//插入装机联盟在线使用统计表
		soft.insertPromotionInstall(Dictionary_DB.db_zj7654_log,tab_date,ip01,user01,pwd01,Dictionary_DB.db_statistics,ip,user,pwd,Dictionary_DB.db_union);
		System.out.println("装机联盟软件数据使用统计结束 : "+new Date());
	}
}
