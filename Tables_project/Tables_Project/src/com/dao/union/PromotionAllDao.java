package com.dao.union;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.db.info.DataBase_Info;
import com.db.info.sql.union.T_Sql_Promotion_install;
import com.entity.union.Promotion_All;
import com.entity.union.Promotion_Online;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/**
 * 
 * װ������
 * ��װ��ϸͳ��
 * @author ZhuYa
 */
public class PromotionAllDao {
	/**
	 * ����װ�������ܱ� (1) : 43��
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public Promotion_All getPromotionAll_01(String db_ip,String user,String pwd,String db_name,String tab_name){
		Promotion_All obj = null;
		 //��ȡ���ݿ�����
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				Statement statement = (Statement) conn.createStatement();
				//��ѯ������������ͳ��
				String sql = T_Sql_Promotion_install.return_getPromotionAll_01(tab_name);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					obj = new Promotion_All();
					obj.setDateline(DataBase_Info.rs.getInt("dateline"));						 		//����
					obj.setFaFangNum(DataBase_Info.rs.getInt("faFangNum"));						 		//���ջ��ַ����û���
					obj.setFaFangIntegrationNum(DataBase_Info.rs.getInt("faFangIntegrationNum"));		//���շ��Ż�������
					obj.setInstallationTodayNum(DataBase_Info.rs.getInt("installationTodayNum"));		//�����ƹ��û���
					obj.setIntegrationNum(DataBase_Info.rs.getInt("integrationNum"));					//�����ƹ������
					obj.setRegNum(DataBase_Info.rs.getInt("regNum"));									//����ǩ���û�����
					obj.setRegIntegrationNum(DataBase_Info.rs.getInt("regIntegrationNum"));				//����ǩ��������
					obj.setUseNum(DataBase_Info.rs.getInt("useNum"));									//���ջ���ʹ���û���
					obj.setUseIntegrationNum(DataBase_Info.rs.getInt("useIntegrationNum"));				//���ջ�����������
					obj.setDuihuanNum(DataBase_Info.rs.getInt("duihuanNum"));							//���նһ������û���
					obj.setDhIntegrationNum(DataBase_Info.rs.getInt("dhIntegrationNum"));				//���նһ����ѻ�����
					obj.setTixianNum(DataBase_Info.rs.getInt("tixianNum"));								//���������û���
					obj.setTxIntegrationNum(DataBase_Info.rs.getInt("txIntegrationNum"));				//�������ֻ�����
					obj.setIntAllIntegrationNum(DataBase_Info.rs.getInt("intAllIntegrationNum"));		//���ַ�������
					obj.setRegAllIntegrationNum(DataBase_Info.rs.getInt("regAllIntegrationNum"));		//ǩ����������
					obj.setInsAllIntegrationNum(DataBase_Info.rs.getInt("insAllIntegrationNum"));		//�ƹ��������
					obj.setAteAllIntegrationNum(DataBase_Info.rs.getInt("ateAllIntegrationNum"));		//���������
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
		return obj;
	}
	
	/**
	 * ����װ�������ܱ� (2) : union 45��
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public Promotion_All getPromotionAll_02(String db_ip,String user,String pwd,String db_name,String tab_name){
		Promotion_All obj = null;
		 //��ȡ���ݿ�����
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				Statement statement = (Statement) conn.createStatement();
				//��ѯ������������ͳ��
				String sql = T_Sql_Promotion_install.return_getPromotionAll_02(tab_name);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					obj = new Promotion_All();
					obj.setActiveNum(DataBase_Info.rs.getInt("activeNum"));					//��Ծ�û�����
					obj.setSpreadNum(DataBase_Info.rs.getInt("spreadNum"));					//��ҵ���û���
					obj.setSpreadJiFenNum(DataBase_Info.rs.getInt("spreadJiFenNum"));		//�û��ƹ��������
					obj.setConsumeNum(DataBase_Info.rs.getInt("consumeNum"));				//�û����Ļ�������
					obj.setLandedNum(DataBase_Info.rs.getInt("landedNum"));					//���յ�½�û���
					obj.setDownloadNum(DataBase_Info.rs.getInt("downloadNum"));				//���������û���
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
		return obj;
	}
	
	
	/**
	 * װ������: ��ʧ�û�
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public Promotion_All getPromotionAll_lostNum(String db_ip,String user,String pwd,String db_name,String tab_name){
		Promotion_All obj = null;
		 //��ȡ���ݿ�����
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				Statement statement = (Statement) conn.createStatement();
				//��ѯ������������ͳ�� : 
				String sql = T_Sql_Promotion_install.return_getPromotionAll_lostNum(tab_name);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					obj = new Promotion_All();
					obj.setLostNum(DataBase_Info.rs.getInt("num"));		//��ʧ�û���
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
		return obj;
	}
	
	/**
	 * ����װ�������ܱ� (3) : union 127��
	 * ��Ҫ:���ջ�Ծ��,���ջ�Ծ��,7�ջ�Ծ��,30�ջ�Ծ��
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name	: ͳ����ʱ��
	 * @param tab_name_01 : ����
	 * @param tab_name_07 : ��7��
	 * @param tab_name_30 : ��30��
	 * @return
	 */
	public Promotion_All getPromotionAll_03(String db_ip,String user,String pwd,String db_name,String tab_name,String tab_name_01,String tab_name_07,String tab_name_30){
		Promotion_All obj = null;
		 //��ȡ���ݿ�����
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				Statement statement = (Statement) conn.createStatement();
				//��ѯ������������ͳ��
				String sql = T_Sql_Promotion_install.return_getPromotionAll_03(tab_name, tab_name_01, tab_name_07, tab_name_30);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					obj = new Promotion_All();
					obj.setLoginNum(DataBase_Info.rs.getInt("loginNum"));							//����ע���û���
					obj.setRegInsActiveNum(DataBase_Info.rs.getInt("regInsActiveNum"));				//���ջ�Ծ�û���
					obj.setNextRegInsActive(DataBase_Info.rs.getInt("nextRegInsActive"));			//���ջ�Ծ�û���
					obj.setNext7DayRegInsActive(DataBase_Info.rs.getInt("next7DayRegInsActive"));	//7�ջ�Ծ�û���
					obj.setNext30DayRegInsActive(DataBase_Info.rs.getInt("next30DayRegInsActive"));	//30�ջ�Ծ�û���
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
		return obj;
	}
	
	/**
	 * ����װ�������ܱ�
	 * @param lists
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 */
	public void insert_PromotionAll(Promotion_All obj,String db_ip,String user,String pwd,String db_name){
		String sql = T_Sql_Promotion_install.insert_PromotionAll;		 //�������
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //��ȡ���ݿ�����
		try{
			if(!conn.isClosed()){
				//��Ծ������ͳ�Ƽ���
			    PreparedStatement pstatement = (PreparedStatement) conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
			    conn.setAutoCommit(false);
			   if(obj != null){
						pstatement.setInt(1, obj.getDateline());
						pstatement.setInt(2, obj.getActiveNum());
						pstatement.setInt(3, obj.getSpreadNum());
						pstatement.setInt(4, obj.getSpreadJiFenNum());
						pstatement.setInt(5, obj.getConsumeNum());
						pstatement.setInt(6, obj.getLoginNum());
						pstatement.setInt(7, obj.getLandedNum());
						pstatement.setInt(8, obj.getDownloadNum());
						pstatement.setInt(9, obj.getLostNum());
						pstatement.setInt(10, obj.getRegInsActiveNum());
						pstatement.setDouble(11, obj.getActivepercentage());
						pstatement.setInt(12, 0);
						pstatement.setInt(13, 0);
						pstatement.setInt(14, 0);
						pstatement.setInt(15, 0);
						pstatement.setInt(16, 0);
						pstatement.setInt(17, 0);
						pstatement.setInt(18, obj.getFaFangNum());
						pstatement.setInt(19, obj.getFaFangIntegrationNum());
						pstatement.setInt(20, obj.getInstallationTodayNum());
						pstatement.setInt(21, obj.getIntegrationNum());
						pstatement.setInt(22, obj.getRegNum());
						pstatement.setInt(23, obj.getRegIntegrationNum());
						pstatement.setInt(24, obj.getUseNum());
						pstatement.setInt(25, obj.getUseIntegrationNum());
						pstatement.setInt(26, obj.getDuihuanNum());
						pstatement.setInt(27, obj.getDhIntegrationNum());
						pstatement.setInt(28, obj.getTixianNum());
						pstatement.setInt(29, obj.getTxIntegrationNum());
						pstatement.setInt(30, obj.getIntAllIntegrationNum());
						pstatement.setInt(31, obj.getRegAllIntegrationNum());
						pstatement.setInt(32, obj.getInsAllIntegrationNum());
						pstatement.setInt(33, obj.getAteAllIntegrationNum());
						
						pstatement.addBatch();
						pstatement.executeBatch();
					    conn.commit();  
					    System.out.println("װ�������ܱ����ɹ�!");
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
	}
	
	/**
	 * ���� ���� װ�������ܱ�
	 * @param nextActive	: ������,��7����,��30����
	 * @param nextRegInsActive : ���ջ�Ծ��,��7�ջ�Ծ��,��30�ջ�Ծ��
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_beforeDate
	 */
	public void update_PromotionAll(double nextActive,int nextRegInsActive,String db_ip,String user,String pwd,String db_name,String tab_beforeDate){
		
		//����
		String sql = T_Sql_Promotion_install.updateNextDay_PromotionAll(nextActive,nextRegInsActive,tab_beforeDate);		 
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //��ȡ���ݿ�����
		try{
			if(!conn.isClosed()){
				//��Ծ������ͳ�Ƽ���
			    PreparedStatement pstatement = (PreparedStatement) conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
			    conn.setAutoCommit(false);
			    //����
			    pstatement.executeUpdate(sql);   
			    pstatement.addBatch();
			    pstatement.executeBatch();
			    conn.commit();  
			    System.out.println("װ�������ܱ� ���³ɹ�");
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
