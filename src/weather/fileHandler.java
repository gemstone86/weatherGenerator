package weather;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedList;

public class fileHandler {
	String basePath;

	BufferedFileReaderClass reader;
	BufferedWriter bufferedWriter;
	LinkedList<nationData> listOfNations = new LinkedList<nationData>();
	
	public fileHandler(String Path){
		this.basePath = Path;
		System.out.println("Here: " + basePath);
		initializeDataFiles();
		
	}
	
	public nationData readFile(String Path){
//		System.out.println("Here: " + Path);
		File readFile = new File(basePath+"/src/data/"+Path);
		int[] temperature = new int[12];
		int[] rainfall = new int[12];
		int[] windStrength = new int[12];

		FileReader fr;
		try {
			fr = new FileReader(readFile.getAbsoluteFile());
			reader = new BufferedFileReaderClass(fr);
			
			
			//Fetch Nation Name
			String nationName = reader.readLine();
			
			//Fetch average temperature per month
			System.out.println("Temperatur: ");
			for(int i = 0; i<12;i++){
				temperature[i] = reader.readNextInt();
				System.out.print(temperature[i]+";");
			}
			System.out.println();

			//fetch precipation value
			for(int i = 0; i<12;i++) {
				rainfall[i] = reader.readNextInt();
			}
			
			//Fetch shift values
			int shift = reader.readNextInt();
			
			//Fetch wind value
			System.out.println("Wind: ");
			for(int i = 0; i<12;i++){
				windStrength[i] = reader.readNextInt();
				System.out.print(windStrength[i]+";");
			}
			//int wind = reader.readNextInt();
			
			
			System.out.println("Shift: " +shift);

			LinkedList<event> events = new LinkedList<event>();
			reader.skip(2);
			while(true){
				String specialEvent = reader.readLine();
				if(specialEvent == null) {
					break;
				}
				else{
					//System.out.println("\tEvent Name: " + specialEvent);
					int occurs = reader.readNextInt();
					//System.out.println("\tOccurs: " + occurs);
					int days = reader.readNextInt();
					//System.out.println("\tDays: " + days);
					reader.skip(2);

					events.add(new event(specialEvent, occurs, days));
				}
			}

			return new nationData(nationName, temperature, rainfall, shift, windStrength, events);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Couldn't read Nation file");
			e.printStackTrace();
		}
		return null;
	}


	public void addToFile(String add, boolean linebreak){
		try {
			bufferedWriter.write(add);
			if(linebreak)bufferedWriter.write(System.lineSeparator());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String[] getListOfNations(){
		String[] list = new String[listOfNations.size()];

		for(int i = 0; i<listOfNations.size();i++){
			list[i] = listOfNations.get(i).getName();
		}

		return list;
	}

	public int[] getTemperatures(String Name){
		for(int i = 0; i<listOfNations.size();i++){
			if(listOfNations.get(i).getName() == Name){
				return listOfNations.get(i).getTemperature();
			}
		}
		return null;
	}
	
	public int[] getWindStrength(String Name) {
		for(int i = 0; i<listOfNations.size();i++) {
			if(listOfNations.get(i).getName() == Name) {
				return listOfNations.get(i).getWindStrength();
			}
		}
		return null;
	}

	/**
	 * reads all the nation data files and then returns a list of nation objects
	 */
	public void initializeDataFiles(){
//		System.out.println(basePath + "src/data");
		String Folderpath = basePath + "/src/data";
		File folder = new File(Folderpath);

		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i<listOfFiles.length;i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("    "+listOfFiles[i]);
				listOfNations.add(readFile(listOfFiles[i].getName()));
//				System.out.println(readFile(listOfFiles[i].getName()));
			}
		}
	}

	/**
	 * create weatherfile. Path is extended by nation and from year to year
	 * @param name
	 * @param from
	 * @param to
	 */
	public void createWeatherFile(String name, int from, int to){
		try {
			File file = new File(basePath + " " +name + " ("+from+" to "+(to-1)+").txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter writer;

			writer = new FileWriter(file.getAbsoluteFile());
			bufferedWriter =  new BufferedWriter(writer);


		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Couldn't create weather file at "+basePath);
			e.printStackTrace();
		}
	}

	public void closeWeatherFile(){
		try {
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String printHeader(){
		return "Year\tMonth\tDay\tWind\tTemperature\tRainfall\tOther\n";
	}

	/**
	 * print, to console, the information
	 * @param Year
	 * @param Month
	 * @param Day
	 * @param Wind
	 * @param temp
	 * @param Rainfall
	 * @param Other
	 * @return
	 */
	public String printData(int Year, int Month, int Day, int Wind, double temp, int Rainfall, String Other){
		DecimalFormat df = new DecimalFormat("##.#");
		return Year +"\t"+Month+"\t"+Day+"\t"+Wind+"\t"+df.format(temp)+" C"+"\t\t"+Rainfall+"\t\t"+Other+"\n";
	}

	/**
	 * return the temperature list as a String
	 * @param Name
	 * @return
	 */
	public String[] getTemperaturesAsString(String Name) {
		String[] temp = new String[12];
		for(int i = 0; i<listOfNations.size();i++){
			if(listOfNations.get(i).getName() == Name){
				for(int j = 0; j<12;j++){
					temp[j] = Integer.toString(listOfNations.get(i).getTemperature(j));
				}
				return temp;
			}
		}
		return null;
	}

	/**
	 * Read through the list of Nations and return the correct nation.
	 * @param nation
	 * @return
	 */
	public nationData getNation(String nation) {
		for(int i = 0; i<listOfNations.size();i++){
			if(listOfNations.get(i).getName().equals(nation)){
				return listOfNations.get(i);
			}
		}

		System.out.println("Couldn't find area");
		return null;
	}
}
