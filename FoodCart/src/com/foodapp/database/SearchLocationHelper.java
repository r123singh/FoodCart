package com.foodapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SearchLocationHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = GlobaHelper.DATABASE_VERSION;
	private static final String DATABASE_NAME = "foodvilla.db";

	public static final String SEARCH_LOCATION_TABLE_NAME = "search_location";

	public static final String ID = "id";
	public static final String CITY = "city";
	public static final String STATE = "state";
	public static final String COUNTRY = "country";
	public static final String LOGO = "companylogo";
	public static final String USER_LOCATION = "user_loc";

	public static final String SEARCH_LOCATION_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + SEARCH_LOCATION_TABLE_NAME + " (" + ID + " BIGINT, " + CITY + " TEXT," + STATE + " TEXT," + COUNTRY
			+ " TEXT," + LOGO + " TEXT," + USER_LOCATION + " TEXT)";

	public SearchLocationHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SEARCH_LOCATION_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	}

}
