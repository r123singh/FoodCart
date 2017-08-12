<%@page import="com.foodvilla.login.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registeration Done!</title>
</head>
<body>

<center>
		<%
			UserBean registerUserBean = ((UserBean) (session.getAttribute("registeredSessionUser")));
		%>
		You Are Now Registered-Proceed to Login<br>
		 First Name: <%=registerUserBean.getFirstName() %><br>
		 Last Name:<%=registerUserBean.getLastName() %><br>
		User Name: <%=registerUserBean.getUsername() %><br>
		Password: <%=registerUserBean.getPassword() %><br>
		 Mobile: <%=registerUserBean.getMobile() %><br>
	</center>
</body>
</html>