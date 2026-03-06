import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Random;
import gui.*;
import weather.*;

public class main {

	LinkedList<String> list_of_files = new LinkedList<String>();

	String path = Paths.get("").toAbsolutePath().toString();
	
	boolean print = true;
	static String nation = "Colonan";
	Random rng;

	//static int start_day = 1;
	//static int start_month = 7;
	//static int start_year = 2967;
	static int start_day = 1;
	static int start_month = 7;
	static int start_year = 2977;
	
	static int until_day = 28;
	static int until_month = 12;
	static int until_year = 2961;
	
	
	int wind_bonus = 0;

	public void run(String[] args, String Path, String file, int startMonth){
		for(int i = 0; i<args.length; i++)
		{
			if(args[i] == "-path") {
				try {
					System.out.println("Path set to:" + args[i+1]);
					Path = args[i+1];
				}
				catch(Exception e) {
					System.out.println("Path expected");
					
				}
			}
		}
		
		rng = new Random(3118725);

		System.out.println("Step 0: Path is \"" + Path+"\"");
		
		weatherCalculator calculator = new weatherCalculator(rng);
		
		System.out.println("Step 1: Loading Data Files");
		fileHandler filehandler = new fileHandler(Path);
		
		System.out.println("Step 2: Opening Weather File");
		filehandler.createWeatherFile(nation, start_year, until_year);
		
		nationData Nation = filehandler.getNation(nation);
		
		System.out.println("Step 3: setting up data");
		double temp = Nation.getTemperature(start_month);
		int rain = calculator.obd6(), wind = calculator.obd6();

		if(print)System.out.println("Step 3b: Printing Data on screen");
		if(print)System.out.print(filehandler.printHeader());

		LinkedList<weather> list_of_weather = new LinkedList<weather>();
		
		/*write header to file*/
		filehandler.addToFile(filehandler.printHeader(), true);
		
		//boolean run = true;
		
		//int fromDate = startYear * 100*100 +(startMonth)*100+startDay-1;

		System.out.println("Step 4: Closing files");
		filehandler.closeWeatherFile();
	
		System.out.println("Step 5: Starting gui app");
		new GuiApp(filehandler, list_of_weather, start_year,start_month,start_day,nation);
	}



	public static void main(String[] args){
		if (args.length>0){
			nation = args[0];
		}
		
		main program = new main();

		program.run(args, program.getPath(), program.getNation(), start_month);
	}

	public String getNation(){
		return nation;
	}

	public String getPath(){
		return path;
	}
}
