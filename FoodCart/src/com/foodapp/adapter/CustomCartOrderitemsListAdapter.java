package com.foodapp.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.foodapp.R;
import com.foodapp.cart.CartItemsBean;

public class CustomCartOrderitemsListAdapter extends BaseAdapter {

	private ArrayList<CartItemsBean> cartitems;
	private Context context;

	public CustomCartOrderitemsListAdapter(Context context) {
		this.context = context;
	}
	@Override
	public int getCount() {
		return getCartitems().size();
	}

	@Override
	public CartItemsBean getItem(int position) {
		return getCartitems().get(position);
	}


	class order_items_view_holder {
		TextView typecartorderitemsnameTextView;
		TextView typecartorderitemsquantityTextView;
		TextView typecartorderitemspriceTextView;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		order_items_view_holder holder = null;
		LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.cart_type_order_items_detail_view_holder, parent, false);
			holder = new order_items_view_holder();
			holder.typecartorderitemsnameTextView = (TextView) convertView.findViewById(R.id.tv_type_cart_order_items_name_detail);
			holder.typecartorderitemsquantityTextView = convertView.findViewById(R.id.tv_type_cart_order_items_qty_detail);
			holder.typecartorderitemspriceTextView = (TextView) convertView.findViewById(R.id.tv_type_cart_order_items_price_detail);
			convertView.setTag(holder);
		} else {
			holder = (order_items_view_holder) convertView.getTag();
		}
		holder.typecartorderitemsnameTextView.setText(getItem(position).getItemname());
		holder.typecartorderitemsquantityTextView.setText(String.valueOf(getItem(position).getQuantity()));
		holder.typecartorderitemspriceTextView.setText(String.valueOf(getItem(position).getItemsubtotalCharges()));
		return convertView;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}
	public ArrayList<CartItemsBean> getCartitems() {
		return cartitems;
	}
	public void setCartitems(ArrayList<CartItemsBean> cartitems) {
		this.cartitems = cartitems;
	}

}
