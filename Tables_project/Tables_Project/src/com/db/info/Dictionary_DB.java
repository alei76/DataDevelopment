package com.db.info;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.DatabaseMetaData;

/**
 * 数据库字典
 * @author ZhuYa
 *
 */
public class Dictionary_DB {
	public static int today = 20140213;  //表名时间 -----------待用类
	public static int today_substr = 140213; //截取的时间 
	
	/************************ 数据库  **********************/
	public static String db_gswb_log = "gswb_log"; 			//光速输入法
	public static String db_haharili_log = "haharili_log";  //哈哈日历
	public static String db_ysie_log = "ysie_log"; 			//音速浏览器
	public static String db_ysqd_log = "ysqd_log"; 			//音速启动
	public static String db_statistics = "statistics"; 		//统计库
	public static String db_zj7654_log = "zj7654_log"; 		//装机联盟清单库
	public static String db_mini_log = "mini_log"; 			//迷你播报清单
	
	public static String db_union = "union"; 				//装机联盟WEB库
	
	
	public static String ip_01 = "112.124.97.43:3306";	//光速输入法正式库
//	public static String ip_01 = "localhost:3306";
	public static String user_01 = "developer";				//USER
	public static String password_01 = "DN3v74JB";			//PWD
	
//	public static String ip_02 = "192.168.193.127:3306"; 	//测试库IP地址
	public static String ip_02 = "211.149.193.127:3306"; 	//正式库IP地址   : 外网IP - 211.149.193.127 
	public static String user_02 = "developer"; 			//USER
	public static String password_02 = "DN3v74JB";			//PWD
	
	
	/************************ 光速输入法库 表名  **********************/
	public static String tblname_gswb_log_active = "active";   		 	//光速输入法库  ：活跃表
	public static String tblname_gswb_log_install = "install"; 		 	//光速输入法库  ：安装表
	public static String tblname_gswb_log_online = "online";   		 	//光速输入法库  ：在线表  
	public static String tblname_gswb_log_uninstall = "uninstall";   	//光速输入法库  ：卸载表
	
	/************************** 哈哈日历 表名  ************************/
	public static String tblname_haharili_log_install = "install";    	//哈哈日历库  ：安装表
	public static String tblname_haharili_log_online = "online";     	//哈哈日历库  ：在线表
	public static String tblname_haharili_log_uninstall = "uninstall";  //哈哈日历库  ：卸载表
	
	/************************* 音速浏览器 表名  ***********************/
	public static String tblname_ysie_log_active = "active";    		//音速浏览器库  ：活跃表
	public static String tblname_ysie_log_install = "install";    		//音速浏览器库  ：安装表
	public static String tblname_ysie_log_online = "online";    		//音速浏览器库  ：在线表
	public static String tblname_ysie_log_update = "update";    		//音速浏览器库  ：升级表
	public static String tblname_ysie_log_uninstall = "uninstall";    	//音速浏览器库  ：卸载表
	
	/************************* 音速启动 表名  ***********************/
	public static String tblname_ysqd_log_active = "active";    		//音速启动库  ：活跃表
	public static String tblname_ysqd_log_check = "check";    			//音速启动库  ：检测环境表
	public static String tblname_ysqd_log_install = "install";    		//音速启动库  ：安装表
	public static String tblname_ysqd_log_online = "online";    		//音速启动库  ：在线表
	public static String tblname_ysqd_log_uninstall = "uninstall";    	//音速启动库  ：卸载表
	
	/**
	 * 大渠道别名
	 */
	public static String[] strs = {"gw","ald","nb","wb","other"};
	
	/**
	 * 前一天截取过的时间
	 * 转换成Integer类型
	 * @param inputType : 0是自动当前日期,1是手动输入日期
	 * @param inputDate : 手动输入的时间
	 * @return
	 */
	public static Integer getBeforeDate(int inputType,Integer inputDate){
		Date dNow = new Date();   				 	 //当前时间
		Date dBefore = new Date();
		Integer bDay = 0;							 //返回表时间
		Calendar calendar = Calendar.getInstance();  //得到日历
		calendar.setTime(dNow);						 //把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -1);     //设置为前一天
		dBefore = calendar.getTime();   			 //得到前一天的时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd"); //设置时间格式
		String beforeDate = sdf.format(dBefore);    	   	   //格式化前一天
		if(inputType == 0){
			bDay = Integer.parseInt(beforeDate);
		}else{
			bDay = inputDate;
		}
		return bDay;
	}
	
	/**
	 * 输入时间
	 * 前几天截取过的时间
	 * 转换成Integer类型
	 * @param inputType : 0是自动当前日期,1是手动输入日期
	 * @param inputDate : 手动输入的时间
	 * @param beDate : 前几天
	 * @return
	 * @throws ParseException 
	 */
	public static Integer getBeforeDate_money(int inputType,Integer inputDate,int beDate){
		Date dNow = new Date();   				 	 	//当前时间
		Date dBefore = new Date();						
		Integer bDay = 0;							 	//返回表时间
		Calendar calendar = Calendar.getInstance();  	//得到日历
		calendar.setTime(dNow);						 	//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, beDate);    //设置为前一天
		dBefore = calendar.getTime();   			 	//得到前一天的时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd"); //设置时间格式
		String beforeDate = sdf.format(dBefore);    	   	   //格式化前一天
		//判断是否手动输入时间
		if(inputType == 0){
			bDay = Integer.parseInt(beforeDate);
		}else{
			try {
				Date dDate = sdf.parse(inputDate.toString());		//输入时间
				Date dBefore_1 = new Date();
				Calendar calendar_1 = Calendar.getInstance();  		//得到日历
				calendar_1.setTime(dDate);						 	//把输入时间赋给日历
				calendar_1.add(Calendar.DAY_OF_MONTH, beDate);    	//设置为前一天
				dBefore_1 = calendar_1.getTime();   			 	//得到前一天的时间
				SimpleDateFormat sdf_1 =new SimpleDateFormat("yyyyMMdd"); 	  //设置时间格式
				String beforeDate_1 = sdf_1.format(dBefore_1);    	   	   	  //格式化前一天
				bDay = Integer.parseInt(beforeDate_1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return bDay;
	}
	
	/**
	 * 获取前1天,7天,30天数据
	 * 转换成Integer类型
	 * @return
	 */
	public static Integer getTodayDate(Integer dayType){
		Date dNow = new Date();   				 	 //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance();  //得到日历
		calendar.setTime(dNow);						 //把当前时间赋给日历
		if(dayType == 1){
			calendar.add(Calendar.DAY_OF_MONTH, -2);     //设置为前一天
		}else if(dayType == 7){
			calendar.add(Calendar.DAY_OF_MONTH, -7);     //设置为前七天
		}else if(dayType == 30){
			calendar.add(Calendar.DAY_OF_MONTH, -30);    //设置为前三十天
		}
		dBefore = calendar.getTime();   			 //得到前一天的时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd"); //设置时间格式
		String beforeDate = sdf.format(dBefore);    	   	   //格式化前一天
		Integer tday = Integer.parseInt(beforeDate.substring(beforeDate.length()-8));
		return tday;
	}
	
	/**
	 * 获取前1天,7天,30天数据
	 * 转换成Integer类型
	 * @return
	 * @throws ParseException 
	 */
	public static Integer getTodayDate(String inputDate,Integer dayType){
		//字符串格式化时间
		DateFormat df = new SimpleDateFormat("yyyyMMdd");		
		Date date = null;
		Integer tday = 0;
		try 
		{
			date = df.parse(inputDate.toString());
			Calendar calendar = Calendar.getInstance();  //得到日历
			calendar.setTime(date);						 //把输入时间赋给日历
			if(dayType == 1){
				calendar.add(Calendar.DAY_OF_MONTH, -1);     //设置为前一天
			}else if(dayType == 7){
				calendar.add(Calendar.DAY_OF_MONTH, -6);     //设置为前七天
			}else if(dayType == 30){
				calendar.add(Calendar.DAY_OF_MONTH, -29);    //设置为前三十天
			}
			date = calendar.getTime();   					 //根据输入时间前一天的时间
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd"); //设置时间格式
			String beforeDate = sdf.format(date);    	   	   //格式化前一天
			tday = Integer.parseInt(beforeDate.substring(beforeDate.length()-8));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tday;
	}
	
	/**
	 * 渠道汇总统计字典
	 * @return
	 */
	public static Map<String,String> getQdName(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("gw", "gw");
		map.put("ald", "ald");
		map.put("nb", "nb");
		map.put("wb", "wb");
		map.put("other", "other");
		return map;
	} 
	
	
	/**
	 * 获取光速输入法库下的所有表名
	 * @param db_ip : 服务器IP
	 * @param user : 用户
	 * @param pwd : 密码
	 * @param db_name : 数据库名称
	 * @param tabTitle : "install"
	 * @return
	 */
	public List<Integer> getGswb_Log_Install_tab(String db_ip,String user,String pwd,String db_name,String tabTitle){
		Connection con = (Connection) DataBase_Info.getConn(db_ip,user,pwd,db_name);
		List<Integer> lists = new ArrayList<Integer>();
		 try {
			   DatabaseMetaData meta = (DatabaseMetaData) con.getMetaData();
			   ResultSet rs = (ResultSet) meta.getTables(null, null, null,
			     new String[] { "TABLE" });
			   while (rs.next()) {
				   String name = rs.getString(3);
				   String tab_name = rs.getString(3).substring(0,name.length()-8);
				   Integer tab_date = Integer.parseInt(rs.getString(3).substring(name.length()-8,name.length()));
				   if(tab_name.equals(tabTitle)){
					   lists.add(tab_date); 
				   }
			   }
			   Collections.reverse(lists);
		   }catch (Exception e) {
			   e.printStackTrace();
		   }finally{
				try {
				    if(DataBase_Info.rs != null){
				    	DataBase_Info.rs.close();   
				    } 
				    if(con != null){
				    	con.close();   
				    } 
				 } catch (Exception ex) {   
					 	ex.printStackTrace();   
				 }
			}
		return lists;
	}
}
