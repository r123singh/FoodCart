<%@page import="com.foodvilla.menu.MenuItemBean"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu-Home</title>
</head>

<script type="text/javascript">
	function addItemstoCart(id) {
		var rowIndex = 'Row-'+ id;
		var table = document.getElementById("menutable");
		var itemName = table.rows[rowIndex].cells[0].innerText;
		var itemPrice = table.rows[rowIndex].cells[1].innerText;
		var basket = document.getElementById("basket");
		basket.appendChild(document.createTextNode(itemName - itemPrice));
	}
</script>

<body>

	<form action="Checkout.jsp">
		<table align="center" cellspacing="5" border="2" cellpadding="10"
			id="menutable">
			<tr>
				<th>Item</th>
				<th>Price</th>
				<th>Select</th>
			</tr>
			<%
				Vector <MenuItemBean> menuItems=((Vector<MenuItemBean>)(session.getAttribute("MenuBeans"))); 
								for(int i=0 ; i< menuItems.size();i++){
			%>
			<tr id='Row-<%=i%>'>
				<td><%=menuItems.get(i).getMenuitemname()%></td>
				<td><%=menuItems.get(i).getMenuitemprice()%></td>
				<td><input type="button" name="selected" id=<%=i%>
					onclick="addItemstoCart(this.id)"></td>
			</tr>
			<%
				}
			%>
			<tr>
				<td><input type="submit" value="Checkout" /></td>
			</tr>
		</table>
	</form>
	<div class="container" align="right">
		<label> Your Order</label>
		<div class="container">
			<label>Add Items into your basket</label>
			<div id="basket"></div>
		</div>
	</div>
</body>
</html>