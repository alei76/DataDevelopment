package com.action.union;


import com.entity.union.Promotion_All;
import com.server.union.PromotionAllServer;

/**
 * װ�������ܱ� ͳ��
 * @author ZhuYa
 *
 */
public class PromotionAll_Action {
	
		/**
		 * ����װ������ �ܱ� (45���Կ�)��(127��ʽ��)
		 * @param db_ip	: (43����ʽ��)
		 * @param user
		 * @param pwd
		 * @param db_name
		 * @param db_ip01 :  (45���Կ�)��(127��ʽ��)
		 * @param user01
		 * @param pwd01
		 * @param db_name01
		 * @param tab_name	: ͳ����
		 * @param tab_name_01 : ����
		 * @param tab_name_07 : ��7��
		 * @param tab_name_30 : ��30��
		 */
		public void insertPromotionAll(String db_ip,String user,String pwd,String db_name,String tab_name,String tab_name_01,String tab_name_07,String tab_name_30,String db_ip01,String user01,String pwd01,String db_name01){
				double nextNum = 0.0;			//���ջ�Ծ��
				double next7Num = 0.0;		//��7�ջ�Ծ��
				double next30Num = 0.0;  	//��30�ջ�Ծ��
				PromotionAllServer qdServer = new PromotionAllServer();
				//��ȡװ������ ��װ����
				Promotion_All obj =  qdServer.getPromotionInstall(db_ip, user, pwd, db_name, tab_name, tab_name_01, tab_name_07, tab_name_30);
				if(obj != null){
						//����װ�������ܱ�
						qdServer.insertPromotionAll(obj, db_ip01, user01, pwd01, db_name01);
						
						// ����ע��/���ջ�Ծ�û���
						if(obj.getNextRegInsActive() != 0){
							nextNum = Math.round((obj.getNextRegInsActive()*1.00/obj.getLoginNum())*100);
						}
						qdServer.updatePromotionAll(nextNum,obj.getNextRegInsActive(), db_ip01, user01, pwd01, db_name01, tab_name_01);  //����װ�������ܱ�(���ջ�Ծ��)
						
						// ����ע��/7�ջ�Ծ�û���
						if(obj.getNext7DayRegInsActive() != 0){
							next7Num = Math.round((obj.getNext7DayRegInsActive()*1.00/obj.getLoginNum())*100);
						}
						qdServer.updatePromotionAll(next7Num,obj.getNext7DayRegInsActive(), db_ip01, user01, pwd01, db_name01, tab_name_07); //����װ�������ܱ�(7�ջ�Ծ��)
						
						// ����ע��/30�ջ�Ծ�û���
						if(obj.getNext30DayRegInsActive() != 0){
							next30Num = Math.round((obj.getNext30DayRegInsActive()*1.00/obj.getLoginNum())*100);
						}
						qdServer.updatePromotionAll(next30Num,obj.getNext30DayRegInsActive(), db_ip01, user01, pwd01, db_name01, tab_name_30); //����װ�������ܱ�(30�ջ�Ծ��)
				}
		}
}
