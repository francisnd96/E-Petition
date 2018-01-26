<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Login</title>

</head>
<body>


	<h1>Welcome to E-Petition</h1>
	<h2>Login Here</h2>


	<form method="POST" id="myform" name="myform"
		action="${pageContext.request.contextPath}/servlets/Login">
		<table border="0">
			<tr>
				<td>Email</td>
				<td><input type="text" name="email" id="email"
					value="${user.email}" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" id="password"
					value="${user.pword}" /></td>
			</tr>
		</table>
		<p id = "ajax"></p>
		<br /> <input type="submit" value="Submit" />

	</form>
	<p>Don't have an account. Click to Register</p>
	<a href="${pageContext.request.contextPath}/register.jsp">Register</a>

</body>

<script type="text/javascript">
	$(document).ready(function() {
		$('#myform').submit(function(e) {
			var email = $('#email').val();
			var password = $('#password').val();
            if(email == "" && password == ""){
                alert("Email and Password required");
                return;
            }
			if(email == ""){
                $('#ajax');
                alert("Email is required");
                return;
            }
            if(password == ""){
                $('#ajax');
                alert("Password is required");
                return;
            }
		})
	});
</script>
</html>