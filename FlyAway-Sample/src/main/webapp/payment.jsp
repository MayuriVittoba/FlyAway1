<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.Flight"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment</title>
</head>
<body>
<p>Make payment</p>
	<form action="SaveBookingServlet" method="post">
		
		<%
		Flight flightObject= (Flight)getServletConfig().getServletContext().getAttribute("AppContextFlightDetail");
		out.println(flightObject.getAirlineName());%><br><%
		out.println(flightObject.getFlightName());%><br><%
		out.println(flightObject.getDestPlace());%><br><%
		out.println(flightObject.getSourcePlace());%><br><%
		out.println(flightObject.getDateOfTravel());%><br><%
		out.println(flightObject.getTicketPrice());%><br><%
		out.println(flightObject.getPersonNumber());%><br>
		Enter Card Number:<input type="number" name="amount"><br>
		<input type="submit" value="Pay & Book">
	</form>
</body>
</html>