package com.foodapp.food;

import java.io.Serializable;

public class FoodItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String itemname;
	private String itemDescription;
	private double itemprice;
	private double itemdiscountcharges;
	private double itempackagingcharges;
	private double itemdeliverycharges;
	private double itemtaxcharges;
	private long menufoodCategoryId;
	private String bitmapimage;
	private int quantity = 1;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getItemdiscountcharges() {
		return itemdiscountcharges;
	}
	public void setItemdiscountcharges(double itemdiscountcharges) {
		this.itemdiscountcharges = itemdiscountcharges;
	}
	public double getItempackagingcharges() {
		return itempackagingcharges;
	}
	public void setItempackagingcharges(double itempackagingcharges) {
		this.itempackagingcharges = itempackagingcharges;
	}
	public double getItemdeliverycharges() {
		return itemdeliverycharges;
	}
	public void setItemdeliverycharges(double itemdeliverycharges) {
		this.itemdeliverycharges = itemdeliverycharges;
	}
	public double getItemtaxcharges() {
		return itemtaxcharges;
	}
	public void setItemtaxcharges(double itemtaxcharges) {
		this.itemtaxcharges = itemtaxcharges;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public double getItemprice() {
		return itemprice;
	}
	public void setItemprice(double itemprice) {
		this.itemprice = itemprice;
	}
	public long getMenufoodCategoryId() {
		return menufoodCategoryId;
	}
	public void setMenufoodCategoryId(long menufoodCategoryId) {
		this.menufoodCategoryId = menufoodCategoryId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getBitmapimage() {
		return bitmapimage;
	}
	public void setBitmapimage(String bitmapimage) {
		this.bitmapimage = bitmapimage;
	}

}
