import java.util.List;


public class Day {

	private List events;
	private String name;
	
	public Day()
	{
		
	}
	
	public Day(String name)
	{
		this.name = name;
	}
	
	public void addEvent(Event toAdd)
	{
		this.events.add(toAdd);
	}
	
	public List getEvents()
	{
		return this.events;
	}
}
