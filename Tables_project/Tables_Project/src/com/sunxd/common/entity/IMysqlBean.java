package com.sunxd.common.entity;


public interface IMysqlBean {
	
	 Integer fieldnum=0;
	 public  class InnerIter implements BeanIter 
	 {
		 // inner class
		  private int i = 0;
		  public boolean end() 
		  {
			  return i == fieldnum;
		  }
		  
		  public Object current() 
		  {
			  return i; // 直接引用enclose类的field
		  }
		  
		  public void next() 
		  {
			  if (i < fieldnum)
				  i++;
		  }
	 }
	String getSQL_insert();

}
