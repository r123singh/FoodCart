<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/main.css"
	type="text/css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/css/marketing.css" type="text/css" rel="stylesheet" />
	
<title>New User Registeration</title>
</head>
<body>

<a href="userRegister.jsp">Register</a>
	<a href="MenuPage.jsp">Menu</a>
	<a href="LoginPage.jsp">Login</a>
	<center>
		<form action="RegisterUserServlet" title="Registeration Form" class="form">
		<label>Register With Us!</label>
		
				<label>Please Enter Your First Name</label> <input type="text" name="firstname" /><label>Please Enter Your Last Name</label>
				<input type="text" name="lastname" /><label>Please Enter User Name </label><input
					type="text" name="username" /><label>Pleas Enter Password </label><input type="text"
					name="password" /><label> Mobile</label> <input type="text" name="mobile" />
				<br/><input type="submit" value="Submit" />
		</form>
	</center>
</body>
</html>