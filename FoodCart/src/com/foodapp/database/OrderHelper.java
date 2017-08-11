package com.foodapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class OrderHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = GlobaHelper.DATABASE_VERSION;
	private static final String DATABASE_NAME = "foodvilla";
	public static final String ORDER_TABLE_NAME = "foodorder";
	public static final String ORDER_ID = "id";
	public static final String ORDER_DATE = "order_date";
	public static final String ORDER_STATUS = "order_status";
	public static final String ORDER_TOTAL_AMOUNT = "total_amount";
	public static final String ORDER_TOTAL_TAX = "total_tax";
	public static final String ORDER_DELIVERY_CHARGE = "delivery_charge";
	public static final String ORDER_PACKAGING_CHARGE = "packaging_charge";
	public static final String ORDER_DELIVERY_ADDRESS = "delivery_address";
	public static final String ORDER_SUBTOTAL = "subtotal";

	public static final String ORDER_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + ORDER_TABLE_NAME + " (" + ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ORDER_DATE + " TEXT," + ORDER_STATUS
			+ " TEXT," + ORDER_TOTAL_AMOUNT + " DOUBLE," + ORDER_SUBTOTAL + " DOUBLE," + ORDER_TOTAL_TAX + " DOUBLE," + ORDER_PACKAGING_CHARGE + " DOUBLE," + ORDER_DELIVERY_CHARGE + " DOUBLE,"
			+ ORDER_DELIVERY_ADDRESS + " TEXT)";

	public OrderHelper(Context context, String name, CursorFactory factory, int version) {
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
