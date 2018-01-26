<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="uk.ac.le.cs.CO3098.bean.*"%><% 
HttpSession se=request.getSession();
User u = null;

if(se.getAttribute("User")!=null){
 	u=(User)se.getAttribute("User");
}else{
	response.sendRedirect("regError.jsp?errorid=5");
}
if(u!=null){
%>
<html>
<head><title>User Page</title></head>
<body>

<h1>Welcome to E-Petition</h1>
<h2>Hello, <%= u.getFirstName() %>!</h2> 
<h3>As a valued member of Shangri-La you have a new way to influence what is debated 
in Parliament. <br />You can create a petition, sign petition, edit your own petitions and
if you're an MP you can comment on petitions.</h3>
<h3>Rules</h3>
<ul>
<li><h4>If a petition is over 7 days old and has less than the number of valid signatures (20% of the population)
it will be deleted</h4></li>
<li><h4>Once a petition has been signed, it cannot be edited</h4></li>
<li><h4>Only MP's can comment on petitions</h4></li>
<li><h4>MP's can only comment when 20% of the population has signed a petition</h4></li>
</ul>
<div>
<h1>Begin</h1>
<ul>
<li><h2><a href ="createPetition.jsp">Create Petition</a></h2></li>
<li><h2><a href ="./servlets/PetitionServlet">View Petitions</a></h2></li>
<li><h2><a href="./servlets/Logout">Logout</a></h2></li>
</ul>
</div>
</body></html>
<%}%>
