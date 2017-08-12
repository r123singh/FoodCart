package com.foodvilla.menu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SyncMenuItemServlet
 */
public class SyncMenuCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SyncMenuCategoryServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String restaurant = request.getParameter("restaurant");
		MenuItemBean menuItemBean = new MenuItemBean();
		menuItemBean.setMenuitemrestaurant(restaurant);
		menuItemBean = MenuService.fetchallmenucategoryjsonresponse(menuItemBean);
		if (menuItemBean.isValid()) {
			response.setContentType("application/json");
			response.getWriter().write(menuItemBean.getMenuitemsjson().toString());
		} else {
			response.setContentType("application/json");
			response.getWriter().write(menuItemBean.getErrormessage());
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
