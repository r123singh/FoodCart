package com.foodapp.order;

import android.graphics.Bitmap;

public class FoodOrderListDetail {

	public static final String TYPE_ORDER_IMAGE_DETAIL = "type_image_detail";
	public static final String TYPE_ORDER_TITLE_DETAIL = "type_order_title_detail";
	public static final String TYPE_ORDER_DESCRIPTION_DETAIL = "type_order_description_detail";
	public static final String TYPE_ORDER_PRICE_STRING_DETAIL = "type_order_price_string_detail";
	public static final String TYPE_ORDER_PRICE_VALUE_DETAIL = "type_order_price_value_detail";
	public static final String TYPE_ORDER_REMARKS_EDIT_TEXT_DETAIL = "type_order_remarks_edit_text_detail";
	public static final String TYPE_ORDER_QUANTITY_DETAIL = "type_order_quantity_detail";
	public static final String TYPE_ORDER_ADD_BUTTON_DETAIL = "type_order_add_button_detail";

	public static final int POS_ORDER_IMAGE_DETAIL = 0;
	public static final int POS_ORDER_TITLE_DETAIL = 1;
	public static final int POS_ORDER_DESCRIPTION_DETAIL = 2;
	public static final int POS_ORDER_PRICE_STRING_DETAIL = 3;
	public static final int POS_ORDER_PRICE_VALUE_DETAIL = 4;
	public static final int POS_ORDER_REMARKS_EDIT_TEXT_DETAIL = 5;
	public static final int POS_ORDER_QUANTITY_DETAIL = 6;
	public static final int POS_ORDER_ADD_BUTTON_DETAIL = 7;

	private long cartitem;
	private long fooditem;
	private String detailtype;
	private String orderfoodName;
	private String orderfoodDescription;
	private double orderfoodPrice;
	private int quantity;
	private Bitmap orderfoodbitmap;
	private double subtotal;
	private String orderfoodremarks;

	public FoodOrderListDetail() {
	}

	public String getDetailtype() {
		return detailtype;
	}
	public void setDetailtype(String detailtype) {
		this.detailtype = detailtype;
	}
	public String getOrderfoodName() {
		return orderfoodName;
	}
	public void setOrderfoodName(String orderfoodName) {
		this.orderfoodName = orderfoodName;
	}
	public String getOrderfoodDescription() {
		return orderfoodDescription;
	}
	public void setOrderfoodDescription(String orderfoodDescription) {
		this.orderfoodDescription = orderfoodDescription;
	}
	public double getOrderfoodPrice() {
		return orderfoodPrice;
	}
	public void setOrderfoodPrice(double orderfoodPrice) {
		this.orderfoodPrice = orderfoodPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public String getOrderfoodremarks() {
		return orderfoodremarks;
	}

	public void setOrderfoodremarks(String orderfoodremarks) {
		this.orderfoodremarks = orderfoodremarks;
	}

	public long getFooditem() {
		return fooditem;
	}

	public void setFooditem(long fooditem) {
		this.fooditem = fooditem;
	}

	public long getCartitem() {
		return cartitem;
	}

	public void setCartitem(long cartitem) {
		this.cartitem = cartitem;
	}

	public Bitmap getOrderfoodbitmap() {
		return orderfoodbitmap;
	}

	public void setOrderfoodbitmap(Bitmap orderfoodbitmap) {
		this.orderfoodbitmap = orderfoodbitmap;
	}

}
