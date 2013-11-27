package com.caldining;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class listV extends ListActivity{

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
	}
	static final String[] Meal = new String[]{
		"Breakfast", "Lunch", "Dinner"
	};
}
