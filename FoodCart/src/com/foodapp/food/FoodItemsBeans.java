package com.foodapp.food;

import java.util.Vector;

public class FoodItemsBeans {

	private Vector<FoodItem> foodItems;
	private boolean valid;
	private String errormessage;
	private long menufoodCategoryId;
	
	public Vector<FoodItem> getFoodItems() {
		return foodItems;
	}
	public void setFoodItems(Vector<FoodItem> foodItems) {
		this.foodItems = foodItems;
	}
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
	public long getMenufoodCategoryId() {
		return menufoodCategoryId;
	}
	public void setMenufoodCategoryId(long menufoodCategoryId) {
		this.menufoodCategoryId = menufoodCategoryId;
	}

}
