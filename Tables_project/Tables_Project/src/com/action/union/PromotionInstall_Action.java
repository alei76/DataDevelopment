package com.action.union;

import java.util.ArrayList;
import java.util.List;

import com.entity.union.Promotion_Install;
import com.entity.union.Promotion_Online;
import com.entity.union.Promotion_Software;
import com.server.union.PromotionInstallServer;

/**
 * 装机联盟明细 统计
 * @author ZhuYa
 *
 */
public class PromotionInstall_Action {
	PromotionInstallServer qdServer = new PromotionInstallServer();
	/**
	 * 装机联盟明细 安装统计
	 * @param db_ip	: 43库 
	 * @param user
	 * @param pwd
	 * @param db_name	:	db_zj7654_log
	 * @param db_ip_01	:	127库
	 * @param user_01
	 * @param pwd_01
	 * @param db_name_01 : db_union
	 * @param todayDate   : 当前时间
	 * @param input	:	输入类型
	 * @param tab_name02 : db_statistics
	 * @param tableType	:   表头类型(install/online)
	 */
	public void insertPromotionInstall(String db_ip,String user,String pwd,String db_name,String db_ip_01,String user_01,String pwd_01,String db_name_01,String todayDate,int input,String tab_name02,String tableType){
		
		//获取装机联盟 安装集合 : 43服务器zj7654_log库
		List<Promotion_Install> lists =  qdServer.getPromotionInstall(db_ip, user, pwd, db_name, todayDate);
		//获取存储30天表头
		List<Integer> tab_list_sum = qdServer.getGswb30Days(db_ip, user, pwd, tab_name02, Integer.parseInt(todayDate), input, tableType,30);
		List<Promotion_Install> listInstall = null;
		if(tab_list_sum.size() > 0){
			//30天表头
			for(int k = 0;k<tab_list_sum.size();k++){
					//获取安装表30天数据集合 : 43服务器 statitics库
					listInstall = qdServer.getGswb30DayInstall(db_ip, user, pwd, tab_name02, Integer.parseInt(todayDate), input, tableType,tab_list_sum.get(k));
					//每天去重
					if(listInstall.size() > 0){
							//装机联盟集合
							for(int i=0;i<lists.size();i++){
									String pc_id = lists.get(i).getPc_id();
									String softwareId = lists.get(i).getSoftware_id();
									String softVerion = lists.get(i).getSoftware_version();
									//30天的集合
									for(int j = 0;j<listInstall.size();j++){
											String pc_id02 = listInstall.get(j).getPc_id();
											String softwareId02 = listInstall.get(j).getSoftware_id();
											String softVerion02 = listInstall.get(j).getSoftware_version();
											//装机联盟的安装用户 在光速输入法用户中存在的话就去除
											if(pc_id.equals(pc_id02) && softwareId.equals(softwareId02) && softVerion.equals(softVerion02)){
												lists.remove(j);
											}
									}
							}
					}
					listInstall.clear();
			}
		}
		
		if(lists!=null){
			qdServer.insertPromotionInstall(lists, db_ip_01, user_01, pwd_01, db_name_01, todayDate);
		}
	}
	
	/**
	 *  备份 剔重全渠道GSWB Online30天的
	 * 装机联盟 在线使用 统计
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param db_ip_01
	 * @param user_01
	 * @param pwd_01
	 * @param db_name_01
	 * @param inputType	: 输入类型
	 * @param inputDate : 输入时间
	 * @param tableType : 输入表头类型(install/online)
	 */
	public void insertPromotionOnline_BAK(String db_ip,String user,String pwd,String db_name,String db_ip_01,String user_01,String pwd_01,String db_name_01,Integer inputType,Integer inputDate,String tableType,String tab_name02){
		//获取装机联盟 15天任意2天在线使用集合
		List<Promotion_Online> lists =  qdServer.getPromotionOnline(db_ip, user, pwd, db_name,tableType,inputType, inputDate);
		//获取存储30天表头
		List<Integer> tab_list_sum = qdServer.getGswb30Days(db_ip, user, pwd, tab_name02, inputDate, inputType, tableType,30);
		if(tab_list_sum.size() > 0){
			//获取30天数据,并每天去重
			for(int k = 0;k<tab_list_sum.size();k++){
				//获取在线表30天数据集合
				List<Promotion_Install> listOnline = qdServer.getGswb30DayOnline(db_ip, user, pwd, tab_name02, inputDate, inputType, tableType,tab_list_sum.get(k));
				if(listOnline.size() > 0){
						for(int i = 0;i<lists.size();i++){
								String pc_id = lists.get(i).getPc_id();
								String softwareId = lists.get(i).getSoftware_id();
								String softVerion = lists.get(i).getSoftware_version();
								for(int j = 0;j<listOnline.size();j++){
										String pc_id02 = listOnline.get(i).getPc_id();
										String softwareId02 = listOnline.get(i).getSoftware_id();
										String softVerion02 = listOnline.get(i).getSoftware_version();
										//装机联盟的在线用户 在光速输入法用户中存在的话就去除
										if(pc_id.equals(pc_id02) && softwareId.equals(softwareId02) && softVerion.equals(softVerion02)){
											lists.remove(i);
										}
								}
						}
				}
				listOnline.clear();
			}
		}
	
		if(lists!=null){
			qdServer.insertPromotionOnline_BAK(lists, db_ip_01, user_01, pwd_01, db_name_01);
		}
	}
	
	/**
	 * 15天任意2天使用的这种用户
	 * 1. promotionInstall这个表里面的数据都是 对GSWB install30天做过剔重的
	 * 2. 那我直接用 装机联盟符合  15天在线使用任意2天的用户 直接跟 1.关联 获取得数据  就是 promotiOnline
	 * 装机联盟 在线使用 统计
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param db_ip_01
	 * @param user_01
	 * @param pwd_01
	 * @param db_name_01
	 * @param inputType	: 输入类型
	 * @param inputDate : 输入时间
	 * @param tableType : 输入表头类型(install/online)
	 */
	public void insertPromotionOnline_BAK2(String db_ip,String user,String pwd,String db_name,String db_ip_01,String user_01,String pwd_01,String db_name_01,Integer inputType,Integer inputDate,String tableType,String tab_name02){
		//(1)获取装机联盟 15天任意2天在线使用集合
		List<Promotion_Online> lists =  qdServer.getPromotionOnline(db_ip, user, pwd, db_name,tableType,inputType, inputDate);
		//(2)获取promotionInstall15天安装量
		List<Promotion_Install> listInstall = qdServer.getUnionPromotionInstall(db_ip_01, user_01, pwd_01, db_name_01, inputDate.toString());
		//定义总集合: //获取(1)中匹配(2)中的数据
		List<Promotion_Online> listAll = new ArrayList<Promotion_Online>();
		
		//获取(1)中匹配(2)中的数据
		if(lists.size() > 0 && listInstall.size() > 0){
				for(int i = 0; i<lists.size(); i++){
						int uid = lists.get(i).getUid();
						String softwareId = lists.get(i).getSoftware_id();
						String softVerion = lists.get(i).getSoftware_version();
						int dateline = lists.get(i).getDateline();
						String pc_id = lists.get(i).getPc_id();
						int online_id = lists.get(i).getId();
						
						for(int j = 0; j<listInstall.size(); j++){
								int uid02 = listInstall.get(j).getUid();
								String softwareId02 = listInstall.get(j).getSoftware_id();
								String softVerion02 = listInstall.get(j).getSoftware_version();
								String pc_id02 = listInstall.get(j).getPc_id();
								//相同则添加数据
								if(pc_id.equals(pc_id02) && softwareId.equals(softwareId02) && softVerion.equals(softVerion02) && uid == uid02){
										Promotion_Online online = new Promotion_Online();
										online.setUid(uid);
										online.setSoftware_id(softwareId);
										online.setSoftware_version(softVerion);
										online.setDateline(dateline);
										online.setPc_id(pc_id);
										online.setId(online_id);
										listAll.add(online);
								}
						}
				}
		}
		//插入
		if(listAll.size() > 0 ){
			qdServer.insertPromotionOnline_BAK(listAll, db_ip_01, user_01, pwd_01, db_name_01);
		}

	}
	
	/**
	 * 最新需求
	 * 装机联盟 有效安装用户数 统计
	 * 调用根平那边的有效用户数的接口
	 * @param db_ip : 43库
	 * @param user
	 * @param pwd
	 * @param db_name : statistics
	 * @param db_ip02 : 127库
	 * @param user02
	 * @param pwd02
	 * @param db_name02 : union
	 * @param db_name03 : gswb_log
	 * @param inputDate
	 */
	public void insertPromotionOnline(String db_ip,String user,String pwd,String db_name,String db_ip02,String user02,String pwd02,String db_name02,String db_name03,String inputDate){
		//获取有效安装用户
		List<Promotion_Install> listAll = qdServer.getUnionInstallAll(db_ip, user, pwd, db_name, db_ip02, user02, pwd02, db_name02, db_name03, inputDate);
		
		//插入127库的 有效安装用户表中
		if(listAll.size() > 0 ){
			qdServer.insertPromotionOnline(listAll, db_ip02, user02, pwd02, db_name02);
		}

	}
	
	/**
	 * 旧需求，连续3天在线使用的用户
	 * 装机联盟 在线使用 统计
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param db_ip_01
	 * @param user_01
	 * @param pwd_01
	 * @param db_name_01
	 * @param inputType	: 输入类型
	 * @param inputDate : 输入时间
	 * @param tableType : 输入表头类型(install/online)
	 */
	public void insertPromotionOnlineOld(String db_ip,String user,String pwd,String db_name,String db_ip_01,String user_01,String pwd_01,String db_name_01,Integer inputType,Integer inputDate,String tableType,String tab_name02){
		//连续在线使用3天的用户
		List<Promotion_Online> lists =  qdServer.getPromotionOnlineOld(db_ip, user, pwd, db_name,tableType,inputType, inputDate);
		//插入
		if(lists.size() > 0 ){
			qdServer.insertPromotionOnline_BAK(lists, db_ip_01, user_01, pwd_01, db_name_01);
		}

	}
	
	
	
//	/**
//	 * 插入好123数据
//	 * @param db_ip
//	 * @param user
//	 * @param pwd
//	 * @param db_name
//	 * @param db_ip_01
//	 * @param user_01
//	 * @param pwd_01
//	 * @param db_name_01
//	 * @param inputDate
//	 */
//	public void insertHao123(String db_ip,String user,String pwd,String db_name,String db_ip_01,String user_01,String pwd_01,String db_name_01,Integer inputDate){
//		//获取好123IP统计量
//		List<Promotion_Software> lists = qdServer.getHao123(db_ip_01, user_01, pwd_01, db_name_01, inputDate);
//		
//	}
}
