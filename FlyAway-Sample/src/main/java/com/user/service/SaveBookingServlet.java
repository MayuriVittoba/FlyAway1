package com.user.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
 * Servlet implementation class SaveBookingServlet
 */
@WebServlet("/SaveBookingServlet")
public class SaveBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveBookingServlet() {
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
			insertUser();
			response.sendRedirect("bookingconfirmation.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void insertFlightBooking(int userid) throws SQLException {
		Flight fObj = (Flight) getServletConfig().getServletContext().getAttribute("AppContextFlightDetail");
		DatabaseConnection obj = new DatabaseConnection();
		Connection jdbcConnection = obj.connect();
		String s = "INSERT INTO bookingdetails(UserId,FlightId,NumberOfPerson,AmtPaid,TransactionNumber,Source,Destination,Flydate)VALUES(?,?,?,?,?,?,?,?)";

		PreparedStatement statement1 = jdbcConnection.prepareStatement(s);
		statement1.setInt(1, userid);
		statement1.setInt(2, fObj.getFlightId());
		statement1.setInt(3, fObj.getPersonNumber());
		statement1.setDouble(4, fObj.getPersonNumber() * fObj.getTicketPrice());
		statement1.setString(5, "123455");
		statement1.setString(6, fObj.getSourcePlace());
		statement1.setString(7, fObj.getDestPlace());
		statement1.setString(8, fObj.getDateOfTravel());

		statement1.close();
		obj.disconnect();
//		jdbcConnection = obj.connect();
//		String query = "update flightdetails set noofpersons=(noofpersons-" + fObj.getPersonNumber()
//				+ ") where flightid=" + fObj.getFlightId() + " and (noofpersons-" + fObj.getPersonNumber()
//				+ ")>0 and flydatetime='" + fObj.getDateOfTravel() + "'";

//		jdbcConnection = obj.connect();
//
//		statement1 = jdbcConnection.prepareStatement(query);
//		statement1.executeUpdate(query);
//		statement1.close();
//		obj.disconnect();
	}

	public void insertUser() throws SQLException {
		try {
			User userObj = (User) getServletConfig().getServletContext().getAttribute("userDetail");

			DatabaseConnection obj = new DatabaseConnection();
			Connection jdbcConnection = obj.connect();
			String sql1 = "INSERT INTO User(Name, Address, PhoneNumber) VALUES (?, ?, ?)";

			PreparedStatement statement1 = jdbcConnection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
			statement1.setString(1, userObj.getUserName());
			statement1.setString(2, userObj.getUserAddr());
			statement1.setString(3, userObj.getUserPhone());

			int affectedRows = statement1.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating user failed, no rows affected.");
			}

			try (ResultSet generatedKeys = statement1.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					insertFlightBooking(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}

			statement1.close();
			obj.disconnect();
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
