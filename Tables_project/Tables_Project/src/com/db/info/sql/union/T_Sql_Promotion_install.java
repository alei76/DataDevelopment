package com.db.info.sql.union;

import com.db.info.Dictionary_DB;

/**
 * װ������ ��װ��ϸ
 * @author ZhuYa
 *
 */
public class T_Sql_Promotion_install {
	/**
	 * ����װ�����˰�װ��ϸ
	 * ͬһ���˺�/��ͬ�˺� ֻ���ڲ�ͬ�Ļ�����������㰲װ��
	 * �෴ ��ͬ���˺���ͬһ�������ϣ����㰲װ��
	 * @param tab_name : ����
	 * @param sql
	 * @return
	 */
	public static String return_getPromotionInstall(String todayDate){
		String tab_date = todayDate.substring(todayDate.length()-8); //��ȡʱ��
		String sql = "select min(id) as insId,qid as uid,count(distinct uid) as num,SoftID,SoftVer,FROM_UNIXTIME(Time,'%Y%m%d') as dateline,uid as pc_id from install"+tab_date+" group by softID,SoftVer,uid order by id";
		return sql;
	}
	
	/**
	 * ���� װ�����˰�װ��ϸ
	 */
	public static String insert_PromotionInstall = "insert into promotion_install(uid,software_id,software_version,num,dateline,pc_id,install_id)" +
	" values(?,?,?,?,?,?,?)";
	
	/**
	 * ����װ����������ʹ����ϸ
	 * @param tab_name : ����
	 * @param sql
	 * @return
	 */
	public static String return_getPromotionOnlineOld(Integer inputType,Integer inputDate){
		String tab_date_be1 = Dictionary_DB.getBeforeDate_money(inputType, inputDate, -1).toString();
		String tab_date_be2 = Dictionary_DB.getBeforeDate_money(inputType, inputDate, -2).toString();
		String tab_date_be3 = Dictionary_DB.getBeforeDate_money(inputType, inputDate, -3).toString();
		String sql = "select a.pc_id, a.softID, a.softVer, min(a.id) as onlineId ,a.uid ,"+tab_date_be1+" as dateline from						  "+
				"	(select id,ipdress,uid as pc_id,softID,softVer,qid as uid from online"+tab_date_be1+") a                    "+
				"inner join                                                                                                   "+
				"	(select distinct uid as pc_id,qid as uid  from online"+tab_date_be2+") b on a.pc_id = b.pc_id and a.uid = b.uid   "+
				"inner join                                                                                                   "+
				"	(select distinct uid as pc_id,qid as uid  from online"+tab_date_be3+") c on a.pc_id = c.pc_id and a.uid = c.uid group by a.pc_id, a.softID, a.softVer order by a.id ";
		return sql;
	}
	
	/**
	 * ����װ����������ʹ����ϸ
	 */
	public static String return_getPromotionOnline ="select uid as pc_id, softID, softVer, min(id) as onlineId ,qid as uid   from online? group by uid, softID, softVer,qid order by id";
	
	/**
	 * ���� װ�����˰�װ��ϸ
	 */
	public static String insert_PromotionOnline = "insert into promotion_online(uid,software_id,software_version,dateline,pc_id,online_id)" +
	" values(?,?,?,?,?,?)";
	
	/**
	 * ���� 127����Ч��װ�û���
	 */
	public static String insert_PromotionEffective = "insert into promotion_effective (uid,num,dateline,soft_name) values(?,?,?,?) "; 
	
	/**
	 * ����װ�������ܱ� (1) : union 127��
	 * @param tab_name : ����
	 * @param sql
	 * @return
	 */
	public static String return_getPromotionAll_01(String tab_name){
		String tab_date = tab_name.substring(tab_name.length()-8); //��ȡʱ��
		String sql = "select                                                                                                                                                                                     "+
				"	"+tab_date+" as dateline	,                                                                                                                                                                   "+
				"	max(case when a.unionName = 'faFang' then a.num else 0 end) as faFangNum,																                                                                                  "+
				"	max(case when a.unionName = 'faFang' then a.integrationNum else 0 end) as faFangIntegrationNum ,				                                                                                  "+
				"	max(case when a.unionName = 'installationToday' then a.num else 0 end) as installationTodayNum ,				                                                                                  "+
				"	max(case when a.unionName = 'installationToday' then a.integrationNum else 0 end) as integrationNum ,		                                                                                  "+
				"	max(case when a.unionName = 'registrationToday' then a.num else 0 end) as regNum ,											                                                                                  "+
				"	max(case when a.unionName = 'registrationToday' then a.integrationNum else 0 end) as regIntegrationNum ,                                                                                  "+
				"	max(case when a.unionName = 'useIntegration' then a.num else 0 end) as useNum ,													                                                                                  "+
				"	max(case when a.unionName = 'useIntegration' then a.integrationNum else 0 end) as useIntegrationNum ,		                                                                                  "+
				"	max(case when a.unionName = 'duihuan' then a.num else 0 end) as duihuanNum ,														                                                                                  "+
				"	max(case when a.unionName = 'duihuan' then a.integrationNum else 0 end) as dhIntegrationNum ,						                                                                                  "+
				"	max(case when a.unionName = 'tixian' then a.num else 0 end) as tixianNum ,															                                                                                  "+
				"	max(case when a.unionName = 'tixian' then a.integrationNum else 0 end) as txIntegrationNum ,						                                                                                  "+
				"	max(case when a.unionName = 'integrationAll' then a.integrationNum else 0 end) as intAllIntegrationNum ,                                                                                  "+
				"	max(case when a.unionName = 'registrationAll' then a.integrationNum else 0 end) as regAllIntegrationNum,                                                                                  "+
				"	max(case when a.unionName = 'installationAll' then a.integrationNum else 0 end) as insAllIntegrationNum,                                                                                   "+
				"	max(case when a.unionName = 'activeAtionAll' then a.integrationNum else 0 end) as ateAllIntegrationNum                                                                                   "+
				"from                                                                                                                                                                                       "+
				"(                                                                                                                                                                                          "+
				"	select 'faFang' as unionName, count(distinct uid) as num ,sum(credit) as integrationNum from credit_stat where FROM_UNIXTIME(dateline,'%Y%m%d') = "+tab_date+"			                          "+
				"	union all                                                                                                                                                                                 "+
				"	select 'installationToday' as unionName,count(distinct uid) as num,sum(credit) as integrationNum from credit_stat where type =2 and FROM_UNIXTIME(dateline,'%Y%m%d') = "+tab_date+" 	        "+
				"	union all                                                                                                                                                                                 "+
				"	select 'registrationToday' as unionName,count(distinct uid) as num,sum(credit) as integrationNum from credit_stat where type = 1 and FROM_UNIXTIME(dateline,'%Y%m%d') = "+tab_date+"		      "+
				"	union all                                                                                                                                                                                 "+
				"	select 'useIntegration' as unionName,count(distinct uid) as num,sum(credit) as integrationNum from credit_expense where FROM_UNIXTIME(dateline,'%Y%m%d') = "+tab_date+"			                  "+
				"	union all                                                                                                                                                                                 "+
				"	select 'duihuan' as unionName,count(distinct uid) as num,sum(credit) as integrationNum from exchange where type =1 and status = 2 and FROM_UNIXTIME(datetime,'%Y%m%d') = "+tab_date+"		      "+
				"	union all                                                                                                                                                                                 "+
				"	select 'tixian' as unionName,count(distinct uid) as num , sum(credit) as integrationNum from exchange where type = 2 and status = 2 and FROM_UNIXTIME(datetime,'%Y%m%d') = "+tab_date+"       "+
				"	union all                                                                                                                                                                                 "+
				"	select 'integrationAll' as unionName, 0 as num , sum(credit) as integrationNum from credit_stat where FROM_UNIXTIME(dateline,'%Y%m%d')<= "+tab_date+"                                         "+
				"	union all                                                                                                                                                                                 "+
				"	select 'registrationAll' as unionName, 0 as num , sum(credit) as integrationNum from credit_stat where type = 1 and FROM_UNIXTIME(dateline,'%Y%m%d')<= "+tab_date+"                           "+
				"	union all                                                                                                                                                                                 "+
				"	select 'installationAll' as unionName, 0 as num , sum(credit) as integrationNum from credit_stat where type = 2	and FROM_UNIXTIME(dateline,'%Y%m%d')<= "+tab_date+"                           "+
				"	union all                                                                                                                                                                                 "+
				"	select 'activeAtionAll' as unionName, 0 as num , sum(credit) as integrationNum from credit_stat where type = 10	and FROM_UNIXTIME(dateline,'%Y%m%d')<= "+tab_date+"                           "+
				")a                                                                                                                                                                                         ";
		return sql;
	}
	
	/**
	 * ����װ�������ܱ� (2) : union 127��
	 * @param tab_name : ����
	 * @param sql
	 * select 'landedNum' as unionName, count(a.last_login_time) as num,0 as integrationNum from (select FROM_UNIXTIME(last_login_time,'%Y%m%d') as dateline,last_login_time from user) a where a.dateline
	 * @return
	 */
	public static String return_getPromotionAll_02(String tab_name){
		String tab_date = tab_name.substring(tab_name.length()-8); //��ȡʱ��
		String sql = "select                                                                                                                                                                                                                  "+
				"	max(case when a.unionName = 'activeNum' then a.num else 0 end) as activeNum ,                                                                                                                                          "+
				"	max(case when a.unionName = 'spreadNum' then a.num else 0 end) as spreadNum ,                                                                                                                                          "+
				"	max(case when a.unionName = 'spreadNum' then a.integrationNum else 0 end) as spreadJiFenNum ,                                                                                                                          "+
				"	max(case when a.unionName = 'consumeNum' then a.integrationNum else 0 end) as consumeNum ,                                                                                                                                "+
				"	max(case when a.unionName = 'landedNum' then a.num else 0 end) as landedNum , 	                                                                                                                                       "+
				"	max(case when a.unionName = 'downloadNum' then a.num else 0 end) as downloadNum                                                                                                                                       "+
				"from                                                                                                                                                                                                                    "+
				"(	                                                                                                                                                                                                                     "+
				"	select 'activeNum' as unionName , count(distinct uid) as num,0 as integrationNum from promotion_install where dateline = "+tab_date+"  				                                                                           "+
				"	union all                                                                                                                                                                                                              "+
				"	select 'spreadNum' as unionName , count(distinct uid) as num,sum(credit) as integrationNum from credit_stat where type = 2 and FROM_UNIXTIME(dateline,'%Y%m%d') = "+tab_date+"                                           "+
				"	union all                                                                                                                                                                                                              "+
				"	select 'consumeNum' as unionName , 0 as num,sum(credit) as integrationNum from credit_expense where FROM_UNIXTIME(dateline,'%Y%m%d') = "+tab_date+"                                                                      "+
				"	union all	                                                                                                                                                                                                             "+
				"	select 'landedNum' as unionName, count(distinct uid)  as num,0 as integrationNum from login_log where ymd = "+tab_date+"       "+
				"	union all                                                                                                                                                                                                              "+
				"	select 'downloadNum' as unionName, count(distinct uid) as num, 0 as integrationNum from download_log where FROM_UNIXTIME(dateline,'%Y%m%d') = "+tab_date+"                                                                                       "+
				")a                                                                                                                                                                                                                      ";
		return sql;
	}
	
	/**
	 * ����װ�������ܱ� �ֶ� : ��ʧ�û�
	 * @param tab_name
	 * @return
	 */
	public static String return_getPromotionAll_lostNum(String tab_name){
		String tab_date = tab_name.substring(tab_name.length()-8); //��ȡʱ��
		String sql = "select count(distinct a.uid) as num from (                                                                                                    "+
				"	select                                                                                                                                "+
				"		uid, dateline                                                                                                                       "+
				"	from                                                                                                                                  "+
				"	(                                                                                                                                     "+
				"		select                                                                                                                              "+
				"			uid	,                                                                                                                             "+
				"			type ,                                                                                                                            "+
				"			min(from_unixtime(dateline,'%Y%m%d')) as dateline                                                                                 "+
				"		from                                                                                                                                "+
				"			credit_stat                                                                                                                       "+
				"		where                                                                                                                               "+
				"			(from_unixtime(dateline,'%Y%m%d') between date_sub("+tab_date+", interval 30 day) and date("+tab_date+"))                             "+
				"		group by                                                                                                                            "+
				"			uid	,type ,from_unixtime(dateline,'%Y%m%d')                                                                                       "+
				"		HAVING type not in (2,100,10)                                                                                                       "+
				"	)a 			                                                                                                                         "+
				") a                                                                                                                                    "+
				"inner join                                                                                                                             "+
				"(                                                                                                                                      "+
				"	select                                                                                                                                "+
				"		min(from_unixtime(dateline,'%Y%m%d')) as dateline,                                                                                  "+
				"		uid                                                                                                                                 "+
				"	from                                                                                                                                  "+
				"		credit_stat where (from_unixtime(dateline,'%Y%m%d') between date_sub("+tab_date+", interval 30 day) and date("+tab_date+")) group by uid"+
				") b on a.uid = b.uid                                                                                                                   "+
				"where                                                                                                                                  "+
				"	TIMESTAMPDIFF(DAY,b.dateline,"+tab_date+") = 30																						";
		return sql;
	}
	
	/**
	 * ����װ�������ܱ� (3) : union 127��
	 * ��Ҫ:���ջ�Ծ��,���ջ�Ծ��,7�ջ�Ծ��,30�ջ�Ծ��
	 * @param tab_name : ����
	 * @param tab_date : 
	 * @param tab_date_01 : ����
	 * @param tab_date_07 : ��7��
	 * @param tab_date_30 : ��30��
	 * @param sql
	 * @return
	 */
	public static String return_getPromotionAll_03(String tab_date,String tab_date_01,String tab_date_07,String tab_date_30){
		String sql = "select																																																												"+
				"	max(case when a.unionName = 'login' then a.num else 0 end) as loginNum ,												                            "+
				"	max(case when a.unionName = 'regInsActive' then a.num else 0 end) as regInsActiveNum ,							                        "+
				"	max(case when a.unionName = 'nextRegInsActive' then a.num else 0 end) as nextRegInsActive ,			                        "+
				"	max(case when a.unionName = 'next7DayRegInsActive' then a.num else 0 end) as next7DayRegInsActive ,                      "+
				"	max(case when a.unionName = 'next30DayRegInsActive' then a.num else 0 end) as next30DayRegInsActive		                      "+
				"from                                                                                                                         "+
				"(	                                                                                                                          "+
				"	select 'login' as unionName, count(reg_dateline) as num from user where FROM_UNIXTIME(reg_dateline,'%Y%m%d') = "+tab_date+"   "+
				"	union all                                                                                                                   "+
				"	select                                                                                                                      "+
				"		'regInsActive' as unionName ,                                                                                             "+
				"		count(distinct a.id) as num                                                                                               "+
				"	from                                                                                                                        "+
				"		(select distinct id from user where from_unixtime(reg_dateline,'%Y%m%d') = "+tab_date+") a                                  "+
				"	inner join                                                                                                                  "+
				"		(select distinct uid from promotion_install where dateline = "+tab_date+") b on a.id = b.uid                                "+
				"	union all                                                                                                                   "+
				"	select                                                                                                                      "+
				"		'nextRegInsActive' as unionName	,                                                                                         "+
				"		count(distinct a.id) as nextNum                                                                                           "+
				"	from                                                                                                                        "+
				"		(select distinct id from user where from_unixtime(reg_dateline,'%Y%m%d') = "+tab_date_01+") a                                  "+
				"	inner join                                                                                                                  "+
				"		(select distinct uid from promotion_install where dateline = "+tab_date+") b on a.id = b.uid                                "+
				"	union all                                                                                                                   "+
				"	select                                                                                                                      "+
				"		'next7DayRegInsActive' as unionName	,                                                                                     "+
				"		count(distinct a.id) as nextNum                                                                                           "+
				"	from                                                                                                                        "+
				"		(select distinct id from user where from_unixtime(reg_dateline,'%Y%m%d') = "+tab_date_07+") a                                  "+
				"	inner join                                                                                                                  "+
				"		(select distinct uid from promotion_install where dateline = "+tab_date+") b on a.id = b.uid                                "+
				"	union all                                                                                                                   "+
				"	select                                                                                                                      "+
				"		'next30DayRegInsActive' as unionName	,                                                                                   "+
				"		count(distinct a.id) as nextNum                                                                                           "+
				"	from                                                                                                                        "+
				"		(select distinct id from user where from_unixtime(reg_dateline,'%Y%m%d') = "+tab_date_30+") a                                  "+
				"	inner join                                                                                                                  "+
				"		(select distinct uid from promotion_install where dateline = "+tab_date+") b on a.id = b.uid                                "+
				") a                                                                                                                          ";
		return sql;
	}
	
	
	/**
	 * ���뵽װ�������ܱ�
	 */
	public static String insert_PromotionAll = "insert into promotion_all_union(dateline,activeNum,spreadNum,spreadJiFenNum,consumeNum,login,landedNum,downloadNum,lostNum,regInsActive,Activepercentage,nextRegInsActive,nextActivePercentage,next7DayRegInsActive,next7DayPercentage,next30DayRegInsActive,next30DayPercentage,faFangNum,faFangIntegrationNum,installationTodayNum,integrationNum,regNum,regIntegrationNum,useNum,useIntegrationNum,duihuanNum,dhIntegrationNum,tixianNum,txIntegrationNum,intAllIntegrationNum,regAllIntegrationNum,insAllIntegrationNum,ateAllIntegrationNum)" +
			" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	
	/**
	 * ����װ�������ܱ� ����
	 */
	public static String updateNextDay_PromotionAll(double nextActive,int nextRegInsActive, String tab_beforeDate){
		String sql = "update promotion_all_union set nextActivePercentage = "+nextActive+" ,nextRegInsActive = "+nextRegInsActive+" where dateline = "+tab_beforeDate+" ";
		return sql;
	}
	
	/**
	 * ����װ�������ܱ� 7��
	 */
	public static String updateNext7Day_PromotionAll(double nextActive,int nextRegInsActive, String tab_beforeDate){
		String sql = "update promotion_all_union set next7DayPercentage = "+nextActive+" ,next7DayRegInsActive = "+nextRegInsActive+" where dateline = "+tab_beforeDate+" ";
		return sql;
	}
	
	/**
	 * ����װ�������ܱ� 30��
	 */
	public static String updateNext30Day_PromotionAll(double nextActive,int nextRegInsActive, String tab_beforeDate){
		String sql = "update promotion_all_union set next7DayPercentage = "+nextActive+" ,next30DayRegInsActive = "+nextRegInsActive+" where dateline = "+tab_beforeDate+" ";
		return sql;
	}
	
	/**
	 * װ�����ˣ����ͳ��
	 * @param tab_date
	 * @return
	 */
	public static String return_getPromotionSoftware(String tab_date){
		String sql = "select                                                                                                                                                        "+
				"	"+tab_date+" as dateline ,                                                                                                                                       "+
				"	a.softName	,                                                                                                                                                "+
				"	max(case when a.title_name = 'useNum' then a.num else 0 end) as useNum ,                                                                                           "+
				"	max(case when a.title_name = 'pcNum' then a.num else 0 end) as pcNum                                                                                               "+
				"from                                                                                                                                                          "+
				"(	                                                                                                                                                           "+
				"	select 'pcNum' as title_name,count(distinct uid) as num,SoftID as softName from install"+tab_date+" group by SoftID "+
				"	union all                                                                                                                                                  "+
				"	select 'useNum' as title_name,count(distinct qid) as num,SoftID as softName from online"+tab_date+" group by SoftID "+
				") a group by a.softName                                                                                                                                       ";
		return sql;
	}
	
	/**
	 * װ������ : ���ͳ�� : ��123IP��ַͳ��
	 */
	public static String return_getHao123 = "select count(distinct uid) as pcNum, sum(ip_count) as useNum, sum(credit) as credit,name,ymd from credit_stat_detail where type = 2 and ymd = ? group by name,ymd";
	
	/**
	 * �����Ʒ��Ϣ
	 */
	public static String insert_PromotionSoft = "insert into promotion_union_software(softName,pcNum,useNum,Time,credit)  values(?,?,?,?,?)";
	
	/**
	 * ǰ30������:���뷨��װlog����
	 */
	public static String return_getGswbLog = "select distinct SoftID,SoftVer,uid as pc_id from install? where QID <> '7654'";
	
	/**
	 * ǰ30������:���뷨����log����
	 */
	public static String return_getGswbOnline = "select distinct SoftID,SoftVer,uid as pc_id from online? where QID <> '7654'";
	
	/**
	 * װ������15������ذ�װ��
	 */
	public static String return_getUnionPromotionInstall = "select id, uid, software_id, software_version, pc_id, dateline from promotion_install  WHERE  dateline between DATE_SUB(?, INTERVAL 14 DAY) and ?";
	
	/**
	 * װ������: ��Ч��װ�û���: 
	 * 	1. �¶�Ψһ������ - ���ɰ�װ���� ; 
	 * 	2. ���ɰ�װ��: û��ȥ�أ��������п��� ;  
	 * 	3. �¶�Ψһ���������Ϊ0 ����Ч�û�����0
	 */
	public static String return_getInstallOkGswb = "select distinct substring_index(AdvertIdentifier,'_',-1) as uid,MonthAddInstallNum as uidNum from install_collect_gswb where AdvertIdentifier like '%7654%' and DateLine = ?";
	
	/**
	 * �������뷨���ɰ�װ��
	 */
	public static String return_getInstallWbGswb(){
		StringBuilder strBud = new StringBuilder();
		strBud.append("select ");
		strBud.append("	substring_index(qid,'_',-1) as uid,count(distinct uid) as uidNum ");
		strBud.append("	from (select distinct uid,left(qid,length(qid)-6) as qid from install? where qid like '%7654%' and IsWB = 1) a group by substring_index(qid,'_',-1) ");
		return strBud.toString(); 
	}
	
	/**
	 * ��ȡװ�������û�ID
	 * @return
	 */
	public static String return_getUserId=" select id from user ";
}
