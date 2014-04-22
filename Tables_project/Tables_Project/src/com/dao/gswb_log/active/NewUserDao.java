package com.dao.gswb_log.active;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.db.info.DataBase_Info;
import com.db.info.sql.gswb.T_Sql_Gswb_log;
import com.entity.statistics.Install_collect_gswb;
import com.entity.statistics.New_user_data_gswb;
import com.entity.statistics.Online_collect_gswb;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/**
 * 数据访问层
 * 新用户信息
 * @author ZhuYa
 *
 */
public class NewUserDao {
	
	/**
	 * 查询新用户信息表01: 当日新用户安装量,当日新用户卸载量,当日新用户活跃量,次日活跃用户数
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public List<New_user_data_gswb> getNew_user_data_01(String db_ip,String user,String pwd,String db_name,String tab_name){
		New_user_data_gswb newuser = null;		
		List<New_user_data_gswb> newlist  = null;
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //获取数据库连接
		try{
			if(!conn.isClosed()){
				newlist = new ArrayList<New_user_data_gswb>();
				Statement statement = (Statement) conn.createStatement();
				//查询新用户信息SQL
				String sql = T_Sql_Gswb_log.return_sql_new_user01(tab_name);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					newuser = new New_user_data_gswb();
					newuser.setDateLine(DataBase_Info.rs.getInt("dateline"));								//表时间
					newuser.setTodayNewInstallNum(DataBase_Info.rs.getInt("todayNewInstallNum"));			//当日新用户安装量
					newuser.setTodayNewUninstallNum(DataBase_Info.rs.getInt("todayNewUninstallNum"));		//当日新用户卸载量
					newuser.setTodayNewActiveNum(DataBase_Info.rs.getInt("todayNewActiveNum"));				//当日新用户活跃量
					newuser.setNextDayActiveNum(DataBase_Info.rs.getInt("nextDayActiveNum"));				//次日活跃用户数
					//光速输入法 活跃集合
					newlist.add(newuser);
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
		return newlist;
	}
	
	/**
	 * 查询新用户信息表02: 7日活跃量, 30日活跃量, 30日卸载量
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public List<New_user_data_gswb> getNew_user_data_02(String db_ip,String user,String pwd,String db_name,String tab_name){
		New_user_data_gswb newuser = null;		
		List<New_user_data_gswb> newlist  = null;
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); 		//获取数据库连接
		try{
			if(!conn.isClosed()){
				newlist = new ArrayList<New_user_data_gswb>();
				Statement statement = (Statement) conn.createStatement();
				//查询新用户信息SQL
				String sql = T_Sql_Gswb_log.return_sql_new_user02(tab_name);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					newuser = new New_user_data_gswb();
					newuser.setDateLine(DataBase_Info.rs.getInt("dateline"));					//表时间
					newuser.setActiveNum7Day(DataBase_Info.rs.getInt("7DayActiveNum"));			//7日活跃量
					newuser.setActiveNum30Day(DataBase_Info.rs.getInt("30DayActiveNum"));		//30日活跃量	
					newuser.setUninstallNum30Day(DataBase_Info.rs.getInt("30DayUninstallNum"));	//30日卸载量
					//光速输入法 新用户集合
					newlist.add(newuser);
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
		return newlist;
	}
	
	/**
	 * 光速输入法
	 * 往新用户统计表中插入数据
	 * @param lists
	 * @param tab_name
	 */
	public void insert_Gswb_Log_new_user(List<New_user_data_gswb> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
//		Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-6));
//		Integer tab_next = tab_date-1;    //次日
//		Integer tab_7days = tab_date-7;   //7日数据
//		Integer tab_30days = tab_date-30; //30日数据
		//查询新活跃用户
		String sql = T_Sql_Gswb_log.insert_new_user_gswb;	//插入语句
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //获取数据库连接 
		try{
			if(!conn.isClosed()){
				//活跃表最终统计集合
			    PreparedStatement pstatement = (PreparedStatement) conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
			    conn.setAutoCommit(false);
			    for(int i=0;i<lists.size();i++){
		    			pstatement.setInt(1, lists.get(i).getDateLine());
						pstatement.setInt(2, lists.get(i).getTodayNewInstallNum());
						pstatement.setInt(3, lists.get(i).getTodayNewUninstallNum());
						pstatement.setInt(4, lists.get(i).getTodayNewActiveNum());
						pstatement.setInt(5, lists.get(i).getNextDayActiveNum());
						pstatement.setInt(6, lists.get(i).getActiveNum7Day());
						pstatement.setInt(7, lists.get(i).getActiveNum30Day());
						pstatement.setInt(8, lists.get(i).getUninstallNum30Day());
						pstatement.addBatch();
				}
			    pstatement.executeBatch();
			    conn.commit();  
			    System.out.println("光速输入法新用户信息统计数据添加成功!");
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
