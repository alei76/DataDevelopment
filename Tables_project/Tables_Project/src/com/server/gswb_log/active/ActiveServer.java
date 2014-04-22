package com.server.gswb_log.active;

import java.util.ArrayList;
import java.util.List;

import com.dao.gswb_log.active.ActiveDao;
import com.entity.statistics.Active_collect_gswb;

/**
 * �������뷨
 * �߼�ҵ���
 * ��Ծ��ͳ��
 * @author ZhuYa
 *
 */
public class ActiveServer {
	ActiveDao activeDao = new ActiveDao();
	
	/**
	 * �������뷨��Ծ���ͳ��
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public List<Active_collect_gswb> getGswb_Log_All_Active_01(String db_ip,String user,String pwd,String db_name,String tab_name){
		//��ȡ�����û���,�����û���Ծ����
		List<Active_collect_gswb> lists_01 = activeDao.getGswb_Log_All_Active_01(db_ip, user, pwd, db_name, tab_name);
		//��ȡ��������
		List<Active_collect_gswb> lists_02 = activeDao.getGswb_Log_only_Active_02(db_ip, user, pwd, db_name, tab_name);
		
		//�ܼ���
		List<Active_collect_gswb> lists_sum = null;
		if(lists_01.size()>0 && lists_02.size()>0){
			lists_sum = new ArrayList<Active_collect_gswb>();
			for(int i=0;i<lists_01.size();i++){
				Active_collect_gswb obj = new Active_collect_gswb();
				String qid_01 = lists_01.get(i).getQid();
				String softVer_01 = lists_01.get(i).getSoftVer();
				
				obj.setQid(lists_01.get(i).getQid());
				obj.setSoftVer(lists_01.get(i).getSoftVer());
				obj.setAdvertIdentifier(lists_01.get(i).getAdvertIdentifier());
				obj.setActiveNum(lists_01.get(i).getActiveNum());
				obj.setNewUserActiveNum(lists_01.get(i).getNewUserActiveNum());
				obj.setAdvertDate(lists_01.get(i).getAdvertDate());
				
				for(int j=0;j<lists_02.size();j++){
					String qid_02 = lists_02.get(j).getQid();
					String softVer_02 = lists_02.get(j).getSoftVer();
					//���QID��ͬ�����
					if(qid_01.equals(qid_02) && softVer_01.equals(softVer_02)){
						obj.setInputLV1Num(lists_02.get(j).getInputLV1Num());
						obj.setInputLV1Num(lists_02.get(j).getInputLV2Num());
						obj.setInputLV1Num(lists_02.get(j).getInputLV3Num());
					}
				}
				lists_sum.add(obj);
			}
		}
		return lists_sum;
	}
	
	/**
	 * ����������뷨��Ծͳ�Ʊ�
	 * @param lists : �������뷨��Ծͳ�Ʊ�
	 * @param tab_name : ����
	 */
	public void insertActiveGwsp(List<Active_collect_gswb> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
		if(lists.size()>0 && lists!=null){
			activeDao.insert_Gswb_Log_Active(lists,db_ip,user,pwd,db_name,tab_name);
		}
	}
}
