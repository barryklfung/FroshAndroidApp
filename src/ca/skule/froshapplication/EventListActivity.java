package ca.skule.froshapplication;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.content.Intent;
import java.util.ArrayList;

public class EventListActivity extends Activity {
	ArrayList<Event> eventList;
	Day thisDay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_list);
		
		Intent intent = getIntent();
		thisDay = intent.getExtras().getParcelable("Day");
		eventList = thisDay.getEvents();
		
		final ListView eventsEntryListView = (ListView)findViewById(R.id.event_list);
		final EventEntryAdapter eventEntryAdapter = new EventEntryAdapter (this);
		
		eventsEntryListView.setAdapter(eventEntryAdapter);
			
		
		int length = eventList.size();
		for (int i = 0; i<length;i++)
		{
			eventEntryAdapter.add(eventList.get(i));
		}
		eventsEntryListView.setOnItemClickListener (new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				//code for handling events here
		
				runIntent (eventList.get(position));
				
				
			}
			
		});
	}
	
	private void runIntent(Event event){
		Intent intent = new Intent (this, DisplayEventActivity.class);
		intent.putExtra("Event", event);
		startActivity(intent);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_list, menu);
		getActionBar().setDisplayShowTitleEnabled(false);		
		return true;
	}
	public void onMenuClick (MenuItem Item){
		String id = Item.getTitle().toString();
		if (id.equalsIgnoreCase("Schedule")){
			Intent intent = new Intent (this, ScheduleSwipeActivity.class);
			startActivity (intent);
		}
		else if (id.equalsIgnoreCase("Map")){
			Intent intent = new Intent (this, MapActivity.class);
			startActivity(intent);
		}
	}
}
