package com.caldining;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;
import android.widget.Toast;
public class MenuItems extends ExpandableListActivity {
	public static ArrayList m = new ArrayList();
	private ArrayList<String> parentItems = new ArrayList<String>();
    private ArrayList<Object> childItemsLunch = new ArrayList<Object>();
    private ArrayList<Object> childItemsBreakfast = new ArrayList<Object>();
    private ArrayList<Object> childItemsDinner = new ArrayList<Object>();
    private ArrayList<Object> childItems = new ArrayList<Object>();
    public static ArrayList color = new ArrayList();

    @Override
    public void onCreate(Bundle savedInstanceState) 
    {

        super.onCreate(savedInstanceState);
        // Create Expandable List and set it's properties
        ExpandableListView expandableList = getExpandableListView(); 
        expandableList.setDividerHeight(2);
        expandableList.setGroupIndicator(null);
        expandableList.setClickable(true);
        setGroupParents();
        // Set The Child Data
        try {
			try {
				setChildData(m);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Create the Adapter
        Intent intent= getIntent();
        String id = intent.getStringExtra("product");
        if (id.equals("Lunch"))
        	childItems = childItemsLunch;
        else if (id.equals("Breakfast"))
        	childItems = childItemsBreakfast;
        else if (id.equals("Dinner"))
        	childItems = childItemsDinner;
        
        ExpandableListAdapter adapter = new ExpandableListAdapter(parentItems, childItems);

        adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
        
        // Set the Adapter to expandableList
        expandableList.setAdapter(adapter);
        expandableList.setOnChildClickListener(this);
    }
     // method to add parent Items
        public void setGroupParents() 
        {
            parentItems.add("Crossroads");
            parentItems.add("Cafe 3");
            parentItems.add("Foothill");
            parentItems.add("Clark Kerr");
        }

        // method to set child data of each parent
        public void setChildData(ArrayList m) throws IOException, InterruptedException, ExecutionException 
        { 
        	
        	ArrayList[] lunch = new ArrayList[4];
        	lunch[0] = (ArrayList) m.get(4);
        	lunch[1] = (ArrayList) m.get(5);
        	lunch[2] = (ArrayList) m.get(6);
        	lunch[3] = (ArrayList) m.get(7);

        	ArrayList[] breakfast = new ArrayList[4];
        	breakfast[0] = (ArrayList) m.get(0);
        	breakfast[1] = (ArrayList) m.get(1);
        	breakfast[2] = (ArrayList) m.get(2);
        	breakfast[3] = (ArrayList) m.get(3);
        	
        	ArrayList[] dinner = new ArrayList[4];
        	dinner[0] = (ArrayList) m.get(8);
        	dinner[1] = (ArrayList) m.get(9);
        	dinner[2] = (ArrayList) m.get(10);
        	dinner[3] = (ArrayList) m.get(11);

        	for (int i = 0; i < 4; i++)
        	{
                ArrayList<String> child = new ArrayList<String>();
                for (int a = 0; a < ((ArrayList)lunch[i]).size(); a++)
                {
                	if ((String) ((ArrayList)lunch[i]).get(a) != null)
                	child.add((String) ((ArrayList)lunch[i]).get(a));
                }
                childItemsLunch.add(child);	
        	}
        	for (int i = 0; i < 4; i++)
        	{
                ArrayList<String> child = new ArrayList<String>();
                for (int a = 0; a < ((ArrayList)breakfast[i]).size(); a++)
                {
                	if ((String) ((ArrayList)breakfast[i]).get(a) != null)
                	child.add((String) ((ArrayList)breakfast[i]).get(a));
                }
                childItemsBreakfast.add(child);	
        	}
        	for (int i = 0; i < 4; i++)
        	{
                ArrayList<String> child = new ArrayList<String>();
                for (int a = 0; a < ((ArrayList)dinner[i]).size(); a++)
                {
                	if ((String) ((ArrayList)dinner[i]).get(a) != null)
                	child.add((String) ((ArrayList)dinner[i]).get(a));
                }
                childItemsDinner.add(child);	
        	}
           
        	 
           
        }
			
	    
        	    
}
 


    








