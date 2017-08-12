package com.foodvilla.restaurant;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FetchAllRestaurants
 */
public class FetchAllRestaurants extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchAllRestaurants() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RestaurantBean restaurantbean = new RestaurantBean();
		restaurantbean = RestaurantService.fetchallrestaurantslisted(restaurantbean);
		HttpSession session = request.getSession(true);
		if (restaurantbean.isValid()) {
			session.setAttribute("restaurantbean", restaurantbean);
			response.sendRedirect("syncrestaurants.jsp");
		} else {
			response.getWriter().write(restaurantbean.getErrormessage());
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
