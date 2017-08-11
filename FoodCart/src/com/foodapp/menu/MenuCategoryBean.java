package com.foodapp.menu;

public class MenuCategoryBean {
	private boolean valid;
	private MenuFoodCategory menufoodcategory;
	private long category;
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	private String categoryName;
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	private String imageName;
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public MenuFoodCategory getMenufoodcategory() {
		return menufoodcategory;
	}
	public void setMenufoodcategory(MenuFoodCategory menufoodcategory) {
		this.menufoodcategory = menufoodcategory;
	}
	public long getCategory() {
		return category;
	}
	public void setCategory(long category) {
		this.category = category;
	}

}
