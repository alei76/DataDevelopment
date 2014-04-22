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
 * װ�����˲�Ʒ����(��������)
 * @author ZhuYa
 *
 */
public class PromotionSoftwareDao {
	
	/**
	 * ����������ݼ���
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public List<Promotion_Software> getPromotionSoftware(String db_ip,String user,String pwd,String db_name,String tab_name){
		List<Promotion_Software> objList = null;
		 //��ȡ���ݿ�����
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name);
		try{
			if(!conn.isClosed()){
				Promotion_Software obj = null;
				objList = new ArrayList<Promotion_Software>();
				Statement statement = (Statement) conn.createStatement();
				//��ѯ������������ͳ��
				String sql = T_Sql_Promotion_install.return_getPromotionSoftware(tab_name);
				DataBase_Info.rs = statement.executeQuery(sql);
				while(DataBase_Info.rs.next()) {
					obj = new Promotion_Software();
					obj.setSoftName(DataBase_Info.rs.getString("softName"));	//������ϸ(���ֲ�Ʒ)
					obj.setPcNum(DataBase_Info.rs.getInt("pcNum"));				//�ƹ㼼��Ա(�Ե���Ϊ��λ)��
					obj.setUseNum(DataBase_Info.rs.getInt("useNum"));			//ʹ���û���
					obj.setTime(DataBase_Info.rs.getInt("dateline"));				//ʱ��
					
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
	 * ���� װ������������ݼ���
	 * @param lists
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 */
	public void insert_PromotionSoftware(List<Promotion_Software> lists,String db_ip,String user,String pwd,String db_name){
		//��ѯ�»�Ծ�û�
		String sql = T_Sql_Promotion_install.insert_PromotionSoft;	//�������
		Connection conn = DataBase_Info.getConn(db_ip,user,pwd,db_name); //��ȡ���ݿ�����
		try{
			if(!conn.isClosed()){
				//��Ծ������ͳ�Ƽ���
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
			    System.out.println("װ���������������ӳɹ�!");
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
					obj.setTime(DataBase_Info.rs.getInt("ymd"));	//ʱ��
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
}
