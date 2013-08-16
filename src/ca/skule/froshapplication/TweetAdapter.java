package ca.skule.froshapplication;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import twitter4j.Status;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TweetAdapter extends BaseAdapter {

	private List<Status> socialFeed;
	private Context context;

	//new constructor for adapter
	public TweetAdapter(final Context context, List<Status> socialFeed)
	{
		super();
		this.socialFeed = socialFeed;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		//gets current view

		//fills up view if nothing is there
		if (row == null)
		{
			final LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = vi.inflate (R.layout.tweet, null);
		}

		Status tweet = (Status) getItem(position);//finds appropriate tweet

		//binds username with appropriate text view
		final TextView profileName = (TextView) row.findViewById(R.id.User);
		profileName.setText(tweet.getUser().getName());

		//binds tweet with appropriate text view
		final TextView tweetMessage = (TextView)row.findViewById(R.id.listDescription);
		tweetMessage.setText(tweet.getText());

		//binds date with appropriate text view
		final TextView date = (TextView)row.findViewById(R.id.Date);
		date.setText(tweet.getCreatedAt().toString());

		return row;
	}

	@Override
	public int getCount() {

		return socialFeed.size();//get the total size
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return socialFeed.get(position);//gets the item at a given index
	}

	@Override
	public long getItemId(int position) {
		return position;//returns the position
	}

}
