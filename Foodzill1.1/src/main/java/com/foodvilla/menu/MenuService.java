package com.foodvilla.menu;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;

import com.foodvilla.restaurant.Restaurant;
import com.foodvilla.restaurant.RestaurantBean;
import com.foodvilla.restaurant.RestaurantDAO;
import com.foodvilla.restaurant.RestaurantService;

public class MenuService {

	public static MenuItemBean createMenuitem(MenuItemBean menuItemBean) {
		MenuItem menuItem = null;
		MenuDAO menuDAO = null;
		long restaurantid = Long.parseLong(menuItemBean.getMenuitemrestaurant());
		RestaurantBean restaurantBean = new RestaurantBean();
		restaurantBean.setRestaurantid(restaurantid);
		restaurantBean = RestaurantService.fetchrestaurantbyrestaurantid(restaurantBean);
		if (restaurantBean.isValid()) {
			try {
				Restaurant restaurant = restaurantBean.getRestaurant();
				menuItem = new MenuItem();
				menuItem.setRestaurant(restaurant);
				menuItem.setName(menuItemBean.getMenuitemname());
				menuItem.setDescription(menuItemBean.getMenuitemdescription());
				menuItem.setCategory(menuItemBean.getMenuitemcategory());
				menuItem.setCategoryimage(menuItemBean.getMenuitemcategoryimage());
				menuItem.setVegetarian(menuItemBean.getMenuitemvegetarian());
				menuItem.setPrice(Double.parseDouble(menuItemBean.getMenuitemprice()));
				menuItem.setPackagingcharges(Double.parseDouble(menuItemBean.getMenuitempackagingcharges()));
				menuItem.setDiscount(Double.parseDouble(menuItemBean.getMenuitemdiscountcharges()));
				menuItem.setTaxcharges(Double.parseDouble(menuItemBean.getMenuitemtaxcharges()));
				menuItem.setCreated(new Date(System.currentTimeMillis()));
				menuItemBean.setMenuitem(menuItem);
				menuDAO = new MenuDAO();
				menuItemBean = menuDAO.createnewmenuitem(menuItemBean);
			} catch (Exception e) {
				e.printStackTrace();
				menuItemBean.setValid(false);
				menuItemBean.setErrormessage(e.getLocalizedMessage());
			}
		} else {
			menuItemBean.setValid(false);
			menuItemBean.setErrormessage(restaurantBean.getErrormessage());
		}

		return menuItemBean;
	}

	public static MenuItemBean fetchallmenucategoryjsonresponse(MenuItemBean menuItemBean) {
		MenuDAO menuDAO = null;
		RestaurantDAO restaurantDAO = null;

		try {
			restaurantDAO = new RestaurantDAO();
			RestaurantBean restaurantBean = new RestaurantBean();
			long restaurantid = Long.parseLong(menuItemBean.getMenuitemrestaurant());
			Restaurant restaurant = restaurantDAO.fetchrestaurantbyid(restaurantBean, restaurantid);
			menuDAO = new MenuDAO();
			menuItemBean = menuDAO.fetchallmenuCategorybyrestaurant(menuItemBean, restaurant);
			if (menuItemBean.isValid()) {
				JSONArray menucategoriesjsonarray = new JSONArray();
				HashMap<String, String> menucategories = menuItemBean.getPropertymap();
				for (Entry<String, String> category : menucategories.entrySet()) {
					JSONObject menuitemjsonobject = new JSONObject();
					menuitemjsonobject.put("category", category.getKey());
					menuitemjsonobject.put("category_image", category.getValue());
					menucategoriesjsonarray.put(menuitemjsonobject);
				}
				JSONObject menuitemjson = new JSONObject();
				menuitemjson.put("menu_categories", menucategoriesjsonarray);
				menuitemjson.put("restaurant", menuItemBean.getMenuitemrestaurant());
				menuItemBean.setMenuitemsjson(menuitemjson);
				menuItemBean.setValid(true);
			} else {
				menuItemBean.setValid(false);
				menuItemBean.setErrormessage("No Menu Categories For Restaurant " + menuItemBean.getMenuitemrestaurant());
			}
		} catch (Exception e) {
			menuItemBean.setValid(false);
			menuItemBean.setErrormessage(e.getLocalizedMessage());
		}
		return menuItemBean;
	}

	public static MenuItemBean fetchallmenuitemsbymenucategory(MenuItemBean menuitembean) {
		MenuDAO menudao = null;
		RestaurantDAO restaurantdao = null;
		try {
			menuitembean.setValid(false);
			restaurantdao = new RestaurantDAO();
			RestaurantBean restaurantbean = new RestaurantBean();
			restaurantbean.setRestaurantid(Long.parseLong(menuitembean.getMenuitemrestaurant()));
			restaurantbean = restaurantdao.fetchrestaurantbyid(restaurantbean);
			if (restaurantbean.getRestaurant() != null) {
				menudao = new MenuDAO();
				menuitembean = menudao.fetchallmenuitemsbycategory(menuitembean, restaurantbean.getRestaurant());
				if (menuitembean.getMenuItems() != null) {
					JSONArray menuitemsarray = new JSONArray();
					ArrayList<MenuItem> menuitems = menuitembean.getMenuItems();
					for (MenuItem menuItem : menuitems) {
						JSONObject menuitemjsonobject = new JSONObject();
						menuitemjsonobject.put("name", menuItem.getName());
						menuitemjsonobject.put("description", menuItem.getDescription());
						menuitemjsonobject.put("price", menuItem.getPrice());
						menuitemjsonobject.put("discount", menuItem.getDiscount());
						menuitemjsonobject.put("packaging", menuItem.getPackagingcharges());
						menuitemjsonobject.put("tax", menuItem.getTaxcharges());
						menuitemjsonobject.put("category_image", menuItem.getCategoryimage());
						menuitemjsonobject.put("vegcategory", menuItem.getVegetarian());
						menuitemsarray.put(menuitemjsonobject);
					}
					JSONObject menuitemjson = new JSONObject();
					menuitemjson.put("menu_items", menuitemsarray);
					menuitemjson.put("category", menuitembean.getMenuitemcategory());
					menuitemjson.put("restaurant", restaurantbean.getRestaurant().getName());
					menuitembean.setMenuitemsjson(menuitemjson);
					menuitembean.setValid(true);
				}
			} else {
				menuitembean.setErrormessage(restaurantbean.getErrormessage());
				menuitembean.setValid(false);
			}
		} catch (Exception e) {
			menuitembean.setValid(false);
			menuitembean.setErrormessage("Restaurant Closed Now");
		}
		return menuitembean;
	}
}
