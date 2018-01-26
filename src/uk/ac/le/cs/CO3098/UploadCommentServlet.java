package uk.ac.le.cs.CO3098;

import java.io.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import uk.ac.le.cs.CO3098.bean.Petition;
import uk.ac.le.cs.CO3098.bean.SHA256HashGenerator;
import uk.ac.le.cs.CO3098.bean.User;

public class UploadCommentServlet extends HttpServlet {

	private static String host = "mysql.mcscw3.le.ac.uk";
	private static String database = "fnd1";
	private static String username = "fnd1";
	private static String password = "eebahngo";
	boolean skip = true;
	boolean doComment = true;
	String email;


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		User u = (User) session.getAttribute("User");

		if (u != null) {
			email = u.getEmail();
		}else{
			response.sendRedirect("../regError.jsp?errorid=5");
			return;
		}
		
		
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String comment = request.getParameter("comment");
		String creator = request.getParameter("creator");

		if (request.getParameter("buttonChange") != null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				// loads mysql driver

				String conn_string = "jdbc:mysql://" + host + "/" + database;
				Connection connect = DriverManager.getConnection(conn_string, username, password);

				String id = request.getParameter("id");
				String query = "INSERT INTO COMMENTS VALUES(?,?,?,?)";
				String query2 = "UPDATE PETITIONS SET comments = (comments+1) WHERE id = ?";

				PreparedStatement ps = connect.prepareStatement(query);
				PreparedStatement ps2 = connect.prepareStatement(query2);

				ps.setString(1, null);
				ps.setString(2, comment);
				ps.setString(3, creator);
				ps.setString(4, id);
				
				ps2.setString(1, id);

				ps.executeUpdate();
				ps2.executeUpdate();
				// execute it on test database
				ps.close();
				ps2.close();
				connect.close();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("doComment", doComment);
			request.getRequestDispatcher("/servlets/PetitionServlet").forward(request, response);
			return;
		}

	}
	
	public void doGet(HttpServletRequest req, 
			HttpServletResponse res) 
            throws ServletException, IOException {
	doPost(req, res);}
}
