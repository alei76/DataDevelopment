package com.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.action.gswb_qd.Gswb_collect_qd_action;
import com.dao.union.PromotionInstallDao;
import com.db.info.Dictionary_DB;
/**
 * 光速输入法渠道统计入口
 * @author ZhuYa
 *
 */
public class Copy_OfGswbQdTest {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
//		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
//		Gswb_collect_qd_action action = new Gswb_collect_qd_action();
//		Integer tab_date = Dictionary_DB.getBeforeDate(0, null);
//		action.insertGswb_Collect_Qd_02(Dictionary_DB.ip_01, Dictionary_DB.user_01, Dictionary_DB.password_01, Dictionary_DB.db_statistics, tab_date);
		Integer tab_date = Dictionary_DB.getBeforeDate(0, null);
		System.out.println(tab_date);
	}

}	
