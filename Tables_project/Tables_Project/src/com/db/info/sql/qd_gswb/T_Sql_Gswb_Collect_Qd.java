package com.db.info.sql.qd_gswb;

/**
 * 光速输入法
 * 渠道汇总统计 : SQL
 * @author ZhuYa
 *
 */
public class T_Sql_Gswb_Collect_Qd {
	/**
	 * 返回光速输入法库  ：渠道汇总统计
	 * @param tab_name : 表名
	 * @param sql
	 * @return
	 */
	public static String return_getGswbAllQd(String tab_name,String qd_name,String qdType){
		Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-6));
		String str = " ";
		String strOther = "";
		if(qd_name.equals("gw")){
			str = " (AdvertIdentifier regexp '^gf[0-9][0-9][0-9]$') and ";
		}else if(qd_name.equals("ald")){
			str = " (AdvertIdentifier regexp '^ald[0-9][0-9][0-9]$') and ";
		}else if(qd_name.equals("wb")){
			str = " (AdvertIdentifier regexp '^xt[0-9][0-9][0-9]_[0-9][0-9][0-9][0-9]$' or AdvertIdentifier regexp '^(xz|kb|xt)[0-9][0-9][0-9]$') and ";
		}else if(qd_name.equals("nb")){
			str = " (AdvertIdentifier regexp '^nb[0-9][0-9][0-9]$') and ";
		}else if(qd_name.equals("other")){
			str = " (AdvertIdentifier not regexp '^(gf|ald|xz|kb|nb|xt)[0-9][0-9][0-9]$') and ";
			strOther = " where AdvertIdentifier not regexp '^xt[0-9][0-9][0-9]_[0-9][0-9][0-9][0-9]$'";
			qdType = qd_name;
		}
		
		String sql = "select                                                                                                                                                                                                        "+
		"	'"+qdType+"' as qdType	,                                                                                                                                                                                          "+
		"	max(case when a.qd_name = 'serviceStartNum' then a.num else 0 end) as serviceStartNum	,                                                                                                                      "+
		"	max(case when a.qd_name = 'activeNum' then a.num else 0 end) as activeNum ,                                                                                                                                  "+
		"	max(case when a.qd_name = 'onlyInstallNum' then a.num else 0 end) as onlyInstallNum ,                                                                                                                        "+
		"	max(case when a.qd_name = 'monthAddInstallNum' then a.num else 0 end) as monthAddInstallNum ,                                                                                                                "+
		"	max(case when a.qd_name = 'inputLV1Num' then a.num else 0 end) as inputLV1Num ,                                                                                                                              "+
		"	max(case when a.qd_name = 'inputLV2Num' then a.num else 0 end) as inputLV2Num ,                                                                                                                              "+
		"	max(case when a.qd_name = 'inputLV3Num' then a.num else 0 end) as inputLV3Num ,                                                                                                                              "+
		"	max(case when a.qd_name = 'uninstNum' then a.num else 0 end) as uninstNum ,                                                                                                                                  "+
		"	max(case when a.qd_name = 'activeNum7Days' then a.num else 0 end) as activeNum7Days ," +
		"   max(case when a.qd_name = 'installNum' then a.num else 0 end) as installNum  "+
		"from                                                                                                                                                                                                          "+
		"(                                                                                                                                                                                                             "+
		"	select 'serviceStartNum' as qd_name , sum(ServiceStartNum) as num,AdvertIdentifier from online_collect_gswb where "+str+" dateline ="+tab_date+" group by dateline                                          "+
		"	union all                                                                                                                                                                                                    "+
		"	select 'activeNum' as qd_name , sum(ActiveNum) as num,AdvertIdentifier from active_collect_gswb where "+str+" dateline ="+tab_date+" group by dateline                                                      "+
		"	union all                                                                                                                                                                                                    "+
		"	select 'onlyInstallNum' as qd_name ,sum(OnlyInstallNum) as num,AdvertIdentifier from install_collect_gswb where "+str+" dateline ="+tab_date+" group by dateline                                            "+
		"	union all                                                                                                                                                                                                    "+
		"	select 'monthAddInstallNum' as qd_name ,sum(MonthAddInstallNum) as num,AdvertIdentifier from install_collect_gswb where "+str+" dateline ="+tab_date+" group by dateline                                    "+
		"	union all                                                                                                                                                                                                    "+
		"	select 'inputLV1Num' as qd_name ,sum(InputLV1Num) as num,AdvertIdentifier from active_collect_gswb where "+str+" dateline ="+tab_date+" group by dateline                                                   "+
		"	union all                                                                                                                                                                                                    "+
		"	select 'inputLV2Num' as qd_name ,sum(InputLV2Num) as num,AdvertIdentifier from active_collect_gswb where "+str+" dateline ="+tab_date+" group by dateline                                                   "+
		"	union all                                                                                                                                                                                                    "+
		"	select 'inputLV3Num' as qd_name ,sum(InputLV3Num) as num,AdvertIdentifier from active_collect_gswb where "+str+" dateline ="+tab_date+" group by dateline                                                   "+
		"	union all                                                                                                                                                                                                    "+
		"	select 'uninstNum' as qd_name ,sum(UninstNum) as num,AdvertIdentifier from uninstall_collect_gswb where "+str+" dateline ="+tab_date+" group by dateline                                                    "+
		"	union all                                                                                                                                                                                                    "+
		"	select 'activeNum7Days' as qd_name ,sum(ActiveNum) as num,AdvertIdentifier from active_collect_gswb where "+str+" TIMESTAMPDIFF(day,AdvertDate,DateLine) = 7 and dateline ="+tab_date+" group by dateline   "+
		"	union all                                                                                                                                                                                                    "+
		"	select 'installNum' as qd_name ,sum(AllInstallNum) as num,AdvertIdentifier from install_collect_gswb where "+str+" dateline ="+tab_date+" group by dateline                                            "+
		")a  "+strOther+"                                                                                                                                                                                                          ";
		return sql;
	}
	
	/**
	 * 返回光速渠道: 安装表集合
	 * @param tab_name
	 * @param qd_name
	 * @param qdType
	 * @return
	 */
	public static String return_getGswbQdInstall_01(String tab_name){
		Integer tab_all = Integer.parseInt(tab_name);
		String sql = "select distinct qid,uid from install"+tab_all+" ";
		return sql;
	}
	
	/**
	 * 返回光速渠道: 1,2,3度活跃表集合
	 * @param tab_name
	 * @param qd_name
	 * @param qdType
	 * @return
	 */
	public static String return_getGswbQdActive_01(String tab_name){
		Integer tab_all = Integer.parseInt(tab_name);
		String sql = "select distinct qid,uid from active"+tab_all+" where InputCount > 0 ";
		return sql;
	}
	
	/**
	 * 光速大渠道: 安装用户在活跃1度,2度,3度的用户数 : 当日活跃数
	 * @param tab_name
	 * @param qd_name
	 * @param qdType
	 * @return
	 */
	public static String return_getGswbQdInsActive(String tab_name,String qd_name,String qdType){
		Integer tab_all = Integer.parseInt(tab_name);
		String str = " ";
		String strOther = "";
		if(qd_name.equals("gw")){
			str = " (AdvertIdentifier regexp '^gf[0-9][0-9][0-9]$') ";
		}else if(qd_name.equals("ald")){
			str = " (AdvertIdentifier regexp '^ald[0-9][0-9][0-9]$') ";
		}else if(qd_name.equals("wb")){
			str = " (AdvertIdentifier regexp '^xt[0-9][0-9][0-9]_[0-9][0-9][0-9][0-9]$' or AdvertIdentifier regexp '^(xz|kb|xt)[0-9][0-9][0-9]$') ";
		}else if(qd_name.equals("nb")){
			str = " (AdvertIdentifier regexp '^nb[0-9][0-9][0-9]$') ";
		}else if(qd_name.equals("other")){
			str = " (AdvertIdentifier not regexp '^(gf|ald|xz|kb|nb|xt)[0-9][0-9][0-9]$') ";
			strOther = " and b.AdvertIdentifier not regexp '^xt[0-9][0-9][0-9]_[0-9][0-9][0-9][0-9]$'";
			qdType = qd_name;
		}
		String sql= "select                                       "+
				"	'"+qdType+"' as qdType	,					  "+
				"	count(distinct b.uid) as num                "+
				"from                                         "+
				"(                                            "+
				"	select AdvertIdentifier,uid from (select DISTINCT (left(qid,length(qid)-6)) as AdvertIdentifier,uid from install"+tab_all+") a where "+str+" "+
				")a left join                                "+
				"(                                            "+
				"	select                                      "+
				"		AdvertIdentifier,uid		                      "+
				"	from                                        "+
				"		(select DISTINCT (left(qid,length(qid)-6)) as AdvertIdentifier,uid,InputCount from active"+tab_all+") a                            "+
				"	where                                       "+
				"		InputCount >0 and "+str+"                         "+
				")b on a.AdvertIdentifier = b.AdvertIdentifier and a.uid = b.uid where b.uid is not null "+strOther+"      "+
				"                            ";
		return sql;
	}
	
	/**
	 * 光速二级渠道: 安装用户在活跃1度,2度,3度的用户数 : 当日活跃数
	 * @param tab_name
	 * @param qd_name
	 * @param qdType
	 * @return
	 */
	public static String return_getGswbQdInsActive_02(String tab_name,String qd_name,String qdType){
		Integer tab_all = Integer.parseInt(tab_name);
		String str = " ";
		String strOther = "";
		if(qd_name.equals("gw")){
			str = " (AdvertIdentifier regexp '^gf[0-9][0-9][0-9]$') ";
		}else if(qd_name.equals("ald")){
			str = " (AdvertIdentifier regexp '^ald[0-9][0-9][0-9]$') ";
		}else if(qd_name.equals("wb")){
			str = " (AdvertIdentifier regexp '^xt[0-9][0-9][0-9]_[0-9][0-9][0-9][0-9]$' or AdvertIdentifier regexp '^(xz|kb|xt)[0-9][0-9][0-9]$') ";
		}else if(qd_name.equals("nb")){
			str = " (AdvertIdentifier regexp '^nb[0-9][0-9][0-9]$') ";
		}else if(qd_name.equals("other")){
			str = " (AdvertIdentifier not regexp '^(gf|ald|xz|kb|nb|xt)[0-9][0-9][0-9]$') ";
			strOther = " and b.AdvertIdentifier not regexp '^xt[0-9][0-9][0-9]_[0-9][0-9][0-9][0-9]$'";
			qdType = qd_name;
		}
		String sql= "select                                       "+
				"	'"+qdType+"' as qdType	,					  "+
				"	substring_index(b.AdvertIdentifier,'_',1) as qid ,"+
				"	count(distinct b.uid) as num                "+
				"from                                         "+
				"(                                            "+
				"	select AdvertIdentifier,uid from (select DISTINCT (left(qid,length(qid)-6)) as AdvertIdentifier,uid from install"+tab_all+") a where "+str+" "+
				")a left join                                "+
				"(                                            "+
				"	select                                      "+
				"		AdvertIdentifier,uid		                      "+
				"	from                                        "+
				"		(select DISTINCT (left(qid,length(qid)-6)) as AdvertIdentifier,uid,InputCount from active"+tab_all+") a                            "+
				"	where                                       "+
				"		InputCount >0 and "+str+"                         "+
				")b on a.AdvertIdentifier = b.AdvertIdentifier and a.uid = b.uid where b.uid is not null "+strOther+"      "+
				"group by substring_index(b.AdvertIdentifier,'_',1)                   ";
		return sql;
	}
	
	/**
	 * 返回光速输入法库  ：二级渠道汇总统计
	 * @param tab_name : 表名
	 * @param sql
	 * @return
	 */
	public static String return_getGswbAllQd_02(String tab_name,String qd_name,String qdType){
		Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-6));
		String str = " ";
		String strOther = "";
		if(qd_name.equals("gw")){
			str = " (AdvertIdentifier regexp '^gf[0-9][0-9][0-9]$') and ";
		}else if(qd_name.equals("ald")){
			str = " (AdvertIdentifier regexp '^ald[0-9][0-9][0-9]$') and ";
		}else if(qd_name.equals("wb")){
			str = " (AdvertIdentifier regexp '^xt[0-9][0-9][0-9]_[0-9][0-9][0-9][0-9]$' or AdvertIdentifier regexp '^(xz|kb|xt)[0-9][0-9][0-9]$') and ";
		}else if(qd_name.equals("nb")){
			str = " (AdvertIdentifier regexp '^nb[0-9][0-9][0-9]$') and ";
		}else if(qd_name.equals("other")){
			str = " (AdvertIdentifier not regexp '^(gf|ald|xz|kb|nb|xt)[0-9][0-9][0-9]$') and ";
			strOther = " where qid not regexp '^xt[0-9][0-9][0-9]_[0-9][0-9][0-9][0-9]$'";
			qdType = qd_name;
		}
		String sql = "select                                                                                                                                                                                                        "+
				"	'"+qdType+"' as qdType	,                                                                                                                                                                                          "+
				"	a.qid 	,                                                                                                                                                                                          "+
				"	max(case when a.qd_name = 'serviceStartNum' then a.num else 0 end) as serviceStartNum	,                                                                                                                      "+
				"	max(case when a.qd_name = 'activeNum' then a.num else 0 end) as activeNum ,                                                                                                                                  "+
				"	max(case when a.qd_name = 'onlyInstallNum' then a.num else 0 end) as onlyInstallNum ,                                                                                                                        "+
				"	max(case when a.qd_name = 'monthAddInstallNum' then a.num else 0 end) as monthAddInstallNum ,                                                                                                                "+
				"	max(case when a.qd_name = 'inputLV1Num' then a.num else 0 end) as inputLV1Num ,                                                                                                                              "+
				"	max(case when a.qd_name = 'inputLV2Num' then a.num else 0 end) as inputLV2Num ,                                                                                                                              "+
				"	max(case when a.qd_name = 'inputLV3Num' then a.num else 0 end) as inputLV3Num ,                                                                                                                              "+
				"	max(case when a.qd_name = 'uninstNum' then a.num else 0 end) as uninstNum ,                                                                                                                                  "+
				"	max(case when a.qd_name = 'activeNum7Days' then a.num else 0 end) as activeNum7Days ," +
				"   max(case when a.qd_name = 'installNum' then a.num else 0 end) as installNum  "+
				"from                                                                                                                                                                                                          "+
				"(                                                                                                                                                                                                             "+
				"	select 'serviceStartNum' as qd_name , sum(ServiceStartNum) as num,substring_index(AdvertIdentifier,'_',1) as qid from online_collect_gswb where "+str+"  dateline ="+tab_date+" group by dateline,substring_index(AdvertIdentifier,'_',1)                                          "+
				"	union all                                                                                                                                                                                                    "+
				"	select 'activeNum' as qd_name , sum(ActiveNum) as num,substring_index(AdvertIdentifier,'_',1) as qid from active_collect_gswb where "+str+" dateline ="+tab_date+" group by dateline,substring_index(AdvertIdentifier,'_',1)                                                 "+
				"	union all                                                                                                                                                                                                    "+
				"	select 'onlyInstallNum' as qd_name ,sum(OnlyInstallNum) as num,substring_index(AdvertIdentifier,'_',1) as qid from install_collect_gswb where "+str+" dateline ="+tab_date+" group by dateline,substring_index(AdvertIdentifier,'_',1)                                            "+
				"	union all                                                                                                                                                                                                    "+
				"	select 'monthAddInstallNum' as qd_name ,sum(MonthAddInstallNum) as num,substring_index(AdvertIdentifier,'_',1) as qid from install_collect_gswb where "+str+" dateline ="+tab_date+" group by dateline,substring_index(AdvertIdentifier,'_',1)                                    "+
				"	union all                                                                                                                                                                                                    "+
				"	select 'inputLV1Num' as qd_name ,sum(InputLV1Num) as num,substring_index(AdvertIdentifier,'_',1) as qid from active_collect_gswb where "+str+" dateline ="+tab_date+" group by dateline,substring_index(AdvertIdentifier,'_',1)                                                   "+
				"	union all                                                                                                                                                                                                    "+
				"	select 'inputLV2Num' as qd_name ,sum(InputLV2Num) as num,substring_index(AdvertIdentifier,'_',1) as qid from active_collect_gswb where "+str+" dateline ="+tab_date+" group by dateline,substring_index(AdvertIdentifier,'_',1)                                                  "+
				"	union all                                                                                                                                                                                                    "+
				"	select 'inputLV3Num' as qd_name ,sum(InputLV3Num) as num,substring_index(AdvertIdentifier,'_',1) as qid from active_collect_gswb where "+str+" dateline ="+tab_date+" group by dateline,substring_index(AdvertIdentifier,'_',1)                                                   "+
				"	union all                                                                                                                                                                                                    "+
				"	select 'uninstNum' as qd_name ,sum(UninstNum) as num,substring_index(AdvertIdentifier,'_',1) as qid from uninstall_collect_gswb where "+str+" dateline ="+tab_date+" group by dateline,substring_index(AdvertIdentifier,'_',1)                                                    "+
				"	union all                                                                                                                                                                                                    "+
				"	select 'activeNum7Days' as qd_name ,sum(ActiveNum) as num,substring_index(AdvertIdentifier,'_',1) as qid from active_collect_gswb where "+str+" TIMESTAMPDIFF(day,AdvertDate,DateLine) = 7 and dateline ="+tab_date+" group by dateline,substring_index(AdvertIdentifier,'_',1)   "+
				"	union all                                                                                                                                                                                                    "+
				"	select 'installNum' as qd_name ,sum(AllInstallNum) as num,substring_index(AdvertIdentifier,'_',1) as qid from install_collect_gswb where "+str+" dateline ="+tab_date+" group by dateline,substring_index(AdvertIdentifier,'_',1)                                            "+
				")a "+strOther+" group by a.qid                                                                                                                                                                                                           ";
				return sql;	
	}
	
	/**
	 * 光速输入法: 三级渠道数据查询
	 * @param tab_date
	 * @return
	 */
	public static String return_getPromotionNode3(String tab_name){
		Integer tab_date = Integer.parseInt(tab_name.substring(tab_name.length()-6));
		String sql= "select 'qd' as qdType,a.AdvertIdentifier,a.DateLine,                                                                                                                                                                           "+
				"	max(case when a.qd_name = 'serviceStartNum' then a.num else 0 end) as serviceStartNum	,                                                                                                                                       "+
				"	max(case when a.qd_name = 'activeNum' then a.num else 0 end) as activeNum ,                                                                                                                                                   "+
				"	max(case when a.qd_name = 'onlyInstallNum' then a.num else 0 end) as onlyInstallNum ,                                                                                                                                         "+
				"	max(case when a.qd_name = 'monthAddInstallNum' then a.num else 0 end) as monthAddInstallNum ,                                                                                                                                 "+
				"	max(case when a.qd_name = 'inputLV1Num' then a.num else 0 end) as inputLV1Num ,                                                                                                                                               "+
				"	max(case when a.qd_name = 'inputLV2Num' then a.num else 0 end) as inputLV2Num ,                                                                                                                                               "+
				"	max(case when a.qd_name = 'inputLV3Num' then a.num else 0 end) as inputLV3Num ,                                                                                                                                               "+
				"	max(case when a.qd_name = 'uninstNum' then a.num else 0 end) as uninstNum ,                                                                                                                                                   "+
				"	max(case when a.qd_name = 'activeNum7Days' then a.num else 0 end) as activeNum7Days ,                                                                                                                                         "+
				"	max(case when a.qd_name = 'installNum' then a.num else 0 end) as installNum    from                                                                                                                                           "+ 
				"( select 'serviceStartNum' as qd_name , sum(ServiceStartNum) as num,AdvertIdentifier,DateLine from online_collect_gswb where  DateLine="+tab_date+"  group by AdvertIdentifier,DateLine                                        "+
				"	union all                                                                                                                                                                                                                     "+
				"	select 'activeNum' as qd_name , sum(ActiveNum) as num,AdvertIdentifier,DateLine from active_collect_gswb where  DateLine="+tab_date+" group by AdvertIdentifier,DateLine                                                      "+
				"	union all                                                                                                                                                                                                                     "+
				"	select 'onlyInstallNum' as qd_name ,sum(OnlyInstallNum) as num,AdvertIdentifier,DateLine from install_collect_gswb where  DateLine="+tab_date+"  group by AdvertIdentifier,DateLine                                           "+
				"	union all                                                                                                                                                                                                                     "+
				"	select 'monthAddInstallNum' as qd_name ,sum(MonthAddInstallNum) as num,AdvertIdentifier,DateLine from install_collect_gswb where   DateLine="+tab_date+" group by AdvertIdentifier,DateLine                                   "+
				"	union all                                                                                                                                                                                                                     "+
				"	select 'inputLV1Num' as qd_name ,sum(InputLV1Num) as num,AdvertIdentifier,DateLine from active_collect_gswb where  DateLine="+tab_date+"  group by AdvertIdentifier,DateLine                                                  "+
				"	union all                                                                                                                                                                                                                     "+
				"	select 'inputLV2Num' as qd_name ,sum(InputLV2Num) as num,AdvertIdentifier,DateLine from active_collect_gswb where  DateLine="+tab_date+"  group by AdvertIdentifier,DateLine                                                  "+
				"	union all                                                                                                                                                                                                                     "+
				"	select 'inputLV3Num' as qd_name ,sum(InputLV3Num) as num,AdvertIdentifier,DateLine from active_collect_gswb where  DateLine="+tab_date+"  group by AdvertIdentifier,DateLine                                                  "+
				"	union all                                                                                                                                                                                                                     "+
				"	select 'uninstNum' as qd_name ,sum(UninstNum) as num,AdvertIdentifier,DateLine from uninstall_collect_gswb where  DateLine="+tab_date+"   group by AdvertIdentifier,DateLine                                                  "+
				"	union all                                                                                                                                                                                                                     "+
				"	select 'activeNum7Days' as qd_name ,sum(ActiveNum) as num,AdvertIdentifier,DateLine from active_collect_gswb where  DateLine="+tab_date+" and TIMESTAMPDIFF(day,AdvertDate,DateLine) = 7  group by AdvertIdentifier,DateLine  "+   
				"	union all                                                                                                                                                                                                                     "+
				"	select 'installNum' as qd_name ,sum(AllInstallNum) as num,AdvertIdentifier,DateLine from install_collect_gswb where   DateLine="+tab_date+"  group by AdvertIdentifier,DateLine                                               "+
				") a GROUP BY a.AdvertIdentifier,a.DateLine   ";
		return sql;
	}
	
	/**
	 * 插入光速输入法 渠道统计汇总表
	 */
	public static String insert_gswb_qd = "insert into gswb_collect_qd(QdType,ServiceStartNum,ActiveNum,OnlyInstallNum,MonthAddInstallNum,InputLV1Num,InputLV2Num,InputLV3Num,ActiveNum7Days,UninstNum,DateLine,InstallNum)" +
	" values(?,?,?,?,?,?,?,?,?,?,?,?)";
	
	/**
	 * 插入光速输入法 二级渠道统计汇总表
	 */
	public static String insert_gswb_qd_02 = "insert into gswb_collect_qd_node2(QdType,QID,ServiceStartNum,ActiveNum,OnlyInstallNum,MonthAddInstallNum,InputLV1Num,InputLV2Num,InputLV3Num,ActiveNum7Days,UninstNum,DateLine,InstallNum)" +
			" values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	/**
	 * 插入光速输入法 二级渠道统计汇总表
	 */
	public static String insert_gswb_qd_03 = "insert into gswb_collect_qd_node3(QdType,AdvertIdentifier,ServiceStartNum,ActiveNum,OnlyInstallNum,MonthAddInstallNum,InputLV1Num,InputLV2Num,InputLV3Num,ActiveNum7Days,UninstNum,DateLine,InstallNum)" +
			" values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	/**
	 * 更新大渠道7日存活量
	 * @param tabName
	 * @param tab_beforeDate : 7天前时间
	 * @param num7Day : 第7 日的存活量
	 * @return
	 */
	public static String update_gswb_qd(){
		String sql = "update gswb_collect_qd set ActiveNum7Days = ? where dateline = ? and QdType = ? ";
		return sql;
	}
	
//	public static String update_gswb_qd(String tabName, String tab_beforeDate,Integer num7Day,String qdType){
//		Integer tab_date = Integer.parseInt(tab_beforeDate.substring(tab_beforeDate.length()-6));
//		String sql = "update "+tabName+" set ActiveNum7Days = "+num7Day+" where dateline = "+tab_date+" and QdType = '"+qdType+"' ";
//		return sql;
//	}
	
	
	
	/**
	 * 更新二级渠道7日存活量
	 * @param tabName
	 * @param tab_beforeDate : 7天前时间
	 * @param num7Day : 第7 日的存活量
	 * @return
	 */
	public static String update_gswb_qd02(){
		String sql = "update gswb_collect_qd_node2 set ActiveNum7Days = ? where dateline = ? and QdType = ? and qid= ? ";
		return sql;
	}
//	public static String update_gswb_qd02(String tabName, String tab_beforeDate,Integer num7Day,String qdType,String qid){
//		Integer tab_date = Integer.parseInt(tab_beforeDate.substring(tab_beforeDate.length()-6));
//		String sql = "update "+tabName+" set ActiveNum7Days = "+num7Day+" where dateline = "+tab_date+" and QdType = '"+qdType+"' and qid='"+qid+"' ";
//		return sql;
//	}
	
	/**
	 * 更新三级渠道7日存活量
	 * @param tabName
	 * @param tab_beforeDate : 7天前时间
	 * @param num7Day : 第7 日的存活量
	 * @return
	 */
	public static String update_gswb_qd03(){
		String sql = "update gswb_collect_qd_node3 set ActiveNum7Days = ? where dateline = ? and QdType = ? and AdvertIdentifier = ? ";
		return sql;
	}
//	public static String update_gswb_qd03(String tabName, String tab_beforeDate,Integer num7Day,String qdType,String qid){
//		Integer tab_date = Integer.parseInt(tab_beforeDate.substring(tab_beforeDate.length()-6));
//		String sql = "update "+tabName+" set ActiveNum7Days = "+num7Day+" where dateline = "+tab_date+" and QdType = '"+qdType+"' and AdvertIdentifier = '"+qid+"' ";
//		return sql;
//	}
}
