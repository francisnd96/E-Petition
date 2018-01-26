<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="uk.ac.le.cs.CO3098.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
HttpSession se=request.getSession();
User u = null;

if(se.getAttribute("User")!=null){
 	u=(User)se.getAttribute("User");
}else{
	response.sendRedirect("regError.jsp?errorid=5");

}%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Petition</title>
</head>
<body>
	<h1>Create Petition</h1>
	<div class="ex">
		<form action="${pageContext.request.contextPath}/servlets/PetitionServlet" method="post">
			<table style="with: 50%">
				<tr>
					<td>Title</td>
					<td><input type="text" name="title" /></td>
				</tr>
				<tr>
					<td>Content</td>
					<td><input type="text"  name="content" /></td>
				</tr>
			</table>
			<br />
			<input type="submit" value="Create" />
		</form>
		<br>
		
	</div>
			<a href ="${pageContext.request.contextPath}/servlets/PetitionServlet">View Petitions</a>
			<br /><br />
			<a href="${pageContext.request.contextPath}/servlets/Logout">Logout</a>
</body>
</html>