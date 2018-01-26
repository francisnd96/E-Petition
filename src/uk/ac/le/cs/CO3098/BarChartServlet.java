package uk.ac.le.cs.CO3098;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uk.ac.le.cs.CO3098.bean.Petition;
import uk.ac.le.cs.CO3098.bean.SHA256HashGenerator;
import uk.ac.le.cs.CO3098.bean.User;

public class BarChartServlet extends HttpServlet {

	private static String host = "mysql.mcscw3.le.ac.uk";
	private static String database = "fnd1";
	private static String username = "fnd1";
	private static String password = "eebahngo";
	
	//Arraylist to store Petition Details
	public List<Petition> petitions = new ArrayList<Petition>();
	
	//Fields to store current user information
	String email;
	String firstName;
	String lastName;
	String id;
	int isMP;
	String fullName;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Retrieve user session information, if not logged in error page shown
		HttpSession session = request.getSession(true);
		User u = (User) session.getAttribute("User");

		if (u != null) {
			isMP = u.getIsMP();
			firstName = u.getFirstName();
			lastName = u.getLastName();
		}else{
		response.sendRedirect("../regError.jsp?errorid=5");
		return;
		}

		id = request.getParameter("id");
		fullName = firstName + " " + lastName;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String conn_string = "jdbc:mysql://" + host + "/" + database;
			Connection connect = DriverManager.getConnection(conn_string, username, password);
			String query = "SELECT * FROM PETITIONS";
			PreparedStatement ps = connect.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Petition p = new Petition();
				p.setId(rs.getInt("id"));
				p.setTitle(rs.getString("title"));
				p.setContent(rs.getString("content"));
				p.setDate(rs.getDate("date"));
				p.setCreator(rs.getString("creator"));
				p.setSign(rs.getInt("sign"));
				p.setNoComments(rs.getInt("comments"));

				petitions.add(p);
			}
			ps.close();
			connect.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//send Petition details to jsp
		request.setAttribute("petitions", petitions);
		request.getRequestDispatcher("/petitionBarChart.jsp").forward(request, response);
		petitions.clear();
	}
}