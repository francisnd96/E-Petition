<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Petition</title>
</head>
<style>
div.ex {
	text-align: right width:300px;
	padding: 10px;
	border: 5px solid grey;
	margin: 0px
}
</style>
<body>
	<h1>Edit Petition</h1>
	<div class="ex">
		<form action="${pageContext.request.contextPath}/servlets/ChangePetitionServlet" method="post">
			<table style="with: 50%">
			<c:forEach var="petition" items="${petitions}">
			<input type= "hidden" value = "${petition.id}" name = "id">
				<tr>
					<td>Title</td>
					<td><input type="text" name="title" value="${petition.title}" /></td>
				</tr>
				<tr>
					<td>Content</td>
					<td><input type="text" name="content" value = "${petition.content}" /></td>
				</tr>
				</c:forEach>
			</table>
			<input type="submit" value="change" name = "buttonChange" />
		</form>
		<br>
		
	</div>
</body>
</html>