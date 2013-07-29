package ca.skule.froshapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.app.AlertDialog;

public class SettingsActivity extends Activity implements OnSharedPreferenceChangeListener {

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v (CommonUtilities.TAG, "Created Settings Activity");
		getFragmentManager().beginTransaction()
        .replace(android.R.id.content, new settingsFragment())
        .commit();
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (this);
		prefs.registerOnSharedPreferenceChangeListener(this);

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static class settingsFragment extends PreferenceFragment {
		@Override
		public void onCreate(Bundle savedInstances){
			super.onCreate(savedInstances);
			PreferenceManager.setDefaultValues(getActivity(), R.xml.preferences, false);
			addPreferencesFromResource(R.xml.preferences);
		}
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences prefs, String arg1) {
		AlertDialog dialog = new AlertDialog.Builder(this).create();
		dialog.setTitle("Muhahaha");
		dialog.setMessage("message");
		dialog.setButton(1, "asfasdf",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
		
		dialog.show();
				
	}

}
