package com.foodvilla.orders;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SyncfoodOrders
 */
public class SyncfoodOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SyncfoodOrdersServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String foodorderstatus = request.getParameter("status");
		String foodorderrestaurant = request.getParameter("restaurant");
		FoodOrdersBean foodorderbean = new FoodOrdersBean();
		foodorderbean.setFoodorderstatus(foodorderstatus);
		foodorderbean.setFoodorderrestaurant(foodorderrestaurant);
		foodorderbean = OrderService.syncfoodordersfordeliveryguy(foodorderbean);
		if (foodorderbean.isValid()) {
			response.setContentType("application/json");
			response.getWriter().write(foodorderbean.getFoodorderjsonresponse().toString());
		} else {
			response.setContentType("application/json");
			response.getWriter().write(foodorderbean.getFoodorderjsonresponse().toString());
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
