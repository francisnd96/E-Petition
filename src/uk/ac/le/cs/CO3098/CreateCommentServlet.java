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

public class CreateCommentServlet extends HttpServlet {

	private static String host = "mysql.mcscw3.le.ac.uk";
	private static String database = "fnd1";
	private static String username = "fnd1";
	private static String password = "eebahngo";
	public List<Petition> petitions = new ArrayList<Petition>();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Get user session info, if not logged in send to unauthorised access page
		HttpSession session = request.getSession(true);
		User u = (User) session.getAttribute("User");

		if (u != null) {
		}else{
			response.sendRedirect("../regError.jsp?errorid=5");
			return;
		}
		
		String id = request.getParameter("id");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String conn_string = "jdbc:mysql://" + host + "/" + database;
			Connection connect = DriverManager.getConnection(conn_string, username, password);
			String query = "select * from PETITIONS WHERE ID = ?";
			PreparedStatement ps = connect.prepareStatement(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Petition p = new Petition();
				p.setId(rs.getInt("id"));
				p.setTitle(rs.getString("title"));
				p.setContent(rs.getString("content"));
				p.setDate(rs.getDate("date"));
				p.setCreator(rs.getString("creator"));
				p.setSign(rs.getInt("sign"));

				petitions.add(p);
			}
			ps.close();
			connect.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("petitions", petitions);
		request.getRequestDispatcher("/commentPetition.jsp").forward(request, response);
		petitions.clear();
	}


}
