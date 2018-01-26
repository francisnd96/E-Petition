package uk.ac.le.cs.CO3098.bean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.security.*;
import java.math.*;

public class UserVerification {
	
	  private static Connection connect = null;
	  private static String host="mysql.mcscw3.le.ac.uk";
	  private static String database="fnd1";
	  private static String username="fnd1";
	  private static String password="eebahngo";
	  
	  public static Connection getConnection(){
			if(connect ==null){
				try{
				 Class.forName("com.mysql.jdbc.Driver");
			      String conn_string="jdbc:mysql://"+host+"/"+database;
			      Connection connect = DriverManager.getConnection(conn_string,username,password);
			      return connect;
				}catch(Exception ex){
					 return null;
					//ex.printStackTrace();
				}
			}else{
				return connect;
			}
		}
	  
	  public User checkUser(String email,String password){
		  
		  String sql="select email,pword,firstName,lastName, ismp from GUESTS where email=? and pword=?";
		  User u=null;
	    	try( Connection connect = getConnection(); 
		    	 PreparedStatement pstmt = connect.prepareStatement(sql);
	    		){	
		    	 pstmt.setString(1,email);
		    	 pstmt.setString(2,SHA256HashGenerator.getSHA256Hash(password));
		    	 try (ResultSet rs = pstmt.executeQuery();){
		    	   while(rs.next()){    		 
			    	  String DBemail = rs.getString("email");			
			    	  String pass = rs.getString("pword");
			    	  String firstName = rs.getString("firstName");
			    	  String lastName = rs.getString("lastName");
			    	  int isMP = rs.getInt("ismp");
			    	  u=new User(DBemail,pass,firstName,lastName,isMP);
			    	  break;
			    	
			      }
		    	 }
	    	}catch(SQLException ex){
	    		ex.printStackTrace();	
	    	}
	    	return u;
		  
	  }
	  
	  public static void main(String[] args){
		 UserVerification DBoperator=new UserVerification();
		 System.out.println(DBoperator.checkUser("admin", "helloworld11"));
		  
	  }

}
