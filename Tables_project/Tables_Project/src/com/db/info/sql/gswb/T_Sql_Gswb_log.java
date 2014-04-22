package com.db.info.sql.gswb;

import com.db.info.Dictionary_DB;

/**
 * 光速输入法库 : SQL
 * @author ZhuYa
 *
 */
public class T_Sql_Gswb_log {
	/************************ 光速输入法库 表名  **********************/
	/**
	 * 返回光速输入法库  ：活跃表 : 所有用户总活跃量
	 * @param tab_name : 表名
	 * @param sql
	 * @return
	 */
	public static String return_getGswbAllActive_01(String tab_name){
		Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-6));
		String sql = "select                         "+                                                 
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
		return sql;
	}

	
	/**
	 * 返回光速输入法库:  活跃表
	 * 输入次数小于等于10
	 * 输入次数小于等于140
	 * 输入次数大于140
	 * @param tab_name
	 * @param tab_date
	 * @return
	 */
	public static String return_getGswbAllActive_02(String tab_name){
		String sql = "select 		"+
		"	distinct                                                "+
		"	a.QID,                                                  "+
		"	left(a.qid,length(a.qid)-6) as AdvertIdentifier,        "+
		"	right(a.qid,6) as AdvertDate ,                          "+
		"	b.InputLV1Num,                                          "+
		"	c.InputLV2Num,                                          "+
		"	d.InputLV3Num,                                          "+
		"	a.SoftVer                                               "+
		"from                                                      "+
		"	(select distinct QID,SoftVer from "+tab_name+") a     "+
		"left join                                                 "+
		"(                                                         "+
		"	select                                                  "+
		"		QID			,                                             "+
		"		SoftVer ,                                             "+
		"		count(distinct UID) as InputLV1Num		                "+
		"	from                                                    "+
		"		"+tab_name+"                                        "+
		"	where                                                   "+
		"		InputCount <= 10                                      "+
		"	group by                                                "+
		"		QID,SoftVer                                           "+
		")b on a.QID = b.QID and a.SoftVer = b.SoftVer             "+
		"left join                                                 "+
		"(                                                         "+
		"	select                                                  "+
		"		QID			,                                             "+
		"		SoftVer ,                                             "+
		"		count(distinct UID) as InputLV2Num		                "+
		"	from                                                    "+
		"		"+tab_name+"                                        "+
		"	where                                                   "+
		"		InputCount <= 140                                     "+
		"	group by                                                "+
		"		QID,SoftVer                                           "+
		")c on a.QID = c.QID and a.SoftVer = c.SoftVer             "+
		"left join                                                 "+
		"(                                                         "+
		"	select                                                  "+
		"		QID			,                                             "+
		"		SoftVer ,                                             "+
		"		count(distinct UID) as InputLV3Num		                "+
		"	from                                                    "+
		"		"+tab_name +" "+
		"	where                                                   "+
		"		InputCount > 140                                      "+
		"	group by                                                "+
		"		QID,SoftVer                                           "+
		")d on a.QID = d.QID and a.SoftVer = d.SoftVer             ";
		return sql;
	}
	
	
	/**
	 * 光速输入法库:  活跃表
	 * 安装语句SQL
	 */
	public static String insert_collect_gswb = "insert into active_collect_gswb(AdvertIdentifier,ActiveNum,NewUserActiveNum,InputLV1Num,InputLV2Num,InputLV3Num,AdvertDate,DateLine,SoftVer)" +
			" values(?,?,?,?,?,?,?,?,?)";
	
	
	/**############################################### 在线表(online) 开始 ############################################################**/
	/**
	 * 光速输入法库: 在线表
	 * 返回 在线统计表统计 数据
	 * @param tab_name : 表名
	 * @return
	 */
	public static String return_getGswbOnline(String tab_name){
		String sql = "select                                                           "+
		"	a.qid	,                                                         "+
		"	left(a.qid,length(a.qid)-6) as advertIdentifier,                "+
		"	right(a.qid,6) as advertDate ,                                  "+
		"	a.onlinenum ,                                                   "+
		"	a.servicestarnum ,                                              "+
		"	a.softVer                                                       "+
		"from                                                             "+
		"(                                                                "+
		"	select                                                          "+
		"		qid ,                                                         "+
		"		count(distinct uid) as onlinenum,                             "+
		"		count(case when type = 0 then type end) as servicestarnum ,   "+
		"		softVer                                                       "+
		"	from                                                            "+
		"		"+tab_name+"                                                "+
		"	group by                                                        "+
		"		qid,                                                          "+
		"		type,                                                         "+
		"		softVer                                                       "+
		") a                                                              ";
		return sql;
	}
	
	/**
	 * 光速输入法库:  在线表
	 * 安装语句SQL
	 */
	public static String insert_gswb_online = "insert into online_collect_gswb(AdvertIdentifier,OnlineNum,AdvertDate,DateLine,SoftVer,ServiceStartNum)" +
			" values(?,?,?,?,?,?)";
	
	/**############################################# 在线表(online) 结束  ############################################################**/
	
	
	
	/**############################################### 卸载表(uninstall) 开始 ############################################################**/
	/**
	 * 光速输入法库: 卸载表
	 * 返回 卸载统计表统计 数据
	 * @param tab_name : 表名
	 * @return
	 */
	public static String return_getGswbUninstall(String tab_name){
		String sql = "select qid,count(distinct uid) as uninstNum,softVer,left(qid,length(qid)-6) as advertIdentifier,right(qid,6) as advertDate from "+tab_name+" group by qid,softVer";
		return sql;
	}
	
	/**
	 * 插入光速输入法卸载表
	 * 
	 */
	public static String insert_gswb_uninstall = "insert into uninstall_collect_gswb(AdvertIdentifier,UninstNum,AdvertDate,DateLine,SoftVer)" +
			" values(?,?,?,?,?)";
	/**############################################### 卸载表(uninstall) 结束 ############################################################**/
	
	
	
	
	/**################################################# 安装表(install) 开始 ############################################################**/
	/**
	 * 返回光速输入法库:  安装表
	 * 总得安装数量
	 * 去重之后得安装量
	 * 新增加得用户量
	 * 网吧安装数量
	 * @param tab_name : 表名
	 * @param tab_date : 时间
	 * @param inputType : 输入类型   :  0是自动当前日期,1是手动输入日期
	 * @param inputDate : 手动输入时间
	 * @return
	 */
	public static String return_gswb_log_install_01(String tab_name){
		String sql = "select "+
		"	distinct                                                                                                                                                                                          "+
		"	a.qid	,                                                                                                                                                                                           "+
		"	left(a.qid,length(a.qid)-6) as AdvertIdentifier,                                                                                                                                                  "+
		"	right(a.qid,6) as AdvertDate ,                                                                                                                                                                    "+
		"	a.SoftVer	,                                                                                                                                                                                       "+
		"	b.AllInstallNum	,		                                                                                                                                                                              "+
		"	c.OnlyInstallNum	,                                                                                                                                                                               "+
		"	d.addOnlyPlayerNum	,                                                                                                                                                                             "+
		"	e.cybercafeInstallNum                                                                                                                                                                             "+
		"from                                                                                                                                                                                               "+
		"	 (select distinct QID,SoftVer from "+tab_name+") a                                                                                                                                              "+
		"left join                                                                                                                                                                                          "+
		"	(select qid,softver,count(uid) as allInstallNum from "+tab_name+"  group by qid,softver) b on a.qid = b.qid and a.softver = b.softver                                 "+
		"left join                                                                                                                                                                                          "+
		"	(select qid,softver,count(distinct uid) as onlyInstallNum from "+tab_name+"  group by qid,softver) c on a.qid = c.qid and a.softver = c.softver                       "+
		"left join                                                                                                                                                                                          "+
		"	(select qid,softver,count(distinct uid) as addOnlyPlayerNum from "+tab_name+" where IsOld = 0  group by qid,softver) d on a.qid = d.qid and a.softver = d.softver       "+
		"left join                                                                                                                                                                                          "+
		"	(select qid,softver,count(distinct uid) as cybercafeInstallNum from "+tab_name+" where IsWB = 1  group by qid,softver) e on a.qid = e.qid and a.softver = e.softver     ";
		return sql;
	}
	
	/**
	 * 返回查询安装表30Days
	 * @param tab_name
	 * @return
	 */
	public static String return_sql_gswb_all_install30Days(String tab_name){
		String sql = "select distinct qid,uid,softver from "+tab_name;
		return sql;
	}
	
	/**
	 * 出入光速输入法安装表
	 */
	public static String insert_gswb_install = "insert into install_collect_gswb(AdvertIdentifier,AllInstallNum,OnlyInstallNum,AddOnlyPlayerNum,CybercafeInstallNum,MonthOnlyInstallNum,MonthAddInstallNum,AdvertDate,DateLine,SoftVer)" +
	" values(?,?,?,?,?,?,?,?,?,?)";
	
	/**################################################# 安装表(install) 结束 ############################################################**/
	
	
	/**############################################### 新用户信息表(new_user_data_gswb) 开始 #############################################**/
	
	/**
	 * 返回查询: 当日新用户安装量,当日新用户卸载量,当日新用户活跃量,次日活跃用户数
	 * @param tab_name : 表名
	 * @return
	 */
	public static String return_sql_new_user01(String tab_name){
		Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-6));
		Integer tab_date_02 = Dictionary_DB.getTodayDate(tab_date.toString(),1); //次日
		String sql = "select " +
		"max(case when  b.type ='install' then b.num end) as todayNewInstallNum,"+
		"max(case when  b.type ='uninstall' then b.num end) as  todayNewUninstallNum,"+
		"max(case when  b.type ='active' then b.num end) as todayNewActiveNum,"+
		"max(case when  b.type ='nextActive' then b.num end) as nextDayActiveNum,"+
		"b.dateline from ("+
		"select                                                                                                                                                                     "+
		"	a.type	,                                                                                                                                                                 "+
		"	a.num	,                                                                                                                                                                  "+
		"	a.dateline																																								"+
		"from                                                                                                                                                                       "+
		"(                                                                                                                                                                          "+
		"select 'install' as type , sum(OnlyInstallNum) as num ,dateline from install_collect_gswb where dateline="+tab_date+"  group by dateline   "+
		"union all                                                                                                                                                                  "+
		"select 'uninstall' as type , sum(UninstNum) as num,dateline from uninstall_collect_gswb where dateline="+tab_date+" and AdvertDate = "+tab_date+"  group by dateline     "+
		"union all                                                                                                                                                                  "+
		"select 'active' as type , sum(NewUserActiveNum)as num ,dateline from active_collect_gswb where dateline="+tab_date+"  group by dateline	  "+
		"union all																																									"+
		"select 'nextActive' as type , sum(NewUserActiveNum)as num ,"+tab_date+" as dateline from active_collect_gswb where dateline="+tab_date_02+" group by dateline"+
		") a  ) b                                                                                                                                                                      ";
		return sql;
	}
	
	/**
	 * 返回查询: 7日活跃量,30日活跃量,30日卸载量
	 * @param tab_name : 表名
	 * @return
	 */
	public static String return_sql_new_user02(String tab_name){
		Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-6));
		Integer days_07 = Dictionary_DB.getTodayDate(tab_date.toString(),7);   //7日
		Integer days_30 = Dictionary_DB.getTodayDate(tab_date.toString(),30);  //30日
		String sql = "select " +
		"max(case when  b.type ='active7Days' then b.num end) as 7DayActiveNum,"+
		"max(case when  b.type ='active30Days' then b.num end) as 30DayActiveNum,"+
		"max(case when  b.type ='uninstall30Days' then b.num end) as 30DayUninstallNum,"+
		"b.dateline from ("+
		"select                                                                                                                                                                                   "+
		"	a.type,                                                                                                                                                                                            "+
		"	sum(a.num) as num,                                                                                                                                                                                          "+
		"	a.dateline																																													"+
		"from                                                                                                                                                                                                "+
		"(                                                                                                                                                                                                   "+
		"	select 'active7Days' as type , sum(NewUserActiveNum)as num ,"+tab_date+" as dateline from active_collect_gswb where dateline between "+days_07+" and "+tab_date+"  group by dateline    "+
		"	union all                                                                                                                                                                                          "+
		"	select 'active30Days' as type , sum(NewUserActiveNum)as num ,"+tab_date+" as dateline from active_collect_gswb where dateline between "+days_30+" and "+tab_date+"  group by dateline   "+
		"	union all                                                                                                                                                                                          "+
		"	select 'uninstall30Days' as type , sum(UninstNum) as num, "+tab_date+" as dateline from uninstall_collect_gswb where dateline between "+days_30+" and "+tab_date+"  group by dateline    "+
		")a  group by a.type,a.dateline )b                                                                                                                                                                     "; 
		return sql;
	}
	
	/**
	 * 添加到新用户信息表
	 */
	public static String insert_new_user_gswb = "insert into new_user_data_gswb(DateLine,TodayNewInstallNum,TodayNewUninstallNum,TodayNewActiveNum,NextDayActiveNum,7DayActiveNum,30DayActiveNum,30DayUninstallNum)" +
			" values(?,?,?,?,?,?,?,?)";
	/**############################################### 新用户信息表(new_user_data_gswb) 结束 #############################################**/
}
