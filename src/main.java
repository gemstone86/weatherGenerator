import java.util.LinkedList;
import java.util.Random;

public class main {

	LinkedList<String> listOfFiles = new LinkedList<String>();


	public void run(String Path, String file, int startMonth){
		rng = new Random(3118725);

		weatherCalculator calculator = new weatherCalculator(rng);

		System.out.println("Step 1: Loading Data Files");
		fileHandler filehandler = new fileHandler(Path);
		
		System.out.println("Step 2: Opening Weather File");
		filehandler.openWeatherFile(nation, startYear, untilYear);
		
		NationData Nation = filehandler.getNation(nation);
		
		System.out.println("Step 3: setting up data");
		int temp = Nation.getTemperature(startMonth), rain = calculator.obd6(), wind = calculator.obd6();

		if(print)System.out.println("Step 3b: Printing Data on screen");
		if(print)System.out.print(filehandler.printHeader());

		LinkedList<weather> listOfWeather = new LinkedList<weather>();
		
		/*write header to file*/
		filehandler.addToFile(filehandler.printHeader(), true);
		
		boolean run = true;
	
		int fromDate = startYear * 100*100 +(startMonth)*100+startDay-1;
		
		yearLoop:
		for(int currentYear = 0; currentYear<untilYear;currentYear++){
			calculator.setSeed(startYear);
			for(int currentMonth = 1; currentMonth<13;currentMonth++){
				for(int currentDay = 1; currentDay<29;currentDay++){
					int currentDateSum = currentYear*100*100+currentMonth*100+currentDay;
					int average = Nation.getTemperature(currentMonth);
					
					String events = calculator.generateEvents(0,0,0, Nation.getEvents(),currentMonth);
					
//					wind = calculator.windMod(calculator.windStrength(wind, windBonus), events);
//					temp = calculator.tempMod(calculator.temperature(temp, average, 0), events);
//					rain = calculator.rainMod(rain = calculator.rainfall(temp, average, wind, rain),events);	
					
					wind = calculator.windStrength(wind, windBonus);
					temp = calculator.temperature(temp, average, 0);
					rain = calculator.rainfall(temp, average, wind, rain);	
					
					if(print && currentDateSum > fromDate)	
						System.out.print(filehandler.printData(currentYear, currentMonth, currentDay, wind, temp, rain, events ));
					
					/*write data to file*/
					if(currentYear>startYear-1){
						filehandler.addToFile(filehandler.printData(currentYear, currentMonth, currentDay, wind, temp, rain, events), true);
						listOfWeather.add(new weather(currentYear,currentMonth,currentDay,temp,wind,rain,events));	
					
					//12*28
					}
					if(currentYear >= untilYear-1 && currentMonth == untilMonth && currentDay == untilDay){
						break yearLoop;
					}
				}
			}
		}
		System.out.println("Step 4: Closing files");
		filehandler.closeWeatherFile();
	
		new GuiApp(filehandler, listOfWeather, startYear,startMonth,startDay,nation);
	}

	boolean print = true;
	static String nation = "Ordensstaten";
	Random rng;

	static int startDay = 1;
	static int startMonth = 1;
	static int startYear = 2960;
	
	static int untilDay = 28;
	static int untilMonth = 12;
	static int untilYear = 2966;
	
	
	int windBonus = 0;

	String Path = "weather";

	public static void main(String[] args){

		if (args.length>0){
			nation = args[0];
		}
		
		main program = new main();

		program.run(program.getPath(), program.getNation(), startMonth);
	}

	public String getNation(){
		return nation;
	}

	public String getPath(){
		return Path;
	}
}
