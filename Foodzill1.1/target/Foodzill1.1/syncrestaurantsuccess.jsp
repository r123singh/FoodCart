<%@page import="com.foodvilla.restaurant.RestaurantBean"%>
<%@page import="com.foodvilla.login.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Syncing Done!</title>
</head>
<body>

	<center>
		<%
			RestaurantBean restaurantBean = ((RestaurantBean) (session.getAttribute("restaurantbean")));
		%>
		Page Token<br> Token:
		<%=restaurantBean.getPagetoken()%><br>
	</center>
</body>
</html>