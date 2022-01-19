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
 * Servlet implementation class UserListServlet
 */

public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserListServlet() {
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
			String s = "select name,address,phonenumber from user";
			
			PreparedStatement statement1 = jdbcConnection.prepareStatement(s);

			ResultSet resSet = statement1.executeQuery();

			List<User> userList = new ArrayList<>();
			while (resSet.next()) {

				User userObj = new User();
				userObj.setUserName(resSet.getString(1));
				userObj.setUserAddr(resSet.getString(2));
				userObj.setUserPhone(resSet.getString(3));

				userList.add(userObj);
			}
			statement1.close();
			obj.disconnect();
//			getServletConfig().getServletContext().setAttribute("AdminUserList", userList);
//			response.sendRedirect("adminviewusers.jsp");
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.setAttribute("adminLoginEmail", session.getAttribute("adminLoginEmail"));
				getServletConfig().getServletContext().setAttribute("AdminUserList", userList);
				response.sendRedirect("adminviewusers.jsp");
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
