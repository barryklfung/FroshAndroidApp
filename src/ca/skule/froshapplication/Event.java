package ca.skule.froshapplication;


import android.os.Parcelable;
import android.os.Parcel;



public class Event implements Parcelable {
	private String time;
	private String name;
	private Location location;
	private String details;
	public static final int FROSH = 0;
	public static final int FACULTY = 1;
	public static final int EXTERNAL = 2;
	private int type;
	
	//functions for Parcellable
	public int describeContents(){
		return 0;
	}
	
	public void writeToParcel (Parcel out, int flags){
		out.writeString(details);
		out.writeString(time);
		out.writeString(name);
		out.writeInt(location.getCoordX());
		out.writeInt(location.getCoordY());
		out.writeString(location.getLocationName());
	}
	public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>(){
		public Event createFromParcel (Parcel in){
			return new Event(in);
		}
		public Event[] newArray (int size){
			return new Event[size];
		}
	};
	
	//private constructor for parcelable
	private Event (Parcel in){
		details = in.readString();
		time = in.readString();
		name = in.readString();
		int coordX = in.readInt();
		int coordY = in.readInt();
		String locationName = in.readString();
		this.location = new Location (coordX, coordY, locationName);	
	}
	
	
//****************Start actual Class*******************
	public Event(String name, String time, Location location, String details, int type)
	{
		this.name = name;
		this.time = time;
		this.details = details;
		this.location = location;
		this.type=type;
	}
	public Event()
	{
		
	}
	
//setter methods	
	public void setLocation (Location location)
	{
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
	
	public void setTime(String newTime)
	{
		this.time = newTime;
	}
	public void setType (int type)
	{
		this.type = type;
	}
	
//getter methods	

	public Location getLocation ()
	{
		return this.location;
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
	public int getType()
	{
		return this.type;
	}
	
}
