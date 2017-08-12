<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Order</title>
</head>
<body>
<form action="CreateJsonOrderServlet" method="post" title="Book Order">
<label>Restaurant</label><input type="text" name="restaurant"/><br/>
<label>Restaurant Address</label><input type="text" name="restAddress"/><br/>
<label>Delivery Address</label><input type="text" name="deliveryAddress"/><br/>
<label>Item-1</label><input type="text" name="itemone"/><br/>
<label>Item1-description</label><input type="text" name="itemoneDescription"/><br>
<label>Item1-quantity</label><input type="text" name="itemoneQty"/><br>
<label>Item1-remarks</label><input type="text" name="itemoneRmk"/><br>
<label>Item1-price</label><input type="text" name="itemonePrice"/><br>

<label>Item-2</label><input type="text" name="itemtwo"/><br>
<label>Item2-description</label><input type="text" name="itemtwoDescription"/><br>
<label>Item2-quantity</label><input type="text" name="itemtwoQty"/><br>
<label>Item2-remarks</label><input type="text" name="itemtwoRmk"/><br>
<label>Item2-price</label><input type="text" name="itemtwoPrice"/><br>
<label>price</label><input type="text" name="totalprice" /><br>
<label>tax</label><input type="text" name="totaltax"/><br>

<input type="submit" value="submit" />

</form>

</body>
</html>