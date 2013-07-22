package ca.skule.froshapplication;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import java.util.ArrayList;

import android.widget.AdapterView.OnItemClickListener;

public class DayListActivity extends Activity {

	private Day[] days;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_day_list);
		
		this.days = initDays();
		final ListView daysEntryListView = (ListView)findViewById(R.id.list);
		final DaysEntryAdapter daysEntryAdapter = new DaysEntryAdapter (this);
		
		daysEntryListView.setAdapter(daysEntryAdapter);
		
		for (int i =0; i<days.length; i++)
		{
			daysEntryAdapter.add(days[i]);
		}
		
		daysEntryListView.setOnItemClickListener (new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				//code for handling events here
				
				runIntent (days[position]);
				
				
			}
			
		});
	}
	
	public void runIntent(Day day)
	{		
		
		Intent intent = new Intent(this, EventListActivity.class);
		day = initDay(day);
		intent.putExtra ("Day", day);
		startActivity(intent);
				
	}
	
	//use this function to initialize the relevant day and all its events.
	public Day initDay(Day day){

		if (day.getDay().equalsIgnoreCase("Monday"))
		{
			day.addEvent(new Event("Arrival and Registration", "7:30AM-9:00AM", new Location ("FC"), "Your big day begins outside of Front Campus. Grab your F!rosh Kit and get to know your F!rosh Group!", Event.FROSH));
			day.addEvent(new Event("Matriculation", "9:00AM-11:00AM", new Location ("CH"), "This is your official introduction ot U of T Engineering. Find out what Skule has to offer for you and take your first oath toward engineeringdom!", Event.FROSH));
			day.addEvent(new Event("Campus Tours + Lunch + Dye", "11:00AM-2:00PM", new Location ("FC"), "Here we will give you a whirlwind tour of where you'll be spending your next the next four years of your life, from th ebest study spots to the coolest places to hang out. Participate in the time-honoured tradition of getting dyed purple and show your engineering pride. Did we mention there was lunch?", Event.FROSH));
			day.addEvent(new Event("F!rosh Picture", "2:00PM-3:00PM", new Location ("FC"), "Gather around with your fellow Skulemate, frosh and leedurs alike. Here's the twist: We'll be arranging you guys into a giant F!rosh Picture and taking your photo from above! What's the Design? You'll have to wait until you get your Skule Yearkbook at the end of the year (free with admission to F!rosh!)", Event.FROSH));
			day.addEvent(new Event("Downtown Walkaround", "3:00PM-5:30PM", new Location ("QP"), "The epic stats here. Imaging about 1100 purple F!rosh and leedurs crashing through the streets of downtown Toronto, screaming cheers and just generally being, well, epc! Thats going to be you. Get excited!", Event.FROSH));
			day.addEvent(new Event("Dinner", "5:30PM-6:30PM", new Location ("BA"), "Food! Yum. Need we say more?", Event.FROSH));
			day.addEvent(new Event("EEAT Tutorial", "6:30PM-7:30PM", new Location ("SF"), "The Engineering Entrance Aptitude Test (EEAT) will be administered on September 3 from 9-10:30AM. This tutorial will prepare you with everything you need to know about the test on Tuesday.", Event.FACULTY));
			day.addEvent(new Event("Spark Design Club Event", "7:30PM-Late", new Location (""), "The Spark Design Club presents their first project of the year! What will it be? Find out.", Event.FROSH));
			day.addEvent(new Event("Movie Night", "7:30PM-Late", new Location (""), "Not feeling design-y? Happening at the same time is a movie! Relax and enjoy with your newfound friends.", Event.FROSH));	
			
		}
		else if (day.getDay().equalsIgnoreCase("Tuesday"))
		{
			
		}
		else if (day.getDay().equalsIgnoreCase("Wednesday"))
		{
			
		}
		else if (day.getDay().equalsIgnoreCase("Thursday"))
		{
			
		}
		else if (day.getDay().equalsIgnoreCase("Friday"))
		{
			
		}
		else{
			
		}
		day.trimList();
		return day;
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.day_list, menu);
		getActionBar().setDisplayShowTitleEnabled(false);
		return true;
	}
	
	private Day[] initDays (){
		
		String[] dayNames = {"Monday", "Tuesday", "Wednesday", "Thursday", "F!riday"};
		String[] dateArray = {"September 2, 2013", "September 3, 2013", "September 4, 2013", "September 5, 2013", "September 6, 2013"};
		Day[] days = new Day[dayNames.length];
		for (int i = 0; i < dayNames.length;i++)
		{
			days [i] = new Day (dayNames[i], dateArray[i]);
		}
		return days;
	}
	
	public void onMenuClick (MenuItem Item){
		String id = Item.getTitle().toString();
		if (id.equalsIgnoreCase("Schedule")){
			//don't do anything cause you're already at schedule
		}
		else if (id.equalsIgnoreCase("Map")){
			Intent intent = new Intent (this, MapActivity.class);
			startActivity(intent);
		}
	}

}
