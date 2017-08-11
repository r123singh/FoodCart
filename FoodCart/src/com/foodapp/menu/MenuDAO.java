package com.foodapp.menu;

import java.util.Vector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.foodapp.database.AppDatabaseInitializer;
import com.foodapp.database.FoodCategoryHelper;

public class MenuDAO {

	public static MenuCategoryBeans menuCategories(Context context) {
		boolean success = false;
		SQLiteDatabase menucategoryDatabase = null;
		MenuCategoryBeans menuCategoryBeans = new MenuCategoryBeans();
		Vector<MenuCategoryBean> categoryBeans = new Vector<MenuCategoryBean>();
		try {
			menucategoryDatabase = AppDatabaseInitializer.getglobalReadableDatabase();
			Cursor cursor = menucategoryDatabase.rawQuery("select * from menucategory", null);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						String categoryName = cursor.getString(cursor.getColumnIndex("name"));
						if (categoryName.contains("_")) {
							categoryName.replace("_", " ");
						}
						String image = cursor.getString(cursor.getColumnIndex("image"));
						MenuCategoryBean menuCategoryBean = new MenuCategoryBean();
						menuCategoryBean.setCategoryName(categoryName);
						menuCategoryBean.setImageName(image);
						menuCategoryBean.setValid(true);
						categoryBeans.add(menuCategoryBean);
					} while (cursor.moveToNext());
					menuCategoryBeans.setMenuCategoryBeans(categoryBeans);
					success = true;
					menuCategoryBeans.setErrorMessage(null);
				} else {
					success = true;
					menuCategoryBeans.setErrorMessage("No Menu Categories");
					menuCategoryBeans.setMenuCategoryBeans(null);
				}
			}
		} catch (SQLiteException e) {
			Log.e("Error", e.getLocalizedMessage());
			menuCategoryBeans.setErrorMessage(e.getMessage());
			success = false;
		} finally {
			menucategoryDatabase.close();
			if (!success) {
				menuCategoryBeans.setMenuCategoryBeans(null);
				menuCategoryBeans.setValid(false);
			} else {
				menuCategoryBeans.setValid(true);
			}
		}

		return menuCategoryBeans;
	}

	public static FoodCategoriesBean fetchAllfoodCategoriesByRestaurant(FoodCategoriesBean foodCategoriesBean, Context context) {
		Vector<MenuFoodCategory> menuFoodCategories = null;
		SQLiteDatabase foodCategoryDatabase = null;
		Cursor cursor = null;
		try {
			foodCategoryDatabase = AppDatabaseInitializer.getglobalReadableDatabase();
			String tablename = FoodCategoryHelper.FOOD_CATEGORY_TABLE_NAME;
			String[] columns = new String[]{FoodCategoryHelper.DESCRIPTION, FoodCategoryHelper.NAME, FoodCategoryHelper.ID, FoodCategoryHelper.IMAGE};
			String selectioncriteria = FoodCategoryHelper.RESTAURANT + "=?";
			String[] arguements = new String[]{String.valueOf(foodCategoriesBean.getRestaurantid())};

			cursor = foodCategoryDatabase.query(tablename, columns, selectioncriteria, arguements, null, null, null);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					menuFoodCategories = new Vector<MenuFoodCategory>();
					do {
						String categoryName = cursor.getString(cursor.getColumnIndex(FoodCategoryHelper.NAME));
						String categoryDescription = cursor.getString(cursor.getColumnIndex(FoodCategoryHelper.DESCRIPTION));
						long categoryid = cursor.getLong(cursor.getColumnIndex(FoodCategoryHelper.ID));
						MenuFoodCategory menuFoodCategory = new MenuFoodCategory();
						menuFoodCategory.setDescription(categoryDescription);
						menuFoodCategory.setName(categoryName);
						menuFoodCategory.setId(categoryid);
						menuFoodCategory.setRestaurantId(foodCategoriesBean.getRestaurantid());
						menuFoodCategories.add(menuFoodCategory);
					} while (cursor.moveToNext());
					foodCategoriesBean.setMenuFoodCategories(menuFoodCategories);
					foodCategoriesBean.setValid(true);
				} else {
					foodCategoriesBean.setValid(false);
					foodCategoriesBean.setErrmessage("Failed to get results");
				}
			} else {
				foodCategoriesBean.setValid(true);
				foodCategoriesBean.setErrmessage("No food categories found");
			}

		} catch (Exception e) {
			foodCategoriesBean.setValid(false);
			foodCategoriesBean.setErrmessage("Exception" + e.getLocalizedMessage());
		}
		return foodCategoriesBean;
	}

	public static MenuCategoryBean savemenucategory(MenuCategoryBean categorybean) {
		SQLiteDatabase menufoodcategorydatabase=null;
		try {
			menufoodcategorydatabase=AppDatabaseInitializer.getglobalWritableDatabase();
			MenuFoodCategory category=categorybean.getMenufoodcategory();
			ContentValues contentvalues=new ContentValues();
			contentvalues.put(FoodCategoryHelper.NAME, category.getName());
			contentvalues.put(FoodCategoryHelper.RESTAURANT, category.getRestaurantId());
			contentvalues.put(FoodCategoryHelper.DESCRIPTION, category.getDescription());
			long menucategory=menufoodcategorydatabase.insert(FoodCategoryHelper.FOOD_CATEGORY_TABLE_NAME, null, contentvalues);
			categorybean.setCategory(menucategory);
			categorybean.setValid(true);
		} catch (Exception e) {
			categorybean.setValid(false);
		}
		return categorybean;
	}

}
