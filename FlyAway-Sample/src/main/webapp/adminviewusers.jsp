<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.model.User"%>
<!DOCTYPE html>
<html>
<style>
table, th, td {
	border: 1px solid black;
}
</style>
<body>

	<h2>Users</h2>

	<table style="width: 100%">
		<tr>
			<th>NAME</th>
			<th>ADDRESS</th>
			<th>CONTACT</th>
		</tr>
		<%
// retrieve your list from the request, with casting 
List<User> list = (ArrayList<User>) getServletConfig().getServletContext().getAttribute("AdminUserList");

for(User userObj : list) {%>

		<tr>
			<td><%=userObj.getUserName() %></td>
			<td><%=userObj.getUserAddr() %></td>
			<td><%=userObj.getUserPhone() %></td>
		</tr>

		<%} %>
	</table>

</body>
</html>