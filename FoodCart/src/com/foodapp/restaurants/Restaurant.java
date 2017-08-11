package com.foodapp.restaurants;

import com.foodapp.address.Address;
import com.foodapp.menu.Menu;

public class Restaurant {

	private String restaurantName;
	private String location;
	private String address;
	private String contact;
	private String rating;
	private String category;
	private Menu menu;
	private Address restaurantAddress;
	
	
	
	public Address getRestaurantAddress() {
		return restaurantAddress;
	}
	public void setRestaurantAddress(Address restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}
