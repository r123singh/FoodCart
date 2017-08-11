package com.foodapp.activities;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Vector;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.foodapp.R;
import com.foodapp.adapter.MenuFoodCategoryAdapter;
import com.foodapp.cart.CartDAO;
import com.foodapp.cart.CartItemDAO;
import com.foodapp.cart.CartItemsBean;
import com.foodapp.menu.FoodCategoriesBean;
import com.foodapp.menu.MenuCategoryBean;
import com.foodapp.menu.MenuDAO;
import com.foodapp.menu.MenuFoodCategory;
import com.foodapp.restaurants.RestaurantItem;

public class MenuFoodCategoryActivity extends BaseActivity implements OnItemClickListener {
	private GridView categorygridView = null;
	private RestaurantItem restaurantItem;
	private Vector<MenuFoodCategory> menuFoodCategories;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actvity_menu_food_category);
		restaurantItem = (RestaurantItem) getIntent().getSerializableExtra("restaurant");
		categorygridView = (GridView) findViewById(R.id.menu_food_category_grid_view);
		categorygridView.setOnItemClickListener(this);
		if (ActivityStrings.ONLINE_MODE) {
			Getmenucategoryresponse getmenucategoryresponse = new Getmenucategoryresponse();
			getmenucategoryresponse.execute(0l);
		} else {
			LazyMenuFoodCategoryloader foodCategoryloader = new LazyMenuFoodCategoryloader();
			foodCategoryloader.execute(0l);
		}

	}
	@Override
	protected void onResume() {
		if (menutextview != null) {
			menutextview.setText(String.valueOf(getSharedPreferences(ActivityStrings.APPPREFERENCES, MODE_PRIVATE).getInt(ActivityStrings.CART_ITEM_COUNT, 0)));
		}
		super.onResume();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.cart_menu_item) {
			Intent menufoodcategoryIntent = new Intent(MenuFoodCategoryActivity.this, OrderCartLandingActivity.class);
			menufoodcategoryIntent.putExtra("restaurant", restaurantItem);
			startActivity(menufoodcategoryIntent);
		}
		if (item.getItemId() == R.id.history_item) {
			Intent historypage = new Intent(this, OrderHistoryActivity.class);
			historypage.putExtra("status", true);
			startActivity(historypage);
		}
		return true;
	}

	class Getmenucategoryresponse extends AsyncTask<Long, Integer, String> {

		@Override
		protected String doInBackground(Long... restaurant) {
			String responsemessage = null;
			try {
				String link = "http://10.10.16.120:8080/Foodzill1.1/SyncMenuCategoryServlet?restaurant=" + restaurantItem.getId();
				HttpClient client = new DefaultHttpClient();
				HttpGet request = new HttpGet();
				request.setURI(new URI(link));
				HttpResponse response = client.execute(request);
				BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				StringBuffer sb = new StringBuffer("");
				String line = "";
				while ((line = in.readLine()) != null) {
					sb.append(line);
				}
				in.close();
				responsemessage = sb.toString();
				updateFromDownload(responsemessage);
			} catch (Exception e) {
				Log.e("Getmenucategoryresponse:execute", e.getLocalizedMessage());
				responsemessage = null;
			}
			return responsemessage;
		}

		@Override
		protected void onPostExecute(String response) {
			super.onPostExecute(response);
			if (response != null) {
				Log.d("onPostExecute", response);
				MenuFoodCategoryAdapter menuFoodCategoryAdapter = new MenuFoodCategoryAdapter(getApplicationContext());
				menuFoodCategoryAdapter.setMenuFoodCategories(menuFoodCategories);
				categorygridView.setAdapter(menuFoodCategoryAdapter);
			}
		}
	}

	public void updateFromDownload(String response) {
		Log.d("updatefromdownload", response);
		JSONObject menuitemresponse = null;
		try {
			menuitemresponse = new JSONObject(response);
			JSONArray menuitemsarray = menuitemresponse.getJSONArray("menu_categories");
			menuFoodCategories = new Vector<MenuFoodCategory>();
			for (int i = 0; i < menuitemsarray.length(); i++) {
				MenuCategoryBean categorybean = null;
				JSONObject menuitemjson = menuitemsarray.getJSONObject(i);
				MenuFoodCategory menufoodcategory = new MenuFoodCategory();
				menufoodcategory.setName(menuitemjson.getString("category"));
				menufoodcategory.setCategoryimage(menuitemjson.getString("category_image"));
				menufoodcategory.setRestaurantId(menuitemresponse.getLong("restaurant"));
				menufoodcategory.setDescription(menuitemjson.getString("category"));
				menuFoodCategories.add(menufoodcategory);
				if (ActivityStrings.STORE_OFFLINE) {
					categorybean = new MenuCategoryBean();
					categorybean.setMenufoodcategory(menufoodcategory);
					categorybean = MenuDAO.savemenucategory(categorybean);
					menufoodcategory.setId(categorybean.getCategory());
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private Bitmap downloadrestuarantbitmap(String image) {
		String imageURL = "http://10.10.16.120:8080/Foodzill1.1/Imageservlet?image=" + image;
		Bitmap bitmap = null;
		try {
			InputStream input = new java.net.URL(imageURL).openStream();
			bitmap = BitmapFactory.decodeStream(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	private class LazyMenuFoodCategoryloader extends AsyncTask<Long, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(Long... restaurant) {
			boolean success = false;
			FoodCategoriesBean foodCategoriesBean = new FoodCategoriesBean();
			foodCategoriesBean.setRestaurantid(restaurantItem.getId());
			foodCategoriesBean = MenuDAO.fetchAllfoodCategoriesByRestaurant(foodCategoriesBean, getApplicationContext());
			if (foodCategoriesBean.isValid()) {
				menuFoodCategories = foodCategoriesBean.getMenuFoodCategories();
				success = lazyloadCategory(success);
			} else {
				success = false;
			}

			return Boolean.valueOf(success);
		}

		private boolean lazyloadCategory(boolean success) {
			success = false;
			try {
				MenuFoodCategoryAdapter menuFoodCategoryAdapter = new MenuFoodCategoryAdapter(getApplicationContext());
				menuFoodCategoryAdapter.setMenuFoodCategories(menuFoodCategories);
				categorygridView.setAdapter(menuFoodCategoryAdapter);
				success = true;
			} catch (Exception e) {
				Log.e("Error", "Failed to load First level Food Categories " + e.getLocalizedMessage());
				success = false;
			}
			return success;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {
		MenuFoodCategory menuFoodCategory = (MenuFoodCategory) adapter.getItemAtPosition(position);
		Intent menufoodcategoryitemIntent = new Intent(MenuFoodCategoryActivity.this, MenuFoodCategoryItemsActivity.class);
		menufoodcategoryitemIntent.putExtra("category", menuFoodCategory);
		menufoodcategoryitemIntent.putExtra("restaurant", restaurantItem);
		menufoodcategoryitemIntent.putExtra("status", true);
		startActivity(menufoodcategoryitemIntent);
	}

	@Override
	public void onBackPressed() {
		boolean cartalreadycreated = getSharedPreferences(ActivityStrings.APPPREFERENCES, MODE_PRIVATE).getBoolean(ActivityStrings.CART_ALREADY_CREATED, false);
		if (cartalreadycreated) {
			long cartid = getSharedPreferences(ActivityStrings.APPPREFERENCES, MODE_PRIVATE).getLong(ActivityStrings.CART, 0);
			CartItemsBean cartItemsBean = new CartItemsBean();
			cartItemsBean.setCartid(cartid);
			cartItemsBean = CartDAO.removecartbyid(cartItemsBean);
			if (cartItemsBean.isValid()) {
				getIntent().putExtra(ActivityStrings.CART_ALREADY_CREATED, false);
				cartItemsBean = CartItemDAO.removecartitemsbycartid(cartItemsBean);
			} else {
				Log.e("Error", cartItemsBean.getErrmsg());
			}
		}
		SharedPreferences.Editor editor = getSharedPreferences(ActivityStrings.APPPREFERENCES, MODE_PRIVATE).edit();
		editor.putBoolean(ActivityStrings.CART_ALREADY_CREATED, false);
		editor.putBoolean(ActivityStrings.NEW_CART, true);
		editor.putLong(ActivityStrings.CART, 0);
		editor.putInt(ActivityStrings.CART_ITEM_COUNT, 0);
		editor.putLong(ActivityStrings.RESTAURANT, 0);
		editor.commit();
		getIntent().putExtra("status", true);
		super.onBackPressed();
	}

}
