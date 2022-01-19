package com.user.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DatabaseConnection;
import com.model.Flight;

/**
 * Servlet implementation class SearchServlet
 */

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String dateOfTravel = request.getParameter("dateOfTravel");
		String sourcePlace = request.getParameter("source");
		String destinationPlace = request.getParameter("destination");
		String noOfPersons = request.getParameter("personNum");
		boolean flightExist = false;

		try {
			DatabaseConnection obj = new DatabaseConnection();
			Connection jdbcConnection = obj.connect();

			String sql = "select f.flightid,a.airlinename,f.flightname,fd.noofpersons,fd.ticketprice from flightdetails as fd \r\n"
					+ "			inner join flight as f \r\n" + "			on fd.flyid=f.flightid \r\n"
					+ "			inner join sourcetable s \r\n" + "			on fd.srcid=s.srcid \r\n"
					+ "			inner join destinationtable as d \r\n" + "			on fd.destid=d.destid \r\n"
					+ "			inner join airlines as a\r\n" + "			on f.idairline=a.airlineid\r\n" + ""
					+ "			where fd.FlyDate='" + dateOfTravel + "' and s.srcname='" + sourcePlace
					+ "' and d.destname='" + destinationPlace + "' and NoOfPersons>=" + noOfPersons;

			Statement statement = jdbcConnection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			List<Flight> flightList = new ArrayList<>();
			while (resultSet.next()) {
				flightExist = true;
				Flight f_obj = new Flight();
				f_obj.setFlightId(resultSet.getInt("flightid"));
				f_obj.setAirlineName(resultSet.getString("airlinename"));
				f_obj.setFlightName(resultSet.getString("flightname"));
				f_obj.setPersonNumber(resultSet.getInt("noofpersons"));
				f_obj.setTicketPrice(resultSet.getDouble("ticketprice"));
				f_obj.setSourcePlace(sourcePlace);
				f_obj.setDestPlace(destinationPlace);
				f_obj.setDateOfTravel(dateOfTravel);

				flightList.add(f_obj);
			}

			resultSet.close();
			statement.close();
			obj.disconnect();

			if (flightExist) {
				getServletConfig().getServletContext().setAttribute("SearchServletDetails", flightList);
				response.sendRedirect("JSPflightlist.jsp");
			} else {
				response.getOutputStream().println("Flights not available");
				RequestDispatcher rd = request.getRequestDispatcher("index.html");
				rd.include(request, response);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
