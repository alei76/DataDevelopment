package com.dao.gswb_log.active;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.db.info.DataBase_Info;
import com.db.info.sql.gswb.T_Sql_Gswb_log;
import com.entity.statistics.Active_collect_gswb;
import com.entity.statistics.Install_collect_gswb;
import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/**
 * 数据访问层
 * 安装表
 * @author ZhuYa
 *
 */
public class InstallDao {
	
	/**
	 * 获取光束输入法 安装量
	 * @param db_ip : 连接IP
	 * @param db_name : 数据库名
	 * @param user 	: 用户名
	 * @param pwd	: 密码
	 * @param inputType	: 输入类型  : 0是自动当前日期,1是手动输入日期
	 * @param inputDate : 手动输入的时间
	 * @return
	 */
	public List<Install_collect_gswb> getGswb_Log_Install_01(String db_ip,String user,String pwd,String db_name,String tab_name){
		Install_collect_gswb install_statistics = null;		
		List<Install_collect_gswb> active_gswb_list  = null;
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //获取数据库连接
		try{
			if(!conn.isClosed()){
				active_gswb_list = new ArrayList<Install_collect_gswb>();
				Statement statement = (Statement) conn.createStatement();
				//查询安装表SQL
				String sql = T_Sql_Gswb_log.return_gswb_log_install_01(tab_name);	
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					install_statistics = new Install_collect_gswb();
					install_statistics.setQid(DataBase_Info.rs.getString("QID"));							//渠道
					install_statistics.setAdvertIdentifier(DataBase_Info.rs.getString("AdvertIdentifier")); //截取QID后6位之外的字符
					install_statistics.setAllInstallNum(DataBase_Info.rs.getInt("AllInstallNum"));			//总得安装数量
					install_statistics.setOnlyInstallNum(DataBase_Info.rs.getInt("OnlyInstallNum"));		//去重之后得安装量
					install_statistics.setAddOnlyPlayerNum(DataBase_Info.rs.getInt("addOnlyPlayerNum"));	//新增加得用户量
					install_statistics.setCybercafeInstallNum(DataBase_Info.rs.getInt("cybercafeInstallNum")); 	//网吧安装数量
					//判断是否有字符串
					 try{
						 install_statistics.setAdvertDate(DataBase_Info.rs.getInt("AdvertDate"));
					 }catch(Exception e){
						 install_statistics.setAdvertDate(0);
					 }
					install_statistics.setSoftVer(DataBase_Info.rs.getString("SoftVer"));					//版本
					//光速输入法 活跃集合
					active_gswb_list.add(install_statistics);
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
	 * 获取光速输入法库下的所有表名
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @return
	 */
	public List<Integer> getGswb_Log_Install_tab(String db_ip,String user,String pwd,String db_name){
		Connection con = (Connection) DataBase_Info.getConn(db_ip,user,pwd,db_name);
		List<Integer> lists = new ArrayList<Integer>();
		 try {
			   DatabaseMetaData meta = (DatabaseMetaData) con.getMetaData();
			   ResultSet rs = (ResultSet) meta.getTables(null, null, null,
			     new String[] { "TABLE" });
			   while (rs.next()) {
				   String name = rs.getString(3);
				   String tab_name = rs.getString(3).substring(0,name.length()-8);
				   Integer tab_date = Integer.parseInt(rs.getString(3).substring(name.length()-8,name.length()));
				   if(tab_name.equals("install")){
					   lists.add(tab_date); 
				   }
			   }
			   Collections.reverse(lists);
		   }catch (Exception e) {
			   e.printStackTrace();
		   }finally{
				try {
				    if(DataBase_Info.rs != null){
				    	DataBase_Info.rs.close();   
				    } 
				    if(con != null){
				    	con.close();   
				    } 
				 } catch (Exception ex) {   
					 	ex.printStackTrace();   
				 }
			}
		return lists;
	}
	
	/**
	 * 
	 * 获取31天的总安装量
	 * @param db_ip : 连接IP
	 * @param user	： 用户名
	 * @param pwd	: 密码
	 * @param db_name	: 数据库名
	 * @param tab_name	: 表名
	 * @return
	 */
	public List<Install_collect_gswb> getGswb_Log_Install_30Days(String db_ip,String user,String pwd,String db_name,String tab_name,List<Integer> list_inst){
		//初始化插入类
		Install_collect_gswb install_statistics = null;	
		//初始化集合
		List<Install_collect_gswb> active_gswb_list  = null; 
		//获取数据库连接
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); 
		try{
			if(!conn.isClosed()){
				active_gswb_list = new ArrayList<Install_collect_gswb>();
				Statement statement = (Statement) conn.createStatement();
				//获取表名集合
				List<Integer> tabs = list_inst;
				//遍历所有获取表名31日所有数据
				for(int i=0;i<tabs.size();i++){
					System.out.println(" 表名 "+tabs.get(i));
					//遍历循环每个表名
					tab_name = "install"+tabs.get(i); 
					//查询安装表SQL
					String sql = T_Sql_Gswb_log.return_sql_gswb_all_install30Days(tab_name);
					DataBase_Info.rs = statement.executeQuery(sql);
					while(DataBase_Info.rs.next()) {
						install_statistics = new Install_collect_gswb();
						install_statistics.setQid(DataBase_Info.rs.getString("QID"));					  //渠道
						install_statistics.setSoftVer(DataBase_Info.rs.getString("SoftVer"));			  //版本
						install_statistics.setUid(DataBase_Info.rs.getString("uid"));			  		  //用户标示符
						install_statistics.setTab_date(tabs.get(i));							//每张表的时间
						//光速输入法 活跃集合
						active_gswb_list.add(install_statistics);
					}
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
	 * 往安装统计表中插入数据
	 * @param db_ip
	 * @param user
	 * @param pwd
	 */
	public void insert_Gswb_Log_Install(List<Install_collect_gswb> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
		Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-6));
		//查询新活跃用户
		String sql = T_Sql_Gswb_log.insert_gswb_install;	//插入语句
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //获取数据库连接
		try{
			if(!conn.isClosed()){
				//活跃表最终统计集合
			    PreparedStatement pstatement = (PreparedStatement) conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
			    conn.setAutoCommit(false);
			    for(int i=0;i<lists.size();i++){
						pstatement.setString(1, lists.get(i).getAdvertIdentifier());
						pstatement.setInt(2, lists.get(i).getAllInstallNum());
						pstatement.setInt(3, lists.get(i).getOnlyInstallNum());
						pstatement.setInt(4, lists.get(i).getAddOnlyPlayerNum());
						pstatement.setInt(5, lists.get(i).getCybercafeInstallNum());
						pstatement.setInt(6, lists.get(i).getMonthOnlyInstallNum());
						pstatement.setInt(7, lists.get(i).getMonthAddInstallNum());
						pstatement.setInt(8, lists.get(i).getAdvertDate());
						pstatement.setInt(9, tab_date);
						pstatement.setString(10, lists.get(i).getSoftVer());
						pstatement.addBatch();
				}
			    pstatement.executeBatch();
			    conn.commit();  
			    System.out.println("光速输入法安装表数据添加成功!");
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
