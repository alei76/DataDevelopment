package com.action.union;

import java.util.ArrayList;
import java.util.List;

import com.entity.union.Promotion_Install;
import com.entity.union.Promotion_Online;
import com.entity.union.Promotion_Software;
import com.server.union.PromotionInstallServer;

/**
 * װ��������ϸ ͳ��
 * @author ZhuYa
 *
 */
public class PromotionInstall_Action {
	PromotionInstallServer qdServer = new PromotionInstallServer();
	/**
	 * װ��������ϸ ��װͳ��
	 * @param db_ip	: 43�� 
	 * @param user
	 * @param pwd
	 * @param db_name	:	db_zj7654_log
	 * @param db_ip_01	:	127��
	 * @param user_01
	 * @param pwd_01
	 * @param db_name_01 : db_union
	 * @param todayDate   : ��ǰʱ��
	 * @param input	:	��������
	 * @param tab_name02 : db_statistics
	 * @param tableType	:   ��ͷ����(install/online)
	 */
	public void insertPromotionInstall(String db_ip,String user,String pwd,String db_name,String db_ip_01,String user_01,String pwd_01,String db_name_01,String todayDate,int input,String tab_name02,String tableType){
		
		//��ȡװ������ ��װ���� : 43������zj7654_log��
		List<Promotion_Install> lists =  qdServer.getPromotionInstall(db_ip, user, pwd, db_name, todayDate);
		//��ȡ�洢30���ͷ
		List<Integer> tab_list_sum = qdServer.getGswb30Days(db_ip, user, pwd, tab_name02, Integer.parseInt(todayDate), input, tableType,30);
		List<Promotion_Install> listInstall = null;
		if(tab_list_sum.size() > 0){
			//30���ͷ
			for(int k = 0;k<tab_list_sum.size();k++){
					//��ȡ��װ��30�����ݼ��� : 43������ statitics��
					listInstall = qdServer.getGswb30DayInstall(db_ip, user, pwd, tab_name02, Integer.parseInt(todayDate), input, tableType,tab_list_sum.get(k));
					//ÿ��ȥ��
					if(listInstall.size() > 0){
							//װ�����˼���
							for(int i=0;i<lists.size();i++){
									String pc_id = lists.get(i).getPc_id();
									String softwareId = lists.get(i).getSoftware_id();
									String softVerion = lists.get(i).getSoftware_version();
									//30��ļ���
									for(int j = 0;j<listInstall.size();j++){
											String pc_id02 = listInstall.get(j).getPc_id();
											String softwareId02 = listInstall.get(j).getSoftware_id();
											String softVerion02 = listInstall.get(j).getSoftware_version();
											//װ�����˵İ�װ�û� �ڹ������뷨�û��д��ڵĻ���ȥ��
											if(pc_id.equals(pc_id02) && softwareId.equals(softwareId02) && softVerion.equals(softVerion02)){
												lists.remove(j);
											}
									}
							}
					}
					listInstall.clear();
			}
		}
		
		if(lists!=null){
			qdServer.insertPromotionInstall(lists, db_ip_01, user_01, pwd_01, db_name_01, todayDate);
		}
	}
	
	/**
	 *  ���� ����ȫ����GSWB Online30���
	 * װ������ ����ʹ�� ͳ��
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param db_ip_01
	 * @param user_01
	 * @param pwd_01
	 * @param db_name_01
	 * @param inputType	: ��������
	 * @param inputDate : ����ʱ��
	 * @param tableType : �����ͷ����(install/online)
	 */
	public void insertPromotionOnline_BAK(String db_ip,String user,String pwd,String db_name,String db_ip_01,String user_01,String pwd_01,String db_name_01,Integer inputType,Integer inputDate,String tableType,String tab_name02){
		//��ȡװ������ 15������2������ʹ�ü���
		List<Promotion_Online> lists =  qdServer.getPromotionOnline(db_ip, user, pwd, db_name,tableType,inputType, inputDate);
		//��ȡ�洢30���ͷ
		List<Integer> tab_list_sum = qdServer.getGswb30Days(db_ip, user, pwd, tab_name02, inputDate, inputType, tableType,30);
		if(tab_list_sum.size() > 0){
			//��ȡ30������,��ÿ��ȥ��
			for(int k = 0;k<tab_list_sum.size();k++){
				//��ȡ���߱�30�����ݼ���
				List<Promotion_Install> listOnline = qdServer.getGswb30DayOnline(db_ip, user, pwd, tab_name02, inputDate, inputType, tableType,tab_list_sum.get(k));
				if(listOnline.size() > 0){
						for(int i = 0;i<lists.size();i++){
								String pc_id = lists.get(i).getPc_id();
								String softwareId = lists.get(i).getSoftware_id();
								String softVerion = lists.get(i).getSoftware_version();
								for(int j = 0;j<listOnline.size();j++){
										String pc_id02 = listOnline.get(i).getPc_id();
										String softwareId02 = listOnline.get(i).getSoftware_id();
										String softVerion02 = listOnline.get(i).getSoftware_version();
										//װ�����˵������û� �ڹ������뷨�û��д��ڵĻ���ȥ��
										if(pc_id.equals(pc_id02) && softwareId.equals(softwareId02) && softVerion.equals(softVerion02)){
											lists.remove(i);
										}
								}
						}
				}
				listOnline.clear();
			}
		}
	
		if(lists!=null){
			qdServer.insertPromotionOnline_BAK(lists, db_ip_01, user_01, pwd_01, db_name_01);
		}
	}
	
	/**
	 * 15������2��ʹ�õ������û�
	 * 1. promotionInstall�������������ݶ��� ��GSWB install30���������ص�
	 * 2. ����ֱ���� װ�����˷���  15������ʹ������2����û� ֱ�Ӹ� 1.���� ��ȡ������  ���� promotiOnline
	 * װ������ ����ʹ�� ͳ��
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param db_ip_01
	 * @param user_01
	 * @param pwd_01
	 * @param db_name_01
	 * @param inputType	: ��������
	 * @param inputDate : ����ʱ��
	 * @param tableType : �����ͷ����(install/online)
	 */
	public void insertPromotionOnline_BAK2(String db_ip,String user,String pwd,String db_name,String db_ip_01,String user_01,String pwd_01,String db_name_01,Integer inputType,Integer inputDate,String tableType,String tab_name02){
		//(1)��ȡװ������ 15������2������ʹ�ü���
		List<Promotion_Online> lists =  qdServer.getPromotionOnline(db_ip, user, pwd, db_name,tableType,inputType, inputDate);
		//(2)��ȡpromotionInstall15�찲װ��
		List<Promotion_Install> listInstall = qdServer.getUnionPromotionInstall(db_ip_01, user_01, pwd_01, db_name_01, inputDate.toString());
		//�����ܼ���: //��ȡ(1)��ƥ��(2)�е�����
		List<Promotion_Online> listAll = new ArrayList<Promotion_Online>();
		
		//��ȡ(1)��ƥ��(2)�е�����
		if(lists.size() > 0 && listInstall.size() > 0){
				for(int i = 0; i<lists.size(); i++){
						int uid = lists.get(i).getUid();
						String softwareId = lists.get(i).getSoftware_id();
						String softVerion = lists.get(i).getSoftware_version();
						int dateline = lists.get(i).getDateline();
						String pc_id = lists.get(i).getPc_id();
						int online_id = lists.get(i).getId();
						
						for(int j = 0; j<listInstall.size(); j++){
								int uid02 = listInstall.get(j).getUid();
								String softwareId02 = listInstall.get(j).getSoftware_id();
								String softVerion02 = listInstall.get(j).getSoftware_version();
								String pc_id02 = listInstall.get(j).getPc_id();
								//��ͬ���������
								if(pc_id.equals(pc_id02) && softwareId.equals(softwareId02) && softVerion.equals(softVerion02) && uid == uid02){
										Promotion_Online online = new Promotion_Online();
										online.setUid(uid);
										online.setSoftware_id(softwareId);
										online.setSoftware_version(softVerion);
										online.setDateline(dateline);
										online.setPc_id(pc_id);
										online.setId(online_id);
										listAll.add(online);
								}
						}
				}
		}
		//����
		if(listAll.size() > 0 ){
			qdServer.insertPromotionOnline_BAK(listAll, db_ip_01, user_01, pwd_01, db_name_01);
		}

	}
	
	/**
	 * ��������
	 * װ������ ��Ч��װ�û��� ͳ��
	 * ���ø�ƽ�Ǳߵ���Ч�û����Ľӿ�
	 * @param db_ip : 43��
	 * @param user
	 * @param pwd
	 * @param db_name : statistics
	 * @param db_ip02 : 127��
	 * @param user02
	 * @param pwd02
	 * @param db_name02 : union
	 * @param db_name03 : gswb_log
	 * @param inputDate
	 */
	public void insertPromotionOnline(String db_ip,String user,String pwd,String db_name,String db_ip02,String user02,String pwd02,String db_name02,String db_name03,String inputDate){
		//��ȡ��Ч��װ�û�
		List<Promotion_Install> listAll = qdServer.getUnionInstallAll(db_ip, user, pwd, db_name, db_ip02, user02, pwd02, db_name02, db_name03, inputDate);
		
		//����127��� ��Ч��װ�û�����
		if(listAll.size() > 0 ){
			qdServer.insertPromotionOnline(listAll, db_ip02, user02, pwd02, db_name02);
		}

	}
	
	/**
	 * ����������3������ʹ�õ��û�
	 * װ������ ����ʹ�� ͳ��
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param db_ip_01
	 * @param user_01
	 * @param pwd_01
	 * @param db_name_01
	 * @param inputType	: ��������
	 * @param inputDate : ����ʱ��
	 * @param tableType : �����ͷ����(install/online)
	 */
	public void insertPromotionOnlineOld(String db_ip,String user,String pwd,String db_name,String db_ip_01,String user_01,String pwd_01,String db_name_01,Integer inputType,Integer inputDate,String tableType,String tab_name02){
		//��������ʹ��3����û�
		List<Promotion_Online> lists =  qdServer.getPromotionOnlineOld(db_ip, user, pwd, db_name,tableType,inputType, inputDate);
		//����
		if(lists.size() > 0 ){
			qdServer.insertPromotionOnline_BAK(lists, db_ip_01, user_01, pwd_01, db_name_01);
		}

	}
	
	
	
//	/**
//	 * �����123����
//	 * @param db_ip
//	 * @param user
//	 * @param pwd
//	 * @param db_name
//	 * @param db_ip_01
//	 * @param user_01
//	 * @param pwd_01
//	 * @param db_name_01
//	 * @param inputDate
//	 */
//	public void insertHao123(String db_ip,String user,String pwd,String db_name,String db_ip_01,String user_01,String pwd_01,String db_name_01,Integer inputDate){
//		//��ȡ��123IPͳ����
//		List<Promotion_Software> lists = qdServer.getHao123(db_ip_01, user_01, pwd_01, db_name_01, inputDate);
//		
//	}
}
