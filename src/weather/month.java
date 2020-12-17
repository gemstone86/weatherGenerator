package weather;

public enum month{
	Januari(1), Februari(2), Mars(3), April(4), Maj(5), Juni(6), Juli(7), Augusti(8), September(9), Oktober(10),November(11),December(12);
	
	private int month;
	
	month (int month){
		this.month=month;
	}
	
	public int getValue(){
		return month;
	}
	
	public String getMonth(){
		switch(month){
		case 1: return "Januari";
		case 2: return "Februari";
		case 3: return "Mars";
		case 4: return "April";
		case 5: return "Maj";
		case 6: return "Juni";
		case 7: return "Juli";
		case 8: return "Augusti";
		case 9: return "September";
		case 10: return "Oktober";
		case 11: return "November";
		case 12: return "December";
		default: return "error";
		}
	}
}