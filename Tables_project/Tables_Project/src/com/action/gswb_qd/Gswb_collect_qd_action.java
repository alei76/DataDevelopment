package com.action.gswb_qd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.db.info.Dictionary_DB;
import com.entity.statistics.Gswb_collect_qd;
import com.server.gswb_log.qd.GswbCollectQdServer;

/**
 * 光速输入法
 * 渠道汇总统计
 * @author ZhuYa
 *
 */
public class Gswb_collect_qd_action {
	GswbCollectQdServer qdServer = new GswbCollectQdServer();
	/**
	 * 插入渠道汇总统计表
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_date
	 * @return
	 */
	public List<Gswb_collect_qd> insertGswb_Collect_Qd(String db_ip,String user,String pwd,String db_name,String db_gswb_log,Integer tab_date,String tab_beforeDate){
		String tab_name = tab_date.toString();
		//大渠道总集合表
		List<Gswb_collect_qd> listAll = new ArrayList<Gswb_collect_qd>();
		
		//返回大渠道汇总统计表
		List<Gswb_collect_qd> lists =  qdServer.getGswb_Collect_Qd(db_ip, user, pwd, db_name, tab_name, Dictionary_DB.getQdName());
//		//返回大渠道活跃量 : 安装用户在1度2度3度下的用户数
//		List<Gswb_collect_qd> lists_02 = qdServer.getGswbQdInsActive(db_ip, user, pwd, db_gswb_log, tab_name);
//		//遍历总表
//		for(int i=0;i<lists.size();i++){
//			Gswb_collect_qd obj = new Gswb_collect_qd();
//			obj.setQdType(lists.get(i).getQdType());
//			obj.setServiceStartNum(lists.get(i).getServiceStartNum()); //当日服务启动量
//			obj.setActiveNum(lists.get(i).getActiveNum());			   //当日活跃用户量
//			obj.setOnlyInstallNum(lists.get(i).getOnlyInstallNum());   //当日安装量(去重)
//			obj.setMonthAddInstallNum(lists.get(i).getMonthAddInstallNum()); //当日按月增加
//			obj.setInputLV1Num(lists.get(i).getInputLV1Num());		   //输入字数小于等于10
//			obj.setInputLV2Num(lists.get(i).getInputLV2Num());		   //输入字数小于等于140
//			obj.setInputLV3Num(lists.get(i).getInputLV3Num());		   //输入字数大于140
//			obj.setActiveNum7Days(lists.get(i).getActiveNum7Days());   //7日存活量
//			obj.setUninstNum(lists.get(i).getUninstNum());			   //当日用户卸载量
//			obj.setInstallNum(lists.get(i).getInstallNum());		   //当日安装量(不去重)
//			
//			//遍历活跃量:安装用户在1,2,3度的用户数
//			for(int j=0;j<lists_02.size();j++){
//				if(lists.get(i).getQdType().equals(lists_02.get(i).getQdType())){
//					obj.setActiveInputNum(lists_02.get(j).getActiveInputNum()); //活跃用户量:安装用户在1度2度3度下的用户数
//				}
//			}
//			listAll.add(obj);
//		}
		
		//插入
		if(lists!=null && lists.size()>0){
			qdServer.insertGswb_Collect_Qd(lists, db_ip, user, pwd, db_name, tab_name);
			qdServer.updateGswb_Collect_Qd(lists, db_ip, user, pwd, db_name, tab_beforeDate);
		}
		return null;
	}
	
	
	/**
	 * 插入二级渠道汇总统计表
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_date
	 * @return
	 */
	public List<Gswb_collect_qd> insertGswb_Collect_Qd_02(String db_ip,String user,String pwd,String db_name,String db_gswb_log,Integer tab_date,String tab_beforeDate){
		String tab_name = tab_date.toString();
		//二级渠道总集合表
		Map<String,List<Gswb_collect_qd>> mapList = new HashMap<String,List<Gswb_collect_qd>>();
		
		Map<String,List<Gswb_collect_qd>> maplists =  qdServer.getGswb_Collect_Qd_02(db_ip, user, pwd, db_name, tab_name, Dictionary_DB.getQdName());
//		//返回二级渠道活跃量 : 安装用户在1度2度3度下的用户数
//		List<Gswb_collect_qd> lists_02 = qdServer.getGswbQdInsActiveNode2(db_ip, user, pwd, db_gswb_log, tab_name);
//		
//		if(maplists != null){
//			List<Gswb_collect_qd> listsLast  = new ArrayList<Gswb_collect_qd>();
//	    	for (String key : maplists.keySet()) {
//	    		List<Gswb_collect_qd> lists= maplists.get(key);
//	    		if(lists != null && lists.size()>0){
//	    			for(int i=0;i<lists.size();i++){
//	    				Gswb_collect_qd obj = new Gswb_collect_qd();
//	    				obj.setQdType(lists.get(i).getQdType());
//	    				obj.setServiceStartNum(lists.get(i).getServiceStartNum()); //当日服务启动量
//	    				obj.setActiveNum(lists.get(i).getActiveNum());			   //当日活跃用户量
//	    				obj.setOnlyInstallNum(lists.get(i).getOnlyInstallNum());   //当日安装量(去重)
//	    				obj.setMonthAddInstallNum(lists.get(i).getMonthAddInstallNum()); //当日按月增加
//	    				obj.setInputLV1Num(lists.get(i).getInputLV1Num());		   //输入字数小于等于10
//	    				obj.setInputLV2Num(lists.get(i).getInputLV2Num());		   //输入字数小于等于140
//	    				obj.setInputLV3Num(lists.get(i).getInputLV3Num());		   //输入字数大于140
//	    				obj.setActiveNum7Days(lists.get(i).getActiveNum7Days());   //7日存活量
//	    				obj.setUninstNum(lists.get(i).getUninstNum());			   //当日用户卸载量
//	    				obj.setInstallNum(lists.get(i).getInstallNum());		   //当日安装量(不去重)
//			    		if(lists_02 != null && lists_02.size()>0){
//			    			for(int j=0;j<lists_02.size();j++){
//			    				String qdType02 = lists_02.get(j).getQdType();
//			    				String qid02 = lists_02.get(j).getQid(); 
//			    				//判断是否二级渠道 : 添加安装符合123度活跃量
//			    				if(lists.get(i).getQdType().equals(qdType02) && lists.get(i).getQid().equals(qid02)){
//			    					obj.setInstallNum(lists_02.get(j).getActiveInputNum());
//			    					//当日活跃率：当日活跃量/用户安装量（当日去重）
//			    					if(lists.get(i).getOnlyInstallNum() != 0){
//			    						obj.setActiveInputPer(Math.round((lists_02.get(j).getActiveInputNum() *1.00/lists.get(i).getOnlyInstallNum())*100));
//			    					}else{
//			    						obj.setActiveInputPer(0);
//			    					}
//			    				}else{
//			    					obj.setInstallNum(0);
//			    				}
//			    			}
//			    		}
//	    			    	
//	    				listsLast.add(obj);
//	    			}
//	    			mapList.put(key, listsLast);
//	    		}
//	    	}
//	    }
		
		if(maplists!=null){
			qdServer.insertGswb_Collect_Qd_02(maplists, db_ip, user, pwd, db_name, tab_name);
			qdServer.updateGswb_Collect_Qd02(maplists, db_ip, user, pwd, db_name, tab_beforeDate);
		}
		return null;
	}
	
	/**
	 * 插入三级渠道汇总统计表
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param db_gswb_log
	 * @param tab_date
	 * @param tab_beforeDate
	 * @param tabName
	 * @return
	 */
	public List<Gswb_collect_qd> insertGswb_Collect_Qd_03(String db_ip,String user,String pwd,String db_name,String db_gswb_log,Integer tab_date,String tab_beforeDate){
		String tab_name = tab_date.toString();
		List<Gswb_collect_qd> lists =  qdServer.getPromotionNode3(db_ip, user, pwd, db_name, tab_name);
			
//		//三级渠道部分汇总
//		List<Gswb_collect_qd> list01 = qdServer.getPromotionNode3(db_ip, user, pwd, db_name, tab_name);
//		//三级渠道新增两需求集合
//		List<Gswb_collect_qd> list02 = qdServer.getPromotionNode3(db_ip, user, pwd, db_name, tab_name);
//		//合并集合数据
		if(lists!=null && lists.size() > 0){
			qdServer.insertGswb_Collect_Qd_03(lists, db_ip, user, pwd, db_name, tab_name);
			qdServer.updateGswb_Collect_Qd03(lists, db_ip, user, pwd, db_name, tab_beforeDate);
		}
		return null;
	}
}
