package com.job;

import java.util.Date;

import com.action.gswb_log.Active_collect_gswb_action;
import com.db.info.Dictionary_DB;

/**
 * 光速输入法统计
 * 任务调度 类
 * @author ZhuYa
 *
 */
public class Statistics_job {
	Active_collect_gswb_action action =  new Active_collect_gswb_action();
	//param1(0:是当前日期,1:是手动输入)
	//param2(手动输入的时间)
	Integer tab_date = Dictionary_DB.getBeforeDate(0, null);
	/**
	 * 调度gswb_log库的统计
	 */
	public void gswb_log_work(){
		System.out.println("开始执行"+new Date());
		
		//插入光速输入法 (活跃统计表)
		action.insertActiveGwsp(Dictionary_DB.ip_01, Dictionary_DB.user_01, Dictionary_DB.password_01, Dictionary_DB.db_gswb_log,tab_date,Dictionary_DB.tblname_gswb_log_active);
		//插入光速输入法(在线统计表)
		action.insertOnlineGwsp(Dictionary_DB.ip_01, Dictionary_DB.user_01, Dictionary_DB.password_01, Dictionary_DB.db_gswb_log,tab_date,Dictionary_DB.tblname_gswb_log_online);
		//插入光速输入法(卸载统计表)
		action.insertGwsp_Uninstall(Dictionary_DB.ip_01, Dictionary_DB.user_01, Dictionary_DB.password_01, Dictionary_DB.db_gswb_log,tab_date,Dictionary_DB.tblname_gswb_log_uninstall);
		//插入光速输入法(安装统计表)
		action.insertInstallGwsp(Dictionary_DB.ip_01, Dictionary_DB.user_01, Dictionary_DB.password_01, Dictionary_DB.db_gswb_log,tab_date,Dictionary_DB.tblname_gswb_log_install);
		//插入光速输入法(新用户统计表) : 表名随便,只需要表时间
		action.insertNewUserGwsp(Dictionary_DB.ip_01, Dictionary_DB.user_01, Dictionary_DB.password_01, Dictionary_DB.db_gswb_log,tab_date,Dictionary_DB.tblname_gswb_log_install);
		
		System.out.println("结束执行"+new Date());
	}
}
