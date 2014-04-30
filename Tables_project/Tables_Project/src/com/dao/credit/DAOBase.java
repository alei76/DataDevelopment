package com.dao.credit;

import com.sunxd.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.sunxd.DBConInfo;
import com.sunxd.common.EnumBeanFieldType;
import com.sunxd.common.Factory;
import com.sunxd.common.db.DataBase_Info;
import com.sunxd.common.entity.AnnotationGenSQL4Bean;
import com.sunxd.common.entity.AnnotationSQLCommon4Bean;
import com.sunxd.common.entity.IMysqlBean;


public class DAOBase {
	//DAO层应该不关心业务,只根据业务的enum名和日期提取对应某天的bean，然后append到合成表里

	private DBConInfo dbcGet=null;///////////////////////////
	private DBConInfo dbcSet=null;///////////////////////////

	
	
	public DAOBase(DBConInfo dbcGet, DBConInfo dbcSet)
	{
		this.dbcGet=dbcGet;
		this.dbcSet=dbcSet;
	}
	
	//要注意的是sql里是有问号的,用前需要将这些问号替换掉
	
	/**提取对应某天的bean的list*/
	public Class inner;
	private Object fetchBeanField4Date(String sql,EnumBeanFieldType et,String columnName)
	{
		
		Connection conn = DataBase_Info.getConn(dbcGet.getDb_ip(),dbcGet.getUser(),dbcGet.getPwd(),dbcGet.getDb_name());
		
		Object rst=new Object();// 返回值
		
		try{
			if(!conn.isClosed()){				
				Statement statement = (Statement) conn.createStatement();
				//查询所有活跃表,新用户活跃表
				DataBase_Info.rs = statement.executeQuery(sql);//通过sql的select语句获得结果集合
						
				while(DataBase_Info.rs.next()) 
				{
					
					
					 switch(et) //JAVA的up-cast，之后可以自动downcast
					 {
					 	case INT:
						 {
							 rst=DataBase_Info.rs.getInt(1);///？？？？？？？？？？？？？？						 
							 break;
						 }
					 	case Double:
						 {
						     rst=DataBase_Info.rs.getDouble(1);
							 break;
						 }
					 	case STRING:
					 	 {
							 rst=DataBase_Info.rs.getString(1);
							 break;
						 }
					 	 default:
					 	 {
					 		 throw new Throwable("undefined type when fetchting from mysql");// 什么都不做会有异常
					 	 }					 
					 }
					 				
				
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		try {
			    if(DataBase_Info.rs != null){   
			    	DataBase_Info.rs.close();   
			    } 
			    if(conn != null){   
			     conn.close();   
			    }   
		   } catch (Exception ex) {   
			   ex.printStackTrace();   
		   }   
		}
		return rst; 
		
	}
	
	public List<Object> fetchBeanFieldList4Date(String sql,EnumBeanFieldType et, String fieldName) {
		
     Connection conn = DataBase_Info.getConn(dbcGet.getDb_ip(),dbcGet.getUser(),dbcGet.getPwd(),dbcGet.getDb_name());	
     List<Object> rstList=new ArrayList<Object>();// 返回值
	 Object temp;
		
		try{
			if(!conn.isClosed()){				
				Statement statement = (Statement) conn.createStatement();
				//查询所有活跃表,新用户活跃表
				DataBase_Info.rs = statement.executeQuery(sql);//通过sql的select语句获得结果集合				
				
				while(DataBase_Info.rs.next()) 
				{
					
					
					 switch(et) //JAVA的up-cast，之后可以自动downcast
					 {
					 	case INT:
						 {
							 rstList.add(DataBase_Info.rs.getInt(1));///？？？？？？？？？？？？？？	
							 break;
						 }
					 	case Double:
						 {
						     rstList.add(DataBase_Info.rs.getDouble(1));
							 break;
						 }
					 	case STRING:
					 	 {
							 rstList.add(DataBase_Info.rs.getString(1));
							 break;
						 }
					 	 default:
					 	 {
					 		 throw new Throwable("undefined type when fetchting from mysql");// 什么都不做会有异常
					 	 }					 
					 }
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		try {
			    if(DataBase_Info.rs != null){   
			    	DataBase_Info.rs.close();   
			    } 
			    if(conn != null){   
			     conn.close();   
			    }   
		   } catch (Exception ex) {   
			   ex.printStackTrace();   
		   }   
		}
		return rstList; 
		
	}

	
	
	/*public void updateTable4Date(String[] strDateArray) //用todayActiveCount来 自动更新1日，7日和30日的数据
	{   			
		List<String> sqlList=getUpdateSql(strDateArray); //获得需要更新用的sql的array,大小是天数（1，7，30）*field数
		String []array=new String[sqlList.size()];
		int i=0;
		for(String str:sqlList)
		{
			array[i++]=str;
		}
		updateTbCollumn4Date(array); //将准备好的sql语句组成的SQL的array连接数据库完成更新操作		
	}*/
	/**只针对特定的bean才有update操作，因此这里用特定的bean*/
	 /*private List<String> getUpdateSql(String[]dateArray) {//次日活跃率和活跃数，率需要先抽出历史上的n天前的那个数据
		// TODO Auto-generated method stub
		*//**首先获得通用:今天相对于历史的活跃数*//*
		// 先获得数select count(distinct b.uid)from install[?] as a inner join user_action[?] as b on a.UID=b.uid，然后获得率
		AnnotationSQLCommon4Bean AnnoCommonSQL=(AnnotationSQLCommon4Bean)clazz.getAnnotation(AnnotationSQLCommon4Bean.class);
		if(AnnoCommonSQL==null) return null;
		String sqlFutureActive=AnnoCommonSQL.sql1();
	
		
		List<String>  sqlArray=new ArrayList<String>();
		Map<String,Map.Entry<String, String>> map=new HashMap();

		String histSQLString="select count(distinct a.uid) from install[?] as a";

		Long histBase; //
		Long val;
		
		for(int i=1;i<=dateArray.length-1;i++) // 对于次日，7日和30日要查询的表格是不一样的，算两个数
		{
			
			String sql=replaceSqlVal(sqlFutureActive,dateArray[0],dateArray[i]);///???????????????????????????????????
		
			histBase=(Long) executeSQL(histSQLString.replaceAll("\\[\\?\\]", dateArray[i]),dbcGet);
			val=(Long) executeSQL(sql,dbcGet);//统计出 次日，7日和30日活跃用户数，相对于历史上的新用户 
			
			
			//Math.round((obj.getLoginNum()*1.00/obj.getNext7DayRegInsActive())*100);

			Map.Entry<String, String> pair = new AbstractMap.SimpleEntry(val.toString(), dateArray[i]);
			
	        map.put(AnnoCommonSQL.reqArray[i-1]+AnnoCommonSQL.typeArray[0], pair);//加入count
	        
	        Map.Entry<String, String> pair2 = new AbstractMap.SimpleEntry(Double.toString(Math.round(1.00*val/histBase*100)), dateArray[i]);
			
	        map.put(AnnoCommonSQL.reqArray[i-1]+AnnoCommonSQL.typeArray[1], pair2);//加入rate 
		}
		
		
		for(Method method:methodArray)// 把map里的 <七日,val><30日,val>放入sql中
    	{	
    	  AnnotationUpdateSQL4FutureBean updateAnnotation = method.getAnnotation(AnnotationUpdateSQL4FutureBean.class);
    	  if(updateAnnotation!=null)// 如果该field有注解，则说明需要更新
    	  {
	    		
	    		String sql=updateAnnotation.sql();
	    		
	    		map.get(updateAnnotation.name()).getKey();
	    		
	    		
	    		String strVal=map.get(updateAnnotation.name()).getKey();
				String strDate=map.get(updateAnnotation.name()).getValue();//在pair里面，因为只有一个元素，谁是pair谁是key无所谓
				//
				String sql1=replaceSqlVal(sql,strVal,strDate); // update [val] where time=[time]
	    		sqlArray.add(sql1);
    	  }
    	}
		
		
		return sqlArray;
		
	}
	*/
	
	/**update haha_new_usr_stat set MonthActiveRate = [?] where Time = [?]*/
	private String replaceSqlVal(String sql, String val, String strDate) {
		// TODO Auto-generated method stub
		String newsql=sql.replaceFirst("\\[\\?\\]", val);
		String newsq2=newsql.replaceFirst("\\[\\?\\]", strDate);
		//Object rst=executeSQL(newsq2,dbcSet);///?????????????????????????
		//return rst.toString(); // 返回次日活跃数等
		return newsq2;
	}

	/*当天的数据产生后，更新历史数据，如7日活跃率*/
	private <T> IMysqlBean updateTbCollumn4Date(String []sqlArray)
	{
		//更新7天的数据
		
		Connection conn = DataBase_Info.getConn(dbcSet.getDb_ip(), dbcSet.getUser(), dbcSet.getPwd(), dbcSet.getDb_name()); // 获取数据库连接	
		try {
				if (!conn.isClosed()) 
				{
					for(String sql:sqlArray)
					{
						
						PreparedStatement pstatement = (PreparedStatement) conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
						conn.setAutoCommit(false);	
						System.out.println(sql);
						pstatement.executeUpdate(sql);   
						pstatement.addBatch();// 更新1天，更新7天，更新一个月前的数据
						pstatement.executeBatch();// 批量执行刚才插入的语句
					}
					
					conn.commit();
					System.out.println("哈哈日历新用户前n天数据更新成功updateTbCollumn4Date!");
				}
			} 
					catch (Exception e) {
					e.printStackTrace();
				} finally {
								try {
									if (DataBase_Info.rs != null) {
										DataBase_Info.rs.close();
									}
									if (conn != null) {
										conn.close();
									}
								} catch (Exception ex) {
									ex.printStackTrace();
								}
			}//try
		return null;	
	}
	
	
	/*根据日期组装好当天的数据做成一个bean： 关键是把sql语句中的？号替换成当天的日期，因此该方法传入日期参数*/
	public IMysqlBean assebmleBean4Date(String date,IMysqlBean inputbean)
	{
		Class clazz = inputbean.getClass();		
		Method [] mArray=clazz.getDeclaredMethods();
		try
		{
			for(Method mt:mArray)//遍历该Bean的所有的set方法，需要将来更新的在bean定义时已经默认为0
			{			
				if(mt.isAnnotationPresent(AnnotationGenSQL4Bean.class))// 只有set方法会有annotation，且要区分update的annotation和非update的annotation
				{					
					AnnotationGenSQL4Bean anno=mt.getAnnotation(AnnotationGenSQL4Bean.class);//获取该方法的annotation	
					if(anno!=null)//如果获得的是set方法的注解
					{
						String annoSql=anno.sql1();// 从annotation里获取sql语句
						EnumBeanFieldType et=anno.et();
						String fieldName=anno.TbColumnName();				
						String sql=annoSql.replaceAll("\\[\\?\\]", date);//用前将sql替换成相应的日期
						// 注意fieldName不应该是新的，而应该是
						Object fieldVal=fetchBeanField4Date(sql, et, fieldName); //根据获得的SQL语句来执行查询
						mt.invoke(inputbean, fieldVal);// 调用set方法来注入bean的属性，注意int到object的upcast和downcast的问题
					}
				}		
			}
		}catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return inputbean;
	}
	
	
	/**交织操作:根据日期组装好当天的数据做成一个bean的list：因此该方法传入日期参数
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException */
	public List<IMysqlBean> assebmleBeanList4Date(String date,IMysqlBean inputbean) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException
	{
		List<IMysqlBean> blist=new ArrayList<IMysqlBean>();
		Class<? extends IMysqlBean> clazz = inputbean.getClass();		
		Method [] mArray=clazz.getDeclaredMethods();
		Map<Method,List<Object>> map=new HashMap<Method,List<Object>>();
		int beanNum=0;
		
		try
		{
			for(Method mt:mArray)//遍历该Bean的所有的set方法，需要将来更新的在bean定义时已经默认为0
			{			
				if(mt.isAnnotationPresent(AnnotationGenSQL4Bean.class))// 只有set方法会有annotation，且要区分update的annotation和非update的annotation
				{					
					AnnotationGenSQL4Bean anno=mt.getAnnotation(AnnotationGenSQL4Bean.class);//获取该方法的annotation	
					if(anno!=null)//如果获得的是set方法的注解
					{
						String annoSql=anno.sql1();// 从annotation里获取sql语句
						EnumBeanFieldType et=anno.et();
						String fieldName=anno.TbColumnName();				
						String sql=annoSql.replaceAll("\\[\\?\\]", date);//用前将sql替换成相应的日期
						// 注意fieldName不应该是新的，而应该是
						
						List<Object> fieldValList=fetchBeanFieldList4Date(sql, et, fieldName); //根据获得的SQL语句来执行查询
						map.put(mt, fieldValList);
						
						beanNum=fieldValList.size();
					}
				}		
			}
		}catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		
		IMysqlBean temp=null;
		
		//交织后组装到一起，此处要用结果的长度最大的那个字段作为columnNum，其他字段的结果都用这个来orderby，如果某个字段的结果的长度不够，则填充为null
		
		for(int i=0;i<beanNum;i++)
		{
		    temp=(IMysqlBean) clazz.newInstance(); /// throws 
		    Iterator<Entry<Method, List<Object>>> iter = map.entrySet().iterator(); 
		    while (iter.hasNext()) { 
		        Map.Entry entry = (Map.Entry) iter.next(); 
		        Method mt = (Method) entry.getKey(); 
		        List<Object> valList = (List<Object>) entry.getValue();
		        Object val=valList.get(i);
		        mt.invoke(temp, val); ////throws
		    }		        
		   blist.add(temp);
		}
		
		return blist;
	}
	
	



	/**向数据库中append一个Bean的数据 */
	public void appendEntry2Tb(IMysqlBean bean) //把组装好的新bean插入到数据库中
	{
			
		//String sql = Gen_Sql_haha_Collect.getSQL_insertFromTbName("NewUserStatDAO"); // 获得插入语句			
		String sql=bean.getSQL_insert();
		List<String> colList = extractColNameFromSqlInsert(sql);// 获取数据库每一列的名字	
		
		Class clazz = bean.getClass();
		
		Connection conn = DataBase_Info.getConn(dbcSet.getDb_ip(), dbcSet.getUser(), dbcSet.getPwd(), dbcSet.getDb_name()); // 获取数据库连接
			try {
				if (!conn.isClosed()) 
				{
					PreparedStatement pstatement = (PreparedStatement) conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
					conn.setAutoCommit(false);	
					int colNum = colList.size();// 获取需要插入的方法
					for (int j = 1; j <= colNum; j++) 
					{
						Method m1 = clazz.getDeclaredMethod("get"+ colList.get(j-1));// 通过反射调用“get列名”这个函数
						pstatement.setString(j,  m1.invoke(bean).toString());// bean的数据已经准备好,调用bean的get方法来插入数据
					}//对于需要update的数据，在准备bean的时候应该设为0，这样可以将0插入到数据库中
					pstatement.addBatch();
					pstatement.executeBatch();// 批量执行刚才插入的语句
					conn.commit();
					System.out.println("哈哈日历"+bean.getClass().getCanonicalName()+"单个entry添加成功!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (DataBase_Info.rs != null) {
						DataBase_Info.rs.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	/**向数据库中批量添加数据,即添加List<Bean>的数据 */
	public void appendEntryList2Tb(List<? extends IMysqlBean> beanlist3) //把组装好的新bean插入到数据库中
	{
			
			if(beanlist3==null) 
			{
				System.out.println("beanlist3 is null!!!!!!!!!!!!!!!!!!");
				return;
			}
			
			if(beanlist3.size()==0) 
			{
				System.out.println("beanlist is empty!!!!!!!!!!!!!!!!!!");
				return;
			}
				
				
			String sql=beanlist3.get(0).getSQL_insert();
			
			
			List<String> colList = extractColNameFromSqlInsert(sql);// 获取数据库每一列的名字
			
			Connection conn = DataBase_Info.getConn(dbcSet.getDb_ip(), dbcSet.getUser(), dbcSet.getPwd(), dbcSet.getDb_name()); // 获取数据库连接
			try {
				if (!conn.isClosed()) 
				{
					PreparedStatement pstatement = (PreparedStatement) conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
					conn.setAutoCommit(false);
					for (int i = 0; i < beanlist3.size(); i++) {

						IMysqlBean nu = beanlist3.get(i);
						Class clazz = nu.getClass();
						int colNum = colList.size();
						for (int j = 0; j < colNum; j++) {
							String hha=colList.get(j);
							
							String name=hha.replaceFirst(hha.substring(0,1), hha.substring(0,1).toUpperCase());
							
							Method m1 = clazz.getDeclaredMethod("get"+name );// 通过反射调用“get列名”这个函数
							
							System.out.println(m1.getName());
							
							System.out.println((String) m1.invoke(nu).toString());
							pstatement.setString(j+1, (String) m1.invoke(nu).toString());
						
						}
						pstatement.addBatch();
					}
					pstatement.executeBatch();// 批量执行刚才插入的语句
					conn.commit();
					System.out.println("哈哈日历list数据添加成功!");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (DataBase_Info.rs != null) {
						DataBase_Info.rs.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	
	
	/** 从sql里获取每个列的名字
	 * 
	 *  例如从insert into new_user_data_gswb(DateLine,TodayNewInstallNum,TodayNewUninstallNum,TodayNewActiveNum,NextDayActiveNum,7DayActiveNum,30DayActiveNum,30DayUninstallNum)" +
		" values(?,?,?,?,?,?,?,?)";提取各个列的信息
	 */
	
	/**从insert语句里提取表的各个行的名字*/
	private List<String> extractColNameFromSqlInsert(String sqlExp) {
		// TODO Auto-generated method stub
		List<String> rstList=new ArrayList<String>();
		int i1=sqlExp.indexOf("(", 1);
		int i2=sqlExp.indexOf(")", 1);
		String[] rst=sqlExp.substring(i1+1, i2).split(",");
		for(String str:rst)
			rstList.add(str);
		return rstList;
	}

	
	
	
	public Object executeSQL(String sql,DBConInfo dbin)
	{
		
		Connection conn = DataBase_Info.getConn(dbin.getDb_ip(),dbin.getUser(),dbin.getPwd(),dbin.getDb_name());
		
		Object rst=new Object();// 返回值
		
		try{
			if(!conn.isClosed()){				
				Statement statement = (Statement) conn.createStatement();
				//查询所有活跃表,新用户活跃表
				DataBase_Info.rs = statement.executeQuery(sql);//通过sql的select语句获得结果集合				
				while(DataBase_Info.rs.next()) 
				{
					rst=DataBase_Info.rs.getObject(1);//不管是什么元素,获得结果集合
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		try {
			    if(DataBase_Info.rs != null){   
			    	DataBase_Info.rs.close();   
			    } 
			    if(conn != null){   
			     conn.close();   
			    }   
		   } catch (Exception ex) {   
			   ex.printStackTrace();   
		   }   
		}//finally
		return rst; 
		
	}

	public <T extends IMysqlBean> List<T> batchFetchBeanList4Date(String inSql,String strDate,Class clazz,Factory<? extends T> factory) {
		
	     Connection conn = DataBase_Info.getConn(dbcGet.getDb_ip(),dbcGet.getUser(),dbcGet.getPwd(),dbcGet.getDb_name());	
	 
	     List<T> beanlist3=new ArrayList<T>();
	     
	     T bean=null; 
	 
	     String sql=inSql.replaceAll("\\[\\?\\]",strDate);//用前将sql替换成相应的日期
	     	
			try{
				if(!conn.isClosed()){				
					Statement statement = (Statement) conn.createStatement();
					//查询所有活跃表,新用户活跃表
					
					
					DataBase_Info.rs = statement.executeQuery(sql);//通过sql的select语句获得结果集合				
					// 此时已经获得了一个bean的list
					while(DataBase_Info.rs.next()) 
					{
					 bean=factory.create(); //此处必须新建一个bean，否则beanlist里的全是重复的数据
					 Method [] mArray=clazz.getDeclaredMethods();
					 Object fieldVal=null;
					 try
							{
								for(Method mt:mArray)//遍历该Bean的所有的set方法，需要将来更新的在bean定义时已经默认为0
								{			
									if(mt.isAnnotationPresent(AnnotationGenSQL4Bean.class))// 只有set方法会有annotation，且要区分update的annotation和非update的annotation
									{					
										AnnotationGenSQL4Bean anno=mt.getAnnotation(AnnotationGenSQL4Bean.class);//获取该方法的annotation	
										if(anno!=null)//如果获得的是set方法的注解
										{
											
											EnumBeanFieldType et=anno.et();
											String fieldName=anno.TbColumnName();				
											
											// 注意fieldName不应该是新的，而应该是
											
											
											
											switch(et) //JAVA的up-cast，之后可以自动downcast
											 {
											 	case INT:
												 {
													 fieldVal=DataBase_Info.rs.getInt(fieldName);
													 
													 break;
												 }
											 	case Double:
												 {
													 fieldVal=DataBase_Info.rs.getDouble(fieldName);
												   
													 break;
												 }
											 	case STRING:
											 	 {
											 		 fieldVal=DataBase_Info.rs.getString(fieldName);
				
													 break;
												 }
											 	 default:
											 	 {
											 		 throw new Throwable("undefined type when fetchting from mysql");// 什么都不做会有异常
											 	 }					 
											 }
											
											
											
											
											mt.invoke(bean, fieldVal);// 调用set方法来注入bean的属性，注意int到object的upcast和downcast的问题
											System.out.println();
										}
									}		
								}
								beanlist3.add(bean);
						//	inputRstList.add((IMysqlBean)bean);	
							
							}catch (Throwable e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							};
						
						
						 
					}//while 循环查询的结果，一个结果是一个bean
				}//if conn is not closed
			}catch(Exception e){
				e.printStackTrace();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
			try {
				    if(DataBase_Info.rs != null){   
				    	DataBase_Info.rs.close();   
				    } 
				    if(conn != null){   
				     conn.close();   
				    }   
			   } catch (Exception ex) {   
				   ex.printStackTrace();   
			   }   
			}
			return beanlist3; 
			
		}
	
	
	
	/*public <T extends IMysqlBean> List<T> batchFetchBeanList4Date(String inSql,String strDate,Class clazz,Factory<? extends T> factory) {
		
	     Connection conn = DataBase_Info.getConn(dbcGet.getDb_ip(),dbcGet.getUser(),dbcGet.getPwd(),dbcGet.getDb_name());	
	 
	     List<T> beanlist3=new ArrayList<T>();
	     
	     T bean=factory.create(); 
	 
	     String sql=inSql.replaceAll("\\[\\?\\]",strDate);//用前将sql替换成相应的日期
	     	
			try{
				if(!conn.isClosed()){				
					Statement statement = (Statement) conn.createStatement();
					//查询所有活跃表,新用户活跃表
					
					
					DataBase_Info.rs = statement.executeQuery(sql);//通过sql的select语句获得结果集合				
					
					while(DataBase_Info.rs.next()) 
					{
						
					 Method [] mArray=clazz.getDeclaredMethods();
					 Object fieldVal=null;
					 try
							{
								for(Method mt:mArray)//遍历该Bean的所有的set方法，需要将来更新的在bean定义时已经默认为0
								{			
									if(mt.isAnnotationPresent(AnnotationGenSQL4Bean.class))// 只有set方法会有annotation，且要区分update的annotation和非update的annotation
									{					
										AnnotationGenSQL4Bean anno=mt.getAnnotation(AnnotationGenSQL4Bean.class);//获取该方法的annotation	
										if(anno!=null)//如果获得的是set方法的注解
										{
											String annoSql=anno.sql1();//此句子没有用
											EnumBeanFieldType et=anno.et();
											String fieldName=anno.TbColumnName();				
											
											// 注意fieldName不应该是新的，而应该是
											
											
											
											switch(et) //JAVA的up-cast，之后可以自动downcast
											 {
											 	case INT:
												 {
													 fieldVal=DataBase_Info.rs.getInt(fieldName);
													 
													 break;
												 }
											 	case Double:
												 {
													 fieldVal=DataBase_Info.rs.getDouble(fieldName);
												   
													 break;
												 }
											 	case STRING:
											 	 {
											 		 fieldVal=DataBase_Info.rs.getString(fieldName);
				
													 break;
												 }
											 	 default:
											 	 {
											 		 throw new Throwable("undefined type when fetchting from mysql");// 什么都不做会有异常
											 	 }					 
											 }
											
											
											
											
											mt.invoke(bean, fieldVal);// 调用set方法来注入bean的属性，注意int到object的upcast和downcast的问题
										}
									}		
								}
								beanlist3.add(bean);
						//	inputRstList.add((IMysqlBean)bean);	
							
							}catch (Throwable e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							};
						
						
						 
					}//while 循环查询的结果，一个结果是一个bean
				}//if conn is not closed
			}catch(Exception e){
				e.printStackTrace();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
			try {
				    if(DataBase_Info.rs != null){   
				    	DataBase_Info.rs.close();   
				    } 
				    if(conn != null){   
				     conn.close();   
				    }   
			   } catch (Exception ex) {   
				   ex.printStackTrace();   
			   }   
			}
			return beanlist3; 
			
		}*/

	
	public <T extends IMysqlBean> List<T> batch2FetchBeanList4Date(String inSql,String strDate,Map<Integer,String> map,Factory<? extends T> factory,String strmt) {
		
	     Connection conn = DataBase_Info.getConn(dbcGet.getDb_ip(),dbcGet.getUser(),dbcGet.getPwd(),dbcGet.getDb_name());	
	 
	     List<T> beanlist3=new ArrayList<T>();
	     
	     T bean=factory.create(); 
	 
	     String sql=inSql.replaceAll("\\[\\?\\]",strDate);//用前将sql替换成相应的日期
	     	
			try{
				if(!conn.isClosed()){				
					Statement statement = (Statement) conn.createStatement();
					//查询所有活跃表,新用户活跃表
					
					
					DataBase_Info.rs = statement.executeQuery(sql);//通过sql的select语句获得结果集合				
					
					while(DataBase_Info.rs.next()) 
					{						
					 
					 int actionID=DataBase_Info.rs.getInt("action");
					 int userCount=DataBase_Info.rs.getInt("userCount");
					 int actionCount=DataBase_Info.rs.getInt("actionCount");
					System.out.println(map.get(actionID));
					 
					
					 Method mt1=bean.getClass().getDeclaredMethod("set"+map.get(actionID)+"_click",Integer.class);
					 Method mt2=bean.getClass().getDeclaredMethod("set"+map.get(actionID)+"_num",Integer.class);
				
					 
					 mt1.invoke(bean, actionCount);// 调用set方法来注入bean的属性，注意int到object的upcast和downcast的问题
					 mt2.invoke(bean, userCount);
					
					 
					}//while 循环查询的结果，一个结果是一个bean
					Method mtt=bean.getClass().getDeclaredMethod(strmt, Integer.class); 
					mtt.invoke(bean,Integer.valueOf(strDate));
					beanlist3.add(bean);
				}//if conn is not closed
			}catch(Exception e){
				e.printStackTrace();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
			try {
				    if(DataBase_Info.rs != null){   
				    	DataBase_Info.rs.close();   
				    } 
				    if(conn != null){   
				     conn.close();   
				    }   
			   } catch (Exception ex) {   
				   ex.printStackTrace();   
			   }   
			}
			return beanlist3; 
			
		}

	private String getFieldNameFromMethod(Method mt) {
		// TODO Auto-generated method stub
		String name=mt.getName();
		if(name.startsWith("get"))
		return name.replaceFirst("get", "");
		else
			return name.replaceFirst("set", "");
	}
	
	
}
