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
 * �������뷨
 * ��������ͳ��
 * @author ZhuYa
 *
 */
public class GswbCollectQdDao {
	
	/**
	 * ��������ͳ��
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
		 //��ȡ���ݿ�����
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				Statement statement = (Statement) conn.createStatement();
				//��ѯ������������ͳ��
				String sql = T_Sql_Gswb_Collect_Qd.return_getGswbAllQd(tab_name, qdType, qd_name);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					gswbQd = new Gswb_collect_qd();
					gswbQd.setQdType(DataBase_Info.rs.getString("qdType"));						 //��������
					gswbQd.setServiceStartNum(DataBase_Info.rs.getInt("serviceStartNum"));		 //���շ���������	
					gswbQd.setActiveNum(DataBase_Info.rs.getInt("activeNum"));					 //���ջ�Ծ�û���
					gswbQd.setOnlyInstallNum(DataBase_Info.rs.getInt("onlyInstallNum"));		 //���հ�װ��(ȥ��)
					gswbQd.setMonthAddInstallNum(DataBase_Info.rs.getInt("monthAddInstallNum")); //���հ�������
					gswbQd.setInputLV1Num(DataBase_Info.rs.getInt("inputLV1Num"));				 //����ʹ��(1-10)
					gswbQd.setInputLV2Num(DataBase_Info.rs.getInt("inputLV2Num"));				 //����ʹ��(1-140)
					gswbQd.setInputLV3Num(DataBase_Info.rs.getInt("inputLV3Num"));				 //����ʹ��(140+)
					gswbQd.setUninstNum(DataBase_Info.rs.getInt("uninstNum"));					 //�����û�ж����
					gswbQd.setActiveNum7Days(DataBase_Info.rs.getInt("activeNum7Days"));		 //7�մ����
					gswbQd.setInstallNum(DataBase_Info.rs.getInt("installNum"));			 	 //���հ�װ��(û��ȥ��)
				}
				System.out.println("����"+qdType+"����ͳ�Ʋ�ѯ���");
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
	 * �������뷨 :��װ��/��Ծ��
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
		 //��ȡ���ݿ�����
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				list = new ArrayList<Gswb_collect_qd>();
				Statement statement = (Statement) conn.createStatement();
				//��ѯ��װ��/��Ծ��
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					gswbQd = new Gswb_collect_qd();
					gswbQd.setQid(DataBase_Info.rs.getString("qid"));	//��������
					gswbQd.setUid(DataBase_Info.rs.getString("uid"));	//��Ծ���� : ��װ�û���1��2��3���µ��û���
					list.add(gswbQd);
				}
				System.out.println("��װ/��Ծ�����ݳɹ�����");
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
	 * ��������:�û���Ծ������װ�û�����1��2��3�ȵ��û���
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
		 //��ȡ���ݿ�����
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				lists = new ArrayList<Gswb_collect_qd>();
				Statement statement = (Statement) conn.createStatement();
				//��ѯ��װ�û���1��2��3���µ��û���
				String sql = T_Sql_Gswb_Collect_Qd.return_getGswbQdInsActive_02(tab_name, qd_name, qdType);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					gswbQd = new Gswb_collect_qd();
					gswbQd.setQdType(DataBase_Info.rs.getString("qdType"));	 //��������
					gswbQd.setQid(DataBase_Info.rs.getString("qid"));		 //��������
					gswbQd.setActiveInputNum(DataBase_Info.rs.getInt("num"));//��Ծ���� : ��װ�û���1��2��3���µ��û���
					
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
	 * ������������ͳ��
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
		 //��ȡ���ݿ�����
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				lists = new ArrayList<Gswb_collect_qd>();
				Statement statement = (Statement) conn.createStatement();
				//��ѯ������������ͳ��
				String sql = T_Sql_Gswb_Collect_Qd.return_getGswbAllQd_02(tab_name, qdType, qd_name);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					gswbQd = new Gswb_collect_qd();
					gswbQd.setQdType(DataBase_Info.rs.getString("qdType"));						 //��������
					gswbQd.setQid(DataBase_Info.rs.getString("QID"));							 //����
					gswbQd.setServiceStartNum(DataBase_Info.rs.getInt("serviceStartNum"));		 //���շ���������	
					gswbQd.setActiveNum(DataBase_Info.rs.getInt("activeNum"));					 //���ջ�Ծ�û���
					gswbQd.setOnlyInstallNum(DataBase_Info.rs.getInt("onlyInstallNum"));		 //���հ�װ��(ȥ��)
					gswbQd.setMonthAddInstallNum(DataBase_Info.rs.getInt("monthAddInstallNum")); //���հ�������
					gswbQd.setInputLV1Num(DataBase_Info.rs.getInt("inputLV1Num"));				 //����ʹ��(1-10)
					gswbQd.setInputLV2Num(DataBase_Info.rs.getInt("inputLV2Num"));				 //����ʹ��(1-140)
					gswbQd.setInputLV3Num(DataBase_Info.rs.getInt("inputLV3Num"));				 //����ʹ��(140+)
					gswbQd.setUninstNum(DataBase_Info.rs.getInt("uninstNum"));					 //�����û�ж����
					gswbQd.setActiveNum7Days(DataBase_Info.rs.getInt("activeNum7Days"));		 //7�մ����
					gswbQd.setInstallNum(DataBase_Info.rs.getInt("installNum"));			 	 //���հ�װ��(û��ȥ��)
					
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
	 * ���������㲿����ͳ��
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
		 //��ȡ���ݿ�����
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				lists = new ArrayList<Gswb_collect_qd>();
				Statement statement = (Statement) conn.createStatement();
				//��ѯ������������ͳ��
				String sql = T_Sql_Gswb_Collect_Qd.return_getPromotionNode3(tab_name);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					gswbQd = new Gswb_collect_qd();
					gswbQd.setQdType(DataBase_Info.rs.getString("qdType"));						 //��������
					gswbQd.setQid(DataBase_Info.rs.getString("AdvertIdentifier"));				 //����
					gswbQd.setServiceStartNum(DataBase_Info.rs.getInt("serviceStartNum"));		 //���շ���������	
					gswbQd.setActiveNum(DataBase_Info.rs.getInt("activeNum"));					 //���ջ�Ծ�û���
					gswbQd.setOnlyInstallNum(DataBase_Info.rs.getInt("onlyInstallNum"));		 //���հ�װ��(ȥ��)
					gswbQd.setMonthAddInstallNum(DataBase_Info.rs.getInt("monthAddInstallNum")); //���հ�������
					gswbQd.setInputLV1Num(DataBase_Info.rs.getInt("inputLV1Num"));				 //����ʹ��(1-10)
					gswbQd.setInputLV2Num(DataBase_Info.rs.getInt("inputLV2Num"));				 //����ʹ��(1-140)
					gswbQd.setInputLV3Num(DataBase_Info.rs.getInt("inputLV3Num"));				 //����ʹ��(140+)
					gswbQd.setUninstNum(DataBase_Info.rs.getInt("uninstNum"));					 //�����û�ж����
					gswbQd.setActiveNum7Days(DataBase_Info.rs.getInt("activeNum7Days"));		 //7�մ����
					gswbQd.setInstallNum(DataBase_Info.rs.getInt("installNum"));			 	 //���հ�װ��(û��ȥ��)
					
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
	 * �������뷨
	 * ������������ͳ��
	 * @param lists
	 * @param tab_name
	 */
	public void insert_Gswb_Qd(List<Gswb_collect_qd> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
		Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-6));
		//��ѯ�»�Ծ�û�
		String sql = T_Sql_Gswb_Collect_Qd.insert_gswb_qd;	//�������
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //��ȡ���ݿ�����
		try{
			if(!conn.isClosed()){
				//��Ծ������ͳ�Ƽ���
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
			    System.out.println("�������뷨��������ͳ��������ӳɹ�!");
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
	 * �������뷨
	 * ���������������ͳ��
	 * @param lists
	 * @param tab_name
	 */
	public void insert_Gswb_Qd_02(Map<String,List<Gswb_collect_qd>> map,String db_ip,String user,String pwd,String db_name,String tab_name){
		Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-6));
		//��ѯ�»�Ծ�û�
		String sql = T_Sql_Gswb_Collect_Qd.insert_gswb_qd_02;	//�������
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //��ȡ���ݿ�����
		try{
			if(!conn.isClosed()){
				//��Ծ������ͳ�Ƽ���
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
			    System.out.println("�������뷨������������ͳ��������ӳɹ�!");
			    
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
	 * ������������
	 * @param map : map���ݼ�
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 */
	public void insert_Gswb_Qd_03(List<Gswb_collect_qd> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
		Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-6));
		//��ѯ�»�Ծ�û�
		String sql = T_Sql_Gswb_Collect_Qd.insert_gswb_qd_03;	//�������
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //��ȡ���ݿ�����
		try{
			if(!conn.isClosed()){
				//��Ծ������ͳ�Ƽ���
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
			    System.out.println("�������뷨������������ͳ��������ӳɹ�!");
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
	 * �������뷨������7�մ��������
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
		//��ȡʱ��
		Integer tab_date = Integer.parseInt(tab_beforeDate.substring(tab_beforeDate.length()-6));
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //��ȡ���ݿ�����
		try{
			if(!conn.isClosed()){
				PreparedStatement pstatement = null;
			    conn.setAutoCommit(false);
			    String sql = T_Sql_Gswb_Collect_Qd.update_gswb_qd();
			    if(lists != null && lists.size() > 0){
			    	//��Ծ������ͳ�Ƽ���
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
			    System.out.println("�������뷨�������������³ɹ�!");
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
	 * �������뷨2������7�մ��������
	 * @param lists
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tabName
	 * @param tab_beforeDate
	 */
	public void update_Gswb_Qd02(Map<String,List<Gswb_collect_qd>> mapLists,String db_ip,String user,String pwd,String db_name,String tab_beforeDate){
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //��ȡ���ݿ�����
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
				    		//��Ծ������ͳ�Ƽ���
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
			    	//����
				    pstatement.executeUpdate();
				    pstatement.addBatch();
				    
			    }
			    pstatement.executeBatch();
			    conn.commit();  
			    System.out.println("�������뷨���������������³ɹ�!");
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
	 * �������뷨3������7�մ��������
	 * @param lists
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tabName
	 * @param tab_beforeDate
	 */
	public void update_Gswb_Qd03(List<Gswb_collect_qd> lists,String db_ip,String user,String pwd,String db_name,String tab_beforeDate){
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //��ȡ���ݿ�����
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
			    System.out.println("�������뷨���������������³ɹ�!");
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
