package com.server.gswb_log.qd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dao.gswb_log.qd.GswbCollectQdDao;
import com.db.info.DataBase_Info;
import com.db.info.Dictionary_DB;
import com.db.info.sql.qd_gswb.T_Sql_Gswb_Collect_Qd;
import com.entity.statistics.Gswb_collect_qd;
import com.entity.statistics.Online_collect_gswb;

/**
 * �������뷨
 * �߼�ҵ���
 * ��������ͳ��
 * @author ZhuYa
 *
 */
public class GswbCollectQdServer {
	GswbCollectQdDao qdDao = new GswbCollectQdDao();
	
	/**
	 * ���ع������뷨��������ͳ��
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name: ����
	 * @param ����map����
	 * @return
	 */
	public List<Gswb_collect_qd> getGswb_Collect_Qd(String db_ip,String user,String pwd,String db_name,String tab_name,Map<String,String> map){
		List<Gswb_collect_qd> lists = null;
		if(map!=null){
			lists = new ArrayList<Gswb_collect_qd>();
			for (String key : map.keySet()) {
				Gswb_collect_qd gswbCollectQd = qdDao.getGswb_Collect_Qd(db_ip, user, pwd, db_name, tab_name, key, map.get(key));
				lists.add(gswbCollectQd);
			}
		}
		return lists;
	}
	
	/**
	 * ���ع������뷨������ ��װ��/��Ծ��
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name: ����
	 * @param ����map����
	 * @return
	 */
	public List<Gswb_collect_qd> getGswbQdInsActive(String db_ip,String user,String pwd,String db_name,String tab_name){
		List<Gswb_collect_qd> lists = this.getGswbQdInsActiveInfo(db_ip, user, pwd, db_name, tab_name);
		List<Gswb_collect_qd> listAll2 = getGswbQdInsActiveCount(lists);	//��ȡ��������ֵ����
		
		return listAll2;
	}
	
	/**
	 * ���ض������� ��װ��/��Ծ��
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name: ����
	 * @param ����map����
	 * @return
	 */
	public List<Gswb_collect_qd> getGswbQdInsActiveNode2(String db_ip,String user,String pwd,String db_name,String tableDate){
		//��װ����123�Ȼ�Ծ����ļ���
		List<Gswb_collect_qd> lists = this.getGswbQdInsActiveInfo(db_ip, user, pwd, db_name, tableDate);
		//��ȡ��"_"�Ķ�����������
		List<Gswb_collect_qd> lists2 = getGswbQdInsActiveCount2(lists);
		//ȥ�ش�"_"�Ķ�����������
		List<Gswb_collect_qd> listsOnly2 = getGswbQdInsActiveCountOnly2(lists2);
		//���ض���������Ϣ:��װ����123�Ȼ�Ծ����ļ���
		List<Gswb_collect_qd> lists2All = getGswbQdInsActiveCount3(lists, listsOnly2);
		return lists2All;
	}
	
	/**
	 * �������������������� : ���ϲ�ı�
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tableDate
	 * @return
	 */
	public List<Gswb_collect_qd> getGswbQdInsActiveNode3(String db_ip,String user,String pwd,String db_name,String tableDate){
		String install_sql = T_Sql_Gswb_Collect_Qd.return_getGswbQdInstall_01(tableDate);  //��װSQL
		String active_sql = T_Sql_Gswb_Collect_Qd.return_getGswbQdActive_01(tableDate);	  //��ԾSQL
		List<Gswb_collect_qd> insList = qdDao.getGswbQdInstall_01(db_ip, user, pwd, db_name, tableDate, install_sql);  //��ȡ��װ����
		List<Gswb_collect_qd> aveList = qdDao.getGswbQdInstall_01(db_ip, user, pwd, db_name, tableDate, active_sql);	  //��ȡ��Ծ����
		List<Gswb_collect_qd> insSub = getMethodList03(insList);	// ��װȥ�ؼ���
		List<Gswb_collect_qd> aveSub = getMethodList03(aveList);	// ��Ծȥ�ؼ���
		
		List<Gswb_collect_qd> listAll = getGswbQdInsActiveAll03(insSub,aveSub);	//����: ��װ����123�Ȼ�Ծ����ļ��� 
		List<Gswb_collect_qd> listAllQuChong = getQuChongMethodList03(listAll);
		return listAllQuChong;
	}
	
	/**
	 *  ��װ����123�Ȼ�Ծ����ļ��� : ����ȥ��
	 * �������ܻ�����Ϣ����
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public List<Gswb_collect_qd> getGswbQdInsActiveInfo(String db_ip,String user,String pwd,String db_name,String tableDate){
		String install_sql = T_Sql_Gswb_Collect_Qd.return_getGswbQdInstall_01(tableDate);  //��װSQL
		String active_sql = T_Sql_Gswb_Collect_Qd.return_getGswbQdActive_01(tableDate);	  //��ԾSQL
		List<Gswb_collect_qd> insList = qdDao.getGswbQdInstall_01(db_ip, user, pwd, db_name, tableDate, install_sql);  //��ȡ��װ����
		List<Gswb_collect_qd> aveList = qdDao.getGswbQdInstall_01(db_ip, user, pwd, db_name, tableDate, active_sql);	  //��ȡ��Ծ����
		
		List<Gswb_collect_qd> insSub = getMethodList(insList);	// ��װȥ�ؼ���
		List<Gswb_collect_qd> aveSub = getMethodList(aveList);	// ��Ծȥ�ؼ���
		
		List<Gswb_collect_qd> listAll = getGswbQdInsActiveAll(insSub,aveSub);		//����: ��װ����123�Ȼ�Ծ����ļ���
		List<Gswb_collect_qd> listAllQuChong = getQuChongMethodList(listAll);		//���� �� ����ȥ��
		return listAllQuChong;
	}
	
	/**
	 * ������������� ����
	 * @param lists
	 * @return
	 */
	public List<Gswb_collect_qd> getGswbQdInsActiveCount(List<Gswb_collect_qd> lists){
		List<Gswb_collect_qd> qdList = new ArrayList<Gswb_collect_qd>();
		Gswb_collect_qd obj_gw = null;
		Gswb_collect_qd obj_ald = null;
		Gswb_collect_qd obj_nb = null;
		Gswb_collect_qd obj_wb = null;
		Gswb_collect_qd obj_other = null;
		if(lists != null && lists.size()>0){
			int gw = 0;
			int ald = 0;
			int nb = 0;
			int wb = 0;
			int other = 0;
			for(int i = 0;i<lists.size();i++){
				if(lists.get(i).getQdType().equals(Dictionary_DB.strs[0])){
					gw=gw+1;
				}else if(lists.get(i).getQdType().equals(Dictionary_DB.strs[1])){
					ald=ald+1;
				}else if(lists.get(i).getQdType().equals(Dictionary_DB.strs[2])){
					nb=nb+1;
				}else if(lists.get(i).getQdType().equals(Dictionary_DB.strs[3])){
					wb=wb+1;
				}else if(lists.get(i).getQdType().equals(Dictionary_DB.strs[4])){
					other = other+1;
				}
			}
			//gs
			obj_gw = new Gswb_collect_qd();
			obj_gw.setQdType(Dictionary_DB.strs[0]);
			obj_gw.setActiveInputNum(gw);
			//ald
			obj_ald = new Gswb_collect_qd();
			obj_ald.setQdType(Dictionary_DB.strs[1]);
			obj_ald.setActiveInputNum(ald);
			//nb
			obj_nb = new Gswb_collect_qd();
			obj_nb.setQdType(Dictionary_DB.strs[2]);
			obj_nb.setActiveInputNum(nb);
			//wb
			obj_wb = new Gswb_collect_qd();
			obj_wb.setQdType(Dictionary_DB.strs[3]);
			obj_wb.setActiveInputNum(wb);
			//other
			obj_other = new Gswb_collect_qd();
			obj_other.setQdType(Dictionary_DB.strs[4]);
			obj_other.setActiveInputNum(other);
		}
		qdList.add(obj_gw);
		qdList.add(obj_ald);
		qdList.add(obj_nb);
		qdList.add(obj_wb);
		qdList.add(obj_other);
		return qdList;
	}
	
	/**
	 * ��ȡ��"_"����������
	 * @param lists
	 * @return
	 */
	public List<Gswb_collect_qd> getGswbQdInsActiveCount2(List<Gswb_collect_qd> lists){
		List<Gswb_collect_qd> qdList_node2 = new ArrayList<Gswb_collect_qd>();
		Gswb_collect_qd obj_gw = null;
		if(lists != null && lists.size()>0){
			for(int i = 0;i<lists.size();i++){
				int str_index = lists.get(i).getQid().indexOf("_");	//��ȡ��������
				if(str_index != -1){
					obj_gw = new Gswb_collect_qd();
					
					String qid_node2 = lists.get(i).getQid().substring(0,str_index);  //��ȡ�ڶ�������
					obj_gw.setQdType(lists.get(i).getQdType());		//��������
					obj_gw.setQid(qid_node2);	
					qdList_node2.add(obj_gw);	
				}
			}
		}
		return qdList_node2;
	}
	
	/**
	 * ��ȡȥ�صĴ�"_"�Ķ�����������
	 * @param lists
	 * @return
	 */
	public List<Gswb_collect_qd> getGswbQdInsActiveCountOnly2(List<Gswb_collect_qd> lists){
		List<Gswb_collect_qd> qdList = null;
		if(lists != null && lists.size()>0){
			qdList = new ArrayList<Gswb_collect_qd>();
			for(int i = 0;i<lists.size();i++){
				for ( int j = lists.size() - 1 ; j > i; j -- ) {
			    	 if(lists.get(i).getQdType().equals(lists.get(j).getQdType()) && lists.get(i).getUid().equals(lists.get(j).getUid())){
			    		 lists.remove(j);
			         }
		        }
				Gswb_collect_qd qd = new Gswb_collect_qd();
				qd.setUid(lists.get(i).getUid());
				qd.setQdType(lists.get(i).getQdType());
				qdList.add(qd);
			}
		}
		return qdList;
	}
	
	/**
	 * ��ȡ��"_"�Ķ�����������
	 * @param lists
	 * @return
	 */
	public List<Gswb_collect_qd> getGswbQdInsActiveCount3(List<Gswb_collect_qd> lists,List<Gswb_collect_qd> listsTwo){
		List<Gswb_collect_qd> qdList_node2 = new ArrayList<Gswb_collect_qd>();
		Gswb_collect_qd obj_gw = null;
		if(lists != null && lists.size()>0){
			obj_gw = new Gswb_collect_qd();
			int num = 0;
			//�󼯺�
			for(int i = 0;i<lists.size();i++){
				String qdType = lists.get(i).getQdType();
				String qid = lists.get(i).getQid();
				//��������
				for(int j = 0;j<lists.size();j++){
					String qdType2 = lists.get(i).getQdType();
					String qid2 = lists.get(i).getQid();
					if(qdType.equals(qdType2) && qid.equals(qid2)){
						num = num+1;
					}
				}
				obj_gw.setQdType(qdType);
				obj_gw.setQid(qid);
				obj_gw.setActiveInputNum(num);
				//���ջ�Ծ�ʣ����ջ�Ծ��/�û���װ��������ȥ�أ�
				if(obj_gw.getOnlyInstallNum() != 0){
					obj_gw.setActiveInputPer(Math.round((num *1.00/obj_gw.getOnlyInstallNum())*100));
				}else{
					obj_gw.setActiveInputPer(0);
				}
				qdList_node2.add(obj_gw);
			}
		}
		return qdList_node2;
	}
	
	/**
	 * ���ع������뷨������: ��װ����123�Ȼ�Ծ����ļ���
	 * @param lists1 : ��װ����
	 * @param lists2 : ��Ծ����
	 * @return
	 */
	public List<Gswb_collect_qd> getGswbQdInsActiveAll(List<Gswb_collect_qd> lists1,List<Gswb_collect_qd> lists2){
		List<Gswb_collect_qd> qdList = new ArrayList<Gswb_collect_qd>();
		if(lists1 != null && lists2 != null){
			for(int i = 0;i<lists1.size();i++){
				for(int j = 0;j<lists2.size();j++){
					if(lists1.get(i).getQdType().equals(lists2.get(j).getQdType()) && lists1.get(i).getUid().equals(lists2.get(j).getUid())){
						Gswb_collect_qd obj = new Gswb_collect_qd();
						obj.setQdType(obj.getQdType());
						obj.setUid(obj.getUid());
						qdList.add(obj);
					}
				}
			}
		}
		return qdList;
	}
	
	/**
	 * �������������뷨
	 * @param lists1
	 * @param lists2
	 * @return
	 */
	public List<Gswb_collect_qd> getGswbQdInsActiveAll03(List<Gswb_collect_qd> lists1,List<Gswb_collect_qd> lists2){
		List<Gswb_collect_qd> qdList = new ArrayList<Gswb_collect_qd>();
		if(lists1 != null && lists2 != null){
			for(int i = 0;i<lists1.size();i++){
				for(int j = 0;j<lists2.size();j++){
					if(lists1.get(i).getQid().equals(lists2.get(j).getQid()) && lists1.get(i).getUid().equals(lists2.get(j).getUid())){
						Gswb_collect_qd obj = new Gswb_collect_qd();
						obj.setQdType(obj.getQdType());
						obj.setUid(obj.getUid());
						qdList.add(obj);
					}
				}
			}
		}
		return qdList;
	}
	
	/**
	 * �������ݼ���ȥ��
	 * @param lists
	 * @return
	 */
	public List<Gswb_collect_qd> getQuChongMethodList(List<Gswb_collect_qd> lists){
		List<Gswb_collect_qd> qdList = null;
		if(lists != null && lists.size()>0){
			qdList = new ArrayList<Gswb_collect_qd>();
			for(int i = 0;i<lists.size();i++){
				for ( int j = lists.size() - 1 ; j > i; j -- ) {
			    	 if(lists.get(i).getQdType().equals(lists.get(j).getQdType()) && lists.get(i).getUid().equals(lists.get(j).getUid())){
			    		 lists.remove(j);
			         }
		        }
				Gswb_collect_qd qd = new Gswb_collect_qd();
				qd.setUid(lists.get(i).getUid());
				qd.setQdType(lists.get(i).getQdType());
				qdList.add(qd);
			}
		}
		return qdList;
	}
	
	/**
	 * �����������ݼ���ȥ��
	 * @param lists
	 * @return
	 */
	public List<Gswb_collect_qd> getQuChongMethodList03(List<Gswb_collect_qd> lists){
		List<Gswb_collect_qd> qdList = null;
		if(lists != null && lists.size()>0){
			qdList = new ArrayList<Gswb_collect_qd>();
			for(int i = 0;i<lists.size();i++){
				for ( int j = lists.size() - 1 ; j > i; j -- ) {
			    	 if(lists.get(i).getQid().equals(lists.get(j).getQid()) && lists.get(i).getUid().equals(lists.get(j).getUid())){
			    		 lists.remove(j);
			         }
		        }
				Gswb_collect_qd qd = new Gswb_collect_qd();
				qd.setUid(lists.get(i).getUid());
				qd.setQid(lists.get(i).getQid());
				qd.setQdType(lists.get(i).getQdType());
				qdList.add(qd);
			}
		}
		return qdList;
	}
	
	/**
	 * ���ع������뷨���ϴ������ı�������
	 * @param lists
	 * @return
	 */
	public List<Gswb_collect_qd> getMethodList(List<Gswb_collect_qd> lists){
		List<Gswb_collect_qd> qdLists = new ArrayList<Gswb_collect_qd>();
		if(lists != null && lists.size()>0){
			for(int i = 0;i<lists.size();i++){
				
				
				Gswb_collect_qd obj = new Gswb_collect_qd();
				String qid_sub = null;
				String qid = lists.get(i).getQid();
				if(qid.length() > 6){
					qid_sub =  qid.substring(0, qid.length()-6);	//��ϴԭ������
				}else{
					qid_sub = qid;
				}
				obj.setQid(qid_sub);
				obj.setUid(lists.get(i).getUid());
				
//				int str_index = qid_sub.indexOf("_");	//������������
//				String qid_node2 = qid_sub.substring(0,str_index);  //��ȡ�ڶ�������
//				obj.setQid(qid_node2);
//				obj.setUid(lists.get(i).getUid());
				//gw
				Pattern pattern_gw = Pattern.compile("^gf[0-9][0-9][0-9]$");  
				Matcher matcher_gw = pattern_gw.matcher(qid); 
				boolean bs_gw= matcher_gw.matches();
				//ald
				Pattern pattern_ald = Pattern.compile("^ald[0-9][0-9][0-9]$");  	  
				Matcher matcher_ald = pattern_ald.matcher(qid); 
				boolean bs_ald= matcher_ald.matches();
				//wb(���� wb1 �� wb2)
				Pattern pattern_wb1 = Pattern.compile("^xt[0-9][0-9][0-9]_[0-9][0-9][0-9][0-9]$");  	  
				Matcher matcher_wb1 = pattern_wb1.matcher(qid);
				boolean bs_wb1= matcher_wb1.matches();
				
				Pattern pattern_wb2 = Pattern.compile("^(xz|kb|xt)[0-9][0-9][0-9]$");
				Matcher matcher_wb2 = pattern_wb2.matcher(qid);
				boolean bs_wb2= matcher_wb2.matches();
				//nb
				Pattern pattern_nb = Pattern.compile("^nb[0-9][0-9][0-9]$");
				Matcher matcher_nb = pattern_nb.matcher(qid);
				boolean bs_nb= matcher_nb.matches();
				//�ж����ĸ�������
				if(bs_gw){
					obj.setQdType("gw");
				}else if(bs_ald){
					obj.setQdType("ald");
				}else if(bs_wb1 || bs_wb2){
					obj.setQdType("wb");
				}else if(bs_nb){
					obj.setQdType("nb");
				}else{
					obj.setQdType("other");
				}
				qdLists.add(obj);
			}
		}
		return qdLists;
	}
	
	/**
	 * ��������
	 * @param lists
	 * @return
	 */
	public List<Gswb_collect_qd> getMethodList03(List<Gswb_collect_qd> lists){
		List<Gswb_collect_qd> qdLists = new ArrayList<Gswb_collect_qd>();
		if(lists != null && lists.size()>0){
			for(int i = 0;i<lists.size();i++){
				Gswb_collect_qd obj = new Gswb_collect_qd();
				String qid_sub = null;
				String qid = lists.get(i).getQid();
				if(qid.length() > 6){
					qid_sub =  qid.substring(0, qid.length()-6);	//��ϴԭ������
				}else{
					qid_sub = qid;
				}
				obj.setQid(qid_sub);
				obj.setUid(lists.get(i).getUid());
				qdLists.add(obj);
			}
		}
		return qdLists;
	}
	
	/**
	 * ���ع������뷨��������:��װ�û���1��2��3���µ��û���
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name: ����
	 * @param ����map����
	 * @return
	 */
	public Map<String,List<Gswb_collect_qd>> getGswbQdInsActive02(String db_ip,String user,String pwd,String db_name,String tab_name,Map<String,String> map){
		Map<String,List<Gswb_collect_qd>> mapList = null;
		if(map!=null){
			mapList = new HashMap<String, List<Gswb_collect_qd>>();
			for (String key : map.keySet()) {
				List<Gswb_collect_qd> lists = qdDao.getGswbQdInsActive02(db_ip, user, pwd, db_name, tab_name, key, map.get(key));
				mapList.put(key, lists);
			}
		}
		return mapList;
	}
	
	/**
	 * ���ع������뷨������������ͳ��
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name: ����
	 * @param ����map����
	 * @return
	 */
	public Map<String,List<Gswb_collect_qd>> getGswb_Collect_Qd_02(String db_ip,String user,String pwd,String db_name,String tab_name,Map<String,String> map){
		Map<String,List<Gswb_collect_qd>> mapList = null;
		if(map!=null){
			mapList = new HashMap<String, List<Gswb_collect_qd>>();
			for (String key : map.keySet()) {
				List<Gswb_collect_qd> lists = qdDao.getGswb_Collect_Qd_02(db_ip, user, pwd, db_name, tab_name, key, map.get(key));
				mapList.put(key, lists);
			}
		}
		return mapList;
	}
	
	/**
	 * ���ع������뷨�����������ֻ���ͳ��
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name: ����
	 * @param ����map����
	 * @return
	 */
	public List<Gswb_collect_qd> getPromotionNode3(String db_ip,String user,String pwd,String db_name,String tab_name){
		//��ȡ����������������
 		List<Gswb_collect_qd> lists = qdDao.getPromotionNode3(db_ip, user, pwd, db_name, tab_name);
		List<Gswb_collect_qd> listAll =new ArrayList<Gswb_collect_qd>();
		if(lists != null && lists.size() > 0){
			for(int i = 0;i<lists.size();i++){
				Gswb_collect_qd gswbQd = new Gswb_collect_qd();
				String qid = lists.get(i).getQid();
				
				gswbQd.setQid(qid);							 //����
				gswbQd.setServiceStartNum(lists.get(i).getServiceStartNum());		 //���շ���������	
				gswbQd.setActiveNum(lists.get(i).getActiveNum());					 //���ջ�Ծ�û���
				gswbQd.setOnlyInstallNum(lists.get(i).getOnlyInstallNum());		 	 //���հ�װ��(ȥ��)
				gswbQd.setMonthAddInstallNum(lists.get(i).getMonthAddInstallNum());  //���հ�������
				gswbQd.setInputLV1Num(lists.get(i).getInputLV1Num());				 //����ʹ��(1-10)
				gswbQd.setInputLV2Num(lists.get(i).getInputLV2Num());				 //����ʹ��(1-140)
				gswbQd.setInputLV3Num(lists.get(i).getInputLV3Num());				 //����ʹ��(140+)
				gswbQd.setUninstNum(lists.get(i).getUninstNum());					 //�����û�ж����
				gswbQd.setActiveNum7Days(lists.get(i).getActiveNum7Days());			 //7�մ����
				gswbQd.setInstallNum(lists.get(i).getInstallNum());			 	 	 //���հ�װ��(û��ȥ��)
				
				//gw
				Pattern pattern_gw = Pattern.compile("^gf[0-9][0-9][0-9]$");  
				Matcher matcher_gw = pattern_gw.matcher(qid); 
				boolean bs_gw= matcher_gw.matches();
				//ald
				Pattern pattern_ald = Pattern.compile("^ald[0-9][0-9][0-9]$");  	  
				Matcher matcher_ald = pattern_ald.matcher(qid); 
				boolean bs_ald= matcher_ald.matches();
				//wb(���� wb1 �� wb2)
				Pattern pattern_wb1 = Pattern.compile("^xt[0-9][0-9][0-9]_[0-9][0-9][0-9][0-9]$");  	  
				Matcher matcher_wb1 = pattern_wb1.matcher(qid);
				boolean bs_wb1= matcher_wb1.matches();
				
				Pattern pattern_wb2 = Pattern.compile("^(xz|kb|xt)[0-9][0-9][0-9]$");
				Matcher matcher_wb2 = pattern_wb2.matcher(qid);
				boolean bs_wb2= matcher_wb2.matches();
				//nb
				Pattern pattern_nb = Pattern.compile("^nb[0-9][0-9][0-9]$");
				Matcher matcher_nb = pattern_nb.matcher(qid);
				boolean bs_nb= matcher_nb.matches();
				//�ж����ĸ�������
				if(bs_gw){
					gswbQd.setQdType("gw");
				}else if(bs_ald){
					gswbQd.setQdType("ald");
				}else if(bs_wb1 || bs_wb2){
					gswbQd.setQdType("wb");
				}else if(bs_nb){
					gswbQd.setQdType("nb");
				}else{
					gswbQd.setQdType("other");
				}
				listAll.add(gswbQd);
			}
		}
		return listAll;
	}
	
	
	/**
	 * ����������뷨��������ͳ��
	 * @param lists : �������뷨��������ͳ�Ƽ���
	 * @param tab_name : ����
	 */
	public void insertGswb_Collect_Qd(List<Gswb_collect_qd> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
		if(lists.size()>0 && lists!=null){
			qdDao.insert_Gswb_Qd(lists, db_ip, user, pwd, db_name, tab_name);
		}
	}
	
	/**
	 * ����������뷨������������ͳ��
	 * @param lists : �������뷨��������ͳ�Ƽ���
	 * @param tab_name : ����
	 */
	public void insertGswb_Collect_Qd_02(Map<String,List<Gswb_collect_qd>> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
		if(lists.size()>0 && lists!=null){
			qdDao.insert_Gswb_Qd_02(lists, db_ip, user, pwd, db_name, tab_name);
		}
	}
	
	/**
	 * ����������뷨������������ͳ��
	 * @param lists : �������뷨��������ͳ�Ƽ���
	 * @param tab_name : ����
	 */
	public void insertGswb_Collect_Qd_03(List<Gswb_collect_qd> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
		if(lists.size()>0 && lists!=null){
			qdDao.insert_Gswb_Qd_03(lists, db_ip, user, pwd, db_name, tab_name);
		}
	}
	
	/**
	 * �������뷨��������������7�մ����
	 * @param lists
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tabName
	 * @param tab_beforeDate : 7��ǰ����
	 */
	public void updateGswb_Collect_Qd(List<Gswb_collect_qd> lists,String db_ip,String user,String pwd,String db_name,String tab_beforeDate){
		if(lists != null && lists.size() > 0){
			//������
			qdDao.update_Gswb_Qd(lists, db_ip, user, pwd, db_name,  tab_beforeDate);
		}
	}
	
	/**
	 * �������뷨��������������7�մ����
	 * @param lists
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tabName
	 * @param tab_beforeDate
	 */
	public void updateGswb_Collect_Qd02(Map<String,List<Gswb_collect_qd>> lists,String db_ip,String user,String pwd,String db_name,String tab_beforeDate){
		if(lists != null && lists.size() > 0){
			qdDao.update_Gswb_Qd02(lists, db_ip, user, pwd, db_name,  tab_beforeDate);
		}
	}
	
	/**
	 * �������뷨��������������7�մ����
	 * @param lists
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tabName
	 * @param tab_beforeDate
	 */
	public void updateGswb_Collect_Qd03(List<Gswb_collect_qd> lists,String db_ip,String user,String pwd,String db_name,String tab_beforeDate){
		if(lists != null && lists.size() > 0){
			qdDao.update_Gswb_Qd03(lists, db_ip, user, pwd, db_name,  tab_beforeDate);
		}
	}
}
