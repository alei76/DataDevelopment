package com.sunxd.daoTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dao.hahaCal.EnumBeanFieldType;
import com.dao.hahaCal.HahaCollectDAOBase;
import com.db.info.Dictionary_DB;
import com.server.haha.Service;
import com.sunxd.DBConInfo;

public class TestDAOMethod {

	
	HahaCollectDAOBase h=null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		
		
		
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		
		DBConInfo dbconGet=new DBConInfo();
		dbconGet.setDb_ip("127.0.0.1:3306");
		dbconGet.setDb_name("haharili_log");
		dbconGet.setUser("root");
		dbconGet.setPwd("gaoxin");
		/////////////////////////////////
		/*DBConInfo dbconSet=new DBConInfo();
		dbconSet.setDb_ip("112.124.97.43:3306");
		dbconSet.setDb_name("statistics");
		dbconSet.setUser("zhuyahuangweinia");
		dbconSet.setPwd("vcr4mmdrG6SaCNHV");*/
		
		DBConInfo dbconSet=new DBConInfo();
		dbconSet.setDb_ip("127.0.0.1:3306");
		dbconSet.setDb_name("statistics");
		dbconSet.setUser("root");
		dbconSet.setPwd("gaoxin");
		
		
	    h=new HahaCollectDAOBase(dbconGet, dbconSet);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		//h.inner.getEnclosingClass().new();
		List<Object> l=h.fetchBeanFieldList4Date("select count(distinct a.id),a.uid from install20140104 as a group by a.SoftVer",EnumBeanFieldType.INT,"haha");
		assert(l!=null);
		assert(l.get(0).equals(92));
		//fail("Not yet implemented");
	}

}
