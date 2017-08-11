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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.foodapp.R;
import com.foodapp.adapter.CustomFoodCategoryItemsAdapter;
import com.foodapp.food.FoodDAO;
import com.foodapp.food.FoodItem;
import com.foodapp.food.FoodItemsBeans;
import com.foodapp.food.FooditemBean;
import com.foodapp.menu.MenuFoodCategory;
import com.foodapp.restaurants.RestaurantItem;

public class MenuFoodCategoryItemsActivity extends BaseActivity implements OnItemClickListener {
	private ListView fooditemsListView;
	private ImageView restaurantlogoView;
	private MenuFoodCategory menufoodcategory;
	private RestaurantItem restaurantitem;
	private Vector<FoodItem> fooditemsList = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_food_category_items_landing);
		fooditemsListView = (ListView) findViewById(R.id.food_items_list_view);
		fooditemsListView.setOnItemClickListener(this);
		restaurantlogoView = findViewById(R.id.im_menu_food_category_items_restaurant);
		menufoodcategory = (MenuFoodCategory) getIntent().getSerializableExtra("category");
		restaurantitem = (RestaurantItem) getIntent().getSerializableExtra("restaurant");
		try {
			restaurantlogoView.setImageBitmap(new downloadrestaurantlogotask().execute(restaurantitem.getRestaurantimage()).get());
		} catch (Exception e) {

		}
		if (ActivityStrings.ONLINE_MODE) {
			Getmenucategoryitemstask getmenucategoryitemstask = new Getmenucategoryitemstask();
			getmenucategoryitemstask.execute(0l);
		} else {
			LazyFooditemsloader lazyRestaurantsloader = new LazyFooditemsloader();
			lazyRestaurantsloader.execute(0l);
		}
	}
	@Override
	protected void onResume() {
		if (menutextview != null) {
			menutextview.setText(String.valueOf(getSharedPreferences(ActivityStrings.APPPREFERENCES, MODE_PRIVATE).getInt(ActivityStrings.CART_ITEM_COUNT, 0)));
		}
		super.onResume();
	}

	class downloadrestaurantlogotask extends AsyncTask<String, Integer, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... image) {
			String imageURL = "http://10.10.16.120:8080/Foodzill1.1/Imageservlet?image=" + image[0].concat(".png");
			Log.d("downloadrestaurantlogotask", imageURL);
			Bitmap bitmap = null;
			try {
				InputStream input = new java.net.URL(imageURL).openStream();
				bitmap = BitmapFactory.decodeStream(input);
			} catch (Exception e) {
				Log.e("downloadrestaurantlogotask", e.getLocalizedMessage());
			}
			return bitmap;
		}
	}

	class Getmenucategoryitemstask extends AsyncTask<Long, Integer, String> {

		@Override
		protected String doInBackground(Long... restaurant) {
			String responsemessage = null;
			try {
				String link = "http://10.10.16.120:8080/Foodzill1.1/SyncMenuItemByCategory?category=" + menufoodcategory.getName() + "&restaurant=" + restaurantitem.getId();
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
			} catch (Exception e) {
				Log.e("Getmenucategoryitemstask:execute", e.getLocalizedMessage());
				responsemessage = null;
			}
			return responsemessage;
		}
		@Override
		protected void onPostExecute(String response) {
			super.onPostExecute(response);
			if (response != null) {
				Log.d("onPostExecute", response);
				updateFromDownload(response);
				CustomFoodCategoryItemsAdapter customFoodCategoryItemsAdapter = new CustomFoodCategoryItemsAdapter(getApplicationContext(), R.layout.food_category_items_list_item, fooditemsList);
				fooditemsListView.setAdapter(customFoodCategoryItemsAdapter);
			}
		}
	}

	public void updateFromDownload(String response) {

		JSONObject menuitemresponse = null;
		try {
			fooditemsList = new Vector<FoodItem>();
			menuitemresponse = new JSONObject(response);
			JSONArray menuitemsarray = menuitemresponse.getJSONArray("menu_items");
			for (int i = 0; i < menuitemsarray.length(); i++) {
				JSONObject menuitemjson = menuitemsarray.getJSONObject(i);
				FoodItem fooditem = new FoodItem();
				fooditem.setItemname(menuitemjson.getString("name"));
				fooditem.setItemDescription(menuitemjson.getString("description"));
				fooditem.setItemdiscountcharges(menuitemjson.getDouble("discount"));
				fooditem.setBitmapimage(menuitemjson.getString("category_image"));
				fooditem.setItempackagingcharges(menuitemjson.getDouble("packaging"));
				fooditem.setItemtaxcharges(menuitemjson.getDouble("tax"));
				fooditem.setItemprice(menuitemjson.getDouble("price"));
				fooditemsList.add(fooditem);
				if (ActivityStrings.STORE_OFFLINE) {
					fooditem.setMenufoodCategoryId(menufoodcategory.getId());
					FooditemBean fooditembean = new FooditemBean();
					fooditembean.setFoodItem(fooditem);
					fooditembean = FoodDAO.savemenucategoryfooditem(fooditembean);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.cart_menu_item) {
			Intent menufoodcategoryIntent = new Intent(this, OrderCartLandingActivity.class);
			menufoodcategoryIntent.putExtra("restaurant", restaurantitem);
			startActivity(menufoodcategoryIntent);
		}
		if (item.getItemId() == R.id.history_item) {
			Intent historypage = new Intent(this, OrderHistoryActivity.class);
			historypage.putExtra("status", true);
			startActivity(historypage);
		}
		return true;
	}
	private class LazyFooditemsloader extends AsyncTask<Long, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(Long... menucategory) {
			boolean success = false;
			FoodItemsBeans foodItemsBeans = new FoodItemsBeans();
			foodItemsBeans.setMenufoodCategoryId(menufoodcategory.getId());
			foodItemsBeans = FoodDAO.fetchallFooditemsByCategory(foodItemsBeans, getApplicationContext());
			if (foodItemsBeans.isValid() && foodItemsBeans.getFoodItems() != null) {
				fooditemsList = foodItemsBeans.getFoodItems();
				success = lazyloadFoodItems(success);
			} else if (!foodItemsBeans.isValid()) {
				success = false;
			}
			return Boolean.valueOf(success);
		}

		private boolean lazyloadFoodItems(boolean success) {
			success = false;
			try {
				CustomFoodCategoryItemsAdapter customFoodCategoryItemsAdapter = new CustomFoodCategoryItemsAdapter(getApplicationContext(), R.layout.food_category_items_list_item, fooditemsList);
				fooditemsListView.setAdapter(customFoodCategoryItemsAdapter);
				success = true;
			} catch (Exception e) {
				Log.e("Error", "Failed to load Restaurants list");
				success = false;
			}
			return success;
		}
	}

	@Override
	public void onBackPressed() {
		getIntent().putExtra("status", true);
		super.onBackPressed();
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {
		FoodItem foodItem = (FoodItem) adapter.getItemAtPosition(position);
		Intent addtoordercartactivityintent = new Intent(MenuFoodCategoryItemsActivity.this, AddtoOrderLandingActivity.class);
		addtoordercartactivityintent.putExtra("fooditem", foodItem);
		addtoordercartactivityintent.putExtra("restaurant", restaurantitem);
		addtoordercartactivityintent.putExtra("status", true);
		startActivity(addtoordercartactivityintent);
	}

}
