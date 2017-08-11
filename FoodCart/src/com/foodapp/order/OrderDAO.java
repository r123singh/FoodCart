package com.foodapp.order;

import java.util.ArrayList;

import com.foodapp.database.AppDatabaseInitializer;
import com.foodapp.database.OrderHelper;
import com.foodapp.database.OrderItemHelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class OrderDAO {

	public static ArrayList<Foodorder> fetchallordersplaced() {
		SQLiteDatabase orderdatabase = null;
		ArrayList<Foodorder> foodorderlist = null;
		try {
			orderdatabase = AppDatabaseInitializer.getglobalReadableDatabase();
			Cursor ordercursor = orderdatabase.rawQuery("select * from order", null);
			if (ordercursor.getCount() > 0) {
				foodorderlist = new ArrayList<Foodorder>();
				if (ordercursor.moveToFirst()) {
					do {
						Foodorder foodorder = new Foodorder();
						foodorder.setOrderid(ordercursor.getLong(ordercursor.getColumnIndex(OrderHelper.ORDER_ID)));
						foodorder.setOrderdeliveryaddress(ordercursor.getString(ordercursor.getColumnIndex(OrderHelper.ORDER_DELIVERY_ADDRESS)));
						foodorder.setOrderdeliverycharge(ordercursor.getDouble(ordercursor.getColumnIndex(OrderHelper.ORDER_DELIVERY_CHARGE)));
						foodorder.setOrderdeliverydate(ordercursor.getString(ordercursor.getColumnIndex(OrderHelper.ORDER_DATE)));
						foodorder.setOrderpackagingcharges(ordercursor.getDouble(ordercursor.getColumnIndex(OrderHelper.ORDER_PACKAGING_CHARGE)));
						foodorder.setOrderstatus(ordercursor.getString(ordercursor.getColumnIndex(OrderHelper.ORDER_STATUS)));
						foodorder.setOrdersubtotal(ordercursor.getDouble(ordercursor.getColumnIndex(OrderHelper.ORDER_SUBTOTAL)));
						foodorder.setOrdertotalamount(ordercursor.getColumnIndex(OrderHelper.ORDER_TOTAL_AMOUNT));
						foodorder.setOrdertotaltax(ordercursor.getColumnIndex(OrderHelper.ORDER_TOTAL_TAX));
						foodorderlist.add(foodorder);
					} while (ordercursor.moveToNext());
				}
			} else {
				foodorderlist = null;
			}
		} catch (Exception e) {
			Log.e("OrderDAO", "fetchallordersplaced");
		}
		return foodorderlist;
	}

	public static ArrayList<Foodorderitem> fetchallorderitemsplaced(long orderid) {
		SQLiteDatabase orderitemdatabase = null;
		ArrayList<Foodorderitem> orderitemlist = null;
		try {
			orderitemdatabase = AppDatabaseInitializer.getglobalReadableDatabase();
			String[] selectionargs = new String[]{String.valueOf(orderid)};
			Cursor itemcursor = orderitemdatabase.rawQuery("select * from orderitem where order_id=?", selectionargs);
			if (itemcursor.getCount() > 0) {
				orderitemlist = new ArrayList<Foodorderitem>();
				if (itemcursor.moveToFirst()) {
					do {
						Foodorderitem orderitem = new Foodorderitem();
						orderitem.setOrderitemid(itemcursor.getLong(itemcursor.getColumnIndex(OrderItemHelper.ORDERITEM_ID)));
						orderitem.setOrderitemname(itemcursor.getString(itemcursor.getColumnIndex(OrderItemHelper.ORDERITEM_NAME)));
						orderitem.setOrderitemprice(itemcursor.getDouble(itemcursor.getColumnIndex(OrderItemHelper.ORDERITEM_PRICE)));
						orderitem.setOrderitemquantity(itemcursor.getInt(itemcursor.getColumnIndex(OrderItemHelper.ORDERITEM_QUANTITY)));
						orderitem.setOrderitemremarks(itemcursor.getString(itemcursor.getColumnIndex(OrderItemHelper.ORDERITEM_REMARKS)));
						orderitem.setOrderitemsubtotal(itemcursor.getDouble(itemcursor.getColumnIndex(OrderItemHelper.ORDERITEM_SUBTOTAL)));
						orderitem.setOrderid(orderid);
						orderitemlist.add(orderitem);
					} while (itemcursor.moveToNext());
				}
			} else {
				orderitemlist = null;
			}
		} catch (Exception e) {
			Log.e("OrderDAO", "fetchallorderitemsplaced");
		}
		return orderitemlist;
	}

	public static long saveneworder(Foodorder foodorder) {
		long orderid = -1;
		SQLiteDatabase orderdatabase = null;
		try {
			orderdatabase = AppDatabaseInitializer.getglobalWritableDatabase();
			ContentValues ordervalues = new ContentValues();
			ordervalues.put(OrderHelper.ORDER_DATE, foodorder.getOrderdeliverydate());
			ordervalues.put(OrderHelper.ORDER_DELIVERY_ADDRESS, foodorder.getOrderdeliveryaddress());
			ordervalues.put(OrderHelper.ORDER_DELIVERY_CHARGE, foodorder.getOrderdeliverycharge());
			ordervalues.put(OrderHelper.ORDER_PACKAGING_CHARGE, foodorder.getOrderpackagingcharges());
			ordervalues.put(OrderHelper.ORDER_STATUS, foodorder.getOrderstatus());
			ordervalues.put(OrderHelper.ORDER_SUBTOTAL, foodorder.getOrdersubtotal());
			ordervalues.put(OrderHelper.ORDER_TOTAL_AMOUNT, foodorder.getOrdertotalamount());
			ordervalues.put(OrderHelper.ORDER_TOTAL_TAX, foodorder.getOrdertotaltax());
			orderid = orderdatabase.insert(OrderHelper.ORDER_TABLE_NAME, null, ordervalues);
		} catch (Exception e) {
			Log.e("OrderDAO", "saveneworder");
		}
		return orderid;
	}

	public static long saveneworderitem(Foodorderitem orderitem) {
		long orderitemid = -1;
		SQLiteDatabase orderitemdatabase = null;
		try {
			orderitemdatabase = AppDatabaseInitializer.getglobalWritableDatabase();
			ContentValues contentvalues = new ContentValues();
			contentvalues.put(OrderItemHelper.ORDERITEM_NAME, orderitem.getOrderitemname());
			contentvalues.put(OrderItemHelper.ORDERITEM_PRICE, orderitem.getOrderitemprice());
			contentvalues.put(OrderItemHelper.ORDERITEM_QUANTITY, orderitem.getOrderitemquantity());
			contentvalues.put(OrderItemHelper.ORDERITEM_REMARKS, orderitem.getOrderitemremarks());
			contentvalues.put(OrderItemHelper.ORDERITEM_SUBTOTAL, orderitem.getOrderitemsubtotal());
			contentvalues.put(OrderItemHelper.ORDER_ID, orderitem.getOrderid());
			orderitemid = orderitemdatabase.insert("orderitem", null, contentvalues);

		} catch (Exception e) {
			Log.e("OrderDAO", "saveneworderitem");
		}
		return orderitemid;
	}

}
