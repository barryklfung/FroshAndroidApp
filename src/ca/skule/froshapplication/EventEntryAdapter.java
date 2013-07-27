package ca.skule.froshapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.text.format.Time;


public class EventEntryAdapter extends ArrayAdapter<Event> {
	
	private Context context;
	
	public EventEntryAdapter (Context context){
		super (context, 0);
		this.context=context;
		
	}
	
	public View getView (final int position, final View convertView, final ViewGroup parent){
		LayoutInflater inflater = (LayoutInflater)context.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
		
		View eventView = inflater.inflate(R.layout.event_list_item,parent, false);
		TextView title = (TextView)eventView.findViewById(R.id.eventTitle);
		TextView time = (TextView)eventView.findViewById(R.id.eventTime);
		TextView location = (TextView)eventView.findViewById(R.id.eventLocation);
		
		Event currentItem = getItem(position);
		title.setText(currentItem.getName());
		time.setText(currentItem.getTime() + "     |");
		if (currentItem.getLocation().getLocationName().equals("Unknown Location")){
			location.setText("");
			}
		else
			location.setText(currentItem.getLocation().getLocationName());
		return eventView;
	}
}
