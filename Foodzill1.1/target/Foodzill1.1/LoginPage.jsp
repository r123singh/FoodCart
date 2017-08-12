<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" type="text/css" rel="stylesheet" />
  <link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet" />
 
<title>Food Delivery</title>
</head>
<body>
<div class="menu" align="right">
<a href="userRegister.jsp">Register</a>
<a href="/Foodzill1.1/FetchAllRestaurants">View Restaurants</a>
</div>
	
	
			<div class="splash-head"><label>Order Now</label></div>
			<form action="UserLoginServlet" title="Login">
				<label > Username</label> <input type="text" name="username" ><br>
				<label >Password</label> <input type="password" name="password" ><br><br>
				<input type="submit" value="Login" class="pure-button" />
			</form>
	
</body>
</html>