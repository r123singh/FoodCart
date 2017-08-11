package com.foodapp.restaurants;

import java.util.Vector;

public class RestaurantBeans {

	private Vector<RestaurantBean> restaurantBeans;
	private boolean valid;
	private String errorMessage;
	private String category;
	private String locationid;
	private Vector<RestaurantItem> restaurantItems;
	
	
	public RestaurantBeans() {
		// TODO Auto-generated constructor stub
	}

	public Vector<RestaurantBean> getRestaurantBeans() {
		return restaurantBeans;
	}

	public void setRestaurantBeans(Vector<RestaurantBean> restaurantBeans) {
		this.restaurantBeans = restaurantBeans;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLocationid() {
		return locationid;
	}

	public void setLocationid(String locationid) {
		this.locationid = locationid;
	}

	public Vector<RestaurantItem> getRestaurantItems() {
		return restaurantItems;
	}

	public void setRestaurantItems(Vector<RestaurantItem> restaurantItems) {
		this.restaurantItems = restaurantItems;
	}


}
