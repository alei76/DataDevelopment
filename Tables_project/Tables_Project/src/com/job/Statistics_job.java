package com.job;

import java.util.Date;

import com.action.gswb_log.Active_collect_gswb_action;
import com.db.info.Dictionary_DB;

/**
 * �������뷨ͳ��
 * ������� ��
 * @author ZhuYa
 *
 */
public class Statistics_job {
	Active_collect_gswb_action action =  new Active_collect_gswb_action();
	//param1(0:�ǵ�ǰ����,1:���ֶ�����)
	//param2(�ֶ������ʱ��)
	Integer tab_date = Dictionary_DB.getBeforeDate(0, null);
	/**
	 * ����gswb_log���ͳ��
	 */
	public void gswb_log_work(){
		System.out.println("��ʼִ��"+new Date());
		
		//����������뷨 (��Ծͳ�Ʊ�)
		action.insertActiveGwsp(Dictionary_DB.ip_01, Dictionary_DB.user_01, Dictionary_DB.password_01, Dictionary_DB.db_gswb_log,tab_date,Dictionary_DB.tblname_gswb_log_active);
		//����������뷨(����ͳ�Ʊ�)
		action.insertOnlineGwsp(Dictionary_DB.ip_01, Dictionary_DB.user_01, Dictionary_DB.password_01, Dictionary_DB.db_gswb_log,tab_date,Dictionary_DB.tblname_gswb_log_online);
		//����������뷨(ж��ͳ�Ʊ�)
		action.insertGwsp_Uninstall(Dictionary_DB.ip_01, Dictionary_DB.user_01, Dictionary_DB.password_01, Dictionary_DB.db_gswb_log,tab_date,Dictionary_DB.tblname_gswb_log_uninstall);
		//����������뷨(��װͳ�Ʊ�)
		action.insertInstallGwsp(Dictionary_DB.ip_01, Dictionary_DB.user_01, Dictionary_DB.password_01, Dictionary_DB.db_gswb_log,tab_date,Dictionary_DB.tblname_gswb_log_install);
		//����������뷨(���û�ͳ�Ʊ�) : �������,ֻ��Ҫ��ʱ��
		action.insertNewUserGwsp(Dictionary_DB.ip_01, Dictionary_DB.user_01, Dictionary_DB.password_01, Dictionary_DB.db_gswb_log,tab_date,Dictionary_DB.tblname_gswb_log_install);
		
		System.out.println("����ִ��"+new Date());
	}
}
