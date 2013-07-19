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

import java.util.GregorianCalendar;
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
				
				//(Toast.makeText(getApplicationContext(), "CLicked Item #" + position, Toast.LENGTH_LONG)).show();
				runIntent();
				
			}
			
		});
	}
	
	public void runIntent()
	{
		Intent intent = new Intent(this, DisplayEventActivity.class);
		startActivity(intent);
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
		GregorianCalendar[] dateArray = {new GregorianCalendar(2013, 9, 2), new GregorianCalendar(2013, 9, 3), new GregorianCalendar(2013, 9, 4), new GregorianCalendar(2013, 9, 5), new GregorianCalendar(2013, 9, 6)};
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
