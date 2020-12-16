import java.util.LinkedList;


public class NationData {

	private String nationName;
	private int[] temperature = new int[12];
	private int shift;
	private int wind;
	private String specialEvent;
	private int specialEventOccurs;
	private int specialEventDays;
	
	private LinkedList<event> listOfEvents = new LinkedList<event>();
	
	public NationData(String nationName, int[] temperature, int shift, int wind, LinkedList<event> events){
		this.nationName = nationName;
		this.temperature = temperature;
		this.shift = shift;
		this.wind = wind;
		listOfEvents = events;
	}
	
	public String getName(){
		return nationName;
	}
	public int getTemperature(int month){
		if(month == 0) {
			return temperature[11];
		}
		if(month == 13) {
			return temperature[1];
		}
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
	
}
