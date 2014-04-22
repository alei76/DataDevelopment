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
 * ���ݷ��ʲ�
 * ��װ��
 * @author ZhuYa
 *
 */
public class InstallDao {
	
	/**
	 * ��ȡ�������뷨 ��װ��
	 * @param db_ip : ����IP
	 * @param db_name : ���ݿ���
	 * @param user 	: �û���
	 * @param pwd	: ����
	 * @param inputType	: ��������  : 0���Զ���ǰ����,1���ֶ���������
	 * @param inputDate : �ֶ������ʱ��
	 * @return
	 */
	public List<Install_collect_gswb> getGswb_Log_Install_01(String db_ip,String user,String pwd,String db_name,String tab_name){
		Install_collect_gswb install_statistics = null;		
		List<Install_collect_gswb> active_gswb_list  = null;
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //��ȡ���ݿ�����
		try{
			if(!conn.isClosed()){
				active_gswb_list = new ArrayList<Install_collect_gswb>();
				Statement statement = (Statement) conn.createStatement();
				//��ѯ��װ��SQL
				String sql = T_Sql_Gswb_log.return_gswb_log_install_01(tab_name);	
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					install_statistics = new Install_collect_gswb();
					install_statistics.setQid(DataBase_Info.rs.getString("QID"));							//����
					install_statistics.setAdvertIdentifier(DataBase_Info.rs.getString("AdvertIdentifier")); //��ȡQID��6λ֮����ַ�
					install_statistics.setAllInstallNum(DataBase_Info.rs.getInt("AllInstallNum"));			//�ܵð�װ����
					install_statistics.setOnlyInstallNum(DataBase_Info.rs.getInt("OnlyInstallNum"));		//ȥ��֮��ð�װ��
					install_statistics.setAddOnlyPlayerNum(DataBase_Info.rs.getInt("addOnlyPlayerNum"));	//�����ӵ��û���
					install_statistics.setCybercafeInstallNum(DataBase_Info.rs.getInt("cybercafeInstallNum")); 	//���ɰ�װ����
					//�ж��Ƿ����ַ���
					 try{
						 install_statistics.setAdvertDate(DataBase_Info.rs.getInt("AdvertDate"));
					 }catch(Exception e){
						 install_statistics.setAdvertDate(0);
					 }
					install_statistics.setSoftVer(DataBase_Info.rs.getString("SoftVer"));					//�汾
					//�������뷨 ��Ծ����
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
	 * ��ȡ�������뷨���µ����б���
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
	 * ��ȡ31����ܰ�װ��
	 * @param db_ip : ����IP
	 * @param user	�� �û���
	 * @param pwd	: ����
	 * @param db_name	: ���ݿ���
	 * @param tab_name	: ����
	 * @return
	 */
	public List<Install_collect_gswb> getGswb_Log_Install_30Days(String db_ip,String user,String pwd,String db_name,String tab_name,List<Integer> list_inst){
		//��ʼ��������
		Install_collect_gswb install_statistics = null;	
		//��ʼ������
		List<Install_collect_gswb> active_gswb_list  = null; 
		//��ȡ���ݿ�����
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); 
		try{
			if(!conn.isClosed()){
				active_gswb_list = new ArrayList<Install_collect_gswb>();
				Statement statement = (Statement) conn.createStatement();
				//��ȡ��������
				List<Integer> tabs = list_inst;
				//�������л�ȡ����31����������
				for(int i=0;i<tabs.size();i++){
					System.out.println(" ���� "+tabs.get(i));
					//����ѭ��ÿ������
					tab_name = "install"+tabs.get(i); 
					//��ѯ��װ��SQL
					String sql = T_Sql_Gswb_log.return_sql_gswb_all_install30Days(tab_name);
					DataBase_Info.rs = statement.executeQuery(sql);
					while(DataBase_Info.rs.next()) {
						install_statistics = new Install_collect_gswb();
						install_statistics.setQid(DataBase_Info.rs.getString("QID"));					  //����
						install_statistics.setSoftVer(DataBase_Info.rs.getString("SoftVer"));			  //�汾
						install_statistics.setUid(DataBase_Info.rs.getString("uid"));			  		  //�û���ʾ��
						install_statistics.setTab_date(tabs.get(i));							//ÿ�ű��ʱ��
						//�������뷨 ��Ծ����
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
	 * �������뷨
	 * ����װͳ�Ʊ��в�������
	 * @param db_ip
	 * @param user
	 * @param pwd
	 */
	public void insert_Gswb_Log_Install(List<Install_collect_gswb> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
		Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-6));
		//��ѯ�»�Ծ�û�
		String sql = T_Sql_Gswb_log.insert_gswb_install;	//�������
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //��ȡ���ݿ�����
		try{
			if(!conn.isClosed()){
				//��Ծ������ͳ�Ƽ���
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
			    System.out.println("�������뷨��װ��������ӳɹ�!");
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
