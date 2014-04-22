package com.dao.gswb_log.qd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.db.info.DataBase_Info;
import com.db.info.sql.gswb.T_Sql_Gswb_log;
import com.db.info.sql.qd_gswb.T_Sql_Gswb_Collect_Qd;
import com.db.info.sql.union.T_Sql_Promotion_install;
import com.entity.statistics.Active_collect_gswb;
import com.entity.statistics.Gswb_collect_qd;
import com.entity.statistics.Online_collect_gswb;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/**
 * 光速输入法
 * 渠道汇总统计
 * @author ZhuYa
 *
 */
public class GswbCollectQdDao {
	
	/**
	 * 渠道汇总统计
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @param qdType
	 * @param qd_name
	 * @return
	 */
	public Gswb_collect_qd getGswb_Collect_Qd(String db_ip,String user,String pwd,String db_name,String tab_name,String qdType,String qd_name){
		Gswb_collect_qd gswbQd = null;	
		 //获取数据库连接
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				Statement statement = (Statement) conn.createStatement();
				//查询所有渠道汇总统计
				String sql = T_Sql_Gswb_Collect_Qd.return_getGswbAllQd(tab_name, qdType, qd_name);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					gswbQd = new Gswb_collect_qd();
					gswbQd.setQdType(DataBase_Info.rs.getString("qdType"));						 //渠道类型
					gswbQd.setServiceStartNum(DataBase_Info.rs.getInt("serviceStartNum"));		 //当日服务启动量	
					gswbQd.setActiveNum(DataBase_Info.rs.getInt("activeNum"));					 //当日活跃用户量
					gswbQd.setOnlyInstallNum(DataBase_Info.rs.getInt("onlyInstallNum"));		 //当日安装量(去重)
					gswbQd.setMonthAddInstallNum(DataBase_Info.rs.getInt("monthAddInstallNum")); //当日按月增加
					gswbQd.setInputLV1Num(DataBase_Info.rs.getInt("inputLV1Num"));				 //当日使用(1-10)
					gswbQd.setInputLV2Num(DataBase_Info.rs.getInt("inputLV2Num"));				 //当日使用(1-140)
					gswbQd.setInputLV3Num(DataBase_Info.rs.getInt("inputLV3Num"));				 //当日使用(140+)
					gswbQd.setUninstNum(DataBase_Info.rs.getInt("uninstNum"));					 //当日用户卸载量
					gswbQd.setActiveNum7Days(DataBase_Info.rs.getInt("activeNum7Days"));		 //7日存活量
					gswbQd.setInstallNum(DataBase_Info.rs.getInt("installNum"));			 	 //当日安装量(没有去重)
				}
				System.out.println("渠道"+qdType+"汇总统计查询完毕");
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
		return gswbQd;
	}
	
	/**
	 * 光速输入法 :安装表/活跃表
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @param qdType
	 * @param qd_name
	 * @return
	 */
	public List<Gswb_collect_qd> getGswbQdInstall_01(String db_ip,String user,String pwd,String db_name,String tab_name,String sql){
		Gswb_collect_qd gswbQd = null;	
		List<Gswb_collect_qd> list = null;
		 //获取数据库连接
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				list = new ArrayList<Gswb_collect_qd>();
				Statement statement = (Statement) conn.createStatement();
				//查询安装表/活跃表
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					gswbQd = new Gswb_collect_qd();
					gswbQd.setQid(DataBase_Info.rs.getString("qid"));	//渠道类型
					gswbQd.setUid(DataBase_Info.rs.getString("uid"));	//活跃数量 : 安装用户在1度2度3度下的用户数
					list.add(gswbQd);
				}
				System.out.println("安装/活跃表数据成功返回");
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
		return list;
	}
	
	/**
	 * 二级渠道:用户活跃量：安装用户数在1度2度3度的用户数
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @param qdType
	 * @param qd_name
	 * @return
	 */
	public List<Gswb_collect_qd> getGswbQdInsActive02(String db_ip,String user,String pwd,String db_name,String tab_name,String qdType,String qd_name){
		Gswb_collect_qd gswbQd = null;	
		List<Gswb_collect_qd> lists = null;
		 //获取数据库连接
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				lists = new ArrayList<Gswb_collect_qd>();
				Statement statement = (Statement) conn.createStatement();
				//查询安装用户在1度2度3度下的用户数
				String sql = T_Sql_Gswb_Collect_Qd.return_getGswbQdInsActive_02(tab_name, qd_name, qdType);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					gswbQd = new Gswb_collect_qd();
					gswbQd.setQdType(DataBase_Info.rs.getString("qdType"));	 //渠道类型
					gswbQd.setQid(DataBase_Info.rs.getString("qid"));		 //渠道名称
					gswbQd.setActiveInputNum(DataBase_Info.rs.getInt("num"));//活跃数量 : 安装用户在1度2度3度下的用户数
					
					lists.add(gswbQd);
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
		return lists;
	}
	
	/**
	 * 二级渠道汇总统计
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @param qdType
	 * @param qd_name
	 * @return
	 */
	public List<Gswb_collect_qd> getGswb_Collect_Qd_02(String db_ip,String user,String pwd,String db_name,String tab_name,String qdType,String qd_name){
		Gswb_collect_qd gswbQd = null;		
		List<Gswb_collect_qd> lists = null;
		 //获取数据库连接
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				lists = new ArrayList<Gswb_collect_qd>();
				Statement statement = (Statement) conn.createStatement();
				//查询所有渠道汇总统计
				String sql = T_Sql_Gswb_Collect_Qd.return_getGswbAllQd_02(tab_name, qdType, qd_name);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					gswbQd = new Gswb_collect_qd();
					gswbQd.setQdType(DataBase_Info.rs.getString("qdType"));						 //渠道类型
					gswbQd.setQid(DataBase_Info.rs.getString("QID"));							 //渠道
					gswbQd.setServiceStartNum(DataBase_Info.rs.getInt("serviceStartNum"));		 //当日服务启动量	
					gswbQd.setActiveNum(DataBase_Info.rs.getInt("activeNum"));					 //当日活跃用户量
					gswbQd.setOnlyInstallNum(DataBase_Info.rs.getInt("onlyInstallNum"));		 //当日安装量(去重)
					gswbQd.setMonthAddInstallNum(DataBase_Info.rs.getInt("monthAddInstallNum")); //当日按月增加
					gswbQd.setInputLV1Num(DataBase_Info.rs.getInt("inputLV1Num"));				 //当日使用(1-10)
					gswbQd.setInputLV2Num(DataBase_Info.rs.getInt("inputLV2Num"));				 //当日使用(1-140)
					gswbQd.setInputLV3Num(DataBase_Info.rs.getInt("inputLV3Num"));				 //当日使用(140+)
					gswbQd.setUninstNum(DataBase_Info.rs.getInt("uninstNum"));					 //当日用户卸载量
					gswbQd.setActiveNum7Days(DataBase_Info.rs.getInt("activeNum7Days"));		 //7日存活量
					gswbQd.setInstallNum(DataBase_Info.rs.getInt("installNum"));			 	 //当日安装量(没有去重)
					
					lists.add(gswbQd);
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
		return lists;
	}
	
	/**
	 * 三级渠道汇部分总统计
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @param qdType
	 * @param qd_name
	 * @return
	 */
	public List<Gswb_collect_qd> getPromotionNode3(String db_ip,String user,String pwd,String db_name,String tab_name){
		Gswb_collect_qd gswbQd = null;		
		List<Gswb_collect_qd> lists = null;
		 //获取数据库连接
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				lists = new ArrayList<Gswb_collect_qd>();
				Statement statement = (Statement) conn.createStatement();
				//查询所有渠道汇总统计
				String sql = T_Sql_Gswb_Collect_Qd.return_getPromotionNode3(tab_name);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					gswbQd = new Gswb_collect_qd();
					gswbQd.setQdType(DataBase_Info.rs.getString("qdType"));						 //渠道类型
					gswbQd.setQid(DataBase_Info.rs.getString("AdvertIdentifier"));				 //渠道
					gswbQd.setServiceStartNum(DataBase_Info.rs.getInt("serviceStartNum"));		 //当日服务启动量	
					gswbQd.setActiveNum(DataBase_Info.rs.getInt("activeNum"));					 //当日活跃用户量
					gswbQd.setOnlyInstallNum(DataBase_Info.rs.getInt("onlyInstallNum"));		 //当日安装量(去重)
					gswbQd.setMonthAddInstallNum(DataBase_Info.rs.getInt("monthAddInstallNum")); //当日按月增加
					gswbQd.setInputLV1Num(DataBase_Info.rs.getInt("inputLV1Num"));				 //当日使用(1-10)
					gswbQd.setInputLV2Num(DataBase_Info.rs.getInt("inputLV2Num"));				 //当日使用(1-140)
					gswbQd.setInputLV3Num(DataBase_Info.rs.getInt("inputLV3Num"));				 //当日使用(140+)
					gswbQd.setUninstNum(DataBase_Info.rs.getInt("uninstNum"));					 //当日用户卸载量
					gswbQd.setActiveNum7Days(DataBase_Info.rs.getInt("activeNum7Days"));		 //7日存活量
					gswbQd.setInstallNum(DataBase_Info.rs.getInt("installNum"));			 	 //当日安装量(没有去重)
					
					lists.add(gswbQd);
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
		return lists;
	}
	
	/**
	 * 光速输入法
	 * 插入渠道汇总统计
	 * @param lists
	 * @param tab_name
	 */
	public void insert_Gswb_Qd(List<Gswb_collect_qd> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
		Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-6));
		//查询新活跃用户
		String sql = T_Sql_Gswb_Collect_Qd.insert_gswb_qd;	//插入语句
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //获取数据库连接
		try{
			if(!conn.isClosed()){
				//活跃表最终统计集合
			    PreparedStatement pstatement = (PreparedStatement) conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
			    conn.setAutoCommit(false);
			    for(int i=0;i<lists.size();i++){
						pstatement.setString(1, lists.get(i).getQdType());
						pstatement.setInt(2, lists.get(i).getServiceStartNum());
						pstatement.setInt(3, lists.get(i).getActiveNum());
						pstatement.setInt(4, lists.get(i).getOnlyInstallNum());
						pstatement.setInt(5, lists.get(i).getMonthAddInstallNum());
						pstatement.setInt(6, lists.get(i).getInputLV1Num());
						pstatement.setInt(7, lists.get(i).getInputLV2Num());
						pstatement.setInt(8, lists.get(i).getInputLV3Num());
						pstatement.setInt(9, 0);
						pstatement.setInt(10, lists.get(i).getUninstNum());
						pstatement.setInt(11, tab_date);
						pstatement.setInt(12, lists.get(i).getInstallNum());
						pstatement.addBatch();
				}
			    pstatement.executeBatch();
			    conn.commit();  
			    System.out.println("光速输入法渠道汇总统计数据添加成功!");
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
	 * 光速输入法
	 * 插入二级渠道汇总统计
	 * @param lists
	 * @param tab_name
	 */
	public void insert_Gswb_Qd_02(Map<String,List<Gswb_collect_qd>> map,String db_ip,String user,String pwd,String db_name,String tab_name){
		Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-6));
		//查询新活跃用户
		String sql = T_Sql_Gswb_Collect_Qd.insert_gswb_qd_02;	//插入语句
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //获取数据库连接
		try{
			if(!conn.isClosed()){
				//活跃表最终统计集合
			    PreparedStatement pstatement = (PreparedStatement) conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
			    conn.setAutoCommit(false);
			    if(map != null){
			    	for (String key : map.keySet()) {
			    		List<Gswb_collect_qd> lists= map.get(key);
			    		if(lists != null){
			    			for(int i=0;i<lists.size();i++){
								pstatement.setString(1, lists.get(i).getQdType());
								pstatement.setString(2, lists.get(i).getQid());
								pstatement.setInt(3, lists.get(i).getServiceStartNum());
								pstatement.setInt(4, lists.get(i).getActiveNum());
								pstatement.setInt(5, lists.get(i).getOnlyInstallNum());
								pstatement.setInt(6, lists.get(i).getMonthAddInstallNum());
								pstatement.setInt(7, lists.get(i).getInputLV1Num());
								pstatement.setInt(8, lists.get(i).getInputLV2Num());
								pstatement.setInt(9, lists.get(i).getInputLV3Num());
								pstatement.setInt(10, 0);
								pstatement.setInt(11, lists.get(i).getUninstNum());
								pstatement.setInt(12, tab_date);
								pstatement.setInt(13, lists.get(i).getInstallNum());
								pstatement.addBatch();
			    			}
			    		}
			    	}
			    }
			    
			    pstatement.executeBatch();
			    conn.commit();  
			    System.out.println("光速输入法二级渠道汇总统计数据添加成功!");
			    
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
	 * 插入三级渠道
	 * @param map : map数据集
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 */
	public void insert_Gswb_Qd_03(List<Gswb_collect_qd> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
		Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-6));
		//查询新活跃用户
		String sql = T_Sql_Gswb_Collect_Qd.insert_gswb_qd_03;	//插入语句
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //获取数据库连接
		try{
			if(!conn.isClosed()){
				//活跃表最终统计集合
			    PreparedStatement pstatement = (PreparedStatement) conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
			    conn.setAutoCommit(false);
	    		if(lists != null){
	    			for(int i=0;i<lists.size();i++){
						pstatement.setString(1, lists.get(i).getQdType());
						pstatement.setString(2, lists.get(i).getQid());
						pstatement.setInt(3, lists.get(i).getServiceStartNum());
						pstatement.setInt(4, lists.get(i).getActiveNum());
						pstatement.setInt(5, lists.get(i).getOnlyInstallNum());
						pstatement.setInt(6, lists.get(i).getMonthAddInstallNum());
						pstatement.setInt(7, lists.get(i).getInputLV1Num());
						pstatement.setInt(8, lists.get(i).getInputLV2Num());
						pstatement.setInt(9, lists.get(i).getInputLV3Num());
						pstatement.setInt(10, 0);
						pstatement.setInt(11, lists.get(i).getUninstNum());
						pstatement.setInt(12, tab_date);
						pstatement.setInt(13, lists.get(i).getInstallNum());
						pstatement.addBatch();
	    			}
	    		}
			    pstatement.executeBatch();
			    conn.commit();  
			    System.out.println("光速输入法三级渠道汇总统计数据添加成功!");
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
	 * 光速输入法大渠道7日存活量更新
	 * @param lists
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tabName
	 * @param tab_beforeDate
	 * @param num7Day
	 */
	public void update_Gswb_Qd(List<Gswb_collect_qd> lists,String db_ip,String user,String pwd,String db_name,String tab_beforeDate){
		//截取时间
		Integer tab_date = Integer.parseInt(tab_beforeDate.substring(tab_beforeDate.length()-6));
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //获取数据库连接
		try{
			if(!conn.isClosed()){
				PreparedStatement pstatement = null;
			    conn.setAutoCommit(false);
			    String sql = T_Sql_Gswb_Collect_Qd.update_gswb_qd();
			    if(lists != null && lists.size() > 0){
			    	//活跃表最终统计集合
				    pstatement = (PreparedStatement) conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			    	for(int i = 0;i<lists.size();i++){
			    		pstatement.setInt(1, lists.get(i).getActiveNum7Days());
			    		pstatement.setInt(2, tab_date);
			    		pstatement.setString(3, lists.get(i).getQdType());
			    		pstatement.addBatch();
			    	}
			    }
			    pstatement.executeBatch();
			    conn.commit();  
			    System.out.println("光速输入法大渠道批量更新成功!");
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
	 * 光速输入法2级渠道7日存活量更新
	 * @param lists
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tabName
	 * @param tab_beforeDate
	 */
	public void update_Gswb_Qd02(Map<String,List<Gswb_collect_qd>> mapLists,String db_ip,String user,String pwd,String db_name,String tab_beforeDate){
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //获取数据库连接
		Integer tab_date = Integer.parseInt(tab_beforeDate.substring(tab_beforeDate.length()-6));
		try{
			if(!conn.isClosed()){
				PreparedStatement pstatement = null;
			    conn.setAutoCommit(false);
			    String sql = T_Sql_Gswb_Collect_Qd.update_gswb_qd02();
			    if(mapLists != null){
			    	for (String key : mapLists.keySet()) {
			    		List<Gswb_collect_qd> lists= mapLists.get(key);
					    if(lists != null && lists.size() > 0){
				    		//活跃表最终统计集合
						    pstatement = (PreparedStatement) conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
					    	for(int i = 0;i<lists.size();i++){
					    		pstatement.setInt(1, lists.get(i).getActiveNum7Days());
					    		pstatement.setInt(2, tab_date);
					    		pstatement.setString(3, lists.get(i).getQdType());
					    		pstatement.setString(4, lists.get(i).getQid());
					    		pstatement.addBatch();
					    	}
					    }
			    	}
			    	//更新
				    pstatement.executeUpdate();
				    pstatement.addBatch();
				    
			    }
			    pstatement.executeBatch();
			    conn.commit();  
			    System.out.println("光速输入法二级渠道批量更新成功!");
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
	 * 光速输入法3级渠道7日存活量更新
	 * @param lists
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tabName
	 * @param tab_beforeDate
	 */
	public void update_Gswb_Qd03(List<Gswb_collect_qd> lists,String db_ip,String user,String pwd,String db_name,String tab_beforeDate){
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //获取数据库连接
		Integer tab_date = Integer.parseInt(tab_beforeDate.substring(tab_beforeDate.length()-6));
		try{
			if(!conn.isClosed()){
				PreparedStatement pstatement = null;
			    conn.setAutoCommit(false);
			    if(lists != null && lists.size() > 0){
			    	String sql = T_Sql_Gswb_Collect_Qd.update_gswb_qd03();
				    pstatement = (PreparedStatement) conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			    	for(int i = 0;i<lists.size();i++){
			    		pstatement.setInt(1, lists.get(i).getActiveNum7Days());
			    		pstatement.setInt(2, tab_date);
			    		pstatement.setString(3, lists.get(i).getQdType());
			    		pstatement.setString(4, lists.get(i).getQid());
			    		pstatement.addBatch();
			    	}
			    }
			    pstatement.executeBatch();
			    conn.commit();  
			    System.out.println("光速输入法三级渠道批量更新成功!");
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
