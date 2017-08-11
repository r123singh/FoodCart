package com.foodapp.adapter;

import java.io.InputStream;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.foodapp.R;
import com.foodapp.menu.MenuFoodCategory;

public class MenuFoodCategoryAdapter extends BaseAdapter {
	private Context context;
	private Vector<MenuFoodCategory> menuFoodCategories;

	public MenuFoodCategoryAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;

	}

	@Override
	public int getCount() {
		return menuFoodCategories.size();
	}

	@Override
	public MenuFoodCategory getItem(int position) {
		return menuFoodCategories.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public Vector<MenuFoodCategory> getMenuFoodCategories() {
		return menuFoodCategories;
	}

	public void setMenuFoodCategories(Vector<MenuFoodCategory> menuFoodCategories) {
		this.menuFoodCategories = menuFoodCategories;
	}

	public class MenuFoodCategoryViewHolder {
		ImageView foodcategoryImage;
		TextView foodcategoryTextView;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MenuFoodCategoryViewHolder holder = null;
		LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.menu_food_category_view_holder, parent, false);
			holder = new MenuFoodCategoryViewHolder();
			holder.foodcategoryImage = (ImageView) convertView.findViewById(R.id.im_menu_food_category_icon);
			holder.foodcategoryTextView = (TextView) convertView.findViewById(R.id.tv_menu_food_category_description);
			convertView.setTag(holder);
		} else {
			holder = (MenuFoodCategoryViewHolder) convertView.getTag();
		}
		try {
			holder.foodcategoryImage.setImageBitmap(new LazyBitmapLoader().execute(getItem(position).getCategoryimage()).get());
			holder.foodcategoryTextView.setText(getItem(position).getDescription());
		} catch (Exception e) {
			Log.e("menufoodcategoryadapter", e.getLocalizedMessage());
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
