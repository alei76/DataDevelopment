package com.action.gswb_log;

import java.util.List;

import com.db.info.Dictionary_DB;
import com.entity.statistics.Active_collect_gswb;
import com.entity.statistics.Install_collect_gswb;
import com.entity.statistics.New_user_data_gswb;
import com.entity.statistics.Online_collect_gswb;
import com.entity.statistics.Uninstall_collect_gswb;
import com.server.gswb_log.active.ActiveServer;
import com.server.gswb_log.active.InstallServer;
import com.server.gswb_log.active.NewUserServer;
import com.server.gswb_log.active.OnlineServer;
import com.server.gswb_log.active.UninstallServer;

/**
 * �������뷨
 * չ�ֲ�
 * @author ZhuYa
 *
 */
public class Active_collect_gswb_action {
	//"localhost:3306","root","zhubao","stu_info"
	/**
	 * ����������뷨(��Ծͳ�Ʊ�)
	 */
	public void insertActiveGwsp(String db_ip,String user,String pwd,String db_name,Integer tab_date,String tab){
		String tab_name = tab+tab_date;
		ActiveServer activeServer = new ActiveServer();
		//��ȡ��Ծ����
		List<Active_collect_gswb> lists = activeServer.getGswb_Log_All_Active_01(db_ip,user,pwd,db_name,tab_name);
		if(lists.size()>0 && lists != null){
			activeServer.insertActiveGwsp(lists, "localhost:3306","root","zhubao","stu_info",tab_name);
		}
	}
	
	/**
	 * ����������뷨(����ͳ�Ʊ�)
	 * @param lists : �������뷨����ͳ�Ƽ���
	 * @param tab_name : ����
	 */
	public void insertOnlineGwsp(String db_ip,String user,String pwd,String db_name,Integer tab_date,String tab){
		String tab_name = tab+tab_date;
		OnlineServer onlineServer = new OnlineServer();
		List<Online_collect_gswb> lists = onlineServer.getGswb_Log_Online(db_ip,user,pwd,db_name,tab_name);
		if(lists.size()>0 && lists!=null){
			onlineServer.insertOnlineGwsp(lists, "localhost:3306","root","zhubao","stu_info",tab_name);
		}
	}
	
	/**
	 * ����������뷨(��װͳ�Ʊ�)
	 */
	public void insertInstallGwsp(String db_ip,String user,String pwd,String db_name,Integer tab_date,String tab){
		String tab_name = tab+tab_date;
		InstallServer installServer = new InstallServer();
		List<Install_collect_gswb> lists = installServer.getGswb_Log_Install_sum_cont(db_ip,user,pwd,db_name,tab_name);
		if(lists.size()>0 && lists!=null){
			installServer.insertInstallGwsp(lists, "localhost:3306","root","zhubao","stu_info",tab_name);
		}
	}
	
	/**
	 * ����������뷨(ж��ͳ�Ʊ�)
	 * @param lists : �������뷨ж��ͳ�Ƽ���
	 * @param tab_name : ����
	 */
	public void insertGwsp_Uninstall(String db_ip,String user,String pwd,String db_name,Integer tab_date,String tab){
		String tab_name = tab+tab_date;
		UninstallServer uninstallServer = new UninstallServer();
		List<Uninstall_collect_gswb> lists = uninstallServer.getGswb_Log_Uninstall(db_ip,user,pwd,db_name,tab_name);
		if(lists.size()>0 && lists!=null){
			uninstallServer.insertGwsp_Uninstall(lists, "localhost:3306","root","zhubao","stu_info",tab_name);
		}
	}
	
	/**
	 * ����������뷨(���û�ͳ�Ʊ�)
	 * @param lists : �������뷨���û�ͳ�Ƽ���
	 * @param tab_name : ����
	 */
	public void insertNewUserGwsp(String db_ip,String user,String pwd,String db_name,Integer tab_date,String tab){
		String tab_name = tab+tab_date;
		NewUserServer newUserServer = new NewUserServer();
		List<New_user_data_gswb> lists = newUserServer.getNew_user_data_sum("localhost:3306","root","zhubao","stu_info", tab_name);
		if(lists.size()>0 && lists!=null){
			newUserServer.insertNewUserGwsp(lists, db_ip,user,pwd,db_name,tab_name);
		}
	}
}
