package com.action.haha;

import java.util.List;

import com.db.info.Dictionary_DB;
import com.entity.statistics.Gswb_collect_qd;
import com.server.gswb_log.qd.GswbCollectQdServer;
import com.server.haha.Service;
import com.sunxd.DBConInfo;

/**先让service组装好，然后将组装好的list 插入到数据库*/
public class ActionInsertNewTable 

{
	
	public void action(DBConInfo dbconGet,DBConInfo dbconSet)
	{
		
		String tab_date_01 = Dictionary_DB.getBeforeDate(0, null).toString();//获得前一天的数据	
		String tab_date_02 = Dictionary_DB.getTodayDate(1).toString();  //次日   -2
		String tab_date_07 = Dictionary_DB.getTodayDate(7).toString();  //第7日 -7
		String tab_date_30 = Dictionary_DB.getTodayDate(30).toString(); //第30日 -30
		
		
		String[]  strDateArray={tab_date_01,tab_date_02,tab_date_07,tab_date_30};
		
		Service server=new Service(dbconGet, dbconSet);
		
		server.excecute(strDateArray); // 执行必要的操作
		
		
	}//void
}//class