package com.server.gswb_log.active;

import java.util.List;

import com.dao.gswb_log.active.UninstallDao;
import com.entity.statistics.Online_collect_gswb;
import com.entity.statistics.Uninstall_collect_gswb;

/**
 * 光速输入法
 * 逻辑业务层
 * 卸载表统计
 * @author ZhuYa
 *
 */
public class UninstallServer {
	UninstallDao uninstallDao = new UninstallDao();
	/**
	 * 获取卸载表统计集合
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public List<Uninstall_collect_gswb> getGswb_Log_Uninstall(String db_ip,String user,String pwd,String db_name,String tab_name){
		//获取在线表统计集合
		List<Uninstall_collect_gswb> lists = uninstallDao.getGswb_Log_Uninstall(db_ip, user, pwd, db_name, tab_name);
		return lists;
	}
	
	/**
	 * 插入光速输入法卸载统计表
	 * @param lists : 光速输入法卸载统计集合
	 * @param tab_name : 表名
	 */
	public void insertGwsp_Uninstall(List<Uninstall_collect_gswb> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
		if(lists.size()>0 && lists!=null){
			uninstallDao.insert_Gswb_Log_Uninstall(lists,db_ip,user,pwd,db_name,tab_name);
		}
	}
}
