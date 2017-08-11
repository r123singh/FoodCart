package com.foodapp.cart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.foodapp.database.AppDatabaseInitializer;
import com.foodapp.database.CartHelper;
import com.foodapp.database.CartItemHelper;

public class CartDAO {

	public static CartBean pushnewfoodcartforordering(CartBean cartbean, Context context) {
		SQLiteDatabase cartLiteDatabase = null;
		try {
			cartLiteDatabase = AppDatabaseInitializer.getglobalWritableDatabase();
			String tablename = CartHelper.CART_TABLE_NAME;
			String nullablecolhacks = null;
			ContentValues cartContentValues = new ContentValues();
			cartContentValues.put(CartHelper.DELIVERY_CHARGES, cartbean.getDeliverycharges());
			cartContentValues.put(CartHelper.PACKING_CHARGES, cartbean.getPackagingcharges());
			cartContentValues.put(CartHelper.DISCOUNT_CHARGES, cartbean.getDiscountcharges());
			cartContentValues.put(CartHelper.SUBTOTAL_CHARGES, cartbean.getSubtotalCharges());
			cartContentValues.put(CartHelper.TOTAL_CHARGES, cartbean.getTotalcharges());
			cartContentValues.put(CartHelper.TAX_CHARGES, cartbean.getTaxcharges());
			cartContentValues.put(CartHelper.RESTAURANT, cartbean.getRestaurant());
			cartContentValues.put(CartHelper.RESTAURANT_IMAGE, cartbean.getRestaurantimage());
			long cartid = cartLiteDatabase.insert(tablename, nullablecolhacks, cartContentValues);
			cartbean.setId(cartid);
			cartbean.setValid(true);
		} catch (Exception e) {
			cartbean.setValid(false);
			Log.e("pushnewfoodcartforordering", e.getLocalizedMessage());
		}
		return cartbean;
	}

	public static CartItemsBean removecartbyid(CartItemsBean cartItemsBean) {
		SQLiteDatabase cartDatabase = null;
		try {
			cartDatabase = AppDatabaseInitializer.getglobalWritableDatabase();
			long cart_id = cartItemsBean.getCartid();
			cartDatabase.delete(CartHelper.CART_TABLE_NAME, "id=?", new String[]{String.valueOf(cart_id)});
			cartItemsBean.setValid(true);
		} catch (Exception e) {
			cartItemsBean.setValid(false);
			cartItemsBean.setErrmsg(e.getLocalizedMessage());
		}
		return cartItemsBean;
	}
	public static CartBean fetchOrderCartById(CartBean cartbean, Context context) {
		SQLiteDatabase cartdatabase = null;
		try {
			cartdatabase = AppDatabaseInitializer.getglobalReadableDatabase();
			String tablename = CartHelper.CART_TABLE_NAME;
			String[] columns = new String[]{CartHelper.DELIVERY_CHARGES, CartHelper.RESTAURANT_IMAGE, CartHelper.DISCOUNT_CHARGES, CartHelper.PACKING_CHARGES, CartHelper.RESTAURANT,
					CartHelper.TAX_CHARGES, CartHelper.TOTAL_CHARGES, CartHelper.SUBTOTAL_CHARGES};
			String selectioncriteria = CartHelper.ID + "=?";
			String[] arguements = new String[]{String.valueOf(cartbean.getId())};
			Cursor cursor = cartdatabase.query(tablename, columns, selectioncriteria, arguements, null, null, null);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						double deliverycharges = cursor.getDouble(cursor.getColumnIndex(CartHelper.DELIVERY_CHARGES));
						double discountcharges = cursor.getDouble(cursor.getColumnIndex(CartHelper.DISCOUNT_CHARGES));
						double packagingcharges = cursor.getDouble(cursor.getColumnIndex(CartHelper.PACKING_CHARGES));
						double taxcharges = cursor.getDouble(cursor.getColumnIndex(CartHelper.TAX_CHARGES));
						double totalcharges = cursor.getDouble(cursor.getColumnIndex(CartHelper.TOTAL_CHARGES));
						long restaurantid = cursor.getLong(cursor.getColumnIndex(CartHelper.RESTAURANT));
						double subtotalcharges = cursor.getDouble(cursor.getColumnIndex(CartHelper.SUBTOTAL_CHARGES));
						String restaurantimage = cursor.getString(cursor.getColumnIndex(CartHelper.RESTAURANT_IMAGE));
						cartbean.setRestaurant(restaurantid);
						cartbean.setDeliverycharges(deliverycharges);
						cartbean.setTotalcharges(totalcharges);
						cartbean.setTaxcharges(taxcharges);
						cartbean.setDiscountcharges(discountcharges);
						cartbean.setPackagingcharges(packagingcharges);
						cartbean.setSubtotalCharges(subtotalcharges);
						cartbean.setRestaurantimage(restaurantimage);
						cartbean.setValid(true);
					} while (cursor.moveToNext());
				} else {
					cartbean.setValid(true);
					cartbean.setError("Fetch cart by id failed");
				}
			} else {
				cartbean.setValid(false);
			}
		} catch (Exception e) {
			cartbean.setValid(false);
			cartbean.setError(e.getLocalizedMessage());
		}
		return cartbean;
	}

	public static CartBean updateoldcart(CartBean cartbean, Context context) {
		SQLiteDatabase cartdatabase = null;
		try {
			cartdatabase = AppDatabaseInitializer.getglobalWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(CartHelper.PACKING_CHARGES, cartbean.getPackagingcharges());
			values.put(CartHelper.DISCOUNT_CHARGES, cartbean.getDiscountcharges());
			values.put(CartHelper.TAX_CHARGES, cartbean.getTaxcharges());
			values.put(CartHelper.SUBTOTAL_CHARGES, cartbean.getSubtotalCharges());
			values.put(CartHelper.TOTAL_CHARGES, cartbean.getTotalcharges());
			cartdatabase.update(CartHelper.CART_TABLE_NAME, values, "id=?", new String[]{String.valueOf(cartbean.getId())});
			cartbean.setValid(true);
		} catch (Exception e) {
			cartbean.setValid(false);
			cartbean.setError(e.getLocalizedMessage());
		}
		return cartbean;
	}

	public static void clearallcarttable() {
		SQLiteDatabase cartdatabase = null;
		try {
			cartdatabase = AppDatabaseInitializer.getglobalWritableDatabase();
			cartdatabase.delete(CartHelper.CART_TABLE_NAME, null, null);
			cartdatabase.delete(CartItemHelper.CART_ITEM_TABLE_CREATE, null, null);
		} catch (Exception e) {
			Log.e("clearallcarttable", e.getLocalizedMessage());
		}
	}
}
