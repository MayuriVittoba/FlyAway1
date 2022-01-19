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
import com.user.service.RegistrationServlet;
import com.utils.Utils;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd;
		String paramEmail = ((String) request.getParameter("adminEmail"));
		String paramPwd = ((String) request.getParameter("adminPwd"));
		DatabaseConnection obj = null;
		PreparedStatement statement = null;
		if (Utils.isValidEmail(paramEmail)) {
			if (Utils.isValidPassword(paramPwd)) {

				try {
					obj = new DatabaseConnection();

					String query = "select adminpassword from admin where adminemail='" + paramEmail + "'";

					Connection jdbcConnection = obj.connect();

					statement = jdbcConnection.prepareStatement(query);
					ResultSet resSet = statement.executeQuery();

					if (resSet.next()) {
						if (((String) resSet.getString("adminpassword")).equals(paramPwd)) {
							HttpSession session = request.getSession();
							session.setAttribute("adminLoginEmail", paramEmail);
							response.sendRedirect("AdminDashboardServlet");
						} else {
							response.getOutputStream().println("Incorrect Password");
							rd = request.getRequestDispatcher("adminlogin.html");
							rd.include(request, response);
						}
					} else {
						response.getOutputStream().println("No account found");
						rd = request.getRequestDispatcher("adminlogin.html");
						rd.include(request, response);
					}

				} catch (SQLException ex) {
					ex.printStackTrace();
				} finally {

					try {
						statement.close();
						obj.disconnect();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} else {
				response.getOutputStream().println("Invalid Password");
				rd = request.getRequestDispatcher("adminlogin.html");
				rd.include(request, response);
			}
		} else {
			response.getOutputStream().println("Invalid Email");
			rd = request.getRequestDispatcher("adminlogin.html");
			rd.include(request, response);
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
