package com.server.union;

import java.util.List;

import com.dao.union.PromotionSoftwareDao;
import com.entity.union.Promotion_Install;
import com.entity.union.Promotion_Software;

public class PromotionSoftwareServer {
	PromotionSoftwareDao softDao = new PromotionSoftwareDao();
	
	/**
	 * 返回软件数据集合
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public List<Promotion_Software> getPromotionSoftware(String db_ip,String user,String pwd,String db_name,String tab_name){
		List<Promotion_Software> lists = softDao.getPromotionSoftware(db_ip, user, pwd, db_name, tab_name);
		return lists;
	}
	
	/**
	 * 插入软件数据集合
	 * @param lists
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 */
	public void insertPromotionInstall(List<Promotion_Software> lists,String db_ip,String user,String pwd,String db_name){
		if(lists.size()>0 && lists!=null){
			softDao.insert_PromotionSoftware(lists, db_ip, user, pwd, db_name);
		}
	}
	
	/**
	 * 获取好123IP地址统计量
	 * @param db_ip	: 127库
	 * @param user
	 * @param pwd
	 * @param db_name	:	union
	 * @param inputType	: 	统计时间
	 * @return
	 */
	public List<Promotion_Software> getHao123(String db_ip,String user,String pwd,String db_name,int inputDate){
		List<Promotion_Software> lists = softDao.getHao123(db_ip, user, pwd, db_name, inputDate);
		return lists;
	}
}
