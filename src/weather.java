

public class weather {

	private int Year;
	private int Month;
	private int Day;
	
	private int temperature;
	private int windStrength;
	private int rain;
	private String other;
	
	public weather(int Year, int Month, int Day, int temperature, int windStrength, int rain, String other){
		this.Year = Year;
		this.Month = Month;
		this.Day = Day;
		this.temperature = temperature;
		this.windStrength = windStrength;
		this.other = other;
		this.rain = rain;
	}

	public int getYear(){
		return Year;
	}
	public int getMonth(){
		return Month;
	}
	public int getDay(){
		return Day;
	}
	
	public int getTemperature(){
		return temperature;
	}
	public int getWindStrength(){
		return windStrength;
	}
	public String getOther(){
		return other;
	}
	public int getRain(){
		return rain;
	}
	
	public String getMessage(){
		return Year +"\t"+Month+"\t"+Day+"\t"+windStrength+"\t"+temperature+" C"+"\t\t"+rain+"\t\t"+other+"\n";
	}
	
}
