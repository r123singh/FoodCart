<%@page import="com.foodvilla.restaurant.RestaurantImageBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Image</title>
</head>
<body>
<center>
	<%
			RestaurantImageBean staticimagebean = ((RestaurantImageBean) (session.getAttribute("staticimagelink")));
	
		%>
		<a href="<%=staticimagebean.getStaticimageurl()%>">Link</a>
</center>
</body>
</html>