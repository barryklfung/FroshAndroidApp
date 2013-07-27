package ca.skule.froshapplication;

import ca.skule.froshapplication.R;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class ScheduleSwipeActivity extends FragmentActivity {
	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule_swipe);
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.schedule_swipe, menu);
		getActionBar().setDisplayShowTitleEnabled(false);
		return true;
	}
	
	public void onMenuClick (MenuItem Item){
		String id = Item.getTitle().toString();
		if (id.equalsIgnoreCase("Schedule")){
			//don't do anything cause you're already at schedule
		}
		else if (id.equalsIgnoreCase("Map")){
			Intent intent = new Intent (this, MapActivity.class);
			startActivity(intent);
		}
		else if (id.equalsIgnoreCase("F! Tips")){
			Intent intent = new Intent (this, FTipListActivity.class);
			startActivity(intent);
		}
	}
	
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DayFragment (defined as a static inner class
			// below).
			Fragment fragment = new DayFragment();
			Bundle args = new Bundle();
			args.putInt(DayFragment.DayFragmentListAdapter.ARG_SECTION_NUMBER, position);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 5 total pages.
			return 5;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			/* Locale l = Locale.getDefault(); I don't know why this is here*/
			switch (position) {
			case 0:
				return " MON ";
			case 1:
				return " TUES ";
			case 2:
				return " WED ";
			case 3:
				return "THURS";
			case 4:
				return " FRI ";
			}
			return null;
		}
	}
		
	public static class DayFragment extends Fragment {
		 
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        View v = inflater.inflate(R.layout.day_fragment_item, null);
	        ExpandableListView elv = (ExpandableListView) v.findViewById(R.id.list);
	        elv.setAdapter(new DayFragmentListAdapter());
	        return v;
	    }
	 
	    public class DayFragmentListAdapter extends BaseExpandableListAdapter {
	    	public static final String ARG_SECTION_NUMBER = "day_of_the_week"; /* dunno why it's called this*/
	    	
	    	int dotw = getArguments().getInt(ARG_SECTION_NUMBER);
	        private String[] events_and_times = getEventsAndTimes(dotw);
	        private String[][] locations_and_descriptions = getLocationsAndDescriptions(dotw);
	 
	        @Override
	        public int getGroupCount() {
	            return events_and_times.length;
	        }
	 
	        @Override
	        public int getChildrenCount(int i) {
	            return locations_and_descriptions[i].length;
	        }
	 
	        @Override
	        public Object getGroup(int i) {
	            return events_and_times[i];
	        }
	 
	        @Override
	        public Object getChild(int i, int i1) {
	            return locations_and_descriptions[i][i1];
	        }
	 
	        @Override
	        public long getGroupId(int i) {
	            return i;
	        }
	 
	        @Override
	        public long getChildId(int i, int i1) {
	            return i1;
	        }
	 
	        @Override
	        public boolean hasStableIds() {
	            return true;
	        }
	 
	        @Override
	        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
	            TextView textView = new TextView(DayFragment.this.getActivity());
	            String[] text_to_add = getGroup(i).toString().split("\n");
	            textView.setText(Html.fromHtml("<b>" + text_to_add[0] + "</b> <br />" + "<small>" + text_to_add[1] + "</small>"));
	            textView.setTextSize(16);
	            textView.setPadding(5, 5, 5, 5);
	            textView.setLineSpacing(5, 1);
	            return textView;
	        }
	 
	        @Override
	        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
	            TextView textView = new TextView(DayFragment.this.getActivity());
	            textView.setText(Html.fromHtml(getChild(i, i1).toString()));
	            textView.setLineSpacing(3, 1);
	            textView.setPadding(0, 3, 0, 3);
	            return textView;
	        }
	 
	        @Override
	        public boolean isChildSelectable(int i, int i1) {
	            return true;
	        }
	        
	        private String[] getEventsAndTimes(int dotw) {
	        	switch (dotw) {
				case 0:
					String[] monday_list = {"Arrival and Registration\n7:30AM-9:00AM", "Matriculation\n9:00AM-11:00AM", "Campus Tours + Lunch + Dye\n11:00AM-2:00PM", "F!rosh Picture\n2:00PM-3:00PM", "Downtown Walkaround\n3:00PM-5:30PM", "Dinner\n5:30PM-6:30PM", "EEAT Tutorial\n6:30PM-7:30PM", "Spark Design Club Event\n7:30PM-Late", "Movie Night\n7:30PM-Late"};
					return monday_list;
				case 1:
					String[] tuesday_list = {"Engineering Entrance Aptitude Test\n9:00AM-10:30AM", "F!rosh Olympics + BBQ Lunch\n10:30AM-1:30PM", "Engineering Clubs Fair\n1:30PM-3:00PM", "Charity Buskerfest\n3:00PM-5:00PM", "Toronto Argonauts Football Game\n5:00PM-10:00PM"};
					return tuesday_list;
				case 2:
					String[] wednesday_list = {"Fun with Faculty\n9:00AM-12:00PM", "Department Lunch\n12:00PM-2:00PM", "Choice: Faculty Presentation\n2:00PM-4:00PM", "Choice: World Record Ninja!\n2:00PM-4:00PM", "Choice: Skule Nite 101\n2:00PM-4:00PM", "Discipline Club Activities\n4:00PM-5:30PM", "Suds!\n5:30PM-8:00PM", "F!rosh Nite at Guvernment\n8:00PM-Late"};
					return wednesday_list;
				case 3:
					String[] thursday_list = {"Classes\nStart from 9:00AM", "Blue & Gold Bed Races\n3:00PM-5:00PM", "Suds!\n5:00PM-Late"};
					return thursday_list;
				case 4:
					String[] friday_list = {"Classes\nStart from 9:00AM", "Parade\n2:00PM-5:00PM", "Havenger Scunt\n7:00PM-Late"};
					return friday_list;
				}
				return null;
	        	
	        }
	        
	        private String[][] getLocationsAndDescriptions(int dotw) {
	        	switch (dotw) {
	        	case 0:
					String[][] monday_list = {
							{"\t\t Your big day begins outside of Front Campus. Grab your F!rosh Kit and get to know your F!rosh Group!"},
							{"\t\t This is your official introduction to UofT Engineering. Find out what Skule has to offer for you and take your first oath toward engineeringdom!"},
							{"\t\t Here we will give you a whirlwind tour of where you'll be spending the next four years of your life, from the best study spots to the coolest places to hang out. Participate in the time-honoured tradition of getting dyed purple and show your engineering pride. Did we mention there was lunch?"},
							{"\t\t Gather around with your fellow Skulemates, F!rosh and leedurs alike. Here's the twist: we'll be arranging you guys into a giant F!rosh Picture and taking your photo from above! What's the design? You'll have to wait until you get your Skule Yearbook at the end of the year (free with admission to F!rosh!)"},
							{"\t\t The epic starts here. Imagine about 1100 purple F!rosh and leedurs crashing through the streets of downtown Toronto, screaming cheers and just generally being, well, epic! Thats going to be you. Get excited!"},
							{"\t\t Food! Yum. Need we say more?"},
							{"\t\t The Engineering Entrance Aptitude Test (EEAT) will be administered on September 3 from 9:00-10:30AM. This tutorial will prepare you with everything you need to know about the test on Tuesday."},
							{"\t\t Choice: The Spark Design Club presents their first project of the year! What will it be? Find out."},
							{"\t\t Choice: Not feeling design-y? Happening at the same time is a movie! Relax and enjoy with your newfound friends."}
					};
					return monday_list;
				case 1:
					String[][] tuesday_list = {
							{"\t\t Take the Engineering Entrance Aptitude Test. This is a real test, but don't worry; it will not affect your grades."},
							{"\t\t F!rosh Olympics is a series of fun physical challenges that will test your skills, endurance, and sheer willpower. Compete with other F!rosh Groups and get to know your own group a little bit better! Which group will triumph?"},
							{"\t\t Skule truly has something to offer to everyone, including a wide assortment of undergraduate Engineering Clubs. Feeling high tech? Or are you on a mission to make the world better? At Skule, there's a club for that."},
							{"\t\t Gather around with your fellow Skulemates, F!rosh and leedurs alike. Here's the twist: we'll be arranging you guys into a giant F!rosh Picture and taking your photo from above! What's the design? You'll have to wait until you get your Skule Yearkbook at the end of the year (free with admission to F!rosh)."},
							{"\t\t The Toronto Argonauts is Toronto's own football team (\"Football\" here refers to North American football). Winning their latest championship as recently as 2012, the Argos are set to face the Montreal Alouettes! Come along with your Skulemates, enjoy the game, and cheer on Toronto (or Montreal; we're cool with that too). Tickets are included with F!rosh Week admission."}
					};
					return tuesday_list;
				case 2:
					String[][] wednesday_list = {
							{"\t\t Get ready for classes on Thursday. Meet your professors. And learn if you're better at them at Guitar Hero! Wait, what? Fun with Faculty is an event for every first-year engineering student where you'll get to know your profs and the academic side of Engineering. Note that this event is free for all engineering students. You do not need to be signed up for F!rosh Week to attend. All first year engineering students are encouraged to attend!"},
							{"\t\t Get to know your own department. Enjoy free lunch. And get valuable advice for your university life here."},
							{"\t\t Content to be determined."},
							{"\t\t Feel like breaking a world record? We'd like to tell you about this event. We will gather as many people to play the game \"Ninja\"; more than the current Guinness World Record."},
							{"\t\t Skule Nite, the Engineering musical and sketch comedy revue, invites you to embark on a journey towards ultimate F!rosh victory! But beware, the quest is fraught with a variety of challenges to test the skills, courage, and creativity of those F!rosh brave enough to undertake it. In the end, a lone group shall emerge victorious, and to the victors go the spoils!"},
							{"\t\t Discipline Clubs represent your engineering program, plan social events for your program, and are an opportunity to get involved within Skule Life. Meet your Discipline Clubs, get some advice, and grab free dinner!"},
							{"\t\t Yay! A break in your schedule! Why not go to Suds? It's a social event that takes place in the Sandford Fleming atrium. Chill and meet some upper-year engineers."},
							{"\t\t This all-ages club night is the hottest event on campus during Orientation. Held at the Guvernment nightclub, this is free with F!rosh Week admission."},
							};
					return wednesday_list;
				case 3:
					String[][] thursday_list = {
							{"\t\t I can already hear you booing. But class is important! And you don't want to miss your first day of lectures."},
							{"\t\t Use whatever you have to build a vehicle that resembles something you can sleep on. Then race against the other colleges to defend the engineering honour!"},
							{"\t\t Yay! a break in your schedule! Why not go to Suds? It's a social event that takes place in the Sandford Fleming atrium. Chill and meet some upper-year engineers."}
							};
					return thursday_list;
				case 4:
					String[][] friday_list = {
							{"\t\t I can hear you booing again. But class is important! And you don't want to miss your second day of lectures."},
							{"\t\t Take to the streets one last time with the other colleges and faculties, along with our awesome Engineering parade float!"},
							{"\t\t Nope, you didn't read that wrong. That is what we call our F!rosh Week scavenger hunt. But beware! This is not your average scavenger hunt. From eating cloves of raw garlic to getting the mayor of Toronto to officially endorse the Engineering Havenger Scunt, craziness of all sorts have made the list. You won't believe the things that happen at this event!"}
							};
					return friday_list;
				}
				return null;
	        }
	    } 
	}
}