<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.model.Flight"%>

<!DOCTYPE html>
<html>
<head>
<title>List of Flights</title>
</head>
<body>
<p>Available Flights</p>
	<%
	// retrieve your list from the request, with casting 
	/*List<Flight> list = (ArrayList<Flight>) request.getAttribute("SearchServletDetails");*/
	List<Flight> list = (ArrayList<Flight>)getServletConfig().getServletContext().getAttribute("SearchServletDetails");
	for (Flight f_obj : list) {
	%>
	<form action="" method="post">
		<input type="text" name="flightId" value=<%= f_obj.getFlightId() %> readonly="readonly" style="border: 1px"><br>
		<input type="text" name="flightName" value=<%= f_obj.getFlightName() %> readonly="readonly" style="border: 1px"> <br>
		<input type="text" name="airlineName" value=<%= f_obj.getAirlineName() %> readonly="readonly" style="border: 1px"><br>
		<input type="text" name="travelDate" value=<%= f_obj.getDateOfTravel() %> readonly="readonly" style="border: 1px"><br>
		<input type="text" name="srcPlace" value=<%= f_obj.getSourcePlace() %> readonly="readonly" style="border: 1px"><br>
		<input type="text" name="destPlace" value=<%= f_obj.getDestPlace() %> readonly="readonly" style="border: 1px"><br>
		<input type="text" name="persons" value=<%= f_obj.getPersonNumber() %> readonly="readonly" style="border: 1px"><br>
		<input type="text" name="ticketPrice" value=<%= f_obj.getTicketPrice() %> readonly="readonly" style="border: 1px"><br>
		 
		<input type="submit" name="flight" value="Proceed to Book">
	</form>
	<%
	if (request.getParameter("flight") != null) {
	%>
	
	<jsp:useBean id="flight" class="com.model.Flight" scope="request"></jsp:useBean>
	<jsp:setProperty property="*" name="flight"/>
	<jsp:forward page="BookingServlet"></jsp:forward>
	<%
	}
	}
	%>


</body>
</html>
