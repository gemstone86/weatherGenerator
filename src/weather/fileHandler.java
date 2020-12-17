package weather;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedList;

public class fileHandler {
	String path;

	BufferedFileReaderClass reader;
	BufferedWriter bufferedWriter;
	LinkedList<NationData> listOfNations = new LinkedList<NationData>();
	
	public fileHandler(String Path){
		this.path = Path;
		initializeDataFiles();
	}
	
	public NationData readFile(String Path){
		File readFile = new File(Path);
		int[] weather = new int[12];

		FileReader fr;
		try {
			fr = new FileReader(readFile.getAbsoluteFile());
			reader = new BufferedFileReaderClass(fr);

			String nationName = reader.readLine();
			for(int i = 0; i<12;i++){
				weather[i] = reader.readNextInt();
			}
			reader.skip(2);
			
			int shift = reader.readNextInt();
			int wind = reader.readNextInt();
			reader.skip(1);

			int numberOfEvents = reader.readNextInt();
			reader.skip(2);
			
			LinkedList<event> events = new LinkedList<event>();

			for(int i = 0; i<numberOfEvents;i++){
				String specialEvent = reader.readLine();
				int occurs = reader.readNextInt();
				int days = reader.readNextInt();
				reader.skip(2);

				events.add(new event(specialEvent, occurs, days));
			}

			return new NationData(nationName, weather, shift, wind, events);	
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

	/**
	 * reads all the nation data files and then returns a list of nation objects
	 */
	public void initializeDataFiles(){
		File folder = new File("data/");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i<listOfFiles.length;i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("    "+listOfFiles[i]);
				listOfNations.add(readFile("data/"+listOfFiles[i].getName()));
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
			File file = new File(path + " " +name + " ("+from+" to "+(to-1)+").txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter writer;

			writer = new FileWriter(file.getAbsoluteFile());
			bufferedWriter =  new BufferedWriter(writer);


		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Couldn't create weather file at "+path);
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
	public NationData getNation(String nation) {
		for(int i = 0; i<listOfNations.size();i++){
			if(listOfNations.get(i).getName().equals(nation)){
				return listOfNations.get(i);
			}
		}

		System.out.println("Couldn't find area");
		return null;
	}
}
