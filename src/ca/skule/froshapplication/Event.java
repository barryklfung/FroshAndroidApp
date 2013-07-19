package ca.skule.froshapplication;

public class Event {
	private String name;
	private String time;
	private String location;
	private String details;
	
	public Event()
	{
		
	}
	
	public Event(String name, String time, String location, String details)
	{
		this.name = name;
		this.time = time;
		this.details = details;
		this.location = location;
	}
	
	public void setDetails(String newDets)
	{
		this.details = newDets;
	}
	
	public void setName (String newName)
	{
		this.name = newName;
	}
	
	public void setLocation(String newLoc)
	{
		this.location = newLoc;
	}
	
	public void setTime(String newTime)
	{
		this.time = newTime;
	}
	
	public String getTime()
	{
		return this.time;
	}
	
	public String getDetails()
	{
		return this.details;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getLocation()
	{
		return this.location;
	}
}
