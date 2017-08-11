package com.foodapp.users;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.foodapp.database.AppDatabaseInitializer;
import com.foodapp.database.UserHelper;

public class UserDAO {

	public static UserBean fetchcurrentappuserifany(UserBean userBean, Context applicationContext) {
		return null;
	}

	public static UserBean fetchuserdetailsbyuserid(UserBean userBean, Context applicationContext) {
		SQLiteDatabase userDatabase = null;
		try {
			userDatabase = AppDatabaseInitializer.getglobalReadableDatabase();
			String tablename = UserHelper.USER_TABLE_NAME;
			String[] columns = new String[]{UserHelper.FIRST_NAME, UserHelper.LAST_NAME, UserHelper.USERNAME, UserHelper.ADDRESS_LINE1, UserHelper.ADDRESS_LINE2};
			String selectioncriteria = "_id=?";
			String[] arguements = new String[]{String.valueOf(userBean.getId())};
			Cursor cursor = userDatabase.query(tablename, columns, selectioncriteria, arguements, null, null, null);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					while (cursor.moveToNext()) {
						String firstname = cursor.getString(cursor.getColumnIndex(UserHelper.FIRST_NAME));
						String lastname = cursor.getString(cursor.getColumnIndex(UserHelper.LAST_NAME));
						String addressline1 = cursor.getString(cursor.getColumnIndex(UserHelper.ADDRESS_LINE1));
						String addressline2 = cursor.getString(cursor.getColumnIndex(UserHelper.ADDRESS_LINE2));
						User user = new User();
						user.setLastname(lastname);
						user.setFirstname(firstname);
						user.setAddressline1(addressline1);
						user.setAddressline2(addressline2);
						userBean.setUser(user);
					}
				} else {
					userBean.setValid(false);
				}
			} else {
				userBean.setValid(true);
			}
		} catch (Exception e) {
			userBean.setValid(false);
		}
		return userBean;
	}

}
