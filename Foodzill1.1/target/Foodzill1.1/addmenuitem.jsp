<%@page import="com.foodvilla.restaurant.Restaurant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.foodvilla.restaurant.RestaurantService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/font-awesome.min.css" type="text/css" rel="stylesheet" />
  <link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet" />
<title>Add MenuItem</title>
</head>
<body>
	<form action="AddNewMenuItemServlet" method="post" title="Add New Menu Item">
		<label>Name</label><input type="text" name="menutitemname" /><br />
		<label>Description</label><input type="text" name="menuitemdescription" /><br />
		 <label>Price</label><input	type="text" name="menuitemprice" /><br />
		 <label>Category</label><input	type="text" name="menuitemcategory" /><br />
		  <label>Packaging Charge</label><input type="text" name="menuitempackagingcharges" /><br>
		<label>Tax Charge</label><input type="text" name="menuitemtaxcharges" /><br>
		<label>Discount Charge</label><input type="text" name="menuitemdiscountcharges" /><br>
		<label>Restaurant</label><select name="menuitemrestaurant"	id="menuitemrestaurant">
			<option value=0>Select Restaurant</option>
			<%
				ArrayList<Restaurant> restaurants= RestaurantService.fetchallrestaurantlist();
					for(Restaurant restaurant:restaurants)
					{
					%><option value=<%=restaurant.getId()%>><%=restaurant.getName() %></option>			
					<%}%>
		</select><br> 
		<label>Category Image</label><input type="text"	name="menuitemcategoryimage" /><br> 
		<label>Vegetarian/Non-Vegetarian</label><input name="menuitemvegetarian" /><br> 
		<input type="submit" value="Save" />
	</form>

</body>
</html>