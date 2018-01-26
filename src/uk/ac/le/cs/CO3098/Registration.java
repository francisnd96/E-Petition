package uk.ac.le.cs.CO3098;

import java.io.*;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;

import uk.ac.le.cs.CO3098.bean.SHA256HashGenerator;

public class Registration extends HttpServlet {
	
	  private static String host="mysql.mcscw3.le.ac.uk";
	  private static String database="fnd1";
	  private static String username="fnd1";
	  private static String password="eebahngo";
	  int mp;
	  boolean emailExists = false;

	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  // TODO Auto-generated method stub
	  response.setContentType("text/html");
	  PrintWriter out = response.getWriter();
	  String email = request.getParameter("email");
	  String firstName = request.getParameter("firstName");
	  String lastName = request.getParameter("lastName");
	  String dob = request.getParameter("dob");
	  String address = request.getParameter("address");
	  String postcode = request.getParameter("postcode");
	  String pword = request.getParameter("password");
	  String nic = request.getParameter("nic");

	  // validate given input
	  if (email.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || dob.isEmpty() || address.isEmpty() || postcode.isEmpty() || pword.isEmpty() || nic.isEmpty()) {
		  response.sendRedirect("../regError.jsp?errorid=4");
	  } else {
	   try {
	    Class.forName("com.mysql.jdbc.Driver");
	    // loads mysql driver
	    
	    String conn_string="jdbc:mysql://"+host+"/"+database;
	      Connection connect = DriverManager.getConnection(conn_string,username,password);
	      
	    String nicCheck = "select * from NIC_RECORDS where NIC = ? and USED = 0";
	    
	    PreparedStatement ps1 = connect.prepareStatement(nicCheck);
	    ps1.setString(1, nic);
	    ResultSet rs = ps1.executeQuery();
	    String myQuery = "UPDATE NIC_RECORDS SET USED = 1, EMAIL = ? WHERE NIC = ?";
	    String isMP = "select MP FROM NIC_RECORDS WHERE NIC = ?";
	    String emailCheck = "SELECT EMAIL FROM NIC_RECORDS";
	    PreparedStatement ps4 = connect.prepareStatement(emailCheck);
	    PreparedStatement ps3 = connect.prepareStatement(isMP);
	    PreparedStatement ps2 = connect.prepareStatement(myQuery);
	    ps2.setString(1, email);
	    ps2.setString(2, nic);
	    ps3.setString(1,nic);
	    ResultSet rs3 = ps3.executeQuery();
	    ResultSet rs4 = ps4.executeQuery();
	    	    
		while(rs4.next()){
			if(rs4.getString("EMAIL") != null){
				if(rs4.getString("EMAIL").equals(email)){
					emailExists = true;
					break;
				}
			}
		}
		
		if(emailExists == true){
			   response.sendRedirect("../regError.jsp?errorid=1");
			   emailExists = false;
			   return;
		}else{
		    ps2.executeUpdate();
		}
	    
	    if(!rs.absolute(1)){
		    response.sendRedirect("../regError.jsp?errorid=2");
		   return;
	    }
	    
		while(rs3.next()){
			if(rs3.getInt("MP") == 1){
				mp = 1;
			}else{
				mp = 0;
			}
			break;
		}
	    
	     
	    String query = "insert into GUESTS values(?,?,?,?,?,?,?,?)";

	    PreparedStatement ps = connect.prepareStatement(query); // generates sql query
	    

	    String hashed = SHA256HashGenerator.getSHA256Hash(pword);
	    
	    ps.setString(1, email);
	    ps.setString(2, firstName);
	    ps.setString(3, lastName);
	    ps.setString(4, dob);
	    ps.setString(5, address);
	    ps.setString(6, postcode);
	    ps.setString(7, hashed);
	    ps.setInt(8, mp);
	 
	    


	    ps.executeUpdate(); // execute it on test database
	    ps.close();
	    connect.close();
	   } catch (ClassNotFoundException | SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	   }
	   RequestDispatcher rd = request.getRequestDispatcher("/regSuccess.jsp");
	   rd.forward(request, response);
	  }
	 }
	}

