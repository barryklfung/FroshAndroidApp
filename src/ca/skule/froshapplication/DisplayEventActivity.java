package ca.skule.froshapplication;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DisplayEventActivity extends Activity {

	private Event thisEvent = new Event();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_event);
		
		Button button = (Button)findViewById(R.id.mapButton);
		Button localButton = (Button)findViewById(R.id.local_notify_button);
		
		
		Intent intent = getIntent();
		thisEvent = intent.getExtras().getParcelable("Event");
		
		TextView title = (TextView)(findViewById(R.id.eventTitle));
		title.setText(thisEvent.getName());
		TextView time = (TextView)(findViewById(R.id.eventTime));
		time.setText(thisEvent.getTime());
		TextView details = (TextView)(findViewById(R.id.eventDetails));
		details.setText(thisEvent.getDetails());
		
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick (View view){
				runIntent();
			}
		});
		
		localButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick (View view){
				runLocalNotiIntent();
			}
		});
		
	}
	private void runIntent (){
		Intent intent = new Intent (this, MapActivity.class);
		intent.putExtra("Location", thisEvent.getLocation());
		startActivity(intent);
	}
	
	private void runLocalNotiIntent (){	        
	        Intent notificationIntent = 
	            new Intent(DisplayEventActivity.this, NotificationService.class);
	        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
	        PendingIntent contentIntent = 
	            PendingIntent.getService(this, 0, notificationIntent, 0);
	        Calendar calendar = Calendar.getInstance();
	           calendar.set(Calendar.HOUR_OF_DAY, 16);
	       calendar.set(Calendar.MINUTE, 41);
	       calendar.set(Calendar.SECOND, 00);
	       
	       alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), contentIntent);  //set repeating every 24 hours
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_event, menu);
		return true;
	}

}
