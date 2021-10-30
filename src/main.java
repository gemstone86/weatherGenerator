import java.util.LinkedList;
import java.util.Random;
import gui.*;
import weather.*;

public class main {

	LinkedList<String> listOfFiles = new LinkedList<String>();

	public void run(String[] args, String Path, String file, int startMonth){
		for(int i = 0; i<args.length; i++)
		{
			if(args[i] == "-path") {
				try {
					Path = args[i+1];
				}
				catch(Exception e) {
					System.out.println("Path expected");
				}
			}
		}
		
		rng = new Random(3118725);

		weatherCalculator calculator = new weatherCalculator(rng);

		System.out.println("Step 1: Loading Data Files");
		fileHandler filehandler = new fileHandler(Path);
		
		System.out.println("Step 2: Opening Weather File");
		filehandler.createWeatherFile(nation, startYear, untilYear);
		
		nationData Nation = filehandler.getNation(nation);
		
		System.out.println("Step 3: setting up data");
		double temp = Nation.getTemperature(startMonth);
		int rain = calculator.obd6(), wind = calculator.obd6();

		if(print)System.out.println("Step 3b: Printing Data on screen");
		if(print)System.out.print(filehandler.printHeader());

		LinkedList<weather> listOfWeather = new LinkedList<weather>();
		
		/*write header to file*/
		filehandler.addToFile(filehandler.printHeader(), true);
		
		boolean run = true;
	
		int fromDate = startYear * 100*100 +(startMonth)*100+startDay-1;
//		
//		yearLoop:
//		for(int currentYear = 0; currentYear<untilYear;currentYear++){
//			calculator.setSeed(startYear);
//			for(int currentMonth = 1; currentMonth<13;currentMonth++){
//				
//				double previous = Nation.getTemperature(currentMonth-1); 
//				double average = Nation.getTemperature(currentMonth);
//				double next = Nation.getTemperature(currentMonth+1);
//				
//				for(int currentDay = 1; currentDay<29;currentDay++){
//					int currentDateSum = calculator.daySeed(currentYear, currentMonth, currentDay);
//					
//					String events = calculator.generateEvents(0,0,0, Nation.getEvents(),currentMonth);
//										
//					wind = calculator.windStrength(wind, windBonus);
//					temp = calculator.nonRandomtemperature(previous, average, next, currentDay, currentDateSum);
//					rain = calculator.rainfall(temp, average, wind, rain);	
//					
//					if(print && currentDateSum > fromDate)	
//						System.out.print(filehandler.printData(currentYear, currentMonth, currentDay, wind, temp, rain, events ));
//					
//					/*write data to file*/
//					if(currentYear>startYear-1){
//						filehandler.addToFile(filehandler.printData(currentYear, currentMonth, currentDay, wind, temp, rain, events), true);
//						listOfWeather.add(new weather(currentYear,currentMonth,currentDay,temp,wind,rain,events));	
//					}
//					if(currentYear >= untilYear-1 && currentMonth == untilMonth && currentDay == untilDay){
//						break yearLoop;
//					}
//				}
//			}
//		}
		System.out.println("Step 4: Closing files");
		filehandler.closeWeatherFile();
	
		System.out.println("Step 5: Starting gui app");
		new GuiApp(filehandler, listOfWeather, startYear,startMonth,startDay,nation);
	}

	boolean print = true;
	static String nation = "Colonan";
	Random rng;

	static int startDay = 1;
	static int startMonth = 1;
	static int startYear = 2960;
	
	static int untilDay = 28;
	static int untilMonth = 12;
	static int untilYear = 2961;
	
	
	int windBonus = 0;

	String Path = "GeneratedData\\weather";

	public static void main(String[] args){

		

			if (args.length>0){
			nation = args[0];
		}
		
		main program = new main();

		program.run(args, program.getPath(), program.getNation(), startMonth);
	}

	public String getNation(){
		return nation;
	}

	public String getPath(){
		return Path;
	}
}
