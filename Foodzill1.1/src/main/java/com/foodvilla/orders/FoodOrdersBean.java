package com.foodvilla.orders;

import java.util.ArrayList;

import org.json.JSONObject;

public class FoodOrdersBean {

	private ArrayList<FoodOrder> foodOrders;
	private String foodorderid;
	private String foodorderttotalcharge;
	private String foodorderdeliveryaddress;
	private String foodorderstatus;
	private String foodorderdate;
	private String foodordertotaltax;
	private String foodordersubtotal;
	private String foodorderpackagingcharge;
	private String foodorderdeliverycharge;
	private String foodorderrestaurant;
	private String foodordercustomermobile;
	private String errormessage;
	private JSONObject foodorderjsonresponse;

	public String getFoodorderid() {
		return foodorderid;
	}
	public void setFoodorderid(String foodorderid) {
		this.foodorderid = foodorderid;
	}
	public String getFoodorderttotalcharge() {
		return foodorderttotalcharge;
	}
	public void setFoodorderttotalcharge(String foodorderttotalcharge) {
		this.foodorderttotalcharge = foodorderttotalcharge;
	}
	public String getFoodorderdeliveryaddress() {
		return foodorderdeliveryaddress;
	}
	public void setFoodorderdeliveryaddress(String foodorderdeliveryaddress) {
		this.foodorderdeliveryaddress = foodorderdeliveryaddress;
	}
	public String getFoodorderstatus() {
		return foodorderstatus;
	}
	public void setFoodorderstatus(String foodorderstatus) {
		this.foodorderstatus = foodorderstatus;
	}
	public String getFoodorderdate() {
		return foodorderdate;
	}
	public void setFoodorderdate(String foodorderdate) {
		this.foodorderdate = foodorderdate;
	}
	public String getFoodordertotaltax() {
		return foodordertotaltax;
	}
	public void setFoodordertotaltax(String foodordertotaltax) {
		this.foodordertotaltax = foodordertotaltax;
	}
	public String getFoodordersubtotal() {
		return foodordersubtotal;
	}
	public void setFoodordersubtotal(String foodordersubtotal) {
		this.foodordersubtotal = foodordersubtotal;
	}
	public String getFoodorderpackagingcharge() {
		return foodorderpackagingcharge;
	}
	public void setFoodorderpackagingcharge(String foodorderpackagingcharge) {
		this.foodorderpackagingcharge = foodorderpackagingcharge;
	}
	public String getFoodorderdeliverycharge() {
		return foodorderdeliverycharge;
	}
	public void setFoodorderdeliverycharge(String foodorderdeliverycharge) {
		this.foodorderdeliverycharge = foodorderdeliverycharge;
	}
	public String getFoodorderrestaurant() {
		return foodorderrestaurant;
	}
	public void setFoodorderrestaurant(String foodorderrestaurant) {
		this.foodorderrestaurant = foodorderrestaurant;
	}
	public String getFoodordercustomermobile() {
		return foodordercustomermobile;
	}
	public void setFoodordercustomermobile(String foodordercustomermobile) {
		this.foodordercustomermobile = foodordercustomermobile;
	}
	private boolean valid;

	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getErrormessage() {
		return errormessage;
	}
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	public ArrayList<FoodOrder> getFoodOrders() {
		return foodOrders;
	}
	public void setFoodOrders(ArrayList<FoodOrder> foodOrders) {
		this.foodOrders = foodOrders;
	}
	public JSONObject getFoodorderjsonresponse() {
		return foodorderjsonresponse;
	}
	public void setFoodorderjsonresponse(JSONObject foodorderjsonresponse) {
		this.foodorderjsonresponse = foodorderjsonresponse;
	}

}
