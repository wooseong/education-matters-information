import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class CurrentLectureMarks extends JPanel {
	private JPanel 			informationPanel;
	private JLabel 			IDLabel, nameLabel, birthLabel, enterLabel, 
							collegeDepGradeLabel;
	
	private JTextField		IDTextField, nameTextField, birthTextField, enterTextField,
							collegeDepGradeTextField;
	
	private SelfCreateTable table;
	
	public CurrentLectureMarks() {
		setPreferredSize(new Dimension(1030, 570));

		setBackground(new Color(0xEEDDDD));
		setLayout(null);

		informationPanel = new JPanel();
		informationPanel.setBounds(5, 5, 1020, 100);
		informationPanel.setBackground(new Color(0xEEEEEE));
		informationPanel.setLayout(null);
		informationPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.lightGray), "학생 기초 정보"));
		add(informationPanel);

		IDLabel = new JLabel("학번");
		IDLabel.setBounds(20, 20, 30, 30);
		informationPanel.add(IDLabel);

		IDTextField = new JTextField("");
		IDTextField.setBounds(60, 20, 100, 30);
		IDTextField.setEditable(false);
		informationPanel.add(IDTextField);
		
		nameLabel = new JLabel("성명");
		nameLabel.setBounds(190, 20, 30, 30);
		informationPanel.add(nameLabel);

		nameTextField = new JTextField("");
		nameTextField.setBounds(230, 20, 100, 30);
		nameTextField.setEditable(false);
		informationPanel.add(nameTextField);
		
		birthLabel = new JLabel("생년월일");
		birthLabel.setBounds(370, 20, 60, 30);
		informationPanel.add(birthLabel);

		birthTextField = new JTextField("");
		birthTextField.setBounds(440, 20, 100, 30);
		birthTextField.setEditable(false);
		informationPanel.add(birthTextField);
		
		enterLabel = new JLabel("입학일자");
		enterLabel.setBounds(570, 20, 60, 30);
		informationPanel.add(enterLabel);

		enterTextField = new JTextField("");
		enterTextField.setBounds(640, 20, 100, 30);
		enterTextField.setEditable(false);
		informationPanel.add(enterTextField);
		
		collegeDepGradeLabel = new JLabel("소속 및 학년");
		collegeDepGradeLabel.setBounds(20, 60, 80, 30);
		informationPanel.add(collegeDepGradeLabel);

		collegeDepGradeTextField = new JTextField("");
		collegeDepGradeTextField.setBounds(110, 60, 250, 30);
		collegeDepGradeTextField.setEditable(false);
		informationPanel.add(collegeDepGradeTextField);

		table = new SelfCreateTable();
		table.setBounds(5, 110, 1020, 535);
		this.add(table);
	}
	
	public void init() {
		this.birthTextField.setText(User.BIRTH);
		this.collegeDepGradeTextField.setText(User.COLLEGE + "  " + User.MAJOR + "  " + User.GRADE);
		this.enterTextField.setText(User.ENTER);
		this.IDTextField.setText(User.LOGINID);
		this.nameTextField.setText(User.USERNAME);
		
		String query = "select '2017/2', `2017/2`.lecture, `2017/2`.class, `lecture`.name, `2017/2`.midterm, `2017/2`.final, `2017/2`.score ";
		query		+= "from `2017/2` inner join `lecture` on `2017/2`.lecture=`lecture`.number ";
		query		+="where `2017/2`.id='" + User.LOGINID + "'";
		
		this.table.makeTable(query);
		
		String[] colum = {"연도", "학수번호", "분반", "수업명", "중간고사", "기말고사", "학점"};
		for(int i=0; i<colum.length; i++) {
			this.table.getTable().getColumnModel().getColumn(i).setHeaderValue(colum[i]);
		}
	}
}
