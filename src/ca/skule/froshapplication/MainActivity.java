package ca.skule.froshapplication;

import java.util.Calendar;
import java.util.Date;

import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
		
		Calendar c = Calendar.getInstance();
		System.out.println(c.get(Calendar.HOUR_OF_DAY));
		System.out.println(c.get(Calendar.DAY_OF_MONTH));
		
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
		else if (id.equalsIgnoreCase("locations")){
			// Point to Building List Activity
			Intent intent = new Intent (this,ListsActivity.class);
			startActivity(intent);
		}
	}
}

