<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard jsp</title>
</head>
<body>
<% if (session.getAttribute("adminLoginEmail") == null) { %>
    <form action="AdminLoginServlet" method="post"> 
		<input type="submit" value="Login">
	</form>
<% } else {%>
    <form action="UserListServlet" method="post"> 
		<input type="submit" value="Users">
	</form>
	<form action="FlightListServlet" method="post"> 
		<input type="submit" value="Flights">
	</form>
	<form action="BookingListServlet" method="post"> 
		<input type="submit" value="Bookings">
	</form>
	<form action="AdminLogoutServlet" method="post"> 
		<input type="submit" value="Logout">
	</form>
<% } %>
</body>
</html>