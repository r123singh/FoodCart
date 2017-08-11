package com.foodapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MenuCategoryHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = GlobaHelper.DATABASE_VERSION;
	private static final String DATABASE_NAME = "foodvilla.db";

	private static final String MENU_CATEGORY_TABLE_NAME = "menucategory";

	private static final String NAME = "name";
	private static final String IMAGE = "image";

	private static final String MENU_CATEGORY_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + MENU_CATEGORY_TABLE_NAME + " (" + NAME + " TEXT, " + IMAGE + " INTEGER)";

	public MenuCategoryHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(MENU_CATEGORY_TABLE_CREATE);
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", "north_indian");
		contentValues.put("image", "northcategory");

		ContentValues contentValues1 = new ContentValues();
		contentValues1.put("name", "south_indian");
		contentValues1.put("image", "southcategory");

		ContentValues contentValues2 = new ContentValues();
		contentValues2.put("name", "chinese");
		contentValues2.put("image", "chinesecategory");

		ContentValues contentValues3 = new ContentValues();
		contentValues3.put("name", "italian");
		contentValues3.put("image", "italiancategory");

		ContentValues contentValues4 = new ContentValues();
		contentValues4.put("name", "mexican");
		contentValues4.put("image", "mexicancategory");

		db.insert(MENU_CATEGORY_TABLE_NAME, null, contentValues);
		db.insert(MENU_CATEGORY_TABLE_NAME, null, contentValues1);
		db.insert(MENU_CATEGORY_TABLE_NAME, null, contentValues2);
		db.insert(MENU_CATEGORY_TABLE_NAME, null, contentValues3);
		db.insert(MENU_CATEGORY_TABLE_NAME, null, contentValues4);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
		// TODO Auto-generated method stub
		onCreate(db);
	}

}
