package com.caldining;


import java.io.IOException;
import java.util.*;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
public class CalDiningNoAsync {
	
	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("http://services.housing.berkeley.edu/FoodPro/dining/static/todaysentrees.asp").get();
		Elements scraped = doc.select("table table td");
		Object[] tables = scraped.toArray();
		ArrayList menu = new ArrayList(); // The menu array that will contain all menus for all places at all meals
		int MenuIn = 0;
		int k = 0;
		for (int i = 15; i <= 26; i++) {
			ArrayList<String> curr = new ArrayList<String>(); // The current menu for a place at a time (e.g. Crossroads Lunch)
			ArrayList<String> color = new ArrayList<String>(); // The current color for a place at a time (e.g. Crossroads Lunch)
			String blocks = (String) tables[i].toString().split("<hr />")[1]; // All items for a specific meal (e.g. all dinner items)
			if (!blocks.contains("Closed")) { // Only do the following if it's not closed
				String [] item = blocks.split("<br />"); // Get each item
				for (int j = 0; j < item.length - 3; j++) {
					String s = item[j].split(">")[1].split("=")[1];
					String name = item[j].split(">")[2].split("</")[0];
					if (name.contains("&amp;"))
					{
						System.out.println("I am here flaksdjfdfasssddddddddddddddddddddddddddddd");
						name = name.replace("&amp;", "&");
					}
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
				curr.add("");
		
		menu.add(curr);
		MenuIn++;
		}
		for(int i=0;i<menu.size();i++)
		{
			ArrayList temp = (ArrayList) menu.get(i);
			for (int j = 0; j < temp.size(); j++)
			{
				System.out.println(temp.get(j));
			}
			
		}
}
}