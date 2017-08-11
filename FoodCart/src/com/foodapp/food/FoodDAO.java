package com.foodapp.food;

import java.util.Vector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.foodapp.database.AppDatabaseInitializer;
import com.foodapp.database.FooditemHelper;

public class FoodDAO {

	public static FoodItemsBeans fetchallFooditemsByCategory(FoodItemsBeans foodItemsBeans, Context context) {
		SQLiteDatabase foodItemDatabase = null;
		Vector<FoodItem> foodItems = null;
		try {
			foodItemDatabase = AppDatabaseInitializer.getglobalReadableDatabase();
			String tablename = FooditemHelper.FOOD_ITEM_TABLE_NAME;
			String[] columns = new String[]{FooditemHelper.ID, FooditemHelper.NAME, FooditemHelper.DESCRIPTION, FooditemHelper.PRICE, FooditemHelper.IMAGE};
			String selectioncriteria = FooditemHelper.FOODCATEGORY + "=?";
			String[] arguements = new String[]{String.valueOf(foodItemsBeans.getMenufoodCategoryId())};
			Cursor cursor = foodItemDatabase.query(tablename, columns, selectioncriteria, arguements, null, null, null);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					foodItems = new Vector<FoodItem>();
					do {
						String fooditemName = cursor.getString(cursor.getColumnIndex(FooditemHelper.NAME));
						String fooditemDesc = cursor.getString(cursor.getColumnIndex(FooditemHelper.DESCRIPTION));
						long fooditemId = cursor.getLong(cursor.getColumnIndex(FooditemHelper.ID));
						double fooditemPrice = cursor.getDouble(cursor.getColumnIndex(FooditemHelper.PRICE));
						FoodItem foodItem = new FoodItem();
						foodItem.setId(fooditemId);
						foodItem.setItemname(fooditemName);
						foodItem.setItemDescription(fooditemDesc);
						foodItem.setItemprice(fooditemPrice);
						foodItem.setMenufoodCategoryId(foodItemsBeans.getMenufoodCategoryId());
						foodItems.add(foodItem);
					} while (cursor.moveToNext());
					foodItemsBeans.setFoodItems(foodItems);
					foodItemsBeans.setValid(true);
				} else {
					foodItemsBeans.setValid(false);
					foodItemsBeans.setErrormessage("SQL Failed to get results");
				}
			} else {
				foodItemsBeans.setValid(true);
				foodItemsBeans.setErrormessage("No fooditems for the category" + foodItemsBeans.getMenufoodCategoryId());
			}
		} catch (Exception e) {
			foodItemsBeans.setValid(false);
			foodItemsBeans.setErrormessage("Exception" + e.getLocalizedMessage());
		}
		return foodItemsBeans;
	}

	public static FooditemBean fetchFooditemById(FooditemBean fooditemBean, Context context) {
		SQLiteDatabase foodItemDatabase = null;
		try {
			foodItemDatabase = AppDatabaseInitializer.getglobalReadableDatabase();
			String tablename = FooditemHelper.FOOD_ITEM_TABLE_NAME;
			String[] columns = new String[]{FooditemHelper.NAME, FooditemHelper.DESCRIPTION, FooditemHelper.PRICE, FooditemHelper.IMAGE};
			String selectioncriteria = FooditemHelper.ID + "=?";
			String[] arguements = new String[]{String.valueOf(fooditemBean.getItemId())};
			Cursor cursor = foodItemDatabase.query(tablename, columns, selectioncriteria, arguements, null, null, null);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						String name = cursor.getString(cursor.getColumnIndex(FooditemHelper.NAME));
						String description = cursor.getString(cursor.getColumnIndex(FooditemHelper.DESCRIPTION));
						double price = cursor.getDouble(cursor.getColumnIndex(FooditemHelper.PRICE));
						FoodItem foodItem = new FoodItem();
						foodItem.setItemDescription(description);
						foodItem.setItemname(name);
						foodItem.setItemprice(price);
						foodItem.setQuantity(1);
						fooditemBean.setFoodItem(foodItem);
						fooditemBean.setValid(true);
					} while (cursor.moveToNext());
				} else {
					fooditemBean.setValid(true);
					fooditemBean.setErrormsg("SQL Failed get results");
					Log.d("fetchFooditemById", "cursor.moveToFirst()");
				}
			} else {
				fooditemBean.setValid(false);
				fooditemBean.setErrormsg("Failed to retrive results");
				Log.d("fetchFooditemById", "cursor != null");
			}
		} catch (Exception e) {
			fooditemBean.setValid(false);
			fooditemBean.setErrormsg("Fetchfooditem" + e.getLocalizedMessage());
		}
		return fooditemBean;
	}

	public static FooditemBean savemenucategoryfooditem(FooditemBean fooditembean) {
		SQLiteDatabase fooitemdatabase = null;
		try {
			fooitemdatabase = AppDatabaseInitializer.getglobalWritableDatabase();
			FoodItem fooditem = fooditembean.getFoodItem();
			ContentValues contentvalues = new ContentValues();
			contentvalues.put(FooditemHelper.NAME, fooditem.getItemname());
			contentvalues.put(FooditemHelper.DESCRIPTION, fooditem.getItemDescription());
			contentvalues.put(FooditemHelper.PRICE, fooditem.getItemprice());
			contentvalues.put(FooditemHelper.DISCOUNT, fooditem.getItemdiscountcharges());
			contentvalues.put(FooditemHelper.TAX, fooditem.getItemtaxcharges());
			contentvalues.put(FooditemHelper.PACKAGING, fooditem.getItempackagingcharges());
			contentvalues.put(FooditemHelper.FOODCATEGORY, fooditem.getMenufoodCategoryId());
			fooitemdatabase.insert(FooditemHelper.FOOD_ITEM_TABLE_NAME, null, contentvalues);
			fooditembean.setValid(true);
		} catch (Exception e) {
			fooditembean.setValid(false);
		}
		return fooditembean;
	}
}
