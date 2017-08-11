package com.foodapp.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.foodapp.R;
import com.foodapp.order.Foodorder;
import com.foodapp.order.Foodorderitem;

public class Orderhistorylistadapter extends BaseExpandableListAdapter {
	private ArrayList<Foodorder> foodorderlist;
	private Context context;

	public Orderhistorylistadapter(Context context) {
		this.context = context;
	}

	public ArrayList<Foodorder> getFoodorderlist() {
		return foodorderlist;
	}
	public void setFoodorderlist(ArrayList<Foodorder> foodorderlist) {
		this.foodorderlist = foodorderlist;
	}

	@Override
	public int getGroupCount() {
		return foodorderlist.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return foodorderlist.get(groupPosition).getOrderitems().size();
	}

	@Override
	public Foodorder getGroup(int groupPosition) {
		return foodorderlist.get(groupPosition);
	}

	@Override
	public Foodorderitem getChild(int groupPosition, int childPosition) {
		return foodorderlist.get(groupPosition).getOrderitems().get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	class foodorderviewholder {
		TextView orderid;
		TextView itemscount;
		TextView orderdate;
		TextView orderstatus;
		TextView totalamt;
	}

	class foodorderitemviewholder {
		TextView itemname;
		TextView itemquantity;
		TextView itemsubtotal;
	}
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertview, ViewGroup parent) {
		foodorderviewholder holder = null;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertview == null) {
			convertview = inflater.inflate(R.layout.fragment_group_item, parent, false);
			holder = new foodorderviewholder();
			holder.orderid = (TextView) convertview.findViewById(R.id.order_id);
			holder.itemscount = (TextView) convertview.findViewById(R.id.order_total_item);
			holder.orderdate = (TextView) convertview.findViewById(R.id.order_date);
			holder.orderstatus = (TextView) convertview.findViewById(R.id.order_status);
			holder.totalamt = (TextView) convertview.findViewById(R.id.order_total_amt);
			convertview.setTag(holder);
		} else {
			holder = (foodorderviewholder) convertview.getTag();
		}
		holder.orderid.setText(String.valueOf(getGroup(groupPosition).getOrderid()));
		holder.itemscount.setText(String.valueOf(getGroup(groupPosition).getOrderitems().size()));
		holder.orderdate.setText(getGroup(groupPosition).getOrderdeliverydate());
		holder.orderstatus.setText(getGroup(groupPosition).getOrderstatus());
		holder.totalamt.setText(String.valueOf(getGroup(groupPosition).getOrdertotalamount()));
		return convertview;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertview, ViewGroup parent) {
		foodorderitemviewholder holder = null;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertview == null) {
			convertview = inflater.inflate(R.layout.fragment_child_item, parent, false);
			holder = new foodorderitemviewholder();
			holder.itemname = (TextView) convertview.findViewById(R.id.order_item_name);
			holder.itemquantity = (TextView) convertview.findViewById(R.id.item_qty);
			holder.itemsubtotal = (TextView) convertview.findViewById(R.id.sub_total);
			convertview.setTag(holder);
		} else {
			holder = (foodorderitemviewholder) convertview.getTag();
		}
		holder.itemname.setText(getChild(groupPosition, childPosition).getOrderitemname());
		holder.itemquantity.setText(String.valueOf(getChild(groupPosition, childPosition).getOrderitemquantity()));
		holder.itemsubtotal.setText(String.valueOf(getChild(groupPosition, childPosition).getOrderitemsubtotal()));
		return convertview;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}
