package com.db.info.sql.gswb;

import com.db.info.Dictionary_DB;

/**
 * �������뷨�� : SQL
 * @author ZhuYa
 *
 */
public class T_Sql_Gswb_log {
	/************************ �������뷨�� ����  **********************/
	/**
	 * ���ع������뷨��  ����Ծ�� : �����û��ܻ�Ծ��
	 * @param tab_name : ����
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
	 * ���ع������뷨��:  ��Ծ��
	 * �������С�ڵ���10
	 * �������С�ڵ���140
	 * �����������140
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
	 * �������뷨��:  ��Ծ��
	 * ��װ���SQL
	 */
	public static String insert_collect_gswb = "insert into active_collect_gswb(AdvertIdentifier,ActiveNum,NewUserActiveNum,InputLV1Num,InputLV2Num,InputLV3Num,AdvertDate,DateLine,SoftVer)" +
			" values(?,?,?,?,?,?,?,?,?)";
	
	
	/**############################################### ���߱�(online) ��ʼ ############################################################**/
	/**
	 * �������뷨��: ���߱�
	 * ���� ����ͳ�Ʊ�ͳ�� ����
	 * @param tab_name : ����
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
	 * �������뷨��:  ���߱�
	 * ��װ���SQL
	 */
	public static String insert_gswb_online = "insert into online_collect_gswb(AdvertIdentifier,OnlineNum,AdvertDate,DateLine,SoftVer,ServiceStartNum)" +
			" values(?,?,?,?,?,?)";
	
	/**############################################# ���߱�(online) ����  ############################################################**/
	
	
	
	/**############################################### ж�ر�(uninstall) ��ʼ ############################################################**/
	/**
	 * �������뷨��: ж�ر�
	 * ���� ж��ͳ�Ʊ�ͳ�� ����
	 * @param tab_name : ����
	 * @return
	 */
	public static String return_getGswbUninstall(String tab_name){
		String sql = "select qid,count(distinct uid) as uninstNum,softVer,left(qid,length(qid)-6) as advertIdentifier,right(qid,6) as advertDate from "+tab_name+" group by qid,softVer";
		return sql;
	}
	
	/**
	 * ����������뷨ж�ر�
	 * 
	 */
	public static String insert_gswb_uninstall = "insert into uninstall_collect_gswb(AdvertIdentifier,UninstNum,AdvertDate,DateLine,SoftVer)" +
			" values(?,?,?,?,?)";
	/**############################################### ж�ر�(uninstall) ���� ############################################################**/
	
	
	
	
	/**################################################# ��װ��(install) ��ʼ ############################################################**/
	/**
	 * ���ع������뷨��:  ��װ��
	 * �ܵð�װ����
	 * ȥ��֮��ð�װ��
	 * �����ӵ��û���
	 * ���ɰ�װ����
	 * @param tab_name : ����
	 * @param tab_date : ʱ��
	 * @param inputType : ��������   :  0���Զ���ǰ����,1���ֶ���������
	 * @param inputDate : �ֶ�����ʱ��
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
	 * ���ز�ѯ��װ��30Days
	 * @param tab_name
	 * @return
	 */
	public static String return_sql_gswb_all_install30Days(String tab_name){
		String sql = "select distinct qid,uid,softver from "+tab_name;
		return sql;
	}
	
	/**
	 * ����������뷨��װ��
	 */
	public static String insert_gswb_install = "insert into install_collect_gswb(AdvertIdentifier,AllInstallNum,OnlyInstallNum,AddOnlyPlayerNum,CybercafeInstallNum,MonthOnlyInstallNum,MonthAddInstallNum,AdvertDate,DateLine,SoftVer)" +
	" values(?,?,?,?,?,?,?,?,?,?)";
	
	/**################################################# ��װ��(install) ���� ############################################################**/
	
	
	/**############################################### ���û���Ϣ��(new_user_data_gswb) ��ʼ #############################################**/
	
	/**
	 * ���ز�ѯ: �������û���װ��,�������û�ж����,�������û���Ծ��,���ջ�Ծ�û���
	 * @param tab_name : ����
	 * @return
	 */
	public static String return_sql_new_user01(String tab_name){
		Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-6));
		Integer tab_date_02 = Dictionary_DB.getTodayDate(tab_date.toString(),1); //����
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
	 * ���ز�ѯ: 7�ջ�Ծ��,30�ջ�Ծ��,30��ж����
	 * @param tab_name : ����
	 * @return
	 */
	public static String return_sql_new_user02(String tab_name){
		Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-6));
		Integer days_07 = Dictionary_DB.getTodayDate(tab_date.toString(),7);   //7��
		Integer days_30 = Dictionary_DB.getTodayDate(tab_date.toString(),30);  //30��
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
	 * ��ӵ����û���Ϣ��
	 */
	public static String insert_new_user_gswb = "insert into new_user_data_gswb(DateLine,TodayNewInstallNum,TodayNewUninstallNum,TodayNewActiveNum,NextDayActiveNum,7DayActiveNum,30DayActiveNum,30DayUninstallNum)" +
			" values(?,?,?,?,?,?,?,?)";
	/**############################################### ���û���Ϣ��(new_user_data_gswb) ���� #############################################**/
}
