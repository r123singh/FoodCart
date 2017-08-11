package com.foodapp.adapter;

import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.foodapp.R;
import com.foodapp.restaurants.RestaurantItem;

public class CustomRestaurantLandingListAdapter extends ArrayAdapter<RestaurantItem> {

	private Context context;
	private List<RestaurantItem> restaurantItems;
	private static final String CURRENCY_CODE = "\u20B9";

	public CustomRestaurantLandingListAdapter(Context context, int resource, List<RestaurantItem> restaurantItems) {
		super(context, resource, restaurantItems);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.restaurantItems = restaurantItems;
	}

	@Override
	public RestaurantItem getItem(int position) {
		// TODO Auto-generated method stub
		return restaurantItems.get(position);
	}

	private class RestaurantViewHolder {
		ImageView restaurantImage;
		TextView restaurantTitle;
		TextView deliveryStringTextView;
		TextView paymentmodeTextView;
		TextView categoriesTextView;
		TextView distancevalueTextView;
		TextView estimatedTextView;
		TextView deliverychargesTextView;
		TextView minimumordervalueTextView;
		TextView availableTextView;
	}

	@Override
	public void remove(RestaurantItem restaurantItem) {
		// TODO Auto-generated method stub
		super.remove(restaurantItem);
	}
	public View getView(int position, View convertView, ViewGroup parent) {
		RestaurantViewHolder restaurantViewHolder = null;
		RestaurantItem restaurantItem = getItem(position);
		LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.restaurant_landing_list_item, parent, false);
			restaurantViewHolder = new RestaurantViewHolder();
			restaurantViewHolder.restaurantTitle = (TextView) convertView.findViewById(R.id.tv_restaurant_title);
			restaurantViewHolder.deliveryStringTextView = (TextView) convertView.findViewById(R.id.tv_restaurant_delivery_string);
			restaurantViewHolder.restaurantImage = (ImageView) convertView.findViewById(R.id.im_restaurant_icon);
			restaurantViewHolder.paymentmodeTextView = (TextView) convertView.findViewById(R.id.tv_restaurant_payment_mode);
			restaurantViewHolder.categoriesTextView = (TextView) convertView.findViewById(R.id.tv_restaurant_food_categories);
			restaurantViewHolder.distancevalueTextView = convertView.findViewById(R.id.tv_restaurant_distance_value);
			restaurantViewHolder.estimatedTextView = convertView.findViewById(R.id.tv_restaurant_estimated_delivery);
			restaurantViewHolder.deliverychargesTextView = convertView.findViewById(R.id.tv_restaurant_delivery_charges);
			restaurantViewHolder.minimumordervalueTextView = convertView.findViewById(R.id.tv_restaurant_minimum_order_value);
			restaurantViewHolder.availableTextView = convertView.findViewById(R.id.tv_restaurant_available_now);
			convertView.setTag(restaurantViewHolder);
		} else {
			restaurantViewHolder = (RestaurantViewHolder) convertView.getTag();

		}
		try {
			restaurantViewHolder.restaurantTitle.setText(restaurantItem.getRestaurantmainTitle());
			restaurantViewHolder.restaurantImage.setImageBitmap(new LazyBitmapLoader().execute(restaurantItem.getRestaurantimage()).get());
			restaurantViewHolder.deliveryStringTextView.setText("Delivery");
			restaurantViewHolder.paymentmodeTextView.setText(restaurantItem.getPaymentmode());
			restaurantViewHolder.categoriesTextView.setText(restaurantItem.getFoodcategories());
			restaurantViewHolder.distancevalueTextView.setText(restaurantItem.getRestaurantDistance());
			restaurantViewHolder.estimatedTextView.setText(restaurantItem.getEstimatedDelivery());
			restaurantViewHolder.deliverychargesTextView.setText(CURRENCY_CODE + "" + restaurantItem.getDeliverycharges());
			restaurantViewHolder.minimumordervalueTextView.setText(CURRENCY_CODE + "" + restaurantItem.getMinimumordercharges());
			restaurantViewHolder.availableTextView.setText(restaurantItem.isAvailableNow() ? "Avaialable" : "Closed");
			restaurantViewHolder.availableTextView.setBackgroundColor(restaurantItem.isAvailableNow() ? Color.GREEN : Color.RED);
		} catch (Exception e) {
			Log.e("customrestuarantlistadapter", e.getLocalizedMessage());
		}

		return convertView;
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

}
