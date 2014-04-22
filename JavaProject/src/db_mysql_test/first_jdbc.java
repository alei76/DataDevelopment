package db_mysql_test;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;



public class first_jdbc {
	
	  // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	   static final String DB_URL ="jdbc:mysql://112.124.97.43:3306/haharili_log";

	   //  Database credentials
	   static final String USER = "developer";
	   static final String PASS = "DN3v74JB";

	   public static void main(String[] args) {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      
		  Class.forName(JDBC_DRIVER).newInstance();

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);

	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      stmt = (Statement) conn.createStatement();
	      String sql;
	      sql = "SELECT * FROM install20131216";
	      ResultSet rs = stmt.executeQuery(sql);

	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         int id  = rs.getInt("id");
	         String time = rs.getString("time");
	         
	         //Display values
	         System.out.print("ID: " + id);
	         System.out.println("time: " + time);
	      }
	      //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
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
	         if(stmt!=null)
	            stmt.close();
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


