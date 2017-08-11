package com.foodapp.search;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.foodapp.database.AppDatabaseInitializer;
import com.foodapp.database.SearchLocationHelper;

public class SearchDAO {

	public static SearchLocationBean fetchrestaurantLocations(SearchLocationBean searchLocationBean, Context context) {
		SQLiteDatabase searchLocationDatabase = null;
		try {
			searchLocationDatabase = AppDatabaseInitializer.getglobalReadableDatabase();
			String tablename = SearchLocationHelper.SEARCH_LOCATION_TABLE_NAME;
			String[] columns = new String[]{SearchLocationHelper.LOGO, SearchLocationHelper.CITY, SearchLocationHelper.STATE, SearchLocationHelper.COUNTRY};
			String selectioncriteria = SearchLocationHelper.USER_LOCATION + "=?";
			String[] arguements = new String[]{String.valueOf(searchLocationBean.isUserlocation())};
			Cursor cursor = searchLocationDatabase.query(tablename, columns, selectioncriteria, arguements, null, null, null);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						String companylogo = cursor.getString(cursor.getColumnIndex(SearchLocationHelper.LOGO));
						String city = cursor.getString(cursor.getColumnIndex(SearchLocationHelper.CITY));
						String state = cursor.getString(cursor.getColumnIndex(SearchLocationHelper.STATE));
						String country = cursor.getString(cursor.getColumnIndex(SearchLocationHelper.COUNTRY));
						SearchLocation searchLocation = new SearchLocation();
						searchLocation.setCity(city);
						searchLocation.setCountry(country);
						searchLocation.setState(state);
						searchLocation.setCompanylogo(companylogo);
						searchLocationBean.setSearchLocation(searchLocation);
					} while (cursor.moveToNext());
					searchLocationBean.setValid(true);
				} else {
					searchLocationBean.setValid(true);
					searchLocationBean.setErrormsg("failed to get results");
				}

			} else {
				searchLocationBean.setValid(false);
				searchLocationBean.setErrormsg("failed to get results");
			}
		} catch (Exception e) {
			searchLocationBean.setValid(false);
			searchLocationBean.setErrormsg(e.getLocalizedMessage());
		}
		return searchLocationBean;
	}

}
