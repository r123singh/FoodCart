package com.foodapp.order;

import java.io.Serializable;


public class Foodorderitem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long orderitemid;
	private long orderid;
	private double orderitemprice;
	private String orderitemremarks;
	private String orderitemname;
	private double orderitemsubtotal;
	private int orderitemquantity;

	public Foodorderitem() {
	}

	public long getOrderitemid() {
		return orderitemid;
	}
	public void setOrderitemid(long orderitemid) {
		this.orderitemid = orderitemid;
	}
	public double getOrderitemprice() {
		return orderitemprice;
	}
	public void setOrderitemprice(double orderitemprice) {
		this.orderitemprice = orderitemprice;
	}
	public String getOrderitemremarks() {
		return orderitemremarks;
	}
	public void setOrderitemremarks(String orderitemremarks) {
		this.orderitemremarks = orderitemremarks;
	}
	public String getOrderitemname() {
		return orderitemname;
	}
	public void setOrderitemname(String orderitemname) {
		this.orderitemname = orderitemname;
	}
	public double getOrderitemsubtotal() {
		return orderitemsubtotal;
	}
	public void setOrderitemsubtotal(double orderitemsubtotal) {
		this.orderitemsubtotal = orderitemsubtotal;
	}
	public int getOrderitemquantity() {
		return orderitemquantity;
	}
	public void setOrderitemquantity(int orderitemquantity) {
		this.orderitemquantity = orderitemquantity;
	}

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

}
