package com.server.gswb_log.active;

import java.util.List;

import com.dao.gswb_log.active.OnlineDao;
import com.entity.statistics.Active_collect_gswb;
import com.entity.statistics.Online_collect_gswb;

/**
 * 光速输入法
 * 逻辑业务层
 * 在线表统计
 * @author ZhuYa
 *
 */
public class OnlineServer {
	OnlineDao onlineDao = new OnlineDao();
	
	/**
	 * 获取在线表统计集合
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public List<Online_collect_gswb> getGswb_Log_Online(String db_ip,String user,String pwd,String db_name,String tab_name){
		//获取在线表统计集合
		List<Online_collect_gswb> lists = onlineDao.getGswb_Log_Online(db_ip, user, pwd, db_name, tab_name);
		return lists;
	}
	
	/**
	 * 插入光速输入法在线统计表
	 * @param lists : 光速输入法在线统计集合
	 * @param tab_name : 表名
	 */
	public void insertOnlineGwsp(List<Online_collect_gswb> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
		if(lists.size()>0 && lists!=null){
			onlineDao.insert_Gswb_Log_Online(lists,db_ip,user,pwd,db_name,tab_name);
		}
	}
}
