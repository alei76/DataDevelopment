package com.dao.union;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.db.info.DataBase_Info;
import com.db.info.Dictionary_DB;
import com.db.info.sql.union.T_Sql_Promotion_install;
import com.entity.union.Promotion_Install;
import com.entity.union.Promotion_Online;
import com.entity.union.Promotion_Software;
import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/**
 * 装机联盟
 * 安装明细统计
 * @author ZhuYa
 *
 */
public class PromotionInstallDao {
	
	/**
	 * 返回装机联盟安装集合
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param todayDate
	 * @return
	 */
	public List<Promotion_Install> getPromotionInstall(String db_ip,String user,String pwd,String db_name,String todayDate){
		List<Promotion_Install> lists = null;
		Promotion_Install obj = null;
		 //获取数据库连接
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		
		try{
			if(!conn.isClosed()){
				conn.setAutoCommit(false);
				lists = new ArrayList<Promotion_Install>(); //返回结果集合
				Statement statement = (Statement) conn.createStatement();
				//查询所有渠道汇总统计
				String sql = T_Sql_Promotion_install.return_getPromotionInstall(todayDate);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					obj = new Promotion_Install();
					obj.setUid(DataBase_Info.rs.getInt("uid"));						 //渠道类型
					obj.setNum(DataBase_Info.rs.getInt("num"));						 //数量
					obj.setSoftware_id(DataBase_Info.rs.getString("SoftID"));		 //版本ID
					obj.setSoftware_version(DataBase_Info.rs.getString("SoftVer"));	 //版本
					obj.setDateline(DataBase_Info.rs.getInt("dateline"));	 	 	 //时间
					obj.setPc_id(DataBase_Info.rs.getString("pc_id"));	 	 	 	 //PC终端
					obj.setId(DataBase_Info.rs.getInt("insId"));					 //清单表ID
					lists.add(obj);
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
	 * 获取光速输入法库下的所有表名
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @return
	 */
	public List<Integer> getGswb_Log_Tab_Title(String db_ip,String user,String pwd,String db_name,String table_name){
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
				   if(tab_name.equals(table_name)){
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
	 * 返回装机联盟前15天在线使用集合
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param inputType	: 是否手动输入
	 * @param inputDate : 输入日期
	 * @param 前15天表集合
	 */
	public List<Promotion_Online> getPromotionOnline(String db_ip,String user,String pwd,String db_name,int inputType,int inputDate,List<Integer> tabList){
		List<Promotion_Online> lists = null;
		Promotion_Online obj = null;
		 //获取数据库连接
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
				if(!conn.isClosed()){
					lists = new ArrayList<Promotion_Online>(); //返回结果集合
					//查询所有渠道汇总统计
					String sql = T_Sql_Promotion_install.return_getPromotionOnline.toString();
//					String sql = T_Sql_Promotion_install.return_getPromotionOnline(inputType, inputDate);
					PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
					if(tabList.size() > 0){
						for(int i = 0; i < tabList.size(); i++){
							statement.setInt(1, tabList.get(i));
							DataBase_Info.rs = statement.executeQuery();
							while(DataBase_Info.rs.next()) {
								obj = new Promotion_Online();
								obj.setUid(DataBase_Info.rs.getInt("uid"));						 		 	 //渠道类型
								obj.setSoftware_id(DataBase_Info.rs.getString("softID"));			 //软件id
								obj.setSoftware_version(DataBase_Info.rs.getString("softVer"));	 //软件版本
								obj.setPc_id(DataBase_Info.rs.getString("pc_id"));	 		 	 		 //版本 
								obj.setDateline(tabList.get(i));					 									//时间
								obj.setId(DataBase_Info.rs.getInt("onlineId"));								//清单表ID
								lists.add(obj);
							}
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
		return lists;
	}

	/**
	 * 旧需求
	 * 获取装机联盟连续在线使用3天的用户
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param inputType	: 是否手动输入
	 * @param inputDate : 输入日期
	 * @param 前15天表集合
	 */
	public List<Promotion_Online> getPromotionOnlineOld(String db_ip,String user,String pwd,String db_name,int inputType,int inputDate){
		List<Promotion_Online> lists = null;
		Promotion_Online obj = null;
		 //获取数据库连接
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
				if(!conn.isClosed()){
					lists = new ArrayList<Promotion_Online>(); //返回结果集合
					//查询所有渠道汇总统计
//					String sql = T_Sql_Promotion_install.return_getPromotionOnline.toString();
					String sql = T_Sql_Promotion_install.return_getPromotionOnlineOld(inputType, inputDate);
//					PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
					Statement statement = (Statement) conn.createStatement();
					DataBase_Info.rs = statement.executeQuery(sql);
					while(DataBase_Info.rs.next()) {
						obj = new Promotion_Online();
						obj.setUid(DataBase_Info.rs.getInt("uid"));						 		 	 //渠道类型
						obj.setSoftware_id(DataBase_Info.rs.getString("softID"));			 //软件id
						obj.setSoftware_version(DataBase_Info.rs.getString("softVer"));	 //软件版本
						obj.setPc_id(DataBase_Info.rs.getString("pc_id"));	 		 	 		 //版本 
						obj.setDateline(inputDate);					 										//时间
						obj.setId(DataBase_Info.rs.getInt("onlineId"));								//清单表ID
						lists.add(obj);
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
	 * 旧需求备份
	 * 插入装机联盟在线使用 集合
	 * @param lists
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 */
	public void insert_PromotionOnline_BAK(List<Promotion_Online> lists,String db_ip,String user,String pwd,String db_name){
		//查询新活跃用户
		String sql = T_Sql_Promotion_install.insert_PromotionOnline;	//插入语句
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //获取数据库连接
		try{
			if(!conn.isClosed()){
				//活跃表最终统计集合
			    PreparedStatement pstatement = (PreparedStatement) conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
			    conn.setAutoCommit(false);
			    for(int i=0;i<lists.size();i++){
						pstatement.setInt(1, lists.get(i).getUid());
						pstatement.setString(2, lists.get(i).getSoftware_id());
						pstatement.setString(3, lists.get(i).getSoftware_version());
						pstatement.setInt(4, lists.get(i).getDateline());
						pstatement.setString(5, lists.get(i).getPc_id());
						pstatement.setInt(6, lists.get(i).getId());
						pstatement.addBatch();
				}
			    pstatement.executeBatch();
			    conn.commit();  
			    System.out.println("装机联盟在线使用统计数据添加成功!");
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
	 * 插入装机联盟 有效安装用户数
	 * @param lists
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 */
	public void insert_PromotionOnline(List<Promotion_Install> lists,String db_ip,String user,String pwd,String db_name){
		//插入有效安装用户数
		String sql = T_Sql_Promotion_install.insert_PromotionEffective;	
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //获取数据库连接
		try{
			if(!conn.isClosed()){
				//活跃表最终统计集合
			    PreparedStatement pstatement = (PreparedStatement) conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
			    conn.setAutoCommit(false);
			    for(int i=0;i<lists.size();i++){
						pstatement.setString(1, lists.get(i).getUserId());
						pstatement.setInt(2, lists.get(i).getNum());
						pstatement.setInt(3, lists.get(i).getDateline());
						pstatement.setString(4, "gswb");
						pstatement.addBatch();
				}
			    pstatement.executeBatch();
			    conn.commit();  
			    System.out.println("装机联盟有效安装用户数统计数据添加成功!");
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
	 * 获取15天 127库 PromotionInstall 数据
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param inputDate
	 * @return
	 */
	public List<Promotion_Install> getUnionPromotionInstall(String db_ip,String user,String pwd,String db_name,String inputDate){
		List<Promotion_Install> lists = null;
		Promotion_Install obj = null;
		 //获取数据库连接
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
				if(!conn.isClosed()){
						lists = new ArrayList<Promotion_Install>(); //返回结果集合
						//查询15天 装机联盟安装量
						String sql = T_Sql_Promotion_install.return_getUnionPromotionInstall;
						PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
						statement.setString(1, inputDate);
						statement.setString(2, inputDate);
						DataBase_Info.rs = statement.executeQuery();
						while(DataBase_Info.rs.next()) {
							obj = new Promotion_Install();
							obj.setUid(DataBase_Info.rs.getInt("uid"));						 		 	 		//渠道类型
							obj.setSoftware_id(DataBase_Info.rs.getString("software_id"));			//软件id
							obj.setSoftware_version(DataBase_Info.rs.getString("software_version"));	 //软件版本
							obj.setPc_id(DataBase_Info.rs.getString("pc_id"));	 		 	 		 //版本 
							obj.setDateline(DataBase_Info.rs.getInt("dateline"));					 //时间
							obj.setId(DataBase_Info.rs.getInt("id"));										//安装id
							lists.add(obj);
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
	 * 插入装机联盟安装明细 集合
	 * @param lists
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 */
	public void insert_PromotionInstall(List<Promotion_Install> lists,String db_ip,String user,String pwd,String db_name){
		//查询新活跃用户
		String sql = T_Sql_Promotion_install.insert_PromotionInstall;	//插入语句
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //获取数据库连接
		try{
			if(!conn.isClosed()){
				conn.setAutoCommit(false);
				//活跃表最终统计集合
			    PreparedStatement pstatement = (PreparedStatement) conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
			    for(int i=0;i<lists.size();i++){
						pstatement.setInt(1, lists.get(i).getUid());
						pstatement.setString(2, lists.get(i).getSoftware_id());
						pstatement.setString(3, lists.get(i).getSoftware_version());
						pstatement.setInt(4, lists.get(i).getNum());
						pstatement.setInt(5, lists.get(i).getDateline());
						pstatement.setString(6, lists.get(i).getPc_id());
						pstatement.setInt(7, lists.get(i).getId());
						pstatement.addBatch();
				}
			    pstatement.executeBatch();
			    conn.commit();
			    System.out.println("装机联盟明细统计数据添加成功!");
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
	 * 返回前30天数据:输入法安装log数据
	 * @param db_ip	: 43库
	 * @param user
	 * @param pwd
	 * @param db_name	: gswb_log
	 * @param inputType	: 是否手动输入
	 * @param inputDate : 输入日期
	 * @param inputDate : tabTitle : 30天install表头集合
	 */
	public List<Promotion_Install> getGswbInstallLog(String db_ip, String user, String pwd, String db_name, Integer tab_date){
		List<Promotion_Install> lists = null;
		Promotion_Install obj = null;
		 //获取数据库连接
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
					//查询30天光速输入法安装
					String sql = T_Sql_Promotion_install.return_getGswbLog;
					PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
					lists = new ArrayList<Promotion_Install>(); //返回结果集合
						statement.setInt(1, tab_date);
						DataBase_Info.rs = statement.executeQuery();
						while(DataBase_Info.rs.next()) {
							obj = new Promotion_Install();
							obj.setSoftware_id(DataBase_Info.rs.getString("SoftID"));		//软件版本ID
							obj.setSoftware_version(DataBase_Info.rs.getString("SoftVer")); //软件版本
							obj.setPc_id(DataBase_Info.rs.getString("pc_id"));				//PC终端
							lists.add(obj);
						}
						System.out.println("光速输入法安装log: "+tab_date+" 完成! ");
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
	 * 返回前30天数据,输入法在线log数据
	 * @param db_ip		: 43库
	 * @param user
	 * @param pwd
	 * @param db_name	: gswb_log
	 * @param tabTitle	: 30天的在线使用
	 * @param inputType ：是否手动输入
	 * @param inputDate : 输入时间
	 * @return
	 */
	public List<Promotion_Install> getGswbOnlineLog(String db_ip,String user,String pwd,String db_name,Integer tabTitle,int inputType){
		List<Promotion_Install> lists = null;
		Promotion_Install obj = null;
		 //获取数据库连接
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
					//查询30天光速输入法在线使用
					String sql = T_Sql_Promotion_install.return_getGswbOnline;
					PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
					lists = new ArrayList<Promotion_Install>(); //返回结果集合
					statement.setInt(1, tabTitle);	//赋值统计时间
					DataBase_Info.rs = statement.executeQuery();
					while(DataBase_Info.rs.next()) {
						obj = new Promotion_Install();
						obj.setSoftware_id(DataBase_Info.rs.getString("SoftID"));		//软件版本ID
						obj.setSoftware_version(DataBase_Info.rs.getString("SoftVer")); //软件版本
						obj.setPc_id(DataBase_Info.rs.getString("pc_id"));				//PC终端
						lists.add(obj);
					}
					System.out.println("光速输入法在线log: "+tabTitle+" 完成! ");
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
	 * 统计好123IP地址量
	 * @param db_ip	:	127库
	 * @param user	
	 * @param pwd
	 * @param db_name	: union
	 * @param inputType	: 统计时间
	 * @return
	 */
	public List<Promotion_Software> getHao123(String db_ip,String user,String pwd,String db_name,int inputDate){
		Promotion_Software obj = null;
		List<Promotion_Software> lists = null;
		 //获取数据库连接
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				lists = new ArrayList<Promotion_Software>();
				//查询30天光速输入法,连续3天在线使用
				String sql = T_Sql_Promotion_install.return_getHao123;
				PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
				statement.setInt(1, inputDate);	//赋值统计时间
				DataBase_Info.rs = statement.executeQuery();
				while(DataBase_Info.rs.next()) {
					obj = new Promotion_Software();
					obj.setPcNum(DataBase_Info.rs.getInt("pcNum"));		//推广技术员(以账号为单位)数
					obj.setUseNum(DataBase_Info.rs.getInt("useNum")); 	//使用用户数
					obj.setTime(DataBase_Info.rs.getInt("dateline"));	//时间
					obj.setCredit(DataBase_Info.rs.getInt("credit"));	//积分
					obj.setSoftName(DataBase_Info.rs.getString("name"));//软件名称
					lists.add(obj);
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
	 * 有效用户安装量 : 月度唯一增加量 - 网吧安装量
	 *  获取月度唯一增加量
	 * @param db_ip : 43库
	 * @param user
	 * @param pwd
	 * @param db_name : statistics
	 * @param inputDate
	 * @return
	 */
	public List<Promotion_Install> getInstallOkGswb1 (String db_ip,String user,String pwd,String db_name,String inputDate){
		int tab_date = Integer.parseInt(inputDate.substring(inputDate.length()-6)); // 截取时间格式 : 140421
		Promotion_Install obj = null;
		List<Promotion_Install> lists = null;
		 //获取数据库连接
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				lists = new ArrayList<Promotion_Install>();
				//查询月度唯一增加量
				String sql = T_Sql_Promotion_install.return_getInstallOkGswb;
				PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
				statement.setInt(1, tab_date);	//赋值统计时间
				DataBase_Info.rs = statement.executeQuery();
				while(DataBase_Info.rs.next()) {
					obj = new Promotion_Install();
					obj.setUserId(DataBase_Info.rs.getString("uid"));		//装机联盟账号ID
					obj.setNum(DataBase_Info.rs.getInt("uidNum"));	//月度唯一增加量
					lists.add(obj);
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
		 * 有效用户安装量 : 月度唯一增加量 - 网吧安装量
		 * 获取网吧安装量
		 * @param db_ip : 43库
		 * @param user
		 * @param pwd
		 * @param db_name :  gswb_log
		 * @param inputDate
		 * @return
		 */
		public List<Promotion_Install> getInstallWbGswb2 (String db_ip,String user,String pwd,String db_name,String inputDate){
			int tab_date = Integer.parseInt(inputDate); 
			Promotion_Install obj = null;
			List<Promotion_Install> lists = null;
			 //获取数据库连接
			Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
			try{
				if(!conn.isClosed()){
					lists = new ArrayList<Promotion_Install>();
					//查询 光速输入法网吧安装量
					String sql = T_Sql_Promotion_install.return_getInstallWbGswb();
					PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
					statement.setInt(1, tab_date);	//赋值统计时间
					DataBase_Info.rs = statement.executeQuery();
					while(DataBase_Info.rs.next()) {
						obj = new Promotion_Install();
						obj.setUserId(DataBase_Info.rs.getString("uid"));				//装机联盟账号ID
						obj.setNum(DataBase_Info.rs.getInt("uidNum"));	//月度唯一增加量
						lists.add(obj);
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
		 * 获取装机联盟账号ID
		 * @param db_ip : 127库
		 * @param user
		 * @param pwd
		 * @param db_name :  union
		 * @param inputDate
		 * @return
		 */
		public List<Integer> getUserId3 (String db_ip,String user,String pwd,String db_name){
				List<Integer> lists = null;
				 //获取数据库连接
				Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
				try{
						if(!conn.isClosed()){
							lists = new ArrayList<Integer>();
							//查询 光速输入法网吧安装量
							String sql = T_Sql_Promotion_install.return_getUserId;
							PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
							DataBase_Info.rs = statement.executeQuery();
							while(DataBase_Info.rs.next()) {
								lists.add(DataBase_Info.rs.getInt("id"));
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
}
