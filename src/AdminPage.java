import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AdminPage extends JPanel {
    SelfCreateTable table;
    JButton newStudent;
    JButton newLecture;
    JButton openFile;
    JFileChooser filechooser;
    File file;

    public AdminPage() {
        this.setLayout(null);
        openFile = new JButton("파일 열기");
        openFile.setBounds(10, 10, 100, 20);
        openFile.addActionListener(new ButtonListener());
        this.add(openFile);

        filechooser = new JFileChooser();
        filechooser.setCurrentDirectory(new File("."));
        filechooser.setDialogTitle("Open File");
        filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        filechooser.setFileFilter(new FileNameExtensionFilter("Excel File", "csv", "xls","xlsx"));
        filechooser.setVisible(true);

        newStudent = new JButton("학생 추가");
        newStudent.setBounds(810, 10, 100, 20);
        this.add(newStudent);

        newLecture = new JButton("수업 추가");
        newLecture.setBounds(920, 10, 100, 20);
        this.add(newLecture);

        table = new SelfCreateTable();
        table.setBounds(10, 40, 1010, 550);
        this.add(table);
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
    
    public class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Object obj = event.getSource();

            if(obj == openFile) {
                if(filechooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    file = filechooser.getSelectedFile();
                    String[][] data = null;
                    System.out.println(getFileExtension(file));
                    switch(getFileExtension(file)) {
                    case "csv":
                    case "CSV":
                    	try {
                    		CsvReader csvreader = new CsvReader(file);
							data = csvreader.parse();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    	break;
                    case "xls":
                    case "XLS":
                   		XlsReader xlsreader = new XlsReader(file);
						data = xlsreader.parse();
                    	break;
                    case "xlsx":
                    case "XLSX":
                    	XlsxReader xlsxreader = new XlsxReader(file);
						data = xlsxreader.parse();
                    	break;
                    default:
                    	break;
                    }
                    if (data != null) {
                    	String[] col = data[0];
                    	String[][] rows = new String[data.length - 1][];
                    	for(int i=1; i<data.length; i++) {
                    		rows[i-1] = data[i];
                    	}
                    	table.makeTable(rows, col);
                    }
                } // if(filechooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            } // if(obj == openFile)
        } // actionPerformed
    } // class ButtonListener
}
