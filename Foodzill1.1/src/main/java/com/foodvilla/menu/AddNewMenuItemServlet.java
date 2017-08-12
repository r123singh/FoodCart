package com.foodvilla.menu;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddNewMenuItemServlet
 */
public class AddNewMenuItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddNewMenuItemServlet() {
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
		String menuitemname = request.getParameter("menutitemname");
		String menuitemdescription = request.getParameter("menuitemdescription");
		String menuitemprice = request.getParameter("menuitemprice");
		String menuitemcategory = request.getParameter("menuitemcategory");
		String menuitempackagingcharges = request.getParameter("menuitempackagingcharges");
		String menuitemtaxcharges = request.getParameter("menuitemtaxcharges");
		String menuitemdiscountcharges = request.getParameter("menuitemdiscountcharges");
		String menuitemrestaurant = request.getParameter("menuitemrestaurant");
		String menuitemcategoryimage = request.getParameter("menuitemcategoryimage");
		String menutitemvegetarian = request.getParameter("menuitemvegetarian");
		MenuItemBean menuitembean = new MenuItemBean();
		menuitembean.setMenuitemcategory(menuitemcategory);
		menuitembean.setMenuitemcategoryimage(menuitemcategoryimage);
		menuitembean.setMenuitemdescription(menuitemdescription);
		menuitembean.setMenuitemdiscountcharges(menuitemdiscountcharges);
		menuitembean.setMenuitemname(menuitemname);
		menuitembean.setMenuitempackagingcharges(menuitempackagingcharges);
		menuitembean.setMenuitemprice(menuitemprice);
		menuitembean.setMenuitemrestaurant(menuitemrestaurant);
		menuitembean.setMenuitemtaxcharges(menuitemtaxcharges);
		menuitembean.setMenuitemvegetarian(menutitemvegetarian);
		menuitembean = MenuService.createMenuitem(menuitembean);
		if (menuitembean.isValid()) {
			response.sendRedirect("menuitemSuccess.jsp");
		} else {
			response.sendRedirect("menuitemFailure.jsp");
		}
	}

}
