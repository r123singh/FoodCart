package com.foodvilla.orders;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class CreateJsonOrderServlet
 */
public class CreateJsonOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateJsonOrderServlet() {
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
		// TODO Auto-generated method stub
		String restaurant = request.getParameter("restaurant");
		String restAddress = request.getParameter("restAddress");
		String deliveryAddress = request.getParameter("deliveryAddress");

		String itemone = request.getParameter("itemone");
		String itemoneDescription = request.getParameter("itemoneDescription");
		int itemoneQty = Integer.parseInt(request.getParameter("itemoneQty"));
		String itemoneRmk = request.getParameter("itemoneRmk");
		float itemonePrice = Float.parseFloat(request.getParameter("itemonePrice"));

		String itemtwo = request.getParameter("itemtwo");
		String itemtwoDescription = request.getParameter("itemtwoDescription");
		int itemtwoQty = Integer.parseInt(request.getParameter("itemtwoQty"));
		String itemtwoRmk = request.getParameter("itemtwoRmk");
		float itemtwoPrice = Float.parseFloat(request.getParameter("itemtwoPrice"));

		float totalprice = Float.parseFloat(request.getParameter("totalprice"));
		float totaltax = Float.parseFloat(request.getParameter("totaltax"));
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("restaurantName", restaurant);
			jsonObject.put("restaurantAddress", restAddress);
			jsonObject.put("deliveryAddress", deliveryAddress);
			jsonObject.put("totalprice", totalprice);
			jsonObject.put("totaltax", totaltax);
			JSONArray itemsJsonArray = new JSONArray();
			JSONObject item1JsonObject = new JSONObject();
			item1JsonObject.put("count", 1);
			item1JsonObject.put("name", itemone);
			item1JsonObject.put("description", itemoneDescription);
			item1JsonObject.put("qunatity", itemoneQty);
			item1JsonObject.put("remarks", itemoneRmk);
			item1JsonObject.put("price", itemonePrice);
			JSONObject item2JsonObject = new JSONObject();
			item2JsonObject.put("count", 2);
			item2JsonObject.put("name", itemtwo);
			item2JsonObject.put("description", itemtwoDescription);
			item2JsonObject.put("qunatity", itemtwoQty);
			item2JsonObject.put("remarks", itemtwoRmk);
			item2JsonObject.put("price", itemtwoPrice);
			itemsJsonArray.put(item1JsonObject);
			itemsJsonArray.put(item2JsonObject);
			jsonObject.put("orderitems", itemsJsonArray);
			JSONObject finalJsonObject = new JSONObject();
			finalJsonObject.put("order", jsonObject);
			finalJsonObject.put("id", 1);
			System.out.println(finalJsonObject.toString());
			request.setAttribute("final_order", finalJsonObject);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/OrderServlet");
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
