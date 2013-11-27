package com.caldining;


import java.util.concurrent.ExecutionException;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity {
	TextView textview1;
	@Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
      // textview1=(TextView)findViewById(R.id.label);
       

		try {
			new CalDiningJava(MainActivity.this).execute().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
       // storing string resources into Array
       String[] locations = getResources().getStringArray(R.array.Meals);
        
       // Binding resources Array to ListAdapter
       this.setListAdapter(new ArrayAdapter<String>(this, R.layout.meal, R.id.label, locations));
        
       ListView lv = getListView();

       // listening to single list item on click
       lv.setOnItemClickListener(new OnItemClickListener() {
         public void onItemClick(AdapterView<?> parent, View view,
             int position, long id) {
        	

             // selected item 
             String product = ((TextView) view).getText().toString();
              
             // Launching new Activity on selecting single List Item
             Intent i = new Intent(getApplicationContext(), MenuItems.class);
             // sending data to new activity
             i.putExtra("product", product);
             startActivity(i);
            
         }
       });
   }
}