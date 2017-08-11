package com.foodapp.order;

import java.io.Serializable;
import java.util.ArrayList;

public class Foodorder implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderstatus;
	private String orderdeliveryaddress;
	private String orderdeliverydate;
	private long orderrestaurant;
	private long orderid;
	private double ordersubtotal;
	private double orderdeliverycharge;
	private double ordertotalamount;
	private double ordertotaltax;
	private double orderpackagingcharges;
	private ArrayList<Foodorderitem> orderitems;

	public Foodorder() {
	}

	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
	public String getOrderdeliveryaddress() {
		return orderdeliveryaddress;
	}
	public void setOrderdeliveryaddress(String orderdeliveryaddress) {
		this.orderdeliveryaddress = orderdeliveryaddress;
	}
	public String getOrderdeliverydate() {
		return orderdeliverydate;
	}
	public void setOrderdeliverydate(String orderdeliverydate) {
		this.orderdeliverydate = orderdeliverydate;
	}
	public long getOrderid() {
		return orderid;
	}
	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}
	public double getOrdersubtotal() {
		return ordersubtotal;
	}
	public void setOrdersubtotal(double ordersubtotal) {
		this.ordersubtotal = ordersubtotal;
	}
	public double getOrderdeliverycharge() {
		return orderdeliverycharge;
	}
	public void setOrderdeliverycharge(double orderdeliverycharge) {
		this.orderdeliverycharge = orderdeliverycharge;
	}
	public double getOrdertotalamount() {
		return ordertotalamount;
	}
	public void setOrdertotalamount(double ordertotalamount) {
		this.ordertotalamount = ordertotalamount;
	}
	public double getOrdertotaltax() {
		return ordertotaltax;
	}
	public void setOrdertotaltax(double ordertotaltax) {
		this.ordertotaltax = ordertotaltax;
	}
	public ArrayList<Foodorderitem> getOrderitems() {
		return orderitems;
	}
	public void setOrderitems(ArrayList<Foodorderitem> orderitems) {
		this.orderitems = orderitems;
	}
	public double getOrderpackagingcharges() {
		return orderpackagingcharges;
	}
	public void setOrderpackagingcharges(double orderpackagingcharges) {
		this.orderpackagingcharges = orderpackagingcharges;
	}

	public long getOrderrestaurant() {
		return orderrestaurant;
	}

	public void setOrderrestaurant(long orderrestaurant) {
		this.orderrestaurant = orderrestaurant;
	}

}
