package com.admin.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DatabaseConnection;
import com.model.Flight;
import com.model.User;

/**
 * Servlet implementation class FlightListServlet
 */
@WebServlet("/FlightListServlet")
public class FlightListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FlightListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			DatabaseConnection obj = new DatabaseConnection("jdbc:mysql://localhost/mydb", "mayuri", "password");
			Connection jdbcConnection = obj.connect();
			String sql = "select a.airlinename,f.flightname,s.srcname,d.destname,fd.flydate,fd.noofpersons,fd.ticketprice from flightdetails as fd \r\n"
					+ "			inner join flight as f \r\n" + "			on fd.flyid=f.flightid \r\n"
					+ "			inner join sourcetable s \r\n" + "			on fd.srcid=s.srcid \r\n"
					+ "			inner join destinationtable as d \r\n" + "			on fd.destid=d.destid \r\n"
					+ "			inner join airlines as a\r\n" + "			on f.idairline=a.airlineid\r\n" + "";

		
			PreparedStatement statement1 = jdbcConnection.prepareStatement(sql);

			ResultSet resSet = statement1.executeQuery();

			List<Flight> flightList = new ArrayList<>();
			while (resSet.next()) {

				Flight flightObj = new Flight();
				flightObj.setAirlineName(resSet.getString(1));
				flightObj.setFlightName(resSet.getString(2));
				flightObj.setSourcePlace(resSet.getString(3));
				flightObj.setDestPlace(resSet.getString(4));
				flightObj.setDateOfTravel(resSet.getString(5));
				flightObj.setPersonNumber(resSet.getInt(6));
				flightObj.setTicketPrice(resSet.getDouble(7));

				flightList.add(flightObj);
			}

			statement1.close();
			obj.disconnect();

//			getServletConfig().getServletContext().setAttribute("AdminFlightList", flightList);
//			response.sendRedirect("adminviewflights.jsp");
			
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.setAttribute("adminLoginEmail", session.getAttribute("adminLoginEmail"));
				getServletConfig().getServletContext().setAttribute("AdminFlightList", flightList);
				response.sendRedirect("adminviewflights.jsp");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
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
