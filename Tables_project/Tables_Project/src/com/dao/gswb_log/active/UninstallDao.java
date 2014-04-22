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
 * ���ݷ��ʲ�
 * ж�ر�
 * @author ZhuYa
 *
 */
public class UninstallDao {
	
	/**
	 * ��ѯж��������
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
		 //��ȡ���ݿ�����
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				active_gswb_list = new ArrayList<Uninstall_collect_gswb>();
				Statement statement = (Statement) conn.createStatement();
				//��ѯ���߱�ͳ�����ݼ���
				String sql = T_Sql_Gswb_log.return_getGswbUninstall(tab_name);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					 activeGswb = new Uninstall_collect_gswb();
					 activeGswb.setAdvertIdentifier(DataBase_Info.rs.getString("advertIdentifier"));
					 activeGswb.setUninstNum(DataBase_Info.rs.getInt("UninstNum"));
					 //�ж��Ƿ����ַ���
					 try{
						 activeGswb.setAdvertDate(DataBase_Info.rs.getInt("advertDate"));
					 }catch(Exception e){
						 activeGswb.setAdvertDate(0);
					 }
					 activeGswb.setSoftVer(DataBase_Info.rs.getString("softVer"));
					 //��ӹ������뷨 ж�ر���
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
	 * �������뷨
	 * ��ж��ͳ�Ʊ��в�������
	 * @param lists
	 * @param tab_name
	 */
	public void insert_Gswb_Log_Uninstall(List<Uninstall_collect_gswb> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
		Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-6));
		//��ѯ�»�Ծ�û�
		String sql = T_Sql_Gswb_log.insert_gswb_uninstall;	//ж�ز������
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //��ȡ���ݿ�����
		try{
			if(!conn.isClosed()){
				//��Ծ������ͳ�Ƽ���
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
			    System.out.println("��������ж��ͳ��������ӳɹ�!");
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
