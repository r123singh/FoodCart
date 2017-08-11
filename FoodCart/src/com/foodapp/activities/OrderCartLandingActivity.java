package com.foodapp.activities;

import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.app.Dialog;
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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.foodapp.R;
import com.foodapp.adapter.CustomCartOrderitemsListAdapter;
import com.foodapp.cart.CartBean;
import com.foodapp.cart.CartDAO;
import com.foodapp.cart.CartItemDAO;
import com.foodapp.cart.CartItemsBean;
import com.foodapp.restaurants.RestaurantItem;

public class OrderCartLandingActivity extends BaseActivity implements OnItemClickListener {
	private long cartid;
	private RestaurantItem restaurant;
	private ImageView typecartimagedetailImageView;
	private TextView typecartimagedetailTextView;
	private TextView typecarteditorderyourorderTextView;
	private TextView typecarteditordereditorderTextView;
	private TextView typecartsubtotalvalueTextView;
	private TextView typecartdeliverychargevalueTextView;
	private TextView typecartpackagingvalueTextView;
	private TextView typecarttotalchargevalueTextView;
	private TextView typecartdeliverydateTextView;
	private CheckBox typecartdeliverytimepriorityCheckBox;
	private ArrayList<CartItemsBean> cartitems;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_cart_landing);
		typecartimagedetailImageView = (ImageView) findViewById(R.id.im_cart_type_image_detail_icon);
		typecartimagedetailTextView = findViewById(R.id.tv_type_cart_image_detail_price);
		typecarteditorderyourorderTextView = findViewById(R.id.tv_type_cart_edit_order_your_order_detail);
		typecarteditordereditorderTextView = findViewById(R.id.tv_type_cart_edit_order_detail);
		typecartsubtotalvalueTextView = findViewById(R.id.tv_type_cart_sub_total_detail);
		typecartdeliverychargevalueTextView = findViewById(R.id.tv_type_cart_delivery_charge_detail);
		typecartpackagingvalueTextView = findViewById(R.id.tv_type_cart_packaging_detail);
		typecarttotalchargevalueTextView = findViewById(R.id.tv_type_cart_total_charge_detail);
		typecartdeliverydateTextView = findViewById(R.id.tv_type_cart_delivery_date_detail);
		typecartdeliverytimepriorityCheckBox = findViewById(R.id.ch_type_cart_delivery_time_priority_detail);
		cartid = getSharedPreferences(ActivityStrings.APPPREFERENCES, MODE_PRIVATE).getLong(ActivityStrings.CART, 0);
		if (cartid == 0) {
			Toast.makeText(getApplicationContext(), "Cart not exist", Toast.LENGTH_LONG).show();
			// setContentView(R.layout.cart_type_);
		} else {
			CartBean cartbean = new CartBean();
			CartItemsBean cartitembean = null;
			cartbean.setId(cartid);
			cartbean = CartDAO.fetchOrderCartById(cartbean, OrderCartLandingActivity.this);
			if (cartbean.isValid()) {
				cartitembean = new CartItemsBean();
				cartitembean.setCartid(cartid);
				cartitembean = CartItemDAO.fetchOrderCartItemsById(cartitembean, OrderCartLandingActivity.this);
				if (cartitembean.isValid() && cartitembean.getCartitems().size() > 0) {
					cartitems = cartitembean.getCartitems();
					restaurant = (RestaurantItem) getIntent().getSerializableExtra("restaurant");
					try {
						typecartimagedetailImageView.setImageBitmap(new LazyBitmapLoader().execute(restaurant.getRestaurantimage()).get());
						typecartimagedetailTextView.setText(String.valueOf(cartbean.getTotalcharges()));
						typecartsubtotalvalueTextView.setText(String.valueOf(cartbean.getSubtotalCharges()));
						typecartdeliverychargevalueTextView.setText(String.valueOf(cartbean.getDeliverycharges()));
						typecartpackagingvalueTextView.setText(String.valueOf(cartbean.getPackagingcharges()));
						typecarttotalchargevalueTextView.setText(String.valueOf(cartbean.getTotalcharges()));
						typecartdeliverydateTextView.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date(System.currentTimeMillis())));
						typecartdeliverytimepriorityCheckBox.setChecked(false);
					} catch (Exception e) {
					}
				} else {
					// setContentView(R.layout.cart_type_);
				}
			} else {
				// setContentView(R.layout.cart_type_);
			}
		}
	}

	private class LazyBitmapLoader extends AsyncTask<String, Integer, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... image) {
			return downloadbitmapimage(image[0]);
		}

		private Bitmap downloadbitmapimage(String image) {
			String imageURL = "http://10.10.16.120:8080/Foodzill1.1/Imageservlet?image=" + image.concat(".png");
			Log.d("downloadfooditemimagetask", imageURL);
			Bitmap bitmap = null;
			try {
				InputStream input = new java.net.URL(imageURL).openStream();
				bitmap = BitmapFactory.decodeStream(input);
			} catch (Exception e) {
				Log.e("downloadfooditemimagetask", e.getLocalizedMessage());
			}
			return bitmap;
		}
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
	public void checkoutorderitemsintherodercart(View view) {
		Intent checkoutorderactivityIntent = new Intent(OrderCartLandingActivity.this, CheckoutOrderActivity.class);
		checkoutorderactivityIntent.putExtra("cartid", cartid);
		checkoutorderactivityIntent.putExtra("restaurant", restaurant);
		checkoutorderactivityIntent.putExtra("status", true);
		startActivityForResult(checkoutorderactivityIntent, 1);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d("onactivityresutl", requestCode + "" + resultCode);
		if (resultCode == 2) {
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
			if (menutextview != null) {
				menutextview.setText(String.valueOf(getSharedPreferences(ActivityStrings.APPPREFERENCES, MODE_PRIVATE).getInt(ActivityStrings.CART_ITEM_COUNT, 0)));
			}
			finish();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	public void onclickvieworderitems(View view) {
		Dialog dialog = new Dialog(OrderCartLandingActivity.this);
		dialog.setContentView(R.layout.cart_type_order_items_detail_list_view);
		dialog.setTitle("Orderitems");
		CustomCartOrderitemsListAdapter customadapter = new CustomCartOrderitemsListAdapter(getApplicationContext());
		customadapter.setCartitems(cartitems);
		ListView orderlistitems = dialog.findViewById(R.id.list_cart_type_order_items_detail);
		orderlistitems.setAdapter(customadapter);
		dialog.setCancelable(true);
		dialog.show();
	}

	@Override
	public void onBackPressed() {
		getIntent().putExtra("status", true);
		super.onBackPressed();
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int arg2, long arg3) {
	}

}
