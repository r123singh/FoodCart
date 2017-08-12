package com.foodvilla.menu;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

public class MenuItemBean {
	private String menuitemname;
	private String menuitemdescription;
	private String menuitemprice;
	private String menuitemcategory;
	private String menuitempackagingcharges;
	private String menuitemtaxcharges;
	private String menuitemdiscountcharges;
	private String menuitemrestaurant;
	private String menuitemcategoryimage;
	private String menuitemvegetarian;
	private boolean valid;
	private String errormessage;
	private MenuItem menuitem;
	private JSONObject menuitemsjson;
	private ArrayList<MenuItem> menuItems;
	private HashMap<String, String> propertymap;
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getErrormessage() {
		return errormessage;
	}
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	public MenuItem getMenuitem() {
		return menuitem;
	}
	public void setMenuitem(MenuItem menuitem) {
		this.menuitem = menuitem;
	}
	public String getMenuitemname() {
		return menuitemname;
	}
	public void setMenuitemname(String menuitemname) {
		this.menuitemname = menuitemname;
	}
	public String getMenuitemdescription() {
		return menuitemdescription;
	}
	public void setMenuitemdescription(String menuitemdescription) {
		this.menuitemdescription = menuitemdescription;
	}
	public String getMenuitemprice() {
		return menuitemprice;
	}
	public void setMenuitemprice(String menuitemprice) {
		this.menuitemprice = menuitemprice;
	}
	public String getMenuitemcategory() {
		return menuitemcategory;
	}
	public void setMenuitemcategory(String menuitemcategory) {
		this.menuitemcategory = menuitemcategory;
	}
	public String getMenuitempackagingcharges() {
		return menuitempackagingcharges;
	}
	public void setMenuitempackagingcharges(String menuitempackagingcharges) {
		this.menuitempackagingcharges = menuitempackagingcharges;
	}
	public String getMenuitemtaxcharges() {
		return menuitemtaxcharges;
	}
	public void setMenuitemtaxcharges(String menuitemtaxcharges) {
		this.menuitemtaxcharges = menuitemtaxcharges;
	}
	public String getMenuitemdiscountcharges() {
		return menuitemdiscountcharges;
	}
	public void setMenuitemdiscountcharges(String menuitemdiscountcharges) {
		this.menuitemdiscountcharges = menuitemdiscountcharges;
	}
	public String getMenuitemrestaurant() {
		return menuitemrestaurant;
	}
	public void setMenuitemrestaurant(String menuitemrestaurant) {
		this.menuitemrestaurant = menuitemrestaurant;
	}
	public String getMenuitemcategoryimage() {
		return menuitemcategoryimage;
	}
	public void setMenuitemcategoryimage(String menuitemcategoryimage) {
		this.menuitemcategoryimage = menuitemcategoryimage;
	}
	public String getMenuitemvegetarian() {
		return menuitemvegetarian;
	}
	public void setMenuitemvegetarian(String menuitemvegetarian) {
		this.menuitemvegetarian = menuitemvegetarian;
	}
	public JSONObject getMenuitemsjson() {
		return menuitemsjson;
	}
	public void setMenuitemsjson(JSONObject menuitemsjson) {
		this.menuitemsjson = menuitemsjson;
	}
	public ArrayList<MenuItem> getMenuItems() {
		return menuItems;
	}
	public void setMenuItems(ArrayList<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}
	public HashMap<String, String> getPropertymap() {
		return propertymap;
	}
	public void setPropertymap(HashMap<String, String> propertymap) {
		this.propertymap = propertymap;
	}

}
