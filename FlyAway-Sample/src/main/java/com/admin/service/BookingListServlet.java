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
import com.model.Bookings;
import com.model.Flight;

/**
 * Servlet implementation class BookingListServlet
 */
@WebServlet("/BookingListServlet")
public class BookingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookingListServlet() {
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
			String s = "SELECT u.name,\r\n" + "a.AirlineName,\r\n" + "    f.flightname,\r\n"
					+ "    bd.NumberOfPerson,\r\n" + "    bd.AmtPaid,\r\n" + "    bd.TransactionNumber,\r\n"
					+ "    bd.Source,\r\n" + "    bd.Destination,\r\n" + "    bd.flydate\r\n"
					+ "FROM bookingdetails as bd inner join flight as f on bd.flightid=f.FlightId inner join user as u on bd.userid=u.userid inner join airlines as a on f.IdAirline=a.AirlineId\r\n";
			
			PreparedStatement statement1 = jdbcConnection.prepareStatement(s);

			ResultSet resSet = statement1.executeQuery();

			List<Bookings> bookingsList = new ArrayList<>();
			while (resSet.next()) {

				Bookings bookObj = new Bookings();
				bookObj.setUserName(resSet.getString("username"));
				bookObj.setAirlineName(resSet.getString("airlinename"));
				bookObj.setFlightName(resSet.getString("flightname"));
				bookObj.setTravelDate(resSet.getString("flydate"));
				bookObj.setSrcPlace(resSet.getString("source"));
				bookObj.setDestPlace(resSet.getString("Destination"));
				bookObj.setNoOfPersons(resSet.getInt("noofpersons"));
				bookObj.setAmtPaid(resSet.getDouble("amtpaid"));
				bookingsList.add(bookObj);
			}
			statement1.close();

			obj.disconnect();

//			getServletConfig().getServletContext().setAttribute("AdminBookingsList", bookingsList);
//			response.sendRedirect("adminviewbookings.jsp");
			
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.setAttribute("adminLoginEmail", session.getAttribute("adminLoginEmail"));
				getServletConfig().getServletContext().setAttribute("AdminBookingsList", bookingsList);
				response.sendRedirect("adminviewbookings.jsp");
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
