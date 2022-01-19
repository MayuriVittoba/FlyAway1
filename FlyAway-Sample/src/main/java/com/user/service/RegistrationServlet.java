package com.user.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uName = (String) request.getParameter("userName");
		String uAddr = (String) request.getParameter("userAddr");
		String uPhone = (String) request.getParameter("userPhone");
		if (uName.length() > 0 && uAddr.length() > 0 && uPhone.length() > 0) {
			User userObj = new User();
			userObj.setUserName(uName);
			userObj.setUserAddr(uAddr);
			userObj.setUserPhone(uPhone);

			this.getServletConfig().getServletContext().setAttribute("userDetail", userObj);

			response.sendRedirect("payment.jsp");
		} else {
			response.getWriter().write("please give valid details");
			RequestDispatcher rd = request.getRequestDispatcher("register.html");
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
