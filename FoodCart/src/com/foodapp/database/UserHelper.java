package com.foodapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class UserHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = GlobaHelper.DATABASE_VERSION;
	private static final String DATABASE_NAME = "foodvilla.db";

	public static final String USER_TABLE_NAME = "user";

	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String FIRST_NAME = "firstname";
	public static final String LAST_NAME = "lastname";
	public static final String ROLE = "role";
	public static final String ADDRESS_LINE1 = "addressline1";
	public static final String ADDRESS_LINE2 = "addressline2";

	private static final String USER_TABLE_CREATE = "create table if not exists " + USER_TABLE_NAME + " (" + USERNAME + " TEXT, " + PASSWORD + " TEXT," + FIRST_NAME + " TEXT," + LAST_NAME + " TEXT,"
			+ ROLE + " TEXT," + ADDRESS_LINE1 + " TEXT," + ADDRESS_LINE2 + " TEXT)";

	public UserHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(USER_TABLE_CREATE);
		} catch (Exception e) {
			Log.d("Error", e.getLocalizedMessage());
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	}

}
