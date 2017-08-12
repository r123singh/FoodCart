package com.foodvilla.restaurant;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant_image_map")
public class RestaurantImageMap {
	private long id;
	private String restaurantplaceid;
	private String imagereference;
	private Date created;

	public RestaurantImageMap(long id, String restaurantplaceid, String imagereference) {
		super();
		this.id = id;
		this.restaurantplaceid = restaurantplaceid;
		this.imagereference = imagereference;
	}
	public RestaurantImageMap() {
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name = "restaurant_place_id")
	public String getRestaurantplaceid() {
		return restaurantplaceid;
	}
	public void setRestaurantplaceid(String restaurantplaceid) {
		this.restaurantplaceid = restaurantplaceid;
	}
	@Column(name = "photo_reference")
	public String getImagereference() {
		return imagereference;
	}
	public void setImagereference(String imagereference) {
		this.imagereference = imagereference;
	}
	@Column(name = "created")
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}

}
