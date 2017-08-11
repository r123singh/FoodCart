package com.foodapp.user.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.foodapp.database.UserHelper;
import com.foodapp.users.LoginUserBean;
import com.foodapp.users.UserBean;

public class LoginDAO {

	public static LoginUserBean verifyLoginDetails(Context context, LoginUserBean loginUserBean) {
		// TODO Auto-generated method stub
		boolean success = false;
		UserHelper userHelper = new UserHelper(context, null, null, 0);
		SQLiteDatabase userDatabase = userHelper.getReadableDatabase();
		String tablename = "user";
		String[] columns = new String[]{"username", "password"};
		String selectionCriteria = "username= ? and password =?";
		String groupBy = null;
		String orderBy = null;
		String[] selectioArgs = new String[]{loginUserBean.getUsername().trim(), loginUserBean.getPassword().trim()};
		try {
			Cursor cursor = userDatabase.query(tablename, columns, selectionCriteria, selectioArgs, groupBy, null, orderBy);
			if (cursor.getCount() <= 0) {
				success = true;
				loginUserBean.setErrormsg("You are not a registered member");
			} else {
				success = true;
				loginUserBean.setUsername(cursor.getString(cursor.getColumnIndex("username")));
			}
		} catch (SQLiteException sqLiteException) {
			success = false;
			Log.d("error", sqLiteException.getLocalizedMessage());
			loginUserBean.setErrormsg(sqLiteException.getLocalizedMessage());
		} finally {
			userDatabase.close();
		}
		loginUserBean.setValid(success);
		return loginUserBean;
	}
	public static UserBean createNewUser(Context context, UserBean userBean) {
		// TODO Auto-generated method stub
		boolean success = false;
		SQLiteDatabase userDatabase = null;
		UserHelper helper = new UserHelper(context, null, null, 0);
		try {
			userDatabase = helper.getWritableDatabase();
			ContentValues contentValues = new ContentValues();
			contentValues.put("username", userBean.getUser().getUsername());
			contentValues.put("password", userBean.getUser().getPassword());
			contentValues.put("firstname", userBean.getUser().getFirstname());
			contentValues.put("lastname", userBean.getUser().getLastname());
			contentValues.put("role", userBean.getUser().getRoles().toString());
			userDatabase.insert("user", null, contentValues);
			success = true;
		} catch (Exception e) {
			Log.d("Error", e.getLocalizedMessage());
			userBean.setErrormessage(e.getLocalizedMessage());
			success = false;
		} finally {
			userDatabase.close();
		}
		userBean.setValid(success);
		return userBean;
	}

	public static UserBean loggedInUser(LoginUserBean loginUserBean) {
		// TODO Auto-generated method stub
		return null;
	}

	public static UserBean persistUserSession(UserBean userBean) {
		// TODO Auto-generated method stub
		return null;
	}

	public static int allUserCount(Context context) {
		// TODO Auto-generated method stub
		SQLiteDatabase userDatabase = null;
		int count = 0;
		try {
			UserHelper helper = new UserHelper(context, null, null, 0);
			userDatabase = helper.getReadableDatabase();
			Cursor cursor = userDatabase.rawQuery("select * from user", null);
			count = cursor.getCount();
		} catch (Exception e) {
			Log.e("ERROR", e.getLocalizedMessage());
		}
		return count;
	}
}
