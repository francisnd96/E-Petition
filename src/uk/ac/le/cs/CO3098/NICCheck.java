package uk.ac.le.cs.CO3098;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import uk.ac.le.cs.CO3098.bean.Comment;
import uk.ac.le.cs.CO3098.bean.User;

public class NICCheck extends HttpServlet {

	private static String host = "mysql.mcscw3.le.ac.uk";
	private static String database = "fnd1";
	private static String username = "fnd1";
	private static String password = "eebahngo";
	int used;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		//retrieve nic value sent from registration.jsp via ajax
		String nic = request.getParameter("nic");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String conn_string = "jdbc:mysql://" + host + "/" + database;
			Connection connect = DriverManager.getConnection(conn_string, username, password);
			String query = "select USED from NIC_RECORDS WHERE NIC = ?";
			PreparedStatement ps = connect.prepareStatement(query);
			ps.setString(1, nic);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {;
				if(rs.getInt("USED") == 1){
					used = 1;
					break;
				}
				if(rs.getInt("USED") == 0){
					used = 2;
					break;
				}else{
					used = 0;
				}
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//display message on registration.jsp depending on NIC entered
		if (used == 1){
			out.println("NIC is already in use");
		}if(used == 2){
			out.print("NIC is valid");
		}
			
		used = 0;
	}
}