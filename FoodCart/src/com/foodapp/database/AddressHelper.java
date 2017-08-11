package com.foodapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AddressHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = GlobaHelper.DATABASE_VERSION;
	private static final String DATABASE_NAME = "foodvilla.db";

	private static final String ADDRESS_TABLE_NAME = "address";

	private static final String FIRST_LINE = "firstline";
	private static final String SECOND_LINE = "secondline";
	private static final String THIRD_LINE = "thirdline";
	private static final String PINCODE = "pincode";
	private static final String CITY = "city";
	private static final String STATE = "state";
	private static final String TYPE = "type";
	private static final String ADDRESS_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS" + ADDRESS_TABLE_NAME + " (" + FIRST_LINE + " TEXT, " + SECOND_LINE + " TEXT," + THIRD_LINE + " TEXT," + PINCODE
			+ " TEXT," + CITY + " TEXT," + STATE + " /TEXT," + TYPE + " /TEXT)";

	public AddressHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(ADDRESS_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
