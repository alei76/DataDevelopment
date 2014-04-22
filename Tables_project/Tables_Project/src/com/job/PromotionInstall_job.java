package com.job;

import java.util.Date;

import com.action.union.PromotionAll_Action;
import com.action.union.PromotionInstall_Action;
import com.action.union.PromotionSoftware_Action;
import com.db.info.Dictionary_DB;

/**
 * װ������  ͳ��
 * �������
 * @author ZhuYa
 *
 */
public class PromotionInstall_job {
	//param1(0:�ǵ�ǰ����,1:���ֶ�����)
	//param2(�ֶ������ʱ��)
	String tab_date = Dictionary_DB.getBeforeDate(0, null).toString();
	PromotionInstall_Action action = new PromotionInstall_Action();
	PromotionAll_Action actionAll = new PromotionAll_Action();
	PromotionSoftware_Action soft = new PromotionSoftware_Action();
	
	/**
	 * ��ϸͳ�� �������
	 * @param dateTime ����ʱ��
	 * @param ip01	: localhost = 43��
	 * @param user01 
	 * @param pwd01  
	 * @param ip02 : 127��
	 * @param user02 
	 * @param pwd02 
	 */
	public void PromotionInstall_work(String dateTime,String ip01,String user01,String pwd01,String ip02,String user02,String pwd02){
		int inputType = 0;	//0:�ǵ�ǰ����	1:�ֶ�����
		//�ж��ֶ�����ʱ��
		if(dateTime != null && dateTime.trim().length()!=0){
			tab_date = dateTime;
			inputType = 1;
		}
		System.out.println("��ϸͳ�ƿ�ʼ : "+ new Date());
		//����װ��������ϸͳ�Ʊ�
		action.insertPromotionInstall(ip01, user01, pwd01, Dictionary_DB.db_zj7654_log, ip02, user02, pwd02, Dictionary_DB.db_union, tab_date,inputType,Dictionary_DB.db_gswb_log,"install");
		System.out.println("��ϸͳ�ƽ��� : "+ new Date());
	}
	
	/**
	 * ��Ч��װ�û���
	 * @param dateTime
	 * @param ip01	:	43��
	 * @param user01
	 * @param pwd01
	 * @param ip02	:	127��
	 * @param user02
	 * @param pwd02
	 */
	public void PromotionOnline_work(String dateTime,String ip01,String user01,String pwd01,String ip02,String user02,String pwd02){
		int inputType = 0;
		//�ж��ֶ�����ʱ��
		if(dateTime != null && dateTime.trim().length()!=0){
			tab_date = dateTime;
			inputType = 1;
		}
		System.out.println("��Ч��װ�û�ͳ�ƿ�ʼ : "+new Date());
		//����װ����������ʹ��ͳ�Ʊ�
		action.insertPromotionOnline(ip01, user01, pwd01, Dictionary_DB.db_statistics, ip02, user02, pwd02, Dictionary_DB.db_union, Dictionary_DB.db_gswb_log, String.valueOf(tab_date));
		System.out.println("��Ч��װ�û�ͳ�ƽ��� : "+new Date());
	}
	
	/**
	 * ������ : ����ʹ��3��
	 * ����ʹ��ͳ��  �������
	 * @param dateTime
	 */
	public void PromotionOnline_work_old(String dateTime,String ip01,String user01,String pwd01,String ip02,String user02,String pwd02){
		int inputType = 0;
		//�ж��ֶ�����ʱ��
		if(dateTime != null && dateTime.trim().length()!=0){
			tab_date = dateTime;
			inputType = 1;
		}
		System.out.println("����ʹ��ͳ�ƿ�ʼ : "+new Date());
		//����װ����������ʹ��ͳ�Ʊ�
		action.insertPromotionOnlineOld(ip01, user01, pwd01, Dictionary_DB.db_zj7654_log, ip02, user02, pwd02, Dictionary_DB.db_union,inputType,Integer.parseInt(tab_date),"online",Dictionary_DB.db_gswb_log);
		System.out.println("����ʹ��ͳ�ƽ��� : "+new Date());
	}
	
	/**
	 * װ�������ܱ�  �������
	 * @param dateTime
	 * @param ip	: 127��
	 * @param user
	 * @param pwd
	 * @param ip01	: 43��
	 * @param user01
	 * @param pwd01
	 */
	public void PromotionAll_work(String dateTime,String ip,String user,String pwd,String ip01,String user01,String pwd01){
		String tab_date_01 = Dictionary_DB.getTodayDate(1).toString();  //����   -2
		String tab_date_07 = Dictionary_DB.getTodayDate(7).toString();  //��7�� -7
		String tab_date_30 = Dictionary_DB.getTodayDate(30).toString(); //��30�� -30
		
		//�ж��ֶ�����ʱ��
		if(dateTime != null && dateTime.trim().length()!=0){
			tab_date = dateTime;
			tab_date_01 = Dictionary_DB.getTodayDate(tab_date.toString(),1).toString();  //����   -2
			tab_date_07 = Dictionary_DB.getTodayDate(tab_date.toString(),7).toString();  //����   -7
			tab_date_30 = Dictionary_DB.getTodayDate(tab_date.toString(),30).toString();  //����   -30
		}
		
		System.out.println("װ�������ܱ�ʹ��ͳ�ƿ�ʼ : "+new Date());
		//����װ����������ʹ��ͳ�Ʊ�
		actionAll.insertPromotionAll(ip, user, pwd, Dictionary_DB.db_union, tab_date, tab_date_01, tab_date_07, tab_date_30,ip01,user01,pwd01,Dictionary_DB.db_statistics);
		System.out.println("װ�������ܱ�ʹ��ͳ�ƽ��� : "+new Date());
	}
	
	/**
	 * ����װ�������������ͳ��
	 * @param dateTime
	 * @param ip	: 127��
	 * @param user
	 * @param pwd
	 * @param ip01	:	43��
	 * @param user01
	 * @param pwd01
	 */
	public void PromotionSoft_work(String dateTime,String ip,String user,String pwd,String ip01,String user01,String pwd01){
		//�ж��ֶ�����ʱ��
		if(dateTime != null && dateTime.trim().length()!=0){
			tab_date = dateTime;
		}
		
		System.out.println("װ�������������ʹ��ͳ�ƿ�ʼ : "+new Date());
		//����װ����������ʹ��ͳ�Ʊ�
		soft.insertPromotionInstall(Dictionary_DB.db_zj7654_log,tab_date,ip01,user01,pwd01,Dictionary_DB.db_statistics,ip,user,pwd,Dictionary_DB.db_union);
		System.out.println("װ�������������ʹ��ͳ�ƽ��� : "+new Date());
	}
}
