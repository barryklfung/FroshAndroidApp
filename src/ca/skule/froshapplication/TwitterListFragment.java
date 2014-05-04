package ca.skule.froshapplication;

import java.net.URL;
import java.util.List;

import ca.skule.froshapplication.item.ItemContent;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;
import android.app.ListFragment;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TwitterListFragment extends ListFragment {
	
	// Progress dialog
	ProgressDialog pDialog;
	
	static String TWITTER_CONSUMER_KEY = "wth151gnTCF9L4cO66biTw";
	static String TWITTER_CONSUMER_SECRET = "XHV48QLE6KdtRQKLLfYWW5a5R81QsDzradq4TMJhyw";
	static String TWITTER_ACCESS_TOKEN_DEFAULT="576611664-C4afBfax3WwoBhrqf8vLo29mOdE1XNKWc8AFiAP5";
	static String TWITTER_ACCESS_TOKEN_DEFAULT_SECRET="DSn7PYkyWRy2Tk91pIxtzrzVxeYiDUIQGEd5aganUAM";
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.twitter_list_fragment,
				container, false);
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		loadData();
	}
	
	private void loadData(){
		AsyncTask <String, Void, List<twitter4j.Status>> asyncTask = new AsyncTask<String, Void, List<twitter4j.Status>>(){
			private List<twitter4j.Status> tweetsList; 

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				pDialog = new ProgressDialog(getActivity());
				pDialog.setMessage("Updating to twitter...");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(false);
				pDialog.show();
			}

			@Override
			protected List<twitter4j.Status> doInBackground(String... params) {
				
				ConfigurationBuilder builder = new ConfigurationBuilder();
				builder.setOAuthConsumerKey(TWITTER_CONSUMER_KEY);
				builder.setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET);

				AccessToken accessToken = new AccessToken(TWITTER_ACCESS_TOKEN_DEFAULT, TWITTER_ACCESS_TOKEN_DEFAULT_SECRET);
				Twitter twitter = new TwitterFactory(builder.build()).getInstance(accessToken);
				
		        try {
		            Query query = new Query("#FroshWeek");
		            QueryResult result;
		            result = twitter.search(query);
		            tweetsList= result.getTweets();
	                for (twitter4j.Status tweet : tweetsList) {
	                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
	                }
		        } catch (TwitterException te) {
		            te.printStackTrace();
		            System.out.println("Failed to search tweets: " + te.getMessage());
		            System.exit(-1);
		        }
				return tweetsList;
			}

			protected void onPostExecute(List<twitter4j.Status> qResult) {
				super.onPostExecute(qResult);
				//pDialog.dismiss();

				if (qResult.size() <= 0) //if query is unsuccessful
				{
					setListAdapter(null);
				}
				else//if query was successful
				{
					
					setListAdapter(new TweetAdapter(getActivity().getApplicationContext(),qResult));
				}
			}
		};
		asyncTask.execute();//executes async task
	}

}
