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
 * װ������
 * ��װ��ϸͳ��
 * @author ZhuYa
 *
 */
public class PromotionInstallDao {
	
	/**
	 * ����װ�����˰�װ����
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
		 //��ȡ���ݿ�����
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		
		try{
			if(!conn.isClosed()){
				conn.setAutoCommit(false);
				lists = new ArrayList<Promotion_Install>(); //���ؽ������
				Statement statement = (Statement) conn.createStatement();
				//��ѯ������������ͳ��
				String sql = T_Sql_Promotion_install.return_getPromotionInstall(todayDate);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					obj = new Promotion_Install();
					obj.setUid(DataBase_Info.rs.getInt("uid"));						 //��������
					obj.setNum(DataBase_Info.rs.getInt("num"));						 //����
					obj.setSoftware_id(DataBase_Info.rs.getString("SoftID"));		 //�汾ID
					obj.setSoftware_version(DataBase_Info.rs.getString("SoftVer"));	 //�汾
					obj.setDateline(DataBase_Info.rs.getInt("dateline"));	 	 	 //ʱ��
					obj.setPc_id(DataBase_Info.rs.getString("pc_id"));	 	 	 	 //PC�ն�
					obj.setId(DataBase_Info.rs.getInt("insId"));					 //�嵥��ID
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
	 * ��ȡ�������뷨���µ����б���
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
	 * ����װ������ǰ15������ʹ�ü���
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param inputType	: �Ƿ��ֶ�����
	 * @param inputDate : ��������
	 * @param ǰ15�����
	 */
	public List<Promotion_Online> getPromotionOnline(String db_ip,String user,String pwd,String db_name,int inputType,int inputDate,List<Integer> tabList){
		List<Promotion_Online> lists = null;
		Promotion_Online obj = null;
		 //��ȡ���ݿ�����
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
				if(!conn.isClosed()){
					lists = new ArrayList<Promotion_Online>(); //���ؽ������
					//��ѯ������������ͳ��
					String sql = T_Sql_Promotion_install.return_getPromotionOnline.toString();
//					String sql = T_Sql_Promotion_install.return_getPromotionOnline(inputType, inputDate);
					PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
					if(tabList.size() > 0){
						for(int i = 0; i < tabList.size(); i++){
							statement.setInt(1, tabList.get(i));
							DataBase_Info.rs = statement.executeQuery();
							while(DataBase_Info.rs.next()) {
								obj = new Promotion_Online();
								obj.setUid(DataBase_Info.rs.getInt("uid"));						 		 	 //��������
								obj.setSoftware_id(DataBase_Info.rs.getString("softID"));			 //���id
								obj.setSoftware_version(DataBase_Info.rs.getString("softVer"));	 //����汾
								obj.setPc_id(DataBase_Info.rs.getString("pc_id"));	 		 	 		 //�汾 
								obj.setDateline(tabList.get(i));					 									//ʱ��
								obj.setId(DataBase_Info.rs.getInt("onlineId"));								//�嵥��ID
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
	 * ������
	 * ��ȡװ��������������ʹ��3����û�
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param inputType	: �Ƿ��ֶ�����
	 * @param inputDate : ��������
	 * @param ǰ15�����
	 */
	public List<Promotion_Online> getPromotionOnlineOld(String db_ip,String user,String pwd,String db_name,int inputType,int inputDate){
		List<Promotion_Online> lists = null;
		Promotion_Online obj = null;
		 //��ȡ���ݿ�����
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
				if(!conn.isClosed()){
					lists = new ArrayList<Promotion_Online>(); //���ؽ������
					//��ѯ������������ͳ��
//					String sql = T_Sql_Promotion_install.return_getPromotionOnline.toString();
					String sql = T_Sql_Promotion_install.return_getPromotionOnlineOld(inputType, inputDate);
//					PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
					Statement statement = (Statement) conn.createStatement();
					DataBase_Info.rs = statement.executeQuery(sql);
					while(DataBase_Info.rs.next()) {
						obj = new Promotion_Online();
						obj.setUid(DataBase_Info.rs.getInt("uid"));						 		 	 //��������
						obj.setSoftware_id(DataBase_Info.rs.getString("softID"));			 //���id
						obj.setSoftware_version(DataBase_Info.rs.getString("softVer"));	 //����汾
						obj.setPc_id(DataBase_Info.rs.getString("pc_id"));	 		 	 		 //�汾 
						obj.setDateline(inputDate);					 										//ʱ��
						obj.setId(DataBase_Info.rs.getInt("onlineId"));								//�嵥��ID
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
	 * �����󱸷�
	 * ����װ����������ʹ�� ����
	 * @param lists
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 */
	public void insert_PromotionOnline_BAK(List<Promotion_Online> lists,String db_ip,String user,String pwd,String db_name){
		//��ѯ�»�Ծ�û�
		String sql = T_Sql_Promotion_install.insert_PromotionOnline;	//�������
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //��ȡ���ݿ�����
		try{
			if(!conn.isClosed()){
				//��Ծ������ͳ�Ƽ���
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
			    System.out.println("װ����������ʹ��ͳ��������ӳɹ�!");
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
	 * ����װ������ ��Ч��װ�û���
	 * @param lists
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 */
	public void insert_PromotionOnline(List<Promotion_Install> lists,String db_ip,String user,String pwd,String db_name){
		//������Ч��װ�û���
		String sql = T_Sql_Promotion_install.insert_PromotionEffective;	
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //��ȡ���ݿ�����
		try{
			if(!conn.isClosed()){
				//��Ծ������ͳ�Ƽ���
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
			    System.out.println("װ��������Ч��װ�û���ͳ��������ӳɹ�!");
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
	 * ��ȡ15�� 127�� PromotionInstall ����
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
		 //��ȡ���ݿ�����
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
				if(!conn.isClosed()){
						lists = new ArrayList<Promotion_Install>(); //���ؽ������
						//��ѯ15�� װ�����˰�װ��
						String sql = T_Sql_Promotion_install.return_getUnionPromotionInstall;
						PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
						statement.setString(1, inputDate);
						statement.setString(2, inputDate);
						DataBase_Info.rs = statement.executeQuery();
						while(DataBase_Info.rs.next()) {
							obj = new Promotion_Install();
							obj.setUid(DataBase_Info.rs.getInt("uid"));						 		 	 		//��������
							obj.setSoftware_id(DataBase_Info.rs.getString("software_id"));			//���id
							obj.setSoftware_version(DataBase_Info.rs.getString("software_version"));	 //����汾
							obj.setPc_id(DataBase_Info.rs.getString("pc_id"));	 		 	 		 //�汾 
							obj.setDateline(DataBase_Info.rs.getInt("dateline"));					 //ʱ��
							obj.setId(DataBase_Info.rs.getInt("id"));										//��װid
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
	 * ����װ�����˰�װ��ϸ ����
	 * @param lists
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 */
	public void insert_PromotionInstall(List<Promotion_Install> lists,String db_ip,String user,String pwd,String db_name){
		//��ѯ�»�Ծ�û�
		String sql = T_Sql_Promotion_install.insert_PromotionInstall;	//�������
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //��ȡ���ݿ�����
		try{
			if(!conn.isClosed()){
				conn.setAutoCommit(false);
				//��Ծ������ͳ�Ƽ���
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
			    System.out.println("װ��������ϸͳ��������ӳɹ�!");
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
	 * ����ǰ30������:���뷨��װlog����
	 * @param db_ip	: 43��
	 * @param user
	 * @param pwd
	 * @param db_name	: gswb_log
	 * @param inputType	: �Ƿ��ֶ�����
	 * @param inputDate : ��������
	 * @param inputDate : tabTitle : 30��install��ͷ����
	 */
	public List<Promotion_Install> getGswbInstallLog(String db_ip, String user, String pwd, String db_name, Integer tab_date){
		List<Promotion_Install> lists = null;
		Promotion_Install obj = null;
		 //��ȡ���ݿ�����
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
					//��ѯ30��������뷨��װ
					String sql = T_Sql_Promotion_install.return_getGswbLog;
					PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
					lists = new ArrayList<Promotion_Install>(); //���ؽ������
						statement.setInt(1, tab_date);
						DataBase_Info.rs = statement.executeQuery();
						while(DataBase_Info.rs.next()) {
							obj = new Promotion_Install();
							obj.setSoftware_id(DataBase_Info.rs.getString("SoftID"));		//����汾ID
							obj.setSoftware_version(DataBase_Info.rs.getString("SoftVer")); //����汾
							obj.setPc_id(DataBase_Info.rs.getString("pc_id"));				//PC�ն�
							lists.add(obj);
						}
						System.out.println("�������뷨��װlog: "+tab_date+" ���! ");
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
	 * ����ǰ30������,���뷨����log����
	 * @param db_ip		: 43��
	 * @param user
	 * @param pwd
	 * @param db_name	: gswb_log
	 * @param tabTitle	: 30�������ʹ��
	 * @param inputType ���Ƿ��ֶ�����
	 * @param inputDate : ����ʱ��
	 * @return
	 */
	public List<Promotion_Install> getGswbOnlineLog(String db_ip,String user,String pwd,String db_name,Integer tabTitle,int inputType){
		List<Promotion_Install> lists = null;
		Promotion_Install obj = null;
		 //��ȡ���ݿ�����
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
					//��ѯ30��������뷨����ʹ��
					String sql = T_Sql_Promotion_install.return_getGswbOnline;
					PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
					lists = new ArrayList<Promotion_Install>(); //���ؽ������
					statement.setInt(1, tabTitle);	//��ֵͳ��ʱ��
					DataBase_Info.rs = statement.executeQuery();
					while(DataBase_Info.rs.next()) {
						obj = new Promotion_Install();
						obj.setSoftware_id(DataBase_Info.rs.getString("SoftID"));		//����汾ID
						obj.setSoftware_version(DataBase_Info.rs.getString("SoftVer")); //����汾
						obj.setPc_id(DataBase_Info.rs.getString("pc_id"));				//PC�ն�
						lists.add(obj);
					}
					System.out.println("�������뷨����log: "+tabTitle+" ���! ");
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
	 * ͳ�ƺ�123IP��ַ��
	 * @param db_ip	:	127��
	 * @param user	
	 * @param pwd
	 * @param db_name	: union
	 * @param inputType	: ͳ��ʱ��
	 * @return
	 */
	public List<Promotion_Software> getHao123(String db_ip,String user,String pwd,String db_name,int inputDate){
		Promotion_Software obj = null;
		List<Promotion_Software> lists = null;
		 //��ȡ���ݿ�����
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				lists = new ArrayList<Promotion_Software>();
				//��ѯ30��������뷨,����3������ʹ��
				String sql = T_Sql_Promotion_install.return_getHao123;
				PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
				statement.setInt(1, inputDate);	//��ֵͳ��ʱ��
				DataBase_Info.rs = statement.executeQuery();
				while(DataBase_Info.rs.next()) {
					obj = new Promotion_Software();
					obj.setPcNum(DataBase_Info.rs.getInt("pcNum"));		//�ƹ㼼��Ա(���˺�Ϊ��λ)��
					obj.setUseNum(DataBase_Info.rs.getInt("useNum")); 	//ʹ���û���
					obj.setTime(DataBase_Info.rs.getInt("dateline"));	//ʱ��
					obj.setCredit(DataBase_Info.rs.getInt("credit"));	//����
					obj.setSoftName(DataBase_Info.rs.getString("name"));//�������
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
	 * ��Ч�û���װ�� : �¶�Ψһ������ - ���ɰ�װ��
	 *  ��ȡ�¶�Ψһ������
	 * @param db_ip : 43��
	 * @param user
	 * @param pwd
	 * @param db_name : statistics
	 * @param inputDate
	 * @return
	 */
	public List<Promotion_Install> getInstallOkGswb1 (String db_ip,String user,String pwd,String db_name,String inputDate){
		int tab_date = Integer.parseInt(inputDate.substring(inputDate.length()-6)); // ��ȡʱ���ʽ : 140421
		Promotion_Install obj = null;
		List<Promotion_Install> lists = null;
		 //��ȡ���ݿ�����
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				lists = new ArrayList<Promotion_Install>();
				//��ѯ�¶�Ψһ������
				String sql = T_Sql_Promotion_install.return_getInstallOkGswb;
				PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
				statement.setInt(1, tab_date);	//��ֵͳ��ʱ��
				DataBase_Info.rs = statement.executeQuery();
				while(DataBase_Info.rs.next()) {
					obj = new Promotion_Install();
					obj.setUserId(DataBase_Info.rs.getString("uid"));		//װ�������˺�ID
					obj.setNum(DataBase_Info.rs.getInt("uidNum"));	//�¶�Ψһ������
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
		 * ��Ч�û���װ�� : �¶�Ψһ������ - ���ɰ�װ��
		 * ��ȡ���ɰ�װ��
		 * @param db_ip : 43��
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
			 //��ȡ���ݿ�����
			Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
			try{
				if(!conn.isClosed()){
					lists = new ArrayList<Promotion_Install>();
					//��ѯ �������뷨���ɰ�װ��
					String sql = T_Sql_Promotion_install.return_getInstallWbGswb();
					PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
					statement.setInt(1, tab_date);	//��ֵͳ��ʱ��
					DataBase_Info.rs = statement.executeQuery();
					while(DataBase_Info.rs.next()) {
						obj = new Promotion_Install();
						obj.setUserId(DataBase_Info.rs.getString("uid"));				//װ�������˺�ID
						obj.setNum(DataBase_Info.rs.getInt("uidNum"));	//�¶�Ψһ������
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
		 * ��ȡװ�������˺�ID
		 * @param db_ip : 127��
		 * @param user
		 * @param pwd
		 * @param db_name :  union
		 * @param inputDate
		 * @return
		 */
		public List<Integer> getUserId3 (String db_ip,String user,String pwd,String db_name){
				List<Integer> lists = null;
				 //��ȡ���ݿ�����
				Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
				try{
						if(!conn.isClosed()){
							lists = new ArrayList<Integer>();
							//��ѯ �������뷨���ɰ�װ��
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
