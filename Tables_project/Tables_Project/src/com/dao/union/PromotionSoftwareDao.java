package com.dao.union;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.db.info.DataBase_Info;
import com.db.info.sql.union.T_Sql_Promotion_install;
import com.entity.union.Promotion_Install;
import com.entity.union.Promotion_Software;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/**
 * 装机联盟产品数据(渠道数据)
 * @author ZhuYa
 *
 */
public class PromotionSoftwareDao {
	
	/**
	 * 返回软件数据集合
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public List<Promotion_Software> getPromotionSoftware(String db_ip,String user,String pwd,String db_name,String tab_name){
		List<Promotion_Software> objList = null;
		 //获取数据库连接
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				Promotion_Software obj = null;
				objList = new ArrayList<Promotion_Software>();
				Statement statement = (Statement) conn.createStatement();
				//查询所有渠道汇总统计
				String sql = T_Sql_Promotion_install.return_getPromotionSoftware(tab_name);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					obj = new Promotion_Software();
					obj.setSoftName(DataBase_Info.rs.getString("softName"));	//渠道明细(各种产品)
					obj.setPcNum(DataBase_Info.rs.getInt("pcNum"));				//推广技术员(以电脑为单位)数
					obj.setUseNum(DataBase_Info.rs.getInt("useNum"));			//使用用户数
					obj.setTime(DataBase_Info.rs.getInt("dateline"));				//时间
					
					objList.add(obj);
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
		return objList;
	}
	
	/**
	 * 插入 装机联盟软件数据集合
	 * @param lists
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 */
	public void insert_PromotionSoftware(List<Promotion_Software> lists,String db_ip,String user,String pwd,String db_name){
		//查询新活跃用户
		String sql = T_Sql_Promotion_install.insert_PromotionSoft;	//插入语句
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //获取数据库连接
		try{
			if(!conn.isClosed()){
				//活跃表最终统计集合
			    PreparedStatement pstatement = (PreparedStatement) conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
			    conn.setAutoCommit(false);
			    for(int i=0;i<lists.size();i++){
						pstatement.setString(1, lists.get(i).getSoftName());
						pstatement.setInt(2, lists.get(i).getPcNum());
						pstatement.setInt(3, lists.get(i).getUseNum());
						pstatement.setInt(4, lists.get(i).getTime());
						pstatement.setInt(5, lists.get(i).getCredit());
						pstatement.addBatch();
				}
			    pstatement.executeBatch();
			    conn.commit();  
			    System.out.println("装机联盟软件数据添加成功!");
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
					obj.setTime(DataBase_Info.rs.getInt("ymd"));	//时间
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
}
