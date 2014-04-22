package com.test;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.action.gswb_log.Active_collect_gswb_action;
import com.dao.gswb_log.active.InstallDao;
import com.db.info.Dictionary_DB;
import com.entity.statistics.Active_collect_gswb;
import com.entity.statistics.Install_collect_gswb;
import com.entity.statistics.Online_collect_gswb;
import com.entity.statistics.Uninstall_collect_gswb;
import com.job.Statistics_job;
import com.server.gswb_log.active.ActiveServer;
import com.server.gswb_log.active.InstallServer;
import com.server.gswb_log.active.OnlineServer;
import com.server.gswb_log.active.UninstallServer;

/**
 * 光速输入法入口
 * @author ZhuYa
 *
 */
public class GswbTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		ActiveServer active = new ActiveServer();
//		InstallServer install = new InstallServer();
		//db_ip, user, pwd, db_name, tab_name
//		List<Active_collect_gswb> lists = active.getGswb_Log_All_Active_01(Dictionary_DB.ip_01, Dictionary_DB.user_01, Dictionary_DB.password_01, Dictionary_DB.db_gswb_log,"active20140213");
//		active.insertActiveGwsp(lists, "active20140213");
		
		
//		List<Uninstall_collect_gswb> listss = uninstall.getGswb_Log_Uninstall(Dictionary_DB.ip_01, Dictionary_DB.user_01, Dictionary_DB.password_01, Dictionary_DB.db_gswb_log,"uninstall20140213");
//		uninstall.insertGwsp_Uninstall(listss,"uninstall20140213");
		
		
//		
//		List<Install_collect_gswb> listTable = install.getGswb_Log_Install_sum_cont(Dictionary_DB.ip_01, Dictionary_DB.user_01, Dictionary_DB.password_01, Dictionary_DB.db_gswb_log,"install20140213");
//		install.insertInstallGwsp(listTable, "install20140213");
		
		
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		
//		Statistics_job job = new Statistics_job();
//		job.gswb_log_work();
		
//		
//		Integer tab_date = Dictionary_DB.getBeforeDate(0, null);
//		Active_collect_gswb_action action =  new Active_collect_gswb_action();
//		action.insertNewUserGwsp(Dictionary_DB.ip_01, Dictionary_DB.user_01, Dictionary_DB.password_01, Dictionary_DB.db_statistics,tab_date,Dictionary_DB.tblname_gswb_log_install);
//	
//		Integer tab_date =20140227;
//		Integer days_071= Dictionary_DB.getTodayDate(tab_date,30);
//		System.out.println(days_071);
	}

}
