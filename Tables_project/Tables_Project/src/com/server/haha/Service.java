package com.server.haha;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.sunxd.DBConInfo;
import com.dao.hahaCal.Factory;
import com.dao.hahaCal.HahaCollectDAOBase;
import com.entity.haha.AllUserStatBean;
import com.entity.haha.BeanIter;
import com.entity.haha.EditionStatBean;
import com.entity.haha.ExperienceBean;
import com.entity.haha.IMysqlBean;
import com.entity.haha.NewUserStatBean;
import com.entity.haha.SkinStatBean;
import com.entity.union.Promotion_All;
import com.job.haha.EnumReqName;

/**从各种零碎的表里获取结果*/

public class Service 
{
	private HahaCollectDAOBase dao=null; //DAO类
	
	public Service(DBConInfo dbcGet,DBConInfo dbcSet) 
	{
		super();
		this.dao = new HahaCollectDAOBase(dbcGet,dbcSet); //新建DAO类，存储获取数据的dbc和插入数据的dbc
	}
	
	
	public void excecute(String[] strDateArray) // 每次调用execute方法Dao不需要新建类
	{
		IMysqlBean beanin1=new NewUserStatBean();
		IMysqlBean beanin2=new AllUserStatBean();
		
		NewUserStatBean bean1=(NewUserStatBean) dao.assebmleBean4Date(strDateArray[0],beanin1); //组装好今天的bean，未知信息设置为默认值
		AllUserStatBean bean2=(AllUserStatBean) dao.assebmleBean4Date(strDateArray[0],beanin2);
		
		String inSql3=new EditionStatBean().sql;
		String inSql4=new SkinStatBean().sql;
		
		String inSql5=new ExperienceBean().sql;
		
		List<EditionStatBean> beanlist3=dao.batchFetchBeanList4Date(inSql3.replaceAll("\\[\\?\\]", strDateArray[0]), strDateArray[0], EditionStatBean.class,new Factory<EditionStatBean>() { @Override public EditionStatBean create() { return new EditionStatBean(); }});
		
		List<SkinStatBean> beanlist4=dao.batchFetchBeanList4Date(inSql4.replaceAll("\\[\\?\\]", strDateArray[0]), strDateArray[0], SkinStatBean.class, new Factory<SkinStatBean>() { @Override public SkinStatBean create() { return new SkinStatBean(); }});
		
		Map<Integer,String> map = new HashMap<Integer,String>();
		
		
		try {
			fillMap(map);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<ExperienceBean> beanlist5=dao.batch2FetchBeanList4Date(inSql5, strDateArray[0],map , new Factory<ExperienceBean>() { @Override public ExperienceBean create() { return new ExperienceBean(); }},"setTime");
		
		dao.appendEntry2Tb(bean1);//将获取的信息append到数据仓库中，未知信息如次日活跃率被当做默认值先插入，后来再更新
		dao.appendEntry2Tb(bean2);
		dao.appendEntryList2Tb(beanlist3);
		dao.appendEntryList2Tb(beanlist4);
		dao.appendEntryList2Tb(beanlist5);
		
		dao.updateTable4Date(strDateArray);//更新相对于今天date的若干天前的active统计
		//在update操作里需要查询历史的活跃数，才能算出活跃率
		//over!
	}


	private Map<Integer,Map.Entry<Method, Method>> fillMap(Map<Integer, String> map) throws NoSuchMethodException, SecurityException {
		map.put(1, "Ref");
		map.put(2, "Weather");
		map.put(3,"Year_choose");
		map.put(4,"Month_choose");
		map.put(5,"Rili");
		map.put(6,"Notepad");
		map.put(7,"Trans");
		map.put(8,"Today");
		map.put(9,"Close_open");
		map.put(10,"Synch");
		map.put(11,"Rili");
		// TODO Auto-generated method stub
	/*	1 刷新按钮,ref_click,ref_num
		2 天气图标,weather_click,weather_num
		3 年份选择下拉框,year_choose_click,year_choose_num
		4 月份选择下拉框,month_choose_click,month_choose_num
		5 日历界面点击,rili_click, rili_num
		6 记事本使用, notepad_click,notepad_num   
		7 透明度使用, trans_click,trans_num
		8 回到“今”日图标,today_click,today_num
		9 关闭开机自动运行,close_open_click,close_open_num
		10 时间同步,synch_click,synch_num
		11. 7654
		*/
		//ExperienceBean.class.getDeclaredMethod("get");
		//Map<Integer,Map.Entry<Method, Method>> am=new HashMap<Integer,Map.Entry<Method, Method>>();
		
		return null;
	}
	
	
//	public List<IMysqlBean> CreatBeanList(String date) //根据日期来生成需要的bean的list
//	{
//		
//		List<IMysqlBean> beanlist=new ArrayList<IMysqlBean>();
//		IMysqlBean bean=dao.assebmleBean4Date(date);
//		beanlist.add(bean);
//		return beanlist;
//	}
	
}
