import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;

/**
 * csv 파일 파싱 클래스
 * @author bang
 *
 */
public class CsvReader {
	private CSVReader reader;
	private File file;
	
	/**
	 * 파싱할 파일 설정
	 * @param file	target to parsing
	 */
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
	
	/**
	 * setFile 메소드 호출 필요
	 */
	public CsvReader() {
		this(null);
	}
	
	/**
	 * csv 파일 파서
	 * @param file	target to parsing
	 */
	public CsvReader(File file) {
		this.setFile(file);
	}
	
	/**
	 * 파일 파싱 메소드
	 * @return String[][] data from csv file 
	 * @throws IOException
	 */
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
