package com.foodvilla.orders;

import org.json.JSONObject;


public class OrdersBean {

	private String orderId;
	private JSONObject neworderjsonrequest;
	private JSONObject orderstatusjson;
	private double totalPrice;
	private double totalTax;
	private OrderItemsBeans orderItems;
	private String deliveryAddress;
	private String restaurantAddress;
	private String restaurantName;
	private java.util.Date orderdate;
	private boolean valid;
	private String errormsg;
	private String status;

	private FoodOrder foodOrder;

	public String getStatus() {
		return status;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getRestaurantAddress() {
		return restaurantAddress;
	}
	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}
	public void setStatus(String status) {
		this.status = status;
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

	public OrderItemsBeans getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(OrderItemsBeans orderItems) {
		this.orderItems = orderItems;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public FoodOrder getFoodOrder() {
		return foodOrder;
	}
	public void setFoodOrder(FoodOrder foodOrder) {
		this.foodOrder = foodOrder;
	}
	public java.util.Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(java.util.Date orderdate) {
		this.orderdate = orderdate;
	}
	public JSONObject getNeworderjsonrequest() {
		return neworderjsonrequest;
	}
	public void setNeworderjsonrequest(JSONObject neworderjsonrequest) {
		this.neworderjsonrequest = neworderjsonrequest;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public JSONObject getOrderstatusjson() {
		return orderstatusjson;
	}
	public void setOrderstatusjson(JSONObject orderstatusjson) {
		this.orderstatusjson = orderstatusjson;
	}

}
