package com.foodapp.cart;

import java.util.ArrayList;
import java.util.Date;

public class CartItemsBean {
	private long cartid;
	private double itemprice;
	private double itemtotalcharges;
	private double itemtaxcharges;
	private double itemdiscountcharges;
	private double itempackagingcharges;
	private double itemsubtotalCharges;
	private int quantity;
	private String itemname;
	private ArrayList<CartItemsBean> cartitems;
	private Date deliveryDate;
	private boolean valid;
	private String errmsg;
	private boolean priority;

	public CartItemsBean() {
	}
	public double getItemtotalcharges() {
		return itemtotalcharges;
	}

	public void setItemtotalcharges(double itemtotalcharges) {
		this.itemtotalcharges = itemtotalcharges;
	}

	public double getItemtaxcharges() {
		return itemtaxcharges;
	}

	public void setItemtaxcharges(double itemtaxcharges) {
		this.itemtaxcharges = itemtaxcharges;
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

	public double getItemsubtotalCharges() {
		return itemsubtotalCharges;
	}

	public void setItemsubtotalCharges(double itemsubtotalCharges) {
		this.itemsubtotalCharges = itemsubtotalCharges;
	}

	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public boolean isPriority() {
		return priority;
	}

	public void setPriority(boolean priority) {
		this.priority = priority;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getCartid() {
		return cartid;
	}

	public void setCartid(long cartid) {
		this.cartid = cartid;
	}

	public ArrayList<CartItemsBean> getCartitems() {
		return cartitems;
	}

	public void setCartitems(ArrayList<CartItemsBean> cartitems) {
		this.cartitems = cartitems;
	}
	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public double getItemprice() {
		return itemprice;
	}

	public void setItemprice(double itemprice) {
		this.itemprice = itemprice;
	}

}
