<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function alertName() {
		alert("A petition that is 7 days old without the required signatures has been deleted");
	}
	var old = '${deletedOldRow}';
	console.log(old);
	if (old == 1) {
		window.onload = alertName;
	}
</script>
<meta charset="UTF-8">
<title>Petitions</title>
</head>
<body>



	<h1>Petition List</h1>


	<table border="1" cellpadding="5" cellspacing="1">
		<tr>
			<th>Petition ID</th>
			<th>Petition Name</th>
			<th>Content</th>
			<th>Date Created</th>
			<th>Creator</th>
			<th>Signatures</th>
			<th>Comments</th>
		</tr>
		<c:forEach var="petition" items="${petitions}">
			<tr>
				<td><c:out value="${petition.id}" /></td>
				<td><c:out value="${petition.title}" /></td>
				<td><c:out value="${petition.content}" /></td>
				<td><c:out value="${petition.date}" /></td>
				<td><c:out value="${petition.creator}" /></td>
				<td><c:out value="${petition.sign}" /></td>
				<td><c:out value="${petition.noComments}" />
					<form
						action="${pageContext.request.contextPath}/servlets/RetrieveCommentsServlet">
						<input type="hidden" name="id" value="${petition.id}"> <input
							TYPE="submit" ID="BUTTON" VALUE="view comments"
							name="commentButton">
					</form></td>
				<td>
					<form id="myform"
						action="${pageContext.request.contextPath}/servlets/PetitionServlet"
						method="post">

						<c:if test="${petition.isSigned == false}">
							<input type="hidden" name="id" value="${petition.id}">
							<INPUT TYPE="submit" ID="BUTTON" VALUE="Sign" name="button">
						</c:if>

						<c:if test="${petition.isSigned == true}">
						Already signed
						</c:if>

					</form>
				</td>
				<td>
					<form
						action="${pageContext.request.contextPath}/servlets/EditPetitionServlet">

						<c:if test="${fullName == petition.creator}">
							<c:if test="${petition.isSigned == false}">
								<input type="hidden" name="id" value="${petition.id}">
								<INPUT TYPE="submit" ID="BUTTON" VALUE="Edit" name="button">
							</c:if>

							<c:if test="${petition.isSigned == true}">
						This Petition has been signed and cannot be edited
							</c:if>

						</c:if>

						<c:if test="${fullName != petition.creator}">
						You can only edit your own petitions
						</c:if>

					</form>
				</td>
				<td>
					<form
						action="${pageContext.request.contextPath}/servlets/CreateCommentServlet">

						<c:if test="${petition.sign >= 0.2*noRecords}">
							<c:if test="${isMP == 1}">
								<input type="hidden" name="id" value="${petition.id}">
								<input TYPE="submit" ID="BUTTON" VALUE="Comment"
									name="commentButton">
							</c:if>

							<c:if test="${isMP == 0}">
						Only MP's are allowed to comment
						</c:if>
						</c:if>

						<c:if test="${petition.sign < 0.2*noRecords}">
							<c:if test="${isMP == 1}">
								You can only comment when 20% of the population has signed this petition
							</c:if>

							<c:if test="${isMP == 0}">
						Only MP's are allowed to comment
						</c:if>

						</c:if>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br />

	<a href="${pageContext.request.contextPath}/createPetition.jsp">Create
		Petition</a>

	<br />
	<c:if test="${isMP == 1}">
		<p>
			<a href="${pageContext.request.contextPath}/servlets/BarChartServlet">Petition
				Bar Chart</a> - You, as an MP, have access
		</p>
	</c:if>
	<c:if test="${isMP == 0}">
		<p>
			Petition Bar Chart - Only MP's have access
		</p>
	</c:if>
	<br />
	<a href="${pageContext.request.contextPath}/servlets/Logout">Logout</a>

</body>
</html>