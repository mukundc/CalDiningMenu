package com.caldining;

import java.io.IOException;
import java.util.*;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.os.AsyncTask;
import android.widget.TextView;

public class CalDiningJava extends AsyncTask<Void, Void, Document> {

	ArrayList menu = new ArrayList(); // The menu array that will contain all menus for all places at all meals
    public MainActivity host = new MainActivity();

	    
	    /** The system calls this to perform work in the UI thread and delivers
	      * the result from doInBackground() 
	     * @return */
	public CalDiningJava (MainActivity host) 
    {
        this.host=host;
    }
	  @Override
      protected void onPreExecute() {
		  super.onPreExecute();
	     //host.textview1.setText("Gathering Data...");
      }
    
    @Override
    protected Document doInBackground(Void... unused) {
		Document doc = null;

		try {
			doc = Jsoup.connect("http://services.housing.berkeley.edu/FoodPro/dining/static/todaysentrees.asp").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
		
		return doc;
		
    }
	
    protected void onPostExecute(Document doc) {
    	Elements scraped = doc.select("table table td");
		Object[] tables = scraped.toArray();
		
		for (int i = 15; i <= 26; i++) {
			ArrayList<String> color = new ArrayList<String>(); // The current color for a place at a time (e.g. Crossroads Lunch)
			ArrayList<String> curr = new ArrayList<String>(); // The current menu for a place at a time (e.g. Crossroads Lunch)

			String blocks = (String) tables[i].toString().split("<hr />")[1]; // All items for a specific meal (e.g. all dinner items)
			if (!blocks.contains("Closed")) { // Only do the following if it's not closed
				String [] item = blocks.split("<br />"); // Get each item
				for (int j = 0; j < item.length - 3; j++) {
					String s = item[j].split(">")[1].split("=")[1];
					String name = item[j].split(">")[2].split("</")[0];
					if (name.contains("&amp;"))
						name = name.replace("&amp;", "&");
					s = s.substring(1,s.length()-1);
					if (s.equals("#000000"))	
						curr.add(name + " " + "(Not Vegetarian)");
					else if (s.equals("#800040"))
						curr.add(name + " " + "(Vegan)");
					else
						curr.add(name + " " + "(Vegetarian)");

				}
			}
			else
			{
				curr.add("Nothing Today");
			}
			menu.add(curr);
			MenuItems.m = menu;
			MenuItems.color = color;
		}
		}  	
    	
}
   