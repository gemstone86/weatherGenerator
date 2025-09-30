package weather;

public enum direction{
	N(1), NE(2), E(3), SE(4), S(5), SW(6), W(7), NW(8);
	
	private int direction;
	
	direction (int direction){
		this.direction=direction;
	}
	
	public int getValue(){
		return direction;
	}
	
	public String getDirection(){
		switch(direction){
		case 1: return "N";
		case 2: return "NE";
		case 3: return "E";
		case 4: return "SE";
		case 5: return "S";
		case 6: return "SW";
		case 7: return "W";
		case 8: return "NW";
		default: return "error";
		}
	}
}