package com.sunxd;

public class DBConInfo {
	
	private String Db_ip=new String();
	private String Db_name=new String();
	private String User=new String();
	private String Pwd=new String();

	public String getDb_ip() {
		return Db_ip;
	}

	public String getDb_name() {
		return Db_name;
	}

	public void setDb_ip(String db_ip) {
		this.Db_ip = db_ip;
	}

	public void setDb_name(String db_name) {
		this.Db_name = db_name;
	}

	public void setUser(String user) {
		this.User = user;
	}

	public void setPwd(String pwd) {
		this.Pwd = pwd;
	}

	public String getUser() {
		return User;
	}

	public String getPwd() {
		return Pwd;
	}

	

}
