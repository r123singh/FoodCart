package com.foodapp.menu;

import java.io.Serializable;

import android.graphics.Bitmap;

public class MenuFoodCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private String description;
	private int itemscount;
	private long restaurantId;
	private String categoryimage;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getItemscount() {
		return itemscount;
	}
	public void setItemscount(int itemscount) {
		this.itemscount = itemscount;
	}
	public long getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getCategoryimage() {
		return categoryimage;
	}
	public void setCategoryimage(String categoryimage) {
		this.categoryimage = categoryimage;
	}

}
