<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Comments List</title>
</head>
<body>


	<h1>Comments</h1>


	<table border="1" cellpadding="5" cellspacing="1">
		<tr>
			<th>MP</th>
			<th>Comment</th>
		</tr>
		<c:forEach var="comment" items="${comments}">
			<tr>
				<td><c:out value="${comment.mp}" /></td>
				<td><c:out value="${comment.comment}" /></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<a href="${pageContext.request.contextPath}/createPetition.jsp">Create
		Petition</a>
	<br />
	<a href="${pageContext.request.contextPath}/servlets/PetitionServlet">View
		Petitions</a>
		<br /> <br />
			<a href="${pageContext.request.contextPath}/servlets/Logout">Logout</a>
		

</body>
</html>