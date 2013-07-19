package ca.skule.froshapplication;

import android.os.Parcelable;
import android.os.Parcel;

public class Location implements Parcelable {
	private int coordX=0;
	private int coordY=0;
	private String locationName ="uninitialized";
	
//Parcelable methods
	public int describeContents(){
		return 0;
	}
	
	public void writeToParcel (Parcel out, int flags){
		out.writeString(locationName);
		out.writeInt(coordX);
		out.writeInt(coordY);
	}
	public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>(){
		public Location createFromParcel (Parcel in){
			return new Location(in);
		}
		public Location[] newArray (int size){
			return new Location[size];
		}
	};
//private constructor for parcellable
	private Location (Parcel in){
		locationName = in.readString();
		coordX = in.readInt();
		coordY=  in.readInt();	
	}
	
//****************Start actual Class*******************
	public Location (){
		
	}
	
	public Location (int coordX, int coordY, String locationName){
		this.coordX = coordX;
		this.coordY = coordY;
		this.locationName = locationName;
	}
	
	public Location (String shortName)
	{
		if (shortName.equalsIgnoreCase("BA")){
			coordX = 325;
			coordY = 1040;
			locationName = "Bahen Centre for Information Technology";							
		}
		else if (shortName.equalsIgnoreCase("CH")){
			coordX = 564;
			coordY = 979;
			locationName = "Convocation Hall";							
		}
		else if (shortName.equalsIgnoreCase("EA")){
			coordX = 530;
			coordY = 1109;
			locationName = "Engineering Annex";							
		}
		else if (shortName.equalsIgnoreCase("GB")){
			coordX = 488;
			coordY = 1048;
			locationName = "Galbraith Building";							
		}
		else if (shortName.equalsIgnoreCase("FC")){
			coordX = 630;
			coordY = 887;
			locationName = "Front Campus";							
		}
		else if (shortName.equalsIgnoreCase("MB")){
			coordX = 694;
			coordY = 1160;
			locationName = "Lassonde Mining Building";							
		}
		else if (shortName.equalsIgnoreCase("MC")){
			coordX = 659;
			coordY = 1082;
			locationName = "Mechanical Engineering Building";							
		}
		else if (shortName.equalsIgnoreCase("QP")){
			coordX = 957;
			coordY = 635;
			locationName = "Queen's Park";							
		}
		else if (shortName.equalsIgnoreCase("SF")){
			coordX = 618;
			coordY = 1048;
			locationName = "Sandford Fleming Building";							
		}
		else if (shortName.equalsIgnoreCase("WB")){
			coordX = 530;
			coordY = 1163;
			locationName = "Wallberg Building";							
		}
		else{
			coordX=0;
			coordY=0;
			locationName = "Unknown Location";
		}

	}


public int getCoordX (){
	return coordX;
}

public int getCoordY(){
	return coordY;
}

public String getLocationName(){
	return locationName;
}
}