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

public class ChangePetitionServlet extends HttpServlet {

	private static String host = "mysql.mcscw3.le.ac.uk";
	private static String database = "fnd1";
	private static String username = "fnd1";
	private static String password = "eebahngo";
	boolean skip = true;


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		//if Petition edit is submitted 
		if (request.getParameter("buttonChange") != null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				// loads mysql driver

				String conn_string = "jdbc:mysql://" + host + "/" + database;
				Connection connect = DriverManager.getConnection(conn_string, username, password);

				String id = request.getParameter("id");
				String query = "UPDATE PETITIONS SET TITLE = ?, CONTENT = ? WHERE ID = ?";

				PreparedStatement ps = connect.prepareStatement(query);

				ps.setString(1, title);
				ps.setString(2, content);
				ps.setString(3, id);

				ps.executeUpdate();
				// execute it on test database
				ps.close();
				connect.close();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("skip", skip);
			request.getRequestDispatcher("/servlets/PetitionServlet").forward(request, response);
			return;
		}

	}
}
