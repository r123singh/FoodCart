<%@page import="com.foodvilla.restaurant.RestaurantBean"%>
<%@page import="com.foodvilla.restaurant.RestaurantImageBean"%>
<%@page import="com.foodvilla.restaurant.RestaurantImageMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/font-awesome.min.css" type="text/css" rel="stylesheet" />
  <link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet" />
<title>Restaurant Image Links</title>
</head>
<body>
	<center>
		<table align="center" cellspacing="5" border="2" cellpadding="10"
			id="restaurantimagetable">
			<tr>
				<th>Restaurant</th>
				<th>Images Url</th>
				<th>Display Image</th>

			</tr>
			<%
				RestaurantImageBean restaurantimagebean=(RestaurantImageBean)(session.getAttribute("restaurantimagerefs"));
						ArrayList <RestaurantImageMap> imagereferences=restaurantimagebean.getImageMaps(); 
											for(int i=0 ; i< imagereferences.size();i++){
			%>
			<tr>
				<td><%=restaurantimagebean.getRestaurantname()%></td>
				<td><a target="_blank"
					href="https://maps.googleapis.com/maps/api/place/photo?key=AIzaSyATo3r84gKzn05B7lgBzCn61Om-G_M90IA&maxwidth=200&photoreference=<%=imagereferences.get(i).getImagereference()%>&placeid=<%=imagereferences.get(i).getRestaurantplaceid()%>">Restaurant
						<%=restaurantimagebean.getRestaurantplaceid()%>Image Link -<%=i%></a></td>
				<td><input type="image"
					src="https://maps.googleapis.com/maps/api/place/photo?key=AIzaSyATo3r84gKzn05B7lgBzCn61Om-G_M90IA&maxwidth=200&photoreference=<%=imagereferences.get(i).getImagereference()%>&placeid=<%=imagereferences.get(i).getRestaurantplaceid()%>"></td>
			</tr>
			<%
				}
			%>
		</table>
		<br>
		<form action="UpdateRestaurantImageLinkServlet" method="get" target="_blank">
			<input type="text"
				value="<%=restaurantimagebean.getRestaurantplaceid()%>"
				name="placeid" /> <input type="text" value="paste image link"
				name="placeimageurl" /> <input type="submit" value="Update link" />
		</form>
	</center>
</body>
</html>