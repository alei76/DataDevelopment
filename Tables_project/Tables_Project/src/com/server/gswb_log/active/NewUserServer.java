package com.server.gswb_log.active;

import java.util.ArrayList;
import java.util.List;

import com.dao.gswb_log.active.NewUserDao;
import com.entity.statistics.New_user_data_gswb;
import com.entity.statistics.Online_collect_gswb;

/**
 * �������뷨
 * �߼�ҵ���
 * ���û���ͳ��
 * @author ZhuYa
 *
 */
public class NewUserServer {
	NewUserDao newDao = new NewUserDao();
	/**
	 * ��ѯ���û�ͳ����Ϣ����
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public List<New_user_data_gswb> getNew_user_data_sum(String db_ip,String user,String pwd,String db_name,String tab_name){
		//��ȡ���û���Ϣ����1
		List<New_user_data_gswb> newList_01 =  newDao.getNew_user_data_01(db_ip, user, pwd, db_name, tab_name);
		//��ȡ���û���Ϣ����2
		List<New_user_data_gswb> newList_02 =  newDao.getNew_user_data_02(db_ip, user, pwd, db_name, tab_name);
		
		//�����û���Ϣ �ܼ���
		List<New_user_data_gswb> newListSum = null;
		
		if(newList_01.size()>0 && newList_01 != null){
			//�ܼ���
			newListSum = new ArrayList<New_user_data_gswb>();
			
			for(int i = 0;i<newList_01.size();i++){
				New_user_data_gswb newObj = new New_user_data_gswb();
				newObj.setType(newList_01.get(i).getType());					
				newObj.setDateLine(newList_01.get(i).getDateLine());			
				newObj.setTodayNewInstallNum(newList_01.get(i).getTodayNewInstallNum());
				newObj.setTodayNewUninstallNum(newList_01.get(i).getTodayNewUninstallNum());
				newObj.setTodayNewActiveNum(newList_01.get(i).getTodayNewActiveNum());
				newObj.setNextDayActiveNum(newList_01.get(i).getNextDayActiveNum());
				
				for(int j=0;j<newList_02.size();j++){
					if(newObj.getDateLine()== newList_02.get(j).getDateLine()){
						newObj.setActiveNum7Day(newList_02.get(j).getActiveNum7Day());
						newObj.setActiveNum30Day(newList_02.get(j).getActiveNum30Day());
						newObj.setUninstallNum30Day(newList_02.get(j).getUninstallNum30Day());
					}
				}
				//��ӵ��ܼ���
				newListSum.add(newObj);
			}
		}
		return newListSum;
	}
	
	/**
	 * ����������뷨���û�ͳ�Ʊ�
	 * @param lists : �������뷨���û�ͳ�Ƽ���
	 * @param tab_name : ����
	 */
	public void insertNewUserGwsp(List<New_user_data_gswb> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
		if(lists.size()>0 && lists!=null){
			newDao.insert_Gswb_Log_new_user(lists,db_ip,user,pwd,db_name,tab_name);
		}
	}
}
