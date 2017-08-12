package com.foodvilla.restaurant;

import java.util.ArrayList;

public class RestaurantImageBean {

	private RestaurantImageMap restaurantimagemap;
	private String restaurantname;
	private ArrayList<RestaurantImageMap> imageMaps;
	private String staticimageurl;
	private String restaurantplaceid;
	private boolean valid;
	private String errormessage;
	
	
	public RestaurantImageMap getRestaurantimagemap() {
		return restaurantimagemap;
	}
	public void setRestaurantimagemap(RestaurantImageMap restaurantimagemap) {
		this.restaurantimagemap = restaurantimagemap;
	}
	public ArrayList<RestaurantImageMap> getImageMaps() {
		return imageMaps;
	}
	public void setImageMaps(ArrayList<RestaurantImageMap> imageMaps) {
		this.imageMaps = imageMaps;
	}
	public String getRestaurantplaceid() {
		return restaurantplaceid;
	}
	public void setRestaurantplaceid(String restaurantplaceid) {
		this.restaurantplaceid = restaurantplaceid;
	}
	public String getErrormessage() {
		return errormessage;
	}
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getStaticimageurl() {
		return staticimageurl;
	}
	public void setStaticimageurl(String staticimageurl) {
		this.staticimageurl = staticimageurl;
	}
	public String getRestaurantname() {
		return restaurantname;
	}
	public void setRestaurantname(String restaurantname) {
		this.restaurantname = restaurantname;
	}

}
