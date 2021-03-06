package com.dao.gswb_log.active;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.db.info.DataBase_Info;
import com.db.info.sql.gswb.T_Sql_Gswb_log;
import com.entity.statistics.Online_collect_gswb;
import com.entity.statistics.Uninstall_collect_gswb;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/**
 * 数据访问层
 * 卸载表
 * @author ZhuYa
 *
 */
public class UninstallDao {
	
	/**
	 * 查询卸载量集合
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public List<Uninstall_collect_gswb> getGswb_Log_Uninstall(String db_ip,String user,String pwd,String db_name,String tab_name){
		Uninstall_collect_gswb activeGswb = null;		
		List<Uninstall_collect_gswb> active_gswb_list  = null;
		 //获取数据库连接
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				active_gswb_list = new ArrayList<Uninstall_collect_gswb>();
				Statement statement = (Statement) conn.createStatement();
				//查询在线表统计数据集合
				String sql = T_Sql_Gswb_log.return_getGswbUninstall(tab_name);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					 activeGswb = new Uninstall_collect_gswb();
					 activeGswb.setAdvertIdentifier(DataBase_Info.rs.getString("advertIdentifier"));
					 activeGswb.setUninstNum(DataBase_Info.rs.getInt("UninstNum"));
					 //判断是否有字符串
					 try{
						 activeGswb.setAdvertDate(DataBase_Info.rs.getInt("advertDate"));
					 }catch(Exception e){
						 activeGswb.setAdvertDate(0);
					 }
					 activeGswb.setSoftVer(DataBase_Info.rs.getString("softVer"));
					 //添加光速输入法 卸载表集合
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
	 * 往卸载统计表中插入数据
	 * @param lists
	 * @param tab_name
	 */
	public void insert_Gswb_Log_Uninstall(List<Uninstall_collect_gswb> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
		Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-6));
		//查询新活跃用户
		String sql = T_Sql_Gswb_log.insert_gswb_uninstall;	//卸载插入语句
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //获取数据库连接
		try{
			if(!conn.isClosed()){
				//活跃表最终统计集合
			    PreparedStatement pstatement = (PreparedStatement) conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
			    conn.setAutoCommit(false);
			    for(int i=0;i<lists.size();i++){
						pstatement.setString(1, lists.get(i).getAdvertIdentifier());
						pstatement.setInt(2, lists.get(i).getUninstNum());
						pstatement.setInt(3, lists.get(i).getAdvertDate());
						pstatement.setInt(4, tab_date);
						pstatement.setString(5, lists.get(i).getSoftVer());
						pstatement.addBatch();
				}
			    pstatement.executeBatch();
			    conn.commit();  
			    System.out.println("光速输入卸载统计数据添加成功!");
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
