package com.job;

import java.util.Date;

import com.action.gswb_qd.Gswb_collect_qd_action;
import com.db.info.Dictionary_DB;

/**
 * ��������ͳ��
 * ���������
 * @author ZhuYa
 *
 */
public class Gswb_qd_job {
	//param1(0:�ǵ�ǰ����,1:���ֶ�����)
	//param2(�ֶ������ʱ��)
	Integer tab_date = Dictionary_DB.getBeforeDate(0, null);
	Gswb_collect_qd_action action = new Gswb_collect_qd_action(); 
	
	public void gswb_qd_work(String dateTime,String ip01,String user01,String pwd01){
		String tab_date_07 = Dictionary_DB.getTodayDate(7).toString();  //��7�� -7
		//�ж��ֶ�����ʱ��
		if(dateTime != null && dateTime.trim().length()!=0){
			tab_date = Integer.parseInt(dateTime);
			tab_date_07 = Dictionary_DB.getTodayDate(tab_date.toString(),7).toString();  //��7�� -7
		}
		System.out.println("���������ܿ�ʼ : "+ new Date());
		//������������ͳ��
		action.insertGswb_Collect_Qd(ip01, user01, pwd01, Dictionary_DB.db_statistics,Dictionary_DB.db_gswb_log, tab_date,tab_date_07);
		System.out.println("���������ܽ��� : "+ new Date());
		System.out.println();
		
		System.out.println("����������ʼ : "+ new Date());
		//���������������ͳ��
		action.insertGswb_Collect_Qd_02(ip01, user01, pwd01, Dictionary_DB.db_statistics,Dictionary_DB.db_gswb_log, tab_date,tab_date_07);
		System.out.println("������������ : "+ new Date());
		
		System.out.println("����������ʼ : "+ new Date());
		//����������������ͳ��
		action.insertGswb_Collect_Qd_03(ip01, user01, pwd01, Dictionary_DB.db_statistics,Dictionary_DB.db_gswb_log, tab_date,tab_date_07);
		System.out.println("������������ : "+ new Date());
	}
}
