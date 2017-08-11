package com.foodapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class GlobaHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 12;
	private static final String DATABASE_NAME = "foodvilla.db";

	public GlobaHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			// RestaurantHelper
			db.execSQL(RestaurantHelper.RESTAURANT_TABLE_CREATE);
			ContentValues contentValues = new ContentValues();
			contentValues.put(RestaurantHelper.TITLE, "Quizine");
			contentValues.put(RestaurantHelper.ADDRESS, "police bazar");
			contentValues.put(RestaurantHelper.CONTACT, "999999999");
			contentValues.put(RestaurantHelper.CATEGORY, "north indian");
			contentValues.put(RestaurantHelper.LOCATION, "shillong");
			contentValues.put(RestaurantHelper.VEG, "veg");
			contentValues.put(RestaurantHelper.DELIVERY_TYPE, "cash on delivery");
			contentValues.put(RestaurantHelper.AVAILABLE_NOW, "TRUE");
			contentValues.put(RestaurantHelper.DELIVERY_CHARGES, 29.0);
			contentValues.put(RestaurantHelper.MINIMUM_ORDER_CHARGES, 250);
			contentValues.put(RestaurantHelper.PAYMENT_MODE, "cash on delivery");
			contentValues.put(RestaurantHelper.ESTIMATED_DELIVERY, "49 minutes appro");
			contentValues.put(RestaurantHelper.RESTAURANT_DISTANCE, "1.6 kilometers");
			contentValues.put(RestaurantHelper.FOOD_CATEGORY, "Italian,Chinese,Indian");
			contentValues.put(RestaurantHelper.IMAGE, "quizineicon");
			long restaurantid = db.insert(RestaurantHelper.RESTAURANT_TABLE_NAME, null, contentValues);
			// SearchLocationHelper
			db.execSQL(SearchLocationHelper.SEARCH_LOCATION_TABLE_CREATE);
			// FoodCategoryHelper
			db.execSQL(FoodCategoryHelper.FOOD_CATEGORY_TABLE_CREATE);
			ContentValues contentValues3 = new ContentValues();
			contentValues3.put(FoodCategoryHelper.NAME, "veg soup");
			contentValues3.put(FoodCategoryHelper.DESCRIPTION, "Veg Soup");
			contentValues3.put(FoodCategoryHelper.IMAGE, "soupcategory");
			contentValues3.put(FoodCategoryHelper.RESTAURANT, restaurantid);
			ContentValues contentValues4 = new ContentValues();
			contentValues4.put(FoodCategoryHelper.NAME, "Salads");
			contentValues4.put(FoodCategoryHelper.DESCRIPTION, "Salads");
			contentValues4.put(FoodCategoryHelper.IMAGE, "saladcategory");
			contentValues4.put(FoodCategoryHelper.RESTAURANT, restaurantid);
			long foddcategoryid_1 = db.insert(FoodCategoryHelper.FOOD_CATEGORY_TABLE_NAME, null, contentValues3);
			long foodcategoryid_2 = db.insert(FoodCategoryHelper.FOOD_CATEGORY_TABLE_NAME, null, contentValues4);
			// FooditemHelper
			db.execSQL(FooditemHelper.FOOD_ITEM_TABLE_CREATE);
			ContentValues contentValues5 = new ContentValues();
			contentValues5.put(FooditemHelper.NAME, "walford salad");
			contentValues5.put(FooditemHelper.DESCRIPTION, "A rich salad of apples,walnuts and pinapple");
			contentValues5.put(FooditemHelper.PRICE, 220.0);
			contentValues5.put(FooditemHelper.FOODCATEGORY, foddcategoryid_1);
			contentValues5.put(FooditemHelper.IMAGE, "walfordsalad");
			ContentValues contentValues6 = new ContentValues();
			contentValues6.put(FooditemHelper.NAME, "Russian Salad");
			contentValues6.put(FooditemHelper.DESCRIPTION, "Diced potato,green peas,carrots and apple in mayo");
			contentValues6.put(FooditemHelper.PRICE, 210.0);
			contentValues6.put(FooditemHelper.FOODCATEGORY, foodcategoryid_2);
			contentValues6.put(FooditemHelper.IMAGE, "russiansalad");
			db.insert(FooditemHelper.FOOD_ITEM_TABLE_NAME, null, contentValues5);
			db.insert(FooditemHelper.FOOD_ITEM_TABLE_NAME, null, contentValues6);
			// CartHelper
			db.execSQL(CartHelper.CART_TABLE_CREATE);
			// CartItemHelper
			db.execSQL(CartItemHelper.CART_ITEM_TABLE_CREATE);
			// OrderHelper
			db.execSQL(OrderHelper.ORDER_TABLE_CREATE);
			// OrderItemHelper
			db.execSQL(OrderItemHelper.ORDERITEM_TABLE_CREATE);
		} catch (Exception e) {
			Log.e("GlobalHelper:", e.getLocalizedMessage());
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
