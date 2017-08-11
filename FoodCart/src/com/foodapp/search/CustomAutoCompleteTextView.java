package com.foodapp.search;

import java.util.HashMap;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.AutoCompleteTextView;

public class CustomAutoCompleteTextView extends AutoCompleteTextView {

	public CustomAutoCompleteTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CharSequence convertSelectionToString(Object selectedItem) {
		HashMap<String, String> selectedmap = ((HashMap<String, String>) selectedItem);
		return selectedmap.get("description");
	}

}
