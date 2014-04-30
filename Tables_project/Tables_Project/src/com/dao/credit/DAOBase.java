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
	//DAO��Ӧ�ò�����ҵ��,ֻ����ҵ���enum����������ȡ��Ӧĳ���bean��Ȼ��append���ϳɱ���

	private DBConInfo dbcGet=null;///////////////////////////
	private DBConInfo dbcSet=null;///////////////////////////

	
	
	public DAOBase(DBConInfo dbcGet, DBConInfo dbcSet)
	{
		this.dbcGet=dbcGet;
		this.dbcSet=dbcSet;
	}
	
	//Ҫע�����sql�������ʺŵ�,��ǰ��Ҫ����Щ�ʺ��滻��
	
	/**��ȡ��Ӧĳ���bean��list*/
	public Class inner;
	private Object fetchBeanField4Date(String sql,EnumBeanFieldType et,String columnName)
	{
		
		Connection conn = DataBase_Info.getConn(dbcGet.getDb_ip(),dbcGet.getUser(),dbcGet.getPwd(),dbcGet.getDb_name());
		
		Object rst=new Object();// ����ֵ
		
		try{
			if(!conn.isClosed()){				
				Statement statement = (Statement) conn.createStatement();
				//��ѯ���л�Ծ��,���û���Ծ��
				DataBase_Info.rs = statement.executeQuery(sql);//ͨ��sql��select����ý������
						
				while(DataBase_Info.rs.next()) 
				{
					
					
					 switch(et) //JAVA��up-cast��֮������Զ�downcast
					 {
					 	case INT:
						 {
							 rst=DataBase_Info.rs.getInt(1);///����������������������������						 
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
					 		 throw new Throwable("undefined type when fetchting from mysql");// ʲô�����������쳣
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
     List<Object> rstList=new ArrayList<Object>();// ����ֵ
	 Object temp;
		
		try{
			if(!conn.isClosed()){				
				Statement statement = (Statement) conn.createStatement();
				//��ѯ���л�Ծ��,���û���Ծ��
				DataBase_Info.rs = statement.executeQuery(sql);//ͨ��sql��select����ý������				
				
				while(DataBase_Info.rs.next()) 
				{
					
					
					 switch(et) //JAVA��up-cast��֮������Զ�downcast
					 {
					 	case INT:
						 {
							 rstList.add(DataBase_Info.rs.getInt(1));///����������������������������	
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
					 		 throw new Throwable("undefined type when fetchting from mysql");// ʲô�����������쳣
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

	
	
	/*public void updateTable4Date(String[] strDateArray) //��todayActiveCount�� �Զ�����1�գ�7�պ�30�յ�����
	{   			
		List<String> sqlList=getUpdateSql(strDateArray); //�����Ҫ�����õ�sql��array,��С��������1��7��30��*field��
		String []array=new String[sqlList.size()];
		int i=0;
		for(String str:sqlList)
		{
			array[i++]=str;
		}
		updateTbCollumn4Date(array); //��׼���õ�sql�����ɵ�SQL��array�������ݿ���ɸ��²���		
	}*/
	/**ֻ����ض���bean����update����������������ض���bean*/
	 /*private List<String> getUpdateSql(String[]dateArray) {//���ջ�Ծ�ʺͻ�Ծ��������Ҫ�ȳ����ʷ�ϵ�n��ǰ���Ǹ�����
		// TODO Auto-generated method stub
		*//**���Ȼ��ͨ��:�����������ʷ�Ļ�Ծ��*//*
		// �Ȼ����select count(distinct b.uid)from install[?] as a inner join user_action[?] as b on a.UID=b.uid��Ȼ������
		AnnotationSQLCommon4Bean AnnoCommonSQL=(AnnotationSQLCommon4Bean)clazz.getAnnotation(AnnotationSQLCommon4Bean.class);
		if(AnnoCommonSQL==null) return null;
		String sqlFutureActive=AnnoCommonSQL.sql1();
	
		
		List<String>  sqlArray=new ArrayList<String>();
		Map<String,Map.Entry<String, String>> map=new HashMap();

		String histSQLString="select count(distinct a.uid) from install[?] as a";

		Long histBase; //
		Long val;
		
		for(int i=1;i<=dateArray.length-1;i++) // ���ڴ��գ�7�պ�30��Ҫ��ѯ�ı���ǲ�һ���ģ���������
		{
			
			String sql=replaceSqlVal(sqlFutureActive,dateArray[0],dateArray[i]);///???????????????????????????????????
		
			histBase=(Long) executeSQL(histSQLString.replaceAll("\\[\\?\\]", dateArray[i]),dbcGet);
			val=(Long) executeSQL(sql,dbcGet);//ͳ�Ƴ� ���գ�7�պ�30�ջ�Ծ�û������������ʷ�ϵ����û� 
			
			
			//Math.round((obj.getLoginNum()*1.00/obj.getNext7DayRegInsActive())*100);

			Map.Entry<String, String> pair = new AbstractMap.SimpleEntry(val.toString(), dateArray[i]);
			
	        map.put(AnnoCommonSQL.reqArray[i-1]+AnnoCommonSQL.typeArray[0], pair);//����count
	        
	        Map.Entry<String, String> pair2 = new AbstractMap.SimpleEntry(Double.toString(Math.round(1.00*val/histBase*100)), dateArray[i]);
			
	        map.put(AnnoCommonSQL.reqArray[i-1]+AnnoCommonSQL.typeArray[1], pair2);//����rate 
		}
		
		
		for(Method method:methodArray)// ��map��� <����,val><30��,val>����sql��
    	{	
    	  AnnotationUpdateSQL4FutureBean updateAnnotation = method.getAnnotation(AnnotationUpdateSQL4FutureBean.class);
    	  if(updateAnnotation!=null)// �����field��ע�⣬��˵����Ҫ����
    	  {
	    		
	    		String sql=updateAnnotation.sql();
	    		
	    		map.get(updateAnnotation.name()).getKey();
	    		
	    		
	    		String strVal=map.get(updateAnnotation.name()).getKey();
				String strDate=map.get(updateAnnotation.name()).getValue();//��pair���棬��Ϊֻ��һ��Ԫ�أ�˭��pair˭��key����ν
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
		//return rst.toString(); // ���ش��ջ�Ծ����
		return newsq2;
	}

	/*��������ݲ����󣬸�����ʷ���ݣ���7�ջ�Ծ��*/
	private <T> IMysqlBean updateTbCollumn4Date(String []sqlArray)
	{
		//����7�������
		
		Connection conn = DataBase_Info.getConn(dbcSet.getDb_ip(), dbcSet.getUser(), dbcSet.getPwd(), dbcSet.getDb_name()); // ��ȡ���ݿ�����	
		try {
				if (!conn.isClosed()) 
				{
					for(String sql:sqlArray)
					{
						
						PreparedStatement pstatement = (PreparedStatement) conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
						conn.setAutoCommit(false);	
						System.out.println(sql);
						pstatement.executeUpdate(sql);   
						pstatement.addBatch();// ����1�죬����7�죬����һ����ǰ������
						pstatement.executeBatch();// ����ִ�иղŲ�������
					}
					
					conn.commit();
					System.out.println("�����������û�ǰn�����ݸ��³ɹ�updateTbCollumn4Date!");
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
	
	
	/*����������װ�õ������������һ��bean�� �ؼ��ǰ�sql����еģ����滻�ɵ�������ڣ���˸÷����������ڲ���*/
	public IMysqlBean assebmleBean4Date(String date,IMysqlBean inputbean)
	{
		Class clazz = inputbean.getClass();		
		Method [] mArray=clazz.getDeclaredMethods();
		try
		{
			for(Method mt:mArray)//������Bean�����е�set��������Ҫ�������µ���bean����ʱ�Ѿ�Ĭ��Ϊ0
			{			
				if(mt.isAnnotationPresent(AnnotationGenSQL4Bean.class))// ֻ��set��������annotation����Ҫ����update��annotation�ͷ�update��annotation
				{					
					AnnotationGenSQL4Bean anno=mt.getAnnotation(AnnotationGenSQL4Bean.class);//��ȡ�÷�����annotation	
					if(anno!=null)//�����õ���set������ע��
					{
						String annoSql=anno.sql1();// ��annotation���ȡsql���
						EnumBeanFieldType et=anno.et();
						String fieldName=anno.TbColumnName();				
						String sql=annoSql.replaceAll("\\[\\?\\]", date);//��ǰ��sql�滻����Ӧ������
						// ע��fieldName��Ӧ�����µģ���Ӧ����
						Object fieldVal=fetchBeanField4Date(sql, et, fieldName); //���ݻ�õ�SQL�����ִ�в�ѯ
						mt.invoke(inputbean, fieldVal);// ����set������ע��bean�����ԣ�ע��int��object��upcast��downcast������
					}
				}		
			}
		}catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return inputbean;
	}
	
	
	/**��֯����:����������װ�õ������������һ��bean��list����˸÷����������ڲ���
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
			for(Method mt:mArray)//������Bean�����е�set��������Ҫ�������µ���bean����ʱ�Ѿ�Ĭ��Ϊ0
			{			
				if(mt.isAnnotationPresent(AnnotationGenSQL4Bean.class))// ֻ��set��������annotation����Ҫ����update��annotation�ͷ�update��annotation
				{					
					AnnotationGenSQL4Bean anno=mt.getAnnotation(AnnotationGenSQL4Bean.class);//��ȡ�÷�����annotation	
					if(anno!=null)//�����õ���set������ע��
					{
						String annoSql=anno.sql1();// ��annotation���ȡsql���
						EnumBeanFieldType et=anno.et();
						String fieldName=anno.TbColumnName();				
						String sql=annoSql.replaceAll("\\[\\?\\]", date);//��ǰ��sql�滻����Ӧ������
						// ע��fieldName��Ӧ�����µģ���Ӧ����
						
						List<Object> fieldValList=fetchBeanFieldList4Date(sql, et, fieldName); //���ݻ�õ�SQL�����ִ�в�ѯ
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
		
		//��֯����װ��һ�𣬴˴�Ҫ�ý���ĳ��������Ǹ��ֶ���ΪcolumnNum�������ֶεĽ�����������orderby�����ĳ���ֶεĽ���ĳ��Ȳ����������Ϊnull
		
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
	
	



	/**�����ݿ���appendһ��Bean������ */
	public void appendEntry2Tb(IMysqlBean bean) //����װ�õ���bean���뵽���ݿ���
	{
			
		//String sql = Gen_Sql_haha_Collect.getSQL_insertFromTbName("NewUserStatDAO"); // ��ò������			
		String sql=bean.getSQL_insert();
		List<String> colList = extractColNameFromSqlInsert(sql);// ��ȡ���ݿ�ÿһ�е�����	
		
		Class clazz = bean.getClass();
		
		Connection conn = DataBase_Info.getConn(dbcSet.getDb_ip(), dbcSet.getUser(), dbcSet.getPwd(), dbcSet.getDb_name()); // ��ȡ���ݿ�����
			try {
				if (!conn.isClosed()) 
				{
					PreparedStatement pstatement = (PreparedStatement) conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
					conn.setAutoCommit(false);	
					int colNum = colList.size();// ��ȡ��Ҫ����ķ���
					for (int j = 1; j <= colNum; j++) 
					{
						Method m1 = clazz.getDeclaredMethod("get"+ colList.get(j-1));// ͨ��������á�get�������������
						pstatement.setString(j,  m1.invoke(bean).toString());// bean�������Ѿ�׼����,����bean��get��������������
					}//������Ҫupdate�����ݣ���׼��bean��ʱ��Ӧ����Ϊ0���������Խ�0���뵽���ݿ���
					pstatement.addBatch();
					pstatement.executeBatch();// ����ִ�иղŲ�������
					conn.commit();
					System.out.println("��������"+bean.getClass().getCanonicalName()+"����entry��ӳɹ�!");
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
	/**�����ݿ��������������,�����List<Bean>������ */
	public void appendEntryList2Tb(List<? extends IMysqlBean> beanlist3) //����װ�õ���bean���뵽���ݿ���
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
			
			
			List<String> colList = extractColNameFromSqlInsert(sql);// ��ȡ���ݿ�ÿһ�е�����
			
			Connection conn = DataBase_Info.getConn(dbcSet.getDb_ip(), dbcSet.getUser(), dbcSet.getPwd(), dbcSet.getDb_name()); // ��ȡ���ݿ�����
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
							
							Method m1 = clazz.getDeclaredMethod("get"+name );// ͨ��������á�get�������������
							
							System.out.println(m1.getName());
							
							System.out.println((String) m1.invoke(nu).toString());
							pstatement.setString(j+1, (String) m1.invoke(nu).toString());
						
						}
						pstatement.addBatch();
					}
					pstatement.executeBatch();// ����ִ�иղŲ�������
					conn.commit();
					System.out.println("��������list������ӳɹ�!");
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
	
	
	/** ��sql���ȡÿ���е�����
	 * 
	 *  �����insert into new_user_data_gswb(DateLine,TodayNewInstallNum,TodayNewUninstallNum,TodayNewActiveNum,NextDayActiveNum,7DayActiveNum,30DayActiveNum,30DayUninstallNum)" +
		" values(?,?,?,?,?,?,?,?)";��ȡ�����е���Ϣ
	 */
	
	/**��insert�������ȡ��ĸ����е�����*/
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
		
		Object rst=new Object();// ����ֵ
		
		try{
			if(!conn.isClosed()){				
				Statement statement = (Statement) conn.createStatement();
				//��ѯ���л�Ծ��,���û���Ծ��
				DataBase_Info.rs = statement.executeQuery(sql);//ͨ��sql��select����ý������				
				while(DataBase_Info.rs.next()) 
				{
					rst=DataBase_Info.rs.getObject(1);//������ʲôԪ��,��ý������
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
	 
	     String sql=inSql.replaceAll("\\[\\?\\]",strDate);//��ǰ��sql�滻����Ӧ������
	     	
			try{
				if(!conn.isClosed()){				
					Statement statement = (Statement) conn.createStatement();
					//��ѯ���л�Ծ��,���û���Ծ��
					
					
					DataBase_Info.rs = statement.executeQuery(sql);//ͨ��sql��select����ý������				
					// ��ʱ�Ѿ������һ��bean��list
					while(DataBase_Info.rs.next()) 
					{
					 bean=factory.create(); //�˴������½�һ��bean������beanlist���ȫ���ظ�������
					 Method [] mArray=clazz.getDeclaredMethods();
					 Object fieldVal=null;
					 try
							{
								for(Method mt:mArray)//������Bean�����е�set��������Ҫ�������µ���bean����ʱ�Ѿ�Ĭ��Ϊ0
								{			
									if(mt.isAnnotationPresent(AnnotationGenSQL4Bean.class))// ֻ��set��������annotation����Ҫ����update��annotation�ͷ�update��annotation
									{					
										AnnotationGenSQL4Bean anno=mt.getAnnotation(AnnotationGenSQL4Bean.class);//��ȡ�÷�����annotation	
										if(anno!=null)//�����õ���set������ע��
										{
											
											EnumBeanFieldType et=anno.et();
											String fieldName=anno.TbColumnName();				
											
											// ע��fieldName��Ӧ�����µģ���Ӧ����
											
											
											
											switch(et) //JAVA��up-cast��֮������Զ�downcast
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
											 		 throw new Throwable("undefined type when fetchting from mysql");// ʲô�����������쳣
											 	 }					 
											 }
											
											
											
											
											mt.invoke(bean, fieldVal);// ����set������ע��bean�����ԣ�ע��int��object��upcast��downcast������
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
						
						
						 
					}//while ѭ����ѯ�Ľ����һ�������һ��bean
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
	 
	     String sql=inSql.replaceAll("\\[\\?\\]",strDate);//��ǰ��sql�滻����Ӧ������
	     	
			try{
				if(!conn.isClosed()){				
					Statement statement = (Statement) conn.createStatement();
					//��ѯ���л�Ծ��,���û���Ծ��
					
					
					DataBase_Info.rs = statement.executeQuery(sql);//ͨ��sql��select����ý������				
					
					while(DataBase_Info.rs.next()) 
					{
						
					 Method [] mArray=clazz.getDeclaredMethods();
					 Object fieldVal=null;
					 try
							{
								for(Method mt:mArray)//������Bean�����е�set��������Ҫ�������µ���bean����ʱ�Ѿ�Ĭ��Ϊ0
								{			
									if(mt.isAnnotationPresent(AnnotationGenSQL4Bean.class))// ֻ��set��������annotation����Ҫ����update��annotation�ͷ�update��annotation
									{					
										AnnotationGenSQL4Bean anno=mt.getAnnotation(AnnotationGenSQL4Bean.class);//��ȡ�÷�����annotation	
										if(anno!=null)//�����õ���set������ע��
										{
											String annoSql=anno.sql1();//�˾���û����
											EnumBeanFieldType et=anno.et();
											String fieldName=anno.TbColumnName();				
											
											// ע��fieldName��Ӧ�����µģ���Ӧ����
											
											
											
											switch(et) //JAVA��up-cast��֮������Զ�downcast
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
											 		 throw new Throwable("undefined type when fetchting from mysql");// ʲô�����������쳣
											 	 }					 
											 }
											
											
											
											
											mt.invoke(bean, fieldVal);// ����set������ע��bean�����ԣ�ע��int��object��upcast��downcast������
										}
									}		
								}
								beanlist3.add(bean);
						//	inputRstList.add((IMysqlBean)bean);	
							
							}catch (Throwable e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							};
						
						
						 
					}//while ѭ����ѯ�Ľ����һ�������һ��bean
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
	 
	     String sql=inSql.replaceAll("\\[\\?\\]",strDate);//��ǰ��sql�滻����Ӧ������
	     	
			try{
				if(!conn.isClosed()){				
					Statement statement = (Statement) conn.createStatement();
					//��ѯ���л�Ծ��,���û���Ծ��
					
					
					DataBase_Info.rs = statement.executeQuery(sql);//ͨ��sql��select����ý������				
					
					while(DataBase_Info.rs.next()) 
					{						
					 
					 int actionID=DataBase_Info.rs.getInt("action");
					 int userCount=DataBase_Info.rs.getInt("userCount");
					 int actionCount=DataBase_Info.rs.getInt("actionCount");
					System.out.println(map.get(actionID));
					 
					
					 Method mt1=bean.getClass().getDeclaredMethod("set"+map.get(actionID)+"_click",Integer.class);
					 Method mt2=bean.getClass().getDeclaredMethod("set"+map.get(actionID)+"_num",Integer.class);
				
					 
					 mt1.invoke(bean, actionCount);// ����set������ע��bean�����ԣ�ע��int��object��upcast��downcast������
					 mt2.invoke(bean, userCount);
					
					 
					}//while ѭ����ѯ�Ľ����һ�������һ��bean
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
