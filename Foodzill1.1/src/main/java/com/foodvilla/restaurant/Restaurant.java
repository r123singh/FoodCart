package com.foodvilla.restaurant;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
public class Restaurant {
	private long id;
	private String name;
	private String placeid;
	private String placeimageurl;
	private String address;
	private String rating;
	private String location;
	private int esitmatedtime;
	private String opennow;
	private String deliverymode;
	private String paymentmode;
	private String active;
	private Date created;
	private double deliverycharges;
	private double minimumordercharges;
	private String foodcategory;

	public Restaurant() {
	}

	public Restaurant(long id, String name, String placeid, String placeimageurl, String address, String rating, String location, int esitmatedtime, String opennow, String deliverymode,
			String paymentmode, String active, Date created, double deliverycharges, double minimumordercharges, String foodcategory) {
		super();
		this.id = id;
		this.name = name;
		this.placeid = placeid;
		this.placeimageurl = placeimageurl;
		this.address = address;
		this.rating = rating;
		this.location = location;
		this.esitmatedtime = esitmatedtime;
		this.opennow = opennow;
		this.deliverymode = deliverymode;
		this.paymentmode = paymentmode;
		this.active = active;
		this.created = created;
		this.deliverycharges = deliverycharges;
		this.minimumordercharges = minimumordercharges;
		this.foodcategory = foodcategory;
	}

	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "restaurant_id")
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
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "location")
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "estimated_time")
	public int getEsitmatedtime() {
		return esitmatedtime;
	}

	public void setEsitmatedtime(int esitmatedtime) {
		this.esitmatedtime = esitmatedtime;
	}

	@Column(name = "delivery_mode")
	public String getDeliverymode() {
		return deliverymode;
	}
	public void setDeliverymode(String deliverymode) {
		this.deliverymode = deliverymode;
	}

	@Column(name = "payment_mode")
	public String getPaymentmode() {
		return paymentmode;
	}
	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}

	@Column(name = "created")
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	@Column(name = "delivery_charges")
	public double getDeliverycharges() {
		return deliverycharges;
	}
	public void setDeliverycharges(double deliverycharges) {
		this.deliverycharges = deliverycharges;
	}
	@Column(name = "minimum_order_charges")
	public double getMinimumordercharges() {
		return minimumordercharges;
	}
	public void setMinimumordercharges(double minimumordercharges) {
		this.minimumordercharges = minimumordercharges;
	}
	@Column(name = "food_categories")
	public String getFoodcategory() {
		return foodcategory;
	}
	public void setFoodcategory(String foodcategory) {
		this.foodcategory = foodcategory;
	}
	@Column(name = "place_id")
	public String getPlaceid() {
		return placeid;
	}
	public void setPlaceid(String placeid) {
		this.placeid = placeid;
	}
	@Column(name = "place_image")
	public String getPlaceimageurl() {
		return placeimageurl;
	}

	public void setPlaceimageurl(String placeimageurl) {
		this.placeimageurl = placeimageurl;
	}
	@Column(name = "rating")
	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	@Column(name = "open_now")
	public String getOpennow() {
		return opennow;
	}

	public void setOpennow(String opennow) {
		this.opennow = opennow;
	}
	@Column(name = "active")
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}
