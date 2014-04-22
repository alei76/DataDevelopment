package com.sunxd;

import java.util.ArrayList;
import java.util.List;

import com.db.info.Dictionary_DB;
import com.entity.haha.IMysqlBean;
import com.entity.haha.SkinStatBean;

public class DateTest {
	
		public static void main(String[] args) {
			String tab_date_01 = Dictionary_DB.getTodayDate(1).toString();  //次日   -2
			String tab_date_07 = Dictionary_DB.getTodayDate(7).toString();  //第7日 -7
			String tab_date_30 = Dictionary_DB.getTodayDate(30).toString(); //第30日 -30
			
			Object rst=new Object();
			
			rst=(Integer)3;
			
			rst=(Integer)rst+9;
			
			Object rstStr=new Object();
			
			rstStr=(String)"hello world";
			System.out.println(rst);
			System.out.println(rstStr);
			
			String sql="select 20140104 as Time,\"\" as SkinName,a.skinid as SkinID,a.selectnum as clickCount  from user_skin_select20140104 as a";
			System.out.println(sql);
			System.out.println(tab_date_01);
			System.out.println(tab_date_07);
			System.out.println(tab_date_30);
			
			String hh="getref_click";
			System.out.println(hh.charAt(0));
			System.out.println(hh.substring(0,1).toUpperCase());
			System.out.println(hh.replaceFirst(hh.substring(0,1), hh.substring(0,1).toUpperCase()));
			
			Integer tab_date1 = Dictionary_DB.getBeforeDate(0, null);
			String tab_name = tab_date1.toString();// 获得日期	
			System.out.println(tab_name);
			
			IMysqlBean bean=new SkinStatBean();
			
			List<? extends IMysqlBean> ll=new ArrayList<SkinStatBean>();
			
			System.out.println(ll.getClass().getCanonicalName());
			
			System.out.println(bean.getClass().getSimpleName());
			try {
				IMysqlBean temp=bean.getClass().newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	

}
