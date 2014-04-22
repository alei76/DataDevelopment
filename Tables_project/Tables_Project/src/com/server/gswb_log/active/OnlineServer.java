package com.server.gswb_log.active;

import java.util.List;

import com.dao.gswb_log.active.OnlineDao;
import com.entity.statistics.Active_collect_gswb;
import com.entity.statistics.Online_collect_gswb;

/**
 * �������뷨
 * �߼�ҵ���
 * ���߱�ͳ��
 * @author ZhuYa
 *
 */
public class OnlineServer {
	OnlineDao onlineDao = new OnlineDao();
	
	/**
	 * ��ȡ���߱�ͳ�Ƽ���
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public List<Online_collect_gswb> getGswb_Log_Online(String db_ip,String user,String pwd,String db_name,String tab_name){
		//��ȡ���߱�ͳ�Ƽ���
		List<Online_collect_gswb> lists = onlineDao.getGswb_Log_Online(db_ip, user, pwd, db_name, tab_name);
		return lists;
	}
	
	/**
	 * ����������뷨����ͳ�Ʊ�
	 * @param lists : �������뷨����ͳ�Ƽ���
	 * @param tab_name : ����
	 */
	public void insertOnlineGwsp(List<Online_collect_gswb> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
		if(lists.size()>0 && lists!=null){
			onlineDao.insert_Gswb_Log_Online(lists,db_ip,user,pwd,db_name,tab_name);
		}
	}
}
