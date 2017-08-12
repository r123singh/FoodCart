package com.foodvilla.orders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SyncOrderStatus
 */
public class SyncOrderStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SyncOrderStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrdersBean orderbean = new OrdersBean();
		orderbean.setOrderId(request.getParameter("orderid"));
		orderbean = OrderService.fetchorderstatusbyid(orderbean);
		if (orderbean.isValid()) {
			response.setContentType("application/json");
			response.getWriter().write(orderbean.getOrderstatusjson().toString());
		} else {
			response.getWriter().write(orderbean.getErrormsg());
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
