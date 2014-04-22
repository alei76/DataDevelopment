package com.job;

import java.util.Date;

import com.action.gswb_qd.Gswb_collect_qd_action;
import com.db.info.Dictionary_DB;

/**
 * 渠道汇总统计
 * 任务调度类
 * @author ZhuYa
 *
 */
public class Gswb_qd_job {
	//param1(0:是当前日期,1:是手动输入)
	//param2(手动输入的时间)
	Integer tab_date = Dictionary_DB.getBeforeDate(0, null);
	Gswb_collect_qd_action action = new Gswb_collect_qd_action(); 
	
	public void gswb_qd_work(String dateTime,String ip01,String user01,String pwd01){
		String tab_date_07 = Dictionary_DB.getTodayDate(7).toString();  //第7日 -7
		//判断手动输入时间
		if(dateTime != null && dateTime.trim().length()!=0){
			tab_date = Integer.parseInt(dateTime);
			tab_date_07 = Dictionary_DB.getTodayDate(tab_date.toString(),7).toString();  //第7日 -7
		}
		System.out.println("大渠道汇总开始 : "+ new Date());
		//插入渠道汇总统计
		action.insertGswb_Collect_Qd(ip01, user01, pwd01, Dictionary_DB.db_statistics,Dictionary_DB.db_gswb_log, tab_date,tab_date_07);
		System.out.println("大渠道汇总结束 : "+ new Date());
		System.out.println();
		
		System.out.println("二级渠道开始 : "+ new Date());
		//插入二级渠道汇总统计
		action.insertGswb_Collect_Qd_02(ip01, user01, pwd01, Dictionary_DB.db_statistics,Dictionary_DB.db_gswb_log, tab_date,tab_date_07);
		System.out.println("二级渠道结束 : "+ new Date());
		
		System.out.println("三级渠道开始 : "+ new Date());
		//插入三级渠道汇总统计
		action.insertGswb_Collect_Qd_03(ip01, user01, pwd01, Dictionary_DB.db_statistics,Dictionary_DB.db_gswb_log, tab_date,tab_date_07);
		System.out.println("三级渠道结束 : "+ new Date());
	}
}
