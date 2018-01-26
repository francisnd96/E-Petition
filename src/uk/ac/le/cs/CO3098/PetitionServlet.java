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

public class PetitionServlet extends HttpServlet {

	private static String host = "mysql.mcscw3.le.ac.uk";
	private static String database = "fnd1";
	private static String username = "fnd1";
	private static String password = "eebahngo";
	public List<Petition> petitions = new ArrayList<Petition>();
	String email;
	String firstName;
	String lastName;
	String id;
	int isMP;
	String fullName;
	int noRecords;
	boolean hasSigned;
	int deletedOldRow;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		User u = (User) session.getAttribute("User");

		if (u != null) {
			isMP = u.getIsMP();
			firstName = u.getFirstName();
			lastName = u.getLastName();
			email = u.getEmail();
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
			String query2 = "SELECT * FROM COMMENTS WHERE PETITIONID = ?";
			String query3 = "SELECT * FROM NIC_RECORDS";
			String query4 = "DELETE FROM PETITIONS WHERE DATE < NOW() - INTERVAL 7 DAY AND SIGN < 0.2*?";
			PreparedStatement ps = connect.prepareStatement(query);
			PreparedStatement ps2 = connect.prepareStatement(query2);
			PreparedStatement ps3 = connect.prepareStatement(query3);
			PreparedStatement ps4 = connect.prepareStatement(query4);
			
			ResultSet rs3 = ps3.executeQuery();
			while (rs3.next()) {
				noRecords++;
			}
			ps4.setInt(1, noRecords);
			deletedOldRow = ps4.executeUpdate();
			ps2.setString(1, id);
			ResultSet rs = ps.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			
			


				while (rs.next()) {
					Petition p = new Petition();
					p.setId(rs.getInt("id"));
					p.setTitle(rs.getString("title"));
					p.setContent(rs.getString("content"));
					p.setDate(rs.getDate("date"));
					p.setCreator(rs.getString("creator"));
					p.setSign(rs.getInt("sign"));
					ifRecordExists(rs.getInt("id"), email);
					p.setIsSigned(hasSigned);
					p.setNoComments(rs.getInt("comments"));

					petitions.add(p);
				}

				
				
				ps.close();
				connect.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("petitions", petitions);
		request.setAttribute("isMP", isMP);
		request.setAttribute("fullName", fullName);
		request.setAttribute("noRecords", noRecords);
		request.setAttribute("deletedOldRow", deletedOldRow);
		request.getRequestDispatcher("/petitionList.jsp").forward(request, response);
		petitions.clear();
		noRecords = 0;
	}

	public void ifRecordExists(int id, String name) throws ClassNotFoundException, SQLException {
		String signQuery = "SELECT * FROM SIGNED WHERE CITIZEN = ? AND PETITIONID = ? ";
		Class.forName("com.mysql.jdbc.Driver");
		String conn_string = "jdbc:mysql://" + host + "/" + database;
		Connection connect = DriverManager.getConnection(conn_string, username, password);
		PreparedStatement ps1 = connect.prepareStatement(signQuery);
		ps1.setString(1, email);
		ps1.setString(2, String.valueOf(id));
		ResultSet rs1 = ps1.executeQuery();

		if (!rs1.next()) {
			hasSigned = false;
		} else
			hasSigned =  true;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		User u = (User) session.getAttribute("User");

		if (u != null) {
			email = u.getEmail();
			firstName = u.getFirstName();
			lastName = u.getLastName();
		}

		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		if (request.getParameter("button") != null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				// loads mysql driver

				String conn_string = "jdbc:mysql://" + host + "/" + database;
				Connection connect = DriverManager.getConnection(conn_string, username, password);

				String id = request.getParameter("id");
				String query = "UPDATE PETITIONS SET SIGN = SIGN+1 WHERE ID = ?";
				String query2 = "insert into SIGNED values(?,?)";

				PreparedStatement ps = connect.prepareStatement(query);
				PreparedStatement ps2 = connect.prepareStatement(query2);

				ps2.setString(1, email);
				ps2.setString(2, id);

				ps.setString(1, id);

				ps.executeUpdate();
				ps2.executeUpdate();
				// execute it on test database
				ps.close();
				ps2.close();
				connect.close();
				doGet(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}

		// validate given input

		if (request.getAttribute("skip") != null) {
			doGet(request, response);
		} else if (request.getAttribute("doComment") != null) {
			doGet(request, response);
		} else {

			try {
				Class.forName("com.mysql.jdbc.Driver");
				// loads mysql driver

				String conn_string = "jdbc:mysql://" + host + "/" + database;
				Connection connect = DriverManager.getConnection(conn_string, username, password);

				String query = "insert into PETITIONS values(?,?,?,?,?,?,?)";

				PreparedStatement ps = connect.prepareStatement(query);

				Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

				ps.setString(1, null);
				ps.setString(2, title);
				ps.setString(3, content);
				ps.setTimestamp(4, date);
				ps.setString(5, firstName + " " + lastName);
				ps.setInt(6, 0);
				ps.setInt(7, 0);

				ps.executeUpdate(); // execute it on test database

				ps.close();
				connect.close();
				doGet(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}