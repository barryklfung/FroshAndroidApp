package ca.skule.froshapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.GregorianCalendar;





public class Day {

	private List<Event> events;
	private String name;
	private GregorianCalendar date;
	
	public Day(String dayName, GregorianCalendar date)
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
	
	public List<Event> getEvents()
	{
		return this.events;
	}
	
	public GregorianCalendar getDate ()
	{
		return date;
	}
	
}
