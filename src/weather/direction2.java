package weather;

public enum direction2{
	N(1), NE(2), E(3), SE(4), S(5), SW(6), W(7), NW(8);
	
	private int direction;
	
	direction2 (int direction){
		this.direction=direction;
	}
	
	public int getValue(){
		return direction;
	}
	
	private direction2 getDirectionRoot(int i) {
		switch(i){
			case 1: return direction2.N;
			case 2: return direction2.NE;
			case 3: return direction2.E;
			case 4: return direction2.SE;
			case 5: return direction2.S;
			case 6: return direction2.SW;
			case 7: return direction2.W;
			case 8: return direction2.NW;
			default: return direction2.N;
		}
	}
	
	
	public direction2 getDirection(int i) {
		return getDirectionRoot(i);
	}
	
	public direction2 getDirection(){
		return getDirectionRoot(direction);
	}
}