<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.model.Bookings"%>
<!DOCTYPE html>
<html>
<style>
table, th, td {
	border: 1px solid black;
}
</style>
<body>

	<h2>Bookings</h2>

	<table style="width: 100%">
		<tr>
			<th>NAME</th>
			<th>ADDRESS</th>
			<th>CONTACT</th>
		</tr>
		<%
// retrieve your list from the request, with casting 
List<Bookings> list = (ArrayList<Bookings>) getServletConfig().getServletContext().getAttribute("AdminBookingsList");

for(Bookings bookingsObj : list) {%>

		<tr>
			<td><%=bookingsObj.getUserName() %></td>
		</tr>

		<%} %>
	</table>

</body>
</html>