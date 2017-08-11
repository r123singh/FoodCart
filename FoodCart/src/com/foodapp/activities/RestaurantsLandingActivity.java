package com.foodapp.activities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.foodapp.R;
import com.foodapp.adapter.CustomRestaurantLandingListAdapter;
import com.foodapp.restaurants.RestaurantBeans;
import com.foodapp.restaurants.RestaurantDAO;
import com.foodapp.restaurants.RestaurantItem;

public class RestaurantsLandingActivity extends Activity implements OnItemClickListener {

	private ListView restaurantsListView;
	private Vector<RestaurantItem> restaurantItems = null;
	private String errormsg = null;
	private ProgressDialog mProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurants_landing);
		String selectedlocation = getIntent().getStringExtra(ActivityStrings.SEARCH_RESTAURANT_LOCATION);
		restaurantsListView = (ListView) findViewById(R.id.restaurants_list_view);
		restaurantsListView.setOnItemClickListener(this);
		if (ActivityStrings.ONLINE_MODE) {
			GetRestaurantsResponse getallrestaurantsbylocationresponse = new GetRestaurantsResponse();
			getallrestaurantsbylocationresponse.execute(new String[]{selectedlocation});
		} else {
			LazyRestaurantsloader lazyRestaurantsloader = new LazyRestaurantsloader();
			lazyRestaurantsloader.execute(new String[]{selectedlocation});
		}
	}

	class GetRestaurantsResponse extends AsyncTask<String, Long, String> {

		@Override
		protected String doInBackground(String... location) {
			String responsemessage = null;
			try {
				String link = "http://10.10.16.120:8080/Foodzill1.1/SyncAllRestaurantServlet?location=" + location[0];
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
				if (responsemessage != null) {
					updateFromDownload(responsemessage);
				} else {
					errormsg = "All Restaurants Closed";
				}

			} catch (Exception e) {
				Log.e("GetRestaurantsResponse:execute", e.getLocalizedMessage());
				errormsg = e.getLocalizedMessage();
				responsemessage = null;
			}
			return responsemessage;
		}

		@Override
		protected void onPostExecute(String response) {
			super.onPostExecute(response);
			if (response != null) {
				Log.d("GetRestaurantsResponse:onPostExecute", response);
				try {
					CustomRestaurantLandingListAdapter customRestaurantLandingListAdapter = new CustomRestaurantLandingListAdapter(RestaurantsLandingActivity.this,
							R.layout.restaurant_landing_list_item, restaurantItems);
					restaurantsListView.setAdapter(customRestaurantLandingListAdapter);
				} catch (Exception e) {
					Log.e("Error", "Failed to load Restaurants list");
				}
			} else {
				errormsg = "All Restaurants Closed";
			}
			if (errormsg != null) {
				Log.e("GetRestaurantsResponse:onPostExecute", errormsg);
			}
		}

		private boolean checkwhetherrestaurantclosedornot(String starttime, String closetime) {
			boolean isavailableatthehour = false;
			try {
				Date startdate = new SimpleDateFormat("hh:mm").parse(starttime);
				Date closedate = new SimpleDateFormat("hh:mm").parse(closetime);
				Calendar calendarstart = Calendar.getInstance();
				calendarstart.setTime(startdate);
				Calendar calendarclose = Calendar.getInstance();
				calendarclose.setTime(closedate);
				calendarstart.set(Calendar.AM_PM, Calendar.AM);
				calendarclose.set(Calendar.AM_PM, Calendar.PM);
				Date currenttime = Calendar.getInstance().getTime();
				if (currenttime.after(calendarstart.getTime()) && currenttime.before(calendarclose.getTime())) {
					isavailableatthehour = true;
				} else {
					isavailableatthehour = false;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return isavailableatthehour;
		}

		private void updateFromDownload(String response) {
			JSONObject restaurantsresponseobj = null;
			try {
				restaurantsresponseobj = new JSONObject(response);
				JSONArray restaurantarray = restaurantsresponseobj.getJSONArray("restaurants");
				String restaurantlocation = restaurantsresponseobj.getString("location");
				restaurantItems = new Vector<RestaurantItem>();
				for (int i = 0; i < restaurantarray.length(); i++) {
					JSONObject restaurantobj = restaurantarray.getJSONObject(i);
					RestaurantItem restaurantitem = new RestaurantItem();
					restaurantitem.setId(restaurantobj.getLong("id"));
					restaurantitem.setMinimumordercharges(restaurantobj.getDouble("minimum_order"));
					restaurantitem.setDeliverycharges(restaurantobj.getDouble("delivery_charge"));
					restaurantitem.setPaymentmode(restaurantobj.getString("payment_mode"));
					restaurantitem.setAddress(restaurantobj.getString("address"));
					restaurantitem.setEstimatedDelivery(String.valueOf(restaurantobj.getInt("estimated_delivery")));
					restaurantitem.setRestaurantmainTitle(restaurantobj.getString("name"));
					String starttime = restaurantobj.getString("starttime").replace(" ", "T").split("T")[1];
					String closetime = restaurantobj.getString("closetime").replace(" ", "T").split("T")[1];
					restaurantitem.setAvailableNow(checkwhetherrestaurantclosedornot(starttime, closetime));
					restaurantitem.setRestaurantloc(restaurantlocation);
					if (restaurantobj.has("bitmap_image")) {
						restaurantitem.setRestaurantimage(restaurantobj.getString("bitmap_image"));
					} else {
						restaurantitem.setRestaurantimage("munchies");
					}
					restaurantItems.add(restaurantitem);
				}
			} catch (Exception e) {
				Log.e("updateFromDownload", e.getLocalizedMessage());
				errormsg = "All Restaurants Closed";
			}
			if (ActivityStrings.STORE_OFFLINE) {
				RestaurantBeans restaurantbean = new RestaurantBeans();
				restaurantbean.setRestaurantItems(restaurantItems);
				restaurantbean = RestaurantDAO.saverestaurantlist(restaurantbean);
			}

		}
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View viewHolder, int position, long arg3) {
		Intent intent = new Intent(RestaurantsLandingActivity.this, MenuFoodCategoryActivity.class);
		RestaurantItem restaurantItem = (RestaurantItem) adapter.getItemAtPosition(position);
		SharedPreferences.Editor editor = getSharedPreferences(ActivityStrings.APPPREFERENCES, MODE_PRIVATE).edit();
		editor.putLong(ActivityStrings.RESTAURANT, restaurantItem.getId());
		editor.commit();
		intent.putExtra("restaurant", restaurantItem);
		intent.putExtra("status", true);
		startActivity(intent);
	}

	private class LazyRestaurantsloader extends AsyncTask<String, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(String... restaurantlocation) {
			boolean success = false;
			RestaurantBeans restaurantBeans = new RestaurantBeans();
			restaurantBeans.setLocationid(restaurantlocation[0].toLowerCase());
			restaurantBeans = RestaurantDAO.fetchAllRestaurantsByLocation(restaurantBeans, getApplicationContext());
			if (restaurantBeans.isValid() && restaurantBeans.getRestaurantItems() != null) {
				restaurantItems = restaurantBeans.getRestaurantItems();
				success = lazyloadRestaurants(success);
			} else if (!restaurantBeans.isValid()) {
				success = false;
				errormsg = restaurantBeans.getErrorMessage();
			}
			return Boolean.valueOf(success);
		}

		private boolean lazyloadRestaurants(boolean success) {
			success = false;
			try {
				CustomRestaurantLandingListAdapter customRestaurantLandingListAdapter = new CustomRestaurantLandingListAdapter(RestaurantsLandingActivity.this, R.layout.restaurant_landing_list_item,
						restaurantItems);
				restaurantsListView.setAdapter(customRestaurantLandingListAdapter);
				success = true;
			} catch (Exception e) {
				Log.e("Error", "Failed to load Restaurants list");
				success = false;
			}
			return success;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if (errormsg != null) {
				Log.e("GetRestaurantsResponse:onPostExecute", errormsg);
			}
		}
	}

}
