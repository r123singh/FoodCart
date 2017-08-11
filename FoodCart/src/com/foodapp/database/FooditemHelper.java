package com.foodapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class FooditemHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = GlobaHelper.DATABASE_VERSION;
	private static final String DATABASE_NAME = "foodvilla.db";
	public static final String FOOD_ITEM_TABLE_NAME = "fooditems";

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String PRICE = "price";
	public static final String FOODCATEGORY = "food_category";
	public static final String IMAGE = "image";
	public static final String DISCOUNT = "discount";
	public static final String TAX = "tax";
	public static final String PACKAGING = "packaging";

	public static final String FOOD_ITEM_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + FOOD_ITEM_TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT," + DESCRIPTION
			+ " TEXT," + PRICE + " DOUBLE," + FOODCATEGORY + " BIGINT," + IMAGE + " TEXT)";

	public FooditemHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
		// TODO Auto-generated method stub
		onCreate(db);
	}

}
