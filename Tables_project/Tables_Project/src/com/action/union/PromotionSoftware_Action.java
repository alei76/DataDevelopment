package com.action.union;

import java.util.ArrayList;
import java.util.List;

import com.entity.union.Promotion_Software;
import com.server.union.PromotionSoftwareServer;

/**
 * װ������ �������ͳ��
 * @author ZhuYa
 *
 */
public class PromotionSoftware_Action {
	PromotionSoftwareServer softServer = new PromotionSoftwareServer();
	
	public void insertPromotionInstall(String db_name_01,String tab_name,String ip02,String user02,String pwd02,String db_name_02,String ip,String user,String pwd,String db_name03){

		//��ȡװ�����˺�123���� : 127�� union
		List<Promotion_Software> haoLists = softServer.getHao123(ip, user, pwd, db_name03, Integer.parseInt(tab_name));
		
		if(haoLists != null){
			//����43��
			softServer.insertPromotionInstall(haoLists, ip02, user02, pwd02, db_name_02);
		}
	}
	
	public void insertPromotionInstall_BAK(String db_name_01,String tab_name,String ip02,String user02,String pwd02,String db_name_02,String ip,String user,String pwd,String db_name03){
		int flag = 0 ;   //���
		Promotion_Software obj = null;
		Promotion_Software obj02 = null;
		List<Promotion_Software> gswbList = new ArrayList<Promotion_Software>();   //���: �������뷨�����ƹ����
		List<Promotion_Software> haoList02 = new ArrayList<Promotion_Software>();		//���: ��123
		//��ȡװ������ ��װ���� : 43��zj7654_log
		List<Promotion_Software> lists =  softServer.getPromotionSoftware(ip02, user02, pwd02, db_name_01, tab_name);
		//��ȡװ�����˺�123���� : 127�� union
		List<Promotion_Software> haoLists = softServer.getHao123(ip, user, pwd, db_name03, Integer.parseInt(tab_name));
		//������������ܼ���
		List<Promotion_Software> listAll = new ArrayList<Promotion_Software>();
		
		if(lists.size()>0 && haoLists.size()>0){
				for(int i = 0;i<lists.size();i++){
					obj = new Promotion_Software();
					obj.setSoftName(lists.get(i).getSoftName());
					obj.setCredit(lists.get(i).getCredit());
					obj.setPcNum(lists.get(i).getPcNum());
					obj.setUseNum(lists.get(i).getUseNum());
					obj.setTime(lists.get(i).getTime());
					
					for(int j = 0;j<haoLists.size();j++){
							flag = flag+1;
							if(flag <= haoLists.size() ){
									//�ж��Ƿ��123
									if(lists.get(i).getSoftName().equals(haoLists.get(j).getSoftName()) && lists.get(i).getTime()==haoLists.get(j).getTime()){
										obj.setCredit(haoLists.get(j).getCredit());    //��� : �������뷨���� �ƹ����
									}else{
										obj02 = new Promotion_Software();
										obj02.setSoftName(haoLists.get(j).getSoftName());
										obj02.setCredit(haoLists.get(j).getCredit());
										obj02.setPcNum(haoLists.get(j).getPcNum());
										obj02.setUseNum(haoLists.get(j).getUseNum());
										obj02.setTime(haoLists.get(j).getTime());
										obj02.setCredit(haoLists.get(j).getCredit());
									}
									haoList02.add(obj02);
							}
					}
					gswbList.add(obj);
				}

				//����� : �������뷨(gswbList) �� ��123(haoList) ���ݼ��Ϻϲ���lists������ �� ��ֵ��������ܼ���(listAll)
				if(haoLists.size()>0){
						for(int k = 0; k<haoList02.size(); k++){
							gswbList.add(haoList02.get(k));
						}
				}
				listAll = gswbList ;
		}else if(lists.size()<=0 && haoLists.size()>0){
			listAll = haoLists;
		}else if(lists.size()>0 && haoLists.size()<=0){
			listAll = lists;
		}
		
		if(listAll != null){
			//����43��
			softServer.insertPromotionInstall(listAll, ip02, user02, pwd02, db_name_02);
		}
	}
}
