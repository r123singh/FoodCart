package com.foodapp.restaurants;

import java.util.Locale;
import java.util.Vector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.foodapp.database.AppDatabaseInitializer;
import com.foodapp.database.RestaurantHelper;

public class RestaurantDAO {

	public static RestaurantBeans fetchAllRestaurants(RestaurantBeans restaurantBeans, Context context) {
		SQLiteDatabase restaurantsDatabase = null;
		try {
			restaurantsDatabase = AppDatabaseInitializer.getglobalWritableDatabase();
			String tablename = RestaurantHelper.RESTAURANT_TABLE_NAME;
			String[] columns = new String[]{RestaurantHelper.CATEGORY};
			String selectioncriteria = RestaurantHelper.CATEGORY + "=?";
			String[] arguements = new String[]{restaurantBeans.getCategory().trim()};

			Cursor cursor = restaurantsDatabase.query(tablename, columns, selectioncriteria, arguements, null, null, null);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						String restaurantName = cursor.getString(cursor.getColumnIndex(RestaurantHelper.TITLE));
					} while (cursor.moveToNext());
				}
			}
		} catch (SQLiteException e) {
			// TODO: handle exception
		} finally {
			restaurantsDatabase.close();
		}
		return null;
	}

	public static RestaurantBeans fetchAllRestaurantsByLocation(RestaurantBeans restaurantBeans, Context applicationContext) {
		SQLiteDatabase restaurantsDatabase = null;
		Vector<RestaurantItem> restaurantItems = new Vector<RestaurantItem>();
		Cursor cursor = null;
		try {
			restaurantsDatabase = AppDatabaseInitializer.getglobalReadableDatabase();
			String tablename = RestaurantHelper.RESTAURANT_TABLE_NAME;
			String[] columns = new String[]{RestaurantHelper.ID, RestaurantHelper.LOCATION, RestaurantHelper.TITLE, RestaurantHelper.DELIVERY_TYPE, RestaurantHelper.DELIVERY_CHARGES,
					RestaurantHelper.MINIMUM_ORDER_CHARGES, RestaurantHelper.AVAILABLE_NOW, RestaurantHelper.PAYMENT_MODE, RestaurantHelper.FOOD_CATEGORY, RestaurantHelper.ESTIMATED_DELIVERY,
					RestaurantHelper.RESTAURANT_DISTANCE, RestaurantHelper.RESTAURANT_DISTANCE, RestaurantHelper.IMAGE};
			String selectioncriteria = RestaurantHelper.LOCATION + "=?";
			String[] arguements = new String[]{restaurantBeans.getLocationid()};
			cursor = restaurantsDatabase.query(tablename, columns, selectioncriteria, arguements, null, null, null);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						long restaurandid = cursor.getLong(cursor.getColumnIndex(RestaurantHelper.ID));
						String restaurantTitle = cursor.getString(cursor.getColumnIndex(RestaurantHelper.TITLE));
						String deliverytype = cursor.getString(cursor.getColumnIndex(RestaurantHelper.DELIVERY_TYPE));
						double deliverycharges = cursor.getDouble(cursor.getColumnIndex(RestaurantHelper.DELIVERY_CHARGES));
						double minimumordercharges = cursor.getDouble(cursor.getColumnIndex(RestaurantHelper.MINIMUM_ORDER_CHARGES));
						boolean availableNow = cursor.getString(cursor.getColumnIndex(RestaurantHelper.AVAILABLE_NOW)).toLowerCase(Locale.ENGLISH) == "true" ? true : false;
						String paymentmode = cursor.getString(cursor.getColumnIndex(RestaurantHelper.PAYMENT_MODE));
						String categories = cursor.getString(cursor.getColumnIndex(RestaurantHelper.FOOD_CATEGORY));
						String estimatedDelivery = cursor.getString(cursor.getColumnIndex(RestaurantHelper.ESTIMATED_DELIVERY));
						String restaurantDistance = cursor.getString(cursor.getColumnIndex(RestaurantHelper.RESTAURANT_DISTANCE));
						String restauratnImageid = cursor.getString(cursor.getColumnIndex(RestaurantHelper.IMAGE));
						RestaurantItem restaurantItem = new RestaurantItem();
						restaurantItem.setId(restaurandid);
						restaurantItem.setRestaurantmainTitle(restaurantTitle);
						restaurantItem.setAvailableNow(availableNow);
						restaurantItem.setDeliverycharges(deliverycharges);
						restaurantItem.setDeliveryType(deliverytype);
						restaurantItem.setEstimatedDelivery(estimatedDelivery);
						restaurantItem.setMinimumordercharges(minimumordercharges);
						restaurantItem.setPaymentmode(paymentmode);
						restaurantItem.setFoodcategories(categories);
						restaurantItem.setRestaurantDistance(restaurantDistance);
						restaurantItems.add(restaurantItem);

					} while (cursor.moveToNext());
					restaurantBeans.setRestaurantItems(restaurantItems);
					restaurantBeans.setValid(true);
				} else {
					restaurantBeans.setValid(true);
				}
			} else {
				restaurantBeans.setValid(false);
				restaurantBeans.setErrorMessage("Cursor Null" + restaurantBeans.getLocationid());
			}
		} catch (SQLiteException e) {
			restaurantBeans.setValid(false);
			restaurantBeans.setErrorMessage(e.getLocalizedMessage());
		} finally {
			try {
				cursor.close();
			} catch (Exception e) {
				restaurantBeans.setValid(false);
			}
		}
		return restaurantBeans;
	}

	public static RestaurantBean fetchrestaurantbyid(RestaurantBean restaurantbean) {
		SQLiteDatabase restaurantDatabase = null;
		Cursor cursor = null;
		try {
			restaurantDatabase = AppDatabaseInitializer.getglobalReadableDatabase();
			cursor = restaurantDatabase.rawQuery("select * from " + RestaurantHelper.RESTAURANT_TABLE_NAME + " where id=?", new String[]{String.valueOf(restaurantbean.getId())});
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				String name = cursor.getString(cursor.getColumnIndex(RestaurantHelper.TITLE));
				String address = cursor.getString(cursor.getColumnIndex(RestaurantHelper.ADDRESS));
				String image = cursor.getString(cursor.getColumnIndex(RestaurantHelper.IMAGE));
				restaurantbean.setImage(image);
				restaurantbean.setRestaurantName(name);
				restaurantbean.setAddress(address);
				restaurantbean.setValid(true);
			} else {
				restaurantbean.setValid(false);
				restaurantbean.setErrormsg("No Such Restaurant Found");
			}
		} catch (Exception e) {
			Log.e("fetchrestaurantbyid: ", e.getLocalizedMessage());
			restaurantbean.setValid(false);
			restaurantbean.setErrormsg("No Such Restaurant Found");
		}
		return restaurantbean;
	}

	public static RestaurantBeans saverestaurantlist(RestaurantBeans restaurantbean) {
		SQLiteDatabase restaurantdatabase = null;
		try {
			restaurantdatabase = AppDatabaseInitializer.getglobalWritableDatabase();
			Vector<RestaurantItem> restaurantitems = restaurantbean.getRestaurantItems();
			for (RestaurantItem restaurantitem : restaurantitems) {
				ContentValues contentvalues = new ContentValues();
				contentvalues.put(RestaurantHelper.TITLE, restaurantitem.getRestaurantmainTitle());
				contentvalues.put(RestaurantHelper.ADDRESS, restaurantitem.getAddress());
				contentvalues.put(RestaurantHelper.MINIMUM_ORDER_CHARGES, restaurantitem.getMinimumordercharges());
				contentvalues.put(RestaurantHelper.DELIVERY_CHARGES, restaurantitem.getDeliverycharges());
				contentvalues.put(RestaurantHelper.DELIVERY_TYPE, restaurantitem.getDeliveryType());
				contentvalues.put(RestaurantHelper.ESTIMATED_DELIVERY, restaurantitem.getEstimatedDelivery());
				contentvalues.put(RestaurantHelper.AVAILABLE_NOW, restaurantitem.isAvailableNow());
				contentvalues.put(RestaurantHelper.PAYMENT_MODE, restaurantitem.getPaymentmode());
				contentvalues.put(RestaurantHelper.LOCATION, restaurantitem.getRestaurantloc());
				contentvalues.put(RestaurantHelper.FOOD_CATEGORY, restaurantitem.getFoodcategories());
				restaurantdatabase.insert(RestaurantHelper.RESTAURANT_TABLE_NAME, null, contentvalues);
				restaurantbean.setValid(true);
			}
		} catch (Exception e) {
			restaurantbean.setValid(false);
			restaurantbean.setErrorMessage(e.getLocalizedMessage());
		}
		return restaurantbean;
	}
}
