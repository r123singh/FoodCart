package com.foodapp.restaurants;

import java.io.Serializable;

public class RestaurantItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private String restaurantmainTitle;
	private String deliveryType;
	private double deliverycharges;
	private double minimumordercharges;
	private String address;
	private boolean availableNow;
	private String paymentmode;
	private String foodcategories;
	private String restaurantimage;
	private String restaurantDistance;
	private String estimatedDelivery;
	private String restaurantloc;

	public RestaurantItem() {
	}

	public String getRestaurantmainTitle() {
		return restaurantmainTitle;
	}
	public void setRestaurantmainTitle(String restaurantmainTitle) {
		this.restaurantmainTitle = restaurantmainTitle;
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	public double getDeliverycharges() {
		return deliverycharges;
	}
	public void setDeliverycharges(double deliverycharges) {
		this.deliverycharges = deliverycharges;
	}
	public double getMinimumordercharges() {
		return minimumordercharges;
	}
	public void setMinimumordercharges(double minimumordercharges) {
		this.minimumordercharges = minimumordercharges;
	}
	public boolean isAvailableNow() {
		return availableNow;
	}
	public void setAvailableNow(boolean availableNow) {
		this.availableNow = availableNow;
	}
	public String getPaymentmode() {
		return paymentmode;
	}
	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}
	public String getEstimatedDelivery() {
		return estimatedDelivery;
	}
	public void setEstimatedDelivery(String estimatedDelivery) {
		this.estimatedDelivery = estimatedDelivery;
	}

	public String getRestaurantDistance() {
		return restaurantDistance;
	}

	public void setRestaurantDistance(String restaurantDistance) {
		this.restaurantDistance = restaurantDistance;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRestaurantloc() {
		return restaurantloc;
	}

	public void setRestaurantloc(String restaurantloc) {
		this.restaurantloc = restaurantloc;
	}

	public String getFoodcategories() {
		return foodcategories;
	}

	public void setFoodcategories(String foodcategories) {
		this.foodcategories = foodcategories;
	}

	public String getRestaurantimage() {
		return restaurantimage;
	}

	public void setRestaurantimage(String restaurantimage) {
		this.restaurantimage = restaurantimage;
	}

}
