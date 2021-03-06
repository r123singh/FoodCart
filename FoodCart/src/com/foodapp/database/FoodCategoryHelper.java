package com.foodapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class FoodCategoryHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 4;
	private static final String DATABASE_NAME = "foodvilla.db";
	public static final String FOOD_CATEGORY_TABLE_NAME = "foodcategory";

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String IMAGE = "image";
	public static final String RESTAURANT = "restaurant_id";
	public static final String FOOD_CATEGORY_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + FOOD_CATEGORY_TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT,"
			+ DESCRIPTION + " TEXT," + IMAGE + " TEXT," + RESTAURANT + " BIGINT)";

	public FoodCategoryHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
		onCreate(db);
	}

}
