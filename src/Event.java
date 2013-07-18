
public class Event {
	private String name;
	private String time;
	private String location;

	public Event()
	{
		
	}
	
	public Event(String name, String time, String location)
	{
		this.name = name;
		this.time = time;
		this.location = location;
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
	
	public String getName()
	{
		return this.name;
	}
	
	public String getLocation()
	{
		return this.location;
	}
}
