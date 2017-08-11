package com.foodapp.activities;

import java.util.ArrayList;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.foodapp.R;
import com.foodapp.adapter.Orderhistorylistadapter;
import com.foodapp.order.Foodorder;
import com.foodapp.order.Foodorderitem;
import com.foodapp.order.OrderDAO;
import com.foodapp.restaurants.RestaurantItem;

public class OrderHistoryActivity extends BaseActivity {
	private ExpandableListView orderhistorylistview = null;
	private ArrayList<Foodorder> orderhistorylist = null;
	private RestaurantItem restaurant = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		restaurant = (RestaurantItem) getIntent().getSerializableExtra("restaurant");
		orderhistorylistview = (ExpandableListView) findViewById(R.id.orders_history_list_view);
		GetOrderHistoryTask getorderhistorytask = new GetOrderHistoryTask();
		getorderhistorytask.execute("param");
	}

	class GetOrderHistoryTask extends AsyncTask<String, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(String... history) {
			boolean success = false;
			ArrayList<Foodorder> orderlist = OrderDAO.fetchallordersplaced();
			orderhistorylist = new ArrayList<Foodorder>();
			if (orderlist != null) {
				for (Foodorder foodorder : orderlist) {
					ArrayList<Foodorderitem> orderitemlist = OrderDAO.fetchallorderitemsplaced(foodorder.getOrderid());
					foodorder.setOrderitems(orderitemlist);
					orderhistorylist.add(foodorder);
				}
			} else {
				success = false;
			}

			return success;
		}
		@Override
		protected void onPostExecute(Boolean result) {
			if (result) {
				Orderhistorylistadapter orderhistorylistadapter = new Orderhistorylistadapter(getApplicationContext());
				orderhistorylistadapter.setFoodorderlist(orderhistorylist);
				orderhistorylistview.setAdapter(orderhistorylistadapter);
			} else {
				if (orderhistorylist.size() == 0) {
					TextView noorderfoundtextview = findViewById(R.id.orders_history_no_orders);
					noorderfoundtextview.setText("No Orders Placed Now");
				}
			}
			super.onPostExecute(result);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.cart_menu_item) {
			Intent menufoodcategoryIntent = new Intent(this, OrderCartLandingActivity.class);
			menufoodcategoryIntent.putExtra("restaurant", restaurant);
			startActivity(menufoodcategoryIntent);
		}
		return true;
	}
	@Override
	public void onBackPressed() {
		getIntent().putExtra("status", true);
		super.onBackPressed();
	}

}
