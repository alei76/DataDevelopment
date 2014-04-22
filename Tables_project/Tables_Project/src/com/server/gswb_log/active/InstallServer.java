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
 * 光速输入法
 * 逻辑业务层
 * 安装表统计
 * @author ZhuYa
 *
 */
public class InstallServer {
	InstallDao installDao = new InstallDao();
	
	/**
	 * 获取光束输入法 安装量
	 * @param db_ip : 连接IP
	 * @param db_name : 数据库名
	 * @param user 	: 用户名
	 * @param pwd	: 密码
	 * @param tab_name : 表名
	 * @return
	 */
	public List<Install_collect_gswb> getGswb_Log_Install_01(String db_ip,String user,String pwd,String db_name,String tab_name){
		List<Install_collect_gswb> installList = installDao.getGswb_Log_Install_01(db_ip, user, pwd, db_name, tab_name);
		return installList;
	}
	
	/**
	 * 
	 * 获取31天的总安装量
	 * @param db_ip : 连接IP
	 * @param user	： 用户名
	 * @param pwd	: 密码
	 * @param db_name	: 数据库名
	 * @param tab_name	: 表名
	 * @return
	 */
	public List<Install_collect_gswb> getGswb_Log_Install_30Days(String db_ip,String user,String pwd,String db_name,String tab_name){
		//定义31天的表名集合
		List<Integer> tab_list_sum = new ArrayList<Integer>();
		
		//获取该库所有install表名
		List<Integer> listTable = installDao.getGswb_Log_Install_tab(db_ip, user, pwd, db_name);
		//集合-1：去除空表 
		if(listTable != null && listTable.size() > 0){
			//############################################## 正式库 3天  改成32天
//			for(int i = 1;i<32;i++){
			for(int i = 1;i<3;i++){
				tab_list_sum.add(listTable.get(i));
			}
		}
		List<Install_collect_gswb> listSum = installDao.getGswb_Log_Install_30Days(db_ip, user, pwd, db_name, tab_name, tab_list_sum);
		return listSum;
	}
	
	/**
	 * 获取月度去重安装量
	 * 获取月度唯一增加量
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public Map<String,List<Install_collect_gswb>> getGswb_Log_Install_30Days_sum(String db_ip,String user,String pwd,String db_name,String tab_name){
		Install_collect_gswb install_statistics = null;
		//存储 月度去重安装量集合,月度唯一增加量安装集合
		Map<String,List<Install_collect_gswb>> mapList= new HashMap<String, List<Install_collect_gswb>>();
		//定义符合查询时间的数据集合
		List<Install_collect_gswb> install_tabDate = null;
		//定义符合查询时间前30天的数据集合
		List<Install_collect_gswb> install_tabDate_30days = null;
		//获取要查询的时间
		Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-8));
		//获取31天的安装数据集合
		List<Install_collect_gswb> install_31days = this.getGswb_Log_Install_30Days(db_ip, user, pwd, db_name, tab_name);
		//分开 获取查询时间的数据集合 跟 查询前30天的数据集合 
		if(install_31days != null && install_31days.size() > 0){
			install_tabDate = new ArrayList<Install_collect_gswb>();		//符合查询时间的数据集合
			install_tabDate_30days = new ArrayList<Install_collect_gswb>(); //符合查询时间前30天的数据集合
			for(int i=0;i<install_31days.size();i++){
				
				install_statistics = new Install_collect_gswb();
				install_statistics.setQid(install_31days.get(i).getQid());					  //渠道
				install_statistics.setSoftVer(install_31days.get(i).getSoftVer());			  //版本
				install_statistics.setUid(install_31days.get(i).getUid());			  		  //用户标示符
				install_statistics.setTab_date(install_31days.get(i).getTab_date());		  //每张表的时间
				//判断是否查询时间的数据
				if(install_statistics.getTab_date() == tab_date){
					//添加查询时间的数据
					install_tabDate.add(install_statistics);
				}else{
					//添加查询时间前30天的数据
					install_tabDate_30days.add(install_statistics); 
				}
			}
		}
		
		//月度去重安装量 集合
		List<Install_collect_gswb> month_install = new ArrayList<Install_collect_gswb>();
		//月度唯一增加量 集合
		List<Install_collect_gswb> month_only_install = new ArrayList<Install_collect_gswb>();
		
		//QID对应的UID 不在前30天的QID数据里 则count(distinct uid)
		if(install_tabDate.size() > 0 && install_tabDate_30days.size() > 0){
			for(int i = 0;i<install_tabDate.size();i++){
				Install_collect_gswb month_obj = new Install_collect_gswb();
				month_obj.setQid(install_tabDate.get(i).getQid());
				month_obj.setSoftVer(install_tabDate.get(i).getSoftVer());
				month_obj.setUid(install_tabDate.get(i).getUid());
				
				//遍历前30天数据
				for(int j = 0;j<install_tabDate_30days.size();j++){
					String qid_02 = install_tabDate_30days.get(j).getSoftVer();
					String uid_02 = install_tabDate_30days.get(j).getUid();
					
					//与前30天相同QID,SoftVer 并且与 前30天不同的统计
					if(month_obj.getQid().equals(qid_02) && !(month_obj.getUid().equals(uid_02))){
						month_install.add(month_obj);
					}
					//与前30天相同SoftVer 并且与 前30天不同的统计
					if(!(month_obj.getUid().equals(uid_02))){
						month_only_install.add(month_obj);
					}
				}
			}
		}
		
		mapList.put("month", month_install);		 //添加 月度去重安装量集合
		mapList.put("monthOnly", month_only_install);//添加 月度唯一增加量安装集合
		return mapList;
	}
	
	/**
	 * 月度去重安装量 集合统计
	 * @param month_install : 月度去重安装量 集合
	 * @return
	 */
	public List<Install_collect_gswb> getGswb_Log_month_install_01(List<Install_collect_gswb> month_install){
		//定义返回月度去重集合统计   
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
			
			//计数器
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
	 * 月度唯一增加量 集合 统计
	 * @param month_only_install : 月度唯一增加量 集合
	 * @return
	 */
	public List<Install_collect_gswb> getGswb_Log_month_only_01(List<Install_collect_gswb> month_only_install){
		//定义返回月度去重集合统计   
		List<Install_collect_gswb> stuList_sum_02 = new ArrayList<Install_collect_gswb>();
		if(month_only_install.size()>0){

			 for (int i = 0 ; i < month_only_install.size(); i ++ ) {
				 Install_collect_gswb intall = new Install_collect_gswb();
				 intall.setQid(month_only_install.get(i).getQid());
				 intall.setUid(month_only_install.get(i).getUid());
				 intall.setSoftVer(month_only_install.get(i).getSoftVer());
				 //去重qid,uid,softVer,删除重复的
			     for ( int j = month_only_install.size() - 1 ; j > i; j -- ) {
			    	 
			       if(intall.getQid().equals(month_only_install.get(j).getQid()) && intall.getUid().equals(month_only_install.get(j).getUid()) && intall.getSoftVer().equals(month_only_install.get(j).getSoftVer())){
			    	   month_only_install.remove(j);
			       }
			     }
			  }
			
			//计数器
			int num = 0;
			for(int i = 0;i<month_only_install.size();i++){
				Install_collect_gswb intall = new Install_collect_gswb();
				intall.setQid(month_only_install.get(i).getQid());
				intall.setUid(month_only_install.get(i).getUid());
				intall.setSoftVer(month_only_install.get(i).getSoftVer());
				intall.setMonthOnlyInstallNum(num);
				num = 1;
				for (int j = month_only_install.size() - 1 ; j > i; j -- ) {
					//去重 qid,softVer ,删除重复的并 增1
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
	 * 安装表总数据集合 统计
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
		//月度去重安装量 集合
		List<Install_collect_gswb> month_install = mapList.get("month");
		//月度唯一增加量 集合
		List<Install_collect_gswb> month_only_install = mapList.get("monthOnly");
		
		//月度去重安装量 集合 统计
		List<Install_collect_gswb> list_01 = this.getGswb_Log_month_install_01(month_install);
		//月度唯一增加量 集合
		List<Install_collect_gswb> list_02 = this.getGswb_Log_month_only_01(month_only_install);
		//获取安装数据集合
		List<Install_collect_gswb> list_03 = this.getGswb_Log_Install_01(db_ip,user,pwd,db_name,tab_name);
		
		//定义输出安装总数据集合
		List<Install_collect_gswb> list_sum = new ArrayList<Install_collect_gswb>();
		
		if(list_03.size()>0){
			for(int i=0;i<list_03.size();i++){
				obj = new Install_collect_gswb();
				obj.setQid(list_03.get(i).getQid());							//渠道
				obj.setAdvertIdentifier(list_03.get(i).getAdvertIdentifier());  //截取QID后6位之外的字符
				obj.setAllInstallNum(list_03.get(i).getAllInstallNum());		//总得安装数量
				obj.setOnlyInstallNum(list_03.get(i).getOnlyInstallNum());		//去重之后得安装量
				obj.setAddOnlyPlayerNum(list_03.get(i).getAddOnlyPlayerNum());			//新增加得用户量
				obj.setCybercafeInstallNum(list_03.get(i).getCybercafeInstallNum()); 	//网吧安装数量
				obj.setAdvertDate(list_03.get(i).getAdvertDate());
				obj.setSoftVer(list_03.get(i).getSoftVer());					//版本
				//添加月度去重安装量
				for(int j=0;j<list_01.size();j++){
					if(obj.getQid().equals(list_01.get(j).getQid()) && obj.getSoftVer().equals(list_01.get(j).getSoftVer())){
						obj.setMonthOnlyInstallNum(list_01.get(j).getMonthOnlyInstallNum());
					}
				}
				//添加月度唯一增加量 
				for(int k=0;k<list_02.size();k++){
					if(obj.getQid().equals(list_02.get(k).getQid()) && obj.getSoftVer().equals(list_02.get(k).getSoftVer())){
						obj.setMonthOnlyInstallNum(list_01.get(k).getMonthAddInstallNum());
					}
				}
				//添加集合
				list_sum.add(obj);
			}
		}
		
		return list_sum;
	}
	
	
	/**
	 * 插入光速输入法安装统计表
	 * @param lists : 光速输入法安装统计表
	 * @param tab_name : 表名
	 */
	public void insertInstallGwsp(List<Install_collect_gswb> lists,String db_ip,String user,String pwd,String db_name,String tab_name){
		if(lists.size()>0 && lists!=null){
			installDao.insert_Gswb_Log_Install(lists,db_ip,user,pwd,db_name,tab_name);
		}
	}
	
}
