package com.foodvilla.restaurant;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GetStaticRestaurantImageUrlServlet
 */
public class GetStaticRestaurantImageUrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetStaticRestaurantImageUrlServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RestaurantImageBean imagebean = new RestaurantImageBean();
		imagebean = RestaurantService.fetchimagestaticurllocationsbyreference(imagebean);
		if (imagebean.isValid()) {
			HttpSession session = request.getSession(true);
			session.setAttribute("staticimagelink", imagebean);
			response.sendRedirect("staticimageview.jsp");
		} else {
			response.getWriter().write(imagebean.getErrormessage());
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
