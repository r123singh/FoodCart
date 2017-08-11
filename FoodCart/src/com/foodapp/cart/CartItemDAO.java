package com.foodapp.cart;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.foodapp.database.AppDatabaseInitializer;
import com.foodapp.database.CartItemHelper;

public class CartItemDAO {

	public static CartItemsBean fetchOrderCartItemsById(CartItemsBean cartItemsBean, Context context) {
		SQLiteDatabase caritemdatabase = null;
		ArrayList<CartItemsBean> cartitems = new ArrayList<CartItemsBean>();
		try {
			caritemdatabase = AppDatabaseInitializer.getglobalReadableDatabase();
			String tablename = CartItemHelper.CART_ITEM_TABLE_NAME;
			String[] columns = new String[]{CartItemHelper.ID, CartItemHelper.ITEM_DISCOUNT, CartItemHelper.ITEM_NAME, CartItemHelper.ITEM_PRICE, CartItemHelper.ITEM_PACKAGING,
					CartItemHelper.ITEM_QUANTITY, CartItemHelper.ITEM_SUBTOTAL, CartItemHelper.ITEM_TAX, CartItemHelper.ITEM_TOTAL};
			String selectioncriteria = CartItemHelper.CART_ID + "=?";
			String[] arguements = new String[]{String.valueOf(cartItemsBean.getCartid())};
			Cursor cursor = caritemdatabase.query(tablename, columns, selectioncriteria, arguements, null, null, null);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						CartItemsBean cartitem = new CartItemsBean();
						cartitem.setItemname(cursor.getString(cursor.getColumnIndex(CartItemHelper.ITEM_NAME)));
						cartitem.setItempackagingcharges(cursor.getDouble(cursor.getColumnIndex(CartItemHelper.ITEM_PACKAGING)));
						cartitem.setItemsubtotalCharges(cursor.getDouble(cursor.getColumnIndex(CartItemHelper.ITEM_SUBTOTAL)));
						cartitem.setItemprice(cursor.getDouble(cursor.getColumnIndex(CartItemHelper.ITEM_PRICE)));
						cartitem.setItemdiscountcharges(cursor.getDouble(cursor.getColumnIndex(CartItemHelper.ITEM_DISCOUNT)));
						cartitem.setItemtaxcharges(cursor.getDouble(cursor.getColumnIndex(CartItemHelper.ITEM_TAX)));
						cartitem.setItemtotalcharges(cursor.getDouble(cursor.getColumnIndex(CartItemHelper.ITEM_TOTAL)));
						cartitem.setQuantity(cursor.getInt(cursor.getColumnIndex(CartItemHelper.ITEM_QUANTITY)));
						cartitems.add(cartitem);
					} while (cursor.moveToNext());
					cartItemsBean.setCartitems(cartitems);
					cartItemsBean.setValid(true);
				} else {
					cartItemsBean.setValid(true);
					cartItemsBean.setErrmsg("Your Order Cart is Empty");
				}
			} else {
				cartItemsBean.setValid(false);
				cartItemsBean.setErrmsg("Your Order Cart is Empty");
			}
		} catch (Exception e) {
			e.printStackTrace();
			cartItemsBean.setValid(false);
		}
		return cartItemsBean;
	}
	public static CartItemsBean pushfooditemintocart(CartItemsBean cartItemBean, Context context) {
		SQLiteDatabase cartitemDatabase = null;
		try {
			cartitemDatabase = AppDatabaseInitializer.getglobalWritableDatabase();
			String tablename = CartItemHelper.CART_ITEM_TABLE_NAME;
			ContentValues cartitemsContentValues = new ContentValues();
			cartitemsContentValues.put(CartItemHelper.CART_ID, cartItemBean.getCartid());
			cartitemsContentValues.put(CartItemHelper.ITEM_NAME, cartItemBean.getItemname());
			cartitemsContentValues.put(CartItemHelper.ITEM_PRICE, cartItemBean.getItemprice());
			cartitemsContentValues.put(CartItemHelper.ITEM_QUANTITY, cartItemBean.getQuantity());
			cartitemsContentValues.put(CartItemHelper.ITEM_SUBTOTAL, cartItemBean.getItemsubtotalCharges());
			cartitemsContentValues.put(CartItemHelper.ITEM_PACKAGING, cartItemBean.getItempackagingcharges());
			cartitemsContentValues.put(CartItemHelper.ITEM_DISCOUNT, cartItemBean.getItemdiscountcharges());
			cartitemsContentValues.put(CartItemHelper.ITEM_TOTAL, cartItemBean.getItemtotalcharges());
			cartitemsContentValues.put(CartItemHelper.ITEM_TAX, cartItemBean.getItemtaxcharges());
			cartitemDatabase.insert(tablename, null, cartitemsContentValues);
			cartItemBean.setValid(true);
		} catch (Exception e) {
			cartItemBean.setValid(false);
			cartItemBean.setErrmsg(e.getLocalizedMessage());
		}
		return cartItemBean;
	}

	public static CartItemsBean removecartitemsbycartid(CartItemsBean cartItemsBean) {
		SQLiteDatabase cartitemdatabase = null;
		try {
			cartitemdatabase = AppDatabaseInitializer.getglobalWritableDatabase();
			long cartid = cartItemsBean.getCartid();
			cartitemdatabase.delete(CartItemHelper.CART_ITEM_TABLE_NAME, "cart_id=?", new String[]{String.valueOf(cartid)});
			cartItemsBean.setValid(true);
		} catch (Exception e) {
			cartItemsBean.setValid(false);
			cartItemsBean.setErrmsg(e.getLocalizedMessage());
		}
		return cartItemsBean;
	}
}
