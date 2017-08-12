<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Restaurant</title>
</head>
<body>
<form action="CreateRestaurantServlet" method="post" title="Add Restaurant">
<label>Restaurant Name</label><input type="text" name="restaurantname"/><br/>
<label>Restaurant Address</label><input type="text" name="restaurantaddress"/><br/>
<label>Food Categories</label><input type="text" name="restaurantfoodcategory" /><br/>
<label>Delivery mode</label><input type="text" name="restaurantdeliverymode"/><br/>
<label>payment mode</label><input type="text" name="restaurantpaymentmode"/><br>
<label>Minimum order</label><input type="text" name="restaurantminimumordercharges"/><br>
<label>Delivery Estimate Time</label><input type="text" name="restaurantestimateddeliverytime"/><br>
<label>Start time</label><input type="text" name="restaurantstarttime"/><br>
<label>Close Time</label><input type="text" name="restaurantclosetime"/><br>
<label>Delivery Charges</label><input type="text" name="restaurantdeliverycharges"/><br>
<label>Display Image</label><input type="text" name="restaurantdisplayimage"/><br>
<label>Location</label><input type="text" name="restaurantlocation"/><br>

<input type="submit" value="submit" />

</form>

</body>
</html>