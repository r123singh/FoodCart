package com.foodapp.activities;

import java.io.InputStream;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.foodapp.R;
import com.foodapp.cart.CartBean;
import com.foodapp.cart.CartDAO;
import com.foodapp.cart.CartItemDAO;
import com.foodapp.cart.CartItemsBean;
import com.foodapp.food.FoodItem;
import com.foodapp.restaurants.RestaurantItem;

public class AddtoOrderLandingActivity extends BaseActivity implements OnItemClickListener {

	private FoodItem selectedfooditem;
	private RestaurantItem restaurantitem;
	private ImageView typeimagedetailImageView;
	private TextView typeordertitledetailTextView;
	private TextView typeorderdescriptiondetailTextView;
	private TextView typeorderpricevaluedetailTextView;
	private TextView typeorderquantitydetailtotalpriceTextView;
	private TextView typeorderquantitydetailquantityTextView;
	private EditText itemspecialinstructionstext;
	private int itemtotalquantity = 1;
	private double itemsubtotalcharges;
	private double itemtotalcharges;
	private double itemtotalpackaging;
	private double itemtotaltaxcharges;
	private double itemtotaldiscountcharges;
	private double restaurantdeliverycharges;
	private boolean quantitytoggled = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_order_landing);
		selectedfooditem = (FoodItem) getIntent().getSerializableExtra("fooditem");
		restaurantitem = (RestaurantItem) getIntent().getSerializableExtra("restaurant");
		restaurantdeliverycharges = restaurantitem.getDeliverycharges();
		typeimagedetailImageView = findViewById(R.id.im_order_type_image_detail_icon);
		typeordertitledetailTextView = findViewById(R.id.tv_type_order_title_detail);
		typeorderdescriptiondetailTextView = findViewById(R.id.tv_type_order_description_detail);
		typeorderpricevaluedetailTextView = findViewById(R.id.tv_type_order_price_value_detail);
		typeorderquantitydetailtotalpriceTextView = findViewById(R.id.tv_type_order_quantity_detail_subtotal_price);
		typeorderquantitydetailquantityTextView = findViewById(R.id.tv_type_order_quantity_detail);
		itemspecialinstructionstext = findViewById(R.id.tv_type_order_remarks_edit_text_detail);
		try {
			typeimagedetailImageView.setImageBitmap(new LazyBitmapLoader().execute(selectedfooditem.getBitmapimage()).get());
			typeordertitledetailTextView.setText(selectedfooditem.getItemname());
			typeorderdescriptiondetailTextView.setText(selectedfooditem.getItemDescription());
			typeorderpricevaluedetailTextView.setText(ActivityStrings.CURRENCY_CODE + "" + String.valueOf(selectedfooditem.getItemprice()));
			typeorderquantitydetailtotalpriceTextView.setText(ActivityStrings.CURRENCY_CODE + "" + String.valueOf(selectedfooditem.getItemprice() * selectedfooditem.getQuantity()));
			typeorderquantitydetailquantityTextView.setText(String.valueOf(selectedfooditem.getQuantity()));
		} catch (Exception e) {
			Log.e("Error", "Failed to load Order item" + e.getLocalizedMessage());
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

	public void decrementitemquantity(View view) {
		quantitytoggled = true;
		if (itemtotalquantity > 1) {
			itemtotalquantity = itemtotalquantity - 1;
			itemsubtotalcharges = selectedfooditem.getItemprice() * itemtotalquantity;
			itemtotaldiscountcharges = itemtotalquantity * selectedfooditem.getItemdiscountcharges();
			itemtotalpackaging = itemtotalquantity * selectedfooditem.getItempackagingcharges();
			itemtotaltaxcharges = itemtotalquantity * selectedfooditem.getItemtaxcharges();
			itemtotalcharges = itemsubtotalcharges + itemtotalpackaging + itemtotaltaxcharges - itemtotaldiscountcharges + restaurantdeliverycharges;
			typeorderquantitydetailquantityTextView.setText(String.valueOf(itemtotalquantity));
			typeorderquantitydetailtotalpriceTextView.setText(ActivityStrings.CURRENCY_CODE + "" + String.valueOf(itemsubtotalcharges));
		}
	}

	public void incrementitemquantity(View view) {
		quantitytoggled = true;
		itemtotalquantity = itemtotalquantity + 1;
		itemsubtotalcharges = selectedfooditem.getItemprice() * itemtotalquantity;
		itemtotaldiscountcharges = itemtotalquantity * selectedfooditem.getItemdiscountcharges();
		itemtotalpackaging = itemtotalquantity * selectedfooditem.getItempackagingcharges();
		itemtotaltaxcharges = itemtotalquantity * selectedfooditem.getItemtaxcharges();
		itemtotalcharges = itemsubtotalcharges + itemtotalpackaging + itemtotaltaxcharges - itemtotaldiscountcharges + restaurantdeliverycharges;
		typeorderquantitydetailquantityTextView.setText(String.valueOf(itemtotalquantity));
		typeorderquantitydetailtotalpriceTextView.setText(ActivityStrings.CURRENCY_CODE + "" + String.valueOf(itemsubtotalcharges));
	}
	public void onclickAddOrderToCartButton(View view) {
		AdditemToCartTask additemsToCartTask = new AdditemToCartTask();
		boolean newcarttaskoroldcart = getSharedPreferences(ActivityStrings.APPPREFERENCES, MODE_PRIVATE).getBoolean(ActivityStrings.NEW_CART, true);
		String taskstring = "";
		if (newcarttaskoroldcart) {
			taskstring = "Task create newcart ";
		} else {
			taskstring = "Adding to Old Cart: ";
		}
		additemsToCartTask.execute(new String[]{taskstring});
	}

	class AdditemToCartTask extends AsyncTask<String, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(String... task) {
			Log.d("AdditemToCartTask:", task[0]);
			boolean success = false;
			try {
				if (!quantitytoggled) {
					itemtotalquantity = 1;
					itemsubtotalcharges = selectedfooditem.getItemprice() * itemtotalquantity;
					itemtotaldiscountcharges = itemtotalquantity * selectedfooditem.getItemdiscountcharges();
					itemtotalpackaging = itemtotalquantity * selectedfooditem.getItempackagingcharges();
					itemtotaltaxcharges = itemtotalquantity * selectedfooditem.getItemtaxcharges();
					itemtotalcharges = itemsubtotalcharges + itemtotalpackaging + itemtotaltaxcharges - itemtotaldiscountcharges + restaurantdeliverycharges;
				}
				String fooditemremarks = itemspecialinstructionstext.getText().toString();
				if (fooditemremarks.isEmpty() || fooditemremarks == "") {
					fooditemremarks = "none";
				}
				CartBean cartbean = new CartBean();
				boolean newcarttaskoroldcart = getSharedPreferences(ActivityStrings.APPPREFERENCES, MODE_PRIVATE).getBoolean(ActivityStrings.NEW_CART, true);
				if (newcarttaskoroldcart) {
					cartbean.setDeliverycharges(restaurantitem.getDeliverycharges());
					cartbean.setRestaurant(restaurantitem.getId());
					cartbean.setPackagingcharges(itemtotalpackaging);
					cartbean.setTaxcharges(itemtotaltaxcharges);
					cartbean.setDiscountcharges(itemtotaldiscountcharges);
					cartbean.setSubtotalCharges(itemsubtotalcharges);
					cartbean.setTotalcharges(itemtotalcharges);
					cartbean.setRestaurantimage(restaurantitem.getRestaurantimage());
					cartbean = CartDAO.pushnewfoodcartforordering(cartbean, AddtoOrderLandingActivity.this);
					if (cartbean.isValid()) {
						CartItemsBean cartitembean = new CartItemsBean();
						cartitembean.setItemname(selectedfooditem.getItemname());
						cartitembean.setItemprice(selectedfooditem.getItemprice());
						cartitembean.setItemdiscountcharges(itemtotaldiscountcharges);
						cartitembean.setItempackagingcharges(itemtotalpackaging);
						cartitembean.setItemtaxcharges(itemtotaltaxcharges);
						cartitembean.setItemsubtotalCharges(itemsubtotalcharges);
						cartitembean.setItemtotalcharges(itemtotalcharges);
						cartitembean.setQuantity(itemtotalquantity);
						cartitembean.setCartid(cartbean.getId());
						cartitembean = CartItemDAO.pushfooditemintocart(cartitembean, AddtoOrderLandingActivity.this);
						if (cartitembean.isValid()) {
							Log.d("AdditemsToCartTask", "Cart Item Pushed" + "New Cart: " + cartbean.getId() + " pushed");
							SharedPreferences sharedPreferences = getSharedPreferences(ActivityStrings.APPPREFERENCES, MODE_PRIVATE);
							SharedPreferences.Editor editor = sharedPreferences.edit();
							editor.putBoolean(ActivityStrings.NEW_CART, false);
							editor.putLong(ActivityStrings.CART, cartbean.getId());
							editor.putBoolean(ActivityStrings.CART_ALREADY_CREATED, true);
							editor.commit();
							success = true;
						} else {
							success = false;
						}
					} else {
						success = false;
					}

				} else if (!newcarttaskoroldcart) {
					long oldcartid = getSharedPreferences(ActivityStrings.APPPREFERENCES, MODE_PRIVATE).getLong(ActivityStrings.CART, 0);
					cartbean.setId(oldcartid);
					cartbean = CartDAO.fetchOrderCartById(cartbean, AddtoOrderLandingActivity.this);
					double packagingcharges = cartbean.getPackagingcharges() + itemtotalpackaging;
					double taxcharges = cartbean.getTaxcharges() + itemtotaltaxcharges;
					double discountcharges = cartbean.getDiscountcharges() + itemtotaldiscountcharges;
					double subtotalcharges = cartbean.getSubtotalCharges() + itemsubtotalcharges;
					double totalcharges = cartbean.getTotalcharges() + itemtotalcharges;
					cartbean.setDiscountcharges(discountcharges);
					cartbean.setPackagingcharges(packagingcharges);
					cartbean.setTaxcharges(taxcharges);
					cartbean.setSubtotalCharges(subtotalcharges);
					cartbean.setTotalcharges(totalcharges);
					cartbean = CartDAO.updateoldcart(cartbean, AddtoOrderLandingActivity.this);
					CartItemsBean cartitembean = new CartItemsBean();
					cartitembean.setItemname(selectedfooditem.getItemname());
					cartitembean.setItemprice(selectedfooditem.getItemprice());
					cartitembean.setItemdiscountcharges(itemtotaldiscountcharges);
					cartitembean.setItempackagingcharges(itemtotalpackaging);
					cartitembean.setItemtaxcharges(itemtotaltaxcharges);
					cartitembean.setItemsubtotalCharges(itemsubtotalcharges);
					cartitembean.setItemtotalcharges(itemtotalcharges);
					cartitembean.setQuantity(itemtotalquantity);
					cartitembean.setCartid(oldcartid);
					cartitembean = CartItemDAO.pushfooditemintocart(cartitembean, getApplicationContext());
					if (cartitembean.isValid()) {
						Log.d("AdditemsToCartTask:", "Old Cart Item Pushed " + oldcartid);
						success = true;
					} else {
						success = false;
					}
				}
			} catch (Exception e) {
				Log.e("AdditemsToCartTask:Error", e.getLocalizedMessage());
			}
			return success;
		}
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if (result) {
				int totalcartitems = getSharedPreferences(ActivityStrings.APPPREFERENCES, MODE_PRIVATE).getInt(ActivityStrings.CART_ITEM_COUNT, 0);
				totalcartitems = totalcartitems + 1;
				menutextview.setText(String.valueOf(totalcartitems));
				SharedPreferences.Editor editor = getSharedPreferences(ActivityStrings.APPPREFERENCES, MODE_PRIVATE).edit();
				editor.putInt(ActivityStrings.CART_ITEM_COUNT, totalcartitems);
				editor.commit();
				Toast.makeText(getApplicationContext(), "Items added to Cart", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getApplicationContext(), "Failed Items add", Toast.LENGTH_SHORT).show();
			}
			finish();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.cart_menu_item) {
			Intent menufoodcategoryIntent = new Intent(this, OrderCartLandingActivity.class);
			menufoodcategoryIntent.putExtra("restaurant", restaurantitem);
			menufoodcategoryIntent.putExtra("status", true);
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
	public void onItemClick(AdapterView<?> adapter, View arg1, int arg2, long arg3) {
	}

	@Override
	public void onBackPressed() {
		getIntent().putExtra("status", true);
		super.onBackPressed();
	}
	public FoodItem getSelectedfooditem() {
		return selectedfooditem;
	}

	public void setSelectedfooditem(FoodItem selectedfooditem) {
		this.selectedfooditem = selectedfooditem;
	}

}
