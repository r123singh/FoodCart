package com.foodapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class RestaurantHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = GlobaHelper.DATABASE_VERSION;
	private static final String DATABASE_NAME = "foodvilla.db";
	public static final String RESTAURANT_TABLE_NAME = "restaurant";
	public static final String ID = "id";
	public static final String TITLE = "name";
	public static final String ADDRESS = "address";
	public static final String CONTACT = "contact";
	public static final String CATEGORY = "category";
	public static final String LOCATION = "location_id";
	public static final String VEG = "veg";
	public static final String DELIVERY_TYPE = "delivery_type";
	public static final String AVAILABLE_NOW = "available_now";
	public static final String DELIVERY_CHARGES = "delivery_charges";
	public static final String MINIMUM_ORDER_CHARGES = "minimum_order_value";
	public static final String PAYMENT_MODE = "payment_mode";
	public static final String ESTIMATED_DELIVERY = "estimated_delivery";
	public static final String RESTAURANT_DISTANCE = "restaurant_distance";
	public static final String FOOD_CATEGORY = "food_category";
	public static final String IMAGE = "image_id";

	public static final String RESTAURANT_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + RESTAURANT_TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + TITLE + " TEXT, " + ADDRESS
			+ " TEXT," + CONTACT + " TEXT," + CATEGORY + " TEXT," + VEG + " TEXT," + LOCATION + " TEXT," + DELIVERY_TYPE + " TEXT," + AVAILABLE_NOW + " TEXT," + DELIVERY_CHARGES + " DOUBLE,"
			+ MINIMUM_ORDER_CHARGES + " DOUBLE," + PAYMENT_MODE + " TEXT," + ESTIMATED_DELIVERY + " TEXT," + RESTAURANT_DISTANCE + " TEXT," + FOOD_CATEGORY + " TEXT," + IMAGE + " TEXT)";

	public RestaurantHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		onCreate(db);
	}

}
