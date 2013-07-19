package ca.skule.froshapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class DisplayEventActivity extends Activity {

	private Event thisEvent = new Event("Test event", "6:00pm - 7:00am", "Sanford Fleming (SF)", "Fun stuff yo");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_event);
		
		TextView title = (TextView)(findViewById(R.id.eventTitle));
		title.setText(thisEvent.getName());
		TextView time = (TextView)(findViewById(R.id.eventTime));
		time.setText(thisEvent.getTime());
		TextView details = (TextView)(findViewById(R.id.eventDetails));
		details.setText(thisEvent.getDetails());
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_event, menu);
		return true;
	}

}
