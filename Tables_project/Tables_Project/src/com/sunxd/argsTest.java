package com.sunxd;

import com.db.info.Dictionary_DB;
import com.server.haha.Service;


public class argsTest 

{
	
     public static void main(String[] args)
     {
    	    if(args.length<4)
    	    {
    	    	System.out.println("parameter not enough");	
    	    }
    	    
    	    String strDate=args[0];
    	    String strIPort=args[1];
    	    String strUsr=args[2];
    	    String strPwd=args[3];
    	    
    	    
    		DBConInfo dbconGet=new DBConInfo();
			dbconGet.setDb_ip(strIPort);
			dbconGet.setDb_name("haharili_log");
			dbconGet.setUser(strUsr);
			dbconGet.setPwd(strPwd);
			
			
			DBConInfo dbconSet=new DBConInfo();
			dbconSet.setDb_ip(strIPort);
			dbconSet.setDb_name("statistics");
			dbconSet.setUser(strUsr);
			dbconSet.setPwd(strPwd);
			
			
			String strOneAhead=Dictionary_DB.getBeforeDate_money(1, Integer.valueOf(strDate), -1).toString();
			String strWeekAhead=Dictionary_DB.getBeforeDate_money(1, Integer.valueOf(strDate), -7).toString();
			String strMonthAhead=Dictionary_DB.getBeforeDate_money(1, Integer.valueOf(strDate), -30).toString();
		
			
			//获得20140208类型的日期
			/*String tab_date_01 = Dictionary_DB.getBeforeDate(0, null).toString();//获得前一天的数据	
			String tab_date_02 = Dictionary_DB.getTodayDate(1).toString();  //次日   -2
			String tab_date_07 = Dictionary_DB.getTodayDate(7).toString();  //第7日 -7
			String tab_date_30 = Dictionary_DB.getTodayDate(30).toString(); //第30日 -30
			*/
			
			//String[]  strDateArray={tab_date_01,tab_date_02,tab_date_07,tab_date_30};
			//String[]  strDateArray={"20140107","20140106","20140105","20140104"};
			
			String[]  strDateArray={strDate,strOneAhead,strWeekAhead,strMonthAhead};
			
			Service server=new Service(dbconGet, dbconSet);
			server.excecute(strDateArray); // 执行必要的操作
     }

}
