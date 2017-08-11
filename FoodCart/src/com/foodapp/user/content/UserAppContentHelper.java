package com.foodapp.user.content;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class UserAppContentHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 2;
	private static final String DATABASE_NAME = "appfoodvilla";

	private static final String DATA_TABLE_NAME = "usercontent";

	private static final String CONTENT_ID = "contentid";
	private static final String CONTENT_DATE = "date";
	private static final String CONTENT_TYPE = "tpye";
	private static final String CONTENT_PARAMETER = "parameter";
	private static final String COTENET_VALUE = "value";

	private static final String DATA_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS" + DATA_TABLE_NAME + " (" + CONTENT_ID + " TEXT, " + CONTENT_DATE + " DATE," + CONTENT_TYPE + " TEXT,"
			+ CONTENT_PARAMETER + " TEXT," + COTENET_VALUE + " TEXT)";

	public UserAppContentHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATA_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
