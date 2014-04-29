package com.sunxd.common.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.CallableStatement;


public class DataBase_Info {
	public static CallableStatement cs = null;
	public static ResultSet rs = null;
	public static Connection conn = null;
	
	/**
	 * 返回数据库连接
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @return
	 */
	public static Connection getConn(String db_ip,String useName,String pwdName,String db_name){
		try {
			String url = "jdbc:mysql://"+db_ip+"/"+db_name+"?useUnicode=true&amp;characterEncoding=utf-8";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, useName, pwdName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
			    if(DataBase_Info.rs != null){   
			    	DataBase_Info.rs.close();   
			    }
			    if(conn != null){   
			     conn.close();   
			    }
			    //防止连接超时 ，再次请求连接
			    String url = "jdbc:mysql://"+db_ip+"/"+db_name+"?useUnicode=true&amp;characterEncoding=utf-8";
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, useName, pwdName);
		   } catch (Exception ex) {
			   ex.printStackTrace();   
		   }
		}
		return conn;
	}
	
}
