package com.foodvilla.orders;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "foodorder")
public class FoodOrder implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long orderid;

	private double totalPrice;

	private double totalTax;

	private double subtotal;

	private double packaging;

	private double delivery;

	private String customerMobile;

	private Date orderDate;

	private String deliveryAddress;

	private long restaurantId;

	private String status;

	public FoodOrder() {
	}

	public FoodOrder(long orderid, double totalPrice, double totalTax, double subtotal, double packaging, double delivery, String customerMobile, Date orderDate, String deliveryAddress,
			long restaurantId, String status) {
		super();
		this.orderid = orderid;
		this.totalPrice = totalPrice;
		this.totalTax = totalTax;
		this.subtotal = subtotal;
		this.packaging = packaging;
		this.delivery = delivery;
		this.customerMobile = customerMobile;
		this.orderDate = orderDate;
		this.deliveryAddress = deliveryAddress;
		this.setRestaurantId(restaurantId);
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	@Column(name = "totalprice")
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "totaltax")
	public double getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}

	@Column(name = "orderdate")
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Column(name = "deliveryaddress")
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "subtotal")
	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	@Column(name = "packaging")
	public double getPackaging() {
		return packaging;
	}

	public void setPackaging(double packaging) {
		this.packaging = packaging;
	}
	@Column(name = "delivery")
	public double getDelivery() {
		return delivery;
	}

	public void setDelivery(double delivery) {
		this.delivery = delivery;
	}
	@Column(name = "customer_mobile")
	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	@Column(name = "restaurant_id")
	public long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}
}
