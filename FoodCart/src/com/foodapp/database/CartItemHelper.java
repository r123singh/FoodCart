package com.foodapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CartItemHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = GlobaHelper.DATABASE_VERSION;
	private static final String DATABASE_NAME = "foodvilla.db";
	public static final String CART_ITEM_TABLE_NAME = "cartitem";

	public static final String ID = "id";
	public static final String CART_ID = "cart_id";
	public static final String ITEM_NAME = "item_name";
	public static final String ITEM_PRICE = "item_price";
	public static final String ITEM_QUANTITY = "item_quantity";
	public static final String ITEM_SUBTOTAL = "item_subtotal";
	public static final String ITEM_PACKAGING = "item_packaging";
	public static final String ITEM_DISCOUNT = "item_discount";
	public static final String ITEM_TOTAL = "item_total";
	public static final String ITEM_TAX = "item_tax";

	public static final String CART_ITEM_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + CART_ITEM_TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CART_ID + " BIGINT," + ITEM_NAME
			+ " TEXT," + ITEM_PRICE + " DOUBLE," + ITEM_QUANTITY + " INTEGER," + ITEM_SUBTOTAL + " DOUBLE," + ITEM_PACKAGING + " DOUBLE," + ITEM_DISCOUNT + " DOUBLE," + ITEM_TOTAL + " DOUBLE,"
			+ ITEM_TAX + " DOUBLE)";

	public CartItemHelper(Context context, String name, CursorFactory factory, int version) {
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
