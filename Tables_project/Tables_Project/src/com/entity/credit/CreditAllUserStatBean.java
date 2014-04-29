package com.entity.credit;


import com.sunxd.common.entity.BeanFactory;
import com.sunxd.common.entity.IMysqlBean;


public class CreditAllUserStatBean implements IMysqlBean
{
    String s="hello world";
	private static class Factory extends BeanFactory 
	{
	    protected IMysqlBean create() { 
	      return new CreditAllUserStatBean(); 
	    }
	}
	
	  static {
	    BeanFactory.addFactory(
	      "credit.CreditAllUserStatBean", new Factory());
	  }

	@Override
	public String getSQL_insert() {
		// TODO Auto-generated method stub
		return null;
	}
}
