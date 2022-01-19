<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.Flight"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Booking</title>
</head>
<body>
<p>Booking Summary</p>
	<form action="index.html" method="post">
		<input type="submit" value="Book Again"><br>
		<%
		Flight flightObject = (Flight) getServletConfig().getServletContext()
				.getAttribute("AppContextFlightDetail");
		
		out.println(flightObject.getAirlineName());%><br><%
		out.println(flightObject.getFlightName());%><br><%
		out.println(flightObject.getSourcePlace());%><br><%
		out.println(flightObject.getDestPlace());%><br><%
		out.println(flightObject.getDateOfTravel());%><br><%
		out.println(flightObject.getTicketPrice());%><br><%
		out.println(flightObject.getPersonNumber());%><br>
	
	</form>
</body>
</html>