package ca.skule.froshapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class DisplayEventActivity extends Activity {

	private Event thisEvent = new Event();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_event);
		
		Button button = (Button)findViewById(R.id.mapButton);
		
		
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
		
	}
	private void runIntent (){
		Intent intent = new Intent (this, MapActivity.class);
		intent.putExtra("Location", thisEvent.getLocation());
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_event, menu);
		return true;
	}

}
