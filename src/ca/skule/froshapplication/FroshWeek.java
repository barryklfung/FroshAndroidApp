package ca.skule.froshapplication;

import java.util.List;


public class FroshWeek {
	private List days;
	
	public FroshWeek(){
		
	}
	
	public void addDay(Day toAdd)
	{
		this.days.add(toAdd);
	}
	
	public List getDays()
	{
		return this.days;
	}

}
