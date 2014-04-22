package db_mysql_test;



import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;



public class JDBCDemo {
	
	  // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	   static final String DB_URL ="jdbc:mysql://localhost:3306/test";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "gaoxin";

	   public static void main(String[] args)
	   {
		   
	   Connection conn = null;
	   Statement queryStmt = null;
	   
	   try{
	      //STEP 2: Register JDBC driver
	      
		  Class.forName(JDBC_DRIVER).newInstance();

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);

	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      
	      /////
	      queryStmt = (Statement) conn.createStatement();
	      
	     // String sql="creat table `demo_table`(`id` int(10),`name` varchar(40),`birth` int(11),PRIMARY KEY (`id`))";
	      
	     // PreparedStatement ps=(PreparedStatement) conn.prepareStatement(sql);
	      //ps.execute();
	      
	      String sql="insert into demo_table"
	      + "(name,birth)"
	    		  +
	    		" values(?,?)";
	    					
	      PreparedStatement pstatement = (PreparedStatement) conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); 
		  //id 设置为auto_increament 后不需要设置
		  pstatement.setString(1, "li");
		  pstatement.setLong(2,20010311);
				
		
	      pstatement.execute();
		  
	      
	      
	      
	      sql = "SELECT * FROM demo_table";
	      ResultSet rs = queryStmt.executeQuery(sql);

	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         int id  = rs.getInt("id");
	         String name = rs.getString("name");
	         
	         //Display values
	         System.out.print("ID: " + id);
	         System.out.println("name: " + name);
	      }
	      //STEP 6: Clean-up environment
	      rs.close();
	      queryStmt.close();
	      conn.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(queryStmt!=null)
	            queryStmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	}//end main

}



