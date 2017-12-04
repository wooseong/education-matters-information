import java.io.*;
import java.util.*;

public class Admin{
	
	public Admin() {
	String fileName = "data.csv";
	File file = new File(fileName);
	try {
		Scanner inputStream = new Scanner(file);
		inputStream.next(); 
		while(inputStream.hasNext()) {
			String data = inputStream.next();
			String[] values = data.split("\"");
			System.out.println(data);
		}
		inputStream.close();
	}catch(FileNotFoundException e) {
		e.printStackTrace();
	}
	}
}
