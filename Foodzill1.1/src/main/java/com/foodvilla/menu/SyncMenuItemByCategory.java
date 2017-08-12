package com.foodvilla.menu;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SyncMenuItemByCategory
 */
public class SyncMenuItemByCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SyncMenuItemByCategory() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MenuItemBean menuitembean = new MenuItemBean();
		menuitembean.setMenuitemrestaurant(request.getParameter("restaurant"));
		menuitembean.setMenuitemcategory(request.getParameter("category"));
		menuitembean = MenuService.fetchallmenuitemsbymenucategory(menuitembean);
		if (menuitembean.isValid()) {
			response.setContentType("application/json");
			response.getWriter().write(menuitembean.getMenuitemsjson().toString());
		} else {
			response.setContentType("application/text");
			response.getWriter().write(menuitembean.getErrormessage());
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
