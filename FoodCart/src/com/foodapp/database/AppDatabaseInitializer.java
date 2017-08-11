package com.foodapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class AppDatabaseInitializer {

	private static SQLiteDatabase globaLiteReadableDatabase;
	private static SQLiteDatabase globaLiteWritableDatabase;
	private static GlobaHelper globaHelper;

	public AppDatabaseInitializer(Context appContext) {
		setGlobaHelper(new GlobaHelper(appContext, null, null, 0));
	}

	public static void initializedb(Context context) {
		new AppDatabaseInitializer(context);
		loadalldatabaseresourcesfromserver();
	}
	
	private static void loadalldatabaseresourcesfromserver() {
		
	}

	public static SQLiteDatabase getglobalWritableDatabase() {

		if (globaLiteWritableDatabase == null) {
			globaLiteWritableDatabase = getGlobaHelper().getWritableDatabase();
		}
		return globaLiteWritableDatabase;
	}

	public static SQLiteDatabase getglobalReadableDatabase() {
		if (globaLiteReadableDatabase == null) {
			globaLiteReadableDatabase = getGlobaHelper().getReadableDatabase();
		}
		return globaLiteReadableDatabase;
	}

	public static GlobaHelper getGlobaHelper() {
		return globaHelper;
	}

	public static void setGlobaHelper(GlobaHelper globaHelper) {
		AppDatabaseInitializer.globaHelper = globaHelper;
	}

}
