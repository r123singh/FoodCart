package com.foodvilla.menu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

/**
 * Servlet implementation class CreateMenuServlet
 */
public class CreateMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateMenuServlet() {
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
		try {
			JSONObject menuObject = (JSONObject) request.getAttribute("menu");
			String restaurantName = menuObject.getString("restaurant");
			String restaurantAddress = menuObject.getString("address");
			String restaurantCategory = menuObject.getString("category");

			JSONObject menuItemObject = menuObject.getJSONObject("menuitem");

			String itemname = menuItemObject.getString("name");
			String itemdescription = menuItemObject.getString("description");
			double price = menuItemObject.getDouble("price");
			String type = menuItemObject.getString("type");
			MenuItemBean menuItemBean = new MenuItemBean();
			menuItemBean = MenuService.createMenuitem(menuItemBean);

			HttpSession currentSession = request.getSession(true);
			if (menuItemBean.isValid()) {
				response.getWriter().print("Menu item created");
				currentSession.setAttribute("MENU_STATUS", "menu item success");
			} else {
				response.getWriter().print("menu item failed");
				currentSession.setAttribute("MENU_STATUS", "menu item failed");
			}
		} catch (Exception e) {

		}

	}

}
