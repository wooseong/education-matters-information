import javax.swing.JButton;
import javax.swing.JPanel;

public class AdminPage extends JPanel {
    SelfCreateTable table;
    JButton newStudent;
    JButton newLecture;
    JButton openFile;
    
    public AdminPage() {
    	this.setLayout(null);
    	openFile = new JButton("파일 열기");
    	openFile.setBounds(10, 10, 100, 20);
    	this.add(openFile);
    	
    	newStudent = new JButton("학생 추가");
    	newStudent.setBounds(760, 10, 100, 20);
    	this.add(newStudent);
    	
    	newLecture = new JButton("수업 추가");
    	newLecture.setBounds(870, 10, 100, 20);
    	this.add(newLecture);
    }
}
