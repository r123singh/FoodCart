package com.foodapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MenuHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = GlobaHelper.DATABASE_VERSION;
	private static final String DATABASE_NAME = "foodvilla.db";

	private static final String MENU_TABLE_NAME = "menu";

	private static final String NAME = "name";
	private static final String RESTAURANT = "restaurant";
	private static final String ITEM_NAME = "itemname";
	private static final String ITEM_DESCRIPTION = "description";
	private static final String ITEM_RATE = "rate";
	private static final String ITEM_CATEGORY = "category";

	private static final String MENU_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + MENU_TABLE_NAME + " (" + NAME + " TEXT, " + RESTAURANT + " TEXT," + ITEM_NAME + " TEXT," + ITEM_DESCRIPTION
			+ " TEXT," + ITEM_RATE + " NUMBER," + ITEM_CATEGORY + " /TEXT)";

	public MenuHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(MENU_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
		// TODO Auto-generated method stub
		onCreate(db);
	}

}
