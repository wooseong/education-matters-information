import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * xlsx 파일 파싱을 위한 클래스
 * @author bang
 *
 */
public class XlsxReader {
    private File file;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
        try {
            this.workbook = new XSSFWorkbook(new FileInputStream(file));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.sheet = this.workbook.getSheetAt(0);
    }

    /**
     * need setFile(File)
     */
    public XlsxReader(){
        this(null);
    }
    /**
     * xlsx file parser
     * @param file xlsx file to parsing
     */
    public XlsxReader(File file){
        this.setFile(file);
    }

    /**
     * 실제 파싱 수행하는 메소드
     * @return String[][] 파싱된 데이터
     */
    public String[][] parse(){
        if(file == null) return null;

        String[][] str = new String[this.sheet.getPhysicalNumberOfRows()][];

        for(int i=0; i<this.sheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheet.getRow(i);
            if(row != null) {
                str[i] = new String[row.getPhysicalNumberOfCells()];
                for(int j=0; j<row.getPhysicalNumberOfCells(); j++) {
                    XSSFCell cell = row.getCell(j);
                    if(cell == null) {
                        continue;
                    } else {
                        switch (cell.getCellType()){
                        case XSSFCell.CELL_TYPE_FORMULA:
                            str[i][j]=cell.getCellFormula();
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                        	str[i][j]=cell.getNumericCellValue()+"";
                            break;
                        case XSSFCell.CELL_TYPE_STRING:
                        	str[i][j]=cell.getStringCellValue()+"";
                            break;
                        case XSSFCell.CELL_TYPE_BLANK:
                        	str[i][j]=cell.getBooleanCellValue()+"";
                            break;
                        case XSSFCell.CELL_TYPE_ERROR:
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
