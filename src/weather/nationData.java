package weather;

import java.util.LinkedList;

public class nationData {

	private String nationName;
	private int[] temperature = new int[12];
	private int[] rainfall = new int[12];
	private int shift;
	private int wind;
	private String specialEvent;
	private int specialEventOccurs;
	private int specialEventDays;
	
	private LinkedList<event> listOfEvents = new LinkedList<event>();
	
	public nationData(String nationName, int[] temperature, int[] rainfall, int shift, int wind, LinkedList<event> events){
		this.nationName = nationName;
		this.temperature = temperature;
		this.shift = shift;
		this.wind = wind;
		listOfEvents = events;
		this.rainfall=rainfall;
	}
	
	public String getName(){
		return nationName;
	}
	public int getTemperature(int month){
		if(month == 0) {
			//System.out.println(temperature[11]);
			return temperature[11];
		}
		if(month == 13) {
			//System.out.println(temperature[0]);
			return temperature[0];
		}
		//System.out.println(temperature[month-1]);
		return temperature[month-1];
	}
	
	public int getShift(){
		return shift;
	}
	public int getWind(){
		return wind;
	}
	public String getEvent(){
		return specialEvent;
	}
	public int getOccurs(){
		return specialEventOccurs;
	}
	public int getDays(){
		return specialEventDays;
	}

	public int[] getTemperature() {
		// TODO Auto-generated method stub
		return temperature;
	}
	
	public LinkedList<event> getEvents(){
		return listOfEvents;
	}

	int getRain(int month) {
		// TODO Auto-generated method stub
		if(month == 0) {
			return rainfall[11];
		};
		if(month == 12) {
			return rainfall[0];
		}
		else {
			return rainfall[month];
		}
	}
}
