package com.sunxd.junitTest;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.server.credit.creditService;
import com.sunxd.DBConInfo;

public class testCreditService {

	static DBConInfo dbconGet=new DBConInfo();
	static DBConInfo dbconSet=new DBConInfo();
	static{
	dbconGet.setDb_ip("192.168.1.45:3306");
	dbconGet.setDb_name("money");
	dbconGet.setUser("developer");
	dbconGet.setPwd("DN3v74JB");
	/////////////////////////////////
	/*DBConInfo dbconSet=new DBConInfo();
	dbconSet.setDb_ip("112.124.97.43:3306");
	dbconSet.setDb_name("statistics");
	dbconSet.setUser("zhuyahuangweinia");
	dbconSet.setPwd("vcr4mmdrG6SaCNHV");*/
	dbconSet.setDb_ip("127.0.0.1:3306");
	dbconSet.setDb_name("statistics");
	dbconSet.setUser("root");
	dbconSet.setPwd("gaoxin");
	}
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		creditService cs=new creditService(dbconGet, dbconSet);
		String[]  strDateArray={"20140107","20140106","20140105","20140104"};
		cs.excecute(strDateArray);
		fail("Not yet implemented");
	}

}
