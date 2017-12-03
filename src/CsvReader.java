import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;

public class CsvReader {
	private CSVReader reader;
	private File file;
	
	public void setFile(File file) {
		this.file = file;
		try {
			FileReader fileReader;
			fileReader = new FileReader(this.file);
			fileReader.ready();
			this.reader = new CSVReader(fileReader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public CsvReader() {
		this(null);
	}
	public CsvReader(File file) {
		this.setFile(file);
	}
	
	public String[][] parse() throws IOException {
		if(file == null) return null;
		List<String[]> str = this.reader.readAll();
		String[][] ret = new String[str.size()][];
		
		for(int i=0;i<str.size(); i++) {
			ret[i] = str.get(i);
		}
		
		return ret;
	}
}
