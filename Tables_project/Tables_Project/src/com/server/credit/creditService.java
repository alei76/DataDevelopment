package com.server.credit;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.dao.credit.DAOBase;
import com.sunxd.DBConInfo;
import com.sunxd.common.entity.BeanFactory;
import com.sunxd.common.entity.IMysqlBean;


/**�Ӹ�������ı����ȡ���*/

public class creditService 
{
	private DAOBase dao=null; //DAO��
	
	public creditService(DBConInfo dbcGet,DBConInfo dbcSet) 
	{
		super();
		this.dao = new DAOBase(dbcGet,dbcSet); //�½�DAO�࣬�洢��ȡ���ݵ�dbc�Ͳ������ݵ�dbc
	}
	
	public void excecute(String[] strDateArray) // ÿ�ε���execute����Dao����Ҫ�½���
	{
		IMysqlBean beanin1=BeanFactory.createBean("credit.CreditAllUserStatBean");
		System.out.println("");
	    
		
		//IMysqlBean bean1=dao.assebmleBean4Date(strDateArray[0],beanin1); //��װ�ý����bean��δ֪��Ϣ����ΪĬ��ֵ
		
	}

}
