<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.model.Flight"%>
<!DOCTYPE html>
<html>
<style>
table, th, td {
	border: 1px solid black;
}
</style>
<body>

	<h2>Flights</h2>

	<table style="width: 100%">
		<tr>
			<th>AIRLINE NAME</th>
			<th>FLIGHT NAME</th>
			<th>SOURCE</th>
			<th>DESTINATION</th>
			<th>TRAVEL DATE</th>
			<th>NO OF PERSONS</th>
			<th>TICKET PRICE</th>
		</tr>
		<%
		// retrieve your list from the request, with casting 
		List<Flight> list = (ArrayList<Flight>) getServletConfig().getServletContext().getAttribute("AdminFlightList");

		for (Flight flightObj : list) {
		%>

		<tr>
			<td><%=flightObj.getAirlineName()%></td>
			<td><%=flightObj.getFlightName()%></td>
			<td><%=flightObj.getSourcePlace()%></td>
			<td><%=flightObj.getDestPlace()%></td>
			<td><%=flightObj.getDateOfTravel()%></td>
			<td><%=flightObj.getPersonNumber()%></td>
			<td><%=flightObj.getTicketPrice()%></td>
		</tr>

		<%
		}
		%>
	</table>

</body>
</html>