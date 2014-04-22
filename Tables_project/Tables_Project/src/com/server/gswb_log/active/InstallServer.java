package com.server.gswb_log.active;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.gswb_log.active.InstallDao;
import com.db.info.DataBase_Info;
import com.entity.statistics.Active_collect_gswb;
import com.entity.statistics.Install_collect_gswb;

/**
 * �������뷨
 * �߼�ҵ���
 * ��װ��ͳ��
 * @author ZhuYa
 *
 */
public class InstallServer {
	InstallDao installDao = new InstallDao();
	
	/**
	 * ��ȡ�������뷨 ��װ��
	 * @param db_ip : ����IP
	 * @param db_name : ���ݿ���
	 * @param user 	: �û���
	 * @param pwd	: ����
	 * @param tab_name : ����
	 * @return
	 */
	public List<Install_collect_gswb> getGswb_Log_Install_01(String db_ip,String user,String pwd,String db_name,String tab_name){
		List<Install_collect_gswb> installList = installDao.getGswb_Log_Install_01(db_ip, user, pwd, db_name, tab_name);
		return installList;
	}
	
	/**
	 * 
	 * ��ȡ31����ܰ�װ��
	 * @param db_ip : ����IP
	 * @param user	�� �û���
	 * @param pwd	: ����
	 * @param db_name	: ���ݿ���
	 * @param tab_name	: ����
	 * @return
	 */
	public List<Install_collect_gswb> getGswb_Log_Install_30Days(String db_ip,String user,String pwd,String db_name,String tab_name){
		//����31��ı�������
		List<Integer> tab_list_sum = new ArrayList<Integer>();
		
		//��ȡ�ÿ�����install����
		List<Integer> listTable = installDao.getGswb_Log_Install_tab(db_ip, user, pwd, db_name);
		//����-1��ȥ���ձ� 
		if(listTable != null && listTable.size() > 0){
			//############################################## ��ʽ�� 3��  �ĳ�32��
//			for(int i = 1;i<32;i++){
			for(int i = 1;i<3;i++){
				tab_list_sum.add(listTable.get(i));
			}
		}
		List<Install_collect_gswb> listSum = installDao.getGswb_Log_Install_30Days(db_ip, user, pwd, db_name, tab_name, tab_list_sum);
		return listSum;
	}
	
	/**
	 * ��ȡ�¶�ȥ�ذ�װ��
	 * ��ȡ�¶�Ψһ������
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public Map<String,List<Install_collect_gswb>> getGswb_Log_Install_30Days_sum(String db_ip,String user,String pwd,String db_name,String tab_name){
		Install_collect_gswb install_statistics = null;
		//�洢 �¶�ȥ�ذ�װ������,�¶�Ψһ��������װ����
		Map<String,List<Install_collect_gswb>> mapList= new HashMap<String, List<Install_collect_gswb>>();
		//������ϲ�ѯʱ������ݼ���
		List<Install_collect_gswb> install_tabDate = null;
		//������ϲ�ѯʱ��ǰ30������ݼ���
		List<Install_collect_gswb> install_tabDate_30days = null;
		//��ȡҪ��ѯ��ʱ��
		Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-8));
		//��ȡ31��İ�װ���ݼ���
		List<Install_collect_gswb> install_31days = this.getGswb_Log_Install_30Days(db_ip, user, pwd, db_name, tab_name);
		//�ֿ� ��ȡ��ѯʱ������ݼ��� �� ��ѯǰ30������ݼ��� 
		if(install_31days != null && install_31days.size() > 0){
			install_tabDate = new ArrayList<Install_collect_gswb>();		//���ϲ�ѯʱ������ݼ���
			install_tabDate_30days = new ArrayList<Install_collect_gswb>(); //���ϲ�ѯʱ��ǰ30������ݼ���
			for(int i=0;i<install_31days.size();i++){
				
				install_statistics = new Install_collect_gswb();
				install_statistics.setQid(install_31days.get(i).getQid());					  //����
				install_statistics.setSoftVer(install_31days.get(i).getSoftVer());			  //�汾
				install_statistics.setUid(install_31days.get(i).getUid());			  		  //�û���ʾ��
				install_statistics.setTab_date(install_31days.get(i).getTab_date());		  //ÿ�ű��ʱ��
				//�ж��Ƿ��ѯʱ�������
				if(install_statistics.getTab_date() == tab_date){
					//��Ӳ�ѯʱ�������
					install_tabDate.add(install_statistics);
				}else{
					//��Ӳ�ѯʱ��ǰ30�������
					install_tabDate_30days.add(install_statistics); 
				}
			}
		}
		
		//�¶�ȥ�ذ�װ�� ����
		List<Install_collect_gswb> month_install = new ArrayList<Install_collect_gswb>();
		//�¶�Ψһ������ ����
		List<Install_collect_gswb> month_only_install = new ArrayList<Install_collect_gswb>();
		
		//QID��Ӧ��UID ����ǰ30���QID������ ��count(distinct uid)
		if(install_tabDate.size() > 0 && install_tabDate_30days.size() > 0){
			for(int i = 0;i<install_tabDate.size();i++){
				Install_collect_gswb month_obj = new Install_collect_gswb();
				month_obj.setQid(install_tabDate.get(i).getQid());
				month_obj.setSoftVer(install_tabDate.get(i).getSoftVer());
				month_obj.setUid(install_tabDate.get(i).getUid());
				
				//����ǰ30������
				for(int j = 0;j<install_tabDate_30days.size();j++){
					String qid_02 = install_tabDate_30days.get(j).getSoftVer();
					String uid_02 = install_tabDate_30days.get(j).getUid();
					
					//��ǰ30����ͬQID,SoftVer ������ ǰ30�첻ͬ��ͳ��
					if(month_obj.getQid().equals(qid_02) && !(month_obj.getUid().equals(uid_02))){
						month_install.add(month_obj);
					}
					//��ǰ30����ͬSoftVer ������ ǰ30�첻ͬ��ͳ��
					if(!(month_obj.getUid().equals(uid_02))){
						month_only_install.add(month_obj);
					}
				}
			}
		}
		
		mapList.put("month", month_install);		 //��� �¶�ȥ�ذ�װ������
		mapList.put("monthOnly", month_only_install);//��� �¶�Ψһ��������װ����
		return mapList;
	}
	
	/**
	 * �¶�ȥ�ذ�װ�� ����ͳ��
	 * @param month_install : �¶�ȥ�ذ�װ�� ����
	 * @return
	 */
	public List<Install_collect_gswb> getGswb_Log_month_install_01(List<Install_collect_gswb> month_install){
		//���巵���¶�ȥ�ؼ���ͳ��   
		List<Install_collect_gswb> stuList_sum_02 = new ArrayList<Install_collect_gswb>();
		if(month_install.size()>0){

			 for (int i = 0 ; i < month_install.size(); i ++ ) {
				 Install_collect_gswb intall = new Install_collect_gswb();
				 intall.setQid(month_install.get(i).getQid());
				 intall.setUid(month_install.get(i).getUid());
				 intall.setSoftVer(month_install.get(i).getSoftVer());
				 
			     for ( int j = month_install.size() - 1 ; j > i; j -- ) {
			    	 
			       if(intall.getQid().equals(month_install.get(j).getQid()) && intall.getUid().equals(month_install.get(j).getUid()) && intall.getSoftVer().equals(month_install.get(j).getSoftVer())){
			    	   month_install.remove(j);
			       }
			     }
			  }
			
			//������
			int num = 0;
			for(int i = 0;i<month_install.size();i++){
				Install_collect_gswb intall = new Install_collect_gswb();
				intall.setQid(month_install.get(i).getQid());
				intall.setUid(month_install.get(i).getUid());
				intall.setSoftVer(month_install.get(i).getSoftVer());
				intall.setMonthOnlyInstallNum(num);
				num = 1;
				for ( int j = month_install.size() - 1 ; j > i; j -- ) {
			    	 if(intall.getQid().equals(month_install.get(j).getQid()) && month_install.get(i).getSoftVer().equals(month_install.get(j).getSoftVer())){
			    		 num=num+1;
			    		 month_install.remove(j);
			         }
		        }
				stuList_sum_02.add(intall);
			}
		}
		return stuList_sum_02;
	}
	
	/**
	 * �¶�Ψһ������ ���� ͳ��
	 * @param month_only_install : �¶�Ψһ������ ����
	 * @return
	 */
	public List<Install_collect_gswb> getGswb_Log_month_only_01(List<Install_collect_gswb> month_only_install){
		//���巵���¶�ȥ�ؼ���ͳ��   
		List<Install_collect_gswb> stuList_sum_02 = new ArrayList<Install_collect_gswb>();
		if(month_only_install.size()>0){

			 for (int i = 0 ; i < month_only_install.size(); i ++ ) {
				 Install_collect_gswb intall = new Install_collect_gswb();
				 intall.setQid(month_only_install.get(i).getQid());
				 intall.setUid(month_only_install.get(i).getUid());
				 intall.setSoftVer(month_only_install.get(i).getSoftVer());
				 //ȥ��qid,uid,softVer,ɾ���ظ���
			     for ( int j = month_only_install.size() - 1 ; j > i; j -- ) {
			    	 
			       if(intall.getQid().equals(month_only_install.get(j).getQid()) && intall.getUid().equals(month_only_install.get(j).getUid()) && intall.getSoftVer().equals(month_only_install.get(j).getSoftVer())){
			    	   month_only_install.remove(j);
			       }
			     }
			  }
			
			//������
			int num = 0;
			for(int i = 0;i<month_only_install.size();i++){
				Install_collect_gswb intall = new Install_collect_gswb();
				intall.setQid(month_only_install.get(i).getQid());
				intall.setUid(month_only_install.get(i).getUid());
				intall.setSoftVer(month_only_install.get(i).getSoftVer());
				intall.setMonthOnlyInstallNum(num);
				num = 1;
				for (int j = month_only_install.size() - 1 ; j > i; j -- ) {
					//ȥ�� qid,softVer ,ɾ���ظ��Ĳ� ��1
			    	 if(intall.getQid().equals(month_only_install.get(j).getQid()) && month_only_install.get(i).getSoftVer().equals(month_only_install.get(j).getSoftVer())){
			    		 num=num+1;
			    		 month_only_install.remove(j);
			         }
		        }
				stuList_sum_02.add(intall);
			}
		}
		return stuList_sum_02;
	}
	
	/**
	 * ��װ�������ݼ��� ͳ��
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public List<Install_collect_gswb> getGswb_Log_Install_sum_cont(String db_ip,String user,String pwd,String db_name,String tab_name){
		Map<String,List<Install_collect_gswb>> mapList = this.getGswb_Log_Install_30Days_sum(db_ip, user, pwd, db_name, tab_name);
		Install_collect_gswb obj = null;
		//�¶�ȥ�ذ�װ�� ����
		List<Install_collect_gswb> month_install = mapList.get("month");
		//�¶�Ψһ������ ����
		List<Install_collect_gswb> month_only_install = mapList.get("monthOnly");
		
		//�¶�ȥ�ذ�װ�� ���� ͳ��
		List<Install_collect_gswb> list_01 = this.getGswb_Log_month_install_01(month_install);
		//�¶�Ψһ������ ����
		List<Install_collect_gswb> list_02 = this.getGswb_Log_month_only_01(month_only_install);
		//��ȡ��װ���ݼ���
		List<Install_collect_gswb> list_03 = this.getGswb_Log_Install_01(db_ip,user,pwd,db_name,tab_name);
		
		//���������װ�����ݼ���
		List<Install_collect_gswb> list_sum = new ArrayList<Install_collect_gswb>();
		
		if(list_03.size()>0){
			for(int i=0;i<list_03.size();i++){
				obj = new Install_collect_gswb();
				obj.setQid(list_03.get(i).getQid());							//����
				obj.setAdvertIdentifier(list_03.get(i).getAdvertIdentifier());  //��ȡQID��6λ֮����ַ�
				obj.setAllInstallNum(list_03.get(i).getAllInstallNum());		//�ܵð�װ����
				obj.setOnlyInstallNum(list_03.get(i).getOnlyInstallNum());		//ȥ��֮��ð�װ��
				obj.setAddOnlyPlayerNum(list_03.get(i).getAddOnlyPlayerNum());			//�����ӵ��û���
				obj.setCybercafeInstallNum(list_03.get(i).getCybercafeInstallNum()); 	//���ɰ�װ����
				obj.setAdvertDate(list_03.get(i).getAdvertDate());
				obj.setSoftVer(list_03.get(i).getSoftVer());					//�汾
				//����¶�ȥ�ذ�װ��
				for(int j=0;j<list_01.size();j++){
					if(obj.getQid().equals(list_01.get(j).getQid()) && obj.getSoftVer().equals(list_01.get(j).getSoftVer())){
						obj.setMonthOnlyInstallNum(list_01.get(j).getMonthOnlyInstallNum());
					}
				}
				//����¶�Ψһ������ 
				for(int k=0;k<list_02.size();k++){
					if(obj.getQid().equals(list_02.get(k).getQid()) && obj.getSoftVer().equals(list_02.get(k).getSoftVer())){
						obj.setMonthOnlyInstallNum(list_01.get(k).getMonthAddInstallNum());
					}
				}
				//��Ӽ���
				list_sum.add(obj);
			}
		}
		
		return list_sum;
	}
	
	
	/**
	 * ����������뷨��װͳ�Ʊ�
	 * @param lists : �������뷨��װͳ�Ʊ�
	 * @param tab_name : ����
	 */
	public void insertInstallGwsp(List<Install_collect_gswb> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
		if(lists.size()>0 && lists!=null){
			installDao.insert_Gswb_Log_Install(lists,db_ip,user,pwd,db_name,tab_name);
		}
	}
	
}
