package com.foodapp.activities;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.foodapp.R;

public class BaseActivity extends Activity {
	public TextView menutextview;

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		final MenuItem item = menu.findItem(R.id.history_item);
		final MenuItem cartitem = menu.findItem(R.id.cart_menu_item);
		RelativeLayout layout = (RelativeLayout) item.getActionView();
		layout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onOptionsItemSelected(item);
			}
		});
		RelativeLayout cartlayout = (RelativeLayout) cartitem.getActionView();
		cartlayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onOptionsItemSelected(cartitem);
			}
		});
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.app_custom_menu, menu);
		RelativeLayout layout = (RelativeLayout) menu.findItem(R.id.cart_menu_item).getActionView();
		int itemcount = getSharedPreferences(ActivityStrings.APPPREFERENCES, MODE_PRIVATE).getInt(ActivityStrings.CART_ITEM_COUNT, 0);
		menutextview = layout.findViewById(R.id.actionbar_cart_textview);
		menutextview.setText(String.valueOf(itemcount));
		return true;
	}

}
