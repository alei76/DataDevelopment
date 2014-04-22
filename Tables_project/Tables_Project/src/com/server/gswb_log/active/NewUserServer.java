package com.server.gswb_log.active;

import java.util.ArrayList;
import java.util.List;

import com.dao.gswb_log.active.NewUserDao;
import com.entity.statistics.New_user_data_gswb;
import com.entity.statistics.Online_collect_gswb;

/**
 * 光速输入法
 * 逻辑业务层
 * 新用户表统计
 * @author ZhuYa
 *
 */
public class NewUserServer {
	NewUserDao newDao = new NewUserDao();
	/**
	 * 查询新用户统计信息集合
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public List<New_user_data_gswb> getNew_user_data_sum(String db_ip,String user,String pwd,String db_name,String tab_name){
		//获取新用户信息集合1
		List<New_user_data_gswb> newList_01 =  newDao.getNew_user_data_01(db_ip, user, pwd, db_name, tab_name);
		//获取新用户信息集合2
		List<New_user_data_gswb> newList_02 =  newDao.getNew_user_data_02(db_ip, user, pwd, db_name, tab_name);
		
		//定义用户信息 总集合
		List<New_user_data_gswb> newListSum = null;
		
		if(newList_01.size()>0 && newList_01 != null){
			//总集合
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
				//添加到总集合
				newListSum.add(newObj);
			}
		}
		return newListSum;
	}
	
	/**
	 * 插入光速输入法新用户统计表
	 * @param lists : 光速输入法新用户统计集合
	 * @param tab_name : 表名
	 */
	public void insertNewUserGwsp(List<New_user_data_gswb> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
		if(lists.size()>0 && lists!=null){
			newDao.insert_Gswb_Log_new_user(lists,db_ip,user,pwd,db_name,tab_name);
		}
	}
}
