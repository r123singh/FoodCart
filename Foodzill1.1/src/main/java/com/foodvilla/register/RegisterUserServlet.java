package com.foodvilla.register;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodvilla.login.UserBean;

/**
 * Servlet implementation class RegisterUserServlet
 */
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserBean registerUserBean = new UserBean();
		registerUserBean.setFirstName(request.getParameter("firstname"));
		registerUserBean.setLastName(request.getParameter("lastname"));
		registerUserBean.setUsername(request.getParameter("username"));
		registerUserBean.setPassword(request.getParameter("password"));
		registerUserBean.setMobile(request.getParameter("mobile"));
		registerUserBean = RegisterDAO.registerUser(registerUserBean);
		if (registerUserBean.isValid()) {
			HttpSession session = request.getSession(true);
			session.setAttribute("registeredSessionUser", registerUserBean);
			response.sendRedirect("userRegistered.jsp");
		} else {
			response.sendRedirect("registerationFailed.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
