package com.foodapp.menu;

import java.util.Vector;

public class FoodCategoriesBean {
	private long restaurantid;
	private Vector<MenuFoodCategory> menuFoodCategories;
	private boolean valid;
	private String errmessage;
	
	public long getRestaurantid() {
		return restaurantid;
	}
	public void setRestaurantid(long restaurantid) {
		this.restaurantid = restaurantid;
	}
	public Vector<MenuFoodCategory> getMenuFoodCategories() {
		return menuFoodCategories;
	}
	public void setMenuFoodCategories(Vector<MenuFoodCategory> menuFoodCategories) {
		this.menuFoodCategories = menuFoodCategories;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getErrmessage() {
		return errmessage;
	}
	public void setErrmessage(String errmessage) {
		this.errmessage = errmessage;
	}

}
