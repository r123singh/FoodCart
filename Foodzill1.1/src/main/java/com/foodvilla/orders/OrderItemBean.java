package com.foodvilla.orders;

public class OrderItemBean {

	private int allorderitemsid;
	private long orderItemid;
	private long orderid;
	private String itemName;
	private String itemDescription;
	private String itemRemarks;
	private double itemPrice;
	private int itemQuantity;
	private String itemImage;
	private String errormsg;
	private boolean valid;
	private Orderitem orderitem;
	
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getItemRemarks() {
		return itemRemarks;
	}
	public void setItemRemarks(String itemRemarks) {
		this.itemRemarks = itemRemarks;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public String getItemImage() {
		return itemImage;
	}
	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public int getAllorderitemsid() {
		return allorderitemsid;
	}
	public void setAllorderitemsid(int allorderitemsid) {
		this.allorderitemsid = allorderitemsid;
	}
	public long getOrderid() {
		return orderid;
	}
	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}
	public long getOrderItemid() {
		return orderItemid;
	}
	public void setOrderItemid(long orderItemid) {
		this.orderItemid = orderItemid;
	}
	public Orderitem getOrderitem() {
		return orderitem;
	}
	public void setOrderitem(Orderitem orderitem) {
		this.orderitem = orderitem;
	}

}
