package com.foodapp.menu;

import com.foodapp.restaurants.Restaurant;

public class Menu {

	private Menuitems[] menuitems;
	private Restaurant restaurant;
	private MenuitemRates[] rates;
	private String menutitle;
	private MenuCategories[] categories;

	public Menuitems[] getMenuitems() {
		return menuitems;
	}
	public void setMenuitems(Menuitems[] menuitems) {
		this.menuitems = menuitems;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public MenuitemRates[] getRates() {
		return rates;
	}
	public void setRates(MenuitemRates[] rates) {
		this.rates = rates;
	}
	public String getMenutitle() {
		return menutitle;
	}
	public void setMenutitle(String menutitle) {
		this.menutitle = menutitle;
	}
	public MenuCategories[] getCategories() {
		return categories;
	}
	public void setCategories(MenuCategories[] categories) {
		this.categories = categories;
	}

}
