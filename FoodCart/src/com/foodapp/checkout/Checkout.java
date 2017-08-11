package com.foodapp.checkout;

import java.io.Serializable;
import java.sql.Date;
import java.util.Vector;

public class Checkout implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long cart;
	private double totalcharges;
	private double packagingcharges;
	private double discount;
	private double subtotalcharges;
	private double taxcharges;
	private int orderitemcount;
	private Date deliverydate;
	private Vector<CheckoutItem> checkoutItems;
	public long getCart() {
		return cart;
	}
	public void setCart(long cart) {
		this.cart = cart;
	}
	public double getTotalcharges() {
		return totalcharges;
	}
	public void setTotalcharges(double totalcharges) {
		this.totalcharges = totalcharges;
	}
	public double getPackagingcharges() {
		return packagingcharges;
	}
	public void setPackagingcharges(double packagingcharges) {
		this.packagingcharges = packagingcharges;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getSubtotalcharges() {
		return subtotalcharges;
	}
	public void setSubtotalcharges(double subtotalcharges) {
		this.subtotalcharges = subtotalcharges;
	}
	public double getTaxcharges() {
		return taxcharges;
	}
	public void setTaxcharges(double taxcharges) {
		this.taxcharges = taxcharges;
	}
	public int getOrderitemcount() {
		return orderitemcount;
	}
	public void setOrderitemcount(int orderitemcount) {
		this.orderitemcount = orderitemcount;
	}
	public Date getDeliverydate() {
		return deliverydate;
	}
	public void setDeliverydate(Date deliverydate) {
		this.deliverydate = deliverydate;
	}
	public Vector<CheckoutItem> getCheckoutItems() {
		return checkoutItems;
	}
	public void setCheckoutItems(Vector<CheckoutItem> checkoutItems) {
		this.checkoutItems = checkoutItems;
	}

}
