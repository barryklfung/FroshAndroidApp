package ca.skule.froshapplication;

import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TwitterFragment extends Fragment{

	// Constants
	/**
	 * Register your here app https://dev.twitter.com/apps/new and get your
	 * consumer key and secret
	 * */
	static String TWITTER_CONSUMER_KEY = "wth151gnTCF9L4cO66biTw";
	static String TWITTER_CONSUMER_SECRET = "XHV48QLE6KdtRQKLLfYWW5a5R81QsDzradq4TMJhyw";

	// Preference Constants
	static String PREFERENCE_NAME = "twitter_oauth";
	static final String PREF_KEY_OAUTH_TOKEN = "oauth_token";
	static final String PREF_KEY_OAUTH_SECRET = "oauth_token_secret";
	static final String PREF_KEY_TWITTER_LOGIN = "isTwitterLogedIn";

	static final String TWITTER_CALLBACK_URL = "oauth://froshapp";

	// Twitter oauth urls
	static final String URL_TWITTER_AUTH = "auth_url";
	static final String URL_TWITTER_OAUTH_VERIFIER = "oauth_verifier";
	static final String URL_TWITTER_OAUTH_TOKEN = "oauth_token";

	// Login button
	Button btnLoginTwitter;
	// Update status button
	Button btnUpdateStatus;
	// Logout button
	Button btnLogoutTwitter;
	// EditText for update
	EditText txtUpdate;
	// lbl update
	TextView lblUpdate;
	ListView recentTweets;

	// Progress dialog
	ProgressDialog pDialog;

	// Twitter
	private static Twitter twitter;
	private static RequestToken requestToken;
	private static String verifier;

	// Shared Preferences
	private static SharedPreferences mSharedPreferences;

	// Internet Connection detector
	private ConnectionDetector cd;
	
	private List<twitter4j.Status> tweetsList; 
	
	private Menu mMenu;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.twitter_fragment, container, false);
		
	}

	@Override
	public void onActivityCreated (Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// All UI elements
		btnLoginTwitter = (Button) getActivity().findViewById(R.id.btnLoginTwitter);
		txtUpdate = (EditText) getActivity().findViewById(R.id.txtUpdateStatus);
		btnUpdateStatus = (Button) getActivity().findViewById(R.id.btnUpdateStatus);
		btnLogoutTwitter = (Button) getActivity().findViewById(R.id.btnLogoutTwitter);
		lblUpdate = (TextView) getActivity().findViewById(R.id.lblUpdate);
		recentTweets = (ListView) getActivity().findViewById(R.id.Tweets);

		// Shared Preferences
		mSharedPreferences = getActivity().getApplicationContext().getSharedPreferences(
				"MyPref", 0);

		/**
		 * Twitter login button click event will call loginToTwitter() function
		 * */
		btnLoginTwitter.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				loginToTwitter();
			}
		});

		/**
		 * Button click event to Update Status, will call updateTwitterStatus()
		 * function
		 * */
		btnUpdateStatus.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Call update status function
				// Get the status from EditText
				String status = txtUpdate.getText().toString();

				// Check for blank text
				if (status.trim().length() > 0) {
					// update status
					if (!status.contains("#Fweek")&&!status.contains("#fweek"))
					{
						status.concat(" #Fweek");
					}
					
					new updateTwitterStatus().execute(status);
				} else {
					// EditText is empty
					Toast.makeText(getActivity().getApplicationContext(),
							"Please enter status message", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});

		/**
		 * Button click event for logout from twitter
		 * */
		btnLogoutTwitter.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Call logout twitter function
				logoutFromTwitter();
			}
		});

		/** This if conditions is tested once is
		 * redirected from twitter page. Parse the uri to get oAuth
		 * Verifier
		 * */
		if (!isTwitterLoggedInAlready()) {
			Uri uri = getActivity().getIntent().getData();
			if (uri != null && uri.toString().startsWith(TWITTER_CALLBACK_URL)) {
				// oAuth verifier
				verifier = uri
						.getQueryParameter(URL_TWITTER_OAUTH_VERIFIER);

				Thread th = new Thread(new Runnable() {
					public void run() {
						// Get the access token
						AccessToken accessToken = null;
						try {
							accessToken = twitter.getOAuthAccessToken(
									requestToken, verifier);
						} catch (TwitterException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						// Shared Preferences
						Editor e = mSharedPreferences.edit();

						// After getting access token, access token secret
						// store them in application preferences
						e.putString(PREF_KEY_OAUTH_TOKEN, accessToken.getToken());
						e.putString(PREF_KEY_OAUTH_SECRET,
								accessToken.getTokenSecret());
						// Store login status - true
						e.putBoolean(PREF_KEY_TWITTER_LOGIN, true);
						e.commit(); // save changes

						Log.e("Twitter OAuth Token", "> " + accessToken.getToken());
						Log.e("Twitter Secret Token", "> " + accessToken.getTokenSecret());
					}
				});

				th.start();

				// Hide login button
				btnLoginTwitter.setVisibility(View.GONE);

				// Show Update Twitter
				lblUpdate.setVisibility(View.VISIBLE);
				txtUpdate.setVisibility(View.VISIBLE);
				btnUpdateStatus.setVisibility(View.VISIBLE);
				btnLogoutTwitter.setVisibility(View.VISIBLE);
				recentTweets.setVisibility(View.VISIBLE);
				loadData();
				
			}
		}

	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		mMenu=menu;
		inflater.inflate(R.menu.twitter, menu);
		
		//if (mMenu != null && isTwitterLoggedInAlready())
		if (mMenu != null)
		{
			Log.d("mMenu","mMenu is not null");
			mMenu.findItem(R.id.twitterLogout).setVisible(true);
		}
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		   // handle item selection
		   switch (item.getItemId()) {
		      case R.id.twitterLogout:
		         logoutFromTwitter();
		         return true;
		      default:
		         return super.onOptionsItemSelected(item);
		   }
		}


	/**
	 * Function to login twitter
	 * */
	private void loginToTwitter() {
		
		cd = new ConnectionDetector(getActivity().getApplicationContext());

		// Check if Internet present
		if (!cd.isConnectingToInternet()) {
			// Internet Connection is not present
			// stop executing code by return
			Toast.makeText(getActivity().getApplicationContext(),
					"Please connect to the internet to access Twitter", Toast.LENGTH_LONG).show();
			return;
		}
		
		// Check if already logged in
		if (!isTwitterLoggedInAlready()) {
			//ConfigurationBuilder builder = new ConfigurationBuilder();
			///builder.setOAuthConsumerKey(TWITTER_CONSUMER_KEY);
			//builder.setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET);
			//Configuration configuration = builder.build();

			//TwitterFactory factory = new TwitterFactory(configuration);
			TwitterFactory factory = new TwitterFactory();
			twitter = factory.getInstance();
			twitter.setOAuthConsumer(TWITTER_CONSUMER_KEY, TWITTER_CONSUMER_SECRET);

			if(!(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)) {
				try {
					requestToken = twitter.getOAuthRequestToken(TWITTER_CALLBACK_URL);
					this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(requestToken.getAuthenticationURL())));
				} catch (TwitterException e) {
					e.printStackTrace();
				}
			}
			else
			{
				new Thread(new Runnable() {
					public void run() {
						try {   
							requestToken = twitter.getOAuthRequestToken(TWITTER_CALLBACK_URL);
							TwitterFragment.this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(requestToken.getAuthenticationURL())));
							System.out.println(requestToken.getAuthenticationURL());
						} catch (TwitterException e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		} else {
			// user already logged into twitter
			Toast.makeText(getActivity().getApplicationContext(),
					"Already Logged into twitter", Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * Function to update status
	 * */
	class updateTwitterStatus extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Updating to twitter...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting Places JSON
		 * */
		protected String doInBackground(String... args) {
			Log.d("Tweet Text", "> " + args[0]);
			String status = args[0];
			try {
				ConfigurationBuilder builder = new ConfigurationBuilder();
				builder.setOAuthConsumerKey(TWITTER_CONSUMER_KEY);
				builder.setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET);

				// Access Token 
				String access_token = mSharedPreferences.getString(PREF_KEY_OAUTH_TOKEN, "");
				// Access Token Secret
				String access_token_secret = mSharedPreferences.getString(PREF_KEY_OAUTH_SECRET, "");

				AccessToken accessToken = new AccessToken(access_token, access_token_secret);
				Twitter twitter1 = new TwitterFactory(builder.build()).getInstance(accessToken);

				// Update status
				twitter4j.Status response = twitter1.updateStatus(status);

				Log.d("Status", "> " + response.getText());
			} catch (TwitterException e) {
				// Error in updating status
				Log.d("Twitter Update Error", e.getMessage());
			}
			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog and show
		 * the data in UI Always use runOnUiThread(new Runnable()) to update UI
		 * from background thread, otherwise you will get error
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			Toast.makeText(getActivity().getApplicationContext(),
					"Status tweeted successfully", Toast.LENGTH_SHORT)
					.show();

			txtUpdate.setText("");
		}

	}
	
	private void loadData(){
		AsyncTask <String, Void, List<twitter4j.Status>> asyncTask = new AsyncTask<String, Void, List<twitter4j.Status>>(){


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

				// Access Token 
				String access_token = mSharedPreferences.getString(PREF_KEY_OAUTH_TOKEN, "");
				// Access Token Secret
				String access_token_secret = mSharedPreferences.getString(PREF_KEY_OAUTH_SECRET, "");

				AccessToken accessToken = new AccessToken(access_token, access_token_secret);
				Twitter twitter = new TwitterFactory(builder.build()).getInstance(accessToken);
				
		        try {
		            Query query = new Query("#FroshWeek");
		            QueryResult result;
		            result = twitter.search(query);
		            tweetsList= result.getTweets();
		        } catch (TwitterException te) {
		            te.printStackTrace();
		            System.out.println("Failed to search tweets: " + te.getMessage());
		            System.exit(-1);
		        }
				return tweetsList;
			}

			protected void onPostExecute(List<twitter4j.Status> qResult) {
				super.onPostExecute(qResult);
				pDialog.dismiss();

				if (qResult.size() <= 0) //if query is unsuccessful
				{
					recentTweets.setAdapter(null);
				}
				else//if query was successful
				{
					
					recentTweets.setAdapter(new TweetAdapter(getActivity().getApplicationContext(),qResult));
				}
			}
		};
		asyncTask.execute();//executes async task
	}

	/**
	 * Function to logout from twitter
	 * It will just clear the application shared preferences
	 * */
	public void logoutFromTwitter() {
		// Clear the shared preferences
		Editor e = mSharedPreferences.edit();
		e.remove(PREF_KEY_OAUTH_TOKEN);
		e.remove(PREF_KEY_OAUTH_SECRET);
		e.remove(PREF_KEY_TWITTER_LOGIN);
		e.commit();

		// After this take the appropriate action
		// I am showing the hiding/showing buttons again
		// You might not needed this code
		btnLogoutTwitter.setVisibility(View.GONE);
		btnUpdateStatus.setVisibility(View.GONE);
		txtUpdate.setVisibility(View.GONE);
		lblUpdate.setVisibility(View.GONE);
		recentTweets.setVisibility(View.GONE);

		btnLoginTwitter.setVisibility(View.VISIBLE);
		
		Toast.makeText(getActivity().getApplicationContext(),
				"Logged out of Twitter", Toast.LENGTH_SHORT)
				.show();
	}


	/**
	 * Check user already logged in your application using twitter Login flag is
	 * fetched from Shared Preferences
	 * */
	private boolean isTwitterLoggedInAlready() {
		// return twitter login status from Shared Preferences
		return mSharedPreferences.getBoolean(PREF_KEY_TWITTER_LOGIN, false);
	}

}
