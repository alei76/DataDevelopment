package com.server.union;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dao.union.PromotionInstallDao;
import com.db.info.DataBase_Info;
import com.entity.statistics.Gswb_collect_qd;
import com.entity.statistics.Online_collect_gswb;
import com.entity.union.Promotion_Install;
import com.entity.union.Promotion_Online;
import com.entity.union.Promotion_Software;

/**
 * װ��������ϸ
 * �߼�ҵ���
 * @author ZhuYa
 *
 */
public class PromotionInstallServer {
	PromotionInstallDao pitDao = new PromotionInstallDao();
	
	/**
	 * ����װ�����˰�װ����
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public List<Promotion_Install> getPromotionInstall(String db_ip,String user,String pwd,String db_name,String todayDate){
		//��ȡ���߱�ͳ�Ƽ���
		List<Promotion_Install> lists = pitDao.getPromotionInstall(db_ip, user, pwd, db_name, todayDate);
		return lists;
	}
	
	/**
	 * ���ø�ƽ��Ч��װ�û�
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tableType
	 * @param inputType
	 * @param inputDate
	 * @return
	 */
	public List<Promotion_Online> getPromotionOnline(String db_ip,String user,String pwd,String db_name,String tableType,Integer inputType,Integer inputDate){
		//��ȡǰ15���online��ͷ
		List<Integer> onLine15Days = getGswb30Days(db_ip,user,pwd,db_name,inputDate,inputType,tableType,15);
		//��ȡǰ15��online���ݼ���
		List<Promotion_Online> lists = pitDao.getPromotionOnline(db_ip, user, pwd, db_name, inputType, inputDate,onLine15Days);
		//�����ܽ����
		List<Promotion_Online> listAll = new ArrayList<Promotion_Online>();
		return listAll;
	}
	
	/**
	 * ������
	 * ����װ�����˻�ȡ15���������û����ڵ���2����û�
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tableType
	 * @param inputType
	 * @param inputDate
	 * @return
	 */
	public List<Promotion_Online> getPromotionOnlineOld2(String db_ip,String user,String pwd,String db_name,String tableType,Integer inputType,Integer inputDate){
//		//��ȡ��ͷ
//		List<Integer> onLineList = pitDao.getGswb_Log_Tab_Title(db_ip, user, pwd, db_name, tableType);
		//��ȡǰ15���online��ͷ
		List<Integer> onLine15Days = getGswb30Days(db_ip,user,pwd,db_name,inputDate,inputType,tableType,15);
		//��ȡǰ15��online���ݼ���
		List<Promotion_Online> lists = pitDao.getPromotionOnline(db_ip, user, pwd, db_name, inputType, inputDate,onLine15Days);
		//15�����������
		List<Promotion_Online> listsOnly = getOnlineOnly(lists);
		//�����ܽ����
		List<Promotion_Online> listAll = new ArrayList<Promotion_Online>();
		//��ȡ15���������û����ڵ���2����û�
		for(int i = 0; i<listsOnly.size();i++){
				if( listsOnly.get(i).getDay() >= 2 ){
						Promotion_Online  obj = new Promotion_Online();
						obj.setUid(listsOnly.get(i).getUid());						 		 					//��������
						obj.setSoftware_id(listsOnly.get(i).getSoftware_id());			 	    	//���id
						obj.setSoftware_version(listsOnly.get(i).getSoftware_version());	 	//����汾
						obj.setPc_id(listsOnly.get(i).getPc_id());	 		 	 			//�汾 
						obj.setDateline(listsOnly.get(i).getDateline());					//ʱ��
						obj.setId(listsOnly.get(i).getId());								 		//�嵥��ID
						obj.setNum(listsOnly.get(i).getDay());  								//����
						listAll.add(obj);
				}
		}
		return listAll;
	}
	
	/**
	 * ������
	 * װ������ ��������ʹ��3����û�
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tableType
	 * @param inputType
	 * @param inputDate
	 * @return
	 */
	public List<Promotion_Online> getPromotionOnlineOld(String db_ip,String user,String pwd,String db_name,String tableType,Integer inputType,Integer inputDate){
		List<Promotion_Online> lists = null;
		List<Integer> onLineList = pitDao.getGswb_Log_Tab_Title(db_ip, user, pwd, db_name, tableType);
		if(onLineList.size()-1>=3){
			lists = pitDao.getPromotionOnlineOld(db_ip, user, pwd, db_name, inputType, inputDate);
		}
		return lists;
	}
	
	/**
	 * װ���������߼���,���ز��� ��������
	 * ���� : �û�15�����ж����� ����
	 * @param lists
	 * @return
	 */
	public List<Promotion_Online> getOnlineOnly( List<Promotion_Online> lists ){
		List<Promotion_Online> listAll = new ArrayList<Promotion_Online>();
		if(lists.size() > 0){
				for(int i = 0 ; i < lists.size(); i++){
					String pc_id = lists.get(i).getPc_id();
					String softID = lists.get(i).getSoftware_id();
					String softVer = lists.get(i).getSoftware_version();
					int uid = lists.get(i).getUid();
					int dateline = lists.get(i).getDateline();
					int onlineId = lists.get(i).getId();
					int flag = 1;		//��ʶ
					for(int j = lists.size()-1; j > i;  j--){
							String pc_id02 = lists.get(j).getPc_id() ;
							int uid02 = lists.get(j).getUid() ;
							if(pc_id.equals(pc_id02) && uid == uid02){
								flag = flag+1 ;
								lists.remove(j);
							}
					}
					Promotion_Online  obj = new Promotion_Online();
					obj.setUid(uid);						 		 	//��������
					obj.setSoftware_id(softID);			 	    //���id
					obj.setSoftware_version(softVer);	 	//����汾
					obj.setPc_id(pc_id);	 		 	 				//�汾 
					obj.setDateline(dateline);					//ʱ��
					obj.setId(onlineId);								 //�嵥��ID
					obj.setDay(flag);  								//����
					listAll.add(obj);
			}
		}
		return listAll;
	}
	
	
	/**
	 * ���ݾ�����
	 * ����װ����������ʹ�� ����
	 * @param lists
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 */
	public void insertPromotionOnline_BAK(List<Promotion_Online> lists,String db_ip,String user,String pwd,String db_name){
		if(lists.size()>0 && lists!=null){
			pitDao.insert_PromotionOnline_BAK(lists, db_ip, user, pwd, db_name);
		}
	}
	
	/**
	 * ����װ����������ʹ�� ����
	 * @param lists
	 * @param db_ip : 127
	 * @param user
	 * @param pwd
	 * @param db_name : union
	 * @param tab_name
	 */
	public void insertPromotionOnline(List<Promotion_Install> lists,String db_ip,String user,String pwd,String db_name){
		if(lists.size()>0 && lists!=null){
			pitDao.insert_PromotionOnline(lists, db_ip, user, pwd, db_name);
		}
	}
	
	/**
	 * ����װ������ ����
	 * @param lists
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 */
	public void insertPromotionInstall(List<Promotion_Install> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
		if(lists.size()>0 && lists!=null){
			pitDao.insert_PromotionInstall(lists, db_ip, user, pwd, db_name);
		}
	}
	
	
	
	
	/**
	 * ��ȡǰ30���ͷ
	 * @param db_ip	 : 43���
	 * @param user	 
	 * @param pwd
	 * @param db_name :  gswb_log��
	 * @param todayDate :  ��ǰʱ��
	 * @param input	:  1(�ֶ�����ʱ��) : 0(ϵͳ�Զ�ʱ��)
	 * @param tableType :  install(��װ��LOG)
	 * @param tabTitleNum: ǰN/30��ı�ͷ
	 * @return
	 */
	public List<Integer> getGswb30Days(String db_ip,String user,String pwd,String db_name,Integer todayDate,int input,String tableType,int tabTitleNum){
		int lastDate = 20140415;   		// ҵ������ʱ���20140415�տ�ʼ
		int flag02 = 0;			// ��ʶ30��
		int num = 0;   			// ������
		//����洢30���ͷ
		List<Integer> tab_list_sum = new ArrayList<Integer>();
		//��ǰʱ��
//		Integer tab_date = Integer.parseInt(tab_name);
		//��ȡ����install����
		List<Integer> installList = pitDao.getGswb_Log_Tab_Title(db_ip, user, pwd, db_name, tableType);
		//��ȡ����ʱ���30�켯��
		List<Integer> installList02 = new ArrayList<Integer>();	
		//tabTitleNum : 30
		if(installList.size()>0 && installList.size() < tabTitleNum){
				for(int i = 0;i<installList.size();i++){
						if(installList.get(i) >= lastDate &&todayDate >= installList.get(i)){
								installList02.add(installList.get(i));
						}
				}
		}else if(installList.size() > 0 && installList.size() > tabTitleNum){ 		//tabTitleNum : 30
				for(int i = 0;i<installList.size();i++){
						if(installList.get(i) >= lastDate && todayDate >= installList.get(i) && flag02 <= tabTitleNum-1){		//tabTitleNum : 29
								flag02 = flag02+1;
								installList02.add(installList.get(i));
						}
				}
				num = flag02;
		}
		//�ж����ֶ����룬��ǰʱ��
		if(input == 0){
			for(int i = 0;i<num;i++){
				//���С������ʱ��ı�ͷ
				tab_list_sum.add(installList02.get(i));
			}
		}else{
			for(int i = 0;i<num;i++){
				tab_list_sum.add(installList02.get(i));
			}
		}
		return tab_list_sum;
	}
	
	/**
	 * ��ȡ��װ��30�����ݼ���
	 * @param tabList
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @param input
	 * @param tableType
	 * @param tabTitle
	 * @return
	 */
	public List<Promotion_Install> getGswb30DayInstall(String db_ip,String user,String pwd,String db_name,Integer tab_name,int input,String tableType,Integer tabTitle){
		//���ػ�ȡ30������
		List<Promotion_Install>  lists = pitDao.getGswbInstallLog(db_ip, user, pwd, db_name,tabTitle);
		return lists;
	}
	
	/**
	 * ��ȡ���߱�30�����ݼ���
	 * @param tabList
	 * @param db_ip	: 43��
	 * @param user
	 * @param pwd
	 * @param db_name : gswb_log��
	 * @param tab_name : install
	 * @param input : 1(�ֶ�����ʱ��) : 0(ϵͳ�Զ�ʱ��)
	 * @param tableType : install(��װ��LOG)
	 * @param tabTitle : ��ʱ��
	 * @return
	 */
	public List<Promotion_Install> getGswb30DayOnline(String db_ip,String user,String pwd,String db_name,Integer tab_name,int input,String tableType,Integer tabTitle){
		//���ػ�ȡ30������
		List<Promotion_Install> lists = pitDao.getGswbOnlineLog(db_ip, user, pwd, db_name, tabTitle, input);
		return lists;
	}
	
	/**
	 * ��ȡ��123IP��ַͳ����
	 * @param db_ip	: 127��
	 * @param user
	 * @param pwd
	 * @param db_name	:	union
	 * @param inputType	: 	ͳ��ʱ��
	 * @return
	 */
	public List<Promotion_Software> getHao123(String db_ip,String user,String pwd,String db_name,int inputDate){
		List<Promotion_Software> lists = pitDao.getHao123(db_ip, user, pwd, db_name, inputDate);
		return lists;
	}
	
	/**
	 * ��ȡװ������15��������
	 * @param db_ip	: 	127��
	 * @param user	
	 * @param pwd
	 * @param db_name	:	union
	 * @param inputDate	:	����ʱ��
	 * @return
	 */
	public List<Promotion_Install> getUnionPromotionInstall(String db_ip,String user,String pwd,String db_name,String inputDate){
		List<Promotion_Install> lists = pitDao.getUnionPromotionInstall(db_ip, user, pwd, db_name, inputDate);
		return lists;
	}
	
	/**
	 * ������Ч�û���װ�� : �¶�Ψһ������ - ���ɰ�װ��
	 * @param db_ip	: 43��
	 * @param user
	 * @param pwd
	 * @param db_name : statistics
	 * @param db_ip02	: 127��
	 * @param user02
	 * @param pwd02
	 * @param db_name02	: union
	 * @param db_name03 : gswb_log
	 * @param inputDate
	 * @return
	 */
	public List<Promotion_Install> getUnionInstallAll(String db_ip,String user,String pwd,String db_name,String db_ip02,String user02,String pwd02,String db_name02,String db_name03,String inputDate){
		List<Promotion_Install> objAllList = new ArrayList<Promotion_Install>();	//���巵�ؽ����
		List<Promotion_Install> obj1List = pitDao.getInstallOkGswb1(db_ip, user, pwd, db_name, inputDate);		// ��ȡ�¶�Ψһ������
		List<Promotion_Install> obj2List = pitDao.getInstallWbGswb2(db_ip, user, pwd, db_name03, inputDate);		// ��ȡ���ɰ�װ��
		List<Promotion_Install> objInstallList = getUnionEffectiveInstall(obj1List , obj2List);		//�ȶ� : ��ȡ��Ч��װ�û���Ϣ
		
		List<Integer> obj3 = pitDao.getUserId3(db_ip02, user02, pwd02, db_name02);	// ��ȡװ�������˺�ID
		//��ֹ����������ȷ��ȡ�˺�ID�� ��װ������USER��ID����
		if(objInstallList.size()>0){
				for(int i = 0 ; i < objInstallList.size(); i++){
						for(int j = 0; j<obj3.size(); j++){
								if(objInstallList.get(i).getUserId().equals(obj3.get(j).toString())){
									Promotion_Install obj = new Promotion_Install();
									obj.setUserId(objInstallList.get(i).getUserId());
									obj.setNum(objInstallList.get(i).getNum());
									obj.setDateline(Integer.parseInt(inputDate));
									objAllList.add(obj);
								}
						}
				}
		}
		return objAllList;
	}
	
	/**
	 *  �Աȹ��̱� : ��Ч��װ�û�
	 * @param obj1	 : ��ȡ�¶�Ψһ������
	 * @param obj2 : ��ȡ���ɰ�װ��
	 * @return
	 */
	public List<Promotion_Install> getUnionEffectiveInstall(List<Promotion_Install> obj1List, List<Promotion_Install> obj2List){
			List<Promotion_Install> installAll = new ArrayList<Promotion_Install>();
			if(obj1List.size() > 0 && obj2List.size()> 0){
					for(int i = 0; i < obj1List.size(); i++){	 //��ȡ�¶�Ψһ������
						Promotion_Install obj = new Promotion_Install();
						int flag = 0;
						String userId = obj1List.get(i).getUserId();
						int num = obj1List.get(i).getNum() ;
						obj.setUserId(userId);
						
							for(int j = 0; j< obj2List.size(); j++){	//��ȡ���ɰ�װ��
									String userId02 = obj2List.get(j).getUserId();
									int num02 = obj2List.get(j).getNum();
									//��Ч��װ��  : ��ȡ�¶�Ψһ������ - ��ȡ���ɰ�װ��
									if(userId.equals(userId02)){
											flag = flag+1;	// ����˺�
											// �¶�Ψһ������ - ���ɰ�װ��
											int value = num - num02;
											if(value>=0){
												obj.setNum(value);
											}else{ 
												//��ֹ���ָ�ֵ
												obj.setNum(0);
											}
									}
							}
							//���ɰ�װ�����û����Ϊ��
							if(flag==0){
								obj.setNum(num);
							}
							installAll.add(obj);
					}
			}else if(obj1List.size() > 0 && obj2List.size() <= 0){ 	//������ɰ�װ��Ϊ����
				installAll = obj1List;
			}
			return installAll;
		}
}
