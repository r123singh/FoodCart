package com.foodapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class OrderItemHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = GlobaHelper.DATABASE_VERSION;
	private static final String DATABASE_NAME = "foodvilla";
	private static final String ORDERITEM_TABLE_NAME = "orderitem";
	public static final String ORDERITEM_ID = "id";
	public static final String ORDER_ID = "order_id";
	public static final String ORDERITEM_PRICE = "item_price";
	public static final String ORDERITEM_REMARKS = "item_remarks";
	public static final String ORDERITEM_NAME = "item_name";
	public static final String ORDERITEM_QUANTITY = "item_quantity";
	public static final String ORDERITEM_SUBTOTAL = "item_subtotal";
	public static final String ORDERITEM_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + ORDERITEM_TABLE_NAME + " (" + ORDERITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ORDER_ID + " INTEGER,"
			+ ORDERITEM_PRICE + " DOUBLE," + ORDERITEM_REMARKS + " TEXT," + ORDERITEM_NAME + " TEXT," + ORDERITEM_QUANTITY + " INTEGER," + ORDERITEM_SUBTOTAL + " DOUBLE)";

	public OrderItemHelper(Context context, String name, CursorFactory factory, int version) {
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
