package com.foodvilla.restaurant;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SyncAllRestaurantServlet
 */
public class SyncAllRestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SyncAllRestaurantServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userlocation = request.getParameter("location");
		RestaurantBean restaurantBean = new RestaurantBean();
		restaurantBean.setRestaurantlocation(userlocation);
		restaurantBean = RestaurantService.fetchallrestaurantsbylocation(restaurantBean);
		if (restaurantBean.isValid()) {
			response.setContentType("application/json");
			response.getWriter().write(restaurantBean.getRestaurantJson().toString());
		} else {
			response.setContentType("application/text");
			response.getWriter().write(restaurantBean.getErrormessage());
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
