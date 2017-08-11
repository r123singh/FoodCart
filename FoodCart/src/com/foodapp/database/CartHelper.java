package com.foodapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CartHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = GlobaHelper.DATABASE_VERSION;
	private static final String DATABASE_NAME = "foodvilla.db";
	public static final String CART_TABLE_NAME = "cart";

	public static final String ID = "id";
	public static final String TOTAL_CHARGES = "total_charges";
	public static final String DELIVERY_CHARGES = "delivery_charges";
	public static final String DISCOUNT_CHARGES = "discount_charges";
	public static final String PACKING_CHARGES = "packing_charges";
	public static final String TAX_CHARGES = "tax_charges";
	public static final String RESTAURANT = "restaurant_id";
	public static final String SUBTOTAL_CHARGES = "subtotal_charges";
	public static final String ITEM_COUNT = "item_count";
	public static final String RESTAURANT_IMAGE = "restaurant_image";

	public static final String CART_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + CART_TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + TOTAL_CHARGES + " DOUBLE," + DELIVERY_CHARGES
			+ " DOUBLE," + DISCOUNT_CHARGES + " DOUBLE," + PACKING_CHARGES + " DOUBLE," + TAX_CHARGES + " DOUBLE," + RESTAURANT + "  INTEGER," + SUBTOTAL_CHARGES + " DOUBLE," + ITEM_COUNT
			+ " INTEGER," + RESTAURANT_IMAGE + " TEXT)";

	public CartHelper(Context context, String name, CursorFactory factory, int version) {
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
