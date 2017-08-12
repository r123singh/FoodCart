package com.foodvilla.orders;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class OrderServlet
 */
public class CreateNewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateNewOrderServlet() {
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
		JSONObject finalOrderJson = null;
		OrdersBean orderBean = null;
		StringBuffer orderStringBuffer = new StringBuffer();
		BufferedReader orderBufferedReader = request.getReader();
		String line = null;
		while ((line = orderBufferedReader.readLine()) != null) {
			orderStringBuffer.append(line);
		}
		try {
			finalOrderJson = new JSONObject(orderStringBuffer.toString());
			orderBean = new OrdersBean();
			orderBean.setNeworderjsonrequest(finalOrderJson);
			orderBean = OrderService.createnewfoodorder(orderBean);
		} catch (Exception e) {
			orderBean.setValid(false);
			orderBean.setErrormsg("Oops! Orders Times are Closed");
		}
		if (orderBean.isValid()) {
			response.setContentType("application/text");
			response.getWriter().write("success");
		} else {
			response.setContentType("application/text");
			response.getWriter().write("failed");
		}
	}

}
