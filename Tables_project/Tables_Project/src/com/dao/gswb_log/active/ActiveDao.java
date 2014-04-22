package com.dao.gswb_log.active;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.db.info.DataBase_Info;
import com.db.info.sql.gswb.T_Sql_Gswb_log;
import com.entity.statistics.Active_collect_gswb;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/**
 * 数据访问层
 * 活跃表
 * @author ZhuYa
 *
 */
public class ActiveDao {
	
	/**
	 * 查询所有用户总活跃量,新用户活跃量 集合
	 * @param db_ip	: IP地址
	 * @param user	: 用户名
	 * @param pwd	: 密码
	 * @param db_name : 库名
	 * @param tab_name : 表名
	 * @return
	 */
	public List<Active_collect_gswb> getGswb_Log_All_Active_01(String db_ip,String user,String pwd,String db_name,String tab_name){
		Active_collect_gswb activeGswb = null;		
		List<Active_collect_gswb> active_gswb_list  = null;
		 //获取数据库连接
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				active_gswb_list = new ArrayList<Active_collect_gswb>();
				Statement statement = (Statement) conn.createStatement();
				//查询所有活跃表,新用户活跃表
				String sql = T_Sql_Gswb_log.return_getGswbAllActive_01(tab_name);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					 activeGswb = new Active_collect_gswb();
					 activeGswb.setQid(DataBase_Info.rs.getString("qid"));
					 activeGswb.setSoftVer(DataBase_Info.rs.getString("softVer"));
					 activeGswb.setActiveNum(DataBase_Info.rs.getInt("activeNum"));
					 activeGswb.setNewUserActiveNum(DataBase_Info.rs.getInt("newUserActiveNum"));
					 activeGswb.setAdvertIdentifier(DataBase_Info.rs.getString("advertIdentifier"));
					 //判断是否有字符串
					 try{
						 activeGswb.setAdvertDate(DataBase_Info.rs.getInt("AdvertDate"));
					 }catch(Exception e){
						 activeGswb.setAdvertDate(0);
					 }
					 //光速输入法 活跃集合
					 active_gswb_list.add(activeGswb);
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
		return active_gswb_list;
	}
	
	/**
	 * 查询输入次数小于等于10,输入次数小于等于140,输入次数大于140 集合
	 * @param db_ip	: IP地址
	 * @param user	: 用户名
	 * @param pwd	: 密码
	 * @param db_name : 库名
	 * @param tab_name : 表名
	 * @return
	 */
	public List<Active_collect_gswb> getGswb_Log_only_Active_02(String db_ip,String user,String pwd,String db_name,String tab_name){
		Active_collect_gswb activeGswb = null;		
		List<Active_collect_gswb> active_gswb_list  = null;
		 //获取数据库连接
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				active_gswb_list = new ArrayList<Active_collect_gswb>();
				Statement statement = (Statement) conn.createStatement();
				//查询所有活跃表,新用户活跃表
				String sql = T_Sql_Gswb_log.return_getGswbAllActive_02(tab_name);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					 activeGswb = new Active_collect_gswb();
					 activeGswb.setQid(DataBase_Info.rs.getString("qid"));
					 activeGswb.setSoftVer(DataBase_Info.rs.getString("softVer"));
					 activeGswb.setAdvertIdentifier(DataBase_Info.rs.getString("advertIdentifier"));
					 activeGswb.setInputLV1Num(DataBase_Info.rs.getInt("InputLV1Num"));
					 activeGswb.setInputLV1Num(DataBase_Info.rs.getInt("InputLV2Num"));
					 activeGswb.setInputLV1Num(DataBase_Info.rs.getInt("InputLV3Num"));
					 //判断是否有字符串
					 try{
						 activeGswb.setAdvertDate(DataBase_Info.rs.getInt("AdvertDate"));
					 }catch(Exception e){
						 activeGswb.setAdvertDate(0);
					 }
					 //光速输入法 活跃集合
					 active_gswb_list.add(activeGswb);
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
		return active_gswb_list;
	}
	
	/**
	 * 光速输入法
	 * 往活跃统计表中插入数据
	 * @param db_ip
	 * @param user
	 * @param pwd
	 */
	public void insert_Gswb_Log_Active(List<Active_collect_gswb> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
		Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-6));
		//查询新活跃用户
		String sql = T_Sql_Gswb_log.insert_collect_gswb;	//插入语句
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //获取数据库连接
		try{
			if(!conn.isClosed()){
				//活跃表最终统计集合
			    PreparedStatement pstatement = (PreparedStatement) conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
			    conn.setAutoCommit(false);
			    for(int i=0;i<lists.size();i++){
						pstatement.setString(1, lists.get(i).getAdvertIdentifier());
						pstatement.setInt(2, lists.get(i).getActiveNum());
						pstatement.setInt(3, lists.get(i).getNewUserActiveNum());
						pstatement.setInt(4, lists.get(i).getInputLV1Num());
						pstatement.setInt(5, lists.get(i).getInputLV2Num());
						pstatement.setInt(6, lists.get(i).getInputLV3Num());
						pstatement.setInt(7, lists.get(i).getAdvertDate());
						pstatement.setInt(8, tab_date);
						pstatement.setString(9, lists.get(i).getSoftVer());
						pstatement.addBatch();
				}
			    pstatement.executeBatch();
			    conn.commit();  
			    System.out.println("光速输入法活跃表数据添加成功!");
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
