package com.foodvilla.orders;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.CascadeType;
@Entity
@Table(name = "orderitem")
public class Orderitem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long orderitemid;
	private String name;
	private String description;
	private String remarks;
	private double price;
	private int quantity;
	private double subtotal;
	private FoodOrder foodOrder;

	public Orderitem(long id, String name, String description, String remarks, double price, int quantity, FoodOrder foodOrder) {
		super();
		this.setOrderitemid(id);
		this.name = name;
		this.description = description;
		this.remarks = remarks;
		this.price = price;
		this.quantity = quantity;
		this.setFoodOrder(foodOrder);
	}

	public Orderitem(FoodOrder foodOrder) {
		this.setFoodOrder(foodOrder);
	}
	public Orderitem() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orderitem_id")
	public long getOrderitemid() {
		return orderitemid;
	}

	public void setOrderitemid(long orderitemid) {
		this.orderitemid = orderitemid;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "price")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name = "quantity")
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int qunatity) {
		this.quantity = qunatity;
	}

	@Column(name = "subtotal")
	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	public FoodOrder getFoodOrder() {
		return foodOrder;
	}

	public void setFoodOrder(FoodOrder foodOrder) {
		this.foodOrder = foodOrder;
	}

}
