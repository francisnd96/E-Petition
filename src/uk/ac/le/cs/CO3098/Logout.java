package uk.ac.le.cs.CO3098;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uk.ac.le.cs.CO3098.bean.User;
import uk.ac.le.cs.CO3098.bean.UserVerification;


public class Logout extends HttpServlet {
		public void doGet(HttpServletRequest req, 
				        HttpServletResponse res) 
		            throws ServletException, IOException {
			//removes the user from the session on logout
				HttpSession se=req.getSession();
				se.removeAttribute("User");
				res.sendRedirect("../login.jsp");
		}
		public void doPost(HttpServletRequest req, 
					HttpServletResponse res) 
		            throws ServletException, IOException {
			doGet(req, res);}
	}


