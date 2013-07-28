package ca.skule.froshapplication;

import java.io.File;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MapActivity extends Activity {

	Location locationMarker=null;
	private GoogleMap mMap = null;
	static final LatLng DEFAULT = new LatLng(43.6631, -79.3954);
	public boolean onlineAccess;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		Bundle extras = getIntent().getExtras(); 
		onlineAccess=isNetworkAvailable();
		String locationName = null;

		if (extras!=null)
		{
			locationName = extras.getString("Location");
			locationMarker=new Location(locationName);
		}

		if (mMap == null) {
			mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
					.getMap();
			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				if (locationMarker!=null)
				{
					if (!locationMarker.getLocationName().equals("Unknown Location")&&!locationMarker.getLocationName().equals("uninitialized"))
					{

						LatLng markerLocation = locationMarker.getLatLng();
						Marker marker = mMap.addMarker(new MarkerOptions()
						.position(markerLocation)
						.title(locationMarker.getLocationName())
						.snippet("aka. " + locationMarker.getShortName()));
						mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerLocation, 16));

					}
				}
				else{
					mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT, 16));
				}
			}
			else
			{
				onlineAccess=false;
			}
		}
		
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		onlineAccess = sharedPref.getBoolean(SettingsActivity.KEY_PREF_ONLINE, false);

		if (!onlineAccess)
		{
			Intent intent = new Intent (this,MapActivity2.class);
			if (locationName != null && !locationName.isEmpty())
			{
				intent.putExtra("Location", locationName);
			}
			startActivity(intent);
		}
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		getActionBar().setDisplayShowTitleEnabled(false);

		return true;
	}

	public void onMenuClick (MenuItem Item){
		String id = Item.getTitle().toString();
		if (id.equalsIgnoreCase("schedule")){
			Intent intent = new Intent (this, ScheduleSwipeActivity.class);
			startActivity (intent);
		}
		else if (id.equalsIgnoreCase("map")){
			// Point to Map
		}
		else if (id.equalsIgnoreCase("locations")){
			// Point to Building List Activity
			Intent intent = new Intent (this,ListsActivity.class);
			startActivity(intent);
		}
	}

	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
}

