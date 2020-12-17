package weather;

import java.util.LinkedList;
import java.util.Random;

public class weatherCalculator {
	Random rng;

	public weatherCalculator(Random rng){
		this.rng = rng;
	}

	public int obd6(){
		int die = rng.nextInt(6)+1;

		if(die == 6){
			return obd6()+obd6();
		}

		return die;
	}

	
	public int daySeed(int year, int month, int day) {
		return year*100*100+month*100+day;
	}
	
	public weather getWeather(int year, int month, int day, NationData nation){
		int previous = nation.getTemperature(month-1);
		int average = nation.getTemperature(month);
		int next = nation.getTemperature(month+1);
		int averageWind = nation.getWind();
		
		double temperature = nonRandomtemperature(previous, average, next, day, daySeed(year, month, day));
		
		System.out.println(temperature);
		
		int wind = windStrengthNR(averageWind);
		String events = generateEvents(0,0,0, nation.getEvents(),month);
		
		return new weather(year, month, day,temperature,wind,rainfall(temperature, average, wind, 0),events);
	}
	

	
	public int windStrengthNR(int windBonus){
		int strength = obd6() + windBonus -2;

		strength +=  + bonusWind();
		
		if(strength < 0){
			strength = 0;
		}
		return strength;
	}
	
	/**
	 * recursive function
	 * @param previousDay
	 * @param windBonus
	 * @return
	 */
	public int windStrength(int previousDay, int windBonus){
		int die = obd6();
		int strength;

		if(die > previousDay + 4){
			strength = previousDay + 4 + windBonus;
		} 
		else if(die < previousDay-4){
			strength = previousDay -4 + windBonus;
		}
		else{
			strength = die + windBonus;
		}

		strength +=  + bonusWind();
		
		if(strength < 0){
			strength = 0;
		}
		return strength;
	}
	
	private int bonusWind(){
		int tmp = bonusWind;
		bonusWind = 0;
		return tmp;
	}

	public void setSeed(int n){
		rng.setSeed(n);
	}

	public int temperatureNR(int average, int shift){
		return average+rng.nextInt(5) - rng.nextInt(5) + shift;
	}

	public double nonRandomtemperature(double previous, double average, double next, int day, long daySeed) {		
		rng.setSeed(daySeed);
		double variance = 2*rng.nextDouble() - 2*rng.nextDouble();
		
		if(day < 15) {
			double previousStep = (average - previous) / 14;
			return (14+day)*previousStep+average + variance;
		}
		else {
			double nextStep = (next - average) / 14;
			return (day-14)*nextStep+average + variance;
		}
	}
	
//	public int temperature(int previous, int average, int next, int shift){
//
//		//add in the seed based on the date of the day, to force the generation
//		//of the same value every time.
//		
//			int die = rng.nextInt(5) - rng.nextInt(5);
//
//		if(previous < average){
//			die += 1;
//		}
//		else if(previous > average){
//			die -= 1;
//		}
//
//		return die + shift + previous + bonusTemp();
//	}
	
//	private int bonusTemp(){
//		int tmp = bonusTemp;
//		bonusTemp = 0;
//		return tmp;
//	}
	
	public int rainfall(double temperature, double average, int wind, int rain){
		int limit = 6;
		int bonus = 0;
		
		if(rain > 0){
			bonus += rain/2+1;
		}
		
		if(wind > 5){
			bonus += wind/2;
		}
		
		if(temperature < average){
			bonus++;
		}
		
		int die = obd6() + bonus +bonusRain();;
		
		if(die < limit){
			return 0;
		}
		return die-(limit-1);
	}
	
	private int bonusRain(){
		int tmp = bonusRain;
		bonusRain = 0;
		return tmp;
	}
	
	public boolean chance(int occurs, int days){
		if(rng.nextInt(days-1)+1 < occurs+1){
			return true;
		}
		return false;
	}
	
	int bonusWind = 0;
	int bonusRain = 0;
	int bonusTemp = 0;
	
	public String generateEvents(int magistorm, int thunder, int dimma, LinkedList<event> events, int month){
		String event = "";
		
		if(chance(1+magistorm,365)){
			event += "Magistorm ";
		}
		
		if(chance(1+thunder,28)){
			event += "Åska ";
		}
		
		if(chance(1+dimma,28*2)){
			event +="Lätt Dimma ";
			bonusWind = -4;
			bonusTemp = -2;
		}
		else if(chance(1+dimma,28*3)){
			event += "Tjock Dimma ";
			bonusWind = -6;
			bonusTemp = -2;
		}
		
		if((month>9 || month<3) && chance(1,200)){
			event += "Köldknäpp";
			bonusTemp = -5;
		}
		if((month>3 || month<9) && chance(1,200)){
			event +="Värmevåg";
			bonusTemp = 5;
		}
		if(chance(1,150)){
			event +="Molnklart ";
			bonusRain = -100;
		}
		if(chance(1,300)){
			event +="Stjärnfall ";
		}
		if(chance(1,150)){
			event +="Hagel ";
		}
		
		for(int i = 0; i<events.size();i++){
			/*lägg till så den kollar alla events*/
			event temp = events.get(i);
			if(chance(1+temp.getOccurs(),temp.getDays())){
				event += temp.getName()+" ";
			}
		}		
		return event;
	}
}