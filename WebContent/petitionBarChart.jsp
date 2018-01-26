<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>MP's Report</title>
</head>
<body>


	<h1>MP's Report</h1>
	<h3>Bar Chart</h3>

	<p style="color: red;">${errorString}</p>

	<table border="1" cellpadding="5" cellspacing="1">
		<tr>
			<th>Petition Name</th>
			<th>Signatures</th>
		</tr>
		<c:forEach var="petition" items="${petitions}">
			<tr>
				<td><c:out value="${petition.title}" /></td>
				<td><c:forEach begin = "1" end = "${petition.sign}" varStatus="loop">
				<c:out value="*" />
				</c:forEach>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br />

	<a href="${pageContext.request.contextPath}/createPetition.jsp">Create
		Petition</a>
		<br />
	<a href="${pageContext.request.contextPath}/servlets/PetitionServlet">View
		Petitions</a>
		<br /><br />
<a href="${pageContext.request.contextPath}/servlets/Logout">Logout</a>

</body>
</html>