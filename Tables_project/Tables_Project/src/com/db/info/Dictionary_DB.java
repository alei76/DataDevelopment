package com.db.info;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.DatabaseMetaData;

/**
 * ���ݿ��ֵ�
 * @author ZhuYa
 *
 */
public class Dictionary_DB {
	public static int today = 20140213;  //����ʱ�� -----------������
	public static int today_substr = 140213; //��ȡ��ʱ�� 
	
	/************************ ���ݿ�  **********************/
	public static String db_gswb_log = "gswb_log"; 			//�������뷨
	public static String db_haharili_log = "haharili_log";  //��������
	public static String db_ysie_log = "ysie_log"; 			//���������
	public static String db_ysqd_log = "ysqd_log"; 			//��������
	public static String db_statistics = "statistics"; 		//ͳ�ƿ�
	public static String db_zj7654_log = "zj7654_log"; 		//װ�������嵥��
	public static String db_mini_log = "mini_log"; 			//���㲥���嵥
	
	public static String db_union = "union"; 				//װ������WEB��
	
	
	public static String ip_01 = "112.124.97.43:3306";	//�������뷨��ʽ��
//	public static String ip_01 = "localhost:3306";
	public static String user_01 = "developer";				//USER
	public static String password_01 = "DN3v74JB";			//PWD
	
//	public static String ip_02 = "192.168.193.127:3306"; 	//���Կ�IP��ַ
	public static String ip_02 = "211.149.193.127:3306"; 	//��ʽ��IP��ַ   : ����IP - 211.149.193.127 
	public static String user_02 = "developer"; 			//USER
	public static String password_02 = "DN3v74JB";			//PWD
	
	
	/************************ �������뷨�� ����  **********************/
	public static String tblname_gswb_log_active = "active";   		 	//�������뷨��  ����Ծ��
	public static String tblname_gswb_log_install = "install"; 		 	//�������뷨��  ����װ��
	public static String tblname_gswb_log_online = "online";   		 	//�������뷨��  �����߱�  
	public static String tblname_gswb_log_uninstall = "uninstall";   	//�������뷨��  ��ж�ر�
	
	/************************** �������� ����  ************************/
	public static String tblname_haharili_log_install = "install";    	//����������  ����װ��
	public static String tblname_haharili_log_online = "online";     	//����������  �����߱�
	public static String tblname_haharili_log_uninstall = "uninstall";  //����������  ��ж�ر�
	
	/************************* ��������� ����  ***********************/
	public static String tblname_ysie_log_active = "active";    		//�����������  ����Ծ��
	public static String tblname_ysie_log_install = "install";    		//�����������  ����װ��
	public static String tblname_ysie_log_online = "online";    		//�����������  �����߱�
	public static String tblname_ysie_log_update = "update";    		//�����������  ��������
	public static String tblname_ysie_log_uninstall = "uninstall";    	//�����������  ��ж�ر�
	
	/************************* �������� ����  ***********************/
	public static String tblname_ysqd_log_active = "active";    		//����������  ����Ծ��
	public static String tblname_ysqd_log_check = "check";    			//����������  ����⻷����
	public static String tblname_ysqd_log_install = "install";    		//����������  ����װ��
	public static String tblname_ysqd_log_online = "online";    		//����������  �����߱�
	public static String tblname_ysqd_log_uninstall = "uninstall";    	//����������  ��ж�ر�
	
	/**
	 * ����������
	 */
	public static String[] strs = {"gw","ald","nb","wb","other"};
	
	/**
	 * ǰһ���ȡ����ʱ��
	 * ת����Integer����
	 * @param inputType : 0���Զ���ǰ����,1���ֶ���������
	 * @param inputDate : �ֶ������ʱ��
	 * @return
	 */
	public static Integer getBeforeDate(int inputType,Integer inputDate){
		Date dNow = new Date();   				 	 //��ǰʱ��
		Date dBefore = new Date();
		Integer bDay = 0;							 //���ر�ʱ��
		Calendar calendar = Calendar.getInstance();  //�õ�����
		calendar.setTime(dNow);						 //�ѵ�ǰʱ�丳������
		calendar.add(Calendar.DAY_OF_MONTH, -1);     //����Ϊǰһ��
		dBefore = calendar.getTime();   			 //�õ�ǰһ���ʱ��
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd"); //����ʱ���ʽ
		String beforeDate = sdf.format(dBefore);    	   	   //��ʽ��ǰһ��
		if(inputType == 0){
			bDay = Integer.parseInt(beforeDate);
		}else{
			bDay = inputDate;
		}
		return bDay;
	}
	
	/**
	 * ����ʱ��
	 * ǰ�����ȡ����ʱ��
	 * ת����Integer����
	 * @param inputType : 0���Զ���ǰ����,1���ֶ���������
	 * @param inputDate : �ֶ������ʱ��
	 * @param beDate : ǰ����
	 * @return
	 * @throws ParseException 
	 */
	public static Integer getBeforeDate_money(int inputType,Integer inputDate,int beDate){
		Date dNow = new Date();   				 	 	//��ǰʱ��
		Date dBefore = new Date();						
		Integer bDay = 0;							 	//���ر�ʱ��
		Calendar calendar = Calendar.getInstance();  	//�õ�����
		calendar.setTime(dNow);						 	//�ѵ�ǰʱ�丳������
		calendar.add(Calendar.DAY_OF_MONTH, beDate);    //����Ϊǰһ��
		dBefore = calendar.getTime();   			 	//�õ�ǰһ���ʱ��
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd"); //����ʱ���ʽ
		String beforeDate = sdf.format(dBefore);    	   	   //��ʽ��ǰһ��
		//�ж��Ƿ��ֶ�����ʱ��
		if(inputType == 0){
			bDay = Integer.parseInt(beforeDate);
		}else{
			try {
				Date dDate = sdf.parse(inputDate.toString());		//����ʱ��
				Date dBefore_1 = new Date();
				Calendar calendar_1 = Calendar.getInstance();  		//�õ�����
				calendar_1.setTime(dDate);						 	//������ʱ�丳������
				calendar_1.add(Calendar.DAY_OF_MONTH, beDate);    	//����Ϊǰһ��
				dBefore_1 = calendar_1.getTime();   			 	//�õ�ǰһ���ʱ��
				SimpleDateFormat sdf_1 =new SimpleDateFormat("yyyyMMdd"); 	  //����ʱ���ʽ
				String beforeDate_1 = sdf_1.format(dBefore_1);    	   	   	  //��ʽ��ǰһ��
				bDay = Integer.parseInt(beforeDate_1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return bDay;
	}
	
	/**
	 * ��ȡǰ1��,7��,30������
	 * ת����Integer����
	 * @return
	 */
	public static Integer getTodayDate(Integer dayType){
		Date dNow = new Date();   				 	 //��ǰʱ��
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance();  //�õ�����
		calendar.setTime(dNow);						 //�ѵ�ǰʱ�丳������
		if(dayType == 1){
			calendar.add(Calendar.DAY_OF_MONTH, -2);     //����Ϊǰһ��
		}else if(dayType == 7){
			calendar.add(Calendar.DAY_OF_MONTH, -7);     //����Ϊǰ����
		}else if(dayType == 30){
			calendar.add(Calendar.DAY_OF_MONTH, -30);    //����Ϊǰ��ʮ��
		}
		dBefore = calendar.getTime();   			 //�õ�ǰһ���ʱ��
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd"); //����ʱ���ʽ
		String beforeDate = sdf.format(dBefore);    	   	   //��ʽ��ǰһ��
		Integer tday = Integer.parseInt(beforeDate.substring(beforeDate.length()-8));
		return tday;
	}
	
	/**
	 * ��ȡǰ1��,7��,30������
	 * ת����Integer����
	 * @return
	 * @throws ParseException 
	 */
	public static Integer getTodayDate(String inputDate,Integer dayType){
		//�ַ�����ʽ��ʱ��
		DateFormat df = new SimpleDateFormat("yyyyMMdd");		
		Date date = null;
		Integer tday = 0;
		try 
		{
			date = df.parse(inputDate.toString());
			Calendar calendar = Calendar.getInstance();  //�õ�����
			calendar.setTime(date);						 //������ʱ�丳������
			if(dayType == 1){
				calendar.add(Calendar.DAY_OF_MONTH, -1);     //����Ϊǰһ��
			}else if(dayType == 7){
				calendar.add(Calendar.DAY_OF_MONTH, -6);     //����Ϊǰ����
			}else if(dayType == 30){
				calendar.add(Calendar.DAY_OF_MONTH, -29);    //����Ϊǰ��ʮ��
			}
			date = calendar.getTime();   					 //��������ʱ��ǰһ���ʱ��
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd"); //����ʱ���ʽ
			String beforeDate = sdf.format(date);    	   	   //��ʽ��ǰһ��
			tday = Integer.parseInt(beforeDate.substring(beforeDate.length()-8));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tday;
	}
	
	/**
	 * ��������ͳ���ֵ�
	 * @return
	 */
	public static Map<String,String> getQdName(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("gw", "gw");
		map.put("ald", "ald");
		map.put("nb", "nb");
		map.put("wb", "wb");
		map.put("other", "other");
		return map;
	} 
	
	
	/**
	 * ��ȡ�������뷨���µ����б���
	 * @param db_ip : ������IP
	 * @param user : �û�
	 * @param pwd : ����
	 * @param db_name : ���ݿ�����
	 * @param tabTitle : "install"
	 * @return
	 */
	public List<Integer> getGswb_Log_Install_tab(String db_ip,String user,String pwd,String db_name,String tabTitle){
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
				   if(tab_name.equals(tabTitle)){
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
}
