package com.db.info.sql.haha;

import com.job.haha.EnumReqName;

public class Gen_Sql_haha_Collect {

	public static String getSQL_query(String str) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getSQL_insertFromTbName(String tableName) {
		// TODO Auto-generated method stub
		String insert_sql;
		
		/*
		 * newVCU` int(10) NOT NULL,
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Time` int(10) unsigned NOT NULL COMMENT '时间',
  `InstallCount` int(10) NOT NULL COMMENT '当日新用户安装量',
  `UninstallCount` int(10) NOT NULL COMMENT '当日新用户卸载量',
  `LaunchCount` int(32) NOT NULL COMMENT '当日新用户服务启动量',
  `ActiveCount` int(32) NOT NULL COMMENT '当日新用户活跃量',
  `NextDayActiveCount` int(32) NOT NULL COMMENT '次日用户活跃量',
  `NextDayActiveRate` decimal(6,2) unsigned DEFAULT NULL COMMENT '次日活跃率',
  `WeekActiveCount` int(32) NOT NULL COMMENT '7日活跃量',
  `WeekActiveRate` decimal(6,2) unsigned DEFAULT NULL COMMENT '7日活跃率',
  `MonthActiveCount` int(32) NOT NULL COMMENT '30日活跃量',
  `MonthActiveRate` decimal(6,2) unsigned DEFAULT NULL COMMENT '30日活跃率',
		 * */
		if(tableName.equals("NewUserStatDAO")) 
		{
			insert_sql= "insert into haha_new_user_stat(Time,newVCU,InstallCount,UninstallCount,LaunchCount,ActiveCount,NextDayActiveCount,NextDayActiveRate,WeekActiveCount,WeekActiveRate,MonthActiveCount,MonthActiveRate)" +
					" values(?,?,?,?,?,?,?,?,?,?,?,?)";
		}
		
		else
		{	
			return null;
		}
		
		return insert_sql;
	}
	
	
	public static String getSqlTemplateFromTable(String tab_name){
		Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-6));
		String sql1="select <fieldList> from"; 
		String strMasterTb=""; 
		String strCat1="left join table b on a.qid=b.qid and a.softwareVer=b.softwareVer";
		String strCat2="left join table c on a.qid=c.qid and";
		String strCon="where ";
		
		String sql=sql1+strMasterTb+strCat1+strCat2+strCon;
		
		return sql;
	}
	
	
	
    public static String getSqlAssembleFactorybyReq(EnumReqName reqName,String tab_name) // 工厂方法模式
    {
    	Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-6));
    	String rst=null;
    	
    	switch(reqName) 
    	{
    	case NewUserStat:
    		rst="select                         "+                                                 
    				"		                 		a.qid		,               "+                                                  
    				"		                 		a.softVer	,             "+                                                  
    				"		                 		b.activeNum	,           "+                                                  
    				"		                 		c.newUserActiveNum ,    "+                                                  
    				"		                 		b.advertIdentifier	,   "+                                                  
    				"		                 		b.advertDate	          "+                                                  
    				"		                 	from                      "+                                                  
    				"		                 		(select distinct qid,softVer from "+tab_name+") a "+                      
    				"		                 	left join                                           "  +                      
    				"		                 	(	                                                  "  +                      
    				"		                 		select                                            "  +                      
    				"		                 			qid,                                            "  +                      
    				"		                 			count(distinct UID) as activeNum,               "  +                      
    				"		                 			left(qid,length(qid)-6) as advertIdentifier,    "  +                      
    				"		                 			right(qid,6) as advertDate,                     "  +                      
    				"		                 			softVer                                         "  +                      
    				"		                 		from                                              "  +                      
    				"		                 			"+tab_name+"                                  "  +                      
    				"		                 		group by                                          "  +                      
    				"		                 			qid,softVer                                     "  +                      
    				"		                 	)b on a.qid = b.qid and a.softVer = b.softVer       "  +                      
    				"		                 	left join                                           "  +                      
    				"		                 	(                                                   "  +                      
    				"		                 		select                                            "  +                      
    				"		                 			a.qid		 ,                                      "  +                      
    				"		                 			a.softVer,                                      "  +                      
    				"		                 			count(distinct a.uid) as newUserActiveNum       "  +                      
    				"		                 		from                                              "  +                      
    				"		                 		(	"+                                                                        
    				"		                 			select qid,softVer,uid,right(qid,6) as advertDate from "+tab_name+" "+   
    				"		                 		) a                                                                    "+   
    				"		                 		where                                                                  "+   
    				"		                 			a.advertDate ="+tab_date+"                                                 "+   
    				"		                 		group by                                                               "+   
    				"		                 			a.qid,a.softVer                                                      "+   
    				"		                 	)c on a.qid = c.qid and a.softVer = c.softVer                            ";
    	break;
    	default: rst=null;
    	
    	}
    	
    	
		return rst;
    	
    }
    
	

}
