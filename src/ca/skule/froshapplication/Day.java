package ca.skule.froshapplication;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable;
import android.os.Parcel;





public class Day implements Parcelable{

	private ArrayList<Event> events = new ArrayList (40);
	private String name;
	private String date;
	
	public int describeContents (){
		return 0;
	}
	
	public void writeToParcel (Parcel out, int flags){
		out.writeString(name);
		out.writeString(date);
		out.writeTypedList(events);
	}
	public static final Parcelable.Creator<Day> CREATOR = new Parcelable.Creator<Day>(){
		public Day createFromParcel (Parcel in){
			return new Day(in);
		}
		public Day[] newArray (int size){
			return new Day[size];
		}
	};
	//private constructor for parcelable
	private Day (Parcel in){
		name=in.readString();
		date=in.readString();
		in.readTypedList(events,Event.CREATOR);
			
	}
	
//******************Start Actual Class**********************
	public Day(String dayName, String date)
	{
		events=new ArrayList<Event> ();
		this.name = dayName;
		this.date = date;
	}
	
	public void setDay(String dayName)
	{
		name = dayName;
	}
	
	public String getDay()
	{
		return name;
	}

	public void addEvent(Event toAdd)
	{
		this.events.add(toAdd);
	}
	
	public void setEvents(ArrayList<Event> list){
		this.events = list;
	}
	
	public ArrayList<Event> getEvents()
	{
		return this.events;
	}
	
	public String getDate ()
	{
		return date;
	}
	
	public void trimList ()
	{
		events.trimToSize();
	}
}
