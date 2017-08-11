package com.foodapp.activities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.foodapp.R;
import com.foodapp.cart.CartBean;
import com.foodapp.cart.CartDAO;
import com.foodapp.cart.CartItemDAO;
import com.foodapp.cart.CartItemsBean;
import com.foodapp.order.Foodorder;
import com.foodapp.order.Foodorderitem;
import com.foodapp.order.OrderDAO;
import com.foodapp.restaurants.RestaurantItem;
import com.foodapp.users.User;
import com.foodapp.users.UserBean;
import com.foodapp.users.UserDAO;

public class CheckoutOrderActivity extends BaseActivity {

	private EditText firstnameEditText;
	private EditText lastnameEditText;
	private EditText addressline1EditText;
	private EditText addressline2EditText;
	private Button placeorderbutton;
	private RestaurantItem restaurant;
	private String statusmsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		restaurant = (RestaurantItem) getIntent().getSerializableExtra("restaurant");
		setContentView(R.layout.activity_checkout_order_landing);
		firstnameEditText = findViewById(R.id.et_checkout_order_first_name);
		lastnameEditText = findViewById(R.id.et_checkout_order_last_name);
		addressline1EditText = findViewById(R.id.et_checkout_order_address_line1);
		addressline2EditText = findViewById(R.id.et_checkout_order_address_line2);
		placeorderbutton = findViewById(R.id.btn_checkout_order_place_order);
		UserBean userBean = new UserBean();
		userBean.setId(getIntent().getStringExtra(ActivityStrings.APP_LOGGED_IN_USER));
		userBean = UserDAO.fetchuserdetailsbyuserid(userBean, getApplicationContext());
		if (userBean.isValid() && userBean.getUser() != null) {
			User user = userBean.getUser();
			firstnameEditText.setText(user.getFirstname());
			lastnameEditText.setText(user.getLastname());
			addressline1EditText.setText(user.getAddressline1());
			addressline2EditText.setText(user.getAddressline2());
		}
	}
	@Override
	protected void onResume() {
		if (menutextview != null) {
			menutextview.setText(String.valueOf(getSharedPreferences(ActivityStrings.APPPREFERENCES, MODE_PRIVATE).getInt(ActivityStrings.CART_ITEM_COUNT, 0)));
		}
		super.onResume();
	}
	public void onclickingplaceorderButton(View view) {
		placeorderbutton.setEnabled(false);
		PlaceOrderTask placeOrderTask = new PlaceOrderTask();
		placeOrderTask.execute("PlaceOrderTask");
		placeorderbutton.setEnabled(true);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.cart_menu_item) {
			Intent menufoodcategoryIntent = new Intent(this, OrderCartLandingActivity.class);
			menufoodcategoryIntent.putExtra("restaurant", restaurant);
			startActivity(menufoodcategoryIntent);
		}
		if (item.getItemId() == R.id.history_item) {
			Intent historypage = new Intent(this, OrderHistoryActivity.class);
			historypage.putExtra("status", true);
			startActivity(historypage);
		}
		return true;
	}
	@Override
	protected void onDestroy() {
		setResult(2);
		super.onDestroy();
	}
	@Override
	protected void onStop() {
		setResult(2);
		super.onStop();
	}
	class SaveOrderTask extends AsyncTask<JSONObject, Integer, Boolean> {
		@Override
		protected void onPostExecute(Boolean result) {
			if (result) {
				new AlertDialog.Builder(CheckoutOrderActivity.this).setIcon(R.drawable.ic_launcher).setTitle("Status").setMessage("Order Placed Successfully")
						.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int arg1) {
								dialog.dismiss();
								setResult(2, null);
								finish();
							}
						}).show();
			}
			super.onPostExecute(result);
		}
		@Override
		protected Boolean doInBackground(JSONObject... jsonorder) {
			boolean success = false;
			try {
				JSONObject order = jsonorder[0].getJSONObject("order");
				Foodorder foodorder = new Foodorder();
				foodorder.setOrderdeliveryaddress(order.getString("order_delivery_address"));
				foodorder.setOrderdeliverycharge(order.getDouble("order_delivery_charge"));
				foodorder.setOrderdeliverydate(order.getString("order_delivery_date"));
				foodorder.setOrderpackagingcharges(order.getDouble("order_packaging_charge"));
				foodorder.setOrdersubtotal(order.getDouble("order_subtotal"));
				foodorder.setOrdertotalamount(order.getDouble("order_total_amount"));
				foodorder.setOrdertotaltax(order.getDouble("order_total_tax"));
				foodorder.setOrderrestaurant(order.getLong("restaurant_id"));
				foodorder.setOrderstatus(ActivityStrings.PROCESSING);
				JSONArray orderitemsarray = order.getJSONArray("orderitems");
				long orderid = OrderDAO.saveneworder(foodorder);
				ArrayList<Foodorderitem> orderitemslist = new ArrayList<Foodorderitem>();
				for (int i = 0; i < orderitemsarray.length(); i++) {
					JSONObject orderitemjson = (JSONObject) orderitemsarray.get(i);
					Foodorderitem orderitem = new Foodorderitem();
					orderitem.setOrderid(orderid);
					orderitem.setOrderitemname(orderitemjson.getString("order_item_name"));
					orderitem.setOrderitemprice(orderitemjson.getDouble("order_item_price"));
					orderitem.setOrderitemquantity(orderitemjson.getInt("order_item_quantity"));
					orderitem.setOrderitemremarks(orderitemjson.getString("order_item_remarks"));
					orderitem.setOrderitemsubtotal(orderitemjson.getDouble("order_item_subtotal"));
					OrderDAO.saveneworderitem(orderitem);
					orderitemslist.add(orderitem);
				}
				success = true;
			} catch (Exception e) {
				success = false;
			}
			return success;
		}
	}

	class PlaceOrderServerTask extends AsyncTask<JSONObject, Integer, JSONObject> {

		@Override
		protected void onPostExecute(JSONObject jsonorder) {
			super.onPostExecute(jsonorder);
			Toast.makeText(getApplicationContext(), statusmsg, Toast.LENGTH_SHORT).show();
			if (jsonorder != null) {
				SaveOrderTask saveordertask = new SaveOrderTask();
				saveordertask.execute(jsonorder);
			} else {
				cancel(true);
			}
		}
		@Override
		protected JSONObject doInBackground(JSONObject... jsonObjects) {
			boolean success = false;
			JSONObject requestjson = jsonObjects[0];
			HttpClient orderClient = null;
			BufferedReader responseReader = null;
			Log.d("placeorderservertask", requestjson.toString());
			try {
				orderClient = new DefaultHttpClient();
				URI uri = new URI(ActivityStrings.APP_SERVER_URL);
				HttpPost postjsonrequest = new HttpPost(uri);
				postjsonrequest.setHeader("Content-type", "application/json");
				StringEntity jsonEntity = new StringEntity(requestjson.toString());
				postjsonrequest.setEntity(jsonEntity);
				HttpResponse response = orderClient.execute(postjsonrequest);
				if (response != null) {
					responseReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
					StringBuffer responseStringBuffer = new StringBuffer("");
					String responseString = "";
					while ((responseString = responseReader.readLine()) != null) {
						responseStringBuffer.append(responseString);
					}
					String statusString = responseStringBuffer.toString();
					Log.d("placeorderservertask", statusString);
					if (statusString.equalsIgnoreCase("success")) {
						success = true;
						statusmsg = "Order Placed!";
					} else {
						success = false;
						statusmsg = "Orders Closed!";
					}
				}
			} catch (Exception e) {
				Log.e("PlaceOrderServerTask", e.getLocalizedMessage());
				success = false;
			} finally {
				try {
					responseReader.close();
				} catch (IOException e) {
					Log.e("PlaceOrderServerTask:responseReader.close()", e.getLocalizedMessage());
					success = false;
				}
			}
			if (success) {
				return requestjson;
			} else {
				return null;
			}
		}
	}

	@Override
	public void onBackPressed() {
		getIntent().putExtra("status", true);
		super.onBackPressed();
	}

	class PlaceOrderTask extends AsyncTask<String, Integer, JSONObject> {
		@Override
		protected void onPostExecute(JSONObject json) {
			super.onPostExecute(json);
			if (json != null) {
				PlaceOrderServerTask orderServerTask = new PlaceOrderServerTask();
				orderServerTask.execute(json);
			}
		}
		@Override
		protected JSONObject doInBackground(String... checkouttask) {
			boolean success = false;
			JSONObject requestJsonObject = null;
			try {
				long cartid = getSharedPreferences(ActivityStrings.APPPREFERENCES, MODE_PRIVATE).getLong(ActivityStrings.CART, 0);
				requestJsonObject = new JSONObject();
				JSONObject orderrequestJsonObject = new JSONObject();
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				CartBean cartbean = new CartBean();
				CartItemsBean cartitembean = null;
				cartbean.setId(cartid);
				cartbean = CartDAO.fetchOrderCartById(cartbean, CheckoutOrderActivity.this);
				if (cartbean.isValid()) {
					cartitembean = new CartItemsBean();
					cartitembean.setCartid(cartid);
					cartitembean = CartItemDAO.fetchOrderCartItemsById(cartitembean, CheckoutOrderActivity.this);
					if (cartitembean.isValid() && cartitembean.getCartitems().size() > 0) {
						ArrayList<CartItemsBean> cartitems = cartitembean.getCartitems();
						try {
							orderrequestJsonObject.put("restaurant_id", restaurant.getId());
							orderrequestJsonObject.put("order_delivery_address", addressline1EditText.getText().toString() + " " + addressline2EditText.getText().toString());
							orderrequestJsonObject.put("order_total_amount", cartbean.getTotalcharges());
							orderrequestJsonObject.put("order_packaging_charge", cartbean.getPackagingcharges());
							orderrequestJsonObject.put("order_delivery_charge", restaurant.getDeliverycharges());
							orderrequestJsonObject.put("order_discount_charge", cartbean.getDiscountcharges());
							orderrequestJsonObject.put("order_subtotal", cartbean.getSubtotalCharges());
							orderrequestJsonObject.put("order_total_tax", cartbean.getTaxcharges());
							orderrequestJsonObject.put("order_item_count", cartitems.size());
							orderrequestJsonObject.put("order_delivery_date", dateFormat.format(new java.util.Date(System.currentTimeMillis())));
							orderrequestJsonObject.put("order_customer_mobile", "9999999");
							JSONArray orderitemsArray = new JSONArray();
							for (CartItemsBean cartitem : cartitems) {
								JSONObject orderitemJsonObject = new JSONObject();
								orderitemJsonObject.put("order_item_name", cartitem.getItemname());
								orderitemJsonObject.put("order_item_price", cartitem.getItemprice());
								orderitemJsonObject.put("order_item_quantity", cartitem.getQuantity());
								orderitemJsonObject.put("order_item_remarks", "none");
								orderitemJsonObject.put("order_item_description", cartitem.getItemname());
								orderitemJsonObject.put("order_item_subtotal", cartitem.getItemsubtotalCharges());
								orderitemsArray.put(orderitemJsonObject);
							}
							orderrequestJsonObject.put("orderitems", orderitemsArray);
							requestJsonObject.put("order", orderrequestJsonObject);
							requestJsonObject.put("id", cartid);
						} catch (Exception e) {
						}
					} else {
						// setContentView(R.layout.cart_type_);
					}
				} else {
					// setContentView(R.layout.cart_type_);
				}

				success = true;
			} catch (Exception e) {
				success = false;
				statusmsg = "Orders Closed!";
			}

			if (!success) {
				cancel(!success);
			}
			return requestJsonObject;
		}
	}
}
