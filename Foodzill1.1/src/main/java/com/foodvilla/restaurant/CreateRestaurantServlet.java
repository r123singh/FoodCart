package com.foodvilla.restaurant;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateRestaurantServlet
 */
public class CreateRestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateRestaurantServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String restaurantname = request.getParameter("restaurantname");
		String restaurantaddress = request.getParameter("restaurantaddress");
		String restaurantfoodcategory = request.getParameter("restaurantfoodcategory");
		String restaurantdeliverymode = request.getParameter("restaurantdeliverymode");
		String restaurantpaymentmode = request.getParameter("restaurantpaymentmode");
		String restaurantminimumordercharges = request.getParameter("restaurantminimumordercharges");
		String restaurantestimateddeliverytime = request.getParameter("restaurantestimateddeliverytime");
		String restaurantstarttime = request.getParameter("restaurantstarttime");
		String restaurantclosetime = request.getParameter("restaurantclosetime");
		String restaurantdeliverycharges = request.getParameter("restaurantdeliverycharges");
		String restaurantdisplayimage = request.getParameter("restaurantdisplayimage");
		String restaurantlocation = request.getParameter("restaurantlocation");
		RestaurantBean restaurantBean = new RestaurantBean();
		restaurantBean.setRestaurantname(restaurantname);
		restaurantBean.setRestaurantpaymentmode(restaurantpaymentmode);
		restaurantBean.setRestaurantaddress(restaurantaddress);
		restaurantBean.setRestaurantclosetime(restaurantclosetime);
		restaurantBean.setRestaurantdeliverycharges(restaurantdeliverycharges);
		restaurantBean.setRestaurantdeliverymode(restaurantdeliverymode);
		restaurantBean.setRestaurantdisplayimage(restaurantdisplayimage);
		restaurantBean.setRestaurantlocation(restaurantlocation);
		restaurantBean.setRestaurantminimumordercharges(restaurantminimumordercharges);
		restaurantBean.setRestaurantstarttime(restaurantstarttime);
		restaurantBean.setRestaurantfoodcategory(restaurantfoodcategory);
		restaurantBean.setRestaurantestimateddeliverytime(restaurantestimateddeliverytime);
		restaurantBean = RestaurantService.createnewrestaurantobject(restaurantBean);
		if (restaurantBean.isValid()) {
			response.sendRedirect("restaurantSuccess.jsp");
		} else {
			response.sendRedirect("restaurantFailure.jsp");
		}
	}
}
