package com.sunxd;


import com.db.info.Dictionary_DB;
import com.server.haha.Service;
import com.sunxd.DBConInfo;

public class ServiceTest {
	
	
	public static void main(String []args)
	{
		System.out.println("programe begin");
		new ServiceTest().run();
	}
	void run()
	
	{
	//�����µ�������Ϣ
			/*DBConInfo dbconGet=new DBConInfo();
			dbconGet.setDb_ip("112.124.97.43:3306");
			dbconGet.setDb_name("haharili_log");
			dbconGet.setUser("zhuyahuangweinia");
			dbconGet.setPwd("vcr4mmdrG6SaCNHV");*/
			
			DBConInfo dbconGet=new DBConInfo();
			dbconGet.setDb_ip("127.0.0.1:3306");
			dbconGet.setDb_name("haharili_log");
			dbconGet.setUser("root");
			dbconGet.setPwd("gaoxin");
			/////////////////////////////////
			/*DBConInfo dbconSet=new DBConInfo();
			dbconSet.setDb_ip("112.124.97.43:3306");
			dbconSet.setDb_name("statistics");
			dbconSet.setUser("zhuyahuangweinia");
			dbconSet.setPwd("vcr4mmdrG6SaCNHV");*/
			
			DBConInfo dbconSet=new DBConInfo();
			dbconSet.setDb_ip("127.0.0.1:3306");
			dbconSet.setDb_name("statistics");
			dbconSet.setUser("root");
			dbconSet.setPwd("gaoxin");
			
			//���20140208���͵�����
			String tab_date_01 = Dictionary_DB.getBeforeDate(0, null).toString();//���ǰһ�������	
			String tab_date_02 = Dictionary_DB.getTodayDate(1).toString();  //����   -2
			String tab_date_07 = Dictionary_DB.getTodayDate(7).toString();  //��7�� -7
			String tab_date_30 = Dictionary_DB.getTodayDate(30).toString(); //��30�� -30
			
			//String[]  strDateArray={tab_date_01,tab_date_02,tab_date_07,tab_date_30};
			String[]  strDateArray={"20140107","20140106","20140105","20140104"};
			
			Service server=new Service(dbconGet, dbconSet);
			
			server.excecute(strDateArray); // ִ�б�Ҫ�Ĳ���
	}

}
