<%@page import="com.foodvilla.restaurant.RestaurantBean"%>
<%@page import="com.foodvilla.restaurant.Restaurant"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/font-awesome.min.css" type="text/css" rel="stylesheet" />
  <link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet" />
<title>Sync Restaurant</title>
</head>
<body>
<%RestaurantBean restaurantbean=(RestaurantBean)session.getAttribute("restaurantbean");
%>
	<form action="SynServerRestaurants" method="get" title="Sync Restaurant" target="_blank">
		<input type="text" name="pagetoken" value="<%=restaurantbean.getPagetoken() %>">
		<input type="text" name="location"/>
		  <input type="submit" value="Sync" />
	</form>
	<br>
	<table align="center" cellspacing="5" border="2" cellpadding="10"
		id="restauranttable">
		<tr>
			<th>Name</th>
			<th>Available</th>
			<th>Address</th>
			<th>Imageurl</th>
			<th>Active</th>
						<th>Action</th>
						<th>Action</th>
			
		</tr>
		<%
			ArrayList <Restaurant> restaurants=restaurantbean.getRestaurants(); 
								for(int i=0 ; i< restaurants.size();i++){
		%>
		<tr>
			<td><%=restaurants.get(i).getName()%></td>
			<td><%=restaurants.get(i).getOpennow()%></td>
			<td><%=restaurants.get(i).getAddress()%></td>
			<td><img src="<%=restaurants.get(i).getPlaceimageurl()%>"> </td>
			<td><%=restaurants.get(i).getActive()%></td>
			<td><a target="_blank" href="/Foodzill1.1/FetchRestaurantImageServlet?placeid=<%=restaurants.get(i).getPlaceid() %>">Update_Image_Ref</a></td>
			<td><a target="_blank" href="/Foodzill1.1/ViewrestaurantimagelinksServlet?placeid=<%=restaurants.get(i).getPlaceid() %>& restaurant_name=<%=restaurants.get(i).getName()%>">View_Image_links</a></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>