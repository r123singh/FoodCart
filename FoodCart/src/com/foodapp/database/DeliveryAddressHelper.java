package com.foodapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DeliveryAddressHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = GlobaHelper.DATABASE_VERSION;
	private static final String DATABASE_NAME = "foodvilla";

	private static final String DELIVERY_ADDRESS_TABLE_NAME = "deliveryaddress";

	private static final String FIRST_LINE = "firstline";
	private static final String SECOND_LINE = "secondline";
	private static final String THIRD_LINE = "thirdline";
	private static final String PINCODE = "pincode";
	private static final String CITY = "city";
	private static final String STATE = "state";

	private static final String DELIVERY_ADDRESS_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + DELIVERY_ADDRESS_TABLE_NAME + " (" + FIRST_LINE + " TEXT, " + SECOND_LINE + " TEXT," + THIRD_LINE + " TEXT," + PINCODE
			+ " TEXT," + CITY + " TEXT," + STATE + " /TEXT)";

	public DeliveryAddressHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DELIVERY_ADDRESS_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
