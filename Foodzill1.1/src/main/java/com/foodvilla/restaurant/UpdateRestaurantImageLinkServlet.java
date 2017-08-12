package com.foodvilla.restaurant;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateRestaurantImageLinkServlet
 */
public class UpdateRestaurantImageLinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateRestaurantImageLinkServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String restaurantplaceid = request.getParameter("placeid");
		RestaurantBean restaurantbean = new RestaurantBean();
		restaurantbean.setPlaceid(restaurantplaceid);
		restaurantbean.setRestaurantdisplayimage(request.getParameter("placeimageurl"));
		restaurantbean = RestaurantService.updaterestaurantimagelinks(restaurantbean);
		if (restaurantbean.isValid()) {
			HttpSession session = request.getSession(true);
			session.setAttribute("updatestatus",restaurantbean);
			response.sendRedirect("restaurantimagelinkstatus.jsp");
		} else {
			HttpSession session = request.getSession(true);
			session.setAttribute("updatestatus", restaurantbean.getErrormessage());
			response.sendRedirect("restaurantimagelinkfailed.jsp");
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
