<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<html>
<head><title>Error</title></head>
<body>
<style>
form {
 width: 500px;
 overflow:hidden;}
label {
 clear: both;
 float: left;
 width: 40%;}
input {
 float: left;
 width: 55%;}
</style>
<h1>Error</h1>
<%
String errorMsg="Access denied";
String errorid=request.getParameter("errorid");
if(errorid!=null){
	if(errorid.equals("1")){
		errorMsg+= " - Email already exists in database.";
	}else if(errorid.equals("2")){
		errorMsg+=" - NIC is either not in the database or is in use";
	}else if(errorid.equals("4")){
		errorMsg+=" - Please fill in all fields";
	}else if(errorid.equals("5")){
		errorMsg+=" - Unauthorised access. Please login";

	}else{
		errorMsg+=" - You are not authorized to access this page. Wrong email password combination";
	}
}else{
	 errorMsg+=" - An unexpected error has occurred.";
}
%>
<div>
	<label for="link"><%=errorMsg%></label>
</div>
<br /><br /><br />
<p>Please click here to <a href="${pageContext.request.contextPath}/register.jsp">Register</a></p>
<p>Please click here to <a href="${pageContext.request.contextPath}/login.jsp">Login</a></p>
</body></html>



