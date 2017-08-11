package com.foodapp.activities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.foodapp.R;
import com.foodapp.cart.CartDAO;
import com.foodapp.database.AppDatabaseInitializer;
import com.foodapp.search.CustomAutoCompleteTextView;
import com.foodapp.search.SearchLocation;
import com.foodapp.services.OrderStatusService;

public class SearchRestaurantActivity extends Activity implements OnItemClickListener {

	private CustomAutoCompleteTextView locationAutoCompleteTextView;
	private ImageView companylogoimageview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_restaurant);
		AppDatabaseInitializer.initializedb(SearchRestaurantActivity.this);
		initializeapplicationpreferences();
		if (!getIntent().getBooleanExtra("status", false)) {
			Log.d("OrderStatusService", "starting++");
			Intent statusservice = new Intent(this, OrderStatusService.class);
			startService(statusservice);
			getIntent().putExtra("status", true);
		}
		companylogoimageview = findViewById(R.id.im_search_type_company_icon);
		companylogoimageview.setImageResource(R.drawable.default_company_logo_placeholder);
		locationAutoCompleteTextView = findViewById(R.id.atv_type_search_edit_location_detail);
		locationAutoCompleteTextView.setThreshold(3);
		locationAutoCompleteTextView.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence location, int arg1, int arg2, int arg3) {
				FetchLocationsTask fetchLocationsTask = new FetchLocationsTask();
				fetchLocationsTask.execute(location.toString());
			}

			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

			}

			public void afterTextChanged(Editable arg0) {

			}
		});
	}

	private void initializeapplicationpreferences() {
		CartDAO.clearallcarttable();
		SharedPreferences sharedPreferences = getSharedPreferences(ActivityStrings.APPPREFERENCES, MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putBoolean(ActivityStrings.NEW_CART, true);
		editor.putInt(ActivityStrings.CART_ITEM_COUNT, 0);
		editor.putBoolean(ActivityStrings.CART_ALREADY_CREATED, false);
		editor.putLong(ActivityStrings.CART, 0);
		editor.commit();
	}

	class FetchLocationsTask extends AsyncTask<CharSequence, Integer, String> {

		@Override
		protected String doInBackground(CharSequence... location) {
			String placeurl = SearchLocation.API_URL + SearchLocation.PARAM_COMPONENTS + "&" + SearchLocation.PARAM_INPUT + location[0] + "&" + SearchLocation.PARAM_TYPES + "&"
					+ SearchLocation.PARAM_SENSOR + "&" + SearchLocation.PARAM_KEY + SearchLocation.PLACES_API_KEY;
			Log.d("FetchLocationsTask", placeurl);
			HttpClient client = null;
			HttpGet getRequest = null;
			String jsonResponse = null;
			try {
				client = new DefaultHttpClient();
				getRequest = new HttpGet();
				getRequest.setURI(new URI(placeurl));
				HttpResponse apiResponse = client.execute(getRequest);
				BufferedReader br = new BufferedReader(new InputStreamReader(apiResponse.getEntity().getContent()));
				StringBuffer sb = new StringBuffer("");
				String line = "";
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				jsonResponse = sb.toString();
				br.close();
			} catch (Exception e) {
				Log.e("FetchLocationsTask:error ", e.getLocalizedMessage());
			}

			return jsonResponse;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (result != null) {
				ParseJsonTask parseJsonTask = new ParseJsonTask();
				parseJsonTask.execute(result);
			}
		}
	}
	private class ParseJsonTask extends AsyncTask<String, Integer, List<HashMap<String, String>>> {

		@Override
		protected List<HashMap<String, String>> doInBackground(String... json) {
			List<HashMap<String, String>> placelist = null;
			try {
				placelist = new ArrayList<HashMap<String, String>>();
				JSONObject responseJsonObject = new JSONObject(json[0]);
				String status = responseJsonObject.getString("status");
				Log.d("ParseJsonTask", status);
				if (status == "OK" || status.equalsIgnoreCase("OK")) {
					JSONArray predictionsArray = responseJsonObject.getJSONArray("predictions");
					for (int i = 0; i < predictionsArray.length(); i++) {
						JSONObject predictionObject = (JSONObject) predictionsArray.get(i);
						String description = predictionObject.getString("description");
						String placeid = predictionObject.getString("place_id");
						String reference = predictionObject.getString("reference");
						HashMap<String, String> placesMap = new HashMap<String, String>();
						placesMap.put("description", description);
						placesMap.put("place_id", placeid);
						placesMap.put("reference", reference);
						placelist.add(placesMap);
					}
				} else {
					cancel(true);
				}
			} catch (Exception e) {
				Log.e("Parser", e.getLocalizedMessage());
			}
			return placelist;
		}

		@Override
		protected void onPostExecute(List<HashMap<String, String>> result) {
			super.onPostExecute(result);
			if (result != null) {
				int tores[] = new int[]{R.id.tv_type_search_edit_location_list};
				String fromString[] = new String[]{"description"};
				SimpleAdapter editlocationAdapter = new SimpleAdapter(SearchRestaurantActivity.this, result, R.layout.type_search_edit_location_list_item, fromString, tores);
				locationAutoCompleteTextView.setAdapter(editlocationAdapter);
				locationAutoCompleteTextView.showDropDown();
			}
		}
	}

	public void onclickingfindrestaurantButton(View view) {
		Intent restaurantslandingIntent = new Intent(SearchRestaurantActivity.this, RestaurantsLandingActivity.class);
		CustomAutoCompleteTextView typesearcheditlocationCompleteTextView = findViewById(R.id.atv_type_search_edit_location_detail);
		String placedescription[] = typesearcheditlocationCompleteTextView.getText().toString().split(",");
		switch (placedescription.length) {
			case 3 :
				restaurantslandingIntent.putExtra(ActivityStrings.SEARCH_RESTAURANT_LOCATION, placedescription[0]);
				break;
			case 4 :
				restaurantslandingIntent.putExtra(ActivityStrings.SEARCH_RESTAURANT_LOCATION, placedescription[1]);
				break;
			default :
				break;
		}
		startActivityForResult(restaurantslandingIntent, ActivityStrings.SEARCH_RESTAURANTS_REQUEST);
	}

	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this).setIcon(R.drawable.ic_launcher).setTitle("EXIT").setMessage("Are you sure you want to exit?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				finish();
			}
		}).setNegativeButton("No", null).show();
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	}

}
