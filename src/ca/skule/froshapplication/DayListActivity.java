package ca.skule.froshapplication;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import java.util.ArrayList;

import android.widget.AdapterView.OnItemClickListener;

public class DayListActivity extends Activity {

	private Day[] days;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_day_list);
		
		this.days = initDays();
		final ListView daysEntryListView = (ListView)findViewById(R.id.list);
		final DaysEntryAdapter daysEntryAdapter = new DaysEntryAdapter (this);
		
		daysEntryListView.setAdapter(daysEntryAdapter);
		
		for (int i =0; i<days.length; i++)
		{
			daysEntryAdapter.add(days[i]);
		}
		
		daysEntryListView.setOnItemClickListener (new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				//code for handling events here
				
				runIntent (days[position]);
				
				
			}
			
		});
	}
	
	public void runIntent(Day day)
	{		
		
		Intent intent = new Intent(this, EventListActivity.class);
		day = initDay(day);
		intent.putExtra ("Day", day);
		startActivity(intent);
				
	}
	
	//use this function to initialize the relevant day and all its events.
	public Day initDay(Day day){

		if (day.getDay().equalsIgnoreCase("Monday"))
		{
			day.addEvent(new Event("Arrival and Registration", "7:30AM-9:00AM", new Location ("FC"), "Your big day begins outside of Front Campus. Grab your F!rosh Kit and get to know your F!rosh Group!", Event.FROSH));
			day.addEvent(new Event("Matriculation", "9:00AM-11:00AM", new Location ("CH"), "This is your official introduction ot U of T Engineering. Find out what Skule has to offer for you and take your first oath toward engineeringdom!", Event.FROSH));
			day.addEvent(new Event("Campus Tours + Lunch + Dye", "11:00AM-2:00PM", new Location ("FC"), "Here we will give you a whirlwind tour of where you'll be spending your next the next four years of your life, from th ebest study spots to the coolest places to hang out. Participate in the time-honoured tradition of getting dyed purple and show your engineering pride. Did we mention there was lunch?", Event.FROSH));
			day.addEvent(new Event("F!rosh Picture", "2:00PM-3:00PM", new Location ("FC"), "Gather around with your fellow Skulemate, frosh and leedurs alike. Here's the twist: We'll be arranging you guys into a giant F!rosh Picture and taking your photo from above! What's the Design? You'll have to wait until you get your Skule Yearkbook at the end of the year (free with admission to F!rosh!)", Event.FROSH));
			day.addEvent(new Event("Downtown Walkaround", "3:00PM-5:30PM", new Location ("QP"), "The epic stats here. Imaging about 1100 purple F!rosh and leedurs crashing through the streets of downtown Toronto, screaming cheers and just generally being, well, epic! Thats going to be you. Get excited!", Event.FROSH));
			day.addEvent(new Event("Dinner", "5:30PM-6:30PM", new Location ("BA"), "Food! Yum. Need we say more?", Event.FROSH));
			day.addEvent(new Event("EEAT Tutorial", "6:30PM-7:30PM", new Location ("SF"), "The Engineering Entrance Aptitude Test (EEAT) will be administered on September 3 from 9-10:30AM. This tutorial will prepare you with everything you need to know about the test on Tuesday.", Event.FACULTY));
			day.addEvent(new Event("Spark Design Club Event", "7:30PM-Late", new Location (""), "The Spark Design Club presents their first project of the year! What will it be? Find out.", Event.FROSH));
			day.addEvent(new Event("Movie Night", "7:30PM-Late", new Location (""), "Not feeling design-y? Happening at the same time is a movie! Relax and enjoy with your newfound friends.", Event.FROSH));	
			
		}
		else if (day.getDay().equalsIgnoreCase("Tuesday"))
		{
			day.addEvent(new Event("Engineering Entrance Aptitude Test", "9:00AM-10:30AM", new Location ("BA"), "Take the Engineering Entrance Aptitude Test. This is a real test, but don't worry; it will not affect your grades.", Event.FACULTY));
			day.addEvent(new Event("F!rosh Olympics + BBQ Lunch", "10:30AM-1:30PM", new Location ("FC"), "F!rosh Olympics is a series of fun physical challenges that will test your skills, endurance, and sheer willpower. Compete with other F!rosh Groups and get to know your own group a little bit better! Which group will triumph?", Event.FROSH));
			day.addEvent(new Event("Engineering Clubs Fair", "1:30PM-3:00PM", new Location ("BA"), "Skule&trade; truly has something to offer to everyone, including a wide assortment of undergraduate Engineering Clubs. Feeling high tech? Or are you on a mission to make the world better? At Skule&trade; there's a club for that.", Event.FROSH));
			day.addEvent(new Event("Charity Buskerfest", "3:00PM-5:00PM", new Location ("FC"), "Gather around with your fellow Skulemate, frosh and leedurs alike. Here's the twist: We'll be arranging you guys into a giant F!rosh Picture and taking your photo from above! What's the Design? You'll have to wait until you get your Skule Yearkbook at the end of the year (free with admission to F!rosh!)", Event.FROSH));
			day.addEvent(new Event("Toronto Argonauts football game", "5:00PM-10:00PM", new Location (""), "The Toronto Argonauts is Toronto's own football team (\"Football\" here refers to North American football). Winning their latest championship as recently as 2012, the Argos are set to face the Montreal Alouettes! Come along with your Skulemates, enjoy the game, and cheer on Toronto (or Montreal; we're cool with that too). Tickets are inicluded with F!rosh Week admission.", Event.FROSH));
		}
		else if (day.getDay().equalsIgnoreCase("Wednesday"))
		{
			day.addEvent(new Event("Fun with Faculty", "9:00AM-12:00PM", new Location ("BA"), "Get ready for classes on Thursday. Meet your professors. And learn if you're better at them at Guitar Hero! Wait, what? Fun with Faculty is an event for every first-year engineering student where you'll get to know your profs and the academic side of Engineering. Note that this event is free for all engineering students. You do not need to be signed up for F!rosh Week to attend. All first year engineering students are encouraged to attend!", Event.FACULTY));
			day.addEvent(new Event("Department Lunch", "12:00PM-2:00PM", new Location (""), "Get to know your own department. Enjoy free lunch. And get valuable advice for your university life here.", Event.FACULTY));
			day.addEvent(new Event("Choice: Faculty Presentation", "2:00PM-4:00PM", new Location (""), "Content to be determined.", Event.FACULTY));
			day.addEvent(new Event("Choice: World Record Ninja!", "2:00PM-4:00PM", new Location (""), "Feel like breaking a world record? We'd like to tell you about this event. We will gather as many people to play the game \"Ninja\"; more than the current Guinness World Record.", Event.FROSH));
			day.addEvent(new Event("Choice: Skule Nite 101", "2:00PM-4:00PM", new Location (""), "Skule Nite, the Engineering musical and sketch comedy revue, invites you to embark on a journey towards ultimate F!rosh victory! But beware, the quest is fraught with a variety of challenges to test the skills, courage, and creativity of those F!rosh brave enough to undertake it. In the end, a lone group shall emerge victorious, and to the victors go the spoils!", Event.FROSH));
			day.addEvent(new Event("Discipline Club Activities", "4:00PM-5:30PM", new Location (""), "Discipline Clubs represent your engineering program, plan social events for your program, and are an opportunity to get involved within Skule Life. Meet your Discipline Clubs, get some advice, and grab free dinner!", Event.EXTERNAL));
			day.addEvent(new Event("Suds!", "5:30PM-8:00PM", new Location ("SF"), "Yay! a break in your schedule! Why not go to Suds? It's a social event that takes place in the Sandford Fleming atrium. Chill and meet some upper-year engineers.", Event.EXTERNAL));
			day.addEvent(new Event("F!rosh Nite at Guvernment", "8:00PM-Late", new Location (""), "This all-ages club night is THE hottest event on campus during Orientation. Held at the Guvernment nightclub, this is <strong>free</strong> with F!rosh Week admission.", Event.FROSH));
		}
		else if (day.getDay().equalsIgnoreCase("Thursday"))
		{
			day.addEvent(new Event("Classes", "Start from 9:00AM", new Location ("BA"), "I can already hear you booing. But class is important! And you don't want to miss your first lectures", Event.FROSH));
			day.addEvent(new Event("Blue & Gold Bed Races", "3:00PM-5:00PM", new Location ("BA"), "Use whatever you have to build a vehicle that resembles something you can sleep on. Then race against the other colleges to defend the engineering honour!", Event.FROSH));
			day.addEvent(new Event("Suds!", "5:00PM-Late", new Location ("BA"), "", Event.FROSH));
		}
		else if (day.getDay().equalsIgnoreCase("Friday"))
		{
			day.addEvent(new Event("Classes", "Start from 9:00AM", new Location ("BA"), "", Event.FROSH));
			day.addEvent(new Event("Parade", "2:00PM-5:00PM", new Location ("BA"), "Take tot he streets one last time with the other colleges and faculties, along with our awesome Engineering parade float!", Event.FROSH));
			day.addEvent(new Event("Havenger Scunt", "7:00PM-Late", new Location ("BA"), "Nope, you didn't read that wrong. That is what we call our F!rosh Week scavenger hunt. But beware! This is not your average scavenger hunt. From eating cloves of raw garlic to getting the mayor of Toronto to officially endorse the Engineering Havenger Scunt, craziness of all sorts have made the list. You won't believe the things that happen at this event!", Event.FROSH));
		}
		else{
			
		}
		day.trimList();
		return day;
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.day_list, menu);
		getActionBar().setDisplayShowTitleEnabled(false);
		return true;
	}
	
	private Day[] initDays (){
		
		String[] dayNames = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
		String[] dateArray = {"September 2, 2013", "September 3, 2013", "September 4, 2013", "September 5, 2013", "September 6, 2013"};
		Day[] days = new Day[dayNames.length];
		for (int i = 0; i < dayNames.length;i++)
		{
			days [i] = new Day (dayNames[i], dateArray[i]);
		}
		return days;
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
	}

}
