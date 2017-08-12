package com.foodvilla.restaurant;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FetchRestaurantImageServlet
 */
public class FetchRestaurantImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchRestaurantImageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RestaurantBean restaurantbean = new RestaurantBean();
		if (request.getParameter("placeid") == null) {
			response.getWriter().write("place id not sent");
		} else {
			restaurantbean.setPlaceid(request.getParameter("placeid"));
			restaurantbean = RestaurantService.fetchrestaurantimageurls(restaurantbean);
			if (!restaurantbean.isValid()) {
				response.getWriter().write(restaurantbean.getErrormessage());
			}
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
