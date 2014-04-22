package com.action.gswb_qd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.db.info.Dictionary_DB;
import com.entity.statistics.Gswb_collect_qd;
import com.server.gswb_log.qd.GswbCollectQdServer;

/**
 * �������뷨
 * ��������ͳ��
 * @author ZhuYa
 *
 */
public class Gswb_collect_qd_action {
	GswbCollectQdServer qdServer = new GswbCollectQdServer();
	/**
	 * ������������ͳ�Ʊ�
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_date
	 * @return
	 */
	public List<Gswb_collect_qd> insertGswb_Collect_Qd(String db_ip,String user,String pwd,String db_name,String db_gswb_log,Integer tab_date,String tab_beforeDate){
		String tab_name = tab_date.toString();
		//�������ܼ��ϱ�
		List<Gswb_collect_qd> listAll = new ArrayList<Gswb_collect_qd>();
		
		//���ش���������ͳ�Ʊ�
		List<Gswb_collect_qd> lists =  qdServer.getGswb_Collect_Qd(db_ip, user, pwd, db_name, tab_name, Dictionary_DB.getQdName());
//		//���ش�������Ծ�� : ��װ�û���1��2��3���µ��û���
//		List<Gswb_collect_qd> lists_02 = qdServer.getGswbQdInsActive(db_ip, user, pwd, db_gswb_log, tab_name);
//		//�����ܱ�
//		for(int i=0;i<lists.size();i++){
//			Gswb_collect_qd obj = new Gswb_collect_qd();
//			obj.setQdType(lists.get(i).getQdType());
//			obj.setServiceStartNum(lists.get(i).getServiceStartNum()); //���շ���������
//			obj.setActiveNum(lists.get(i).getActiveNum());			   //���ջ�Ծ�û���
//			obj.setOnlyInstallNum(lists.get(i).getOnlyInstallNum());   //���հ�װ��(ȥ��)
//			obj.setMonthAddInstallNum(lists.get(i).getMonthAddInstallNum()); //���հ�������
//			obj.setInputLV1Num(lists.get(i).getInputLV1Num());		   //��������С�ڵ���10
//			obj.setInputLV2Num(lists.get(i).getInputLV2Num());		   //��������С�ڵ���140
//			obj.setInputLV3Num(lists.get(i).getInputLV3Num());		   //������������140
//			obj.setActiveNum7Days(lists.get(i).getActiveNum7Days());   //7�մ����
//			obj.setUninstNum(lists.get(i).getUninstNum());			   //�����û�ж����
//			obj.setInstallNum(lists.get(i).getInstallNum());		   //���հ�װ��(��ȥ��)
//			
//			//������Ծ��:��װ�û���1,2,3�ȵ��û���
//			for(int j=0;j<lists_02.size();j++){
//				if(lists.get(i).getQdType().equals(lists_02.get(i).getQdType())){
//					obj.setActiveInputNum(lists_02.get(j).getActiveInputNum()); //��Ծ�û���:��װ�û���1��2��3���µ��û���
//				}
//			}
//			listAll.add(obj);
//		}
		
		//����
		if(lists!=null && lists.size()>0){
			qdServer.insertGswb_Collect_Qd(lists, db_ip, user, pwd, db_name, tab_name);
			qdServer.updateGswb_Collect_Qd(lists, db_ip, user, pwd, db_name, tab_beforeDate);
		}
		return null;
	}
	
	
	/**
	 * ���������������ͳ�Ʊ�
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_date
	 * @return
	 */
	public List<Gswb_collect_qd> insertGswb_Collect_Qd_02(String db_ip,String user,String pwd,String db_name,String db_gswb_log,Integer tab_date,String tab_beforeDate){
		String tab_name = tab_date.toString();
		//���������ܼ��ϱ�
		Map<String,List<Gswb_collect_qd>> mapList = new HashMap<String,List<Gswb_collect_qd>>();
		
		Map<String,List<Gswb_collect_qd>> maplists =  qdServer.getGswb_Collect_Qd_02(db_ip, user, pwd, db_name, tab_name, Dictionary_DB.getQdName());
//		//���ض���������Ծ�� : ��װ�û���1��2��3���µ��û���
//		List<Gswb_collect_qd> lists_02 = qdServer.getGswbQdInsActiveNode2(db_ip, user, pwd, db_gswb_log, tab_name);
//		
//		if(maplists != null){
//			List<Gswb_collect_qd> listsLast  = new ArrayList<Gswb_collect_qd>();
//	    	for (String key : maplists.keySet()) {
//	    		List<Gswb_collect_qd> lists= maplists.get(key);
//	    		if(lists != null && lists.size()>0){
//	    			for(int i=0;i<lists.size();i++){
//	    				Gswb_collect_qd obj = new Gswb_collect_qd();
//	    				obj.setQdType(lists.get(i).getQdType());
//	    				obj.setServiceStartNum(lists.get(i).getServiceStartNum()); //���շ���������
//	    				obj.setActiveNum(lists.get(i).getActiveNum());			   //���ջ�Ծ�û���
//	    				obj.setOnlyInstallNum(lists.get(i).getOnlyInstallNum());   //���հ�װ��(ȥ��)
//	    				obj.setMonthAddInstallNum(lists.get(i).getMonthAddInstallNum()); //���հ�������
//	    				obj.setInputLV1Num(lists.get(i).getInputLV1Num());		   //��������С�ڵ���10
//	    				obj.setInputLV2Num(lists.get(i).getInputLV2Num());		   //��������С�ڵ���140
//	    				obj.setInputLV3Num(lists.get(i).getInputLV3Num());		   //������������140
//	    				obj.setActiveNum7Days(lists.get(i).getActiveNum7Days());   //7�մ����
//	    				obj.setUninstNum(lists.get(i).getUninstNum());			   //�����û�ж����
//	    				obj.setInstallNum(lists.get(i).getInstallNum());		   //���հ�װ��(��ȥ��)
//			    		if(lists_02 != null && lists_02.size()>0){
//			    			for(int j=0;j<lists_02.size();j++){
//			    				String qdType02 = lists_02.get(j).getQdType();
//			    				String qid02 = lists_02.get(j).getQid(); 
//			    				//�ж��Ƿ�������� : ��Ӱ�װ����123�Ȼ�Ծ��
//			    				if(lists.get(i).getQdType().equals(qdType02) && lists.get(i).getQid().equals(qid02)){
//			    					obj.setInstallNum(lists_02.get(j).getActiveInputNum());
//			    					//���ջ�Ծ�ʣ����ջ�Ծ��/�û���װ��������ȥ�أ�
//			    					if(lists.get(i).getOnlyInstallNum() != 0){
//			    						obj.setActiveInputPer(Math.round((lists_02.get(j).getActiveInputNum() *1.00/lists.get(i).getOnlyInstallNum())*100));
//			    					}else{
//			    						obj.setActiveInputPer(0);
//			    					}
//			    				}else{
//			    					obj.setInstallNum(0);
//			    				}
//			    			}
//			    		}
//	    			    	
//	    				listsLast.add(obj);
//	    			}
//	    			mapList.put(key, listsLast);
//	    		}
//	    	}
//	    }
		
		if(maplists!=null){
			qdServer.insertGswb_Collect_Qd_02(maplists, db_ip, user, pwd, db_name, tab_name);
			qdServer.updateGswb_Collect_Qd02(maplists, db_ip, user, pwd, db_name, tab_beforeDate);
		}
		return null;
	}
	
	/**
	 * ����������������ͳ�Ʊ�
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param db_gswb_log
	 * @param tab_date
	 * @param tab_beforeDate
	 * @param tabName
	 * @return
	 */
	public List<Gswb_collect_qd> insertGswb_Collect_Qd_03(String db_ip,String user,String pwd,String db_name,String db_gswb_log,Integer tab_date,String tab_beforeDate){
		String tab_name = tab_date.toString();
		List<Gswb_collect_qd> lists =  qdServer.getPromotionNode3(db_ip, user, pwd, db_name, tab_name);
			
//		//�����������ֻ���
//		List<Gswb_collect_qd> list01 = qdServer.getPromotionNode3(db_ip, user, pwd, db_name, tab_name);
//		//�����������������󼯺�
//		List<Gswb_collect_qd> list02 = qdServer.getPromotionNode3(db_ip, user, pwd, db_name, tab_name);
//		//�ϲ���������
		if(lists!=null && lists.size() > 0){
			qdServer.insertGswb_Collect_Qd_03(lists, db_ip, user, pwd, db_name, tab_name);
			qdServer.updateGswb_Collect_Qd03(lists, db_ip, user, pwd, db_name, tab_beforeDate);
		}
		return null;
	}
}
