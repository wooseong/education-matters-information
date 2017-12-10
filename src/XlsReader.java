import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class XlsReader {
    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private File file;

    public File getFile() {
        return file;
    }
    public void setFile(File file) throws FileNotFoundException, IOException {
        this.file = file;
        this.workbook = new HSSFWorkbook(new FileInputStream(file));
        this.sheet = this.workbook.getSheetAt(0);
    }

    public XlsReader() {
        this(null);
    }
    public XlsReader(File file) {
        try {
            this.setFile(file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String[][] parse(){
    	if(this.file == null) return null;
    	
        String[][] str = new String[this.sheet.getPhysicalNumberOfRows()][];

        for(int i=0; i<this.sheet.getPhysicalNumberOfRows(); i++) {
            HSSFRow row = sheet.getRow(i);
            if(row != null) {
                str[i] = new String[row.getPhysicalNumberOfCells()];
                for(int j=0; j<row.getPhysicalNumberOfCells(); j++) {
                    HSSFCell cell = row.getCell(j);
                    if(cell == null) {
                        continue;
                    } else {
                        switch (cell.getCellType()){
                        case HSSFCell.CELL_TYPE_FORMULA:
                            str[i][j]=cell.getCellFormula();
                            break;
                        case HSSFCell.CELL_TYPE_NUMERIC:
                        	str[i][j]=cell.getNumericCellValue()+"";
                            break;
                        case HSSFCell.CELL_TYPE_STRING:
                        	str[i][j]=cell.getStringCellValue()+"";
                            break;
                        case HSSFCell.CELL_TYPE_BLANK:
                        	str[i][j]=cell.getBooleanCellValue()+"";
                            break;
                        case HSSFCell.CELL_TYPE_ERROR:
                        	str[i][j]=cell.getErrorCellValue()+"";
                            break;
                        } // switch
                    } // if.else..
                } // for
            }// if
        } //for

        return str;
    }
}