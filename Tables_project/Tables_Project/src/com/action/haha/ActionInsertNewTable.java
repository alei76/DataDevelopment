package com.action.haha;

import java.util.List;

import com.db.info.Dictionary_DB;
import com.entity.statistics.Gswb_collect_qd;
import com.server.gswb_log.qd.GswbCollectQdServer;
import com.server.haha.Service;
import com.sunxd.DBConInfo;

/**����service��װ�ã�Ȼ����װ�õ�list ���뵽���ݿ�*/
public class ActionInsertNewTable 

{
	
	public void action(DBConInfo dbconGet,DBConInfo dbconSet)
	{
		
		String tab_date_01 = Dictionary_DB.getBeforeDate(0, null).toString();//���ǰһ�������	
		String tab_date_02 = Dictionary_DB.getTodayDate(1).toString();  //����   -2
		String tab_date_07 = Dictionary_DB.getTodayDate(7).toString();  //��7�� -7
		String tab_date_30 = Dictionary_DB.getTodayDate(30).toString(); //��30�� -30
		
		
		String[]  strDateArray={tab_date_01,tab_date_02,tab_date_07,tab_date_30};
		
		Service server=new Service(dbconGet, dbconSet);
		
		server.excecute(strDateArray); // ִ�б�Ҫ�Ĳ���
		
		
	}//void
}//class