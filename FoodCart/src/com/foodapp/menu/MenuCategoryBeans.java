package com.foodapp.menu;

import java.util.Vector;

public class MenuCategoryBeans {

	private Vector<MenuCategoryBean> menuCategoryBeans;
	private String errorMessage;
	private boolean valid;

	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public Vector<MenuCategoryBean> getMenuCategoryBeans() {
		return menuCategoryBeans;
	}
	public void setMenuCategoryBeans(Vector<MenuCategoryBean> menuCategoryBeans) {
		this.menuCategoryBeans = menuCategoryBeans;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
