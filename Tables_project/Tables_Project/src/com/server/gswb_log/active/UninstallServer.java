package com.server.gswb_log.active;

import java.util.List;

import com.dao.gswb_log.active.UninstallDao;
import com.entity.statistics.Online_collect_gswb;
import com.entity.statistics.Uninstall_collect_gswb;

/**
 * �������뷨
 * �߼�ҵ���
 * ж�ر�ͳ��
 * @author ZhuYa
 *
 */
public class UninstallServer {
	UninstallDao uninstallDao = new UninstallDao();
	/**
	 * ��ȡж�ر�ͳ�Ƽ���
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public List<Uninstall_collect_gswb> getGswb_Log_Uninstall(String db_ip,String user,String pwd,String db_name,String tab_name){
		//��ȡ���߱�ͳ�Ƽ���
		List<Uninstall_collect_gswb> lists = uninstallDao.getGswb_Log_Uninstall(db_ip, user, pwd, db_name, tab_name);
		return lists;
	}
	
	/**
	 * ����������뷨ж��ͳ�Ʊ�
	 * @param lists : �������뷨ж��ͳ�Ƽ���
	 * @param tab_name : ����
	 */
	public void insertGwsp_Uninstall(List<Uninstall_collect_gswb> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
		if(lists.size()>0 && lists!=null){
			uninstallDao.insert_Gswb_Log_Uninstall(lists,db_ip,user,pwd,db_name,tab_name);
		}
	}
}
