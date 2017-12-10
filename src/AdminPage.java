import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AdminPage extends JPanel {
	private SelfCreateTable table;
	private JButton newStudent;
	private JButton newLecture;
	private JButton openFile;
	private JFileChooser filechooser;
	private File file;
	private ButtonListener buttonListener;
    
    private String[][] data;

    public AdminPage() {
    	this.buttonListener = new ButtonListener();
    	
        this.setLayout(null);
        openFile = new JButton("파일 열기");
        openFile.setBounds(10, 10, 100, 20);
        openFile.addActionListener(buttonListener);
        this.add(openFile);

        filechooser = new JFileChooser();
        filechooser.setCurrentDirectory(new File("."));
        filechooser.setDialogTitle("Open File");
        filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        filechooser.setFileFilter(new FileNameExtensionFilter("Excel File", "csv", "xls","xlsx"));
        filechooser.setVisible(true);

        newStudent = new JButton("학생 추가");
        newStudent.setBounds(810, 10, 100, 20);
        newStudent.addActionListener(buttonListener);
        this.add(newStudent);

        newLecture = new JButton("수업 추가");
        newLecture.setBounds(920, 10, 100, 20);
        newLecture.addActionListener(buttonListener);
        this.add(newLecture);

        table = new SelfCreateTable();
        table.setBounds(10, 40, 1010, 600);
        this.add(table);
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
    
    public synchronized int addStudent(String sql) {
    	int result = 0;
    	Connection conn = null;
    	Statement stmt = null;
    	try {
    		conn = DriverManager.getConnection("jdbc:sqlite:" + DBconf.DB);
    		stmt = conn.createStatement();
    		result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {}
		}
    	
    	return result;
    }
    
    public class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Object obj = event.getSource();

            if (obj == openFile) {
                if(filechooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    file = filechooser.getSelectedFile();
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
            
            else if (obj == newStudent) {
            	Object[] option = {"추가", "취소"};
            	int choose = JOptionPane.showOptionDialog(table, "정말로 추가하시겠습니까?", "학생 추가", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
            	
            	switch(choose){
            	case JOptionPane.OK_OPTION:
            		for(int i=1; i<data.length; i++) {
            			String query = "insert into student values (" + data[i][0];
            			for(int j=1; j<data[i].length; j++) {
            				query += ",'" + data[i][j] + "'";
            			}
            			query += ")";
            			
            			System.out.println(query);
            			
            			if(addStudent(query) != 1) {
            				JOptionPane.showMessageDialog(table, "데이터를 저장하던 도중 문제가 발생하였습니다", "ERROR", JOptionPane.ERROR_MESSAGE);
            				break;
            			}
            		}
            		JOptionPane.showMessageDialog(table, "학생 추가에 성공하셨습니다", "SUCCESS", JOptionPane.PLAIN_MESSAGE);
            		break;
            	case JOptionPane.NO_OPTION:
            	default:
            		break;
            	}
            } // if(obj == newStudent)
            else if (obj == newLecture) {
            	Object[] option = {"추가", "취소"};
            	int choose = JOptionPane.showOptionDialog(table, "정말로 추가하시겠습니까?", "수업 추가", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
            	
            	switch(choose){
            	case JOptionPane.OK_OPTION:
            		for(int i=1; i<data.length; i++) {
            			String query = "insert into lecture values (" + data[i][0];
            			for(int j=1; j<data[i].length; j++) {
            				query += ",'" + data[i][j] + "'";
            			}
            			query += ")";
            			
            			System.out.println(query);
            			
            			if(addStudent(query) != 1) {
            				JOptionPane.showMessageDialog(table, "데이터를 저장하던 도중 문제가 발생하였습니다", "ERROR", JOptionPane.ERROR_MESSAGE);
            				break;
            			}
            		}
            		JOptionPane.showMessageDialog(table, "수업 추가에 성공하셨습니다", "SUCCESS", JOptionPane.PLAIN_MESSAGE);
            		break;
            	case JOptionPane.NO_OPTION:
            	default:
            		break;
            	}
            } // if (obj == newLecture)
        } // actionPerformed
    } // class ButtonListener
}
