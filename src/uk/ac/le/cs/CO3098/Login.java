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

import uk.ac.le.cs.CO3098.bean.User;
import uk.ac.le.cs.CO3098.bean.UserVerification;

public class Login extends HttpServlet {
	
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String email=request.getParameter("email");
		String pw=request.getParameter("password");
		
		if(email.equals("")|| pw.equals("")){
			response.sendRedirect("../login.jsp");
			return;
		}
		
		UserVerification dbOperator=new UserVerification();
		User u=dbOperator.checkUser(email, pw);
		HttpSession se=request.getSession();
		
		if(u!=null){
			se.setAttribute("User",u);
			response.sendRedirect("../user.jsp");
		}else{
			response.sendRedirect("../regError.jsp?errorid=3");
		}
		//out.close();
	}
	public void doPost(HttpServletRequest req, 
				HttpServletResponse res) 
	            throws ServletException, IOException {
		doGet(req, res);}
}

		