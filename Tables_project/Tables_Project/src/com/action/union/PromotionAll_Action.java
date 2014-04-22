package com.action.union;


import com.entity.union.Promotion_All;
import com.server.union.PromotionAllServer;

/**
 * 装机联盟总表 统计
 * @author ZhuYa
 *
 */
public class PromotionAll_Action {
	
		/**
		 * 插入装机联盟 总表 (45测试库)或(127正式库)
		 * @param db_ip	: (43库正式库)
		 * @param user
		 * @param pwd
		 * @param db_name
		 * @param db_ip01 :  (45测试库)或(127正式库)
		 * @param user01
		 * @param pwd01
		 * @param db_name01
		 * @param tab_name	: 统计日
		 * @param tab_name_01 : 次日
		 * @param tab_name_07 : 第7日
		 * @param tab_name_30 : 第30日
		 */
		public void insertPromotionAll(String db_ip,String user,String pwd,String db_name,String tab_name,String tab_name_01,String tab_name_07,String tab_name_30,String db_ip01,String user01,String pwd01,String db_name01){
				double nextNum = 0.0;			//次日活跃率
				double next7Num = 0.0;		//第7日活跃率
				double next30Num = 0.0;  	//第30日活跃率
				PromotionAllServer qdServer = new PromotionAllServer();
				//获取装机联盟 安装集合
				Promotion_All obj =  qdServer.getPromotionInstall(db_ip, user, pwd, db_name, tab_name, tab_name_01, tab_name_07, tab_name_30);
				if(obj != null){
						//插入装机联盟总表
						qdServer.insertPromotionAll(obj, db_ip01, user01, pwd01, db_name01);
						
						// 当日注册/次日活跃用户数
						if(obj.getNextRegInsActive() != 0){
							nextNum = Math.round((obj.getNextRegInsActive()*1.00/obj.getLoginNum())*100);
						}
						qdServer.updatePromotionAll(nextNum,obj.getNextRegInsActive(), db_ip01, user01, pwd01, db_name01, tab_name_01);  //更新装机联盟总表(次日活跃率)
						
						// 当日注册/7日活跃用户数
						if(obj.getNext7DayRegInsActive() != 0){
							next7Num = Math.round((obj.getNext7DayRegInsActive()*1.00/obj.getLoginNum())*100);
						}
						qdServer.updatePromotionAll(next7Num,obj.getNext7DayRegInsActive(), db_ip01, user01, pwd01, db_name01, tab_name_07); //更新装机联盟总表(7日活跃率)
						
						// 当日注册/30日活跃用户数
						if(obj.getNext30DayRegInsActive() != 0){
							next30Num = Math.round((obj.getNext30DayRegInsActive()*1.00/obj.getLoginNum())*100);
						}
						qdServer.updatePromotionAll(next30Num,obj.getNext30DayRegInsActive(), db_ip01, user01, pwd01, db_name01, tab_name_30); //更新装机联盟总表(30日活跃率)
				}
		}
}
