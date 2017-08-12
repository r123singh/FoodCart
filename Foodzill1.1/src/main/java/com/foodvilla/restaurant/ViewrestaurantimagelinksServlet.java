package com.foodvilla.restaurant;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewrestaurantimagelinksServlet
 */
public class ViewrestaurantimagelinksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewrestaurantimagelinksServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RestaurantImageBean restaurantimagebean = new RestaurantImageBean();
		restaurantimagebean.setRestaurantplaceid(request.getParameter("placeid"));
		restaurantimagebean.setRestaurantname(request.getParameter("restaurant_name"));
		restaurantimagebean = RestaurantService.fetchallimagelinksbyplaceid(restaurantimagebean);
		if (restaurantimagebean.isValid()) {
			HttpSession session = request.getSession(true);
			session.setAttribute("restaurantimagerefs", restaurantimagebean);
			response.sendRedirect("restaurantimageurl.jsp");
		} else {
			response.getWriter().write(restaurantimagebean.getErrormessage());
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
