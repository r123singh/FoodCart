package com.foodapp.cart;

public class CartBean {

	private long id;
	private double deliverycharges;
	private double totalcharges;
	private double taxcharges;
	private double discountcharges;
	private double packagingcharges;
	private double subtotalCharges;
	private long restaurant;
	private String restaurantimage;
	private boolean valid;
	private String error;

	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getDeliverycharges() {
		return deliverycharges;
	}
	public void setDeliverycharges(double deliverycharges) {
		this.deliverycharges = deliverycharges;
	}
	public double getTotalcharges() {
		return totalcharges;
	}
	public void setTotalcharges(double totalcharges) {
		this.totalcharges = totalcharges;
	}
	public double getTaxcharges() {
		return taxcharges;
	}
	public void setTaxcharges(double taxcharges) {
		this.taxcharges = taxcharges;
	}
	public double getDiscountcharges() {
		return discountcharges;
	}
	public void setDiscountcharges(double discountcharges) {
		this.discountcharges = discountcharges;
	}
	public double getPackagingcharges() {
		return packagingcharges;
	}
	public void setPackagingcharges(double packagingcharges) {
		this.packagingcharges = packagingcharges;
	}
	public double getSubtotalCharges() {
		return subtotalCharges;
	}
	public void setSubtotalCharges(double subtotalCharges) {
		this.subtotalCharges = subtotalCharges;
	}
	public long getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(long restaurant) {
		this.restaurant = restaurant;
	}
	public String getRestaurantimage() {
		return restaurantimage;
	}
	public void setRestaurantimage(String restaurantimage) {
		this.restaurantimage = restaurantimage;
	}

}
