
package weather;

public class event {

	private String name;
	private int occurs;
	private int days;
	
	public event(String name, int occurs, int days){
		this.name = name;
		this.occurs = occurs;
		this.days = days;
	}
	
	public int getOccurs(){
		return occurs;
	}
	public int getDays(){
		return days;
	}
	
	public String getName(){
		return name;
	}
}
