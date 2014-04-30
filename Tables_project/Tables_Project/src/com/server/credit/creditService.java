package com.server.credit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.dao.credit.DAOBase;
import com.entity.haha.NewUserStatBean;
import com.sunxd.DBConInfo;
import com.sunxd.common.entity.BeanFactory;
import com.sunxd.common.entity.IMysqlBean;


/**从各种零碎的表里获取结果*/

public class creditService 
{
	private DAOBase dao=null; //DAO类
	
	public creditService(DBConInfo dbcGet,DBConInfo dbcSet) 
	{
		super();
		this.dao = new DAOBase(dbcGet,dbcSet); //新建DAO类，存储获取数据的dbc和插入数据的dbc
	}
	
	public void excecute(String[] strDateArray) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException // 每次调用execute方法Dao不需要新建类
	{
		IMysqlBean beanin1=BeanFactory.createBean("credit.CreditAllUserStatBean");
		System.out.println("bean Factory created credit.CreditAllUserStatBean");
		
		List<IMysqlBean> bean1=dao.assebmleBeanList4Date(strDateArray[0],beanin1);
		dao.appendEntryList2Tb(bean1);
		//IMysqlBean bean1=dao.assebmleBean4Date(strDateArray[0],beanin1); //组装好今天的bean，未知信息设置为默认值
		
	}

}
