<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Comment</title>
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
	<h1>Comment Petition</h1>
	<div class="ex">
		<form action="${pageContext.request.contextPath}/servlets/UploadCommentServlet" method="post">
			<table style="with: 50%">
			<c:forEach var="petition" items="${petitions}">
			<input type= "hidden" value = "${petition.id}" name = "id">
			<input type= "hidden" value = "${petition.creator}" name = "creator">
			</c:forEach>
				<tr>
					<td>Comment</td>
					<td><input type="text" name="comment"/></td>
				</tr>
				
			</table>
			<input type="submit" value="Comment" name = "buttonChange" />
		</form>
		<br>
		
	</div>
</body>
</html>