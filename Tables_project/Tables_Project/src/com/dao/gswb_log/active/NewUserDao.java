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
 * ���ݷ��ʲ�
 * ���û���Ϣ
 * @author ZhuYa
 *
 */
public class NewUserDao {
	
	/**
	 * ��ѯ���û���Ϣ��01: �������û���װ��,�������û�ж����,�������û���Ծ��,���ջ�Ծ�û���
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
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //��ȡ���ݿ�����
		try{
			if(!conn.isClosed()){
				newlist = new ArrayList<New_user_data_gswb>();
				Statement statement = (Statement) conn.createStatement();
				//��ѯ���û���ϢSQL
				String sql = T_Sql_Gswb_log.return_sql_new_user01(tab_name);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					newuser = new New_user_data_gswb();
					newuser.setDateLine(DataBase_Info.rs.getInt("dateline"));								//��ʱ��
					newuser.setTodayNewInstallNum(DataBase_Info.rs.getInt("todayNewInstallNum"));			//�������û���װ��
					newuser.setTodayNewUninstallNum(DataBase_Info.rs.getInt("todayNewUninstallNum"));		//�������û�ж����
					newuser.setTodayNewActiveNum(DataBase_Info.rs.getInt("todayNewActiveNum"));				//�������û���Ծ��
					newuser.setNextDayActiveNum(DataBase_Info.rs.getInt("nextDayActiveNum"));				//���ջ�Ծ�û���
					//�������뷨 ��Ծ����
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
	 * ��ѯ���û���Ϣ��02: 7�ջ�Ծ��, 30�ջ�Ծ��, 30��ж����
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
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); 		//��ȡ���ݿ�����
		try{
			if(!conn.isClosed()){
				newlist = new ArrayList<New_user_data_gswb>();
				Statement statement = (Statement) conn.createStatement();
				//��ѯ���û���ϢSQL
				String sql = T_Sql_Gswb_log.return_sql_new_user02(tab_name);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					newuser = new New_user_data_gswb();
					newuser.setDateLine(DataBase_Info.rs.getInt("dateline"));					//��ʱ��
					newuser.setActiveNum7Day(DataBase_Info.rs.getInt("7DayActiveNum"));			//7�ջ�Ծ��
					newuser.setActiveNum30Day(DataBase_Info.rs.getInt("30DayActiveNum"));		//30�ջ�Ծ��	
					newuser.setUninstallNum30Day(DataBase_Info.rs.getInt("30DayUninstallNum"));	//30��ж����
					//�������뷨 ���û�����
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
	 * �������뷨
	 * �����û�ͳ�Ʊ��в�������
	 * @param lists
	 * @param tab_name
	 */
	public void insert_Gswb_Log_new_user(List<New_user_data_gswb> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
//		Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-6));
//		Integer tab_next = tab_date-1;    //����
//		Integer tab_7days = tab_date-7;   //7������
//		Integer tab_30days = tab_date-30; //30������
		//��ѯ�»�Ծ�û�
		String sql = T_Sql_Gswb_log.insert_new_user_gswb;	//�������
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //��ȡ���ݿ����� 
		try{
			if(!conn.isClosed()){
				//��Ծ������ͳ�Ƽ���
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
			    System.out.println("�������뷨���û���Ϣͳ��������ӳɹ�!");
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
