package ca.skule.froshapplication;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MapActivity extends Activity {

	private GoogleMap mMap = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		// Do a null check to confirm that we have not already instantiated the map.
	    if (mMap == null) {
	        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
	                            .getMap();
	        // Check if we were successful in obtaining the map.
	        if (mMap != null) {
	            // The Map is verified. It is now safe to manipulate the map.

	        }
	    }
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
		if (id.equalsIgnoreCase("schedule")){
			// Point to Schedule
		}
		else if (id.equalsIgnoreCase("map")){
			// Point to Map
		}
	}
}
