
package weather;

import java.text.DecimalFormat;

public class weather {

	private int Year;
	private int Month;
	private int Day;
	
	private double temperature;
	private int windStrength;
	private int rain;
	private String other;
	private direction direction;
	
	public weather(int Year, int Month, int Day, double temp, int windStrength, int rain, String other, direction direction){
		this.Year = Year;
		this.Month = Month;
		this.Day = Day;
		this.temperature = temp;
		this.windStrength = windStrength;
		this.other = other;
		this.rain = rain;
		this.direction = direction;
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
	
	public double getTemperature(){
		return temperature;
	}
	public int getWindStrength(){
		return windStrength;
	}
	public String getOther(){
		System.out.println(other);
		return other;
	}
	public int getRain(){
		return rain;
	}
	public direction getDirection(){
		return direction;
	}
	
	public String getMessage(){
		return Year +"\t"+Month+"\t"+Day+"\t"+windStrength+"\t"+temperature+" C"+"\t\t"+rain+"\t\t"+other+"\n";
	}
	
}
