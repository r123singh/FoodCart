package com.foodapp.adapter;

import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.foodapp.R;
import com.foodapp.food.FoodItem;

public class CustomFoodCategoryItemsAdapter extends ArrayAdapter<FoodItem> {

	private Vector<FoodItem> foodItems;
	private Context context;
	private static final String CURRENCY_CODE = "\u20B9";

	public CustomFoodCategoryItemsAdapter(Context context, int resource, Vector<FoodItem> foodItems) {
		super(context, resource, foodItems);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.foodItems = foodItems;
	}

	private class FoodItemViewHolder {
		TextView itemnameTextView;
		TextView itemDescriptionTextView;
		TextView itempriceTextView;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return foodItems.size();
	}

	@Override
	public FoodItem getItem(int position) {
		// TODO Auto-generated method stub
		return foodItems.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void remove(FoodItem foodItem) {
		// TODO Auto-generated method stub
		super.remove(foodItem);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		FoodItemViewHolder foodItemViewHolder = null;
		FoodItem foodItem = getItem(position);
		LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.food_category_items_list_item, parent, false);
			foodItemViewHolder = new FoodItemViewHolder();
			foodItemViewHolder.itemnameTextView = (TextView) convertView.findViewById(R.id.tv_food_catergory_item_name);
			foodItemViewHolder.itemDescriptionTextView = convertView.findViewById(R.id.tv_food_category_item_decription);
			foodItemViewHolder.itempriceTextView = convertView.findViewById(R.id.tv_food_category_item_price);
			convertView.setTag(foodItemViewHolder);
		} else {
			foodItemViewHolder = (FoodItemViewHolder) convertView.getTag();
		}
		foodItemViewHolder.itemnameTextView.setText(foodItem.getItemname());
		foodItemViewHolder.itemDescriptionTextView.setText(foodItem.getItemDescription());
		foodItemViewHolder.itempriceTextView.setText(CURRENCY_CODE + "" + foodItem.getItemprice());
		return convertView;
	}

}
