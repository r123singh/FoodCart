package com.foodapp.restaurants;

public class RestaurantBean {

	private long id;
	private String restaurantName;
	private String address;
	private boolean available;
	private String image;
	private String shortDescription;
	private boolean vegrestaurant;
	private boolean nonvegrestaurant;
	private String contact;
	private String errormsg;
	private boolean valid;
	

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public RestaurantBean() {
		// TODO Auto-generated constructor stub
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public boolean isVegrestaurant() {
		return vegrestaurant;
	}

	public void setVegrestaurant(boolean vegrestaurant) {
		this.vegrestaurant = vegrestaurant;
	}

	public boolean isNonvegrestaurant() {
		return nonvegrestaurant;
	}

	public void setNonvegrestaurant(boolean nonvegrestaurant) {
		this.nonvegrestaurant = nonvegrestaurant;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
