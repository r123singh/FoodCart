package com.foodvilla.menu;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.foodvilla.restaurant.Restaurant;

@Entity
@Table(name = "menuitem")
public class MenuItem {

	private long id;
	private String name;
	private String description;
	private double price;
	private double discount;
	private double taxcharges;
	private double packagingcharges;
	private String category;
	private String categoryimage;
	private String vegetarian;
	private Restaurant restaurant;
	private Date created;

	public MenuItem() {
	}

	public MenuItem(long id, String name, String description, double price, double discount, double taxcharges, double packagingcharges, String category, String categoryimage, String vegetarian,
			Restaurant restaurant, Date created) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.taxcharges = taxcharges;
		this.packagingcharges = packagingcharges;
		this.category = category;
		this.categoryimage = categoryimage;
		this.vegetarian = vegetarian;
		this.restaurant = restaurant;
		this.created=created;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "menuitem_id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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

	@Column(name = "price")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name = "discount_charges")
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Column(name = "tax_charges")
	public double getTaxcharges() {
		return taxcharges;
	}
	public void setTaxcharges(double taxcharges) {
		this.taxcharges = taxcharges;
	}

	@Column(name = "packaging_charges")
	public double getPackagingcharges() {
		return packagingcharges;
	}
	public void setPackagingcharges(double packagingcharges) {
		this.packagingcharges = packagingcharges;
	}

	@Column(name = "category")
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "category_image")
	public String getCategoryimage() {
		return categoryimage;
	}
	public void setCategoryimage(String categoryimage) {
		this.categoryimage = categoryimage;
	}

	@Column(name = "vegetarian")
	public String getVegetarian() {
		return vegetarian;
	}
	public void setVegetarian(String vegetarian) {
		this.vegetarian = vegetarian;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "restaurant_id")
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Column(name = "created")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
