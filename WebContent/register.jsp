<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Register</title>
</head>

<body>
	<h1>Registration Form</h1>
		<form name = "myform" id = "myform" action="${pageContext.request.contextPath}/servlets/Registration" method="post">
			<table style="with: 50%">
				<tr>
					<td>Email</td>
					<td><input type="text" name="email" /></td>
				</tr>
				<tr>
					<td>First Name</td>
					<td><input type="text" name="firstName" /></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><input type="text" name="lastName" /></td>
				</tr>
				<tr>
					<td>Date of Birth</td>
					<td><input type="text" name="dob" /></td>
				</tr>
				<tr>
					<td>1st line of Address</td>
					<td><input type="text" name="address" /></td>
				</tr>
				<tr>
					<td>Postcode</td>
					<td><input type="text" name="postcode" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td>National Identity Code</td>
					<td><input type="text" name="nic" id = "nic" /></td>
				</tr>
			</table>
			<br />
			<input type="submit" value="Register" />
		</form>
		<p id = "ajax"></p>
		<p>Already have an account. Click to Login</p>
			<a href="${pageContext.request.contextPath}/login.jsp">Login</a>
		
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$('#myform').keyup(function(e) {
            $.ajax({
            	type: "GET",
            	url: "${pageContext.request.contextPath}/servlets/NICCheck?nic="+$("#nic").val(),
            	success:function(result){
            		$("#ajax").html(result)
            	}		
            })
		})
	});
</script>

</html>
