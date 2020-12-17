package weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import gui.*;


public class BufferedFileReaderClass extends BufferedReader {

	public BufferedFileReaderClass(Reader arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public int readNextInt(){
		int separator = 9;
		int value = 0;
		int positive = 1;
		
		while(true){
			try {
				int temp = read();
			
				if(temp == separator){
					break;
				}
				if(temp == 45){
					positive = -1;
				}
				else if(temp < 58 && temp > 47){
//					System.out.println(temp);
					value = temp-48 + value*10;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return positive*value;
	}
	
}
