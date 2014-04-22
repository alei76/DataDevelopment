package com.server.union;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dao.union.PromotionInstallDao;
import com.db.info.DataBase_Info;
import com.entity.statistics.Gswb_collect_qd;
import com.entity.statistics.Online_collect_gswb;
import com.entity.union.Promotion_Install;
import com.entity.union.Promotion_Online;
import com.entity.union.Promotion_Software;

/**
 * 装机联盟明细
 * 逻辑业务层
 * @author ZhuYa
 *
 */
public class PromotionInstallServer {
	PromotionInstallDao pitDao = new PromotionInstallDao();
	
	/**
	 * 返回装机联盟安装集合
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public List<Promotion_Install> getPromotionInstall(String db_ip,String user,String pwd,String db_name,String todayDate){
		//获取在线表统计集合
		List<Promotion_Install> lists = pitDao.getPromotionInstall(db_ip, user, pwd, db_name, todayDate);
		return lists;
	}
	
	/**
	 * 调用根平有效安装用户
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tableType
	 * @param inputType
	 * @param inputDate
	 * @return
	 */
	public List<Promotion_Online> getPromotionOnline(String db_ip,String user,String pwd,String db_name,String tableType,Integer inputType,Integer inputDate){
		//获取前15天的online表头
		List<Integer> onLine15Days = getGswb30Days(db_ip,user,pwd,db_name,inputDate,inputType,tableType,15);
		//获取前15天online数据集合
		List<Promotion_Online> lists = pitDao.getPromotionOnline(db_ip, user, pwd, db_name, inputType, inputDate,onLine15Days);
		//定义总结果集
		List<Promotion_Online> listAll = new ArrayList<Promotion_Online>();
		return listAll;
	}
	
	/**
	 * 旧需求
	 * 返回装机联盟获取15天内在线用户大于等于2天的用户
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tableType
	 * @param inputType
	 * @param inputDate
	 * @return
	 */
	public List<Promotion_Online> getPromotionOnlineOld2(String db_ip,String user,String pwd,String db_name,String tableType,Integer inputType,Integer inputDate){
//		//获取表头
//		List<Integer> onLineList = pitDao.getGswb_Log_Tab_Title(db_ip, user, pwd, db_name, tableType);
		//获取前15天的online表头
		List<Integer> onLine15Days = getGswb30Days(db_ip,user,pwd,db_name,inputDate,inputType,tableType,15);
		//获取前15天online数据集合
		List<Promotion_Online> lists = pitDao.getPromotionOnline(db_ip, user, pwd, db_name, inputType, inputDate,onLine15Days);
		//15天的数据剔重
		List<Promotion_Online> listsOnly = getOnlineOnly(lists);
		//定义总结果集
		List<Promotion_Online> listAll = new ArrayList<Promotion_Online>();
		//获取15天内在线用户大于等于2天的用户
		for(int i = 0; i<listsOnly.size();i++){
				if( listsOnly.get(i).getDay() >= 2 ){
						Promotion_Online  obj = new Promotion_Online();
						obj.setUid(listsOnly.get(i).getUid());						 		 					//渠道类型
						obj.setSoftware_id(listsOnly.get(i).getSoftware_id());			 	    	//软件id
						obj.setSoftware_version(listsOnly.get(i).getSoftware_version());	 	//软件版本
						obj.setPc_id(listsOnly.get(i).getPc_id());	 		 	 			//版本 
						obj.setDateline(listsOnly.get(i).getDateline());					//时间
						obj.setId(listsOnly.get(i).getId());								 		//清单表ID
						obj.setNum(listsOnly.get(i).getDay());  								//天数
						listAll.add(obj);
				}
		}
		return listAll;
	}
	
	/**
	 * 旧需求
	 * 装机联盟 连续在线使用3天的用户
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tableType
	 * @param inputType
	 * @param inputDate
	 * @return
	 */
	public List<Promotion_Online> getPromotionOnlineOld(String db_ip,String user,String pwd,String db_name,String tableType,Integer inputType,Integer inputDate){
		List<Promotion_Online> lists = null;
		List<Integer> onLineList = pitDao.getGswb_Log_Tab_Title(db_ip, user, pwd, db_name, tableType);
		if(onLineList.size()-1>=3){
			lists = pitDao.getPromotionOnlineOld(db_ip, user, pwd, db_name, inputType, inputDate);
		}
		return lists;
	}
	
	/**
	 * 装机联盟在线集合,剔重并且 增加数量
	 * 数量 : 用户15天内有多少天 存在
	 * @param lists
	 * @return
	 */
	public List<Promotion_Online> getOnlineOnly( List<Promotion_Online> lists ){
		List<Promotion_Online> listAll = new ArrayList<Promotion_Online>();
		if(lists.size() > 0){
				for(int i = 0 ; i < lists.size(); i++){
					String pc_id = lists.get(i).getPc_id();
					String softID = lists.get(i).getSoftware_id();
					String softVer = lists.get(i).getSoftware_version();
					int uid = lists.get(i).getUid();
					int dateline = lists.get(i).getDateline();
					int onlineId = lists.get(i).getId();
					int flag = 1;		//标识
					for(int j = lists.size()-1; j > i;  j--){
							String pc_id02 = lists.get(j).getPc_id() ;
							int uid02 = lists.get(j).getUid() ;
							if(pc_id.equals(pc_id02) && uid == uid02){
								flag = flag+1 ;
								lists.remove(j);
							}
					}
					Promotion_Online  obj = new Promotion_Online();
					obj.setUid(uid);						 		 	//渠道类型
					obj.setSoftware_id(softID);			 	    //软件id
					obj.setSoftware_version(softVer);	 	//软件版本
					obj.setPc_id(pc_id);	 		 	 				//版本 
					obj.setDateline(dateline);					//时间
					obj.setId(onlineId);								 //清单表ID
					obj.setDay(flag);  								//天数
					listAll.add(obj);
			}
		}
		return listAll;
	}
	
	
	/**
	 * 备份旧需求
	 * 插入装机联盟在线使用 集合
	 * @param lists
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 */
	public void insertPromotionOnline_BAK(List<Promotion_Online> lists,String db_ip,String user,String pwd,String db_name){
		if(lists.size()>0 && lists!=null){
			pitDao.insert_PromotionOnline_BAK(lists, db_ip, user, pwd, db_name);
		}
	}
	
	/**
	 * 插入装机联盟在线使用 集合
	 * @param lists
	 * @param db_ip : 127
	 * @param user
	 * @param pwd
	 * @param db_name : union
	 * @param tab_name
	 */
	public void insertPromotionOnline(List<Promotion_Install> lists,String db_ip,String user,String pwd,String db_name){
		if(lists.size()>0 && lists!=null){
			pitDao.insert_PromotionOnline(lists, db_ip, user, pwd, db_name);
		}
	}
	
	/**
	 * 插入装机联盟 集合
	 * @param lists
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 */
	public void insertPromotionInstall(List<Promotion_Install> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
		if(lists.size()>0 && lists!=null){
			pitDao.insert_PromotionInstall(lists, db_ip, user, pwd, db_name);
		}
	}
	
	
	
	
	/**
	 * 获取前30天表头
	 * @param db_ip	 : 43库的
	 * @param user	 
	 * @param pwd
	 * @param db_name :  gswb_log库
	 * @param todayDate :  当前时间
	 * @param input	:  1(手动输入时间) : 0(系统自动时间)
	 * @param tableType :  install(安装表LOG)
	 * @param tabTitleNum: 前N/30天的表头
	 * @return
	 */
	public List<Integer> getGswb30Days(String db_ip,String user,String pwd,String db_name,Integer todayDate,int input,String tableType,int tabTitleNum){
		int lastDate = 20140415;   		// 业务需求时间从20140415日开始
		int flag02 = 0;			// 标识30天
		int num = 0;   			// 表数量
		//定义存储30天表头
		List<Integer> tab_list_sum = new ArrayList<Integer>();
		//当前时间
//		Integer tab_date = Integer.parseInt(tab_name);
		//获取所有install表名
		List<Integer> installList = pitDao.getGswb_Log_Tab_Title(db_ip, user, pwd, db_name, tableType);
		//获取输入时间的30天集合
		List<Integer> installList02 = new ArrayList<Integer>();	
		//tabTitleNum : 30
		if(installList.size()>0 && installList.size() < tabTitleNum){
				for(int i = 0;i<installList.size();i++){
						if(installList.get(i) >= lastDate &&todayDate >= installList.get(i)){
								installList02.add(installList.get(i));
						}
				}
		}else if(installList.size() > 0 && installList.size() > tabTitleNum){ 		//tabTitleNum : 30
				for(int i = 0;i<installList.size();i++){
						if(installList.get(i) >= lastDate && todayDate >= installList.get(i) && flag02 <= tabTitleNum-1){		//tabTitleNum : 29
								flag02 = flag02+1;
								installList02.add(installList.get(i));
						}
				}
				num = flag02;
		}
		//判断是手动输入，当前时间
		if(input == 0){
			for(int i = 0;i<num;i++){
				//添加小于输入时间的表头
				tab_list_sum.add(installList02.get(i));
			}
		}else{
			for(int i = 0;i<num;i++){
				tab_list_sum.add(installList02.get(i));
			}
		}
		return tab_list_sum;
	}
	
	/**
	 * 获取安装表30天数据集合
	 * @param tabList
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @param input
	 * @param tableType
	 * @param tabTitle
	 * @return
	 */
	public List<Promotion_Install> getGswb30DayInstall(String db_ip,String user,String pwd,String db_name,Integer tab_name,int input,String tableType,Integer tabTitle){
		//返回获取30天数据
		List<Promotion_Install>  lists = pitDao.getGswbInstallLog(db_ip, user, pwd, db_name,tabTitle);
		return lists;
	}
	
	/**
	 * 获取在线表30天数据集合
	 * @param tabList
	 * @param db_ip	: 43库
	 * @param user
	 * @param pwd
	 * @param db_name : gswb_log库
	 * @param tab_name : install
	 * @param input : 1(手动输入时间) : 0(系统自动时间)
	 * @param tableType : install(安装表LOG)
	 * @param tabTitle : 表时间
	 * @return
	 */
	public List<Promotion_Install> getGswb30DayOnline(String db_ip,String user,String pwd,String db_name,Integer tab_name,int input,String tableType,Integer tabTitle){
		//返回获取30天数据
		List<Promotion_Install> lists = pitDao.getGswbOnlineLog(db_ip, user, pwd, db_name, tabTitle, input);
		return lists;
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
		List<Promotion_Software> lists = pitDao.getHao123(db_ip, user, pwd, db_name, inputDate);
		return lists;
	}
	
	/**
	 * 获取装机联盟15天数据量
	 * @param db_ip	: 	127库
	 * @param user	
	 * @param pwd
	 * @param db_name	:	union
	 * @param inputDate	:	输入时间
	 * @return
	 */
	public List<Promotion_Install> getUnionPromotionInstall(String db_ip,String user,String pwd,String db_name,String inputDate){
		List<Promotion_Install> lists = pitDao.getUnionPromotionInstall(db_ip, user, pwd, db_name, inputDate);
		return lists;
	}
	
	/**
	 * 返回有效用户安装量 : 月度唯一增加量 - 网吧安装量
	 * @param db_ip	: 43库
	 * @param user
	 * @param pwd
	 * @param db_name : statistics
	 * @param db_ip02	: 127库
	 * @param user02
	 * @param pwd02
	 * @param db_name02	: union
	 * @param db_name03 : gswb_log
	 * @param inputDate
	 * @return
	 */
	public List<Promotion_Install> getUnionInstallAll(String db_ip,String user,String pwd,String db_name,String db_ip02,String user02,String pwd02,String db_name02,String db_name03,String inputDate){
		List<Promotion_Install> objAllList = new ArrayList<Promotion_Install>();	//定义返回结果集
		List<Promotion_Install> obj1List = pitDao.getInstallOkGswb1(db_ip, user, pwd, db_name, inputDate);		// 获取月度唯一增加量
		List<Promotion_Install> obj2List = pitDao.getInstallWbGswb2(db_ip, user, pwd, db_name03, inputDate);		// 获取网吧安装量
		List<Promotion_Install> objInstallList = getUnionEffectiveInstall(obj1List , obj2List);		//比对 : 获取有效安装用户信息
		
		List<Integer> obj3 = pitDao.getUserId3(db_ip02, user02, pwd02, db_name02);	// 获取装机联盟账号ID
		//防止包名出错，正确获取账号ID： 与装机联盟USER表ID关联
		if(objInstallList.size()>0){
				for(int i = 0 ; i < objInstallList.size(); i++){
						for(int j = 0; j<obj3.size(); j++){
								if(objInstallList.get(i).getUserId().equals(obj3.get(j).toString())){
									Promotion_Install obj = new Promotion_Install();
									obj.setUserId(objInstallList.get(i).getUserId());
									obj.setNum(objInstallList.get(i).getNum());
									obj.setDateline(Integer.parseInt(inputDate));
									objAllList.add(obj);
								}
						}
				}
		}
		return objAllList;
	}
	
	/**
	 *  对比过程表 : 有效安装用户
	 * @param obj1	 : 获取月度唯一增加量
	 * @param obj2 : 获取网吧安装量
	 * @return
	 */
	public List<Promotion_Install> getUnionEffectiveInstall(List<Promotion_Install> obj1List, List<Promotion_Install> obj2List){
			List<Promotion_Install> installAll = new ArrayList<Promotion_Install>();
			if(obj1List.size() > 0 && obj2List.size()> 0){
					for(int i = 0; i < obj1List.size(); i++){	 //获取月度唯一增加量
						Promotion_Install obj = new Promotion_Install();
						int flag = 0;
						String userId = obj1List.get(i).getUserId();
						int num = obj1List.get(i).getNum() ;
						obj.setUserId(userId);
						
							for(int j = 0; j< obj2List.size(); j++){	//获取网吧安装量
									String userId02 = obj2List.get(j).getUserId();
									int num02 = obj2List.get(j).getNum();
									//有效安装量  : 获取月度唯一增加量 - 获取网吧安装量
									if(userId.equals(userId02)){
											flag = flag+1;	// 标记账号
											// 月度唯一增加量 - 网吧安装量
											int value = num - num02;
											if(value>=0){
												obj.setNum(value);
											}else{ 
												//防止出现负值
												obj.setNum(0);
											}
									}
							}
							//网吧安装量的用户如果为空
							if(flag==0){
								obj.setNum(num);
							}
							installAll.add(obj);
					}
			}else if(obj1List.size() > 0 && obj2List.size() <= 0){ 	//如果网吧安装量为空则
				installAll = obj1List;
			}
			return installAll;
		}
}
