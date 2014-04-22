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

/**�Ӹ�������ı����ȡ���*/

public class Service 
{
	private HahaCollectDAOBase dao=null; //DAO��
	
	public Service(DBConInfo dbcGet,DBConInfo dbcSet) 
	{
		super();
		this.dao = new HahaCollectDAOBase(dbcGet,dbcSet); //�½�DAO�࣬�洢��ȡ���ݵ�dbc�Ͳ������ݵ�dbc
	}
	
	
	public void excecute(String[] strDateArray) // ÿ�ε���execute����Dao����Ҫ�½���
	{
		IMysqlBean beanin1=new NewUserStatBean();
		IMysqlBean beanin2=new AllUserStatBean();
		
		NewUserStatBean bean1=(NewUserStatBean) dao.assebmleBean4Date(strDateArray[0],beanin1); //��װ�ý����bean��δ֪��Ϣ����ΪĬ��ֵ
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
		
		dao.appendEntry2Tb(bean1);//����ȡ����Ϣappend�����ݲֿ��У�δ֪��Ϣ����ջ�Ծ�ʱ�����Ĭ��ֵ�Ȳ��룬�����ٸ���
		dao.appendEntry2Tb(bean2);
		dao.appendEntryList2Tb(beanlist3);
		dao.appendEntryList2Tb(beanlist4);
		dao.appendEntryList2Tb(beanlist5);
		
		dao.updateTable4Date(strDateArray);//��������ڽ���date��������ǰ��activeͳ��
		//��update��������Ҫ��ѯ��ʷ�Ļ�Ծ�������������Ծ��
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
	/*	1 ˢ�°�ť,ref_click,ref_num
		2 ����ͼ��,weather_click,weather_num
		3 ���ѡ��������,year_choose_click,year_choose_num
		4 �·�ѡ��������,month_choose_click,month_choose_num
		5 ����������,rili_click, rili_num
		6 ���±�ʹ��, notepad_click,notepad_num   
		7 ͸����ʹ��, trans_click,trans_num
		8 �ص�������ͼ��,today_click,today_num
		9 �رտ����Զ�����,close_open_click,close_open_num
		10 ʱ��ͬ��,synch_click,synch_num
		11. 7654
		*/
		//ExperienceBean.class.getDeclaredMethod("get");
		//Map<Integer,Map.Entry<Method, Method>> am=new HashMap<Integer,Map.Entry<Method, Method>>();
		
		return null;
	}
	
	
//	public List<IMysqlBean> CreatBeanList(String date) //����������������Ҫ��bean��list
//	{
//		
//		List<IMysqlBean> beanlist=new ArrayList<IMysqlBean>();
//		IMysqlBean bean=dao.assebmleBean4Date(date);
//		beanlist.add(bean);
//		return beanlist;
//	}
	
}
