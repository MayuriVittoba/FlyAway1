package com.admin.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DatabaseConnection;
import com.user.service.RegistrationServlet;
import com.utils.Utils;

/**
 * Servlet implementation class AdminChangePassword
 */
@WebServlet("/AdminChangePasswordServlet")
public class AdminChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminChangePasswordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)o00988 ' t?><+_=-098ipoul;i[p
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd;
		DatabaseConnection obj = null;
		PreparedStatement statement = null;
		String paramNewAdminPwd = ((String) request.getParameter("newAdminPwd"));
		String paramConfirmAdminPwd = ((String) request.getParameter("confirmAdminPwd"));
		String paramAdminEmail = ((String) request.getParameter("adminEmail"));
		if (Utils.isValidPassword(paramNewAdminPwd)) {
			if (paramNewAdminPwd.equals(paramConfirmAdminPwd)) {
				try {
					obj = new DatabaseConnection();

					String query = "update admin set adminpassword='" + paramNewAdminPwd + "' where adminemail='"
							+ paramAdminEmail + "'";

					Connection jdbcConnection = obj.connect();

					statement = jdbcConnection.prepareStatement(query);
					int successNum = statement.executeUpdate(query);
					if (successNum > 0) {
						response.sendRedirect("adminsuccess.html");
					} else {
						response.getOutputStream().println("No data found");
						rd = request.getRequestDispatcher("adminchangepassword.html");
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
				response.getOutputStream().println("Password should match");
				rd = request.getRequestDispatcher("adminchangepassword.html");
				rd.include(request, response);
			}
		} else {
			response.getOutputStream().println("Invalid Password");
			rd = request.getRequestDispatcher("adminchangepassword.html");
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
