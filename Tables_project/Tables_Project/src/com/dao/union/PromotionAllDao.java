package com.dao.union;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.db.info.DataBase_Info;
import com.db.info.sql.union.T_Sql_Promotion_install;
import com.entity.union.Promotion_All;
import com.entity.union.Promotion_Online;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/**
 * 
 * 装机联盟
 * 安装明细统计
 * @author ZhuYa
 */
public class PromotionAllDao {
	/**
	 * 返回装机联盟总表 (1) : 43库
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public Promotion_All getPromotionAll_01(String db_ip,String user,String pwd,String db_name,String tab_name){
		Promotion_All obj = null;
		 //获取数据库连接
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				Statement statement = (Statement) conn.createStatement();
				//查询所有渠道汇总统计
				String sql = T_Sql_Promotion_install.return_getPromotionAll_01(tab_name);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					obj = new Promotion_All();
					obj.setDateline(DataBase_Info.rs.getInt("dateline"));						 		//日期
					obj.setFaFangNum(DataBase_Info.rs.getInt("faFangNum"));						 		//当日积分发放用户数
					obj.setFaFangIntegrationNum(DataBase_Info.rs.getInt("faFangIntegrationNum"));		//当日发放积分总量
					obj.setInstallationTodayNum(DataBase_Info.rs.getInt("installationTodayNum"));		//当日推广用户数
					obj.setIntegrationNum(DataBase_Info.rs.getInt("integrationNum"));					//当日推广积分量
					obj.setRegNum(DataBase_Info.rs.getInt("regNum"));									//当日签到用户总数
					obj.setRegIntegrationNum(DataBase_Info.rs.getInt("regIntegrationNum"));				//当日签到积分量
					obj.setUseNum(DataBase_Info.rs.getInt("useNum"));									//当日积分使用用户数
					obj.setUseIntegrationNum(DataBase_Info.rs.getInt("useIntegrationNum"));				//当日积分消耗总量
					obj.setDuihuanNum(DataBase_Info.rs.getInt("duihuanNum"));							//当日兑换话费用户数
					obj.setDhIntegrationNum(DataBase_Info.rs.getInt("dhIntegrationNum"));				//当日兑换话费积分量
					obj.setTixianNum(DataBase_Info.rs.getInt("tixianNum"));								//当日提现用户数
					obj.setTxIntegrationNum(DataBase_Info.rs.getInt("txIntegrationNum"));				//当日提现积分量
					obj.setIntAllIntegrationNum(DataBase_Info.rs.getInt("intAllIntegrationNum"));		//积分发放总量
					obj.setRegAllIntegrationNum(DataBase_Info.rs.getInt("regAllIntegrationNum"));		//签到积分总量
					obj.setInsAllIntegrationNum(DataBase_Info.rs.getInt("insAllIntegrationNum"));		//推广积分总量
					obj.setAteAllIntegrationNum(DataBase_Info.rs.getInt("ateAllIntegrationNum"));		//活动积分总量
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		try {
			    if(DataBase_Info.rs != null){   
			    	DataBase_Info.rs.close();   
			    }
			    if(conn != null){   
			     conn.close();   
			    }
		   } catch (Exception ex) {   
			   ex.printStackTrace();   
		   }
		}
		return obj;
	}
	
	/**
	 * 返回装机联盟总表 (2) : union 45库
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public Promotion_All getPromotionAll_02(String db_ip,String user,String pwd,String db_name,String tab_name){
		Promotion_All obj = null;
		 //获取数据库连接
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				Statement statement = (Statement) conn.createStatement();
				//查询所有渠道汇总统计
				String sql = T_Sql_Promotion_install.return_getPromotionAll_02(tab_name);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					obj = new Promotion_All();
					obj.setActiveNum(DataBase_Info.rs.getInt("activeNum"));					//活跃用户总数
					obj.setSpreadNum(DataBase_Info.rs.getInt("spreadNum"));					//有业绩用户数
					obj.setSpreadJiFenNum(DataBase_Info.rs.getInt("spreadJiFenNum"));		//用户推广积分总量
					obj.setConsumeNum(DataBase_Info.rs.getInt("consumeNum"));				//用户消耗积分总量
					obj.setLandedNum(DataBase_Info.rs.getInt("landedNum"));					//当日登陆用户数
					obj.setDownloadNum(DataBase_Info.rs.getInt("downloadNum"));				//当日下载用户数
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		try {
			    if(DataBase_Info.rs != null){   
			    	DataBase_Info.rs.close();   
			    }
			    if(conn != null){   
			     conn.close();   
			    }
		   } catch (Exception ex) {   
			   ex.printStackTrace();   
		   }
		}
		return obj;
	}
	
	
	/**
	 * 装机联盟: 流失用户
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public Promotion_All getPromotionAll_lostNum(String db_ip,String user,String pwd,String db_name,String tab_name){
		Promotion_All obj = null;
		 //获取数据库连接
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				Statement statement = (Statement) conn.createStatement();
				//查询所有渠道汇总统计 : 
				String sql = T_Sql_Promotion_install.return_getPromotionAll_lostNum(tab_name);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					obj = new Promotion_All();
					obj.setLostNum(DataBase_Info.rs.getInt("num"));		//流失用户数
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		try {
			    if(DataBase_Info.rs != null){   
			    	DataBase_Info.rs.close();   
			    }
			    if(conn != null){   
			     conn.close();   
			    }
		   } catch (Exception ex) {   
			   ex.printStackTrace();   
		   }
		}
		return obj;
	}
	
	/**
	 * 返回装机联盟总表 (3) : union 127库
	 * 主要:当日活跃数,次日活跃数,7日活跃数,30日活跃数
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name	: 统计日时间
	 * @param tab_name_01 : 次日
	 * @param tab_name_07 : 第7日
	 * @param tab_name_30 : 第30日
	 * @return
	 */
	public Promotion_All getPromotionAll_03(String db_ip,String user,String pwd,String db_name,String tab_name,String tab_name_01,String tab_name_07,String tab_name_30){
		Promotion_All obj = null;
		 //获取数据库连接
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				Statement statement = (Statement) conn.createStatement();
				//查询所有渠道汇总统计
				String sql = T_Sql_Promotion_install.return_getPromotionAll_03(tab_name, tab_name_01, tab_name_07, tab_name_30);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					obj = new Promotion_All();
					obj.setLoginNum(DataBase_Info.rs.getInt("loginNum"));							//当日注册用户数
					obj.setRegInsActiveNum(DataBase_Info.rs.getInt("regInsActiveNum"));				//当日活跃用户数
					obj.setNextRegInsActive(DataBase_Info.rs.getInt("nextRegInsActive"));			//次日活跃用户数
					obj.setNext7DayRegInsActive(DataBase_Info.rs.getInt("next7DayRegInsActive"));	//7日活跃用户数
					obj.setNext30DayRegInsActive(DataBase_Info.rs.getInt("next30DayRegInsActive"));	//30日活跃用户数
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		try {
			    if(DataBase_Info.rs != null){   
			    	DataBase_Info.rs.close();   
			    }
			    if(conn != null){   
			     conn.close();   
			    }
		   } catch (Exception ex) {   
			   ex.printStackTrace();   
		   }
		}
		return obj;
	}
	
	/**
	 * 插入装机联盟总表
	 * @param lists
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 */
	public void insert_PromotionAll(Promotion_All obj,String db_ip,String user,String pwd,String db_name){
		String sql = T_Sql_Promotion_install.insert_PromotionAll;		 //插入语句
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //获取数据库连接
		try{
			if(!conn.isClosed()){
				//活跃表最终统计集合
			    PreparedStatement pstatement = (PreparedStatement) conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
			    conn.setAutoCommit(false);
			   if(obj != null){
						pstatement.setInt(1, obj.getDateline());
						pstatement.setInt(2, obj.getActiveNum());
						pstatement.setInt(3, obj.getSpreadNum());
						pstatement.setInt(4, obj.getSpreadJiFenNum());
						pstatement.setInt(5, obj.getConsumeNum());
						pstatement.setInt(6, obj.getLoginNum());
						pstatement.setInt(7, obj.getLandedNum());
						pstatement.setInt(8, obj.getDownloadNum());
						pstatement.setInt(9, obj.getLostNum());
						pstatement.setInt(10, obj.getRegInsActiveNum());
						pstatement.setDouble(11, obj.getActivepercentage());
						pstatement.setInt(12, 0);
						pstatement.setInt(13, 0);
						pstatement.setInt(14, 0);
						pstatement.setInt(15, 0);
						pstatement.setInt(16, 0);
						pstatement.setInt(17, 0);
						pstatement.setInt(18, obj.getFaFangNum());
						pstatement.setInt(19, obj.getFaFangIntegrationNum());
						pstatement.setInt(20, obj.getInstallationTodayNum());
						pstatement.setInt(21, obj.getIntegrationNum());
						pstatement.setInt(22, obj.getRegNum());
						pstatement.setInt(23, obj.getRegIntegrationNum());
						pstatement.setInt(24, obj.getUseNum());
						pstatement.setInt(25, obj.getUseIntegrationNum());
						pstatement.setInt(26, obj.getDuihuanNum());
						pstatement.setInt(27, obj.getDhIntegrationNum());
						pstatement.setInt(28, obj.getTixianNum());
						pstatement.setInt(29, obj.getTxIntegrationNum());
						pstatement.setInt(30, obj.getIntAllIntegrationNum());
						pstatement.setInt(31, obj.getRegAllIntegrationNum());
						pstatement.setInt(32, obj.getInsAllIntegrationNum());
						pstatement.setInt(33, obj.getAteAllIntegrationNum());
						
						pstatement.addBatch();
						pstatement.executeBatch();
					    conn.commit();  
					    System.out.println("装机联盟总表插入成功!");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
			    if(DataBase_Info.rs != null){
			    	DataBase_Info.rs.close();   
			    } 
			    if(conn != null){
			    	conn.close();   
			    }
			 } catch (Exception ex) {
				 	ex.printStackTrace();   
			 }
		}
	}
	
	/**
	 * 更新 次日 装机联盟总表
	 * @param nextActive	: 次日率,第7日率,第30日率
	 * @param nextRegInsActive : 次日活跃数,第7日活跃数,第30日活跃数
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_beforeDate
	 */
	public void update_PromotionAll(double nextActive,int nextRegInsActive,String db_ip,String user,String pwd,String db_name,String tab_beforeDate){
		
		//更新
		String sql = T_Sql_Promotion_install.updateNextDay_PromotionAll(nextActive,nextRegInsActive,tab_beforeDate);		 
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //获取数据库连接
		try{
			if(!conn.isClosed()){
				//活跃表最终统计集合
			    PreparedStatement pstatement = (PreparedStatement) conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
			    conn.setAutoCommit(false);
			    //更新
			    pstatement.executeUpdate(sql);   
			    pstatement.addBatch();
			    pstatement.executeBatch();
			    conn.commit();  
			    System.out.println("装机联盟总表 更新成功");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
			    if(DataBase_Info.rs != null){
			    	DataBase_Info.rs.close();   
			    } 
			    if(conn != null){
			    	conn.close();
			    }
			 } catch (Exception ex) {
				 	ex.printStackTrace();
			 }
		}
	}
	
	
	
	
}
