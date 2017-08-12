package com.foodvilla.restaurant;

import java.util.ArrayList;

import org.json.JSONObject;

public class RestaurantBean {
	private boolean valid;
	private long restaurantid;
	private Restaurant restaurant;
	private String restaurantname;
	private String restaurantaddress;
	private String restaurantfoodcategory;
	private String restaurantdeliverymode;
	private String restaurantpaymentmode;
	private String restaurantdeliverycharges;
	private String restaurantminimumordercharges;
	private String restaurantestimateddeliverytime;
	private String restaurantstarttime;
	private String restaurantclosetime;
	private String restaurantdisplayimage;
	private String restaurantlocation;
	private String pagetoken;
	private String errormessage;
	private String placeid;
	private ArrayList<Restaurant> restaurants;
	private RestaurantImageMap restaurantimagemap;
	private JSONObject restaurantJson;
	
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public String getRestaurantname() {
		return restaurantname;
	}
	public void setRestaurantname(String restaurantname) {
		this.restaurantname = restaurantname;
	}
	public String getRestaurantaddress() {
		return restaurantaddress;
	}
	public void setRestaurantaddress(String restaurantaddress) {
		this.restaurantaddress = restaurantaddress;
	}
	public String getRestaurantfoodcategory() {
		return restaurantfoodcategory;
	}
	public void setRestaurantfoodcategory(String restaurantfoodcategory) {
		this.restaurantfoodcategory = restaurantfoodcategory;
	}
	public String getRestaurantdeliverymode() {
		return restaurantdeliverymode;
	}
	public void setRestaurantdeliverymode(String restaurantdeliverymode) {
		this.restaurantdeliverymode = restaurantdeliverymode;
	}
	public String getRestaurantpaymentmode() {
		return restaurantpaymentmode;
	}
	public void setRestaurantpaymentmode(String restaurantpaymentmode) {
		this.restaurantpaymentmode = restaurantpaymentmode;
	}
	public String getRestaurantdeliverycharges() {
		return restaurantdeliverycharges;
	}
	public void setRestaurantdeliverycharges(String restaurantdeliverycharges) {
		this.restaurantdeliverycharges = restaurantdeliverycharges;
	}
	public String getRestaurantminimumordercharges() {
		return restaurantminimumordercharges;
	}
	public void setRestaurantminimumordercharges(String restaurantminimumordercharges) {
		this.restaurantminimumordercharges = restaurantminimumordercharges;
	}
	public String getRestaurantestimateddeliverytime() {
		return restaurantestimateddeliverytime;
	}
	public void setRestaurantestimateddeliverytime(String restaurantestimateddeliverytime) {
		this.restaurantestimateddeliverytime = restaurantestimateddeliverytime;
	}
	public String getRestaurantstarttime() {
		return restaurantstarttime;
	}
	public void setRestaurantstarttime(String restaurantstarttime) {
		this.restaurantstarttime = restaurantstarttime;
	}
	public String getRestaurantclosetime() {
		return restaurantclosetime;
	}
	public void setRestaurantclosetime(String restaurantclosetime) {
		this.restaurantclosetime = restaurantclosetime;
	}
	public String getRestaurantdisplayimage() {
		return restaurantdisplayimage;
	}
	public void setRestaurantdisplayimage(String restaurantdisplayimage) {
		this.restaurantdisplayimage = restaurantdisplayimage;
	}
	public String getRestaurantlocation() {
		return restaurantlocation;
	}
	public void setRestaurantlocation(String restaurantlocation) {
		this.restaurantlocation = restaurantlocation;
	}
	public String getErrormessage() {
		return errormessage;
	}
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	public long getRestaurantid() {
		return restaurantid;
	}
	public void setRestaurantid(long restaurantid) {
		this.restaurantid = restaurantid;
	}
	public ArrayList<Restaurant> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(ArrayList<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	public JSONObject getRestaurantJson() {
		return restaurantJson;
	}
	public void setRestaurantJson(JSONObject restaurantJson) {
		this.restaurantJson = restaurantJson;
	}
	public String getPagetoken() {
		return pagetoken;
	}
	public void setPagetoken(String pagetoken) {
		this.pagetoken = pagetoken;
	}
	public String getPlaceid() {
		return placeid;
	}
	public void setPlaceid(String placeid) {
		this.placeid = placeid;
	}
	public RestaurantImageMap getRestaurantimagemap() {
		return restaurantimagemap;
	}
	public void setRestaurantimagemap(RestaurantImageMap restaurantimagemap) {
		this.restaurantimagemap = restaurantimagemap;
	}

}
