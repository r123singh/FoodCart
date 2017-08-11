package com.foodapp.cart;

import java.util.Date;
import java.util.Vector;

import android.graphics.Bitmap;

import com.foodapp.order.FoodOrderListDetail;

public class CartListDetail {

	public static final String TYPE_CART_IMAGE_DETAIL = "type_cart_image_detail";
	public static final String TYPE_CART_EDIT_ORDER_DETAIL = "type_cart_edit_order_detail";
	public static final String TYPE_CART_ORDER_ITEMS_DETAIL = "type_cart_order_items_detail";
	public static final String TYPE_CART_SUB_TOTAL_DETAIL = "type_cart_sub_total_detail";
	public static final String TYPE_CART_DELIVERY_CHARGE_DETAIL = "type_cart_delivery_charge_detail";
	public static final String TYPE_CART_PACKAGING_CHARGE_DETAIL = "type_cart_packaging_detail";
	public static final String TYPE_CART_TOTAL_CHARGE_DETAIL = "type_cart_total_charge_detail";
	public static final String TYPE_CART_DELIVERY_DATE_DETAIL = "type_cart_delivery_date_detail";
	public static final String TYPE_CART_DELIVERY_TIME_STRING_DETAIL = "type_cart_delivery_time_string_detail";
	public static final String TYPE_CART_DELIVERY_TIME_PRIORITY_DETAIL = "type_cart_delivery_time_priority_detail";
	public static final String TYPE_CART_CHANGE_DELIVERY_ADDRESS_DETAIL = "type_cart_change_delivery_address_detail";
	public static final String TYPE_CART_CHECKOUT_BUTTON_DETAIL = "type_cart_checkout_button_detail";

	public static final int POS_CART_IMAGE_DETAIL = 0;
	public static final int POS_CART_EDIT_ORDER_DETAIL = 1;
	public static final int POS_CART_ORDER_ITEMS_DETAIL = 2;
	public static final int POS_CART_SUB_TOTAL_DETAIL = 3;
	public static final int POS_CART_DELIVERY_CHARGE_DETAIL = 4;
	public static final int POS_CART_PACKAGING_CHARGE_DETAIL = 5;
	public static final int POS_CART_TOTAL_CHARGE_DETAIL = 6;
	public static final int POS_CART_DELIVERY_DATE_DETAIL = 7;
	public static final int POS_CART_DELIVERY_TIME_STRING_DETAIL = 8;
	public static final int POS_CART_DELIVERY_TIME_PRIORITY_DETAIL = 9;
	public static final int POS_CART_CHANGE_DELIVERY_ADDRESS_DETAIL = 10;
	public static final int POS_CART_CHECKOUT_BUTTON_DETAIL = 11;

	private long id;
	private double deliverycharges;
	private double totalcharges;
	private double taxcharges;
	private double discountcharges;
	private double packagingcharges;
	private Date deliveryDate;
	private int itemscount;
	private Bitmap restaurantbitmap;
	private double subtotal;
	private boolean deliverAsap;
	private String detailtype;
	private Vector<FoodOrderListDetail> foodOrderListDetails;
	

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

	public int getItemscount() {
		return itemscount;
	}
	public void setItemscount(int itemscount) {
		this.itemscount = itemscount;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public boolean isDeliverAsap() {
		return deliverAsap;
	}
	public void setDeliverAsap(boolean deliverAsap) {
		this.deliverAsap = deliverAsap;
	}
	public String getDetailtype() {
		return detailtype;
	}
	public void setDetailtype(String detailtype) {
		this.detailtype = detailtype;
	}
	public Vector<FoodOrderListDetail> getFoodOrderListDetails() {
		return foodOrderListDetails;
	}
	public void setFoodOrderListDetails(Vector<FoodOrderListDetail> foodOrderListDetails) {
		this.foodOrderListDetails = foodOrderListDetails;
	}
	public Bitmap getRestaurantbitmap() {
		return restaurantbitmap;
	}
	public void setRestaurantbitmap(Bitmap restaurantbitmap) {
		this.restaurantbitmap = restaurantbitmap;
	}
}
